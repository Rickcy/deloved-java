import ru.deloved.ReviewValue
import  ru.deloved.Review
import ru.deloved.Deal
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_review_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/review/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
expressionOut.print(objInstance.published ? 'Да' : 'Нет')
printHtmlPart(3)
})
invokeTag('ifAnyGranted','sec',16,['roles':("ROLE_ACCOUNT")],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
expressionOut.print(hasErrors(bean: objInstance, field: 'published', 'error'))
printHtmlPart(6)
if(true && (objInstance?.published == true)) {
printHtmlPart(7)
}
printHtmlPart(8)
invokeTag('radio','g',26,['name':("published"),'value':("true"),'checked':(objInstance?.published == true)],-1)
printHtmlPart(9)
if(true && (objInstance?.published == false)) {
printHtmlPart(7)
}
printHtmlPart(8)
invokeTag('radio','g',29,['name':("published"),'value':("false"),'checked':(objInstance?.published == false)],-1)
printHtmlPart(10)
invokeTag('render','g',32,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'published'])],-1)
printHtmlPart(11)
})
invokeTag('ifAnyGranted','sec',35,['roles':("ROLE_ADMIN,ROLE_MANAGER")],1)
printHtmlPart(12)
expressionOut.print(createLink(controller: 'company', action: 'index', id: objInstance?.to.id))
printHtmlPart(13)
expressionOut.print(objInstance?.to.orgForm.code + ' \"'+objInstance?.to.name + '\"')
printHtmlPart(14)
expressionOut.print(createLink(controller: 'deal', action: 'thread', id: objInstance?.deal.id))
printHtmlPart(13)
expressionOut.print(objInstance?.deal.id)
printHtmlPart(15)
createTagBody(1, {->
printHtmlPart(5)
expressionOut.print(hasErrors(bean: objInstance, field: 'value', 'error'))
printHtmlPart(16)
expressionOut.print(ReviewValue.Good.value())
printHtmlPart(17)
expressionOut.print(objInstance?.value == ReviewValue.Good.value() ? 'checked' : '')
printHtmlPart(18)
expressionOut.print(ReviewValue.Neutral.value())
printHtmlPart(17)
expressionOut.print(objInstance?.value == ReviewValue.Neutral.value() ? 'checked' : '')
printHtmlPart(19)
expressionOut.print(ReviewValue.Bad.value())
printHtmlPart(17)
expressionOut.print(objInstance?.value == ReviewValue.Bad.value() ? 'checked' : '')
printHtmlPart(20)
invokeTag('render','g',92,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'value'])],-1)
printHtmlPart(21)
})
invokeTag('ifAnyGranted','sec',96,['roles':("ROLE_ACCOUNT")],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(22)
if(true && (objInstance.value == 1)) {
printHtmlPart(23)
}
printHtmlPart(24)
if(true && (objInstance.value == 0)) {
printHtmlPart(25)
}
printHtmlPart(24)
if(true && (objInstance.value == -1)) {
printHtmlPart(26)
}
printHtmlPart(21)
})
invokeTag('ifAnyGranted','sec',116,['roles':("ROLE_ADMIN,ROLE_MANAGER")],1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(5)
expressionOut.print(hasErrors(bean: objInstance, field: 'content', 'error'))
printHtmlPart(27)
invokeTag('textArea','g',125,['id':("content"),'style':("min-height: 200px; resize: none"),'class':("form-control"),'name':("content"),'value':(objInstance?.content)],-1)
printHtmlPart(24)
invokeTag('render','g',126,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'content'])],-1)
printHtmlPart(28)
})
invokeTag('ifAnyGranted','sec',130,['roles':("ROLE_ACCOUNT")],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(29)
expressionOut.print(objInstance.content)
printHtmlPart(30)
})
invokeTag('ifAnyGranted','sec',144,['roles':("ROLE_ADMIN,ROLE_MANAGER")],1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(31)
if(true && (objInstance.remark)) {
printHtmlPart(32)
expressionOut.print(objInstance.remark)
printHtmlPart(33)
}
printHtmlPart(0)
})
invokeTag('ifAnyGranted','sec',161,['roles':("ROLE_ACCOUNT")],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
expressionOut.print(hasErrors(bean: objInstance, field: 'content', 'remark'))
printHtmlPart(34)
invokeTag('textArea','g',169,['id':("remark"),'style':("min-height: 200px; resize: none"),'class':("form-control"),'name':("remark"),'value':(objInstance?.remark)],-1)
printHtmlPart(24)
invokeTag('render','g',170,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'remark'])],-1)
printHtmlPart(35)
})
invokeTag('ifAnyGranted','sec',174,['roles':("ROLE_ADMIN, ROLE_MANAGER")],1)
printHtmlPart(0)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1457671708083L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
