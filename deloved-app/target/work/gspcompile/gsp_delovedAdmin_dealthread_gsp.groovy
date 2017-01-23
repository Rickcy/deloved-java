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
class gsp_delovedAdmin_dealthread_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/deal/thread.gsp" }
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
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',53,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(2)
if(true && (freeAccount)) {
printHtmlPart(5)
invokeTag('render','g',56,['template':("/_common/promo")],-1)
printHtmlPart(2)
}
else {
printHtmlPart(6)
invokeTag('render','g',61,['template':("/_common/flash-message")],-1)
printHtmlPart(7)
invokeTag('render','g',65,['template':("deal-partners"),'model':([
		        dealInstance: dealInstance,
		])],-1)
printHtmlPart(0)
createTagBody(3, {->
printHtmlPart(2)
invokeTag('render','g',73,['template':("/_common/thread"),'model':([
		threadPosts         : posts,
		threadAccount       : myAccount ?: dealInstance.account,
		threadStatusPrefix  : 'deal.status',
		threadStatusTemplate: 'thread-status'

])],-1)
printHtmlPart(8)
})
invokeTag('ifAnyGranted','sec',76,['roles':("ROLE_ACCOUNT")],3)
printHtmlPart(0)
createTagBody(3, {->
printHtmlPart(9)
invokeTag('render','g',87,['template':("/_common/thread-post"),'model':([
					threadPosts         : posts,
					threadAccount       : myAccount ?: dealInstance.account,
					threadStatusPrefix  : 'deal.status',
					threadStatusTemplate: 'thread-status'
			])],-1)
printHtmlPart(10)
})
invokeTag('ifAnyGranted','sec',142,['roles':("ROLE_ADMIN,ROLE_MEDIATOR,ROLE_JUDGE,ROLE_JURIST")],3)
printHtmlPart(11)
createTagBody(3, {->
printHtmlPart(12)
expressionOut.print(showReviewAlert ? 'block' : 'none')
printHtmlPart(13)
expressionOut.print(createLink(controller: 'review', action: 'create', id: dealInstance.id))
printHtmlPart(14)
expressionOut.print([DealStatus.Confirmed.value(), DealStatus.Failed.value()].contains(dealInstance.status) ? '' : 'disabled')
printHtmlPart(15)
})
invokeTag('ifAnyGranted','sec',151,['roles':("ROLE_ACCOUNT")],3)
printHtmlPart(16)
invokeTag('render','g',155,['template':("deal-progress"),'model':([
					dealInstance: dealInstance
			])],-1)
printHtmlPart(16)
createTagBody(3, {->
printHtmlPart(17)
invokeTag('render','g',162,['template':("change-status"),'model':([
							statusList: statusList,
							objInstance: dealInstance
				])],-1)
printHtmlPart(18)
invokeTag('render','g',165,['template':("/_common/hint"),'model':([hintText: 'Удалить сообщение можно путем наведения на сообщение! Доступно в течении 5 минут после публикации.'])],-1)
printHtmlPart(19)
invokeTag('hiddenField','g',166,['name':("version"),'value':(dealInstance?.version)],-1)
printHtmlPart(20)
expressionOut.print(DealStatus.Proposed.value()==dealInstance.status ? 'disabled' :'')
printHtmlPart(21)
expressionOut.print(DealStatus.Proposed.value()==dealInstance.status?'Сделка не подтверждена':'Ваше сообщение')
printHtmlPart(22)
expressionOut.print(DealStatus.Proposed.value()==dealInstance.status ? 'disabled' :'')
printHtmlPart(23)
expressionOut.print(message(code: 'deal.send.label'))
printHtmlPart(24)
expressionOut.print(DealStatus.Proposed.value()==dealInstance.status ? 'disabled' :'')
printHtmlPart(25)
invokeTag('render','g',200,['template':("/_common/hint"),'model':([hintText: 'Отзыв, спор и иск доступны на определенных этапах сделки».   '])],-1)
printHtmlPart(26)
expressionOut.print(createLink(controller: 'review', action: 'create', id: dealInstance.id))
printHtmlPart(27)
expressionOut.print([DealStatus.Confirmed.value(), DealStatus.Failed.value()].contains(dealInstance.status) ? '' : 'disabled')
printHtmlPart(28)
if(true && (myAccount==dealInstance.account)) {
printHtmlPart(29)
expressionOut.print((dealInstance.status < DealStatus.SignUP.value()) ? 'disabled' : '')
printHtmlPart(30)
expressionOut.print(createLink(controller: 'dispute', action: 'create', params: [partner:dealInstance.partner.id,deal:dealInstance.id]))
printHtmlPart(31)
expressionOut.print(resource(dir: 'images', file: 'front/mediation_ultra.png'))
printHtmlPart(32)
}
printHtmlPart(19)
if(true && (myAccount==dealInstance.partner)) {
printHtmlPart(29)
expressionOut.print((dealInstance.status < DealStatus.SignUP.value()) ? 'disabled' : '')
printHtmlPart(30)
expressionOut.print(createLink(controller: 'dispute', action: 'create', params: [partner:dealInstance.account.id,deal: dealInstance.id]))
printHtmlPart(31)
expressionOut.print(resource(dir: 'images', file: 'front/mediation_ultra.png'))
printHtmlPart(32)
}
printHtmlPart(33)
if(true && (myAccount==dealInstance.account)) {
printHtmlPart(34)
expressionOut.print((dealInstance.status <  DealStatus.SignUP.value()) ? 'disabled' : '')
printHtmlPart(30)
expressionOut.print(createLink(controller: 'claim', action: 'create', params: [partner: dealInstance.partner.id, deal: dealInstance.id]))
printHtmlPart(35)
}
printHtmlPart(19)
if(true && (myAccount==dealInstance.partner)) {
printHtmlPart(36)
expressionOut.print((dealInstance.status < DealStatus.SignUP.value()) ? 'disabled' : '')
printHtmlPart(37)
expressionOut.print(createLink(controller: 'claim', action: 'create', params: [partner: dealInstance.account.id,deal: dealInstance.id]))
printHtmlPart(35)
}
printHtmlPart(38)
})
invokeTag('ifAnyGranted','sec',256,['roles':("ROLE_ACCOUNT")],3)
printHtmlPart(39)
invokeTag('render','g',259,['template':("/_common/gallery-multi")],-1)
printHtmlPart(5)
invokeTag('render','g',260,['template':("scripts"),'model':([dealInstance: dealInstance])],-1)
printHtmlPart(40)
}
printHtmlPart(41)
})
invokeTag('captureBody','sitemesh',264,[:],1)
printHtmlPart(42)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1481862618154L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
