import ru.deloved.billing.PaymentRequest
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_paymentRequestindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/paymentRequest/index.gsp" }
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
createTagBody(2, {->
printHtmlPart(7)
invokeTag('textField','g',21,['placeholder':("Номер счета"),'class':("form-control"),'name':("id"),'value':(filter.id),'autocomplete':("off")],-1)
printHtmlPart(8)
invokeTag('select','g',30,['class':("form-control"),'name':("status"),'from':(ru.deloved.billing.RequestStatus.values()),'optionKey':({ it.value }),'valueMessagePrefix':("paymentRequest.filter.status"),'value':(filter.status),'noSelection':(['': message(code: 'paymentRequest.filter.status.all')])],-1)
printHtmlPart(9)
invokeTag('submitButton','g',34,['name':("filterAction"),'class':("btn btn-primary"),'value':(message(code: 'default.button.filter.label'))],-1)
printHtmlPart(10)
invokeTag('submitButton','g',35,['name':("resetAction"),'class':("btn btn-default"),'formaction':("reset"),'value':(message(code: 'default.button.filterReset.label'))],-1)
printHtmlPart(11)
})
invokeTag('form','g',38,['url':([controller: 'paymentRequest', action: 'index'])],2)
printHtmlPart(12)
invokeTag('sortableColumn','g',44,['property':("dateCreated"),'title':(message(code: 'paymentRequest.dateCreated.label', default: 'Дата создания'))],-1)
printHtmlPart(13)
invokeTag('sortableColumn','g',45,['property':("keeper"),'title':(message(code: 'paymentRequest.keeper.label', default: 'Keeper'))],-1)
printHtmlPart(13)
invokeTag('sortableColumn','g',46,['property':("profile"),'title':(message(code: 'paymentRequest.profile.label', default: 'Профиль'))],-1)
printHtmlPart(13)
invokeTag('sortableColumn','g',47,['property':("status"),'title':(message(code: 'paymentRequest.status.label', default: 'Статус'))],-1)
printHtmlPart(13)
invokeTag('sortableColumn','g',48,['property':("method"),'title':(message(code: 'paymentRequest.method.label', default: 'Метод'))],-1)
printHtmlPart(13)
invokeTag('sortableColumn','g',49,['property':("amount"),'title':(message(code: 'paymentRequest.amount.label', default: 'Сумма'))],-1)
printHtmlPart(14)
loop:{
int i = 0
for( obj in (paymentRequestInstanceList) ) {
printHtmlPart(15)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(16)
createTagBody(3, {->
expressionOut.print(obj.id)
})
invokeTag('link','g',55,['url':([resource: obj, action: 'show'])],3)
printHtmlPart(17)
invokeTag('formatDate','g',56,['date':(obj.dateCreated),'format':("dd.MM.yyyy HH:mm:ss")],-1)
printHtmlPart(17)
expressionOut.print(fieldValue(bean: obj, field: "keeper.number"))
printHtmlPart(17)
expressionOut.print(fieldValue(bean: obj, field: "profile.fio"))
printHtmlPart(17)
expressionOut.print(message(code: 'paymentRequest.status.' + obj.status, default: obj.status))
printHtmlPart(17)
expressionOut.print(fieldValue(bean: obj, field: "method.name"))
printHtmlPart(17)
expressionOut.print(formatNumber(number: obj.value, type: 'currency', currencyCode: obj.keeper.currency.code))
printHtmlPart(18)
i++
}
}
printHtmlPart(19)
if(true && (params.max < rowsCount)) {
printHtmlPart(20)
invokeTag('paginate','g',68,['total':(rowsCount ?: 0)],-1)
printHtmlPart(1)
}
printHtmlPart(21)
})
invokeTag('captureBody','sitemesh',73,[:],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1460087509260L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
