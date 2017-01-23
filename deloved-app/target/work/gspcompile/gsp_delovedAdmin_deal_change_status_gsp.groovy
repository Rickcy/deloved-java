import ru.deloved.DealStatus
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_deal_change_status_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/deal/_change-status.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
expressionOut.print(statusList ? '' : 'disabled')
printHtmlPart(2)
if(true && (statusList)) {
printHtmlPart(3)
for( st in (statusList) ) {
printHtmlPart(4)
expressionOut.print(st.value)
printHtmlPart(5)
expressionOut.print(message(code: 'deal.status.' + st + '.i', default: st))
printHtmlPart(6)
}
printHtmlPart(7)
}
printHtmlPart(8)
invokeTag('hiddenField','g',51,['name':("newstatus"),'value':("")],-1)
printHtmlPart(9)
createClosureForHtmlPart(10, 1)
invokeTag('link','g',79,['id':("consultButton"),'name':("consultButton"),'controller':("juristConsult"),'action':("create"),'target':("_blank"),'class':("btn btn-primary")],1)
printHtmlPart(11)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1484021435234L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
