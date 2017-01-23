package ru.deloved.admin

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.transform.EqualsAndHashCode
import org.apache.commons.lang.StringEscapeUtils
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import org.hibernate.criterion.CriteriaSpecification
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.servlet.support.RequestContextUtils
import pl.touk.excel.export.WebXlsxExporter
import ru.deloved.*

import javax.swing.text.html.HTML

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_ACCOUNT') and isFullyAuthenticated()"])
class ItemController extends FilteredController {
	def accountService
	def attachService
	def springSecurityService
	def profileService
	def itemService
	def categoryService

	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"

	def fileCategoryDirectory = new DefaultGrailsApplication().config?.fileCategory?.item ?: 'item'

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def reset() {
		resetFilter()
		redirect base: uri,controller: 'item', action: 'index', mapping: params.categoryType?.code
	}

	@Override
	protected String getFilterSessionName() {
		return this.getClass().getName() + params.categoryType?.code
	}

	@Override
	protected void redirectIndex() {
		redirect base: uri,controller: 'item', action: 'index', mapping: params.categoryType?.code
	}

	@Override
	protected FormFilter createFilterInstance() {
		return new ItemFilter()
	}

	@Override
	protected PagedResultWrapper getRows(FormFilter filter) {
		ItemFilter f = filter
		User user = springSecurityService.getCurrentUser()
		def regFilter = []
		if (user.role.authority == 'ROLE_MANAGER') {
			regFilter = profileService.findAllCitiesByProfile(user.profile)
		}
		def list = []
		if (user.role.authority == 'ROLE_ACCOUNT') {
			list = accountService.getMyAccounts()
		}
		def rows = Item.createCriteria().list(max: filter.max, offset: filter.offset) {
			eq("categoryType", params.categoryType)
			if (user.role.authority == 'ROLE_ACCOUNT') {
				inList("account", list)
			}
			if (user.role.authority == 'ROLE_MANAGER') {
				createAlias('account', 'account', CriteriaSpecification.LEFT_JOIN)
				inList("account.city", regFilter)
			}
			if (f.avail != null && params.categoryType?.code == 'GOOD') {
				eq("availability", f.avail)
			}
			if (f.priceMin) {
				ge("price", f.priceMin)
			}
			if (f.priceMax) {
				le("price", f.priceMax)
			}
			if (f.search) {
				or {
					ilike("name", "%" + f.search + "%")
					ilike("kind", "%" + f.search + "%")
					ilike("description", "%" + f.search + "%")
				}
			}

			order(filter.sort ?: "dateCreated", filter.order ?: "desc")
		}
		new PagedResultWrapper(rows)
	}

	def index(ItemFilter dealFilter) {
		if (params.categoryType) {
			processIndex(10, dealFilter) {
				[model: [myAccounts: accountService.getMyAccounts()]]
			}
		} else {
			flash.message = "Категория предложений не выбрана."
			flash.status = "warning"
		}
	}
	def index2(ItemFilter dealFilter) {
		if (params.categoryType) {
			processIndex(10, dealFilter) {
				[model: [myAccounts: accountService.getMyAccounts()]]
			}
		} else {
			flash.message = "Категория предложений не выбрана."
			flash.status = "warning"
		}
	}

	def show(Item itemInstance) {
		if (itemInstance?.id == null) {
			notFound()
			return
		}
		respond itemInstance
	}

	def create() {
		User user = springSecurityService.getCurrentUser()
		List<Account> accountList = accountService.getMyAccounts()
		Account acc = accountList.size() == 1 ? accountList.get(0) : null;

		def count = categoryService.getAccountCategories(acc).count {it.type.code == params.categoryType.code}
		log.debug('categoryType: ' + params.categoryType + ' Count: ' + count)
		if (count == 0) {


			redirect(base: uri,controller: 'item', action: 'index2', mapping: params.categoryType?.code)
			return
		}

		render view: 'create', model: [
				requestLocale: RequestContextUtils.getLocale(request),
				accountList: accountList,
				itemInstance: new Item(account: acc, categoryType: params.categoryType)
		]

	}



	@Transactional
	def save(Item itemInstance) {

		def itemAttaches = []

		log.debug('ItemController.save.params: ' + params)

		User user = springSecurityService.getCurrentUser()

		if (itemInstance == null) {
			notFound()
			return
		}

		if (params.itemAttach) {
			def paramsItemAttaches = []
			if (params.itemAttach instanceof String) {
				paramsItemAttaches = [params.itemAttach]
			} else {
				paramsItemAttaches = params.itemAttach
			}
			paramsItemAttaches.each {
				itemAttaches.push(ItemAttach.load(it))
			}
		}

		itemInstance.validate()

		if(itemInstance.hasErrors()) {
			itemInstance.errors.each{
				log.debug(it)
			}
			render view: 'create', model: [
					requestLocale: RequestContextUtils.getLocale(request),
					//accountList: accountList,
					//errorInstance: itemInstance.errors,
					itemInstance: itemInstance,
					attachList: itemAttaches
			]
			return
		} else {
			itemAttaches.each {
				itemInstance.addToPhotos(it)
			}

			itemInstance.save flush: true

			request.withFormat {
				form {
					flash.message = message(code: 'item.new.'+itemInstance.categoryType.code, args: [itemInstance.name])
					flash.status = 'success'
					redirect base: uri,controller: 'item', action: 'index', mapping: itemInstance.categoryType.code
				}
				'*' { respond itemInstance, [status: CREATED] }
			}
		}
	}

	protected redirectEdit(Item beanResource, objInstance = null) {
		if (!itemService.isEditAllowed(beanResource)) {
			accessDenied()
			return
		}
		Locale requestLocale = RequestContextUtils.getLocale(request)
		log.debug("requestLocale:" + requestLocale)


		List<Account> accountList = accountService.getMyAccounts()

		log.debug(request.xhr)

		render view: request.xhr ? 'editform' : 'edit',

				model: [
						beanResource : beanResource,
						objInstance  : objInstance ?: beanResource,
						accountList  : accountList,
						attachList   : beanResource.photos,
						requestLocale: requestLocale
				]
	}

	def edit(Item itemInstance) {
		redirectEdit(itemInstance)
	}

	@Transactional
	def update(Item itemInstance) {
		if (itemInstance?.id == null) {
			notFound()
			return
		}
		if (!itemService.isEditAllowed(itemInstance)) {
			accessDenied()
			return
		}

		if (itemInstance.hasErrors()) {
			log.error("has errors:" + itemInstance.errors)
			redirectEdit(itemInstance)
			return
		}

		def saved = itemInstance.save flush: true

		if (request.xhr) {
			if (saved) {
				response.contentType = "text/javascript;charset=UTF-8"
				def cols = [
						name        : itemInstance.name,
						price       : formatNumber(number: itemInstance.price, type: 'currency', currencyCode: (itemInstance.currency?.code) ?: 'RUB'),
						availability: message(code: 'item.filter.avail.' + itemInstance.availability, default: itemInstance.availability),
						account     : itemInstance.account.name
				]
				render template: '/_common/grid-row-update', model: [gridRowId: itemInstance.id, gridCols: cols]
			} else {
				redirectEdit(itemInstance)
			}
		} else {
			request.withFormat {
				'*' {
					flash.message = message(code: saved ? 'default.updated.message' : 'default.notUpdated.message',
							args: [message(code:'')]
					)
					redirect base: uri,controller: 'item', action: 'index', mapping: itemInstance.categoryType.code
				}
				json { respond itemInstance, [status: OK] }
			}
		}
	}


	def accounts(String term) {
		User user = springSecurityService.getCurrentUser()
		def regFilter = []
		if (user.role.authority == 'ROLE_MANAGER') {
			regFilter = profileService.findAllCitiesByProfile(user.profile)
		}
		def list = []
		if (user.role.authority in ['ROLE_ADMIN', 'ROLE_MANAGER']) {
			list = Account.createCriteria().list(max: 5) {
				or {
					ilike("name", "%" + term + "%")
					ilike("fullName", "%" + term + "%")
					ilike("brandName", "%" + term + "%")
				}
				if (user.role.authority == 'ROLE_MANAGER') {
					inList("city", regFilter)
				}
			}
		}
		def res = []
		list.each { it ->
			res << [label: it.name, value: it.name, id: it.id]
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

	//v2
	def cat(Long categoryId, Long pid, Long accountId) {

		log.debug("ItemController.Cat.categoryId: " + categoryId)
		log.debug("ItemController.Cat.pid: " + pid)
		log.debug("ItemController.Cat.accountId: " + accountId)



		if (pid == null || accountId == null) {
			render(status: BAD_REQUEST, contentType: 'application/json') {
				[error: 1]
			}
			return
		} else {
			def res = []
			def catParent = Category.get(pid)
			def account = Account.get(accountId)
			def catIds = []
			def undetermCatIds = []

			Category category = null
			if (categoryId) {
				category = Category.load(categoryId)
			}

			List<Category> accCatList = categoryService.getAccountCategories(account)

			def accountCats = AccountCategory.findAllByAccount(account)
			accountCats.each {
				catIds.add(it.category.id)
				loadParentsCategory(it.category, undetermCatIds)
			}
			if (category?.id != null) {
				loadParentsCategory(category, undetermCatIds)
			}
			def childsClos
			childsClos = { Category cat, List<Category> _accCatList, List obj ->
				def list = categoryService.getChilds(cat).findAll { it in _accCatList }
				list.each {
					def el = [
							id  : it.id,
							text: it.name
					]
					def state = [:]
					if (categoryService.getChilds(it).size() > 0) {
						def children = []
						childsClos(it, _accCatList, children)
						el.putAt('children', children)
						state.putAt('opened', (it.id in undetermCatIds) && !(it.id in catIds))
					}
					if (category?.id != null) {
						state.putAt('selected', (it.id == category.id))
					}
					if (!state.isEmpty()) {
						el.putAt('state', state)
					}
					obj << el
				}
				return
			}
			if (accountCats.isEmpty()) {
				def el = [
						id   : 0,
						text : "Список категорий участника пуст",
						state: [disabled: true]
				]
				res << el;
			}

			childsClos(catParent, accCatList, res)

			log.debug('ItemController.Cat.res: ' + res)
			render(status: OK, contentType: 'application/json') {
				res
			}
		}
	}


	@Transactional
	def delete(Item itemInstance) {

		if (itemInstance?.id == null) {
			notFound()
			return
		}

		if (!itemService.isEditAllowed(itemInstance)) {
			accessDenied()
			return
		}

		/**
		 * Просто отвязываем от товара/услуги (Item) изображения (ItemAttach)
		 * CleaningItemAttachJob сам подчистит и записи в БД и файлы аттачей без "хозяина"
		 */

		itemInstance.photos.each {
			it.item = null
		}
		itemInstance.photo = null



		itemInstance.delete(flush: true  )

		request.withFormat {
			form {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'Item.label', default: 'Item'), itemInstance.id])
				redirect base: uri,controller: 'item', action: 'index', method: "GET", mapping: itemInstance.categoryType.code
			}
			'*' { render status: NO_CONTENT }
		}


	}



	@Transactional
	def upload() {

		log.debug('ItemController.upload.params: ' + params + ' Flash scope: ')

		log.debug("itemController.fileCategoryDirectory: " + fileCategoryDirectory)
		Item itemInstance
		if (params.id != null) {
			itemInstance = Item.lock(params.id)
		} else {
			itemInstance = new Item()
		}
		log.debug("itemInstance:" + itemInstance)

		User user = springSecurityService.getCurrentUser()
		def files = []
		boolean uploadAllowed = itemService.isUploadAllowed()
		boolean editAllowed = itemService.isEditAllowed(itemInstance)
		if (request instanceof MultipartHttpServletRequest) {
			for (filename in request.getFileNames()) {
				MultipartFile file = request.getFile(filename)


				if (uploadAllowed) {

					ItemAttach itemAtt = new ItemAttach(owner: user.profile)
					if (itemInstance?.id) {
						if (!editAllowed) {
							files << [
									name : file.originalFilename,
									size : file.size,
									error: "You can't upload. You have no permission to item ${itemInstance.id}"
							]
							continue
						}
					}


					log.debug("file.originalFilename: "  + file.originalFilename)

					if (attachService.uploadImageAndThumbnail(file, fileCategoryDirectory, user.profile, itemAtt.image, itemAtt.imageThumb) {
						Attachment thumb, Attachment image ->
							thumb.save(flush: true)
							image.save(flush: true)
							itemAtt.image = image
							itemAtt.imageThumb = thumb
							return true
					}) {
						itemAtt.save(flush: true)
						if (itemInstance?.id) {
							if (itemInstance.photo == null) {
								itemInstance.photo = itemAtt
							}
							itemInstance.addToPhotos(itemAtt)
						}
						files << [
								name        : file.originalFilename,
								size        : file.size,
								url         : g.createLink([controller: 'item', action: 'image', id: itemAtt.id, params: [name: itemAtt.image.file, type: 'main']]),
								thumbnailUrl: g.createLink([controller: 'item', action: 'image', id: itemAtt.id, params: [name: itemAtt.imageThumb.file]]),
								deleteUrl   : createLink(controller: 'item', action: 'deleteimage', id: itemAtt.id),
								deleteType  : "DELETE",
								cropUrl     : createLink(controller: 'item', action: 'crop', id: itemAtt.id),
								id          : itemAtt.id
						]
					} else {
						files << [
								name : file.originalFilename,
								size : file.size,
								error: 'Error upload'
						]
					}
				} else {
					files << [
							name : file.originalFilename,
							size : file.size,
							error: 'You can\'t upload'
					]
				}
			}
		}
		if (itemInstance?.id != null) {
			itemInstance.save(flush: true)
		}
		def results = [files: files]
		render results as JSON
	}

	@Transactional
	def crop(ItemAttach itemAttachInstance) {
		if (itemAttachInstance?.id == null) {
			notFound()
			return
		}

		//TODO обрабатывает тот случай, когда обрезать картинку мы хотим, а товара или услуга к ней еще не привязаны, тупо и с повтором кода, на первое время сойдет
		if (itemAttachInstance.item == null) {
			log.debug("Delete Attach without Item")
			if (itemAttachInstance.owner == springSecurityService.getCurrentUser()?.profile) {
				if (attachService.cropThumbnail(params, fileCategoryDirectory,
						itemAttachInstance.image, itemAttachInstance.imageThumb)) {

					def results = [thumb: g.createLink([action: 'image', id: itemAttachInstance.id, params: [name: itemAttachInstance.imageThumb.file]])]
					render results as JSON
					return
				} else if (itemAttachInstance.image == null) {
					render status: NO_CONTENT
					return
				} else {
					render status: BAD_REQUEST
					return
				}
			}

			accessDenied()
			return
		}
		//TODO конец блока

		if (!itemService.isEditAllowed(itemAttachInstance.item)) {
			accessDenied()
			return
		}

		if (attachService.cropThumbnail(params, fileCategoryDirectory,
				itemAttachInstance.image, itemAttachInstance.imageThumb)) {

			def results = [thumb: g.createLink([action: 'image', id: itemAttachInstance.id, params: [name: itemAttachInstance.imageThumb.file]])]
			render results as JSON
		} else if (itemAttachInstance.image == null) {
			render status: NO_CONTENT
		} else {
			render status: BAD_REQUEST
		}
	}

	@Transactional
	def deleteimage(ItemAttach itemAttachInstance) {

//		log.debug('PARAMS=' + params + 'itemAttachInstance=' + itemAttachInstance + ', itemAccount=' + itemAttachInstance.owner)
//		log.debug('ITEM=' + itemAttachInstance.item)

		if (itemAttachInstance?.id == null) {
			notFound()
			return
		}

		/*
		if (itemAttachInstance.item == null) {
			def ap = AccountProfile.findByProfile(springSecurityService.getCurrentUser()?.profile)
			itemAttachInstance.item = new Item(account: ap.account)

			log.debug('1account of itemAttachInstance ' + itemAttachInstance.item.account)
			log.debug('ITEM ' + itemAttachInstance.item)

		}
		*/

		//TODO обрабатывает тот случай, когда удалить картинку мы хотим, а товара или услуга к ней еще не привязаны, тупо и с повтором кода, на первое время сойдет
		if (itemAttachInstance.item == null) {
			log.debug("Delete Attach without Item")
			if (itemAttachInstance.owner == springSecurityService.getCurrentUser()?.profile) {
				itemAttachInstance.delete(flush: true)
				def res = []
				render res as JSON
				return
			}

			accessDenied()
			return
		}
		//TODO конец блока 

		if (!itemService.isEditAllowed(itemAttachInstance.item)) {
			accessDenied()
			return
		}

		attachService.deleteImageAndThumbnail(fileCategoryDirectory, itemAttachInstance.image, itemAttachInstance.imageThumb) {
			Item item = itemAttachInstance.item
			if (item?.photo == itemAttachInstance) {
				item.removeFromPhotos(itemAttachInstance)
				item.photo = item.photos.isEmpty() ? null : item.photos.first()
				item.save(flush: true)
			}
			itemAttachInstance.delete(flush: true)
			return true
		}

		def res = []
		render res as JSON
	}

	def image(ItemAttach itemAttachInstance) {
		if (itemAttachInstance?.id == null) {
			notFound()
			return
		}

		if (itemAttachInstance.image) {
			Attachment att = Attachment.get(params.type == 'main' ? itemAttachInstance.image.id : itemAttachInstance.imageThumb.id)
			if (att && att.file == params.name) {
				attachService.sendFile(fileCategoryDirectory, att, response)
				return null
			}
		}
		render status: NO_CONTENT
		return null
	}

	def export() {

		User user = springSecurityService.getCurrentUser()
		if (user.role.authority == 'ROLE_ACCOUNT') {


			def headers = [
					'ID',
					message(code: 'item.name.label'),
					message(code: 'item.price.label'),
					message(code: 'item.description.label'),
					message(code: 'item.availability.label'),
					message(code: 'item.category.label')
			]
			def withProperties = [
					'id',
					'name',
					'price',
					'description',
					'availability',
					'category.name'
			]

			def list = accountService.getMyAccounts()
			CategoryType categoryType = params.categoryType
			List<Item> products = Item.findAllByAccountInListAndCategoryType(list, categoryType, [sort: 'name'])

			def filename = categoryType.code + "_" + new Date().format('yyyy-MM-dd_hh-mm-ss') + ".xlsx";
			response.setHeader("Content-disposition", "attachment; filename=$filename;")
			response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")

			new WebXlsxExporter().with {
//				setResponseHeaders(response, '')
				fillHeader(headers)
				add(products, withProperties)
				save(response.outputStream)
			}
		} else {
			render status: FORBIDDEN
		}
	}

	def "import"() {
		User user = springSecurityService.getCurrentUser()
		if (user.role.authority == 'ROLE_ACCOUNT') {

			def files = []
			if (request instanceof MultipartHttpServletRequest) {
				for (filename in request.getFileNames()) {
					MultipartFile file = request.getFile(filename)

					// Например доступно только для PRO-подписки
					def uploadAllowed = true

					if (uploadAllowed) {

						int count = 0
						def list = accountService.getMyAccounts()
						CategoryType categoryType = params.categoryType
						List items = new ItemExcelImporter(file.inputStream).getItems()
						items.each { Map it ->

							if (it.get('id')) {
								log.debug(it)
								Item item = Item.findByIdAndAccountInListAndCategoryType(it.get('id') as Long, list, categoryType)
								if (item) {
									bindData(item, it, [include: [
											'name',
											'price',
											'description'
									]])
									item.availability = it.get('availability') == 1 ? 1 : 0
									if (item.isDirty('name') ||
											item.isDirty('price') ||
											item.isDirty('description') ||
											item.isDirty('availability')) {

										if (item.save(flush: true)) {
											count++
										}
									}
								}
							}
						}
						flash.message = "Обновлено $count объектов"

						files << [
								name: file.originalFilename,
								size: file.size
						]

					} else {
						flash.message = "Функционал не доступен"
						files << [
								name : file.originalFilename,
								size : file.size,
								error: 'You can\'t upload'
						]
					}
				}
			}
			def results = [files: files]
			render results as JSON
		} else {
			render status: FORBIDDEN
		}

	}

	protected void notFound() {
		request.withFormat {
			'*' {
				render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'Item'), params.id])]
			}
			multipartForm { render status: NOT_FOUND }
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

@EqualsAndHashCode
class ItemFilter extends FormFilter {
	String search
	Integer avail
	BigDecimal priceMin
	BigDecimal priceMax

	@Override
	protected List getFilterParamList() {
		['search', 'avail', 'priceMin', 'priceMax']
	}

	@Override
	protected List getSortedParamList() {
		['search', "dateCreated", "price", "name"]
	}

	@Override
	public Map getMap() {
		def m = super.getMap()
		if (search != null) m.search = search
		if (avail != null) m.avail = avail
		if (priceMin != null) m.priceMin = priceMin
		if (priceMax != null) m.priceMax = priceMax
		return m
	}
}