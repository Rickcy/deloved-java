import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_frontindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/front/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("front")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('titleContent','g',5,['code':("TDKContent")],-1)
})
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(1)
invokeTag('descKeyContent','g',6,['code':("TDKContent")],-1)
printHtmlPart(2)
invokeTag('javascript','asset',9,['src':("/mCustomScrollbar/jquery.mCustomScrollbar.concat.min.js")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',10,['src':("/frontend.js")],-1)
printHtmlPart(3)
invokeTag('link','asset',12,['rel':("stylesheet"),'href':("/mCustomScrollbar/jquery.mCustomScrollbar.min.css")],-1)
printHtmlPart(1)
invokeTag('link','asset',13,['rel':("stylesheet"),'href':("/main.css")],-1)
printHtmlPart(1)
invokeTag('link','asset',14,['rel':("buttons"),'href':("/mCustomScrollbar/mCSB_buttons.png")],-1)
printHtmlPart(4)
createClosureForHtmlPart(5, 2)
invokeTag('link','g',55,['style':("text-decoration: underline"),'controller':("article"),'action':("news"),'params':([var:val])],2)
printHtmlPart(6)
})
invokeTag('captureHead','sitemesh',166,[:],1)
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(8)
expressionOut.print(resource(dir: 'images', file: 'preloader.gif'))
printHtmlPart(9)
invokeTag('render','g',178,['template':("/_common/flash-message")],-1)
printHtmlPart(10)
createTagBody(2, {->
printHtmlPart(11)
expressionOut.print(resource(dir: 'images', file: 'front/icon_deal.png'))
printHtmlPart(12)
})
invokeTag('link','g',196,['style':("color: white;"),'controller':("article"),'action':("deal_online")],2)
printHtmlPart(13)
createTagBody(2, {->
printHtmlPart(14)
expressionOut.print(resource(dir: 'images', file: 'front/icon_rating.png'))
printHtmlPart(15)
})
invokeTag('link','g',197,['style':("color: white;text-shadow: 0 0 1px whitesmoke;"),'controller':("article"),'action':("rating_system")],2)
printHtmlPart(16)
createTagBody(2, {->
printHtmlPart(14)
expressionOut.print(resource(dir: 'images', file: 'front/icon_jurist.png'))
printHtmlPart(17)
})
invokeTag('link','g',198,['style':("color: white;text-shadow: 0 0 1px whitesmoke"),'controller':("article"),'action':("jurist_service")],2)
printHtmlPart(13)
createTagBody(2, {->
printHtmlPart(14)
expressionOut.print(resource(dir: 'images', file: 'front/icon_sud.png'))
printHtmlPart(18)
})
invokeTag('link','g',199,['style':("color: white;text-shadow: 0 0 1px whitesmoke"),'controller':("article"),'action':("judge_service")],2)
printHtmlPart(16)
createTagBody(2, {->
printHtmlPart(14)
expressionOut.print(resource(dir: 'images', file: 'front/icon_mediation.png'))
printHtmlPart(19)
})
invokeTag('link','g',200,['style':("color: white;text-shadow: 0 0 1px whitesmoke"),'controller':("article"),'action':("mediation_service")],2)
printHtmlPart(20)
expressionOut.print(resource(dir: 'images', file: 'front/videow.png'))
printHtmlPart(21)
invokeTag('render','g',212,['template':("video"),'model':([])],-1)
printHtmlPart(22)
createTagBody(2, {->
printHtmlPart(23)
expressionOut.print(resource(dir: 'images', file: 'front/videoq.png'))
printHtmlPart(24)
expressionOut.print(resource(dir: 'images', file: 'front/on_btn.png'))
printHtmlPart(25)
})
invokeTag('link','g',221,['controller':("signup"),'action':("index")],2)
printHtmlPart(26)
expressionOut.print(resource(dir: 'images', file: 'front/dea.png'))
printHtmlPart(27)
expressionOut.print(resource(dir: 'images', file: 'front/rating_ultra.png'))
printHtmlPart(28)
expressionOut.print(resource(dir: 'images', file: 'front/sud_ultra.png'))
printHtmlPart(29)
expressionOut.print(resource(dir: 'images', file: 'front/hammer.png'))
printHtmlPart(30)
expressionOut.print(resource(dir: 'images', file: 'front/mediation_ultra.png'))
printHtmlPart(31)
expressionOut.print(resource(dir: 'images', file: 'front/deal_online.jpg'))
printHtmlPart(32)
expressionOut.print(resource(dir: 'images', file: 'front/rating.jpg'))
printHtmlPart(33)
expressionOut.print(resource(dir: 'images', file: 'front/jurist.jpg'))
printHtmlPart(34)
expressionOut.print(resource(dir: 'images', file: 'front/sud.jpg'))
printHtmlPart(35)
expressionOut.print(resource(dir: 'images', file: 'front/mediation.jpg'))
printHtmlPart(36)
expressionOut.print(resource(dir: 'images', file: 'front/playlist.png'))
printHtmlPart(37)
if(true && (mainAccounts!=null)) {
printHtmlPart(38)
if(true && (mainAccounts.size()>5)) {
printHtmlPart(39)
for( accountInstance in (mainAccounts) ) {
printHtmlPart(40)
createTagBody(5, {->
printHtmlPart(41)
if(true && (accountInstance.logoThumb)) {
printHtmlPart(42)
expressionOut.print(createLink(controller: 'files', action: 'logo', id: accountInstance.logoThumb?.id, params: [name: accountInstance.logoThumb?.file]))
printHtmlPart(43)
}
else {
printHtmlPart(44)
expressionOut.print(resource(dir: 'images', file: 'front/logo_uch.png'))
printHtmlPart(45)
}
printHtmlPart(46)
expressionOut.print(accountInstance.name)
printHtmlPart(47)
})
invokeTag('link','g',311,['url':([controller: 'company', id: accountInstance.id])],5)
printHtmlPart(48)
}
printHtmlPart(49)
}
else {
printHtmlPart(50)
for( accountInstance in (mainAccounts) ) {
printHtmlPart(51)
createTagBody(5, {->
printHtmlPart(41)
if(true && (accountInstance.logoThumb)) {
printHtmlPart(42)
expressionOut.print(createLink(controller: 'files', action: 'logo', id: accountInstance.logoThumb?.id, params: [name: accountInstance.logoThumb?.file]))
printHtmlPart(43)
}
else {
printHtmlPart(44)
expressionOut.print(resource(dir: 'images', file: 'front/logo_uch.png'))
printHtmlPart(52)
}
printHtmlPart(46)
expressionOut.print(accountInstance.name)
printHtmlPart(47)
})
invokeTag('link','g',361,['url':([controller: 'company', id: accountInstance.id])],5)
printHtmlPart(48)
}
printHtmlPart(53)
}
printHtmlPart(54)
}
printHtmlPart(55)
if(true && (mainGoods!=null&&mainGoods.size()>5)) {
printHtmlPart(56)
for( item in (mainGoods) ) {
printHtmlPart(57)
createTagBody(4, {->
printHtmlPart(41)
if(true && (item?.photo)) {
printHtmlPart(58)
expressionOut.print(createLink(controller: 'files', action: 'item', id: item.photo?.imageThumb.id, params: [name: item.photo?.imageThumb.file]))
printHtmlPart(59)
}
else {
printHtmlPart(44)
expressionOut.print(resource(dir: 'images', file: 'front/goods.png'))
printHtmlPart(60)
}
printHtmlPart(61)
expressionOut.print(item.name ?: '')
printHtmlPart(62)
expressionOut.print(item.price)
printHtmlPart(63)
expressionOut.print(item.currency.name)
printHtmlPart(64)
})
invokeTag('link','g',403,['url':([controller: 'goods', action: 'item', id: item.id])],4)
printHtmlPart(65)
}
printHtmlPart(66)
}
printHtmlPart(67)
if(true && (mainServices!=null&&mainServices.size()>3)) {
printHtmlPart(68)
for( item in (mainServices) ) {
printHtmlPart(69)
createTagBody(4, {->
printHtmlPart(41)
if(true && (item?.photo)) {
printHtmlPart(58)
expressionOut.print(createLink(controller: 'files', action: 'item', id: item.photo?.imageThumb.id, params: [name: item.photo?.imageThumb.file]))
printHtmlPart(59)
}
else {
printHtmlPart(44)
expressionOut.print(resource(dir: 'images', file: 'front/services.png'))
printHtmlPart(70)
}
printHtmlPart(61)
expressionOut.print(item.name ?: '')
printHtmlPart(62)
expressionOut.print(item.price)
printHtmlPart(63)
expressionOut.print(item.currency.name)
printHtmlPart(64)
})
invokeTag('link','g',433,['url':([controller: 'services', action: 'item', id: item.id])],4)
printHtmlPart(71)
}
printHtmlPart(38)
}
printHtmlPart(72)
if(true && (mainServices!=null&&mainServices.size()>3||mainGoods!=null&&mainGoods.size()>5)) {
printHtmlPart(73)
}
printHtmlPart(74)
if(true && (mainGoods!=null&&mainGoods.size()<=5)) {
printHtmlPart(68)
for( item in (mainGoods) ) {
printHtmlPart(69)
createTagBody(4, {->
printHtmlPart(41)
if(true && (item?.photo)) {
printHtmlPart(75)
expressionOut.print(createLink(controller: 'files', action: 'item', id: item.photo?.imageThumb.id, params: [name: item.photo?.imageThumb.file]))
printHtmlPart(59)
}
else {
printHtmlPart(44)
expressionOut.print(resource(dir: 'images', file: 'front/goods.png'))
printHtmlPart(60)
}
printHtmlPart(61)
expressionOut.print(item.name ?: '')
printHtmlPart(62)
expressionOut.print(item.price)
printHtmlPart(63)
expressionOut.print(item.currency.name)
printHtmlPart(64)
})
invokeTag('link','g',480,['url':([controller: 'goods', action: 'item', id: item.id])],4)
printHtmlPart(76)
}
printHtmlPart(38)
}
printHtmlPart(38)
if(true && (mainServices!=null&&mainServices.size()<=3)) {
printHtmlPart(68)
for( item in (mainServices) ) {
printHtmlPart(57)
createTagBody(4, {->
printHtmlPart(41)
if(true && (item?.photo)) {
printHtmlPart(58)
expressionOut.print(createLink(controller: 'files', action: 'item', id: item.photo?.imageThumb.id, params: [name: item.photo?.imageThumb.file]))
printHtmlPart(59)
}
else {
printHtmlPart(44)
expressionOut.print(resource(dir: 'images', file: 'front/services.png'))
printHtmlPart(70)
}
printHtmlPart(61)
expressionOut.print(item.name ?: '')
printHtmlPart(62)
expressionOut.print(item.price)
printHtmlPart(63)
expressionOut.print(item.currency.name)
printHtmlPart(64)
})
invokeTag('link','g',505,['url':([controller: 'services', action: 'item', id: item.id])],4)
printHtmlPart(76)
}
printHtmlPart(38)
}
printHtmlPart(77)
invokeTag('render','g',534,['template':("newsTemlate")],-1)
printHtmlPart(78)
createClosureForHtmlPart(79, 2)
invokeTag('link','g',549,['controller':("article"),'action':("news")],2)
printHtmlPart(80)
expressionOut.print(resource(dir: 'images', file: 'front/Dol.png'))
printHtmlPart(81)
expressionOut.print(resource(dir: 'images', file: 'front/Ev.png'))
printHtmlPart(82)
invokeTag('contContent','g',618,['code':("TDKContent")],-1)
printHtmlPart(83)
})
invokeTag('captureBody','sitemesh',632,[:],1)
printHtmlPart(84)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1485145037461L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
