package ru.deloved

/**
 * Created by artwolf on 25.09.2015.
 */

class ActivateTagLib {
	def springSecurityService

	static defaultEncodeAs = 'html'


	def getActivationMail = {

		User user = springSecurityService.getCurrentUser()

		if (user && user.role.authority == 'ROLE_NONE'){
			def cc = ContactConfirm.findByStatusInListAndProfileAndTypeAndConfirmType([ConfirmStatus.New.value(), ConfirmStatus.Process.value()],
					user.profile,
					ContactType.Email.value(),
					ConfirmType.ContactUpdate.value(),
					[max: 1, sort: 'dateCreated', order: 'desc']
			)
			out << cc.contact
		} else {
			out << null
		}
	}

}
