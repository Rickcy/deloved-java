<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="Создание иска"/></title>
</head>

<body>

<div id="create-claim" class="content scaffold-create" role="main">

	<h1><g:message code="Создание иска"/></h1>

	<g:if test="${freeAccount}">
		<g:render template="/_common/promo"/>
	</g:if>
	<g:else>

		<div class=lead>Иск к предприятию: <a href="${createLink(controller: 'account', action: 'show', id: partner?.id)}">${partner?.name}</a></div>

		<g:render template="/_common/create" model="[
				'objInstance' : claimInstance,
				'formTemplate': 'new'
		]"/>
	</g:else>

</div>
</body>
</html>
