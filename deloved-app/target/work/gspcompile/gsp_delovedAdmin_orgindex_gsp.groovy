import ru.deloved.OrgForm
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_orgindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/org/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("admin")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',6,['code':("org.list.label")],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',12,['code':("org.list.label")],-1)
printHtmlPart(5)
invokeTag('render','g',14,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
createTagBody(2, {->
invokeTag('message','g',17,['code':("org.button.new.label")],-1)
})
invokeTag('link','g',17,['class':("btn btn-success"),'action':("create")],2)
printHtmlPart(7)
expressionOut.print(message(code: 'default.button.delete.confirm.message'))
printHtmlPart(8)
invokeTag('message','g',30,['code':("default.button.actionSelect.label")],-1)
printHtmlPart(9)
invokeTag('message','g',33,['code':("org.button.delete.label")],-1)
printHtmlPart(10)
createTagBody(2, {->
printHtmlPart(11)
invokeTag('textField','g',41,['placeholder':("Search"),'class':("form-control"),'name':("name"),'value':(filter.name)],-1)
printHtmlPart(12)
invokeTag('submitButton','g',44,['name':("filterAction"),'class':("btn btn-primary"),'value':(message(code: 'default.button.filter.label'))],-1)
printHtmlPart(13)
invokeTag('submitButton','g',45,['name':("resetAction"),'class':("btn btn-default"),'formaction':("reset"),'value':(message(code: 'default.button.filterReset.label'))],-1)
printHtmlPart(14)
})
invokeTag('form','g',50,['url':([controller: 'org', action: 'index'])],2)
printHtmlPart(15)
createTagBody(2, {->
printHtmlPart(16)
invokeTag('hiddenField','g',53,['name':("batch_action"),'value':("")],-1)
printHtmlPart(17)
invokeTag('sortableColumn','g',59,['property':("name"),'title':(message(code: 'org.name.label', default: 'Name'))],-1)
printHtmlPart(18)
invokeTag('sortableColumn','g',60,['property':("code"),'title':(message(code: 'org.code.label', default: 'Code'))],-1)
printHtmlPart(19)
loop:{
int i = 0
for( obj in (orgFormInstanceList) ) {
printHtmlPart(20)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(21)
expressionOut.print(obj.id)
printHtmlPart(22)
createTagBody(4, {->
expressionOut.print(fieldValue(bean: obj, field: "name"))
})
invokeTag('link','g',76,['action':("edit"),'id':(obj.id),'elementId':("gridRow${obj.id}name"),'data-toggle':("modal"),'data-remote':(createLink(id: obj.id, action: 'edit')),'data-target':("#myModal")],4)
printHtmlPart(23)
expressionOut.print(obj.id)
printHtmlPart(24)
expressionOut.print(fieldValue(bean: obj, field: "code"))
printHtmlPart(25)
i++
}
}
printHtmlPart(26)
if(true && (params.max < rowsCount)) {
printHtmlPart(27)
invokeTag('paginate','g',87,['total':(rowsCount ?: 0)],-1)
printHtmlPart(16)
}
printHtmlPart(1)
})
invokeTag('form','g',89,['id':("batch_form"),'url':([controller: 'org', action: 'batch']),'method':("POST")],2)
printHtmlPart(28)
invokeTag('render','g',93,['template':("/_common/edit-container")],-1)
printHtmlPart(3)
})
invokeTag('captureBody','sitemesh',95,[:],1)
printHtmlPart(29)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1478058086887L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
