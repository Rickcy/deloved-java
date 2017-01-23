import ru.deloved.Content
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_contentshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/content/show.gsp" }
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
invokeTag('message','g',6,['code':("content.show.label")],-1)
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
invokeTag('message','g',13,['code':("content.show.label")],-1)
printHtmlPart(5)
invokeTag('render','g',15,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
if(true && (contentInstance?.name)) {
printHtmlPart(7)
invokeTag('message','g',21,['code':("content.name.label"),'default':("Name")],-1)
printHtmlPart(8)
invokeTag('fieldValue','g',23,['bean':(contentInstance),'field':("name")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (contentInstance?.code)) {
printHtmlPart(11)
invokeTag('message','g',30,['code':("content.code.label"),'default':("Code")],-1)
printHtmlPart(12)
invokeTag('fieldValue','g',32,['bean':(contentInstance),'field':("code")],-1)
printHtmlPart(9)
}
printHtmlPart(13)
if(true && (contentInstance?.enabled)) {
printHtmlPart(11)
invokeTag('message','g',38,['code':("content.enabled.label"),'default':("Enabled")],-1)
printHtmlPart(14)
invokeTag('fieldValue','g',40,['bean':(contentInstance),'field':("enabled")],-1)
printHtmlPart(9)
}
printHtmlPart(13)
if(true && (contentInstance?.content)) {
printHtmlPart(11)
invokeTag('message','g',46,['code':("content.content.label"),'default':("Content")],-1)
printHtmlPart(15)
invokeTag('fieldValue','g',48,['bean':(contentInstance),'field':("content")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
createTagBody(2, {->
printHtmlPart(18)
createTagBody(3, {->
invokeTag('message','g',56,['code':("default.button.edit.label"),'default':("Edit")],-1)
})
invokeTag('link','g',56,['class':("edit"),'action':("edit"),'resource':(contentInstance)],3)
printHtmlPart(19)
invokeTag('actionSubmit','g',58,['class':("delete"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(20)
})
invokeTag('form','g',60,['url':([resource: contentInstance, action: 'delete']),'method':("DELETE")],2)
printHtmlPart(21)
})
invokeTag('captureBody','sitemesh',62,[:],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1435115993025L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
