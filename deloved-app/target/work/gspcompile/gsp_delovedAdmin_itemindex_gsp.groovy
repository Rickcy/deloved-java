import ru.deloved.CategoryType
import  ru.deloved.Item
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_itemindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/item/index.gsp" }
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
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',12,['code':('item.list.' + params.categoryType?.code + '.label')],-1)
printHtmlPart(5)
invokeTag('render','g',14,['template':("/_common/flash-message")],-1)
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(6)
if(true && (params.categoryType?.code == 'GOOD')) {
printHtmlPart(7)
createTagBody(4, {->
invokeTag('message','g',19,['code':("item.button.new.label.good")],-1)
})
invokeTag('link','g',19,['style':("width: 20%;min-width: 110px;margin-bottom: 5px"),'class':("btn btn-success ft"),'controller':("item"),'action':("create"),'mapping':(params.categoryType?.code)],4)
printHtmlPart(8)
}
else {
printHtmlPart(7)
createTagBody(4, {->
invokeTag('message','g',22,['code':("item.button.new.label.service")],-1)
})
invokeTag('link','g',22,['style':("width: 20%;min-width: 110px;margin-bottom: 5px"),'class':("btn btn-success ft"),'controller':("item"),'action':("create"),'mapping':(params.categoryType?.code)],4)
printHtmlPart(8)
}
printHtmlPart(9)
createTagBody(3, {->
invokeTag('message','g',25,['code':("item.button.export.label")],-1)
})
invokeTag('link','g',25,['style':("width: 20%;min-width: 110px;margin-bottom: 5px"),'class':("btn btn-success ft"),'action':("export"),'mapping':(params.categoryType?.code)],3)
printHtmlPart(10)
invokeTag('message','g',28,['code':("item.button.import.label")],-1)
printHtmlPart(11)
expressionOut.print(createLink(action: 'import', mapping: params.categoryType?.code))
printHtmlPart(12)
})
invokeTag('ifAnyGranted','sec',61,['roles':("ROLE_ACCOUNT")],2)
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(13)
if(true && (params.categoryType?.code == 'GOOD')) {
printHtmlPart(14)
invokeTag('select','g',71,['class':("form-control"),'style':("margin-bottom: 3%"),'name':("avail"),'from':([1, 0]),'valueMessagePrefix':("item.filter.avail"),'value':(filter?.avail),'noSelection':(['': message(code: 'item.filter.avail.all')])],-1)
printHtmlPart(15)
}
printHtmlPart(16)
invokeTag('textField','g',75,['placeholder':("Поиск"),'class':("form-control"),'name':("search"),'value':(filter?.search),'style':("margin-bottom: 3%")],-1)
printHtmlPart(17)
invokeTag('textField','g',77,['placeholder':("Цена от"),'class':("form-control"),'name':("priceMin"),'value':(filter?.priceMin),'style':("margin-bottom: 3%")],-1)
printHtmlPart(17)
invokeTag('textField','g',79,['placeholder':("Цена до"),'class':("form-control"),'name':("priceMax"),'value':(filter?.priceMax),'style':("margin-bottom: 3%")],-1)
printHtmlPart(18)
invokeTag('submitButton','g',82,['name':("filterAction"),'class':("btn btn-primary ft"),'value':(message(code: 'default.button.filter.label')),'style':("margin-bottom: 4%")],-1)
printHtmlPart(19)
invokeTag('submitButton','g',86,['name':("resetAction"),'class':("btn btn-default ft"),'style':("margin-bottom: 4%"),'formaction':("reset"),'value':(message(code: 'default.button.filterReset.label'))],-1)
printHtmlPart(20)
})
invokeTag('form','g',91,['url':([mapping: params.categoryType?.code, controller:'item' , action: 'index'])],2)
printHtmlPart(21)
invokeTag('message','g',99,['code':("item.account.label"),'default':("Account")],-1)
printHtmlPart(22)
invokeTag('sortableColumn','g',101,['property':("name"),'class':("ft"),'mapping':(params.categoryType?.code),'title':(message(code: 'item.name.label', default: 'Name'))],-1)
printHtmlPart(23)
invokeTag('sortableColumn','g',103,['property':("price"),'class':("ft"),'mapping':(params.categoryType?.code),'title':(message(code: 'item.price.label', default: 'Price'))],-1)
printHtmlPart(24)
invokeTag('sortableColumn','g',108,['class':("ft"),'property':("dateCreated"),'mapping':(params.categoryType?.code),'title':(message(code: 'item.dateCreated.label', default: 'Date Created'))],-1)
printHtmlPart(25)
loop:{
int i = 0
for( obj in (itemInstanceList) ) {
printHtmlPart(26)
expressionOut.print(obj.id)
printHtmlPart(27)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(28)
expressionOut.print(obj.id)
printHtmlPart(29)
expressionOut.print(obj.id)
printHtmlPart(30)
expressionOut.print(fieldValue(bean: obj, field: "account.name"))
printHtmlPart(31)
createTagBody(3, {->
expressionOut.print(fieldValue(bean: obj, field: "name"))
})
invokeTag('link','g',122,['class':(userNewObjects.contains(obj) ? "newItem" : ""),'controller':("item"),'action':("edit"),'id':(obj.id)],3)
printHtmlPart(32)
if(true && (userNewObjects.contains(obj))) {
printHtmlPart(33)
}
printHtmlPart(34)
expressionOut.print(obj.id)
printHtmlPart(35)
invokeTag('formatNumber','g',128,['number':(obj.price),'type':("currency"),'currencyCode':(obj.currency.code)],-1)
printHtmlPart(36)
invokeTag('formatDate','g',133,['date':(obj.dateCreated),'format':("dd.MM.yyyy HH:mm:ss")],-1)
printHtmlPart(37)
expressionOut.print(obj.id)
printHtmlPart(38)
i++
}
}
printHtmlPart(39)
if(true && (params.max < rowsCount)) {
printHtmlPart(8)
invokeTag('paginate','g',145,['total':(rowsCount ?: 0),'mapping':(params.categoryType?.code)],-1)
printHtmlPart(40)
}
printHtmlPart(41)
invokeTag('render','g',147,['template':("/_common/edit-container")],-1)
printHtmlPart(2)
invokeTag('render','g',149,['template':("/_common/gallery-multi")],-1)
printHtmlPart(2)
invokeTag('render','g',150,['template':("/_common/crop")],-1)
printHtmlPart(42)
expressionOut.print(message(code: 'item.delete.confirm.'+params.categoryType?.code, default: 'Delete this item?'))
printHtmlPart(43)
expressionOut.print(createLink([controller: 'item', action: 'delete']))
printHtmlPart(44)
})
invokeTag('captureBody','sitemesh',167,[:],1)
printHtmlPart(45)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1481863920917L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
