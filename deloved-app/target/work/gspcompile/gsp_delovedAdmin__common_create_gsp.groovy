import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin__common_create_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/_common/_create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('render','g',1,['template':("/_common/flash-message")],-1)
printHtmlPart(0)
createClosureForHtmlPart(1, 1)
invokeTag('hasErrors','g',7,['bean':(objInstance)],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(2)
for( _it32225037 in (hiddenExtra) ) {
changeItVariable(_it32225037)
printHtmlPart(3)
invokeTag('hiddenField','g',9,['name':(it.key),'value':(it.value)],-1)
printHtmlPart(2)
}
printHtmlPart(4)
invokeTag('render','g',13,['template':(formTemplate ?: 'form')],-1)
printHtmlPart(5)
invokeTag('submitButton','g',20,['name':("create"),'class':("btn btn-success asd"),'value':(message(code: 'default.button.create.label'))],-1)
printHtmlPart(3)
createTagBody(2, {->
invokeTag('message','g',22,['code':("default.cancel")],-1)
})
invokeTag('link','g',22,['class':("btn btn-default"),'mapping':(cancelMapping),'controller':("item"),'action':("index")],2)
printHtmlPart(6)
})
invokeTag('form','g',24,['name':("editForm"),'url':([action: 'save', params: formParams, mapping: formMappings]),'class':("form-horizontal")],1)
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1473928700720L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
