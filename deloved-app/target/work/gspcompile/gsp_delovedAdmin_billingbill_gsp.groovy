import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_billingbill_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/billing/bill.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',20,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("Content-Type"),'content':("text/html; charset=utf-8")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',21,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',21,[:],2)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',22,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
expressionOut.print(paymentRequestInstance.id)
printHtmlPart(6)
invokeTag('formatDate','g',81,['date':(paymentRequestInstance.dateCreated),'format':("dd.MM.yyyy")],-1)
printHtmlPart(7)
expressionOut.print(account?.name)
printHtmlPart(8)
expressionOut.print(account?.name)
printHtmlPart(9)
expressionOut.print(formatNumber(number: paymentRequestInstance.value, type: 'currency', currencyCode: paymentRequestInstance.keeper.currency.code))
printHtmlPart(10)
expressionOut.print(formatNumber(number: paymentRequestInstance.value, type: 'currency', currencyCode: paymentRequestInstance.keeper.currency.code))
printHtmlPart(11)
expressionOut.print(formatNumber(number: paymentRequestInstance.value, type: 'currency', currencyCode: paymentRequestInstance.keeper.currency.code))
printHtmlPart(12)
expressionOut.print(formatNumber(number: paymentRequestInstance.value, type: 'currency', currencyCode: paymentRequestInstance.keeper.currency.code))
printHtmlPart(13)
expressionOut.print(formatNumber(number: paymentRequestInstance.value, type: 'currency', currencyCode: paymentRequestInstance.keeper.currency.code))
printHtmlPart(14)
expressionOut.print(propis)
printHtmlPart(15)
expressionOut.print(resource(dir: 'images', file: 'billDocument/sign1.png'))
printHtmlPart(16)
expressionOut.print(resource(dir: 'images', file: 'billDocument/sign2.png'))
printHtmlPart(17)
expressionOut.print(resource(dir: 'images', file: 'billDocument/Shtamp.png'))
printHtmlPart(18)
})
invokeTag('captureBody','sitemesh',180,[:],1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1436244202261L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
