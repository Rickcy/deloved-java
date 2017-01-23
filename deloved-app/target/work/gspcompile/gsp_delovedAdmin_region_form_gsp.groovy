import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_region_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/region/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: objInstance, field: 'name', 'error'))
printHtmlPart(1)
invokeTag('message','g',3,['code':("region.name.label")],-1)
printHtmlPart(2)
invokeTag('textField','g',11,['class':("form-control"),'name':("name"),'required':(""),'value':(fieldValue(bean: objInstance, field: 'name'))],-1)
printHtmlPart(3)
if(true && (countryDefaults)) {
printHtmlPart(4)
invokeTag('message','g',19,['code':("region.currency.label"),'default':("Валюта")],-1)
printHtmlPart(5)
invokeTag('select','g',26,['id':("currency"),'class':("form-control"),'name':("currency.id"),'from':(ru.deloved.SystemCurrency.findAll()),'optionKey':("id"),'optionValue':("code"),'required':(""),'value':(countryDefaults?.currency?.id)],-1)
printHtmlPart(6)
}
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1435115993261L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
