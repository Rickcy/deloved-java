package ru.deloved.admin

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.transform.EqualsAndHashCode
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import ru.deloved.FilteredController
import ru.deloved.FormFilter
import ru.deloved.IdListCommand
import ru.deloved.PagedResultWrapper

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER') and isFullyAuthenticated()"])
class CategoryController extends FilteredController {


	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	@Override
	protected FormFilter createFilterInstance() {
		return new CategoryFilter()
	}

	@Override
	protected PagedResultWrapper getRows(FormFilter filter) {
		CategoryFilter f = filter
		def rows = ru.deloved.Category.createCriteria().list(max: filter.max, offset: filter.offset) {
			if (f.id && f.id > 0) {
				eq('parent.id', f.id)
			} else {
				isNull('parent')
			}
			order(filter.sort ?: "id", filter.order ?: "asc")
		}
		return new PagedResultWrapper(rows)
	}

	void loadParents(ru.deloved.Category el, ArrayList parents) {
		if (el) {
			parents.add([id: el.id, name: el.name])
			loadParents(el.parent, parents)
		}
	}

	def index(CategoryFilter catFilter) {
		processIndex(20, catFilter) {
			CategoryFilter filter ->
				log.debug('p:' + catFilter)
				log.debug('f:' + filter)
				if (filter.id) {
					def parentsList = []
					def parentCat = ru.deloved.Category.get(filter.id)
					loadParents(parentCat, parentsList)
					[model: [
							breadcrumbList: parentsList.reverse(),
							parent        : parentCat
					]]
				}
		}
	}

	def create() {
		CategoryFilter filter = getFilter()
		if (filter.id) {
			def parentCat = ru.deloved.Category.get(filter.id)
			respond new CategoryCreateCommand(), [model: [parent: parentCat], view: 'create']
		} else {
			flash.message = message(code: 'category.parentNotSelect.label')
			redirect base: uri,controller: 'category', action: 'index'
		}
	}

	protected redirectEdit(ru.deloved.Category beanResource, objInstance = null) {
		render view: request.xhr ? 'editform' : 'edit',
				model: [
						beanResource: beanResource,
						objInstance : objInstance ?: beanResource,
				]

	}

	def edit(ru.deloved.Category catInstance) {
		log.debug("edit:" + catInstance);
		if (catInstance?.id == null) {
			notFound()
			return
		}
		CategoryEditCommand catEditCommandInstance = new CategoryEditCommand()
		bindData(catEditCommandInstance, catInstance)
		redirectEdit(catInstance, catEditCommandInstance)
	}

	@Transactional
	def save(CategoryCreateCommand сategoryInstance) {
		if (сategoryInstance == null) {
			notFound()
			return
		}

		ru.deloved.Category parent = ru.deloved.Category.get(сategoryInstance.parentId)

		if (сategoryInstance.hasErrors()) {
			сategoryInstance.errors.each { log.error(it) }
			if (сategoryInstance.parentId) {
				respond сategoryInstance.errors, [model: [parent: parent], view: 'create']
			} else {
				flash.message = message(code: 'category.parentNotSelect.label')
				redirect base: uri,controller: 'category', action: 'index'
			}
			return
		}

		ru.deloved.Category сategory = new ru.deloved.Category(name: сategoryInstance.name, parent: parent)
		def saved = сategory.save(flush: true)
		log.debug "Category:" + сategory

		request.withFormat {
			form {
				flash.message = message(code: 'default.created.message', args: [message(code: 'сategory.label'), сategory.name])
				redirect base: uri,controller: 'category', action: 'index'
			}
			'*' { respond сategory, [status: CREATED] }
		}
	}

	@Transactional
	def update(CategoryEditCommand сategoryEditCommandInstance) {
		log.debug("update:" + сategoryEditCommandInstance);
		log.error("params:" + params)
		ru.deloved.Category category = ru.deloved.Category.get(params.id)
		if (category == null) {
			notFound()
			return
		}

		if (сategoryEditCommandInstance.hasErrors()) {
			log.error("has errors:" + сategoryEditCommandInstance.errors)
			log.error("categoryInstance:" + category)
			redirectEdit(category, сategoryEditCommandInstance)
			return
		}
		bindData(category, сategoryEditCommandInstance)
		def saved = category.save(flush: true)
		log.debug(category);

		if (request.xhr) {
			if (saved) {
				response.contentType = "text/javascript;charset=UTF-8"
				def cols = [
						name: category.name
				]
				render template: '/_common/grid-row-update', model: [gridRowId: category.id, gridCols: cols]
			} else {
				redirectEdit(category)
			}
		} else {
			request.withFormat {
				'*' {
					flash.message = message(code: saved ? 'default.updated.message' : 'default.notUpdated.message',
							args: [message(code: 'category.label', default: 'Category'), category.id]
					)
					redirect base: uri,controller: 'category', action: 'index'
				}
				json { respond category, [status: OK] }
			}
		}
	}

	@Transactional
	@Secured(["hasRole('ROLE_ADMIN')"])
	def delete(IdListCommand idList) {

		def st = NO_CONTENT
		def mess = null

		log.trace('params:' + params)
		log.trace('idList:' + idList)
		if (idList?.id) {
			if (idList.id.size() == 1) {
				def category = ru.deloved.Category.get(idList.id[0])
				if (category) {
					category.delete flush: true
					mess = message(code: 'default.deleted.message', args: [message(code: 'category.label', default: 'Category'), category.name])
				} else {
					notFound()
					return
				}
			} else if (idList.id.size() > 1) {
				int deleted = 0
				idList.getList().each {
					ru.deloved.Category.load(it).delete()
					deleted++
				}
//				def cr = User.createCriteria()
//				cr.list {
//					inList('id', idList.getList())
//				}.each {
//					it.delete()
//					deleted++
//				}
				mess = message(code: 'default.deletedMany.message', args: [message(code: 'category.label', default: 'Category'), idList.id, deleted])
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
				redirect base: uri,controller: 'category', action: "index", method: "GET"
			}
			'*' { render status: st }
		}
	}

	protected void notFound() {
		request.withFormat {
			form {
				render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'category.label', default: 'Category'), params.id])]
			}
			'*' { render status: NOT_FOUND }
		}
	}
}


class CategoryCreateCommand {
	Integer parentId;
	String name;

	static constraints = {
		parentId(nullable: false)
		name(blank: false)
	}

}

class CategoryEditCommand {

	int id
	String name

	static constraints = {
		name(blank: false)
	}

	@Override
	public java.lang.String toString() {
		return "CategoryEditCommand{" +
				"name='" + name + '\'' +
				", id=" + id +
				"}";
	}
}


@EqualsAndHashCode
class CategoryFilter extends FormFilter {
	Long id;

	@Override
	protected List getFilterParamList() {
		['id']
	}

	@Override
	protected List getSortedParamList() {
		['name']
	}

	@Override
	public Map getMap() {
		def m = super.getMap()
		if (id != null) m.id = id;
		return m
	}
}