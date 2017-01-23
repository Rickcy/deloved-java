import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_frontabout_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/front/about.gsp" }
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
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',46,[:],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(6)
expressionOut.print(resource(dir: 'images', file: 'front/icon_deal.png'))
printHtmlPart(7)
createClosureForHtmlPart(8, 2)
invokeTag('link','g',60,['controller':("article"),'action':("deal_online"),'style':("float: left;margin:12px;color: rgb(148, 196, 61);border: 1px solid rgb(148, 196, 61);padding: 5px;-webkit-border-radius:7px ;-moz-border-radius:7px ;border-radius:7px ;")],2)
printHtmlPart(9)
expressionOut.print(resource(dir: 'images', file: 'admin/deal_online1.jpg'))
printHtmlPart(10)
expressionOut.print(resource(dir: 'images', file: 'admin/deal_online2.jpg'))
printHtmlPart(11)
expressionOut.print(resource(dir: 'images', file: 'front/icon_rating.png'))
printHtmlPart(12)
createClosureForHtmlPart(8, 2)
invokeTag('link','g',96,['controller':("article"),'action':("rating_system"),'style':("float: left;margin:12px;color: rgb(75, 100, 190);border: 1px solid rgb(75, 100, 190);padding: 5px;-webkit-border-radius:7px ;-moz-border-radius:7px ;border-radius:7px ;")],2)
printHtmlPart(13)
expressionOut.print(resource(dir: 'images', file: 'admin/rating1.jpg'))
printHtmlPart(14)
expressionOut.print(resource(dir: 'images', file: 'admin/rating2.jpg'))
printHtmlPart(15)
expressionOut.print(resource(dir: 'images', file: 'front/icon_jurist.png'))
printHtmlPart(16)
createClosureForHtmlPart(8, 2)
invokeTag('link','g',127,['controller':("article"),'action':("jurist_service"),'style':("float: left;margin:12px;color: rgb(148, 196, 61);border: 1px solid rgb(148, 196, 61);padding: 5px;-webkit-border-radius:7px ;-moz-border-radius:7px ;border-radius:7px ;")],2)
printHtmlPart(13)
expressionOut.print(resource(dir: 'images', file: 'admin/jurist1.jpg'))
printHtmlPart(17)
expressionOut.print(resource(dir: 'images', file: 'admin/jurist2.jpg'))
printHtmlPart(18)
expressionOut.print(resource(dir: 'images', file: 'front/icon_mediation.png'))
printHtmlPart(19)
createClosureForHtmlPart(8, 2)
invokeTag('link','g',158,['controller':("article"),'action':("mediation_service"),'style':("float: left;margin:12px;color: rgb(75, 100, 190);border: 1px solid rgb(75, 100, 190);padding: 5px;-webkit-border-radius:7px ;-moz-border-radius:7px ;border-radius:7px ;")],2)
printHtmlPart(13)
expressionOut.print(resource(dir: 'images', file: 'admin/mediation.jpg'))
printHtmlPart(20)
expressionOut.print(resource(dir: 'images', file: 'front/icon_sud.png'))
printHtmlPart(21)
createClosureForHtmlPart(8, 2)
invokeTag('link','g',181,['controller':("article"),'action':("judge_service"),'style':("float: left;margin:12px;color: rgb(148, 196, 61);border: 1px solid rgb(148, 196, 61);padding: 5px;-webkit-border-radius:7px ;-moz-border-radius:7px ;border-radius:7px ;")],2)
printHtmlPart(22)
expressionOut.print(resource(dir: 'images', file: 'admin/sud.jpg'))
printHtmlPart(23)
})
invokeTag('captureBody','sitemesh',194,[:],1)
printHtmlPart(24)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1485145037412L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
