import ru.deloved.Adcontent
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_adcontentshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/adcontent/show.gsp" }
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
invokeTag('message','g',6,['code':("adcontent.show.label")],-1)
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
invokeTag('message','g',12,['code':("adcontent.show.label")],-1)
printHtmlPart(5)
invokeTag('render','g',14,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
if(true && (adcontentInstance?.name)) {
printHtmlPart(7)
invokeTag('message','g',21,['code':("adcontent.name.label"),'default':("Name")],-1)
printHtmlPart(8)
invokeTag('fieldValue','g',24,['bean':(adcontentInstance),'field':("name")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (adcontentInstance?.approved)) {
printHtmlPart(11)
invokeTag('message','g',32,['code':("adcontent.approved.label"),'default':("Enabled")],-1)
printHtmlPart(12)
invokeTag('formatBoolean','g',35,['boolean':(adcontentInstance?.approved)],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (adcontentInstance?.dateCreated)) {
printHtmlPart(13)
invokeTag('message','g',43,['code':("adcontent.dateCreated.label"),'default':("Date Created")],-1)
printHtmlPart(14)
invokeTag('formatDate','g',46,['date':(adcontentInstance?.dateCreated)],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (adcontentInstance?.description)) {
printHtmlPart(15)
invokeTag('message','g',54,['code':("adcontent.description.label"),'default':("Description")],-1)
printHtmlPart(16)
invokeTag('fieldValue','g',58,['bean':(adcontentInstance),'field':("description")],-1)
printHtmlPart(9)
}
printHtmlPart(17)
createTagBody(2, {->
printHtmlPart(18)
createTagBody(3, {->
invokeTag('message','g',67,['code':("default.button.edit.label"),'default':("Edit")],-1)
})
invokeTag('link','g',67,['class':("btn btn-primary btn-lg"),'action':("edit"),'resource':(adcontentInstance)],3)
printHtmlPart(19)
invokeTag('actionSubmit','g',70,['class':("btn btn-warning btn-lg"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(20)
})
invokeTag('form','g',73,['url':([resource: adcontentInstance, action: 'delete']),'method':("DELETE")],2)
printHtmlPart(21)
})
invokeTag('captureBody','sitemesh',75,[:],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1435115992880L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
