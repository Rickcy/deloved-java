package ru.deloved.admin

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.transform.EqualsAndHashCode
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import ru.deloved.*

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER') and isFullyAuthenticated()"])
class SystemCurrencyController extends FilteredController {

	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"

	@Override
	protected FormFilter createFilterInstance() {
		return new CurrencyFilter()
	}

	@Override
	protected PagedResultWrapper getRows(FormFilter filter) {
		CurrencyFilter f = filter
		def rows = SystemCurrency.createCriteria().list(max: filter.max, offset: filter.offset) {
			if (f.name != null) {
				or {
					ilike('name', "%" + f.name + "%")
					ilike('code', "%" + f.name + "%")
					ilike('digit3', "%" + f.name + "%")
				}
			}
			order(filter.sort ?: "id", filter.order ?: "asc")
		}
		new PagedResultWrapper(rows)

	}

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(CurrencyFilter currencyFilter) {
		processIndex(10, currencyFilter)
	}

	def show(SystemCurrency currencyInstance) {
		if (currencyInstance?.id == null) {
			notFound()
			return
		}
		respond currencyInstance
	}

	def create() {
	}

	protected redirectEdit(SystemCurrency beanResource, objInstance = null) {
		render view: request.xhr ? 'editform' : 'edit',
				model: [
						beanResource: beanResource,
						objInstance : objInstance ?: beanResource,
				]
	}

	def edit(SystemCurrency currencyInstance) {
		if (currencyInstance?.id == null) {
			notFound()
			return
		}
		redirectEdit(currencyInstance)
	}

	@Transactional
	def save(SystemCurrency currencyInstance) {
		if (currencyInstance == null) {
			notFound()
			return
		}

		if (currencyInstance.hasErrors()) {
			respond currencyInstance.errors, view: 'create'
			return
		}

		currencyInstance.save flush: true

		request.withFormat {
			form {
				flash.message = message(code: 'default.created.message', args: [message(code: 'currencyInstance.label', default: 'SystemCurrency'), currencyInstance.id])
				redirect base: uri,controller: 'systemCurrency', action: 'index'
			}
			'*' { respond currencyInstance, [status: CREATED] }
		}
	}

	@Transactional
	def update(SystemCurrency currencyInstance) {
		if (currencyInstance?.id == null) {
			notFound()
			return
		}

		if (currencyInstance.hasErrors()) {
			redirectEdit(currencyInstance)
			return
		}

		def saved = currencyInstance.save flush: true

		if (request.xhr) {
			if (saved) {
				response.contentType = "text/javascript;charset=UTF-8"
				def cols = [
						name: currencyInstance.name,
						code: currencyInstance.code,
						digit3: currencyInstance.digit3
				]
				render template: '/_common/grid-row-update', model: [gridRowId: currencyInstance.id, gridCols: cols]
			} else {
				redirectEdit(currencyInstance)
			}
		} else {
			request.withFormat {
				'*' {
					flash.message = message(code: saved ? 'default.updated.message' : 'default.notUpdated.message',
							args: [message(code: 'currency.label', default: 'SystemCurrency'), currencyInstance.id]
					)
					redirect base: uri,controller: 'systemCurrency', action: 'index'
				}
				json { respond currencyInstance, [status: OK] }
			}
		}
	}

	@Transactional
	def batch(IdListCommand idList) {
		log.trace('idList:' + idList)
		if (params.batch_action == "batchDelete") {
			deleteList(idList)
			return
		}
		redirect base: uri,controller: 'systemCurrency', action: "index", method: "GET"
	}

	private deleteList(IdListCommand idList) {
		def st = NO_CONTENT
		def mess = null
		log.trace('params:' + params)
		log.trace('idList:' + idList)
		if (idList?.id) {
			if (idList.id.size() == 1) {
				def currency = SystemCurrency.get(idList.id[0])
				if (currency) {
					currency.delete flush: true
					mess = message(code: 'default.deleted.message', args: [message(code: 'currency.label', default: 'currency'), currency.name])
				} else {
					notFound()
					return
				}
			} else if (idList.id.size() > 1) {
				int deleted = 0
				idList.getList().each {
					SystemCurrency.load(it).delete(flush: true)
					deleted++
				}
				mess = message(code: 'default.deletedMany.message', args: [message(code: 'user.label', default: 'currency'), idList.id, deleted])
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
				redirect base: uri,controller: 'systemCurrency', action: "index", method: "GET"
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
				render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'currency.label', default: 'SystemCurrency'), params.id])]
			}
			json { render status: NOT_FOUND }
		}
	}

}

@EqualsAndHashCode
class CurrencyFilter extends FormFilter {
	String name

	@Override
	protected List getFilterParamList() {
		['name']
	}

	@Override
	protected List getSortedParamList() {
		['name', 'code', 'digit3']
	}

	@Override
	public Map getMap() {
		def m = super.getMap()
		if (name != null) m.name = name
		return m
	}
}
