import ru.deloved.billing.RequestStatus
import  ru.deloved.billing.PaymentRequest
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_paymentRequestshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/paymentRequest/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("admin")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('render','g',15,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
invokeTag('message','g',20,['code':("paymentRequest.id.label"),'default':("ID")],-1)
printHtmlPart(7)
invokeTag('fieldValue','g',21,['bean':(paymentRequestInstance),'field':("id")],-1)
printHtmlPart(8)
invokeTag('message','g',24,['code':("paymentRequest.sum.label"),'default':("Сумма")],-1)
printHtmlPart(9)
expressionOut.print(formatNumber(number: paymentRequestInstance.value, type: 'currency', currencyCode: paymentRequestInstance.keeper.currency.code))
printHtmlPart(10)
createTagBody(2, {->
printHtmlPart(11)
createTagBody(3, {->
invokeTag('message','g',31,['code':("default.cancel")],-1)
})
invokeTag('link','g',31,['class':("btn btn-default"),'action':("index")],3)
printHtmlPart(12)
if(true && (paymentRequestInstance.status == ru.deloved.billing.RequestStatus.New.value() && paymentRequestInstance.method.code == 'INCOME_MANUAL')) {
printHtmlPart(13)
invokeTag('actionSubmit','g',35,['class':("btn btn-warning"),'action':("approve"),'value':(message(code: 'paymentRequest.button.approve.label', default: 'Подтвердить')),'onclick':("return confirm('${message(code: 'paymentRequest.button.approve.confirm.message', default: 'Вы точно хотите подтвердить?')}');")],-1)
printHtmlPart(14)
invokeTag('actionSubmit','g',37,['class':("btn btn-warning"),'action':("decline"),'value':(message(code: 'paymentRequest.button.approve.label', default: 'Отказ')),'onclick':("return confirm('${message(code: 'paymentRequest.button.decline.confirm.message', default: 'Вы точно хотите отказать')}');")],-1)
printHtmlPart(12)
}
printHtmlPart(15)
})
invokeTag('form','g',41,['url':([resource: paymentRequestInstance, action: 'show']),'method':("POST")],2)
printHtmlPart(16)
})
invokeTag('captureBody','sitemesh',43,[:],1)
printHtmlPart(17)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1456377739492L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
