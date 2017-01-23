package ru.deloved.admin

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import ru.deloved.User
import ru.deloved.recall.Suggestion
import ru.deloved.recall.SuggestionCategory

import static org.springframework.http.HttpStatus.FORBIDDEN


class SuggestionController {

	def springSecurityService
	def recaptchaService

	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"
	@Secured(["isFullyAuthenticated()"])
	def getSuggestionCategories() {
		User user = springSecurityService.getCurrentUser()
		if (user && request.xhr){
			def categories = SuggestionCategory.findAll()
			log.debug('suggestionController.getSuggestionCategories.categories: ' + categories)
			render categories as JSON
			return
		}
		render status: FORBIDDEN
		return
	}

	@Secured(["hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER') and isFullyAuthenticated()"])
	def index() {
		User user = springSecurityService.getCurrentUser()

		if (user && user.role.authority in ['ROLE_ADMIN', 'ROLE_MANAGER']) {

			def suggestions = Suggestion.createCriteria()
			def result = suggestions.list (max: params.max ?: 10, offset: params.offset ?: 0) {
				if (params.category && params.category != 'null') {
					eq('category', SuggestionCategory.findById(params.category))
				}
				if (params.category == 'null'){
					isNull('category')
				}
				if (params.from) {
					ge('datePublished', params.from)
				}
				if (params.till) {
					le('datePublished', params.till)
				}
				order('datePublished', params.order ?: 'desc')
			}

			def sugCategories = SuggestionCategory.findAll()

			render view: 'index', model: [
					suggestions: result,
					sugCategories: sugCategories,
					sugTotal: Suggestion.count
			]

			return

		} else {
			render status: FORBIDDEN
			return
		}
	}


	@Secured(["isFullyAuthenticated()"])
	@Transactional
	def create() {
		User user = springSecurityService.getCurrentUser()
		if(!user){
			render status: FORBIDDEN
			return
		}
/*
		if (!recaptchaService.verifyAnswer(session, request.getRemoteAddr(), params)) {
			render status: FORBIDDEN
			return
		}

		recaptchaService.cleanUp(session)
*/
		Suggestion suggestion = new Suggestion(
				title: params.sugTitle,
				content: params.sugContent,
				author: user.profile,
				category: params?.sugCategory ?: null
		)
	suggestion.validate()
		if (suggestion.hasErrors()){
			suggestion.errors.each {
				log.debug(it)
			}
		}
		suggestion.save flush: true
	}

	@Secured(["hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER') and isFullyAuthenticated()"])
	@Transactional
	def delete() {
		if (params?.checkSuggestion == null) {
			return
		}
		User user = springSecurityService.getCurrentUser()
		if (user && user.role.authority in ['ROLE_ADMIN', 'ROLE_MANAGER']) {
			def sugList = params.list('checkSuggestion')
			log.debug('suggestionController.suggestionList: ' + sugList)
			sugList.each {
				Suggestion sug = Suggestion.findById(it)
				log.debug('suggestionController.delete: : ' + sug)
				sug.delete(flush: true)
			}
			params.remove('checkSuggestion')
			params.remove('_checkSuggestion')
		} else {
			render status: FORBIDDEN
			return
		}
		redirect( base: uri,controller: 'suggestion', action: 'index', params: params)
	}
}
