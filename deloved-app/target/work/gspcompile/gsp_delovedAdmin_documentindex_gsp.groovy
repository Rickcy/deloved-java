import ru.deloved.DocumentCategory
import ru.deloved.Document
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_documentindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/document/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("admin")],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',7,['code':("document.list.label")],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(0)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(0)
if(true && (freeAccount)) {
printHtmlPart(2)
invokeTag('render','g',12,['template':("/_common/promo")],-1)
printHtmlPart(0)
}
else {
printHtmlPart(4)
invokeTag('message','g',17,['code':("document.list.label")],-1)
printHtmlPart(5)
invokeTag('render','g',19,['template':("/_common/flash-message")],-1)
printHtmlPart(6)
invokeTag('set','g',21,['var':("editAllowed"),'value':(false)],-1)
printHtmlPart(7)
createTagBody(3, {->
printHtmlPart(8)
invokeTag('set','g',23,['var':("editAllowed"),'value':(true)],-1)
printHtmlPart(7)
})
invokeTag('ifAnyGranted','sec',24,['roles':("ROLE_ADMIN,ROLE_MANAGER")],3)
printHtmlPart(9)
loop:{
int i = 0
for( obj in (documentCategoryInstanceList) ) {
printHtmlPart(10)
expressionOut.print(obj.id)
printHtmlPart(11)
expressionOut.print(obj.id)
printHtmlPart(12)
expressionOut.print(obj.id)
printHtmlPart(13)
expressionOut.print(obj.id)
printHtmlPart(14)
expressionOut.print(fieldValue(bean: obj, field: "name"))
printHtmlPart(15)
expressionOut.print(obj.documents.size())
printHtmlPart(16)
if(true && (editAllowed)) {
printHtmlPart(17)
createClosureForHtmlPart(18, 5)
invokeTag('remoteLink','g',40,['method':("DELETE"),'action':("delcat"),'id':(obj.id),'onSuccess':("jQuery('#cat${obj.id}').remove()"),'onFailure':("onDeleteError(XMLHttpRequest)"),'before':("if(!confirm('Вы уверены?')) return false")],5)
printHtmlPart(19)
}
printHtmlPart(20)
expressionOut.print(obj.id)
printHtmlPart(21)
expressionOut.print(obj.id)
printHtmlPart(22)
loop:{
int di = 0
for( att in (obj.documents) ) {
printHtmlPart(23)
expressionOut.print(att.attachment.id)
printHtmlPart(24)
if(true && (editAllowed)) {
printHtmlPart(25)
createTagBody(6, {->
expressionOut.print(fieldValue(bean: att, field: "name"))
printHtmlPart(26)
})
invokeTag('link','g',63,['action':("edit"),'id':(att.id),'elementId':("gridRow${att.id}name"),'data-toggle':("modal"),'data-remote':(createLink(id: att.id, action: 'edit')),'data-target':("#myModal")],6)
printHtmlPart(27)
}
else {
printHtmlPart(25)
expressionOut.print(att.name)
printHtmlPart(27)
}
printHtmlPart(28)
expressionOut.print(createLink([action: 'download', id: att.id, params: [name: att.name]]))
printHtmlPart(29)
expressionOut.print(att.name)
printHtmlPart(30)
expressionOut.print(att.name)
printHtmlPart(31)
expressionOut.print(att.attachment.id)
printHtmlPart(32)
expressionOut.print(createLink([action: 'download', id: att.attachment.id, params: [name: att.attachment.name, preview: true]]))
printHtmlPart(33)
createTagBody(5, {->
printHtmlPart(34)
createClosureForHtmlPart(35, 6)
invokeTag('remoteLink','g',86,['action':("deleteatt"),'id':(att.attachment.id),'params':([name: att.attachment.name]),'onSuccess':("jQuery('#att${att.attachment.id}').remove()"),'onFailure':("onDeleteError(XMLHttpRequest)"),'before':("if(!confirm('Вы уверены?')) return false")],6)
printHtmlPart(36)
})
invokeTag('ifAnyGranted','sec',90,['roles':("ROLE_ADMIN,ROLE_MANAGER")],5)
printHtmlPart(37)
di++
}
}
printHtmlPart(38)
i++
}
}
printHtmlPart(39)
createTagBody(3, {->
printHtmlPart(40)
invokeTag('render','g',108,['template':("/_common/upload-multi"),'model':([
					objInstance: new Document(),
					attachList : attach
			])],-1)
printHtmlPart(41)
if(true && (!attach.isEmpty())) {
printHtmlPart(42)
}
printHtmlPart(8)
createTagBody(4, {->
printHtmlPart(43)
invokeTag('textField','g',116,['name':("name"),'required':(""),'class':("form-control")],-1)
printHtmlPart(44)
createClosureForHtmlPart(45, 5)
invokeTag('submitButton','g',118,['name':(message(code: 'category.button.new.label')),'class':("btn btn-success")],5)
printHtmlPart(8)
})
invokeTag('form','g',119,['class':("form-inline"),'name':("addForm"),'url':([controller: 'document', action: 'savecat'])],4)
printHtmlPart(8)
if(true && (DocumentCategory.count > 0)) {
printHtmlPart(46)
createTagBody(5, {->
printHtmlPart(47)
invokeTag('select','g',128,['class':("form-control"),'name':("id"),'from':(DocumentCategory.list()),'optionKey':("id"),'optionValue':("name")],-1)
printHtmlPart(48)
invokeTag('submitButton','g',130,['name':("Переместить"),'class':("btn btn-success")],-1)
printHtmlPart(49)
})
invokeTag('form','g',131,['class':("form-inline"),'name':("addForm"),'url':([controller: 'document', action: 'move'])],5)
printHtmlPart(8)
}
printHtmlPart(6)
})
invokeTag('ifAnyGranted','sec',134,['roles':("ROLE_ADMIN,ROLE_MANAGER")],3)
printHtmlPart(50)
invokeTag('render','g',137,['template':("/_common/edit-container")],-1)
printHtmlPart(0)
}
printHtmlPart(3)
})
invokeTag('captureBody','sitemesh',140,[:],1)
printHtmlPart(51)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1476438347112L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
