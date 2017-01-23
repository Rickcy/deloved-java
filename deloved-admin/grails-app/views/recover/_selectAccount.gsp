

<style>

	input[type="radio"]{
		display: none;
	}

	div[name=account] {
		margin-top: 1px;
		border-radius: 4px;
		border: 1px solid white;
		padding: 5px;

	}

   div[name=account]:hover {
	   background-color: #e0f5ff;
   }

   input[type="radio"]:checked+div{
	   border-color: #a4d4dd;
	   box-shadow: 0 0 0.5em #a4d4dd;
	   background-color: #d9edf7;
   }
</style>


<g:if test="${accountProfiles}">

	Почтовый ящик <b>${email}</b> указан в контактных данных нескольких профилей. Выберите предприятие, связанное с учетной записью, к которой вы утратили доступ.



	<g:formRemote name="selectRecover" url="[controller: 'recover', action: 'selectAccount']" update="recoverContent" method="POST">

		<input type="hidden" name="email" value="${email}"/>

		<g:each in="${accountProfiles}" var="accountProfile">
		<div>
		<input id="radio${accountProfile.profile.id}" type="radio" name="profile" value="${accountProfile.profile.id}"/>



		<div name="account" for="radio${accountProfile.profile.id}" class="row">


			<div class="col-md-2" align="center">

				<img width="50" height="50"
					 src="${createLink(controller: 'files', action: 'logo', id: accountProfile.account.logoThumb?.id, params: [name: accountProfile.account?.logoThumb?.file])}">

			</div>

			<div class="col-md-9" align="left">
				${accountProfile.account.orgForm.code} "${accountProfile.account.name}" <br>
				<b>ИНН: </b>${accountProfile.account.inn}
			</div>

		</div>


		</div>
	</g:each>

		<div align="right" style="margin-top: 10px">
			<g:link url="[controller: 'login', action: 'auth']" class="btn btn-default">Отменить</g:link>
			<button type="submit" class="btn btn-primary">Выслать инструкции</button>
		</div>
	</g:formRemote>

	<script>
		$(document).ready(function() {
			$('[name=account]').click(function(){
				$('#'+$(this).attr('for')).prop('checked', true).change();
			})
		})
	</script>

</g:if>