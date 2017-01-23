import ru.deloved.Consult
import ru.deloved.ConsultStatus
import ru.deloved.ConsultCategory
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_consultthread_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/consult/thread.gsp" }
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
invokeTag('captureHead','sitemesh',52,[:],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(5)
if(true && (freeAccount)) {
printHtmlPart(2)
invokeTag('render','g',57,['template':("/_common/promo")],-1)
printHtmlPart(0)
}
else {
printHtmlPart(6)
invokeTag('render','g',63,['template':("/_common/flash-message")],-1)
printHtmlPart(7)
createTagBody(3, {->
printHtmlPart(8)
invokeTag('message','g',67,['code':("consult.backallconsults")],-1)
})
invokeTag('link','g',67,['class':("btn btn-default"),'action':("index")],3)
printHtmlPart(9)
createTagBody(3, {->
printHtmlPart(10)
if(true && (consultInstance.jurist)) {
printHtmlPart(11)
expressionOut.print(consultInstance.jurist.fio)
printHtmlPart(12)
expressionOut.print(consultInstance.jurist.fio)
printHtmlPart(13)
if(true && (consultInstance.jurist.avatarThumb)) {
printHtmlPart(14)
expressionOut.print(createLink(controller: 'profile', action: 'avatar', id: consultInstance.jurist.id, params: [name: consultInstance.jurist.avatarThumb?.file]))
printHtmlPart(15)
}
else {
printHtmlPart(16)
expressionOut.print(resource(dir: 'image', file: 'admin/avatar.jpg'))
printHtmlPart(15)
}
printHtmlPart(17)
expressionOut.print(consultInstance.jurist?.city?.name)
printHtmlPart(18)
expressionOut.print(consultInstance.jurist?.city?.name)
printHtmlPart(19)
expressionOut.print(consultInstance.jurist.cellPhone)
printHtmlPart(18)
expressionOut.print(consultInstance.jurist.cellPhone)
printHtmlPart(20)
expressionOut.print(resource(dir: 'image', file: 'spinner.gif'))
printHtmlPart(21)
invokeTag('formatDate','g',164,['date':(consultInstance.jurist.dateCreated),'format':("dd.MM.yyyy")],-1)
printHtmlPart(22)
}
printHtmlPart(23)
})
invokeTag('ifAnyGranted','sec',178,['roles':("ROLE_ACCOUNT")],3)
printHtmlPart(23)
createTagBody(3, {->
printHtmlPart(24)
expressionOut.print(consultInstance.account.name)
printHtmlPart(25)
})
invokeTag('ifAnyGranted','sec',184,['roles':("ROLE_JURIST,ROLE_ADMIN")],3)
printHtmlPart(26)
expressionOut.print(message(code: 'consult.status.' + consultInstance.status(), default: consultInstance.status()))
printHtmlPart(27)
if(true && ([ConsultCategory.Dispute.value(), ConsultCategory.Claim.value()].contains(consultInstance.consultcat))) {
printHtmlPart(28)
expressionOut.print(consultInstance.consultcat == ConsultCategory.Dispute.value() ? 'процедуры медиации' : 'проведения третейского')
printHtmlPart(29)
createTagBody(4, {->
expressionOut.print(consultInstance.deal.id)
})
invokeTag('link','g',196,['controller':("deal"),'action':("thread"),'id':(consultInstance.deal.id)],4)
printHtmlPart(30)
createTagBody(4, {->
expressionOut.print(consultInstance.account.name)
})
invokeTag('link','g',199,['url':([controller: 'company', id: consultInstance.account.id])],4)
printHtmlPart(31)
createTagBody(4, {->
expressionOut.print(consultInstance.defendant?.name)
})
invokeTag('link','g',202,['url':([controller: 'company', id: consultInstance.defendant?.id])],4)
printHtmlPart(32)
createTagBody(4, {->
printHtmlPart(33)
createTagBody(5, {->
printHtmlPart(34)
expressionOut.print(consultInstance.account.id)
printHtmlPart(35)
expressionOut.print(consultInstance.defendant?.id)
printHtmlPart(36)
expressionOut.print(consultInstance.deal.id)
printHtmlPart(37)
})
invokeTag('form','g',214,['id':("subjectForm"),'name':("subjectForm"),'controller':(consultInstance.consultcat == ConsultCategory.Dispute ? 'dispute' : 'claim'),'action':("save"),'target':("_blank")],5)
printHtmlPart(38)
})
invokeTag('ifAllGranted','sec',217,['roles':("ROLE_ADMIN,ROLE_JURIST,ROLE_MANAGER")],4)
printHtmlPart(39)
}
printHtmlPart(40)
createTagBody(3, {->
printHtmlPart(41)
invokeTag('render','g',232,['template':("/_common/thread-post"),'model':([
							threadPosts       : posts,
							threadAccount     : myAccount ?: consultInstance.account,
							threadStatusPrefix: 'consult.status'
					])],-1)
printHtmlPart(42)
})
invokeTag('ifAnyGranted','sec',241,['roles':("ROLE_ACCOUNT,ROLE_JURIST")],3)
printHtmlPart(39)
createTagBody(3, {->
printHtmlPart(41)
invokeTag('render','g',250,['template':("/_common/thread-post"),'model':([
							threadPosts       : posts,
							threadAccount     : myAccount ?: consultInstance.account,
							threadStatusPrefix: 'consult.status'
					])],-1)
printHtmlPart(42)
})
invokeTag('ifAnyGranted','sec',259,['roles':("ROLE_ADMIN")],3)
printHtmlPart(23)
if(true && (statusList)) {
printHtmlPart(43)
expressionOut.print(message(code: 'consult.button.status.confirm.message'))
printHtmlPart(44)
for( st in (statusList) ) {
printHtmlPart(45)
expressionOut.print(st.value())
printHtmlPart(46)
expressionOut.print(message(code: 'consult.status.' + st, default: st))
printHtmlPart(47)
}
printHtmlPart(48)
createTagBody(4, {->
printHtmlPart(49)
invokeTag('hiddenField','g',295,['name':("version"),'value':(consultInstance?.version)],-1)
printHtmlPart(49)
invokeTag('hiddenField','g',296,['name':("newstatus"),'value':("")],-1)
printHtmlPart(50)
})
invokeTag('form','g',297,['id':("statusForm"),'url':([resource: consultInstance, action: 'post'])],4)
printHtmlPart(51)
}
printHtmlPart(7)
if(true && (canPost)) {
printHtmlPart(52)
invokeTag('render','g',307,['template':("/_common/hint"),'model':([hintText: 'Удалить сообщение можно путем наведения на сообщение! Доступно в течении 5 минут после публикации.'])],-1)
printHtmlPart(10)
invokeTag('hiddenField','g',308,['name':("version"),'value':(consultInstance?.version)],-1)
printHtmlPart(10)
invokeTag('textArea','g',309,['id':("messageText"),'class':("form-control"),'name':("post"),'rows':("4"),'placeholder':("Ваше сообщение")],-1)
printHtmlPart(53)
expressionOut.print(message(code: 'consult.send.label'))
printHtmlPart(54)
createTagBody(4, {->
printHtmlPart(55)
expressionOut.print(createLink(controller: 'consult', action: 'upload', id: consultInstance.id))
printHtmlPart(56)
})
invokeTag('ifAnyGranted','sec',383,['roles':("ROLE_ACCOUNT")],4)
printHtmlPart(57)
}
printHtmlPart(58)
invokeTag('render','g',387,['template':("/_common/gallery-multi")],-1)
printHtmlPart(59)
expressionOut.print(createLink(action: 'deletepost'))
printHtmlPart(60)
expressionOut.print(createLink(resource: consultInstance, action: 'post'))
printHtmlPart(61)
expressionOut.print(createLink(controller: 'consult', action: 'post', resource: consultInstance))
printHtmlPart(62)
expressionOut.print(createLink([controller: 'consult', action: 'getLatestPosts']))
printHtmlPart(63)
expressionOut.print(consultInstance.status)
printHtmlPart(64)
}
printHtmlPart(5)
})
invokeTag('captureBody','sitemesh',585,[:],1)
printHtmlPart(65)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1470915091247L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
