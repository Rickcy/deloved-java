package ru.deloved

import grails.orm.PagedResultList
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication

/**
 * Created with IntelliJ IDEA.
 * User: andrey@shertsinger
 * Date: 05.11.13
 * Time: 21:55
 */
abstract class FilteredController {

	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"
	def springSecurityService

	def reset() {
		resetFilter()
		redirect  base: uri, action: 'index'
	}

	abstract protected FormFilter createFilterInstance();

	abstract protected PagedResultWrapper getRows(FormFilter filter);

	protected String getFilterSessionName(){
		return this.getClass().getName()
	}

	protected void resetFilter() {
		String filterName = getFilterSessionName()
		session.setAttribute(filterName + "FormFilter", createFilterInstance())
	}

	protected void processIndex(int max, FormFilter pageFilter) {
		processIndex(max, pageFilter, null)
	}

	protected void redirectIndex(){
		redirect  base: uri, action: 'index'
	}

	protected void processIndex(int max, FormFilter pageFilter, Closure model) {
		FormFilter filter = getFilter(max, pageFilter)
		if (!request.getMethod().equals("GET")) {
			redirectIndex()
		} else {
//			log.debug("FILTER:"+filter)
			def resultWrapper = getRows(filter)
			List rows=resultWrapper.resultList;

			while (rows.isEmpty() && filter.offset > 0) {
//				log.debug("no data. Go prev page")
				filter.offset -= filter.max;
				if (filter.offset < 0) filter.offset = 0;
				resultWrapper = getRows(filter)
				rows=resultWrapper.resultList
			}

			def respondModel = [rowsCount: resultWrapper.totalCount, filter: filter.getMap()]
			if (model) {
				Map r = model.call(filter)
				if (r?.containsKey('model')) {
					respondModel.putAll(r.get('model'))
				}
			}
			respond rows, model: respondModel
		}
	}

	protected Object getFilter() {
		String filterName = getFilterSessionName()
		FormFilter filter = (FormFilter) session.getAttribute(filterName + "FormFilter")
		if (filter == null) {
			filter = createFilterInstance()
			session.setAttribute(filterName + "FormFilter", filter)
		}
		return filter
	}

	protected Object getFilter(int max, FormFilter pageFilter) {
//		log.trace("params:" + params)
//		log.trace("pageFilter:"+pageFilter)
		params.max = Math.min(pageFilter.max ?: max, 100)
		String filterName = getFilterSessionName()
		FormFilter filter = (FormFilter) session.getAttribute(filterName + "FormFilter")
		if (filter == null) {
			filter = createFilterInstance()
			session.setAttribute(filterName + "FormFilter", filter)
		}
		def list = ['max', 'offset', 'sort', 'order']
		list.addAll(filter.getFilterParamList())
		FormFilter before = createFilterInstance()
		FormFilter after = createFilterInstance()
//		log.debug("before:" + before)
		def filterParamList = filter.getFilterParamList()
//		log.debug("filterParamList:" + filterParamList)
		bindData(before, filter, [include: filterParamList])
//		log.debug("before:" + before)
//		log.debug("filter1:" + filter)
		bindData(filter, params, [include: list])
//		log.debug("filter2:" + filter)
		bindData(after, filter, [include: filterParamList])
		if ((filter.order && !['asc', 'desc'].contains(filter.order)) || filter.order == null) {
			filter.order = filter.getDefaultOrder()
		}
		if ((filter.sort && !filter.getSortedParamList().contains(filter.sort)) || filter.sort == null) {
			filter.sort = filter.getDefaultSort()
			filter.order = filter.getDefaultOrder()
		}
		if (!after.equals(before)) {
			log.debug("reset offset!!!")
			filter.offset = null
		}
		params.offset = filter.offset ?: 0
		params.sort = filter.sort
		params.order = filter.order
		log.debug("params:" + params)
		log.debug("filter:" + filter)

		return filter
	}
}
