import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_servicesitem_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/services/item.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',10,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("front")],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
printHtmlPart(3)
expressionOut.print(itemInstance.name)
printHtmlPart(4)
expressionOut.print(itemInstance.account.city.name)
printHtmlPart(5)
expressionOut.print(itemInstance.account.name)
printHtmlPart(6)
})
invokeTag('captureTitle','sitemesh',13,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',13,[:],2)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',17,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("keywords"),'content':("${itemInstance?.kind} ${itemInstance.account.name}")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',18,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("description"),'content':(itemInstance?.description)],-1)
printHtmlPart(7)
})
invokeTag('captureHead','sitemesh',33,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(0)
invokeTag('render','g',39,['template':("/_common/gallery-multi")],-1)
printHtmlPart(8)
invokeTag('render','g',51,['template':("/_common/category-breadcrumb"),'model':([
					categoryFilterData: categoryFilterData
			])],-1)
printHtmlPart(9)
if(true && (itemInstance?.name)) {
printHtmlPart(10)
expressionOut.print(itemInstance.name)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (itemInstance?.photo)) {
printHtmlPart(13)
expressionOut.print(createLink(controller: 'files', action: 'item', id: itemInstance.photo?.image.id, params: [name: itemInstance.photo?.image.file]))
printHtmlPart(14)
expressionOut.print(createLink(controller: 'files', action: 'item', id: itemInstance.photo?.image.id, params: [name: itemInstance.photo?.image.file]))
printHtmlPart(15)
}
else {
printHtmlPart(16)
expressionOut.print(resource(dir: 'images', file: 'front/goods.png'))
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (!attachList.isEmpty())) {
printHtmlPart(19)
expressionOut.print(resource(dir: 'images', file: 'front/arrowleft.png'))
printHtmlPart(20)
for( attach in (attachList) ) {
printHtmlPart(21)
createTagBody(4, {->
printHtmlPart(22)
expressionOut.print(attach.id)
printHtmlPart(23)
expressionOut.print(createLink(controller: 'files', action: 'item', id: attach.imageThumb.id, params: [name: attach.imageThumb.file]))
printHtmlPart(24)
})
invokeTag('link','g',104,['id':("im${attach.id}"),'url':([controller: 'files', action: 'item', id: attach.image.id, params: [name: attach.image.file]]),'data-gallery':("")],4)
printHtmlPart(25)
}
printHtmlPart(26)
expressionOut.print(resource(dir: 'images', file: 'front/arrowright.png'))
printHtmlPart(27)
}
printHtmlPart(28)
if(true && (itemInstance?.price)) {
printHtmlPart(29)
invokeTag('formatNumber','g',165,['number':(itemInstance?.price),'type':("currency"),'currencyCode':(itemInstance?.currency.code)],-1)
printHtmlPart(30)
expressionOut.print('за ' + itemInstance.measureValue + ' ' + itemInstance?.measure?.name)
printHtmlPart(29)
if(true && (itemInstance?.measure.fullname)) {
printHtmlPart(31)
expressionOut.print(itemInstance.measure.fullname)
printHtmlPart(32)
}
printHtmlPart(33)
}
printHtmlPart(34)
if(true && (itemInstance?.kind)) {
printHtmlPart(35)
invokeTag('message','g',187,['code':("item.kind.label"),'default':("Kind")],-1)
printHtmlPart(36)
expressionOut.print(itemInstance.kind)
printHtmlPart(37)
}
printHtmlPart(38)
if(true && (itemInstance?.categoryType?.code == 'GOOD')) {
printHtmlPart(39)
if(true && (itemInstance?.availability == 1)) {
printHtmlPart(40)
invokeTag('message','g',200,['code':("item.filter.avail.1"),'default':("Available")],-1)
printHtmlPart(41)
}
else {
printHtmlPart(40)
invokeTag('message','g',204,['code':("item.filter.avail.0"),'default':("Custom")],-1)
printHtmlPart(41)
}
printHtmlPart(42)
}
printHtmlPart(43)
if(true && (itemInstance?.description)) {
printHtmlPart(44)
expressionOut.print(itemInstance.description)
printHtmlPart(45)
}
printHtmlPart(46)
if(true && (itemInstance.account)) {
printHtmlPart(47)
if(true && (itemInstance.account.name)) {
printHtmlPart(48)
expressionOut.print(createLink(controller: 'company', action: 'index', id: itemInstance.account.id))
printHtmlPart(49)
expressionOut.print(itemInstance.account.name)
printHtmlPart(50)
}
printHtmlPart(51)
if(true && (itemInstance.account.city.name)) {
printHtmlPart(52)
expressionOut.print(itemInstance.account.city.name)
printHtmlPart(53)
}
printHtmlPart(51)
if(true && (itemInstance.account.address)) {
printHtmlPart(54)
expressionOut.print(itemInstance.account.address)
printHtmlPart(55)
}
printHtmlPart(51)
createTagBody(3, {->
printHtmlPart(56)
if(true && (freeAccount && isMyAccount)) {
printHtmlPart(57)
}
else {
printHtmlPart(30)
createClosureForHtmlPart(58, 5)
invokeTag('link','g',273,['class':("deal_button"),'controller':("deal"),'action':("create2"),'params':([partner: itemInstance.account.id])],5)
printHtmlPart(56)
}
printHtmlPart(56)
})
invokeTag('ifLoggedIn','sec',273,[:],3)
printHtmlPart(56)
createTagBody(3, {->
printHtmlPart(30)
createClosureForHtmlPart(58, 4)
invokeTag('link','g',280,['class':("deal_button"),'controller':("login"),'action':("auth"),'data-toggle':("modal"),'data-remote':(createLink(controller:'login' , action: 'auth')),'data-target':("#myModal")],4)
printHtmlPart(56)
})
invokeTag('ifNotLoggedIn','sec',280,[:],3)
printHtmlPart(59)
}
printHtmlPart(60)
})
invokeTag('captureBody','sitemesh',282,[:],1)
printHtmlPart(61)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1485149353340L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
