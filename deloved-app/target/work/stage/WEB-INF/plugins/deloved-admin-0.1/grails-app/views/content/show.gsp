<%@ page import="ru.deloved.Content" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="content.show.label"/></title>
</head>

<body>

<div id="show-content" class="content scaffold-show" role="main">

	<h1><g:message code="content.show.label"/></h1>

	<g:render template="/_common/flash-message"/>

	<ol class="property-list content">

		<g:if test="${contentInstance?.name}">
			<li class="fieldcontain">
				<span id="name-label" class="property-label"><g:message code="content.name.label" default="Name"/></span>

				<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${contentInstance}" field="name"/></span>

			</li>
		</g:if>

		<g:if test="${contentInstance?.code}">
			<li class="fieldcontain">
				<span id="type-label" class="property-label"><g:message code="content.code.label" default="Code"/></span>

				<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${contentInstance}" field="code"/></span>

			</li>
		</g:if>
		<g:if test="${contentInstance?.enabled}">
			<li class="fieldcontain">
				<span id="type-label" class="property-label"><g:message code="content.enabled.label" default="Enabled"/></span>

				<span class="property-value" aria-labelledby="enabled-label"><g:fieldValue bean="${contentInstance}" field="enabled"/></span>

			</li>
		</g:if>
		<g:if test="${contentInstance?.content}">
			<li class="fieldcontain">
				<span id="type-label" class="property-label"><g:message code="content.content.label" default="Content"/></span>

				<span class="property-value" aria-labelledby="enabled-label"><pre><g:fieldValue bean="${contentInstance}" field="content"/></pre></span>

			</li>
		</g:if>

	</ol>
	<g:form url="[resource: contentInstance, action: 'delete']" method="DELETE">
		<fieldset class="buttons">
			<g:link class="edit" action="edit" resource="${contentInstance}"><g:message code="default.button.edit.label" default="Edit"/></g:link>
			<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}"
							onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
		</fieldset>
	</g:form>
</div>
</body>
</html>
