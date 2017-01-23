<%@ page import="ru.deloved.billing.TariffPrice" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="tariff.create.label"/></title>
</head>

<body>
<div id="create-tariff" class="content scaffold-create" role="main">

	<h1><g:message code="tariff.create.label"/></h1>

	<g:render template="/_common/create" model="[
			'objInstance': tariffInstance
	]"/>

</div>
</body>
</html>
