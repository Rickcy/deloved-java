package ru.deloved.admin

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.transform.EqualsAndHashCode
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import ru.deloved.*

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
@Secured(["hasAnyRole('ROLE_ADMIN') and isFullyAuthenticated()"])
class ContentController extends FilteredController {


	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"

	@Override
	protected FormFilter createFilterInstance() {
		return new ContentFilter()
	}

	@Override
	protected PagedResultWrapper getRows(FormFilter filter) {
		ContentFilter f = filter
		def rows = Content.createCriteria().list(max: filter.max, offset: filter.offset) {
			order(filter.sort ?: "id", filter.order ?: "asc")
			if (f.name != null) {
				ilike('name', "%" + f.name + "%")
			}
			if (f.code != null) {
				ilike('code', "%" + f.code + "%")
			}
			if (f.enabled != null) {
				ilike('enabled', f.enabled)
			}
		}
		new PagedResultWrapper(rows)

	}

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(MeasureFilter contentFilter) {
		processIndex(10, contentFilter)
	}

	def show(Content contentInstance) {
		if (contentInstance?.id == null) {
			notFound()
			return
		}
		respond contentInstance
	}

	def create() {
	}

	protected redirectEdit(Content beanResource, objInstance = null) {
		render view: request.xhr ? 'editform' : 'edit',
				model: [
						beanResource: beanResource,
						objInstance : objInstance ?: beanResource,
				]
	}
	protected redirectEdit2(Content beanResource, objInstance = null) {
		render view: request.xhr ? 'editform2' : 'edit2',
				model: [
						beanResource: beanResource,
						objInstance : objInstance ?: beanResource,
				]
	}

	def edit (Content contentInstance) {
		if (contentInstance?.id == null) {
			notFound()
			return
		}
		redirectEdit(contentInstance)
	}

	def edit2 (Content contentInstance) {
		if (contentInstance?.id == null) {
			notFound()
			return
		}
		redirectEdit2(contentInstance)
	}

	@Transactional
	def save(Content contentInstance) {
		if (contentInstance == null) {
			notFound()
			return
		}

		if (contentInstance.hasErrors()) {
			respond contentInstance.errors, view: 'create'
			return
		}

		contentInstance.save flush: true

		request.withFormat {
			form {
				flash.message = message(code: 'default.created.message', args: [message(code: 'contentInstance.label', default: 'Content'), contentInstance.id])
				redirect base: uri,controller: 'content', action: 'index'
			}
			'*' { respond contentInstance, [status: CREATED] }
		}
	}

	@Transactional
	def update(Content contentInstance) {
		if (contentInstance?.id == null) {
			notFound()
			return
		}

		if (contentInstance.hasErrors()) {
			redirectEdit(contentInstance)
			return
		}

		def saved = contentInstance.save flush: true

		if (request.xhr) {
			if (saved) {
				response.contentType = "text/javascript;charset=UTF-8"
				def cols = [
						name: contentInstance.name,
						time:contentInstance.time,
						code: contentInstance.code,
						title: contentInstance.title,
						description: contentInstance.description,
						keywords: contentInstance.keywords,
						enabled: contentInstance.enabled,
						news:contentInstance.news,
						content2:contentInstance.content2
				]
				render template: '/_common/grid-row-update', model: [gridRowId: contentInstance.id, gridCols: cols]
			} else {
				redirectEdit(contentInstance)
			}
		} else {
			request.withFormat {
				'*' {
					flash.message = message(code: saved ? 'default.updated.message' : 'default.notUpdated.message',
							args: [message(code: 'content.label', default: 'Content'), contentInstance.id]
					)
					redirect base: uri,controller: 'content', action: 'index'
				}
				json { respond contentInstance, [status: OK] }
			}
		}
	}

	@Transactional
	def batch(IdListCommand idList) {
		log.trace('idList:' + idList)
		def cnt = 0
		if (params.batch_action == "batchDelete") {
			deleteList(idList)
			return
		} else if (params.batch_action == "batchEnable") {
			if (idList?.id?.size() > 0) {
				cnt = Content.executeUpdate("update Content u set u.enabled = true where u.enabled = false and u.id in (:idlist)", [idlist: idList.getList()])
				flash.message = message(code: 'content.batch.enabled.message', args: [cnt])
			}
		} else if (params.batch_action == "batchDisable") {
			if (idList?.id?.size() > 0) {
				cnt = Content.executeUpdate("update Content u set u.enabled = false where u.enabled = true and u.id in (:idlist)", [idlist: idList.getList()])
				flash.message = message(code: 'content.batch.disabled.message', args: [cnt])
			}
		}
		redirect base: uri,controller: 'content', action: "index", method: "GET"
	}

	private deleteList(IdListCommand idList) {
		def st = NO_CONTENT
		def mess = null
		log.trace('params:' + params)
		log.trace('idList:' + idList)
		if (idList?.id) {
			if (idList.id.size() == 1) {
				def content = Content.get(idList.id[0])
				if (content) {
					content.delete flush: true
					mess = message(code: 'default.deleted.message', args: [message(code: 'content.label', default: 'content'), content.name])
				} else {
					notFound()
					return
				}
			} else if (idList.id.size() > 1) {
				int deleted = 0
				idList.getList().each {
					Content.load(it).delete(flush: true)
					deleted++
				}
				mess = message(code: 'default.deletedMany.message', args: [message(code: 'user.label', default: 'content'), idList.id, deleted])
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
				redirect base: uri,controller: 'content', action: "index", method: "GET"
			}
			'*' { render status: st }
		}
	}

	@Transactional
	def delete(IdListCommand idList) {
		deleteList(idList)
	}

	protected void notFound() {
		request.withFormat {
			'*' {
				render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'content.label', default: 'Content'), params.id])]
			}
			json { render status: NOT_FOUND }
		}
	}
}

@EqualsAndHashCode
class ContentFilter extends FormFilter {
	String name
	String code
	Boolean enabled
	String time
	String title
	String description
	String keywords
	String content2
	String news

	@Override
	protected List getFilterParamList() {
		['name', 'code', 'enabled']
	}

	@Override
	protected List getSortedParamList() {
		['name', 'code']
	}

	@Override
	public Map getMap() {
		def m = super.getMap()
		if (name != null) m.name = name
		if (code != null) m.code = code
		if (enabled != null) m.enabled = enabled
		return m
	}
}
