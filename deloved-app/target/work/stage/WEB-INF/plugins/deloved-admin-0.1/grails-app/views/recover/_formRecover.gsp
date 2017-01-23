<asset:javascript src="front.js"/>
<asset:javascript src="application.js"/>
	<g:formRemote name="recoverForm" url="[controller: 'recover', action: 'send']" update="recoverContent" method="POST" autocomplete='on' style="padding: 0; margin: 0"
		onFailure="showMessage('danger', 'Почтовый ящик ' + jQuery('#email').val() + ' не найден. Проверте правильность написания.'); jQuery('#email').val('')">

		<h3 style="margin: 0;font-family: 'Palatino Linotype', 'Book Antiqua', Palatino, serif">Восстановление доступа к учетной записи</h3>

		<div style="margin-top: 10px;font-family: Georgia, serif">Укажите адрес электронной почты.</div>

		<input class="form-control"  type="email" id="email" name="email" placeholder="Адрес электронной почты" autofocus style="margin-top: 10px">

		<div align="right" style="margin-top: 10px;font-family: 'Palatino Linotype', 'Book Antiqua', Palatino, serif">
			<g:link url="[controller: 'front', action: 'index']" class="btn btn-default">Отменить</g:link>
			<button id="submit" type="submit" class="btn btn-primary">Выслать инструкции</button>
		</div>

	</g:formRemote>

