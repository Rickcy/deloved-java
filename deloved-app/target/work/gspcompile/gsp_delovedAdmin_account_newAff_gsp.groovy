import ru.deloved.Affiliate
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_account_newAff_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/account/_newAff.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('set','g',2,['var':("liID"),'value':(new java.util.Date().time)],-1)
printHtmlPart(1)
expressionOut.print(liID)
printHtmlPart(2)
invokeTag('hiddenField','g',4,['name':("aff.${i}.id"),'value':(affInstance?.id)],-1)
printHtmlPart(3)
invokeTag('hiddenField','g',5,['name':("aff.${i}.city.id"),'value':(affInstance?.city?.id)],-1)
printHtmlPart(3)
invokeTag('hiddenField','g',6,['name':("aff.${i}.cityname2"),'value':(affInstance?.city?.name)],-1)
printHtmlPart(4)
expressionOut.print(hasErrors(bean: affInstance, field: 'address', 'error'))
printHtmlPart(5)
invokeTag('textField','g',8,['class':("form-control"),'name':("aff.${i}.address"),'value':(affInstance?.address),'placeholder':(message(code: "affiliate.address.label"))],-1)
printHtmlPart(6)
expressionOut.print(hasErrors(bean: affInstance, field: 'city', 'error'))
printHtmlPart(7)
invokeTag('textField','g',12,['class':("form-control"),'name':("aff.${i}.cityname"),'value':(affInstance?.city?.name),'placeholder':(message(code: "affiliate.city.label"))],-1)
printHtmlPart(6)
expressionOut.print(hasErrors(bean: affInstance, field: 'phone', 'error'))
printHtmlPart(7)
invokeTag('textField','g',16,['class':("form-control"),'name':("aff.${i}.phone"),'value':(affInstance?.phone),'placeholder':(message(code: "affiliate.phone.label"))],-1)
printHtmlPart(6)
expressionOut.print(hasErrors(bean: affInstance, field: 'email', 'error'))
printHtmlPart(7)
invokeTag('textField','g',20,['class':("form-control"),'name':("aff.${i}.email"),'value':(affInstance?.email),'placeholder':(message(code: "affiliate.email.label"))],-1)
printHtmlPart(8)
expressionOut.print(liID)
printHtmlPart(9)
expressionOut.print(i)
printHtmlPart(10)
expressionOut.print(createLink(controller: 'account', action: 'cities'))
printHtmlPart(11)
expressionOut.print(i)
printHtmlPart(12)
expressionOut.print(i)
printHtmlPart(13)
expressionOut.print(i)
printHtmlPart(14)
expressionOut.print(i)
printHtmlPart(15)
expressionOut.print(i)
printHtmlPart(16)
expressionOut.print(i)
printHtmlPart(17)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1453713850304L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
