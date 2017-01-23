import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_itemsindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/items/index.gsp" }
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
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',11,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',11,[:],2)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',12,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('render','g',33,['template':("/_common/region-filter"),'model':([
						regionFilterData: regionFilterData
				])],-1)
printHtmlPart(6)
invokeTag('render','g',43,['template':("/_common/category-filter"),'model':([
						categoryFilterData: categoryFilterData
				])],-1)
printHtmlPart(7)
if(true && (categoryFilterData.company)) {
printHtmlPart(8)
createTagBody(3, {->
expressionOut.print(categoryFilterData.company.name)
})
invokeTag('link','g',58,['url':([controller: 'company', id: categoryFilterData.company.id])],3)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (categoryFilterData.category)) {
printHtmlPart(11)
invokeTag('render','g',63,['template':("/_common/category-breadcrumb"),'model':([
					categoryFilterData: categoryFilterData
			])],-1)
printHtmlPart(11)
invokeTag('render','g',64,['template':("/_common/flash-message")],-1)
printHtmlPart(12)
loop:{
int i = 0
for( obj in (itemInstanceList) ) {
printHtmlPart(13)
invokeTag('render','g',70,['template':("item"),'model':([itemInstance: obj])],-1)
printHtmlPart(14)
i++
}
}
printHtmlPart(11)
if(true && (params.max < rowsCount)) {
printHtmlPart(13)
invokeTag('paginate','g',72,['total':(rowsCount ?: 0)],-1)
printHtmlPart(14)
}
printHtmlPart(10)
}
else {
printHtmlPart(11)
invokeTag('render','g',81,['template':("/_common/category-catalog"),'model':([
					categoryFilterData: categoryFilterData
			])],-1)
printHtmlPart(10)
}
printHtmlPart(15)
for( obj in (lastCreated) ) {
printHtmlPart(16)
if(true && (obj?.photo)) {
printHtmlPart(17)
expressionOut.print(createLink(controller: 'files', action: 'item', id: obj.photo?.imageThumb.id, params: [name: obj.photo?.imageThumb.file]))
printHtmlPart(18)
}
printHtmlPart(19)
createTagBody(3, {->
printHtmlPart(20)
invokeTag('fieldValue','g',101,['bean':(obj),'field':("name")],-1)
printHtmlPart(21)
})
invokeTag('link','g',101,['controller':("items"),'action':("item"),'id':(obj.id)],3)
printHtmlPart(22)
invokeTag('formatNumber','g',105,['number':(obj?.price),'type':("currency"),'currencyCode':(obj?.currency.code)],-1)
printHtmlPart(23)
expressionOut.print(obj?.measure?.name)
printHtmlPart(24)
}
printHtmlPart(25)
invokeTag('render','g',107,['template':("/_common/gallery-multi")],-1)
printHtmlPart(26)
})
invokeTag('captureBody','sitemesh',110,[:],1)
printHtmlPart(27)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444124074850L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
