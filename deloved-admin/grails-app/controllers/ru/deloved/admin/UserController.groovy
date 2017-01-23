package ru.deloved.admin

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.transform.EqualsAndHashCode
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import org.hibernate.criterion.CriteriaSpecification
import ru.deloved.*
import ru.deloved.billing.Keeper

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER') and isFullyAuthenticated()"])
class UserController extends FilteredController {

	def userService
	def profileService

	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"

	@Override
	protected FormFilter createFilterInstance() {
		return new UserFilter()
	}

	@Override
	protected PagedResultWrapper getRows(FormFilter filter) {
		UserFilter f = filter
		User user = springSecurityService.getCurrentUser()
		def regFilter = []
		if (user.role.authority == 'ROLE_MANAGER') {
			regFilter = profileService.findAllCitiesByProfile(user.profile)
		}
		def rows = User.createCriteria().list(max: filter.max, offset: filter.offset) {
			createAlias('role', 'r')
			createAlias('profile', 'p')
			if (f.enabled != null) {
				eq('enabled', f.enabled)
			}
			if (f.role) {
				eq('role', Role.get(f.role))
			}
			if (f.username) {
				ilike("username", "%" + f.username + "%")
			}
			if (user.role.authority == 'ROLE_MANAGER') {
				inList("r.authority", ['ROLE_ACCOUNT', 'ROLE_MEDIATOR', 'ROLE_JUDGE', 'ROLE_JURIST'])
				inList("p.city", regFilter)
			}
			order(filter.sort ?: "id", filter.order ?: "asc")
		}
		new PagedResultWrapper(rows)
	}

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(UserFilter userFilter) {
		processIndex(10, userFilter)
	}

	def show(User userInstance) {
		if (userInstance?.id == null) {
			notFound()
			return
		}
		respond userInstance
	}

	def create() {
	}

	protected redirectEdit(User beanResource, objInstance = null) {
		render view: request.xhr ? 'editform' : 'edit',
				model: [
						beanResource: beanResource,
						objInstance : objInstance ?: beanResource,
				]
	}

	def edit(User userInstance) {
		log.debug("edit:" + userInstance);
		if (userInstance?.id == null) {
			notFound()
			return
		}

		UserEditCommand userEditCommandInstance = new UserEditCommand()
		bindData(userEditCommandInstance, userInstance, [include: ['username', 'enabled']])
		redirectEdit(userInstance, userEditCommandInstance)
	}

	@Transactional
	def status(User userInstance) {
		log.debug("status:" + userInstance);
		if (userInstance?.id == null) {
			notFound()
			return
		}

		userInstance.enabled = !userInstance.enabled
		log.debug("status new:" + userInstance);
		if (userInstance.save(flush: true)) {
			respond userInstance, view: '_status', model: [status: userInstance.enabled]
		} else {
			render(status: 500, text: '')
		}
	}

	@Transactional
	def save(UserCreateCommand userInstance) {
		if (userInstance == null) {
			notFound()
			return
		}

		if (userInstance.hasErrors()) {
			userInstance.errors.each { log.error(it) }
			respond userInstance.errors, view: 'create'
			return
		}

		def user = userService.createUser(
				userInstance.username,
				userInstance.password,
				Role.findById(userInstance.role),
				userInstance.roleRequest ? Role.findById(userInstance.roleRequest) : null,
				userInstance.city)


		if(user.hasErrors()){
			user.errors.each { log.error(it) }
		}
		log.debug "createUser:" + user

		request.withFormat {
			form {
				flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.username])
				redirect base: uri,controller: 'user', action: 'index'
			}
			'*' { respond user, [status: CREATED] }
		}
	}

	@Transactional
	def update(UserEditCommand userEditCommandInstance) {
		log.debug("update:" + userEditCommandInstance);
		log.debug("params:" + params)
		User user = User.get(params.id)
		if (user == null) {
			notFound()
			return
		}

		if (userEditCommandInstance.hasErrors()) {
			log.error("has errors:" + userEditCommandInstance.errors)
			redirectEdit(user, userEditCommandInstance)
			return
		}
		bindData(user, userEditCommandInstance)
		def saved = user.save(flush: true)
		log.debug(user);

		if (request.xhr) {
			if (saved) {
				response.contentType = "text/javascript;charset=UTF-8"
				def cols = [
						username: user.username,
						enabled : g.render(template: 'status', plugin: 'deloved-admin', model: [status: user.enabled]).replaceAll("[\\n\\r]", "")
				]
				render template: '/_common/grid-row-update', model: [gridRowId: user.id, gridCols: cols, rawCols: [enabled: true], handler: "jQuery('#gridRow${user.id}enabled span.status').bind('click', onClickStatus);"]
			} else {
				redirectEdit(user)
			}
		} else {
			request.withFormat {
				'*' {
					flash.message = message(code: saved ? 'default.updated.message' : 'default.notUpdated.message',
							args: [message(code: 'user.label', default: 'User'), user.id]
					)
					redirect base: uri,controller: 'user', action: 'index'
				}
				json { respond user, [status: OK] }
			}
		}
	}

	@Transactional
	def batch(IdListCommand idList) {
		def cnt = 0
		User user = springSecurityService.getCurrentUser()
		log.trace('idList:' + idList)
		if (user.role.authority == 'ROLE_MANAGER') {
			Set<Long> idSecureList = new HashSet<>()
			def regFilter = profileService.findAllCitiesByProfile(user.profile)
			User.createCriteria().list {
				createAlias('role', 'r', CriteriaSpecification.LEFT_JOIN)
				createAlias('profile', 'p', CriteriaSpecification.LEFT_JOIN)

				inList("r.authority", ['ROLE_ACCOUNT', 'ROLE_MEDIATOR', 'ROLE_JUDGE', 'ROLE_JURIST'])
				inList("p.city", regFilter)
				inList("id", idList.getList())
			}.each {
				idSecureList.add(it.id)
			}
			idList.id = idSecureList
		}
		log.trace('idList:' + idList)
		if (params.batch_action == "batchDelete") {
			if (user.role.authority == 'ROLE_ADMIN') {
				deleteList(idList)
				return
			}
		} else if (params.batch_action == "batchEnable") {
			if (idList?.id?.size() > 0) {
				cnt = User.executeUpdate("update User u set u.enabled = true where u.enabled = false and u.id in (:idlist)", [idlist: idList.getList()])
				flash.message = message(code: 'user.batch.enabled.message', args: [cnt])
			}
		} else if (params.batch_action == "batchDisable") {
			if (idList?.id?.size() > 0) {
				cnt = User.executeUpdate("update User u set u.enabled = false where u.enabled = true and u.id in (:idlist)", [idlist: idList.getList()])
				flash.message = message(code: 'user.batch.disabled.message', args: [cnt])
			}
		}
		redirect base: uri,controller: 'user', action: "index", method: "GET"
	}

	private deleteList(IdListCommand idList) {
		def st = NO_CONTENT
		def mess = null
		def userLogin = springSecurityService.getCurrentUser()

		log.trace('params:' + params)
		log.trace('idList:' + idList)
		if (idList?.id) {
			if (idList.id.size() == 1) {
				def user = User.get(idList.id[0])
				if (user) {
					List <Keeper> keeperList = Keeper.findAllByProfile(user.profile)
					List <ContactConfirm> confirmList = ContactConfirm.findAllByProfile(user.profile)
					List <AccountProfile> accountProfileList = AccountProfile.findAllByProfile(user.profile)
					user.deleteAll(keeperList)
					user.deleteAll(confirmList)
					user.deleteAll(accountProfileList)
					user.delete flush: true
					mess = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), user.username])
				} else {
					notFound()
					return
				}
			} else if (idList.id.size() > 1) {
				int deleted = 0
				idList.getList().each {
					User.load(it).delete(flush: true)
					deleted++
				}
//				def cr = User.createCriteria()
//				cr.list {
//					inList('id', idList.getList())
//				}.each {
//					it.delete()
//					deleted++
//				}
				mess = message(code: 'default.deletedMany.message', args: [message(code: 'user.label', default: 'User'), idList.id, deleted])
			} else {
				notFound()
				return
			}
		} else {
			st = BAD_REQUEST
		}
		request.withFormat {
			form {
				flash.message = mess
				redirect base: uri,controller: 'user', action: "index", method: "GET"
			}
			'*' { render status: st }
		}
	}

	@Transactional
	@Secured(["hasRole('ROLE_ADMIN')"])
	def delete(IdListCommand idList) {
		deleteList(idList)
	}

	protected void notFound() {
		request.withFormat {
			'*' {
				render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])]
			}
			json { render status: NOT_FOUND }
		}
	}
}

class UserCreateCommand {

	def userService

	String username
	String password
	String passwordRepeat
	int role;
	Integer roleRequest;
	Region city

	static constraints = {
		username(blank: false,
				validator: { val, ucc ->
					return ucc.userService.isUsernameAvailable(val)
				}
		)
		password(blank: false)
		passwordRepeat(blank: false,
				validator: { passwd2, ucc ->
					return passwd2 == ucc.password
				}
		)
		role(blank: false)
		roleRequest(nullable: true)
		city(nullable: true)
	}
}

class UserEditCommand {

	def userService

	int id
	String username
	boolean enabled

	static constraints = {
		username(blank: false,
				validator: { val, ucc ->
					log.debug("obj id:" + ucc.id)
					log.debug("new val:" + val)
					return ucc.userService.isUsernameAvailable(val, ucc.id)
				}
		)
		enabled()
	}

	@Override
	public java.lang.String toString() {
		return "UserEditCommand{" +
				"enabled=" + enabled +
				", username='" + username + '\'' +
				", id=" + id +
				"}";
	}
}

@EqualsAndHashCode
class UserFilter extends FormFilter {
	Integer role
	String username
	Boolean enabled

	@Override
	protected List getFilterParamList() {
		['role', 'enabled', 'username']
	}

	@Override
	protected List getSortedParamList() {
		['username', 'enabled', 'role', 'dateCreated']
	}

	@Override
	public Map getMap() {
		def m = super.getMap()
		if (role != null) m.role = role
		if (username != null) m.username = username
		if (enabled != null) m.enabled = enabled
		return m
	}
}