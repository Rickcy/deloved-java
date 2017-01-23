import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_goods_item_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/goods/_item.gsp" }
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
invokeTag('link','g',10,['class':("gallery"),'url':([controller: 'files', action: 'item', id: itemInstance.photo?.image.id, params: [name: itemInstance.photo?.image.file]]),'data-gallery':("")],2)
printHtmlPart(4)
}
else {
printHtmlPart(5)
expressionOut.print(resource(dir: 'images', file: 'front/goods.png'))
printHtmlPart(6)
}
printHtmlPart(7)
if(true && (itemInstance?.availability == 1)) {
printHtmlPart(8)
invokeTag('message','g',17,['code':("item.filter.avail.1"),'default':("Available")],-1)
printHtmlPart(9)
}
else {
printHtmlPart(8)
invokeTag('message','g',21,['code':("item.filter.avail.0"),'default':("Custom")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
createTagBody(1, {->
invokeTag('fieldValue','g',28,['bean':(itemInstance),'field':("name")],-1)
})
invokeTag('link','g',28,['controller':("goods"),'action':("item"),'id':(itemInstance.id)],1)
printHtmlPart(11)
invokeTag('fieldValue','g',34,['bean':(itemInstance),'field':("description")],-1)
printHtmlPart(12)
invokeTag('formatNumber','g',36,['number':(itemInstance?.price),'type':("currency"),'currencyCode':(itemInstance?.currency.code)],-1)
printHtmlPart(13)
expressionOut.print(itemInstance?.measure?.fullname)
printHtmlPart(14)
invokeTag('formatDate','g',38,['date':(itemInstance?.dateCreated),'format':(" dd.MM.yyyy HH:mm:ss")],-1)
printHtmlPart(15)
createClosureForHtmlPart(16, 1)
invokeTag('link','g',42,['controller':("goods"),'class':("podr"),'style':("text-align: center"),'action':("item"),'id':(itemInstance.id)],1)
printHtmlPart(17)
if(true && (freeAccount)) {
printHtmlPart(18)
}
else {
printHtmlPart(19)
createTagBody(2, {->
expressionOut.print(itemInstance?.account?.name)
})
invokeTag('link','g',57,['controller':("company"),'action':("index"),'id':(itemInstance?.account?.id)],2)
printHtmlPart(20)
if(true && (itemInstance?.account?.city)) {
printHtmlPart(21)
expressionOut.print(itemInstance?.account?.city.name)
printHtmlPart(22)
}
printHtmlPart(23)
if(true && (itemInstance?.account?.address)) {
printHtmlPart(24)
expressionOut.print(itemInstance?.account?.address)
printHtmlPart(22)
}
printHtmlPart(1)
}
printHtmlPart(25)
if(true && (freeAccount && isMyAccount)) {
printHtmlPart(26)
}
else {
printHtmlPart(22)
createTagBody(2, {->
printHtmlPart(27)
createClosureForHtmlPart(28, 3)
invokeTag('link','g',85,['class':("deal_button"),'controller':("login"),'action':("auth"),'data-toggle':("modal"),'data-remote':(createLink(controller:'login' , action: 'auth')),'data-target':("#myModal")],3)
printHtmlPart(29)
})
invokeTag('ifNotLoggedIn','sec',85,[:],2)
printHtmlPart(22)
createTagBody(2, {->
printHtmlPart(27)
createClosureForHtmlPart(28, 3)
invokeTag('link','g',87,['class':("deal_button"),'controller':("deal"),'style':("min-width: 143px"),'action':("create"),'params':([partner: itemInstance.account.id])],3)
printHtmlPart(23)
})
invokeTag('ifLoggedIn','sec',89,[:],2)
printHtmlPart(30)
}
printHtmlPart(31)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1484817900062L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
