import ru.deloved.SystemCurrency
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_systemCurrencyshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/systemCurrency/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("admin")],-1)
printHtmlPart(1)
invokeTag('set','g',6,['var':("entityName"),'value':(message(code: 'currency.label', default: 'Currency'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',7,['code':("default.show.label"),'args':([entityName])],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',14,['code':("default.show.label"),'args':([entityName])],-1)
printHtmlPart(5)
invokeTag('render','g',16,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
if(true && (currencyInstance?.name)) {
printHtmlPart(7)
invokeTag('message','g',22,['code':("currency.name.label"),'default':("Name")],-1)
printHtmlPart(8)
invokeTag('fieldValue','g',24,['bean':(currencyInstance),'field':("name")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (currencyInstance?.code)) {
printHtmlPart(11)
invokeTag('message','g',30,['code':("currency.code.label"),'default':("Code")],-1)
printHtmlPart(12)
invokeTag('fieldValue','g',32,['bean':(currencyInstance),'field':("code")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (currencyInstance?.digit3)) {
printHtmlPart(13)
invokeTag('message','g',38,['code':("currency.digit3.label"),'default':("Digit3")],-1)
printHtmlPart(14)
invokeTag('fieldValue','g',40,['bean':(currencyInstance),'field':("digit3")],-1)
printHtmlPart(9)
}
printHtmlPart(15)
createTagBody(2, {->
printHtmlPart(16)
createTagBody(3, {->
invokeTag('message','g',49,['code':("default.button.edit.label"),'default':("Edit")],-1)
})
invokeTag('link','g',49,['class':("edit"),'action':("edit"),'resource':(currencyInstance)],3)
printHtmlPart(17)
invokeTag('actionSubmit','g',51,['class':("delete"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(18)
})
invokeTag('form','g',53,['url':([resource: currencyInstance, action: 'delete']),'method':("DELETE")],2)
printHtmlPart(19)
})
invokeTag('captureBody','sitemesh',55,[:],1)
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1435115993359L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
