import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_accounteditform_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/account/editform.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',3,['code':("account.edit.label")],-1)
printHtmlPart(1)
invokeTag('render','g',8,['template':("/_common/flash-message")],-1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
createTagBody(2, {->
printHtmlPart(4)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(5)
expressionOut.print(error.field)
printHtmlPart(6)
}
printHtmlPart(7)
invokeTag('message','g',13,['error':(error)],-1)
printHtmlPart(8)
})
invokeTag('eachError','g',14,['bean':(objInstance),'var':("error")],2)
printHtmlPart(9)
if(true && (objInstance?.hasProperty('affiliates'))) {
printHtmlPart(10)
loop:{
int i = 0
for( affInstance in (objInstance.affiliates) ) {
printHtmlPart(11)
createTagBody(4, {->
printHtmlPart(12)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(5)
expressionOut.print(error.field)
printHtmlPart(6)
}
printHtmlPart(7)
invokeTag('message','g',19,['error':(error)],-1)
printHtmlPart(13)
})
invokeTag('eachError','g',20,['bean':(affInstance),'var':("error")],4)
printHtmlPart(10)
i++
}
}
printHtmlPart(14)
}
printHtmlPart(15)
})
invokeTag('hasErrors','g',25,['bean':(objInstance)],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(16)
invokeTag('hiddenField','g',28,['name':("_method"),'value':("PUT")],-1)
printHtmlPart(16)
invokeTag('hiddenField','g',29,['name':("version"),'value':(beanResource?.version)],-1)
printHtmlPart(17)
invokeTag('render','g',31,['template':("form")],-1)
printHtmlPart(18)
})
invokeTag('formRemote','g',33,['name':("editForm"),'update':("myModalContent"),'url':([resource: beanResource, action: 'update']),'class':("form-horizontal")],1)
printHtmlPart(19)
expressionOut.print(message(code: 'default.button.close.label'))
printHtmlPart(20)
expressionOut.print(message(code: 'default.button.update.label'))
printHtmlPart(21)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1435115992824L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
