<%@ page import="ru.deloved.SystemCurrency" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<g:set var="entityName" value="${message(code: 'currency.label', default: 'Currency')}"/>
	<title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<div id="show-currency" class="content scaffold-show" role="main">

	<h1><g:message code="default.show.label" args="[entityName]"/></h1>

	<g:render template="/_common/flash-message"/>

	<ol class="property-list currency">

		<g:if test="${currencyInstance?.name}">
			<li class="fieldcontain">
				<span id="name-label" class="property-label"><g:message code="currency.name.label" default="Name"/></span>

				<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${currencyInstance}" field="name"/></span>

			</li>
		</g:if>
		<g:if test="${currencyInstance?.code}">
			<li class="fieldcontain">
				<span id="code-label" class="property-label"><g:message code="currency.code.label" default="Code"/></span>

				<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${currencyInstance}" field="code"/></span>

			</li>
		</g:if>
		<g:if test="${currencyInstance?.digit3}">
			<li class="fieldcontain">
				<span id="digit3-label" class="property-label"><g:message code="currency.digit3.label" default="Digit3"/></span>

				<span class="property-value" aria-labelledby="digit3-label"><g:fieldValue bean="${currencyInstance}" field="digit3"/></span>

			</li>
		</g:if>


	</ol>
	<g:form url="[resource: currencyInstance, action: 'delete']" method="DELETE">
		<fieldset class="buttons">
			<g:link class="edit" action="edit" resource="${currencyInstance}"><g:message code="default.button.edit.label" default="Edit"/></g:link>
			<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}"
							onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
		</fieldset>
	</g:form>
</div>
</body>
</html>
