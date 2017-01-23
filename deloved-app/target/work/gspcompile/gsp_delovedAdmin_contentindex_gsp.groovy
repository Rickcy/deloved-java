import ru.deloved.Content
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_contentindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/content/index.gsp" }
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
invokeTag('message','g',6,['code':("content.list.label")],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',12,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',17,['code':("content.list.label")],-1)
printHtmlPart(5)
invokeTag('render','g',19,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
createTagBody(2, {->
invokeTag('message','g',22,['code':("content.button.new.label")],-1)
})
invokeTag('link','g',22,['class':("btn btn-success"),'action':("create")],2)
printHtmlPart(7)
expressionOut.print(message(code: 'default.button.delete.confirm.message'))
printHtmlPart(8)
expressionOut.print(message(code: 'content.button.enable.confirm.message'))
printHtmlPart(9)
expressionOut.print(message(code: 'content.button.disable.confirm.message'))
printHtmlPart(10)
invokeTag('message','g',51,['code':("default.button.actionSelect.label")],-1)
printHtmlPart(11)
invokeTag('message','g',54,['code':("content.button.delete.label")],-1)
printHtmlPart(12)
invokeTag('message','g',55,['code':("content.button.enable.label")],-1)
printHtmlPart(13)
invokeTag('message','g',56,['code':("content.button.disable.label")],-1)
printHtmlPart(14)
createTagBody(2, {->
printHtmlPart(15)
invokeTag('textField','g',63,['placeholder':("Name"),'class':("form-control"),'name':("name"),'value':(filter.name)],-1)
printHtmlPart(16)
invokeTag('textField','g',64,['placeholder':("Code"),'class':("form-control"),'name':("code"),'value':(filter.code)],-1)
printHtmlPart(17)
invokeTag('submitButton','g',67,['name':("filterAction"),'class':("btn btn-primary"),'value':(message(code: 'default.button.filter.label'))],-1)
printHtmlPart(18)
invokeTag('submitButton','g',68,['name':("resetAction"),'class':("btn btn-default"),'formaction':("reset"),'value':(message(code: 'default.button.filterReset.label'))],-1)
printHtmlPart(19)
})
invokeTag('form','g',73,['url':([controller: 'content', action: 'index'])],2)
printHtmlPart(20)
createTagBody(2, {->
printHtmlPart(21)
invokeTag('hiddenField','g',76,['name':("batch_action"),'value':("")],-1)
printHtmlPart(22)
invokeTag('sortableColumn','g',82,['property':("name"),'title':(message(code: 'content.name.label', default: 'Name'))],-1)
printHtmlPart(18)
invokeTag('sortableColumn','g',83,['property':("code"),'title':(message(code: 'content.code.label', default: 'Code'))],-1)
printHtmlPart(18)
invokeTag('sortableColumn','g',84,['property':("enabled"),'title':(message(code: 'content.enabled.label', default: 'Enabled'))],-1)
printHtmlPart(23)
loop:{
int i = 0
for( obj in (contentInstanceList) ) {
printHtmlPart(24)
expressionOut.print(obj.news!='Новость')
printHtmlPart(25)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(26)
expressionOut.print(obj.id)
printHtmlPart(27)
if(true && (obj.news=='Новость')) {
printHtmlPart(28)
createTagBody(5, {->
expressionOut.print(fieldValue(bean: obj, field: "name"))
})
invokeTag('link','g',106,['action':("edit"),'id':(obj.id),'elementId':("gridRow${obj.id}name"),'data-toggle':("modal"),'data-remote':(createLink(id: obj.id, action: 'edit')),'data-target':("#myModal")],5)
printHtmlPart(29)
}
else {
printHtmlPart(30)
createTagBody(5, {->
expressionOut.print(fieldValue(bean: obj, field: "name"))
})
invokeTag('link','g',116,['action':("edit2"),'id':(obj.id),'elementId':("gridRow${obj.id}name"),'data-toggle':("modal"),'data-remote':(createLink(id: obj.id, action: 'edit2')),'data-target':("#myModal")],5)
printHtmlPart(31)
}
printHtmlPart(32)
expressionOut.print(obj.id)
printHtmlPart(33)
expressionOut.print(obj.code)
printHtmlPart(34)
expressionOut.print(obj.id)
printHtmlPart(35)
expressionOut.print(obj.enabled)
printHtmlPart(36)
i++
}
}
printHtmlPart(37)
if(true && (params.max < rowsCount)) {
printHtmlPart(38)
invokeTag('paginate','g',132,['total':(rowsCount ?: 0)],-1)
printHtmlPart(21)
}
printHtmlPart(1)
})
invokeTag('form','g',134,['id':("batch_form"),'url':([controller: 'content', action: 'batch']),'method':("POST")],2)
printHtmlPart(39)
invokeTag('render','g',138,['template':("/_common/edit-container")],-1)
printHtmlPart(3)
})
invokeTag('captureBody','sitemesh',140,[:],1)
printHtmlPart(40)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1473921633107L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
