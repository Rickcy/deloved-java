import ru.deloved.Measure
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_content_form2_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/content/_form2.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: objInstance, field: 'name', 'error'))
printHtmlPart(1)
invokeTag('message','g',6,['code':("content.name.label"),'default':("Название/h1")],-1)
printHtmlPart(2)
invokeTag('textField','g',11,['name':("name"),'class':("form-control"),'value':(objInstance?.name)],-1)
printHtmlPart(3)
invokeTag('message','g',19,['code':("content.name.label"),'default':("Title")],-1)
printHtmlPart(4)
invokeTag('textField','g',24,['name':("title"),'class':("form-control"),'value':(objInstance?.title)],-1)
printHtmlPart(5)
invokeTag('message','g',29,['code':("content.name.label"),'default':("Description")],-1)
printHtmlPart(4)
invokeTag('textField','g',34,['name':("description"),'class':("form-control"),'value':(objInstance?.description)],-1)
printHtmlPart(6)
invokeTag('message','g',40,['code':("content.name.label"),'default':("Keywords")],-1)
printHtmlPart(4)
invokeTag('textField','g',45,['name':("keywords"),'class':("form-control"),'value':(objInstance?.keywords)],-1)
printHtmlPart(7)
expressionOut.print(hasErrors(bean: objInstance, field: 'code', 'error'))
printHtmlPart(8)
invokeTag('message','g',57,['code':("content.code.label"),'default':("Code")],-1)
printHtmlPart(2)
invokeTag('textField','g',62,['name':("code"),'class':("form-control"),'value':(objInstance?.code)],-1)
printHtmlPart(9)
expressionOut.print(hasErrors(bean: objInstance, field: 'enabled', 'error'))
printHtmlPart(10)
invokeTag('message','g',68,['code':("content.enabled.label"),'default':("Enabled")],-1)
printHtmlPart(11)
invokeTag('checkBox','g',72,['name':("enabled"),'value':(objInstance?.enabled)],-1)
printHtmlPart(12)
expressionOut.print(hasErrors(bean: objInstance, field: 'content', 'error'))
printHtmlPart(13)
invokeTag('message','g',78,['code':("content.content.label"),'default':("MainContent/column1")],-1)
printHtmlPart(14)
invokeTag('textArea','g',82,['class':("form-control"),'name':("content"),'value':(objInstance?.content),'rows':("15"),'maxlength':("500000")],-1)
printHtmlPart(15)
expressionOut.print(hasErrors(bean: objInstance, field: 'content2', 'error'))
printHtmlPart(16)
invokeTag('message','g',86,['code':("content.content.label"),'default':("Content/column2")],-1)
printHtmlPart(14)
invokeTag('textArea','g',90,['class':("form-control"),'name':("content2"),'value':(objInstance?.content2),'rows':("15"),'maxlength':("500000")],-1)
printHtmlPart(17)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1473929424956L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
