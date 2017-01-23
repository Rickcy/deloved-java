<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title><g:layoutTitle/></title>
	<asset:stylesheet src="auth.css"/>
	<asset:javascript src="front.js"/>
	<asset:javascript src="application.js"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<g:layoutHead/>
</head>

<body>
<g:layoutBody/>
<div class="footer" role="contentinfo"></div>

<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>

<asset:deferredScripts/>
</body>
</html>