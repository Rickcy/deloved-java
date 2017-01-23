import ru.deloved.User
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_adcontentindex2_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/adcontent/index2.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("front")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',6,['code':("adcontent.list.label")],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',22,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',26,['code':("adcontent.list.label")],-1)
printHtmlPart(5)
invokeTag('render','g',28,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(7)
invokeTag('hiddenField','g',37,['name':("batch_action"),'value':("")],-1)
printHtmlPart(8)
invokeTag('sortableColumn','g',43,['property':("name"),'title':(message(code: 'adcontent.name.label', default: 'Name'))],-1)
printHtmlPart(9)
invokeTag('sortableColumn','g',48,['property':("approved"),'title':(message(code: 'adcontent.approved.label', default: 'Approved'))],-1)
printHtmlPart(10)
invokeTag('message','g',50,['code':("adcontent.account.label"),'default':("Account")],-1)
printHtmlPart(11)
invokeTag('sortableColumn','g',54,['property':("dateCreated"),'title':(message(code: 'adcontent.dateCreated.label', default: 'Date Created'))],-1)
printHtmlPart(12)
loop:{
int i = 0
for( obj in (adcontentInstanceList) ) {
printHtmlPart(13)
if(true && (obj.approved==true)) {
printHtmlPart(14)
if(true && (params.company==obj.account.name)) {
printHtmlPart(15)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(16)
createTagBody(6, {->
expressionOut.print(fieldValue(bean: obj, field: "name"))
})
invokeTag('link','g',75,['action':("edit"),'controller':("adcontent"),'id':(obj.id),'elementId':("gridRow${obj.id}name"),'data-toggle':("modal"),'data-remote':(createLink(id: obj.id, action: 'edit')),'data-target':("#myModal")],6)
printHtmlPart(17)
if(true && (obj.file)) {
printHtmlPart(18)
expressionOut.print(obj.id)
printHtmlPart(19)
expressionOut.print(createLink([action: 'download', id: obj.file.id, params: [name: obj.file.name]]))
printHtmlPart(20)
expressionOut.print(obj.file.name)
printHtmlPart(21)
expressionOut.print(obj.id)
printHtmlPart(22)
expressionOut.print(createLink([action: 'download', id: obj.file.id, params: [name: obj.file.name, preview: true]]))
printHtmlPart(23)
}
else {
printHtmlPart(24)
expressionOut.print(obj.url)
printHtmlPart(25)
}
printHtmlPart(26)
expressionOut.print(obj.id)
printHtmlPart(27)
invokeTag('render','g',96,['template':("status"),'model':([status: obj.approved])],-1)
printHtmlPart(28)
expressionOut.print(obj.account.name)
printHtmlPart(29)
invokeTag('formatDate','g',102,['date':(obj.dateCreated),'format':("dd.MM.yyyy HH:mm:ss")],-1)
printHtmlPart(30)
}
printHtmlPart(31)
}
printHtmlPart(31)
i++
}
}
printHtmlPart(32)
if(true && (params.max < rowsCount)) {
printHtmlPart(31)
invokeTag('paginate','g',111,['total':(rowsCount ?: 0)],-1)
printHtmlPart(7)
}
printHtmlPart(33)
})
invokeTag('form','g',114,['id':("batch_form"),'url':([controller: 'adcontent', action: 'batch']),'method':("POST")],2)
printHtmlPart(34)
invokeTag('render','g',118,['template':("/_common/edit-container")],-1)
printHtmlPart(35)
invokeTag('render','g',119,['template':("/_common/gallery-multi")],-1)
printHtmlPart(36)
if(true && (!showAccount)) {
printHtmlPart(37)
}
printHtmlPart(7)
if(true && (!showFiles)) {
printHtmlPart(38)
}
printHtmlPart(39)
})
invokeTag('captureBody','sitemesh',163,[:],1)
printHtmlPart(40)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1478582585605L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
