package ru.deloved.admin


import grails.plugin.springsecurity.annotation.Secured
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import ru.deloved.User
import ru.deloved.recall.Suggestion
import ru.deloved.recall.SuggestionCategory
import static org.springframework.http.HttpStatus.FORBIDDEN

@Secured(["hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER','ROLE_SUPPORT') and isFullyAuthenticated()"])
class SuggestionCategoriesController {


	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"
	def springSecurityService

	def index() {
		User user = springSecurityService.getCurrentUser()
		if (user && user.role.authority in ['ROLE_ADMIN', 'ROLE_MANAGER','ROLE_SUPPORT']) {

			def suggestionCategories = SuggestionCategory.findAll()

			render view: 'index', model: [sugCategories: suggestionCategories]
			return
		} else {
			render status: FORBIDDEN
			return
		}
		return
	}

	def saveSuggestionCategory() {

		if (!params.sugCatName) {
			//render(contentType: 'text/json') {[status: 'sugCatName exist']}
			return
		}

		User user = springSecurityService.getCurrentUser()
		if (user && user.role.authority in ['ROLE_ADMIN', 'ROLE_MANAGER','ROLE_SUPPORT']) {
			SuggestionCategory sugCat = new SuggestionCategory(name: params.sugCatName)
			SuggestionCategory sugCat2 = new SuggestionCategory(name: params.sugCatName2)
			sugCat.validate()
			if (!sugCat.hasErrors()) {
				sugCat.save(flush: true)
			//	render template: 'suggestionCategories', model: [sugCategories: SuggestionCategory.findAll()]
				render template: 'suggestionCategory', model: [cat: sugCat,
				cat2: sugCat2]
				return
			} else {
				sugCat.errors.each {
					log.debug(it)
				}
				sugCat2.validate()
				if (!sugCat2.hasErrors(){
					sug
				})
			//	render(contentType: 'text/json') {[status: 'error']}
				//render template: 'suggestionCategories', model: [sugCategories: SuggestionCategory.findAll(), objInstance: sugCat.errors]
				return
			}
		}
		//render(contentType: 'text/json') {[status: 'access denied']}
		return
	}

	def deleteSuggestionCategory(SuggestionCategory sugCat) {

		log.debug(params)

		if (!sugCat) {
			render(contentType: 'text/json') {[status: 'sugCat dont exist']}
			return
		}
		if (!sugCat2)

		User user = springSecurityService.getCurrentUser()
		if (user && user.role.authority in ['ROLE_ADMIN', 'ROLE_MANAGER','ROLE_SUPPORT']) {
			def suggestions = Suggestion.findAllByCategory(sugCat)
			log.debug(sugCat)
			log.debug(suggestions)
			if (suggestions) {
				suggestions.each {
					it.category = null
				}
			}
			sugCat.delete(flush: true)
			//render template: 'suggestionCategories', model: [sugCategories: SuggestionCategory.findAll()]
			render(contentType: 'text/json') {[status: 'success']}
			return
		}

		render(contentType: 'text/json') {[status: 'access denied']}
		return
	}

	def updateSuggestionCategory(SuggestionCategory sugCat) {

		if (!sugCat || !params.sugCatName ) {
			render(contentType: 'text/json') {[status: 'sugCatName or sugCat dont exist']}
			return
		}

		User user = springSecurityService.getCurrentUser()
		if (user && user.role.authority in ['ROLE_ADMIN', 'ROLE_MANAGER','ROLE_SUPPORT']) {
			sugCat.name = params.sugCatName

			sugCat.save(flush: true)
			//render template: 'suggestionCategories', model: [sugCategories: SuggestionCategory.findAll()]
			render(contentType: 'text/json') {[status: 'success']}
			return
		}
		render(contentType: 'text/json') {[status: 'access denaid']}
		return
	}

}
