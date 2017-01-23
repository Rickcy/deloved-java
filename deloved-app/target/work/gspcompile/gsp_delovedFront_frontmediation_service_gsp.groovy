import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_frontmediation_service_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/front/mediation_service.gsp" }
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
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
expressionOut.print(resource(dir: 'images', file: 'admin/dealz.png'))
printHtmlPart(6)
createClosureForHtmlPart(7, 2)
invokeTag('link','g',20,['controller':("front"),'action':("deal_online"),'style':("float: right;margin-top: 10px;color: rgb(148, 196, 61);border: 1px solid rgb(148, 196, 61);padding: 5px;-webkit-border-radius:7px ;-moz-border-radius:7px ;border-radius:7px ;")],2)
printHtmlPart(8)
expressionOut.print(resource(dir: 'images', file: 'admin/arrow_right.jpg'))
printHtmlPart(9)
expressionOut.print(resource(dir: 'images', file: 'admin/deal_online1.jpg'))
printHtmlPart(10)
expressionOut.print(resource(dir: 'images', file: 'admin/deal_online2.jpg'))
printHtmlPart(11)
expressionOut.print(resource(dir: 'images', file: 'admin/arrow_left.jpg'))
printHtmlPart(12)
expressionOut.print(resource(dir: 'images', file: 'admin/rating_ultra.png'))
printHtmlPart(13)
createClosureForHtmlPart(7, 2)
invokeTag('link','g',50,['controller':("front"),'action':("rating_system"),'style':("float: right;margin-top: 10px;color: rgb(75, 100, 190);border: 1px solid rgb(75, 100, 190);padding: 5px;-webkit-border-radius:7px ;-moz-border-radius:7px ;border-radius:7px ;")],2)
printHtmlPart(14)
expressionOut.print(resource(dir: 'images', file: 'admin/arrow_right.jpg'))
printHtmlPart(15)
expressionOut.print(resource(dir: 'images', file: 'admin/rating1.jpg'))
printHtmlPart(16)
expressionOut.print(resource(dir: 'images', file: 'admin/rating2.jpg'))
printHtmlPart(11)
expressionOut.print(resource(dir: 'images', file: 'admin/arrow_left.jpg'))
printHtmlPart(17)
expressionOut.print(resource(dir: 'images', file: 'admin/sud_ultra.png'))
printHtmlPart(18)
createClosureForHtmlPart(7, 2)
invokeTag('link','g',73,['controller':("front"),'action':("jurist_service"),'style':("float: right;margin-top: 10px;color: rgb(148, 196, 61);border: 1px solid rgb(148, 196, 61);padding: 5px;-webkit-border-radius:7px ;-moz-border-radius:7px ;border-radius:7px ;")],2)
printHtmlPart(14)
expressionOut.print(resource(dir: 'images', file: 'admin/arrow_right.jpg'))
printHtmlPart(15)
expressionOut.print(resource(dir: 'images', file: 'admin/jurist1.jpg'))
printHtmlPart(16)
expressionOut.print(resource(dir: 'images', file: 'admin/jurist2.jpg'))
printHtmlPart(11)
expressionOut.print(resource(dir: 'images', file: 'admin/arrow_left.jpg'))
printHtmlPart(19)
expressionOut.print(resource(dir: 'images', file: 'admin/mediation_ultra.png'))
printHtmlPart(20)
createClosureForHtmlPart(7, 2)
invokeTag('link','g',96,['controller':("front"),'action':("mediation_service"),'style':("float: right;margin-top: 10px;color: rgb(75, 100, 190);border: 1px solid rgb(75, 100, 190);padding: 5px;-webkit-border-radius:7px ;-moz-border-radius:7px ;border-radius:7px ;")],2)
printHtmlPart(14)
expressionOut.print(resource(dir: 'images', file: 'admin/arrow_right.jpg'))
printHtmlPart(15)
expressionOut.print(resource(dir: 'images', file: 'admin/mediation.jpg'))
printHtmlPart(21)
expressionOut.print(resource(dir: 'images', file: 'admin/hammer.png'))
printHtmlPart(22)
expressionOut.print(resource(dir: 'images', file: 'admin/sud.jpg'))
printHtmlPart(11)
expressionOut.print(resource(dir: 'images', file: 'admin/arrow_left.jpg'))
printHtmlPart(23)
createClosureForHtmlPart(7, 2)
invokeTag('link','g',118,['controller':("front"),'action':("judge_service"),'style':("float: right;margin-top: 10px;color: rgb(148, 196, 61);border: 1px solid rgb(148, 196, 61);padding: 5px;-webkit-border-radius:7px ;-moz-border-radius:7px ;border-radius:7px ;")],2)
printHtmlPart(24)
})
invokeTag('captureBody','sitemesh',126,[:],1)
printHtmlPart(25)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1473827203637L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
