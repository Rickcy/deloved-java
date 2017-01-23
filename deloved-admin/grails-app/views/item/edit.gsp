<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="item.edit.label"/></title>
</head>

<body>
<div id="edit-item" class="content scaffold-edit" role="main">

	<h1><g:message code="${'item.edit.' + objInstance.categoryType.code + '.label'}" default="${objInstance.categoryType.code}"/></h1>

	<g:render template="/_common/edit" model="[
			'cancelMapping': objInstance.categoryType.code
	]"/>




	<g:render template="/_common/gallery-multi"/>
	<g:render template="/_common/crop"/>
</div>

</body>
</html>



