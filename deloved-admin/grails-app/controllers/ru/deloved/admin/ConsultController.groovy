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
@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_ACCOUNT','ROLE_JURIST') and isFullyAuthenticated()"])
class ConsultController extends FilteredController {
	def accountService
	def attachService
	def profileService


	def fileCategoryDirectory = new DefaultGrailsApplication().config?.fileCategory?.consult ?: 'consult'

	@Override
	protected FormFilter createFilterInstance() {
		return new ConsultFilter()
	}

	@Override
	protected PagedResultWrapper getRows(FormFilter filter) {
		ConsultFilter f = filter
		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()
		def regFilter = []
		if (user.role.authority in ['ROLE_JURIST']) {
			regFilter = profileService.findAllCitiesByProfile(user.profile)
		}
		def rows = Consult.createCriteria().list(max: filter.max, offset: filter.offset) {
			createAlias('account', 'account', CriteriaSpecification.LEFT_JOIN)

			if (user.role.authority in ['ROLE_ACCOUNT']) {
				inList("account", list)
			} else if (user.role.authority in ['ROLE_JURIST']) {
				inList("account.city", regFilter)
				or {
					eq("jurist", user.profile)
					eq("status", ConsultStatus.New.value())
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
			order(filter.sort ?: "id", filter.order ?: "asc")
		}
		new PagedResultWrapper(rows)
	}

	def index(ConsultFilter consultFilter) {
		processIndex(10, consultFilter) {
			[model: [myAccounts: accountService.getMyAccounts()]]
		}
	}

	@Secured(["hasAnyRole('ROLE_ACCOUNT') and isFullyAuthenticated()"])
	def create() {
		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()

		def cmd = new ConsultCreateCommand(
					account:list.size() == 1 ? list.get(0).id : null,
		)

		respond cmd, [model: [myAccounts: list.size() > 1 ? list : null, consultCreateCommandInstance: cmd]]
	}

	def getLatestPosts(ConsultPost lastPost) {
		if (!lastPost || !request.xhr) {
			render status: 404, text: 'NOT_FOUND'
			return
		}

		def latestPosts = ConsultPost.executeQuery("""
			select post
			from ConsultPost as post
			where post.consult = :consult
			and post.dateCreated > :date
		""", [consult: lastPost.consult, date: lastPost.dateCreated])

		if (latestPosts) {
			User user = springSecurityService.getCurrentUser()
			def list = accountService.getMyAccounts()

			def notMyAccount = null, myAccount = null
			if (list.contains(lastPost.consult.jurist)) {
				myAccount = lastPost.person
				notMyAccount = lastPost.account
			} else if (list.contains(lastPost.consult.account)) {
				myAccount = lastPost.account
				notMyAccount = lastPost.person
			}

			render status: 200, template: '/_common/thread-post', model: [
					threadPosts         : latestPosts,
					threadAccount       : myAccount ?: lastPost.consult.account,
					threadStatusPrefix  : 'consult.status',
					threadStatusTemplate: 'thread-status',
					myProfile: user.profile,
					notMyAccount: notMyAccount
			]
		} else {
			render status: 200, text: 'NO_CONTENT'
		}
	}


	def getStatus(Consult consultInstance) {
		if (!params.currentStatus || !consultInstance || !request.xhr) {
			render status: 404, text: 'NOT_FOUND'
			return
		}

		def result = []
		if (consultInstance.status == Integer.parseInt(params.currentStatus)) {
			render result as JSON
			return
		}

		def list = accountService.getMyAccounts()

		def myAccount = null
		if (list.contains(consultInstance.jurist)) {
			myAccount = consultInstance.jurist
		} else if (list.contains(consultInstance.account)) {
			myAccount = consultInstance.account
		}

		def statusName = message(code: 'consult.status.' + consultInstance.status(), default: consultInstance.status())

		List<ConsultStatus> statusList = getNextStatusList(consultInstance, myAccount)

		def resultStatusList = []

		if (statusList != null) {
			for(int i = 0; i < statusList.size(); i ++) {
				resultStatusList.push([value: statusList[i].value(), name: message(code: 'consult.status.' + statusList[i] + '.i', default: statusList[i])])
			}
		}

		result = [currentStatus: [name: statusName, value: consultInstance.status, position: consultInstance.position()], statusList: resultStatusList]

		log.debug('ConsultController.newStatus.result: ' + result)

		render result as JSON

	}

	@Transactional
	def deletepost(ConsultPost post){
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
				ConsultPostAttach cpa = post.attachments[i]
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

	def thread(Consult consultInstance) {
		if (consultInstance?.id == null) {
			notFound()
			return
		}
		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()

		def myAccount = null
		if (list.contains(consultInstance.jurist)) {
			myAccount = consultInstance.jurist
		} else if (list.contains(consultInstance.account)) {
			myAccount = consultInstance.account
		}

		def attach = []
		def posts = ConsultPost.findAllByConsult(consultInstance)
		def statusList = getNextStatusList(consultInstance, user)
		if (myAccount) {
			attach.addAll(ConsultPostAttach.findAllByConsultAndAccountAndConsultPost(consultInstance, myAccount, null))
		}

		def canPost = false
		if (user.role.authority == 'ROLE_ACCOUNT' && (consultInstance.status() in [ConsultStatus.New, ConsultStatus.Processing])) {
			canPost = true
		} else if (user.role.authority == 'ROLE_JURIST' && (consultInstance.status() == ConsultStatus.Processing) && consultInstance.jurist == user.profile) {
			canPost = true
		}
		respond consultInstance, [model: [posts: posts, myAccount: myAccount, myProfile: user.profile, statusList: statusList, attach: attach, canPost: canPost]]
	}

	@Transactional
	def post(Consult consultInstance) {
		if (consultInstance?.id == null) {
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

		}	else if (user.role.authority == 'ROLE_JURIST') {

				canPost = (consultInstance.status() == ConsultStatus.Processing)
				canStatus = ((consultInstance.status() == ConsultStatus.New) || (consultInstance.jurist == user.profile))
		}
		else if (user.role.authority == 'ROLE_ACCOUNT') {
			if (list.contains(consultInstance.account)) {
				myAccount = consultInstance.account
			}
			if (accountService.isMyAccount(consultInstance.account)) {
				canPost = myAccount != null
				canStatus = (consultInstance.status() in [ConsultStatus.New, ConsultStatus.Processing])
			}
		}

		log.debug("canPost:" + canPost)
		log.debug("canStatus:" + canStatus)


		if (!canPost && !canStatus) {
//			flash.message = message(code: 'consult.cant.post', default: 'Вы не участвуете в сделке')
			redirect action: 'index'
			return
		}

		def post
		if (params.post && canPost) {
			post = new ConsultPost(consult: consultInstance, account: myAccount, person: user.profile, post: params.post)

			ConsultPostAttach.findAllByConsultAndAccountAndConsultPost(consultInstance, myAccount, null).each {
				log.debug("free file:" + it.attachment.name)
				it.consultPost = post
			}
		} else if (params.newstatus && canStatus) {
			def nextList = getNextStatusList(consultInstance, user)
			log.debug("params.newstatus:" + params.newstatus)
			log.debug("nextList:" + nextList)
			ConsultStatus newStatus = ConsultStatus.valueOf(Integer.parseInt(params.newstatus))
			if (nextList && nextList.contains(newStatus)) {
				post = new ConsultPost(consult: consultInstance, account: myAccount, person: user.profile, status: newStatus.value())
			}
		}
		if (post) {
			log.debug "createConsultpost:" + post
			if (post.status) {
				def consult = Consult.lock(consultInstance.id)
				def nextList = getNextStatusList(consult, user)
				if (canStatus && nextList && nextList.contains(post.status())) {
					if (consult.status() == ConsultStatus.New){
						if (user.role.authority == 'ROLE_JURIST') {
							consult.jurist = user.profile
						}
						consult.newProfiles.clear()
					}
					if (post.save(flush: true)) {
						flash.message = message(code: 'default.created.message', args: [message(code: 'consultpost.label')])
					} else {
						log.debug(post.errors)
					}
					consult.status = post.status
				}
				consult.save(flush: true)

			} else {
				if (post.save(flush: true)) {
					flash.message = message(code: 'default.created.measure', args: [message(code: 'consult.label')])
				} else {
					log.debug(post.errors)
				}
			}
		}

		redirect action: 'thread', id: consultInstance.id

	}

	@Transactional
	def save(ConsultCreateCommand consultInstance) {
		if (consultInstance == null) {
			notFound()
			return
		}

		if (consultInstance.hasErrors()) {
			consultInstance.errors.each { log.error(it) }
			respond consultInstance.errors, view: 'create'
			return
		}

		//log.debug("cmd.deal: " + consultInstance?.deal + ", cmd.defendant: " +consultInstance?.defendant + ", cmd.consultcat: " + consultInstance?.consultcat)


		def consult = new Consult(
				account: Account.load(consultInstance.account),
				status: ConsultStatus.New.value()

		)

		log.debug "createConsult:" + consult

		if (!consult.save(flush: true)) {
			respond consult.errors, [view: 'create', model: [consultCreateCommandInstance: consultInstance]]
		} else {

			User user = springSecurityService.getCurrentUser()
			def post = new ConsultPost(
					consult: consult,
					account: consult.account,
					person: user.profile,
					post: consultInstance.subject
			)
			log.debug "createConsultpost:" + post
			post.save(flush: true)

			flash.message = message(code: 'default.created.measure', args: [message(code: 'consult.label')])
			redirect action: 'thread', id: consult.id
		}
	}
	@Transactional
	def upload(Consult consultInstance) {

		if (consultInstance?.id == null) {
			notFound()
			return
		}

		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()

		def myAccount = null
		if (list.contains(consultInstance.account)) {
			myAccount = consultInstance.account
		} else if (list.contains(consultInstance.jurist)) {
			myAccount = consultInstance.jurist
		}

		ConsultPost consultPost = new ConsultPost(ticket: consultInstance, account: myAccount, person: user.profile)

		ArrayList<ConsultPostAttach> results = []

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
							ConsultPostAttach dpa = new ConsultPostAttach(attachment: att, ticket: consultInstance, account: myAccount)
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
				it.consultPost = consultPost
			}
			consultPost.save(flush: true)
			render status: 200, text: 'SUCCESS'
			return
		} else {
			render status: 500, text: 'ERROR'
			return
		}

	}

	/*@Transactional
	def upload(Consult consultInstance) {
		if (consultInstance?.id == null) {
			notFound()
			return
		}

		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()

		def myAccount = null
		if (list.contains(consultInstance.account)) {
			myAccount = consultInstance.account
		}

		def files = []
		if (request instanceof MultipartHttpServletRequest) {
			for (filename in request.getFileNames()) {
				MultipartFile file = request.getFile(filename)

				if (myAccount == null) {
					files << [
							name : file.originalFilename,
							size : file.size,
							error: g.message(code: 'consult.cant.upload', default: 'You can not upload to this consult')
					]
				} else {
					def thumbnailUrl

					Attachment att = attachService.uploadAttach(file, fileCategoryDirectory, user.profile) {
						Attachment att, String icon ->
							att.save(flush: true)
							log.debug("att errors:" + att.errors)
							ConsultPostAttach dpa = new ConsultPostAttach(attachment: att, consult: consultInstance, account: myAccount)
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
	def deleteatt() {
		User user = springSecurityService.getCurrentUser()
		boolean canDelete = false;
		Attachment att = Attachment.get(params.id)
		if (user.role.authority == 'ROLE_ADMIN') {
			canDelete = true
		} else if (user.role.authority == 'ROLE_MANAGER') {
			ConsultPostAttach dpa = ConsultPostAttach.findByAttachment(att)
			if (dpa) {
				if (profileService.findAllCitiesByProfile(user.profile).contains(dpa.account.city)) {
					canDelete = true
				}
			}
		} else if (user.role.authority == 'ROLE_ACCOUNT') {
			ConsultPostAttach dpa = ConsultPostAttach.findByAttachment(att)
			if (dpa.consultPost == null && accountService.isMyAccount(dpa.account)) {
				canDelete = true
			}
		}
		log.debug("delete params:" + params)
		if (canDelete) {
			def result
			if (att && att.name == params.name) {
				attachService.deleteAttach(fileCategoryDirectory, att) {
					ConsultPostAttach.findByAttachment(att).delete(flush: true)
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

	static List<ConsultStatus> getNextStatusList(Consult consult, User user) {
		if (user.role.authority == 'ROLE_JURIST') {
			switch (consult.status()) {
				case ConsultStatus.New: return [ConsultStatus.Processing];
				case ConsultStatus.Processing: return [ConsultStatus.Closed];
			}
		} else if (user.role.authority == 'ROLE_ACCOUNT') {
			switch (consult.status()) {
				case ConsultStatus.New: return [ConsultStatus.Closed];
				case ConsultStatus.Processing: return [ConsultStatus.Closed];
			}
		}
		return null;
	}


	protected void notFound() {
		request.withFormat {
			'*' {
				render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'consult.label'), params.id])]
			}
			multipartForm { render status: NOT_FOUND }
			json { render status: NOT_FOUND }
		}
	}
}

class ConsultCreateCommand {
	Long account
	String subject



	static constraints = {
		subject(blank: false)

	}

	@Override
	public String toString() {
		return "ConsultCreateCommand{" +
				"account=" + account +
				", subject='" + subject + "} ";
	}
}

@EqualsAndHashCode
class ConsultFilter extends FormFilter {
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