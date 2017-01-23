<%@ page contentType="text/html;charset=UTF-8" %>

<head>
	<meta name='layout' content='admin'/>
	<title>Лицевой счет</title>
</head>
<body>
<h1><g:message code="billing.list.label"/></h1>
<g:render template="/_common/flash-message"/>



<g:if test="${keeper}">

	<div align="center">

	<g:render template="addfunds" model="[
	        tariffs: tariffs,
			methods: methods
	]"/>

	<br>

	<g:render template="history" model="[
			requestInstanceList: requestInstanceList,
			requestInstanceTotal: requestInstanceTotal
	]"/>

	</div>

</g:if>

<g:else>
	<g:form url="[action: 'account']" method="POST">
		<g:submitButton name="create" class="btn btn-primary ft" value="Создать счет"/>
	</g:form>
</g:else>

</body>
</html>