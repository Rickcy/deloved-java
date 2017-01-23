import ru.deloved.Account
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_accountindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/account/index.gsp" }
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
invokeTag('message','g',6,['code':("account.list.label")],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',24,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',30,['code':("account.list.label")],-1)
printHtmlPart(5)
invokeTag('render','g',31,['template':("/_common/hint"),'model':([hintText: 'Для удаления предприятия в Краткое описание введите Данные удалены и все переключатели выключить!'])],-1)
printHtmlPart(6)
invokeTag('render','g',32,['template':("/_common/flash-message")],-1)
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(8)
createTagBody(3, {->
invokeTag('message','g',36,['code':("account.button.new.label")],-1)
})
invokeTag('link','g',36,['class':("btn btn-success"),'action':("create")],3)
printHtmlPart(9)
expressionOut.print(message(code: 'default.button.delete.confirm.message'))
printHtmlPart(10)
expressionOut.print(message(code: 'user.button.enable.confirm.message'))
printHtmlPart(11)
expressionOut.print(message(code: 'user.button.disable.confirm.message'))
printHtmlPart(12)
expressionOut.print(message(code: 'user.button.setVerified.confirm.message'))
printHtmlPart(13)
expressionOut.print(message(code: 'user.button.setUnverified.confirm.message'))
printHtmlPart(14)
invokeTag('message','g',80,['code':("default.button.actionSelect.label")],-1)
printHtmlPart(15)
createTagBody(3, {->
printHtmlPart(16)
invokeTag('message','g',85,['code':("user.button.delete.label")],-1)
printHtmlPart(17)
})
invokeTag('ifAllGranted','sec',86,['roles':("ROLE_ADMIN")],3)
printHtmlPart(18)
invokeTag('message','g',88,['code':("user.button.enable.label")],-1)
printHtmlPart(19)
invokeTag('message','g',90,['code':("user.button.disable.label")],-1)
printHtmlPart(20)
invokeTag('message','g',92,['code':("user.button.setVerified.label")],-1)
printHtmlPart(21)
invokeTag('message','g',94,['code':("user.button.setUnverified.label")],-1)
printHtmlPart(22)
createTagBody(3, {->
printHtmlPart(23)
invokeTag('textField','g',102,['placeholder':("Name"),'class':("form-control"),'name':("name"),'value':(filter.name)],-1)
printHtmlPart(24)
invokeTag('submitButton','g',105,['name':("filterAction"),'class':("btn btn-primary"),'value':(message(code: 'default.button.filter.label'))],-1)
printHtmlPart(25)
invokeTag('submitButton','g',106,['name':("resetAction"),'class':("btn btn-default"),'formaction':("reset"),'value':(message(code: 'default.button.filterReset.label'))],-1)
printHtmlPart(26)
})
invokeTag('form','g',109,['url':([controller: 'account', action: 'index'])],3)
printHtmlPart(1)
})
invokeTag('ifAnyGranted','sec',110,['roles':("ROLE_ADMIN,ROLE_MANAGER")],2)
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(27)
invokeTag('hiddenField','g',113,['name':("batch_action"),'value':("")],-1)
printHtmlPart(28)
createClosureForHtmlPart(29, 3)
invokeTag('ifAnyGranted','sec',120,['roles':("ROLE_ADMIN,ROLE_MANAGER")],3)
printHtmlPart(25)
invokeTag('sortableColumn','g',121,['property':("name"),'title':(message(code: 'account.name.label'))],-1)
printHtmlPart(30)
createTagBody(3, {->
printHtmlPart(31)
invokeTag('sortableColumn','g',124,['property':("publicStatus"),'title':(message(code: 'account.publicStatus.label'))],-1)
printHtmlPart(31)
invokeTag('sortableColumn','g',125,['property':("verifyStatus"),'title':(message(code: 'account.verifyStatus.label'))],-1)
printHtmlPart(25)
})
invokeTag('ifAnyGranted','sec',126,['roles':("ROLE_ADMIN,ROLE_MANAGER")],3)
printHtmlPart(25)
invokeTag('sortableColumn','g',127,['property':("city.name"),'title':(message(code: 'account.city.label'))],-1)
printHtmlPart(25)
invokeTag('sortableColumn','g',128,['property':("dateCreated"),'title':(message(code: 'account.dateCreated.label'))],-1)
printHtmlPart(32)
loop:{
int i = 0
for( obj in (accountInstanceList) ) {
printHtmlPart(33)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(34)
createTagBody(4, {->
printHtmlPart(35)
expressionOut.print(obj.id)
printHtmlPart(36)
})
invokeTag('ifAnyGranted','sec',138,['roles':("ROLE_ADMIN,ROLE_MANAGER")],4)
printHtmlPart(37)
createTagBody(4, {->
expressionOut.print(fieldValue(bean: obj, field: "name"))
})
invokeTag('link','g',143,['action':("edit"),'id':(obj.id),'class':(userNewObjects.contains(obj)?"newItem":"")],4)
printHtmlPart(38)
if(true && (userNewObjects.contains(obj))) {
printHtmlPart(39)
}
printHtmlPart(40)
createTagBody(4, {->
printHtmlPart(41)
expressionOut.print(obj.id)
printHtmlPart(42)
invokeTag('render','g',153,['template':("status"),'model':([status: obj.publicStatus, statusClass: 'publicStatus', iconFalse: 'glyphicon-lock'])],-1)
printHtmlPart(43)
expressionOut.print(obj.id)
printHtmlPart(44)
invokeTag('render','g',156,['template':("status"),'model':([status: obj.verifyStatus, statusClass: 'verifyStatus'])],-1)
printHtmlPart(45)
})
invokeTag('ifAnyGranted','sec',158,['roles':("ROLE_ADMIN,ROLE_MANAGER")],4)
printHtmlPart(46)
expressionOut.print(obj.id)
printHtmlPart(47)
expressionOut.print(fieldValue(bean: obj, field: "city.name"))
printHtmlPart(48)
invokeTag('formatDate','g',162,['date':(obj.dateCreated),'format':("dd.MM.yyyy HH:mm:ss")],-1)
printHtmlPart(49)
i++
}
}
printHtmlPart(50)
if(true && (params.max < rowsCount)) {
printHtmlPart(51)
invokeTag('paginate','g',171,['total':(rowsCount ?: 0)],-1)
printHtmlPart(27)
}
printHtmlPart(1)
})
invokeTag('form','g',173,['id':("batch_form"),'url':([controller: 'account', action: 'batch']),'method':("POST")],2)
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(52)
expressionOut.print(createLink(controller: 'account', action: 'status'))
printHtmlPart(53)
expressionOut.print(createLink(controller: 'account', action: 'status'))
printHtmlPart(54)
})
invokeTag('ifAnyGranted','sec',219,['roles':("ROLE_ADMIN,ROLE_MANAGER")],2)
printHtmlPart(55)
invokeTag('render','g',222,['template':("/_common/edit-container")],-1)
printHtmlPart(56)
invokeTag('render','g',223,['template':("/_common/gallery-single")],-1)
printHtmlPart(56)
invokeTag('render','g',224,['template':("/_common/crop")],-1)
printHtmlPart(3)
})
invokeTag('captureBody','sitemesh',226,[:],1)
printHtmlPart(57)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1482387511778L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
