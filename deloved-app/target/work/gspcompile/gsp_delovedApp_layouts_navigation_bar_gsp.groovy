import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_delovedApp_layouts_navigation_bar_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/_navigation-bar.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
createClosureForHtmlPart(0, 1)
invokeTag('set','g',1,['var':("section")],1)
printHtmlPart(1)
if(true && (params.controller in ['companies', 'company'])) {
printHtmlPart(2)
createClosureForHtmlPart(0, 2)
invokeTag('set','g',3,['var':("section")],2)
printHtmlPart(1)
}
else if(true && (params.controller in ['goods'])) {
printHtmlPart(2)
createClosureForHtmlPart(3, 2)
invokeTag('set','g',6,['var':("section")],2)
printHtmlPart(1)
}
else if(true && (params.controller in ['services'])) {
printHtmlPart(2)
createClosureForHtmlPart(4, 2)
invokeTag('set','g',9,['var':("section")],2)
printHtmlPart(1)
}
printHtmlPart(5)
expressionOut.print(createLink(controller: 'front', action: 'index'))
printHtmlPart(6)
expressionOut.print(resource(dir: 'images', file: 'front/logo2.png'))
printHtmlPart(7)
expressionOut.print(createLink(controller: 'front', action: 'index'))
printHtmlPart(8)
expressionOut.print(createLink(controller: 'companies', action: 'index'))
printHtmlPart(9)
expressionOut.print(createLink(controller: 'goods', action: 'index'))
printHtmlPart(10)
expressionOut.print(createLink(controller: 'services', action: 'index'))
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(12)
invokeTag('form','g',33,['id':("logoutForm"),'url':([controller: 'logout']),'method':("POST")],-1)
printHtmlPart(13)
expressionOut.print(getAvatar() ?: resource(dir: 'images', file: 'admin/avatar.jpg'))
printHtmlPart(14)
invokeTag('userInfo','g',39,['what':("fio")],-1)
printHtmlPart(15)
createClosureForHtmlPart(16, 2)
invokeTag('link','g',43,['class':("info-menu"),'controller':("profile"),'action':("me")],2)
printHtmlPart(17)
createTagBody(2, {->
printHtmlPart(18)
expressionOut.print(createLink(controller: 'billing', action: 'index'))
printHtmlPart(19)
})
invokeTag('ifAnyGranted','sec',47,['roles':("ROLE_ACCOUNT")],2)
printHtmlPart(20)
})
invokeTag('ifLoggedIn','sec',67,[:],1)
printHtmlPart(21)
createTagBody(1, {->
printHtmlPart(22)
expressionOut.print(createLink(controller: 'login', action: 'auth'))
printHtmlPart(23)
expressionOut.print(createLink(controller: 'signup', action: 'index'))
printHtmlPart(24)
})
invokeTag('ifNotLoggedIn','sec',75,[:],1)
printHtmlPart(25)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1477908817128L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
