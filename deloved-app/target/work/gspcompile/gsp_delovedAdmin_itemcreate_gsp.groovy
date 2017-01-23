import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_itemcreate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/item/create.gsp" }
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
invokeTag('message','g',5,['code':("item.create.label")],-1)
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
invokeTag('message','g',11,['code':('item.create.' + itemInstance.categoryType.code + '.label'),'default':(itemInstance.categoryType.code)],-1)
printHtmlPart(5)
if(true && (count==0)) {
printHtmlPart(3)
}
printHtmlPart(6)
invokeTag('render','g',29,['template':("/_common/create"),'model':([
					'objInstance'  : itemInstance,
					'formMappings' : params.categoryType?.code,
					'cancelMapping': params.categoryType?.code
			])],-1)
printHtmlPart(7)
invokeTag('render','g',37,['template':("/_common/gallery-multi")],-1)
printHtmlPart(1)
invokeTag('render','g',38,['template':("/_common/crop")],-1)
printHtmlPart(8)
})
invokeTag('captureBody','sitemesh',40,[:],1)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1454662014701L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}