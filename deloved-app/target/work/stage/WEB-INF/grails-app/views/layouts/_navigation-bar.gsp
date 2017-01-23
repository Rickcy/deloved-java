<g:set var="section">companies</g:set>
<g:if test="${params.controller in ['companies', 'company']}">
	<g:set var="section">companies</g:set>
</g:if>
<g:elseif test="${params.controller in ['goods']}">
	<g:set var="section">goods</g:set>
</g:elseif>
<g:elseif test="${params.controller in ['services']}">
	<g:set var="section">services</g:set>
</g:elseif>

<div class="header col-md-12">
<div class="col-xs-12 col-sm-2">
	<a href="${createLink(controller: 'front', action: 'index')}"><img class="hlogo" src="${resource(dir: 'images', file: 'front/logo2.png')}"/></a>
</div>
	<div class="col-xs-12 col-sm-10">


		<a class="hmenu" style="font-family: Georgia, serif;margin-right: 10px" href="${createLink(controller: 'front', action: 'index')}">Главная</a>

		<a class="hmenu" style="font-family: Georgia, serif;margin-right: 10px" href="${createLink(controller: 'companies', action: 'index')}">Партнеры</a>

		<a class="hmenu" style="font-family: Georgia, serif;margin-right: 10px" href="${createLink(controller: 'goods', action: 'index')}">Товары</a>

		<a class="hmenu" style="font-family: Georgia, serif;" href="${createLink(controller: 'services', action: 'index')}">Услуги</a>





	<sec:ifLoggedIn>

		<g:form id="logoutForm" url="[controller: 'logout']" method="POST"></g:form>

		<div class="hperson">

			<img id="hperson-avatar" src="${getAvatar() ?: resource(dir: 'images', file: 'admin/avatar.jpg')}"/>

			<span id="hperson-name"><g:userInfo what="fio"/></span>

			<div id="info">

				<g:link class="info-menu" controller="profile" action="me">Мой профиль</g:link>

				<sec:ifAnyGranted roles="ROLE_ACCOUNT">
					<a href="${createLink(controller: 'billing', action: 'index')}" class="info-menu">Лицевой счет</a>
				</sec:ifAnyGranted>

				<a href="javascript:void(0)" class="info-menu" onclick="$('#logoutForm').submit()">Выйти</a>

			</div>

		</div>

		<script>
			$('#hperson-avatar,#hperson-name').click(function() {
				var info = $("#info")
				if (info.is(':visible')) {
					info.hide()
					return false
				}
				info.show()
				return false
			})
		</script>

	</sec:ifLoggedIn>


	<sec:ifNotLoggedIn>

		<a class="hmenu" style="float: right" href="${createLink(controller: 'login', action: 'auth')}">Войти</a>
		<a class="hmenu" style="float: right" href="${createLink(controller: 'signup', action: 'index')}">Зарегистрироваться</a>

	</sec:ifNotLoggedIn>
	</div>

</div>
