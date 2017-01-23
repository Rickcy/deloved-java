import ru.deloved.ConsultStatus
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_consultindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/consult/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("admin")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',5,['code':("consult.list.label")],-1)
})
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',6,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',12,['code':("consult.list.label")],-1)
printHtmlPart(5)
invokeTag('render','g',14,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(7)
createTagBody(3, {->
invokeTag('message','g',18,['code':("consult.button.new.label")],-1)
})
invokeTag('link','g',18,['class':("btn btn-success ft"),'action':("create")],3)
printHtmlPart(8)
})
invokeTag('ifAnyGranted','sec',20,['roles':("ROLE_ACCOUNT")],2)
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(9)
invokeTag('select','g',31,['class':("form-control"),'name':("status"),'from':(ConsultStatus.values()),'optionKey':("value"),'optionValue':({ message(code: 'consult.status.' + it, default: it) }),'value':(filter.status),'noSelection':(['': message(code: 'consult.status.allstatus.label')])],-1)
printHtmlPart(10)
invokeTag('textField','g',34,['placeholder':("Поиск"),'class':("form-control"),'name':("search"),'value':(filter.search)],-1)
printHtmlPart(11)
invokeTag('submitButton','g',37,['name':("filterAction"),'class':("btn btn-primary ft"),'value':(message(code: 'default.button.filter.label'))],-1)
printHtmlPart(12)
invokeTag('submitButton','g',38,['name':("resetAction"),'class':("btn btn-default ft"),'formaction':("reset"),'value':(message(code: 'default.button.filterReset.label'))],-1)
printHtmlPart(13)
})
invokeTag('form','g',43,['url':([controller: 'consult', action: 'index'])],2)
printHtmlPart(14)
invokeTag('message','g',49,['code':("consult.accounts.label"),'default':("Consult")],-1)
printHtmlPart(15)
invokeTag('message','g',50,['code':("consult.status.label"),'default':("Статус")],-1)
printHtmlPart(16)
invokeTag('sortableColumn','g',51,['class':("ft"),'property':("dateCreated"),'title':(message(code: 'consult.dateCreated.label', default: 'Дата создания'))],-1)
printHtmlPart(17)
loop:{
int i = 0
for( obj in (consultInstanceList) ) {
printHtmlPart(18)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(19)
expressionOut.print(i+1)
printHtmlPart(20)
createTagBody(3, {->
expressionOut.print(obj.account.name)
})
invokeTag('link','g',58,['id':(obj.id),'class':(userNewObjects.contains(obj) ? "newItem" : ""),'controller':("consult"),'action':("thread")],3)
printHtmlPart(21)
if(true && (userNewObjectPosts[obj])) {
printHtmlPart(22)
expressionOut.print(userNewObjectPosts[obj])
printHtmlPart(23)
}
printHtmlPart(24)
expressionOut.print(message(code: 'consult.status.' + obj.status(), default: obj.status()))
printHtmlPart(21)
if(true && (userNewObjectStatuses[obj])) {
printHtmlPart(22)
expressionOut.print(userNewObjectStatuses[obj])
printHtmlPart(23)
}
printHtmlPart(24)
invokeTag('formatDate','g',68,['date':(obj.dateCreated),'format':("dd.MM.yyyy HH:mm:ss")],-1)
printHtmlPart(25)
i++
}
}
printHtmlPart(26)
if(true && (params.max < rowsCount)) {
printHtmlPart(27)
invokeTag('paginate','g',75,['total':(rowsCount ?: 0)],-1)
printHtmlPart(1)
}
printHtmlPart(28)
})
invokeTag('captureBody','sitemesh',79,[:],1)
printHtmlPart(29)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1479273252191L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
