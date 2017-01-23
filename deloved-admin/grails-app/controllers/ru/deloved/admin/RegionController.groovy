package ru.deloved.admin

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import ru.deloved.*

import static org.springframework.http.HttpStatus.*

@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER') and isFullyAuthenticated()"])
class RegionController extends FilteredController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"
	@Override
	protected FormFilter createFilterInstance() {
		return new RegionFilter()
	}

	@Override
	protected PagedResultWrapper getRows(FormFilter filter) {
		RegionFilter f = filter
		def rows = Region.createCriteria().list(max: filter.max, offset: filter.offset) {
			if (f.id && f.id > 0) {
				eq('parent.id', f.id)
			} else {
				isNull('parent')
			}
			order(filter.sort ?: "id", filter.order ?: "asc")
		}
		new PagedResultWrapper(rows)
	}

	void loadParents(Region el, ArrayList parents) {
		if (el) {
			parents.add([id: el.id, name: el.name])
			loadParents(el.parent, parents)
		}
	}

	def index(RegionFilter regFilter) {
		processIndex(10, regFilter) {
			RegionFilter filter ->
				log.debug('p:' + regFilter)
				log.debug('f:' + filter)
				if (filter.id) {
					def parentsList = []
					def parentReg = Region.get(filter.id)
					loadParents(parentReg, parentsList)
					[model: [
							breadcrumbList: parentsList.reverse(),
							parent        : parentReg
					]]
				}
		}
	}

	def create() {
		RegionFilter filter = getFilter()
		if (filter.id) {
			def parentReg = Region.get(filter.id)
			respond new RegionCreateCommand(), [model: [parent: parentReg], view: 'create']
		} else {
			flash.message = message(code: 'region.parentNotSelect.label')
			redirect base: uri,controller: 'region', action: 'index'
		}
	}

	protected redirectEdit(Region beanResource, objInstance = null) {
		def cd = null
		if (beanResource.level.type.code == 'COUNTRY') {
			cd = CountryDefaults.findOrCreateByCountry(beanResource)
		}
		render view: request.xhr ? 'editform' : 'edit',
				model: [
						beanResource   : beanResource,
						objInstance    : objInstance ?: beanResource,
						countryDefaults: cd
				]
	}

	def edit(Region regInstance) {
		log.debug("edit:" + regInstance);
		if (regInstance?.id == null) {
			notFound()
			return
		}
		RegionEditCommand regEditCommandInstance = new RegionEditCommand()
		bindData(regEditCommandInstance, regInstance)
		redirectEdit(regInstance, regEditCommandInstance)
	}

	@Transactional
	def save(RegionCreateCommand regionInstance) {
		if (regionInstance == null) {
			notFound()
			return
		}
		Region parent = Region.get(regionInstance.parentId)

		if (regionInstance.hasErrors()) {
			regionInstance.errors.each { log.error(it) }
			if (regionInstance.parentId) {
				respond regionInstance.errors, [model: [parent: parent], view: 'create']
			} else {
				flash.message = message(code: 'region.parentNotSelect.label')
				redirect base: uri,controller: 'region', action: 'index'
			}
			return
		}

		Region region = new Region(name: regionInstance.name, parent: parent)
		def saved = region.save(flush: true)
		log.debug "Region:" + region
		log.debug "saved:" + saved
		log.debug "region.errors:" + region.errors
		if (saved) {
			request.withFormat {
				form {
					flash.message = message(code: 'default.created.message', args: [message(code: 'region.label'), region.name])
					redirect base: uri,controller: 'region', action: 'index'
				}
				'*' { respond region, [status: CREATED] }
			}
		} else {
			respond region.errors, [model: [parent: parent], view: 'create']
		}

	}

	@Transactional
	def update(RegionEditCommand regionEditCommandInstance) {
		log.debug("update:" + regionEditCommandInstance);
		log.error("params:" + params)
		Region region = Region.get(params.id)
		if (region == null) {
			notFound()
			return
		}

		if (regionEditCommandInstance.hasErrors()) {
			log.error("has errors:" + regionEditCommandInstance.errors)
			log.error("regionInstance:" + region)
			redirectEdit(region, regionEditCommandInstance)
			return
		}
		bindData(region, regionEditCommandInstance)
		def saved = region.save(flush: true)
		log.debug(region);
		if (saved) {
			if (regionEditCommandInstance.currency != null && region.level.type.code == 'COUNTRY') {
				def cd = CountryDefaults.findOrCreateByCountry(region)
				cd.currency = regionEditCommandInstance.currency
				cd.save(flush: true)
			}
		}

		if (request.xhr) {
			if (saved) {
				response.contentType = "text/javascript;charset=UTF-8"
				def cols = [
						name: region.name
				]
				render template: '/_common/grid-row-update', model: [gridRowId: region.id, gridCols: cols]
			} else {
				redirectEdit(region)
			}
		} else {
			request.withFormat {
				'*' {
					flash.message = message(code: saved ? 'default.updated.message' : 'default.notUpdated.message',
							args: [message(code: 'region.label', default: 'Region'), region.id]
					)
					redirect base: uri,controller: 'region', action: 'index'
				}
				json { respond region, [status: OK] }
			}
		}
	}

	@Transactional
	@Secured(["hasRole('ROLE_ADMIN')"])
	def delete(IdListCommand idList) {

		def st = NO_CONTENT
		def mess = null

		log.trace('params:' + params)
		log.trace('idList:' + idList)
		if (idList?.id) {
			if (idList.id.size() == 1) {
				def region = Region.get(idList.id[0])
				if (region) {
					region.delete flush: true
					mess = message(code: 'default.deleted.message', args: [message(code: 'region.label', default: 'Region'), region.name])
				} else {
					notFound()
					return
				}
			} else if (idList.id.size() > 1) {
				int deleted = 0
				idList.getList().each {
					Region.load(it).delete()
					deleted++
				}
				mess = message(code: 'default.deletedMany.message', args: [message(code: 'region.label', default: 'Region'), idList.id, deleted])
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
				redirect base: uri,controller: 'region', action: "index", method: "GET"
			}
			'*' { render status: st }
		}
	}

	protected void notFound() {
		request.withFormat {
			'*' {
				render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])]
			}
			json { render status: NOT_FOUND }
		}
	}
}

class RegionCreateCommand {
	Integer parentId;
	String name;

	static constraints = {
		parentId(nullable: false)
		name(blank: false)
	}
}

class RegionEditCommand {
	int id
	String name
	SystemCurrency currency

	static constraints = {
		name(blank: false)
		currency(nullable: true)
	}
}

class RegionFilter extends FormFilter {
	Long id;

	@Override
	protected List getFilterParamList() {
		['id']
	}

	@Override
	protected List getSortedParamList() {
		['name']
	}

	@Override
	public Map getMap() {
		def m = super.getMap()
		if (id != null) m.id = id;
		return m
	}
}