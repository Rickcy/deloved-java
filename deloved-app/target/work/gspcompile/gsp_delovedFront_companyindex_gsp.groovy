import ru.deloved.Account
import ru.deloved.Profile
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_companyindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/company/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("front")],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('fieldValue','g',7,['field':("name"),'bean':(accountInstance)],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',8,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("keywords"),'content':(accountInstance?.keywords)],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',9,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("description"),'content':(accountInstance?.description)],-1)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',38,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('render','g',42,['template':("/_common/flash-message")],-1)
printHtmlPart(5)
if(true && (accountInstance.logoThumb)) {
printHtmlPart(6)
expressionOut.print(createLink(controller: 'files', action: 'logo', id: accountInstance.logoThumb?.id, params: [name: accountInstance.logoThumb?.file]))
printHtmlPart(7)
}
printHtmlPart(8)
invokeTag('fieldValue','g',64,['field':("name"),'bean':(accountInstance)],-1)
printHtmlPart(9)
if(true && (accountInstance?.orgForm)) {
printHtmlPart(10)
invokeTag('fieldValue','g',74,['bean':(accountInstance.orgForm),'field':("name")],-1)
printHtmlPart(11)
}
printHtmlPart(12)
invokeTag('fieldValue','g',81,['bean':(accountInstance),'field':("inn")],-1)
printHtmlPart(13)
invokeTag('fieldValue','g',85,['bean':(accountInstance),'field':("regNumber")],-1)
printHtmlPart(14)
createTagBody(2, {->
printHtmlPart(15)
expressionOut.print(accountInstance.rating ? accountInstance.rating + '%' : '-')
printHtmlPart(16)
invokeTag('rating','g',95,['value':(accountInstance.rating)],-1)
printHtmlPart(17)
if(true && (reviewsCount > 0)) {
printHtmlPart(18)
createTagBody(4, {->
printHtmlPart(19)
expressionOut.print(reviewsCount)
printHtmlPart(20)
})
invokeTag('link','g',101,['class':("otz"),'action':("reviews"),'data-toggle':("modal"),'data-remote':(createLink(id: accountInstance.id, action: 'reviews')),'data-target':("#reviewsModal")],4)
printHtmlPart(21)
}
else {
printHtmlPart(22)
}
printHtmlPart(23)
if(true && (accountInstance?.verifyStatus==false)) {
printHtmlPart(24)
expressionOut.print(accountInstance.rating ? accountInstance.rating + '%' : '-')
printHtmlPart(25)
invokeTag('rating','g',116,['value':(accountInstance.rating)],-1)
printHtmlPart(26)
}
printHtmlPart(0)
})
invokeTag('ifLoggedIn','sec',128,[:],2)
printHtmlPart(27)
createClosureForHtmlPart(28, 2)
invokeTag('ifNotLoggedIn','sec',146,[:],2)
printHtmlPart(29)
if(true && (itsMyAccount)) {
printHtmlPart(30)
if(true && (freeAccount)) {
printHtmlPart(31)
}
else {
printHtmlPart(32)
}
printHtmlPart(33)
}
else {
printHtmlPart(34)
createTagBody(3, {->
printHtmlPart(35)
createClosureForHtmlPart(36, 4)
invokeTag('link','g',204,['class':("deal_button"),'controller':("login"),'action':("auth"),'data-toggle':("modal"),'data-remote':(createLink(controller:'login' , action: 'auth')),'data-target':("#myModal")],4)
printHtmlPart(37)
createClosureForHtmlPart(38, 4)
invokeTag('link','g',211,['class':("review_button"),'controller':("login"),'action':("auth"),'data-toggle':("modal"),'data-remote':(createLink(controller:'login' , action: 'auth')),'data-target':("#myModal")],4)
printHtmlPart(39)
createTagBody(4, {->
printHtmlPart(40)
expressionOut.print(resource(dir: 'images', file: 'front/mediation_ultra.png'))
printHtmlPart(41)
})
invokeTag('link','g',221,['class':("claim_button"),'controller':("login"),'action':("auth"),'data-toggle':("modal"),'data-remote':(createLink(controller:'login' , action: 'auth')),'data-target':("#myModal")],4)
printHtmlPart(10)
createClosureForHtmlPart(42, 4)
invokeTag('link','g',227,['class':("jud_button"),'controller':("login"),'action':("auth"),'data-toggle':("modal"),'data-remote':(createLink(controller:'login' , action: 'auth')),'data-target':("#myModal")],4)
printHtmlPart(43)
})
invokeTag('ifNotLoggedIn','sec',230,[:],3)
printHtmlPart(11)
createTagBody(3, {->
printHtmlPart(35)
createClosureForHtmlPart(36, 4)
invokeTag('link','g',233,['class':("deal_button"),'controller':("deal"),'action':("create"),'params':([partner: accountInstance.id])],4)
printHtmlPart(35)
createClosureForHtmlPart(38, 4)
invokeTag('link','g',234,['class':("review_button"),'controller':("deal"),'action':("index"),'params':([to: accountInstance.id,var: val])],4)
printHtmlPart(44)
createTagBody(4, {->
printHtmlPart(40)
expressionOut.print(resource(dir: 'images', file: 'front/mediation_ultra.png'))
printHtmlPart(41)
})
invokeTag('link','g',236,['class':("claim_button"),'controller':("deal"),'action':("index"),'params':([partner: accountInstance.id,rar:rar])],4)
printHtmlPart(35)
createClosureForHtmlPart(42, 4)
invokeTag('link','g',237,['class':("jud_button"),'controller':("deal"),'action':("index"),'params':([partner: accountInstance.id,sal:sal])],4)
printHtmlPart(34)
})
invokeTag('ifLoggedIn','sec',238,[:],3)
printHtmlPart(45)
}
printHtmlPart(46)
if(true && (accountInstance?.verifyStatus==false)) {
printHtmlPart(47)
expressionOut.print(resource(dir: 'images', file: 'front/mediation_ultra.png'))
printHtmlPart(48)
}
printHtmlPart(49)
if(true && (accountInstance?.verifyStatus==false)) {
printHtmlPart(50)
}
printHtmlPart(51)
if(true && (accountInstance?.description)) {
printHtmlPart(52)
if(true && (accountInstance.description.length() >220)) {
printHtmlPart(53)
createTagBody(4, {->
printHtmlPart(54)
expressionOut.print(tail)
printHtmlPart(55)
})
invokeTag('truncate','g',282,['max':("220"),'words':("true"),'tail':(""),'value':(accountInstance.description),'varTail':("tail")],4)
printHtmlPart(10)
}
else {
printHtmlPart(53)
invokeTag('fieldValue','g',286,['bean':(accountInstance),'field':("description")],-1)
printHtmlPart(35)
}
printHtmlPart(56)
}
printHtmlPart(57)
if(true && (goods.size()>20)) {
printHtmlPart(58)
if(true && (!goods.isEmpty())) {
printHtmlPart(59)
createClosureForHtmlPart(60, 4)
invokeTag('link','g',304,['url':([controller: 'goods', params: [company: accountInstance.id]])],4)
printHtmlPart(61)
for( item in (goods) ) {
printHtmlPart(62)
createTagBody(5, {->
printHtmlPart(63)
if(true && (item?.photo)) {
printHtmlPart(64)
expressionOut.print(createLink(controller: 'files', action: 'item', id: item.photo?.imageThumb.id, params: [name: item.photo?.imageThumb.file]))
printHtmlPart(65)
}
else {
printHtmlPart(66)
expressionOut.print(resource(dir: 'images', file: 'front/goods.png'))
printHtmlPart(67)
}
printHtmlPart(68)
expressionOut.print(item.name ?: '')
printHtmlPart(69)
expressionOut.print(item.price)
printHtmlPart(70)
expressionOut.print(item.currency.name)
printHtmlPart(71)
})
invokeTag('link','g',323,['url':([controller: 'goods', action: 'item', id: item.id])],5)
printHtmlPart(72)
}
printHtmlPart(73)
}
printHtmlPart(2)
}
else {
printHtmlPart(58)
if(true && (!goods.isEmpty())) {
printHtmlPart(59)
createClosureForHtmlPart(60, 4)
invokeTag('link','g',347,['url':([controller: 'goods', params: [company: accountInstance.id]])],4)
printHtmlPart(74)
for( item in (goods) ) {
printHtmlPart(75)
createTagBody(5, {->
printHtmlPart(63)
if(true && (item?.photo)) {
printHtmlPart(64)
expressionOut.print(createLink(controller: 'files', action: 'item', id: item.photo?.imageThumb.id, params: [name: item.photo?.imageThumb.file]))
printHtmlPart(65)
}
else {
printHtmlPart(66)
expressionOut.print(resource(dir: 'images', file: 'front/goods.png'))
printHtmlPart(67)
}
printHtmlPart(68)
expressionOut.print(item.name ?: '')
printHtmlPart(69)
expressionOut.print(item.price)
printHtmlPart(70)
expressionOut.print(item.currency.name)
printHtmlPart(71)
})
invokeTag('link','g',364,['url':([controller: 'goods', action: 'item', id: item.id])],5)
printHtmlPart(76)
}
printHtmlPart(77)
}
printHtmlPart(2)
}
printHtmlPart(78)
if(true && (services.size()>20)) {
printHtmlPart(58)
if(true && (!services.isEmpty())) {
printHtmlPart(79)
createClosureForHtmlPart(80, 4)
invokeTag('link','g',378,['url':([controller: 'services', params: [company: accountInstance.id]])],4)
printHtmlPart(81)
for( good in (services) ) {
printHtmlPart(82)
createTagBody(5, {->
printHtmlPart(63)
if(true && (good?.photo)) {
printHtmlPart(83)
expressionOut.print(createLink(controller: 'files', action: 'item', id: good.photo?.imageThumb.id, params: [name: good.photo?.imageThumb.file]))
printHtmlPart(84)
}
else {
printHtmlPart(66)
expressionOut.print(resource(dir: 'images', file: 'front/services.png'))
printHtmlPart(67)
}
printHtmlPart(85)
expressionOut.print(good.name ?: '')
printHtmlPart(69)
expressionOut.print(good.price)
printHtmlPart(70)
expressionOut.print(good.currency.name)
printHtmlPart(71)
})
invokeTag('link','g',396,['url':([controller: 'services', action: 'item', id: good.id])],5)
printHtmlPart(86)
}
printHtmlPart(87)
}
printHtmlPart(2)
}
else {
printHtmlPart(58)
if(true && (!services.isEmpty())) {
printHtmlPart(79)
createClosureForHtmlPart(80, 4)
invokeTag('link','g',422,['url':([controller: 'services', params: [company: accountInstance.id]])],4)
printHtmlPart(88)
for( good in (services) ) {
printHtmlPart(82)
createTagBody(5, {->
printHtmlPart(63)
if(true && (good?.photo)) {
printHtmlPart(83)
expressionOut.print(createLink(controller: 'files', action: 'item', id: good.photo?.imageThumb.id, params: [name: good.photo?.imageThumb.file]))
printHtmlPart(84)
}
else {
printHtmlPart(66)
expressionOut.print(resource(dir: 'images', file: 'front/services.png'))
printHtmlPart(67)
}
printHtmlPart(85)
expressionOut.print(good.name ?: '')
printHtmlPart(69)
expressionOut.print(good.price)
printHtmlPart(70)
expressionOut.print(good.currency.name)
printHtmlPart(71)
})
invokeTag('link','g',440,['url':([controller: 'services', action: 'item', id: good.id])],5)
printHtmlPart(89)
}
printHtmlPart(77)
}
printHtmlPart(78)
}
printHtmlPart(78)
if(true && (!goods.isEmpty()||!services.isEmpty())) {
printHtmlPart(58)
createTagBody(3, {->
printHtmlPart(79)
createClosureForHtmlPart(90, 4)
invokeTag('link','g',454,['url':([controller: 'adcontent',action: 'index2', params: [company: accountInstance.name]])],4)
printHtmlPart(91)
})
invokeTag('ifLoggedIn','sec',455,[:],3)
printHtmlPart(58)
createTagBody(3, {->
printHtmlPart(92)
createClosureForHtmlPart(90, 4)
invokeTag('link','g',462,['controller':("login"),'action':("auth"),'data-toggle':("modal"),'data-remote':(createLink(controller:'login' , action: 'auth')),'data-target':("#myModal")],4)
printHtmlPart(58)
})
invokeTag('ifNotLoggedIn','sec',463,[:],3)
printHtmlPart(78)
}
printHtmlPart(93)
expressionOut.print(resource(dir: 'images', file: 'front/local.png'))
printHtmlPart(94)
if(true && (accountInstance?.city)) {
printHtmlPart(95)
expressionOut.print(accountInstance?.city?.name)
printHtmlPart(96)
}
printHtmlPart(0)
createTagBody(2, {->
printHtmlPart(97)
if(true && (accountInstance?.address)) {
printHtmlPart(34)
invokeTag('fieldValue','g',481,['bean':(accountInstance),'field':("address")],-1)
printHtmlPart(98)
}
printHtmlPart(99)
})
invokeTag('ifLoggedIn','sec',485,[:],2)
printHtmlPart(18)
if(true && (freeAccount)) {
printHtmlPart(18)
}
else {
printHtmlPart(100)
expressionOut.print(accountInstance?.city?.name)
printHtmlPart(101)
expressionOut.print(accountInstance?.address)
printHtmlPart(102)
expressionOut.print(accountInstance?.city?.name)
if(true && (accountInstance?.address)) {
printHtmlPart(103)
invokeTag('fieldValue','g',548,['bean':(accountInstance),'field':("address")],-1)
}
printHtmlPart(104)
}
printHtmlPart(105)
if(true && (accountInstance.affiliates)) {
printHtmlPart(18)
createClosureForHtmlPart(106, 3)
invokeTag('ifLoggedIn','sec',571,[:],3)
printHtmlPart(58)
createTagBody(3, {->
printHtmlPart(18)
createClosureForHtmlPart(107, 4)
invokeTag('link','g',577,['class':("btn-primary btn"),'style':("width: 100%"),'controller':("login"),'action':("auth"),'data-toggle':("modal"),'data-remote':(createLink(controller:'login' , action: 'auth')),'data-target':("#myModal")],4)
printHtmlPart(21)
})
invokeTag('ifNotLoggedIn','sec',579,[:],3)
printHtmlPart(108)
loop:{
int i = 0
for( aff in (accountInstance.affiliates) ) {
printHtmlPart(109)
if(true && (aff.address)) {
printHtmlPart(110)
expressionOut.print(aff.address)
printHtmlPart(111)
}
printHtmlPart(112)
if(true && (aff.city)) {
printHtmlPart(113)
expressionOut.print(aff.city.name)
printHtmlPart(111)
}
printHtmlPart(112)
if(true && (aff.phone)) {
printHtmlPart(114)
expressionOut.print(aff.phone)
printHtmlPart(111)
}
printHtmlPart(112)
if(true && (aff.email)) {
printHtmlPart(115)
expressionOut.print(aff.email)
printHtmlPart(111)
}
printHtmlPart(116)
i++
}
}
printHtmlPart(117)
}
printHtmlPart(118)
createTagBody(2, {->
printHtmlPart(2)
if(true && (accountInstance?.phone1)) {
printHtmlPart(119)
expressionOut.print(resource(dir: 'images', file: 'front/tel.png'))
printHtmlPart(120)
invokeTag('fieldValue','g',643,['bean':(accountInstance),'field':("phone1")],-1)
printHtmlPart(121)
}
printHtmlPart(122)
if(true && (accountInstance?.email)) {
printHtmlPart(123)
invokeTag('fieldValue','g',655,['bean':(accountInstance),'field':("email")],-1)
printHtmlPart(124)
}
printHtmlPart(125)
})
invokeTag('ifLoggedIn','sec',661,[:],2)
printHtmlPart(126)
invokeTag('fieldValue','g',668,['bean':(accountInstance),'field':("manager")],-1)
printHtmlPart(127)
if(true && (accountInstance?.workTime)) {
printHtmlPart(18)
invokeTag('fieldValue','g',678,['bean':(accountInstance),'field':("workTime")],-1)
printHtmlPart(128)
}
printHtmlPart(129)
if(true && (freeAccount)) {
printHtmlPart(34)
invokeTag('render','g',691,['template':("/_common/promo-modal")],-1)
printHtmlPart(18)
}
printHtmlPart(130)
invokeTag('render','g',702,['template':("/_common/modal"),'model':([
		container: 'reviewsContainer',
		modalId  : 'reviewsModal'
])],-1)
printHtmlPart(131)
})
invokeTag('captureBody','sitemesh',706,[:],1)
printHtmlPart(132)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1485145037339L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
