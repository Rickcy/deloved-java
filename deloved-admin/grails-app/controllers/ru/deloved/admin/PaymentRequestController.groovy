package ru.deloved.admin

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import ru.deloved.FilteredController
import ru.deloved.FormFilter
import ru.deloved.PagedResultWrapper
import ru.deloved.billing.PaymentRequest
import ru.deloved.billing.RequestStatus

import static org.springframework.http.HttpStatus.NOT_FOUND

@Transactional(readOnly = true)
@Secured(["hasAnyRole('ROLE_ADMIN') and isFullyAuthenticated()"])
class PaymentRequestController extends FilteredController {

	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"
	@Override
	protected FormFilter createFilterInstance() {
		return new PaymentRequestFilter(status: RequestStatus.New.value())
	}

	@Override
	protected PagedResultWrapper getRows(FormFilter filter) {
		PaymentRequestFilter f = filter

		def rows = PaymentRequest.createCriteria().list(max: filter.max, offset: filter.offset) {
			if (f.id != null) {
				eq('id', f.id)
			}
			if (f.status != null) {
				eq('status', f.status)
			}
			order(filter.sort ?: "dateCreated", filter.order ?: "desc")
		}
		new PagedResultWrapper(rows)
	}

	def index(PaymentRequestFilter filter) {
		processIndex(10, filter)
	}

	def show(PaymentRequest paymentRequest) {
		if (paymentRequest?.id == null) {
			notFound()
			return
		}

		respond paymentRequest
	}

	def status(PaymentRequest paymentRequest) {
		log.debug('params:' + params)
		if (paymentRequest?.id == null) {
			notFound()
			return
		}
		if (paymentRequest.method.code == 'INCOME_MANUAL') {
			if (params._action_approve) {
				log.debug(("APPROVE"))
//			paymentRequest.lock()
//			paymentRequest
			} else if (params._action_decline) {
				log.debug(("DECLIVE"))

			}
		}
		redirect(base: uri,controller: 'paymentRequest', action: 'show', id: paymentRequest.id)
	}

	@Transactional
	def approve(PaymentRequest paymentRequest) {
		log.debug('params:' + params)
		log.debug("approve action")
		if (paymentRequest?.id == null) {
			notFound()
			return
		}
		if (paymentRequest.method.code == 'INCOME_MANUAL') {
			paymentRequest.lock()
			paymentRequest.status = RequestStatus.Executed.value()
			paymentRequest.save(flush: true)
		}
		redirect(base: uri,controller: 'paymentRequest',action: 'show', id: paymentRequest.id)
	}

	@Transactional
	def decline(PaymentRequest paymentRequest) {
		log.debug('params:' + params)
		log.debug("decline action")
		if (paymentRequest?.id == null) {
			notFound()
			return
		}
		if (paymentRequest.method.code == 'INCOME_MANUAL') {
			paymentRequest.lock()
			paymentRequest.status = RequestStatus.Failed.value()
			paymentRequest.save(flush: true)
		}
		redirect(base: uri,controller: 'paymentRequest',action: 'show', id: paymentRequest.id)
	}

	protected void notFound() {
		request.withFormat {
			'*' {
				render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'paymentRequest.label', default: 'Payment request'), params.id])]
			}
			json { render status: NOT_FOUND }
		}
	}

}

class PaymentRequestFilter extends FormFilter {
	String name
	Integer status
	Long id

	@Override
	protected List getFilterParamList() {
		['name', 'status', 'id']
	}

	@Override
	protected List getSortedParamList() {
		['name', 'dateCreated', 'status', 'id']
	}

	@Override
	public Map getMap() {
		def m = super.getMap()
		if (name != null) m.name = name;
		if (status != null) m.status = status;
		if (id != null) m.id = id;
		return m
	}
}
