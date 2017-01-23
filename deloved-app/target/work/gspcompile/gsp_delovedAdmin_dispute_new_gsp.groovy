import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_dispute_new_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/dispute/_new.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
if(true && (disputeCreateCommandInstance.account)) {
printHtmlPart(0)
invokeTag('hiddenField','g',2,['name':("account"),'value':(disputeCreateCommandInstance.account)],-1)
printHtmlPart(1)
}
printHtmlPart(2)
invokeTag('hiddenField','g',5,['name':("partner"),'value':(disputeCreateCommandInstance.partner)],-1)
printHtmlPart(1)
invokeTag('hiddenField','g',6,['name':("deal"),'value':(disputeCreateCommandInstance.deal)],-1)
printHtmlPart(3)
if(true && (myAccounts)) {
printHtmlPart(4)
invokeTag('message','g',16,['code':("dispute.account.label")],-1)
printHtmlPart(5)
invokeTag('select','g',21,['class':("form-control"),'name':("account"),'from':(myAccounts),'optionKey':("id"),'optionValue':("name")],-1)
printHtmlPart(6)
}
printHtmlPart(7)
expressionOut.print(hasErrors(bean: disputeInstance, field: 'subject'))
printHtmlPart(8)
invokeTag('message','g',28,['code':("dispute.subject.label")],-1)
printHtmlPart(9)
invokeTag('textArea','g',34,['style':("min-height: 200px"),'class':("form-control"),'name':("subject"),'required':(""),'value':(disputeCreateCommandInstance?.subject)],-1)
printHtmlPart(10)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1462423860347L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
