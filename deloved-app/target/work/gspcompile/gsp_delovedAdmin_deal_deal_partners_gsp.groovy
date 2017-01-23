import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_deal_deal_partners_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/deal/_deal-partners.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
if(true && (dealInstance.account.logo)) {
printHtmlPart(2)
expressionOut.print(createLink(controller: 'account', action: 'logo', id: dealInstance.account.id, params: [name: dealInstance.account?.logo?.file, type: 'main']))
printHtmlPart(3)
}
else {
printHtmlPart(2)
expressionOut.print(resource(dir: 'image', file: 'admin/avatar.jpg'))
printHtmlPart(4)
}
printHtmlPart(5)
expressionOut.print(dealInstance.account.orgForm.code + " \"" + dealInstance.account.name + "\"")
printHtmlPart(6)
})
invokeTag('link','g',21,['controller':("company"),'id':(dealInstance.account.id)],1)
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(8)
if(true && (dealInstance.partner.logo)) {
printHtmlPart(2)
expressionOut.print(createLink(controller: 'account', action: 'logo', id: dealInstance.partner.id, params: [name: dealInstance.partner?.logo?.file, type: 'main']))
printHtmlPart(9)
}
else {
printHtmlPart(2)
expressionOut.print(resource(dir: 'image', file: 'admin/avatar.jpg'))
printHtmlPart(4)
}
printHtmlPart(5)
expressionOut.print(dealInstance.partner.orgForm.code + " \"" + dealInstance.partner.name + "\"")
printHtmlPart(6)
})
invokeTag('link','g',38,['controller':("company"),'id':(dealInstance.partner.id)],1)
printHtmlPart(10)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1484024800311L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
