import ru.deloved.Review
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_reviewindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/review/index.gsp" }
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
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('render','g',14,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(7)
createTagBody(3, {->
printHtmlPart(8)
if(true && (filter.type == null || filter.type == "from")) {
printHtmlPart(9)
}
printHtmlPart(10)
expressionOut.print(createLink(action: 'index', params: [type: 'from']))
printHtmlPart(11)
if(true && (filter.type == "to")) {
printHtmlPart(9)
}
printHtmlPart(10)
expressionOut.print(createLink(action: 'index', params: [type: 'to']))
printHtmlPart(12)
})
invokeTag('ifAllGranted','sec',26,['roles':("ROLE_ACCOUNT")],3)
printHtmlPart(13)
invokeTag('textField','g',32,['placeholder':("Поиск"),'class':("form-control"),'name':("search"),'value':(filter.search)],-1)
printHtmlPart(14)
if(true && (!toMe)) {
printHtmlPart(15)
invokeTag('select','g',40,['class':("form-control"),'name':("status"),'from':(statusFilterValues),'valueMessagePrefix':("review.filter.status"),'value':(filter.status),'noSelection':(['': message(code: 'review.filter.status.all')])],-1)
printHtmlPart(16)
}
printHtmlPart(17)
invokeTag('select','g',50,['class':("form-control"),'name':("value"),'from':([1, -1]),'valueMessagePrefix':("review.filter.value"),'value':(filter.value),'noSelection':(['': message(code: 'review.filter.value.all')])],-1)
printHtmlPart(18)
invokeTag('submitButton','g',55,['name':("filterAction"),'class':("btn btn-primary ft"),'value':(message(code: 'default.button.filter.label'))],-1)
printHtmlPart(19)
invokeTag('submitButton','g',56,['name':("resetAction"),'class':("btn btn-default ft"),'formaction':("reset"),'value':(message(code: 'default.button.filterReset.label'))],-1)
printHtmlPart(20)
})
invokeTag('form','g',61,['url':([controller: 'review', action: 'index'])],2)
printHtmlPart(21)
createTagBody(2, {->
printHtmlPart(22)
invokeTag('message','g',70,['code':("review.accounts.label"),'default':("Предприятия")],-1)
printHtmlPart(23)
})
invokeTag('ifNotGranted','sec',71,['roles':("ROLE_ACCOUNT")],2)
printHtmlPart(24)
createTagBody(2, {->
printHtmlPart(22)
invokeTag('message','g',73,['code':("review.account.label"),'default':("Предприятие")],-1)
printHtmlPart(23)
})
invokeTag('ifAllGranted','sec',74,['roles':("ROLE_ACCOUNT")],2)
printHtmlPart(25)
invokeTag('message','g',78,['code':("review.author.label"),'default':("Author")],-1)
printHtmlPart(26)
invokeTag('message','g',80,['code':("review.value.label"),'default':("Value")],-1)
printHtmlPart(26)
invokeTag('message','g',82,['code':("review.published.label"),'default':("Published")],-1)
printHtmlPart(27)
invokeTag('sortableColumn','g',84,['property':("dateCreated"),'title':(message(code: 'review.dateCreated.label', default: 'Date Created')),'class':("ft")],-1)
printHtmlPart(24)
createClosureForHtmlPart(28, 2)
invokeTag('ifAnyGranted','sec',87,['roles':("ROLE_ADMIN,ROLE_MANAGER")],2)
printHtmlPart(29)
loop:{
int i = 0
for( obj in (reviewInstanceList) ) {
printHtmlPart(30)
expressionOut.print(obj.id)
printHtmlPart(31)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(32)
createTagBody(3, {->
printHtmlPart(33)
createTagBody(4, {->
printHtmlPart(34)
expressionOut.print(fieldValue(bean: obj, field: "from.name"))
printHtmlPart(35)
expressionOut.print(fieldValue(bean: obj, field: "to.name"))
printHtmlPart(33)
})
invokeTag('ifNotGranted','sec',100,['roles':("ROLE_ACCOUNT")],4)
printHtmlPart(33)
createTagBody(4, {->
printHtmlPart(34)
expressionOut.print(fieldValue(bean: obj, field: filter.type == 'to' ? "from.name" : "to.name"))
printHtmlPart(33)
})
invokeTag('ifAllGranted','sec',103,['roles':("ROLE_ACCOUNT")],4)
printHtmlPart(36)
})
invokeTag('link','g',104,['class':(userNewObjects.contains(obj) ? "newItem" : ""),'action':("edit"),'id':(obj.id)],3)
printHtmlPart(37)
expressionOut.print(fieldValue(bean: obj, field: "author.fio"))
printHtmlPart(38)
if(true && (obj.value == 1)) {
printHtmlPart(39)
}
printHtmlPart(36)
if(true && (obj.value == 0)) {
printHtmlPart(40)
}
printHtmlPart(36)
if(true && (obj.value == -1)) {
printHtmlPart(41)
}
printHtmlPart(42)
if(true && (obj.finished)) {
printHtmlPart(43)
expressionOut.print(obj.published ? 'glyphicon-ok' : 'glyphicon-lock')
printHtmlPart(44)
}
else {
printHtmlPart(33)
if(true && (obj.value != 0)) {
printHtmlPart(45)
}
printHtmlPart(36)
}
printHtmlPart(46)
invokeTag('formatDate','g',136,['date':(obj.dateCreated),'format':("dd.MM.yyyy HH:mm:ss")],-1)
printHtmlPart(47)
createTagBody(3, {->
printHtmlPart(48)
expressionOut.print(obj.id)
printHtmlPart(49)
})
invokeTag('ifAnyGranted','sec',139,['roles':("ROLE_ADMIN,ROLE_MANAGER")],3)
printHtmlPart(50)
i++
}
}
printHtmlPart(51)
if(true && (params.max < rowsCount)) {
printHtmlPart(7)
invokeTag('paginate','g',146,['total':(rowsCount ?: 0)],-1)
printHtmlPart(1)
}
printHtmlPart(52)
invokeTag('render','g',151,['template':("/_common/edit-container")],-1)
printHtmlPart(3)
invokeTag('render','g',152,['template':("/_common/gallery-multi")],-1)
printHtmlPart(53)
expressionOut.print(message(code: 'item.delete.confirm.'+params.categoryType?.code, default: 'Delete this item?'))
printHtmlPart(54)
expressionOut.print(createLink([controller: 'deal', action: 'deletedeal']))
printHtmlPart(55)
})
invokeTag('captureBody','sitemesh',174,[:],1)
printHtmlPart(56)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1478584147257L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
