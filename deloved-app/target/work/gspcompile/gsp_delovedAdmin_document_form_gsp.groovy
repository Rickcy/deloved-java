import ru.deloved.DocumentCategory
import  ru.deloved.Document
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_document_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/document/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: objInstance, field: 'name', 'error'))
printHtmlPart(1)
invokeTag('message','g',5,['code':("document.name.label"),'default':("Name")],-1)
printHtmlPart(2)
invokeTag('textField','g',10,['name':("name"),'class':("form-control"),'value':(objInstance?.name)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: objInstance, field: 'description', 'error'))
printHtmlPart(4)
invokeTag('message','g',16,['code':("document.description.label"),'default':("Description")],-1)
printHtmlPart(5)
invokeTag('textArea','g',20,['class':("form-control"),'name':("description"),'value':(objInstance?.description),'rows':("10"),'maxlength':("1500")],-1)
printHtmlPart(6)
expressionOut.print(hasErrors(bean: objInstance, field: 'category', 'error'))
printHtmlPart(7)
invokeTag('message','g',26,['code':("document.type.label"),'default':("Type")],-1)
printHtmlPart(8)
invokeTag('select','g',37,['id':("category"),'class':("form-control"),'name':("category.id"),'from':(DocumentCategory.list()),'optionKey':("id"),'optionValue':("name"),'value':(objInstance?.category?.id),'noSelection':(['': '-'])],-1)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1435115993068L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
