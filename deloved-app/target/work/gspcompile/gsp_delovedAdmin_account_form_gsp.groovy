import ru.deloved.CategoryType
import ru.deloved.Category
import ru.deloved.Account
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_account_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/account/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(1)
invokeTag('message','g',7,['code':("account.tab.main")],-1)
printHtmlPart(2)
invokeTag('message','g',8,['code':("account.tab.contacts")],-1)
printHtmlPart(3)
invokeTag('message','g',9,['code':("account.tab.affiliate")],-1)
printHtmlPart(4)
invokeTag('message','g',10,['code':("account.tab.seo")],-1)
printHtmlPart(5)
invokeTag('message','g',11,['code':("account.tab.category")],-1)
printHtmlPart(6)
createTagBody(1, {->
printHtmlPart(7)
expressionOut.print(hasErrors(bean: objInstance, field: 'publicStatus', 'error'))
printHtmlPart(8)
invokeTag('message','g',21,['code':("account.publicStatus.label"),'default':("Public status")],-1)
printHtmlPart(9)
if(true && (objInstance?.publicStatus == false)) {
printHtmlPart(10)
}
printHtmlPart(11)
invokeTag('radio','g',27,['name':("publicStatus"),'value':("false"),'checked':(objInstance?.publicStatus == false)],-1)
printHtmlPart(12)
if(true && (objInstance?.publicStatus == true)) {
printHtmlPart(10)
}
printHtmlPart(11)
invokeTag('radio','g',30,['name':("publicStatus"),'value':("true"),'checked':(objInstance?.publicStatus == true)],-1)
printHtmlPart(13)
expressionOut.print(hasErrors(bean: objInstance, field: 'verifyStatus', 'error'))
printHtmlPart(14)
invokeTag('message','g',38,['code':("account.verifyStatus.label"),'default':("Verify status")],-1)
printHtmlPart(9)
if(true && (objInstance?.verifyStatus == false)) {
printHtmlPart(10)
}
printHtmlPart(11)
invokeTag('radio','g',44,['name':("verifyStatus"),'value':("false"),'checked':(objInstance?.verifyStatus == false)],-1)
printHtmlPart(15)
if(true && (objInstance?.verifyStatus == true)) {
printHtmlPart(10)
}
printHtmlPart(11)
invokeTag('radio','g',47,['name':("verifyStatus"),'value':("true"),'checked':(objInstance?.verifyStatus == true)],-1)
printHtmlPart(16)
invokeTag('message','g',55,['code':("profile.label")],-1)
printHtmlPart(17)
invokeTag('render','g',67,['template':("/_common/auto-complete"),'model':([
								acAction   : createLink(resource: beanResource, action: 'profiles'),
								acMinLength: 4,
								acKeyValue : profile?.id,
								acKeyName  : 'profileid',
								acViewValue: profile?.fio,
								acViewName : 'profilefio'
						])],-1)
printHtmlPart(18)
expressionOut.print(hasErrors(bean: objInstance, field: 'showMain', 'error'))
printHtmlPart(19)
invokeTag('message','g',73,['code':("account.showMain.label"),'default':("Show on main")],-1)
printHtmlPart(9)
if(true && (objInstance?.showMain == false)) {
printHtmlPart(10)
}
printHtmlPart(11)
invokeTag('radio','g',79,['name':("showMain"),'value':("false"),'checked':(objInstance?.showMain == false)],-1)
printHtmlPart(12)
if(true && (objInstance?.showMain == true)) {
printHtmlPart(10)
}
printHtmlPart(11)
invokeTag('radio','g',82,['name':("showMain"),'value':("true"),'checked':(objInstance?.showMain == true)],-1)
printHtmlPart(20)
})
invokeTag('ifAnyGranted','sec',88,['roles':("ROLE_ADMIN,ROLE_MANAGER")],1)
printHtmlPart(21)
expressionOut.print(hasErrors(bean: objInstance, field: 'name', 'error'))
printHtmlPart(22)
invokeTag('message','g',93,['code':("account.name.label"),'default':("Name")],-1)
printHtmlPart(23)
invokeTag('textField','g',98,['class':("form-control"),'name':("name"),'value':(objInstance?.name)],-1)
printHtmlPart(24)
invokeTag('render','g',99,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'name'])],-1)
printHtmlPart(25)
expressionOut.print(hasErrors(bean: objInstance, field: 'fullName', 'error'))
printHtmlPart(26)
invokeTag('message','g',106,['code':("account.fullName.label"),'default':("Full name")],-1)
printHtmlPart(27)
invokeTag('textField','g',110,['class':("form-control"),'name':("fullName"),'value':(objInstance?.fullName)],-1)
printHtmlPart(28)
expressionOut.print(hasErrors(bean: objInstance, field: 'brandName', 'error'))
printHtmlPart(29)
invokeTag('message','g',115,['code':("account.brandName.label"),'default':("Brand name")],-1)
printHtmlPart(27)
invokeTag('textField','g',119,['class':("form-control"),'name':("brandName"),'value':(objInstance?.brandName)],-1)
printHtmlPart(28)
expressionOut.print(hasErrors(bean: objInstance, field: 'orgForm', 'error'))
printHtmlPart(30)
invokeTag('message','g',124,['code':("account.orgForm.label"),'default':("Org Form")],-1)
printHtmlPart(23)
invokeTag('select','g',131,['class':("form-control"),'name':("orgForm"),'from':(ru.deloved.OrgForm.listOrderByCode()),'optionKey':("id"),'optionValue':("code"),'value':(objInstance?.orgForm?.id),'noSelection':(['null': message(code: 'default.selection.notSelected')])],-1)
printHtmlPart(31)
expressionOut.print(hasErrors(bean: objInstance, field: 'regNumber', 'error'))
printHtmlPart(32)
invokeTag('message','g',137,['code':("account.regNumber.label"),'default':("Reg Number")],-1)
printHtmlPart(27)
invokeTag('textField','g',141,['class':("form-control"),'name':("regNumber"),'value':(objInstance?.regNumber)],-1)
printHtmlPart(33)
expressionOut.print(hasErrors(bean: objInstance, field: 'inn', 'error'))
printHtmlPart(34)
invokeTag('message','g',146,['code':("account.inn.label"),'default':("Inn")],-1)
printHtmlPart(27)
invokeTag('textField','g',150,['class':("form-control"),'name':("inn"),'value':(objInstance?.inn)],-1)
printHtmlPart(28)
expressionOut.print(hasErrors(bean: objInstance, field: 'kpp', 'error'))
printHtmlPart(35)
invokeTag('message','g',155,['code':("account.kpp.label"),'default':("Kpp")],-1)
printHtmlPart(36)
invokeTag('textField','g',158,['class':("form-control"),'name':("kpp"),'value':(objInstance?.kpp)],-1)
printHtmlPart(28)
expressionOut.print(hasErrors(bean: objInstance, field: 'legalAddress', 'error'))
printHtmlPart(37)
invokeTag('message','g',163,['code':("account.legalAddress.label"),'default':("Legal Address")],-1)
printHtmlPart(27)
invokeTag('textField','g',167,['class':("form-control"),'name':("legalAddress"),'value':(objInstance?.legalAddress)],-1)
printHtmlPart(28)
expressionOut.print(hasErrors(bean: objInstance, field: 'regDate', 'error'))
printHtmlPart(38)
invokeTag('message','g',172,['code':("account.regDate.label"),'default':("Reg Date")],-1)
printHtmlPart(27)
invokeTag('datePicker','g',176,['name':("regDate"),'precision':("day"),'value':(objInstance?.regDate)],-1)
printHtmlPart(39)
if(true && (beanResource?.id)) {
printHtmlPart(7)
expressionOut.print(hasErrors(bean: objInstance, field: 'logo', 'error'))
printHtmlPart(40)
invokeTag('message','g',182,['code':("profile.avatar.label"),'default':("Avatar")],-1)
printHtmlPart(17)
invokeTag('render','g',198,['template':("/_common/upload-single-image"),'model':([
								'isUpload'    : beanResource?.logo != null,
								'imageUrl'    : createLink(controller: 'account', action: 'logo', id: beanResource?.id, params: [name: beanResource?.logo?.file, type: 'main']),
								'thumbUrl'    : createLink(controller: 'account', action: 'logo', id: beanResource?.id, params: [name: beanResource?.logoThumb?.file]),
								'uploadAction': createLink(controller: 'account', action: 'upload', id: beanResource?.id),
								'cropAction'  : createLink(controller: 'account', action: 'crop', id: beanResource?.id),
								'deleteAction': 'deletelogo',
								'imgId'       : 'logo',
								'imgTitle'    : 'Логотип',
								'imgWidth'    : 100,
								'imgHeight'   : 100
						])],-1)
printHtmlPart(41)
}
printHtmlPart(42)
expressionOut.print(hasErrors(bean: objInstance, field: 'city', 'error'))
printHtmlPart(43)
invokeTag('message','g',209,['code':("account.city.label"),'default':("City")],-1)
printHtmlPart(44)
invokeTag('hiddenField','g',215,['name':("city.id"),'value':(objInstance?.city?.id)],-1)
printHtmlPart(24)
invokeTag('hiddenField','g',216,['name':("cityname2"),'value':(objInstance?.city?.name)],-1)
printHtmlPart(24)
invokeTag('textField','g',217,['class':("form-control"),'name':("cityname"),'value':(objInstance?.city?.name)],-1)
printHtmlPart(45)
expressionOut.print(createLink(resource:objInstance, action: 'cities'))
printHtmlPart(46)
expressionOut.print(objInstance?.city?.name)
printHtmlPart(47)
expressionOut.print(objInstance?.address)
printHtmlPart(48)
expressionOut.print(hasErrors(bean: objInstance, field: 'address', 'error'))
printHtmlPart(49)
invokeTag('message','g',341,['code':("account.address.label"),'default':("Address")],-1)
printHtmlPart(50)
invokeTag('textField','g',345,['class':("form-control"),'name':("address"),'value':(objInstance?.address)],-1)
printHtmlPart(51)
expressionOut.print(hasErrors(bean: objInstance, field: 'manager', 'error'))
printHtmlPart(52)
invokeTag('message','g',357,['code':("account.manager.label"),'default':("Manager")],-1)
printHtmlPart(27)
invokeTag('textField','g',361,['class':("form-control"),'name':("manager"),'value':(objInstance?.manager)],-1)
printHtmlPart(28)
expressionOut.print(hasErrors(bean: objInstance, field: 'phone1', 'error'))
printHtmlPart(53)
invokeTag('message','g',366,['code':("account.phone1.label"),'default':("Phone1")],-1)
printHtmlPart(27)
invokeTag('textField','g',370,['class':("form-control"),'name':("phone1"),'value':(objInstance?.phone1)],-1)
printHtmlPart(28)
expressionOut.print(hasErrors(bean: objInstance, field: 'phone2', 'error'))
printHtmlPart(54)
invokeTag('message','g',375,['code':("account.phone2.label"),'default':("Phone2")],-1)
printHtmlPart(50)
invokeTag('textField','g',379,['class':("form-control"),'name':("phone2"),'value':(objInstance?.phone2)],-1)
printHtmlPart(28)
expressionOut.print(hasErrors(bean: objInstance, field: 'phone3', 'error'))
printHtmlPart(55)
invokeTag('message','g',384,['code':("account.phone3.label"),'default':("Phone3")],-1)
printHtmlPart(50)
invokeTag('textField','g',388,['class':("form-control"),'name':("phone3"),'value':(objInstance?.phone3)],-1)
printHtmlPart(28)
expressionOut.print(hasErrors(bean: objInstance, field: 'fax1', 'error'))
printHtmlPart(56)
invokeTag('message','g',393,['code':("account.fax1.label"),'default':("Fax1")],-1)
printHtmlPart(50)
invokeTag('textField','g',397,['class':("form-control"),'name':("fax1"),'value':(objInstance?.fax1)],-1)
printHtmlPart(28)
expressionOut.print(hasErrors(bean: objInstance, field: 'fax2', 'error'))
printHtmlPart(57)
invokeTag('message','g',402,['code':("account.fax2.label"),'default':("Fax2")],-1)
printHtmlPart(50)
invokeTag('textField','g',406,['class':("form-control"),'name':("fax2"),'value':(objInstance?.fax2)],-1)
printHtmlPart(28)
expressionOut.print(hasErrors(bean: objInstance, field: 'workTime', 'error'))
printHtmlPart(58)
invokeTag('message','g',411,['code':("account.workTime.label"),'default':("Work Time")],-1)
printHtmlPart(27)
invokeTag('textField','g',415,['class':("form-control"),'name':("workTime"),'value':(objInstance?.workTime)],-1)
printHtmlPart(28)
expressionOut.print(hasErrors(bean: objInstance, field: 'webAddress', 'error'))
printHtmlPart(59)
invokeTag('message','g',420,['code':("account.webAddress.label"),'default':("Web Address")],-1)
printHtmlPart(50)
invokeTag('textField','g',424,['class':("form-control"),'name':("webAddress"),'value':(objInstance?.webAddress)],-1)
printHtmlPart(28)
expressionOut.print(hasErrors(bean: objInstance, field: 'email', 'error'))
printHtmlPart(60)
invokeTag('message','g',429,['code':("account.email.label"),'default':("Email Address")],-1)
printHtmlPart(50)
invokeTag('textField','g',433,['class':("form-control"),'name':("email"),'value':(objInstance?.email)],-1)
printHtmlPart(61)
loop:{
int i = 0
for( affInstance in (beanResource?.affiliates) ) {
printHtmlPart(24)
invokeTag('render','g',442,['template':("newAff"),'model':([affInstance: affInstance, i: i])],-1)
printHtmlPart(62)
i++
}
}
printHtmlPart(63)
createTagBody(1, {->
invokeTag('message','g',446,['code':("account.addaffilate")],-1)
})
invokeTag('remoteLink','g',446,['class':("btn btn-default"),'action':("affform"),'onSuccess':("addAffForm(data)"),'params':("getFormData()")],1)
printHtmlPart(64)
expressionOut.print(beanResource?.affiliates == null ? 0 : beanResource?.affiliates.size())
printHtmlPart(65)
expressionOut.print(hasErrors(bean: objInstance, field: 'description', 'error'))
printHtmlPart(66)
invokeTag('message','g',464,['code':("account.description.label"),'default':("Description")],-1)
printHtmlPart(50)
invokeTag('textArea','g',468,['class':("form-control"),'name':("description"),'value':(objInstance?.description),'rows':("15"),'maxlength':("1500")],-1)
printHtmlPart(67)
expressionOut.print(hasErrors(bean: objInstance, field: 'keywords', 'error'))
printHtmlPart(68)
invokeTag('message','g',474,['code':("account.keywords.label"),'default':("Keywords")],-1)
printHtmlPart(50)
invokeTag('textArea','g',478,['class':("form-control"),'name':("keywords"),'value':(objInstance?.keywords)],-1)
printHtmlPart(69)
invokeTag('set','g',483,['var':("typeList"),'value':(ru.deloved.CategoryType.listOrderById())],-1)
printHtmlPart(70)
loop:{
int i = 0
for( typeInstance in (typeList) ) {
printHtmlPart(71)
expressionOut.print(i == 0 ? ' active' : '')
printHtmlPart(72)
expressionOut.print(typeInstance.code)
printHtmlPart(73)
invokeTag('message','g',488,['code':("categorytype.${typeInstance.code}")],-1)
printHtmlPart(74)
i++
}
}
printHtmlPart(75)
loop:{
int i = 0
for( typeInstance in (typeList) ) {
printHtmlPart(76)
expressionOut.print(i == 0 ? 'active' : '')
printHtmlPart(77)
expressionOut.print(typeInstance.code)
printHtmlPart(78)
expressionOut.print(typeInstance.code)
printHtmlPart(79)
i++
}
}
printHtmlPart(80)
loop:{
int i = 0
for( typeInstance in (typeList) ) {
printHtmlPart(81)
expressionOut.print(typeInstance.code)
printHtmlPart(82)
i++
}
}
printHtmlPart(83)
loop:{
int i = 0
for( typeInstance in (typeList) ) {
printHtmlPart(84)
expressionOut.print(typeInstance.code)
printHtmlPart(85)
expressionOut.print(createLink(resource:beanResource, action: 'cat'))
printHtmlPart(86)
expressionOut.print(Category.findByTypeAndParent(typeInstance, null).id)
printHtmlPart(87)
i++
}
}
printHtmlPart(88)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1461213889399L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
