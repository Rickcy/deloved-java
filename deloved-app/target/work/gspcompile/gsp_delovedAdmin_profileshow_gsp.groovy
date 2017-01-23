import ru.deloved.Profile
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_profileshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/profile/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("admin")],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',6,['code':("profile.show.label")],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('message','g',11,['code':("profile.show.label")],-1)
printHtmlPart(6)
invokeTag('render','g',13,['template':("/_common/flash-message")],-1)
printHtmlPart(4)
invokeTag('render','g',15,['template':("/_common/hint"),'model':([hintText: 'Поля, закрытые для редактирования, можно изменить через обращение в службу поддержки.'])],-1)
printHtmlPart(7)
expressionOut.print(profileInstance.id)
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
createTagBody(3, {->
invokeTag('message','g',28,['code':("profile.button.passwd.label")],-1)
})
invokeTag('link','g',28,['controller':("profile"),'action':("passwd")],3)
printHtmlPart(10)
})
invokeTag('ifAnyGranted','sec',30,['roles':("ROLE_ACCOUNT,ROLE_ADMIN")],2)
printHtmlPart(11)
expressionOut.print(profileInstance.fio)
printHtmlPart(12)
invokeTag('render','g',71,['template':("/_common/upload-single-image"),'model':([
						'isUpload'    : profileInstance?.avatar != null,
						'imageUrl'    : createLink(controller: 'profile', action: 'avatar', id: profileInstance.id, params: [name: profileInstance?.avatar?.file, type: 'main']),
						'thumbUrl'    : createLink(controller: 'profile', action: 'avatar', id: profileInstance.id, params: [name: profileInstance?.avatarThumb?.file]),
						'uploadAction': createLink(controller: 'profile', action: 'upload', id:profileInstance.id),
						'cropAction'  : createLink(controller: 'profile', action: 'crop', id: profileInstance.id),
						'deleteAction': 'deleteavatar',
						'imgId'       : 'avatar',
						'imgTitle'    : 'Аватар',
						'imgWidth'    : 100,
						'imgHeight'   : 100

				])],-1)
printHtmlPart(13)
expressionOut.print(profileInstance?.city?.name)
printHtmlPart(14)
expressionOut.print(profileInstance?.city?.name)
printHtmlPart(15)
expressionOut.print(resource(dir: 'image', file: 'spinner.gif'))
printHtmlPart(16)
createClosureForHtmlPart(17, 2)
invokeTag('ifAnyGranted','sec',89,['roles':("ROLE_ADMIN,ROLE_ACCOUNT")],2)
printHtmlPart(18)
expressionOut.print(createLink(controller: 'profile', action: 'cities'))
printHtmlPart(19)
createTagBody(2, {->
printHtmlPart(20)
expressionOut.print(profileInstance.email)
printHtmlPart(14)
expressionOut.print(profileInstance.email)
printHtmlPart(21)
expressionOut.print(resource(dir: 'image', file: 'spinner.gif'))
printHtmlPart(22)
if(true && (confirmEmailList?.size() > 0)) {
printHtmlPart(23)
for( confirm in (confirmEmailList) ) {
printHtmlPart(24)
expressionOut.print(confirm.contact)
printHtmlPart(25)
invokeTag('message','g',121,['code':("contactConfirm.${confirm.status()}.status")],-1)
printHtmlPart(26)
}
printHtmlPart(27)
}
printHtmlPart(3)
})
invokeTag('ifAnyGranted','sec',122,['roles':("ROLE_ADMIN,ROLE_ACCOUNT")],2)
printHtmlPart(3)
createTagBody(2, {->
printHtmlPart(28)
expressionOut.print(profileInstance.dayOfWork)
printHtmlPart(14)
expressionOut.print(profileInstance.dayOfWork)
printHtmlPart(29)
expressionOut.print(resource(dir: 'image', file: 'spinner.gif'))
printHtmlPart(30)
})
invokeTag('ifAnyGranted','sec',148,['roles':("ROLE_MANAGER,ROLE_JUDGE,ROLE_JURIST,ROLE_MEDIATOR")],2)
printHtmlPart(31)
invokeTag('formatDate','g',155,['date':(profileInstance.dateCreated),'format':("dd.MM.yyyy")],-1)
printHtmlPart(32)
createTagBody(2, {->
printHtmlPart(33)
if(true && (profileInstance.chargeStatus == 1)) {
printHtmlPart(34)
invokeTag('formatDate','g',171,['date':(profileInstance.chargeTill),'format':("dd.MM.yyyy")],-1)
printHtmlPart(35)
}
else {
printHtmlPart(36)
}
printHtmlPart(37)
if(true && (profileInstance.chargeStatus == 1)) {
printHtmlPart(38)
createClosureForHtmlPart(39, 4)
invokeTag('link','g',181,['url':([controller: 'billing', action: 'index'])],4)
printHtmlPart(40)
}
else {
printHtmlPart(38)
createClosureForHtmlPart(41, 4)
invokeTag('link','g',184,['url':([controller: 'billing', action: 'index'])],4)
printHtmlPart(40)
}
printHtmlPart(42)
})
invokeTag('ifAnyGranted','sec',186,['roles':("ROLE_ACCOUNT")],2)
printHtmlPart(43)
expressionOut.print(createLink(controller: 'profile', action: 'editNew', id: profileInstance.id))
printHtmlPart(44)
invokeTag('render','g',222,['template':("/_common/crop")],-1)
printHtmlPart(3)
invokeTag('render','g',223,['template':("/_common/gallery-single")],-1)
printHtmlPart(4)
})
invokeTag('captureBody','sitemesh',223,[:],1)
printHtmlPart(45)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1473053217131L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
