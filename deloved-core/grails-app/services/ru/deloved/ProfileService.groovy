package ru.deloved

import grails.transaction.Transactional

@Transactional
class ProfileService {
	def springSecurityService

	def checkForCharge(Profile profile) {

		 if (profile.chargeStatus > 0 && profile.chargeTill >= new Date()) {
			 return true
		 }

		 return false

	 }

	def findAllCitiesByProfile(Profile profile) {
		def list = []
		ProfileRegion.findAllByProfile(profile).each {
			if (it.region.level.type.code == 'CITY') {
				list.add(it.region)
			} else {
				list.addAll(Region.findAllByParent(it.region))
			}
		}
		return list
	}

	def isEditAllowed(Profile profile) {
		User user = springSecurityService.getCurrentUser()
		log.debug("isEditAllowed for:" + user.role.authority)
		if (user.profile == profile) {
			log.debug("Allowed!")
			return true;
		} else if (user.role.authority in ['ROLE_ADMIN']) {
			log.debug("Allowed!")
			return true
		} else if (user.role.authority in ['ROLE_MANAGER']) {
			// check regional access
			def list = []
			findAllCitiesByProfile(user.profile).each { list.add(it.id) }
			log.debug("check for:" + profile.city.id)
			log.debug("region check list:" + list)
			if (profile.city.id in list) {
				log.debug("Allowed!")
				return true
			}
		}
		log.debug("Not Allowed!")
		return false
	}
}
