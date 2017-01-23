import ru.deloved.Measure
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_measureshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/measure/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("admin")],-1)
printHtmlPart(1)
invokeTag('set','g',6,['var':("entityName"),'value':(message(code: 'measure.label', default: 'Measure'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',7,['code':("default.show.label"),'args':([entityName])],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',14,['code':("default.show.label"),'args':([entityName])],-1)
printHtmlPart(5)
invokeTag('render','g',16,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
if(true && (measureInstance?.name)) {
printHtmlPart(7)
invokeTag('message','g',22,['code':("measure.name.label"),'default':("Name")],-1)
printHtmlPart(8)
invokeTag('fieldValue','g',24,['bean':(measureInstance),'field':("name")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (measureInstance?.type)) {
printHtmlPart(11)
invokeTag('message','g',31,['code':("measure.type.label"),'default':("Type")],-1)
printHtmlPart(12)
createTagBody(3, {->
expressionOut.print(measureInstance?.type?.encodeAsHTML())
})
invokeTag('link','g',33,['controller':("categoryType"),'action':("show"),'id':(measureInstance?.type?.id)],3)
printHtmlPart(9)
}
printHtmlPart(13)
createTagBody(2, {->
printHtmlPart(14)
createTagBody(3, {->
invokeTag('message','g',41,['code':("default.button.edit.label"),'default':("Edit")],-1)
})
invokeTag('link','g',41,['class':("edit"),'action':("edit"),'resource':(measureInstance)],3)
printHtmlPart(15)
invokeTag('actionSubmit','g',43,['class':("delete"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(16)
})
invokeTag('form','g',45,['url':([resource: measureInstance, action: 'delete']),'method':("DELETE")],2)
printHtmlPart(17)
})
invokeTag('captureBody','sitemesh',47,[:],1)
printHtmlPart(18)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1435115993123L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
