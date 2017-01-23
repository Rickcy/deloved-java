<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="Смена Пароля"/></title>
</head>

<body>

<div id="edit-profile" class="content scaffold-edit" role="main">

	<h1><g:message code="Смена Пароля"/></h1>

	<g:render template="/_common/flash-message"/>

	<g:hasErrors bean="${objInstance}">
		<ul class="alert alert-danger errors" role="alert">
			<g:eachError bean="${objInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
			</g:eachError>
		</ul>
	</g:hasErrors>

	<g:form name="editForm" url="[controller: 'profile', action: 'changepwd']" method="PUT" class="form-horizontal">
		<fieldset class="form">

			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'oldPassword', 'error')} required">
				<label class="col-sm-3 control-label" for="fio">
					<g:message code="profile.oldPassword.label" default="Старый Пароль"/>
					<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
				</label>

				<div class="col-sm-9">
					<g:passwordField class="form-control" name="oldPassword" required=""/>
				</div>
			</div>

			<hr/>

			<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'newPassword', 'error')} required form-group ">
				<label for="newPassword" class="col-sm-3 control-label">
					<g:message code="user.password.label" default="Password"/>
					<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
				</label>

				<div class="col-sm-9">
					<g:passwordField class="form-control" name="newPassword" required=""/>
				</div>
			</div>

			<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'newPasswordRepeat', 'error')} required form-group">
				<label for="newPasswordRepeat" class="col-sm-3 control-label">
					<g:message code="user.passwordRepeat.label" default="Repeat Password"/>
					<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
				</label>

				<div class="col-sm-9">
					<g:passwordField class="form-control" name="newPasswordRepeat" required=""/>
				</div>
			</div>

		</fieldset>
		<fieldset class="buttons">
			<g:actionSubmit class="btn  btn-success" action="changepwd" value="${message(code: 'default.button.update.label')}"/>
			<g:link class="btn btn-default" action="${params.id ? 'index' : 'me'}"><g:message code="default.cancel"/></g:link>
		</fieldset>
	</g:form>

</div>

</body>
</html>
