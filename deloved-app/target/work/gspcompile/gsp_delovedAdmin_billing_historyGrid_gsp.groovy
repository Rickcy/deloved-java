import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_billing_historyGrid_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/billing/_historyGrid.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createClosureForHtmlPart(1, 1)
invokeTag('remoteLink','g',8,['controller':("billing"),'action':("index"),'update':("historyGrid"),'params':(params + [sort: 'method', order: (params.order =='desc') ? 'asc' : 'desc'])],1)
printHtmlPart(2)
createClosureForHtmlPart(3, 1)
invokeTag('remoteLink','g',12,['controller':("billing"),'action':("index"),'update':("historyGrid"),'params':(params + [sort: 'dateCreated', order: (params.order =='desc') ? 'asc' : 'desc'])],1)
printHtmlPart(4)
createClosureForHtmlPart(5, 1)
invokeTag('remoteLink','g',16,['controller':("billing"),'action':("index"),'update':("historyGrid"),'params':(params + [sort: 'value', order: (params.order =='desc') ? 'asc' : 'desc'])],1)
printHtmlPart(6)
createClosureForHtmlPart(7, 1)
invokeTag('remoteLink','g',20,['controller':("billing"),'action':("index"),'update':("historyGrid"),'params':(params + [sort: 'keeper', order: (params.order =='desc') ? 'asc' : 'desc'])],1)
printHtmlPart(8)
createClosureForHtmlPart(9, 1)
invokeTag('remoteLink','g',24,['controller':("billing"),'action':("index"),'update':("historyGrid"),'params':(params + [sort: 'status', order: (params.order =='desc') ? 'asc' : 'desc'])],1)
printHtmlPart(10)
for( request in (requestInstanceList) ) {
printHtmlPart(11)
expressionOut.print(request.id)
printHtmlPart(12)
expressionOut.print(request.method.name)
printHtmlPart(12)
invokeTag('formatDate','g',35,['date':(request.dateCreated),'format':("dd.MM.yyyy hh.mm")],-1)
printHtmlPart(12)
expressionOut.print(request.value)
printHtmlPart(12)
expressionOut.print(request.keeper.currency.code)
printHtmlPart(12)
expressionOut.print(message(code: 'paymentRequest.status.' + request.status, default: request.status))
printHtmlPart(13)
if(true && (request.method.code != 'INCOME_MANUAL')) {
printHtmlPart(14)
expressionOut.print(request.id)
printHtmlPart(15)
}
else {
printHtmlPart(16)
expressionOut.print(createLink(controller: 'billing',  action: 'bill', id: request.id))
printHtmlPart(17)
}
printHtmlPart(18)
}
printHtmlPart(19)
invokeTag('set','g',52,['var':("offsetValue"),'value':(params.offset ? (params.offset).toInteger() : 0)],-1)
printHtmlPart(20)
expressionOut.print(offsetValue == 0 ? 'disabled' : '')
printHtmlPart(21)
createClosureForHtmlPart(22, 1)
invokeTag('remoteLink','g',58,['controller':("billing"),'action':("index"),'update':("historyGrid"),'params':(params + [offset: offsetValue != 0 ? offsetValue - 10 : offsetValue])],1)
printHtmlPart(23)
for( i in (0..<((requestInstanceTotal % 10 > 0) ? requestInstanceTotal/10 + 1 : requestInstanceTotal/10)) ) {
printHtmlPart(24)
expressionOut.print(offsetValue == i*10 ? 'active' : '')
printHtmlPart(25)
createTagBody(2, {->
expressionOut.print(i+1)
})
invokeTag('remoteLink','g',63,['controller':("billing"),'action':("index"),'update':("historyGrid"),'params':(params + [offset: (i*10)])],2)
printHtmlPart(26)
}
printHtmlPart(27)
expressionOut.print(offsetValue + 10 >= requestInstanceTotal ? 'disabled' : '')
printHtmlPart(21)
createClosureForHtmlPart(28, 1)
invokeTag('remoteLink','g',70,['controller':("billing"),'action':("index"),'update':("historyGrid"),'params':(params + [offset: offsetValue + 10 <= requestInstanceTotal ? offsetValue + 10 : offsetValue])],1)
printHtmlPart(29)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1460090074103L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
