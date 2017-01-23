import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_billing_addfunds_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/billing/_addfunds.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
for( tariff in (tariffs) ) {
printHtmlPart(2)
expressionOut.print(tariff.name)
printHtmlPart(3)
invokeTag('formatNumber','g',28,['number':(tariff.price/tariff.months),'format':("####")],-1)
printHtmlPart(4)
invokeTag('formatNumber','g',33,['number':(tariff.price),'format':("####")],-1)
printHtmlPart(4)
invokeTag('formatNumber','g',38,['number':(basePrice.price*tariff.months - tariff.price),'format':("####")],-1)
printHtmlPart(5)
expressionOut.print(tariff.price)
printHtmlPart(6)
expressionOut.print(tariff.id)
printHtmlPart(7)
}
printHtmlPart(8)
for( method in (methods) ) {
printHtmlPart(9)
expressionOut.print(method.id)
printHtmlPart(10)
expressionOut.print(method.name)
printHtmlPart(11)
}
printHtmlPart(12)
invokeTag('submitButton','g',69,['name':("payment"),'class':("btn btn-primary"),'onclick':(""),'style':("margin-left: 20px"),'value':("Оплатить")],-1)
printHtmlPart(13)
})
invokeTag('form','g',71,['name':("payForm"),'url':([controller: 'billing', action: 'income']),'target':("_blank"),'method':("POST")],1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1460087584828L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
