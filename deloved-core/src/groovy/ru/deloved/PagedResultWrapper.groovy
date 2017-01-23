package ru.deloved

import grails.orm.PagedResultList

/**
 * Created by Андрейка on 15.10.2014.
 */
class PagedResultWrapper {

	private List resultList;
	private long totalCount;

	PagedResultWrapper(List resultList, long totalCount) {
		this.resultList = resultList
		this.totalCount = totalCount
	}

	PagedResultWrapper(PagedResultList pagedResultList) {
		this.resultList = pagedResultList
		this.totalCount = pagedResultList.totalCount
	}

	List getResultList() {
		return resultList
	}

	public int getTotalCount() {
		return totalCount
	}
}
