<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="category.create.label"/></title>
</head>

<body>
<div id="create-category" class="content scaffold-create" role="main">

	<h1><g:message code="category.create.label"/></h1>

	<g:render template="/_common/create" model="[
			'objInstance': categoryCreateCommandInstance,
			'hiddenExtra': [parentId: parent.id]
	]"/>

</div>
</body>
</html>
