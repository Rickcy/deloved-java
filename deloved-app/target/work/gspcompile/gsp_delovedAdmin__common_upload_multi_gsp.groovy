import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin__common_upload_multi_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/_common/_upload-multi.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
createTagBody(1, {->
printHtmlPart(0)
if(true && (!deleteOnly)) {
printHtmlPart(1)
}
printHtmlPart(2)
for( att in (attachList) ) {
printHtmlPart(3)
expressionOut.print(createLink([action: 'download', id: att.attachment.id, params: [name: att.attachment.name, preview: '']]))
printHtmlPart(4)
expressionOut.print(att.attachment.name)
printHtmlPart(5)
if(true && (!att.attachment.isImage())) {
printHtmlPart(6)
expressionOut.print(att.attachment.name)
printHtmlPart(5)
}
else {
printHtmlPart(7)
}
printHtmlPart(8)
if(true && (att.attachment.isImage())) {
printHtmlPart(9)
expressionOut.print(createLink([action: 'thumb', id: att.attachment.id, params: [name: att.attachment.name]]))
printHtmlPart(10)
}
else {
printHtmlPart(11)
invokeTag('image','asset',44,['src':("fileupload/${att.attachment.getIcon()}")],-1)
printHtmlPart(12)
}
printHtmlPart(13)
expressionOut.print(createLink([action: 'download', id: att.attachment.id, params: [name: att.attachment.name]]))
printHtmlPart(4)
expressionOut.print(att.attachment.name)
printHtmlPart(14)
expressionOut.print(att.attachment.name)
printHtmlPart(10)
expressionOut.print(att.attachment.name)
printHtmlPart(15)
expressionOut.print(att.attachment.size)
printHtmlPart(16)
expressionOut.print(createLink([action: 'deleteatt', id: att.attachment.id, params: [name: att.attachment.name]]))
printHtmlPart(17)
}
printHtmlPart(18)
})
invokeTag('form','g',117,['id':("fileupload"),'enctype':("multipart/form-data"),'method':("POST"),'url':([resource: objInstance, action: 'upload'])],1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1464780720202L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
