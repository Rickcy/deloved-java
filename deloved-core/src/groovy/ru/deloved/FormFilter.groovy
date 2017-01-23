package ru.deloved

import groovy.transform.EqualsAndHashCode

/**
 * Created with IntelliJ IDEA.
 * User: Андрейка
 * Date: 05.11.13
 * Time: 21:25
 */
@EqualsAndHashCode
abstract class FormFilter implements Serializable{

	String sort
	String order
	Integer max
	Integer offset

	public Map getMap() {
		def map = [:]
		if (sort != null) map.sort = sort
		if (order != null) map.order = order
		if (max != null) map.max = max
		if (offset != null) map.offset = offset
		return map
	}

	abstract protected List getFilterParamList()

	abstract protected List getSortedParamList()

	protected String getDefaultSort(){
		return null;
	}

	protected String getDefaultOrder(){
		return null;
	}

	@Override
	public String toString() {
		return getMap().toString();
	}
}
