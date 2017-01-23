import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront__common_category_catalog_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/_common/_category-catalog.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
for( cat in (categoryFilterData.categories) ) {
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(2)
expressionOut.print(cat.id)
printHtmlPart(3)
expressionOut.print(cat.name)
printHtmlPart(4)
})
invokeTag('link','g',7,['params':(categoryFilterData.company ? [acategory: cat.id, offset: 0, company: categoryFilterData.company.id] : [category: cat.id, offset: 0])],2)
printHtmlPart(5)
invokeTag('set','g',9,['var':("cnt"),'value':(categoryFilterData.subcategories[cat].size())],-1)
printHtmlPart(6)
loop:{
int i = 0
for( cat2 in (categoryFilterData.subcategories[cat]) ) {
printHtmlPart(7)
createTagBody(3, {->
expressionOut.print(cat2.name)
})
invokeTag('link','g',12,['class':((cnt > 0) ? 'hiddencat hiddencat' + cat.id : ''),'params':(categoryFilterData.company ? [acategory: cat2.id, offset: 0, company: categoryFilterData.company.id] : [category: cat2.id, offset: 0])],3)
printHtmlPart(6)
i++
}
}
printHtmlPart(8)
}
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1485145037234L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
