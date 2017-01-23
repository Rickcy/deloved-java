<%@ page import="ru.deloved.Account" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="account.edit.label"/></title>
	<script src="//api-maps.yandex.ru/2.0-stable/?load=package.standard&lang=ru-RU" type="text/javascript"></script>
</head>

<body>
<div id="edit-account" class="content scaffold-edit" role="main">

	<h1><g:message code="account.edit.label"/></h1>

	<g:render template="/_common/edit"/>

</div>

<g:render template="/_common/gallery-single"/>
<g:render template="/_common/crop"/>

</body>
</html>
