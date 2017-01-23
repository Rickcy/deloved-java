<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="content.create.label"/></title>
	<style>
		.title,.description,.keywords{
		display:none}
	</style>

</head>

<body>
<div id="create-content" class="content scaffold-create" role="main">

	<h1><g:message code="content.create.label"/></h1>


	<div class="row">
	<g:render template="/_common/create" model="[
			'objInstance': contentInstance
	]"/>
	</div>
</div>
</body>
</html>
