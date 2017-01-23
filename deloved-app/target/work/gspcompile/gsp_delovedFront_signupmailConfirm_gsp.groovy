import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_signupmailConfirm_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/signup/mailConfirm.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(contactConfirm.contact)
printHtmlPart(1)
createClosureForHtmlPart(2, 1)
invokeTag('link','g',5,['url':([base:baseURL, plugin: 'deloved-front', controller: 'signup', action: 'activate', params: [code: contactConfirm.secret]]),'target':("_blank")],1)
printHtmlPart(3)
createClosureForHtmlPart(4, 1)
invokeTag('link','g',8,['url':([base:baseURL, plugin: 'deloved-front', controller: 'signup', action: 'activate']),'target':("_blank")],1)
printHtmlPart(5)
expressionOut.print(contactConfirm.secret)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1435115995336L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
