import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_recoverindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/recover/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("front")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(1)
invokeTag('javascript','asset',8,['src':("application.js")],-1)
printHtmlPart(4)
invokeTag('javascript','asset',10,['src':("/mCustomScrollbar/jquery.mCustomScrollbar.concat.min.js")],-1)
printHtmlPart(1)
invokeTag('link','asset',11,['rel':("stylesheet"),'href':("/mCustomScrollbar/jquery.mCustomScrollbar.min.css")],-1)
printHtmlPart(1)
invokeTag('link','asset',12,['rel':("buttons"),'href':("/mCustomScrollbar/mCSB_buttons.png")],-1)
printHtmlPart(1)
invokeTag('link','asset',13,['rel':("stylesheet"),'href':("/main.css")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',14,['src':("front.js")],-1)
printHtmlPart(5)
createClosureForHtmlPart(6, 2)
invokeTag('link','g',29,['controller':("front"),'action':("news"),'params':([var:val])],2)
printHtmlPart(7)
})
invokeTag('captureHead','sitemesh',52,[:],1)
printHtmlPart(8)
createTagBody(1, {->
printHtmlPart(9)
invokeTag('render','g',58,['template':("/_common/flash-message")],-1)
printHtmlPart(10)
expressionOut.print(resource(dir: 'images', file: 'front/dea.png'))
printHtmlPart(11)
expressionOut.print(resource(dir: 'images', file: 'front/rating_ultra.png'))
printHtmlPart(12)
expressionOut.print(resource(dir: 'images', file: 'front/sud_ultra.png'))
printHtmlPart(13)
expressionOut.print(resource(dir: 'images', file: 'front/hammer.png'))
printHtmlPart(14)
expressionOut.print(resource(dir: 'images', file: 'front/mediation_ultra.png'))
printHtmlPart(15)
expressionOut.print(resource(dir: 'images', file: 'front/deal_online.jpg'))
printHtmlPart(16)
expressionOut.print(resource(dir: 'images', file: 'front/rating.jpg'))
printHtmlPart(17)
expressionOut.print(resource(dir: 'images', file: 'front/jurist.jpg'))
printHtmlPart(18)
expressionOut.print(resource(dir: 'images', file: 'front/sud.jpg'))
printHtmlPart(19)
expressionOut.print(resource(dir: 'images', file: 'front/mediation.jpg'))
printHtmlPart(20)
invokeTag('render','g',116,['template':("/_common/flash-message")],-1)
printHtmlPart(21)
invokeTag('render','g',123,['template':("formRecover")],-1)
printHtmlPart(22)
})
invokeTag('captureBody','sitemesh',134,[:],1)
printHtmlPart(23)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1470886078571L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
