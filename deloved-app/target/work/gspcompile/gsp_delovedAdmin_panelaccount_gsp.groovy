import ru.deloved.Profile
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_panelaccount_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/panel/account.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("admin")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',6,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(4)
if(true && (lenta.size())) {
printHtmlPart(5)
loop:{
int i = 0
for( obj in (lenta) ) {
printHtmlPart(6)
invokeTag('set','g',21,['var':("eventDate"),'value':(obj.dateCreated)],-1)
printHtmlPart(6)
invokeTag('set','g',22,['var':("event"),'value':(obj)],-1)
printHtmlPart(6)
invokeTag('set','g',23,['var':("eventType"),'value':(obj.class.simpleName)],-1)
printHtmlPart(7)
createTagBody(4, {->
printHtmlPart(8)
if(true && (eventType == 'Dispute')) {
printHtmlPart(9)
invokeTag('formatDate','g',34,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(10)
createClosureForHtmlPart(11, 6)
invokeTag('link','g',35,['url':([resource: event, action:'thread'])],6)
printHtmlPart(12)
expressionOut.print(event.account.name)
printHtmlPart(8)
}
printHtmlPart(13)
})
invokeTag('ifAnyGranted','sec',37,['roles':("ROLE_MEDIATOR,ROLE_ADMIN")],4)
printHtmlPart(14)
createTagBody(4, {->
printHtmlPart(8)
if(true && (eventType == 'Claim')) {
printHtmlPart(9)
invokeTag('formatDate','g',41,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(10)
createClosureForHtmlPart(15, 6)
invokeTag('link','g',42,['url':([resource: event, action:'thread'])],6)
printHtmlPart(12)
expressionOut.print(event.account.name)
printHtmlPart(8)
}
printHtmlPart(13)
})
invokeTag('ifAnyGranted','sec',44,['roles':("ROLE_JUDGE,ROLE_ADMIN")],4)
printHtmlPart(14)
createTagBody(4, {->
printHtmlPart(8)
if(true && (eventType == 'Review')) {
printHtmlPart(9)
invokeTag('formatDate','g',48,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(16)
invokeTag('message','g',49,['code':('review.value.' + event.value)],-1)
printHtmlPart(17)
createClosureForHtmlPart(18, 6)
invokeTag('link','g',49,['url':([resource: event, action:'show'])],6)
printHtmlPart(12)
expressionOut.print(event.from.name)
printHtmlPart(8)
}
printHtmlPart(13)
})
invokeTag('ifAnyGranted','sec',51,['roles':("ROLE_MANAGER,ROLE_ADMIN")],4)
printHtmlPart(19)
createTagBody(4, {->
printHtmlPart(20)
if(true && (eventType == 'JuristConsult')) {
printHtmlPart(21)
invokeTag('formatDate','g',56,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(22)
createClosureForHtmlPart(23, 6)
invokeTag('link','g',57,['url':([resource: event, action: 'thread'])],6)
printHtmlPart(12)
expressionOut.print(event.account.name)
printHtmlPart(20)
}
printHtmlPart(9)
})
invokeTag('ifAnyGranted','sec',59,['roles':("ROLE_JURIST,ROLE_ADMIN")],4)
printHtmlPart(24)
createTagBody(4, {->
printHtmlPart(20)
if(true && (eventType == 'ConsultNewPost')) {
printHtmlPart(21)
invokeTag('formatDate','g',63,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(25)
createClosureForHtmlPart(26, 6)
invokeTag('link','g',65,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(27)
expressionOut.print(event.item.jurist == profile ? event.item.account.name : event.item.account.name)
printHtmlPart(28)
expressionOut.print(event.count)
printHtmlPart(29)
}
printHtmlPart(9)
})
invokeTag('ifAnyGranted','sec',68,['roles':("ROLE_JURIST,ROLE_ADMIN,ROLE_ACCOUNT")],4)
printHtmlPart(24)
createTagBody(4, {->
printHtmlPart(20)
if(true && (eventType == 'ConsultNewStatus')) {
printHtmlPart(21)
invokeTag('formatDate','g',72,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(30)
createClosureForHtmlPart(26, 6)
invokeTag('link','g',74,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(27)
expressionOut.print(event.item.jurist == profile ? event.item.account.name :event.item.account.name)
printHtmlPart(28)
expressionOut.print(event.count)
printHtmlPart(29)
}
printHtmlPart(9)
})
invokeTag('ifAnyGranted','sec',77,['roles':("ROLE_JURIST,ROLE_ADMIN,ROLE_ACCOUNT")],4)
printHtmlPart(31)
i++
}
}
printHtmlPart(32)
}
printHtmlPart(4)
})
invokeTag('captureBody','sitemesh',89,[:],1)
printHtmlPart(33)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1478751295245L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
