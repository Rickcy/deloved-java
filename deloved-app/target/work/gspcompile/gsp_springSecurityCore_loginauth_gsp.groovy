import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='spring-security-core', version='2.0-RC4')
class gsp_springSecurityCore_loginauth_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/spring-security-core-2.0-RC4/grails-app/views/login/auth.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(postUrl)
printHtmlPart(1)
createClosureForHtmlPart(2, 1)
invokeTag('link','g',4,['controller':("signup"),'action':("index")],1)
printHtmlPart(3)
expressionOut.print(message(code: "springSecurity.login.username.label"))
printHtmlPart(4)
expressionOut.print(message(code: "springSecurity.login.password.label"))
printHtmlPart(5)
createClosureForHtmlPart(6, 1)
invokeTag('link','g',10,['controller':("recover")],1)
printHtmlPart(7)
expressionOut.print(rememberMeParameter)
printHtmlPart(8)
if(true && (hasCookie)) {
printHtmlPart(9)
}
printHtmlPart(10)
invokeTag('message','g',14,['code':("springSecurity.login.remember.me.label")],-1)
printHtmlPart(11)
invokeTag('message','g',17,['code':("springSecurity.login.button")],-1)
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1485145025290L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
