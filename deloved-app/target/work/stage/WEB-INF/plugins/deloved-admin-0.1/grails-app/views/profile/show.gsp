		<%@ page import="ru.deloved.Profile" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="profile.show.label"/></title>
</head>

<body>

<h1><g:message code="profile.show.label"/></h1>

<g:render template="/_common/flash-message"/>

<g:render template="/_common/hint" model="[hintText: 'Поля, закрытые для редактирования, можно изменить через обращение в службу поддержки.']"/>

<div class="profile-info">

	<div class="row">
		<div class="label-col ft">
			<label for="id">ID в системе</label>
		</div>
		<div class="value-col ft">
			<p id="id" name="id">${profileInstance.id}</p>
		</div>
		<sec:ifAnyGranted roles="ROLE_ACCOUNT,ROLE_ADMIN">
		<div class="action-col-password ft">
			<g:link controller="profile" action="passwd"><g:message code="profile.button.passwd.label"/></g:link>
		</div>
		</sec:ifAnyGranted>
	</div>

<%--
	<div class="row">
		<div class="label-col">
			<label for="fio">Ф.И.О.</label>
		</div>
		<div class="value-col">
			<input id="fio" name="fio" readonly value="${profileInstance.fio}" data-old="${profileInstance.fio}"
				placeholder="Отсутствует"/>
			<div class="pods">Фамилия, имя и отчество руководителя предприятия или предпринимателя</div>

			<img id="fiospinner" style="display: none" src="${resource(dir: 'image', file: 'spinner.gif')}"/>

		</div>
		<div class="action-col">
			<a href="javascript:void(0)" name="change" data-for="fio">Изменить</a>
		</div>
	</div>
--%>
	<div class="row">
		<div class="label-col ft">
			<label for="fio">Ф.И.О руководителя</label>
		</div>
		<div class="value-col ft">
			<p id="fio" name="fio">${profileInstance.fio}</p>
		</div>
		<div class="action-col">

		</div>
	</div>

	<div class="row">
		<div class="label-col ft">
			<label>Фото:</label>
		</div>
		<div class="value-col">
			<div name="avatar">
				<g:render template="/_common/upload-single-image" model="[
						'isUpload'    : profileInstance?.avatar != null,
						'imageUrl'    : createLink(controller: 'profile', action: 'avatar', id: profileInstance.id, params: [name: profileInstance?.avatar?.file, type: 'main']),
						'thumbUrl'    : createLink(controller: 'profile', action: 'avatar', id: profileInstance.id, params: [name: profileInstance?.avatarThumb?.file]),
						'uploadAction': createLink(controller: 'profile', action: 'upload', id:profileInstance.id),
						'cropAction'  : createLink(controller: 'profile', action: 'crop', id: profileInstance.id),
						'deleteAction': 'deleteavatar',
						'imgId'       : 'avatar',
						'imgTitle'    : 'Аватар',
						'imgWidth'    : 100,
						'imgHeight'   : 100

				]"/>
			</div>
		</div>
		<div class="action-col">
		</div>
	</div>

	<div class="row">
		<div class="label-col ft">
			<label for="city">Город</label>
		</div>
		<div class="value-col ft">
			<input id="city" name="city" readonly value="${profileInstance?.city?.name}" data-old="${profileInstance?.city?.name}"
			 	placeholder="Отсутствует"/>
			<img id="cityspinner" style="display: none" src="${resource(dir: 'image', file: 'spinner.gif')}"/>
			<div class="pods fr">Город, к которому будет прикреплен ваш профиль</div>
		</div>
		<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_ACCOUNT">
		<div class="action-col">
			<a href="javascript:void(0)" name="change" data-for="city">Изменить</a>
		</div>
		</sec:ifAnyGranted>
	</div>

	<script type="application/javascript">
		$(function () {
			$("#city").autocomplete({
				source: "${createLink(controller: 'profile', action: 'cities')}",
				minLength: 3
			});
		});
	</script>
<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_ACCOUNT">
	<div class="row">
		<div class="label-col ft">
			<label for="email">E-mail</label>
		</div>
		<div class="value-col ft">
			<input id="email" name="email" readonly value="${profileInstance.email}" data-old="${profileInstance.email}"
				placeholder="Отсутствует"/>
			<div class="pods fr">Контактный адрес электронной почты. Смена только по подтверждению. Не одно и тоже с именем пользователя (логином), но может совпадать</div>
			<img id="emailspinner" style="display: none" src="${resource(dir: 'image', file: 'spinner.gif')}"/>

		</div>
		<div class="action-col">
			<a href="javascript:void(0)" name="change" data-for="email">Изменить</a>
		</div>
	</div>


	<g:if test="${confirmEmailList?.size() > 0}">
	<div class="row">
		<div class="label-col">
		</div>
		<div class="value-col">
				<ul>
					<g:each in="${confirmEmailList}" var="confirm">
						<li>
							${confirm.contact} - <g:message code="contactConfirm.${confirm.status()}.status"/>
						</li>
					</g:each>
				</ul>
		</div>
		<div class="action-col">
		</div>
	</div>
	</g:if>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles="ROLE_MANAGER,ROLE_JUDGE,ROLE_JURIST,ROLE_MEDIATOR">
	<div class="row">
		<div class="label-col ft">
			<label for="cellPhone">Стаж работы</label>
		</div>
		<div class="value-col ft">
			<input id="cellPhone" name="cellPhone" readonly value="${profileInstance.dayOfWork}" data-old="${profileInstance.dayOfWork}"
				placeholder="Отсутствует"/>

			<div class="pods fr">Стаж работы по данной специальности</div>
			<img id="cellPhonespinner" style="display: none" src="${resource(dir: 'image', file: 'spinner.gif')}"/>

		</div>


	</div>
</sec:ifAnyGranted>



	<div class="row">
		<div class="label-col ft">
			<label for="dateCreated">Дата регистрации</label>
		</div>
		<div class="value-col ft">
			<p id="dateCreated" name="dateCreated"><g:formatDate date="${profileInstance.dateCreated}" format="dd.MM.yyyy"/></p>
		</div>
		<div class="action-col">

		</div>
	</div>
	<sec:ifAnyGranted roles="ROLE_ACCOUNT">
	<div class="row">
		<div class="label-col ft">
			<label for="charge">Статус подписки</label>
		</div>
		<div class="value-col ft">
			<g:if test="${profileInstance.chargeStatus == 1}">
				<p id="charge" name="charge">Расширенная вресия, истекает <g:formatDate date="${profileInstance.chargeTill}" format="dd.MM.yyyy"/> </p>
			</g:if>
			<g:else>
				<p class="ft" id="charge" name="charge">Стартовая версия </p>
			</g:else>
		</div>
		<div class="action-col fr">
			<g:if test="${profileInstance.chargeStatus == 1}">
				<g:link url="[controller: 'billing', action: 'index']">Продлить</g:link>
			</g:if>
			<g:else>
				<g:link url="[controller: 'billing', action: 'index']">Оплатить</g:link>
			</g:else>
		</div>
	</div>
	</sec:ifAnyGranted>
</div>

<script>
	$(document).ready(function() {
		$('[name=change]').click(function(e) {
			<%--Получаем элемент который вызвал событие--%>
			var el = e.target || e.srcElement;
			<%--Получаем значение поля data-for элемента который вызвал событие--%>
			var prop = $(el).data('for');
			<%--Нажимаем изменить--%>
			if ($('#'+prop).is('[readonly]')) {
				$('#'+prop).attr('readonly', false);
				$(el).html('Сохранить');
			<%--Нажимает сохранить--%>
			} else {
				var oldValue = $('#'+prop).data('old');
				var newValue = $('#'+prop).val();
				if (oldValue != newValue && newValue != "") {
					$.ajax({
						type: 'POST',
						url: '${createLink(controller: 'profile', action: 'editNew', id: profileInstance.id)}',
						data: {prop: prop, value: newValue},
						beforeSend: function() {
							$('#'+prop+'spinner').show();
						},
						complete: function (data, textStatus) {
							$('#'+prop+'spinner').hide();
						},
						success: function (data, textStatus) {
							$('#'+prop).data('old', newValue);
							showMessage('success', data)
						},
						error: function (XMLHttpRequest, textStatus, errorThrown) {
							$('#'+prop).val(oldValue);
							showMessage('danger', errorThrown)
						}
					})
				}
				$('#'+prop).attr('readonly', true);
				$(el).html('Изменить');
			}
		});
	});

</script>

<g:render template="/_common/crop"/>
<g:render template="/_common/gallery-single"/>

</body>
</html>
