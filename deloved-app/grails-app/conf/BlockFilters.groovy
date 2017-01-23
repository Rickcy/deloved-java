import ru.deloved.User

class BlockFilters {

	def filters = {

//		banFilter(controller: '(login|logout)', action: '*', invert: true) {
//			before = {
//				User user = User.findByUsername(applicationContext.getBean("springSecurityService").authentication.name)
//				if (user && !user.isEnabled()) {
//					session.invalidate()
//					redirect(controller: "login")
//					return false
//				}
//			}
//		}
	}
}
