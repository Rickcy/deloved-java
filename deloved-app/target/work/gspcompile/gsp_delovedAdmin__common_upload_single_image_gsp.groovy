import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin__common_upload_single_image_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/_common/_upload-single-image.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(imgHeight)
printHtmlPart(1)
expressionOut.print(imgWidth)
printHtmlPart(2)
if(true && (isUpload)) {
printHtmlPart(3)
expressionOut.print(imageUrl)
printHtmlPart(4)
expressionOut.print(imgId)
printHtmlPart(5)
expressionOut.print(imgTitle)
printHtmlPart(6)
expressionOut.print(imgWidth)
printHtmlPart(7)
expressionOut.print(imgHeight)
printHtmlPart(8)
expressionOut.print(thumbUrl)
printHtmlPart(9)
}
else {
printHtmlPart(10)
expressionOut.print(imgWidth)
printHtmlPart(7)
expressionOut.print(imgHeight)
printHtmlPart(8)
expressionOut.print(resource(dir: 'image', file: '/admin/avatar.jpg'))
printHtmlPart(11)
}
printHtmlPart(12)
expressionOut.print(uploadAction)
printHtmlPart(13)
expressionOut.print(isUpload ? 'block' : 'none')
printHtmlPart(14)
expressionOut.print(remoteFunction(action: deleteAction, id: params.id, onSuccess: 'onImageDelete()'))
printHtmlPart(15)
expressionOut.print(isUpload ? 'block' : 'none')
printHtmlPart(16)
expressionOut.print(imgId)
printHtmlPart(17)
expressionOut.print(cropAction)
printHtmlPart(18)
expressionOut.print(imgId)
printHtmlPart(19)
expressionOut.print(imgTitle)
printHtmlPart(20)
expressionOut.print(imgWidth)
printHtmlPart(21)
expressionOut.print(imgHeight)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444800624719L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
