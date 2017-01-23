import ru.deloved.Dispute
import ru.deloved.DisputeStatus
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_disputethread_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/dispute/thread.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("admin")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(2)
invokeTag('javascript','asset',8,['src':("/dropzonejs/dropzone.min.js")],-1)
printHtmlPart(2)
invokeTag('javascript','asset',9,['src':("/mCustomScrollbar/jquery.mCustomScrollbar.concat.min.js")],-1)
printHtmlPart(2)
invokeTag('link','asset',10,['rel':("stylesheet"),'href':("/mCustomScrollbar/jquery.mCustomScrollbar.min.css")],-1)
printHtmlPart(2)
invokeTag('link','asset',11,['rel':("buttons"),'href':("/mCustomScrollbar/mCSB_buttons.png")],-1)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',55,[:],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(5)
if(true && (freeAccount)) {
printHtmlPart(2)
invokeTag('render','g',60,['template':("/_common/promo")],-1)
printHtmlPart(0)
}
else {
printHtmlPart(6)
invokeTag('render','g',66,['template':("/_common/flash-message")],-1)
printHtmlPart(7)
createTagBody(3, {->
printHtmlPart(8)
invokeTag('message','g',69,['code':("dispute.backalldisputes")],-1)
})
invokeTag('link','g',69,['class':("btn btn-default"),'action':("index")],3)
printHtmlPart(9)
expressionOut.print(disputeInstance.id)
printHtmlPart(10)
createClosureForHtmlPart(11, 3)
invokeTag('link','g',80,['url':([resource: disputeInstance.deal, action: 'thread'])],3)
printHtmlPart(12)
expressionOut.print(message(code: 'dispute.status.' + disputeInstance.status(), default: disputeInstance.status()))
printHtmlPart(13)
if(true && (disputeInstance.mediator)) {
printHtmlPart(14)
expressionOut.print(disputeInstance.mediator.fio)
printHtmlPart(15)
expressionOut.print(disputeInstance.mediator.fio)
printHtmlPart(16)
if(true && (disputeInstance.mediator.avatarThumb)) {
printHtmlPart(17)
expressionOut.print(createLink(controller: 'profile', action: 'avatar', id: disputeInstance.mediator.id, params: [name: disputeInstance.mediator.avatarThumb?.file]))
printHtmlPart(18)
}
else {
printHtmlPart(19)
expressionOut.print(resource(dir: 'image', file: 'admin/avatar.jpg'))
printHtmlPart(18)
}
printHtmlPart(20)
expressionOut.print(disputeInstance.mediator?.city?.name)
printHtmlPart(21)
expressionOut.print(disputeInstance.mediator?.city?.name)
printHtmlPart(22)
expressionOut.print(disputeInstance.mediator.dayOfWork)
printHtmlPart(21)
expressionOut.print(disputeInstance.mediator.dayOfWork)
printHtmlPart(23)
expressionOut.print(resource(dir: 'image', file: 'spinner.gif'))
printHtmlPart(24)
invokeTag('formatDate','g',180,['date':(disputeInstance.mediator.dateCreated),'format':("dd.MM.yyyy")],-1)
printHtmlPart(25)
}
printHtmlPart(26)
invokeTag('render','g',200,['template':("/_common/thread-accounts"),'model':([
				account     : disputeInstance.account,
				partner     : disputeInstance.partner,
				accountUsers: dealUsers1,
				partnerUsers: dealUsers2
		])],-1)
printHtmlPart(27)
createTagBody(3, {->
printHtmlPart(28)
invokeTag('render','g',210,['template':("/_common/thread-post"),'model':([
							threadPosts       : posts,
							threadAccount     : myAccount ?: disputeInstance.account,
							threadStatusPrefix: 'dispute.status'
					])],-1)
printHtmlPart(29)
})
invokeTag('ifAnyGranted','sec',219,['roles':("ROLE_ADMIN")],3)
printHtmlPart(7)
createTagBody(3, {->
printHtmlPart(28)
invokeTag('render','g',229,['template':("/_common/thread-post"),'model':([
							threadPosts       : posts,
							threadAccount     : myAccount ?: disputeInstance.account,
							threadStatusPrefix: 'dispute.status'
					])],-1)
printHtmlPart(29)
})
invokeTag('ifAnyGranted','sec',238,['roles':("ROLE_ACCOUNT,ROLE_MEDIATOR")],3)
printHtmlPart(30)
if(true && (statusList)) {
printHtmlPart(31)
expressionOut.print(message(code: 'dispute.button.status.confirm.message'))
printHtmlPart(32)
expressionOut.print(DisputeStatus.Processing.value())
printHtmlPart(33)
expressionOut.print(DisputeStatus.ResolveWS.value())
printHtmlPart(34)
expressionOut.print(DisputeStatus.Failed.value())
printHtmlPart(35)
expressionOut.print(DisputeStatus.Failed.value())
printHtmlPart(36)
expressionOut.print(DisputeStatus.ResolveWS.value())
printHtmlPart(37)
for( st in (statusList) ) {
printHtmlPart(38)
expressionOut.print(st.value())
printHtmlPart(39)
expressionOut.print(message(code: 'dispute.status.' + st, default: st))
printHtmlPart(40)
}
printHtmlPart(41)
if(true && (disputeInstance.deal)) {
printHtmlPart(42)
expressionOut.print(message(code: 'deal.failed.b', args: [disputeInstance.account.name]))
printHtmlPart(43)
expressionOut.print(message(code: 'deal.failed.b', args: [disputeInstance.partner.name]))
printHtmlPart(44)
for( st in (status2List) ) {
printHtmlPart(45)
if(true && (st == ru.deloved.DealStatus.Suspended)) {
printHtmlPart(46)
}
printHtmlPart(47)
expressionOut.print(st.value())
printHtmlPart(39)
expressionOut.print(message(code: 'deal.status.' + st, default: st))
printHtmlPart(48)
}
printHtmlPart(49)
for( st in (status2List) ) {
printHtmlPart(45)
if(true && (st == ru.deloved.DealStatus.Suspended)) {
printHtmlPart(46)
}
printHtmlPart(50)
expressionOut.print(st.value())
printHtmlPart(39)
expressionOut.print(message(code: 'deal.status.' + st, default: st))
printHtmlPart(48)
}
printHtmlPart(51)
expressionOut.print(message(code: 'deal.failed.b', args: [disputeInstance.account.name]))
printHtmlPart(52)
expressionOut.print(message(code: 'deal.failed.b', args: [disputeInstance.partner.name]))
printHtmlPart(53)
expressionOut.print(message(code: 'deal.failed.bw', args: [disputeInstance.account.name]))
printHtmlPart(54)
expressionOut.print(message(code: 'deal.failed.bw', args: [disputeInstance.partner.name]))
printHtmlPart(55)
}
printHtmlPart(56)
createTagBody(4, {->
printHtmlPart(57)
invokeTag('hiddenField','g',366,['name':("version"),'value':(disputeInstance?.version)],-1)
printHtmlPart(57)
invokeTag('hiddenField','g',367,['name':("newstatus"),'value':("")],-1)
printHtmlPart(57)
invokeTag('hiddenField','g',368,['name':("new2status"),'value':("")],-1)
printHtmlPart(57)
invokeTag('hiddenField','g',369,['name':("failedSide"),'value':("")],-1)
printHtmlPart(58)
})
invokeTag('form','g',370,['id':("statusForm"),'url':([resource: disputeInstance, action: 'post'])],4)
printHtmlPart(59)
}
printHtmlPart(7)
if(true && (canPost)) {
printHtmlPart(60)
invokeTag('render','g',378,['template':("/_common/hint"),'model':([hintText: 'Удалить сообщение можно путем наведения на сообщение! Доступно в течении 5 минут после публикации.'])],-1)
printHtmlPart(61)
invokeTag('hiddenField','g',379,['name':("version"),'value':(disputeInstance?.version)],-1)
printHtmlPart(61)
invokeTag('textArea','g',380,['required':(""),'id':("messageText"),'class':("form-control"),'name':("post"),'rows':("4"),'placeholder':("Ваше сообщение")],-1)
printHtmlPart(62)
expressionOut.print(message(code: 'dispute.send.label'))
printHtmlPart(63)
invokeTag('render','g',397,['template':("/_common/upload-multi"),'model':([
							objInstance: disputeInstance,
							attachList : attach
					])],-1)
printHtmlPart(64)
}
printHtmlPart(65)
invokeTag('render','g',414,['template':("/_common/gallery-multi")],-1)
printHtmlPart(66)
expressionOut.print(createLink(action: 'deletepost'))
printHtmlPart(67)
expressionOut.print(createLink(controller: 'dispute', action: 'post', resource: disputeInstance))
printHtmlPart(68)
expressionOut.print(createLink([controller: 'dispute', action: 'getLatestPosts']))
printHtmlPart(69)
expressionOut.print(disputeInstance.status)
printHtmlPart(70)
}
printHtmlPart(5)
})
invokeTag('captureBody','sitemesh',609,[:],1)
printHtmlPart(71)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1478774947529L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
