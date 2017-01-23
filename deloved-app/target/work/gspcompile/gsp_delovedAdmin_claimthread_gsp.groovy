import ru.deloved.Claim
import ru.deloved.ClaimStatus
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_claimthread_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/claim/thread.gsp" }
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
invokeTag('captureHead','sitemesh',51,[:],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(5)
if(true && (freeAccount)) {
printHtmlPart(2)
invokeTag('render','g',56,['template':("/_common/promo")],-1)
printHtmlPart(0)
}
else {
printHtmlPart(6)
invokeTag('render','g',62,['template':("/_common/flash-message")],-1)
printHtmlPart(7)
createTagBody(3, {->
printHtmlPart(8)
invokeTag('message','g',64,['code':("claim.backallclaims")],-1)
})
invokeTag('link','g',64,['class':("btn btn-default"),'action':("index")],3)
printHtmlPart(9)
expressionOut.print(claimInstance.id)
printHtmlPart(10)
createClosureForHtmlPart(11, 3)
invokeTag('link','g',75,['url':([resource: claimInstance.deal, action: 'thread'])],3)
printHtmlPart(12)
expressionOut.print(message(code: 'claim.status.' + claimInstance.status(), default: claimInstance.status()))
printHtmlPart(13)
if(true && (claimInstance.judge)) {
printHtmlPart(14)
expressionOut.print(claimInstance.judge.fio)
printHtmlPart(15)
expressionOut.print(claimInstance.judge.fio)
printHtmlPart(16)
if(true && (claimInstance.judge.avatarThumb)) {
printHtmlPart(17)
expressionOut.print(createLink(controller: 'profile', action: 'avatar', id: claimInstance.judge.id, params: [name: claimInstance.judge.avatarThumb?.file]))
printHtmlPart(18)
}
else {
printHtmlPart(19)
expressionOut.print(resource(dir: 'image', file: 'admin/avatar.jpg'))
printHtmlPart(20)
}
printHtmlPart(21)
expressionOut.print(claimInstance.judge?.city?.name)
printHtmlPart(22)
expressionOut.print(claimInstance.judge?.city?.name)
printHtmlPart(23)
expressionOut.print(claimInstance.judge.dayOfWork)
printHtmlPart(22)
expressionOut.print(claimInstance.judge.dayOfWork)
printHtmlPart(24)
expressionOut.print(resource(dir: 'image', file: 'spinner.gif'))
printHtmlPart(25)
invokeTag('formatDate','g',175,['date':(claimInstance.judge.dateCreated),'format':("dd.MM.yyyy")],-1)
printHtmlPart(26)
}
printHtmlPart(7)
invokeTag('render','g',194,['template':("/_common/thread-accounts"),'model':([
				account     : claimInstance.account,
				partner     : claimInstance.partner,
				accountUsers: dealUsers1,
				partnerUsers: dealUsers2
		])],-1)
printHtmlPart(27)
createTagBody(3, {->
printHtmlPart(28)
if(true && (myAccount==claimInstance.account)) {
printHtmlPart(29)
}
printHtmlPart(7)
})
invokeTag('ifAnyGranted','sec',208,['roles':("ROLE_ACCOUNT")],3)
printHtmlPart(7)
createTagBody(3, {->
printHtmlPart(28)
invokeTag('render','g',215,['template':("/_common/thread"),'model':([
					threadPosts       : posts,
					threadAccount     : myAccount ?: claimInstance.account,
					threadStatusPrefix: 'claim.status'
			])],-1)
printHtmlPart(7)
})
invokeTag('ifAnyGranted','sec',217,['roles':("ROLE_ACCOUNT,ROLE_JUDGE")],3)
printHtmlPart(30)
createTagBody(3, {->
printHtmlPart(31)
invokeTag('render','g',226,['template':("/_common/thread-post"),'model':([
							threadPosts       : posts,
							threadAccount     : myAccount ?: claimInstance.account,
							threadStatusPrefix: 'claim.status'
					])],-1)
printHtmlPart(32)
})
invokeTag('ifAnyGranted','sec',235,['roles':("ROLE_ADMIN")],3)
printHtmlPart(30)
if(true && (statusList)) {
printHtmlPart(33)
expressionOut.print(message(code: 'claim.button.status.confirm.message'))
printHtmlPart(34)
expressionOut.print(ClaimStatus.Processing.value())
printHtmlPart(35)
expressionOut.print(ClaimStatus.Resolve.value())
printHtmlPart(36)
expressionOut.print(ClaimStatus.ResolveWS.value())
printHtmlPart(37)
expressionOut.print(ClaimStatus.Return.value())
printHtmlPart(38)
expressionOut.print(ClaimStatus.Failed.value())
printHtmlPart(39)
expressionOut.print(ClaimStatus.Resolve.value())
printHtmlPart(40)
expressionOut.print(ClaimStatus.Return.value())
printHtmlPart(41)
expressionOut.print(ClaimStatus.Failed.value())
printHtmlPart(42)
expressionOut.print(ClaimStatus.ResolveWS.value())
printHtmlPart(43)
for( st in (statusList) ) {
printHtmlPart(44)
expressionOut.print(st.value())
printHtmlPart(45)
expressionOut.print(message(code: 'claim.status.' + st, default: st))
printHtmlPart(46)
}
printHtmlPart(47)
if(true && (claimInstance.deal)) {
printHtmlPart(48)
for( st in (status2List) ) {
printHtmlPart(49)
if(true && (st == ru.deloved.DealStatus.Suspended)) {
printHtmlPart(50)
}
printHtmlPart(51)
expressionOut.print(st.value())
printHtmlPart(45)
expressionOut.print(message(code: 'deal.status.' + st, default: st))
printHtmlPart(52)
}
printHtmlPart(53)
for( st in (status2List) ) {
printHtmlPart(49)
if(true && (st == ru.deloved.DealStatus.Suspended)) {
printHtmlPart(50)
}
printHtmlPart(54)
expressionOut.print(st.value())
printHtmlPart(45)
expressionOut.print(message(code: 'deal.status.' + st, default: st))
printHtmlPart(52)
}
printHtmlPart(55)
expressionOut.print(message(code: 'deal.failed.b', args: [claimInstance.account.name]))
printHtmlPart(56)
expressionOut.print(message(code: 'deal.failed.b', args: [claimInstance.partner.name]))
printHtmlPart(57)
expressionOut.print(message(code: 'deal.failed.bw', args: [claimInstance.account.name]))
printHtmlPart(58)
expressionOut.print(message(code: 'deal.failed.bw', args: [claimInstance.partner.name]))
printHtmlPart(59)
}
printHtmlPart(60)
createTagBody(4, {->
printHtmlPart(61)
invokeTag('hiddenField','g',363,['name':("version"),'value':(claimInstance?.version)],-1)
printHtmlPart(61)
invokeTag('hiddenField','g',364,['name':("newstatus"),'value':("")],-1)
printHtmlPart(61)
invokeTag('hiddenField','g',365,['name':("new2status"),'value':("")],-1)
printHtmlPart(61)
invokeTag('hiddenField','g',366,['name':("failedSide"),'value':("")],-1)
printHtmlPart(62)
})
invokeTag('form','g',367,['id':("statusForm"),'url':([resource: claimInstance, action: 'post'])],4)
printHtmlPart(63)
}
printHtmlPart(7)
if(true && (canPost)) {
printHtmlPart(0)
createTagBody(4, {->
printHtmlPart(64)
invokeTag('render','g',375,['template':("/_common/hint"),'model':([hintText: 'Удалить сообщение можно путем наведения на сообщение! Доступно в течении 5 минут после публикации.'])],-1)
printHtmlPart(30)
invokeTag('hiddenField','g',376,['name':("version"),'value':(claimInstance?.version)],-1)
printHtmlPart(30)
invokeTag('textArea','g',377,['required':(""),'id':("messageText"),'class':("form-control"),'name':("post"),'rows':("4"),'placeholder':("Ваше сообщение")],-1)
printHtmlPart(65)
expressionOut.print(message(code: 'claim.send.label'))
printHtmlPart(66)
expressionOut.print(message(code: 'claim.send.label'))
printHtmlPart(67)
invokeTag('render','g',397,['template':("/_common/upload-multi"),'model':([
					objInstance: claimInstance,
					attachList : attach
			])],-1)
printHtmlPart(68)
})
invokeTag('ifAnyGranted','sec',428,['roles':("ROLE_ACCOUNT")],4)
printHtmlPart(0)
createTagBody(4, {->
printHtmlPart(64)
invokeTag('render','g',431,['template':("/_common/hint"),'model':([hintText: 'Удалить сообщение можно путем наведения на сообщение! Доступно в течении 5 минут после публикации.'])],-1)
printHtmlPart(30)
invokeTag('hiddenField','g',432,['name':("version"),'value':(claimInstance?.version)],-1)
printHtmlPart(30)
invokeTag('textArea','g',433,['required':(""),'id':("messageText"),'class':("form-control"),'name':("post"),'rows':("4"),'placeholder':("Ваше сообщение")],-1)
printHtmlPart(65)
expressionOut.print(message(code: 'claim.send.label'))
printHtmlPart(67)
invokeTag('render','g',449,['template':("/_common/upload-multi"),'model':([
					objInstance: claimInstance,
					attachList : attach
			])],-1)
printHtmlPart(69)
})
invokeTag('ifAnyGranted','sec',454,['roles':("ROLE_JUDGE")],4)
printHtmlPart(70)
}
printHtmlPart(71)
invokeTag('render','g',470,['template':("/_common/gallery-multi")],-1)
printHtmlPart(72)
expressionOut.print(createLink(action: 'deletepost'))
printHtmlPart(73)
expressionOut.print(createLink(controller: 'claim', action: 'post', resource: claimInstance))
printHtmlPart(74)
expressionOut.print(createLink([controller: 'claim', action: 'getLatestPosts']))
printHtmlPart(75)
expressionOut.print(claimInstance.status)
printHtmlPart(76)
}
printHtmlPart(5)
})
invokeTag('captureBody','sitemesh',669,[:],1)
printHtmlPart(77)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1476415392348L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
