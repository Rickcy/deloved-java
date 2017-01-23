import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_deal_new2_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/deal/_new2.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
if(true && (dealCreateCommandInstance.account)) {
printHtmlPart(0)
invokeTag('hiddenField','g',2,['name':("account"),'value':(dealCreateCommandInstance.account)],-1)
printHtmlPart(1)
}
printHtmlPart(2)
invokeTag('hiddenField','g',5,['name':("partner"),'value':(dealCreateCommandInstance.partner)],-1)
printHtmlPart(3)
expressionOut.print(freeAccount)
printHtmlPart(4)
if(true && (myAccounts)) {
printHtmlPart(5)
invokeTag('message','g',17,['code':("deal.account.label")],-1)
printHtmlPart(6)
invokeTag('select','g',22,['class':("form-control"),'name':("account"),'from':(myAccounts),'optionKey':("id"),'optionValue':("name")],-1)
printHtmlPart(7)
}
printHtmlPart(8)
expressionOut.print(hasErrors(bean: beanResource, field: 'ifBuyer', 'error'))
printHtmlPart(9)
invokeTag('message','g',32,['code':("deal.role.label"),'default':("DealRole")],-1)
printHtmlPart(10)
invokeTag('radio','g',39,['name':("isBuyer"),'value':("true"),'checked':("true")],-1)
printHtmlPart(11)
invokeTag('radio','g',43,['name':("isBuyer"),'value':("false")],-1)
printHtmlPart(12)
expressionOut.print(hasErrors(bean: dealInstance, field: 'subject', 'error'))
printHtmlPart(13)
invokeTag('message','g',55,['code':("deal.subject.label")],-1)
printHtmlPart(14)
createClosureForHtmlPart(15, 1)
invokeTag('textArea','g',65,['style':("height: 0px;cursor: default"),'class':("form-control text"),'name':("subject"),'required':(""),'value':(dealCreateCommandInstance?.subject)],1)
printHtmlPart(16)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1476418216249L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
