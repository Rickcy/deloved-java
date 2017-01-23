import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin__common_edit2_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/_common/_edit2.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('render','g',1,['template':("/_common/flash-message")],-1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(2)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(3)
expressionOut.print(error.field)
printHtmlPart(4)
}
printHtmlPart(5)
invokeTag('message','g',6,['error':(error)],-1)
printHtmlPart(6)
})
invokeTag('eachError','g',7,['bean':(objInstance),'var':("error")],2)
printHtmlPart(7)
})
invokeTag('hasErrors','g',9,['bean':(objInstance)],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(8)
invokeTag('hiddenField','g',12,['name':("version"),'value':(beanResource?.version)],-1)
printHtmlPart(9)
invokeTag('render','g',14,['template':(formTemplate ?: 'form2')],-1)
printHtmlPart(10)
invokeTag('actionSubmit','g',17,['class':("btn  btn-success"),'action':("update"),'value':(message(code: 'default.button.update.label'))],-1)
printHtmlPart(11)
createTagBody(2, {->
invokeTag('message','g',18,['code':("default.cancel")],-1)
})
invokeTag('link','g',18,['class':("btn btn-default"),'mapping':(cancelMapping),'action':("index")],2)
printHtmlPart(12)
})
invokeTag('form','g',20,['name':("editForm"),'url':([resource: beanResource, action: 'update']),'method':("PUT"),'class':("form-horizontal")],1)
printHtmlPart(13)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1473929264941L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
