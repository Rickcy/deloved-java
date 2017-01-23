import ru.deloved.DealStatus
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_dealindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/deal/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("admin")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("referrer"),'content':("origin-when-crossorigin")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',7,['code':("deal.list.label")],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',102,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
if(true && (myAccounts)) {
printHtmlPart(5)
createClosureForHtmlPart(6, 3)
invokeTag('link','g',157,['class':("jud_button_contunue_main ft"),'controller':("ticket"),'action':("create"),'target':("_blank")],3)
printHtmlPart(7)
createClosureForHtmlPart(6, 3)
invokeTag('link','g',176,['class':("jud_button_contunue_main ft"),'controller':("ticket"),'action':("create"),'target':("_blank")],3)
printHtmlPart(8)
createClosureForHtmlPart(6, 3)
invokeTag('link','g',201,['class':("jud_button_contunue_main ft"),'controller':("ticket"),'action':("create"),'target':("_blank")],3)
printHtmlPart(9)
}
printHtmlPart(10)
invokeTag('message','g',205,['code':("deal.list.label")],-1)
printHtmlPart(11)
invokeTag('render','g',211,['template':("/_common/flash-message")],-1)
printHtmlPart(12)
createTagBody(2, {->
printHtmlPart(13)
invokeTag('select','g',221,['class':("form-control"),'name':("status"),'from':(DealStatus.DEAL()),'optionKey':("value"),'optionValue':({ message(code: 'deal.status.' + it, default: it) }),'value':(filter.status),'noSelection':(['': message(code: 'deal.status.allstatus.labell')])],-1)
printHtmlPart(14)
invokeTag('textField','g',224,['placeholder':("Поиск"),'class':("form-control"),'name':("Поиск"),'value':(filter.search)],-1)
printHtmlPart(15)
expressionOut.print(message(code: 'default.button.filter.label'))
printHtmlPart(16)
expressionOut.print(message(code: 'default.button.filterReset.label'))
printHtmlPart(17)
})
invokeTag('form','g',234,['url':([controller: 'deal', action: 'index']),'style':("float: right;width: 70%;margin-right: 5%")],2)
printHtmlPart(18)
createClosureForHtmlPart(19, 2)
invokeTag('ifAnyGranted','sec',258,['roles':("ROLE_ACCOUNT")],2)
printHtmlPart(20)
invokeTag('message','g',268,['code':("deal.accounts.label"),'default':("Deal")],-1)
printHtmlPart(21)
invokeTag('message','g',271,['code':("deal.status.label"),'default':("Status")],-1)
printHtmlPart(22)
invokeTag('sortableColumn','g',273,['property':("dateCreated"),'title':(message(code: 'deal.dateCreated.label', default: 'Date Created')),'class':("ft")],-1)
printHtmlPart(4)
createClosureForHtmlPart(23, 2)
invokeTag('ifAnyGranted','sec',273,['roles':("ROLE_ADMIN")],2)
printHtmlPart(24)
loop:{
int i = 0
for( obj in (dealInstanceList) ) {
printHtmlPart(25)
expressionOut.print(obj.id)
printHtmlPart(26)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(27)
expressionOut.print(i+1)
printHtmlPart(28)
createTagBody(3, {->
expressionOut.print(obj.account.name)
printHtmlPart(29)
expressionOut.print(obj.partner.name)
})
invokeTag('link','g',285,['class':(userNewObjects.contains(obj) ? "newItem" : ""),'id':(obj.id),'controller':("deal"),'action':("thread")],3)
printHtmlPart(30)
if(true && (userNewObjectPosts[obj])) {
printHtmlPart(31)
expressionOut.print(userNewObjectPosts[obj])
printHtmlPart(32)
}
printHtmlPart(33)
if(true && (myAccounts)) {
printHtmlPart(30)
if(true && (myAccounts.contains(obj.partner))) {
printHtmlPart(34)
createTagBody(5, {->
expressionOut.print(obj.account.name)
})
invokeTag('link','g',294,['controller':("company"),'id':(obj.account.id)],5)
printHtmlPart(35)
}
else {
printHtmlPart(36)
createTagBody(5, {->
expressionOut.print(obj.partner.name)
})
invokeTag('link','g',303,['controller':("company"),'id':(obj.partner.id)],5)
printHtmlPart(37)
}
printHtmlPart(38)
}
else {
printHtmlPart(39)
expressionOut.print(obj.partner.name)
printHtmlPart(40)
expressionOut.print(obj.account.name)
printHtmlPart(41)
}
printHtmlPart(42)
expressionOut.print(message(code: 'deal.status.' + obj.status(), default: obj.status()))
printHtmlPart(30)
if(true && (userNewObjectStatuses[obj])) {
printHtmlPart(31)
expressionOut.print(userNewObjectStatuses[obj])
printHtmlPart(32)
}
printHtmlPart(43)
invokeTag('formatDate','g',311,['date':(obj.dateCreated),'format':("dd.MM.yyyy HH:mm:ss")],-1)
printHtmlPart(41)
createTagBody(3, {->
printHtmlPart(44)
expressionOut.print(obj.id)
printHtmlPart(45)
})
invokeTag('ifAnyGranted','sec',321,['roles':("ROLE_ADMIN")],3)
printHtmlPart(46)
i++
}
}
printHtmlPart(47)
invokeTag('message','g',325,['code':("deal.accounts.label"),'default':("Deal")],-1)
printHtmlPart(48)
invokeTag('message','g',329,['code':("deal.status.label"),'default':("Status")],-1)
printHtmlPart(22)
invokeTag('sortableColumn','g',331,['property':("dateCreated"),'title':(message(code: 'deal.dateCreated.label', default: 'Date Created')),'class':("ft")],-1)
printHtmlPart(49)
loop:{
int i = 0
for( obj in (dealInstanceList) ) {
printHtmlPart(50)
expressionOut.print(obj.id)
printHtmlPart(26)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(51)
expressionOut.print(i+1)
printHtmlPart(28)
createTagBody(3, {->
expressionOut.print(obj.account.name)
printHtmlPart(29)
expressionOut.print(obj.partner.name)
})
invokeTag('link','g',345,['class':(userNewObjects.contains(obj) ? "newItem" : ""),'id':(obj.id),'controller':("deal"),'action':("thread")],3)
printHtmlPart(30)
if(true && (userNewObjectPosts[obj])) {
printHtmlPart(31)
expressionOut.print(userNewObjectPosts[obj])
printHtmlPart(32)
}
printHtmlPart(33)
if(true && (myAccounts)) {
printHtmlPart(30)
if(true && (myAccounts.contains(obj.partner))) {
printHtmlPart(52)
}
else {
printHtmlPart(53)
}
printHtmlPart(38)
}
else {
printHtmlPart(39)
expressionOut.print(obj.partner.name)
printHtmlPart(40)
expressionOut.print(obj.account.name)
printHtmlPart(41)
}
printHtmlPart(42)
expressionOut.print(message(code: 'deal.status.' + obj.status(), default: obj.status()))
printHtmlPart(30)
if(true && (userNewObjectStatuses[obj])) {
printHtmlPart(31)
expressionOut.print(userNewObjectStatuses[obj])
printHtmlPart(32)
}
printHtmlPart(43)
invokeTag('formatDate','g',365,['date':(obj.dateCreated),'format':("dd.MM.yyyy HH:mm:ss")],-1)
printHtmlPart(54)
i++
}
}
printHtmlPart(55)
invokeTag('message','g',374,['code':("deal.accounts.label"),'default':("Deal")],-1)
printHtmlPart(56)
invokeTag('message','g',377,['code':("deal.status.label"),'default':("Status")],-1)
printHtmlPart(22)
invokeTag('sortableColumn','g',379,['property':("dateCreated"),'title':(message(code: 'deal.dateCreated.label', default: 'Date Created')),'class':("ft")],-1)
printHtmlPart(57)
loop:{
int i = 0
for( obj in (dealInstanceList) ) {
printHtmlPart(58)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(59)
expressionOut.print(i+1)
printHtmlPart(28)
createTagBody(3, {->
expressionOut.print(obj.account.name)
printHtmlPart(29)
expressionOut.print(obj.partner.name)
})
invokeTag('link','g',387,['class':(userNewObjects.contains(obj) ? "newItem" : ""),'id':(obj.id),'controller':("deal"),'action':("thread")],3)
printHtmlPart(30)
if(true && (userNewObjectPosts[obj])) {
printHtmlPart(31)
expressionOut.print(userNewObjectPosts[obj])
printHtmlPart(32)
}
printHtmlPart(33)
if(true && (myAccounts)) {
printHtmlPart(30)
if(true && (myAccounts.contains(obj.partner))) {
printHtmlPart(60)
}
else {
printHtmlPart(61)
}
printHtmlPart(38)
}
else {
printHtmlPart(39)
expressionOut.print(obj.partner.name)
printHtmlPart(40)
expressionOut.print(obj.account.name)
printHtmlPart(41)
}
printHtmlPart(42)
expressionOut.print(message(code: 'deal.status.' + obj.status(), default: obj.status()))
printHtmlPart(30)
if(true && (userNewObjectStatuses[obj])) {
printHtmlPart(31)
expressionOut.print(userNewObjectStatuses[obj])
printHtmlPart(32)
}
printHtmlPart(43)
invokeTag('formatDate','g',415,['date':(obj.dateCreated),'format':("dd.MM.yyyy HH:mm:ss")],-1)
printHtmlPart(62)
i++
}
}
printHtmlPart(63)
if(true && (params.max < rowsCount)) {
printHtmlPart(64)
invokeTag('paginate','g',420,['total':(rowsCount ?: 0)],-1)
printHtmlPart(1)
}
printHtmlPart(65)
expressionOut.print(message(code: 'item.delete.confirm.'+params.categoryType?.code, default: 'Delete this item?'))
printHtmlPart(66)
expressionOut.print(createLink([controller: 'deal', action: 'deletedeal']))
printHtmlPart(67)
})
invokeTag('captureBody','sitemesh',434,[:],1)
printHtmlPart(68)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1479273615677L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
