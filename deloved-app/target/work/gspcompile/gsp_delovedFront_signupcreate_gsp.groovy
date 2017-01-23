import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import  ru.deloved.CategoryType
import ru.deloved.Category
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_signupcreate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/signup/create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
printHtmlPart(1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("front")],-1)
printHtmlPart(3)
createTagBody(2, {->
createClosureForHtmlPart(4, 3)
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(5)
})
invokeTag('captureHead','sitemesh',57,[:],1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(6)
invokeTag('render','g',60,['template':("/_common/flash-message")],-1)
printHtmlPart(7)
createClosureForHtmlPart(8, 2)
invokeTag('hasErrors','g',67,['bean':(objInstance)],2)
printHtmlPart(9)
createTagBody(2, {->
printHtmlPart(10)
expressionOut.print(hasErrors(bean: objInstance, field: 'userEmail', 'error'))
printHtmlPart(11)
invokeTag('message','g',89,['code':("signupForm.userEmail.label"),'default':("Email")],-1)
printHtmlPart(12)
invokeTag('field','g',93,['class':("form-control"),'type':("email"),'name':("userEmail"),'required':(""),'value':(beanResource?.userEmail),'placeholder':("name@domain.ru")],-1)
printHtmlPart(13)
invokeTag('render','g',94,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'userEmail'])],-1)
printHtmlPart(14)
expressionOut.print(hasErrors(bean: objInstance, field: 'password', 'error'))
printHtmlPart(15)
invokeTag('message','g',98,['code':("signupForm.password.label"),'default':("Password")],-1)
printHtmlPart(12)
invokeTag('passwordField','g',104,['class':("form-control"),'name':("password"),'required':(""),'value':(beanResource?.password)],-1)
printHtmlPart(13)
invokeTag('render','g',105,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'password'])],-1)
printHtmlPart(14)
expressionOut.print(hasErrors(bean: objInstance, field: 'passwordRepeat', 'error'))
printHtmlPart(16)
invokeTag('message','g',109,['code':("signupForm.passwordRepeat.label"),'default':("Repeat Password")],-1)
printHtmlPart(12)
invokeTag('passwordField','g',115,['class':("form-control"),'name':("passwordRepeat"),'required':(""),'value':(beanResource?.passwordRepeat)],-1)
printHtmlPart(13)
invokeTag('render','g',116,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'passwordRepeat'])],-1)
printHtmlPart(17)
expressionOut.print(hasErrors(bean: objInstance, field: 'userCity', 'error'))
printHtmlPart(18)
invokeTag('message','g',120,['code':("signupForm.userCity.label"),'default':("City")],-1)
printHtmlPart(12)
invokeTag('render','g',130,['template':("/_common/auto-complete"),'model':([
						acAction   : createLink(action: 'cities'),
						acMinLength: 2,
						acKeyValue : beanResource?.userCity?.id,
						acKeyName  : 'userCity.id',
						acViewValue: beanResource?.userCity?.name,
						acViewName : 'userCityname'
				])],-1)
printHtmlPart(13)
invokeTag('render','g',132,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'userCity'])],-1)
printHtmlPart(19)
expressionOut.print(hasErrors(bean: objInstance, field: 'orgForm', 'error'))
printHtmlPart(20)
invokeTag('message','g',146,['code':("signupForm.orgForm.label"),'default':("Org Form")],-1)
printHtmlPart(12)
invokeTag('select','g',152,['class':("form-control"),'name':("orgForm"),'from':(ru.deloved.OrgForm.listOrderByCode()),'optionKey':("id"),'optionValue':("code"),'value':(beanResource?.orgForm?.id),'noSelection':(['null': message(code: 'default.selection.notSelected')])],-1)
printHtmlPart(13)
invokeTag('render','g',155,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'orgForm'])],-1)
printHtmlPart(21)
expressionOut.print(hasErrors(bean: objInstance, field: 'fullName', 'error'))
printHtmlPart(22)
invokeTag('message','g',159,['code':("signupForm.fullName.label"),'default':("Full name")],-1)
printHtmlPart(12)
invokeTag('textField','g',165,['class':("form-control"),'name':("fullName"),'value':(beanResource?.fullName),'placeholder':("Торговый Дом АгроТрейд")],-1)
printHtmlPart(13)
invokeTag('render','g',166,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'fullName'])],-1)
printHtmlPart(23)
expressionOut.print(hasErrors(bean: objInstance, field: 'brandName', 'error'))
printHtmlPart(22)
invokeTag('message','g',172,['code':("signupForm.brandName.label"),'default':("Full name")],-1)
printHtmlPart(12)
invokeTag('textField','g',178,['class':("form-control"),'name':("brandName"),'value':(beanResource?.brandName),'placeholder':("ТД АгроТрейд")],-1)
printHtmlPart(13)
invokeTag('render','g',179,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'brandName'])],-1)
printHtmlPart(24)
expressionOut.print(hasErrors(bean: objInstance, field: 'name', 'error'))
printHtmlPart(25)
invokeTag('message','g',185,['code':("signupForm.name.label"),'default':("Name")],-1)
printHtmlPart(26)
invokeTag('textField','g',187,['class':("form-control"),'name':("name"),'value':(beanResource?.name),'placeholder':("АгроТрейд")],-1)
printHtmlPart(27)
invokeTag('render','g',190,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'name'])],-1)
printHtmlPart(28)
expressionOut.print(hasErrors(bean: objInstance, field: 'regNumber', 'error'))
printHtmlPart(29)
invokeTag('message','g',196,['code':("signupForm.regNumber.label"),'default':("Reg Number")],-1)
printHtmlPart(30)
invokeTag('textField','g',201,['class':("form-control"),'name':("regNumber"),'value':(beanResource?.regNumber),'placeholder':("1234567890123")],-1)
printHtmlPart(13)
invokeTag('render','g',202,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'regNumber'])],-1)
printHtmlPart(31)
expressionOut.print(hasErrors(bean: objInstance, field: 'inn', 'error'))
printHtmlPart(32)
invokeTag('message','g',209,['code':("signupForm.inn.label"),'default':("Inn")],-1)
printHtmlPart(33)
invokeTag('textField','g',212,['class':("form-control"),'name':("inn"),'value':(beanResource?.inn),'placeholder':("000505174307")],-1)
printHtmlPart(34)
invokeTag('render','g',214,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'inn'])],-1)
printHtmlPart(35)
expressionOut.print(hasErrors(bean: objInstance, field: 'legalAddress', 'error'))
printHtmlPart(36)
invokeTag('message','g',222,['code':("signupForm.legalAddress.label"),'default':("Legal Address")],-1)
printHtmlPart(12)
invokeTag('textField','g',225,['class':("form-control"),'name':("legalAddress"),'value':(beanResource?.legalAddress),'placeholder':("г. Москва, Тверской бульвар, 12, офис 14")],-1)
printHtmlPart(13)
invokeTag('render','g',227,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'legalAddress'])],-1)
printHtmlPart(37)
expressionOut.print(hasErrors(bean: objInstance, field: 'regDate', 'error'))
printHtmlPart(38)
invokeTag('message','g',236,['code':("signupForm.regDate.label"),'default':("Reg Date")],-1)
printHtmlPart(12)
invokeTag('datePicker','g',238,['name':("regDate"),'precision':("day"),'value':(beanResource?.regDate)],-1)
printHtmlPart(13)
invokeTag('render','g',243,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'regDate'])],-1)
printHtmlPart(39)
expressionOut.print(hasErrors(bean: objInstance, field: 'city', 'error'))
printHtmlPart(40)
invokeTag('message','g',248,['code':("signupForm.centralOffice.label"),'default':("City")],-1)
printHtmlPart(41)
invokeTag('render','g',255,['template':("/_common/auto-complete"),'model':([
						acAction   : createLink(action: 'cities'),
						acMinLength: 2,
						acKeyValue : beanResource?.city?.id,
						acKeyName  : 'city.id',
						acViewValue: beanResource?.city?.name,
						acViewName : 'cityname'
				])],-1)
printHtmlPart(13)
invokeTag('render','g',256,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'city'])],-1)
printHtmlPart(42)
invokeTag('textField','g',261,['class':("form-control"),'name':("address"),'value':(beanResource?.address),'placeholder':("ул. Тверская, 12, офис 1")],-1)
printHtmlPart(13)
invokeTag('render','g',262,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'address'])],-1)
printHtmlPart(43)
expressionOut.print(hasErrors(bean: objInstance, field: 'manager', 'error'))
printHtmlPart(44)
invokeTag('message','g',268,['code':("signupForm.manager.label"),'default':("Manager")],-1)
printHtmlPart(12)
invokeTag('textField','g',272,['class':("form-control"),'name':("manager"),'value':(beanResource?.manager),'placeholder':("Иванов Иван Иванович")],-1)
printHtmlPart(13)
invokeTag('render','g',275,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'manager'])],-1)
printHtmlPart(45)
expressionOut.print(hasErrors(bean: objInstance, field: 'phone1', 'error'))
printHtmlPart(46)
invokeTag('message','g',285,['code':("signupForm.phone1.label"),'default':("Phone1")],-1)
printHtmlPart(12)
invokeTag('textField','g',288,['class':("form-control"),'name':("phone1"),'value':(beanResource?.phone1),'placeholder':("+7 (945) 222-33-44")],-1)
printHtmlPart(13)
invokeTag('render','g',292,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'phone1'])],-1)
printHtmlPart(47)
expressionOut.print(hasErrors(bean: objInstance, field: 'fax1', 'error'))
printHtmlPart(48)
invokeTag('message','g',299,['code':("signupForm.fax1.label"),'default':("Fax1")],-1)
printHtmlPart(49)
invokeTag('textField','g',300,['class':("form-control"),'name':("fax1"),'value':(beanResource?.fax1),'placeholder':("+7 (945) 222-33-44")],-1)
printHtmlPart(13)
invokeTag('render','g',301,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'fax1'])],-1)
printHtmlPart(50)
expressionOut.print(hasErrors(bean: objInstance, field: 'workTime', 'error'))
printHtmlPart(51)
invokeTag('message','g',311,['code':("signupForm.workTime.label"),'default':("Work Time")],-1)
printHtmlPart(12)
invokeTag('textField','g',315,['class':("form-control"),'name':("workTime"),'value':(beanResource?.workTime),'placeholder':("ПН-ПТ: 9:30 - 18.00, СБ, ВС: Выходной")],-1)
printHtmlPart(13)
invokeTag('render','g',316,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'workTime'])],-1)
printHtmlPart(52)
expressionOut.print(hasErrors(bean: objInstance, field: 'webAddress', 'error'))
printHtmlPart(53)
invokeTag('message','g',322,['code':("signupForm.webAddress.label"),'default':("Web Address")],-1)
printHtmlPart(49)
invokeTag('textField','g',326,['class':("form-control"),'name':("webAddress"),'value':(beanResource?.webAddress),'placeholder':("http://www.domain.ru")],-1)
printHtmlPart(13)
invokeTag('render','g',326,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'webAddress'])],-1)
printHtmlPart(54)
expressionOut.print(hasErrors(bean: objInstance, field: 'email', 'error'))
printHtmlPart(55)
invokeTag('message','g',334,['code':("signupForm.email.label"),'default':("Email Address")],-1)
printHtmlPart(49)
invokeTag('field','g',338,['class':("form-control"),'type':("email"),'name':("email"),'value':(beanResource?.email),'placeholder':("info@domain.ru")],-1)
printHtmlPart(13)
invokeTag('render','g',338,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'email'])],-1)
printHtmlPart(56)
expressionOut.print(hasErrors(bean: objInstance, field: 'description', 'error'))
printHtmlPart(57)
invokeTag('message','g',345,['code':("signupForm.description.label"),'default':("Description")],-1)
printHtmlPart(49)
invokeTag('textArea','g',349,['class':("form-control"),'style':("resize: none"),'name':("description"),'value':(beanResource?.description),'rows':("7"),'maxlength':("1500")],-1)
printHtmlPart(13)
invokeTag('render','g',350,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'description'])],-1)
printHtmlPart(58)
expressionOut.print(hasErrors(bean: objInstance, field: 'keywords', 'error'))
printHtmlPart(59)
invokeTag('message','g',357,['code':("signupForm.keywords.label"),'default':("Keywords")],-1)
printHtmlPart(49)
invokeTag('textArea','g',361,['class':("form-control"),'style':("resize: none"),'name':("keywords"),'value':(beanResource?.keywords)],-1)
printHtmlPart(13)
invokeTag('render','g',362,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'keywords'])],-1)
printHtmlPart(60)
invokeTag('set','g',372,['var':("typeList"),'value':(ru.deloved.CategoryType.listOrderById())],-1)
printHtmlPart(61)
loop:{
int i = 0
for( typeInstance in (typeList) ) {
printHtmlPart(62)
expressionOut.print(i == 0 ? ' active' : '')
printHtmlPart(63)
expressionOut.print(typeInstance.code)
printHtmlPart(64)
invokeTag('message','g',380,['code':("categorytype.${typeInstance.code}")],-1)
printHtmlPart(65)
i++
}
}
printHtmlPart(66)
loop:{
int i = 0
for( typeInstance in (typeList) ) {
printHtmlPart(67)
expressionOut.print(i == 0 ? 'active' : '')
printHtmlPart(68)
expressionOut.print(typeInstance.code)
printHtmlPart(69)
expressionOut.print(typeInstance.code)
printHtmlPart(70)
i++
}
}
printHtmlPart(71)
loop:{
int i = 0
for( typeInstance in (typeList) ) {
printHtmlPart(72)
expressionOut.print(typeInstance.code)
printHtmlPart(73)
i++
}
}
printHtmlPart(74)
loop:{
int i = 0
for( typeInstance in (typeList) ) {
printHtmlPart(75)
expressionOut.print(typeInstance.code)
printHtmlPart(76)
expressionOut.print(createLink(action: 'cat'))
printHtmlPart(77)
expressionOut.print(Category.findByTypeAndParent(typeInstance, null).id)
printHtmlPart(78)
i++
}
}
printHtmlPart(79)
expressionOut.print(hasErrors(bean: objInstance, field: 'agreement', 'error-div'))
printHtmlPart(80)
expressionOut.print(beanResource?.agreement == true ? 'checked' : '')
printHtmlPart(81)
invokeTag('render','g',423,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'agreement'])],-1)
printHtmlPart(82)
expressionOut.print(hasErrors(bean: objInstance, field: 'recaptcha', 'error-div'))
printHtmlPart(83)
createTagBody(3, {->
invokeTag('recaptcha','recaptcha',425,['theme':("")],-1)
})
invokeTag('ifEnabled','recaptcha',425,[:],3)
printHtmlPart(3)
invokeTag('render','g',428,['template':("/_common/error"),'model':([objInstance: objInstance, field: 'recaptcha'])],-1)
printHtmlPart(84)
invokeTag('submitButton','g',430,['name':("create"),'class':("btn btn-md btn-success"),'value':("Завершить регистрацию"),'style':(" font-size: 25px;")],-1)
printHtmlPart(85)
})
invokeTag('form','g',430,['name':("editForm"),'url':([controller: 'signup', action: 'save']),'class':("form-horizontal ")],2)
printHtmlPart(86)
})
invokeTag('captureBody','sitemesh',452,[:],1)
printHtmlPart(87)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1484881089187L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
