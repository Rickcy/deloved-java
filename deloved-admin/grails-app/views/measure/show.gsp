<%@ page import="ru.deloved.Measure" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<g:set var="entityName" value="${message(code: 'measure.label', default: 'Measure')}"/>
	<title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<div id="show-measure" class="content scaffold-show" role="main">

	<h1><g:message code="default.show.label" args="[entityName]"/></h1>

	<g:render template="/_common/flash-message"/>

	<ol class="property-list measure">

		<g:if test="${measureInstance?.name}">
			<li class="fieldcontain">
				<span id="name-label" class="property-label"><g:message code="measure.name.label" default="Name"/></span>

				<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${measureInstance}" field="name"/></span>

			</li>
		</g:if>

		<g:if test="${measureInstance?.type}">
			<li class="fieldcontain">
				<span id="type-label" class="property-label"><g:message code="measure.type.label" default="Type"/></span>

				<span class="property-value" aria-labelledby="type-label"><g:link controller="categoryType" action="show" id="${measureInstance?.type?.id}">${measureInstance?.type?.encodeAsHTML()}</g:link></span>

			</li>
		</g:if>

	</ol>
	<g:form url="[resource: measureInstance, action: 'delete']" method="DELETE">
		<fieldset class="buttons">
			<g:link class="edit" action="edit" resource="${measureInstance}"><g:message code="default.button.edit.label" default="Edit"/></g:link>
			<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}"
							onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
		</fieldset>
	</g:form>
</div>
</body>
</html>
