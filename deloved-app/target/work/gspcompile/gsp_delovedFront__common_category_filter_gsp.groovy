import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront__common_category_filter_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/_common/_category-filter.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (categoryFilterData != null && categoryFilterData.level != null)) {
printHtmlPart(1)
createClosureForHtmlPart(2, 2)
invokeTag('link','g',4,['style':("color: white;background-color: #4B7BBE;padding: 10px;"),'params':(categoryFilterData.company ? [acategory: 0, company: categoryFilterData.company.id] : [category: 0])],2)
printHtmlPart(3)
createTagBody(2, {->
expressionOut.print(categoryFilterData.level.name)
})
invokeTag('link','g',16,['class':((categoryFilterData.level == categoryFilterData.category) ? 'active' : 'under_active'),'params':(categoryFilterData.company ?
									   [
											   acategory: categoryFilterData.level.id,
											   offset   : 0,
											   company  : categoryFilterData.company.id
									   ] :
									   [
											   category: categoryFilterData.level.id,
											   offset  : 0
									   ])],2)
printHtmlPart(4)
}
printHtmlPart(5)
for( cat in (categoryFilterData.categories) ) {
printHtmlPart(6)
createTagBody(2, {->
expressionOut.print(cat.name)
})
invokeTag('link','g',34,['class':((cat == categoryFilterData.category) ? 'active' : ''),'params':(categoryFilterData.company ?
				[
						acategory: cat.id,
						offset   : 0,
						company  : categoryFilterData.company.id
				] :
				[
						category: cat.id,
						offset  : 0
				])],2)
printHtmlPart(4)
}
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1461301295401L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
