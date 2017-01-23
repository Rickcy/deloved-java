import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin__common_auto_complete_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/_common/_auto-complete.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('set','g',2,['var':("_acKeyName"),'value':(acKeyName.replaceAll('[.]','\\\\\\\\.'))],-1)
printHtmlPart(0)
invokeTag('set','g',3,['var':("_acViewName"),'value':(acViewName.replaceAll('[.]','\\\\\\\\.'))],-1)
printHtmlPart(1)
invokeTag('hiddenField','g',5,['name':(acKeyName),'value':(acKeyValue)],-1)
printHtmlPart(0)
invokeTag('hiddenField','g',6,['name':("${acViewName}old"),'value':(acViewValue)],-1)
printHtmlPart(0)
invokeTag('textField','g',7,['class':("form-control"),'name':(acViewName),'value':(acViewValue)],-1)
printHtmlPart(2)
expressionOut.print(_acViewName)
printHtmlPart(3)
expressionOut.print(acAction)
printHtmlPart(4)
expressionOut.print(acMinLength)
printHtmlPart(5)
expressionOut.print(_acViewName)
printHtmlPart(6)
expressionOut.print(_acKeyName)
printHtmlPart(7)
expressionOut.print(_acViewName)
printHtmlPart(8)
expressionOut.print(acOnChange?:'')
printHtmlPart(9)
expressionOut.print(_acKeyName)
printHtmlPart(10)
expressionOut.print(_acViewName)
printHtmlPart(11)
expressionOut.print(_acViewName)
printHtmlPart(12)
expressionOut.print(acOnSelect?:'')
printHtmlPart(13)
expressionOut.print(_acViewName)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1435115992704L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
