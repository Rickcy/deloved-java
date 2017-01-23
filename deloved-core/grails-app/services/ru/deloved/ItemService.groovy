package ru.deloved

import grails.transaction.Transactional

@Transactional
class ItemService {
	def springSecurityService
	def profileService
	def accountService

	def isEditAllowed(Item item) {
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

	def isUploadAllowed() {
		User user = springSecurityService.getCurrentUser()
		log.debug("isUploadAllowed for:" + user.role.authority)
		if (user.role.authority in ['ROLE_ADMIN','ROLE_MANAGER','ROLE_ACCOUNT']) {
			log.debug("Allowed!")
			return true
		}
		log.debug("Not Allowed!")
		return false
	}

}
