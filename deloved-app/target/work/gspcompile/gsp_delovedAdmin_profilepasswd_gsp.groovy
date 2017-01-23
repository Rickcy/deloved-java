import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_profilepasswd_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/profile/passwd.gsp" }
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
invokeTag('message','g',5,['code':("Смена Пароля")],-1)
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
invokeTag('message','g',12,['code':("Смена Пароля")],-1)
printHtmlPart(5)
invokeTag('render','g',14,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(7)
createTagBody(3, {->
printHtmlPart(8)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(9)
expressionOut.print(error.field)
printHtmlPart(10)
}
printHtmlPart(11)
invokeTag('message','g',19,['error':(error)],-1)
printHtmlPart(12)
})
invokeTag('eachError','g',20,['bean':(objInstance),'var':("error")],3)
printHtmlPart(13)
})
invokeTag('hasErrors','g',22,['bean':(objInstance)],2)
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(14)
expressionOut.print(hasErrors(bean: objInstance, field: 'oldPassword', 'error'))
printHtmlPart(15)
invokeTag('message','g',29,['code':("profile.oldPassword.label"),'default':("Старый Пароль")],-1)
printHtmlPart(16)
invokeTag('passwordField','g',34,['class':("form-control"),'name':("oldPassword"),'required':("")],-1)
printHtmlPart(17)
expressionOut.print(hasErrors(bean: objInstance, field: 'newPassword', 'error'))
printHtmlPart(18)
invokeTag('message','g',42,['code':("user.password.label"),'default':("Password")],-1)
printHtmlPart(16)
invokeTag('passwordField','g',47,['class':("form-control"),'name':("newPassword"),'required':("")],-1)
printHtmlPart(19)
expressionOut.print(hasErrors(bean: objInstance, field: 'newPasswordRepeat', 'error'))
printHtmlPart(20)
invokeTag('message','g',53,['code':("user.passwordRepeat.label"),'default':("Repeat Password")],-1)
printHtmlPart(16)
invokeTag('passwordField','g',58,['class':("form-control"),'name':("newPasswordRepeat"),'required':("")],-1)
printHtmlPart(21)
invokeTag('actionSubmit','g',64,['class':("btn  btn-success"),'action':("changepwd"),'value':(message(code: 'default.button.update.label'))],-1)
printHtmlPart(22)
createTagBody(3, {->
invokeTag('message','g',65,['code':("default.cancel")],-1)
})
invokeTag('link','g',65,['class':("btn btn-default"),'action':(params.id ? 'index' : 'me')],3)
printHtmlPart(23)
})
invokeTag('form','g',67,['name':("editForm"),'url':([controller: 'profile', action: 'changepwd']),'method':("PUT"),'class':("form-horizontal")],2)
printHtmlPart(24)
})
invokeTag('captureBody','sitemesh',71,[:],1)
printHtmlPart(25)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1452751422491L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
