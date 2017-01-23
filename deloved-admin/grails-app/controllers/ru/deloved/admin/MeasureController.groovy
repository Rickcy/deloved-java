package ru.deloved.admin

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.transform.EqualsAndHashCode
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import ru.deloved.*

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER') and isFullyAuthenticated()"])
class MeasureController extends FilteredController {

	@Override
	protected FormFilter createFilterInstance() {
		return new MeasureFilter()
	}

	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"

	@Override
	protected PagedResultWrapper getRows(FormFilter filter) {
		MeasureFilter f = filter
		def rows = Measure.createCriteria().list(max: filter.max, offset: filter.offset) {
			if (f.ctype != null) {
				eq('type', CategoryType.get(f.ctype))
			}
			if (f.mname != null) {
				or {
					ilike('name', "%" + f.mname + "%")
					ilike('fullname', "%" + f.mname + "%")
				}
			}
			order(filter.sort ?: "name", filter.order ?: "asc")
		}
		new PagedResultWrapper(rows)

	}

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(MeasureFilter measureFilter) {
		processIndex(10, measureFilter)
	}

	def show(Measure measureInstance) {
		if (measureInstance?.id == null) {
			notFound()
			return
		}
		respond measureInstance
	}

	def create() {
	}

	protected redirectEdit(Measure beanResource, objInstance = null) {
		render view: request.xhr ? 'editform' : 'edit',
				model: [
						beanResource: beanResource,
						objInstance : objInstance ?: beanResource,
				]
	}

	def edit(Measure measureInstance) {
		if (measureInstance?.id == null) {
			notFound()
			return
		}
		redirectEdit(measureInstance)
	}

	@Transactional
	def save(Measure measureInstance) {
		if (measureInstance == null) {
			notFound()
			return
		}

		if (measureInstance.hasErrors()) {
			respond measureInstance.errors, view: 'create'
			return
		}

		measureInstance.save flush: true

		request.withFormat {
			form {
				flash.message = message(code: 'default.created.measure', args: [message(code: 'measure.label')])
				redirect base: uri,controller: 'measure', action: 'index'
			}
			'*' { respond measureInstance, [status: CREATED] }
		}
	}

	@Transactional
	def update(Measure measureInstance) {
		if (measureInstance?.id == null) {
			notFound()
			return
		}

		if (measureInstance.hasErrors()) {
			redirectEdit(measureInstance)
			return
		}

		def saved = measureInstance.save flush: true

		if (request.xhr) {
			if (saved) {
				response.contentType = "text/javascript;charset=UTF-8"
				def cols = [
						name: measureInstance.name,
						fullname: measureInstance.fullname,
						type: message(code: 'categorytype.' + measureInstance.type.code, default: measureInstance.type.code),
				]
				render template: '/_common/grid-row-update', model: [gridRowId: measureInstance.id, gridCols: cols]
			} else {
				redirectEdit(measureInstance)
			}
		} else {
			request.withFormat {
				'*' {
					flash.message = message(code: saved ? 'default.updated.measure' : 'default.notUpdated.measure',
							args: [message(code: 'measure.label')]
					)
					redirect base: uri,controller: 'measure', action: 'index'
				}
				json { respond measureInstance, [status: OK] }
			}
		}
	}

	@Transactional
	def batch(IdListCommand idList) {
		log.trace('idList:' + idList)
		if (params.batch_action == "batchDelete") {
			deleteList(idList)
			return
		}
		redirect base: uri,controller: 'measure', action: "index", method: "GET"
	}

	private deleteList(IdListCommand idList) {
		def st = NO_CONTENT
		def mess = null
		log.trace('params:' + params)
		log.trace('idList:' + idList)
		if (idList?.id) {
			if (idList.id.size() == 1) {
				def measure = Measure.get(idList.id[0])
				if (measure) {
					measure.delete flush: true
					mess = message(code: 'default.deleted.measure', args: [message(code: 'measure.label'), measure.name])
				} else {
					notFound()
					return
				}
			} else if (idList.id.size() > 1) {
				int deleted = 0
				idList.getList().each {
					Measure.load(it).delete(flush: true)
					deleted++
				}
				mess = message(code: 'default.deletedMany.measure', args: [message(code: 'user.label', default: 'measure'), idList.id, deleted])
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
				redirect base: uri,controller: 'measure', action: "index", method: "GET"
			}
			'*' { render status: st }
		}
	}

	@Transactional
	def delete(IdListCommand idList) {
		deleteList(idList)
	}

	protected void notFound() {
		request.withFormat {
			'*' {
				render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'measure.label', default: 'Measure'), params.id])]
			}
			json { render status: NOT_FOUND }
		}
	}

}

@EqualsAndHashCode
class MeasureFilter extends FormFilter {
	Long ctype
	String mname

	@Override
	protected List getFilterParamList() {
		['mname', 'ctype']
	}

	@Override
	protected List getSortedParamList() {
		['name', 'fullname', 'type']
	}

	@Override
	public Map getMap() {
		def m = super.getMap()
		if (ctype != null) m.ctype = ctype
		if (mname != null) m.mname = mname
		return m
	}
}
