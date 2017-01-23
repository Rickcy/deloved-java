import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_goodsindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/goods/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',11,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("front")],-1)
printHtmlPart(3)
invokeTag('captureMeta','sitemesh',13,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("keywords"),'content':(categoryFilterData?.category?.name)],-1)
printHtmlPart(3)
invokeTag('captureMeta','sitemesh',14,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("description"),'content':(categoryFilterData?.category?.name)],-1)
printHtmlPart(3)
createTagBody(2, {->
createTagBody(3, {->
printHtmlPart(4)
if(true && (categoryFilterData.category)) {
printHtmlPart(3)
expressionOut.print(categoryFilterData?.category.name)
printHtmlPart(5)
}
printHtmlPart(6)
})
invokeTag('captureTitle','sitemesh',17,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',18,[:],2)
printHtmlPart(3)
invokeTag('stylesheet','asset',24,['src':("front_not_main.css")],-1)
printHtmlPart(3)
invokeTag('javascript','asset',25,['src':("front_not_main.js")],-1)
printHtmlPart(3)
invokeTag('javascript','asset',26,['src':("goodInd.js")],-1)
printHtmlPart(0)
})
invokeTag('captureHead','sitemesh',26,[:],1)
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
printHtmlPart(10)
}
printHtmlPart(10)
if(true && (categoryFilterData.category)) {
printHtmlPart(11)
expressionOut.print(categoryFilterData.category.name)
printHtmlPart(12)
invokeTag('render','g',67,['template':("/_common/category-breadcrumb"),'model':([
					categoryFilterData: categoryFilterData
			])],-1)
printHtmlPart(13)
invokeTag('render','g',68,['template':("/_common/flash-message")],-1)
printHtmlPart(14)
loop:{
int i = 0
for( obj in (itemInstanceList) ) {
printHtmlPart(15)
invokeTag('render','g',74,['template':("item"),'model':([itemInstance: obj])],-1)
printHtmlPart(16)
i++
}
}
printHtmlPart(13)
if(true && (params.max < rowsCount)) {
printHtmlPart(15)
invokeTag('paginate','g',77,['total':(rowsCount ?: 0)],-1)
printHtmlPart(16)
}
printHtmlPart(10)
}
else {
printHtmlPart(17)
for( obj in (lastCreated) ) {
printHtmlPart(15)
invokeTag('render','g',85,['template':("item"),'model':([itemInstance: obj])],-1)
printHtmlPart(13)
}
printHtmlPart(16)
if(true && (params.max < rowsCount)) {
printHtmlPart(15)
invokeTag('paginate','g',88,['total':(rowsCount ?: 0)],-1)
printHtmlPart(16)
}
printHtmlPart(18)
}
printHtmlPart(19)
if(true && (mainGoods.size()>2)) {
printHtmlPart(3)
if(true && (!mainGoods.isEmpty())) {
printHtmlPart(20)
for( item in (mainGoods) ) {
printHtmlPart(21)
createTagBody(5, {->
printHtmlPart(22)
if(true && (item?.photo)) {
printHtmlPart(23)
expressionOut.print(createLink(controller: 'files', action: 'item', id: item.photo?.imageThumb.id, params: [name: item.photo?.imageThumb.file]))
printHtmlPart(24)
}
else {
printHtmlPart(25)
expressionOut.print(resource(dir: 'images', file: 'front/goods.png'))
printHtmlPart(26)
}
printHtmlPart(27)
expressionOut.print(item.name ?: '')
printHtmlPart(28)
expressionOut.print(item.price)
printHtmlPart(29)
expressionOut.print(item.currency.name)
printHtmlPart(30)
expressionOut.print(item.measure.fullname)
printHtmlPart(31)
})
invokeTag('link','g',122,['url':([controller: 'goods', action: 'item', id: item.id])],5)
printHtmlPart(32)
}
printHtmlPart(33)
}
printHtmlPart(34)
}
else {
printHtmlPart(15)
if(true && (!mainGoods.isEmpty())) {
printHtmlPart(35)
for( item in (mainGoods) ) {
printHtmlPart(36)
createTagBody(5, {->
printHtmlPart(37)
if(true && (item?.photo)) {
printHtmlPart(38)
expressionOut.print(createLink(controller: 'files', action: 'item', id: item.photo?.imageThumb.id, params: [name: item.photo?.imageThumb.file]))
printHtmlPart(39)
}
else {
printHtmlPart(40)
expressionOut.print(resource(dir: 'images', file: 'front/goods.png'))
printHtmlPart(41)
}
printHtmlPart(42)
expressionOut.print(item.name ?: '')
printHtmlPart(43)
expressionOut.print(item.price)
printHtmlPart(44)
expressionOut.print(item.currency.name)
printHtmlPart(30)
expressionOut.print(item.measure.fullname)
printHtmlPart(45)
})
invokeTag('link','g',180,['url':([controller: 'goods', action: 'item', id: item.id])],5)
printHtmlPart(46)
}
printHtmlPart(47)
}
printHtmlPart(16)
}
printHtmlPart(48)
invokeTag('render','g',191,['template':("/_common/gallery-multi")],-1)
printHtmlPart(49)
expressionOut.print(resource(dir: 'images', file: 'front/Arrow.png'))
printHtmlPart(50)
})
invokeTag('captureBody','sitemesh',205,[:],1)
printHtmlPart(51)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1485145037484L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
