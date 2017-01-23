package ru.deloved

import grails.plugin.mail.MailMessageContentRenderer
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import org.joda.time.DateTime
import org.springframework.context.i18n.LocaleContextHolder as LCH

@Transactional
class ContactService {
	def userService
	def mailService
	def messageSource

	public void clearingExpiredContactConfirm() {
		//"Закрываем" подтиверждения с большим сроком давности, чтобы не маячили.
		def expiredDate = DateTime.now().minusDays(7).toDate()
		def expiredStatuses = [ConfirmStatus.New.value(), ConfirmStatus.Process.value()]

		int maxToDel = 10
		def toDel = ContactConfirm.executeQuery("""
			select cc from ContactConfirm cc
			where cc.status in (:expiredStatuses)
			and cc.lastUpdated < :expiredDate
		""", [expiredStatuses: expiredStatuses, expiredDate: expiredDate], [max: maxToDel])
		log.debug('contactService.clearingExpiredConfirm.toDel: ' + toDel)
		if (toDel) {
			toDel.each {
				it.lock()
				it.status = ConfirmStatus.Failed.value()
				it.save(flush: true)
			}
		}
	}

	private void updateRole(Profile profile) {
		User user = profile.user
		if (user.roleRequest && user.role.authority == 'ROLE_NONE') {
			user.role = user.roleRequest
			user.save(flush: true)
		}
	}

	private boolean updateEmail(Profile profile, String email) {
		if (profile.email != email) {
			profile.email = email
			if (profile.save(flush: true)) {
				updateRole(profile)
				return true
			} else {
				return false
			}
		}
		return true
	}

	private boolean updatePassword(Profile profile, String password) {
		User user = profile.user
		user.password = password
		if (user.save(flush: true)) {
			return true
		} else {
			return false
		}
	}

	private boolean updateCellPhone(Profile profile, String cell) {
		if (profile.cellPhone != cell) {
			profile.cellPhone = cell
			if (profile.save(flush: true)) {
				updateRole(profile)
				return true
			} else {
				return false
			}
		}
		return true
	}

	private void createEmailConfirm(ConfirmType ct, Profile profile, String email) {
		def cc = new ContactConfirm(
				profile: profile,
				contact: email,
				type: ContactType.Email.value(),
				confirmType: ct.value(),
				secret: org.apache.commons.lang.RandomStringUtils.random(32, true, true)
		)
		cc.save(flush: true)
	}

	private void createCellConfirm(ConfirmType ct, Profile profile, String cell) {
		def cc = new ContactConfirm(
				profile: profile,
				contact: cell,
				type: ContactType.CellPhoneNumber.value(),
				confirmType: ct.value(),
				secret: org.apache.commons.lang.RandomStringUtils.random(6, false, true)
		)
		cc.save(flush: true)
	}

	def addEmailContact(ConfirmType ct, Profile profile, String email) {
		ContactConfirm exist = ContactConfirm.findByStatusInListAndProfileAndTypeAndConfirmTypeAndContact([ConfirmStatus.New.value(), ConfirmStatus.Process.value(), ConfirmStatus.Confirmed.value()], profile, ContactType.Email.value(), ct.value(), email)
		if (exist) {
			switch (exist.status()) {
				case ConfirmStatus.New:
					// РЅРµС‡РµРіРѕ РґРµР»Р°С‚СЊ - Р¶РґРµРј РєРѕРіРґР° РїРµСЂРµР№РґРµС‚ РІ Process
					break
				case ConfirmStatus.Process:

					// СЃСѓС‰РµСЃС‚РІСѓСЋС‰РёР№ С„СЌР№Р»РёРј
					exist.status = ConfirmStatus.Failed.value();
					exist.save(flush: true)

					// СЃРѕР·РґР°РµРј РЅРѕРІС‹Р№
					createEmailConfirm(ct, profile, email)

					break
				case ConfirmStatus.Confirmed:
					switch (exist.confirmType()) {
						case ConfirmType.ContactUpdate:
							updateEmail(profile, exist.contact)
							break
						case ConfirmType.PasswordRecover:
							// СЃСѓС‰РµСЃС‚РІСѓСЋС‰РёР№ С„СЌР№Р»РёРј
							exist.status = ConfirmStatus.Failed.value();
							exist.save(flush: true)

							// СЃРѕР·РґР°РµРј РЅРѕРІС‹Р№
							createEmailConfirm(ct, profile, email)
							break
					}
					break
				case ConfirmStatus.Failed:
					// РЅРµ РґРѕР»Р¶РЅРѕ СЃР»СѓС‡РёС‚СЊСЃСЏ
					break
			}
		} else {
			createEmailConfirm(ct, profile, email)
		}
	}

	def addCellContact(ConfirmType ct, Profile profile, String cell) {
		ContactConfirm exist = ContactConfirm.findByTypeInListAndProfileAndTypeAndConfirmTypeAndContact([ConfirmStatus.New.value(), ConfirmStatus.Process.value(), ConfirmStatus.Confirmed.value()], profile, ContactType.CellPhoneNumber.value(), ct.value(), cell)
		if (exist) {
			switch (exist.status()) {
				case ConfirmStatus.New:
					// РЅРµС‡РµРіРѕ РґРµР»Р°С‚СЊ - Р¶РґРµРј РєРѕРіРґР° РїРµСЂРµР№РґРµС‚ РІ Process
					break
				case ConfirmStatus.Process:

					// СЃСѓС‰РµСЃС‚РІСѓСЋС‰РёР№ С„СЌР№Р»РёРј
					exist.status = ConfirmStatus.Failed.value();
					exist.save(flush: true)

					// СЃРѕР·РґР°РµРј РЅРѕРІС‹Р№
					createCellConfirm(ct, profile, cell)

					break
				case ConfirmStatus.Confirmed:
					switch (exist.confirmType()) {
						case ConfirmType.ContactUpdate:
							updateCellPhone(profile, exist.contact)
							break
						case ConfirmType.PasswordRecover:

							// СЃСѓС‰РµСЃС‚РІСѓСЋС‰РёР№ С„СЌР№Р»РёРј
							exist.status = ConfirmStatus.Failed.value();
							exist.save(flush: true)

							// СЃРѕР·РґР°РµРј РЅРѕРІС‹Р№
							createCellConfirm(ct, profile, cell)

							break
					}
					break
				case ConfirmStatus.Failed:
					// РЅРµ РґРѕР»Р¶РЅРѕ СЃР»СѓС‡РёС‚СЊСЃСЏ
					break
			}
		} else {
			createCellConfirm(ct, profile, cell)
		}
	}


	ContactConfirm confirmEmail(String code) {
		ContactConfirm exist = ContactConfirm.findByStatusAndTypeAndConfirmTypeAndSecret(ConfirmStatus.Process.value(), ContactType.Email.value(), ConfirmType.ContactUpdate.value(), code)
		if (exist) {
			Profile profile = exist.profile
			if (updateEmail(profile, exist.contact)) {
				exist.status = ConfirmStatus.Confirmed.value()
				if (exist.save(flush: true)) {
					return exist
				}
			}
		}
		return null
	}

	ContactConfirm confirmEmailAndChangePassword(String code, String password) {
		ContactConfirm exist = ContactConfirm.findByStatusAndTypeAndConfirmTypeAndSecret(ConfirmStatus.Process.value(), ContactType.Email.value(), ConfirmType.PasswordRecover.value(), code)
		if (exist) {
			Profile profile = exist.profile
			if (updatePassword(profile, password)) {
				exist.status = ConfirmStatus.Confirmed.value()
				if (exist.save(flush: true)) {
					return exist
				}
			}
		}
		return null
	}

	ContactConfirm confirmCellPhone(Profile profile, String code) {
		ContactConfirm exist = ContactConfirm.findByProfileAndStatusAndTypeAndConfirmTypeAndSecret(profile, ConfirmStatus.Process.value(), ContactType.CellPhoneNumber.value(), ConfirmType.ContactUpdate.value(), code)
		if (exist) {
			if (updateCellPhone(profile, exist.contact)) {
				exist.status = ConfirmStatus.Confirmed.value()
				if (exist.save(flush: true)) {
					return exist
				}
			}
		}
		return null
	}

	void processContactConfirm(ContactConfirm contactConfirm) {

		boolean success = false
		try {
			switch (contactConfirm.type()) {
				case ContactType.Email:
					def baseURL = 'deloved.ru';

					switch (contactConfirm.confirmType()) {

						case ConfirmType.ContactUpdate:

							def subj = messageSource.getMessage('contact.email.confirmation.subject', [].toArray(), 'Email confirmation', LCH.getLocale())
							def mail = mailService.sendMail {
								to contactConfirm.contact
								subject subj
								html view: '/signup/mailConfirm', model: [contactConfirm: contactConfirm, baseURL: baseURL]
							}
							log.debug("mail:" + mail)
							success = true

							break
						case ConfirmType.PasswordRecover:
							def subj = messageSource.getMessage('contact.email.password.subject', [].toArray(), 'Password recover request', LCH.getLocale())
							def mail = mailService.sendMail {
								to contactConfirm.contact
								subject subj
								html view: '/signup/mailRecover', model: [contactConfirm: contactConfirm, baseURL: baseURL]
							}
							log.debug("mail:" + mail)
							success = true
							break
					}
					break
				case ContactType.CellPhoneNumber:
					break
			}
		} catch (Exception e) {
			log.error("Exception:" + e.getMessage(), e)
		}
		if (success) {
			contactConfirm.status = ConfirmStatus.Process.value()
			contactConfirm.save(flush: true)
		} else {
			contactConfirm.status = ConfirmStatus.Failed.value()
			contactConfirm.save(flush: true)
		}

	}


/*

	private void createEmailConfirm(ConfirmType ct, Profile profile, String email) {
		def cc = new ContactConfirm(
				profile: profile,
				contact: email,
				type: ContactType.Email.value(),
				confirmType: ct.value(),
				secret: org.apache.commons.lang.RandomStringUtils.random(32, true, true)
		)
		cc.save(flush: true)
	}



	private void createCellConfirm(ConfirmType ct, Profile profile, String cell) {
		def cc = new ContactConfirm(
				profile: profile,
				contact: cell,
				type: ContactType.CellPhoneNumber.value(),
				confirmType: ct.value(),
				secret: org.apache.commons.lang.RandomStringUtils.random(6, false, true)
		)
		cc.save(flush: true)
	}


	def addEmailContact(ConfirmType ct, Profile profile, String email) {
		ContactConfirm exist = ContactConfirm.findByStatusInListAndProfileAndTypeAndConfirmTypeAndContact([ConfirmStatus.New.value(), ConfirmStatus.Process.value(), ConfirmStatus.Confirmed.value()], profile, ContactType.Email.value(), ct.value(), email)
		if (exist) {
			switch (exist.status()) {
				case ConfirmStatus.New:

					break
				case ConfirmStatus.Process:


					exist.status = ConfirmStatus.Failed.value();
					exist.save(flush: true)


					createEmailConfirm(ct, profile, email)

					break
				case ConfirmStatus.Confirmed:
					switch (exist.confirmType()) {
						case ConfirmType.ContactUpdate:
							updateEmail(profile, exist.contact)
							break
						case ConfirmType.PasswordRecover:

							exist.status = ConfirmStatus.Failed.value();
							exist.save(flush: true)

							createEmailConfirm(ct, profile, email)
							break
					}
					break
				case ConfirmStatus.Failed:

					break
			}
		} else {
			createEmailConfirm(ct, profile, email)
		}
	}



	def addCellContact(ConfirmType ct, Profile profile, String cell) {
		ContactConfirm exist = ContactConfirm.findByTypeInListAndProfileAndTypeAndConfirmTypeAndContact([ConfirmStatus.New.value(), ConfirmStatus.Process.value(), ConfirmStatus.Confirmed.value()], profile, ContactType.CellPhoneNumber.value(), ct.value(), cell)
		if (exist) {
			switch (exist.status()) {
				case ConfirmStatus.New:
					// РЅРµС‡РµРіРѕ РґРµР»Р°С‚СЊ - Р¶РґРµРј РєРѕРіРґР° РїРµСЂРµР№РґРµС‚ РІ Process
					break
				case ConfirmStatus.Process:

					// СЃСѓС‰РµСЃС‚РІСѓСЋС‰РёР№ С„СЌР№Р»РёРј
					exist.status = ConfirmStatus.Failed.value();
					exist.save(flush: true)

					// СЃРѕР·РґР°РµРј РЅРѕРІС‹Р№
					createCellConfirm(ct, profile, cell)

					break
				case ConfirmStatus.Confirmed:
					switch (exist.confirmType()) {
						case ConfirmType.ContactUpdate:
							updateCellPhone(profile, exist.contact)
							break
						case ConfirmType.PasswordRecover:

							// СЃСѓС‰РµСЃС‚РІСѓСЋС‰РёР№ С„СЌР№Р»РёРј
							exist.status = ConfirmStatus.Failed.value();
							exist.save(flush: true)

							// СЃРѕР·РґР°РµРј РЅРѕРІС‹Р№
							createCellConfirm(ct, profile, cell)

							break
					}
					break
				case ConfirmStatus.Failed:
					// РЅРµ РґРѕР»Р¶РЅРѕ СЃР»СѓС‡РёС‚СЊСЃСЏ
					break
			}
		} else {
			createCellConfirm(ct, profile, cell)
		}
	}


	ContactConfirm confirmEmail(String code) {
		ContactConfirm exist = ContactConfirm.findByStatusAndTypeAndSecret(ConfirmStatus.Process.value(), ContactType.Email.value(), code)
		if (exist) {
			Profile profile = exist.profile
			if (updateEmail(profile, exist.contact)) {
				exist.status = ConfirmStatus.Confirmed.value()
				if (exist.save(flush: true)) {
					return exist
				}
			}
		}
		return null
	}

	ContactConfirm confirmCellPhone(Profile profile, String code) {
		ContactConfirm exist = ContactConfirm.findByProfileAndStatusAndTypeAndSecret(profile, ConfirmStatus.Process.value(), ContactType.CellPhoneNumber.value(), code)
		if (exist) {
			if (updateCellPhone(profile, exist.contact)) {
				exist.status = ConfirmStatus.Confirmed.value()
				if (exist.save(flush: true)) {
					return exist
				}
			}
		}
		return null
	}

	void processContactConfirm(ContactConfirm contactConfirm) {

		boolean success = false
		try {
			switch (contactConfirm.type()) {
				case ContactType.Email:
					def baseURL = new DefaultGrailsApplication().config.mailBaseURL ?: '';

					switch (contactConfirm.confirmType()) {

						case ConfirmType.ContactUpdate:

							def subj = messageSource.getMessage('contact.email.confirmation.subject', [].toArray(), 'Email confirmation', LCH.getLocale())
							def mail = mailService.sendMail {
								to contactConfirm.contact
								subject subj
								html view: '/signup/mailConfirm', model: [contactConfirm: contactConfirm, baseURL: baseURL]
							}
							log.debug("mail:" + mail)
							success = true

							break
						case ConfirmType.PasswordRecover:
							def subj = messageSource.getMessage('contact.email.password.subject', [].toArray(), 'Password recover request', LCH.getLocale())
							def mail = mailService.sendMail {
								to contactConfirm.contact
								subject subj
								html view: '/signup/mailRecover', model: [contactConfirm: contactConfirm, baseURL: baseURL]
							}
							log.debug("mail:" + mail)
							success = true
							break
					}
					break
				case ContactType.CellPhoneNumber:
					break
			}
		} catch (Exception e) {
			log.error("Exception:" + e.getMessage(), e)
		}
		if (success) {
			contactConfirm.status = ConfirmStatus.Process.value()
			contactConfirm.save(flush: true)
		} else {
			contactConfirm.status = ConfirmStatus.Failed.value()
			contactConfirm.save(flush: true)
		}

	}





	ContactConfirm confirmEmailAndChangePassword(String code, String password) {
		ContactConfirm exist = ContactConfirm.findByStatusAndTypeAndConfirmTypeAndSecret(ConfirmStatus.Process.value(), ContactType.Email.value(), ConfirmType.PasswordRecover.value(), code)
		if (exist) {
			Profile profile = exist.profile
			if (updatePassword(profile, password)) {
				exist.status = ConfirmStatus.Confirmed.value()
				if (exist.save(flush: true)) {
					return exist
				}
			}
		}
		return null
	}
	*/

}
