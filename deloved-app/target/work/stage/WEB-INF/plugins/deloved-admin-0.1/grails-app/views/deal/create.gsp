	<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="deal.create.label"/></title>
</head>

<body>

<div id="create-deal" class="content scaffold-create" role="main">

	<h1><g:message code="deal.create.label"/></h1>

	<g:if test="${freeAccount}">
		<g:render template="/_common/promo"/>
	</g:if>

	<g:else>
		<div class=lead>Сделка предлагается предприятию: <g:link controller="company" plugin="deloved-front" action="index"
																 id="${partner?.id}">${(partner?.name) ?: 'Не задан'}</g:link>


		<g:render template="/_common/create" model="[
				'objInstance' : dealInstance,
				'formTemplate': 'new'
		]"/>
	</g:else>

</div>
</body>
</html>
