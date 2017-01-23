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
invokeTag('captureHead','sitemesh',33,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(0)
invokeTag('render','g',39,['template':("/_common/gallery-multi")],-1)
printHtmlPart(6)
invokeTag('render','g',51,['template':("/_common/category-breadcrumb"),'model':([
					categoryFilterData: categoryFilterData
			])],-1)
printHtmlPart(7)
if(true && (itemInstance?.name)) {
printHtmlPart(8)
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
expressionOut.print(resource(dir: 'images', file: 'front/goods.png'))
printHtmlPart(15)
}
printHtmlPart(16)
if(true && (!attachList.isEmpty())) {
printHtmlPart(17)
expressionOut.print(resource(dir: 'images', file: 'front/arrowleft.png'))
printHtmlPart(18)
for( attach in (attachList) ) {
printHtmlPart(19)
createTagBody(4, {->
printHtmlPart(20)
expressionOut.print(attach.id)
printHtmlPart(21)
expressionOut.print(createLink(controller: 'files', action: 'item', id: attach.imageThumb.id, params: [name: attach.imageThumb.file]))
printHtmlPart(22)
})
invokeTag('link','g',104,['id':("im${attach.id}"),'url':([controller: 'files', action: 'item', id: attach.image.id, params: [name: attach.image.file]]),'data-gallery':("")],4)
printHtmlPart(23)
}
printHtmlPart(24)
expressionOut.print(resource(dir: 'images', file: 'front/arrowright.png'))
printHtmlPart(25)
}
printHtmlPart(26)
if(true && (itemInstance?.price)) {
printHtmlPart(27)
invokeTag('formatNumber','g',165,['number':(itemInstance?.price),'type':("currency"),'currencyCode':(itemInstance?.currency.code)],-1)
printHtmlPart(28)
expressionOut.print('за ' + itemInstance.measureValue + ' ' + itemInstance?.measure?.name)
printHtmlPart(27)
if(true && (itemInstance?.measure.fullname)) {
printHtmlPart(29)
expressionOut.print(itemInstance.measure.fullname)
printHtmlPart(30)
}
printHtmlPart(31)
}
printHtmlPart(32)
if(true && (itemInstance?.kind)) {
printHtmlPart(33)
invokeTag('message','g',187,['code':("item.kind.label"),'default':("Kind")],-1)
printHtmlPart(34)
expressionOut.print(itemInstance.kind)
printHtmlPart(35)
}
printHtmlPart(36)
if(true && (itemInstance?.categoryType?.code == 'GOOD')) {
printHtmlPart(37)
if(true && (itemInstance?.availability == 1)) {
printHtmlPart(38)
invokeTag('message','g',200,['code':("item.filter.avail.1"),'default':("Available")],-1)
printHtmlPart(39)
}
else {
printHtmlPart(38)
invokeTag('message','g',204,['code':("item.filter.avail.0"),'default':("Custom")],-1)
printHtmlPart(39)
}
printHtmlPart(40)
}
printHtmlPart(41)
if(true && (itemInstance?.description)) {
printHtmlPart(42)
expressionOut.print(itemInstance.description)
printHtmlPart(43)
}
printHtmlPart(44)
if(true && (itemInstance.account)) {
printHtmlPart(45)
if(true && (itemInstance.account.name)) {
printHtmlPart(46)
expressionOut.print(createLink(controller: 'company', action: 'index', id: itemInstance.account.id))
printHtmlPart(47)
expressionOut.print(itemInstance.account.name)
printHtmlPart(48)
}
printHtmlPart(49)
if(true && (itemInstance.account.city.name)) {
printHtmlPart(50)
expressionOut.print(itemInstance.account.city.name)
printHtmlPart(51)
}
printHtmlPart(49)
if(true && (itemInstance.account.address)) {
printHtmlPart(52)
expressionOut.print(itemInstance.account.address)
printHtmlPart(53)
}
printHtmlPart(49)
createTagBody(3, {->
printHtmlPart(54)
if(true && (freeAccount && isMyAccount)) {
printHtmlPart(55)
}
else {
printHtmlPart(28)
createClosureForHtmlPart(56, 5)
invokeTag('link','g',273,['class':("deal_button"),'controller':("deal"),'action':("create2"),'params':([partner: itemInstance.account.id])],5)
printHtmlPart(54)
}
printHtmlPart(54)
})
invokeTag('ifLoggedIn','sec',273,[:],3)
printHtmlPart(54)
createTagBody(3, {->
printHtmlPart(28)
createClosureForHtmlPart(56, 4)
invokeTag('link','g',280,['class':("deal_button"),'controller':("login"),'action':("auth"),'data-toggle':("modal"),'data-remote':(createLink(controller:'login' , action: 'auth')),'data-target':("#myModal")],4)
printHtmlPart(54)
})
invokeTag('ifNotLoggedIn','sec',280,[:],3)
printHtmlPart(57)
}
printHtmlPart(58)
})
invokeTag('captureBody','sitemesh',282,[:],1)
printHtmlPart(59)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1484817923338L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
