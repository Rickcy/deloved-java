import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_services_item_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/services/_item.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (itemInstance?.photo)) {
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(2)
expressionOut.print(createLink(controller: 'files', action: 'item', id: itemInstance.photo?.imageThumb.id, params: [name: itemInstance.photo?.imageThumb.file]))
printHtmlPart(3)
})
invokeTag('link','g',9,['class':("gallery"),'url':([controller: 'files', action: 'item', id: itemInstance.photo?.image.id, params: [name: itemInstance.photo?.image.file]]),'data-gallery':("")],2)
printHtmlPart(4)
}
else {
printHtmlPart(5)
expressionOut.print(resource(dir: 'images', file: 'front/services.png'))
printHtmlPart(6)
}
printHtmlPart(7)
createTagBody(1, {->
invokeTag('fieldValue','g',20,['bean':(itemInstance),'field':("name")],-1)
})
invokeTag('link','g',20,['controller':("services"),'action':("item"),'id':(itemInstance.id)],1)
printHtmlPart(8)
invokeTag('fieldValue','g',24,['bean':(itemInstance),'field':("description")],-1)
printHtmlPart(9)
invokeTag('formatNumber','g',26,['number':(itemInstance?.price),'type':("currency"),'currencyCode':(itemInstance?.currency.code)],-1)
printHtmlPart(10)
expressionOut.print(itemInstance?.measure?.fullname)
printHtmlPart(11)
invokeTag('formatDate','g',28,['date':(itemInstance?.dateCreated),'format':(" dd.MM.yyyy HH:mm:ss")],-1)
printHtmlPart(12)
createClosureForHtmlPart(13, 1)
invokeTag('link','g',32,['controller':("services"),'class':("podr"),'style':("text-align: center"),'action':("item"),'id':(itemInstance.id)],1)
printHtmlPart(14)
if(true && (freeAccount)) {
printHtmlPart(15)
}
else {
printHtmlPart(16)
createTagBody(2, {->
expressionOut.print(itemInstance?.account?.name)
})
invokeTag('link','g',45,['controller':("company"),'action':("index"),'id':(itemInstance?.account?.id)],2)
printHtmlPart(17)
if(true && (itemInstance?.account?.city)) {
printHtmlPart(18)
expressionOut.print(itemInstance?.account?.city.name)
printHtmlPart(19)
}
printHtmlPart(20)
if(true && (itemInstance?.account?.address)) {
printHtmlPart(21)
expressionOut.print(itemInstance?.account?.address)
printHtmlPart(19)
}
printHtmlPart(1)
}
printHtmlPart(22)
if(true && (freeAccount && isMyAccount)) {
printHtmlPart(23)
}
else {
printHtmlPart(19)
createTagBody(2, {->
printHtmlPart(24)
createClosureForHtmlPart(25, 3)
invokeTag('link','g',70,['class':("deal_button"),'controller':("login"),'action':("auth"),'data-toggle':("modal"),'data-remote':(createLink(controller:'login' , action: 'auth')),'data-target':("#myModal")],3)
printHtmlPart(19)
})
invokeTag('ifNotLoggedIn','sec',71,[:],2)
printHtmlPart(26)
createTagBody(2, {->
printHtmlPart(19)
createClosureForHtmlPart(25, 3)
invokeTag('link','g',73,['class':("deal_button"),'controller':("deal"),'style':("min-width: 143px"),'action':("create"),'params':([partner: itemInstance.account.id])],3)
printHtmlPart(1)
})
invokeTag('ifLoggedIn','sec',75,[:],2)
printHtmlPart(26)
}
printHtmlPart(27)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1484817941189L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
