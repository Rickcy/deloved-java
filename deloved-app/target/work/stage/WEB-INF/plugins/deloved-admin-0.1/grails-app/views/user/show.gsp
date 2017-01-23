<%@ page import="ru.deloved.User" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="user.show.label"/></title>
</head>

<body>
<div id="show-user" class="content scaffold-show" role="main">

	<h1><g:message code="user.show.label"/></h1>

	<g:render template="/_common/flash-message"/>

	<ol class="property-list user">

		<g:if test="${userInstance?.username}">
			<li class="fieldcontain">
				<span id="username-label" class="property-label"><g:message code="user.username.label" default="Username"/></span>

				<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${userInstance}" field="username"/></span>

			</li>
		</g:if>

		<g:if test="${userInstance?.enabled}">
			<li class="fieldcontain">
				<span id="enabled-label" class="property-label"><g:message code="user.enabled.label" default="Enabled"/></span>

				<span class="property-value" aria-labelledby="enabled-label"><g:formatBoolean boolean="${userInstance?.enabled}"/></span>

			</li>
		</g:if>

		<g:if test="${userInstance?.dateCreated}">
			<li class="fieldcontain">
				<span id="dateCreated-label" class="property-label"><g:message code="user.dateCreated.label" default="Date Created"/></span>

				<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${userInstance?.dateCreated}"/></span>

			</li>
		</g:if>

		<g:if test="${userInstance?.profile}">
			<li class="fieldcontain">
				<span id="profile-label" class="property-label"><g:message code="user.profile.label" default="Profile"/></span>

				<span class="property-value" aria-labelledby="profile-label"><g:link controller="profile" action="show" id="${userInstance?.profile?.id}">${userInstance?.profile?.encodeAsHTML()}</g:link></span>

			</li>
		</g:if>

	</ol>
	<g:form url="[resource: userInstance, action: 'delete']" method="DELETE">
		<fieldset class="buttons">
			<g:link class="btn btn-primary btn-lg" action="edit" resource="${userInstance}"><g:message code="default.button.edit.label" default="Edit"/></g:link>
			<g:actionSubmit class="btn btn-warning btn-lg" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}"
							onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>

		</fieldset>
	</g:form>
</div>
</body>
</html>
