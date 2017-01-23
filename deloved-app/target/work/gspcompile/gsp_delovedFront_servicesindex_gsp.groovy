import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_servicesindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/services/index.gsp" }
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
invokeTag('captureMeta','sitemesh',12,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("keywords"),'content':(categoryFilterData?.category?.name)],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',13,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("description"),'content':(categoryFilterData?.category?.name)],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
printHtmlPart(3)
if(true && (categoryFilterData.category)) {
printHtmlPart(4)
expressionOut.print(categoryFilterData.category.name)
printHtmlPart(5)
}
printHtmlPart(6)
})
invokeTag('captureTitle','sitemesh',17,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',18,[:],2)
printHtmlPart(2)
invokeTag('stylesheet','asset',25,['src':("front_not_main.css")],-1)
printHtmlPart(2)
invokeTag('javascript','asset',26,['src':("front_not_main.js")],-1)
printHtmlPart(2)
invokeTag('javascript','asset',27,['src':("servecesInd.js")],-1)
printHtmlPart(0)
})
invokeTag('captureHead','sitemesh',27,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(7)
invokeTag('render','g',46,['template':("/_common/category-filter"),'model':([
						categoryFilterData: categoryFilterData
				])],-1)
printHtmlPart(8)
if(true && (categoryFilterData.company)) {
printHtmlPart(9)
createTagBody(3, {->
expressionOut.print(categoryFilterData.company.name)
})
invokeTag('link','g',61,['url':([controller: 'company', id: categoryFilterData.company.id])],3)
printHtmlPart(5)
}
printHtmlPart(5)
if(true && (categoryFilterData.category)) {
printHtmlPart(10)
expressionOut.print(categoryFilterData.category.name)
printHtmlPart(11)
invokeTag('render','g',65,['template':("/_common/category-breadcrumb"),'model':([
					categoryFilterData: categoryFilterData
			])],-1)
printHtmlPart(12)
invokeTag('render','g',66,['template':("/_common/flash-message")],-1)
printHtmlPart(13)
loop:{
int i = 0
for( obj in (itemInstanceList) ) {
printHtmlPart(14)
invokeTag('render','g',72,['template':("item"),'model':([itemInstance: obj])],-1)
printHtmlPart(4)
i++
}
}
printHtmlPart(12)
if(true && (params.max < rowsCount)) {
printHtmlPart(14)
invokeTag('paginate','g',74,['total':(rowsCount ?: 0)],-1)
printHtmlPart(4)
}
printHtmlPart(15)
}
else {
printHtmlPart(16)
for( obj in (lastCreated) ) {
printHtmlPart(14)
invokeTag('render','g',83,['template':("item"),'model':([itemInstance: obj])],-1)
printHtmlPart(12)
}
printHtmlPart(4)
if(true && (params.max < rowsCount)) {
printHtmlPart(14)
invokeTag('paginate','g',86,['total':(rowsCount ?: 0)],-1)
printHtmlPart(4)
}
printHtmlPart(15)
}
printHtmlPart(17)
if(true && (mainServices.size()>2)) {
printHtmlPart(2)
if(true && (!mainServices.isEmpty())) {
printHtmlPart(18)
for( item in (mainServices) ) {
printHtmlPart(19)
createTagBody(5, {->
printHtmlPart(20)
if(true && (item?.photo)) {
printHtmlPart(21)
expressionOut.print(createLink(controller: 'files', action: 'item', id: item.photo?.imageThumb.id, params: [name: item.photo?.imageThumb.file]))
printHtmlPart(22)
}
else {
printHtmlPart(23)
expressionOut.print(resource(dir: 'images', file: 'front/services.png'))
printHtmlPart(24)
}
printHtmlPart(25)
expressionOut.print(item.name ?: '')
printHtmlPart(26)
expressionOut.print(item.price)
printHtmlPart(27)
expressionOut.print(item.currency.name)
printHtmlPart(28)
expressionOut.print(item.measure.fullname)
printHtmlPart(29)
})
invokeTag('link','g',121,['url':([controller: 'services', action: 'item', id: item.id])],5)
printHtmlPart(30)
}
printHtmlPart(31)
}
printHtmlPart(32)
}
else {
printHtmlPart(14)
if(true && (!mainServices.isEmpty())) {
printHtmlPart(33)
for( item in (mainServices) ) {
printHtmlPart(34)
createTagBody(5, {->
printHtmlPart(35)
if(true && (item?.photo)) {
printHtmlPart(36)
expressionOut.print(createLink(controller: 'files', action: 'item', id: item.photo?.imageThumb.id, params: [name: item.photo?.imageThumb.file]))
printHtmlPart(37)
}
else {
printHtmlPart(38)
expressionOut.print(resource(dir: 'images', file: 'front/services.png'))
printHtmlPart(39)
}
printHtmlPart(40)
expressionOut.print(item.name ?: '')
printHtmlPart(41)
expressionOut.print(item.price)
printHtmlPart(42)
expressionOut.print(item.currency.name)
printHtmlPart(28)
expressionOut.print(item.measure.fullname)
printHtmlPart(43)
})
invokeTag('link','g',172,['url':([controller: 'services', action: 'item', id: item.id])],5)
printHtmlPart(44)
}
printHtmlPart(45)
}
printHtmlPart(4)
}
printHtmlPart(46)
invokeTag('render','g',184,['template':("/_common/gallery-multi")],-1)
printHtmlPart(47)
expressionOut.print(resource(dir: 'images', file: 'front/Arrow.png'))
printHtmlPart(48)
})
invokeTag('captureBody','sitemesh',200,[:],1)
printHtmlPart(49)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1484802176930L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
