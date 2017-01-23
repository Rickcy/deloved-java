import ru.deloved.Measure
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_content_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/content/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: objInstance, field: 'news', 'error'))
printHtmlPart(1)
invokeTag('message','g',5,['code':("content.name.label"),'default':("News")],-1)
printHtmlPart(2)
invokeTag('textField','g',10,['name':("news"),'class':("form-control"),'value':(objInstance?.news)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: objInstance, field: 'name', 'error'))
printHtmlPart(4)
invokeTag('message','g',15,['code':("content.name.label"),'default':("Название")],-1)
printHtmlPart(5)
invokeTag('textField','g',20,['name':("name"),'class':("form-control"),'value':(objInstance?.name)],-1)
printHtmlPart(6)
invokeTag('message','g',32,['code':("content.code.label"),'default':("Time")],-1)
printHtmlPart(7)
invokeTag('textField','g',37,['name':("time"),'class':("form-control"),'value':(objInstance?.time)],-1)
printHtmlPart(8)
expressionOut.print(hasErrors(bean: objInstance, field: 'code', 'error'))
printHtmlPart(9)
invokeTag('message','g',43,['code':("content.code.label"),'default':("Code")],-1)
printHtmlPart(5)
invokeTag('textField','g',48,['name':("code"),'class':("form-control"),'value':(objInstance?.code)],-1)
printHtmlPart(8)
expressionOut.print(hasErrors(bean: objInstance, field: 'enabled', 'error'))
printHtmlPart(10)
invokeTag('message','g',54,['code':("content.enabled.label"),'default':("Enabled")],-1)
printHtmlPart(11)
invokeTag('checkBox','g',58,['name':("enabled"),'value':(objInstance?.enabled)],-1)
printHtmlPart(12)
expressionOut.print(hasErrors(bean: objInstance, field: 'content', 'error'))
printHtmlPart(13)
invokeTag('message','g',64,['code':("content.content.label"),'default':("Content")],-1)
printHtmlPart(14)
invokeTag('textArea','g',68,['class':("form-control"),'name':("content"),'value':(objInstance?.content),'rows':("15"),'maxlength':("500000")],-1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1473920757218L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
