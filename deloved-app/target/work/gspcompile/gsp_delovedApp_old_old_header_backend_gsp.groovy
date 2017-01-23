import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_delovedApp_old_old_header_backend_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/old/_old-header-backend.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
expressionOut.print(resource(dir: 'images', file: 'admin/logo.png'))
printHtmlPart(2)
})
invokeTag('link','g',3,['controller':("front")],1)
printHtmlPart(3)
if(true && (userInfo(what: 'avatarThumb'))) {
printHtmlPart(4)
}
else {
printHtmlPart(5)
}
printHtmlPart(6)
createClosureForHtmlPart(7, 1)
invokeTag('link','g',46,['class':("btn btn-default"),'controller':("profile"),'action':("edit"),'style':("height: 40px; vertical-align: middle")],1)
printHtmlPart(8)
createTagBody(1, {->
printHtmlPart(9)
createClosureForHtmlPart(10, 2)
invokeTag('link','g',68,['controller':("profile"),'style':("text-decoration:underline"),'action':("edit")],2)
printHtmlPart(11)
createTagBody(2, {->
expressionOut.print(userInfo(what: 'fio') ?: userInfo(what: 'username'))
})
invokeTag('link','g',69,['controller':("profile"),'style':("text-decoration:underline"),'action':("me")],2)
printHtmlPart(12)
invokeTag('form','g',71,['id':("logoutForm"),'url':([controller: 'logout']),'method':("POST")],-1)
printHtmlPart(13)
})
invokeTag('ifLoggedIn','sec',75,[:],1)
printHtmlPart(14)
createTagBody(1, {->
printHtmlPart(15)
createClosureForHtmlPart(16, 2)
invokeTag('link','g',78,['controller':("login"),'action':("auth")],2)
printHtmlPart(17)
})
invokeTag('ifNotLoggedIn','sec',79,[:],1)
printHtmlPart(18)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444124074519L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
