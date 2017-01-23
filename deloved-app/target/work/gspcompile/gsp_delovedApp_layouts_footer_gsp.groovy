import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_delovedApp_layouts_footer_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/_footer.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createClosureForHtmlPart(1, 1)
invokeTag('link','g',6,['controller':("article"),'action':("ticket")],1)
printHtmlPart(2)
createClosureForHtmlPart(3, 1)
invokeTag('link','g',7,['controller':("front"),'action':("about")],1)
printHtmlPart(2)
createClosureForHtmlPart(4, 1)
invokeTag('link','g',8,['controller':("front"),'action':("tariffs")],1)
printHtmlPart(2)
createClosureForHtmlPart(5, 1)
invokeTag('link','g',9,['controller':("front"),'action':("sogl")],1)
printHtmlPart(6)
createTagBody(1, {->
printHtmlPart(7)
invokeTag('render','g',11,['template':("/layouts/create-answer"),'model':([])],-1)
printHtmlPart(6)
})
invokeTag('ifNotLoggedIn','sec',12,[:],1)
printHtmlPart(8)
createTagBody(1, {->
printHtmlPart(7)
createClosureForHtmlPart(9, 2)
invokeTag('link','g',14,['controller':("ticket"),'action':("create")],2)
printHtmlPart(6)
})
invokeTag('ifLoggedIn','sec',15,[:],1)
printHtmlPart(10)
invokeTag('render','g',16,['template':("/layouts/create-suggestion"),'model':([])],-1)
printHtmlPart(11)
expressionOut.print(resource(dir: 'images', file: 'front/logo2.gif'))
printHtmlPart(12)
expressionOut.print(resource(dir: 'images', file: 'front/vkIcon.png'))
printHtmlPart(13)
expressionOut.print(resource(dir: 'images', file: 'front/facebook.png'))
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1480903555935L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
