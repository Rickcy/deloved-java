import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_itemsitem_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/items/item.gsp" }
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
if(true && (itemInstance.categoryType.code == 'GOOD')) {
printHtmlPart(4)
invokeTag('message','g',13,['code':("good.list.label"),'default':("Good")],-1)
printHtmlPart(3)
}
else {
printHtmlPart(5)
invokeTag('message','g',14,['code':("service.list.label"),'default':("Service")],-1)
printHtmlPart(3)
}
printHtmlPart(2)
})
invokeTag('captureTitle','sitemesh',16,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',17,[:],2)
printHtmlPart(6)
})
invokeTag('captureHead','sitemesh',17,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(0)
invokeTag('render','g',20,['template':("/_common/gallery-multi")],-1)
printHtmlPart(7)
invokeTag('render','g',32,['template':("/_common/category-breadcrumb"),'model':([
				categoryFilterData: categoryFilterData
		])],-1)
printHtmlPart(8)
if(true && (itemInstance?.name)) {
printHtmlPart(3)
expressionOut.print(itemInstance.name)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (itemInstance?.photo)) {
printHtmlPart(11)
expressionOut.print(createLink(controller: 'files', action: 'item', id: itemInstance.photo?.image.id, params: [name: itemInstance.photo?.image.file]))
printHtmlPart(12)
expressionOut.print(createLink(controller: 'files', action: 'item', id: itemInstance.photo?.image.id, params: [name: itemInstance.photo?.image.file]))
printHtmlPart(13)
}
else {
printHtmlPart(14)
expressionOut.print(resource(dir: 'images', file: 'front/logo_uch.png'))
printHtmlPart(15)
}
printHtmlPart(16)
if(true && (!attachList.isEmpty())) {
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
invokeTag('link','g',84,['id':("im${attach.id}"),'url':([controller: 'files', action: 'item', id: attach.image.id, params: [name: attach.image.file]]),'data-gallery':("")],4)
printHtmlPart(22)
}
printHtmlPart(23)
}
printHtmlPart(24)
if(true && (itemInstance?.price)) {
printHtmlPart(25)
invokeTag('formatNumber','g',146,['number':(itemInstance?.price),'type':("currency"),'currencyCode':(itemInstance?.currency.code)],-1)
printHtmlPart(26)
expressionOut.print('за ' + itemInstance.measureValue + ' ' + itemInstance?.measure?.name)
printHtmlPart(25)
if(true && (itemInstance?.measure.fullname)) {
printHtmlPart(27)
expressionOut.print(itemInstance.measure.fullname)
printHtmlPart(28)
}
printHtmlPart(29)
}
printHtmlPart(30)
if(true && (itemInstance?.kind)) {
printHtmlPart(31)
invokeTag('message','g',168,['code':("item.kind.label"),'default':("Kind")],-1)
printHtmlPart(32)
expressionOut.print(itemInstance.kind)
printHtmlPart(33)
}
printHtmlPart(34)
if(true && (itemInstance?.categoryType?.code == 'GOOD')) {
printHtmlPart(35)
invokeTag('message','g',176,['code':("item.availability.label"),'default':("Availability")],-1)
printHtmlPart(36)
if(true && (itemInstance?.availability == 1)) {
printHtmlPart(37)
invokeTag('message','g',181,['code':("item.filter.avail.1"),'default':("Available")],-1)
printHtmlPart(38)
}
else {
printHtmlPart(37)
invokeTag('message','g',185,['code':("item.filter.avail.0"),'default':("Custom")],-1)
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
if(true && (freeAccount || isMyAccount)) {
printHtmlPart(44)
}
else {
printHtmlPart(45)
createClosureForHtmlPart(46, 3)
invokeTag('link','g',215,['class':("deal_button"),'controller':("deal"),'action':("create"),'params':([partner: itemInstance.account.id])],3)
printHtmlPart(5)
}
printHtmlPart(47)
if(true && (itemInstance.account)) {
printHtmlPart(48)
if(true && (itemInstance.account?.logo)) {
printHtmlPart(26)
createTagBody(4, {->
printHtmlPart(49)
expressionOut.print(createLink(controller: 'files', action: 'logo', id: itemInstance.account.logo.id, params: [name: itemInstance.account.logo?.file]))
printHtmlPart(50)
})
invokeTag('link','g',238,['url':([controller: 'files', action: 'logo', id: itemInstance.account.logo.id, params: [name: itemInstance.account.logo?.file]]),'data-gallery':("")],4)
printHtmlPart(45)
}
else {
printHtmlPart(51)
expressionOut.print(resource(dir: 'images', file: 'front/logo_uch.png'))
printHtmlPart(52)
}
printHtmlPart(53)
if(true && (itemInstance.account.name)) {
printHtmlPart(54)
expressionOut.print(createLink(controller: 'company', action: 'index', id: itemInstance.account.id))
printHtmlPart(55)
expressionOut.print(itemInstance.account.name)
printHtmlPart(56)
}
printHtmlPart(35)
if(true && (itemInstance.account.city.name)) {
printHtmlPart(57)
expressionOut.print(itemInstance.account.city.name)
printHtmlPart(58)
}
printHtmlPart(35)
if(true && (itemInstance.account.address)) {
printHtmlPart(59)
expressionOut.print(itemInstance.account.address)
printHtmlPart(58)
}
printHtmlPart(35)
if(true && (itemInstance.account.phone1)) {
printHtmlPart(60)
expressionOut.print(itemInstance.account.phone1)
printHtmlPart(61)
}
printHtmlPart(35)
if(true && (itemInstance.account.webAddress)) {
printHtmlPart(62)
expressionOut.print(itemInstance.account.webAddress)
printHtmlPart(55)
expressionOut.print(itemInstance.account.webAddress)
printHtmlPart(63)
}
printHtmlPart(35)
if(true && (itemInstance.account.email)) {
printHtmlPart(64)
expressionOut.print(itemInstance.account.email)
printHtmlPart(55)
expressionOut.print(itemInstance.account.email)
printHtmlPart(65)
}
printHtmlPart(66)
if(true && (itemInstance.categoryType.code == 'GOOD')) {
printHtmlPart(26)
invokeTag('message','g',282,['code':("othergoods.list.label"),'default':("All goods of company")],-1)
printHtmlPart(45)
}
else {
printHtmlPart(26)
invokeTag('message','g',285,['code':("otherservices.list.label"),'default':("All services of company")],-1)
printHtmlPart(45)
}
printHtmlPart(67)
}
printHtmlPart(68)
if(true && (!itemList.isEmpty())) {
printHtmlPart(69)
for( item in (itemList) ) {
printHtmlPart(70)
if(true && (item?.photo)) {
printHtmlPart(71)
createTagBody(5, {->
printHtmlPart(72)
expressionOut.print(createLink(controller: 'files', action: 'item', id: item.photo?.imageThumb.id, params: [name: item.photo?.imageThumb.file]))
printHtmlPart(73)
})
invokeTag('link','g',305,['id':("it${item.id}"),'url':([controller: 'files', action: 'item', id: item.photo?.image.id, params: [name: item.photo?.image.file]]),'data-gallery':("")],5)
printHtmlPart(74)
}
else {
printHtmlPart(72)
expressionOut.print(resource(dir: 'images', file: 'front/logo_uch.png'))
printHtmlPart(75)
}
printHtmlPart(76)
createTagBody(4, {->
printHtmlPart(77)
expressionOut.print(item.name ?: '')
printHtmlPart(78)
})
invokeTag('link','g',317,['url':([controller: 'goods', action: 'item', id: item.id])],4)
printHtmlPart(79)
}
printHtmlPart(80)
}
printHtmlPart(81)
})
invokeTag('captureBody','sitemesh',331,[:],1)
printHtmlPart(82)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444124074867L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
