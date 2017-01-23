import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_delovedApp_oldold_header_frontend_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/old/old-header-frontend.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('form','g',4,['id':("logoutForm"),'url':([controller: 'logout']),'method':("POST")],-1)
printHtmlPart(2)
createClosureForHtmlPart(3, 2)
invokeTag('link','g',5,['target':("_blank"),'class':("btn btn-default btn-sm"),'controller':("panel")],2)
printHtmlPart(4)
})
invokeTag('ifLoggedIn','sec',7,[:],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(2)
createClosureForHtmlPart(6, 2)
invokeTag('link','g',9,['controller':("panel"),'class':("btn btn-default btn-sm")],2)
printHtmlPart(5)
})
invokeTag('ifNotLoggedIn','sec',10,[:],1)
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(8)
expressionOut.print(resource(dir: 'images', file: 'front/logo.gif'))
printHtmlPart(9)
})
invokeTag('link','g',14,['controller':("front")],1)
printHtmlPart(10)
createClosureForHtmlPart(11, 1)
invokeTag('set','g',18,['var':("section")],1)
printHtmlPart(2)
if(true && (params.controller in ['companies', 'company'])) {
printHtmlPart(12)
createClosureForHtmlPart(11, 2)
invokeTag('set','g',20,['var':("section")],2)
printHtmlPart(2)
}
else if(true && (params.controller in ['goods'])) {
printHtmlPart(12)
createClosureForHtmlPart(13, 2)
invokeTag('set','g',23,['var':("section")],2)
printHtmlPart(2)
}
else if(true && (params.controller in ['services'])) {
printHtmlPart(12)
createClosureForHtmlPart(14, 2)
invokeTag('set','g',26,['var':("section")],2)
printHtmlPart(2)
}
printHtmlPart(15)
createClosureForHtmlPart(16, 1)
invokeTag('link','g',29,['class':((section == 'companies') ? 'active' : ''),'url':([controller: 'companies'])],1)
createTagBody(1, {->
invokeTag('message','g',30,['code':("goods.list.label")],-1)
})
invokeTag('link','g',30,['class':((section == 'goods') ? 'active' : ''),'url':([controller: 'goods'])],1)
createTagBody(1, {->
invokeTag('message','g',32,['code':("services.list.label")],-1)
})
invokeTag('link','g',32,['class':((section == 'services') ? 'active' : ''),'url':([controller: 'services'])],1)
printHtmlPart(17)
createTagBody(1, {->
printHtmlPart(18)
invokeTag('textField','g',41,['required':(""),'name':("search"),'class':("form-control"),'value':(params.search)],-1)
printHtmlPart(19)
})
invokeTag('form','g',46,['controller':(section),'action':("search"),'method':("POST")],1)
printHtmlPart(20)
createClosureForHtmlPart(21, 1)
invokeTag('link','g',50,['controller':(section),'action':("search"),'class':("search_link")],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444124074519L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
