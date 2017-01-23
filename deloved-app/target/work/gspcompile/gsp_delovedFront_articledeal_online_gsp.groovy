import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_articledeal_online_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/article/deal_online.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("front")],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('titleContent','g',6,['code':("TDKContentDealOnline")],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
invokeTag('descKeyContent','g',7,['code':("TDKContentDealOnline")],-1)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',48,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
expressionOut.print(resource(dir: 'images', file: 'front/icon_deal.png'))
printHtmlPart(6)
expressionOut.print(resource(dir: 'images', file: 'front/deal_online.jpg'))
printHtmlPart(7)
invokeTag('contContentColumn1','g',64,['code':("TDKContentDealOnline")],-1)
printHtmlPart(8)
expressionOut.print(resource(dir: 'images', file: 'admin/deal_online1.jpg'))
printHtmlPart(9)
invokeTag('contContentColumn2','g',77,['code':("TDKContentDealOnline")],-1)
printHtmlPart(10)
expressionOut.print(resource(dir: 'images', file: 'admin/deal_online2.jpg'))
printHtmlPart(11)
})
invokeTag('captureBody','sitemesh',90,[:],1)
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1484015489339L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
