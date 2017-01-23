import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_account_affiliate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/account/_affiliate.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(i)
printHtmlPart(1)
expressionOut.print(i == 0 || active == true ? 'in active' : '')
printHtmlPart(2)
invokeTag('hiddenField','g',6,['name':("aff.${i}.id"),'value':(affiliateInstance?.id)],-1)
printHtmlPart(3)
invokeTag('textField','g',13,['name':("aff.${i}.address"),'class':("form-control"),'value':(affiliateInstance?.address),'style':("width: 100%")],-1)
printHtmlPart(4)
invokeTag('textField','g',22,['name':("aff.${i}.city"),'class':("form-control"),'value':(affiliateInstance?.city?.name),'style':("width: 100%")],-1)
printHtmlPart(5)
invokeTag('textField','g',31,['name':("aff.${i}.email"),'class':("form-control"),'value':(affiliateInstance?.email),'style':("width: 100%")],-1)
printHtmlPart(6)
invokeTag('textField','g',40,['name':("aff.${i}.phone"),'class':("form-control"),'value':(affiliateInstance?.phone),'style':("width: 100%")],-1)
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1458714262960L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
