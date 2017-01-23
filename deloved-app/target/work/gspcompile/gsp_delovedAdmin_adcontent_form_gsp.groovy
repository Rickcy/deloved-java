import ru.deloved.Adcontent
import ru.deloved.AdcontentType
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_adcontent_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/adcontent/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
expressionOut.print(hasErrors(bean: objInstance, field: 'account', 'error'))
printHtmlPart(2)
invokeTag('message','g',7,['code':("adcontent.account.label"),'default':("Account")],-1)
printHtmlPart(3)
if(true && (objInstance.id == null)) {
printHtmlPart(4)
if(true && (objInstance.accountId)) {
printHtmlPart(5)
expressionOut.print(objInstance.account?.name)
printHtmlPart(5)
invokeTag('hiddenField','g',15,['name':("account.id"),'value':(objInstance.accountId)],-1)
printHtmlPart(4)
}
printHtmlPart(4)
if(true && (accountList?.size() > 0)) {
printHtmlPart(5)
invokeTag('select','g',19,['id':("account"),'name':("account.id"),'class':("form-control"),'from':(accountList),'optionKey':("id"),'optionValue':("name"),'required':(""),'value':(objInstance?.account?.id)],-1)
printHtmlPart(4)
}
else {
printHtmlPart(5)
invokeTag('render','g',29,['template':("/_common/auto-complete"),'model':([
						acAction   : createLink(controller: 'adcontent', action: 'accounts'),
						acMinLength: 4,
						acKeyValue : '',
						acKeyName  : 'account.id',
						acViewValue: '',
						acViewName : 'accountname'
				])],-1)
printHtmlPart(6)
}
printHtmlPart(7)
}
else {
printHtmlPart(4)
if(true && (accountList?.size() > 1)) {
printHtmlPart(5)
invokeTag('select','g',36,['id':("account"),'name':("account.id"),'class':("form-control"),'from':(accountList),'optionKey':("id"),'optionValue':("name"),'required':(""),'value':(objInstance?.account?.id)],-1)
printHtmlPart(4)
}
printHtmlPart(4)
if(true && (accountList?.size() == 1)) {
printHtmlPart(5)
expressionOut.print(objInstance.account?.name)
printHtmlPart(4)
}
else {
printHtmlPart(5)
invokeTag('render','g',49,['template':("/_common/auto-complete"),'model':([
						acAction   : createLink(controller: 'adcontent', action: 'accounts'),
						acMinLength: 4,
						acKeyValue : objInstance.accountId,
						acKeyName  : 'account.id',
						acViewValue: objInstance.account.name,
						acViewName : 'accountname'
				])],-1)
printHtmlPart(4)
}
printHtmlPart(7)
}
printHtmlPart(8)
expressionOut.print(hasErrors(bean: objInstance, field: 'name', 'error'))
printHtmlPart(9)
invokeTag('message','g',57,['code':("adcontent.name.label"),'default':("Name")],-1)
printHtmlPart(10)
invokeTag('textField','g',63,['class':("form-control"),'name':("name"),'required':(""),'value':(objInstance?.name)],-1)
printHtmlPart(11)
expressionOut.print(hasErrors(bean: objInstance, field: 'description', 'error'))
printHtmlPart(12)
invokeTag('message','g',71,['code':("adcontent.description.label"),'default':("Description")],-1)
printHtmlPart(13)
invokeTag('textArea','g',76,['style':("min-height: 100px"),'class':("form-control"),'name':("description"),'value':(objInstance?.description)],-1)
printHtmlPart(14)
if(true && (objInstance.type == AdcontentType.Video.value())) {
printHtmlPart(15)
expressionOut.print(hasErrors(bean: objInstance, field: 'url', 'error'))
printHtmlPart(16)
invokeTag('message','g',84,['code':("adcontent.url.label"),'default':("URL")],-1)
printHtmlPart(17)
invokeTag('textArea','g',90,['style':("min-height: 100px"),'class':("form-control"),'name':("url"),'required':(""),'value':(objInstance?.url)],-1)
printHtmlPart(18)
}
else if(true && (objInstance.type == AdcontentType.Audio.value())) {
printHtmlPart(19)
}
else if(true && (objInstance.type == AdcontentType.Image.value())) {
printHtmlPart(20)
}
else if(true && (objInstance.type == AdcontentType.Pdf.value())) {
printHtmlPart(21)
}
printHtmlPart(22)
createTagBody(1, {->
printHtmlPart(23)
expressionOut.print(hasErrors(bean: objInstance, field: 'approved', 'error'))
printHtmlPart(24)
invokeTag('message','g',107,['code':("account.chargeStatus.label"),'default':("Approved")],-1)
printHtmlPart(25)
if(true && (!objInstance?.approved)) {
printHtmlPart(26)
}
printHtmlPart(27)
invokeTag('radio','g',113,['name':("approved"),'value':("false"),'checked':(!objInstance?.approved)],-1)
printHtmlPart(28)
if(true && (objInstance?.approved)) {
printHtmlPart(26)
}
printHtmlPart(27)
invokeTag('radio','g',116,['name':("approved"),'value':("true"),'checked':(objInstance?.approved)],-1)
printHtmlPart(29)
})
invokeTag('ifAnyGranted','sec',121,['roles':("ROLE_ADMIN,ROLE_MANAGER")],1)
printHtmlPart(0)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1470627494959L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
