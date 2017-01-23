import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_companyreviews_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/company/reviews.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',3,['code':("review.list.label")],-1)
printHtmlPart(1)
if(true && (freeAccount)) {
printHtmlPart(2)
createTagBody(2, {->
printHtmlPart(3)
invokeTag('render','g',10,['template':("/_common/promo-modal-unauth")],-1)
printHtmlPart(2)
})
invokeTag('ifNotLoggedIn','sec',11,[:],2)
printHtmlPart(2)
createTagBody(2, {->
printHtmlPart(3)
invokeTag('render','g',13,['template':("/_common/promo-modal-unauth")],-1)
printHtmlPart(2)
})
invokeTag('ifAllGranted','sec',14,['roles':("ROLE_ACCOUNT")],2)
printHtmlPart(4)
}
else {
printHtmlPart(5)
if(true && (reviewsCount > 0)) {
printHtmlPart(6)
for( review in (reviews) ) {
printHtmlPart(7)
invokeTag('formatDate','g',23,['date':(review.dateCreated),'format':("dd.MM.yyyy HH:mm:ss")],-1)
printHtmlPart(8)
if(true && (review.value > 0)) {
printHtmlPart(9)
}
else if(true && (review.value < 0)) {
printHtmlPart(10)
}
else {
printHtmlPart(11)
}
printHtmlPart(12)
createTagBody(4, {->
printHtmlPart(13)
invokeTag('fieldValue','g',36,['bean':(review.from),'field':("name")],-1)
printHtmlPart(14)
})
invokeTag('link','g',37,['url':([controller: 'company', id: review.from.id])],4)
printHtmlPart(15)
expressionOut.print(review.content)
printHtmlPart(16)
}
printHtmlPart(17)
}
else {
printHtmlPart(18)
}
printHtmlPart(4)
}
printHtmlPart(19)
expressionOut.print(message(code: 'default.button.close.label'))
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1485145037382L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
