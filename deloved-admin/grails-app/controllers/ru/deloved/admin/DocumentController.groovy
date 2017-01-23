package ru.deloved.admin

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import ru.deloved.Attachment
import ru.deloved.Document
import ru.deloved.DocumentCategory
import ru.deloved.User

import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.NOT_FOUND

@Transactional(readOnly = true)
@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_ACCOUNT','ROLE_MEDIATOR','ROLE_JUDGE') and isFullyAuthenticated()"])
class DocumentController {
	def springSecurityService
	def attachService

	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"

	def fileCategoryDirectory = new DefaultGrailsApplication().config?.fileCategory?.docs ?: 'docs'

	def index() {
		def attach = []
		attach.addAll(Document.findAllByCategory(null))
		respond DocumentCategory.listOrderByName(), [model: [attach: attach]]
	}

	protected redirectEdit(Document beanResource, objInstance = null) {
		render view: request.xhr ? 'editform' : 'edit',
				model: [
						beanResource: beanResource,
						objInstance : objInstance ?: beanResource,
				]
	}

	@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER') and isFullyAuthenticated()"])
	def edit(Document documentInstance) {
		if (documentInstance?.id == null) {
			notFound()
			return
		}
		redirectEdit(documentInstance)
	}

	@Transactional
	@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER') and isFullyAuthenticated()"])
	def update(Document documentInstance) {
		if (documentInstance?.id == null) {
			notFound()
			return
		}

		if (documentInstance.hasErrors()) {
			redirectEdit(documentInstance)
			return
		}

		def saved = documentInstance.save flush: true

		if (request.xhr) {
			if (saved) {
				response.contentType = "text/javascript;charset=UTF-8"
				def cols = [
						name: documentInstance.name,
						description: documentInstance.description,
				]
				render template: '/_common/grid-row-update', model: [gridRowId: documentInstance.id, gridCols: cols]
			} else {
				redirectEdit(documentInstance)
			}
		} else {
			request.withFormat {
				'*' {
					flash.message = message(code: saved ? 'default.updated.message' : 'default.notUpdated.message',
							args: [message(code: 'document.label', default: 'Document'), documentInstance.id]
					)
					redirect base: uri,controller: 'document', action: 'index'
				}
				json { respond documentInstance, [status: OK] }
			}
		}
	}


	@Transactional
	@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER') and isFullyAuthenticated()"])
	def savecat(DocumentCategory сategoryInstance) {
		if (сategoryInstance == null) {
			notFound()
			return
		}

		if (сategoryInstance.hasErrors()) {
			сategoryInstance.errors.each { log.error(it) }
			redirect base: uri,controller: 'document', action: 'index'
			return
		}

		DocumentCategory category = new DocumentCategory(name: сategoryInstance.name)
		Document.findAllByCategory(null).each { category.addToDocuments(it) }
		def saved = category.save(flush: true)
		log.debug "Category:" + category


		request.withFormat {
			form {
				flash.message = message(code: 'default.created.message', args: [message(code: 'сategory.label'), category.name])
				redirect base: uri,controller: 'document', action: 'index'
			}
		}
	}

	@Transactional
	@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER') and isFullyAuthenticated()"])
	def delcat(DocumentCategory сategoryInstance) {
		if (сategoryInstance == null) {
			notFound()
			return
		}


		Document.findAllByCategory(сategoryInstance).each { сategoryInstance.removeFromDocuments(it) }
		def saved = сategoryInstance.save(flush: true)
		if (saved) {
			сategoryInstance.delete(flush: true)
		}
		log.debug "Category:" + сategoryInstance


		request.withFormat {
			form {
				redirect base: uri,controller: 'document', action: 'index'
			}
			'*' {
				def result = [error: false]
				response.status = OK.value()
				render result as JSON
			}
		}
	}

	@Transactional
	@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER') and isFullyAuthenticated()"])
	def move(DocumentCategory сategoryInstance) {
		if (сategoryInstance == null) {
			notFound()
			return
		}

		Document.findAllByCategory(null).each { сategoryInstance.addToDocuments(it) }
		def saved = сategoryInstance.save(flush: true)
		log.debug "Category:" + сategoryInstance


		request.withFormat {
			form {
				redirect base: uri,controller: 'document', action: 'index'
			}
			'*' { respond сategoryInstance, [status: OK] }
		}
	}


	@Transactional
	@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER') and isFullyAuthenticated()"])
//	def upload() {
//
//		User user = springSecurityService.getCurrentUser()
//
//		def files = []
//		if (request instanceof MultipartHttpServletRequest) {
//			for (filename in request.getFileNames()) {
//				MultipartFile file = request.getFile(filename)
//
//				def thumbnailUrl = null
//
//				Attachment att = attachService.uploadAttach(file, fileCategoryDirectory, user.profile) {
//					Attachment att, String icon ->
//						att.save(flush: true)
//						log.debug("att errors:" + att.errors)
//						Document dpa = new Document(attachment: att, name: att.name)
//						dpa.save(flush: true)
//						log.debug("dpa errors:" + dpa.errors)
//
//						if (icon) {
//							thumbnailUrl = icon
//						}
//				}
//
//
//				files << [
//						name        : att.name,
//						size        : att.size,
//						url         : createLink([action: 'download', id: att.id, params: [name: att.name]]),
//						thumbnailUrl: thumbnailUrl,
//						deleteUrl   : createLink(action: 'deleteatt', id: att.id, params: [name: att.name]),
//						deleteType  : "DELETE"
//				]
//
//
//			}
//		}
//
//		def results = [files: files]
//		render results as JSON
//
//	}
	def upload() {

		User user = springSecurityService.getCurrentUser()
		def files = []
		if (request instanceof MultipartHttpServletRequest) {
			for (filename in request.getFileNames()) {
				MultipartFile file = request.getFile(filename)
				boolean supported = false

				def thumbnailUrl=null
				Document dpa
				log.debug("mime: " + file.contentType)

				switch (file.contentType) {

					case 'application/octet-stream':
					case 'document/word':

						supported = true

						break;}




				Attachment attachment = attachService.uploadAttach(file, fileCategoryDirectory, user.profile) {
							Attachment attachment, String icon ->
								attachment.save(flush: true)
								log.debug("att errors:" + attachment.errors)
								dpa = new Document(attachment: attachment, name: attachment.name,  )
								dpa.save(flush: true)
								log.debug("dpa errors:" + dpa.errors)

								if (icon) {
									thumbnailUrl = icon
								}
						}
				files << [
						id          : dpa.id,
						name        : attachment.name,
						size        : attachment.size,
						url         : createLink([action: 'download', id: attachment.id, params: [name: attachment.name]]),
						previewUrl  : createLink([action: 'download', id: attachment.id, params: [name: attachment.name, preview: true]]),
						thumbnailUrl: thumbnailUrl,
						deleteUrl   : createLink(action: 'deleteatt', id: attachment.id, params: [name: attachment.name]),
						deleteType  : "DELETE"
				]



			}
		}

		def results = [files: files]
		render results as JSON

	}
	@Transactional
	@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER') and isFullyAuthenticated()"])
	def deleteatt() {
		Attachment att = Attachment.get(params.id)
		log.debug("delete params:" + params)
		def result
		if (att && att.name == params.name) {
			attachService.deleteAttach(fileCategoryDirectory, att) {
				Document.findByAttachment(att).delete(flush: true)
				return true
			}
			result = [files: [["${att.name}": true]]]
			render result as JSON
		} else {
			result = [error: true]
			response.status = NOT_FOUND.value()
			render result as JSON
		}
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
				render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'document.label'), params.id])]
			}
			multipartForm { render status: NOT_FOUND }
			json { render status: NOT_FOUND }
		}
	}

}
