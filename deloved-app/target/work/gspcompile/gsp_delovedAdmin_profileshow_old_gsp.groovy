import ru.deloved.Profile
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_profileshow_old_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/profile/show-old.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("admin")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',6,['code':("profile.show.label")],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',12,['code':("profile.show.label")],-1)
printHtmlPart(5)
invokeTag('render','g',14,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
invokeTag('message','g',22,['code':("profile.fio.label")],-1)
printHtmlPart(7)
invokeTag('fieldValue','g',23,['bean':(profileInstance),'field':("fio")],-1)
printHtmlPart(8)
invokeTag('message','g',26,['code':("profile.email.label")],-1)
printHtmlPart(9)
invokeTag('fieldValue','g',27,['bean':(profileInstance),'field':("email")],-1)
printHtmlPart(10)
if(true && (confirmEmailList?.size()>0)) {
printHtmlPart(11)
for( confirm in (confirmEmailList) ) {
printHtmlPart(12)
expressionOut.print(confirm.contact)
printHtmlPart(13)
invokeTag('message','g',32,['code':("contactConfirm.${confirm.status()}.status")],-1)
printHtmlPart(14)
}
printHtmlPart(15)
}
printHtmlPart(16)
invokeTag('message','g',39,['code':("profile.cellPhone.label")],-1)
printHtmlPart(17)
invokeTag('fieldValue','g',40,['bean':(profileInstance),'field':("cellPhone")],-1)
printHtmlPart(10)
if(true && (confirmCellphoneList?.size()>0)) {
printHtmlPart(11)
for( confirm in (confirmCellphoneList) ) {
printHtmlPart(12)
expressionOut.print(confirm.contact)
printHtmlPart(13)
invokeTag('message','g',45,['code':("contactConfirm.${confirm.status()}.status")],-1)
printHtmlPart(14)
}
printHtmlPart(15)
}
printHtmlPart(18)
if(true && (profileInstance?.avatar)) {
printHtmlPart(19)
expressionOut.print(createLink([controller: 'profile', action: 'avatar', id: profileInstance.id, params: [name: profileInstance.avatar.file, type: 'main']]))
printHtmlPart(20)
expressionOut.print(createLink([controller: 'profile', action: 'avatar', id: profileInstance.id, params: [name: profileInstance.avatarThumb.file]]))
printHtmlPart(21)
}
printHtmlPart(22)
if(true && (profileInstance.user.role.authority = 'ROLE_ACCOUNT')) {
printHtmlPart(23)
invokeTag('message','g',63,['code':("profile.chargeStatus.label"),'default':("Charge Status")],-1)
printHtmlPart(24)
invokeTag('fieldValue','g',64,['bean':(profileInstance),'field':("chargeStatus")],-1)
printHtmlPart(25)
invokeTag('message','g',67,['code':("profile.chargeTill.label"),'default':("Charge till")],-1)
printHtmlPart(26)
invokeTag('fieldValue','g',68,['bean':(profileInstance),'field':("chargeTill")],-1)
printHtmlPart(27)
}
printHtmlPart(28)
createTagBody(2, {->
invokeTag('message','g',75,['code':("default.button.edit.label")],-1)
})
invokeTag('link','g',75,['class':("btn btn-primary btn-lg"),'controller':("profile"),'action':("edit")],2)
printHtmlPart(29)
createTagBody(2, {->
invokeTag('message','g',76,['code':("profile.button.passwd.label")],-1)
})
invokeTag('link','g',76,['class':("btn btn-primary btn-lg"),'controller':("profile"),'action':("passwd")],2)
printHtmlPart(30)
invokeTag('render','g',81,['template':("/_common/gallery-single")],-1)
printHtmlPart(3)
})
invokeTag('captureBody','sitemesh',83,[:],1)
printHtmlPart(31)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444124073832L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
