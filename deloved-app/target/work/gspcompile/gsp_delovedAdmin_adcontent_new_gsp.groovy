import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_adcontent_new_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/adcontent/_new.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: objInstance, field: 'account', 'error'))
printHtmlPart(1)
invokeTag('message','g',3,['code':("adcontent.account.label"),'default':("Account")],-1)
printHtmlPart(2)
if(true && (objInstance.id == null)) {
printHtmlPart(3)
if(true && (objInstance.accountId)) {
printHtmlPart(4)
expressionOut.print(objInstance.account?.name)
printHtmlPart(4)
invokeTag('hiddenField','g',11,['name':("account.id"),'value':(objInstance.accountId)],-1)
printHtmlPart(3)
}
else if(true && (accountList?.size() > 0)) {
printHtmlPart(3)
invokeTag('select','g',14,['id':("account"),'name':("account.id"),'class':("form-control"),'from':(accountList),'optionKey':("id"),'optionValue':("name"),'required':(""),'value':(objInstance?.account?.id)],-1)
printHtmlPart(3)
}
else {
printHtmlPart(4)
invokeTag('render','g',24,['template':("/_common/auto-complete"),'model':([
					acAction   : createLink(controller: 'adcontent', action: 'accounts'),
					acMinLength: 4,
					acKeyValue : '',
					acKeyName  : 'account.id',
					acViewValue: '',
					acViewName : 'accountname'
					])],-1)
printHtmlPart(5)
}
printHtmlPart(6)
}
else {
printHtmlPart(3)
if(true && (accountList?.size() > 1)) {
printHtmlPart(3)
invokeTag('select','g',30,['id':("account"),'name':("account.id"),'class':("form-control"),'from':(accountList),'optionKey':("id"),'optionValue':("name"),'required':(""),'value':(objInstance?.account?.id)],-1)
printHtmlPart(3)
}
else if(true && (accountList?.size() == 1)) {
printHtmlPart(4)
expressionOut.print(objInstance.account?.name)
printHtmlPart(3)
}
else {
printHtmlPart(4)
invokeTag('render','g',43,['template':("/_common/auto-complete"),'model':([
					acAction   : createLink(controller: 'adcontent', action: 'accounts'),
					acMinLength: 4,
					acKeyValue : objInstance.accountId,
					acKeyName  : 'account.id',
					acViewValue: objInstance.account.name,
					acViewName : 'accountname'
					])],-1)
printHtmlPart(3)
}
printHtmlPart(6)
}
printHtmlPart(7)
expressionOut.print(hasErrors(bean: objInstance, field: 'name', 'error'))
printHtmlPart(8)
invokeTag('message','g',51,['code':("adcontent.name.label"),'default':("Name")],-1)
printHtmlPart(9)
invokeTag('textField','g',57,['class':("form-control"),'name':("name"),'required':(""),'value':(objInstance?.name)],-1)
printHtmlPart(10)
expressionOut.print(hasErrors(bean: objInstance, field: 'description', 'error'))
printHtmlPart(11)
invokeTag('message','g',65,['code':("adcontent.description.label"),'default':("Description")],-1)
printHtmlPart(12)
invokeTag('textArea','g',70,['style':("min-height: 100px"),'class':("form-control"),'name':("description"),'value':(objInstance?.description)],-1)
printHtmlPart(13)
expressionOut.print(hasErrors(bean: objInstance, field: 'url', 'error'))
printHtmlPart(14)
invokeTag('message','g',77,['code':("adcontent.url.label"),'default':("URL")],-1)
printHtmlPart(9)
invokeTag('textArea','g',83,['style':("min-height: 100px"),'class':("form-control"),'name':("url"),'required':(""),'value':(objInstance?.url)],-1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1435115992856L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
