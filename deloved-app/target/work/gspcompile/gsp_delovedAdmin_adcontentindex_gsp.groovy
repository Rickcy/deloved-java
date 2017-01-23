import ru.deloved.User
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_adcontentindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/adcontent/index.gsp" }
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
invokeTag('message','g',6,['code':("adcontent.list.label")],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',22,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(3)
if(true && (freeAccount)) {
printHtmlPart(1)
invokeTag('render','g',27,['template':("/_common/promo")],-1)
printHtmlPart(4)
}
else {
printHtmlPart(5)
invokeTag('message','g',31,['code':("adcontent.list.label")],-1)
printHtmlPart(6)
invokeTag('render','g',33,['template':("/_common/flash-message")],-1)
printHtmlPart(7)
expressionOut.print(message(code: 'default.button.delete.confirm.message'))
printHtmlPart(8)
expressionOut.print(message(code: 'user.button.enable.confirm.message'))
printHtmlPart(9)
expressionOut.print(message(code: 'user.button.disable.confirm.message'))
printHtmlPart(10)
invokeTag('set','g',68,['var':("showFiles"),'value':(true)],-1)
printHtmlPart(11)
if(true && (showAccount)) {
printHtmlPart(12)
if(true && (myAccounts?.size() > 0)) {
printHtmlPart(13)
invokeTag('select','g',71,['id':("account"),'name':("account"),'class':("form-control"),'from':(myAccounts),'optionKey':("id"),'optionValue':("name"),'required':("")],-1)
printHtmlPart(12)
}
else {
printHtmlPart(13)
invokeTag('set','g',74,['var':("showFiles"),'value':(false)],-1)
printHtmlPart(13)
invokeTag('render','g',84,['template':("/_common/auto-complete"),'model':([
									acAction   : createLink(controller: 'adcontent', action: 'accounts'),
									acMinLength: 4,
									acKeyValue : '',
									acKeyName  : 'naccount',
									acViewValue: '',
									acViewName : 'naccountname',
									acOnSelect : 'setupForm(1);',
									acOnChange : 'setupForm(0);'
							])],-1)
printHtmlPart(14)
}
printHtmlPart(11)
}
printHtmlPart(15)
createTagBody(3, {->
printHtmlPart(16)
invokeTag('render','g',137,['template':("status"),'model':([status: false])],-1)
printHtmlPart(17)
})
invokeTag('form','g',147,['id':("fileupload"),'enctype':("multipart/form-data"),'method':("POST"),'url':([action: 'upload'])],3)
printHtmlPart(18)
createTagBody(3, {->
printHtmlPart(11)
createTagBody(4, {->
invokeTag('message','g',154,['code':("adcontent.button.new.label")],-1)
})
invokeTag('link','g',154,['class':("btn btn-success"),'action':("create")],4)
printHtmlPart(11)
})
invokeTag('ifAnyGranted','sec',155,['roles':("ROLE_ACCOUNT")],3)
printHtmlPart(19)
invokeTag('message','g',158,['code':("default.button.actionSelect.label")],-1)
printHtmlPart(20)
invokeTag('message','g',161,['code':("adcontent.button.delete.label")],-1)
printHtmlPart(21)
createTagBody(3, {->
printHtmlPart(22)
invokeTag('message','g',164,['code':("adcontent.button.enable.label")],-1)
printHtmlPart(23)
invokeTag('message','g',165,['code':("adcontent.button.disable.label")],-1)
printHtmlPart(24)
})
invokeTag('ifAnyGranted','sec',166,['roles':("ROLE_ADMIN,ROLE_MANAGER")],3)
printHtmlPart(25)
createTagBody(3, {->
printHtmlPart(26)
invokeTag('select','g',184,['class':("form-control"),'name':("type"),'from':(ru.deloved.AdcontentType.values()),'optionKey':("value"),'valueMessagePrefix':("adcontent.type"),'value':(filter.type),'noSelection':(['': message(code: 'adcontent.alltypes.label')])],-1)
printHtmlPart(27)
invokeTag('select','g',193,['class':("form-control"),'name':("approved"),'from':([true, false]),'valueMessagePrefix':("adcontent.approved"),'value':(filter.approved),'noSelection':(['': message(code: 'adcontent.approved.all')])],-1)
printHtmlPart(28)
invokeTag('textField','g',197,['placeholder':("Search"),'class':("form-control"),'name':("search"),'value':(filter.search)],-1)
printHtmlPart(29)
invokeTag('submitButton','g',201,['name':("filterAction"),'class':("btn btn-primary"),'value':(message(code: 'default.button.filter.label'))],-1)
printHtmlPart(11)
invokeTag('submitButton','g',203,['name':("resetAction"),'class':("btn btn-default"),'formaction':("reset"),'value':(message(code: 'default.button.filterReset.label'))],-1)
printHtmlPart(30)
})
invokeTag('form','g',207,['url':([controller: 'adcontent', action: 'index'])],3)
printHtmlPart(31)
createTagBody(3, {->
printHtmlPart(32)
invokeTag('hiddenField','g',212,['name':("batch_action"),'value':("")],-1)
printHtmlPart(33)
invokeTag('sortableColumn','g',219,['property':("name"),'title':(message(code: 'adcontent.name.label', default: 'Name'))],-1)
printHtmlPart(34)
invokeTag('sortableColumn','g',223,['property':("approved"),'title':(message(code: 'adcontent.approved.label', default: 'Approved'))],-1)
printHtmlPart(35)
invokeTag('message','g',225,['code':("adcontent.account.label"),'default':("Account")],-1)
printHtmlPart(36)
invokeTag('sortableColumn','g',229,['property':("dateCreated"),'title':(message(code: 'adcontent.dateCreated.label', default: 'Date Created'))],-1)
printHtmlPart(37)
loop:{
int i = 0
for( obj in (adcontentInstanceList) ) {
printHtmlPart(11)
createTagBody(5, {->
printHtmlPart(12)
if(true && (myAccounts.name==[obj.account.name])) {
printHtmlPart(13)
if(true && (obj.file.mimeType!='application/octet-stream'&&'text/plain')) {
printHtmlPart(38)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(39)
expressionOut.print(obj.id)
printHtmlPart(40)
createTagBody(8, {->
expressionOut.print(fieldValue(bean: obj, field: "name"))
})
invokeTag('link','g',250,['action':("edit"),'controller':("adcontent"),'id':(obj.id),'elementId':("gridRow${obj.id}name"),'data-toggle':("modal"),'data-remote':(createLink(id: obj.id, action: 'edit')),'data-target':("#myModal")],8)
printHtmlPart(41)
if(true && (obj.file)) {
printHtmlPart(42)
expressionOut.print(obj.id)
printHtmlPart(43)
expressionOut.print(createLink([action: 'download', id: obj.file.id, params: [name: obj.file.name]]))
printHtmlPart(44)
expressionOut.print(obj.file.name)
printHtmlPart(45)
expressionOut.print(obj.id)
printHtmlPart(46)
expressionOut.print(createLink([action: 'download', id: obj.file.id, params: [name: obj.file.name, preview: true]]))
printHtmlPart(47)
}
else {
printHtmlPart(48)
expressionOut.print(obj.url)
printHtmlPart(49)
}
printHtmlPart(50)
expressionOut.print(obj.id)
printHtmlPart(51)
invokeTag('render','g',274,['template':("status"),'model':([status: obj.approved])],-1)
printHtmlPart(52)
expressionOut.print(obj.account.name)
printHtmlPart(53)
invokeTag('formatDate','g',280,['date':(obj.dateCreated),'format':("dd.MM.yyyy HH:mm:ss")],-1)
printHtmlPart(54)
}
printHtmlPart(12)
}
printHtmlPart(11)
})
invokeTag('ifAnyGranted','sec',286,['roles':("ROLE_ACCOUNT")],5)
printHtmlPart(11)
createTagBody(5, {->
printHtmlPart(55)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(56)
expressionOut.print(obj.id)
printHtmlPart(57)
createTagBody(6, {->
expressionOut.print(fieldValue(bean: obj, field: "name"))
})
invokeTag('link','g',302,['action':("edit"),'controller':("adcontent"),'id':(obj.id),'elementId':("gridRow${obj.id}name"),'data-toggle':("modal"),'data-remote':(createLink(id: obj.id, action: 'edit')),'data-target':("#myModal")],6)
printHtmlPart(58)
if(true && (obj.file)) {
printHtmlPart(59)
expressionOut.print(obj.id)
printHtmlPart(43)
expressionOut.print(createLink([action: 'download', id: obj.file.id, params: [name: obj.file.name]]))
printHtmlPart(60)
expressionOut.print(obj.file.name)
printHtmlPart(61)
expressionOut.print(obj.id)
printHtmlPart(46)
expressionOut.print(createLink([action: 'download', id: obj.file.id, params: [name: obj.file.name, preview: true]]))
printHtmlPart(62)
}
else {
printHtmlPart(63)
expressionOut.print(obj.url)
printHtmlPart(64)
}
printHtmlPart(65)
expressionOut.print(message(code: 'adcontent.type.' + obj.type, default: obj.type))
printHtmlPart(66)
expressionOut.print(obj.id)
printHtmlPart(67)
invokeTag('render','g',324,['template':("status"),'model':([status: obj.approved])],-1)
printHtmlPart(68)
expressionOut.print(obj.account.name)
printHtmlPart(69)
invokeTag('formatDate','g',330,['date':(obj.dateCreated),'format':("dd.MM.yyyy HH:mm:ss")],-1)
printHtmlPart(70)
})
invokeTag('ifAnyGranted','sec',335,['roles':("ROLE_ADMIN,ROLE_MANAGER")],5)
printHtmlPart(71)
i++
}
}
printHtmlPart(72)
if(true && (params.max < rowsCount)) {
printHtmlPart(71)
invokeTag('paginate','g',340,['total':(rowsCount ?: 0)],-1)
printHtmlPart(32)
}
printHtmlPart(73)
})
invokeTag('form','g',344,['id':("batch_form"),'url':([controller: 'adcontent', action: 'batch']),'method':("POST")],3)
printHtmlPart(74)
}
printHtmlPart(75)
invokeTag('render','g',352,['template':("/_common/edit-container")],-1)
printHtmlPart(4)
invokeTag('render','g',353,['template':("/_common/gallery-multi")],-1)
printHtmlPart(76)
if(true && (showAccount)) {
printHtmlPart(77)
}
printHtmlPart(78)
if(true && (!showFiles)) {
printHtmlPart(79)
}
printHtmlPart(80)
})
invokeTag('captureBody','sitemesh',397,[:],1)
printHtmlPart(81)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1484900355377L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
