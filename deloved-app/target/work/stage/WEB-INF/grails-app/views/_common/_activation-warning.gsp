<!-- Шаблон для полной обработки этапа активации учетной записи по электрнной почте -->

<sec:ifAnyGranted roles="ROLE_NONE">
<%@ page import="ru.deloved.ContactConfirm" %>

<div class="alert alert-info" style="padding: 10px; margin-bottom: 10px">
		<button type="button" class="close" id="cActWarn" style="outline: none">&times;</button>
	<ul>
		<li>
			Вы зарегистровали учетную запись в системе, но еще не активировали её. На <b>${getActivationMail()}</b> были высланы инструкции по активации учетной записи.
		</li>
		<li>
			Письмо должно должно придти в течении нескольких минут после регистрации.
		</li>
		<li>
			Если письмо не приходит, то попробуйте проверить раздел "Спам" в вашем почтовом ящике.
		</li>
		<li>
			Если письмо все же не пришло или вы указали не правильный почтовый ящик при регистрации и теперь не можете получить к нему доступ, то нажмите
			<g:link controller="signup" action="resend"><b>сюда</b></g:link>.
		</li>
	</ul>
</div>

	<script>
		$(document).ready(function() {
			$('#cActWarn').click(function(){
				var actWarn = jQuery(this).parent()
				actWarn.css('opacity', 0)
				actWarn.animate({height: 0}, 500, function(){
					actWarn.remove();
				});
			});
		});
	</script>

</sec:ifAnyGranted>
