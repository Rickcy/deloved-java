import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_reviewedit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/review/edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("admin")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',5,['code':("review.edit.label")],-1)
})
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',6,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',11,['code':("review.edit.label")],-1)
printHtmlPart(5)
if(true && (freeAccount)) {
printHtmlPart(6)
invokeTag('render','g',13,['template':("/_common/promo")],-1)
printHtmlPart(7)
}
else {
printHtmlPart(8)
createTagBody(3, {->
printHtmlPart(9)
invokeTag('render','g',21,['template':("form"),'model':([
						        objInstance: reviewInstance
						])],-1)
printHtmlPart(10)
expressionOut.print(createLink(action: 'delete', id: reviewInstance.id))
printHtmlPart(11)
})
invokeTag('form','g',39,['id':("editReview"),'url':([resource: reviewInstance, action: 'update']),'method':("POST"),'class':("form-horizontal")],3)
printHtmlPart(12)
createTagBody(3, {->
printHtmlPart(9)
invokeTag('staticContent','g',44,['code':("editReviewByUser")],-1)
printHtmlPart(13)
})
invokeTag('ifAnyGranted','sec',45,['roles':("ROLE_ACCOUNT")],3)
printHtmlPart(13)
createTagBody(3, {->
printHtmlPart(9)
invokeTag('staticContent','g',47,['code':("editReviewByAdmin")],-1)
printHtmlPart(13)
})
invokeTag('ifAnyGranted','sec',48,['roles':("ROLE_ADMIN")],3)
printHtmlPart(13)
createTagBody(3, {->
printHtmlPart(9)
invokeTag('staticContent','g',50,['code':("editReviewByManager")],-1)
printHtmlPart(13)
})
invokeTag('ifAnyGranted','sec',51,['roles':("ROLE_MANAGER")],3)
printHtmlPart(14)
}
printHtmlPart(15)
})
invokeTag('captureBody','sitemesh',57,[:],1)
printHtmlPart(16)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1448268475419L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
