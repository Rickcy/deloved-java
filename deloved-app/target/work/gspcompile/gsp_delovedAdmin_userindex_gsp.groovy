import ru.deloved.User
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_userindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/user/index.gsp" }
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
invokeTag('message','g',6,['code':("user.list.label")],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',21,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',25,['code':("user.list.label")],-1)
printHtmlPart(5)
invokeTag('render','g',27,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
createTagBody(2, {->
invokeTag('message','g',30,['code':("user.button.new.label")],-1)
})
invokeTag('link','g',30,['class':("btn btn-success"),'action':("create")],2)
printHtmlPart(7)
expressionOut.print(message(code: 'default.button.delete.confirm.message'))
printHtmlPart(8)
expressionOut.print(message(code: 'user.button.enable.confirm.message'))
printHtmlPart(9)
expressionOut.print(message(code: 'user.button.disable.confirm.message'))
printHtmlPart(10)
invokeTag('message','g',58,['code':("default.button.actionSelect.label")],-1)
printHtmlPart(11)
createTagBody(2, {->
printHtmlPart(12)
invokeTag('message','g',61,['code':("user.button.delete.label")],-1)
printHtmlPart(13)
})
invokeTag('ifAllGranted','sec',62,['roles':("ROLE_ADMIN")],2)
printHtmlPart(14)
invokeTag('message','g',63,['code':("user.button.enable.label")],-1)
printHtmlPart(15)
invokeTag('message','g',64,['code':("user.button.disable.label")],-1)
printHtmlPart(16)
createTagBody(2, {->
printHtmlPart(17)
invokeTag('select','g',80,['class':("form-control"),'name':("role"),'from':(ru.deloved.Role.list()),'optionKey':("id"),'optionValue':({ message(code: 'role.' + it.authority, default: it.authority) }),'value':(filter.role),'noSelection':(['': message(code: 'role.allroles.label')])],-1)
printHtmlPart(18)
invokeTag('select','g',89,['class':("form-control"),'name':("enabled"),'from':([true, false]),'valueMessagePrefix':("user.activity"),'value':(filter.enabled),'noSelection':(['': message(code: 'user.activity.all')])],-1)
printHtmlPart(19)
invokeTag('textField','g',92,['placeholder':("Login"),'class':("form-control"),'name':("username"),'value':(filter.username)],-1)
printHtmlPart(20)
invokeTag('submitButton','g',95,['name':("filterAction"),'class':("btn btn-primary"),'value':(message(code: 'default.button.filter.label'))],-1)
printHtmlPart(21)
invokeTag('submitButton','g',96,['name':("resetAction"),'class':("btn btn-default"),'formaction':("reset"),'value':(message(code: 'default.button.filterReset.label'))],-1)
printHtmlPart(22)
})
invokeTag('form','g',101,['url':([controller: 'user', action: 'index'])],2)
printHtmlPart(23)
createTagBody(2, {->
printHtmlPart(24)
invokeTag('hiddenField','g',105,['name':("batch_action"),'value':("")],-1)
printHtmlPart(25)
invokeTag('sortableColumn','g',112,['property':("username"),'title':(message(code: 'user.username.label', default: 'Username'))],-1)
printHtmlPart(26)
invokeTag('sortableColumn','g',114,['property':("enabled"),'title':(message(code: 'user.enabled.label', default: 'Enabled'))],-1)
printHtmlPart(27)
if(true && (!filter.role)) {
printHtmlPart(28)
invokeTag('sortableColumn','g',116,['property':("role"),'title':(message(code: 'user.role.label', default: 'Role'))],-1)
printHtmlPart(27)
}
printHtmlPart(26)
invokeTag('sortableColumn','g',119,['property':("dateCreated"),'title':(message(code: 'user.dateCreated.label', default: 'Date Created'))],-1)
printHtmlPart(29)
loop:{
int i = 0
for( obj in (userInstanceList) ) {
printHtmlPart(30)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(31)
expressionOut.print(obj.id)
printHtmlPart(32)
createTagBody(4, {->
expressionOut.print(fieldValue(bean: obj, field: "username"))
})
invokeTag('link','g',135,['action':("edit"),'id':(obj.id),'elementId':("gridRow${obj.id}username"),'data-toggle':("modal"),'data-remote':(createLink(id: obj.id, action: 'edit')),'data-target':("#myModal")],4)
printHtmlPart(33)
expressionOut.print(obj.id)
printHtmlPart(34)
invokeTag('render','g',139,['template':("status"),'model':([status: obj.enabled])],-1)
printHtmlPart(35)
if(true && (!filter.role)) {
printHtmlPart(36)
expressionOut.print(message(code: 'role.' + obj.role.authority, default: obj.role.authority))
printHtmlPart(37)
}
printHtmlPart(38)
invokeTag('formatDate','g',146,['date':(obj.dateCreated),'format':("dd.MM.yyyy HH:mm:ss")],-1)
printHtmlPart(39)
i++
}
}
printHtmlPart(40)
if(true && (params.max < rowsCount)) {
printHtmlPart(41)
invokeTag('paginate','g',155,['total':(rowsCount ?: 0)],-1)
printHtmlPart(24)
}
printHtmlPart(42)
})
invokeTag('form','g',158,['id':("batch_form"),'url':([controller: 'user', action: 'batch']),'method':("POST")],2)
printHtmlPart(43)
expressionOut.print(createLink(controller: 'user', action: 'status'))
printHtmlPart(44)
invokeTag('render','g',190,['template':("/_common/edit-container")],-1)
printHtmlPart(3)
})
invokeTag('captureBody','sitemesh',192,[:],1)
printHtmlPart(45)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1461305624240L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
