import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import  ru.deloved.Profile
import ru.deloved.DealPost
import ru.deloved.Account
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_panelindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/panel/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("admin")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
if(true && (defaultAccount)) {
printHtmlPart(0)
createTagBody(3, {->
printHtmlPart(0)
if(true && (profile.chargeTill)) {
printHtmlPart(6)
if(true && (profile.dateCreated.dateString==profile.getChargeTill().minus(31).dateString)) {
printHtmlPart(7)
expressionOut.print(profile.getChargeTill().dateString)
printHtmlPart(8)
}
printHtmlPart(9)
expressionOut.print(profile.dateCreated.dateString)
printHtmlPart(10)
expressionOut.print(profile.getChargeTill().minus(31).dateString)
printHtmlPart(11)
}
printHtmlPart(2)
if(true && (profile.chargeTill.date - new Date().date<=4&&profile.chargeTill.month-new Date().month==0&&profile.chargeTill.year-new Date().year==0)) {
printHtmlPart(12)
expressionOut.print(profile.chargeTill.date - new Date().date<=4&&profile.chargeTill.month-new Date().month==0&&profile.chargeTill.year-new Date().year==0)
printHtmlPart(13)
expressionOut.print(profile.chargeTill.date - new Date().date)
printHtmlPart(14)
createClosureForHtmlPart(15, 5)
invokeTag('link','g',37,['url':([controller: 'billing', action: 'index'])],5)
printHtmlPart(16)
}
printHtmlPart(0)
})
invokeTag('ifAnyGranted','sec',47,['roles':("ROLE_ACCOUNT")],3)
printHtmlPart(17)
createTagBody(3, {->
printHtmlPart(18)
if(true && (freeAccount)) {
printHtmlPart(19)
}
else {
printHtmlPart(20)
expressionOut.print(defaultAccount.rating ? defaultAccount.rating + '%' : '-')
printHtmlPart(21)
invokeTag('rating','g',69,['value':(defaultAccount.rating)],-1)
printHtmlPart(22)
expressionOut.print(defaultAccountStat.reviewsPositiveCount)
printHtmlPart(23)
expressionOut.print(defaultAccountStat.reviewsNegativeCount)
printHtmlPart(24)
expressionOut.print(defaultAccountStat.goodsCount)
printHtmlPart(25)
expressionOut.print(defaultAccountStat.servicesCount)
printHtmlPart(26)
expressionOut.print(defaultAccountStat.dealsFinishedCount)
printHtmlPart(27)
expressionOut.print(defaultAccountStat.dealsInProcessCount)
printHtmlPart(28)
expressionOut.print(defaultAccountStat.disputesInProcessCount)
printHtmlPart(29)
expressionOut.print(defaultAccountStat.disputesFailed)
printHtmlPart(30)
expressionOut.print(defaultAccountStat.claimsInProcessCount)
printHtmlPart(31)
expressionOut.print(defaultAccountStat.claimsFailed)
printHtmlPart(32)
}
printHtmlPart(33)
if(true && (freeAccount)) {
printHtmlPart(19)
}
else {
printHtmlPart(34)
expressionOut.print(defaultAccountStat.viewStat.monthViewAccount)
printHtmlPart(35)
expressionOut.print(defaultAccountStat.viewStat.monthViewGoods)
printHtmlPart(36)
expressionOut.print(defaultAccountStat.viewStat.monthViewServices)
printHtmlPart(37)
expressionOut.print(defaultAccountStat.viewStat.totalViewAccount)
printHtmlPart(35)
expressionOut.print(defaultAccountStat.viewStat.totalViewGoods)
printHtmlPart(36)
expressionOut.print(defaultAccountStat.viewStat.totalViewServices)
printHtmlPart(32)
}
printHtmlPart(38)
})
invokeTag('ifAnyGranted','sec',118,['roles':("ROLE_ACCOUNT")],3)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (lenta.size())) {
printHtmlPart(39)
loop:{
int i = 0
for( obj in (lenta) ) {
printHtmlPart(40)
invokeTag('set','g',136,['var':("eventDate"),'value':(obj.dateCreated)],-1)
printHtmlPart(40)
invokeTag('set','g',137,['var':("event"),'value':(obj)],-1)
printHtmlPart(40)
invokeTag('set','g',138,['var':("eventType"),'value':(obj.class.simpleName)],-1)
printHtmlPart(41)
createTagBody(4, {->
printHtmlPart(42)
if(true && (eventType == 'Deal')) {
printHtmlPart(43)
invokeTag('formatDate','g',146,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(44)
createClosureForHtmlPart(45, 6)
invokeTag('link','g',148,['url':([resource: event, action: 'thread'])],6)
printHtmlPart(46)
expressionOut.print(event.account == (accounts ?: event.account) ? event.partner.name : event.account.name)
printHtmlPart(42)
}
printHtmlPart(42)
if(true && (eventType == 'DealNewPost')) {
printHtmlPart(43)
invokeTag('formatDate','g',151,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(47)
createClosureForHtmlPart(48, 6)
invokeTag('link','g',153,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(46)
expressionOut.print(event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name)
printHtmlPart(49)
expressionOut.print(event.count)
printHtmlPart(50)
}
printHtmlPart(42)
if(true && (eventType == 'DealNewStatus')) {
printHtmlPart(43)
invokeTag('formatDate','g',157,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(51)
createClosureForHtmlPart(48, 6)
invokeTag('link','g',159,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(46)
expressionOut.print(event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name)
printHtmlPart(49)
expressionOut.print(event.count)
printHtmlPart(50)
}
printHtmlPart(42)
})
invokeTag('ifAnyGranted','sec',162,['roles':("ROLE_ACCOUNT")],4)
printHtmlPart(52)
createTagBody(4, {->
printHtmlPart(42)
if(true && (eventType == 'Dispute')) {
printHtmlPart(43)
invokeTag('formatDate','g',166,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(53)
createClosureForHtmlPart(54, 6)
invokeTag('link','g',167,['url':([resource: event, action: 'thread'])],6)
printHtmlPart(55)
expressionOut.print(event.account.name)
printHtmlPart(42)
}
printHtmlPart(40)
})
invokeTag('ifAnyGranted','sec',169,['roles':("ROLE_MEDIATOR,ROLE_ADMIN")],4)
printHtmlPart(52)
createTagBody(4, {->
printHtmlPart(42)
if(true && (eventType == 'DisputeNewPost')) {
printHtmlPart(43)
invokeTag('formatDate','g',173,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(47)
createClosureForHtmlPart(56, 6)
invokeTag('link','g',175,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(46)
expressionOut.print(event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name)
printHtmlPart(49)
expressionOut.print(event.count)
printHtmlPart(50)
}
printHtmlPart(40)
})
invokeTag('ifAnyGranted','sec',178,['roles':("ROLE_MEDIATOR,ROLE_ADMIN,ROLE_ACCOUNT")],4)
printHtmlPart(52)
createTagBody(4, {->
printHtmlPart(42)
if(true && (eventType == 'DisputeNewStatus')) {
printHtmlPart(43)
invokeTag('formatDate','g',182,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(51)
createClosureForHtmlPart(56, 6)
invokeTag('link','g',184,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(46)
expressionOut.print(event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name)
printHtmlPart(49)
expressionOut.print(event.count)
printHtmlPart(50)
}
printHtmlPart(42)
})
invokeTag('ifAnyGranted','sec',187,['roles':("ROLE_MEDIATOR,ROLE_ADMIN,ROLE_ACCOUNT")],4)
printHtmlPart(52)
createTagBody(4, {->
printHtmlPart(42)
if(true && (eventType == 'Claim')) {
printHtmlPart(43)
invokeTag('formatDate','g',191,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(57)
createClosureForHtmlPart(58, 6)
invokeTag('link','g',192,['url':([resource: event, action: 'thread'])],6)
printHtmlPart(55)
expressionOut.print(event.account.name)
printHtmlPart(42)
}
printHtmlPart(40)
})
invokeTag('ifAnyGranted','sec',194,['roles':("ROLE_JUDGE,ROLE_ADMIN")],4)
printHtmlPart(59)
createTagBody(4, {->
printHtmlPart(60)
if(true && (eventType == 'Item')) {
printHtmlPart(43)
invokeTag('formatDate','g',203,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(61)
createTagBody(6, {->
printHtmlPart(62)
if(true && (event.categoryType.code=='GOOD')) {
printHtmlPart(63)
}
else {
printHtmlPart(64)
}
printHtmlPart(62)
})
invokeTag('link','g',211,['url':([resource: event, action: 'edit'])],6)
printHtmlPart(55)
expressionOut.print(event.account.name)
printHtmlPart(60)
}
printHtmlPart(42)
if(true && (eventType == 'Account')) {
printHtmlPart(43)
invokeTag('formatDate','g',215,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(65)
createClosureForHtmlPart(66, 6)
invokeTag('link','g',216,['url':([resource: event, action: 'edit'])],6)
printHtmlPart(52)
}
printHtmlPart(67)
})
invokeTag('ifAnyGranted','sec',219,['roles':("ROLE_MANAGER,ROLE_ADMIN")],4)
printHtmlPart(68)
createTagBody(4, {->
printHtmlPart(42)
if(true && (eventType == 'ClaimNewPost')) {
printHtmlPart(43)
invokeTag('formatDate','g',229,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(47)
createClosureForHtmlPart(69, 6)
invokeTag('link','g',231,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(46)
expressionOut.print(event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name)
printHtmlPart(49)
expressionOut.print(event.count)
printHtmlPart(50)
}
printHtmlPart(40)
})
invokeTag('ifAnyGranted','sec',233,['roles':("ROLE_JUDGE,ROLE_ADMIN,ROLE_ACCOUNT")],4)
printHtmlPart(70)
createTagBody(4, {->
printHtmlPart(42)
if(true && (eventType == 'ClaimNewStatus')) {
printHtmlPart(43)
invokeTag('formatDate','g',238,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(51)
createClosureForHtmlPart(69, 6)
invokeTag('link','g',240,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(46)
expressionOut.print(event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name)
printHtmlPart(49)
expressionOut.print(event.count)
printHtmlPart(50)
}
printHtmlPart(40)
})
invokeTag('ifAnyGranted','sec',242,['roles':("ROLE_JUDGE,ROLE_ADMIN,ROLE_ACCOUNT")],4)
printHtmlPart(70)
createTagBody(4, {->
printHtmlPart(42)
if(true && (eventType == 'Review')) {
printHtmlPart(43)
invokeTag('formatDate','g',247,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(57)
invokeTag('message','g',249,['code':('review.value.' + event.value)],-1)
printHtmlPart(71)
createClosureForHtmlPart(72, 6)
invokeTag('link','g',249,['url':([resource: event, action: 'show'])],6)
printHtmlPart(55)
expressionOut.print(event.from.name)
printHtmlPart(42)
}
printHtmlPart(40)
})
invokeTag('ifAnyGranted','sec',250,['roles':("ROLE_MANAGER,ROLE_ADMIN,ROLE_ACCOUNT")],4)
printHtmlPart(70)
createTagBody(4, {->
printHtmlPart(42)
if(true && (eventType == 'JuristConsult')) {
printHtmlPart(43)
invokeTag('formatDate','g',254,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(73)
createClosureForHtmlPart(74, 6)
invokeTag('link','g',256,['url':([resource: event, action: 'thread'])],6)
printHtmlPart(55)
expressionOut.print(event.account.name)
printHtmlPart(42)
}
printHtmlPart(40)
})
invokeTag('ifAnyGranted','sec',256,['roles':("ROLE_JURIST,ROLE_ADMIN")],4)
printHtmlPart(52)
createTagBody(4, {->
printHtmlPart(42)
if(true && (eventType == 'ConsultNewPost')) {
printHtmlPart(43)
invokeTag('formatDate','g',261,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(47)
createClosureForHtmlPart(75, 6)
invokeTag('link','g',263,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(46)
expressionOut.print(event.item.jurist == profile ? event.item.account.name : event.item.account.name)
printHtmlPart(49)
expressionOut.print(event.count)
printHtmlPart(50)
}
printHtmlPart(42)
})
invokeTag('ifAnyGranted','sec',265,['roles':("ROLE_JURIST,ROLE_ADMIN,ROLE_ACCOUNT")],4)
printHtmlPart(52)
createTagBody(4, {->
printHtmlPart(42)
if(true && (eventType == 'ConsultNewStatus')) {
printHtmlPart(43)
invokeTag('formatDate','g',270,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(51)
createClosureForHtmlPart(75, 6)
invokeTag('link','g',272,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(46)
expressionOut.print(event.item.jurist == profile ? event.item.account.name :event.item.account.name)
printHtmlPart(49)
expressionOut.print(event.count)
printHtmlPart(50)
}
printHtmlPart(42)
})
invokeTag('ifAnyGranted','sec',274,['roles':("ROLE_JURIST,ROLE_ADMIN,ROLE_ACCOUNT")],4)
printHtmlPart(52)
createTagBody(4, {->
printHtmlPart(42)
if(true && (eventType == 'TicketNewPost')) {
printHtmlPart(43)
invokeTag('formatDate','g',279,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(76)
createClosureForHtmlPart(77, 6)
invokeTag('link','g',281,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(49)
expressionOut.print(event.count)
printHtmlPart(50)
}
printHtmlPart(52)
})
invokeTag('ifAnyGranted','sec',283,['roles':("ROLE_MANAGER,ROLE_ADMIN,ROLE_ACCOUNT")],4)
printHtmlPart(42)
createTagBody(4, {->
printHtmlPart(78)
if(true && (eventType == 'TicketNewStatus')) {
printHtmlPart(79)
invokeTag('formatDate','g',288,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(80)
createClosureForHtmlPart(81, 6)
invokeTag('link','g',290,['url':([resource: event.item, action: 'thread'])],6)
printHtmlPart(82)
expressionOut.print(event.count)
printHtmlPart(83)
}
printHtmlPart(52)
})
invokeTag('ifAnyGranted','sec',292,['roles':("ROLE_MANAGER,ROLE_ADMIN,ROLE_ACCOUNT")],4)
printHtmlPart(42)
createTagBody(4, {->
printHtmlPart(78)
if(true && (eventType == 'Ticket')) {
printHtmlPart(79)
invokeTag('formatDate','g',297,['date':(eventDate),'format':("dd MMMM yyyy")],-1)
printHtmlPart(80)
createClosureForHtmlPart(84, 6)
invokeTag('link','g',299,['url':([resource: event, action: 'thread'])],6)
printHtmlPart(55)
expressionOut.print(event.account.name)
printHtmlPart(78)
}
printHtmlPart(52)
})
invokeTag('ifAnyGranted','sec',299,['roles':("ROLE_MANAGER,ROLE_ADMIN")],4)
printHtmlPart(85)
i++
}
}
printHtmlPart(86)
}
printHtmlPart(4)
})
invokeTag('captureBody','sitemesh',300,[:],1)
printHtmlPart(87)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1484812365101L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
