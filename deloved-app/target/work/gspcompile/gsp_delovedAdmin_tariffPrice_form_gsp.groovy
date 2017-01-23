import ru.deloved.billing.TariffPrice
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_tariffPrice_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/tariffPrice/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: objInstance, field: 'name', 'error'))
printHtmlPart(1)
invokeTag('message','g',5,['code':("tariff.name.label"),'default':("Name")],-1)
printHtmlPart(2)
invokeTag('textField','g',10,['name':("name"),'class':("form-control"),'value':(objInstance?.name)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: objInstance, field: 'price', 'error'))
printHtmlPart(4)
invokeTag('message','g',15,['code':("item.price.label"),'default':("Price")],-1)
printHtmlPart(5)
invokeTag('textField','g',20,['name':("price"),'class':("form-control"),'value':(formatNumber(number: (objInstance?.price) ?: 0, format: '###,##0.00')),'required':("")],-1)
printHtmlPart(6)
invokeTag('select','g',25,['id':("currency"),'class':("form-control"),'name':("currency.id"),'from':(ru.deloved.SystemCurrency.findAll()),'optionKey':("id"),'optionValue':({it.getSymbol(requestLocale)}),'required':(""),'value':(objInstance?.currency?.id)],-1)
printHtmlPart(7)
expressionOut.print(hasErrors(bean: objInstance, field: 'days', 'error'))
printHtmlPart(8)
invokeTag('message','g',31,['code':("tariff.days.label"),'default':("Days")],-1)
printHtmlPart(9)
invokeTag('textField','g',35,['name':("days"),'class':("form-control"),'value':(objInstance?.days)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: objInstance, field: 'weeks', 'error'))
printHtmlPart(10)
invokeTag('message','g',40,['code':("tariff.weeks.label"),'default':("Weeks")],-1)
printHtmlPart(9)
invokeTag('textField','g',44,['name':("weeks"),'class':("form-control"),'value':(objInstance?.weeks)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: objInstance, field: 'months', 'error'))
printHtmlPart(11)
invokeTag('message','g',49,['code':("tariff.months.label"),'default':("Months")],-1)
printHtmlPart(9)
invokeTag('textField','g',53,['name':("months"),'class':("form-control"),'value':(objInstance?.months)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: objInstance, field: 'years', 'error'))
printHtmlPart(12)
invokeTag('message','g',58,['code':("tariff.years.label"),'default':("Years")],-1)
printHtmlPart(9)
invokeTag('textField','g',62,['name':("years"),'class':("form-control"),'value':(objInstance?.years)],-1)
printHtmlPart(13)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1435115993369L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
