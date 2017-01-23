package ru.deloved.admin

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.transform.EqualsAndHashCode
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import ru.deloved.FilteredController
import ru.deloved.FormFilter
import ru.deloved.IdListCommand
import ru.deloved.OrgForm
import ru.deloved.PagedResultWrapper

import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.CREATED

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK


@Transactional(readOnly = true)
@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_MANAGER') and isFullyAuthenticated()"])
class OrgController extends FilteredController{

    String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"

    @Override
    protected FormFilter createFilterInstance() {
        return new OrgFilter();
    }

    @Override
    protected PagedResultWrapper getRows(FormFilter filter) {
        OrgFilter f =filter
        def rows = OrgForm.createCriteria().list(max: filter.max, offset: filter.offset){
            if (f.name != null) {
                or {
                    ilike('name', "%" + f.name + "%")
                    ilike('code', "%" + f.name + "%")
                }
            }
            order(filter.sort ?: "id", filter.order ?: "asc")
        }
        new PagedResultWrapper(rows)
    }

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def index(OrgFilter orgFilter) {
        processIndex(10, orgFilter)
    }

    def show(OrgForm orgInstance) {
        if (orgInstance?.id == null) {
            notFound()
            return
        }
        respond orgInstance
    }

    protected redirectEdit(OrgForm beanResource, objInstance = null) {
        render view: request.xhr ? 'editform' : 'edit',
                model: [
                        beanResource: beanResource,
                        objInstance : objInstance ?: beanResource,
                ]
    }


    def create() {
    }

    def edit(OrgForm orgInstance) {
        if (orgInstance?.id == null) {
            notFound()
            return
        }
        redirectEdit(orgInstance)
    }


    @Transactional
    def save(OrgForm orgInstance) {
        if (orgInstance == null) {
            notFound()
            return
        }

        if (orgInstance.hasErrors()) {
            respond orgInstance.errors, view: 'create'
            return
        }

        orgInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'orgInstance.label', default: 'Org forms'), orgInstance.id])
                redirect base: uri,controller: 'org', action: 'index'
            }
            '*' { respond orgInstance, [status: CREATED] }
        }
    }


    @Transactional
    def update(OrgForm orgInstance) {
        if (orgInstance?.id == null) {
            notFound()
            return
        }

        if (orgInstance.hasErrors()) {
            redirectEdit(orgInstance)
            return
        }

        def saved = orgInstance.save flush: true

        if (request.xhr) {
            if (saved) {
                response.contentType = "text/javascript;charset=UTF-8"
                def cols = [
                        name: orgInstance.name,
                        code: orgInstance.code,

                ]
                render template: '/_common/grid-row-update', model: [gridRowId: orgInstance.id, gridCols: cols]
            } else {
                redirectEdit(orgInstance)
            }
        } else {
            request.withFormat {
                '*' {
                    flash.message = message(code: saved ? 'default.updated.message' : 'default.notUpdated.message',
                            args: [message(code: 'org.label', default: 'Org form'), orgInstance.id]
                    )
                    redirect base: uri,controller: 'org', action: 'index'
                }
                json { respond orgInstance, [status: OK] }
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
        redirect base: uri,controller: 'org', action: "index", method: "GET"
    }


    private deleteList(IdListCommand idList) {
        def st = NO_CONTENT
        def mess = null
        log.trace('params:' + params)
        log.trace('idList:' + idList)
        if (idList?.id) {
            if (idList.id.size() == 1) {
                def org = OrgForm.get(idList.id[0])
                if (org) {
                    org.delete flush: true
                    mess = message(code: 'default.deleted.message', args: [message(code: 'org.label', default: 'org'), org.name])
                } else {
                    notFound()
                    return
                }
            } else if (idList.id.size() > 1) {
                int deleted = 0
                idList.getList().each {
                    OrgForm.load(it).delete(flush: true)
                    deleted++
                }
                mess = message(code: 'default.deletedMany.message', args: [message(code: 'user.label', default: 'orgForm'), idList.id, deleted])
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
                redirect base: uri,controller: 'org', action: "index", method: "GET"
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
                render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'org.label', default: 'Org form'), params.id])]
            }
            json { render status: NOT_FOUND }
        }
    }
}


@EqualsAndHashCode
class OrgFilter extends FormFilter{

    String name;

    @Override
    protected List getFilterParamList() {
        ['name']
    }

    @Override
    protected List getSortedParamList() {
        ['name','code']
    }

    @Override
    public Map getMap() {
        def m = super.getMap()
        if (name != null) m.name = name
        return m
    }
}
