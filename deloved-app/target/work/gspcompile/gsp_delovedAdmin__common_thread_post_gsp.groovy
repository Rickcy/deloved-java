import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin__common_thread_post_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/_common/_thread-post.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
loop:{
int i = 0
for( obj in (threadPosts) ) {
printHtmlPart(1)
expressionOut.print(obj.id)
printHtmlPart(2)
invokeTag('hiddenField','g',7,['name':("postId"),'value':(obj.id)],-1)
printHtmlPart(3)
if(true && (obj.person != myProfile)) {
printHtmlPart(4)
if(true && (notMyAccount?.logo)) {
printHtmlPart(5)
expressionOut.print(createLink(controller: 'account', action: 'logo', id: notMyAccount.id, params: [name: notMyAccount?.logoThumb?.file]))
printHtmlPart(6)
}
else if(true && (obj.person.avatarThumb)) {
printHtmlPart(5)
expressionOut.print(createLink(controller: 'profile', action: 'avatar', id: obj.person.id, params: [name: obj.person.avatarThumb?.file]))
printHtmlPart(6)
}
else {
printHtmlPart(5)
expressionOut.print(resource(dir: 'image', file: 'admin/avatar.jpg'))
printHtmlPart(6)
}
printHtmlPart(7)
}
printHtmlPart(8)
if(true && (obj.person != myProfile)) {
printHtmlPart(4)
if(true && (notMyAccount?.name)) {
printHtmlPart(9)
expressionOut.print(notMyAccount.orgForm.code + ' \"' + notMyAccount.name + '\"')
printHtmlPart(10)
}
else {
printHtmlPart(9)
expressionOut.print(obj.person.fio)
printHtmlPart(10)
}
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (obj.status != null)) {
printHtmlPart(13)
if(true && (threadStatusTemplate)) {
printHtmlPart(14)
invokeTag('render','g',43,['template':("/_common/thread-status"),'model':([obj: obj, myProfile: myProfile])],-1)
printHtmlPart(15)
}
else {
printHtmlPart(16)
expressionOut.print(obj.person == myProfile ? 'bubble bubble-in bubble-status' : 'bubble bubble-out bubble-status')
printHtmlPart(17)
expressionOut.print(message(code: threadStatusPrefix + '.' + obj.status(), default: obj.status()))
printHtmlPart(18)
}
printHtmlPart(12)
}
else {
printHtmlPart(19)
expressionOut.print(obj.person == myProfile ? 'bubble bubble-in' : 'bubble bubble-out')
printHtmlPart(20)
expressionOut.print(obj.post)
printHtmlPart(21)
if(true && (obj.attachments)) {
printHtmlPart(22)
for( att in (obj.attachments) ) {
printHtmlPart(23)
expressionOut.print(att.attachment.id)
printHtmlPart(24)
if(true && (att.attachment.isImage())) {
printHtmlPart(25)
expressionOut.print(createLink([action: 'download', id: att.attachment.id, params: [name: att.attachment.name]]))
printHtmlPart(26)
expressionOut.print(att.attachment.name)
printHtmlPart(27)
expressionOut.print(att.attachment.name)
printHtmlPart(28)
expressionOut.print(createLink([action: 'thumb', id: att.attachment.id, params: [name: att.attachment.name]]))
printHtmlPart(29)
}
else {
printHtmlPart(30)
expressionOut.print(resource(dir: 'images', file: "fileupload/${att.attachment.getIcon()}"))
printHtmlPart(31)
}
printHtmlPart(32)
expressionOut.print(att.attachment.name)
printHtmlPart(33)
expressionOut.print(att.attachment.readableByte())
printHtmlPart(34)
expressionOut.print(createLink([action: 'download', id: att.attachment.id, params: [name: att.attachment.name]]))
printHtmlPart(35)
expressionOut.print(att.attachment.name)
printHtmlPart(36)
expressionOut.print(att.attachment.name)
printHtmlPart(37)
createTagBody(5, {->
printHtmlPart(38)
createClosureForHtmlPart(39, 6)
invokeTag('remoteLink','g',90,['action':("deleteatt"),'id':(att.attachment.id),'params':([name: att.attachment.name]),'onSuccess':("jQuery('#att${att.attachment.id}').remove()"),'onFailure':("onDeleteError(XMLHttpRequest)"),'before':("if(!confirm('Are you sure?')) return false")],6)
printHtmlPart(40)
})
invokeTag('ifAnyGranted','sec',90,['roles':("ROLE_ADMIN,ROLE_MANAGER")],5)
printHtmlPart(41)
}
printHtmlPart(42)
}
printHtmlPart(43)
createTagBody(3, {->
printHtmlPart(44)
invokeTag('set','g',91,['var':("canEdit"),'value':("true")],-1)
printHtmlPart(43)
})
invokeTag('ifAnyGranted','sec',91,['roles':("ROLE_ADMIN")],3)
printHtmlPart(14)
if(true && (obj.person == myProfile || canEdit)) {
printHtmlPart(45)
expressionOut.print(obj.id)
printHtmlPart(46)
expressionOut.print(obj.id)
printHtmlPart(47)
}
printHtmlPart(48)
}
printHtmlPart(49)
invokeTag('formatDate','g',104,['id':("timeouts1"),'date':(obj.dateCreated),'format':("dd.MM.yyyy")],-1)
printHtmlPart(50)
invokeTag('formatDate','g',104,['id':("timeouts2"),'date':(obj.dateCreated),'format':("HH:mm")],-1)
printHtmlPart(51)
i++
}
}
printHtmlPart(52)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1478772978778L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
