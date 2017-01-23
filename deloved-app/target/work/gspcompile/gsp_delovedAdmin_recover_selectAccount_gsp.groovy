import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_recover_selectAccount_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/recover/_selectAccount.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (accountProfiles)) {
printHtmlPart(1)
expressionOut.print(email)
printHtmlPart(2)
createTagBody(2, {->
printHtmlPart(3)
expressionOut.print(email)
printHtmlPart(4)
for( accountProfile in (accountProfiles) ) {
printHtmlPart(5)
expressionOut.print(accountProfile.profile.id)
printHtmlPart(6)
expressionOut.print(accountProfile.profile.id)
printHtmlPart(7)
expressionOut.print(accountProfile.profile.id)
printHtmlPart(8)
expressionOut.print(createLink(controller: 'files', action: 'logo', id: accountProfile.account.logoThumb?.id, params: [name: accountProfile.account?.logoThumb?.file]))
printHtmlPart(9)
expressionOut.print(accountProfile.account.orgForm.code)
printHtmlPart(10)
expressionOut.print(accountProfile.account.name)
printHtmlPart(11)
expressionOut.print(accountProfile.account.inn)
printHtmlPart(12)
}
printHtmlPart(13)
createClosureForHtmlPart(14, 3)
invokeTag('link','g',67,['url':([controller: 'login', action: 'auth']),'class':("btn btn-default")],3)
printHtmlPart(15)
})
invokeTag('formRemote','g',70,['name':("selectRecover"),'url':([controller: 'recover', action: 'selectAccount']),'update':("recoverContent"),'method':("POST")],2)
printHtmlPart(16)
}
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444124073862L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
