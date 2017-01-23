package ru.deloved.admin

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import grails.transaction.Transactional
import grails.util.Environment
import groovy.transform.EqualsAndHashCode
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import org.hibernate.criterion.CriteriaSpecification
import org.joda.time.DateTime
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import ru.deloved.*

import static org.springframework.http.HttpStatus.FORBIDDEN
import static org.springframework.http.HttpStatus.NOT_FOUND

@Transactional(readOnly = true)
@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_ACCOUNT','ROLE_MEDIATOR','ROLE_JUDGE','ROLE_JURIST') and isFullyAuthenticated()"])
class DealController extends FilteredController {
	def mailService
	def messageSource
	def accountService
	def attachService
	def profileService

	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"

	def fileCategoryDirectory = new DefaultGrailsApplication().config?.fileCategory?.deal ?: 'deal'

	@Override
	protected FormFilter createFilterInstance() {
		return new DealFilter()
	}

	@Override
	protected PagedResultWrapper getRows(FormFilter filter) {
		DealFilter f = filter
		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()
		def regFilter = []
		if (user.role.authority in ['ROLE_MEDIATOR', 'ROLE_JUDGE','ROLE_JURIST']) {
			regFilter = profileService.findAllCitiesByProfile(user.profile)
		}
		def rows = Deal.createCriteria().list(max: filter.max, offset: filter.offset) {
			createAlias('account', 'account', CriteriaSpecification.LEFT_JOIN)
			createAlias('partner', 'partner', CriteriaSpecification.LEFT_JOIN)
			if (user.role.authority == 'ROLE_ACCOUNT') {
				or {
					inList("account", list)
					inList("partner", list)
				}
			} else if (user.role.authority in ['ROLE_MEDIATOR', 'ROLE_JUDGE','ROLE_JURIST']) {
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

	def index(DealFilter dealFilter) {
		processIndex(10, dealFilter) {
			[model: [myAccounts: accountService.getMyAccounts()]]
		}
	}

	@Secured(["hasAnyRole('ROLE_ACCOUNT') and isFullyAuthenticated()"])
	def create() {


		def list = accountService.getMyAccounts()

		if(list.contains(Account.findById(params.partner))) {
			flash.message = 'Вы не можете предлагать сделку самому себе'
			redirect(base: uri,controller: 'company', action: 'index', id: params.partner)
			return
		}

		User user = springSecurityService.getCurrentUser()


		def cmd = new DealCreateCommand(
				account: list.size() == 1 ? list.get(0).id : null,
				partner: Long.valueOf(params.partner)
		)
		log.debug("params.partner:" + params.partner)
		log.debug("params:" + params)
		log.debug("list:" + list)
		log.debug("cmd:" + cmd)
		respond cmd, [model: [myAccounts: list.size() > 1 ? list : null, dealCreateCommandInstance: cmd, partner: Account.get(cmd.partner)]]
	}



	@Secured(["hasAnyRole('ROLE_ACCOUNT') and isFullyAuthenticated()"])
	def create2() {


		def list = accountService.getMyAccounts()

		if(list.contains(Account.findById(params.partner))) {
			flash.message = 'Вы не можете предлагать сделку самому себе'
			redirect(base: uri,controller: 'company', action: 'index', id: params.partner)
			return
		}

		User user = springSecurityService.getCurrentUser()


		def cmd = new DealCreateCommand(
				account: list.size() == 1 ? list.get(0).id : null,
				partner: Long.valueOf(params.partner)
		)
		log.debug("params.partner:" + params.partner)
		log.debug("params:" + params)
		log.debug("list:" + list)
		log.debug("cmd:" + cmd)
		respond cmd, [model: [myAccounts: list.size() > 1 ? list : null, dealCreateCommandInstance: cmd, partner: Account.get(cmd.partner)]]
	}





	def getLatestPosts(DealPost lastPost) {
		if (!lastPost || !request.xhr) {
			render status: 404, text: 'NOT_FOUND'
			return
		}

		def latestPosts = DealPost.executeQuery("""
			select post
			from DealPost as post
			where post.deal = :deal
			and post.dateCreated > :date

		""", [deal: lastPost.deal, date: lastPost.dateCreated])

		if (latestPosts) {
			User user = springSecurityService.getCurrentUser()
			def list = accountService.getMyAccounts()

			def notMyAccount = null, myAccount = null
			if (list.contains(lastPost.deal.partner)) {
				myAccount = lastPost.deal.partner
				notMyAccount = lastPost.deal.account
			} else if (list.contains(lastPost.deal.account)) {
				myAccount = lastPost.deal.account
				notMyAccount = lastPost.deal.partner
			}

			render status: 200, template: '/_common/thread-post', model: [
					threadPosts         : latestPosts,
					threadAccount       : myAccount ?: lastPost.deal.account,
					threadStatusPrefix  : 'deal.status',
					threadStatusTemplate: 'thread-status',
					myProfile: user.profile,
					notMyAccount: notMyAccount
			]
		} else {
			render status: 200, text: 'NO_CONTENT'
		}
	}



	def getCheckPoints(Deal dealInstance) {
		if (!dealInstance || !request.xhr) {
			render status: 404, text: 'NOT_FOUND'
			return
		}
		render template: 'check-points', model: [dealInstance: dealInstance]
	}


	def getStatus(Deal dealInstance) {
		if (!params.currentStatus || !dealInstance || !request.xhr) {
			render status: 404, text: 'NOT_FOUND'
			return
		}

		def result = []
		if (dealInstance.status == Integer.parseInt(params.currentStatus)) {
			render result as JSON
			return
		}

		def list = accountService.getMyAccounts()

		def myAccount = null
		if (list.contains(dealInstance.partner)) {
			myAccount = dealInstance.partner
		} else if (list.contains(dealInstance.account)) {
			myAccount = dealInstance.account
		}

		def statusName = message(code: 'deal.status.' + dealInstance.status(), default: dealInstance.status())

		List<DealStatus> statusList = getNextStatusList(dealInstance, myAccount)

		def resultStatusList = []

		if (statusList != null) {
		    for(int i = 0; i < statusList.size(); i ++) {
				resultStatusList.push([value: statusList[i].value(), name: message(code: 'deal.status.' + statusList[i] + '.i', default: statusList[i])])
			}
		}

		result = [currentStatus: [name: statusName, value: dealInstance.status, position: dealInstance.position()], statusList: resultStatusList]

		log.debug('DealController.newStatus.result: ' + result)

		render result as JSON

	}

	def thread(Deal dealInstance) {
		if (dealInstance?.id == null) {
			notFound()
			return
		}
		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()

		def myAccount = null, notMyAccount = null
		if (list.contains(dealInstance.partner)) {
			myAccount = dealInstance.partner
			notMyAccount = dealInstance.account
		} else if (list.contains(dealInstance.account)) {
			myAccount = dealInstance.account
			notMyAccount = dealInstance.partner
		}

		//def attach = []
		def posts = DealPost.findAllByDeal(dealInstance,[sort: 'dateCreated'])
		def statusList = []

		if (myAccount) {
			statusList = getNextStatusList(dealInstance, myAccount)
			//attach.addAll(DealPostAttach.findAllByDealAndAccountAndDealPost(dealInstance, myAccount, null))
		}

		def dealUsers1 = []
		def dealUsers2 = []
		AccountProfile.findAllByAccount(dealInstance.account).each { dealUsers1.add(it.profile) }
		AccountProfile.findAllByAccount(dealInstance.partner).each { dealUsers2.add(it.profile) }

		Boolean showReviewAlert = false;

		if (!Review.findByDealAndAuthor(dealInstance, user.profile) && dealInstance.status == DealStatus.Confirmed.value()) {
			showReviewAlert = true;
		}

		respond dealInstance, [model: [
				posts     : posts,
				myAccount : myAccount,
				notMyAccount: notMyAccount,
				myProfile : user.profile,
				showReviewAlert: showReviewAlert,
				statusList: statusList,
				dealUsers1: dealUsers1,
				dealUsers2: dealUsers2,
		]]
	}



	@Transactional
	def post(Deal dealInstance) {

		log.debug('ping')

		if (dealInstance?.id == null) {
			notFound()
			return
		}

		if (request.xhr == false) {
			render status: 403, text: 'FORBIDDEN'
		}

		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()

		def myAccount = null
		if (list.contains(dealInstance.account)) {
			myAccount = dealInstance.account
		} else if (list.contains(dealInstance.partner)) {
			myAccount = dealInstance.partner
		}
		if (myAccount == null) {
			flash.message = message(code: 'deal.cant.post', default: 'Вы не участвуете в сделке')
			redirect base: uri,controller: 'deal', action: 'index'
			return
		}

		def post
		if (params.post) {
			post = new DealPost(deal: dealInstance, account: myAccount, person: user.profile, post: params.post)

			DealPostAttach.findAllByDealAndAccountAndDealPost(dealInstance, myAccount, null).each {
				log.debug("free file:" + it.attachment.name)
				it.dealPost = post
			}

		} else if (params.newstatus) {
			def nextList = getNextStatusList(dealInstance, myAccount)
			log.debug("params.newstatus:" + params.newstatus)
			log.debug("nextList:" + nextList)
			DealStatus newSt = DealStatus.valueOf(Integer.parseInt(params.newstatus))
			if (nextList && nextList.contains(newSt)) {
				post = new DealPost(deal: dealInstance, account: myAccount, person: user.profile, status: newSt.value())
			}
		}
		if (post) {
			log.debug "createDealpost:" + post
			if (post.save(flush: true)) {
				if (post.status) {
					dealInstance.status = post.status
					dealInstance.save(flush: true)
					/**
					 * TODO уточнить по расчетам рейтинга
					 */
					if ([DealStatus.Confirmed].contains(dealInstance.status())) {
						AccountDealRating.findAllByDeal(dealInstance).each {
							it.enable = 1
							it.dealFail = 0
							it.dealSuccess = 1
							it.save(flush: true)
						}
					}
				}
				render status: 200
			} else {
				render status: 404
			}
		}
	}

	@Transactional
	def save(DealCreateCommand dealInstance) {

		/**
		 * Проверка на пустые входные данные
		 */

		if (dealInstance == null) {
			notFound()
			return
		}

		/**
		 * Проверка на наличие ошибок в данных
		 */

		if (dealInstance.hasErrors()) {
			dealInstance.errors.each { log.error(it) }
			respond dealInstance.errors, view: 'create'
			return
		}

		/**
		 * По умолчанию иницатор сделки (account) является покупателем (заказчиком). А акцептор (partner) - продавцом (исполнителем)
		 */

		def deal = new Deal(
				account: Account.load(dealInstance.account), // инициатор
				partner: Account.load(dealInstance.partner), // акцептор
				status: DealStatus.Proposed.value()
		)

		/**
		 * Если инициатор выбрал себе роль продавца то меняем продавца и покупателя в экземпляре домена сделки местами.
		 */

		if (params == null) {
			notFound()
			return
		}

		if (params.isBuyer == "false") {
			def t = deal.account
			deal.account = deal.partner
			deal.partner = t
		}

		//TODO

		log.debug "Buyer=" + deal.account + " Seller=" + deal.partner + " isBuyer=" + params.isBuyer

		log.debug "createDeal:" + deal

		if (!deal.save(flush: true)) {
			respond deal.errors, [view: 'create', model: [dealCreateCommandInstance: dealInstance]]
		} else {
			User user = springSecurityService.getCurrentUser()

//			def subj = messageSource.getMessage('contact.email.confirmation.subject', [].toArray(), 'Email confirmation', LocaleContextHolder.getLocale())
//			def mail = mailService.sendMail {
//				to deal.partner.email
//				subject subj
//				html view: '/account/newDeal', model: [deal: deal]
//			}
			def post = new DealPost(
					deal: deal,
					account: dealInstance.account, //Изначально было deal.account, но тогда получалось что инициатором сделки всегда был покупатель
					person: user.profile,
					post: dealInstance.subject,
					//status: deal.status
			)
			log.debug "createDealpost:" + post

			post.save(flush: true)

			log.debug "Initiator of Deal:" + DealPost.findByDeal(deal, [Order: 'id', max: 1]).account

			def r1 = new AccountDealRating(account: deal.account, deal: deal)
			log.debug("r1:" + r1)
			def save1 = r1.save(flush: true)
			log.debug("AccountDealRating account:" + save1)
			def r2 = new AccountDealRating(account: deal.partner, deal: deal)
			log.debug("r2:" + r2)
			def save2 = r2.save(flush: true)
			log.debug("AccountDealRating partner:" + save2)


			flash.message = message(code: 'default.created.message', args: [message(code: 'deal.label')])

			redirect base: uri,controller: 'deal', action: 'thread', id: deal.id
		}
	}

	@Transactional
	def deletepost(DealPost post){
		if (!post || !request.xhr) {
			render status: 404, text: 'NOT_FOUND'
			return
		}
		User user = springSecurityService.getCurrentUser()
		if (user.role.authority != 'ROLE_ADMIN') {
			if (post.person != user.profile || post.dateCreated < DateTime.now().minusMinutes(5).toDate()) {
				render status: 403, text: 'FORBIDDEN'
				return
			}
		}
		def attToDel = []
		if (post.attachments) {
			for(int i=0; i < post.attachments.size(); i++) {
				DealPostAttach dpa = post.attachments[i]
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
	@Transactional
	def deletedeal(Deal deal){
		if (!deal || !request.xhr) {
			render status: 404, text: 'NOT_FOUND'
			return
		}
		List<AccountDealRating> rating =AccountDealRating.findAllByDeal(deal)
		List<DealLastVisit> lastVisits = DealLastVisit.findAllByDeal(deal)
		List<DealPost> post = DealPost.findAllByDeal(deal)
		deal.deleteAll(rating)
		deal.deleteAll(lastVisits)
		deal.deleteAll(post)
		deal.delete(flush: true)

		render status: 200, text: 'SUCCESS'
	}
	/*
	@Transactional
	def upload(Deal dealInstance) {

		log.debug("DealController.Upload.params: " + params)

		if (dealInstance?.id == null) {
			notFound()
			return
		}

		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()

		def myAccount = null
		if (list.contains(dealInstance.account)) {
			myAccount = dealInstance.account
		} else if (list.contains(dealInstance.partner)) {
			myAccount = dealInstance.partner
		}

		def files = []
		if (request instanceof MultipartHttpServletRequest) {
			for (filename in request.getFileNames()) {
				MultipartFile file = request.getFile(filename)

				if (myAccount == null) {
					files << [
							name : file.originalFilename,
							size : file.size,
							error: g.message(code: 'deal.cant.upload', default: 'You can not upload to this deal')
					]
				} else {
					def thumbnailUrl

					Attachment att = attachService.uploadAttach(file, fileCategoryDirectory, user.profile) {
						Attachment att, String icon ->
							att.save(flush: true)
							log.debug("att errors:" + att.errors)
							DealPostAttach dpa = new DealPostAttach(attachment: att, deal: dealInstance, account: myAccount)
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
				}

			}
		}


		DealPost post = new DealPost(deal: dealInstance, account: myAccount, person: user.profile, post: null)

		def results = [files: files]

		log.debug("DealController.Upload.files: " + results)

		render results as JSON

	}
	*/

	@Transactional
	def upload(Deal dealInstance) {

		if (dealInstance?.id == null) {
			notFound()
			return
		}

		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()

		def myAccount = null
		if (list.contains(dealInstance.account)) {
			myAccount = dealInstance.account
		} else if (list.contains(dealInstance.partner)) {
			myAccount = dealInstance.partner
		}

		DealPost dealPost = new DealPost(deal: dealInstance, account: myAccount, person: user.profile)

		ArrayList<DealPostAttach> results = []

		if (request instanceof MultipartHttpServletRequest) {
			for (filename in request.getFileNames()) {
				MultipartFile file = request.getFile(filename)

				if (myAccount == null) {
					render status: 404, text: 'NOT_FOUND'
					return
				} else {
					Attachment att = attachService.uploadAttach(file, fileCategoryDirectory, user.profile) {
						Attachment att, String icon ->
						att.save(flush: true)
						log.debug("att errors:" + att.errors)
						DealPostAttach dpa = new DealPostAttach(attachment: att, deal: dealInstance, account: myAccount)
						if (dpa.save(flush: true)) {
							results.push(dpa)
						}
						log.debug("dpa errors:" + dpa.errors)
					}
				}
			}
		}

		if (results) {
			results.each {
				it.dealPost = dealPost
			}
			dealPost.save(flush: true)
			render status: 200, text: 'SUCCESS'
			return
		} else {
			render status: 500, text: 'ERROR'
			return
		}

	}


	@Transactional
	def deleteatt() {
		User user = springSecurityService.getCurrentUser()
		boolean canDelete = false;
		Attachment att = Attachment.get(params.id)
		if (user.role.authority == 'ROLE_ADMIN') {
			canDelete = true
		} else if (user.role.authority == 'ROLE_MANAGER') {
			DealPostAttach dpa = DealPostAttach.findByAttachment(att)
			if (dpa) {
				if (profileService.findAllCitiesByProfile(user.profile).contains(dpa.account.city)) {
					canDelete = true
				}
			}
		}
		else if (user.role.authority == 'ROLE_MEDIATOR') {
			DealPostAttach dpa = DealPostAttach.findByAttachment(att)
			if (dpa) {
				if (profileService.findAllCitiesByProfile(user.profile).contains(dpa.account.city)) {
					canDelete = true
				}
			}
		}
		else if (user.role.authority == 'ROLE_ACCOUNT') {
			DealPostAttach dpa = DealPostAttach.findByAttachment(att)
			if (dpa.dealPost == null && accountService.isMyAccount(dpa.account)) {
				canDelete = true
			}
		}
		log.debug("delete params:" + params)
		if (canDelete) {
			def result
			if (att && att.name == params.name) {
				attachService.deleteAttach(fileCategoryDirectory, att) {
					DealPostAttach.findByAttachment(att).delete(flush: true)
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
				render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'deal.label'), params.id])]
			}
			multipartForm { render status: NOT_FOUND }
			json { render status: NOT_FOUND }
		}
	}

	static List<DealStatus> getNextStatusList(Deal deal, Account side) {

   		switch (deal.status()) {
			case DealStatus.Proposed:
				if (side != DealPost.findByDeal(deal, [Order: 'id', max: 1]).account) {  //если пользователь инициатор сделки
					return [DealStatus.Discass, DealStatus.Rejected]
				} else {																 //если пользователь акцептор сделки
					return [DealStatus.Rejected]
				}
		}

		if (side == deal.partner) { // продавец
			switch (deal.status()) {
				case DealStatus.Discass: return [DealStatus.SellerSign, DealStatus.Rejected]
				case DealStatus.SellerSign: return [DealStatus.Rejected]
				case DealStatus.BuyerSign: return [DealStatus.SignUP, DealStatus.Rejected]
				case DealStatus.SignUP: return null

				case DealStatus.WaitNonPaidedExecute: return [DealStatus.NonPaidedExecute]
				case DealStatus.FullPrePaid: return [DealStatus.FullPrePaidConfirm]
				case DealStatus.HalfPrePaid: return [DealStatus.HalfPrePaidConfirm]

				case DealStatus.FullPostPaid: return [DealStatus.Confirmed]
				case DealStatus.WaitPaidedExecute: return [DealStatus.PaidedExecute]

				case DealStatus.WaitHalfPaidedExecute: return [DealStatus.HalfPaidedExecute]
				case DealStatus.HalfPostPaid: return [DealStatus.Confirmed]
			}
		} else { // покупатель
			switch (deal.status()) {
				case DealStatus.Discass: return [DealStatus.BuyerSign, DealStatus.Rejected]
				case DealStatus.BuyerSign: return [DealStatus.Rejected]
				case DealStatus.SellerSign: return [DealStatus.SignUP, DealStatus.Rejected]
				case DealStatus.SignUP: return [DealStatus.WaitNonPaidedExecute, DealStatus.FullPrePaid, DealStatus.HalfPrePaid]
				case DealStatus.NonPaidedExecute: return [DealStatus.FullPostPaid]
				case DealStatus.FullPrePaidConfirm: return [DealStatus.WaitPaidedExecute]
				case DealStatus.HalfPrePaidConfirm: return [DealStatus.WaitHalfPaidedExecute]
				case DealStatus.HalfPaidedExecute: return [DealStatus.HalfPostPaid]
				case DealStatus.PaidedExecute: return [DealStatus.Confirmed]
			}
		}
		return null
	}
}

class DealCreateCommand {
	Long account //покупатель
	Long partner //продавец
	String subject

	static constraints = {
		subject(blank: false)
	}

	@Override
	public String toString() {
		return "DealCreateCommand{" +
				"account=" + account +
				", partner=" + partner +
				", subject='" + subject + "} ";
	}
}

@EqualsAndHashCode
class DealFilter extends FormFilter {
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