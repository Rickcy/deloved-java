import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_suggestionindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/suggestion/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("admin")],-1)
printHtmlPart(3)
createTagBody(2, {->
createClosureForHtmlPart(4, 3)
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(5)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(0)
createTagBody(2, {->
printHtmlPart(5)
createTagBody(3, {->
printHtmlPart(6)
for( cat in (sugCategories) ) {
printHtmlPart(7)
expressionOut.print(cat.id)
printHtmlPart(8)
expressionOut.print(cat.name)
printHtmlPart(9)
}
printHtmlPart(10)
invokeTag('datePicker','g',27,['id':("selDateFrom"),'name':("from"),'precision':("day")],-1)
printHtmlPart(11)
invokeTag('datePicker','g',28,['id':("selDateTill"),'name':("till"),'precision':("day")],-1)
printHtmlPart(12)
createClosureForHtmlPart(13, 4)
invokeTag('link','g',52,['class':("btn btn-default"),'controller':("suggestion"),'action':("index")],4)
printHtmlPart(14)
})
invokeTag('form','g',55,['style':("text-align: justify"),'url':([controller: 'suggestion', action: 'index'])],3)
printHtmlPart(5)
createTagBody(3, {->
printHtmlPart(0)
for( suggestion in (suggestions) ) {
printHtmlPart(15)
expressionOut.print(suggestion?.category?.name ?: 'Без категории')
printHtmlPart(16)
invokeTag('formatDate','g',68,['date':(suggestion?.datePublished),'format':("dd-MM-yyyy HH:mm")],-1)
printHtmlPart(17)
invokeTag('checkBox','g',70,['name':("checkSuggestion"),'value':(suggestion.id),'checked':("false")],-1)
printHtmlPart(18)
expressionOut.print(suggestion.title)
printHtmlPart(19)
expressionOut.print(suggestion.content)
printHtmlPart(20)
expressionOut.print(suggestion.author?.fio)
printHtmlPart(21)
expressionOut.print(suggestion.author.id)
printHtmlPart(22)
}
printHtmlPart(0)
})
invokeTag('form','g',91,['id':("suggestionAction"),'name':("suggestionAction"),'url':([controller: 'suggestion', action: 'delete'])],3)
printHtmlPart(23)
invokeTag('paginate','g',93,['total':(sugTotal)],-1)
printHtmlPart(24)
})
invokeTag('ifAnyGranted','sec',94,['roles':("ROLE_ADMIN, ROLE_MANAGER")],2)
printHtmlPart(0)
})
invokeTag('captureBody','sitemesh',95,[:],1)
printHtmlPart(25)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1484118174850L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
