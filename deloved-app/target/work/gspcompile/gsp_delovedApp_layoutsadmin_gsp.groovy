import ru.deloved.Account
import org.codehaus.groovy.grails.plugins.jquery.JQueryConfig
import  ru.deloved.Account
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_delovedApp_layoutsadmin_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/admin.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('layoutTitle','g',6,[:],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',7,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("viewport"),'content':("width=device-width, initial-scale=1.0")],-1)
printHtmlPart(3)
invokeTag('stylesheet','asset',16,['src':("admin.css")],-1)
printHtmlPart(2)
invokeTag('stylesheet','asset',17,['src':("deloved.css")],-1)
printHtmlPart(2)
invokeTag('javascript','asset',18,['src':("front.js")],-1)
printHtmlPart(2)
invokeTag('javascript','asset',19,['src':("application.js")],-1)
printHtmlPart(2)
invokeTag('javascript','asset',20,['src':("/mCustomScrollbar/jquery.mCustomScrollbar.concat.min.js")],-1)
printHtmlPart(4)
expressionOut.print(createLinkTo(dir: 'images', file: 'favicon/favicon.ico'))
printHtmlPart(5)
invokeTag('layoutHead','g',22,[:],-1)
printHtmlPart(6)
})
invokeTag('captureHead','sitemesh',25,[:],1)
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(7)
invokeTag('render','g',29,['template':("/_common/flash-message")],-1)
printHtmlPart(7)
invokeTag('render','g',31,['template':("/_common/activation-warning")],-1)
printHtmlPart(7)
invokeTag('render','g',33,['template':("/layouts/navigation-bar")],-1)
printHtmlPart(8)
expressionOut.print(createLink(controller: "panel"))
printHtmlPart(9)
invokeTag('message','g',45,['code':("default.home.label")],-1)
printHtmlPart(10)
createTagBody(2, {->
printHtmlPart(11)
if(true && (params.controller == 'category')) {
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(3, {->
invokeTag('message','g',58,['code':("category.list.label")],-1)
})
invokeTag('link','g',58,['controller':("category")],3)
printHtmlPart(14)
if(true && (params.controller == 'measure')) {
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(3, {->
invokeTag('message','g',59,['code':("measure.list.label")],-1)
})
invokeTag('link','g',59,['controller':("measure")],3)
printHtmlPart(14)
if(true && (params.controller == 'region')) {
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(3, {->
invokeTag('message','g',60,['code':("region.list.label")],-1)
})
invokeTag('link','g',60,['controller':("region")],3)
printHtmlPart(14)
if(true && (params.controller == 'content')) {
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(3, {->
invokeTag('message','g',61,['code':("content.list.label")],-1)
})
invokeTag('link','g',61,['controller':("content")],3)
printHtmlPart(14)
if(true && (params.controller == 'systemCurrency')) {
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(3, {->
invokeTag('message','g',62,['code':("currency.list.label")],-1)
})
invokeTag('link','g',62,['controller':("systemCurrency")],3)
printHtmlPart(14)
if(true && (params.controller == 'org')) {
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(3, {->
invokeTag('message','g',63,['code':("org.list.label2")],-1)
})
invokeTag('link','g',63,['controller':("org")],3)
printHtmlPart(15)
createTagBody(3, {->
printHtmlPart(16)
if(true && (params.controller == 'tariffPrice')) {
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(4, {->
invokeTag('message','g',65,['code':("tariff.list.label")],-1)
})
invokeTag('link','g',65,['controller':("tariffPrice")],4)
printHtmlPart(15)
})
invokeTag('ifAnyGranted','sec',66,['roles':("ROLE_ADMIN")],3)
printHtmlPart(17)
})
invokeTag('ifAnyGranted','sec',68,['roles':("ROLE_ADMIN")],2)
printHtmlPart(18)
createTagBody(2, {->
printHtmlPart(19)
if(true && (params.controller == 'suggestion')) {
printHtmlPart(12)
}
printHtmlPart(13)
createClosureForHtmlPart(20, 3)
invokeTag('link','g',77,['controller':("suggestion")],3)
printHtmlPart(15)
createTagBody(3, {->
printHtmlPart(16)
if(true && (params.controller == 'suggestionCategories')) {
printHtmlPart(12)
}
printHtmlPart(13)
createClosureForHtmlPart(21, 4)
invokeTag('link','g',79,['controller':("suggestionCategories")],4)
printHtmlPart(15)
})
invokeTag('ifAnyGranted','sec',80,['roles':("ROLE_ADMIN")],3)
printHtmlPart(22)
if(true && (params.controller == 'ticket')) {
printHtmlPart(12)
}
printHtmlPart(13)
createClosureForHtmlPart(23, 3)
invokeTag('link','g',85,['controller':("ticket")],3)
printHtmlPart(24)
})
invokeTag('ifAnyGranted','sec',87,['roles':("ROLE_ADMIN,ROLE_MANAGER")],2)
printHtmlPart(25)
createTagBody(2, {->
printHtmlPart(26)
if(true && (params.controller == 'user')) {
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(3, {->
invokeTag('message','g',101,['code':("user.list.label")],-1)
})
invokeTag('link','g',101,['controller':("user")],3)
printHtmlPart(14)
if(true && (params.controller == 'profile')) {
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(3, {->
invokeTag('message','g',102,['code':("profile.list.label")],-1)
})
invokeTag('link','g',102,['controller':("profile")],3)
printHtmlPart(27)
})
invokeTag('ifAnyGranted','sec',105,['roles':("ROLE_ADMIN,ROLE_MANAGER")],2)
printHtmlPart(28)
createTagBody(2, {->
printHtmlPart(29)
if(true && (params.controller == 'paymentRequest')) {
printHtmlPart(12)
}
printHtmlPart(13)
createClosureForHtmlPart(30, 3)
invokeTag('link','g',113,['controller':("paymentRequest")],3)
printHtmlPart(24)
})
invokeTag('ifAnyGranted','sec',115,['roles':("ROLE_ADMIN")],2)
printHtmlPart(28)
createTagBody(2, {->
printHtmlPart(31)
createTagBody(3, {->
printHtmlPart(32)
if(true && (params.controller == 'account')) {
printHtmlPart(12)
}
printHtmlPart(13)
createClosureForHtmlPart(33, 4)
invokeTag('link','g',128,['controller':("account")],4)
printHtmlPart(15)
})
invokeTag('ifAnyGranted','sec',129,['roles':("ROLE_ADMIN,ROLE_MANAGER")],3)
printHtmlPart(34)
createTagBody(3, {->
printHtmlPart(16)
if(true && (params.controller == 'account')) {
printHtmlPart(12)
}
printHtmlPart(13)
createClosureForHtmlPart(35, 4)
invokeTag('link','g',132,['controller':("account")],4)
printHtmlPart(15)
})
invokeTag('ifAnyGranted','sec',133,['roles':("ROLE_ACCOUNT")],3)
printHtmlPart(36)
createTagBody(3, {->
printHtmlPart(37)
if(true && (params.controller == 'deal')) {
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(4, {->
invokeTag('message','g',136,['code':("deal.list.label")],-1)
printHtmlPart(38)
if(true && (freeAccount)) {
printHtmlPart(39)
}
printHtmlPart(0)
})
invokeTag('link','g',139,['controller':("deal")],4)
printHtmlPart(40)
})
invokeTag('ifAnyGranted','sec',140,['roles':("ROLE_ADMIN,ROLE_ACCOUNT")],3)
printHtmlPart(41)
createTagBody(3, {->
printHtmlPart(42)
if(true && (params.controller == 'review')) {
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(4, {->
printHtmlPart(43)
if(true && (freeAccount)) {
printHtmlPart(39)
}
printHtmlPart(44)
})
invokeTag('link','g',148,['controller':("review")],4)
printHtmlPart(45)
if(true && (params.controller == 'item' && params.categoryTypeCode == 'GOOD')) {
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(4, {->
invokeTag('message','g',152,['code':("goods.list.label")],-1)
printHtmlPart(46)
})
invokeTag('link','g',154,['mapping':("GOOD")],4)
printHtmlPart(47)
if(true && (params.controller == 'item' && params.categoryTypeCode == 'SERVICE')) {
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(4, {->
invokeTag('message','g',158,['code':("services.list.label")],-1)
printHtmlPart(46)
})
invokeTag('link','g',160,['mapping':("SERVICE")],4)
printHtmlPart(48)
if(true && (params.controller == 'adcontent')) {
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(4, {->
invokeTag('message','g',164,['code':("adcontent.list.label")],-1)
printHtmlPart(49)
if(true && (freeAccount)) {
printHtmlPart(39)
}
printHtmlPart(44)
})
invokeTag('link','g',166,['controller':("adcontent")],4)
printHtmlPart(50)
})
invokeTag('ifAnyGranted','sec',167,['roles':("ROLE_ADMIN,ROLE_MANAGER,ROLE_ACCOUNT")],3)
printHtmlPart(51)
})
invokeTag('ifAnyGranted','sec',170,['roles':("ROLE_ADMIN,ROLE_MANAGER,ROLE_ACCOUNT")],2)
printHtmlPart(28)
createTagBody(2, {->
printHtmlPart(52)
createTagBody(3, {->
printHtmlPart(16)
if(true && (params.controller == 'dispute')) {
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(4, {->
invokeTag('message','g',177,['code':("dispute.list.label")],-1)
printHtmlPart(53)
if(true && (freeAccount)) {
printHtmlPart(39)
}
printHtmlPart(49)
})
invokeTag('link','g',180,['controller':("dispute")],4)
printHtmlPart(15)
})
invokeTag('ifAnyGranted','sec',181,['roles':("ROLE_ADMIN,ROLE_ACCOUNT,ROLE_MEDIATOR")],3)
printHtmlPart(44)
createTagBody(3, {->
printHtmlPart(16)
if(true && (params.controller == 'claim')) {
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(4, {->
invokeTag('message','g',183,['code':("claim.list.label")],-1)
printHtmlPart(54)
if(true && (freeAccount)) {
printHtmlPart(39)
}
printHtmlPart(49)
})
invokeTag('link','g',186,['controller':("claim")],4)
printHtmlPart(15)
})
invokeTag('ifAnyGranted','sec',187,['roles':("ROLE_ADMIN,ROLE_JUDGE,ROLE_ACCOUNT")],3)
printHtmlPart(44)
createTagBody(3, {->
printHtmlPart(16)
if(true && (params.controller == 'juristConsult')) {
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(4, {->
invokeTag('message','g',189,['code':("consult.list.label")],-1)
printHtmlPart(55)
if(true && (freeAccount)) {
printHtmlPart(39)
}
printHtmlPart(49)
})
invokeTag('link','g',192,['controller':("juristConsult")],4)
printHtmlPart(56)
})
invokeTag('ifAnyGranted','sec',194,['roles':("ROLE_ADMIN,ROLE_JURIST,ROLE_ACCOUNT")],3)
printHtmlPart(44)
createTagBody(3, {->
printHtmlPart(32)
if(true && (params.controller == 'document')) {
printHtmlPart(12)
}
printHtmlPart(57)
createTagBody(4, {->
invokeTag('message','g',197,['code':("document.list.label")],-1)
})
invokeTag('link','g',197,['controller':("document")],4)
printHtmlPart(58)
})
invokeTag('ifAnyGranted','sec',199,['roles':("ROLE_ADMIN,ROLE_ACCOUNT,ROLE_MANAGER")],3)
printHtmlPart(17)
})
invokeTag('ifAnyGranted','sec',201,['roles':("ROLE_ADMIN,ROLE_ACCOUNT,ROLE_MEDIATOR,ROLE_JURIST,ROLE_JUDGE")],2)
printHtmlPart(28)
createTagBody(2, {->
printHtmlPart(59)
if(true && (params.controller == 'ticket')) {
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(3, {->
invokeTag('message','g',207,['code':("ticket.list.label")],-1)
printHtmlPart(60)
})
invokeTag('link','g',209,['controller':("ticket")],3)
printHtmlPart(24)
})
invokeTag('ifAnyGranted','sec',211,['roles':("ROLE_ACCOUNT,ROLE_JUDGE,ROLE_MEDIATOR,ROLE_JURIST")],2)
printHtmlPart(18)
createTagBody(2, {->
printHtmlPart(61)
if(true && (params.controller == 'adminTool')) {
printHtmlPart(12)
}
printHtmlPart(13)
createTagBody(3, {->
invokeTag('message','g',218,['code':("admintool.label")],-1)
})
invokeTag('link','g',218,['controller':("adminTool")],3)
printHtmlPart(27)
})
invokeTag('ifAnyGranted','sec',221,['roles':("ROLE_ADMIN")],2)
printHtmlPart(62)
invokeTag('layoutBody','g',230,[:],-1)
printHtmlPart(63)
invokeTag('render','g',237,['template':("/layouts/footer")],-1)
printHtmlPart(64)
invokeTag('message','g',239,['code':("spinner.alt"),'default':("Loading&hellip;")],-1)
printHtmlPart(65)
invokeTag('deferredScripts','asset',240,[:],-1)
printHtmlPart(66)
expressionOut.print(createLink([controller: 'panel', action: 'getAccount']))
printHtmlPart(67)
})
invokeTag('captureBody','sitemesh',385,['style':("background-color: #ececec; padding: 15px;width: 92%;margin: 0 auto")],1)
printHtmlPart(68)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1478762634729L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
