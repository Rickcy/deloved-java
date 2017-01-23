package ru.deloved.admin

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import org.hibernate.criterion.CriteriaSpecification
import ru.deloved.*

@Transactional(readOnly = true)
@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_ACCOUNT') and isFullyAuthenticated()"])
class ReviewController extends FilteredController {
	def accountService
	def profileService
	def reviewService


	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"
	@Override
	protected FormFilter createFilterInstance() {
		return new ReviewFilter()
	}

	@Override
	protected PagedResultWrapper getRows(FormFilter filter) {
		ReviewFilter f = filter
		User user = springSecurityService.getCurrentUser()
		def list = accountService.getMyAccounts()
		def regFilter = []
		if (user.role.authority =='ROLE_MANAGER') {
			regFilter = profileService.findAllCitiesByProfile(user.profile)
		}
		def toMe = false
		def rows = Review.createCriteria().list(max: filter.max, offset: filter.offset) {
			if (user.role.authority == 'ROLE_ACCOUNT') {
				if (f.type == null || f.type == 'from') {
					inList("from", list)
				} else {
					toMe = true
					and {
						inList("to", list)
						eq("finished", true)
						eq("published", true)
					}
				}
			} else if (user.role.authority =='ROLE_MANAGER') {
				createAlias('from', 'from', CriteriaSpecification.LEFT_JOIN)
				createAlias('to', 'to', CriteriaSpecification.LEFT_JOIN)
				or {
					inList("from.city", regFilter)
					inList("to.city", regFilter)
				}
			}
			if (f.status != null && !toMe) {
				if (f.status == 0) {// черновик
					if (user.role.authority == 'ROLE_ACCOUNT') {
						isNull("value")
					}
				} else if (f.status == 1) {// На модерации
					eq("finished", false)
					isNotNull("value")        // исключаем черновики
				} else if (f.status == 2) {// опубликован
					eq("finished", true)
					eq("published", true)
				} else if (f.status == 3) {// Отклонен
					eq("finished", true)
					eq("published", false)
				}
			}
			if (user.role.authority != 'ROLE_ACCOUNT') {// если не участник
				isNotNull("value")                    // то исключаем черновики
			}
			if (f.value != null) {
				eq("value", f.value > 0 ? 1 : (f.value < 0 ? -1 : 0))
			}
			if (f.search != null) {
				ilike "content", "%" + f.search + "%"
			}
			order(filter.sort ?: "dateCreated", filter.order ?: "desc")
		}
		new PagedResultWrapper(rows)
	}

	def index(ReviewFilter reviewFilter) {
		User user = springSecurityService.getCurrentUser()
		ReviewFilter f = getFilter(10, reviewFilter)
		log.debug("ReviewFilter:" + f)
		log.debug(user.role.authority)
		log.debug("user.role.authority == 'ROLE_ACCOUNT':" + (user.role.authority == "ROLE_ACCOUNT"))
		log.debug("type != null:" + (f.type != null))
		log.debug("type != 'from':" + (f.type != 'from'))
		def toMe = ((user.role.authority == 'ROLE_ACCOUNT') && (f.type != null) && (f.type != 'from'))
		log.debug("toMe:" + toMe)
		processIndex(10, reviewFilter) {
			[model: [
					statusFilterValues: ((user.role.authority != 'ROLE_ACCOUNT') ? [1, 2, 3] : [0, 1, 2, 3]),
					toMe              : toMe
			]]
		}
	}


	def show(Review reviewInstance) {
		if (!reviewInstance) {
			notFound()
		}

		Boolean canEdit = reviewService.canEdit(reviewInstance)

		if (canEdit) {
			redirectShow(reviewInstance, canEdit)
		} else {
			//до момента публикации отзыва не существует для лиц, которые не могут его редактировать
			notFound()
		}
	}

	protected redirectShow(Review reviewInstance, Boolean canEdit) {
		if (reviewInstance.finished || canEdit) {
			respond reviewInstance, view: 'show'
		} else {
			notFound()
		}
	}

	@Transactional
	@Secured(["hasAnyRole('ROLE_ACCOUNT') and isFullyAuthenticated()"])
	def create(Deal deal) {
		if (!deal) {//не указана сделка
			flash.message = 'Сделка не указана.'
			redirect(base: uri,controller: 'review')
		} else if (![DealStatus.Confirmed.value(), DealStatus.Failed.value()].contains(deal.status)) {	//сделка не завершена
			flash.message = 'Сделка не завершена.'
			redirect(base: uri,controller: 'review')
		} else if (reviewService.isReviewExist(deal)) { //данный пользователь уже оставлял отзыв к этой сделке
			flash.message = 'Вы уже оставили свой отзыв к этой сделке.'
			redirect(base: uri,controller: 'review')
		} else if (reviewService.isOldDeal(deal)) { //сделка завершилась давно
			flash.message = 'Отзыв возможно оставлять только не позднее 30 с момента её завершения'
			redirect(base: uri,controller: 'review')
		} else {
			/*
			Изначальная задумка была в том, что каждой учетной записи могло бы соответствовать сразу несколько предприятий / ип
			От этой идеи отказались еще до моего прихода, что переписывать код не стали, оставили возможность на будущее
			 */
			def myAccounts = accountService.getMyAccounts()
			Account to, from
			if (myAccounts) {
				if (myAccounts.contains(deal.partner)) {
					to = deal.account
					from = deal.partner
				}
				else if (myAccounts.contains(deal.account)) {
					to = deal.partner
					from = deal.account
				}
				else {
					flash.message = 'Вы не являетесь участником той сделки, к которой хотите оставить отзыв'
					redirect(base: uri,controller: 'review')
					return
				}
			}
			User user = springSecurityService.getCurrentUser()
			Review review = new Review(to: to,	from: from,	author: user.profile, deal: deal)
			redirectCreate(review)
		}
	}

	protected redirectCreate(Review review) {
		render view: 'create', model: [
				reviewInstance: review
		]
	}

	@Transactional
	@Secured(["hasAnyRole('ROLE_ACCOUNT') and isFullyAuthenticated()"])
	def save(Review reviewInstance) {
		if (!reviewInstance) {
			flash.message = 'Отсутсвует отзыв'
			redirect(base: uri,controller: 'review')
		} else {
			reviewInstance.validate()
			if(reviewInstance.hasErrors()) {
				reviewInstance.errors.each {
					log.debug(it)
				}
				redirectCreate(reviewInstance)
			} else {
				def saved = reviewInstance.save(flush: true)
				if (saved) {
					flash.message = "Отзыв оставлен. Ожидайте одобрения модератора."
					flash.status = "success"
					redirect(base: uri,controller: 'review', action: 'index')
				} else {
					flash.message = "Сбой при попытке создать отзыв."
					flash.status = "warning"
					redirect(base: uri,controller: 'review', action: 'index')
				}
			}
		}
	}

	def edit(Review reviewInstance) {
		if (reviewInstance?.id == null) {
			notFound()
			return
		}
		Boolean canEdit = reviewService.canEdit(reviewInstance)
		if (canEdit) {
			redirectEdit(reviewInstance, canEdit)
		} else if (reviewInstance.finished) {
			redirectShow(reviewInstance, canEdit)
		}
	}


	protected redirectEdit(Review reviewInstance, Boolean canEdit) {
		render view: 'edit', model: [
				reviewInstance: reviewInstance,
				canEdit: canEdit
		]
	}

	@Transactional
	def update(Review reviewInstance) {
		if(!reviewInstance){
			notFound()
			return
		}

		Boolean canEdit = reviewService.canEdit(reviewInstance)
		if(canEdit){
			/*
			Предполагалось разделить возможно доступа к редактированию отзыва и статус публичности отзыва,
			но реализацию этой идеи отложили, по этому статус закончености отзыва и статус публичности по сути одна переменная
			 */
			reviewInstance.finished = reviewInstance.published
			reviewInstance.validate()
			if(reviewInstance.hasErrors()){
				reviewInstance.errors.each {
					log.debug(it)
				}
				redirectEdit(reviewInstance, canEdit)
			} else {
				def saved = reviewInstance.save(flush: true)
				if (saved) {
					flash.message = 'Отзыв успешно изменен'
					flash.status = 'success'
					redirect action: 'index'
				} else {
					flash.message = 'Сбой при попытке изменить отзыв. При повторном сбое обратитесь к администрации портала'
					flash.status = 'warning'
					redirect action: 'index'
				}
			}
		} else {
			flash.message = 'У вас нет прав изменять данный отзыв'
			flash.status = 'warning'
			redirect action: 'index'
		}
	}

	@Transactional
	def deleteRev(Review reviewInstance){
		if (!reviewInstance || !request.xhr) {
			render status: 404, text: 'NOT_FOUND'
			return
		}

		reviewInstance.delete(flush: true)
	}

	@Transactional
	def delete(Review reviewInstance) {
		if (!reviewInstance){
			render status: 404
			return
		}
		if (reviewService.canEdit(reviewInstance)) {
			reviewInstance.delete(flush: true)
			if (request.xhr) {
				render status: 200
			} else {
				redirect action: 'index'
				flash.message = 'Отзыв успешно удален'
				flash.status = 'success'
			}
			//return
		} else {
			if (request.xhr) {
				render status: 403
			} else {
				redirect action: 'index'
				flash.message = 'У вас нет прав на удаление этого отзыва'
				flash.status = 'success'
			}
		}
	}

	protected void notFound() {
		render view: '/notfound'
	}
}

class ReviewFilter extends FormFilter {
	String search
	Integer status
	Integer value
	String type

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
		["search", "status", "value", "type"]
	}

	@Override
	protected List getSortedParamList() {
		["dateCreated"]
	}

	@Override
	public Map getMap() {
		def m = super.getMap()
		if (search != null) m.search = search
		if (status != null) m.status = status
		if (value != null) m.value = value
		if (type != null) m.type = type
		return m
	}

}