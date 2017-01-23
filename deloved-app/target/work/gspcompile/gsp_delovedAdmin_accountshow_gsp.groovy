import org.codehaus.groovy.grails.plugins.jquery.JQueryConfig
import  ru.deloved.Account
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
class gsp_delovedAdmin_accountshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/account/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',8,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("admin")],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',9,['code':("account.show.label")],-1)
})
invokeTag('captureTitle','sitemesh',9,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',9,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',13,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('render','g',27,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(2)
invokeTag('render','g',34,['template':("/_common/hint"),'model':([hintText: 'Поля, закрытые для редактирования, можно изменить через обращение в службу поддержки.'])],-1)
printHtmlPart(0)
})
invokeTag('ifAnyGranted','sec',35,['roles':("ROLE_ACCOUNT")],2)
printHtmlPart(7)
expressionOut.print(accountInstance.id)
printHtmlPart(8)
expressionOut.print(accountInstance.orgForm.name)
printHtmlPart(9)
expressionOut.print(accountInstance.fullName)
printHtmlPart(10)
expressionOut.print(accountInstance.name)
printHtmlPart(11)
expressionOut.print(accountInstance.brandName)
printHtmlPart(12)
invokeTag('render','g',121,['template':("/_common/upload-single-image"),'model':([
                            'isUpload'    : accountInstance?.logo != null,
                            'imageUrl'    : createLink(controller: 'account', action: 'logo', id: accountInstance?.id, params: [name: accountInstance?.logo?.file, type: 'main']),
                            'thumbUrl'    : createLink(controller: 'account', action: 'logo', id: accountInstance?.id, params: [name: accountInstance?.logoThumb?.file]),
                            'uploadAction': createLink(controller: 'account', action: 'upload', id: accountInstance?.id),
                            'cropAction'  : createLink(controller: 'account', action: 'crop', id: accountInstance?.id),
                            'deleteAction': 'deletelogo',
                            'imgId'       : 'logo',
                            'imgTitle'    : 'Логотип',
                            'imgWidth'    : 100,
                            'imgHeight'   : 100
                    ])],-1)
printHtmlPart(13)
expressionOut.print(accountInstance.rating)
printHtmlPart(14)
createClosureForHtmlPart(15, 2)
invokeTag('link','g',142,['class':("otz"),'data-toggle':("modal"),'data-remote':(createLink(id: accountInstance.id, action: 'revi                                                                                                                                                                                          ews')),'data-target':("#OpenOTZ")],2)
printHtmlPart(16)
invokeTag('message','g',151,['code':("review.list.label")],-1)
printHtmlPart(17)
if(true && (reviewsCount > 0)) {
printHtmlPart(18)
for( review in (reviews) ) {
printHtmlPart(19)
invokeTag('formatDate','g',163,['date':(review.dateCreated),'format':("dd.MM.yyyy HH:mm:ss")],-1)
printHtmlPart(20)
if(true && (review.value > 0)) {
printHtmlPart(21)
}
else if(true && (review.value < 0)) {
printHtmlPart(22)
}
else {
printHtmlPart(23)
}
printHtmlPart(24)
createTagBody(4, {->
printHtmlPart(25)
invokeTag('fieldValue','g',176,['bean':(review.from),'field':("name")],-1)
printHtmlPart(26)
})
invokeTag('link','g',177,['url':([controller: 'company', id: review.from.id])],4)
printHtmlPart(27)
expressionOut.print(review.content)
printHtmlPart(28)
}
printHtmlPart(29)
}
else {
printHtmlPart(30)
}
printHtmlPart(31)
expressionOut.print(message(code: 'default.button.close.label'))
printHtmlPart(32)
expressionOut.print(accountInstance.inn)
printHtmlPart(33)
expressionOut.print(accountInstance.regNumber)
printHtmlPart(34)
expressionOut.print(accountInstance.legalAddress)
printHtmlPart(35)
invokeTag('formatDate','g',250,['date':(accountInstance.regDate),'format':("dd.MM.yyyy")],-1)
printHtmlPart(36)
expressionOut.print(accountInstance.manager)
printHtmlPart(37)
expressionOut.print(accountInstance?.city.name)
printHtmlPart(38)
expressionOut.print(accountInstance?.city.name)
printHtmlPart(39)
expressionOut.print(accountInstance.address)
printHtmlPart(38)
expressionOut.print(accountInstance.address)
printHtmlPart(40)
expressionOut.print(createLink(controller: 'profile', action: 'cities'))
printHtmlPart(41)
expressionOut.print(accountInstance?.city?.name)
printHtmlPart(42)
expressionOut.print(accountInstance?.address)
printHtmlPart(43)
expressionOut.print(accountInstance.email)
printHtmlPart(38)
expressionOut.print(accountInstance.email)
printHtmlPart(44)
expressionOut.print(accountInstance.webAddress)
printHtmlPart(38)
expressionOut.print(accountInstance.webAddress)
printHtmlPart(45)
expressionOut.print(accountInstance.phone1)
printHtmlPart(38)
expressionOut.print(accountInstance.phone1)
printHtmlPart(46)
expressionOut.print(accountInstance.phone2)
printHtmlPart(38)
expressionOut.print(accountInstance.phone2)
printHtmlPart(47)
expressionOut.print(accountInstance.phone3)
printHtmlPart(38)
expressionOut.print(accountInstance.phone3)
printHtmlPart(48)
expressionOut.print(accountInstance.fax1)
printHtmlPart(38)
expressionOut.print(accountInstance.fax1)
printHtmlPart(49)
expressionOut.print(accountInstance.fax2)
printHtmlPart(38)
expressionOut.print(accountInstance.fax2)
printHtmlPart(50)
expressionOut.print(accountInstance.workTime)
printHtmlPart(38)
expressionOut.print(accountInstance.workTime)
printHtmlPart(51)
expressionOut.print(accountInstance.description)
printHtmlPart(52)
expressionOut.print(accountInstance.description)
printHtmlPart(53)
expressionOut.print(accountInstance.keywords)
printHtmlPart(54)
expressionOut.print(accountInstance.keywords)
printHtmlPart(55)
loop:{
int i = 0
for( affiliateInstance in (accountInstance.affiliates) ) {
printHtmlPart(56)
expressionOut.print((i == 0) ? 'active' : '')
printHtmlPart(57)
expressionOut.print(i)
printHtmlPart(58)
expressionOut.print(i+1)
printHtmlPart(59)
i++
}
}
printHtmlPart(60)
createClosureForHtmlPart(61, 2)
invokeTag('remoteLink','g',541,['action':("addAffiliate"),'onSuccess':("pushAffiliate(data)"),'params':("affiliateBlockCount()")],2)
printHtmlPart(62)
createTagBody(2, {->
printHtmlPart(63)
loop:{
int i = 0
for( affiliateInstance in (accountInstance.affiliates) ) {
printHtmlPart(64)
invokeTag('render','g',555,['template':("affiliate"),'model':([affiliateInstance: affiliateInstance, i: i, active: false])],-1)
printHtmlPart(65)
i++
}
}
printHtmlPart(66)
})
invokeTag('formRemote','g',555,['id':("affiliateList"),'name':("affiliateList"),'url':([action: 'updateAffiliates', id: accountInstance.id]),'onSuccess':("showMessage('success','Изменения успешно сохранены')"),'onFailure':("showMessage('success','Не верные данные')")],2)
printHtmlPart(67)
invokeTag('set','g',589,['var':("typeList"),'value':(ru.deloved.CategoryType.listOrderById())],-1)
printHtmlPart(68)
loop:{
int i = 0
for( typeInstance in (typeList) ) {
printHtmlPart(69)
expressionOut.print(i == 0 ? ' active' : '')
printHtmlPart(70)
expressionOut.print(typeInstance.code)
printHtmlPart(71)
invokeTag('message','g',605,['code':("categorytype.${typeInstance.code}")],-1)
printHtmlPart(72)
i++
}
}
printHtmlPart(73)
loop:{
int i = 0
for( typeInstance in (typeList) ) {
printHtmlPart(74)
expressionOut.print(i == 0 ? 'active' : '')
printHtmlPart(75)
expressionOut.print(typeInstance.code)
printHtmlPart(76)
expressionOut.print(typeInstance.code)
printHtmlPart(77)
i++
}
}
printHtmlPart(78)
loop:{
int i = 0
for( typeInstance in (typeList) ) {
printHtmlPart(79)
expressionOut.print(typeInstance.code)
printHtmlPart(80)
i++
}
}
printHtmlPart(81)
expressionOut.print(createLink(controller: 'account', action: 'editNew', id: accountInstance.id))
printHtmlPart(82)
loop:{
int i = 0
for( typeInstance in (typeList) ) {
printHtmlPart(83)
expressionOut.print(typeInstance.code)
printHtmlPart(84)
expressionOut.print(createLink(resource:accountInstance, action: 'cat'))
printHtmlPart(85)
expressionOut.print(Category.findByTypeAndParent(typeInstance, null).id)
printHtmlPart(86)
i++
}
}
printHtmlPart(87)
expressionOut.print(createLink(controller: 'account', action: 'editNew', id: accountInstance.id))
printHtmlPart(88)
invokeTag('render','g',752,['template':("/_common/modal"),'model':([
        container: 'reviewsContainer',
        modalId  : 'reviewsModal'
])],-1)
printHtmlPart(89)
invokeTag('render','g',753,['template':("/_common/crop")],-1)
printHtmlPart(0)
invokeTag('render','g',753,['template':("/_common/gallery-single")],-1)
printHtmlPart(0)
})
invokeTag('captureBody','sitemesh',753,[:],1)
printHtmlPart(90)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1481863803899L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
