package ru.deloved

/**
 * artwolf, 14/09/2015
 * Чистим файлы и записи в базе данных изображений, которые не были использованы при создании нового товара/услуги
 */

import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin
import org.joda.time.DateTime

class CleaningItemAttachJob {

	def attachService
	def sessionFactory

	def concurrent = false
	static triggers = {
		simple repeatInterval: 86400001, // execute job once in 1 day 24*60*60+1000
		       startDelay: 800000
	}

	@Transactional
	def execute() {

		def fileCategoryDirectory = new DefaultGrailsApplication().config?.fileCategory?.item ?: 'item'

		def list = ItemAttach.executeQuery("""
		select itemAtt from ItemAttach as itemAtt
		where itemAtt.item = null
		and itemAtt.dateCreated < :date
		""", [date: DateTime.now().minusHours(12).toDate()], [max: 10])

		if (list) {
			for(int i=0; i < list.size(); i++) {
				attachService.deleteImageAndThumbnail(fileCategoryDirectory, list[i].image, list[i].imageThumb) {
					list[i].delete(flush: true)
					return true
				}
			}
		}
	}
}
