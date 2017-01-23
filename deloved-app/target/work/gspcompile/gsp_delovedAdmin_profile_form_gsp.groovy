import ru.deloved.Profile
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='deloved-admin', version='0.1')
class gsp_delovedAdmin_profile_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/deloved-admin-0.1/grails-app/views/profile/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (regEditAllowed)) {
printHtmlPart(1)
invokeTag('message','g',7,['code':("profile.tab.main")],-1)
printHtmlPart(2)
if(true && (regEditAllowed)) {
printHtmlPart(3)
invokeTag('set','g',10,['var':("countryList"),'value':(ru.deloved.Region.findAllByParent(null))],-1)
printHtmlPart(4)
invokeTag('message','g',11,['code':("profile.tab.regions")],-1)
printHtmlPart(5)
}
printHtmlPart(6)
invokeTag('render','g',17,['template':("formfields")],-1)
printHtmlPart(7)
if(true && (regEditAllowed)) {
printHtmlPart(8)
loop:{
int i = 0
for( countryInstance in (countryList) ) {
printHtmlPart(9)
expressionOut.print(i == 0 ? ' active' : '')
printHtmlPart(10)
expressionOut.print(countryInstance.id)
printHtmlPart(11)
expressionOut.print(countryInstance.name)
printHtmlPart(12)
i++
}
}
printHtmlPart(13)
loop:{
int i = 0
for( countryInstance in (countryList) ) {
printHtmlPart(14)
expressionOut.print(i == 0 ? 'active' : '')
printHtmlPart(15)
expressionOut.print(countryInstance.id)
printHtmlPart(16)
expressionOut.print(countryInstance.id)
printHtmlPart(17)
i++
}
}
printHtmlPart(18)
}
printHtmlPart(19)
if(true && (regEditAllowed)) {
printHtmlPart(20)
loop:{
int i = 0
for( countryInstance in (countryList) ) {
printHtmlPart(21)
expressionOut.print(countryInstance.id)
printHtmlPart(22)
i++
}
}
printHtmlPart(23)
loop:{
int i = 0
for( countryInstance in (countryList) ) {
printHtmlPart(24)
expressionOut.print(countryInstance.id)
printHtmlPart(25)
expressionOut.print(createLink(id: params.id, controller: 'profile', action: 'reg'))
printHtmlPart(26)
expressionOut.print(countryInstance.id)
printHtmlPart(27)
i++
}
}
printHtmlPart(28)
}
printHtmlPart(29)
}
else {
printHtmlPart(30)
invokeTag('render','g',98,['template':("formfields")],-1)
printHtmlPart(31)
}
printHtmlPart(0)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1435115993171L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
