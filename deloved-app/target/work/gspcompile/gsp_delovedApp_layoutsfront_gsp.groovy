import grails.plugin.springsecurity.SpringSecurityUtils
import  org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_delovedApp_layoutsfront_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/front.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('layoutTitle','g',7,['default':("Бизнес-портал Деловед")],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',8,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("viewport"),'content':("width=device-width, initial-scale=1.0")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',9,['src':("front.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',10,['src':("deloved.css")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',11,['src':("front.js")],-1)
printHtmlPart(2)
expressionOut.print(createLinkTo(dir: 'images', file: 'favicon/favicon.ico'))
printHtmlPart(3)
invokeTag('layoutHead','g',15,[:],-1)
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
expressionOut.print(createLink([controller: 'panel', action: 'getAccount']))
printHtmlPart(6)
})
invokeTag('ifLoggedIn','sec',69,[:],2)
printHtmlPart(7)
expressionOut.print(createLink(controller: 'front',action: 'index'))
printHtmlPart(8)
expressionOut.print(createLink(controller: 'panel',action: 'index'))
printHtmlPart(9)
expressionOut.print(createLink(controller: 'front', action: 'index'))
printHtmlPart(10)
})
invokeTag('captureHead','sitemesh',316,[:],1)
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(12)
invokeTag('render','g',321,['template':("/_common/activation-warning")],-1)
printHtmlPart(4)
invokeTag('render','g',322,['template':("/_common/flash-message")],-1)
printHtmlPart(13)
createTagBody(2, {->
printHtmlPart(14)
createClosureForHtmlPart(15, 3)
invokeTag('link','g',332,['class':("navbar-brand"),'style':("font-size: 11pt;margin-right: 7%;margin-left:2%;color: black"),'controller':("panel"),'action':("index")],3)
printHtmlPart(16)
})
invokeTag('ifLoggedIn','sec',334,[:],2)
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(14)
createClosureForHtmlPart(17, 3)
invokeTag('link','g',341,['class':("navbar-brand loginBtn"),'style':("font-size: 11pt;margin-right: 7%;margin-left:2%;color: black"),'controller':("login"),'action':("auth"),'data-toggle':("modal"),'data-remote':(createLink(controller:'login' , action: 'auth')),'data-target':("#myModal")],3)
printHtmlPart(18)
})
invokeTag('ifNotLoggedIn','sec',343,[:],2)
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(14)
invokeTag('form','g',346,['id':("logoutForm"),'url':([controller: 'logout', action: 'index']),'method':("POST")],-1)
printHtmlPart(19)
})
invokeTag('ifLoggedIn','sec',347,[:],2)
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(14)
createClosureForHtmlPart(20, 3)
invokeTag('link','g',350,['class':("navbar-brand registert"),'style':("font-size: 11pt;color: black"),'controller':("signup"),'action':("index")],3)
printHtmlPart(1)
})
invokeTag('ifNotLoggedIn','sec',350,[:],2)
printHtmlPart(21)
expressionOut.print(resource(dir: 'images', file: 'front/Dol.png'))
printHtmlPart(22)
expressionOut.print(resource(dir: 'images', file: 'front/Ev.png'))
printHtmlPart(23)
createClosureForHtmlPart(24, 2)
invokeTag('set','g',359,['var':("section")],2)
printHtmlPart(25)
if(true && (params.controller in ['companies', 'company'])) {
printHtmlPart(26)
createClosureForHtmlPart(24, 3)
invokeTag('set','g',361,['var':("section")],3)
printHtmlPart(25)
}
else if(true && (params.controller in ['goods'])) {
printHtmlPart(26)
createClosureForHtmlPart(27, 3)
invokeTag('set','g',364,['var':("section")],3)
printHtmlPart(25)
}
else if(true && (params.controller in ['services'])) {
printHtmlPart(26)
createClosureForHtmlPart(28, 3)
invokeTag('set','g',367,['var':("section")],3)
printHtmlPart(25)
}
printHtmlPart(29)
createClosureForHtmlPart(30, 2)
invokeTag('link','g',372,['class':("${(section == 'companies') ? 'active' : ''} nav-item nav-link"),'url':([controller: 'companies'])],2)
printHtmlPart(1)
createClosureForHtmlPart(31, 2)
invokeTag('link','g',373,['class':("${(section == 'goods') ? 'active' : ''} nav-item nav-link"),'url':([controller: 'goods'])],2)
printHtmlPart(1)
createClosureForHtmlPart(32, 2)
invokeTag('link','g',374,['class':("${(section == 'services') ? 'active' : ''} nav-item nav-link"),'url':([controller: 'services'])],2)
printHtmlPart(1)
createClosureForHtmlPart(33, 2)
invokeTag('link','g',375,['class':(" nav-item nav-link"),'url':([controller: 'front', action:'about'])],2)
printHtmlPart(1)
createClosureForHtmlPart(34, 2)
invokeTag('link','g',376,['class':("nav-item nav-link"),'url':([controller: 'front', action:'tariffs'])],2)
printHtmlPart(35)
createTagBody(2, {->
printHtmlPart(36)
expressionOut.print(resource(dir: 'images', file: 'front/logo.gif'))
printHtmlPart(37)
})
invokeTag('link','g',387,['controller':("front")],2)
printHtmlPart(38)
createTagBody(2, {->
printHtmlPart(39)
invokeTag('textField','g',394,['required':(""),'name':("search"),'class':("form-control"),'style':("height: 45px;font-size: 15pt;box-shadow: inset 0 0 15px #e2e2e2"),'value':(""),'placeholder':("Поиск")],-1)
printHtmlPart(40)
})
invokeTag('form','g',396,['class':("pull-sm-right"),'controller':(section),'action':("search"),'method':("POST")],2)
printHtmlPart(41)
createClosureForHtmlPart(42, 2)
invokeTag('link','g',402,['controller':(section),'action':("search"),'class':("search_link")],2)
printHtmlPart(43)
createTagBody(2, {->
printHtmlPart(36)
expressionOut.print(resource(dir: 'images', file: 'front/logo.gif'))
printHtmlPart(44)
})
invokeTag('link','g',415,['controller':("front")],2)
printHtmlPart(45)
createClosureForHtmlPart(24, 2)
invokeTag('set','g',417,['var':("section")],2)
printHtmlPart(14)
if(true && (params.controller in ['companies', 'company'])) {
printHtmlPart(25)
createClosureForHtmlPart(24, 3)
invokeTag('set','g',420,['var':("section")],3)
printHtmlPart(14)
}
else if(true && (params.controller in ['goods'])) {
printHtmlPart(25)
createClosureForHtmlPart(27, 3)
invokeTag('set','g',423,['var':("section")],3)
printHtmlPart(14)
}
else if(true && (params.controller in ['services'])) {
printHtmlPart(25)
createClosureForHtmlPart(28, 3)
invokeTag('set','g',426,['var':("section")],3)
printHtmlPart(14)
}
printHtmlPart(46)
createClosureForHtmlPart(47, 2)
invokeTag('link','g',431,['class':((section == 'companies') ? 'active' : ''),'url':([controller: 'companies'])],2)
printHtmlPart(48)
createClosureForHtmlPart(49, 2)
invokeTag('link','g',432,['class':((section == 'goods') ? 'active' : ''),'url':([controller: 'goods'])],2)
printHtmlPart(48)
createClosureForHtmlPart(50, 2)
invokeTag('link','g',433,['class':((section == 'services') ? 'active' : ''),'url':([controller: 'services'])],2)
printHtmlPart(48)
createClosureForHtmlPart(51, 2)
invokeTag('link','g',434,['class':("about"),'url':([controller: 'front', action:'about'])],2)
printHtmlPart(48)
createClosureForHtmlPart(52, 2)
invokeTag('link','g',435,['class':("about"),'url':([controller: 'front', action:'tariffs'])],2)
printHtmlPart(53)
createTagBody(2, {->
printHtmlPart(54)
invokeTag('textField','g',445,['required':(""),'name':("search"),'class':("form-control"),'style':("height: 45px;font-size: 15pt;box-shadow: inset 0 0 15px #e2e2e2"),'value':(""),'placeholder':("Поиск")],-1)
printHtmlPart(40)
})
invokeTag('form','g',447,['controller':(section),'action':("search"),'method':("POST")],2)
printHtmlPart(55)
createClosureForHtmlPart(42, 2)
invokeTag('link','g',455,['controller':(section),'action':("search"),'class':("search_link")],2)
printHtmlPart(56)
invokeTag('layoutBody','g',465,[:],-1)
printHtmlPart(57)
invokeTag('render','g',465,['template':("/layouts/footer")],-1)
printHtmlPart(11)
invokeTag('deferredScripts','asset',469,[:],-1)
printHtmlPart(11)
invokeTag('render','g',471,['template':("/_common/edit-container")],-1)
printHtmlPart(4)
})
invokeTag('captureBody','sitemesh',473,['style':("width: 95%;margin: 0 auto")],1)
printHtmlPart(58)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1484901568062L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
