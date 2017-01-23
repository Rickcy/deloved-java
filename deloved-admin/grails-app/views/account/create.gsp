<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="account.create.label"/></title>
	<script src="//api-maps.yandex.ru/2.0-stable/?load=package.standard&lang=ru-RU" type="text/javascript"></script>
</head>

<body>

<div id="create-account" class="content scaffold-create" role="main">
	<h1><g:message code="account.create.label"/></h1>

	<g:render template="/_common/create" model="[
			'objInstance' : accountInstance
	]"/>

</div>
</body>
</html>
