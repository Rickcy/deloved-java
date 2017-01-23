import ru.deloved.User

class ChargeFilters {
	def accountService

	def filters = {
		adminModuleAccountChargeState(controller: '(account|adcontent|billing|panel|item|profile|deal|dispute|claim|review|consult|document)', action: '(index|me|show|thread|edit|create|create2)') {
			before = {
				if (!request.xhr) {
					def userName = applicationContext.getBean("springSecurityService").authentication.name
					User user = User.findByUsername(userName)
					if (user?.isEnabled()) {
						if (user.role.authority in ['ROLE_ACCOUNT']) {
							def charge = user.profile.chargeStatus > 0 && user.profile.chargeTill >= new Date()
							request.freeAccount = !charge
						}
					}
				}
				return true
			}
		}
		frontModuleAccountChargeState(controller: '(company)', action: '(index|reviews)') {
			before = {
				def userName = applicationContext.getBean("springSecurityService").authentication.name
				log.debug("springSecurityService user:" + userName)
				User user = User.findByUsername(userName)
				if (user) {
					if (user.role.authority in ['ROLE_ACCOUNT']) {
						def charge = user.profile.chargeStatus > 0 && user.profile.chargeTill >= new Date()
						request.freeAccount = !charge
					} else {
						request.freeAccount = false
					}
				} else {
					log.debug("not logged!")
					request.freeAccount = true
				}
				return true
			}
		}
	}
}
