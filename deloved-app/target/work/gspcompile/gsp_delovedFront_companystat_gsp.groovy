import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_companystat_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/company/stat.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (freeAccount)) {
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(2)
invokeTag('render','g',10,['template':("/_common/promo-modal-unauth")],-1)
printHtmlPart(1)
})
invokeTag('ifNotLoggedIn','sec',11,[:],2)
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(2)
invokeTag('render','g',13,['template':("/_common/promo-modal-unauth")],-1)
printHtmlPart(1)
})
invokeTag('ifAllGranted','sec',14,['roles':("ROLE_ACCOUNT")],2)
printHtmlPart(3)
}
else {
printHtmlPart(4)
}
printHtmlPart(5)
expressionOut.print(message(code: 'default.button.close.label'))
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1460974896751L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
