package ru.deloved.front

import grails.plugin.springsecurity.annotation.Secured
import ru.deloved.*

import static org.springframework.http.HttpStatus.NOT_FOUND

@Secured(["permitAll"])
class CompanyController {

	def accountService
	def springSecurityService

	def index(Account accountInstance) {
		log.debug("id:" + params.id)
		if (accountInstance?.id == null /*|| params.id == null*/) {
			notFound()
			return
		}
		def myAcc = false
		if (springSecurityService.isLoggedIn()) {
			User user = springSecurityService.getCurrentUser()
			myAcc = AccountProfile.findByAccountAndProfile(accountInstance, user.profile) != null
		}
		log.debug("myAcc:" + myAcc)
		log.debug("accountInstance:" + accountInstance)


		def goods = Item.createCriteria().list(max: 16) {
			eq('categoryType', ru.deloved.CategoryType.findByCode('GOOD'))
			eq('account', accountInstance)
		}
		def services = Item.createCriteria().list(max: 16) {
			eq('categoryType', ru.deloved.CategoryType.findByCode('SERVICE'))
			eq('account', accountInstance)
		}


		def reviews = Review.findAllByToAndPublishedAndFinished(accountInstance, true, true)

		accountService.updateStatViewAccount(accountInstance)

		respond accountInstance, [model: [
				itsMyAccount: myAcc,
				goods       : goods,
				services    : services,
				reviewsCount: reviews.size(),
				reviews     : reviews,

		]]
	}

	def reviews(Account accountInstance) {
		if (accountInstance?.id == null /*|| params.id == null*/) {
			notFound()
			return
		}
		def reviews = Review.findAllByToAndPublishedAndFinished(accountInstance, true, true)

		render view: 'reviews',
				model: [
						accountInstance: accountInstance,
						reviewsCount   : reviews.size(),
						reviews        : reviews
				]
	}

	protected void notFound() {
		render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'company.label'), params.id])]
	}

}
