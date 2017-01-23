import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_left_barbar_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/left-bar/bar.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(createLink(controller: "panel"))
printHtmlPart(1)
invokeTag('message','g',6,['code':("default.home.label")],-1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
if(true && (params.controller == 'category')) {
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(2, {->
invokeTag('message','g',19,['code':("category.list.label")],-1)
})
invokeTag('link','g',19,['controller':("category")],2)
printHtmlPart(6)
if(true && (params.controller == 'measure')) {
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(2, {->
invokeTag('message','g',20,['code':("measure.list.label")],-1)
})
invokeTag('link','g',20,['controller':("measure")],2)
printHtmlPart(6)
if(true && (params.controller == 'region')) {
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(2, {->
invokeTag('message','g',21,['code':("region.list.label")],-1)
})
invokeTag('link','g',21,['controller':("region")],2)
printHtmlPart(6)
if(true && (params.controller == 'content')) {
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(2, {->
invokeTag('message','g',22,['code':("content.list.label")],-1)
})
invokeTag('link','g',22,['controller':("content")],2)
printHtmlPart(6)
if(true && (params.controller == 'systemCurrency')) {
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(2, {->
invokeTag('message','g',23,['code':("currency.list.label")],-1)
})
invokeTag('link','g',23,['controller':("systemCurrency")],2)
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(8)
if(true && (params.controller == 'tariffPrice')) {
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(3, {->
invokeTag('message','g',25,['code':("tariff.list.label")],-1)
})
invokeTag('link','g',25,['controller':("tariffPrice")],3)
printHtmlPart(7)
})
invokeTag('ifAnyGranted','sec',26,['roles':("ROLE_ADMIN")],2)
printHtmlPart(9)
if(true && (params.controller == 'suggestion')) {
printHtmlPart(4)
}
printHtmlPart(5)
createClosureForHtmlPart(10, 2)
invokeTag('link','g',33,['controller':("suggestion")],2)
printHtmlPart(6)
if(true && (params.controller == 'suggestionCategories')) {
printHtmlPart(4)
}
printHtmlPart(5)
createClosureForHtmlPart(11, 2)
invokeTag('link','g',34,['controller':("suggestionCategories")],2)
printHtmlPart(6)
if(true && (params.controller == 'ticket')) {
printHtmlPart(4)
}
printHtmlPart(5)
createClosureForHtmlPart(12, 2)
invokeTag('link','g',35,['controller':("ticket")],2)
printHtmlPart(13)
})
invokeTag('ifAnyGranted','sec',39,['roles':("ROLE_ADMIN,ROLE_MANAGER")],1)
printHtmlPart(14)
createTagBody(1, {->
printHtmlPart(15)
if(true && (params.controller == 'user')) {
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(2, {->
invokeTag('message','g',49,['code':("user.list.label")],-1)
})
invokeTag('link','g',49,['controller':("user")],2)
printHtmlPart(6)
if(true && (params.controller == 'profile')) {
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(2, {->
invokeTag('message','g',50,['code':("profile.list.label")],-1)
})
invokeTag('link','g',50,['controller':("profile")],2)
printHtmlPart(16)
})
invokeTag('ifAnyGranted','sec',53,['roles':("ROLE_ADMIN,ROLE_MANAGER")],1)
printHtmlPart(14)
createTagBody(1, {->
printHtmlPart(17)
if(true && (params.controller == 'paymentRequest')) {
printHtmlPart(4)
}
printHtmlPart(5)
createClosureForHtmlPart(18, 2)
invokeTag('link','g',61,['controller':("paymentRequest")],2)
printHtmlPart(19)
})
invokeTag('ifAnyGranted','sec',63,['roles':("ROLE_ADMIN,ROLE_MANAGER")],1)
printHtmlPart(14)
createTagBody(1, {->
printHtmlPart(20)
createTagBody(2, {->
printHtmlPart(8)
if(true && (params.controller == 'account')) {
printHtmlPart(4)
}
printHtmlPart(5)
createClosureForHtmlPart(21, 3)
invokeTag('link','g',73,['controller':("account")],3)
printHtmlPart(7)
})
invokeTag('ifAnyGranted','sec',74,['roles':("ROLE_ADMIN,ROLE_MANAGER")],2)
printHtmlPart(22)
createTagBody(2, {->
printHtmlPart(8)
if(true && (params.controller == 'account')) {
printHtmlPart(4)
}
printHtmlPart(5)
createClosureForHtmlPart(23, 3)
invokeTag('link','g',77,['controller':("account")],3)
printHtmlPart(7)
})
invokeTag('ifAnyGranted','sec',78,['roles':("ROLE_ACCOUNT")],2)
printHtmlPart(24)
if(true && (params.controller == 'deal')) {
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(2, {->
invokeTag('message','g',81,['code':("deal.list.label")],-1)
printHtmlPart(25)
if(true && (userNewObjectsCount?.deals)) {
printHtmlPart(26)
expressionOut.print(userNewObjectsCount?.deals)
printHtmlPart(27)
}
printHtmlPart(25)
if(true && (freeAccount)) {
printHtmlPart(28)
}
printHtmlPart(29)
})
invokeTag('link','g',84,['controller':("deal")],2)
printHtmlPart(30)
if(true && (params.controller == 'review')) {
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(31)
if(true && (userNewObjectsCount?.reviews)) {
printHtmlPart(26)
expressionOut.print(userNewObjectsCount?.reviews)
printHtmlPart(27)
}
printHtmlPart(25)
if(true && (freeAccount)) {
printHtmlPart(28)
}
printHtmlPart(29)
})
invokeTag('link','g',90,['controller':("review")],2)
printHtmlPart(32)
if(true && (params.controller == 'item' && params.categoryTypeCode == 'GOOD')) {
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(2, {->
invokeTag('message','g',94,['code':("goods.list.label")],-1)
})
invokeTag('link','g',94,['mapping':("GOOD")],2)
printHtmlPart(33)
if(true && (params.controller == 'item' && params.categoryTypeCode == 'SERVICE')) {
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(2, {->
invokeTag('message','g',98,['code':("services.list.label")],-1)
})
invokeTag('link','g',98,['mapping':("SERVICE")],2)
printHtmlPart(34)
if(true && (params.controller == 'adcontent')) {
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(2, {->
invokeTag('message','g',101,['code':("adcontent.list.label")],-1)
printHtmlPart(25)
if(true && (freeAccount)) {
printHtmlPart(28)
}
printHtmlPart(29)
})
invokeTag('link','g',103,['controller':("adcontent")],2)
printHtmlPart(35)
})
invokeTag('ifAnyGranted','sec',107,['roles':("ROLE_ADMIN,ROLE_MANAGER,ROLE_ACCOUNT")],1)
printHtmlPart(14)
createTagBody(1, {->
printHtmlPart(36)
createTagBody(2, {->
printHtmlPart(8)
if(true && (params.controller == 'dispute')) {
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(3, {->
invokeTag('message','g',114,['code':("dispute.list.label")],-1)
printHtmlPart(37)
if(true && (userNewObjectsCount?.disputes)) {
printHtmlPart(26)
expressionOut.print(userNewObjectsCount?.disputes)
printHtmlPart(27)
}
printHtmlPart(37)
if(true && (freeAccount)) {
printHtmlPart(28)
}
printHtmlPart(25)
})
invokeTag('link','g',117,['controller':("dispute")],3)
printHtmlPart(7)
})
invokeTag('ifAnyGranted','sec',118,['roles':("ROLE_ADMIN,ROLE_MANAGER,ROLE_ACCOUNT,ROLE_MEDIATOR")],2)
printHtmlPart(29)
createTagBody(2, {->
printHtmlPart(8)
if(true && (params.controller == 'claim')) {
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(3, {->
invokeTag('message','g',120,['code':("claim.list.label")],-1)
printHtmlPart(37)
if(true && (userNewObjectsCount?.claims)) {
printHtmlPart(26)
expressionOut.print(userNewObjectsCount?.claims)
printHtmlPart(27)
}
printHtmlPart(37)
if(true && (freeAccount)) {
printHtmlPart(28)
}
printHtmlPart(25)
})
invokeTag('link','g',123,['controller':("claim")],3)
printHtmlPart(7)
})
invokeTag('ifAnyGranted','sec',124,['roles':("ROLE_ADMIN,ROLE_MANAGER,ROLE_ACCOUNT,ROLE_JUDGE")],2)
printHtmlPart(29)
createTagBody(2, {->
printHtmlPart(8)
if(true && (params.controller == 'consult')) {
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(3, {->
invokeTag('message','g',126,['code':("consult.list.label")],-1)
printHtmlPart(37)
if(true && (userNewObjectsCount?.consults)) {
printHtmlPart(26)
expressionOut.print(userNewObjectsCount?.consults)
printHtmlPart(27)
}
printHtmlPart(37)
if(true && (freeAccount)) {
printHtmlPart(28)
}
printHtmlPart(25)
})
invokeTag('link','g',129,['controller':("consult")],3)
printHtmlPart(38)
})
invokeTag('ifAnyGranted','sec',131,['roles':("ROLE_ADMIN,ROLE_MANAGER,ROLE_ACCOUNT,ROLE_JURIST")],2)
printHtmlPart(29)
createTagBody(2, {->
printHtmlPart(8)
if(true && (params.controller == 'document')) {
printHtmlPart(4)
}
printHtmlPart(39)
createTagBody(3, {->
invokeTag('message','g',134,['code':("document.list.label")],-1)
})
invokeTag('link','g',134,['controller':("document")],3)
printHtmlPart(40)
})
invokeTag('ifAnyGranted','sec',136,['roles':("ROLE_ADMIN,ROLE_MANAGER,ROLE_ACCOUNT,ROLE_MEDIATOR,ROLE_JURIST,ROLE_JUDGE")],2)
printHtmlPart(41)
})
invokeTag('ifAnyGranted','sec',138,['roles':("ROLE_ADMIN,ROLE_MANAGER,ROLE_ACCOUNT,ROLE_MEDIATOR,ROLE_JURIST,ROLE_JUDGE")],1)
printHtmlPart(42)
createTagBody(1, {->
printHtmlPart(43)
if(true && (params.controller == 'ticket')) {
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(2, {->
invokeTag('message','g',145,['code':("ticket.list.label")],-1)
})
invokeTag('link','g',145,['controller':("ticket")],2)
printHtmlPart(19)
})
invokeTag('ifAnyGranted','sec',147,['roles':("ROLE_ACCOUNT")],1)
printHtmlPart(44)
createTagBody(1, {->
printHtmlPart(45)
if(true && (params.controller == 'adminTool')) {
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(2, {->
invokeTag('message','g',154,['code':("admintool.label")],-1)
})
invokeTag('link','g',154,['controller':("adminTool")],2)
printHtmlPart(16)
})
invokeTag('ifAnyGranted','sec',157,['roles':("ROLE_ADMIN")],1)
printHtmlPart(46)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1453889427362L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
