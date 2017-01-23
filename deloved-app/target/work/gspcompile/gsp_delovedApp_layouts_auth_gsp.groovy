import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_delovedApp_layouts_auth_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/_auth.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(request.contextPath)
printHtmlPart(1)
createClosureForHtmlPart(2, 1)
invokeTag('link','g',37,['controller':("signup"),'action':("index")],1)
printHtmlPart(3)
expressionOut.print(message(code: "springSecurity.login.username.label"))
printHtmlPart(4)
expressionOut.print(message(code: "springSecurity.login.password.label"))
printHtmlPart(5)
createClosureForHtmlPart(6, 1)
invokeTag('link','g',43,['controller':("recover")],1)
printHtmlPart(7)
expressionOut.print(rememberMeParameter)
printHtmlPart(8)
if(true && (hasCookie)) {
printHtmlPart(9)
}
printHtmlPart(10)
invokeTag('message','g',47,['code':("springSecurity.login.remember.me.label")],-1)
printHtmlPart(11)
invokeTag('message','g',50,['code':("springSecurity.login.button")],-1)
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1484800535958L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
