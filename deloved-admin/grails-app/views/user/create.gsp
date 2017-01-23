<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="user.create.label"/></title>
</head>

<body>
<div id="create-user" class="content scaffold-create" role="main">

	<h1><g:message code="user.create.label"/></h1>

	<g:render template="/_common/create" model="[
			'objInstance' : userCreateCommandInstance,
			'formTemplate': 'new'
	]"/>

</div>
</body>
</html>


