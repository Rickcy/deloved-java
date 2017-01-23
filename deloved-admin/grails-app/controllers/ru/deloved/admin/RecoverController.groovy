package ru.deloved.admin

import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import ru.deloved.AccountProfile
import ru.deloved.ConfirmStatus
import ru.deloved.ConfirmType
import ru.deloved.ContactConfirm
import ru.deloved.ContactType
import ru.deloved.Profile
import ru.deloved.User

class RecoverController {
	def contactService
	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"
	def index() {}

	/*
	Попытка найти профиль по адресу электронной почты.
	Сначало по значению почты в профиле. Если профилей больше двух то высылаем на последний подтвержденный.
	Решение временное, в идеале надо сделать выбор предприятия, которое нуждается в восстановлении доступа.
	Если по профилям пользователей ничего не находим, то пытаемся найти пользователя с таким же логином.
	Есть хотя бы один профиль найден, то высылаем письмо с подтверждением.
	 */

	def send(String email) {

		log.debug("Password recover request for $email")

		def profiles = Profile.findAllByEmail(email)
		def profile = null

		if (profiles.size() == 1) {

			profile = profiles.get(0)

		} else if (profiles.size() > 1) {

			def accountProfiles = []

			profiles.each {
				def accountProfile = AccountProfile.findByProfile(it)
				if (accountProfile && accountProfile.account) {
					accountProfiles.add(accountProfile)
				}
			}

			log.debug('chose send recover email: ' + accountProfiles)

			render template: 'selectAccount', model: [accountProfiles: accountProfiles, email: email]
			return

		} else if (profiles.isEmpty()) {
				User user = User.findByUsername(email)
			if (user) {
				profile = user.profile
			}
		}

		if (profile) {
			contactService.addEmailContact(ConfirmType.PasswordRecover, profile, email)
			log.debug('send recover mail to ' + email)
			//redirect(controller: 'login', action: 'auth')
			//flash.message = 'На почтовый ящик <b>' + email + '</b> высланы инструкции по восстановлению доступа к учетной записи'
			//flash.status = 'success'
			render template: 'recoverResult', model: [recoverResult: 'На почтовый ящик ' + email + ' высланы инструкции по восстановлению доступа к учетной записи']
		} else {
			render status: 404
		}
	}

	def selectAccount(){

		if (!request.xhr) {
			render status: 404
			return
		}

		log.debug(params)
		contactService.addEmailContact(ConfirmType.PasswordRecover, Profile.get(params.profile), params.email)
		render template: 'recoverResult', model: [recoverResult: 'На почтовый ящик ' + params.email + ' высланы инструкции по восстановлению доступа к учетной записи']
		//redirect(controller: 'login', action: 'auth')
		//flash.message = 'На почтовый ящик <b>' + params.email + '</b> высланы инструкции по восстановлению доступа к учетной записи'
		//flash.status = 'success'
	}


	def change() {
		render(view: 'change', model: [
				code       : params.code,
				objInstance: new RecoverCommand()
		])
	}

	def update(String code, RecoverCommand recoverCmd) {

		recoverCmd.validate()
		if (recoverCmd.hasErrors()) {
			log.error(recoverCmd.errors)
			render(view: 'change', model: [
					code       : code,
					objInstance: recoverCmd.errors
			])
			return
		}
		if (code) {
			def confirmation = contactService.confirmEmailAndChangePassword(code, recoverCmd.newPassword)
			if (confirmation) {
				flash.message = "Пароль успешно обновлен"
				flash.status = 'success'
			} else {
				flash.message = "Неверный проверочный код"
				flash.status = 'danger'
			}
		} else {
			flash.message = "Неверный проверочный код"
			flash.status = 'danger'
		}
		redirect(base: uri,action: 'auth', controller: 'login')
	}
}


class RecoverCommand {
	String newPassword
	String newPasswordRepeat

	static constraints = {
		newPassword(blank: false)
		newPasswordRepeat(blank: false,
				validator: { val, obj ->
					return obj.newPassword == val
				}
		)
	}

	@Override
	public String toString() {
		return "PasswordEditCommand{" +
				"newPassword='" + newPassword + '\'' +
				", newPasswordRepeat='" + newPasswordRepeat + '\'' +
				'}';
	}

}