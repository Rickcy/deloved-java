package ru.deloved.admin

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.transform.EqualsAndHashCode
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import org.hibernate.criterion.CriteriaSpecification
import org.joda.time.DateTime
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import ru.deloved.*

import static org.springframework.http.HttpStatus.FORBIDDEN
import static org.springframework.http.HttpStatus.NOT_FOUND

@Transactional(readOnly = true)
@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_ACCOUNT','ROLE_MEDIATOR','ROLE_JUDGE') and isFullyAuthenticated()"])
class DisputeController extends FilteredController {

	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"

	def accountService
	def attachService
	def profileService

	def fileCategoryDirectory = new DefaultGrailsApplication().config?.fileCategory?.dispute ?: 'dispute'

	@Override
	protected FormFilter createFilterInstance() {
		return new DisputeFilter()
	}

	@Override
	protected PagedResultWrapper getRows(FormFilter filter) {
		DisputeFilter f = filter
		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()
		def regFilter = []
		if (user.role.authority =='ROLE_MEDIATOR') {
			regFilter = profileService.findAllCitiesByProfile(user.profile)
		}
		def rows = Dispute.createCriteria().list(max: filter.max, offset: filter.offset) {
			createAlias('account', 'account', CriteriaSpecification.LEFT_JOIN)
			createAlias('partner', 'partner', CriteriaSpecification.LEFT_JOIN)
			if (user.role.authority == 'ROLE_ACCOUNT') {
				or {
					inList("account", list)
					inList("partner", list)
				}
			} else if (user.role.authority =='ROLE_MEDIATOR') {
				or {
					inList("account.city", regFilter)
					inList("partner.city", regFilter)
				}
			}
			if (f.status != null) {
				eq("status", f.status)
			}
			if (f.search) {
				or {
					ilike("account.name", "%" + f.search + "%")
					ilike("partner.name", "%" + f.search + "%")
				}
			}
			order(filter.sort ?: "id", filter.order ?: "asc")
		}
		new PagedResultWrapper(rows)
	}

	def index(DisputeFilter disputeFilter) {
		processIndex(10, disputeFilter) {
			[model: [myAccounts: accountService.getMyAccounts()]]
		}
	}

	@Secured(["hasAnyRole('ROLE_ACCOUNT') and isFullyAuthenticated()"])
	def create() {
		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()

		if(list.contains(Account.findById(params.partner))) {
			flash.message = 'Вы не можете объявлять спор самому себе'
			redirect(base: uri,controller: 'company', action: 'index', id: params.partner)
			return
		}



		Deal deal = null
		if (params.deal) {
			def dl = Deal.withCriteria {
				eq("id", Long.parseLong(params.deal))
				or {
					inList("account", list)
					inList("partner", list)

				}
			}
			deal = dl.isEmpty() ? null : dl.get(0)
		}

		def cmd = new DisputeCreateCommand(
				account: list.size() == 1 ? list.get(0).id : null,
				partner: Long.valueOf(params.partner),
				deal: deal?.id
		)
		log.debug("params.partner:" + params.partner)
		log.debug("params:" + params)
		log.debug("list:" + list)
		log.debug("cmd:" + cmd)
		respond cmd, [model: [myAccounts: list.size() > 1 ? list : null, disputeCreateCommandInstance: cmd, partner: Account.load(cmd.partner)]]
	}

	def getLatestPosts(DisputePost lastPost) {
		if (!lastPost || !request.xhr) {
			render status: 404, text: 'NOT_FOUND'
			return
		}

		def latestPosts = DisputePost.executeQuery("""
			select post
			from DisputePost as post
			where post.dispute = :dispute
			and post.dateCreated > :date
		""", [dispute: lastPost.dispute, date: lastPost.dateCreated])

		if (latestPosts) {
			User user = springSecurityService.getCurrentUser()
			def list = accountService.getMyAccounts()

			def notMyAccount = null, myAccount = null
			if (list.contains(lastPost.dispute.mediator)) {
				myAccount = lastPost.dispute.mediator
				notMyAccount = lastPost.dispute.account
			} else if (list.contains(lastPost.dispute.account)) {
				myAccount = lastPost.dispute.account
				notMyAccount = lastPost.dispute.mediator
			}
			render status: 200, template: '/_common/thread-post', model: [
					threadPosts         : latestPosts,
					threadAccount       : myAccount ?: lastPost.dispute.account,
					threadStatusPrefix  : 'dispute.status',
					threadStatusTemplate: 'thread-status',
					myProfile: user.profile,
					notMyAccount: notMyAccount
			]
		} else {
			render status: 200, text: 'NO_CONTENT'
		}
	}
	@Transactional
	def save(DisputeCreateCommand disputeInstance) {
		if (disputeInstance == null) {
			notFound()
			return
		}

		if (disputeInstance.hasErrors()) {
			disputeInstance.errors.each { log.error(it) }
			respond disputeInstance.errors, view: 'create'
			return
		}
		Deal deal = disputeInstance.deal ? Deal.load(disputeInstance.deal) : null


		if (deal == null || deal.status() != DealStatus.Suspended) {
			def dispute = new Dispute(
					account: Account.load(disputeInstance.account),
					partner: Account.load(disputeInstance.partner),
					deal: deal,
					status: DisputeStatus.New.value()
			)
			log.debug "createDispute:" + dispute

			if (!dispute.save(flush: true)) {
				respond dispute.errors, [view: 'create', model: [disputeCreateCommandInstance: disputeInstance]]
			} else {
				User user = springSecurityService.getCurrentUser()
				def post = new DisputePost(
						dispute: dispute,
						account: dispute.account,
						person: user.profile,
						post: disputeInstance.subject
				)
				log.debug "createDisputePost:" + post
				post.save(flush: true)

				if (deal) {
					deal.status = DealStatus.Suspended.value()
					deal.save(flush: true)

					DealPost dp = new DealPost(
							deal: deal,
							account: dispute.account,
							person: user.profile,
							status: DealStatus.Suspended.value(),
							dispute: dispute
					)
					log.debug "createDealPost:" + dp
					dp.save(flush: true)

					AccountDealRating.findAllByDeal(deal).each {
						it.enable = 0
						it.save(flush: true)
						accountService.updateAccoutRating(it.account)
					}

				}

				flash.message = message(code: 'default.created.message', args: [message(code: 'dispute.label')])
				redirect base: uri,controller: 'dispute', action: 'thread', id: dispute.id
			}
		} else {
			flash.message = message(code: 'deal.suspended.message')
			redirect base: uri, controller: 'deal', action: 'thread', id: deal?.id
		}
	}

	@Transactional
	def deletepost(DisputePost post){
		if (!post || !request.xhr) {
			render status: 404, text: 'NOT_FOUND'
			return
		}
		User user = springSecurityService.getCurrentUser()
		if (user.role.authority != 'ROLE_ADMIN') {
			if (post.person != user.profile || post.dateCreated < DateTime.now().minusMinutes(60).toDate()) {
				render status: 403, text: 'FORBIDDEN'
				return
			}
		}
		def attToDel = []
		if (post.attachments) {
			for(int i=0; i < post.attachments.size(); i++) {
				DisputePostAttach dpa = post.attachments[i]
				if (dpa.attachment) {
					attToDel.push(dpa.attachment)
				}
				post.removeFromAttachments(dpa)
				dpa.delete(flush: true)
			}
		}
		attToDel.each {
			attachService.deleteAttach(fileCategoryDirectory, it) {
				return true
			}
		}
		post.delete(flush: true)
		render status: 200, text: 'SUCCESS'
	}


	def thread(Dispute disputeInstance) {
		if (disputeInstance?.id == null) {
			notFound()
			return
		}
		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()

		def myAccount = null
		if (list.contains(disputeInstance.partner)) {
			myAccount = disputeInstance.partner
		} else if (list.contains(disputeInstance.account)) {
			myAccount = disputeInstance.account
		}

		def attach = []
		def posts = DisputePost.findAllByDispute(disputeInstance)
		def statusList = getNextStatusList(disputeInstance, user)
		def status2List = [DealStatus.Proposed]
		def slist = DealPost.withCriteria {
			eq("deal", disputeInstance.deal)
			isNotNull("status")
		}
		slist.each {
			status2List << it.status()
		}
		log.debug("status2List:" + slist)

		attach.addAll(DisputePostAttach.findAllByDisputeAndPersonAndDisputePost(disputeInstance, user.profile, null))

		log.debug("next status:" + statusList)

		def canPost = false
		if (user.role.authority == 'ROLE_ACCOUNT' && (disputeInstance.status() in [DisputeStatus.New, DisputeStatus.Processing])) {
			canPost = true
		} else if (user.role.authority == 'ROLE_MEDIATOR' && (disputeInstance.status() == DisputeStatus.Processing)) {
			canPost = true
		}
		def dealUsers1 = []
		def dealUsers2 = []
		AccountProfile.findAllByAccount(disputeInstance.account).each { dealUsers1.add(it.profile) }
		AccountProfile.findAllByAccount(disputeInstance.partner).each { dealUsers2.add(it.profile) }

		respond disputeInstance, [model: [
				posts      : posts,
				myAccount  : myAccount,
				myProfile  : user.profile,
				statusList : statusList,
				status2List: status2List,
				attach     : attach,
				canPost    : canPost,
				dealUsers1 : dealUsers1,
				dealUsers2 : dealUsers2
		]]
	}


	@Transactional
	def post(Dispute disputeInstance) {
		if (disputeInstance?.id == null) {
			notFound()
			return
		}

		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()

		def canPost = false
		def canStatus = false
		def myAccount = null
		if (user.role.authority == 'ROLE_ACCOUNT') {

			if (list.contains(disputeInstance.account)) {
				myAccount = disputeInstance.account
			} else if (list.contains(disputeInstance.partner)) {
				myAccount = disputeInstance.partner
			}
			if (myAccount) {
				canPost = true
				canStatus = true
			}
		} else if (user.role.authority == 'ROLE_MEDIATOR') {
			canPost = (disputeInstance.status() == DisputeStatus.Processing)
			canStatus = ((disputeInstance.status() == DisputeStatus.New) || (disputeInstance.mediator == user.profile))
		}
		log.debug("canPost:" + canPost)
		log.debug("canStatus:" + canStatus)

		def post
		if (params.post && canPost) {
			post = new DisputePost(dispute: disputeInstance, account: myAccount, person: user.profile, post: params.post)

			DisputePostAttach.findAllByDisputeAndPersonAndDisputePost(disputeInstance, user.profile, null).each {
				log.debug("free file:" + it.attachment.name)
				it.disputePost = post
			}

		} else if (params.newstatus != null && canStatus) {
			def nextList = getNextStatusList(disputeInstance, user)
			log.debug("params.newstatus:" + params.newstatus)
			log.debug("nextList:" + nextList)
			DisputeStatus newStatus = DisputeStatus.valueOf(Integer.parseInt(params.newstatus))
			if (nextList && nextList.contains(newStatus)) {
				post = new DisputePost(dispute: disputeInstance, account: myAccount, person: user.profile, status: newStatus.value())
			}
		}
		if (post) {
			log.debug "createDisputePost:" + post
			if (post.status) {
				def dispute = Dispute.lock(disputeInstance.id)
				def nextList = getNextStatusList(dispute, user)
				if (canStatus && nextList && nextList.contains(post.status())) {
					if (dispute.status() == DisputeStatus.New) {
						dispute.mediator = user.profile
						def otherMediators = []
						dispute.newProfiles.each {
							if (it.user.role.authority == 'ROLE_MEDIATOR') {
								otherMediators.add(it)
							}
						}
						if (!otherMediators.isEmpty()) {
							log.debug("other mediators:" + otherMediators)
							dispute.newProfiles.removeAll(otherMediators)
						}
					}
					if (post.save(flush: true)) {
						flash.message = message(code: 'default.created.message', args: [message(code: 'disputepost.label')])
					} else {
						log.debug(post.errors)
					}
					dispute.status = post.status
					if (dispute.status() == DisputeStatus.Failed && params.failedSide) {
						dispute.failedBy = (params.failedSide as int == 2) ? dispute.partner : dispute.account
					} else {
						dispute.failedBy = null
					}
				}
				dispute.save(flush: true)
				log.debug(dispute.errors)
				log.debug(dispute.mediator)
				 if (dispute.status() == DisputeStatus.Failed && params.new2status) {
					DealStatus newStatus2 = DealStatus.valueOf(Integer.parseInt(params.new2status))
					DealPost dealPost = new DealPost(deal: dispute.deal, person: user.profile, status: newStatus2.value())
					dealPost.save(flush: true)
					dealPost.deal.status = dealPost.status
					dealPost.deal.failedBy = null
					dealPost.deal.save(flush: true)
				}

				if (dispute.status() == DisputeStatus.ResolveWS && params.new2status) {
					DealStatus newStatus2 = DealStatus.valueOf(Integer.parseInt(params.new2status))
					DealPost dealPost = new DealPost(deal: dispute.deal, person: user.profile, status: newStatus2.value())
					dealPost.save(flush: true)
					dealPost.deal.status = dealPost.status
					dealPost.deal.failedBy = null
					dealPost.deal.save(flush: true)
				}
			} else {
				if (post.save(flush: true)) {
					flash.message = message(code: 'default.created.message', args: [message(code: 'disputepost.label')])
				} else {
					log.debug(post.errors)
				}
			}
		}

		redirect base: uri,controller: 'dispute', action: 'thread', id: disputeInstance.id

	}

	@Transactional
	def upload(Dispute disputeInstance) {
		if (disputeInstance?.id == null) {
			notFound()
			return
		}

		boolean canUpload = false;
		User user = springSecurityService.getCurrentUser()
		def myAccount = null
		if (user.role.authority == 'ROLE_ACCOUNT') {
			def list = accountService.getMyAccounts()
			if (list.contains(disputeInstance.account)) {
				myAccount = disputeInstance.account
			} else if (list.contains(disputeInstance.partner)) {
				myAccount = disputeInstance.partner
			}
			if (myAccount) {
				canUpload = true
			}
		} else if (user.role.authority == 'ROLE_MEDIATOR') {
			if (disputeInstance.mediator == user.profile) {
				canUpload = true
			}
		}

		def files = []
		if (request instanceof MultipartHttpServletRequest) {
			for (filename in request.getFileNames()) {
				MultipartFile file = request.getFile(filename)

				if (canUpload) {
					def thumbnailUrl

					Attachment att = attachService.uploadAttach(file, fileCategoryDirectory, user.profile) {
						Attachment att, String icon ->
							att.save(flush: true)
							log.debug("att errors:" + att.errors)
							DisputePostAttach dpa = new DisputePostAttach(attachment: att, dispute: disputeInstance, person: user.profile, account: myAccount)
							dpa.save(flush: true)
							log.debug("dpa errors:" + dpa.errors)

							if (icon) {
								thumbnailUrl = icon
							} else {
								thumbnailUrl = createLink([action: 'thumb', id: att.id, params: [name: att.name]])
							}
					}

					files << [
							name        : att.name,
							size        : att.size,
							url         : createLink([action: 'download', id: att.id, params: [name: att.name]]),
							thumbnailUrl: thumbnailUrl,
							deleteUrl   : createLink(action: 'deleteatt', id: att.id, params: [name: att.name]),
							deleteType  : "DELETE"
					]
				} else {
					files << [
							name : file.originalFilename,
							size : file.size,
							error: "You can not upload to this deal"
					]
				}

			}
		}

		def results = [files: files]
		render results as JSON

	}
	@Transactional
	def deletedisp(Dispute disputeInstance){
		if (!disputeInstance || !request.xhr) {
			render status: 404, text: 'NOT_FOUND'
			return
		}
		//List<AccountDealRating> rating =AccountDealRating.findAllByDeal(deal)
		List<DisputeLastVisit> lastVisits = DisputeLastVisit.findAllByDispute(disputeInstance)
		List<DisputePost> post = DisputePost.findAllByDispute(disputeInstance)
		List<DealPost> dealPosts = DealPost.findAllByDispute(disputeInstance)
		//disputeInstance.deleteAll(rating)
		//disputeInstance.deal.setStatus(100)
		disputeInstance.deleteAll(lastVisits)
		disputeInstance.deleteAll(post)
		disputeInstance.deleteAll(dealPosts)
		disputeInstance.delete(flush: true)

		render status: 200, text: 'SUCCESS'
	}

	@Transactional
	def deleteatt() {
		User user = springSecurityService.getCurrentUser()
		boolean canDelete = false;
		Attachment att = Attachment.get(params.id)
		if (user.role.authority == 'ROLE_ADMIN') {
			canDelete = true
		} else if (user.role.authority == 'ROLE_MANAGER') {
			DisputePostAttach dpa = DisputePostAttach.findByAttachment(att)
			if (dpa?.account && profileService.findAllCitiesByProfile(user.profile).contains(dpa.account.city)) {
				canDelete = true
			}
		} else if (user.role.authority == 'ROLE_ACCOUNT') {
			DisputePostAttach dpa = DisputePostAttach.findByAttachment(att)
			if (dpa?.account && dpa.disputePost == null && accountService.isMyAccount(dpa.account)) {
				canDelete = true
			}
		} else if (user.role.authority == 'ROLE_MEDIATOR') {
			DisputePostAttach dpa = DisputePostAttach.findByAttachment(att)
			if (dpa && dpa.disputePost == null && dpa.dispute.mediator == user.profile) {
				canDelete = true
			}
		}
		log.debug("delete params:" + params)
		if (canDelete) {
			def result
			if (att && att.name == params.name) {
				attachService.deleteAttach(fileCategoryDirectory, att) {
					DisputePostAttach.findByAttachment(att).delete(flush: true)
					return true
				}
				result = [files: [["${att.name}": true]]]
				render result as JSON
			} else {
				result = [error: true]
				response.status = NOT_FOUND.value()
				render result as JSON
			}
		} else {
			render status: FORBIDDEN
		}
	}

	def thumb() {
		// TODO Acccess permissions check
		Attachment att = Attachment.get(params.id)
		if (att && att.name == params.name && att.isImage()) {
			attachService.createAndSendThumb(fileCategoryDirectory, att, response)
		} else {
			render status: NOT_FOUND
		}
	}

	def download() {
		// TODO Acccess permissions check
		log.debug("download params:" + params)
		Attachment att = Attachment.get(params.id)
		if (att && att.name == params.name) {
			def saveAs = true
			if (att.isImage() && params.preview != null) {
				saveAs = false
			}
			attachService.sendFile(fileCategoryDirectory, att, response, saveAs)
			return null
		} else {
			render status: NOT_FOUND
		}
	}


	protected void notFound() {
		request.withFormat {
			'*' {
				render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'dispute.label'), params.id])]
			}
			multipartForm { render status: NOT_FOUND }
			json { render status: NOT_FOUND }
		}
	}

	static List<DisputeStatus> getNextStatusList(Dispute dispute, User user) {
		if (user.role.authority == 'ROLE_MEDIATOR') {
			switch (dispute.status()) {
				case DisputeStatus.New: return [DisputeStatus.Processing];
				case DisputeStatus.Processing: return [DisputeStatus.ResolveWS,DisputeStatus.Failed];
			}
		}
		return null;
	}
}

class DisputeCreateCommand {
	Long account
	Long partner
	Long deal
	String subject

	static constraints = {
		deal nullable: true
		subject(blank: false)
	}

	@Override
	public String toString() {
		return "DisputeCreateCommand{" +
				"account=" + account +
				", partner=" + partner +
				", subject='" + subject + "} ";
	}
}


@EqualsAndHashCode
class DisputeFilter extends FormFilter {
	Integer status
	String search

	@Override
	protected String getDefaultSort() {
		return "dateCreated"
	}

	@Override
	protected String getDefaultOrder() {
		return "desc"
	}

	@Override
	protected List getFilterParamList() {
		["status", "search"]
	}

	@Override
	protected List getSortedParamList() {
		["dateCreated"]
	}

	@Override
	public Map getMap() {
		def m = super.getMap()
		if (status != null) m.status = status
		if (search != null) m.search = search
		return m
	}
}
