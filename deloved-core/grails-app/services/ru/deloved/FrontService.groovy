package ru.deloved

import grails.transaction.Transactional

@Transactional
class FrontService {
	def regionService
	def categoryService

	private void loadParentsIdName(Category el, ArrayList parents) {
		if (el) {
			if (el.parent) {
				parents.add([id: el.id, name: el.name])
				loadParentsIdName(el.parent, parents)
			}
		}
	}

	def getCategoryFilterListData(Long categoryId, CategoryType categoryType) {
		def categories = []
		def subcategories = [:]
		def level = null
		def parentsList = []

		Category category = null
		if (categoryId) {
			category = categoryService.getCategory(categoryId)
		}
		if (category) {
			level = category
			loadParentsIdName(category, parentsList)

			categoryService.getChilds(category).each {
				categories.add(it)
			}
			if (categories.isEmpty()) {
				categoryService.getChilds(category.parent).each {
					categories.add(it)
				}
				level = category.parent
			}
		} else {
			def topCat
			if (categoryType) {
				topCat = categoryService.getParentsCategories().findAll { it.type.id == categoryType.id }
			} else {
				topCat = categoryService.getParentsCategories()
			}
			topCat.each { pit ->
				categoryService.getChilds(pit).each {
					categories.add(it)
					def l = []
					categoryService.getChilds(it).each { chit ->
						l.add(chit)
					}
					subcategories.put(it, l)
				}
			}
			categories.sort { Category r1, Category r2 ->
				r1.name.compareTo(r2.name)
			}
		}

		[
				company       : null,
				categories    : categories,
				subcategories : subcategories,
				level         : level,
				category      : category,
				breadcrumbList: parentsList.reverse()
		]
	}

	def getCategoryFilterListData(Account account, Long categoryId, CategoryType categoryType) {
		def categories = []
		def subcategories = [:]
		def level = null
		def parentsList = []

		List<Category> accCatList = categoryService.getAccountCategories(account)
		log.debug("accCatList:" + accCatList)

		Category category = null
		if (categoryId) {
			category = categoryService.getCategory(categoryId)
		}
		if (category && category in accCatList) {
			level = category
			loadParentsIdName(category, parentsList)

			categoryService.getChilds(category).findAll { it in accCatList }.each {
				categories.add(it)
			}
			if (categories.isEmpty()) {
				categoryService.getChilds(category.parent).findAll { it in accCatList }.each {
					categories.add(it)
				}
				level = category.parent
			}
		} else {
			def topCat
			if (categoryType) {
				topCat = categoryService.getParentsCategories().findAll { it.type.id == categoryType.id }
			} else {
				topCat = categoryService.getParentsCategories()
			}
			topCat.each { pit ->
				log.debug("pit:" + pit)
				categoryService.getChilds(pit).findAll {
					log.debug("it:" + it)
					it in accCatList
				}.each {
					categories.add(it)
					def l = []
					categoryService.getChilds(it).findAll { it in accCatList }.each { chit ->
						l.add(chit)
					}
					subcategories.put(it, l)
				}
			}
			categories.sort { Category r1, Category r2 ->
				r1.name.compareTo(r2.name)
			}
		}

		[
				company       : account,
				categories    : categories,
				subcategories : subcategories,
				level         : level,
				category      : category,
				breadcrumbList: parentsList.reverse()
		]
	}


	private void loadChildsCat(Category el, ArrayList childs) {
		def list = categoryService.getChilds(el)
		childs.add(el)
		childs.addAll(list)
		list.each {
			loadChildsCat(it, childs)
		}
	}

	private void loadParentsTop(Category el, ArrayList parents) {
		if (el?.parent?.parent) {
			parents.add(el.parent)
			loadParentsTop(el.parent, parents)
		}
	}

	def getCategoryFilter(Long categoryId) {
		def catFilter = null
		if (categoryId) {
			Category cat = categoryService.getCategory(categoryId)
			catFilter = []
			loadChildsCat(cat, catFilter)

			def parents = []
			loadParentsTop(cat, parents)
			catFilter.addAll(parents)
		}
		catFilter
	}

	def getCategoryFilter(Account account, Long categoryId) {
		def catFilter = null
		if (categoryId) {
			Category cat = categoryService.getCategory(categoryId)
			catFilter = []
			loadChildsCat(cat, catFilter)

			def catFilter2 = categoryService.getAccountCategories(account).intersect(catFilter)
			catFilter = catFilter2
			def parents = []
			loadParentsTop(cat, parents)
			catFilter.addAll(parents)
		}
		catFilter
	}


	def getCategoryFilter(List<Long> categoryIdList) {
		def catFilter = null
		if (categoryIdList && !categoryIdList.isEmpty()) {
			catFilter = []
			categoryIdList.each { categoryId ->
				Category cat = categoryService.getCategory(categoryId)
				loadChildsCat(cat, catFilter)

				def parents = []
				loadParentsTop(cat, parents)
				catFilter.addAll(parents)
			}
		}
		catFilter
	}

	def getRegionFilter(Long regionId) {
		regionService.getRegionFilter(regionId)
	}

	def getRegionFilter(List<Long> regionIdList) {
		regionService.getRegionFilter(regionIdList)
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

	def getCategoryFilterData(Long categoryId, CategoryType categoryType) {
		def topCat
		Category cat = categoryService.getCategory(categoryId)
		if (categoryType) {
			topCat = categoryService.getParentsCategories().findAll { it.type.id == categoryType.id }
		} else {
			if (cat) {
				topCat = cat
			} else {
				topCat = categoryService.getParentsCategories()
			}
		}
		log.debug("topCat:" + topCat)
		List tree = []
		topCat.each { Category it ->
			tree.addAll(categoryService.getTree(it.id))
		}
		String resultJson = tree as grails.converters.JSON

		[
				categoriesTreeJson: resultJson,
				selectedCategory  : cat
		]

	}
}
