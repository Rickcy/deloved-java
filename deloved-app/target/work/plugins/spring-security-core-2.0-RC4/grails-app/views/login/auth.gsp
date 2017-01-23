<form action='${postUrl}' method='POST' id='loginForm' autocomplete='on' style="z-index: 1000">
	<h3 class="form-signin-heading" style="font-family: 'Palatino Linotype', 'Book Antiqua', Palatino, serif">Вход в личный кабинет</h3>
	<h5 class="form-sigin-h5" style="text-align: center;width: 90%;margin-left: 5%;font-family: Georgia, serif;font-size: 11pt"><div class="hidden-xs">Чтобы управлять своей компанией, а также вести сделки, нужно авторизоваться в системе.</div>
		Если вы еще не зарегистрированны, можете сделать это <g:link controller="signup" action="index">здесь</g:link></h5>

	<input style="margin-bottom: 1%;border-radius: 10px;font-family: Georgia, serif" type="text" class="form-control" name='j_username' id='username' placeholder="${message(code: "springSecurity.login.username.label")}" autofocus>
	<input type="password" style="border-radius: 10px;font-family: Georgia, serif" class="form-control" name='j_password' id='password' placeholder="${message(code: "springSecurity.login.password.label")}">

	<div class="col-xs-12 col-sm-6 text-center">
		<g:link controller="recover">Забыли пароль?</g:link>
	</div>
	<div class="col-xs-12 col-sm-6 text-center">
		<input type="checkbox" name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>>
		<g:message code="springSecurity.login.remember.me.label"/>
	</div>
	<div class="clearfix"></div>
	<div class="btn btn-lg btn-primary btn-block loginin" style="font-family: 'Palatino Linotype', 'Book Antiqua', Palatino, serif" onclick="authAjax()"><g:message code="springSecurity.login.button"/></div>


</form>