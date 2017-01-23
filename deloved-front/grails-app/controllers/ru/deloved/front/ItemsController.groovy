package ru.deloved.front

import grails.plugin.springsecurity.annotation.Secured
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import org.grails.datastore.mapping.query.api.BuildableCriteria
import ru.deloved.*

import static org.springframework.http.HttpStatus.NOT_FOUND

@Secured(["permitAll"])
class ItemsController extends FilteredController {
	def frontService
	def accountService


	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"

	@Override
	protected String getFilterSessionName() {
		return "Front"
	}

	@Override
	protected FormFilter createFilterInstance() {
		return new FrontFilter()
	}

	@Override
	protected void redirectIndex() {
		redirect base: uri,  action: 'index', mapping: params.categoryType?.code + 'S'
	}

	@Override
	protected PagedResultWrapper getRows(FormFilter f) {
		FrontFilter filter = f
		def catFilter
//		log.debug("catFilter:" + catFilter)
		def company = null
		if (params.company) {
			company = Account.load(params.company as Long)
		}
		if (company) {
			catFilter = frontService.getCategoryFilter(company, params.acategory as Long)
			def rows = Item.createCriteria().list(max: filter.max, offset: filter.offset) {
				eq("account", company)
				if (catFilter) {
					inList('category', catFilter)
				}
				order(filter.sort ?: "name", filter.order ?: "asc")
			}
			new PagedResultWrapper(rows)
		} else {
			catFilter = frontService.getCategoryFilter(filter.category2[params.categoryType.code])
			def regFilter = frontService.getRegionFilter(filter.region)

			if (catFilter) {
				def rows = Item.createCriteria().list(max: filter.max, offset: filter.offset) {
					createAlias('account', 'account')
					if (regFilter) {
						inList("account.city", regFilter)
					}
					inList('category', catFilter)
					order(filter.sort ?: "name", filter.order ?: "asc")
				}
				new PagedResultWrapper(rows)
			} else {
				def rows = []
				new PagedResultWrapper(rows, 0)
			}
		}

	}

	def index(FrontFilter indexFilter) {

		log.debug('ItemsController.index.params: '+params)

		if (params.categoryType) {
			def mainGoods = Item.findAllByShowMainAndCategoryType(true, CategoryType.findByCode('GOOD'), [max: 5555])
			def mainServices = Item.findAllByShowMainAndCategoryType(true, CategoryType.findByCode('SERVICE'), [max: 5555])
			def max = 20
			def maxLastCreated = 200000
			FrontFilter filter = getFilter(max, indexFilter)

			Account acc = null
			def categoryFilterData
			def regionFilterData
			def lastCreated = []
			if (params.company) {
				acc = Account.get(params.company)
			}
			if (acc) {
				categoryFilterData = frontService.getCategoryFilterListData(acc, params.acategory as Long, params.categoryType)
				regionFilterData = null
			} else {
				Long cat = params.category as Long
				if (cat == 0) {
					filter.category2[params.categoryType.code] = null
				} else if (cat != null) {
					filter.category2[params.categoryType.code] = cat
				}

				categoryFilterData = frontService.getCategoryFilterListData(filter.category2[params.categoryType.code], params.categoryType)
				regionFilterData = frontService.getRegionFilterData(filter.region)

				def catFilter = frontService.getCategoryFilter(filter.category2[params.categoryType.code])

				log.debug("ItemsController.index.catFilter: " + catFilter)

				def regFilter = frontService.getRegionFilter(filter.region)

				log.debug("ItemsController.index.regFilter: " + regFilter)


					lastCreated = Item.createCriteria().list(max:maxLastCreated) {
						createAlias('account', 'account')
						if (regFilter) {
							inList("account.city", regFilter)
						}
						if (catFilter) {
							inList('category', catFilter)
						}
						order("dateCreated", "desc")
					}


					log.debug('ItemsController.index.lastCreated: ' + lastCreated)


			}
			processIndex(max, filter) {
				[model: [
						mainServices   : mainServices,
						mainGoods   : mainGoods,
						account           : acc,
						lastCreated       : lastCreated,
						categoryFilterData: categoryFilterData,
						regionFilterData  : regionFilterData
				]]
			}

		} else {
			render plugin: 'deloved-front', view: "/notfound", status: 404, model: [message: message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'Item'), params.id])]
			return

		}
	}


	@Secured('permitAll')
	def item(Item itemInstance) {

		if (itemInstance?.id == null) {
			render plugin: 'deloved-front', view: "/notfound", status: 404, model: [message: message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'Item'), params.id])]
			return
		}

		switch (itemInstance.categoryType.code) {
			case 'GOOD':
				accountService.updateStatViewGoods(itemInstance.account)
				break;
			case 'SERVICE':
				accountService.updateStatViewServices(itemInstance.account)
				break;
		}

		def categoryFilterData = frontService.getCategoryFilterListData(itemInstance.category.id, null)
		log.debug(categoryFilterData)

		def attachList = []

		if (itemInstance.photo) {
			attachList = ItemAttach.executeQuery("""
				select attach
				from ItemAttach attach
				where attach.item = :item
				and attach != :photo
				""", [item: itemInstance, photo: itemInstance.photo])
		}

		log.debug('attachList: ' + attachList)



		def itemList = Item.executeQuery("""
			select item
			from Item item
			where item.id != :id
			and item.categoryType = :type
			""", [id: itemInstance.id, type: itemInstance.categoryType])
		log.debug('itemList: ' + itemList)

		def isMyAccount = false
		if (springSecurityService.isLoggedIn()) {
			User user = springSecurityService.getCurrentUser()
		    isMyAccount = AccountProfile.findByAccountAndProfile(itemInstance.account, user.profile) != null
		}
		log.debug('isMyAccount: ' + isMyAccount)

		render(view: 'item', model: [
		        itemInstance: itemInstance,
				attachList: attachList,
				itemList: itemList,
				categoryFilterData: categoryFilterData,
				isMyAccount: isMyAccount
		])

	}


	protected PagedResultWrapper getSearchRows(ItemSearchFilter filter, CategoryType categoryType) {
		def max = 10000

		filter.max = Math.min(filter.max ?: max, 10000)
		filter.offset = filter.offset ?: 0
		params.max = filter.max
		def catFilter = frontService.getCategoryFilter(filter.cat)
		def regFilter = frontService.getRegionFilter(filter.reg)
    	BuildableCriteria cr = Item.createCriteria()
		BuildableCriteria cr2 = Item.createCriteria()

		def words = []

		if (filter.search) {
			words.add(filter.search)
//			filter.search.split(' ').split { w ->
//				if (!w.isEmpty()) {
//					words.add(w)
//				}
//			}
		}
		log.debug('search words:' + words)

		if (!words.isEmpty()) {
			def rows = cr.list(max: filter.max, offset: filter.offset) {
				createAlias('account', 'account')
				if (regFilter) {
					inList("account.city", regFilter)
				}
				if (catFilter) {
					inList('category', catFilter)
				}
				or {
					words.each {
						ilike "name", "%" + it + "%"
						ilike "kind", "%" + it + "%"
						ilike "description", "%" + it + "%"
					}
				}
				if (filter.priceMin) {
					ge("price", filter.priceMin)
				}
				if (filter.priceMax) {
					le("price", filter.priceMax)
				}
				eq('categoryType', categoryType)
				eq('account.publicStatus', true)
				order(filter.sort ?: "name", filter.order ?: "asc")
			}
			new PagedResultWrapper(rows)
		} else {
			def rows = []
			new PagedResultWrapper(rows, 0)
		}

	}

	def search(ItemSearchFilter searchFilter) {

		List rows = []
		def resultWrapper = null

		def categoryFilterData = frontService.getCategoryFilterData(null, params.categoryType)
		def regionFilterData = frontService.getRegionFilterData(null)
		log.debug('searchFilter:' + searchFilter)

		if (request.method == 'POST') {

			resultWrapper = getSearchRows(searchFilter, params.categoryType)
			rows = resultWrapper.resultList;

			while (rows.isEmpty() && searchFilter.offset > 0) {
				log.debug("no data. Go prev page")
				searchFilter.offset = 0;
				resultWrapper = getSearchRows(searchFilter)
				rows = resultWrapper.resultList
			}

		}
		def respondModel = [
				rowsCount         : resultWrapper ? resultWrapper.totalCount : 0,
				filter            : searchFilter.getMap(),
				categoryFilterData: categoryFilterData,
				regionFilterData  : regionFilterData
		]
		respond view: 'search', rows, model: respondModel
	}

}

class ItemSearchFilter extends FormFilter {
	String search
	String regl
	String catl
	List<Long> reg = []
	List<Long> cat = []
	BigDecimal priceMin
	BigDecimal priceMax

	@Override
	protected List getFilterParamList() {
		['search', 'reg', 'cat', 'priceMin', 'priceMax']
	}

	@Override
	protected List getSortedParamList() {
		[]
	}

	void setRegl(String regl) {
		this.regl = regl
		reg = []
		regl.split(',').each {
			reg.add(it as Long)
		}
	}

	void setCatl(String catl) {
		this.catl = catl
		cat = []
		catl.split(',').each {
			cat.add(it as Long)
		}
	}

	@Override
	Map getMap() {
		def m = super.getMap()
		if (search != null) m.search = search;
		if (priceMin != null) m.priceMin = priceMin
		if (priceMax != null) m.priceMax = priceMax
		m.regl = regl;
		m.catl = catl;
		m.reg = reg;
		m.cat = cat;
		return m
	}

	@Override
	public String toString() {
		return "ItemSearchFilter{" +
				"search='" + search + '\'' +
				", regl='" + regl + '\'' +
				", reg=" + reg +
				", catl='" + catl + '\'' +
				", cat=" + cat +
				", min=" + priceMin +
				", max=" + priceMax +
				'}';
	}
}