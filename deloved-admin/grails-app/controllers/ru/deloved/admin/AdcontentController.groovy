package ru.deloved.admin

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import org.hibernate.criterion.CriteriaSpecification
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import ru.deloved.*

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_ACCOUNT') and isFullyAuthenticated()"])
class AdcontentController extends FilteredController {
	def accountService
	def attachService
	def profileService
	def adcontentService

	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"

	def fileCategoryDirectory = new DefaultGrailsApplication().config?.fileCategory?.adcontent ?: 'adcontent'

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	@Override
	protected FormFilter createFilterInstance() {
		return new AdcontentFilter()
	}

	@Override
	protected PagedResultWrapper getRows(FormFilter filter) {
		AdcontentFilter f = filter
		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()
		def regFilter = []
		if (user.role.authority in ['ROLE_MANAGER']) {
			regFilter = profileService.findAllCitiesByProfile(user.profile)
		}
		def rows = Adcontent.createCriteria().list(max: filter.max, offset: filter.offset) {
			createAlias('account', 'account', CriteriaSpecification.LEFT_JOIN)
			if (user.role.authority in ['ROLE_MANAGER']) {
				or {
					inList("account.city", regFilter)
				}
			}
			if (f.approved != null) {
				eq("approved", f.approved)
			}
			if (f.type != null) {
				eq("type", f.type)
			}
			if (f.search) {
				or {
					ilike("account.name", "%" + f.search + "%")
					ilike("name", "%" + f.search + "%")
					ilike("description", "%" + f.search + "%")
				}
			}
			order(filter.sort ?: "id", filter.order ?: "asc")
		}
		new PagedResultWrapper(rows)
	}

	def index(AdcontentFilter filter) {
		User user = springSecurityService.getCurrentUser()
		def accList = accountService.getMyAccounts()

		def charge = profileService.checkForCharge(user.profile)
		def showAccount = (accList.size() > 1 || user.role.authority in ['ROLE_ADMIN', 'ROLE_MANAGER'])

		processIndex(10, filter) {
			[model: [myAccounts: accList, showAccount: showAccount, charge: charge]]
		}
	}


	def index2(AdcontentFilter filter) {
		User user = springSecurityService.getCurrentUser()
		def accList = accountService.getMyAccounts()

		def charge = profileService.checkForCharge(user.profile)
		def showAccount = (accList.size() > 1 || user.role.authority in ['ROLE_ADMIN', 'ROLE_MANAGER'])

		processIndex(10, filter) {
			[model: [myAccounts: accList, showAccount: showAccount, charge: charge]]
		}
	}
	def show(Adcontent adcontentInstance) {
		if (adcontentInstance?.id == null) {
			notFound()
			return
		}
		respond adcontentInstance
	}

	def create() {
		List<Account> accountList = accountService.getMyAccounts()
		Account acc = accountList.size() == 1 ? accountList.get(0) : null;

		Adcontent ad = new Adcontent(account: acc)
		log.debug("AD:" + ad)
		respond ad,
				model: [
						accountList: accountList
				]
	}

	def accounts(String term) {
		User user = springSecurityService.getCurrentUser()
		def regFilter = []
		if (user.role.authority == 'ROLE_MANAGER') {
			regFilter = profileService.findAllCitiesByProfile(user.profile)
		}
		def list = []
		if (user.role.authority in ['ROLE_ADMIN', 'ROLE_MANAGER']) {
			list = Account.createCriteria().list(max: 5) {
				or {
					ilike("name", "%" + term + "%")
					ilike("fullName", "%" + term + "%")
					ilike("brandName", "%" + term + "%")
				}
				if (user.role.authority == 'ROLE_MANAGER') {
					inList("city", regFilter)
				}
			}
		}
		def res = []
		list.each { it ->
			res << [label: it.name, value: it.name, id: it.id]
		}
		render(status: OK, contentType: 'application/json') {
			res
		}
	}

	protected redirectEdit(Adcontent beanResource, objInstance = null) {
		if (!adcontentService.isEditAllowed(beanResource)) {
			accessDenied()
			return
		}
		List<Account> accountList = accountService.getMyAccounts()
		def charge = accountService.checkForCharge(accountList)

		render view: request.xhr ? 'editform' : 'edit',
				model: [
						beanResource: beanResource,
						objInstance : objInstance ?: beanResource,
						accountList : accountList,
						charge      : charge
				]
	}

	@Transactional
	def save(Adcontent adcontentInstance) {
		if (adcontentInstance == null) {
			notFound()
			return
		}
		adcontentInstance.type = AdcontentType.Video.value()


		if (adcontentInstance.hasErrors()) {
			respond adcontentInstance.errors, view: 'create'
			return
		}

		adcontentInstance.save flush: true
		log.debug("ad.id:" + adcontentInstance.id)

		request.withFormat {
			form {
				flash.message = message(code: 'default.created.message', args: [message(code: 'adcontent.label', default: 'Adcontent'), adcontentInstance.id])
				redirect base: uri,controller: 'adcontent', action: 'index'
			}
			'*' { respond itemInstance, [status: CREATED] }
		}

	}

	def edit(Adcontent adcontentInstance) {
		if (adcontentInstance?.id == null) {
			notFound()
			return
		}
		redirectEdit(adcontentInstance)
	}

	@Transactional
	def update() {
		Adcontent adcontentInstance = Adcontent.get(params.id)
		if (adcontentInstance == null) {
			notFound()
			return
		}
		if (!adcontentService.isEditAllowed(adcontentInstance)) {
			accessDenied()
			return
		}
		User user = springSecurityService.getCurrentUser()
		if (user.role.authority == 'ROLE_ACCOUNT') {
			bindData(adcontentInstance, params, [include: ["name", "description", "url", "account"]])
		} else {
			bindData(adcontentInstance, params, [include: ["name", "description", "url", "account", "approved"]])
		}

		if (adcontentInstance.hasErrors()) {
			redirectEdit(adcontentInstance)
			return
		}

		def saved = adcontentInstance.save flush: true

		if (request.xhr) {
			if (saved) {
				response.contentType = "text/javascript;charset=UTF-8"
				def cols = [
						name    : adcontentInstance.name,
						approved: g.render(template: 'status', plugin: 'deloved-admin', model: [status: adcontentInstance.approved]).replaceAll("[\\n\\r]", "")
				]
				render template: '/_common/grid-row-update', model: [gridRowId: adcontentInstance.id, gridCols: cols, rawCols: [approved: true]]
			} else {
				redirectEdit(adcontentInstance)
			}
		} else {
			request.withFormat {
				'*' {
					flash.message = message(code: saved ? 'default.updated.message' : 'default.notUpdated.message',
							args: [message(code: 'adcontent.label', default: 'Adcontent'), adcontentInstance.id]
					)
					redirect base: uri,controller: 'adcontent', action: 'index'
				}
				json { respond adcontentInstance, [status: OK] }
			}
		}
	}

	@Transactional
	def batch(IdListCommand idList) {
		def cnt = 0
		User user = springSecurityService.getCurrentUser()
		log.trace('idList:' + idList)
		if (user.role.authority in ['ROLE_MANAGER', 'ROLE_ACCOUNT']) {
			Set<Long> idSecureList = new HashSet<>()
			Adcontent.createCriteria().list {
				if (user.role.authority == 'ROLE_MANAGER') {
					createAlias('account', 'a', CriteriaSpecification.LEFT_JOIN)
					inList("a.city", profileService.findAllCitiesByProfile(user.profile))
				} else if (user.role.authority == 'ROLE_ACCOUNT') {
					inList("account", accountService.getMyAccounts())
				}
				inList("id", idList.getList())
			}.each {
				idSecureList.add(it.id)
			}
			idList.id = idSecureList
		}
		log.trace('idList:' + idList)
		if (params.batch_action == "batchDelete") {
			deleteList(idList)
			return
		} else if (params.batch_action == "batchEnable" && (user.role.authority != 'ROLE_ACCOUNT')) {
			if (idList?.id?.size() > 0) {
				cnt = User.executeUpdate("update Adcontent ad set ad.approved = true where ad.approved = false and ad.id in (:idlist)", [idlist: idList.getList()])
				flash.message = message(code: 'user.batch.enabled.message', args: [cnt])
			}
		} else if (params.batch_action == "batchDisable" && (user.role.authority != 'ROLE_ACCOUNT')) {
			if (idList?.id?.size() > 0) {
				cnt = User.executeUpdate("update Adcontent ad set ad.approved = false where ad.approved = true and ad.id in (:idlist)", [idlist: idList.getList()])
				flash.message = message(code: 'user.batch.disabled.message', args: [cnt])
			}
		}
		redirect base: uri,controller: 'adcontent', action: "index", method: "GET"
	}

	private deleteOne(Adcontent adcontent) {
		if (adcontent.type() == AdcontentType.Image) {
			attachService.deleteImageAndThumbnail(fileCategoryDirectory, adcontent.file, adcontent.fileThumb) {
				adcontent.file = null
				adcontent.fileThumb = null
				adcontent.save(flush: true)
				return true
			}
		} else if (adcontent.file != null) {
			attachService.deleteAttach(fileCategoryDirectory, adcontent.file) {
				adcontent.file = null
				adcontent.fileThumb = null
				adcontent.save(flush: true)
				return true
			}
		}
		adcontent.delete flush: true
	}

	private deleteList(IdListCommand idList) {
		def st = NO_CONTENT
		def mess = null
		def userLogin = springSecurityService.getCurrentUser()

		log.trace('params:' + params)
		log.trace('idList:' + idList)
		if (idList?.id) {
			if (idList.id.size() == 1) {
				def adcontent = Adcontent.get(idList.id[0])
				if (adcontent) {
					deleteOne(adcontent)
					mess = message(code: 'default.deleted.message', args: [message(code: 'adcontent.label', default: 'Adcontent'), adcontent.name])
				} else {
					notFound()
					return
				}
			} else if (idList.id.size() > 1) {
				int deleted = 0
				idList.getList().each {
					def adcontent = Adcontent.get(it)
					deleteOne(adcontent)
					deleted++
				}
				mess = message(code: 'default.deletedMany.message', args: [message(code: 'adcontent.label', default: 'Adcontent'), idList.id, deleted])
			} else {
				notFound()
				return
			}
		} else {
			st = BAD_REQUEST
		}
		request.withFormat {
			form {
				flash.message = mess
				redirect base: uri,controller: 'adcontent', action: "index", method: "GET"
			}
			'*' { render status: st }
		}
	}

	@Transactional
	def upload() {

		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()
		log.debug("update params:" + params)
		Account acc = null
		if (list.size() == 1) {
			acc = list[0]
		} else {
			if (params.account) {
				acc = Account.get(params.account)
			}
		}


		def files = []
		if (request instanceof MultipartHttpServletRequest) {
			for (filename in request.getFileNames()) {
				MultipartFile file = request.getFile(filename)

				boolean supported = false
				AdcontentType type = null
				log.debug("mime: " + file.contentType)
				switch (file.contentType) {
					case 'image/jpeg':
					case 'image/png':
					case 'image/gif':
						supported = true
						type = AdcontentType.Image
						break;
//					case 'application/octet-stream':
//					case 'document/word':
//					case 'text/plain':
//						supported = true
//						type = AdcontentType.Doc
//						break;
					case 'application/pdf':
					case 'application/x-pdf':
						supported = true
						type = AdcontentType.Pdf
						break;
					case 'audio/mpeg':
						supported = true
						type = AdcontentType.Audio
						break;
				}

				if (acc == null) {
					files << [
							name : file.originalFilename,
							size : file.size,
							error: "You can not upload to this deal"
					]
				} else if (type == null) {
					files << [
							name : file.originalFilename,
							size : file.size,
							error: "This file type not supported"
					]
				} else {
					def thumbnailUrl
					Attachment attachment
					Adcontent ad
					if (type == AdcontentType.Image) {

						attachService.uploadImageAndThumbnail(file, fileCategoryDirectory, user.profile, null, null) {
							Attachment thumb, Attachment image ->
								thumb.save(flush: true)
								image.save(flush: true)
								ad = new Adcontent(account: acc, type: type.value(), name: image.name, file: image, fileThumb: thumb, approved: false)
								ad.save(flush: true)
								log.debug("dpa errors:" + ad.errors)
								attachment = image
								thumbnailUrl = createLink([controller: 'adcontent', action: 'download', id: ad.fileThumb.id, params: [name: ad.fileThumb.name, preview: true]])
								return true
						}

					} else {
						attachment = attachService.uploadAttach(file, fileCategoryDirectory, user.profile) {
							Attachment att, String icon ->
								att.save(flush: true)
								log.debug("att errors:" + att.errors)
								ad = new Adcontent(account: acc, type: type.value(), name: att.name, file: att, approved: false)
								ad.save(flush: true)
								log.debug("dpa errors:" + ad.errors)

								thumbnailUrl = icon
						}
					}


					files << [
							id          : ad.id,
							dateCreated : formatDate(date: ad.dateCreated, format: 'dd.MM.yyyy HH:mm:ss'),
							type        : message(code: 'adcontent.type.' + ad.type, default: ad.type),
							account     : ad.account.name,
							name        : attachment.name,
							size        : attachment.size,
							url         : createLink([action: 'download', id: attachment.id, params: [name: attachment.name]]),
							editUrl     : createLink([action: 'edit', id: ad.id]),
							thumbnailUrl: thumbnailUrl,
							previewUrl  : createLink([action: 'download', id: attachment.id, params: [name: attachment.name, preview: true]])
					]
				}

			}
		}

		def results = [files: files]
		render results as JSON

	}

	def download() {
		// TODO Acccess permissions check
		log.debug("download params:" + params)
		Attachment att = Attachment.get(params.id)
		if (att && att.name == params.name) {
			def saveAs = true
			if (params.preview != null) {
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
				render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'adcontent.label'), params.id])]
			}
			multipartForm { render status: NOT_FOUND }
			json { render status: NOT_FOUND }
		}
	}

	protected void accessDenied() {
		if (springSecurityService.isAjax(request)) {
			render status: FORBIDDEN
		} else {
			render view: "/login/denied", status: FORBIDDEN
		}
	}

}

class AdcontentFilter extends FormFilter {
	Integer type
	String search
	Boolean approved

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
		["type", "search", "approved"]
	}

	@Override
	protected List getSortedParamList() {
		["dateCreated"]
	}

	@Override
	public Map getMap() {
		def m = super.getMap()
		if (type != null) m.type = type
		if (search != null) m.search = search
		if (approved != null) m.approved = approved
		return m
	}

}
