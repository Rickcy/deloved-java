import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_delovedApp_layouts_menu_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/_menu.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
createTagBody(1, {->
printHtmlPart(0)
if(true && (lenta.size())) {
printHtmlPart(1)
loop:{
int i = 0
for( obj in (lenta) ) {
printHtmlPart(2)
invokeTag('set','g',15,['var':("eventDate"),'value':(obj.dateCreated)],-1)
printHtmlPart(2)
invokeTag('set','g',16,['var':("event"),'value':(obj)],-1)
printHtmlPart(2)
invokeTag('set','g',17,['var':("eventType"),'value':(obj.class.simpleName)],-1)
printHtmlPart(3)
invokeTag('formatDate','g',19,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(4)
createTagBody(4, {->
printHtmlPart(5)
if(true && (eventType == 'Deal')) {
printHtmlPart(6)
createClosureForHtmlPart(7, 6)
invokeTag('link','g',23,['url':([resource: event, action: 'thread'])],6)
printHtmlPart(8)
expressionOut.print(event.account == (accounts ?: event.account) ? event.partner.name : event.account.name)
printHtmlPart(5)
}
printHtmlPart(5)
if(true && (eventType == 'DealNewPost')) {
printHtmlPart(9)
createClosureForHtmlPart(10, 6)
invokeTag('link','g',27,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(8)
expressionOut.print(event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name)
printHtmlPart(11)
expressionOut.print(event.count)
printHtmlPart(12)
}
printHtmlPart(5)
if(true && (eventType == 'DealNewStatus')) {
printHtmlPart(13)
createClosureForHtmlPart(10, 6)
invokeTag('link','g',32,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(8)
expressionOut.print(event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name)
printHtmlPart(11)
expressionOut.print(event.count)
printHtmlPart(12)
}
printHtmlPart(14)
})
invokeTag('ifAnyGranted','sec',35,['roles':("ROLE_ACCOUNT")],4)
printHtmlPart(14)
createTagBody(4, {->
printHtmlPart(5)
if(true && (eventType == 'Dispute')) {
printHtmlPart(15)
createClosureForHtmlPart(16, 6)
invokeTag('link','g',38,['url':([resource: event, action: 'thread'])],6)
printHtmlPart(17)
expressionOut.print(event.account.name)
printHtmlPart(5)
}
printHtmlPart(14)
})
invokeTag('ifAnyGranted','sec',40,['roles':("ROLE_MEDIATOR,ROLE_ADMIN")],4)
printHtmlPart(14)
createTagBody(4, {->
printHtmlPart(5)
if(true && (eventType == 'DisputeNewPost')) {
printHtmlPart(9)
createClosureForHtmlPart(18, 6)
invokeTag('link','g',44,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(8)
expressionOut.print(event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name)
printHtmlPart(11)
expressionOut.print(event.count)
printHtmlPart(12)
}
printHtmlPart(14)
})
invokeTag('ifAnyGranted','sec',47,['roles':("ROLE_MEDIATOR,ROLE_ADMIN,ROLE_ACCOUNT")],4)
printHtmlPart(14)
createTagBody(4, {->
printHtmlPart(5)
if(true && (eventType == 'DisputeNewStatus')) {
printHtmlPart(13)
createClosureForHtmlPart(18, 6)
invokeTag('link','g',51,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(8)
expressionOut.print(event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name)
printHtmlPart(11)
expressionOut.print(event.count)
printHtmlPart(12)
}
printHtmlPart(14)
})
invokeTag('ifAnyGranted','sec',54,['roles':("ROLE_MEDIATOR,ROLE_ADMIN,ROLE_ACCOUNT")],4)
printHtmlPart(14)
createTagBody(4, {->
printHtmlPart(5)
if(true && (eventType == 'Claim')) {
printHtmlPart(19)
createClosureForHtmlPart(20, 6)
invokeTag('link','g',57,['url':([resource: event, action: 'thread'])],6)
printHtmlPart(17)
expressionOut.print(event.account.name)
printHtmlPart(5)
}
printHtmlPart(14)
})
invokeTag('ifAnyGranted','sec',59,['roles':("ROLE_JUDGE,ROLE_ADMIN")],4)
printHtmlPart(14)
createTagBody(4, {->
printHtmlPart(5)
if(true && (eventType == 'NewItem')) {
printHtmlPart(19)
createClosureForHtmlPart(21, 6)
invokeTag('link','g',62,['url':([resource: event, action: 'edit'])],6)
printHtmlPart(17)
expressionOut.print(event.account.name)
printHtmlPart(5)
}
printHtmlPart(5)
if(true && (eventType == 'Account')) {
printHtmlPart(22)
createClosureForHtmlPart(23, 6)
invokeTag('link','g',65,['url':([resource: event, action: 'edit'])],6)
printHtmlPart(24)
}
printHtmlPart(25)
})
invokeTag('ifAnyGranted','sec',69,['roles':("ROLE_MANAGER,ROLE_ADMIN")],4)
printHtmlPart(14)
createTagBody(4, {->
printHtmlPart(5)
if(true && (eventType == 'ClaimNewPost')) {
printHtmlPart(9)
createClosureForHtmlPart(26, 6)
invokeTag('link','g',74,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(8)
expressionOut.print(event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name)
printHtmlPart(11)
expressionOut.print(event.count)
printHtmlPart(12)
}
printHtmlPart(14)
})
invokeTag('ifAnyGranted','sec',76,['roles':("ROLE_JUDGE,ROLE_ADMIN,ROLE_ACCOUNT")],4)
printHtmlPart(14)
createTagBody(4, {->
printHtmlPart(5)
if(true && (eventType == 'ClaimNewStatus')) {
printHtmlPart(13)
createClosureForHtmlPart(26, 6)
invokeTag('link','g',81,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(8)
expressionOut.print(event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name)
printHtmlPart(11)
expressionOut.print(event.count)
printHtmlPart(12)
}
printHtmlPart(14)
})
invokeTag('ifAnyGranted','sec',83,['roles':("ROLE_JUDGE,ROLE_ADMIN,ROLE_ACCOUNT")],4)
printHtmlPart(14)
createTagBody(4, {->
printHtmlPart(5)
if(true && (eventType == 'Review')) {
printHtmlPart(19)
invokeTag('message','g',87,['code':('review.value.' + event.value)],-1)
printHtmlPart(27)
createClosureForHtmlPart(28, 6)
invokeTag('link','g',88,['url':([resource: event, action: 'show'])],6)
printHtmlPart(17)
expressionOut.print(event.from.name)
printHtmlPart(5)
}
printHtmlPart(14)
})
invokeTag('ifAnyGranted','sec',88,['roles':("ROLE_MANAGER,ROLE_ADMIN,ROLE_ACCOUNT")],4)
printHtmlPart(14)
createTagBody(4, {->
printHtmlPart(5)
if(true && (eventType == 'Consult')) {
printHtmlPart(29)
createClosureForHtmlPart(30, 6)
invokeTag('link','g',92,['url':([resource: event, action: 'thread'])],6)
printHtmlPart(17)
expressionOut.print(event.account.name)
printHtmlPart(5)
}
printHtmlPart(14)
})
invokeTag('ifAnyGranted','sec',93,['roles':("ROLE_JURIST,ROLE_ADMIN")],4)
printHtmlPart(14)
createTagBody(4, {->
printHtmlPart(5)
if(true && (eventType == 'ConsultNewPost')) {
printHtmlPart(9)
createClosureForHtmlPart(31, 6)
invokeTag('link','g',98,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(8)
expressionOut.print(event.item.jurist == profile ? event.item.account.name : event.item.account.name)
printHtmlPart(11)
expressionOut.print(event.count)
printHtmlPart(12)
}
printHtmlPart(14)
})
invokeTag('ifAnyGranted','sec',100,['roles':("ROLE_JURIST,ROLE_ADMIN,ROLE_ACCOUNT")],4)
printHtmlPart(32)
createTagBody(4, {->
printHtmlPart(5)
if(true && (eventType == 'ConsultNewStatus')) {
printHtmlPart(13)
createClosureForHtmlPart(31, 6)
invokeTag('link','g',106,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(8)
expressionOut.print(event.item.jurist == profile ? event.item.account.name :event.item.account.name)
printHtmlPart(11)
expressionOut.print(event.count)
printHtmlPart(12)
}
printHtmlPart(14)
})
invokeTag('ifAnyGranted','sec',108,['roles':("ROLE_JURIST,ROLE_ADMIN,ROLE_ACCOUNT")],4)
printHtmlPart(32)
createTagBody(4, {->
printHtmlPart(5)
if(true && (eventType == 'TicketNewPost')) {
printHtmlPart(33)
createClosureForHtmlPart(34, 6)
invokeTag('link','g',114,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(11)
expressionOut.print(event.count)
printHtmlPart(12)
}
printHtmlPart(32)
})
invokeTag('ifAnyGranted','sec',116,['roles':("ROLE_MANAGER,ROLE_ADMIN,ROLE_ACCOUNT")],4)
printHtmlPart(14)
createTagBody(4, {->
printHtmlPart(5)
if(true && (eventType == 'TicketNewStatus')) {
printHtmlPart(35)
createClosureForHtmlPart(34, 6)
invokeTag('link','g',122,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(11)
expressionOut.print(event.count)
printHtmlPart(12)
}
printHtmlPart(32)
})
invokeTag('ifAnyGranted','sec',124,['roles':("ROLE_MANAGER,ROLE_ADMIN,ROLE_ACCOUNT")],4)
printHtmlPart(36)
i++
}
}
printHtmlPart(37)
}
printHtmlPart(38)
})
invokeTag('ifAnyGranted','sec',127,['roles':("ROLE_ACCOUNT,ROLE_ADMIN,ROLE_MEDIATOR,ROLE_JURIST,ROLE_JUDGE")],1)
printHtmlPart(39)
createTagBody(1, {->
printHtmlPart(40)
invokeTag('form','g',140,['id':("logoutForm"),'url':([controller: 'logout']),'method':("POST")],-1)
printHtmlPart(40)
createClosureForHtmlPart(41, 2)
invokeTag('link','g',143,['style':("margin-right: 5%;color: black;font-size: 12pt"),'controller':("panel")],2)
printHtmlPart(42)
})
invokeTag('ifLoggedIn','sec',148,[:],1)
printHtmlPart(43)
createTagBody(1, {->
printHtmlPart(40)
createClosureForHtmlPart(44, 2)
invokeTag('link','g',151,['class':("reg"),'style':("margin-right: 5%;color: black;font-size: 12pt"),'controller':("signup"),'action':("create")],2)
printHtmlPart(40)
createClosureForHtmlPart(45, 2)
invokeTag('link','g',152,['controller':("panel"),'style':("margin-right: 5%;color: black;font-size: 12pt")],2)
printHtmlPart(43)
})
invokeTag('ifNotLoggedIn','sec',152,[:],1)
printHtmlPart(46)
invokeTag('render','g',157,['template':("/_common/region-filter"),'model':([
                    regionFilterData: regionFilterData
            ])],-1)
printHtmlPart(47)
createTagBody(1, {->
printHtmlPart(48)
expressionOut.print(resource(dir: 'images', file: 'front/logo.gif'))
printHtmlPart(49)
})
invokeTag('link','g',171,['controller':("front")],1)
printHtmlPart(50)
createClosureForHtmlPart(51, 1)
invokeTag('set','g',171,['var':("section")],1)
printHtmlPart(40)
if(true && (params.controller in ['companies', 'company'])) {
printHtmlPart(52)
createClosureForHtmlPart(51, 2)
invokeTag('set','g',175,['var':("section")],2)
printHtmlPart(40)
}
else if(true && (params.controller in ['goods'])) {
printHtmlPart(52)
createClosureForHtmlPart(53, 2)
invokeTag('set','g',177,['var':("section")],2)
printHtmlPart(40)
}
else if(true && (params.controller in ['services'])) {
printHtmlPart(52)
createClosureForHtmlPart(54, 2)
invokeTag('set','g',180,['var':("section")],2)
printHtmlPart(40)
}
printHtmlPart(55)
createClosureForHtmlPart(56, 1)
invokeTag('link','g',187,['class':((section == 'companies') ? 'active' : ''),'url':([controller: 'companies'])],1)
printHtmlPart(52)
createClosureForHtmlPart(57, 1)
invokeTag('link','g',188,['class':((section == 'goods') ? 'active' : ''),'url':([controller: 'goods'])],1)
printHtmlPart(52)
createClosureForHtmlPart(58, 1)
invokeTag('link','g',189,['class':((section == 'services') ? 'active' : ''),'url':([controller: 'services'])],1)
printHtmlPart(52)
createClosureForHtmlPart(59, 1)
invokeTag('link','g',190,['class':("about"),'url':([controller: 'front', action:'about'])],1)
printHtmlPart(60)
createTagBody(1, {->
printHtmlPart(61)
invokeTag('textField','g',200,['required':(""),'name':("search"),'class':("form-control"),'style':("height: 45px;font-size: 15pt;box-shadow: inset 0 0 15px #e2e2e2"),'value':(""),'placeholder':("Поиск")],-1)
printHtmlPart(62)
})
invokeTag('form','g',202,['controller':(section),'action':("search"),'method':("POST")],1)
printHtmlPart(63)
createClosureForHtmlPart(64, 1)
invokeTag('link','g',207,['controller':(section),'action':("search"),'class':("search_link")],1)
printHtmlPart(65)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1478838505651L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
