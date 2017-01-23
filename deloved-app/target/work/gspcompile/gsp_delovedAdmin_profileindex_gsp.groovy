import org.joda.time.LocalDate
import  ru.deloved.Profile
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_profileindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/profile/index.gsp" }
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
invokeTag('message','g',6,['code':("profile.list.label")],-1)
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
invokeTag('message','g',12,['code':("profile.list.label")],-1)
printHtmlPart(5)
invokeTag('render','g',14,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(7)
invokeTag('select','g',27,['class':("form-control"),'name':("role"),'from':(ru.deloved.Role.list()),'optionKey':("id"),'optionValue':({ message(code: 'role.' + it.authority, default: it.authority) }),'value':(filter.role),'noSelection':(['': message(code: 'role.allroles.label')])],-1)
printHtmlPart(8)
invokeTag('select','g',36,['class':("form-control"),'name':("enabled"),'from':([true, false]),'valueMessagePrefix':("user.activity"),'value':(filter.enabled),'noSelection':(['': message(code: 'user.activity.all')])],-1)
printHtmlPart(9)
invokeTag('select','g',44,['class':("form-control"),'name':("charge"),'from':([0, 1, 2]),'valueMessagePrefix':("profile.chargeFilter"),'value':(filter.charge),'noSelection':(['': message(code: 'profile.chargeFilter.all')])],-1)
printHtmlPart(10)
invokeTag('textField','g',47,['placeholder':("Login"),'class':("form-control"),'name':("username"),'value':(filter.username)],-1)
printHtmlPart(11)
expressionOut.print(regionFilterData.selectedRegion?.id)
printHtmlPart(12)
expressionOut.print(raw(regionFilterData.regionsTreeJson))
printHtmlPart(13)
invokeTag('submitButton','g',61,['name':("filterAction"),'class':("btn btn-primary"),'value':(message(code: 'default.button.filter.label'))],-1)
printHtmlPart(14)
invokeTag('submitButton','g',62,['name':("resetAction"),'class':("btn btn-default"),'formaction':("reset"),'value':(message(code: 'default.button.filterReset.label'))],-1)
printHtmlPart(15)
})
invokeTag('form','g',67,['url':([controller: 'profile', action: 'index'])],2)
printHtmlPart(16)
invokeTag('message','g',73,['code':("profile.username.label")],-1)
printHtmlPart(17)
invokeTag('sortableColumn','g',76,['property':("fio"),'title':(message(code: 'profile.fio.label'))],-1)
printHtmlPart(18)
invokeTag('sortableColumn','g',78,['property':("city"),'title':(message(code: 'profile.city.label'))],-1)
printHtmlPart(18)
if(true && (!filter.role)) {
printHtmlPart(14)
invokeTag('sortableColumn','g',81,['property':("user.role"),'title':(message(code: 'user.role.label'))],-1)
printHtmlPart(19)
}
printHtmlPart(20)
loop:{
int i = 0
for( profileInstance in (profileInstanceList) ) {
printHtmlPart(21)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(22)
createTagBody(3, {->
expressionOut.print(fieldValue(bean: profileInstance, field: "user.username"))
})
invokeTag('link','g',96,['action':("edit"),'id':(profileInstance.id),'data-toggle':("modal"),'data-remote':(createLink(id: profileInstance.id, action: 'edit')),'data-target':("#myModal")],3)
printHtmlPart(23)
expressionOut.print(profileInstance.id)
printHtmlPart(24)
expressionOut.print(fieldValue(bean: profileInstance, field: "fio"))
printHtmlPart(25)
expressionOut.print(profileInstance.id)
printHtmlPart(26)
expressionOut.print(profileInstance.city?.name)
printHtmlPart(27)
if(true && (!filter.role)) {
printHtmlPart(28)
expressionOut.print(message(code: 'role.' + profileInstance.user.role.authority))
printHtmlPart(27)
}
printHtmlPart(29)
i++
}
}
printHtmlPart(30)
if(true && (params.max < rowsCount)) {
printHtmlPart(31)
invokeTag('paginate','g',115,['total':(rowsCount ?: 0)],-1)
printHtmlPart(1)
}
printHtmlPart(32)
invokeTag('render','g',120,['template':("/_common/edit-container")],-1)
printHtmlPart(2)
invokeTag('render','g',121,['template':("/_common/gallery-single")],-1)
printHtmlPart(2)
invokeTag('render','g',122,['template':("/_common/crop")],-1)
printHtmlPart(3)
})
invokeTag('captureBody','sitemesh',124,[:],1)
printHtmlPart(33)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1478583622017L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
