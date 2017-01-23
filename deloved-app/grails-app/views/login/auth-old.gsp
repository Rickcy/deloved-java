<html>
<head>
	<meta name='layout' content='unauthorized'/>
	<title><g:message code="springSecurity.login.title"/></title>
</head>

<body>
<g:if test='${flash.message}'>
	<div class='login_message'>${flash.message}</div>
</g:if>
<div class="container">

	<div class="row">
		<div class="col-md-2"></div>

		<div class="col-md-3" class="logo_auth">
			<img src="${resource(dir: 'images', file: 'admin/logo_auth.png')}" alt="Deloved"/>

			<div class=auth_text>В административном разделе вы можете управлять своими товарами, предприятием, сделками и спорами, а также получать юридическую помощь</div>
		</div>

		<div class="col-md-5">
			<form class="form-signin" action='${postUrl}' method='POST' id='loginForm' autocomplete='on'>
				<h3 class="form-signin-heading">Вход для пользователя</h3>
				<input type="text" class="form-control" name='j_username' id='username' placeholder="${message(code: "springSecurity.login.username.label")}" autofocus>
				<input type="password" class="form-control" name='j_password' id='password' placeholder="${message(code: "springSecurity.login.password.label")}">
				<label class="checkbox">
					<input type="checkbox" name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>>
					<g:message code="springSecurity.login.remember.me.label"/>
				</label>
				<button class="btn btn-lg btn-primary btn-block" type="submit" id="submit"><g:message code="springSecurity.login.button"/></button>
			</form>
		</div>

		<div class="col-md-2"></div>

	</div>

</div>
</body>
</html>
