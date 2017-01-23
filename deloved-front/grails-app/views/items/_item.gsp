<div class="row minicart">
	<div class="col-md-3">

		<g:if test="${itemInstance?.photo}">

			<g:link class="gallery" url="[controller: 'files', action: 'item', id: itemInstance.photo?.image.id, params: [name: itemInstance.photo?.image.file]]" data-gallery="">
				<img class="img-thumbnail"
					 src="${createLink(controller: 'files', action: 'item', id: itemInstance.photo?.imageThumb.id, params: [name: itemInstance.photo?.imageThumb.file])}"/>
			</g:link>
		</g:if>

		<g:if test="${itemInstance.categoryType == 'GOODS'}">
		<div class="availability">В наличии</div>
		</g:if>

	</div>

	<div class="col-md-5">

		<g:link controller="goods" action="item" id="${itemInstance.id}"><g:fieldValue bean="${itemInstance}" field="name"/></g:link>



		<div class="description"><g:fieldValue bean="${itemInstance}" field="description"/></div>

		<div class=price><g:formatNumber number="${itemInstance?.price}" type="currency" currencyCode="${itemInstance?.currency.code}"/> / ${itemInstance?.measure?.name}</div>

		<div class="date_create"><g:formatDate date="${itemInstance?.dateCreated}" format=" dd.MM.yyyy HH:mm:ss"/></div>

	</div>

	<div class="col-md-4">

		<div class="gray_block">

			<g:if test="${freeAccount}">
				<a data-toggle="modal" data-target="#myModalPromo">Показать поставщика</a>
			</g:if>

			<g:else>

				Поставщик: <g:link controller="company" action="index"	id="${itemInstance?.account?.id}">${itemInstance?.account?.name}</g:link>

				<g:if test="${itemInstance?.account?.webAddress}">
				<br>Cайт: <a href="${itemInstance?.account?.webAddress}">${itemInstance?.account?.webAddress}</a>
				</g:if>

				<g:if test="${itemInstance?.account?.email}">
				<br>E-mail: <a href="mailto:${itemInstance?.account?.email}">${itemInstance?.account?.email}</a>
				</g:if>

				<g:if test="${itemInstance?.account?.phone1}">
				<br>Телефон: ${itemInstance?.account?.phone1}
				</g:if>

				<g:if test="${itemInstance?.account?.city}">
				<br>Город: ${itemInstance?.account?.city.name}
				</g:if>

				<g:if test="${itemInstance?.account?.address}">
				<br>Адресс: ${itemInstance?.account?.address}
				</g:if>

			</g:else>







		</div>

	</div>

</div>
