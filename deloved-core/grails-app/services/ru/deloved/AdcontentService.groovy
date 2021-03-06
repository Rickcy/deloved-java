package ru.deloved

import grails.transaction.Transactional

@Transactional
class AdcontentService {
	def springSecurityService
	def profileService
	def accountService

	def isEditAllowed(Adcontent item) {
		User user = springSecurityService.getCurrentUser()
		log.debug("isEditAllowed for:" + user.role.authority)
		if (user.role.authority in ['ROLE_ADMIN']) {
			log.debug("Allowed!")
			return true
		} else if (user.role.authority in ['ROLE_MANAGER']) {
			// check regional access
			def list = []
			profileService.findAllCitiesByProfile(user.profile).each { list.add(it.id) }
			log.debug("check for:" + item.account.city.id)
			log.debug("region check list:" + list)
			if (item.account.city.id in list) {
				log.debug("Allowed!")
				return true
			}
		} else if (user.role.authority in ['ROLE_ACCOUNT']) {
			return accountService.isMyAccount(item.account)
		}
		log.debug("Not Allowed!")
		return false
	}
}
