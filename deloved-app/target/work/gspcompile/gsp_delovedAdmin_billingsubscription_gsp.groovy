import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_billingsubscription_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/billing/subscription.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("admin")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',6,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
if(true && (freeAccount)) {
printHtmlPart(6)
invokeTag('message','g',12,['code':("billing.list.subscription.activate")],-1)
printHtmlPart(7)
}
else {
printHtmlPart(6)
invokeTag('message','g',16,['code':("billing.list.subscription.renew")],-1)
printHtmlPart(7)
}
printHtmlPart(3)
createClosureForHtmlPart(8, 2)
invokeTag('link','g',18,['class':("btn btn-default"),'action':("account")],2)
printHtmlPart(3)
invokeTag('render','g',19,['template':("/_common/flash-message")],-1)
printHtmlPart(9)
for( tariff in (tariffs) ) {
printHtmlPart(10)
expressionOut.print(tariff.name)
printHtmlPart(11)
expressionOut.print(30*tariff.months)
printHtmlPart(12)
invokeTag('formatNumber','g',37,['number':(tariff.price/tariff.months),'format':("####")],-1)
printHtmlPart(13)
invokeTag('formatNumber','g',38,['number':(tariff.price),'format':("####")],-1)
printHtmlPart(14)
invokeTag('formatNumber','g',39,['number':(basePrice*tariff.months - tariff.price),'format':("####")],-1)
printHtmlPart(15)
createTagBody(3, {->
printHtmlPart(16)
expressionOut.print(tariff.id)
printHtmlPart(17)
invokeTag('submitButton','g',43,['class':("tariff-btn"),'name':('send'+tariff.id),'value':("Купить")],-1)
printHtmlPart(18)
})
invokeTag('form','g',44,['name':('subscriptionForm'+tariff.id),'method':("POST"),'action':("invoice")],3)
printHtmlPart(19)
}
printHtmlPart(20)
})
invokeTag('captureBody','sitemesh',60,[:],1)
printHtmlPart(21)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1436256865002L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
