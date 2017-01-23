import ru.deloved.Category
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_categoryindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/category/index.gsp" }
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
invokeTag('message','g',6,['code':("category.list.label")],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('message','g',14,['code':("category.list.label")],-1)
printHtmlPart(4)
invokeTag('render','g',16,['template':("/_common/flash-message")],-1)
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
invokeTag('set','g',19,['var':("ADMIN_USER"),'value':(true)],-1)
printHtmlPart(1)
})
invokeTag('ifAllGranted','sec',20,['roles':("ROLE_ADMIN")],2)
printHtmlPart(1)
if(true && (parent || ADMIN_USER)) {
printHtmlPart(7)
if(true && (parent)) {
printHtmlPart(8)
createTagBody(4, {->
printHtmlPart(9)
invokeTag('hiddenField','g',27,['name':("parentId"),'value':(parent.id)],-1)
printHtmlPart(9)
invokeTag('textField','g',28,['name':("name"),'required':("")],-1)
printHtmlPart(9)
createClosureForHtmlPart(10, 5)
invokeTag('submitButton','g',29,['name':(message(code: 'category.button.new.label')),'class':("btn btn-success")],5)
printHtmlPart(8)
})
invokeTag('form','g',30,['name':("addForm"),'url':([controller: 'category', action: 'save'])],4)
printHtmlPart(11)
}
printHtmlPart(12)
createTagBody(3, {->
printHtmlPart(13)
expressionOut.print(message(code: 'default.button.delete.confirm.message'))
printHtmlPart(14)
expressionOut.print(message(code: 'default.button.deleteSelected.label'))
printHtmlPart(15)
})
invokeTag('ifAllGranted','sec',40,['roles':("ROLE_ADMIN")],3)
printHtmlPart(16)
}
printHtmlPart(17)
createTagBody(2, {->
expressionOut.print(message(code: 'category.breadcrumb.root', default: 'Root'))
})
invokeTag('link','g',47,['id':("0")],2)
printHtmlPart(18)
for( obj in (breadcrumbList) ) {
printHtmlPart(19)
createTagBody(3, {->
expressionOut.print(obj.name)
})
invokeTag('link','g',49,['id':(obj.id)],3)
printHtmlPart(18)
}
printHtmlPart(20)
createTagBody(2, {->
printHtmlPart(21)
invokeTag('sortableColumn','g',59,['property':("name"),'title':(message(code: 'category.name.label', default: 'Name'))],-1)
printHtmlPart(22)
loop:{
int i = 0
for( obj in (categoryInstanceList) ) {
printHtmlPart(23)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(24)
expressionOut.print(obj.id)
printHtmlPart(25)
createTagBody(4, {->
expressionOut.print(fieldValue(bean: obj, field: "name"))
})
invokeTag('link','g',73,['action':("edit"),'id':(obj.id),'elementId':("gridRow${obj.id}name"),'data-toggle':("modal"),'data-remote':(createLink(id: obj.id, action: 'edit')),'data-target':("#myModal")],4)
printHtmlPart(26)
createClosureForHtmlPart(27, 4)
invokeTag('link','g',75,['id':(obj.id)],4)
printHtmlPart(28)
i++
}
}
printHtmlPart(29)
})
invokeTag('form','g',80,['id':("deleteForm"),'url':([controller: 'category', action: 'delete']),'method':("DELETE")],2)
printHtmlPart(1)
if(true && (params.max < rowsCount)) {
printHtmlPart(6)
invokeTag('paginate','g',82,['total':(rowsCount ?: 0)],-1)
printHtmlPart(1)
}
printHtmlPart(30)
invokeTag('render','g',87,['template':("/_common/edit-container")],-1)
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',89,[:],1)
printHtmlPart(31)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1453976895959L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
