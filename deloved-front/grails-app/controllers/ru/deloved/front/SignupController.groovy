package ru.deloved.front

import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import org.springframework.security.access.annotation.Secured
import org.springframework.validation.ObjectError
import ru.deloved.*

import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.OK

@Secured(["isAnonymous()"])
class SignupController {
	def userService
	def contactService
	def regionService
	def billingService
	def categoryService
	def accountService
	def recaptchaService
	def springSecurityService


	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"


	def index(){
		redirect(base: uri,controller: 'signup',action: 'create')
	}

	def create() {
		render view: 'create', model: [
				recaptchaNoError: Boolean.TRUE
		]
	}


	def cities(String term) {
		if (request.xhr && term) {
			def t = RegionType.findByCode('CITY')
			log.debug(t?.code)
			def l = RegionLevel.findByType(t)
			log.debug(l)
			def list = Region.findAllByNameIlikeAndLevel("%" + term + "%", l)
			def res = []
			list.each { it ->
				res << [label: it.parent.name + ", " + it.name, value: it.name, id: it.id]
			}
			render(status: OK, contentType: 'application/json') {
				res
			}
		} else {
			render status: 404, text: 'NOT_FOUND'
		}
	}

	void loadParentsCategory(ru.deloved.Category el, ArrayList parents) {
		if (el) {
			parents.add(el.id)
			loadParentsCategory(el.parent, parents)
		}
	}

	def cat(Long pid) {
		if (request.xhr && pid){
			if (pid == null) {
				render(status: BAD_REQUEST, contentType: 'application/json') {
					[error: 1]
				}
				return
			} else {
				def res = []
				def catParent = categoryService.getCategory(pid)
				def catIds = []
				def undetermCatIds = []

				def catList = session.getAttribute('regCatList')
				if (catList) {
					catList.each {
						def categ = categoryService.getCategory(it as Long)
						if (categ?.id) {
							catIds.add(categ.id)
							loadParentsCategory(categ, undetermCatIds)
						}
					}
				}

				def childsClos
				childsClos = { Category cat, List obj ->
					def list = categoryService.getChilds(cat)
					list.each {
						def el = [
								id  : it.id,
								text: it.name
						]
						def state = [:]
						if (categoryService.getChilds(it).size() > 0) {
							def children = []
							childsClos(it, children)
							el.putAt('children', children)
							state.putAt('opened', (it.id in undetermCatIds) && !(it.id in catIds))
						}
						state.putAt('selected', (it.id in catIds))
						if (!state.isEmpty()) {
							el.putAt('state', state)
						}
						obj << el
					}
					return
				}

				childsClos(catParent, res)
				render(status: OK, contentType: 'application/json') {
					res
				}
			}
		} else {
			render status: 404, text: 'NOT_FOUND'
		}
	}


	@Transactional
	def save(SignupForm formInstance) {
		if (formInstance == null) {
			flash.message = "Bad request"
			redirect(base: uri,controller: 'signup',action: 'create')
			return
		}

		if (params.cat != null) {
			def catList = []
			catList.addAll(params.cat)
			session.setAttribute('regCatList', catList)
		}

		formInstance.recaptcha = recaptchaService.verifyAnswer(session, request.getRemoteAddr(), params)

		formInstance.validate()

		accountService.deactivateAccount(formInstance.inn)

		if (formInstance.hasErrors()) {
			formInstance.errors.each { log.error('error:' + it) }
			log.debug("sign.recaptcha: " + formInstance.recaptcha)

			render view: 'create', model: [
					beanResource: formInstance,
					objInstance : formInstance.errors,
			]
			return
		}

		recaptchaService.cleanUp(session)

		Account account = new Account()
		bindData(account, formInstance, [include: ['name', 'fullName', 'brandName', 'orgForm', 'regNumber', 'inn', 'kpp', 'legalAddress', 'regDate', 'phone1', 'phone2', 'phone3', 'fax1', 'fax2', 'webAddress', 'email', 'description', 'manager', 'workTime', 'city', 'address', 'keywords']])
		account.validate()
		if (account.hasErrors()) {
			log.error('account error:' + account.errors)
			render view: 'create', model: [
					beanResource: formInstance,
					objInstance : account.errors
			]
			return
		} else {
			session.removeAttribute('regCatList')
		}
		/* 07.07.2015
		Сделал присвоение ФИО Деректора к ФИО Профиля
		 */
		User user = userService.createUser(formInstance.manager, formInstance.userEmail, formInstance.password, Role.findByAuthority('ROLE_NONE'), Role.findByAuthority('ROLE_ACCOUNT'), formInstance.userCity)
		log.debug "createUser:" + user
		if (user?.id) {
			user.enabled = true
			user.save(flush: true)


			def saved = account.save(flush: true)
			if (saved) {
				new AccountProfile(account: account, profile: user.profile).save(flush: true)
				if (params.cat != null) {
					ArrayList<Long> catList = new ArrayList<Long>()

					if (params.cat instanceof String) {
						catList.add(params.cat)
					} else {
						params.cat.each {
							catList.add(it as Long)
						}
					}

					catList.each {
						new AccountCategory(account: account, category: Category.load(it)).save(flush: true)
					}
				}
				//flash.message = message(code: 'signup.account.created.message')

				contactService.addEmailContact(ConfirmType.ContactUpdate, user.profile, user.username)

				springSecurityService.reauthenticate(user.username)

				redirect(base: uri,controller: 'signup',action: 'success')
				return
			} else {

				log.error('account error:' + account.errors)
				render view: 'create', model: [
						beanResource: formInstance,
						objInstance : account.errors
				]
				return
			}
		} else {
			flash.message = message(code: 'signup.user.notCreated.message')
			flash.status = 'warning'
			log.error('user error:' + user.errors)
		}

		redirect(base: uri,controller: 'front',action: 'index')

	}


	def success() {
		redirect(base: uri,controller: 'panel',action: 'index')
	}

	@Transactional
	@Secured(["hasAnyRole('ROLE_NONE') and isFullyAuthenticated()"])
	def activate(String code) {
		if (code) {
			def confirmation = contactService.confirmEmail(code)
			if (confirmation) {
				flash.message = 'Активация учетной записи успешно завершена'
				flash.status = 'success'
				springSecurityService.reauthenticate(confirmation.profile.user.username)
				redirect(base: uri,controller: 'panel',action: 'index')
			} else {
				flash.message = 'Сбой активации или неверный код'
				flash.status = 'danger'
				redirect(base: uri,controller: 'signup',action: 'activate')
			}
			return
		}
	}

	@Transactional
	@Secured(["hasAnyRole('ROLE_NONE') and isFullyAuthenticated()"])
	def resendActivateMail(FormResend formResend) {

		User user = springSecurityService.getCurrentUser()

		formResend.captcha = recaptchaService.verifyAnswer(session, request.getRemoteAddr(), params)
		formResend.validate()

		if (formResend.hasErrors()) {
			formResend.errors.each {
				log.error('error:' + it)
			}
			render(view: 'resend', model: [beanResource: formResend, objInstance : formResend.errors])
		} else {
			recaptchaService.cleanUp(session)
			contactService.addEmailContact(ConfirmType.ContactUpdate, user.profile, formResend.email)
			flash.message = 'Запрос успешно обработан.'
			flash.status = 'success'
			redirect(base: uri,controller: 'panel',action: 'index')
		}
		return
	}


	@Secured(["hasAnyRole('ROLE_NONE') and isFullyAuthenticated()"])
	def resend(){
		User user = springSecurityService.getCurrentUser()

		def cc = ContactConfirm.findByStatusInListAndProfileAndTypeAndConfirmType([ConfirmStatus.New.value(), ConfirmStatus.Process.value()],
				user.profile,
				ContactType.Email.value(),
				ConfirmType.ContactUpdate.value(),
				[max: 1, sort: 'dateCreated', order: 'desc'])
		if (cc) {
			def beanResource = new FormResend(email: cc?.contact, captcha: false)
			render view: 'resend', model: [beanResource: beanResource]
		} else {
			render status: 404
		}
		return
	}

}

class SignupForm {
	def userService
	def accountService

	String userEmail
	String password
	String passwordRepeat
	Region userCity

	String name
	String fullName
	String brandName
	OrgForm orgForm
	String regNumber
	String inn
	String kpp
	String legalAddress
	Date regDate
	String phone1
	String phone2
	String phone3
	String fax1
	String fax2
	String webAddress
	String email
	String description
	String manager
	String workTime
	Region city
	String address
	String keywords
	Boolean recaptcha = false
	Boolean agreement = false


	static constraints = {
		userEmail blank: false,
				email: true,
				validator: {val, form ->	return form.userService.isUsernameAvailable(val)}

		password blank: false, minSize: 8
		passwordRepeat blank: false,
				validator: { passwd2, form -> return passwd2 == form.password }

		userCity nullable: false

		name blank: false
		fullName blank: false
		brandName blank: false
		orgForm nullable: false
		regNumber blank: false, minSize: 13, maxSize: 15, matches: '(\\d{13}|\\d{15})'
		inn blank: false, unique: true, minSize: 10, maxSize: 12, matches: '(\\d{10}|\\d{12})',
				validator: { val, obj ->
					return obj.accountService.isInnAvailable(val)
				}


		kpp nullable: true, blank: true, minSize: 9, maxSize: 9, matches: '\\d{9}'
		legalAddress blank: false
		regDate nullable: false
		phone1 blank: false
		phone2 nullable: true
		phone3 nullable: true
		fax1 nullable: true
		fax2 nullable: true
		webAddress nullable: true
		email nullable: true
		description nullable: true, maxSize: 1500
		manager blank: false
		workTime blank: false
		city nullable: false
		address nullable: true
		keywords nullable: true, maxSize: 300
		recaptcha nullable: false, validator: {val, obj -> return val}
		agreement nullable: false, validator: {val, obj -> return val}
	}
}

class FormResend {
	String email
	Boolean captcha = false

	static constraints = {
		email nullable: false, email: true
		captcha nullable: false, validator: {val, obj -> return val}
	}
}