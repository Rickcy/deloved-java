package ru.deloved.front

import grails.plugin.springsecurity.annotation.Secured
import org.grails.datastore.mapping.query.api.BuildableCriteria
import ru.deloved.*

import static org.springframework.http.HttpStatus.NOT_FOUND

@Secured(["permitAll"])
class CompaniesController extends FilteredController {
	def frontService

	@Override
	protected String getFilterSessionName() {
		return "Front"
	}

	@Override
	protected FormFilter createFilterInstance() {
		return new FrontFilter()
	}

	@Override
	protected PagedResultWrapper getRows(FormFilter f) {
		FrontFilter filter = f

		def catFilter = frontService.getCategoryFilter(filter.category1)
		def regFilter = frontService.getRegionFilter(filter.region)

		if (catFilter) {
			BuildableCriteria cr = AccountCategory.createCriteria()
			BuildableCriteria cr2 = AccountCategory.createCriteria()
			def rows = cr.listDistinct {
				createAlias('account', 'account')
				if (regFilter) {
					inList("account.city", regFilter)
				}
				projections {
					groupProperty('account')
//					groupProperty('account.' + filter.sort ?: "account.name")
				}
				maxResults(filter.max)
				firstResult(filter.offset)
				eq('account.publicStatus', true)
				inList('category', catFilter)
//				order('account.' + filter.sort ?: "account.name", filter.order ?: "asc")
			}
			def total = cr2.get {
				createAlias('account', 'account')
				if (regFilter) {
					inList("account.city", regFilter)
				}
				projections {
					countDistinct('account')
				}
				eq('account.publicStatus', true)
				inList('category', catFilter)
			}
			new PagedResultWrapper(rows, total)
		} else {
			def rows = []
			new PagedResultWrapper(rows, 0)
		}
	}

	def index(FrontFilter indexFilter,Account accountInstance) {

		def reviews = Review.findAllByToAndPublishedAndFinished(accountInstance, true, true)

		def mainAccounts = Account.findAllByShowMainAndPublicStatus(true, true, [max: 5555])
		def max = 20
		def maxLastCreated = 50
		FrontFilter filter = getFilter(max, indexFilter)

		Long cat = params.category as Long
		if (cat == 0) {
			filter.category1 = null
		} else if (cat != null) {
			filter.category1 = cat
		}

		def categoryFilterData = frontService.getCategoryFilterListData(filter.category1, null)
		def regionFilterData = frontService.getRegionFilterData(filter.region)

		def lastCreated = []
		def catFilter = frontService.getCategoryFilter(filter.category1)
		def regFilter = frontService.getRegionFilter(filter.region)
		if (catFilter) {
			BuildableCriteria cr = AccountCategory.createCriteria()
			lastCreated = cr.listDistinct {
				createAlias('account', 'account')
				if (regFilter) {
					inList("account.city", regFilter)
				}
				projections {
					groupProperty('account.dateCreated')
					groupProperty('account')
				}
				maxResults(maxLastCreated)
				eq('account.publicStatus', true)
				inList('category', catFilter)
				order("account.dateCreated", "asc")
			}
		} else {
			BuildableCriteria cr = Account.createCriteria()
			lastCreated = cr.list(max: maxLastCreated) {
				if (regFilter) {
					inList("city", regFilter)
				}
				eq('publicStatus', true)
				order("rating", "asc")
			}
		}

//		log.debug("lastCreated: " + lastCreated)
		processIndex(max, filter) {
			[model: [
					mainAccounts: mainAccounts,
					lastCreated       : lastCreated,
					categoryFilterData: categoryFilterData,
					regionFilterData  : regionFilterData,

			]]
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

	protected void notFound() {
		render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'company.label'), params.id])]
	}
	protected PagedResultWrapper getSearchRows(CompanySearchFilter filter) {
		def max = 100000

		filter.max = Math.min(filter.max ?: max, 100000)
		filter.offset = filter.offset ?: 0
		params.max = filter.max
		def catFilter = frontService.getCategoryFilter(filter.cat)
		def regFilter = frontService.getRegionFilter(filter.reg)

		log.debug("regFilter:" + regFilter)
		log.debug("catFilter:" + catFilter)

		BuildableCriteria cr = AccountCategory.createCriteria()
		BuildableCriteria cr2 = AccountCategory.createCriteria()

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
			def rows = cr.listDistinct {
				createAlias('account', 'account')
				or {
					words.each {
						ilike "account.name", "%" + it + "%"
						ilike "account.fullName", "%" + it + "%"
						ilike "account.brandName", "%" + it + "%"


						ilike "account.regNumber", "%" + it + "%"
						ilike "account.inn", "%" + it + "%"
						ilike "account.kpp", "%" + it + "%"

					}
				}
				if (regFilter) {
					inList("account.city", regFilter)
				}
				projections {
					groupProperty('account')
				}
				maxResults(filter.max)
				firstResult(filter.offset)
				eq('account.publicStatus', true)
				if (catFilter) {
					inList('category', catFilter)
				}
				//order('account.' + filter.sort ?: "account.name", filter.order ?: "asc")
			}
			def total = cr2.get {
				createAlias('account', 'account')
				or {
					words.each {
						ilike "account.name", "%" + it + "%"
						ilike "account.fullName", "%" + it + "%"
						ilike "account.brandName", "%" + it + "%"

						ilike "account.regNumber", "%" + it + "%"
						ilike "account.inn", "%" + it + "%"
						ilike "account.kpp", "%" + it + "%"

					}
				}
				if (regFilter) {
					inList("account.city", regFilter)
				}
				projections {
					countDistinct('account')
				}
				eq('account.publicStatus', true)
				if (catFilter) {
					inList('category', catFilter)
				}
			}
			new PagedResultWrapper(rows, total)
		} else {
			def rows = []
			new PagedResultWrapper(rows, 0)
		}

	}


	def search(CompanySearchFilter searchFilter) {

		List rows = []
		def resultWrapper = null

		def categoryFilterData = frontService.getCategoryFilterData(null, null)
		def regionFilterData = frontService.getRegionFilterData(null)
		log.debug('searchFilter:' + searchFilter)

		if (request.method == 'POST') {

			resultWrapper = getSearchRows(searchFilter)
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
		respond rows, model: respondModel
	}

}

class CompanySearchFilter extends FormFilter {
	String search
	String regl
	String catl
	List<Long> reg = []
	List<Long> cat = []

	@Override
	protected List getFilterParamList() {
		['search', 'reg', 'cat']
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
		m.regl = regl;
		m.catl = catl;
		m.reg = reg;
		m.cat = cat;
		return m
	}

	@Override
	public String toString() {
		return "CompanySearchFilter{" +
				"search='" + search + '\'' +
				", regl='" + regl + '\'' +
				", reg=" + reg +
				", catl='" + catl + '\'' +
				", cat=" + cat +
				'}';
	}
}