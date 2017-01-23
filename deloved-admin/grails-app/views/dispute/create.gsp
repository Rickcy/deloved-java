<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="Открытие Спора"/></title>
</head>

<body>

<div id="create-dispute" class="content scaffold-create" role="main">

	<h1><g:message code="Открытие Спора"/></h1>
	<g:if test="${freeAccount}">
		<g:render template="/_common/promo"/>
	</g:if>
	<g:else>

		<div class=lead>Спор с предприятем: <a href="${createLink(controller: 'account', action: 'show', id: partner?.id)}">${partner?.name}</a></div>

		<g:render template="/_common/create" model="[
				'objInstance' : disputeInstance,
				'formTemplate': 'new'
		]"/>
	</g:else>

</div>
</body>
</html>
