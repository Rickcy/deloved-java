import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_deal_scripts_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/deal/_scripts.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(createLink(controller: 'deal', action: 'upload', id: dealInstance.id))
printHtmlPart(1)
expressionOut.print(createLink(action: 'getCheckPoints', id: dealInstance.id))
printHtmlPart(2)
expressionOut.print(dealInstance.status)
printHtmlPart(3)
expressionOut.print(createLink([controller: 'deal', action: 'getLatestPosts']))
printHtmlPart(4)
expressionOut.print(dealInstance.status)
printHtmlPart(5)
expressionOut.print(ru.deloved.DealStatus.Rejected.value())
printHtmlPart(6)
expressionOut.print(ru.deloved.DealStatus.Failed.value())
printHtmlPart(7)
expressionOut.print(ru.deloved.DealStatus.Suspended.value())
printHtmlPart(8)
expressionOut.print(ru.deloved.DealStatus.SignUP.value())
printHtmlPart(9)
expressionOut.print(ru.deloved.DealStatus.Confirmed.value())
printHtmlPart(10)
expressionOut.print(createLink(action: 'getStatus', id: dealInstance.id))
printHtmlPart(11)
expressionOut.print(createLink(action: 'deletepost'))
printHtmlPart(12)
expressionOut.print(createLink(resource: dealInstance, action: 'post'))
printHtmlPart(13)
expressionOut.print(createLink(controller: 'deal', action: 'post', resource: dealInstance))
printHtmlPart(14)
invokeTag('staticContent','g',429,['code':("arbitrationСlause")],-1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1478831842414L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
