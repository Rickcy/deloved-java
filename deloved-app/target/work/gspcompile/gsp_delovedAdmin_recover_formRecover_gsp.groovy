import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_recover_formRecover_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/recover/_formRecover.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('javascript','asset',1,['src':("front.js")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',2,['src':("application.js")],-1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
createClosureForHtmlPart(3, 2)
invokeTag('link','g',13,['url':([controller: 'front', action: 'index']),'class':("btn btn-default")],2)
printHtmlPart(4)
})
invokeTag('formRemote','g',17,['name':("recoverForm"),'url':([controller: 'recover', action: 'send']),'update':("recoverContent"),'method':("POST"),'autocomplete':("on"),'style':("padding: 0; margin: 0"),'onFailure':("showMessage('danger', 'Почтовый ящик ' + jQuery('#email').val() + ' не найден. Проверте правильность написания.'); jQuery('#email').val('')")],1)
printHtmlPart(5)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1470886258592L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
