import ru.deloved.Measure
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_measure_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/measure/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: objInstance, field: 'name', 'error'))
printHtmlPart(1)
invokeTag('message','g',5,['code':("measure.name.label"),'default':("Name")],-1)
printHtmlPart(2)
invokeTag('textField','g',10,['name':("name"),'class':("form-control"),'value':(objInstance?.name)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: objInstance, field: 'fullname', 'error'))
printHtmlPart(4)
invokeTag('message','g',15,['code':("measure.fullname.label"),'default':("Fullname")],-1)
printHtmlPart(5)
invokeTag('textField','g',19,['name':("fullname"),'class':("form-control"),'value':(objInstance?.fullname)],-1)
printHtmlPart(6)
expressionOut.print(hasErrors(bean: objInstance, field: 'type', 'error'))
printHtmlPart(7)
invokeTag('message','g',25,['code':("measure.type.label"),'default':("Type")],-1)
printHtmlPart(8)
invokeTag('select','g',35,['id':("type"),'class':("form-control"),'name':("type.id"),'from':(ru.deloved.CategoryType.list()),'optionKey':("id"),'optionValue':({ message(code: 'categorytype.' + it.code, default: it.code) }),'value':(objInstance?.type?.id)],-1)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1435115993105L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
