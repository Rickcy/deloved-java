import ru.deloved.CategoryType
import  ru.deloved.Category
import  ru.deloved.Item
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_item_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/item/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
expressionOut.print(hasErrors(bean: objInstance, field: 'showMain', 'error'))
printHtmlPart(2)
invokeTag('message','g',7,['code':("item.showMain.label"),'default':("Show on main")],-1)
printHtmlPart(3)
if(true && (objInstance?.showMain == false)) {
printHtmlPart(4)
}
printHtmlPart(5)
invokeTag('radio','g',13,['name':("showMain"),'value':("false"),'checked':(objInstance?.showMain == false)],-1)
printHtmlPart(6)
if(true && (objInstance?.showMain == true)) {
printHtmlPart(4)
}
printHtmlPart(5)
invokeTag('radio','g',16,['name':("showMain"),'value':("true"),'checked':(objInstance?.showMain == true)],-1)
printHtmlPart(7)
expressionOut.print(hasErrors(bean: objInstance, field: 'account', 'error'))
printHtmlPart(8)
invokeTag('message','g',23,['code':("item.account.label"),'default':("Account")],-1)
printHtmlPart(9)
if(true && (objInstance.id == null)) {
printHtmlPart(10)
if(true && (objInstance.accountId)) {
printHtmlPart(11)
expressionOut.print(objInstance.account?.name)
printHtmlPart(11)
invokeTag('hiddenField','g',31,['name':("account.id"),'value':(objInstance.accountId)],-1)
printHtmlPart(10)
}
else if(true && (accountList?.size() > 0)) {
printHtmlPart(11)
invokeTag('select','g',35,['id':("account"),'name':("account.id"),'class':("form-control"),'from':(accountList),'optionKey':("id"),'optionValue':("name"),'required':(""),'value':(objInstance?.account?.id),'onchange':("initTree(jQuery('#account').val());")],-1)
printHtmlPart(10)
}
else {
printHtmlPart(11)
invokeTag('render','g',46,['template':("/_common/auto-complete"),'model':([
										acAction   : createLink(controller: 'item', action: 'accounts'),
										acMinLength: 4,
										acKeyValue : '',
										acKeyName  : 'account.id',
										acViewValue: '',
										acViewName : 'accountname',
										acOnSelect : 'initTree(ui.item.id);'
								])],-1)
printHtmlPart(12)
}
printHtmlPart(13)
}
else {
printHtmlPart(10)
if(true && (accountList?.size() > 1)) {
printHtmlPart(11)
invokeTag('select','g',53,['id':("account"),'name':("account.id"),'class':("form-control"),'from':(accountList),'optionKey':("id"),'optionValue':("name"),'required':(""),'value':(objInstance?.account?.id),'onchange':("initTree(jQuery('#account').val());")],-1)
printHtmlPart(10)
}
else if(true && (accountList?.size() == 1)) {
printHtmlPart(11)
expressionOut.print(objInstance.account?.name)
printHtmlPart(10)
}
else {
printHtmlPart(11)
invokeTag('render','g',67,['template':("/_common/auto-complete"),'model':([
										acAction   : createLink(controller: 'item', action: 'accounts'),
										acMinLength: 4,
										acKeyValue : objInstance.accountId,
										acKeyName  : 'account.id',
										acViewValue: objInstance.account.name,
										acViewName : 'accountname',
										acOnSelect : 'initTree(ui.item.id);'
								])],-1)
printHtmlPart(10)
}
printHtmlPart(13)
}
printHtmlPart(14)
})
invokeTag('ifAnyGranted','sec',72,['roles':("ROLE_ADMIN, ROLE_MANAGER")],1)
printHtmlPart(15)
createTagBody(1, {->
printHtmlPart(16)
expressionOut.print(objInstance.account.id)
printHtmlPart(17)
})
invokeTag('ifAnyGranted','sec',76,['roles':("ROLE_ACCOUNT")],1)
printHtmlPart(18)
expressionOut.print(objInstance.categoryType.id)
printHtmlPart(19)
expressionOut.print(hasErrors(bean: objInstance, field: 'name', 'error'))
printHtmlPart(20)
invokeTag('message','g',82,['code':("item.name.label"),'default':("Name")],-1)
printHtmlPart(21)
invokeTag('textField','g',86,['name':("name"),'class':("form-control"),'value':(objInstance?.name)],-1)
printHtmlPart(22)
invokeTag('render','g',87,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'name'])],-1)
printHtmlPart(23)
if(true && (objInstance.categoryType.code == 'GOOD')) {
printHtmlPart(24)
expressionOut.print(hasErrors(bean: objInstance, field: 'kind', 'error'))
printHtmlPart(25)
invokeTag('message','g',94,['code':("item.kind.label"),'default':("Kind")],-1)
printHtmlPart(26)
invokeTag('textField','g',98,['name':("kind"),'class':("form-control"),'value':(objInstance?.kind)],-1)
printHtmlPart(27)
invokeTag('render','g',99,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'kind'])],-1)
printHtmlPart(28)
}
printHtmlPart(29)
if(true && (objInstance.categoryType.code == 'SERVICE')) {
printHtmlPart(24)
expressionOut.print(hasErrors(bean: objInstance, field: 'kind', 'error'))
printHtmlPart(25)
invokeTag('message','g',107,['code':("item.kind.label"),'default':("Kind")],-1)
printHtmlPart(26)
invokeTag('textField','g',111,['name':("kind"),'class':("form-control"),'value':(objInstance?.kind)],-1)
printHtmlPart(27)
invokeTag('render','g',112,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'kind'])],-1)
printHtmlPart(30)
}
printHtmlPart(31)
invokeTag('message','g',121,['code':("item.price.label"),'default':("Price")],-1)
printHtmlPart(32)
expressionOut.print(hasErrors(bean: objInstance, field: 'price', 'error'))
printHtmlPart(33)
invokeTag('textField','g',125,['name':("price"),'class':("form-control"),'value':(formatNumber(number: objInstance?.price, format: '###,##0.00'))],-1)
printHtmlPart(22)
invokeTag('render','g',126,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'price'])],-1)
printHtmlPart(34)
expressionOut.print(hasErrors(bean: objInstance, field: 'currency', 'error'))
printHtmlPart(33)
invokeTag('select','g',131,['id':("currency"),'class':("form-control"),'name':("currency.id"),'from':(ru.deloved.SystemCurrency.findAll()),'optionKey':("id"),'optionValue':({it.getSymbol(requestLocale) }),'value':(objInstance?.currency?.id),'noSelection':([null:  'Выберите валюту'])],-1)
printHtmlPart(22)
invokeTag('render','g',132,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'currency'])],-1)
printHtmlPart(35)
expressionOut.print(hasErrors(bean: objInstance, field: 'measure', 'error'))
printHtmlPart(36)
invokeTag('message','g',139,['code':("item.measure.label"),'default':("Measure")],-1)
printHtmlPart(21)
invokeTag('select','g',145,['id':("measure"),'class':("form-control"),'name':("measure.id"),'from':(ru.deloved.Measure.findAllByType(objInstance.categoryType)),'optionKey':("id"),'noSelection':([null:'Выберите единицу измерения']),'value':(objInstance?.measure?.id)],-1)
printHtmlPart(22)
invokeTag('render','g',146,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'measure'])],-1)
printHtmlPart(35)
expressionOut.print(hasErrors(bean: objInstance, field: 'description', 'error'))
printHtmlPart(37)
invokeTag('message','g',153,['code':("item.description.label"),'default':("Description")],-1)
printHtmlPart(21)
invokeTag('textArea','g',157,['style':("min-height: 150px"),'class':("form-control"),'name':("description"),'value':(objInstance?.description)],-1)
printHtmlPart(22)
invokeTag('render','g',158,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'description'])],-1)
printHtmlPart(38)
if(true && (objInstance.categoryType.code == 'GOOD')) {
printHtmlPart(1)
expressionOut.print(hasErrors(bean: objInstance, field: 'availability', 'error'))
printHtmlPart(39)
invokeTag('message','g',166,['code':("item.availability.label"),'default':("Availability")],-1)
printHtmlPart(3)
if(true && (objInstance?.availability == 1)) {
printHtmlPart(4)
}
printHtmlPart(5)
invokeTag('radio','g',172,['name':("availability"),'value':("1"),'checked':(objInstance?.availability == 1)],-1)
printHtmlPart(40)
if(true && (objInstance?.availability == 0)) {
printHtmlPart(4)
}
printHtmlPart(5)
invokeTag('radio','g',175,['name':("availability"),'value':("0"),'checked':(objInstance?.availability == 0)],-1)
printHtmlPart(41)
invokeTag('render','g',178,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'availability'])],-1)
printHtmlPart(42)
}
printHtmlPart(43)
invokeTag('render','g',186,['template':("/_common/hint"),'model':([hintText: 'Загружать фото в формате .pnsg'])],-1)
invokeTag('message','g',186,['code':("item.photo.label"),'default':("photo")],-1)
printHtmlPart(44)
invokeTag('render','g',195,['template':("/_common/upload-multi-image"),'model':([
							objInstance: beanResource,
							attachList : attachList,
							imgWidth   : 100,
							imgHeight  : 100
					])],-1)
printHtmlPart(45)
expressionOut.print(hasErrors(bean: objInstance, field: 'category', 'error'))
printHtmlPart(46)
invokeTag('message','g',203,['code':("item.category.label"),'default':("Category")],-1)
printHtmlPart(47)
invokeTag('hiddenField','g',209,['name':("category.id"),'value':(objInstance?.categoryId)],-1)
printHtmlPart(48)
invokeTag('render','g',212,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'category'])],-1)
printHtmlPart(49)
expressionOut.print(createLink(controller: 'item', action: 'cat'))
printHtmlPart(50)
expressionOut.print(objInstance?.category?.id)
printHtmlPart(51)
expressionOut.print(Category.findByTypeAndParent(objInstance.categoryType, null).id)
printHtmlPart(52)
if(true && (objInstance.account)) {
printHtmlPart(53)
expressionOut.print(objInstance.account.id)
printHtmlPart(54)
}
else if(true && (accountList?.size() > 0)) {
printHtmlPart(53)
expressionOut.print(accountList[0].id)
printHtmlPart(54)
}
printHtmlPart(55)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1478073106850L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
