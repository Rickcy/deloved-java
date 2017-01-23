import ru.deloved.Item
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_itemshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/item/show.gsp" }
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
invokeTag('message','g',6,['code':("item.show.label")],-1)
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
invokeTag('message','g',12,['code':("item.show.label")],-1)
printHtmlPart(5)
invokeTag('render','g',14,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
if(true && (itemInstance?.account)) {
printHtmlPart(7)
invokeTag('message','g',20,['code':("item.account.label"),'default':("Account")],-1)
printHtmlPart(8)
createTagBody(3, {->
expressionOut.print(itemInstance?.account?.encodeAsHTML())
})
invokeTag('link','g',22,['controller':("account"),'action':("show"),'id':(itemInstance?.account?.id)],3)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (itemInstance?.name)) {
printHtmlPart(11)
invokeTag('message','g',29,['code':("item.name.label"),'default':("Name")],-1)
printHtmlPart(12)
invokeTag('fieldValue','g',31,['bean':(itemInstance),'field':("name")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (itemInstance?.kind)) {
printHtmlPart(13)
invokeTag('message','g',38,['code':("item.kind.label"),'default':("Kind")],-1)
printHtmlPart(14)
invokeTag('fieldValue','g',40,['bean':(itemInstance),'field':("kind")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (itemInstance?.price)) {
printHtmlPart(15)
invokeTag('message','g',47,['code':("item.price.label"),'default':("Price")],-1)
printHtmlPart(16)
invokeTag('fieldValue','g',49,['bean':(itemInstance),'field':("price")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (itemInstance?.measure)) {
printHtmlPart(17)
invokeTag('message','g',56,['code':("item.measure.label"),'default':("Measure")],-1)
printHtmlPart(18)
createTagBody(3, {->
expressionOut.print(itemInstance?.measure?.encodeAsHTML())
})
invokeTag('link','g',58,['controller':("measure"),'action':("show"),'id':(itemInstance?.measure?.id)],3)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (itemInstance?.measureValue)) {
printHtmlPart(19)
invokeTag('message','g',65,['code':("item.measureValue.label"),'default':("Measure Value")],-1)
printHtmlPart(20)
invokeTag('fieldValue','g',67,['bean':(itemInstance),'field':("measureValue")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (itemInstance?.photo)) {
printHtmlPart(21)
invokeTag('message','g',74,['code':("item.photo.label"),'default':("Photo")],-1)
printHtmlPart(22)
createTagBody(3, {->
expressionOut.print(itemInstance?.photo?.encodeAsHTML())
})
invokeTag('link','g',76,['controller':("attachment"),'action':("show"),'id':(itemInstance?.photo?.id)],3)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (itemInstance?.photoThumb)) {
printHtmlPart(23)
invokeTag('message','g',83,['code':("item.photoThumb.label"),'default':("Photo Thumb")],-1)
printHtmlPart(24)
createTagBody(3, {->
expressionOut.print(itemInstance?.photoThumb?.encodeAsHTML())
})
invokeTag('link','g',86,['controller':("attachment"),'action':("show"),'id':(itemInstance?.photoThumb?.id)],3)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (itemInstance?.description)) {
printHtmlPart(25)
invokeTag('message','g',93,['code':("item.description.label"),'default':("Description")],-1)
printHtmlPart(26)
invokeTag('fieldValue','g',95,['bean':(itemInstance),'field':("description")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (itemInstance?.availability)) {
printHtmlPart(27)
invokeTag('message','g',102,['code':("item.availability.label"),'default':("Availability")],-1)
printHtmlPart(28)
invokeTag('fieldValue','g',104,['bean':(itemInstance),'field':("availability")],-1)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (itemInstance?.dateCreated)) {
printHtmlPart(29)
invokeTag('message','g',111,['code':("item.dateCreated.label"),'default':("Date Created")],-1)
printHtmlPart(30)
invokeTag('formatDate','g',113,['date':(itemInstance?.dateCreated)],-1)
printHtmlPart(9)
}
printHtmlPart(31)
createTagBody(2, {->
printHtmlPart(32)
createTagBody(3, {->
invokeTag('message','g',121,['code':("default.button.edit.label"),'default':("Edit")],-1)
})
invokeTag('link','g',121,['class':("edit"),'action':("edit"),'resource':(itemInstance)],3)
printHtmlPart(33)
invokeTag('actionSubmit','g',123,['class':("delete"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(34)
})
invokeTag('form','g',125,['url':([resource: itemInstance, action: 'delete']),'method':("DELETE")],2)
printHtmlPart(35)
})
invokeTag('captureBody','sitemesh',127,[:],1)
printHtmlPart(36)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1478073106820L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
