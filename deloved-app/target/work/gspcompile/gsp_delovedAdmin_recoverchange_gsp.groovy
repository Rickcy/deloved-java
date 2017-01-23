import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_recoverchange_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/recover/change.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("front")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(1)
invokeTag('javascript','asset',7,['src':("application.js")],-1)
printHtmlPart(2)
invokeTag('javascript','asset',9,['src':("/mCustomScrollbar/jquery.mCustomScrollbar.concat.min.js")],-1)
printHtmlPart(1)
invokeTag('link','asset',10,['rel':("stylesheet"),'href':("/mCustomScrollbar/jquery.mCustomScrollbar.min.css")],-1)
printHtmlPart(1)
invokeTag('link','asset',11,['rel':("buttons"),'href':("/mCustomScrollbar/mCSB_buttons.png")],-1)
printHtmlPart(1)
invokeTag('link','asset',12,['rel':("stylesheet"),'href':("/main.css")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',13,['src':("front.js")],-1)
printHtmlPart(4)
createClosureForHtmlPart(5, 2)
invokeTag('link','g',28,['controller':("front"),'action':("news"),'params':([var:val])],2)
printHtmlPart(6)
})
invokeTag('captureHead','sitemesh',51,[:],1)
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(8)
invokeTag('render','g',57,['template':("/_common/flash-message")],-1)
printHtmlPart(9)
expressionOut.print(resource(dir: 'images', file: 'front/dea.png'))
printHtmlPart(10)
expressionOut.print(resource(dir: 'images', file: 'front/rating_ultra.png'))
printHtmlPart(11)
expressionOut.print(resource(dir: 'images', file: 'front/sud_ultra.png'))
printHtmlPart(12)
expressionOut.print(resource(dir: 'images', file: 'front/hammer.png'))
printHtmlPart(13)
expressionOut.print(resource(dir: 'images', file: 'front/mediation_ultra.png'))
printHtmlPart(14)
expressionOut.print(resource(dir: 'images', file: 'front/deal_online.jpg'))
printHtmlPart(15)
expressionOut.print(resource(dir: 'images', file: 'front/rating.jpg'))
printHtmlPart(16)
expressionOut.print(resource(dir: 'images', file: 'front/jurist.jpg'))
printHtmlPart(17)
expressionOut.print(resource(dir: 'images', file: 'front/sud.jpg'))
printHtmlPart(18)
expressionOut.print(resource(dir: 'images', file: 'front/mediation.jpg'))
printHtmlPart(19)
invokeTag('render','g',115,['template':("/_common/flash-message")],-1)
printHtmlPart(20)
createTagBody(2, {->
printHtmlPart(21)
createTagBody(3, {->
printHtmlPart(22)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(23)
expressionOut.print(error.field)
printHtmlPart(24)
}
printHtmlPart(25)
})
invokeTag('eachError','g',121,['bean':(objInstance),'var':("error")],3)
printHtmlPart(26)
})
invokeTag('hasErrors','g',123,['bean':(objInstance)],2)
printHtmlPart(27)
createTagBody(2, {->
printHtmlPart(28)
if(true && (!code)) {
printHtmlPart(29)
expressionOut.print(code)
printHtmlPart(30)
}
else {
printHtmlPart(31)
invokeTag('hiddenField','g',136,['id':("code"),'name':("code"),'value':(code),'required':("")],-1)
printHtmlPart(32)
}
printHtmlPart(33)
})
invokeTag('form','g',148,['name':("recoverForm"),'class':("form-signin"),'url':([controller: 'recover', action: 'update']),'method':("POST"),'autocomplete':("on"),'style':("padding: 10px; margin: 10px")],2)
printHtmlPart(34)
})
invokeTag('captureBody','sitemesh',157,[:],1)
printHtmlPart(35)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1470886635404L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
