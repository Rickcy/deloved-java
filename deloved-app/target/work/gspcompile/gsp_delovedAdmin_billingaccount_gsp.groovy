import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_billingaccount_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/billing/account.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("admin")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',6,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',8,['code':("billing.list.label")],-1)
printHtmlPart(5)
invokeTag('render','g',9,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
if(true && (keeper)) {
printHtmlPart(7)
invokeTag('render','g',20,['template':("addfunds"),'model':([
	        tariffs: tariffs,
			methods: methods
	])],-1)
printHtmlPart(8)
invokeTag('render','g',27,['template':("history"),'model':([
			requestInstanceList: requestInstanceList,
			requestInstanceTotal: requestInstanceTotal
	])],-1)
printHtmlPart(9)
}
else {
printHtmlPart(1)
createTagBody(3, {->
printHtmlPart(10)
invokeTag('submitButton','g',35,['name':("create"),'class':("btn btn-primary ft"),'value':("Создать счет")],-1)
printHtmlPart(1)
})
invokeTag('form','g',36,['url':([action: 'account']),'method':("POST")],3)
printHtmlPart(3)
}
printHtmlPart(0)
})
invokeTag('captureBody','sitemesh',39,[:],1)
printHtmlPart(11)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1458713031259L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
