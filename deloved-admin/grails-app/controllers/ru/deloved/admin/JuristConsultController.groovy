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

import ru.deloved.recall.JuristConsult
import ru.deloved.recall.JuristConsultPost
import ru.deloved.recall.JuristConsultPostAttach
import ru.deloved.recall.JuristConsultLastVisit
import ru.deloved.recall.JuristConsultStatus

import static org.springframework.http.HttpStatus.FORBIDDEN
import static org.springframework.http.HttpStatus.NOT_FOUND


/*TODO
Добавить ролей для тикетов. Что-то вроде ROLE_TECH
 */
@Transactional(readOnly = true)
@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_ACCOUNT','ROLE_JURIST') and isFullyAuthenticated()"])
class JuristConsultController extends FilteredController {
    def accountService
    def attachService
    def profileService
//GetAccounts

    String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"

    def fileCategoryDirectory = new DefaultGrailsApplication().config?.fileCategory?.juristConsult ?: 'juristConsult'

    @Override
    protected FormFilter createFilterInstance() {
        return new JuristConsultFilter()
    }

    @Override
    protected PagedResultWrapper getRows(FormFilter filter) {
        JuristConsultFilter f = filter
        User user = springSecurityService.getCurrentUser()
        def list = accountService.getMyAccounts()
        def regFilter = []
        if (user.role.authority in ['ROLE_JURIST']) {
            regFilter = profileService.findAllCitiesByProfile(user.profile)
        }
        def rows = JuristConsult.createCriteria().list(max: filter.max, offset: filter.offset) {
            createAlias('account', 'account', CriteriaSpecification.LEFT_JOIN)

            if (user.role.authority in ['ROLE_ACCOUNT']) {
                inList("account", list)
            } else if (user.role.authority in ['ROLE_JURIST']) {
                inList("account.city", regFilter)
                or {
                    eq("jurist", user.profile)
                    eq("status", JuristConsultStatus.New.value())
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

    def index(JuristConsultFilter juristConsultFilter) {
        processIndex(10, juristConsultFilter) {
            [model: [myAccounts: accountService.getMyAccounts()]]

        }
    }

    @Secured(["hasAnyRole('ROLE_ACCOUNT') and isFullyAuthenticated()"])

    def create() {
        User user = springSecurityService.getCurrentUser()
        def list = accountService.getMyAccounts()

        def cmd = new JuristConsultCreateCommand(
                account: list.size() == 1 ? list.get(0).id : null,
        )
        respond cmd, [model: [myAccounts: list.size() > 1 ? list : null, juristConsultCreateCommandInstance: cmd]]
    }

    def getLatestPosts(JuristConsultPost lastPost) {
        if (!lastPost || !request.xhr) {
            render status: 404, text: 'NOT_FOUND'
            return
        }

        def latestPosts = JuristConsultPost.executeQuery("""
			select post
			from JuristConsultPost as post
			where post.juristConsult = :juristConsult
			and post.dateCreated > :date
		""", [juristConsult: lastPost.juristConsult, date: lastPost.dateCreated])

        if (latestPosts) {
            User user = springSecurityService.getCurrentUser()
            def list = accountService.getMyAccounts()

            def notMyAccount = null, myAccount = null
            if (list.contains(lastPost.juristConsult.jurist)) {
                myAccount = lastPost.person
                notMyAccount = lastPost.account
            } else if (list.contains(lastPost.juristConsult.account)) {
                myAccount = lastPost.account
                notMyAccount = lastPost.person
            }

            render status: 200, template: '/_common/thread-post', model: [
                    threadPosts         : latestPosts,
                    threadAccount       : myAccount ?: lastPost.juristConsult.account,
                    threadStatusPrefix  : 'juristConsult.status',
                    threadStatusTemplate: 'thread-status',
                    myProfile: user.profile,
                    notMyAccount: notMyAccount
            ]
        } else {
            render status: 200, text: 'NO_CONTENT'
        }
    }

    def getStatus(JuristConsult juristConsultInstance) {
        if (!params.currentStatus || !juristConsultInstance || !request.xhr) {
            render status: 404, text: 'NOT_FOUND'
            return
        }

        def result = []
        if (juristConsultInstance.status == Integer.parseInt(params.currentStatus)) {
            render result as JSON
            return
        }

        def list = accountService.getMyAccounts()

        def myAccount = null
        if (list.contains(juristConsultInstance.jurist)) {
            myAccount = juristConsultInstance.jurist
        } else if (list.contains(juristConsultInstance.account)) {
            myAccount = juristConsultInstance.account
        }

        def statusName = message(code: 'juristConsult.status.' + juristConsultInstance.status(), default: juristConsultInstance.status())

        List<JuristConsultStatus> statusList = getNextStatusList(juristConsultInstance, myAccount)

        def resultStatusList = []

        if (statusList != null) {
            for(int i = 0; i < statusList.size(); i ++) {
                resultStatusList.push([value: statusList[i].value(), name: message(code: 'JuristConsultStatus.status.' + statusList[i] + '.i', default: statusList[i])])
            }
        }

        result = [currentStatus: [name: statusName, value: juristConsultInstance.status, position: juristConsultInstance.position()], statusList: resultStatusList]

        log.debug('JuristConsultController.newStatus.result: ' + result)

        render result as JSON

    }


    def thread(JuristConsult juristConsultInstance) {
        if (juristConsultInstance?.id == null) {
            notFound()
            return
        }
        User user = springSecurityService.getCurrentUser()
        def list = accountService.getMyAccounts()

        def myAccount = null
        if (list.contains(juristConsultInstance.jurist)) {
            myAccount = juristConsultInstance.jurist
        } else if (list.contains(juristConsultInstance.account)) {
            myAccount = juristConsultInstance.account
        }

        def attach = []
        def posts = JuristConsultPost.findAllByJuristConsult(juristConsultInstance)
        def statusList = getNextStatusList(juristConsultInstance, user)
        if (myAccount) {
            attach.addAll(JuristConsultPostAttach.findAllByJuristConsultAndAccountAndJuristConsultPost(juristConsultInstance, myAccount, null))
        }

        def canPost = false
        if (user.role.authority in ['ROLE_ACCOUNT'] && (juristConsultInstance.status() in [JuristConsultStatus.New, JuristConsultStatus.Processing])) {
            canPost = true
        } else if (user.role.authority == 'ROLE_JURIST' && (juristConsultInstance.status() == JuristConsultStatus.Processing) && juristConsultInstance.jurist == user.profile) {
            canPost = true
        }
        respond juristConsultInstance, [model: [posts: posts, myAccount: myAccount, myProfile: user.profile, statusList: statusList, attach: attach, canPost: canPost]]
    }

    @Transactional
    def deletepost(JuristConsultPost post){
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
                JuristConsultPostAttach tpa = post.attachments[i]
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
    def post(JuristConsult juristConsultInstance) {
        if (juristConsultInstance?.id == null) {
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

        }  else if (user.role.authority =='ROLE_JURIST') {

            canPost = (juristConsultInstance.status() == JuristConsultStatus.Processing)
            canStatus = ((juristConsultInstance.status() == JuristConsultStatus.New) || (juristConsultInstance.jurist == user.profile))

        } else if (user.role.authority in ['ROLE_ACCOUNT']) {
            if (list.contains(juristConsultInstance.account)) {
                myAccount = juristConsultInstance.account
            }
            if (accountService.isMyAccount(juristConsultInstance.account)) {
                canPost = myAccount != null
                canStatus = (juristConsultInstance.status() in [JuristConsultStatus.New, JuristConsultStatus.Processing])
            }
        }

        log.debug("canPost:" + canPost)
        log.debug("canStatus:" + canStatus)


        if (!canPost && !canStatus) {
            /*TODO
            обработать ошибку
             */
            //flash.message = message(code: 'ticket.cant.post', default: 'Вы не участвуете в сделке')
            redirect base: uri,controller: 'juristConsult', action: 'index'
            return
        }

        def post
        if (params.post && canPost) {
            post = new JuristConsultPost(juristConsult: juristConsultInstance, account: myAccount, person: user.profile, post: params.post)

            JuristConsultPostAttach.findAllByJuristConsultAndAccountAndJuristConsultPost(juristConsultInstance, myAccount, null).each {
                log.debug("free file:" + it.attachment.name)
                it.juristConsultPost = post
            }
        } else if (params.newstatus && canStatus) {
            def nextList = getNextStatusList(juristConsultInstance, user)
            log.debug("params.newstatus:" + params.newstatus)
            log.debug("nextList:" + nextList)
            JuristConsultStatus newStatus = JuristConsultStatus.valueOf(Integer.parseInt(params.newstatus))
            if (nextList && nextList.contains(newStatus)) {
                post = new JuristConsultPost(juristConsult: juristConsultInstance, account: myAccount, person: user.profile, status: newStatus.value())
            }
        }
        if (post) {
            log.debug "createJuristConsultpost:" + post
            if (post.status) {
                def juristConsult = JuristConsult.lock(juristConsultInstance.id)
                def nextList = getNextStatusList(juristConsult, user)
                if (canStatus && nextList && nextList.contains(post.status())) {
                    if (juristConsult.status() == JuristConsultStatus.New) {
                        if (user.role.authority == 'ROLE_JURIST') {
                            juristConsult.jurist = user.profile
                        }
                        juristConsult.newProfiles.clear()
                    }
                    if (post.save(flush: true)) {
                        /*TODO
                        обработать
                         */
                        flash.message = message(code: 'default.created.message', args: [message(code: 'consultpost.label')])
                    } else {
                        log.debug(post.errors)
                    }
                    juristConsult.status = post.status
                }
                juristConsult.save(flush: true)

            } else {
                if (post.save(flush: true)) {
                    /*TODO
                        обработать
                         */
                    flash.message = message(code: 'default.created.ticket', args: [message(code: 'consult.label')])
                } else {
                    log.debug(post.errors)
                }
            }
        }

        redirect base: uri,controller: 'juristConsult', action: 'thread', id: juristConsultInstance.id

    }

    @Transactional
    def save(JuristConsultCreateCommand juristConsultInstance) {
        if (juristConsultInstance == null) {
            notFound()
            return
        }

        if (juristConsultInstance.hasErrors()) {
            juristConsultInstance.errors.each { log.error(it) }
            respond juristConsultInstance.errors, view: 'create'
            return
        }

        def juristConsult = new JuristConsult(
                account: Account.load(juristConsultInstance.account),
                status: JuristConsultStatus.New.value()
        )

        log.debug "createJuristConsult:" + juristConsult

        if (!juristConsult.save(flush: true)) {
            respond juristConsult.errors, [view: 'create', model: [juristConsultCreateCommand: juristConsultInstance]]
        } else {
            User user = springSecurityService.getCurrentUser()
            def post = new JuristConsultPost(
                    juristConsult: juristConsult,
                    account: juristConsult.account,
                    person: user.profile,
                    post: juristConsultInstance.subject
            )
            log.debug "createTicketpost:" + post
            post.save(flush: true)

            flash.message = message(code: 'default.created.measure', args: [message(code: 'consult.label')])
            redirect base: uri,controller:'juristConsult', action: 'thread', id: juristConsult.id
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
    def upload(JuristConsult juristConsultInstance) {

        if (juristConsultInstance?.id == null) {
            notFound()
            return
        }

        User user = springSecurityService.getCurrentUser()
        def list = accountService.getMyAccounts()

        def myAccount = null
        if (list.contains(juristConsultInstance.account)) {
            myAccount = juristConsultInstance.account
        } else if (list.contains(juristConsultInstance.jurist)) {
            myAccount = juristConsultInstance.jurist
        }

        JuristConsultPost juristConsultPost = new JuristConsultPost(juristConsult: juristConsultInstance, account: myAccount, person: user.profile)

        ArrayList<JuristConsultPostAttach> results = []

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
                            JuristConsultPostAttach dpa = new JuristConsultPostAttach(attachment: att, juristConsult: juristConsultInstance, account: myAccount)
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
                it.juristConsultPost = juristConsultPost
            }
            juristConsultPost.save(flush: true)
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
        } else if (user.role.authority == 'ROLE_JURIST') {
            JuristConsultPostAttach dpa = JuristConsultPostAttach.findByAttachment(att)
            if (dpa) {
                if (profileService.findAllCitiesByProfile(user.profile).contains(dpa.account.city)) {
                    canDelete = true
                }
            }
        } else if (user.role.authority in ['ROLE_ACCOUNT']) {
            JuristConsultPostAttach dpa = JuristConsultPostAttach.findByAttachment(att)
            if (dpa.juristConsultPost == null && accountService.isMyAccount(dpa.account)) {
                canDelete = false
            }
        }
        log.debug("delete params:" + params)
        if (canDelete) {
            def result
            if (att && att.name == params.name) {
                attachService.deleteAttach(fileCategoryDirectory, att) {
                    JuristConsultPostAttach.findByAttachment(att).delete(flush: true)
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

    @Transactional
    def deleteJurist(JuristConsult juristConsultInstance){
        if (!juristConsultInstance || !request.xhr) {
            render status: 404, text: 'NOT_FOUND'
            return
        }
        //List<AccountDealRating> rating =AccountDealRating.findAllByDeal(deal)
        List<JuristConsultLastVisit> lastVisits = JuristConsultLastVisit.findAllByJuristConsult(juristConsultInstance)
        List<JuristConsultPost> post = JuristConsultPost.findAllByJuristConsult(juristConsultInstance)

        //disputeInstance.deleteAll(rating)
        juristConsultInstance.deleteAll(lastVisits)
        juristConsultInstance.deleteAll(post)
        juristConsultInstance.delete(flush: true)

        render status: 200, text: 'SUCCESS'
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

    static List<JuristConsultStatus> getNextStatusList(JuristConsult juristConsult, User user) {
        if (user.role.authority == 'ROLE_JURIST') {
            switch (juristConsult.status()) {
                case JuristConsultStatus.New: return [JuristConsultStatus.Processing];
                case JuristConsultStatus.Processing: return [JuristConsultStatus.Closed];
            }
        } else if (user.role.authority in ['ROLE_ACCOUNT']) {
            switch (juristConsult.status()) {
                case JuristConsultStatus.New: return [JuristConsultStatus.Closed];
                case JuristConsultStatus.Processing: return [JuristConsultStatus.Closed];
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

class JuristConsultCreateCommand {
    Long account
    String subject

    static constraints = {
        subject(blank: false)
    }

    @Override
    public String toString() {
        return "JuristConsultCreateCommand{" +
                "account=" + account +
                ", subject='" + subject + "} ";
    }
}

@EqualsAndHashCode
class JuristConsultFilter extends FormFilter {
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