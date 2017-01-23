package ru.deloved

import grails.transaction.Transactional

//Created by artwolf (iartwolf@gmail.com) on 05.10.2015.


class ClearingExpiredContactConfirmJob {
	def contactService
	def concurrent = false
	static triggers = {
		simple repeatInterval: 86400001, // execute job once in 1 day 24*60*60+1000
				startDelay: 700000
	}

	@Transactional
	def execute() {
		contactService.clearingExpiredContactConfirm()
	}

}
