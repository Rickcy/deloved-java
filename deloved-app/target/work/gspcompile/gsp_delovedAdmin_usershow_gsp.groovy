import ru.deloved.User
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_usershow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/user/show.gsp" }
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
invokeTag('message','g',6,['code':("user.show.label")],-1)
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
invokeTag('message','g',12,['code':("user.show.label")],-1)
printHtmlPart(5)
invokeTag('render','g',14,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
if(true && (userInstance?.username)) {
printHtmlPart(7)
invokeTag('message','g',20,['code':("user.username.label"),'default':("Username")],-1)
printHtmlPart(8)
invokeTag('fieldValue','g',22,['bean':(userInstance),'field':("username")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (userInstance?.enabled)) {
printHtmlPart(11)
invokeTag('message','g',29,['code':("user.enabled.label"),'default':("Enabled")],-1)
printHtmlPart(12)
invokeTag('formatBoolean','g',31,['boolean':(userInstance?.enabled)],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (userInstance?.dateCreated)) {
printHtmlPart(13)
invokeTag('message','g',38,['code':("user.dateCreated.label"),'default':("Date Created")],-1)
printHtmlPart(14)
invokeTag('formatDate','g',40,['date':(userInstance?.dateCreated)],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (userInstance?.profile)) {
printHtmlPart(15)
invokeTag('message','g',47,['code':("user.profile.label"),'default':("Profile")],-1)
printHtmlPart(16)
createTagBody(3, {->
expressionOut.print(userInstance?.profile?.encodeAsHTML())
})
invokeTag('link','g',49,['controller':("profile"),'action':("show"),'id':(userInstance?.profile?.id)],3)
printHtmlPart(9)
}
printHtmlPart(17)
createTagBody(2, {->
printHtmlPart(18)
createTagBody(3, {->
invokeTag('message','g',57,['code':("default.button.edit.label"),'default':("Edit")],-1)
})
invokeTag('link','g',57,['class':("btn btn-primary btn-lg"),'action':("edit"),'resource':(userInstance)],3)
printHtmlPart(19)
invokeTag('actionSubmit','g',59,['class':("btn btn-warning btn-lg"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(20)
})
invokeTag('form','g',62,['url':([resource: userInstance, action: 'delete']),'method':("DELETE")],2)
printHtmlPart(21)
})
invokeTag('captureBody','sitemesh',64,[:],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1435115993458L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
