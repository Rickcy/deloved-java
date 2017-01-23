package ru.deloved.admin

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.transform.EqualsAndHashCode
import jline.internal.Log
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import org.hibernate.criterion.CriteriaSpecification
import org.joda.time.DateTime
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import ru.deloved.*

import ru.deloved.recall.Ticket
import ru.deloved.recall.TicketPost
import ru.deloved.recall.TicketPostAttach
import ru.deloved.recall.TicketLastVisit
import ru.deloved.recall.TicketStatus

import static org.springframework.http.HttpStatus.FORBIDDEN
import static org.springframework.http.HttpStatus.NOT_FOUND


/*TODO
Добавить ролей для тикетов. Что-то вроде ROLE_TECH
 */
@Transactional(readOnly = true)
@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_ACCOUNT','ROLE_JUDGE','ROLE_MEDIATOR','ROLE_JURIST') and isFullyAuthenticated()"])
class TicketController extends FilteredController {
	def accountService
	def attachService
	def profileService
//GetAccounts
	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"
	def fileCategoryDirectory = new DefaultGrailsApplication().config?.fileCategory?.ticket ?: 'ticket'

	@Override
	protected FormFilter createFilterInstance() {
		return new TicketFilter()
	}

	@Override
	protected PagedResultWrapper getRows(FormFilter filter) {
		TicketFilter f = filter
		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()
		def regFilter = []
		if (user.role.authority in ['ROLE_MANAGER']) {
			regFilter = profileService.findAllCitiesByProfile(user.profile)
		}
		def rows = Ticket.createCriteria().list(max: filter.max, offset: filter.offset) {
			createAlias('account', 'account', CriteriaSpecification.LEFT_JOIN)

			if (user.role.authority in ['ROLE_ACCOUNT','ROLE_JUDGE','ROLE_MEDIATOR','ROLE_JURIST']) {
				inList("account", list)
			} else if (user.role.authority in ['ROLE_MANAGER']) {
				inList("account.city", regFilter)
				or {
					eq("support", user.profile)
					eq("status", TicketStatus.New.value())
				}
			}
			if (f.status != null) {
				eq("status", f.status)
			}
			if (f.search) {
				or {
					ilike("account.name", "%" + f.search + "%")
				}
			}
			order(filter.sort ?: "dateCreated", filter.order ?: "desc")
		}
		new PagedResultWrapper(rows)
	}

	def index(TicketFilter ticketFilter) {
		processIndex(10, ticketFilter) {
			[model: [myAccounts: accountService.getMyAccounts()]]

		}
	}

	@Secured(["hasAnyRole('ROLE_ACCOUNT','ROLE_JUDGE','ROLE_MEDIATOR','ROLE_JURIST') and isFullyAuthenticated()"])

	def create() {
		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()

		def cmd = new TicketCreateCommand(
				account: list.size() == 1 ? list.get(0).id : null,
		)
		respond cmd, [model: [myAccounts: list.size() > 1 ? list : null, ticketCreateCommandInstance: cmd]]
	}

	def getLatestPosts(TicketPost lastPost) {
		if (!lastPost || !request.xhr) {
			render status: 404, text: 'NOT_FOUND'
			return
		}

		def latestPosts = TicketPost.executeQuery("""
			select post
			from TicketPost as post
			where post.ticket = :ticket
			and post.dateCreated > :date
		""", [ticket: lastPost.ticket, date: lastPost.dateCreated])

		if (latestPosts) {
			User user = springSecurityService.getCurrentUser()
			def list = accountService.getMyAccounts()

			def notMyAccount = null, myAccount = null
			if (list.contains(lastPost.ticket.support)) {
				myAccount = lastPost.person
				notMyAccount = lastPost.account
			} else if (list.contains(lastPost.ticket.account)) {
				myAccount = lastPost.account
				notMyAccount = lastPost.person
			}

			render status: 200, template: '/_common/thread-post', model: [
					threadPosts         : latestPosts,
					threadAccount       : myAccount ?: lastPost.ticket.account,
					threadStatusPrefix  : 'ticket.status',
					threadStatusTemplate: 'thread-status',
					myProfile: user.profile,
					notMyAccount: notMyAccount
			]
		} else {
			render status: 200, text: 'NO_CONTENT'
		}
	}

	def getStatus(Ticket ticketInstance) {
		if (!params.currentStatus || !ticketInstance || !request.xhr) {
			render status: 404, text: 'NOT_FOUND'
			return
		}

		def result = []
		if (ticketInstance.status == Integer.parseInt(params.currentStatus)) {
			render result as JSON
			return
		}

		def list = accountService.getMyAccounts()

		def myAccount = null
		if (list.contains(ticketInstance.support)) {
			myAccount = ticketInstance.support
		} else if (list.contains(ticketInstance.account)) {
			myAccount = ticketInstance.account
		}

		def statusName = message(code: 'ticket.status.' + ticketInstance.status(), default: ticketInstance.status())

		List<TicketStatus> statusList = getNextStatusList(ticketInstance, myAccount)

		def resultStatusList = []

		if (statusList != null) {
			for(int i = 0; i < statusList.size(); i ++) {
				resultStatusList.push([value: statusList[i].value(), name: message(code: 'ticket.status.' + statusList[i] + '.i', default: statusList[i])])
			}
		}

		result = [currentStatus: [name: statusName, value: ticketInstance.status, position: ticketInstance.position()], statusList: resultStatusList]

		log.debug('TicketController.newStatus.result: ' + result)

		render result as JSON

	}


	def thread(Ticket ticketInstance) {
		if (ticketInstance?.id == null) {
			notFound()
			return
		}
		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()

		def myAccount = null
		if (list.contains(ticketInstance.support)) {
			myAccount = ticketInstance.support
		} else if (list.contains(ticketInstance.account)) {
			myAccount = ticketInstance.account
		}

		def attach = []
		def posts = TicketPost.findAllByTicket(ticketInstance)
		def statusList = getNextStatusList(ticketInstance, user)
		if (myAccount) {
			attach.addAll(TicketPostAttach.findAllByTicketAndAccountAndTicketPost(ticketInstance, myAccount, null))
		}

		def canPost = false
		if (user.role.authority in ['ROLE_ACCOUNT','ROLE_JUDGE','ROLE_MEDIATOR','ROLE_JURIST'] && (ticketInstance.status() in [TicketStatus.New, TicketStatus.Processing])) {
			canPost = true
		} else if (user.role.authority == 'ROLE_MANAGER' && (ticketInstance.status() == TicketStatus.Processing) && ticketInstance.support == user.profile) {
			canPost = true
		}
		respond ticketInstance, [model: [posts: posts, myAccount: myAccount, myProfile: user.profile, statusList: statusList, attach: attach, canPost: canPost]]
	}

	@Transactional
	def deleteTick(Ticket ticketInstance){
		if (!ticketInstance || !request.xhr) {
			render status: 404, text: 'NOT_FOUND'
			return
		}
		//List<AccountDealRating> rating =AccountDealRating.findAllByDeal(deal)
		List<TicketLastVisit> lastVisits = TicketLastVisit.findAllByTicket(ticketInstance)
		List<TicketPost> post = TicketPost.findAllByTicket(ticketInstance)

		//disputeInstance.deleteAll(rating)

		ticketInstance.deleteAll(lastVisits)
		ticketInstance.deleteAll(post)
		ticketInstance.delete(flush: true)

		render status: 200, text: 'SUCCESS'
	}


	@Transactional
	def deletepost(TicketPost post){
		if (!post || !request.xhr) {
			render status: 404, text: 'NOT_FOUND'
			return
		}
		User user = springSecurityService.getCurrentUser()
		if (user.role.authority != 'ROLE_ADMIN') {
			if (post.person != user.profile || post.dateCreated < DateTime.now().minusMinutes(30).toDate()) {
				render status: 403, text: 'FORBIDDEN'
				return
			}
		}
		def attToDel = []
		if (post.attachments) {
			for(int i=0; i < post.attachments.size(); i++) {
				TicketPostAttach tpa = post.attachments[i]
				if (tpa.attachment) {
					attToDel.push(tpa.attachment)
				}
				post.removeFromAttachments(tpa)
				tpa.delete(flush: true)
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
	def post(Ticket ticketInstance) {
		if (ticketInstance?.id == null) {
			notFound()
			return
		}

		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()
		def myAccount = null

		boolean canPost = false
		boolean canStatus = false
		if (user.role.authority == 'ROLE_ADMIN') {
			canPost = true

		}  else if (user.role.authority =='ROLE_MANAGER') {

				canPost = (ticketInstance.status() == TicketStatus.Processing)
				canStatus = ((ticketInstance.status() == TicketStatus.New) || (ticketInstance.support == user.profile))

		} else if (user.role.authority in ['ROLE_ACCOUNT','ROLE_JUDGE','ROLE_MEDIATOR','ROLE_JURIST']) {
			if (list.contains(ticketInstance.account)) {
				myAccount = ticketInstance.account
			}
			if (accountService.isMyAccount(ticketInstance.account)) {
				canPost = myAccount != null
				canStatus = (ticketInstance.status() in [TicketStatus.New, TicketStatus.Processing])
			}
		}

		log.debug("canPost:" + canPost)
		log.debug("canStatus:" + canStatus)


		if (!canPost && !canStatus) {
			/*TODO
			обработать ошибку
			 */
			//flash.message = message(code: 'ticket.cant.post', default: 'Вы не участвуете в сделке')
			redirect base: uri,controller: 'ticket', action: 'index'
			return
		}

		def post
		if (params.post && canPost) {
			post = new TicketPost(ticket: ticketInstance, account: myAccount, person: user.profile, post: params.post)

			TicketPostAttach.findAllByTicketAndAccountAndTicketPost(ticketInstance, myAccount, null).each {
				log.debug("free file:" + it.attachment.name)
				it.ticketPost = post
			}
		} else if (params.newstatus && canStatus) {
			def nextList = getNextStatusList(ticketInstance, user)
			log.debug("params.newstatus:" + params.newstatus)
			log.debug("nextList:" + nextList)
			TicketStatus newStatus = TicketStatus.valueOf(Integer.parseInt(params.newstatus))
			if (nextList && nextList.contains(newStatus)) {
				post = new TicketPost(ticket: ticketInstance, account: myAccount, person: user.profile, status: newStatus.value())
			}
		}
		if (post) {
			log.debug "createTicketpost:" + post
			if (post.status) {
				def ticket = Ticket.lock(ticketInstance.id)
				def nextList = getNextStatusList(ticket, user)
				if (canStatus && nextList && nextList.contains(post.status())) {
					if (ticket.status() == TicketStatus.New) {
						if (user.role.authority == 'ROLE_MANAGER') {
							ticket.support = user.profile
						}
						ticket.newProfiles.clear()
					}
					if (post.save(flush: true)) {
						/*TODO
						обработать
						 */
						flash.message = message(code: 'default.created.message', args: [message(code: 'ticketpost.label')])
					} else {
						log.debug(post.errors)
					}
					ticket.status = post.status
				}
				ticket.save(flush: true)

			} else {
				if (post.save(flush: true)) {
					/*TODO
						обработать
						 */
					flash.message = message(code: 'default.created.ticket', args: [message(code: 'ticket.label')])
				} else {
					log.debug(post.errors)
				}
			}
		}

		redirect base: uri,controller: 'ticket', action: 'thread', id: ticketInstance.id

	}

	@Transactional
	def save(TicketCreateCommand ticketInstance) {
		if (ticketInstance == null) {
			notFound()
			return
		}

		if (ticketInstance.hasErrors()) {
			ticketInstance.errors.each { log.error(it) }
			respond ticketInstance.errors, view: 'create'
			return
		}

		def ticket = new Ticket(
				account: Account.load(ticketInstance.account),
				status: TicketStatus.New.value()
		)

		log.debug "createTicket:" + ticket

		if (!ticket.save(flush: true)) {
			respond ticket.errors, [view: 'create', model: [ticketCreateCommandInstance: ticketInstance]]
		} else {
			User user = springSecurityService.getCurrentUser()
			def post = new TicketPost(
					ticket: ticket,
					account: ticket.account,
					person: user.profile,
					post: ticketInstance.subject
			)
			log.debug "createTicketpost:" + post
			post.save(flush: true)

			flash.message = message(code: 'default.created.measure', args: [message(code: 'ticket.label')])
			redirect base: uri,controller: 'ticket', action: 'thread', id: ticket.id
		}
	}
/*
	@Transactional
	def upload(Ticket ticketInstance) {
		if (tickettInstance?.id == null) {
			notFound()
			return
		}

		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()

		def myAccount = null
		if (list.contains(ticketInstance.account)) {
			myAccount = ticketInstance.account
		}

		def files = []
		if (request instanceof MultipartHttpServletRequest) {
			for (filename in request.getFileNames()) {
				MultipartFile file = request.getFile(filename)

				if (myAccount == null) {
					files << [
							name : file.originalFilename,
							size : file.size,
							error: g.message(code: 'ticket.cant.upload', default: 'You can not upload to this ticket')
					]
				} else {
					def thumbnailUrl

					Attachment att = attachService.uploadAttach(file, fileCategoryDirectory, user.profile) {
						Attachment att, String icon ->
							att.save(flush: true)
							log.debug("att errors:" + att.errors)
							TicketPostAttach dpa = new TicketPostAttach(attachment: att, ticket: ticketInstance, account: myAccount)
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

		def results = [files: files]
		render results as JSON

	}
*/
	@Transactional
	def upload(Ticket ticketInstance) {

		if (ticketInstance?.id == null) {
			notFound()
			return
		}

		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()

		def myAccount = null
		if (list.contains(ticketInstance.account)) {
			myAccount = ticketInstance.account
		} else if (list.contains(ticketInstance.support)) {
			myAccount = ticketInstance.support
		}

		TicketPost ticketPost = new TicketPost(ticket: ticketInstance, account: myAccount, person: user.profile)

		ArrayList<TicketPostAttach> results = []

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
							TicketPostAttach dpa = new TicketPostAttach(attachment: att, ticket: ticketInstance, account: myAccount)
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
				it.ticketPost = ticketPost
			}
			ticketPost.save(flush: true)
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
			TicketPostAttach dpa = TicketPostAttach.findByAttachment(att)
			if (dpa) {
				if (profileService.findAllCitiesByProfile(user.profile).contains(dpa.account.city)) {
					canDelete = true
				}
			}
		} else if (user.role.authority in ['ROLE_ACCOUNT','ROLE_JUDGE','ROLE_MEDIATOR','ROLE_JURIST']) {
			TicketPostAttach dpa = TicketPostAttach.findByAttachment(att)
			if (dpa.ticketPost == null && accountService.isMyAccount(dpa.account)) {
				canDelete = false
			}
		}
		log.debug("delete params:" + params)
		if (canDelete) {
			def result
			if (att && att.name == params.name) {
				attachService.deleteAttach(fileCategoryDirectory, att) {
					TicketPostAttach.findByAttachment(att).delete(flush: true)
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

	static List<TicketStatus> getNextStatusList(Ticket ticket, User user) {
		if (user.role.authority == 'ROLE_MANAGER') {
			switch (ticket.status()) {
				case TicketStatus.New: return [TicketStatus.Processing];
				case TicketStatus.Processing: return [TicketStatus.Closed];
			}
		} else if (user.role.authority in ['ROLE_ACCOUNT','ROLE_JUDGE','ROLE_MEDIATOR','ROLE_JURIST']) {
			switch (ticket.status()) {
				case TicketStatus.New: return [TicketStatus.Closed];
				case TicketStatus.Processing: return [TicketStatus.Closed];
			}
		}
		return null;
	}


	protected void notFound() {
		request.withFormat {
			'*' {
				render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'ticket.label'), params.id])]
			}
			multipartForm { render status: NOT_FOUND }
			json { render status: NOT_FOUND }
		}
	}
}

class TicketCreateCommand {
	Long account
	String subject

	static constraints = {
		subject(blank: false)
	}

	@Override
	public String toString() {
		return "TicketCreateCommand{" +
				"account=" + account +
				", subject='" + subject + "} ";
	}
}

@EqualsAndHashCode
class TicketFilter extends FormFilter {
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