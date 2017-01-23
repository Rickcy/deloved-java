import ru.deloved.billing.TariffPrice
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_tariffPriceindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/tariffPrice/index.gsp" }
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
invokeTag('message','g',12,['code':("tariff.list.label")],-1)
printHtmlPart(6)
invokeTag('render','g',14,['template':("/_common/flash-message")],-1)
printHtmlPart(7)
createTagBody(2, {->
invokeTag('message','g',17,['code':("tariff.button.new.label")],-1)
})
invokeTag('link','g',17,['class':("btn btn-success"),'action':("create")],2)
printHtmlPart(8)
expressionOut.print(message(code: 'default.button.delete.confirm.message'))
printHtmlPart(9)
invokeTag('message','g',30,['code':("default.button.actionSelect.label")],-1)
printHtmlPart(10)
invokeTag('message','g',33,['code':("tariff.button.delete.label")],-1)
printHtmlPart(11)
createTagBody(2, {->
printHtmlPart(12)
invokeTag('select','g',47,['class':("form-control"),'name':("currencyId"),'from':(ru.deloved.SystemCurrency.list()),'optionKey':("id"),'optionValue':("code"),'value':(filter.currencyId),'noSelection':(['': message(code: 'tariff.allcurrencies.label', default: '')])],-1)
printHtmlPart(13)
invokeTag('submitButton','g',51,['name':("filterAction"),'class':("btn btn-primary"),'value':(message(code: 'default.button.filter.label'))],-1)
printHtmlPart(14)
invokeTag('submitButton','g',52,['name':("resetAction"),'class':("btn btn-default"),'formaction':("reset"),'value':(message(code: 'default.button.filterReset.label'))],-1)
printHtmlPart(15)
})
invokeTag('form','g',57,['url':([controller: 'tariffPrice', action: 'index'])],2)
printHtmlPart(16)
createTagBody(2, {->
printHtmlPart(17)
invokeTag('hiddenField','g',60,['name':("batch_action"),'value':("")],-1)
printHtmlPart(18)
invokeTag('sortableColumn','g',66,['property':("name"),'title':(message(code: 'tariff.name.label', default: 'Name'))],-1)
printHtmlPart(14)
invokeTag('sortableColumn','g',67,['property':("price"),'title':(message(code: 'tariff.price.label', default: 'Price'))],-1)
printHtmlPart(19)
loop:{
int i = 0
for( obj in (tariffPriceInstanceList) ) {
printHtmlPart(20)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(21)
expressionOut.print(obj.id)
printHtmlPart(22)
createTagBody(4, {->
expressionOut.print(fieldValue(bean: obj, field: "name"))
})
invokeTag('link','g',82,['action':("edit"),'id':(obj.id),'elementId':("gridRow${obj.id}name"),'data-toggle':("modal"),'data-remote':(createLink(id: obj.id, action: 'edit')),'data-target':("#myModal")],4)
printHtmlPart(23)
expressionOut.print(obj.id)
printHtmlPart(24)
invokeTag('formatNumber','g',84,['number':(obj.price),'type':("currency"),'currencyCode':(obj.currency.code)],-1)
printHtmlPart(25)
i++
}
}
printHtmlPart(26)
if(true && (params.max < rowsCount)) {
printHtmlPart(27)
invokeTag('paginate','g',92,['total':(rowsCount ?: 0)],-1)
printHtmlPart(17)
}
printHtmlPart(1)
})
invokeTag('form','g',94,['id':("batch_form"),'url':([controller: 'tariffPrice', action: 'batch']),'method':("POST")],2)
printHtmlPart(28)
invokeTag('render','g',98,['template':("/_common/edit-container")],-1)
printHtmlPart(29)
})
invokeTag('captureBody','sitemesh',100,[:],1)
printHtmlPart(30)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1460087293614L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
