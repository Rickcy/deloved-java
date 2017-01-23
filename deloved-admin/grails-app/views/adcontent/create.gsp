<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="adcontent.create.label"/></title>
</head>

<body>
<div id="create-adcontent" class="content scaffold-create" role="main">

	<h1><g:message code="adcontent.create.label"/></h1>

	<g:render template="/_common/create" model="[
			'objInstance' : adcontentInstance,
			'formTemplate': 'new'
	]"/>

</div>
</body>
</html>


