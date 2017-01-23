import ru.deloved.Review
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_reviewshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/review/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("admin")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',6,['code':("review.show.label")],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',12,['code':("review.show.label")],-1)
printHtmlPart(5)
invokeTag('render','g',14,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
if(true && (freeAccount)) {
printHtmlPart(7)
invokeTag('render','g',18,['template':("/_common/promo")],-1)
printHtmlPart(1)
}
else {
printHtmlPart(8)
expressionOut.print(createLink(controller: 'company', action: 'index', id: reviewInstance.to.id))
printHtmlPart(9)
expressionOut.print(reviewInstance.to.orgForm.code + '\"'+ reviewInstance.to.name + '\"')
printHtmlPart(10)
if(true && (reviewInstance.deal)) {
printHtmlPart(11)
expressionOut.print(createLink(controller: 'deal', action: 'thread', id: reviewInstance.deal.id))
printHtmlPart(12)
expressionOut.print(reviewInstance.deal.id)
printHtmlPart(13)
if(true && (reviewInstance.value == 1)) {
printHtmlPart(14)
}
printHtmlPart(15)
if(true && (reviewInstance.value == 0)) {
printHtmlPart(16)
}
printHtmlPart(15)
if(true && (reviewInstance.value == -1)) {
printHtmlPart(17)
}
printHtmlPart(18)
}
printHtmlPart(19)
expressionOut.print(reviewInstance.content)
printHtmlPart(20)
expressionOut.print(createLink(controller: 'company', action: 'index', id: reviewInstance.from.id))
printHtmlPart(9)
expressionOut.print(reviewInstance.from.orgForm.code + '\"'+ reviewInstance.from.name + '\"')
printHtmlPart(21)
createTagBody(3, {->
printHtmlPart(22)
invokeTag('message','g',112,['code':("review.backallreviews")],-1)
})
invokeTag('link','g',112,['class':("btn btn-default"),'action':("index")],3)
printHtmlPart(23)
}
printHtmlPart(24)
invokeTag('render','g',127,['template':("/_common/gallery-multi")],-1)
printHtmlPart(3)
})
invokeTag('captureBody','sitemesh',129,[:],1)
printHtmlPart(25)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1457692197547L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
