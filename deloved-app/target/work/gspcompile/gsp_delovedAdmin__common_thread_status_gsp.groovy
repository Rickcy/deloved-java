import ru.deloved.DealStatus
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin__common_thread_status_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/_common/_thread-status.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(obj.person == myProfile ? 'bubble bubble-in bubble-status' : 'bubble bubble-out bubble-status')
printHtmlPart(1)
expressionOut.print(message(code: params.controller + '.status.' + obj.status(), default: obj.status()))
printHtmlPart(2)
if(true && (obj instanceof ru.deloved.DealPost)) {
printHtmlPart(3)
if(true && (obj?.dispute)) {
printHtmlPart(4)
createTagBody(3, {->
printHtmlPart(5)
expressionOut.print(message(code: 'deal.open.dispute'))
printHtmlPart(3)
})
invokeTag('link','g',13,['url':([resource: obj.dispute, action: 'thread'])],3)
printHtmlPart(6)
}
printHtmlPart(3)
if(true && (obj?.claim)) {
printHtmlPart(4)
createTagBody(3, {->
printHtmlPart(5)
expressionOut.print(message(code: 'deal.open.claim'))
printHtmlPart(3)
})
invokeTag('link','g',18,['url':([resource: obj.claim, action: 'thread'])],3)
printHtmlPart(6)
}
printHtmlPart(3)
if(true && (obj.status() == DealStatus.Failed && obj.deal.failedBy)) {
printHtmlPart(4)
createTagBody(3, {->
printHtmlPart(5)
expressionOut.print(obj.deal.failedBy.name)
printHtmlPart(3)
})
invokeTag('link','g',23,['url':([resource: obj.deal.failedBy, action: 'show'])],3)
printHtmlPart(6)
}
printHtmlPart(7)
}
printHtmlPart(8)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444124073627L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
