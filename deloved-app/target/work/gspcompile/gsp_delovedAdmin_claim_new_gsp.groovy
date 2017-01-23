import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_claim_new_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/claim/_new.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (claimCreateCommandInstance.account)) {
printHtmlPart(1)
invokeTag('hiddenField','g',7,['name':("account"),'value':(claimCreateCommandInstance.account)],-1)
printHtmlPart(2)
}
printHtmlPart(3)
invokeTag('hiddenField','g',10,['name':("partner"),'value':(claimCreateCommandInstance.partner)],-1)
printHtmlPart(2)
invokeTag('hiddenField','g',11,['name':("deal"),'value':(claimCreateCommandInstance.deal)],-1)
printHtmlPart(4)
expressionOut.print(resource(dir: 'images', file: 'admin/sud_ultra.png'))
printHtmlPart(5)
expressionOut.print(resource(dir: 'images', file: 'admin/hammer.png'))
printHtmlPart(6)
if(true && (myAccounts)) {
printHtmlPart(7)
invokeTag('message','g',27,['code':("claim.account.label")],-1)
printHtmlPart(8)
invokeTag('select','g',32,['class':("form-control"),'name':("account"),'from':(myAccounts),'optionKey':("id"),'optionValue':("name")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
expressionOut.print(hasErrors(bean: claimInstance, field: 'subject', 'error'))
printHtmlPart(11)
invokeTag('textArea','g',42,['style':("min-height: 100px"),'class':("form-control"),'name':("subject"),'required':(""),'value':(claimCreateCommandInstance?.subject)],-1)
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1465879968704L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
