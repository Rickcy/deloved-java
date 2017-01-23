<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="profile.edit.label"/></title>
</head>

<body>

<div id="edit-profile" class="content scaffold-edit" role="main">

	<h1><g:message code="profile.edit.label"/></h1>

	<g:render template="/_common/flash-message"/>

	<g:hasErrors bean="${objInstance}">
		<ul class="alert alert-danger errors" role="alert">
			<g:eachError bean="${objInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
			</g:eachError>
		</ul>
	</g:hasErrors>

	<g:form name="editForm" url="[controller: 'profile', action: 'update', id: params.id]" method="PUT" class="form-horizontal">
		<g:hiddenField name="version" value="${beanResource?.version}"/>
		<fieldset class="form">
			<g:render template="form"/>
		</fieldset>
		<fieldset class="buttons">
			<g:actionSubmit class="btn  btn-success" action="update" value="${message(code: 'default.button.update.label')}"/>
			<g:link class="btn btn-default" action="${params.id ? 'index' : 'me'}"><g:message code="default.cancel"/></g:link>
		</fieldset>
	</g:form>

</div>

<g:render template="/_common/gallery-single"/>
<g:render template="/_common/crop"/>

</body>
</html>
