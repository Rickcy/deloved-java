package ru.deloved

import grails.transaction.Transactional


class UserService {

	def springSecurityService



	def renameAccountManager(Profile profile){
		Account account = AccountProfile.findByProfile(profile)?.account
		if (account) {
			if (profile.fio == account.manager) {
				return
			} else {
			account.lock()
			account.manager = profile?.fio
			account.save(flush: true)
			return
			}
		} else {
			return
		}
	}


	/* 07.07.2015
	Сделал присвоение ФИО Деректора к ФИО Профиля
	*/
	@Transactional
	User createUser(String manager, String username, String password, Role role, Role roleRequest, Region city) {
		User user = new User(
				username: username,
				password: password,
				enabled: false,
				profile: new Profile(fio: manager, city: city),
				role: role,
				roleRequest: roleRequest
		)
		log.debug("Saving")
		user.save(flush: true)
		return user
	}

	@Transactional
	User createUser(String username, String password, Role role, Role roleRequest, Region city) {
		User user = new User(
				username: username,
				password: password,
				enabled: false,
				profile: new Profile(city: city),
				role: role,
				roleRequest: roleRequest
		)
		log.debug("Saving")
		user.save(flush: true)
		return user
	}

	boolean isAccountOrSpecialist(User user) {
		log.debug("user:" + user);
		return user?.role?.authority in ['ROLE_ACCOUNT', 'ROLE_MEDIATOR', 'ROLE_JUDGE', 'ROLE_JURIST'];
	}

	boolean isUsernameAvailable(String username) {
		log.debug "Check for new user '$username'"
		return User.countByUsername(username) == 0
	}

	boolean isUsernameAvailable(String username, int id) {
		log.debug "Check for new username '$username' with id=$id"
		return User.countByUsernameAndIdNotEqual(username, id) == 0
	}

	String encodePassword(String password) {
		springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

}
