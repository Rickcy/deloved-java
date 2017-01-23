import ru.deloved.Account
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_companies_item_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/companies/_item.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (accountInstance.logoThumb)) {
printHtmlPart(1)
expressionOut.print(createLink(controller: 'files', action: 'logo', id: accountInstance.logoThumb?.id, params: [name: accountInstance.logoThumb?.file]))
printHtmlPart(2)
}
else {
printHtmlPart(3)
expressionOut.print(resource(dir: 'images', file: 'front/logo_uch.png'))
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (accountInstance?.name)) {
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(7)
invokeTag('fieldValue','g',26,['bean':(accountInstance),'field':("name")],-1)
printHtmlPart(6)
})
invokeTag('link','g',27,['class':("name"),'url':([controller: 'company', id: accountInstance.id])],2)
printHtmlPart(8)
}
printHtmlPart(9)
if(true && (accountInstance?.description)) {
printHtmlPart(10)
expressionOut.print(truncate(max: 200, words: true, value: accountInstance.description))
printHtmlPart(11)
}
printHtmlPart(12)
createTagBody(1, {->
printHtmlPart(13)
expressionOut.print(accountInstance.rating ? accountInstance.rating + '%' : '-')
printHtmlPart(14)
invokeTag('rating','g',40,['value':(accountInstance.rating)],-1)
printHtmlPart(15)
if(true && (reviewsCount > 0)) {
printHtmlPart(16)
createTagBody(3, {->
printHtmlPart(17)
expressionOut.print(reviewsCount)
printHtmlPart(18)
})
invokeTag('link','g',48,['class':("otz"),'action':("reviews"),'data-toggle':("modal"),'data-remote':(createLink(id: accountInstance.id, action: 'reviews')),'data-target':("#reviewsModal")],3)
printHtmlPart(19)
}
else {
printHtmlPart(20)
}
printHtmlPart(7)
invokeTag('render','g',57,['template':("/_common/modal"),'model':([
								container: 'reviewsContainer',
								modalId  : 'reviewsModal'
						])],-1)
printHtmlPart(21)
})
invokeTag('ifLoggedIn','sec',59,[:],1)
printHtmlPart(8)
createTagBody(1, {->
printHtmlPart(22)
createClosureForHtmlPart(23, 2)
invokeTag('link','g',68,['class':("otz"),'controller':("login"),'action':("auth"),'data-toggle':("modal"),'data-remote':(createLink(controller:'login' , action: 'auth')),'data-target':("#myModal")],2)
printHtmlPart(24)
})
invokeTag('ifNotLoggedIn','sec',75,[:],1)
printHtmlPart(25)
createClosureForHtmlPart(26, 1)
invokeTag('link','g',83,['class':("podr"),'url':([controller: 'company', id: accountInstance.id])],1)
printHtmlPart(27)
expressionOut.print(resource(dir: 'images', file: 'front/local.png'))
printHtmlPart(28)
expressionOut.print(accountInstance?.city?.name)
printHtmlPart(29)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1484817933965L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
