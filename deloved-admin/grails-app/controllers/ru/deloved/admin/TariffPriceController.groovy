package ru.deloved.admin

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.transform.EqualsAndHashCode
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import org.springframework.web.servlet.support.RequestContextUtils
import ru.deloved.*
import ru.deloved.billing.TariffPrice

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
@Secured(["hasAnyRole('ROLE_ADMIN') and isFullyAuthenticated()"])
class TariffPriceController extends FilteredController {

	@Override
	protected FormFilter createFilterInstance() {
		return new TariffPriceFilter()
	}

	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"

	@Override
	protected PagedResultWrapper getRows(FormFilter filter) {
		TariffPriceFilter f = filter
		def rows = TariffPrice.createCriteria().list(max: filter.max, offset: filter.offset) {
			if (f.currencyId != null) {
				eq('currency', SystemCurrency.get(f.currencyId))
			}
			order(filter.sort ?: "id", filter.order ?: "asc")
		}
		new PagedResultWrapper(rows)

	}

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(TariffPriceFilter tariffFilter) {
		processIndex(10, tariffFilter)
	}

	def show(TariffPrice tariffInstance) {
		if (tariffInstance?.id == null) {
			notFound()
			return
		}
		respond tariffInstance
	}

	def create() {
		render view: 'create', model: [
				tariffInstance: new TariffPrice(price: BigDecimal.ZERO),
				requestLocale : RequestContextUtils.getLocale(request)
		]
	}

	protected redirectEdit(TariffPrice beanResource, objInstance = null) {
		render view: request.xhr ? 'editform' : 'edit',
				model: [
						beanResource : beanResource,
						objInstance  : objInstance ?: beanResource,
						requestLocale: RequestContextUtils.getLocale(request)
				]
	}

	def edit(TariffPrice tariffInstance) {
		if (tariffInstance?.id == null) {
			notFound()
			return
		}
		redirectEdit(tariffInstance)
	}

	@Transactional
	def save(TariffPrice tariffInstance) {
		if (tariffInstance == null) {
			notFound()
			return
		}

		if (tariffInstance.hasErrors()) {
			respond tariffInstance.errors, view: 'create'
			return
		}

		tariffInstance.save flush: true

		request.withFormat {
			form {
				flash.message = message(code: 'default.created.message', args: [message(code: 'tariff.label', default: 'TariffPrice'), tariffInstance.id])
				redirect base: uri,controller: 'tariffPrice', action: 'index'
			}
			'*' { respond tariffInstance, [status: CREATED] }
		}
	}

	@Transactional
	def update(TariffPrice tariffInstance) {
		if (tariffInstance?.id == null) {
			notFound()
			return
		}

		if (tariffInstance.hasErrors()) {
			redirectEdit(tariffInstance)
			return
		}

		def saved = tariffInstance.save flush: true

		if (request.xhr) {
			if (saved) {
				response.contentType = "text/javascript;charset=UTF-8"
				def cols = [
						name : tariffInstance.name,
						price: formatNumber(number: tariffInstance.price, type: 'currency', currencyCode: (tariffInstance.currency?.code) ?: 'RUB')
				]
				render template: '/_common/grid-row-update', model: [gridRowId: tariffInstance.id, gridCols: cols]
			} else {
				redirectEdit(tariffInstance)
			}
		} else {
			request.withFormat {
				'*' {
					flash.message = message(code: saved ? 'default.updated.message' : 'default.notUpdated.message',
							args: [message(code: 'tariff.label', default: 'TariffPrice'), tariffInstance.id]
					)
					redirect base: uri,controller: 'tariffPrice', action: 'index'
				}
				json { respond tariffInstance, [status: OK] }
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
		redirect base: uri,controller: 'tariffPrice', action: "index", method: "GET"
	}

	private deleteList(IdListCommand idList) {
		def st = NO_CONTENT
		def mess = null
		log.trace('params:' + params)
		log.trace('idList:' + idList)
		if (idList?.id) {
			if (idList.id.size() == 1) {
				def tariff = TariffPrice.get(idList.id[0])
				if (tariff) {
					tariff.delete flush: true
					mess = message(code: 'default.deleted.message', args: [message(code: 'tariff.label', default: 'tariff'), tariff.name])
				} else {
					notFound()
					return
				}
			} else if (idList.id.size() > 1) {
				int deleted = 0
				idList.getList().each {
					TariffPrice.load(it).delete(flush: true)
					deleted++
				}
				mess = message(code: 'default.deletedMany.message', args: [message(code: 'tariff.label', default: 'tariff'), idList.id, deleted])
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
				redirect base: uri,controller: 'tariffPrice', action: "index", method: "GET"
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
				render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'tariff.label', default: 'TariffPrice'), params.id])]
			}
			json { render status: NOT_FOUND }
		}
	}

}

@EqualsAndHashCode
class TariffPriceFilter extends FormFilter {
	Long currencyId

	@Override
	protected List getFilterParamList() {
		['currencyId']
	}

	@Override
	protected List getSortedParamList() {
		['name', 'price']
	}

	@Override
	public Map getMap() {
		def m = super.getMap()
		if (currencyId != null) m.currencyId = currencyId
		return m
	}
}
