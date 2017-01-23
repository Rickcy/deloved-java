import ru.deloved.ConsultCategory
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_consult_new_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/consult/_new.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (consultCreateCommandInstance.account)) {
printHtmlPart(1)
invokeTag('hiddenField','g',3,['name':("account"),'value':(consultCreateCommandInstance.account)],-1)
printHtmlPart(0)
}
printHtmlPart(2)
if(true && (myAccounts)) {
printHtmlPart(3)
invokeTag('message','g',17,['code':("consult.account.label")],-1)
printHtmlPart(4)
invokeTag('select','g',22,['class':("form-control"),'name':("account"),'from':(myAccounts),'optionKey':("id"),'optionValue':("name")],-1)
printHtmlPart(5)
}
printHtmlPart(6)
expressionOut.print(hasErrors(bean: consultInstance, field: 'subject', 'error'))
printHtmlPart(7)
invokeTag('message','g',29,['code':("consult.subject.label")],-1)
printHtmlPart(8)
invokeTag('textArea','g',35,['style':("min-height: 200px"),'class':("form-control"),'name':("subject"),'required':(""),'value':(consultCreateCommandInstance?.subject)],-1)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1470975446117L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
