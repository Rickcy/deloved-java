import ru.deloved.Dispute
import ru.deloved.DisputeStatus
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_disputeindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/dispute/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("admin")],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',7,['code':("dispute.list.label")],-1)
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
createTagBody(2, {->
printHtmlPart(5)
invokeTag('render','g',14,['template':("/_common/hint"),'model':([hintText: 'Для разрешения нового спора нужно выбрать спорную сделку.'])],-1)
printHtmlPart(6)
})
invokeTag('ifAnyGranted','sec',15,['roles':("ROLE_ACCOUNT")],2)
printHtmlPart(7)
invokeTag('message','g',16,['code':("dispute.list.label")],-1)
printHtmlPart(8)
invokeTag('render','g',18,['template':("/_common/flash-message")],-1)
printHtmlPart(9)
createTagBody(2, {->
printHtmlPart(10)
invokeTag('select','g',31,['class':("form-control"),'name':("status"),'from':(DisputeStatus.values()),'optionKey':("value"),'optionValue':({ message(code: 'dispute.status.' + it, default: it) }),'value':(filter.status),'noSelection':(['': message(code: 'dispute.status.allstatus.label')])],-1)
printHtmlPart(11)
invokeTag('textField','g',34,['placeholder':("Поиск"),'class':("form-control"),'name':("search"),'value':(filter.search)],-1)
printHtmlPart(12)
invokeTag('submitButton','g',37,['name':("filterAction"),'class':("btn btn-primary ft"),'value':(message(code: 'default.button.filter.label'))],-1)
printHtmlPart(13)
invokeTag('submitButton','g',38,['name':("resetAction"),'class':("btn btn-default ft"),'formaction':("reset"),'value':(message(code: 'default.button.filterReset.label'))],-1)
printHtmlPart(14)
})
invokeTag('form','g',43,['url':([controller: 'dispute', action: 'index'])],2)
printHtmlPart(15)
invokeTag('message','g',50,['code':("dispute.accounts.label"),'default':("Dispute")],-1)
printHtmlPart(16)
invokeTag('message','g',51,['code':("dispute.status.label"),'default':("Status")],-1)
printHtmlPart(17)
invokeTag('sortableColumn','g',52,['class':("ft"),'property':("dateCreated"),'title':(message(code: 'dispute.dateCreated.label', default: 'Date Created'))],-1)
printHtmlPart(18)
createClosureForHtmlPart(19, 2)
invokeTag('ifAnyGranted','sec',55,['roles':("ROLE_ADMIN")],2)
printHtmlPart(20)
loop:{
int i = 0
for( obj in (disputeInstanceList) ) {
printHtmlPart(21)
expressionOut.print(obj.id)
printHtmlPart(22)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(23)
expressionOut.print(i+1)
printHtmlPart(24)
createTagBody(3, {->
expressionOut.print(obj.account.name)
printHtmlPart(25)
expressionOut.print(obj.partner.name)
})
invokeTag('link','g',62,['id':(obj.id),'class':(userNewObjects.contains(obj)?"newItem":""),'controller':("dispute"),'action':("thread")],3)
printHtmlPart(26)
if(true && (userNewObjectPosts[obj])) {
printHtmlPart(27)
expressionOut.print(userNewObjectPosts[obj])
printHtmlPart(28)
}
printHtmlPart(29)
expressionOut.print(message(code: 'dispute.status.' + obj.status(), default: obj.status()))
printHtmlPart(26)
if(true && (userNewObjectStatuses[obj])) {
printHtmlPart(27)
expressionOut.print(userNewObjectStatuses[obj])
printHtmlPart(28)
}
printHtmlPart(29)
invokeTag('formatDate','g',72,['date':(obj.dateCreated),'format':("dd.MM.yyyy HH:mm:ss")],-1)
printHtmlPart(30)
createTagBody(3, {->
printHtmlPart(31)
expressionOut.print(obj.id)
printHtmlPart(32)
})
invokeTag('ifAnyGranted','sec',76,['roles':("ROLE_ADMIN")],3)
printHtmlPart(33)
i++
}
}
printHtmlPart(34)
if(true && (params.max < rowsCount)) {
printHtmlPart(35)
invokeTag('paginate','g',83,['total':(rowsCount ?: 0)],-1)
printHtmlPart(2)
}
printHtmlPart(36)
expressionOut.print(message(code: 'item.delete.confirm.'+params.categoryType?.code, default: 'Delete this item?'))
printHtmlPart(37)
expressionOut.print(createLink([controller: 'dispute', action: 'deletedisp']))
printHtmlPart(38)
})
invokeTag('captureBody','sitemesh',108,[:],1)
printHtmlPart(39)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1479273242225L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
