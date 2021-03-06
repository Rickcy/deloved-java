import ru.deloved.recall.JuristConsult
import ru.deloved.recall.JuristConsultStatus
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_juristConsultthread_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/juristConsult/thread.gsp" }
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
invokeTag('message','g',66,['code':("consult.backallconsults")],-1)
})
invokeTag('link','g',66,['class':("btn btn-default"),'action':("index")],3)
printHtmlPart(9)
createTagBody(3, {->
printHtmlPart(10)
if(true && (juristConsultInstance.jurist!=null)) {
printHtmlPart(11)
expressionOut.print(juristConsultInstance.jurist.fio)
printHtmlPart(12)
expressionOut.print(juristConsultInstance.jurist.fio)
printHtmlPart(13)
if(true && (juristConsultInstance.jurist.avatarThumb)) {
printHtmlPart(14)
expressionOut.print(createLink(controller: 'profile', action: 'avatar', id: juristConsultInstance.jurist.id, params: [name: juristConsultInstance.jurist.avatarThumb?.file]))
printHtmlPart(15)
}
else {
printHtmlPart(16)
expressionOut.print(resource(dir: 'image', file: 'admin/avatar.jpg'))
printHtmlPart(15)
}
printHtmlPart(17)
expressionOut.print(juristConsultInstance.jurist?.city?.name)
printHtmlPart(18)
expressionOut.print(juristConsultInstance.jurist?.city?.name)
printHtmlPart(19)
expressionOut.print(juristConsultInstance.jurist.dayOfWork)
printHtmlPart(18)
expressionOut.print(juristConsultInstance.jurist.dayOfWork)
printHtmlPart(20)
expressionOut.print(resource(dir: 'image', file: 'spinner.gif'))
printHtmlPart(21)
invokeTag('formatDate','g',165,['date':(juristConsultInstance.jurist.dateCreated),'format':("dd.MM.yyyy")],-1)
printHtmlPart(22)
}
printHtmlPart(23)
})
invokeTag('ifAnyGranted','sec',179,['roles':("ROLE_ACCOUNT")],3)
printHtmlPart(23)
createTagBody(3, {->
printHtmlPart(24)
expressionOut.print(juristConsultInstance.account.name)
printHtmlPart(25)
})
invokeTag('ifAnyGranted','sec',185,['roles':("ROLE_JURIST,ROLE_ADMIN")],3)
printHtmlPart(26)
expressionOut.print(message(code: 'consult.status.' + juristConsultInstance.status(), default: juristConsultInstance.status()))
printHtmlPart(27)
createTagBody(3, {->
printHtmlPart(28)
invokeTag('render','g',200,['template':("/_common/thread"),'model':([
                    threadPosts       : posts,
                    threadAccount     : myAccount ?: ticketInstance.account,
                    threadStatusPrefix: 'consult.status'
            ])],-1)
printHtmlPart(29)
})
invokeTag('ifAnyGranted','sec',201,['roles':("ROLE_ACCOUNT")],3)
printHtmlPart(29)
createTagBody(3, {->
printHtmlPart(30)
invokeTag('render','g',210,['template':("/_common/thread-post"),'model':([
                            threadPosts       : posts,
                            threadAccount     : myAccount ?: juristConsultInstance.account,
                            threadStatusPrefix: 'consult.status'
                    ])],-1)
printHtmlPart(31)
})
invokeTag('ifAnyGranted','sec',233,['roles':("ROLE_ADMIN,ROLE_JURIST")],3)
printHtmlPart(29)
if(true && (statusList)) {
printHtmlPart(32)
expressionOut.print(message(code: 'deal.button.status.confirm.message'))
printHtmlPart(33)
for( st in (statusList) ) {
printHtmlPart(34)
expressionOut.print(st.value())
printHtmlPart(35)
expressionOut.print(message(code: 'consult.status.' + st, default: st))
printHtmlPart(36)
}
printHtmlPart(37)
createTagBody(4, {->
printHtmlPart(38)
invokeTag('hiddenField','g',269,['name':("version"),'value':(juristConsultInstance?.version)],-1)
printHtmlPart(38)
invokeTag('hiddenField','g',270,['name':("newstatus"),'value':("")],-1)
printHtmlPart(39)
})
invokeTag('form','g',271,['id':("statusForm"),'url':([resource: juristConsultInstance, action: 'post'])],4)
printHtmlPart(40)
}
printHtmlPart(41)
if(true && (canPost)) {
printHtmlPart(42)
invokeTag('render','g',281,['template':("/_common/hint"),'model':([hintText: 'Удалить сообщение можно путем наведения на сообщение! Доступно в течении 5 минут после публикации.'])],-1)
printHtmlPart(10)
invokeTag('hiddenField','g',282,['name':("version"),'value':(juristConsultInstance?.version)],-1)
printHtmlPart(10)
invokeTag('textArea','g',283,['id':("messageText"),'class':("form-control"),'name':("post"),'rows':("4"),'placeholder':("Ваше сообщение")],-1)
printHtmlPart(43)
expressionOut.print(message(code: 'ticket.send.label'))
printHtmlPart(44)
createTagBody(4, {->
printHtmlPart(45)
expressionOut.print(createLink(controller: 'juristConsult', action: 'upload', id: juristConsultInstance.id))
printHtmlPart(46)
})
invokeTag('ifAnyGranted','sec',361,['roles':("ROLE_ACCOUNT")],4)
printHtmlPart(47)
}
printHtmlPart(48)
invokeTag('render','g',362,['template':("/_common/gallery-multi")],-1)
printHtmlPart(49)
expressionOut.print(createLink(action: 'deletepost'))
printHtmlPart(50)
expressionOut.print(createLink(resource: juristConsultInstance, action: 'post'))
printHtmlPart(51)
expressionOut.print(createLink(controller: 'juristConsult', action: 'post', resource: juristConsultInstance))
printHtmlPart(52)
expressionOut.print(createLink([controller: 'juristConsult', action: 'getLatestPosts']))
printHtmlPart(53)
expressionOut.print(juristConsultInstance.status)
printHtmlPart(54)
}
printHtmlPart(5)
})
invokeTag('captureBody','sitemesh',588,[:],1)
printHtmlPart(55)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1473053080920L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
