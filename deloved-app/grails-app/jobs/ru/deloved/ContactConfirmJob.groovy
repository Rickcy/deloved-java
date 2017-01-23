package ru.deloved

import org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin

class ContactConfirmJob {
	def contactService
	def sessionFactory
	def concurrent = false
	static triggers = {
		simple repeatInterval: 50000l, // execute job once in 60 seconds
				startDelay: 600000
	}

	def execute() {
		def list = ContactConfirm.findAllByStatus(ConfirmStatus.New.value())
		list.each {
			ContactConfirm contactConfirm = it
			log.debug("contactConfirm:" + contactConfirm)
			contactService.processContactConfirm(contactConfirm)
		}
	}
}
