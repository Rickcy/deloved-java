import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin__common_upload_multi_image_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/_common/_upload-multi-image.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
for( att in (attachList) ) {
printHtmlPart(1)
expressionOut.print(att.id)
printHtmlPart(2)
expressionOut.print(createLink([action: 'image', id: att.id, params: [name: att.image.file, type: 'main']]))
printHtmlPart(3)
expressionOut.print(att.id)
printHtmlPart(4)
expressionOut.print(createLink([action: 'image', id: att.id, params: [name: att.imageThumb.file]]))
printHtmlPart(5)
expressionOut.print(att.id)
printHtmlPart(6)
expressionOut.print(att.id)
printHtmlPart(7)
expressionOut.print(att.id)
printHtmlPart(8)
expressionOut.print(createLink([action: 'crop', id: att.id]))
printHtmlPart(9)
expressionOut.print(att.id)
printHtmlPart(10)
}
printHtmlPart(11)
if(true && (!deleteOnly)) {
printHtmlPart(12)
}
printHtmlPart(13)
expressionOut.print(createLink(controller: 'item', action: 'deleteimage'))
printHtmlPart(14)
expressionOut.print(createLink(controller: 'item', action: 'upload'))
printHtmlPart(15)
expressionOut.print(objInstance?.id ? 'id: ' + objInstance.id  : null)
printHtmlPart(16)
expressionOut.print(createLink(controller: 'item', action: 'crop'))
printHtmlPart(17)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1448526284466L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
