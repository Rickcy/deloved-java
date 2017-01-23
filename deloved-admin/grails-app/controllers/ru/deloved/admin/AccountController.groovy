package ru.deloved.admin


import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import jline.internal.Log
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import org.grails.datastore.mapping.validation.ValidationErrors
import org.hibernate.criterion.CriteriaSpecification
import org.springframework.validation.FieldError
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import ru.deloved.*
import ru.deloved.recall.JuristConsult
import ru.deloved.recall.JuristConsultLastVisit
import ru.deloved.recall.JuristConsultPost
import ru.deloved.recall.Ticket
import ru.deloved.recall.TicketLastVisit
import ru.deloved.recall.TicketPost

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_ACCOUNT') and isFullyAuthenticated()"])
class AccountController extends FilteredController {

	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"


	def profileService
	def attachService
	def accountService
	def categoryService
	def springSecurityService
	def fileCategoryDirectory = new DefaultGrailsApplication().config?.fileCategory?.logo ?: "logo"

	@Override
	protected FormFilter createFilterInstance() {
		return new AccountFilter()
	}

	@Override
	protected PagedResultWrapper getRows(FormFilter filter) {
		AccountFilter f = filter
		User user = springSecurityService.getCurrentUser()
		def regFilter = []
		List<Account> accountList = []
		if (user.role.authority == 'ROLE_MANAGER') {
			regFilter = profileService.findAllCitiesByProfile(user.profile)
		} else if (user.role.authority == 'ROLE_ACCOUNT') {
			accountList = accountService.getMyAccounts()
		}

		def rows = Account.createCriteria().list(max: filter.max, offset: filter.offset) {
			createAlias("city", "city", CriteriaSpecification.LEFT_JOIN)
			createAlias("orgForm", "orgForm", CriteriaSpecification.LEFT_JOIN)
			if (f.name) {
				ilike "name", "%" + f.name + "%"
			}
			if (user.role.authority == 'ROLE_MANAGER') {
				inList("city", regFilter)
			} else if (user.role.authority == 'ROLE_ACCOUNT') {
				def idlist = []
				accountList.each { idlist.add(it.id) }
				inList("id", idlist)
			}
			order(filter.sort ?: "dateCreated", filter.order ?: "desc")
		}
		new PagedResultWrapper(rows)
	}

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(AccountFilter accountFilter) {
		User user = springSecurityService.getCurrentUser()
		if (user.role.authority == 'ROLE_ACCOUNT') {
			List myAccounts = accountService.getMyAccounts()
			Account defaultAccount = null;
			if (!myAccounts.isEmpty()) {
					defaultAccount = myAccounts.get(0)

			} else {
				notFound()
				return
			}
			respond defaultAccount, view: "show"

		} else {
			processIndex(10, accountFilter)
		}

	}

	def reviews(Account accountInstance) {
		if (accountInstance?.id == null /*|| params.id == null*/) {
			notFound()
			return
		}
		def reviews = Review.findAllByToAndPublishedAndFinished(accountInstance, true, true)

		render view: 'reviews',
				model: [
						accountInstance: accountInstance,
						reviewsCount   : reviews.size(),
						reviews        : reviews
				]
	}

	def show(Account accountInstance) {

		if (accountInstance?.id == null) {
			notFound()
			return
		}

		User user = springSecurityService.getCurrentUser()
		if (user.role.authority == 'ROLE_ACCOUNT' && !accountService.isMyAccount(accountInstance)) {
			accessDenied()
			return
		}
		respond accountInstance,[model:[
				accountInstance: accountInstance,
				reviewsCount: reviews.size(),
				reviews     : reviews
		]]
	}

	@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER') and isFullyAuthenticated()"])
	def create() {
		session.removeAttribute(getFilterSessionName() + "CreateCategories")
		respond new Account(params)
	}

	def affform() {
		render template: "newAff", model: [i: params.index]
	}

	@Transactional
	def save(Account accountInstance) {

		log.debug('accountController.save.params: ' + params)

		if (accountInstance == null) {
			notFound()
			return
		}
		User user = springSecurityService.getCurrentUser()
		if (user.role.authority == 'ROLE_ACCOUNT' && !accountService.isMyAccount(accountInstance)) {
			accessDenied()
			return
		}

		def t = RegionType.findByCode('CITY')
		log.debug(t?.code)
		def l = RegionLevel.findByType(t)
		log.debug(l)
		if (accountInstance.city == null && params.cityname) {
			def city = Region.findByNameAndLevel(params.cityname, l)
			if (city) {
				accountInstance.city = city
			}
		}
		log.debug("city:" + accountInstance.city)

		if (params.aff) {
			def affiliatesParams = params.aff.values().findAll { it instanceof GrailsParameterMap }
			def toAdd = []
			def noError = true
			List<Affiliate> affiliatesList = affiliatesParams.collect {
				def affiliate = new Affiliate()
				bindData(affiliate, it)
				toAdd.add(affiliate)
			}
			if (noError) {
				toAdd.each {
					log.debug("to Add " + it)
					accountInstance.addToAffiliates(it)
				}
				log.debug("affiliates:" + accountInstance.affiliates)
			} else {
				def localErrors = new ValidationErrors(accountInstance)
				localErrors.addError(new FieldError("ru.deloved.Affiliate", "affiliates", message(code: 'account.affiliates.error')))
				accountInstance.errors = localErrors
				respond accountInstance.errors, view: 'create'
				return
			}
		}


		accountInstance.validate()
		if (accountInstance.hasErrors()) {
			if (params.cat != null) {
				ArrayList<Long> catList = new ArrayList<Long>()

				if (params.cat instanceof String) {
					catList.add(params.cat)
				} else {
					params.cat.each {
						catList.add(it as Long)
					}
				}

				session.setAttribute(getFilterSessionName() + "CreateCategories", catList)
			}

			log.error(accountInstance.errors)
			respond accountInstance.errors, view: 'create'
			return
		}

		def saved = accountInstance.save(flush: true)
		if (saved) {
			// insert account's categories
			if (params.profileid != null && params.profileid != '') {
				log.debug("bind to Profile:'" + params.profileid + "'")
				new AccountProfile(account: accountInstance, profile: Profile.load(params.profileid)).save(flush: true)
			}
			if (params.cat != null) {
				ArrayList<Long> catList = new ArrayList<Long>()

				if (params.cat instanceof String) {
					catList.add(params.cat)
				} else {
					params.cat.each {
						catList.add(it as Long)
					}
				}

				log.debug("new cat:" + catList)

				categoryService.save(accountInstance, catList)
			}
			session.removeAttribute(getFilterSessionName() + "CreateCategories")
			request.withFormat {
				'*' {
					flash.message = message(code: 'default.created.message', args: [message(code: 'accountInstance.label', default: 'Account'), accountInstance.id])
					redirect base: uri,controller: 'account', action: 'index'
				}
				json { respond accountInstance, [status: CREATED] }
			}
		} else {
			if (params.cat != null) {
				ArrayList<Long> catList = new ArrayList<Long>()

				if (params.cat instanceof String) {
					catList.add(params.cat)
				} else {
					params.cat.each {
						catList.add(it as Long)
					}
				}

				log.debug("save to session cat:" + catList)
				session.setAttribute(getFilterSessionName() + "CreateCategories", catList)
			}
			log.error(accountInstance.errors)
			respond accountInstance.errors, view: 'create'
		}

	}

	protected redirectEdit(Account beanResource, objInstance = null) {
		render view: request.xhr ? 'editform' : 'edit',
				model: [
						beanResource: beanResource,
						objInstance : objInstance ?: beanResource,
						profile     : AccountProfile.findByAccount(beanResource)?.profile
				]

	}




	def addAffiliate() {
		render(template: 'affiliate', model: [affiliateInstance: null, i: params.index, active: true])
	}

	@Transactional
	@Secured(["isFullyAuthenticated()"])
	def updateAffiliates(Account accountInstance) {
		if (!request.xhr) {
			render(status: 403, text: 'Not xhr request!')
			return
		}

		if (!accountService.isMyAccount(accountInstance)) {
			render(status: 403, text: 'This account not your!')
			return
		}

		if (!params.aff) {
			render(status: 403, text: 'Bad params!')
			return
		}

		def affiliatesParams = params.aff.values().findAll {it instanceof GrailsParameterMap}

		ArrayList<Affiliate> affsToCheck = []
		ArrayList<Affiliate> existAffs = []

		accountInstance.affiliates.each {
			existAffs.add(it)
		}

		ArrayList<Affiliate> mergeAffs = existAffs.clone()

		affiliatesParams.each {
			if (!it.id) {

				def city = Region.findByNameAndLevel(it.city, RegionLevel.findByType(RegionType.findByCode('CITY')))

				Affiliate aff = new Affiliate(city: city, address: it.address, email: it.email, phone: it.phone)
				aff.validate()
				if (aff.hasErrors()) {
					aff.errors.each {
						log.debug(it)
					}
					throw new RuntimeException("Panic what the hell happened")
				} else {
					aff.save(flush: true)
					accountInstance.addToAffiliates(aff)
				}
			} else {
				Affiliate originAff = Affiliate.load(it.id)

				if (originAff?.city?.name != it.city) {
					//if originAff?.city?.name != null
					def newCity = Region.findByNameAndLevel(it.city, RegionLevel.findByType(RegionType.findByCode('CITY')))
					if (!newCity && originAff.city) {
						throw new RuntimeException("Panic what the hell happened")
					}
					originAff.city = newCity
				}
				if (originAff.address != it.address) {
					originAff.address = it.address
				}
				if (originAff.email != it.email) {
					originAff.email = it.email
				}
				if (originAff.phone != it.phone) {
					originAff.phone = it.phone
				}
				originAff.validate()
				if (originAff.hasErrors()) {
					originAff.errors.each {
						log.debug(it)
						throw new RuntimeException("Panic what the hell happened")
					}
				} else {
					originAff.save(flush: true)
				}
				affsToCheck.add(originAff)
			}
		}

		mergeAffs.removeAll(affsToCheck)
		if (!mergeAffs.isEmpty()){
			mergeAffs.each {
				accountInstance.removeFromAffiliates(it)
				it.delete(flush: true)
			}
		}


		render(status: 200, text: 'Изменения успешно сохранены!')
	}

	ArrayList<String> availableProperties = ['email', 'phone1', 'phone2', 'phone3', 'address', 'city', 'fax1', 'fax2', 'webAddress', 'workTime', 'description', 'keywords', 'categories']

	@Transactional
	@Secured(["isFullyAuthenticated()"])
	def editNew(Account defaultAccount) {
		if (!request.xhr) {
			render(status: 404, text: 'Account not found!')
			return
		}

		if (!availableProperties.contains(params.prop)) {
			render(status: 404, text: 'Account not found!')
			return
		}

		if (!accountService.isMyAccount(defaultAccount)) {
			render(status: 404, text: 'Account not found!')
			return
		}

		if (params.aff) {
	    } else if (params.prop == 'city') {
			defaultAccount.city = Region.findByNameAndLevel(params.value, RegionLevel.findByType(RegionType.findByCode('CITY')))
		} else if (params.prop == 'categories') {
			log.debug(params.value)
			if (params.value == null) {
				AccountCategory.executeUpdate('delete AccountCategory where account=?', [defaultAccount])
			} else {
				def paramsCatList = []
				params.value.each {
					paramsCatList.add(it as Long)
				}
				paramsCatList.sort()
				log.debug("AccountController.editNew.paramsCatList: "  + paramsCatList)

				def existCatList = AccountCategory.executeQuery("""
					select ac.category.id from AccountCategory ac
					where ac.account = :account
				""", [account: defaultAccount])
				existCatList.sort()
				log.debug("AccountController.editNew.existCatList: " + existCatList)

				if (!paramsCatList.equals(existCatList)) {

					def catListToAdd = []
					def catListToDel = []

					existCatList.each {
						if (!paramsCatList.contains(it)) {
							catListToDel.add(it)
						}
					}
					log.debug("AccountController.editNew.catListToDel: " + catListToDel)

					if (!catListToDel.isEmpty()) {
						AccountCategory.executeUpdate("""
							delete AccountCategory
							where account=:account
							and category.id in (:catListToDel)
						""", [account: defaultAccount, catListToDel: catListToDel])
					}

					paramsCatList.each {
						if (!existCatList.contains(it)){
							catListToAdd.add(it)
						}
					}

					log.debug("AccountController.editNew.catListToAdd: " + catListToAdd)
					if (!catListToAdd.isEmpty()) {
						catListToAdd.each {
							new AccountCategory(account: defaultAccount, category: Category.load(it)).save(flush: true)
						}
					}
				}
			}
		} else {
			defaultAccount.setProperty(params.prop, params.value)
		}

		defaultAccount.validate()

		def res = []

		if (defaultAccount.hasErrors()) {
			defaultAccount.errors.each {
				log.debug(it)
			}

		/*	def messages = []
			defaultAccount.errors.each {
				messages.push(g.message(error: it))
			}

			log.debug('AccountController.editNew.messages: ' + messages)*/

			res = [status: 'danger', messages: ['Не верные параметры']]
			log.debug('AccountController.editNew.fail')
		} else {
			defaultAccount.save(flush: true)
			res = [status: 'success', messages: ['Успешно изменено']]
			log.debug('AccountController.editNew.success')
		}
			render res as JSON
			return
	}

	def edit(Account accountInstance) {
		if (accountInstance?.id == null) {
			notFound()
			return
		}
		User user = springSecurityService.getCurrentUser()
		if (user.role.authority == 'ROLE_ACCOUNT' && !accountService.isMyAccount(accountInstance)) {
			accessDenied()
			return
		}
		redirectEdit(accountInstance)
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

	void loadParentsCategory(ru.deloved.Category el, ArrayList parents) {
		if (el) {
			parents.add(el.id)
			loadParentsCategory(el.parent, parents)
		}
	}

	def cat(Account accountInstance, Long pid) {
		if (pid == null) {
			render(status: BAD_REQUEST, contentType: 'application/json') {
				[error: 1]
			}
			return
		} else {
			def res = []
			def catParent = Category.get(pid)
			def catIds = []
			def undetermCatIds = []

			if (accountInstance?.id != null) {
				User user = springSecurityService.getCurrentUser()
				if (user.role.authority == 'ROLE_ACCOUNT' && !accountService.isMyAccount(accountInstance)) {
					accessDenied()
					return
				}

				def accountCats = AccountCategory.findAllByAccount(accountInstance)
				accountCats.each {
					catIds.add(it.category.id)
					loadParentsCategory(it.category, undetermCatIds)
				}
			} else {
				// читаем из сессии
				def scatIds = session.getAttribute(getFilterSessionName() + "CreateCategories")
				log.debug("from session cat:" + scatIds)
				if (scatIds == null) {
					catIds = []
				} else {
					scatIds.each {
						catIds.add(it as Long)
						loadParentsCategory(categoryService.getCategory(it as Long), undetermCatIds)
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
	}

	@Transactional
	@Secured(["isFullyAuthenticated()"])
	def status(Account accountInstance) {
		log.debug("status:" + accountInstance);
		if (accountInstance?.id == null) {
			notFound()
			return
		}
		User user = springSecurityService.getCurrentUser()
		if (user.role.authority == 'ROLE_ACCOUNT') {
			accessDenied()
			return
		}

		def m = [:]
		if (params.type == "ps") {
			accountInstance.publicStatus = !accountInstance.publicStatus
			m['status'] = accountInstance.publicStatus
			m['statusClass'] = 'publicStatus'
			m['iconFalse'] = 'glyphicon-lock'
		} else if (params.type == "vs") {
			accountInstance.verifyStatus = !accountInstance.verifyStatus
			m['status'] = accountInstance.verifyStatus
			m['statusClass'] = 'verifyStatus'
		} else {
			render(status: 500, text: '')
			return
		}
		if (accountInstance.save(flush: true)) {
			respond accountInstance, view: '_status', model: m
		} else {
			render(status: 500, text: '')
		}
	}

	@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER') and isFullyAuthenticated()"])
	def profiles(Account accountInstance, String term) {


		def list = Profile.findAll("from Profile as p where p.id not in (select profile.id from AccountProfile group by profile.id) and upper(p.fio) like :search or upper(p.email) = :search ", [search: "%${term}%".toUpperCase()], [max: 5])
		def res = []
		list.each { it ->
			res << [label: it.fio + (it.email != null ? (", " + it.email) : ''), value: it.fio, id: it.id]
		}
		render(status: OK, contentType: 'application/json') {
			res
		}
	}


	@Transactional
	def update(AccountEditCommand accountEditCommandInstance) {

		log.debug("accountController.update.params: " + params)

		Account accountInstance = Account.get(params.id)
		if (accountInstance?.id == null) {
			notFound()
			return
		}
		User user = springSecurityService.getCurrentUser()
		if (user.role.authority == 'ROLE_ACCOUNT' && !accountService.isMyAccount(accountInstance)) {
			accessDenied()
			return
		}


		def t = RegionType.findByCode('CITY')
		log.debug(t?.code)
		def l = RegionLevel.findByType(t)
		log.debug(l)
		if (accountEditCommandInstance.city == null && params.cityname) {
			def city = Region.findByNameAndLevel(params.cityname, l)
			if (city) {
				accountEditCommandInstance.city = city
			}
		}
		log.debug("city:" + accountEditCommandInstance.city)

		if (params.aff) {
			def affiliatesParams = params.aff.values().findAll { it instanceof GrailsParameterMap }
			def exist = [:]
			def toDel = [:]
			def toAdd = []
			accountInstance.affiliates.each {
				exist[it.id] = it
				toDel[it.id] = it.id
			}
			log.debug("exist:" + exist)
			log.debug("toDel:" + toDel)
			def noError = true
			List<Affiliate> affiliatesList = affiliatesParams.collect {
				if (it.id) {
					def affiliate = exist[Long.valueOf(it.id)]
					log.debug("Modify for:" + affiliate)
					toDel.remove(affiliate.id)

					bindData(affiliate, it, [exclude: 'id'])

					if (!affiliate.save()) {
						log.error(affiliate.errors)
						noError = false
					}

				} else {
					def affiliate = new Affiliate()
					bindData(affiliate, it)
					toAdd.add(affiliate)
					if (!affiliate.save()) {
						log.error(affiliate.errors)
						noError = false
					}
				}
			}
			if (noError) {

				log.debug("toDel:" + toDel)
				toDel.keySet().each {
					log.debug("to Delete " + it)
					accountInstance.removeFromAffiliates(exist[it])
					exist[it].delete()
				}
				toAdd.each {
					log.debug("to Add " + it)
					accountInstance.addToAffiliates(it)
				}
				log.debug("affiliates:" + accountInstance.affiliates)
			} else {
				def localErrors = new ValidationErrors(accountInstance)
				localErrors.addError(new FieldError("ru.deloved.Affiliate", "affiliates", message(code: 'account.affiliates.error')))
				accountEditCommandInstance.errors = localErrors
				redirectEdit(accountInstance, accountEditCommandInstance)
				return
			}
		} else if (!accountInstance.affiliates.isEmpty()) {
			def toDel = [:]
			accountInstance.affiliates.each {
				toDel[it.id] = it
			}
			log.debug("toDel:" + toDel)
			toDel.each {
				log.debug("to Delete " + it.key)
				accountInstance.removeFromAffiliates(it.value)
				it.value.delete()
			}
		}

		accountEditCommandInstance.validate()
		if (accountEditCommandInstance.hasErrors()) {
			redirectEdit(accountInstance, accountEditCommandInstance)
			return
		}
		if (user.role.authority == 'ROLE_ACCOUNT') {
			bindData(accountInstance, accountEditCommandInstance, [
					exclude: ['publicStatus', 'verifyStatus', 'showMain'],
					include: ["name", "fullName", "brandName", "orgForm", "regNumber", "inn", "kpp", "legalAddress", "regDate", "phone1", "phone2", "phone3", "fax1", "fax2", "email", "webAddress", "description", "manager", "workTime", "city", "address", "keywords"]
			])
		} else {
			bindData(accountInstance, accountEditCommandInstance)
		}
		def saved

		saved = accountInstance.save flush: true
		log.debug('saved:' + saved)
		log.debug('errors:' + accountInstance.errors)



		if (user.role.authority != 'ROLE_ACCOUNT') {
			if (params.profileid != null && params.profileid != '') {
				log.debug("profileid:" + params.profileid)
				AccountProfile ap = AccountProfile.findByAccount(accountInstance)
				log.debug(accountInstance)
				log.debug(ap)
				log.debug(ap?.id)
				log.debug("ap:" + ap?.profileId)
				if (ap) {
					ap.delete(flush: true)
					ap = new AccountProfile(account: accountInstance, profile: Profile.get(params.profileid))
					ap.save(flush: true)
					log.debug(ap)
					log.debug(ap.errors)
					log.debug("ap:" + ap?.profileId)
				} else {
					new AccountProfile(account: accountInstance, profile: Profile.get(params.profileid)).save(flush: true)
				}
			} else {
				AccountProfile.executeUpdate("delete from AccountProfile where account=?", [accountInstance])
			}
		}

		accountService.renameProfileFio(accountInstance)


		// update account's categories
		log.debug("cat:" + params.cat)
		if (params.cat == null) {
			AccountCategory.executeUpdate('delete AccountCategory where account=?', [accountInstance])
		} else {
			def paramsCatList = []
			params.cat.each {
				paramsCatList.add(it as Long)
			}
			paramsCatList.sort()
			log.debug("AccountController.Update.paramsCatList: "  + paramsCatList)

			def existCatList = AccountCategory.executeQuery("""
				select ac.category.id from AccountCategory ac
				where ac.account = :account
			""", [account: accountInstance])
			existCatList.sort()
			log.debug("AccountController.Update.existCatList: " + existCatList)

			if (!paramsCatList.equals(existCatList)) {

				def catListToAdd = []
				def catListToDel = []

				existCatList.each {
					if (!paramsCatList.contains(it)) {
						catListToDel.add(it)
					}
				}

				log.debug("AccountController.Update.catListToDel: " + catListToDel)

				if (!catListToDel.isEmpty()) {
					AccountCategory.executeUpdate("""
					delete AccountCategory
					where account=:account
					and category.id in (:catListToDel)
				""", [account: accountInstance, catListToDel: catListToDel])
				}

				paramsCatList.each {
					if (!existCatList.contains(it)){
						catListToAdd.add(it)
 					}
				}

				log.debug("AccountController.Update.catListToAdd: " + catListToAdd)
				if (!catListToAdd.isEmpty()) {
					catListToAdd.each {
						new AccountCategory(account: accountInstance, category: Category.load(it)).save(flush: true)
					}
				}
			}
		}



		if (request.xhr) {
			if (saved) {
				response.contentType = "text/javascript;charset=UTF-8"
				def cols = [
						name: accountInstance.name,
						ps  : g.render(template: 'status', plugin: 'deloved-admin', model: [status: accountInstance.publicStatus, statusClass: 'publicStatus', iconFalse: 'glyphicon-lock']).replaceAll("[\\n\\r]", ""),
						vs  : g.render(template: 'status', plugin: 'deloved-admin', model: [status: accountInstance.verifyStatus, statusClass: 'verifyStatus']).replaceAll("[\\n\\r]", ""),
						city: accountInstance.city.name
				]
				render template: '/_common/grid-row-update', model: [gridRowId: accountInstance.id, gridCols: cols, rawCols: [ps: true, vs: true]]
			} else {
				redirectEdit(accountInstance)
			}
		} else {
			request.withFormat {
				'*' {
					flash.message = message(code: saved ? 'default.updated.message' : 'default.notUpdated.message',
							args: [message(code: 'account.label', default: 'Account'), accountInstance.id]
					)
					redirect base: uri,controller: 'account', action: 'index'
				}
				json { respond accountInstance, [status: OK] }
			}
		}


	}

	@Transactional
	def batch(IdListCommand idList) {
		User user = springSecurityService.getCurrentUser()
		if (user.role.authority == 'ROLE_ACCOUNT') {
			accessDenied()
			return
		}

		def cnt = 0
		if (params.batch_action == "batchDelete") {
			if (user.role.authority != 'ROLE_ADMIN') {
				accessDenied()
				return
			}
			delete(idList)
			return
		} else if (params.batch_action == "batchEnable") {
			if (idList?.id?.size() > 0) {
				cnt = User.executeUpdate("update Account u set u.publicStatus = true where u.publicStatus = false and u.id in (:idlist)", [idlist: idList.getList()])
				flash.message = message(code: 'user.batch.enabled.message', args: [cnt])
			}
		} else if (params.batch_action == "batchDisable") {
			if (idList?.id?.size() > 0) {
				cnt = User.executeUpdate("update Account u set u.publicStatus = false where u.publicStatus = true and u.id in (:idlist)", [idlist: idList.getList()])
				flash.message = message(code: 'user.batch.disabled.message', args: [cnt])
			}
		} else if (params.batch_action == "batchSetVerified") {
			if (idList?.id?.size() > 0) {
				cnt = User.executeUpdate("update Account u set u.verifyStatus = true where u.verifyStatus = false and u.id in (:idlist)", [idlist: idList.getList()])
				flash.message = message(code: 'user.batch.verified.message', args: [cnt])
			}
		} else if (params.batch_action == "batchSetUnverified") {
			if (idList?.id?.size() > 0) {
				cnt = User.executeUpdate("update Account u set u.verifyStatus = false where u.verifyStatus = true and u.id in (:idlist)", [idlist: idList.getList()])
				flash.message = message(code: 'user.batch.unverified.message', args: [cnt])
			}
		}
		redirect base: uri,controller: 'account', action: "index", method: "GET"
	}

	@Transactional
	@Secured(["isFullyAuthenticated()"])
	def delete(IdListCommand idList) {
		User user = springSecurityService.getCurrentUser()
		if (user.role.authority != 'ROLE_ADMIN') {
			accessDenied()
			return
		}

		def st = NO_CONTENT
		def mess = null

		log.trace('params:' + params)
		log.trace('idList:' + idList)
		if (idList?.id) {
			if (idList.id.size() == 1) {
				def account = Account.get(idList.id[0])
				if (account) {
					def toDel = []
					toDel.addAll(account.affiliates)
					toDel.each {
						account.removeFromAffiliates(it)
						it.delete flush: true
					}
					AccountProfile.executeUpdate('delete AccountProfile where account=?', [account])
					AccountCategory.executeUpdate('delete AccountCategory where account=?', [account])

					List<AccountStat> accountStatList = AccountStat.findAllByAccount(account)

					account.deleteAll(accountStatList)




					account.delete flush: true
					mess = message(code: 'default.deleted.message', args: [message(code: 'account.label', default: 'Account'), account.name])
				} else {
					notFound()
					return
				}
			} else if (idList.id.size() > 1) {
				int deleted = 0
				idList.getList().each {
					def account = Account.get(it)
					if (account) {
						def toDel = []
						toDel.addAll(account.affiliates)
						toDel.each {
							account.removeFromAffiliates(it)
							it.delete flush: true
						}
						AccountCategory.executeUpdate('delete AccountCategory where account=?', [account])
						account.delete flush: true
						deleted++
					}
				}
				mess = message(code: 'default.deletedMany.message', args: [message(code: 'account.label', default: 'Account'), idList.id, deleted])
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
				redirect base: uri,controller: 'account', action: "index", method: "GET"
			}
			'*' { render status: st }
		}
	}


	@Transactional
	def upload(Account accountInstance) {
		User user = springSecurityService.getCurrentUser()
		if (accountInstance?.id == null) {
			notFound()
			return
		}
		if (user.role.authority == 'ROLE_ACCOUNT' && !accountService.isMyAccount(accountInstance)) {
			accessDenied()
			return
		}

		def files = []
		if (request instanceof MultipartHttpServletRequest) {
			def filename = request.getFileNames().next()
			MultipartFile file = request.getFile(filename)


			if (attachService.uploadImageAndThumbnail(file,
					fileCategoryDirectory,
					user.profile, accountInstance.logo, accountInstance.logoThumb) {
				Attachment thumb, Attachment image ->
					thumb.save(flush: true)
					image.save(flush: true)
					accountInstance.logoThumb = thumb
					accountInstance.logo = image
					accountInstance.save(flush: true)
					return true
			}) {

				files << [
						name        : file.originalFilename,
						size        : file.size,
						url         : g.createLink([controller: 'account', action: 'logo', id: accountInstance.id, params: [name: accountInstance.logo.file, type: 'main']]),
						thumbnailUrl: g.createLink([controller: 'account', action: 'logo', id: accountInstance.id, params: [name: accountInstance.logoThumb.file]]),
						deleteUrl   : createLink(controller: 'account', action: 'deletelogo', id: accountInstance.id),
						deleteType  : "DELETE"
				]
			} else {
				/*files << [
						name : file.originalFilename,
						size : file.size,
						error: 'Error upload'
				]*/
				accessDenied()
				return
			}
		}

		def results = [files: files]
		render results as JSON
	}

	@Transactional
	def crop(Account accountInstance) {
		if (accountInstance?.id == null) {
			notFound()
			return
		}
		User user = springSecurityService.getCurrentUser()
		if (user.role.authority == 'ROLE_ACCOUNT' && !accountService.isMyAccount(accountInstance)) {
			accessDenied()
			return
		}
		log.debug(params)
		if (attachService.cropThumbnail(params, fileCategoryDirectory,
				accountInstance.logo, accountInstance.logoThumb)) {

			def results = [thumb: g.createLink([controller: 'account', action: 'logo', id: accountInstance.id, params: [name: accountInstance.logoThumb.file]])]
			render results as JSON
		} else if (accountInstance.logo == null) {
			render status: NO_CONTENT
		} else {
			render status: BAD_REQUEST
		}
	}

	@Transactional
	def deletelogo(Account accountInstance) {
		User user = springSecurityService.getCurrentUser()

		if (accountInstance?.id == null) {
			def myAccounts = accountService.getMyAccounts()
			if (myAccounts) {
				accountInstance = myAccounts.get(0)
			} else {
				notFound()
				return
			}
		}

		if (user.role.authority == 'ROLE_ACCOUNT' && !accountService.isMyAccount(accountInstance)) {
			accessDenied()
			return
		}
		attachService.deleteImageAndThumbnail(fileCategoryDirectory,
			accountInstance.logo, accountInstance.logoThumb) {
			accountInstance.logo = null
			accountInstance.logoThumb = null
			accountInstance.save(flush: true)
			return true
		}
		def res = []
		render res as JSON
	}

	def logo(Account accountInstance) {
		log.debug("logo params:" + params)
		log.debug("logo profile:" + accountInstance)
		if (accountInstance?.id == null) {
			notFound()
			return
		}
		/*
			User user = springSecurityService.getCurrentUser()
			if (user.role.authority == 'ROLE_ACCOUNT' && !accountService.isMyAccount(accountInstance)) {
				accessDenied()
				return
			}
		*/
		if (accountInstance.logo) {
			Attachment att = Attachment.get(params.type == 'main' ? accountInstance.logo.id : accountInstance.logoThumb.id)
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
				render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Account'), params.id])]
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

	def promo() {

	}
}

class AccountEditCommand {
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
	boolean publicStatus
	boolean verifyStatus
	boolean showMain

	static constraints = {
		name blank: false
		fullName blank: false
		brandName blank: false
		orgForm nullable: false
		regNumber blank: false, minSize: 13, maxSize: 15, matches: '(\\d{13}|\\d{15})'
		inn blank: false, unique: true, minSize: 10, maxSize: 12, matches: '(\\d{10}|\\d{12})'
		kpp nullable: true, blank: true, minSize: 9, maxSize: 9, matches: '\\d{9}'
		legalAddress blank: false
		regDate nullable: false
		phone1 blank: false
		phone2 nullable: true
		phone3 nullable: true
		fax1 nullable: true
		fax2 nullable: true
		webAddress nullable: true
		email nullable: true, email: true
		description nullable: true
		manager blank: false
		workTime blank: false
		city nullable: false
		address nullable: true
		keywords nullable: true
	}
}

class AccountFilter extends FormFilter {
	String name

	@Override
	protected List getFilterParamList() {
		['name']
	}

	@Override
	protected List getSortedParamList() {
		['name', 'orgForm.code', 'chargeStatus', 'city.name', 'dateCreated']
	}

	@Override
	public Map getMap() {
		def m = super.getMap()
		if (name != null) m.name = name;
		return m
	}
}