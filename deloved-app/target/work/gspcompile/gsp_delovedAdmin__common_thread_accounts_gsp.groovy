import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin__common_thread_accounts_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/_common/_thread-accounts.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
invokeTag('fieldValue','g',22,['bean':(account),'field':("name")],-1)
})
invokeTag('link','g',22,['class':("name"),'url':([controller: 'company', id: account.id])],1)
printHtmlPart(1)
createTagBody(1, {->
invokeTag('fieldValue','g',26,['bean':(partner),'field':("name")],-1)
})
invokeTag('link','g',26,['class':("name"),'url':([controller: 'company', id: partner.id])],1)
printHtmlPart(2)
expressionOut.print(account.manager)
printHtmlPart(3)
expressionOut.print(partner.manager)
printHtmlPart(4)
if(true && (!accountUsers.isEmpty())) {
printHtmlPart(5)
for( user in (accountUsers) ) {
printHtmlPart(6)
if(true && (user.avatarThumb)) {
printHtmlPart(7)
expressionOut.print(createLink(controller: 'profile', action: 'avatar', id: user.id, params: [name: user.avatar?.file, type: 'main']))
printHtmlPart(8)
expressionOut.print(user.id)
printHtmlPart(9)
expressionOut.print(createLink(controller: 'profile', action: 'avatar', id: user.id, params: [name: user.avatarThumb?.file]))
printHtmlPart(10)
}
else {
printHtmlPart(11)
expressionOut.print(resource(dir: 'image', file: 'admin/avatar.jpg'))
printHtmlPart(12)
}
printHtmlPart(13)
expressionOut.print(user.fio)
printHtmlPart(14)
}
printHtmlPart(15)
}
printHtmlPart(16)
if(true && (!partnerUsers.isEmpty())) {
printHtmlPart(5)
for( user in (partnerUsers) ) {
printHtmlPart(6)
expressionOut.print(user.fio)
printHtmlPart(13)
if(true && (user.avatarThumb)) {
printHtmlPart(7)
expressionOut.print(createLink(controller: 'profile', action: 'avatar', id: user.id, params: [name: user.avatar?.file, type: 'main']))
printHtmlPart(17)
expressionOut.print(user.id)
printHtmlPart(9)
expressionOut.print(createLink(controller: 'profile', action: 'avatar', id: user.id, params: [name: user.avatarThumb?.file]))
printHtmlPart(10)
}
else {
printHtmlPart(11)
expressionOut.print(resource(dir: 'image', file: 'admin/avatar.jpg'))
printHtmlPart(18)
}
printHtmlPart(19)
}
printHtmlPart(15)
}
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1484025349913L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
