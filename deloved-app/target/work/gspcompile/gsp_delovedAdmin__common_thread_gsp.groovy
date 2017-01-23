import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin__common_thread_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/_common/_thread.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
createTagBody(1, {->
printHtmlPart(0)
invokeTag('render','g',11,['template':("/_common/thread-post"),'model':([
			threadPosts         : posts,
			threadAccount       : myAccount ?: dealInstance.account,
			threadStatusPrefix  : 'deal.status',
			threadStatusTemplate: 'thread-status'
	])],-1)
printHtmlPart(1)
})
invokeTag('ifAnyGranted','sec',19,['roles':("ROLE_ACCOUNT")],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('render','g',29,['template':("/_common/thread-post"),'model':([
					threadPosts       : posts,
					threadAccount     : myAccount ?: ticketInstance.account,
					threadStatusPrefix: 'ticket.status'
			])],-1)
printHtmlPart(4)
})
invokeTag('ifAnyGranted','sec',38,['roles':("ROLE_MANAGER")],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(6)
invokeTag('render','g',47,['template':("/_common/thread-post"),'model':([
					threadPosts       : posts,
					threadAccount     : myAccount ?: disputeInstance.account,
					threadStatusPrefix: 'dispute.status'
			])],-1)
printHtmlPart(7)
})
invokeTag('ifAnyGranted','sec',56,['roles':("ROLE_ADMIN")],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(6)
invokeTag('render','g',65,['template':("/_common/thread-post"),'model':([
					threadPosts       : posts,
					threadAccount     : myAccount ?: consultInstance.account,
					threadStatusPrefix: 'consult.status'
			])],-1)
printHtmlPart(8)
})
invokeTag('ifAnyGranted','sec',74,['roles':("ROLE_JURIST")],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(9)
invokeTag('render','g',83,['template':("/_common/thread-post"),'model':([
					threadPosts       : posts,
					threadAccount     : myAccount ?: disputeInstance.account,
					threadStatusPrefix: 'dispute.status'
			])],-1)
printHtmlPart(10)
})
invokeTag('ifAnyGranted','sec',93,['roles':("ROLE_MEDIATOR")],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(11)
invokeTag('render','g',102,['template':("/_common/thread-post"),'model':([
					threadPosts       : posts,
					threadAccount     : myAccount ?: claimInstance.account,
					threadStatusPrefix: 'claim.status'
			])],-1)
printHtmlPart(12)
})
invokeTag('ifAnyGranted','sec',111,['roles':("ROLE_JUDGE")],1)
printHtmlPart(13)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1470048261044L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
