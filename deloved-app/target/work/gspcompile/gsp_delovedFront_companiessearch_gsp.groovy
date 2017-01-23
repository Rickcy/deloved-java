import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_companiessearch_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/companies/search.gsp" }
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
invokeTag('wrapTitleTag','sitemesh',12,[:],2)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',66,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
invokeTag('textField','g',79,['required':(""),'name':("search"),'value':(params.search),'class':("search_big")],-1)
printHtmlPart(7)
invokeTag('hiddenField','g',87,['name':("regl"),'class':("form-control"),'value':(filter?.regl)],-1)
printHtmlPart(8)
invokeTag('hiddenField','g',92,['name':("catl"),'class':("form-control"),'value':(filter?.catl)],-1)
printHtmlPart(9)
})
invokeTag('form','g',101,['action':("search")],2)
printHtmlPart(10)
expressionOut.print(raw(regionFilterData.regionsTreeJson))
printHtmlPart(11)
expressionOut.print(raw(categoryFilterData.categoriesTreeJson))
printHtmlPart(12)
if(true && (rowsCount > 0)) {
printHtmlPart(13)
loop:{
int i = 0
for( obj in (accountInstanceList) ) {
printHtmlPart(14)
invokeTag('render','g',135,['template':("item"),'model':([accountInstance: obj])],-1)
printHtmlPart(15)
i++
}
}
printHtmlPart(13)
if(true && (rowsCount > 0 && params.max < rowsCount)) {
printHtmlPart(14)
invokeTag('paginate','g',139,['total':(rowsCount ?: 0)],-1)
printHtmlPart(15)
}
printHtmlPart(16)
}
else {
printHtmlPart(17)
}
printHtmlPart(18)
expressionOut.print(resource(dir: 'images', file: 'front/Arrow.png'))
printHtmlPart(19)
})
invokeTag('captureBody','sitemesh',146,[:],1)
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1485145037277L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
