<%@ page import="ru.deloved.billing.TariffPrice" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<g:set var="entityName" value="${message(code: 'tariff.label', default: 'TariffPrice')}"/>
	<title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<div id="show-tariff" class="content scaffold-show" role="main">

	<h1><g:message code="default.show.label" args="[entityName]"/></h1>

	<g:render template="/_common/flash-message"/>

	<ol class="property-list tariff">

		<g:if test="${tariffInstance?.name}">
			<li class="fieldcontain">
				<span id="name-label" class="property-label"><g:message code="tariff.name.label" default="Name"/></span>

				<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${tariffInstance}" field="name"/></span>

			</li>
		</g:if>

		<g:if test="${tariffInstance?.price}">
			<li class="fieldcontain">
				<span id="pricelabel" class="property-label"><g:message code="tariff.price.label" default="Price"/></span>

				<span class="property-value" aria-labelledby="price-label">${formatNumber(number: tariffInstance.price, type: 'currency', currencyCode: (tariffInstance.currency?.code) ?: 'RUB')}</span>

			</li>
		</g:if>

	</ol>
	<g:form url="[resource: tariffInstance, action: 'delete']" method="DELETE">
		<fieldset class="buttons">
			<g:link class="edit" action="edit" resource="${tariffInstance}"><g:message code="default.button.edit.label" default="Edit"/></g:link>
			<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}"
							onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
		</fieldset>
	</g:form>
</div>
</body>
</html>
