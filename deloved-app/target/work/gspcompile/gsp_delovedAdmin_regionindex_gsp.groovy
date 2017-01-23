import ru.deloved.Region
import ru.deloved.RegionLevel
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_regionindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/region/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("admin")],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',7,['code':("region.list.label")],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(0)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',14,['code':("region.list.label")],-1)
printHtmlPart(5)
invokeTag('render','g',16,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(7)
invokeTag('set','g',19,['var':("ADMIN_USER"),'value':(true)],-1)
printHtmlPart(2)
})
invokeTag('ifAllGranted','sec',20,['roles':("ROLE_ADMIN")],2)
printHtmlPart(2)
if(true && (parent || ADMIN_USER)) {
printHtmlPart(8)
if(true && (parent && RegionLevel.findByParent(parent.level))) {
printHtmlPart(9)
createTagBody(4, {->
printHtmlPart(10)
invokeTag('hiddenField','g',27,['name':("parentId"),'value':(parent.id)],-1)
printHtmlPart(10)
invokeTag('textField','g',28,['name':("name"),'required':("")],-1)
printHtmlPart(10)
createClosureForHtmlPart(11, 5)
invokeTag('submitButton','g',30,['name':(message(code: 'region.button.newIn.' + parent.level.type.code + '.label', default: message(code: 'region.button.new.label'))),'class':("btn btn-success")],5)
printHtmlPart(9)
})
invokeTag('form','g',31,['name':("addForm"),'url':([controller: 'region', action: 'save'])],4)
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(3, {->
printHtmlPart(14)
expressionOut.print(message(code: 'default.button.delete.confirm.message'))
printHtmlPart(15)
expressionOut.print(message(code: 'default.button.deleteSelected.label'))
printHtmlPart(16)
})
invokeTag('ifAllGranted','sec',41,['roles':("ROLE_ADMIN")],3)
printHtmlPart(17)
}
printHtmlPart(18)
createTagBody(2, {->
expressionOut.print(message(code: 'region.breadcrumb.root', default: 'Root'))
})
invokeTag('link','g',48,['id':("0")],2)
printHtmlPart(19)
for( obj in (breadcrumbList) ) {
printHtmlPart(20)
createTagBody(3, {->
expressionOut.print(obj.name)
})
invokeTag('link','g',50,['id':(obj.id)],3)
printHtmlPart(19)
}
printHtmlPart(21)
createTagBody(2, {->
printHtmlPart(22)
invokeTag('sortableColumn','g',61,['property':("name"),'title':(message(code: 'region.name.label', default: 'Name'))],-1)
printHtmlPart(23)
invokeTag('message','g',62,['code':("region.type.label")],-1)
printHtmlPart(24)
loop:{
int i = 0
for( obj in (regionInstanceList) ) {
printHtmlPart(25)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(26)
expressionOut.print(obj.id)
printHtmlPart(27)
createTagBody(4, {->
expressionOut.print(fieldValue(bean: obj, field: "name"))
})
invokeTag('link','g',76,['action':("edit"),'id':(obj.id),'elementId':("gridRow${obj.id}name"),'data-toggle':("modal"),'data-remote':(createLink(id: obj.id, action: 'edit')),'data-target':("#myModal")],4)
printHtmlPart(28)
invokeTag('message','g',78,['code':("regionType.${obj.level.type.code}.label")],-1)
printHtmlPart(29)
createClosureForHtmlPart(30, 4)
invokeTag('link','g',79,['id':(obj.id)],4)
printHtmlPart(31)
i++
}
}
printHtmlPart(32)
})
invokeTag('form','g',85,['id':("deleteForm"),'url':([controller: 'region', action: 'delete']),'method':("DELETE")],2)
printHtmlPart(2)
if(true && (params.max < rowsCount)) {
printHtmlPart(7)
invokeTag('paginate','g',87,['total':(rowsCount ?: 0)],-1)
printHtmlPart(2)
}
printHtmlPart(33)
invokeTag('render','g',92,['template':("/_common/edit-container")],-1)
printHtmlPart(3)
})
invokeTag('captureBody','sitemesh',94,[:],1)
printHtmlPart(34)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1460087456589L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
