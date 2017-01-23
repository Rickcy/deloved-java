import ru.deloved.DealStatus
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_deal_deal_progress_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/deal/_deal-progress.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(dealInstance.position())
printHtmlPart(1)
if(true && ([DealStatus.Rejected.value(), DealStatus.Failed.value()].contains(dealInstance.status))) {
printHtmlPart(2)
expressionOut.print(dealInstance.position())
printHtmlPart(3)
}
else if(true && (ru.deloved.DealStatus.Suspended.value() == dealInstance.status)) {
printHtmlPart(4)
expressionOut.print(dealInstance.position())
printHtmlPart(3)
}
else {
printHtmlPart(5)
expressionOut.print(dealInstance.position())
printHtmlPart(3)
}
printHtmlPart(6)
invokeTag('render','g',29,['template':("check-points"),'model':([
			        dealInstance: dealInstance
			])],-1)
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1455784997437L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
