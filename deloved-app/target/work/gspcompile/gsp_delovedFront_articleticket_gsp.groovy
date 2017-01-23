import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_articleticket_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/article/ticket.gsp" }
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
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',13,['gsp_sm_xmlClosingForEmptyTag':(""),'content':("Статьи"),'name':("description")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',14,['gsp_sm_xmlClosingForEmptyTag':(""),'content':("Статьи"),'name':("keywords")],-1)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',60,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
expressionOut.print(resource(dir: 'images', file: 'front/icon_rating.png'))
printHtmlPart(7)
})
invokeTag('link','g',78,['style':("color: white;text-shadow: 0 0 1px whitesmoke;"),'controller':("article"),'action':("rating_system")],2)
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(6)
expressionOut.print(resource(dir: 'images', file: 'front/icon_jurist.png'))
printHtmlPart(9)
})
invokeTag('link','g',83,['style':("color: white;text-shadow: 0 0 1px whitesmoke"),'controller':("article"),'action':("jurist_service")],2)
printHtmlPart(10)
createTagBody(2, {->
printHtmlPart(6)
expressionOut.print(resource(dir: 'images', file: 'front/icon_sud.png'))
printHtmlPart(11)
})
invokeTag('link','g',88,['style':("color: white;text-shadow: 0 0 1px whitesmoke"),'controller':("article"),'action':("judge_service")],2)
printHtmlPart(12)
createTagBody(2, {->
printHtmlPart(6)
expressionOut.print(resource(dir: 'images', file: 'front/icon_mediation.png'))
printHtmlPart(13)
})
invokeTag('link','g',92,['style':("color: white;text-shadow: 0 0 1px whitesmoke"),'controller':("article"),'action':("mediation_service")],2)
printHtmlPart(14)
createTagBody(2, {->
printHtmlPart(15)
expressionOut.print(resource(dir: 'images', file: 'front/icon_deal.png'))
printHtmlPart(16)
})
invokeTag('link','g',98,['style':("color: white;"),'controller':("article"),'action':("deal_online")],2)
printHtmlPart(17)
})
invokeTag('captureBody','sitemesh',101,[:],1)
printHtmlPart(18)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1481866464779L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
