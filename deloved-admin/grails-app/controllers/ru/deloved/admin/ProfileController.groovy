package ru.deloved.admin

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.transform.EqualsAndHashCode
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import org.grails.databinding.BindingFormat
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import ru.deloved.*

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
@Secured(["isFullyAuthenticated()"])
class ProfileController extends FilteredController {


	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"

	def attachService
	def userService
	def profileService
	def contactService
	def regionService

	def fileCategoryDirectory = new DefaultGrailsApplication().config?.fileCategory?.avatar ?: 'avatar'

	static allowedMethods = [update: "PUT", changepwd: "PUT"]

	@Override
	protected FormFilter createFilterInstance() {
		return new ProfileFilter()
	}

	@Override
	protected PagedResultWrapper getRows(FormFilter filter) {
		ProfileFilter f = filter
		User user = springSecurityService.getCurrentUser()
		def regManagerFilter = []
		if (user.role.authority == 'ROLE_MANAGER') {
			regManagerFilter = profileService.findAllCitiesByProfile(user.profile)
		}
		def regFilter = null
		if (f.region != null) {
			regFilter = regionService.getRegionFilter([f.region])
		}
		def rows = Profile.createCriteria().list(max: filter.max, offset: filter.offset) {
			createAlias('user', 'user')
			createAlias('user.role', 'role')
			if (f.enabled) {
				eq('user.enabled', f.enabled)
			}
			if (f.role) {
				eq('user.role', Role.get(f.role))
			}
			if (f.username) {
				ilike "user.username", "%" + f.username + "%"
			}
			if (f.charge != null) {
				if (f.charge == 0) {
					eq('chargeStatus', 0)
				} else if (f.charge == 1) {
					eq('chargeStatus', 1)
					isNotNull('chargeTill')
					gt('chargeTill', new Date())
				} else if (f.charge == 2) {
					isNotNull('chargeTill')
					lt('chargeTill', new Date())
				}
			}
			if (regFilter) {
				inList("city", regFilter)
			}
			if (user.role.authority == 'ROLE_MANAGER') {
				inList("role.authority", ['ROLE_ACCOUNT', 'ROLE_MEDIATOR', 'ROLE_JUDGE', 'ROLE_JURIST'])
				inList("city", regManagerFilter)
			}
			order(filter.sort ?: "id", filter.order ?: "asc")
		}
		new PagedResultWrapper(rows)
	}

	def getRegionFilterData(Long regionId) {
		Region region = regionService.getRegion(regionId)
		Region country = regionService.getParentsRegions().first()
		List tree = regionService.getTree(country.id)
		String resultJson = tree.first().children as grails.converters.JSON

		[
				regionsTreeJson: resultJson,
				selectedRegion : region
		]

	}


	@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER') and isFullyAuthenticated()"])
	def index(ProfileFilter indexFilter) {
		def max = 10
		ProfileFilter filter = getFilter(max, indexFilter)

		def regionFilterData = getRegionFilterData(filter.region)

		processIndex(10, filter) {
			[model: [
					regionFilterData: regionFilterData
			]]
		}
	}

	ArrayList<String> availableProperties = ['city', 'email', 'cellPhone', 'fio','role']

	@Transactional
	@Secured(["isFullyAuthenticated()"])
	def editNew(Profile profile) {

		if (!request.xhr) {
			log.debug('request not xhr')
			redirect(base: uri,controller: 'profile',action: 'me')
			return
		}

		if (!availableProperties.contains(params.prop)) {
			log.debug('wrong property to edit')
			render(status: 404, text: 'Bad email')
			return
		}

		if (!profileService.isEditAllowed(profile)) {
			log.debug('not allowed to edit')
			render(status: 404, text: 'Bad email')
			return
		}

		switch (params.prop) {
			case 'city':
				profile.city = Region.findByNameAndLevel(params.value, RegionLevel.findByType(RegionType.findByCode('CITY')))
				break
			case 'email':
				def emailPattern = /[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})/

				if (!params.value ==~ emailPattern) {
					render(status: 403, text: 'Bad email')
					return
				}

				if (profile.email != params.value) {
					log.debug("addEmailContact:" + params.value)
					contactService.addEmailContact(ConfirmType.ContactUpdate, profile, params.value)
				} else {
					render(status: 200, text: 'Успешно!')
					return
				}
				break
			case 'fio':
				profile.setProperty(params.prop, params.value)
				break
			case 'dayOfWork':
				profile.setProperty(params.prop, params.value)
				break
			case 'phone':
				profile.setProperty(params.prop, params.value)
				break
		}

		profile.validate()

		if (profile.hasErrors()) {
			profile.errors.each {
				log.debug(it)
			}
			log.debug('fail')
			render(status: 403, text: 'Bad property value')
			return
		}
		else {
			profile.save(flush: true)
			log.debug('success')
			render(status: 200, text: 'Изменения успешно сохранены!')
			userService.renameAccountManager(profile)
			return
		}
	}

	def me() {
		User user = springSecurityService.getCurrentUser()
		return show(user.profile)
	}

	def show(Profile profileInstance) {

		def confirmEmailList = ContactConfirm.findAllByProfileAndTypeAndStatusInList(profileInstance, ContactType.Email.value(), [ConfirmStatus.New.value(), ConfirmStatus.Process.value()])
		def confirmCellphoneList = ContactConfirm.findAllByProfileAndTypeAndStatusInList(profileInstance, ContactType.CellPhoneNumber.value(), [ConfirmStatus.New.value(), ConfirmStatus.Process.value()])
		respond profileInstance, [view: 'show', model: [confirmEmailList: confirmEmailList, confirmCellphoneList: confirmCellphoneList]]
	}

	def passwd() {

	}

	@Transactional(readOnly = false)
	def changepwd(PasswordEditCommand pwdInstance) {
		log.debug("pwdInstance:" + pwdInstance)
		pwdInstance.validate()
		if (pwdInstance.hasErrors()) {
			log.error(pwdInstance.errors)
			render(view: 'passwd', model: [
					objInstance: pwdInstance.errors
			])
			return
		}
		User user = springSecurityService.getCurrentUser()
		user.password = pwdInstance.newPassword
		def saved = user.save(flush: true)
		if (saved) {
			flash.message = message(code:'profile.password.updated.message')
			flash.status = 'success'
		} else {
			flash.message = message(code:'profile.password.notUpdated.message')
			flash.status = 'warning'
		}
		redirect(base: uri,controller: 'profile',action: 'me')
	}

	protected redirectEdit(Profile beanResource, ProfileEditCommand objInstance = null) {

		User user = springSecurityService.getCurrentUser()

		def matrix = [
				'ROLE_ADMIN'  : ['ROLE_MANAGER': 1, 'ROLE_MEDIATOR': 1, 'ROLE_JUDGE': 1, 'ROLE_JURIST': 1],
				'ROLE_MANAGER': ['ROLE_MANAGER': 0, 'ROLE_MEDIATOR': 1, 'ROLE_JUDGE': 1, 'ROLE_JURIST': 1],
		]

		def regEditAllowed = (user.role.authority in ['ROLE_ADMIN', 'ROLE_MANAGER']) ?
				((matrix[user.role.authority][beanResource.user.role.authority] ?: 0) == 1) : false;

		def confirmEmailList = ContactConfirm.findAllByProfileAndTypeAndStatusInList(beanResource, ContactType.Email.value(), [ConfirmStatus.New.value(), ConfirmStatus.Process.value()])
		def confirmCellphoneList = ContactConfirm.findAllByProfileAndTypeAndStatusInList(beanResource, ContactType.CellPhoneNumber.value(), [ConfirmStatus.New.value(), ConfirmStatus.Process.value()])

		render view: request.xhr ? 'editform' : 'edit',
				model: [
						beanResource        : beanResource,
						objInstance         : objInstance ?: beanResource,
						inputCity           : userService.isAccountOrSpecialist(beanResource.user),
						regEditAllowed      : regEditAllowed,
						confirmEmailList    : confirmEmailList,
						confirmCellphoneList: confirmCellphoneList
				]
	}


	def edit(Profile profileInstance) {
		log.debug("edit:" + profileInstance);
		User user = springSecurityService.getCurrentUser()
		if (params.id == null) {// my
			profileInstance = user.profile
		}
		if (profileInstance?.id == null) {
			notFound()
			return
		}
		if (!profileService.isEditAllowed(profileInstance)) {
			accessDenied()
			return
		}

		ProfileEditCommand profileEditCommandInstance = new ProfileEditCommand()
		log.debug('profileEditCommandInstance: ' + profileEditCommandInstance)
		log.debug("profileInstance.user: " + profileInstance.user.role)
		bindData(profileEditCommandInstance, profileInstance, [include: ['fio', 'email', 'cellPhone', 'dayOfWork','city','role']])
		redirectEdit(profileInstance, profileEditCommandInstance)
	}

	def cities(String term) {
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
	}

	void loadParentsRegion(Region el, ArrayList parents) {
		if (el) {
			parents.add(el.id)
			loadParentsRegion(el.parent, parents)
		}
	}

	def reg(Profile profileInstance) {
		User user = springSecurityService.getCurrentUser()
		if (params.id == null) {// my
			profileInstance = user.profile
		}
		if (profileInstance?.id == null) {
			notFound()
			return
		}
		if (!profileService.isEditAllowed(profileInstance)) {
			accessDenied()
			return
		}
		if (profileInstance?.id == null || params.pid == null) {
			log.debug('cat - no profile id');
			render(status: BAD_REQUEST, contentType: 'application/json') {
				[error: 1]
			}
			return
		}
		def regParent = Region.get(params.pid)
		def profileRegions = ProfileRegion.findAllByProfile(profileInstance)
		def regIds = []
		def undetermRegIds = []
		profileRegions.each {
			regIds.add(it.region.id)
			loadParentsRegion(it.region, undetermRegIds)
		}
		log.debug(regIds)
		def res = []

		def childsClos
		childsClos = { Region reg, List obj ->
			def list = Region.findAllByParent(reg, [sort: 'name'])
			list.each {
				def el = [
						id  : it.id,
						text: it.name
				]
				def state = [:]
				if (Region.countByParent(it) > 0) {
					def children = []
					childsClos(it, children)
					el.putAt('children', children)
					state.putAt('opened', (it.id in undetermRegIds) && !(it.id in regIds))
				}
				state.putAt('selected', (it.id in regIds))
				if (!state.isEmpty()) {
					el.putAt('state', state)
				}
				obj << el
			}
			return
		}

		childsClos(regParent, res)
		render(status: OK, contentType: 'application/json') {
			res
		}
	}

	@Transactional
	def update(ProfileEditCommand profileEditCommandInstance) {

		log.debug("update:" + profileEditCommandInstance)
		log.error("params:" + params)

		Profile profile = null

		User user = springSecurityService.getCurrentUser()

		if (params.id == null) {// my
			profile = user.profile
			profileEditCommandInstance.id = profile.id
		} else {
			profile = Profile.get(params.id)
			if (profile?.id == null) {
				notFound()
				return
			}
			if (!profileService.isEditAllowed(profile)) {
				accessDenied()
				return
			}
		}
		def inputCity = userService.isAccountOrSpecialist(profile.user)
		if (inputCity) {
			if (profileEditCommandInstance.city == null && params.cityname) {
				def city = Region.findByNameAndLevel(params.cityname, RegionLevel.findByType(RegionType.findByCode('CITY')))
				if (city) {
					profileEditCommandInstance.city = city
				} else {
					profileEditCommandInstance.city = null
				}
			}
			log.debug("city:" + profileEditCommandInstance.city)
		} else {
			profile.city = null
			profileEditCommandInstance.city = null
		}


		profileEditCommandInstance.validate()
		if (profileEditCommandInstance.hasErrors()) {
			log.error("has errors:" + profileEditCommandInstance.errors)
			redirectEdit(profile, profileEditCommandInstance)
			return
		}
		boolean createEmailConfirm = false;
		boolean createSmsConfirm = false;
		if (user.role.authority in ['ROLE_ADMIN', 'ROLE_MANAGER']) {
			def includeList = ['fio', 'city','dayOfWork']
			if (user.role.authority in ['ROLE_ADMIN']) {
				includeList.add('chargeStatus')
				includeList.add('chargeTill')
			}
			if (params.withoutEmailConfirm) {
				includeList.add('email')
			} else {
				createEmailConfirm = true
			}
			if (params.withoutSmsConfirm) {
				includeList.add('cellPhone')
			} else {
				createSmsConfirm = true
			}
			log.debug("params.withoutEmailConfirm:" + params.withoutEmailConfirm)
			log.debug("params.withoutSmsConfirm:" + params.withoutSmsConfirm)
			log.debug("includeList:" + includeList)
			bindData(profile, profileEditCommandInstance, [include: includeList])
			log.debug("profile:" + profile);
		} else {
			bindData(profile, profileEditCommandInstance, [include: ['fio', 'city','dayOfWork']])
			createEmailConfirm = true
			createSmsConfirm = true
		}
		def saved = profile.save(flush: true)

		log.debug("createEmailConfirm:" + createEmailConfirm)
		log.debug("createSmsConfirm:" + createSmsConfirm)
		if (createEmailConfirm) {
			if (profileEditCommandInstance.email && (profileEditCommandInstance.email != profile.email)) {
				log.debug("addEmailContact:" + profileEditCommandInstance.email)
				contactService.addEmailContact(ConfirmType.ContactUpdate, profile, profileEditCommandInstance.email)
			}
		}
		if (createSmsConfirm) {
			if (profileEditCommandInstance.cellPhone && (profileEditCommandInstance.cellPhone != profile.cellPhone)) {
				log.debug("addCellContact:" + profileEditCommandInstance.cellPhone)
				contactService.addCellContact(ConfirmType.ContactUpdate, profile, profileEditCommandInstance.cellPhone)
			}
		}
		if (params.smsCode) {
			contactService.confirmCellPhone(profile, params.smsCode)
		}


		log.debug(profile);
		// update profile's regions
		if (profileEditCommandInstance.reg == null) {
			ProfileRegion.executeUpdate('delete ProfileRegion where profile=?', [profile])
		} else {
			ProfileRegion.executeUpdate('delete ProfileRegion where profile=:profile and region.id not in(:reglist)', [profile: profile, reglist: profileEditCommandInstance.reg.asList()])
			def exist = ProfileRegion.findAllByProfile(profile)
			exist.each {
				if (it.region.id in profileEditCommandInstance.reg) {
					profileEditCommandInstance.reg.remove(it.region.id)
				}
			}
			log.debug("modified reg:" + profileEditCommandInstance.reg)
			profileEditCommandInstance.reg.each {
				new ProfileRegion(profile: profile, region: Region.load(it)).save(flush: true)
			}
		}

		userService.renameAccountManager(profile)

		if (request.xhr) {
			if (saved) {
				response.contentType = "text/javascript;charset=UTF-8"
				def cols = [
						dayOfWork:profile.dayOfWork,
						fio      : profile.fio,
						email    : profile.email,
						cellPhone: profile.cellPhone,
						city     : (profile.city?.name) ?: ''
				]
				render template: '/_common/grid-row-update', model: [gridRowId: profile.id, gridCols: cols]
			} else {
				redirectEdit(profile)
			}
		} else {
			request.withFormat {
				'*' {
					flash.message = message(code: saved ? 'default.updated.message' : 'default.notUpdated.message',
							args: [message(code: 'profile.label'), profile.id]
					)
					if (params.id) {
						redirect base: uri,controller: 'profile',action: 'index'
					} else {
						redirect base: uri,controller: 'profile',action: 'me'
					}
				}
				json { respond user, [status: OK] }
			}
		}
	}

	@Transactional
	def upload(Profile profileInstance) {

		User user = springSecurityService.getCurrentUser()
		if (params.id == null) {// my
			profileInstance = user.profile
		}
		if (profileInstance?.id == null) {
			notFound()
			return
		}
		if (!profileService.isEditAllowed(profileInstance)) {
			accessDenied()
			return
		}

		def files = []
		if (request instanceof MultipartHttpServletRequest) {
			def filename = request.getFileNames().next()
			MultipartFile file = request.getFile(filename)

			if (attachService.uploadImageAndThumbnail(file,
					fileCategoryDirectory,
					profileInstance, profileInstance.avatar, profileInstance.avatarThumb) {
				Attachment thumb, Attachment image ->
					thumb.save(flush: true)
					image.save(flush: true)
					profileInstance.avatarThumb = thumb
					profileInstance.avatar = image
					profileInstance.save(flush: true)
					return true
			}) {

				files << [
						name        : file.originalFilename,
						size        : file.size,
						url         : g.createLink([controller: 'profile', action: 'avatar', id: profileInstance.id, params: [name: profileInstance.avatar.file, type: 'main']]),
						thumbnailUrl: g.createLink([controller: 'profile', action: 'avatar', id: profileInstance.id, params: [name: profileInstance.avatarThumb.file]]),
						deleteUrl   : createLink(controller: 'profile', action: 'deleteavatar', id: profileInstance.id),
						deleteType  : "DELETE"
				]
			} else {
			/*	files << [
						name : file.originalFilename,
						size : file.size,
						error: 'Error upload'
				] */
				accessDenied()
				return
			}
		}
		def results = [files: files]
		render results as JSON
	}

	@Transactional
	def crop(Profile profileInstance) {
		User user = springSecurityService.getCurrentUser()
		if (params.id == null) {// my
			profileInstance = user.profile
		}
		if (profileInstance?.id == null) {
			notFound()
			return
		}
		if (!profileService.isEditAllowed(profileInstance)) {
			accessDenied()
			return
		}
		log.debug(params)
		if (attachService.cropThumbnail(params, fileCategoryDirectory,
				profileInstance.avatar, profileInstance.avatarThumb)) {

			def results = [thumb: g.createLink([controller: 'profile', action: 'avatar', id: profileInstance.id, params: [name: profileInstance.avatarThumb.file]])]
			render results as JSON
		} else if (profileInstance.avatar == null) {
			render status: NO_CONTENT
		} else {
			render status: BAD_REQUEST
		}
	}


	@Transactional
	def deleteavatar(Profile profileInstance) {
		User user = springSecurityService.getCurrentUser()
		if (params.id == null) {// my
			profileInstance = user.profile
		}
		if (profileInstance?.id == null) {
			notFound()
			return
		}
		if (!profileService.isEditAllowed(profileInstance)) {
			accessDenied()
			return
		}
		attachService.deleteImageAndThumbnail(fileCategoryDirectory,
				profileInstance.avatar, profileInstance.avatarThumb) {
			profileInstance.avatar = null
			profileInstance.avatarThumb = null
			profileInstance.save(flush: true)
			return true
		}
		def res = []
		render res as JSON
	}

	def avatar(Profile profileInstance) {
		log.debug("avatar params:" + params)
		log.debug("avatar profile:" + profileInstance)
		User user = springSecurityService.getCurrentUser()
		if (params.id == null) {// my
			profileInstance = user.profile
		}
		if (profileInstance?.id == null) {
			notFound()
			return
		}

		if (profileInstance.avatar) {
			Attachment att = Attachment.get(params.type == 'main' ? profileInstance.avatar.id : profileInstance.avatarThumb.id)
			if (att && att.file == params.name) {
				attachService.sendFile(fileCategoryDirectory, att, response)
				return null
			}
		}
		render status: NO_CONTENT
		return null
	}


	protected void notFound() {
		request.withFormat {
			'*' {
				render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'profile.label', default: 'Profile'), params.id])]
			}
			json { render status: NOT_FOUND }
		}
	}

	protected void accessDenied() {
		if (springSecurityService.isAjax(request)) {
			render status: FORBIDDEN
		} else {
			render view: "/login/denied", status: FORBIDDEN
		}
	}
}

class PasswordEditCommand {
	def springSecurityService
	def userService

	String oldPassword
	String newPassword
	String newPasswordRepeat

	static constraints = {
		oldPassword(blank: false,
				validator: { val, obj ->
					User user = obj.springSecurityService.getCurrentUser()
					String encPassword = obj.userService.encodePassword(val)
					return user.password == encPassword
				}
		)
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
				"oldPassword='" + oldPassword + '\'' +
				", newPassword='" + newPassword + '\'' +
				", newPasswordRepeat='" + newPasswordRepeat + '\'' +
				'}';
	}
}

class ProfileEditCommand {
	def userService

	int id
	String fio
	String email
	String cellPhone
	String dayOfWork
	Region city
	Integer chargeStatus
	@BindingFormat('dd.MM.yyyy HH:mm:ss')
	Date chargeTill

	Set<Long> reg

	static constraints = {
		importFrom(Profile)
		chargeStatus(nullable: true)
		city(nullable: true,
				validator: { val, obj ->
					if (obj.id && obj.userService.isAccountOrSpecialist(Profile.get(obj.id).user)) {
						return val != null
					}
					return true
				}
		)
		reg nullable: true
		chargeTill nullable: true, validator: { val, obj ->
			if (obj.chargeStatus > 0) {
				return val != null
			} else {
				return true;
			}
		}
	}

	@Override
	public java.lang.String toString() {
		return "ProfileEditCommand{" +
				"id=" + id +
				", fio='" + fio + '\'' +
				", email='" + email + '\'' +
				", cellPhone='" + cellPhone + '\'' +
				", dayOfWork='" + dayOfWork + '\'' +
				", reg=" + reg.toString() +
				"}";
	}
}


@EqualsAndHashCode
class ProfileFilter extends FormFilter {
	Integer role
	String username
	Boolean enabled
	Long region
	Integer charge

	def springSecurityService

	@Override
	protected List getFilterParamList() {
		['role', 'enabled', 'username', 'region', 'charge']
	}

	@Override
	protected List getSortedParamList() {
		['user.role', 'enabled', 'username', 'fio', 'email', 'cellPhone']
	}

	@Override
	public Map getMap() {
		def m = super.getMap()
		if (role != null) m.role = role
		if (username != null) m.username = username
		if (enabled != null) m.enabled = enabled
		if (region != null) m.region = region
		if (charge != null) m.charge = charge
		return m
	}
}