import org.codehaus.groovy.grails.plugins.jquery.JQueryConfig
import  ru.deloved.Account
import ru.deloved.CategoryType
import ru.deloved.Category
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_accountshow_old_ajax_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/account/show-old-ajax.gsp" }
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
createTagBody(3, {->
invokeTag('message','g',8,['code':("account.show.label")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',10,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('message','g',23,['code':("account.show.label")],-1)
printHtmlPart(6)
invokeTag('render','g',25,['template':("/_common/flash-message")],-1)
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
invokeTag('render','g',111,['template':("/_common/upload-single-image"),'model':([
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
expressionOut.print(accountInstance.inn)
printHtmlPart(15)
expressionOut.print(accountInstance.regNumber)
printHtmlPart(16)
if(true && (accountInstance.kpp)) {
printHtmlPart(17)
expressionOut.print(accountInstance.kpp)
printHtmlPart(18)
}
printHtmlPart(19)
expressionOut.print(accountInstance.legalAddress)
printHtmlPart(20)
invokeTag('formatDate','g',191,['date':(accountInstance.regDate),'format':("dd.MM.yyyy")],-1)
printHtmlPart(21)
expressionOut.print(accountInstance.manager)
printHtmlPart(22)
expressionOut.print(accountInstance?.city.name ?: 'отсутсвует')
printHtmlPart(23)
expressionOut.print(accountInstance?.city.name ?: 'отсутсвует')
printHtmlPart(24)
expressionOut.print(accountInstance.address ?: 'отсутсвует')
printHtmlPart(23)
expressionOut.print(accountInstance.address ?: 'отсутсвует')
printHtmlPart(25)
expressionOut.print(createLink(controller: 'profile', action: 'cities'))
printHtmlPart(26)
expressionOut.print(accountInstance?.city?.name)
printHtmlPart(27)
expressionOut.print(accountInstance?.address)
printHtmlPart(28)
expressionOut.print(accountInstance.email ?: 'отсутсвует')
printHtmlPart(23)
expressionOut.print(accountInstance.email ?: 'отсутсвует')
printHtmlPart(29)
expressionOut.print(accountInstance.webAddress ?: 'отсутсвует')
printHtmlPart(23)
expressionOut.print(accountInstance.webAddress ?: 'отсутсвует')
printHtmlPart(30)
expressionOut.print(accountInstance.phone1 ?: 'отсутсвует')
printHtmlPart(23)
expressionOut.print(accountInstance.phone1 ?: 'отсутсвует')
printHtmlPart(31)
expressionOut.print(accountInstance.phone2 ?: 'отсутсвует')
printHtmlPart(23)
expressionOut.print(accountInstance.phone2 ?: 'отсутсвует')
printHtmlPart(32)
expressionOut.print(accountInstance.phone3 ?: 'отсутсвует')
printHtmlPart(23)
expressionOut.print(accountInstance.phone3 ?: 'отсутсвует')
printHtmlPart(33)
expressionOut.print(accountInstance.fax1 ?: 'отсутсвует')
printHtmlPart(23)
expressionOut.print(accountInstance.fax1 ?: 'отсутсвует')
printHtmlPart(34)
expressionOut.print(accountInstance.fax2 ?: 'отсутсвует')
printHtmlPart(23)
expressionOut.print(accountInstance.fax2 ?: 'отсутсвует')
printHtmlPart(35)
expressionOut.print(accountInstance.workTime ?: 'отсутсвует')
printHtmlPart(23)
expressionOut.print(accountInstance.workTime ?: 'отсутсвует')
printHtmlPart(36)
invokeTag('formatDate','g',430,['date':(accountInstance.dateCreated ?: 'отсутсвует'),'format':("dd.MM.yyyy")],-1)
printHtmlPart(37)
invokeTag('formatDate','g',442,['date':(accountInstance.lastUpdated ?: 'отсутсвует'),'format':("dd.MM.yyyy")],-1)
printHtmlPart(38)
expressionOut.print(accountInstance.description ?: 'отсутсвует')
printHtmlPart(39)
expressionOut.print(accountInstance.description ?: 'отсутсвует')
printHtmlPart(40)
expressionOut.print(accountInstance.keywords ?: 'отсутсвует')
printHtmlPart(39)
expressionOut.print(accountInstance.keywords ?: 'отсутсвует')
printHtmlPart(41)
createTagBody(2, {->
printHtmlPart(42)
loop:{
int i = 0
for( affiliateInstance in (accountInstance.affiliates) ) {
printHtmlPart(43)
invokeTag('render','g',486,['template':("affiliate"),'model':([affiliateInstance: affiliateInstance, i: i])],-1)
printHtmlPart(44)
i++
}
}
printHtmlPart(45)
})
invokeTag('formRemote','g',489,['id':("affiliateList"),'name':("affiliateList"),'url':([action: 'updateAffiliates', id: accountInstance.id]),'onSuccess':("alert(data)"),'onFailure':("alert('Невернные данные!')")],2)
printHtmlPart(46)
createClosureForHtmlPart(47, 2)
invokeTag('remoteLink','g',493,['action':("addAffiliate"),'onSuccess':("pushAffiliate(data)"),'params':("affiliateBlockCount()")],2)
printHtmlPart(48)
invokeTag('set','g',510,['var':("typeList"),'value':(ru.deloved.CategoryType.listOrderById())],-1)
printHtmlPart(49)
loop:{
int i = 0
for( typeInstance in (typeList) ) {
printHtmlPart(50)
expressionOut.print(i == 0 ? ' active' : '')
printHtmlPart(51)
expressionOut.print(typeInstance.code)
printHtmlPart(52)
invokeTag('message','g',523,['code':("categorytype.${typeInstance.code}")],-1)
printHtmlPart(53)
i++
}
}
printHtmlPart(54)
loop:{
int i = 0
for( typeInstance in (typeList) ) {
printHtmlPart(55)
expressionOut.print(i == 0 ? 'active' : '')
printHtmlPart(56)
expressionOut.print(typeInstance.code)
printHtmlPart(57)
expressionOut.print(typeInstance.code)
printHtmlPart(58)
i++
}
}
printHtmlPart(59)
loop:{
int i = 0
for( typeInstance in (typeList) ) {
printHtmlPart(60)
expressionOut.print(typeInstance.code)
printHtmlPart(61)
i++
}
}
printHtmlPart(62)
expressionOut.print(createLink(controller: 'account', action: 'editNew', id: accountInstance.id))
printHtmlPart(63)
loop:{
int i = 0
for( typeInstance in (typeList) ) {
printHtmlPart(64)
expressionOut.print(typeInstance.code)
printHtmlPart(65)
expressionOut.print(createLink(resource:accountInstance, action: 'cat'))
printHtmlPart(66)
expressionOut.print(Category.findByTypeAndParent(typeInstance, null).id)
printHtmlPart(67)
i++
}
}
printHtmlPart(68)
expressionOut.print(createLink(controller: 'account', action: 'editNew', id: accountInstance.id))
printHtmlPart(69)
invokeTag('render','g',686,['template':("/_common/crop")],-1)
printHtmlPart(0)
invokeTag('render','g',687,['template':("/_common/gallery-single")],-1)
printHtmlPart(0)
})
invokeTag('captureBody','sitemesh',688,[:],1)
printHtmlPart(70)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444124073664L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
