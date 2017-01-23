<div class="row minicart lc">
	<div class="col-xs-4 col-sm-3" style=" text-align: center;">

		<g:if test="${itemInstance?.photo}">

			<g:link class="gallery" url="[controller: 'files', action: 'item', id: itemInstance.photo?.image.id, params: [name: itemInstance.photo?.image.file]]" data-gallery="">
				<img class="img-thumbnail"
					 src="${createLink(controller: 'files', action: 'item', id: itemInstance.photo?.imageThumb.id, params: [name: itemInstance.photo?.imageThumb.file])}"/>
			</g:link>
		</g:if>
		<g:else>
			<img class="img-thumbnail img_left" src="${resource(dir: 'images', file: 'front/services.png')}" style="border: none"/>
		</g:else>


	</div>

	<div class="col-sm-4 col-xs-8" >

			<g:link controller="services" action="item" id="${itemInstance.id}"><g:fieldValue bean="${itemInstance}" field="name"/></g:link>



		<div class="description"><g:fieldValue bean="${itemInstance}" field="description"/></div>

		<div class=price><g:formatNumber number="${itemInstance?.price}" type="currency" currencyCode="${itemInstance?.currency.code}"/> / ${itemInstance?.measure?.fullname}</div>

		<div class="date_create"><g:formatDate date="${itemInstance?.dateCreated}" format=" dd.MM.yyyy HH:mm:ss"/></div>

			<g:link controller="services" class="podr" style="text-align: center" action="item" id="${itemInstance.id}">
				Подробнее
			</g:link>

	</div>

	<div class="col-xs-12 col-sm-5"">
		<div class="gray_block">

			<g:if test="${freeAccount}">
				<a data-toggle="modal" data-target="#myModalPromo">Показать поставщика</a>
			</g:if>

			<g:else>

				Поставщик: <g:link controller="company" action="index"	id="${itemInstance?.account?.id}">${itemInstance?.account?.name}</g:link>



				<g:if test="${itemInstance?.account?.city}">
				<br>Город: ${itemInstance?.account?.city.name}
				</g:if>

				<g:if test="${itemInstance?.account?.address}">
				<br>Адресс: ${itemInstance?.account?.address}
				</g:if>

			</g:else>


			<g:if test="${freeAccount && isMyAccount}">
				<button class="deal_button" style="min-width: 143px;border: none; background-color: grey; text-align: left" disabled="true">Предложить сделку</button>
			</g:if>

			<g:else>
				<sec:ifNotLoggedIn>
					<g:link class="deal_button"  controller="login" action="auth"

							data-toggle="modal"
							data-remote="${createLink(controller:'login' , action: 'auth')}"
							data-target="#myModal">Предложить сделку</g:link>
				</sec:ifNotLoggedIn>
			<sec:ifLoggedIn>
				<g:link class="deal_button" controller="deal" style="min-width: 143px" action="create" params="[partner: itemInstance.account.id]">Предложить сделку</g:link>

			</sec:ifLoggedIn>
			</g:else>






		</div>

	</div>

</div>
