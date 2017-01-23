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
})
invokeTag('captureTitle','sitemesh',11,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',12,[:],2)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',17,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("keywords"),'content':(itemInstance?.kind)],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',18,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("description"),'content':(itemInstance?.description)],-1)
printHtmlPart(5)
})
invokeTag('captureHead','sitemesh',32,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(0)
invokeTag('render','g',38,['template':("/_common/gallery-multi")],-1)
printHtmlPart(6)
invokeTag('render','g',50,['template':("/_common/category-breadcrumb"),'model':([
				categoryFilterData: categoryFilterData
		])],-1)
printHtmlPart(7)
if(true && (itemInstance?.name)) {
printHtmlPart(8)
expressionOut.print(itemInstance.name)
printHtmlPart(2)
}
printHtmlPart(9)
if(true && (itemInstance?.photo)) {
printHtmlPart(10)
expressionOut.print(createLink(controller: 'files', action: 'item', id: itemInstance.photo?.image.id, params: [name: itemInstance.photo?.image.file]))
printHtmlPart(11)
expressionOut.print(createLink(controller: 'files', action: 'item', id: itemInstance.photo?.image.id, params: [name: itemInstance.photo?.image.file]))
printHtmlPart(12)
}
else {
printHtmlPart(13)
expressionOut.print(resource(dir: 'images', file: 'front/goods.png'))
printHtmlPart(14)
}
printHtmlPart(15)
if(true && (!attachList.isEmpty())) {
printHtmlPart(16)
expressionOut.print(resource(dir: 'images', file: 'front/arrowleft.png'))
printHtmlPart(17)
for( attach in (attachList) ) {
printHtmlPart(18)
createTagBody(4, {->
printHtmlPart(19)
expressionOut.print(attach.id)
printHtmlPart(20)
expressionOut.print(createLink(controller: 'files', action: 'item', id: attach.imageThumb.id, params: [name: attach.imageThumb.file]))
printHtmlPart(21)
})
invokeTag('link','g',102,['id':("im${attach.id}"),'url':([controller: 'files', action: 'item', id: attach.image.id, params: [name: attach.image.file]]),'data-gallery':("")],4)
printHtmlPart(22)
}
printHtmlPart(23)
expressionOut.print(resource(dir: 'images', file: 'front/arrowright.png'))
printHtmlPart(24)
}
printHtmlPart(25)
if(true && (itemInstance?.price)) {
printHtmlPart(26)
invokeTag('formatNumber','g',166,['number':(itemInstance?.price),'type':("currency"),'currencyCode':(itemInstance?.currency.code)],-1)
printHtmlPart(27)
expressionOut.print('за ' + itemInstance.measureValue + ' ' + itemInstance?.measure?.name)
printHtmlPart(26)
if(true && (itemInstance?.measure.fullname)) {
printHtmlPart(28)
expressionOut.print(itemInstance.measure.fullname)
printHtmlPart(29)
}
printHtmlPart(30)
}
printHtmlPart(31)
if(true && (itemInstance?.kind)) {
printHtmlPart(32)
invokeTag('message','g',188,['code':("item.kind.label"),'default':("Kind")],-1)
printHtmlPart(33)
expressionOut.print(itemInstance.kind)
printHtmlPart(34)
}
printHtmlPart(35)
if(true && (itemInstance?.categoryType?.code == 'GOOD')) {
printHtmlPart(36)
if(true && (itemInstance?.availability == 1)) {
printHtmlPart(37)
invokeTag('message','g',201,['code':("item.filter.avail.1"),'default':("Available")],-1)
printHtmlPart(38)
}
else {
printHtmlPart(37)
invokeTag('message','g',205,['code':("item.filter.avail.0"),'default':("Custom")],-1)
printHtmlPart(38)
}
printHtmlPart(39)
}
printHtmlPart(40)
if(true && (itemInstance?.description)) {
printHtmlPart(41)
expressionOut.print(itemInstance.description)
printHtmlPart(42)
}
printHtmlPart(43)
if(true && (itemInstance.account)) {
printHtmlPart(44)
if(true && (itemInstance.account.name)) {
printHtmlPart(45)
expressionOut.print(createLink(controller: 'company', action: 'index', id: itemInstance.account.id))
printHtmlPart(46)
expressionOut.print(itemInstance.account.name)
printHtmlPart(47)
}
printHtmlPart(48)
if(true && (itemInstance.account.city.name)) {
printHtmlPart(49)
expressionOut.print(itemInstance.account.city.name)
printHtmlPart(50)
}
printHtmlPart(48)
if(true && (itemInstance.account.address)) {
printHtmlPart(51)
expressionOut.print(itemInstance.account.address)
printHtmlPart(50)
}
printHtmlPart(52)
createTagBody(3, {->
printHtmlPart(53)
if(true && (freeAccount||isMyAccount)) {
printHtmlPart(54)
}
else {
printHtmlPart(27)
createClosureForHtmlPart(55, 5)
invokeTag('link','g',277,['class':("deal_button"),'controller':("deal"),'action':("create2"),'params':([partner: itemInstance.account.id])],5)
printHtmlPart(52)
}
printHtmlPart(52)
})
invokeTag('ifLoggedIn','sec',277,[:],3)
printHtmlPart(52)
createTagBody(3, {->
printHtmlPart(27)
createClosureForHtmlPart(55, 4)
invokeTag('link','g',284,['class':("deal_button"),'controller':("login"),'action':("auth"),'data-toggle':("modal"),'data-remote':(createLink(controller:'login' , action: 'auth')),'data-target':("#myModal")],4)
printHtmlPart(53)
})
invokeTag('ifNotLoggedIn','sec',284,[:],3)
printHtmlPart(56)
}
printHtmlPart(57)
})
invokeTag('captureBody','sitemesh',288,[:],1)
printHtmlPart(58)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1485145037485L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
