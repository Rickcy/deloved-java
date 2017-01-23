import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_items_item_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/items/_item.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (itemInstance?.photo)) {
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(2)
expressionOut.print(createLink(controller: 'files', action: 'item', id: itemInstance.photo?.imageThumb.id, params: [name: itemInstance.photo?.imageThumb.file]))
printHtmlPart(3)
})
invokeTag('link','g',9,['class':("gallery"),'url':([controller: 'files', action: 'item', id: itemInstance.photo?.image.id, params: [name: itemInstance.photo?.image.file]]),'data-gallery':("")],2)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (itemInstance.categoryType == 'GOODS')) {
printHtmlPart(6)
}
printHtmlPart(7)
createTagBody(1, {->
invokeTag('fieldValue','g',20,['bean':(itemInstance),'field':("name")],-1)
})
invokeTag('link','g',20,['controller':("goods"),'action':("item"),'id':(itemInstance.id)],1)
printHtmlPart(8)
invokeTag('fieldValue','g',24,['bean':(itemInstance),'field':("description")],-1)
printHtmlPart(9)
invokeTag('formatNumber','g',26,['number':(itemInstance?.price),'type':("currency"),'currencyCode':(itemInstance?.currency.code)],-1)
printHtmlPart(10)
expressionOut.print(itemInstance?.measure?.name)
printHtmlPart(11)
invokeTag('formatDate','g',28,['date':(itemInstance?.dateCreated),'format':(" dd.MM.yyyy HH:mm:ss")],-1)
printHtmlPart(12)
if(true && (freeAccount)) {
printHtmlPart(13)
}
else {
printHtmlPart(14)
createTagBody(2, {->
expressionOut.print(itemInstance?.account?.name)
})
invokeTag('link','g',42,['controller':("company"),'action':("index"),'id':(itemInstance?.account?.id)],2)
printHtmlPart(15)
if(true && (itemInstance?.account?.webAddress)) {
printHtmlPart(16)
expressionOut.print(itemInstance?.account?.webAddress)
printHtmlPart(17)
expressionOut.print(itemInstance?.account?.webAddress)
printHtmlPart(18)
}
printHtmlPart(15)
if(true && (itemInstance?.account?.email)) {
printHtmlPart(19)
expressionOut.print(itemInstance?.account?.email)
printHtmlPart(17)
expressionOut.print(itemInstance?.account?.email)
printHtmlPart(18)
}
printHtmlPart(15)
if(true && (itemInstance?.account?.phone1)) {
printHtmlPart(20)
expressionOut.print(itemInstance?.account?.phone1)
printHtmlPart(21)
}
printHtmlPart(15)
if(true && (itemInstance?.account?.city)) {
printHtmlPart(22)
expressionOut.print(itemInstance?.account?.city.name)
printHtmlPart(21)
}
printHtmlPart(15)
if(true && (itemInstance?.account?.address)) {
printHtmlPart(23)
expressionOut.print(itemInstance?.account?.address)
printHtmlPart(21)
}
printHtmlPart(1)
}
printHtmlPart(24)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1485145037548L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
