import ru.deloved.Measure
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_measureindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/measure/index.gsp" }
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
invokeTag('message','g',6,['code':("measure.list.label")],-1)
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
invokeTag('message','g',12,['code':("measure.list.label")],-1)
printHtmlPart(5)
invokeTag('render','g',14,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
createTagBody(2, {->
invokeTag('message','g',17,['code':("measure.button.new.label")],-1)
})
invokeTag('link','g',17,['class':("btn btn-success"),'action':("create")],2)
printHtmlPart(7)
expressionOut.print(message(code: 'default.button.delete.confirm.message'))
printHtmlPart(8)
invokeTag('message','g',30,['code':("default.button.actionSelect.label")],-1)
printHtmlPart(9)
invokeTag('message','g',33,['code':("measure.button.delete.label")],-1)
printHtmlPart(10)
createTagBody(2, {->
printHtmlPart(11)
invokeTag('select','g',47,['class':("form-control"),'name':("ctype"),'from':(ru.deloved.CategoryType.list()),'optionKey':("id"),'optionValue':({ message(code: 'categorytype.' + it.code, default: it.code) }),'value':(filter.ctype),'noSelection':(['': message(code: 'categorytype.alltypes.label')])],-1)
printHtmlPart(12)
invokeTag('textField','g',50,['placeholder':("Name"),'class':("form-control"),'name':("mname"),'value':(filter.mname)],-1)
printHtmlPart(13)
invokeTag('submitButton','g',53,['name':("filterAction"),'class':("btn btn-primary"),'value':(message(code: 'default.button.filter.label'))],-1)
printHtmlPart(14)
invokeTag('submitButton','g',54,['name':("resetAction"),'class':("btn btn-default"),'formaction':("reset"),'value':(message(code: 'default.button.filterReset.label'))],-1)
printHtmlPart(15)
})
invokeTag('form','g',59,['url':([controller: 'measure', action: 'index'])],2)
printHtmlPart(16)
createTagBody(2, {->
printHtmlPart(17)
invokeTag('hiddenField','g',62,['name':("batch_action"),'value':("")],-1)
printHtmlPart(18)
invokeTag('sortableColumn','g',68,['property':("name"),'title':(message(code: 'measure.name.label', default: 'Name'))],-1)
printHtmlPart(14)
invokeTag('sortableColumn','g',69,['property':("fullname"),'title':(message(code: 'measure.fullname.label', default: 'Fullname'))],-1)
printHtmlPart(19)
invokeTag('message','g',70,['code':("measure.type.label"),'default':("Type")],-1)
printHtmlPart(20)
loop:{
int i = 0
for( obj in (measureInstanceList) ) {
printHtmlPart(21)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(22)
expressionOut.print(obj.id)
printHtmlPart(23)
createTagBody(4, {->
expressionOut.print(fieldValue(bean: obj, field: "name"))
})
invokeTag('link','g',85,['action':("edit"),'id':(obj.id),'elementId':("gridRow${obj.id}name"),'data-toggle':("modal"),'data-remote':(createLink(id: obj.id, action: 'edit')),'data-target':("#myModal")],4)
printHtmlPart(24)
expressionOut.print(obj.id)
printHtmlPart(25)
expressionOut.print(fieldValue(bean: obj, field: "fullname"))
printHtmlPart(26)
expressionOut.print(obj.id)
printHtmlPart(27)
expressionOut.print(message(code: 'categorytype.' + obj.type.code, default: obj.type.code))
printHtmlPart(28)
i++
}
}
printHtmlPart(29)
if(true && (params.max < rowsCount)) {
printHtmlPart(30)
invokeTag('paginate','g',96,['total':(rowsCount ?: 0)],-1)
printHtmlPart(17)
}
printHtmlPart(1)
})
invokeTag('form','g',98,['id':("batch_form"),'url':([controller: 'measure', action: 'batch']),'method':("POST")],2)
printHtmlPart(31)
invokeTag('render','g',102,['template':("/_common/edit-container")],-1)
printHtmlPart(3)
})
invokeTag('captureBody','sitemesh',104,[:],1)
printHtmlPart(32)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1460086664823L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
