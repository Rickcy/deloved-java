import ru.deloved.recall.TicketStatus
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_ticketindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/ticket/index.gsp" }
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
invokeTag('message','g',5,['code':("ticket.list.label")],-1)
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
invokeTag('message','g',12,['code':("ticket.list.label")],-1)
printHtmlPart(5)
invokeTag('render','g',14,['template':("/_common/flash-message")],-1)
printHtmlPart(3)
createTagBody(2, {->
printHtmlPart(6)
createTagBody(3, {->
invokeTag('message','g',18,['code':("ticket.button.new.label")],-1)
})
invokeTag('link','g',18,['class':("btn btn-success ft"),'action':("create")],3)
printHtmlPart(7)
})
invokeTag('ifAnyGranted','sec',20,['roles':("ROLE_ACCOUNT,ROLE_MEDIATOR,ROLE_JUDGE,ROLE_JURIST")],2)
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
invokeTag('select','g',33,['class':("form-control"),'name':("status"),'from':(TicketStatus.values()),'optionKey':("value"),'optionValue':({ message(code: 'ticket.status.' + it, default: it) }),'value':(filter.status),'noSelection':(['': message(code: 'ticket.status.allstatus.label')])],-1)
printHtmlPart(10)
invokeTag('textField','g',36,['placeholder':("Поиск"),'class':("form-control"),'name':("search"),'value':(filter.search)],-1)
printHtmlPart(11)
invokeTag('submitButton','g',39,['name':("filterAction"),'class':("btn btn-primary ft"),'value':(message(code: 'default.button.filter.label'))],-1)
printHtmlPart(12)
invokeTag('submitButton','g',40,['name':("resetAction"),'class':("btn btn-default ft"),'formaction':("reset"),'value':(message(code: 'default.button.filterReset.label'))],-1)
printHtmlPart(13)
})
invokeTag('form','g',45,['url':([controller: 'ticket', action: 'index'])],2)
printHtmlPart(14)
invokeTag('message','g',51,['code':("ticket.accounts.label"),'default':("Ticket")],-1)
printHtmlPart(15)
invokeTag('message','g',52,['code':("ticket.status.label"),'default':("Статус")],-1)
printHtmlPart(16)
invokeTag('sortableColumn','g',53,['class':("ft"),'property':("dateCreated"),'title':(message(code: 'ticket.dateCreated.label', default: 'Дата создания'))],-1)
printHtmlPart(17)
createClosureForHtmlPart(18, 2)
invokeTag('ifAnyGranted','sec',57,['roles':("ROLE_ADMIN")],2)
printHtmlPart(19)
loop:{
int i = 0
for( obj in (ticketInstanceList) ) {
printHtmlPart(20)
expressionOut.print(obj.id)
printHtmlPart(21)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(22)
expressionOut.print(i+1)
printHtmlPart(23)
createTagBody(3, {->
expressionOut.print(obj.account.name)
})
invokeTag('link','g',64,['id':(obj.id),'class':(userNewObjects.contains(obj) ? "newItem" : ""),'controller':("ticket"),'action':("thread")],3)
printHtmlPart(24)
if(true && (userNewObjectPosts[obj])) {
printHtmlPart(25)
expressionOut.print(userNewObjectPosts[obj])
printHtmlPart(26)
}
printHtmlPart(27)
expressionOut.print(message(code: 'ticket.status.' + obj.status(), default: obj.status()))
printHtmlPart(24)
if(true && (userNewObjectStatuses[obj])) {
printHtmlPart(25)
expressionOut.print(userNewObjectStatuses[obj])
printHtmlPart(26)
}
printHtmlPart(27)
invokeTag('formatDate','g',74,['date':(obj.dateCreated),'format':("dd.MM.yyyy HH:mm:ss")],-1)
printHtmlPart(28)
createTagBody(3, {->
printHtmlPart(29)
expressionOut.print(obj.id)
printHtmlPart(30)
})
invokeTag('ifAnyGranted','sec',78,['roles':("ROLE_ADMIN")],3)
printHtmlPart(31)
i++
}
}
printHtmlPart(32)
if(true && (params.max < rowsCount)) {
printHtmlPart(33)
invokeTag('paginate','g',86,['total':(rowsCount ?: 0)],-1)
printHtmlPart(1)
}
printHtmlPart(34)
expressionOut.print(message(code: 'item.delete.confirm.'+params.categoryType?.code, default: 'Delete this item?'))
printHtmlPart(35)
expressionOut.print(createLink([controller: 'ticket', action: 'deleteTick']))
printHtmlPart(36)
})
invokeTag('captureBody','sitemesh',111,[:],1)
printHtmlPart(37)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1479273471746L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
