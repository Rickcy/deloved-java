package ru.deloved.front

import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import ru.deloved.Attachment

import static org.springframework.http.HttpStatus.NO_CONTENT

class FilesController {

	def attachService
	def contentCategoryDirectory = new DefaultGrailsApplication().config?.fileCategory?.adcontent ?: 'adcontent'

	def logoCategoryDirectory = new DefaultGrailsApplication().config?.fileCategory?.logo ?: 'logo'
	def itemCategoryDirectory = new DefaultGrailsApplication().config?.fileCategory?.item ?: 'item'
	def avatarCategoryDirectory = new DefaultGrailsApplication().config?.fileCategory?.avatar ?: 'avatar'

	def logo(Attachment att) {
		log.debug(att)
		if (att && att.file == params.name) {
			attachService.sendFile(logoCategoryDirectory, att, response)
			return null
		}
		render status: NO_CONTENT
		return null
	}
	def avatar(Attachment att){

		log.debug(att)
		if (att && att.file == params.name) {
			attachService.sendFile(avatarCategoryDirectory, att, response)
			return null
		}
		render status: NO_CONTENT
		return null
	}

	def item(Attachment att) {
		log.debug("params:"+params)
		log.debug(att)
		if (att && att.file == params.name) {
			attachService.sendFile(itemCategoryDirectory, att, response)
			return null
		}
		render status: NO_CONTENT
		return null
	}
	def content(Attachment att) {
		log.debug("params:"+params)
		log.debug(att)
		if (att && att.file == params.name) {
			attachService.sendFile(contentCategoryDirectory, att, response)
			return null
		}
		render status: NO_CONTENT
		return null
	}
}
