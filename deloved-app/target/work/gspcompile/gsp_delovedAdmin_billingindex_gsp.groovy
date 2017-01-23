import ru.deloved.Account
import ru.deloved.Profile
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_billingindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/billing/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("admin")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(0)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('message','g',9,['code':("billing.list.label")],-1)
printHtmlPart(4)
invokeTag('render','g',10,['template':("/_common/flash-message")],-1)
printHtmlPart(5)
expressionOut.print(freeAccount)
printHtmlPart(6)
if(true && (freeAccount)) {
printHtmlPart(7)
}
printHtmlPart(0)
if(true && (profile.dateCreated.dateString==profile.getChargeTill().minus(31).dateString)) {
printHtmlPart(8)
}
printHtmlPart(9)
if(true && (profile.chargeTill.date - new Date().date<=4&&profile.chargeTill.month-new Date().month==0&&profile.chargeTill.year-new Date().year==0)) {
printHtmlPart(10)
invokeTag('formatDate','g',47,['date':(profile.chargeTill),'format':("dd.MM.yyyy")],-1)
printHtmlPart(11)
}
printHtmlPart(0)
if(true && (keeper)) {
printHtmlPart(12)
invokeTag('render','g',56,['template':("addfunds"),'model':([
				tariffs: tariffs,
				methods: methods
		])],-1)
printHtmlPart(13)
invokeTag('render','g',63,['template':("history"),'model':([
				requestInstanceList: requestInstanceList,
				requestInstanceTotal: requestInstanceTotal
		])],-1)
printHtmlPart(14)
}
else {
printHtmlPart(1)
createTagBody(3, {->
printHtmlPart(15)
invokeTag('submitButton','g',71,['name':("create"),'class':("btn btn-primary ft"),'value':("Создать счет")],-1)
printHtmlPart(1)
})
invokeTag('form','g',72,['url':([action: 'account']),'method':("POST")],3)
printHtmlPart(0)
}
printHtmlPart(9)
})
invokeTag('captureBody','sitemesh',75,[:],1)
printHtmlPart(16)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1478754445397L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
