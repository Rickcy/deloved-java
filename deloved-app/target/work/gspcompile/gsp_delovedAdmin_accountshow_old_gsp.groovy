import ru.deloved.Account
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_accountshow_old_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/account/show-old.gsp" }
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
invokeTag('message','g',6,['code':("account.show.label")],-1)
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
invokeTag('message','g',13,['code':("account.show.label")],-1)
printHtmlPart(5)
invokeTag('render','g',15,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
if(true && (accountInstance?.address)) {
printHtmlPart(7)
invokeTag('message','g',21,['code':("account.address.label"),'default':("Address")],-1)
printHtmlPart(8)
invokeTag('fieldValue','g',23,['bean':(accountInstance),'field':("address")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.city)) {
printHtmlPart(11)
invokeTag('message','g',30,['code':("account.city.label"),'default':("City")],-1)
printHtmlPart(12)
expressionOut.print(accountInstance?.city?.name)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.dateCreated)) {
printHtmlPart(13)
invokeTag('message','g',39,['code':("account.dateCreated.label"),'default':("Date Created")],-1)
printHtmlPart(14)
invokeTag('formatDate','g',41,['date':(accountInstance?.dateCreated)],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.description)) {
printHtmlPart(15)
invokeTag('message','g',48,['code':("account.description.label"),'default':("Description")],-1)
printHtmlPart(16)
invokeTag('fieldValue','g',50,['bean':(accountInstance),'field':("description")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.fax1)) {
printHtmlPart(17)
invokeTag('message','g',57,['code':("account.fax1.label"),'default':("Fax1")],-1)
printHtmlPart(18)
invokeTag('fieldValue','g',59,['bean':(accountInstance),'field':("fax1")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.fax2)) {
printHtmlPart(19)
invokeTag('message','g',66,['code':("account.fax2.label"),'default':("Fax2")],-1)
printHtmlPart(20)
invokeTag('fieldValue','g',68,['bean':(accountInstance),'field':("fax2")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.inn)) {
printHtmlPart(21)
invokeTag('message','g',75,['code':("account.inn.label"),'default':("Inn")],-1)
printHtmlPart(22)
invokeTag('fieldValue','g',77,['bean':(accountInstance),'field':("inn")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.keywords)) {
printHtmlPart(23)
invokeTag('message','g',84,['code':("account.keywords.label"),'default':("Keywords")],-1)
printHtmlPart(24)
invokeTag('fieldValue','g',86,['bean':(accountInstance),'field':("keywords")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.kpp)) {
printHtmlPart(25)
invokeTag('message','g',93,['code':("account.kpp.label"),'default':("Kpp")],-1)
printHtmlPart(26)
invokeTag('fieldValue','g',95,['bean':(accountInstance),'field':("kpp")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.legalAddress)) {
printHtmlPart(27)
invokeTag('message','g',102,['code':("account.legalAddress.label"),'default':("Legal Address")],-1)
printHtmlPart(28)
invokeTag('fieldValue','g',104,['bean':(accountInstance),'field':("legalAddress")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.logo)) {
printHtmlPart(29)
invokeTag('message','g',111,['code':("account.logo.label"),'default':("Logo")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.manager)) {
printHtmlPart(30)
invokeTag('message','g',118,['code':("account.manager.label"),'default':("Manager")],-1)
printHtmlPart(31)
invokeTag('fieldValue','g',120,['bean':(accountInstance),'field':("manager")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.name)) {
printHtmlPart(32)
invokeTag('message','g',127,['code':("account.name.label"),'default':("Name")],-1)
printHtmlPart(33)
invokeTag('fieldValue','g',129,['bean':(accountInstance),'field':("name")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.orgForm)) {
printHtmlPart(34)
invokeTag('message','g',136,['code':("account.orgForm.label"),'default':("Org Form")],-1)
printHtmlPart(35)
invokeTag('fieldValue','g',138,['bean':(accountInstance),'field':("orgForm.name")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.phone1)) {
printHtmlPart(36)
invokeTag('message','g',145,['code':("account.phone1.label"),'default':("Phone1")],-1)
printHtmlPart(37)
invokeTag('fieldValue','g',147,['bean':(accountInstance),'field':("phone1")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.phone2)) {
printHtmlPart(38)
invokeTag('message','g',154,['code':("account.phone2.label"),'default':("Phone2")],-1)
printHtmlPart(39)
invokeTag('fieldValue','g',156,['bean':(accountInstance),'field':("phone2")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.phone3)) {
printHtmlPart(40)
invokeTag('message','g',163,['code':("account.phone3.label"),'default':("Phone3")],-1)
printHtmlPart(41)
invokeTag('fieldValue','g',165,['bean':(accountInstance),'field':("phone3")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.publicStatus)) {
printHtmlPart(42)
invokeTag('message','g',172,['code':("account.publicStatus.label"),'default':("Public Status")],-1)
printHtmlPart(43)
invokeTag('fieldValue','g',174,['bean':(accountInstance),'field':("publicStatus")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.regDate)) {
printHtmlPart(44)
invokeTag('message','g',181,['code':("account.regDate.label"),'default':("Reg Date")],-1)
printHtmlPart(45)
invokeTag('formatDate','g',183,['date':(accountInstance?.regDate)],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.regNumber)) {
printHtmlPart(46)
invokeTag('message','g',190,['code':("account.regNumber.label"),'default':("Reg Number")],-1)
printHtmlPart(47)
invokeTag('fieldValue','g',192,['bean':(accountInstance),'field':("regNumber")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.webAddress)) {
printHtmlPart(48)
invokeTag('message','g',199,['code':("account.webAddress.label"),'default':("Web Address")],-1)
printHtmlPart(49)
invokeTag('fieldValue','g',201,['bean':(accountInstance),'field':("webAddress")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (accountInstance?.workTime)) {
printHtmlPart(50)
invokeTag('message','g',208,['code':("account.workTime.label"),'default':("Work Time")],-1)
printHtmlPart(51)
invokeTag('fieldValue','g',210,['bean':(accountInstance),'field':("workTime")],-1)
printHtmlPart(9)
}
printHtmlPart(52)
createTagBody(2, {->
printHtmlPart(53)
createTagBody(3, {->
invokeTag('message','g',218,['code':("default.button.edit.label"),'default':("Edit")],-1)
})
invokeTag('link','g',218,['class':("btn btn-primary btn-lg"),'action':("edit"),'resource':(accountInstance)],3)
printHtmlPart(54)
invokeTag('actionSubmit','g',220,['class':("btn btn-warning btn-lg"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(55)
})
invokeTag('form','g',223,['url':([resource: accountInstance, action: 'delete']),'method':("DELETE")],2)
printHtmlPart(56)
})
invokeTag('captureBody','sitemesh',225,[:],1)
printHtmlPart(57)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444124073667L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
