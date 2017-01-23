<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="currency.create.label"/></title>
</head>

<body>
<div id="create-currency" class="content scaffold-create" role="main">

	<h1><g:message code="currency.create.label"/></h1>

	<g:render template="/_common/create" model="[
			'objInstance': currencyInstance
	]"/>

</div>
</body>
</html>
