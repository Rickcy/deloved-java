import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_user_new_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/user/_new.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: objInstance, field: 'username', 'error'))
printHtmlPart(1)
invokeTag('message','g',3,['code':("user.username.label"),'default':("Username")],-1)
printHtmlPart(2)
invokeTag('textField','g',7,['class':("form-control"),'name':("username"),'required':(""),'value':(objInstance?.username)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: objInstance, field: 'password', 'error'))
printHtmlPart(4)
invokeTag('message','g',13,['code':("user.password.label"),'default':("Password")],-1)
printHtmlPart(2)
invokeTag('passwordField','g',17,['class':("form-control"),'name':("password"),'required':(""),'value':(objInstance?.password)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: objInstance, field: 'passwordRepeat', 'error'))
printHtmlPart(5)
invokeTag('message','g',23,['code':("user.passwordRepeat.label"),'default':("Repeat Password")],-1)
printHtmlPart(2)
invokeTag('passwordField','g',27,['class':("form-control"),'name':("passwordRepeat"),'required':(""),'value':(objInstance?.passwordRepeat)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: objInstance, field: 'role', 'error'))
printHtmlPart(6)
invokeTag('message','g',33,['code':("user.role.label"),'default':("Role")],-1)
printHtmlPart(7)
invokeTag('select','g',45,['class':("form-control"),'name':("role"),'from':(ru.deloved.Role.list()),'optionKey':("id"),'optionValue':({ message(code: 'role.' + it.authority, default: it.authority) }),'value':(objInstance?.role ?: ru.deloved.Role.findByAuthority('ROLE_NONE').id)],-1)
printHtmlPart(8)
expressionOut.print(hasErrors(bean: objInstance, field: 'roleRequest', 'error'))
printHtmlPart(9)
invokeTag('message','g',52,['code':("user.roleRequest.label"),'default':("Role after activate")],-1)
printHtmlPart(10)
invokeTag('select','g',63,['class':("form-control"),'name':("roleRequest"),'from':(ru.deloved.Role.list()),'optionKey':("id"),'optionValue':({ message(code: 'role.' + it.authority, default: it.authority) }),'noSelection':(['': 'Не задано'])],-1)
printHtmlPart(11)
expressionOut.print(hasErrors(bean: objInstance, field: 'city', 'error'))
printHtmlPart(12)
invokeTag('message','g',70,['code':("profile.city.label"),'default':("City")],-1)
printHtmlPart(13)
invokeTag('hiddenField','g',75,['name':("city.id"),'value':(objInstance?.city?.id)],-1)
printHtmlPart(14)
invokeTag('hiddenField','g',76,['name':("cityname2"),'value':(objInstance?.city?.name)],-1)
printHtmlPart(14)
invokeTag('textField','g',77,['class':("form-control"),'name':("cityname"),'value':(objInstance?.city?.name)],-1)
printHtmlPart(15)
expressionOut.print(createLink(controller: 'profile', action: 'cities'))
printHtmlPart(16)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1470042792865L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
