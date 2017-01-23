package ru.deloved

import grails.transaction.Transactional

@Transactional
class RegionService {

	private Map<Long, Region> allItems = new Hashtable<Long, Region>()
	private Map<Long, Region> parentItems = new Hashtable<Long, Region>()
	private Map<Long, List<Region>> childs = new Hashtable<>()
	private Map<Long, List> tree = new Hashtable<>()

	public void init() {

		Region.findAll().each { Region it ->
			allItems.put(it.id, it)
			if (it.parent) {
				List<Region> list = childs.get(it.parent.id)
				if (list == null) {
					list = new ArrayList<>()
					childs.put(it.parent.id, list)
				}
				list.add(it)
			} else {
				parentItems.put(it.id, it)
			}
		}
		childs.values().each { List<Region> list ->
			list.sort { Region r1, Region r2 ->
				r1.name.compareTo(r2.name)
			}
		}
		parentItems.values().each { Region it ->
			def list = []
			fillRegionsTree(it, list)
			tree.put(it.id, list)
		}
	}

	private void fillRegionsTree(Region r, ArrayList res) {
		def list = []
		getChilds(r.id).each { ch ->
			fillRegionsTree(ch, list)
		}
		if (list.isEmpty()) {
			res.add([id: r.id, text: r.name])
		} else {
			res.add([id: r.id, text: r.name, children: list])
		}
	}

	public Region getRegion(Long id) {
		id ? allItems.get(id) : null
	}

	public List<Region> getChilds(Long id) {
		id ? childs.get(id) ?: [] : []
	}

	public List<Region> getChilds(Region item) {
		item ? childs.get(item.id) ?: [] : []
	}

	public Collection<Region> getParentsRegions() {
		parentItems.values()
	}

	public List getTree(Long id) {
		if (id) {
			tree.get(id)
		} else {
			[]
		}
	}

	private void loadChildCities(Region el, List<Region> cities) {
		if (el.level.type.code == 'CITY') {
			cities.add(el)
		} else {
			getChilds(el.id).each {
				loadChildCities(it, cities)
			}
		}
	}

	public List<Region> getRegionFilter(Long regionId) {
		def regFilter = null
		if (regionId) {
			Region reg = allItems.get(regionId)
			if (reg) {
				regFilter = []
				loadChildCities(reg, regFilter)
			}
		}
		regFilter
	}

	public List<Region> getRegionFilter(List<Long> regionIdList) {
		def regFilter = null
		if (regionIdList && !regionIdList.isEmpty()) {
			regFilter = []
			regionIdList.each { regionId ->
				Region reg = allItems.get(regionId)
				if (reg) {
					loadChildCities(reg, regFilter)
				}
			}
		}
		regFilter
	}

	private Region getParent(Region region, RegionType type) {
		def r = region
		while (r.parent) {
			if (type && r.level.typeId == type.id) {
				return r
			}
			r = r.parent
		}
		if (type && r.level.typeId != type.id) {
			// если ищем по типу региона, дошли до верха и тип не наш - возвращаем null
			return null
		}
		return r;
	}

	public CountryDefaults getCountryDefaults(Region region) {
		def country = getParent(region, null)
		return CountryDefaults.findByCountry(country)
	}

}
