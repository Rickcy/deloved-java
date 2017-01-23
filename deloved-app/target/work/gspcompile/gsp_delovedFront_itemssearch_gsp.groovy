import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_itemssearch_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/items/search.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',11,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("front")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',11,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',12,[:],2)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',12,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
invokeTag('textField','g',28,['required':(""),'name':("search"),'class':("search_big"),'value':(params.search)],-1)
printHtmlPart(7)
invokeTag('hiddenField','g',37,['name':("regl"),'class':("form-control"),'value':(filter?.regl)],-1)
printHtmlPart(8)
invokeTag('hiddenField','g',43,['name':("catl"),'class':("form-control"),'value':(filter?.catl)],-1)
printHtmlPart(9)
invokeTag('textField','g',58,['placeholder':("Цена от"),'class':("form-control"),'name':("priceMin"),'value':(formatNumber(number: filter?.priceMin, format: '###,##0.00'))],-1)
printHtmlPart(10)
invokeTag('textField','g',66,['placeholder':("Цена до"),'class':("form-control"),'name':("priceMax"),'value':(formatNumber(number: filter?.priceMax, format: '###,##0.00'))],-1)
printHtmlPart(11)
})
invokeTag('form','g',91,['mapping':(mapping),'action':("search")],2)
printHtmlPart(12)
expressionOut.print(raw(regionFilterData.regionsTreeJson))
printHtmlPart(13)
expressionOut.print(raw(categoryFilterData.categoriesTreeJson))
printHtmlPart(14)
if(true && (rowsCount > 0)) {
printHtmlPart(15)
loop:{
int i = 0
for( obj in (itemInstanceList) ) {
printHtmlPart(16)
invokeTag('render','g',122,['template':("item"),'model':([itemInstance: obj])],-1)
printHtmlPart(17)
i++
}
}
printHtmlPart(15)
if(true && (rowsCount > 0 && params.max < rowsCount)) {
printHtmlPart(16)
invokeTag('paginate','g',126,['total':(rowsCount ?: 0)],-1)
printHtmlPart(17)
}
printHtmlPart(18)
}
else {
printHtmlPart(19)
}
printHtmlPart(20)
})
invokeTag('captureBody','sitemesh',129,[:],1)
printHtmlPart(21)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444124074874L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
