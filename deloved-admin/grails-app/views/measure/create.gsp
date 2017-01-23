<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="measure.create.label"/></title>
</head>

<body>
<div id="create-measure" class="content scaffold-create" role="main">

	<h1><g:message code="measure.create.label"/></h1>

	<g:render template="/_common/create" model="[
			'objInstance': measureInstance
	]"/>

</div>
</body>
</html>
