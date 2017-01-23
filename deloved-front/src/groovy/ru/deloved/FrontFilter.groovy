package ru.deloved

import org.codehaus.groovy.grails.validation.Validateable

/**
 * Created by Андрейка on 22.10.2014.
 */
@Validateable
class FrontFilter extends FormFilter {
	Long category1
	Map<String, Long> category2 = [:]
	Long region

	@Override
	protected List getFilterParamList() {
		["region", "category1", "category2"]
	}

	@Override
	protected String getDefaultSort() {
		return "name"
	}

	@Override
	protected List getSortedParamList() {
		return ["name"]
	}

	@Override
	Map getMap() {
		def m = super.getMap()
		if (region != null) m.region = region;
		if (category1 != null) m.category1 = category1;
		m.category2 = category2;
		return m
	}
}
