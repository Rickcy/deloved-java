<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="region.create.label"/></title>
</head>

<body>
<div id="create-region" class="content scaffold-create" role="main">

	<h1><g:message code="region.create.label"/></h1>

	<g:render template="/_common/create" model="[
			'objInstance': regionCreateCommandInstance
	]"/>

</div>
</body>
</html>
