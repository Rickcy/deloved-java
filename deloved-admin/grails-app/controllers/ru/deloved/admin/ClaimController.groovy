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
import ru.deloved.Claim
import ru.deloved.ClaimPost
import ru.deloved.ClaimPostAttach
import ru.deloved.ClaimLastVisit
import ru.deloved.ClaimStatus


import ru.deloved.*

import static org.springframework.http.HttpStatus.FORBIDDEN
import static org.springframework.http.HttpStatus.NOT_FOUND

@Transactional(readOnly = true)
@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_ACCOUNT','ROLE_JUDGE') and isFullyAuthenticated()"])
class ClaimController extends FilteredController {
	def accountService
	def attachService
	def profileService

	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"

	def fileCategoryDirectory = new DefaultGrailsApplication().config?.fileDirectory?.claim ?: 'claim'

	@Override
	protected FormFilter createFilterInstance() {
		return new ClaimFilter()
	}

	@Override
	protected PagedResultWrapper getRows(FormFilter filter) {
		ClaimFilter f = filter
		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()
		def regFilter = []
		if (user.role.authority in ['ROLE_JUDGE']) {
			regFilter = profileService.findAllCitiesByProfile(user.profile)
		}
		def rows = Claim.createCriteria().list(max: filter.max, offset: filter.offset) {
			createAlias('account', 'account', CriteriaSpecification.LEFT_JOIN)
			createAlias('partner', 'partner', CriteriaSpecification.LEFT_JOIN)
			if (user.role.authority == 'ROLE_ACCOUNT') {
				or {
					inList("account", list)
					inList("partner", list)
				}
			} else if (user.role.authority =='ROLE_JUDGE') {
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

	def index(ClaimFilter claimFilter) {
		processIndex(10, claimFilter) {
			[model: [myAccounts: accountService.getMyAccounts()]]
		}
	}

	@Secured(["hasAnyRole('ROLE_ACCOUNT') and isFullyAuthenticated()"])
	def create() {
		User user = springSecurityService.getCurrentUser()

		def list = accountService.getMyAccounts()

		if(list.contains(Account.findById(params.partner))) {
			flash.message = 'Вы не можете объявлять суд самому себе'
			redirect(base: uri, controller: 'company', action: 'index', id: params.partner)
			return
		}

		//User user = springSecurityService.getCurrentUser()

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

		def cmd = new ClaimCreateCommand(
				account: list.size() == 1 ? list.get(0).id : null,
				partner: Long.valueOf(params.partner),
				deal: deal?.id
		)
		log.debug("params.partner:" + params.partner)
		log.debug("params:" + params)
		log.debug("list:" + list)
		log.debug("cmd:" + cmd)
		respond cmd, [model: [myAccounts: list.size() > 1 ? list : null, claimCreateCommandInstance: cmd, partner: Account.load(cmd.partner)]]
	}

	@Transactional
	def deletepost(ClaimPost post){
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
				ClaimPostAttach cpa = post.attachments[i]
				if (cpa.attachment) {
					attToDel.push(cpa.attachment)
				}
				post.removeFromAttachments(cpa)
				cpa.delete(flush: true)
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

	@Transactional
	def save(ClaimCreateCommand claimInstance) {
		if (claimInstance == null) {
			notFound()
			return
		}

		if (claimInstance.hasErrors()) {
			claimInstance.errors.each { log.error(it) }
			respond claimInstance.errors, view: 'create'
			return
		}
		Deal deal = claimInstance.deal ? Deal.load(claimInstance.deal) : null

		if (deal == null || deal.status() != DealStatus.Suspended) {
			def claim = new Claim(
					account: Account.load(claimInstance.account),
					partner: Account.load(claimInstance.partner),
					deal: deal,
					status: ClaimStatus.New.value()
			)
			log.debug "createClaim:" + claim

			if (!claim.save(flush: true)) {
				respond claim.errors, [view: 'create', model: [claimCreateCommandInstance: claimInstance]]
			} else {
				User user = springSecurityService.getCurrentUser()
				def post = new ClaimPost(
						claim: claim,
						account: claim.account,
						person: user.profile,
						post: claimInstance.subject
				)
				log.debug "createClaimpost:" + post
				post.save(flush: true)

				if (deal) {
					deal.status = DealStatus.Suspended.value()
					deal.save(flush: true)

					DealPost dp = new DealPost(
							deal: deal,
							account: claim.account,
							person: user.profile,
							status: DealStatus.Suspended.value(),
							claim: claim
					)
					log.debug "createDealPost:" + dp
					dp.save(flush: true)

					AccountDealRating.findAllByDeal(deal).each {
						it.enable = 0
						it.save(flush: true)
						accountService.updateAccoutRating(it.account)
					}

				}

				flash.message = message(code: 'default.created.object', args: [message(code: 'claim.label')])
				redirect base: uri,controller: 'claim', action: 'thread', id: claim.id
			}
		} else {
			flash.message = message(code: 'deal.suspended.message')
			redirect base: uri, controller: 'deal', action: 'thread', id: deal?.id
		}
	}

	def getLatestPosts(ClaimPost lastPost) {
		if (!lastPost || !request.xhr) {
			render status: 404, text: 'NOT_FOUND'
			return
		}

		def latestPosts = ClaimPost.executeQuery("""
			select post
			from ClaimPost as post
			where post.claim = :claim
			and post.dateCreated > :date
		""", [claim: lastPost.claim, date: lastPost.dateCreated])

		if (latestPosts) {
			User user = springSecurityService.getCurrentUser()
			def list = accountService.getMyAccounts()

			def notMyAccount = null, myAccount = null
			if (list.contains(lastPost.claim.judge)) {
				myAccount = lastPost.claim.judge
				notMyAccount = lastPost.claim.account
			} else if (list.contains(lastPost.claim.account)) {
				myAccount = lastPost.claim.account
				notMyAccount = lastPost.claim.judge
			}
			render status: 200, template: '/_common/thread-post', model: [
					threadPosts         : latestPosts,
					threadAccount       : myAccount ?: lastPost.claim.account,
					threadStatusPrefix  : 'claim.status',
					threadStatusTemplate: 'thread-status',
					myProfile: user.profile,
					notMyAccount: notMyAccount
			]
		} else {
			render status: 200, text: 'NO_CONTENT'
		}
	}


	def thread(Claim claimInstance) {
		if (claimInstance?.id == null) {
			notFound()
			return
		}
		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()

		def myAccount = null
		if (list.contains(claimInstance.partner)) {
			myAccount = claimInstance.partner
		} else if (list.contains(claimInstance.account)) {
			myAccount = claimInstance.account
		}

		def attach = []
		def posts = ClaimPost.findAllByClaim(claimInstance)
		def statusList = getNextStatusList(claimInstance, user)
		def status2List = [DealStatus.Proposed]
		def slist = DealPost.withCriteria {
			eq("deal", claimInstance.deal)
			isNotNull("status")
		}
		slist.each {
			status2List << it.status()
		}
		log.debug("status2List:" + slist)

		attach.addAll(ClaimPostAttach.findAllByClaimAndPersonAndClaimPost(claimInstance, user.profile, null))

		log.debug("next status:" + statusList)

		def canPost = false
		if (user.role.authority == 'ROLE_ACCOUNT' && (claimInstance.status() in [ClaimStatus.New, ClaimStatus.Processing])) {
			canPost = true
		} else if (user.role.authority == 'ROLE_JUDGE' &&  (claimInstance.status() in [ClaimStatus.New, ClaimStatus.Processing])) {
			canPost = true
		}
		def dealUsers1 = []
		def dealUsers2 = []
		AccountProfile.findAllByAccount(claimInstance.account).each { dealUsers1.add(it.profile) }
		AccountProfile.findAllByAccount(claimInstance.partner).each { dealUsers2.add(it.profile) }

		respond claimInstance, [model: [
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
	def post(Claim claimInstance) {
		if (claimInstance?.id == null) {
			notFound()
			return
		}

		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()

		def canPost = true
		def canStatus = true
		def myAccount = null
		if (user.role.authority == 'ROLE_ACCOUNT') {

			if (list.contains(claimInstance.account)) {
				myAccount = claimInstance.account
			} else if (list.contains(claimInstance.partner)) {
				myAccount = claimInstance.partner
			}
			if (myAccount) {
				canPost = true
				canStatus = true
			}
		} else if (user.role.authority == 'ROLE_JUDGE') {
			canPost = (claimInstance.status() in [ClaimStatus.New, ClaimStatus.Processing])
			canStatus = ((claimInstance.status() in [ClaimStatus.New, ClaimStatus.Return]) || (claimInstance.judge == user.profile))
		}
		log.debug("canPost:" + canPost)
		log.debug("canStatus:" + canStatus)

		def post
		if (params.post && canPost) {
			post = new ClaimPost(claim: claimInstance, account: myAccount, person: user.profile, post: params.post)

			ClaimPostAttach.findAllByClaimAndPersonAndClaimPost(claimInstance, user.profile, null).each {
				log.debug("free file:" + it.attachment.name)
				it.claimPost = post
			}

		} else if (params.newstatus && canStatus) {
			def nextList = getNextStatusList(claimInstance, user)
			log.debug("params.newstatus:" + params.newstatus)
			log.debug("nextList:" + nextList)
			ClaimStatus newStatus = ClaimStatus.valueOf(Integer.parseInt(params.newstatus))
			if (nextList && nextList.contains(newStatus)) {
				post = new ClaimPost(claim: claimInstance, account: myAccount, person: user.profile, status: newStatus.value())
			}
		}
		if (post) {
			log.debug "createClaimPost:" + post
			if (post.status) {
				def claim = Claim.lock(claimInstance.id)
				def nextList = getNextStatusList(claim, user)
				if (canStatus && nextList && nextList.contains(post.status())) {
					if (claim.status() == ClaimStatus.New) {
						claim.judge = user.profile
						def otherJudges = []
						claim.newProfiles.each {
							if (it.user.role.authority == 'ROLE_JUDGE') {
								otherJudges.add(it)
							}
						}
						if (!otherJudges.isEmpty()) {
							log.debug("other judges:" + otherJudges)
							claim.newProfiles.removeAll(otherJudges)
						}
					}
					if (post.save(flush: true)) {
						flash.message = message(code: 'default.created.message', args: [message(code: 'claimpost.label')])
					} else {
						log.debug(post.errors)
					}
					claim.status = post.status
					if (claim.status() == ClaimStatus.Failed && params.failedSide) {
						claim.failedBy = (params.failedSide as int == 2) ? claim.partner : claim.account
					} else {
						claim.failedBy = null
					}
				}
				claim.save(flush: true)
				log.debug(claim.errors)
				log.debug(claim.judge)
				if (claim.deal) {
					if (claim.status() == ClaimStatus.Failed) {
						DealPost dealPost = new DealPost(deal: claim.deal, person: user.profile, status: DealStatus.Failed.value())
						dealPost.save(flush: true)
						dealPost.deal.status = dealPost.status
						dealPost.deal.failedBy = claim.failedBy
						dealPost.deal.save(flush: true)

						AccountDealRating.findAllByDeal(claim.deal).each {
							it.enable = 1
							if (it.accountId == dealPost.deal.failedById) {
								it.badClaim = 1
							}
							it.save(flush: true)
							accountService.updateAccoutRating(it.account)
						}

					} else if (claim.status() == ClaimStatus.Resolve && params.new2status) {
						DealStatus newStatus2 = DealStatus.valueOf(Integer.parseInt(params.new2status))
						DealPost dealPost = new DealPost(deal: claim.deal, person: user.profile, status: newStatus2.value())
						dealPost.save(flush: true)
						dealPost.deal.status = dealPost.status
						dealPost.deal.failedBy = null
						dealPost.deal.save(flush: true)
					}
					else if (claim.status() == ClaimStatus.Return && params.new2status) {
						DealStatus newStatus2 = DealStatus.valueOf(Integer.parseInt(params.new2status))
						DealPost dealPost = new DealPost(deal: claim.deal, person: user.profile, status: newStatus2.value())
						dealPost.save(flush: true)
						dealPost.deal.status = dealPost.status
						dealPost.deal.failedBy = null
						dealPost.deal.save(flush: true)
					}
					if (claim.status() == ClaimStatus.ResolveWS) {
						DealPost dealPost = new DealPost(deal: claim.deal, person: user.profile, status: DealStatus.Failed.value())
						dealPost.save(flush: true)
						dealPost.deal.status = dealPost.status
						dealPost.deal.failedBy = claim.failedBy
						dealPost.deal.save(flush: true)

						AccountDealRating.findAllByDeal(claim.deal).each {
							it.enable = 1
							if (it.accountId == dealPost.deal.failedById) {
								it.badDispute = 1
							}
							it.save(flush: true)
							accountService.updateAccoutRating(it.account)
						}
					}

				}
			} else {
				if (post.save(flush: true)) {
					flash.message = message(code: 'default.created.message', args: [message(code: 'Сообщение')])
				} else {
					log.debug(post.errors)
				}
			}
		}

		redirect base: uri,controller: 'claim', action: 'thread', id: claimInstance.id

	}

	@Transactional
	def upload(Claim claimInstance) {
		if (claimInstance?.id == null) {
			notFound()
			return
		}

		boolean canUpload = false;
		User user = springSecurityService.getCurrentUser()
		def myAccount = null
		if (user.role.authority == 'ROLE_ACCOUNT') {
			def list = accountService.getMyAccounts()
			if (list.contains(claimInstance.account)) {
				myAccount = claimInstance.account
			} else if (list.contains(claimInstance.partner)) {
				myAccount = claimInstance.partner
			}
			if (myAccount) {
				canUpload = true
			}
		} else if (user.role.authority == 'ROLE_JUDGE') {
			if (claimInstance.judge == user.profile) {
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
							ClaimPostAttach dpa = new ClaimPostAttach(attachment: att, claim: claimInstance, person: user.profile, account: myAccount)
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
							error: "You can not upload to this claim"
					]
				}

			}
		}

		def results = [files: files]
		render results as JSON

	}



	@Transactional
	def deleteclaim(Claim claimInstance){
		if (!claimInstance || !request.xhr) {
			render status: 404, text: 'NOT_FOUND'
			return
		}
		//List<AccountDealRating> rating =AccountDealRating.findAllByDeal(deal)
		List<ClaimLastVisit> lastVisits = ClaimLastVisit.findAllByClaim(claimInstance)
		List<ClaimPost> post = ClaimPost.findAllByClaim(claimInstance)
		List<DealPost> dealPosts = DealPost.findAllByClaim(claimInstance)
		//disputeInstance.deleteAll(rating)
		//claimInstance.deal.setStatus(100)
		claimInstance.deleteAll(lastVisits)
		claimInstance.deleteAll(post)
		claimInstance.deleteAll(dealPosts)
		claimInstance.delete(flush: true)

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
			ClaimPostAttach cpa = ClaimPostAttach.findByAttachment(att)
			if (cpa?.account && profileService.findAllCitiesByProfile(user.profile).contains(cpa.account.city)) {
				canDelete = true
			}
		} else if (user.role.authority == 'ROLE_ACCOUNT') {
			ClaimPostAttach cpa = ClaimPostAttach.findByAttachment(att)
			if (cpa?.account && cpa.claimPost == null && accountService.isMyAccount(cpa.account)) {
				canDelete = true
			}
		} else if (user.role.authority == 'ROLE_JUDGE') {
			ClaimPostAttach cpa = ClaimPostAttach.findByAttachment(att)
			if (cpa && cpa.claimPost == null && cpa.claim.judge == user.profile) {
				canDelete = true
			}
		}
		log.debug("delete params:" + params)
		if (canDelete) {
			def result
			if (att && att.name == params.name) {
				attachService.deleteAttach(fileCategoryDirectory, att) {
					ClaimPostAttach.findByAttachment(att).delete(flush: true)
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
				render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'claim.label'), params.id])]
			}
			multipartForm { render status: NOT_FOUND }
			json { render status: NOT_FOUND }
		}
	}

	static List<ClaimStatus> getNextStatusList(Claim claim, User user) {
		if (user.role.authority == 'ROLE_JUDGE') {
			switch (claim.status()) {
				case ClaimStatus.New: return [ClaimStatus.Processing,ClaimStatus.Return];
				case ClaimStatus.Processing: return [ClaimStatus.ResolveWS,ClaimStatus.Resolve, ClaimStatus.Failed];

			}
		}
		return null;
	}

}

class ClaimCreateCommand {
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
		return "ClaimCreateCommand{" +
				"account=" + account +
				", partner=" + partner +
				", subject='" + subject + "} ";
	}
}


@EqualsAndHashCode
class ClaimFilter extends FormFilter {
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
