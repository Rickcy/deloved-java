import ru.deloved.DealStatus
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_deal_check_points_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/deal/_check-points.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (![DealStatus.Suspended, DealStatus.Failed, DealStatus.Confirmed, DealStatus.Rejected].contains(dealInstance.status()))) {
printHtmlPart(1)
}
printHtmlPart(2)
if(true && (DealStatus.nonPaidStatuses().contains(dealInstance.status()))) {
printHtmlPart(3)
for( status in (DealStatus.nonPaidStatuses()) ) {
printHtmlPart(4)
expressionOut.print(status.position())
printHtmlPart(5)
expressionOut.print(message(code: 'deal.status.' + status, default: status))
printHtmlPart(6)
}
printHtmlPart(7)
}
printHtmlPart(0)
if(true && (DealStatus.fullPaidStatuses().contains(dealInstance.status()))) {
printHtmlPart(3)
for( status in (DealStatus.fullPaidStatuses()) ) {
printHtmlPart(4)
expressionOut.print(status.position())
printHtmlPart(8)
expressionOut.print(message(code: 'deal.status.' + status, default: status))
printHtmlPart(6)
}
printHtmlPart(9)
}
printHtmlPart(0)
if(true && (DealStatus.halfPaidStatuses().contains(dealInstance.status()))) {
printHtmlPart(3)
for( status in (DealStatus.halfPaidStatuses()) ) {
printHtmlPart(4)
expressionOut.print(status.position())
printHtmlPart(10)
expressionOut.print(message(code: 'deal.status.' + status, default: status))
printHtmlPart(6)
}
printHtmlPart(11)
}
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1464073142023L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
