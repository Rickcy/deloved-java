import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_signupresend_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/signup/resend.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',10,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("admin")],-1)
printHtmlPart(3)
createTagBody(2, {->
createClosureForHtmlPart(4, 3)
invokeTag('captureTitle','sitemesh',11,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',11,[:],2)
printHtmlPart(5)
})
invokeTag('captureHead','sitemesh',11,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(0)
createTagBody(2, {->
printHtmlPart(6)
createTagBody(3, {->
printHtmlPart(7)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(8)
expressionOut.print(error.field)
printHtmlPart(9)
}
printHtmlPart(10)
invokeTag('message','g',19,['error':(error)],-1)
printHtmlPart(11)
})
invokeTag('eachError','g',19,['bean':(objInstance),'var':("error")],3)
printHtmlPart(12)
})
invokeTag('hasErrors','g',19,['bean':(objInstance)],2)
printHtmlPart(13)
createTagBody(2, {->
printHtmlPart(14)
expressionOut.print(beanResource?.email)
printHtmlPart(15)
createTagBody(3, {->
printHtmlPart(16)
invokeTag('recaptcha','recaptcha',38,['theme':("")],-1)
printHtmlPart(17)
})
invokeTag('ifEnabled','recaptcha',39,[:],3)
printHtmlPart(18)
createClosureForHtmlPart(19, 3)
invokeTag('link','g',45,['controller':("panel"),'action':("index"),'class':("btn btn-default")],3)
printHtmlPart(20)
})
invokeTag('form','g',45,['id':("formResend"),'name':("formResend"),'role':("form"),'style':("margin-top: 15px"),'url':([controller: 'signup', action: 'resendActivateMail']),'method':("POST")],2)
printHtmlPart(21)
})
invokeTag('captureBody','sitemesh',45,[:],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1485145037813L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
