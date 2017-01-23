import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-front', version='0.1')
class gsp_delovedFront_companiesindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-front-0.1/grails-app/views/companies/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("front")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',7,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("keywords"),'content':(categoryFilterData?.category?.name)],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',8,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("description"),'content':(categoryFilterData?.category?.name)],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
printHtmlPart(3)
if(true && (categoryFilterData.category)) {
printHtmlPart(4)
expressionOut.print(categoryFilterData.category.name)
printHtmlPart(5)
}
printHtmlPart(6)
})
invokeTag('captureTitle','sitemesh',15,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',15,[:],2)
printHtmlPart(2)
invokeTag('stylesheet','asset',16,['src':("front_not_main.css")],-1)
printHtmlPart(2)
invokeTag('javascript','asset',17,['src':("front_not_main.js")],-1)
printHtmlPart(7)
})
invokeTag('captureHead','sitemesh',21,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(8)
invokeTag('render','g',39,['template':("/_common/category-filter"),'model':([
						categoryFilterData: categoryFilterData
				])],-1)
printHtmlPart(9)
if(true && (categoryFilterData.category)) {
printHtmlPart(10)
expressionOut.print(categoryFilterData.category.name)
printHtmlPart(11)
invokeTag('render','g',53,['template':("/_common/category-breadcrumb"),'model':([
					categoryFilterData: categoryFilterData
			])],-1)
printHtmlPart(12)
loop:{
int i = 0
for( obj in (accountInstanceList) ) {
printHtmlPart(13)
invokeTag('render','g',56,['template':("item"),'model':([accountInstance: obj])],-1)
printHtmlPart(4)
i++
}
}
printHtmlPart(12)
if(true && (params.max < rowsCount)) {
printHtmlPart(13)
invokeTag('paginate','g',60,['total':(rowsCount ?: 0)],-1)
printHtmlPart(4)
}
printHtmlPart(14)
}
else {
printHtmlPart(15)
for( obj in (lastCreated) ) {
printHtmlPart(16)
invokeTag('render','g',69,['template':("item"),'model':([accountInstance: obj])],-1)
printHtmlPart(17)
}
printHtmlPart(12)
if(true && (params.max < rowsCount)) {
printHtmlPart(13)
invokeTag('paginate','g',77,['total':(rowsCount ?: 0)],-1)
printHtmlPart(4)
}
printHtmlPart(5)
}
printHtmlPart(18)
if(true && (mainAccounts.size()>3)) {
printHtmlPart(2)
if(true && (!mainAccounts.isEmpty())) {
printHtmlPart(19)
for( accountInstance in (mainAccounts) ) {
printHtmlPart(20)
createTagBody(5, {->
printHtmlPart(21)
if(true && (accountInstance.logoThumb)) {
printHtmlPart(22)
expressionOut.print(createLink(controller: 'files', action: 'logo', id: accountInstance.logoThumb?.id, params: [name: accountInstance.logoThumb?.file]))
printHtmlPart(23)
}
else {
printHtmlPart(24)
expressionOut.print(resource(dir: 'images', file: 'front/logo_uch.png'))
printHtmlPart(25)
}
printHtmlPart(26)
expressionOut.print(accountInstance.name)
printHtmlPart(27)
})
invokeTag('link','g',117,['url':([controller: 'company', id: accountInstance.id])],5)
printHtmlPart(28)
}
printHtmlPart(29)
}
printHtmlPart(30)
}
else {
printHtmlPart(13)
if(true && (!mainAccounts.isEmpty())) {
printHtmlPart(31)
for( accountInstance in (mainAccounts) ) {
printHtmlPart(32)
createTagBody(5, {->
printHtmlPart(33)
if(true && (accountInstance.logoThumb)) {
printHtmlPart(34)
expressionOut.print(createLink(controller: 'files', action: 'logo', id: accountInstance.logoThumb?.id, params: [name: accountInstance.logoThumb?.file]))
printHtmlPart(35)
}
else {
printHtmlPart(36)
expressionOut.print(resource(dir: 'images', file: 'front/logo_uch.png'))
printHtmlPart(37)
}
printHtmlPart(38)
expressionOut.print(accountInstance.name)
printHtmlPart(39)
})
invokeTag('link','g',188,['url':([controller: 'company', id: accountInstance.id])],5)
printHtmlPart(40)
}
printHtmlPart(41)
}
printHtmlPart(4)
}
printHtmlPart(42)
invokeTag('render','g',205,['template':("/_common/modal"),'model':([
		container: 'reviewsContainer',
		modalId  : 'reviewsModal'
])],-1)
printHtmlPart(43)
expressionOut.print(resource(dir: 'images', file: 'front/Arrow.png'))
printHtmlPart(44)
})
invokeTag('captureBody','sitemesh',209,[:],1)
printHtmlPart(45)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1485145037275L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
