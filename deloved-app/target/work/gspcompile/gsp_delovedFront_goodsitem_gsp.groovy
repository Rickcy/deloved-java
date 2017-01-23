import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_goodsitem_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/goods/item.gsp" }
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
invokeTag('captureTitle','sitemesh',14,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',14,[:],2)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',17,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("keywords"),'content':("${itemInstance?.kind} ${itemInstance.account.name}")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',18,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("description"),'content':(itemInstance?.description)],-1)
printHtmlPart(7)
})
invokeTag('captureHead','sitemesh',32,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(0)
invokeTag('render','g',38,['template':("/_common/gallery-multi")],-1)
printHtmlPart(8)
invokeTag('render','g',50,['template':("/_common/category-breadcrumb"),'model':([
				categoryFilterData: categoryFilterData
		])],-1)
printHtmlPart(9)
if(true && (itemInstance?.name)) {
printHtmlPart(10)
expressionOut.print(itemInstance.name)
printHtmlPart(2)
}
printHtmlPart(11)
if(true && (itemInstance?.photo)) {
printHtmlPart(12)
expressionOut.print(createLink(controller: 'files', action: 'item', id: itemInstance.photo?.image.id, params: [name: itemInstance.photo?.image.file]))
printHtmlPart(13)
expressionOut.print(createLink(controller: 'files', action: 'item', id: itemInstance.photo?.image.id, params: [name: itemInstance.photo?.image.file]))
printHtmlPart(14)
}
else {
printHtmlPart(15)
expressionOut.print(resource(dir: 'images', file: 'front/goods.png'))
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (!attachList.isEmpty())) {
printHtmlPart(18)
expressionOut.print(resource(dir: 'images', file: 'front/arrowleft.png'))
printHtmlPart(19)
for( attach in (attachList) ) {
printHtmlPart(20)
createTagBody(4, {->
printHtmlPart(21)
expressionOut.print(attach.id)
printHtmlPart(22)
expressionOut.print(createLink(controller: 'files', action: 'item', id: attach.imageThumb.id, params: [name: attach.imageThumb.file]))
printHtmlPart(23)
})
invokeTag('link','g',102,['id':("im${attach.id}"),'url':([controller: 'files', action: 'item', id: attach.image.id, params: [name: attach.image.file]]),'data-gallery':("")],4)
printHtmlPart(24)
}
printHtmlPart(25)
expressionOut.print(resource(dir: 'images', file: 'front/arrowright.png'))
printHtmlPart(26)
}
printHtmlPart(27)
if(true && (itemInstance?.price)) {
printHtmlPart(28)
invokeTag('formatNumber','g',166,['number':(itemInstance?.price),'type':("currency"),'currencyCode':(itemInstance?.currency.code)],-1)
printHtmlPart(29)
expressionOut.print('за ' + itemInstance.measureValue + ' ' + itemInstance?.measure?.name)
printHtmlPart(28)
if(true && (itemInstance?.measure.fullname)) {
printHtmlPart(30)
expressionOut.print(itemInstance.measure.fullname)
printHtmlPart(31)
}
printHtmlPart(32)
}
printHtmlPart(33)
if(true && (itemInstance?.kind)) {
printHtmlPart(34)
invokeTag('message','g',188,['code':("item.kind.label"),'default':("Kind")],-1)
printHtmlPart(35)
expressionOut.print(itemInstance.kind)
printHtmlPart(36)
}
printHtmlPart(37)
if(true && (itemInstance?.categoryType?.code == 'GOOD')) {
printHtmlPart(38)
if(true && (itemInstance?.availability == 1)) {
printHtmlPart(39)
invokeTag('message','g',201,['code':("item.filter.avail.1"),'default':("Available")],-1)
printHtmlPart(40)
}
else {
printHtmlPart(39)
invokeTag('message','g',205,['code':("item.filter.avail.0"),'default':("Custom")],-1)
printHtmlPart(40)
}
printHtmlPart(41)
}
printHtmlPart(42)
if(true && (itemInstance?.description)) {
printHtmlPart(43)
expressionOut.print(itemInstance.description)
printHtmlPart(44)
}
printHtmlPart(45)
if(true && (itemInstance.account)) {
printHtmlPart(46)
if(true && (itemInstance.account.name)) {
printHtmlPart(47)
expressionOut.print(createLink(controller: 'company', action: 'index', id: itemInstance.account.id))
printHtmlPart(48)
expressionOut.print(itemInstance.account.name)
printHtmlPart(49)
}
printHtmlPart(50)
if(true && (itemInstance.account.city.name)) {
printHtmlPart(51)
expressionOut.print(itemInstance.account.city.name)
printHtmlPart(52)
}
printHtmlPart(50)
if(true && (itemInstance.account.address)) {
printHtmlPart(53)
expressionOut.print(itemInstance.account.address)
printHtmlPart(52)
}
printHtmlPart(54)
createTagBody(3, {->
printHtmlPart(55)
if(true && (freeAccount||isMyAccount)) {
printHtmlPart(56)
}
else {
printHtmlPart(29)
createClosureForHtmlPart(57, 5)
invokeTag('link','g',277,['class':("deal_button"),'controller':("deal"),'action':("create2"),'params':([partner: itemInstance.account.id])],5)
printHtmlPart(54)
}
printHtmlPart(54)
})
invokeTag('ifLoggedIn','sec',277,[:],3)
printHtmlPart(54)
createTagBody(3, {->
printHtmlPart(29)
createClosureForHtmlPart(57, 4)
invokeTag('link','g',284,['class':("deal_button"),'controller':("login"),'action':("auth"),'data-toggle':("modal"),'data-remote':(createLink(controller:'login' , action: 'auth')),'data-target':("#myModal")],4)
printHtmlPart(55)
})
invokeTag('ifNotLoggedIn','sec',284,[:],3)
printHtmlPart(58)
}
printHtmlPart(59)
})
invokeTag('captureBody','sitemesh',288,[:],1)
printHtmlPart(60)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1485149310658L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
