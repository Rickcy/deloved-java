import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_delovedApp_layouts_create_suggestion_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/_create-suggestion.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(2)
expressionOut.print(createLink([controller: 'suggestion', action: 'getSuggestionCategories']))
printHtmlPart(3)
createClosureForHtmlPart(4, 3)
invokeTag('formRemote','g',91,['id':("suggestionForm"),'name':("createSuggestion"),'url':([controller: 'suggestion', action: 'create']),'after':("afterSubmitSug()")],3)
printHtmlPart(5)
})
invokeTag('ifNotGranted','sec',102,['roles':("ROLE_NONE")],2)
printHtmlPart(1)
})
invokeTag('ifLoggedIn','sec',103,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(6)
createClosureForHtmlPart(7, 2)
invokeTag('link','g',110,['controller':("login"),'action':("auth"),'data-toggle':("modal"),'data-remote':(createLink(controller:'login' , action: 'auth')),'data-target':("#myModal")],2)
printHtmlPart(1)
})
invokeTag('ifNotLoggedIn','sec',1,[:],1)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1484817900417L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
