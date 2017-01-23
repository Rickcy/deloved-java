import ru.deloved.Deal
import ru.deloved.DealStatus
import ru.deloved.ConsultCategory
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_dealthread2_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/deal/thread2.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',7,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("admin")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(2)
invokeTag('javascript','asset',9,['src':("/dropzonejs/dropzone.min.js")],-1)
printHtmlPart(2)
invokeTag('javascript','asset',10,['src':("/mCustomScrollbar/jquery.mCustomScrollbar.concat.min.js")],-1)
printHtmlPart(2)
invokeTag('link','asset',11,['rel':("stylesheet"),'href':("/mCustomScrollbar/jquery.mCustomScrollbar.min.css")],-1)
printHtmlPart(2)
invokeTag('link','asset',12,['rel':("buttons"),'href':("/mCustomScrollbar/mCSB_buttons.png")],-1)
printHtmlPart(2)
invokeTag('javascript','asset',13,['src':("/moment.js")],-1)
printHtmlPart(0)
})
invokeTag('captureHead','sitemesh',14,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(0)
if(true && (freeAccount)) {
printHtmlPart(2)
invokeTag('render','g',17,['template':("/_common/promo")],-1)
printHtmlPart(0)
}
else {
printHtmlPart(4)
invokeTag('render','g',22,['template':("/_common/flash-message")],-1)
printHtmlPart(5)
invokeTag('render','g',26,['template':("deal-partners"),'model':([
				dealInstance: dealInstance,
		])],-1)
printHtmlPart(5)
invokeTag('render','g',33,['template':("/_common/thread"),'model':([
				threadPosts         : posts,
				threadAccount       : myAccount ?: dealInstance.account,
				threadStatusPrefix  : 'deal.status',
				threadStatusTemplate: 'thread-status'
		])],-1)
printHtmlPart(6)
expressionOut.print(showReviewAlert ? 'block' : 'none')
printHtmlPart(7)
expressionOut.print(createLink(controller: 'review', action: 'create', id: dealInstance.id))
printHtmlPart(8)
expressionOut.print([DealStatus.Confirmed.value(), DealStatus.Failed.value()].contains(dealInstance.status) ? '' : 'disabled')
printHtmlPart(9)
invokeTag('render','g',45,['template':("deal-progress"),'model':([
					dealInstance: dealInstance
			])],-1)
printHtmlPart(10)
createTagBody(3, {->
printHtmlPart(11)
invokeTag('render','g',52,['template':("change-status"),'model':([
						statusList: statusList,
						objInstance: dealInstance
				])],-1)
printHtmlPart(12)
invokeTag('hiddenField','g',55,['name':("version"),'value':(dealInstance?.version)],-1)
printHtmlPart(13)
invokeTag('textArea','g',56,['id':("messageText"),'class':("form-control"),'name':("post"),'rows':("4"),'placeholder':("Ваше сообщение")],-1)
printHtmlPart(14)
expressionOut.print(message(code: 'deal.send.label'))
printHtmlPart(15)
expressionOut.print(createLink(controller: 'review', action: 'create', id: dealInstance.id))
printHtmlPart(16)
expressionOut.print([DealStatus.Confirmed.value(), DealStatus.Failed.value()].contains(dealInstance.status) ? '' : 'disabled')
printHtmlPart(17)
expressionOut.print((dealInstance.status < DealStatus.SignUP.value()) ? 'disabled' : '')
printHtmlPart(18)
expressionOut.print(createLink(controller: 'dispute', action: 'create', params: [partner:dealInstance.account.id]))
printHtmlPart(19)
expressionOut.print((dealInstance.status <  DealStatus.SignUP.value()) ? 'disabled' : '')
printHtmlPart(18)
expressionOut.print(createLink(controller: 'claim', action: 'create', params: [partner: dealInstance.account.id]))
printHtmlPart(20)
})
invokeTag('ifAnyGranted','sec',87,['roles':("ROLE_ACCOUNT")],3)
printHtmlPart(21)
invokeTag('render','g',90,['template':("/_common/gallery-multi")],-1)
printHtmlPart(22)
invokeTag('render','g',91,['template':("scripts"),'model':([dealInstance: dealInstance])],-1)
printHtmlPart(23)
}
printHtmlPart(0)
})
invokeTag('captureBody','sitemesh',94,[:],1)
printHtmlPart(24)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1476263962353L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
