package ru.deloved

class UserInfoTagLib {
	def springSecurityService
	static defaultEncodeAs = 'html'
	//static encodeAsForTags = [tagName: 'raw']

	/**
	 * Отображает свойство текущего пользователя/профиля
	 *
	 * @attr what REQUIRED имя свойства
	 */
	def userInfo = { attrs, body ->
		User user = springSecurityService.getCurrentUser()
		if (user) {
			if (user.hasProperty(attrs.what) && attrs.what != 'password') {
				out << user.getProperty(attrs.what)
			} else if (user.profile.hasProperty(attrs.what)) {
				out << user.profile.getProperty(attrs.what)
			}
		}
	}


	def getAvatar = {
		User user = springSecurityService.getCurrentUser()
		if (user?.profile?.avatarThumb?.file) {
			out << createLink(controller: 'profile', action: 'avatar', id: user.profile.id, params: [name: user.profile.avatarThumb.file])
		}
	}
}
