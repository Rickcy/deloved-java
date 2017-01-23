import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_profile_formfields_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/profile/_formfields.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: objInstance, field: 'fio', 'error'))
printHtmlPart(1)
invokeTag('message','g',3,['code':("profile.fio.label"),'default':("Fio")],-1)
printHtmlPart(2)
invokeTag('textField','g',7,['class':("form-control"),'name':("fio"),'maxlength':("50"),'value':(objInstance?.fio)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: objInstance, field: 'email', 'error'))
printHtmlPart(4)
invokeTag('message','g',13,['code':("profile.email.label"),'default':("Email")],-1)
printHtmlPart(2)
invokeTag('field','g',17,['class':("form-control"),'type':("email"),'name':("email"),'maxlength':("50"),'value':(objInstance?.email)],-1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(6)
invokeTag('checkBox','g',22,['name':("withoutEmailConfirm"),'value':("1"),'checked':("false")],-1)
printHtmlPart(7)
invokeTag('message','g',23,['code':("profile.email.without.confirm.label"),'default':("Подтвердить")],-1)
printHtmlPart(8)
})
invokeTag('ifAnyGranted','sec',26,['roles':("ROLE_ADMIN,ROLE_MANAGER")],1)
printHtmlPart(9)
if(true && (confirmEmailList?.size() > 0)) {
printHtmlPart(10)
for( confirm in (confirmEmailList) ) {
printHtmlPart(11)
expressionOut.print(confirm.contact)
printHtmlPart(12)
invokeTag('message','g',31,['code':("contactConfirm.${confirm.status()}.status")],-1)
printHtmlPart(13)
createTagBody(3, {->
printHtmlPart(14)
if(true && (confirm.status() == ru.deloved.ConfirmStatus.Process)) {
printHtmlPart(15)
createClosureForHtmlPart(16, 5)
invokeTag('link','g',34,['url':([plugin: 'deloved-front', controller: 'signup', action: 'activate', params: [code: confirm.secret]])],5)
printHtmlPart(14)
}
printHtmlPart(13)
})
invokeTag('ifAnyGranted','sec',36,['roles':("ROLE_ADMIN,ROLE_MANAGER")],3)
printHtmlPart(17)
}
printHtmlPart(18)
}
printHtmlPart(19)
if(true && (beanResource.user.role.authority in ['ROLE_MANAGER','ROLE_JUDGE','ROLE_JURIST','ROLE_MEDIATOR'])) {
printHtmlPart(20)
expressionOut.print(hasErrors(bean: objInstance, field: 'dayOfWork', 'error'))
printHtmlPart(21)
invokeTag('message','g',47,['code':("profile.cell.label"),'default':("Стаж работы")],-1)
printHtmlPart(22)
invokeTag('textField','g',51,['class':("form-control"),'name':("dayOfWork"),'maxlength':("20"),'value':(objInstance?.dayOfWork)],-1)
printHtmlPart(23)
}
else {
printHtmlPart(20)
expressionOut.print(hasErrors(bean: objInstance, field: 'cellPhone', 'error'))
printHtmlPart(24)
invokeTag('message','g',61,['code':("profile.cellPhone"),'default':("Сотовый")],-1)
printHtmlPart(25)
if(true && (confirmCellphoneList?.size() == 0)) {
printHtmlPart(26)
invokeTag('textField','g',68,['class':("form-control"),'name':("cellPhone"),'maxlength':("20"),'value':(objInstance?.cellPhone)],-1)
printHtmlPart(27)
createTagBody(3, {->
printHtmlPart(28)
invokeTag('checkBox','g',73,['name':("withoutSmsConfirm"),'value':("1"),'checked':("false")],-1)
printHtmlPart(14)
invokeTag('message','g',74,['code':("profile.cellPhone.without.confirm.label"),'default':("Подтвердить")],-1)
printHtmlPart(29)
})
invokeTag('ifAnyGranted','sec',77,['roles':("ROLE_ADMIN,ROLE_MANAGER")],3)
printHtmlPart(30)
}
else {
printHtmlPart(31)
expressionOut.print(objInstance?.cellPhone)
printHtmlPart(32)
for( confirm in (confirmCellphoneList) ) {
printHtmlPart(33)
expressionOut.print(confirm.contact)
printHtmlPart(12)
invokeTag('message','g',84,['code':("contactConfirm.${confirm.status()}.status")],-1)
printHtmlPart(14)
createTagBody(4, {->
printHtmlPart(34)
expressionOut.print(confirm.secret)
printHtmlPart(14)
})
invokeTag('ifAnyGranted','sec',87,['roles':("ROLE_ADMIN,ROLE_MANAGER")],4)
printHtmlPart(14)
if(true && (confirm.status() == ru.deloved.ConfirmStatus.Process)) {
printHtmlPart(35)
invokeTag('message','g',91,['code':("profile.smsCode.label"),'default':("SMS Code")],-1)
printHtmlPart(36)
invokeTag('textField','g',93,['class':("form-control"),'name':("smsCode"),'maxlength':("6")],-1)
printHtmlPart(37)
}
printHtmlPart(38)
}
printHtmlPart(39)
}
printHtmlPart(40)
}
printHtmlPart(41)
if(true && (inputCity)) {
printHtmlPart(20)
expressionOut.print(hasErrors(bean: objInstance, field: 'city', 'error'))
printHtmlPart(42)
invokeTag('message','g',109,['code':("profile.city.label"),'default':("City")],-1)
printHtmlPart(43)
invokeTag('hiddenField','g',114,['name':("city.id"),'value':(objInstance?.city?.id)],-1)
printHtmlPart(30)
invokeTag('hiddenField','g',115,['name':("cityname2"),'value':(objInstance?.city?.name)],-1)
printHtmlPart(30)
invokeTag('textField','g',116,['class':("form-control"),'name':("cityname"),'value':(objInstance?.city?.name)],-1)
printHtmlPart(44)
expressionOut.print(createLink(controller: 'profile', action: 'cities'))
printHtmlPart(45)
}
printHtmlPart(46)
expressionOut.print(hasErrors(bean: objInstance, field: 'avatar', 'error'))
printHtmlPart(47)
invokeTag('message','g',155,['code':("profile.avatar.label"),'default':("Avatar")],-1)
printHtmlPart(48)
invokeTag('render','g',172,['template':("/_common/upload-single-image"),'model':([
				'isUpload'    : beanResource?.avatar != null,
				'imageUrl'    : createLink(controller: 'profile', action: 'avatar', id: beanResource.id, params: [name: beanResource.avatar?.file, type: 'main']),
				'thumbUrl'    : createLink(controller: 'profile', action: 'avatar', id: beanResource.id, params: [name: beanResource.avatarThumb?.file]),
				'uploadAction': createLink(controller: 'profile', action: 'upload', id: beanResource.id),
				'cropAction'  : createLink(controller: 'profile', action: 'crop', id: beanResource.id),
				'deleteAction': 'deleteavatar',
				'imgId'       : 'avatar',
				'imgTitle'    : 'Аватар',
				'imgWidth'    : 100,
				'imgHeight'   : 100

		])],-1)
printHtmlPart(49)
if(true && (beanResource.user.role.authority = 'ROLE_ACCOUNT')) {
printHtmlPart(50)
createTagBody(2, {->
printHtmlPart(51)
expressionOut.print(hasErrors(bean: beanResource, field: 'chargeStatus', 'error'))
printHtmlPart(52)
invokeTag('message','g',181,['code':("profile.chargeStatus.label"),'default':("Подписка")],-1)
printHtmlPart(53)
if(true && (beanResource?.chargeStatus == 0)) {
printHtmlPart(54)
}
printHtmlPart(55)
invokeTag('radio','g',187,['name':("chargeStatus"),'value':("0"),'checked':(beanResource?.chargeStatus == 0)],-1)
printHtmlPart(56)
if(true && (beanResource?.chargeStatus == 1)) {
printHtmlPart(54)
}
printHtmlPart(55)
invokeTag('radio','g',190,['name':("chargeStatus"),'value':("1"),'checked':(beanResource?.chargeStatus == 1)],-1)
printHtmlPart(57)
expressionOut.print(hasErrors(bean: beanResource, field: 'chargeTill', 'error'))
printHtmlPart(58)
invokeTag('message','g',198,['code':("profile.chargeTill.label"),'default':("Срок подписки")],-1)
printHtmlPart(59)
invokeTag('textField','g',202,['class':("form-control"),'name':("chargeTill"),'value':(formatDate(date: beanResource.chargeTill, format: 'dd.MM.yyyy HH:mm:ss'))],-1)
printHtmlPart(60)
})
invokeTag('ifAnyGranted','sec',206,['roles':("ROLE_ADMIN,ROLE_MANAGER")],2)
printHtmlPart(61)
}
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1473051712420L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
