import ru.deloved.CategoryType
import  ru.deloved.Item
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_itemindex2_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/item/index2.gsp" }
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
invokeTag('message','g',6,['code':('item.list.' + params.categoryType?.code + '.label')],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',35,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
if(true && (myAccounts)) {
printHtmlPart(5)
createClosureForHtmlPart(6, 3)
invokeTag('link','g',56,['class':("jud_button_contunue_main"),'controller':("account"),'action':("index")],3)
printHtmlPart(7)
createClosureForHtmlPart(6, 3)
invokeTag('link','g',75,['class':("jud_button_contunue_main"),'style':("margin-top: 20px !important;"),'controller':("account"),'action':("index")],3)
printHtmlPart(8)
}
printHtmlPart(9)
invokeTag('message','g',83,['code':('item.list.' + params.categoryType?.code + '.label')],-1)
printHtmlPart(10)
invokeTag('render','g',84,['template':("/_common/flash-message")],-1)
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(11)
createTagBody(3, {->
invokeTag('message','g',85,['code':("item.button.new.label")],-1)
})
invokeTag('link','g',85,['class':("btn btn-success"),'action':("create"),'mapping':(params.categoryType?.code)],3)
printHtmlPart(12)
createTagBody(3, {->
invokeTag('message','g',93,['code':("item.button.export.label")],-1)
})
invokeTag('link','g',93,['class':("btn btn-success"),'action':("export"),'mapping':(params.categoryType?.code)],3)
printHtmlPart(13)
invokeTag('message','g',97,['code':("item.button.import.label")],-1)
printHtmlPart(14)
expressionOut.print(createLink(action: 'import', mapping: params.categoryType?.code))
printHtmlPart(15)
})
invokeTag('ifAnyGranted','sec',119,['roles':("ROLE_ACCOUNT")],2)
printHtmlPart(16)
createTagBody(2, {->
printHtmlPart(17)
if(true && (params.categoryType?.code == 'GOOD')) {
printHtmlPart(18)
invokeTag('select','g',137,['class':("form-control"),'name':("avail"),'from':([1, 0]),'valueMessagePrefix':("item.filter.avail"),'value':(filter?.avail),'noSelection':(['': message(code: 'item.filter.avail.all')])],-1)
printHtmlPart(19)
}
printHtmlPart(20)
invokeTag('textField','g',143,['placeholder':("Поиск"),'class':("form-control"),'name':("search"),'value':(filter?.search)],-1)
printHtmlPart(21)
invokeTag('textField','g',146,['placeholder':("Цена от"),'class':("form-control"),'name':("priceMin"),'value':(filter?.priceMin)],-1)
printHtmlPart(21)
invokeTag('textField','g',148,['placeholder':("Цена до"),'class':("form-control"),'name':("priceMax"),'value':(filter?.priceMax)],-1)
printHtmlPart(22)
invokeTag('submitButton','g',153,['name':("filterAction"),'class':("btn btn-primary"),'value':(message(code: 'default.button.filter.label'))],-1)
printHtmlPart(23)
invokeTag('submitButton','g',157,['name':("resetAction"),'class':("btn btn-default"),'formaction':("reset"),'value':(message(code: 'default.button.filterReset.label'))],-1)
printHtmlPart(24)
})
invokeTag('form','g',157,['url':([mapping: params.categoryType?.code, action: 'index'])],2)
printHtmlPart(25)
invokeTag('message','g',160,['code':("item.account.label"),'default':("Account")],-1)
printHtmlPart(26)
invokeTag('sortableColumn','g',164,['property':("name"),'mapping':(params.categoryType?.code),'title':(message(code: 'item.name.label', default: 'Name'))],-1)
printHtmlPart(27)
invokeTag('sortableColumn','g',167,['property':("price"),'mapping':(params.categoryType?.code),'title':(message(code: 'item.price.label', default: 'Price'))],-1)
printHtmlPart(27)
if(true && (params.categoryType?.code == 'GOOD')) {
printHtmlPart(28)
invokeTag('message','g',175,['code':("item.availability.label"),'default':("availability")],-1)
printHtmlPart(26)
}
printHtmlPart(29)
invokeTag('sortableColumn','g',180,['property':("dateCreated"),'mapping':(params.categoryType?.code),'title':(message(code: 'item.dateCreated.label', default: 'Date Created'))],-1)
printHtmlPart(30)
loop:{
int i = 0
for( obj in (itemInstanceList) ) {
printHtmlPart(31)
expressionOut.print(obj.id)
printHtmlPart(32)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(33)
expressionOut.print(obj.id)
printHtmlPart(34)
expressionOut.print(obj.id)
printHtmlPart(35)
expressionOut.print(fieldValue(bean: obj, field: "account.name"))
printHtmlPart(36)
createTagBody(3, {->
expressionOut.print(fieldValue(bean: obj, field: "name"))
})
invokeTag('link','g',202,['action':("edit"),'controller':("item"),'id':(obj.id),'elementId':("gridRow${obj.id}name"),'data-toggle':("modal"),'data-remote':(createLink(id: obj.id, action: 'edit')),'data-target':("#myModal")],3)
printHtmlPart(37)
expressionOut.print(obj.id)
printHtmlPart(38)
invokeTag('formatNumber','g',204,['number':(obj.price),'type':("currency"),'currencyCode':(obj.currency.code)],-1)
printHtmlPart(39)
if(true && (params.categoryType?.code == 'GOOD')) {
printHtmlPart(40)
expressionOut.print(obj.id)
printHtmlPart(41)
expressionOut.print(message(code: 'item.filter.avail.' + obj.availability, default: obj.availability))
printHtmlPart(42)
}
printHtmlPart(43)
invokeTag('formatDate','g',209,['date':(obj.dateCreated),'format':("dd.MM.yyyy HH:mm:ss")],-1)
printHtmlPart(44)
expressionOut.print(obj.id)
printHtmlPart(45)
i++
}
}
printHtmlPart(46)
if(true && (params.max < rowsCount)) {
printHtmlPart(12)
invokeTag('paginate','g',216,['total':(rowsCount ?: 0),'mapping':(params.categoryType?.code)],-1)
printHtmlPart(47)
}
printHtmlPart(48)
invokeTag('render','g',216,['template':("/_common/edit-container")],-1)
printHtmlPart(4)
invokeTag('render','g',220,['template':("/_common/gallery-multi")],-1)
printHtmlPart(4)
invokeTag('render','g',220,['template':("/_common/crop")],-1)
printHtmlPart(49)
expressionOut.print(message(code: 'item.delete.confirm.'+params.categoryType?.code, default: 'Delete this item?'))
printHtmlPart(50)
expressionOut.print(createLink([controller: 'item', action: 'delete']))
printHtmlPart(51)
})
invokeTag('captureBody','sitemesh',246,[:],1)
printHtmlPart(52)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1478073107039L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
