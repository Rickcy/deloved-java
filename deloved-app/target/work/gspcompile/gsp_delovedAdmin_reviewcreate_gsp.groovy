import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_reviewcreate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/review/create.gsp" }
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
invokeTag('message','g',5,['code':("review.create.label")],-1)
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
invokeTag('message','g',12,['code':("review.create.label")],-1)
printHtmlPart(5)
if(true && (freeAccount)) {
printHtmlPart(6)
invokeTag('render','g',15,['template':("/_common/promo")],-1)
printHtmlPart(1)
}
else {
printHtmlPart(7)
createTagBody(3, {->
printHtmlPart(8)
expressionOut.print(reviewInstance.from?.id)
printHtmlPart(9)
expressionOut.print(reviewInstance.to?.id)
printHtmlPart(10)
expressionOut.print(reviewInstance.deal?.id)
printHtmlPart(11)
expressionOut.print(reviewInstance.author?.id)
printHtmlPart(12)
invokeTag('render','g',29,['template':("form"),'model':([
							objInstance: reviewInstance
					])],-1)
printHtmlPart(13)
})
invokeTag('form','g',38,['id':("createReview"),'url':([resource:reviewInstance,action: 'save']),'method':("POST"),'class':("form-horizontal")],3)
printHtmlPart(14)
invokeTag('staticContent','g',41,['code':("createReview")],-1)
printHtmlPart(15)
}
printHtmlPart(16)
})
invokeTag('captureBody','sitemesh',47,[:],1)
printHtmlPart(17)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1457677743303L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
