import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_ticket_new_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/ticket/_new.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
if(true && (ticketCreateCommandInstance.account)) {
printHtmlPart(0)
invokeTag('hiddenField','g',2,['name':("account"),'value':(ticketCreateCommandInstance.account)],-1)
printHtmlPart(1)
}
printHtmlPart(2)
if(true && (myAccounts)) {
printHtmlPart(3)
invokeTag('message','g',12,['code':("ticket.account.label")],-1)
printHtmlPart(4)
invokeTag('select','g',17,['class':("form-control"),'name':("account"),'from':(myAccounts),'optionKey':("id"),'optionValue':("name")],-1)
printHtmlPart(5)
}
printHtmlPart(6)
expressionOut.print(hasErrors(bean: ticketInstance, field: 'subject', 'error'))
printHtmlPart(7)
invokeTag('message','g',24,['code':("ticket.subject.label")],-1)
printHtmlPart(8)
invokeTag('textArea','g',30,['style':("min-height: 200px"),'class':("form-control"),'name':("subject"),'required':(""),'value':(ticketCreateCommandInstance?.subject)],-1)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1470033146798L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
