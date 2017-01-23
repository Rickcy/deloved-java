import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_goodssearch_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/goods/search.gsp" }
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
printHtmlPart(2)
invokeTag('javascript','asset',12,['src':("searchgoods.js")],-1)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',77,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
invokeTag('textField','g',97,['required':(""),'name':("search"),'class':("search_big"),'value':(params.search)],-1)
printHtmlPart(7)
invokeTag('hiddenField','g',106,['name':("regl"),'class':("form-control"),'value':(filter?.regl)],-1)
printHtmlPart(8)
invokeTag('hiddenField','g',112,['name':("catl"),'class':("form-control"),'value':(filter?.catl)],-1)
printHtmlPart(9)
if(true && (rowsCount >5)) {
printHtmlPart(10)
}
printHtmlPart(11)
invokeTag('textField','g',184,['placeholder':("Цена от"),'class':("form-control"),'name':("priceMin"),'value':(formatNumber(number: filter?.priceMin, format: '###,##0.00'))],-1)
printHtmlPart(12)
invokeTag('textField','g',192,['placeholder':("Цена до"),'class':("form-control"),'name':("priceMax"),'value':(formatNumber(number: filter?.priceMax, format: '###,##0.00'))],-1)
printHtmlPart(13)
})
invokeTag('form','g',216,['mapping':(mapping),'action':("search")],2)
printHtmlPart(14)
expressionOut.print(raw(regionFilterData.regionsTreeJson))
printHtmlPart(15)
expressionOut.print(raw(categoryFilterData.categoriesTreeJson))
printHtmlPart(16)
if(true && (rowsCount > 0)) {
printHtmlPart(17)
loop:{
int i = 0
for( obj in (itemInstanceList) ) {
printHtmlPart(18)
invokeTag('render','g',247,['template':("item"),'model':([itemInstance: obj])],-1)
printHtmlPart(19)
i++
}
}
printHtmlPart(17)
if(true && (rowsCount > 0 && params.max < rowsCount)) {
printHtmlPart(18)
invokeTag('paginate','g',251,['total':(rowsCount ?: 0)],-1)
printHtmlPart(19)
}
printHtmlPart(20)
}
else {
printHtmlPart(21)
}
printHtmlPart(22)
expressionOut.print(resource(dir: 'images', file: 'front/Arrow.png'))
printHtmlPart(23)
})
invokeTag('captureBody','sitemesh',258,[:],1)
printHtmlPart(24)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1477903391962L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
