<%--
  Created by IntelliJ IDEA.
  User: Андрейка
  Date: 07.11.2014
  Time: 1:32
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="front">
	<title>
		<g:if test="${itemInstance.categoryType.code == 'GOOD'}">%
			<g:message code="good.list.label" default="Good"/>
		</g:if>
		<g:else>
			<g:message code="service.list.label" default="Service"/>
		</g:else>
	</title>
</head>

<body>

<g:render template="/_common/gallery-multi"/>

<div class="row">
<div class="col-sm-12 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1">



	<div class="row">
		<g:render template="/_common/category-breadcrumb" model="[
				categoryFilterData: categoryFilterData
		]"/>
	</div>



	<h1 style="margin-left: -15px;"><g:if test="${itemInstance?.name}">
		${itemInstance.name}<br>
	</g:if></h1>


	<!-- Начало строки товара -->
	<div class="row">

		<!-- Начало столбца превью -->
		<div style="padding: 0; margin: 0" class="col-md-5 col-lg-5 col-sm-5" align="center">

			<div style="padding: 10px; border-radius: 3px; border: 1px solid silver; width: 100%; height: 400px">
			<div class="flex-image">

					<g:if test="${itemInstance?.photo}">

					<a id="main" href="${createLink(controller: 'files', action: 'item', id: itemInstance.photo?.image.id, params: [name: itemInstance.photo?.image.file])}" data-gallery="">

						<img src="${createLink(controller: 'files', action: 'item', id: itemInstance.photo?.image.id, params: [name: itemInstance.photo?.image.file])}"/>

					</a>

				</g:if>

				<g:else>

					<img src="${resource(dir: 'images', file: 'front/logo_uch.png')}"/>

				</g:else>

			</div>
			</div>

			<g:if test="${!attachList.isEmpty()}">

				<button id="slider-prev" class="next-prev-button"><</button>

				<div style="display: inline-block; width: 89%;">
					<ul id="thumbnail" class="slider">
						<g:each in="${attachList}" var="attach">
							<li>

								<g:link id="im${attach.id}" url="[controller: 'files', action: 'item', id: attach.image.id, params: [name: attach.image.file]]"	data-gallery="">
									<div style="border: 1px solid silver; height: 100px; padding: 2px; border-radius: 2px">
									<div class="flex-image">
									<img id="th${attach.id}" src="${createLink(controller: 'files', action: 'item', id: attach.imageThumb.id, params: [name: attach.imageThumb.file])}"/>
									</div>
									</div>
								</g:link>


							</li>
						</g:each>
					</ul>
				</div>

				<button id="slider-next" class="next-prev-button">></button>


				<script>

					$(document).ready(function(){
						$('#thumbnail').bxSlider({
							slideWidth: 1000,
							minSlides: 3,
							maxSlides: 3,
							slideMargin: 3,
							moveSlides: 1,
							//auto: true,
							adaptiveHeight: false,
							contols: true,
							infiniteLoop: false,
							//autoControls: true,
							//captions: true
						});
					});

					var slider = $('#thumbnail').bxSlider({

					});

					$('#slider-next').click(function(){
						slider.goToNextSlide();
						return false;
					});

					$('#slider-prev').click(function(){
						slider.goToPrevSlide();
						return false;
					});


				</script>

			</g:if>



		</div>
		<!-- Конец столбца превью -->

		<!-- Начало столбца с описанием товара -->
		<div class="col-md-7 col-lg-7 col-sm-7">

			<div class="price" >
				<g:if test="${itemInstance?.price}">

					<g:formatNumber number="${itemInstance?.price}" type="currency" currencyCode="${itemInstance?.currency.code}"/>
					${'за ' + itemInstance.measureValue + ' ' + itemInstance?.measure?.name}

					<g:if test="${itemInstance?.measure.fullname}">
						<a id="measure-desciption" style="text-decoration: none; font-size: 12px" href="javascript:void(0)" data-toggle="popover">?</a>
						<script>
							$(document).ready(function() {
								$('[data-toggle="popover"]').popover({
									content: '${itemInstance.measure.fullname}'
								});
							});
						</script>
					</g:if>

					<br>
				</g:if>
			</div>


			<hr>

			<g:if test="${itemInstance?.kind}">
				<span id="kind-label" class="property-label"><g:message code="item.kind.label" default="Kind"/>: </span>
				<b>${itemInstance.kind}</b>
			</g:if>



			<br>

			<g:if test="${itemInstance?.categoryType?.code == 'GOOD'}">

				<g:message code="item.availability.label" default="Availability"/>:

				<g:if test="${itemInstance?.availability == 1}">
					<div class="availability"><g:message code="item.filter.avail.1" default="Available"/></div>
				</g:if>

				<g:else>
					<div class="availability"><g:message code="item.filter.avail.0" default="Custom"/></div>
				</g:else>

				<br>

			</g:if>
			<hr>



			<div class="description">
				<g:if test="${itemInstance?.description}">



					<!-- Использовал текстарию, чтобы элемент адекватно отображал базовые символы разметки и делал перенос слов -->
					<div style="white-space: pre-wrap">${itemInstance.description}
					</div>

				</g:if>


			</div>


			<g:if test="${freeAccount || isMyAccount}">
				<button class="deal_button" style="border: none; background-color: grey; text-align: left" disabled="true">Предложить сделку</button>
			</g:if>

			<g:else>
				<g:link class="deal_button" controller="deal" action="create" params="[partner: itemInstance.account.id]">Предложить сделку</g:link>
			</g:else>

		</div>
		<!-- Конец столбца с описанием товара -->

	</div>
<!-- Конец строки товара -->


	<g:if test="${itemInstance.account}">

		<div class="row">
			<h3>Поставщик</h3>
		</div>

		<div class="row">

			<div style="padding: 0; margin: 0" class="col-md-5 col-lg-5 col-sm-5">
				<div style="padding: 10px; border-radius: 3px; border: 1px solid silver; width: 100%; height: 400px">
					<div class="flex-image">
				<g:if test="${itemInstance.account?.logo}">
					<g:link  url="[controller: 'files', action: 'logo', id: itemInstance.account.logo.id, params: [name: itemInstance.account.logo?.file]]" data-gallery="">
						<img title="" src="${createLink(controller: 'files', action: 'logo', id: itemInstance.account.logo.id, params: [name: itemInstance.account.logo?.file])}"/>
					</g:link>
				</g:if>
				<g:else>
					<img src="${resource(dir: 'images', file: 'front/logo_uch.png')}"/>
				</g:else>
				</div>
			</div>
			</div>

			<div class="col-md-7 col-lg-7 col-sm-7">


				<g:if test="${itemInstance.account.name}">
					<a class="postavshik" href="${createLink(controller: 'company', action: 'index', id: itemInstance.account.id)}">${itemInstance.account.name}</a><br>
				</g:if>

				<g:if test="${itemInstance.account.city.name}">
					Город: <b>${itemInstance.account.city.name } </b><br>
				</g:if>

				<g:if test="${itemInstance.account.address}">
					Адрес:  <b>${itemInstance.account.address} </b><br>
				</g:if>

				<g:if test="${itemInstance.account.phone1}">
					Телефон:  <b>${itemInstance.account.phone1}</b> <br>
				</g:if>

				<g:if test="${itemInstance.account.webAddress}">
					Сайт:  <b><a href="${itemInstance.account.webAddress}">${itemInstance.account.webAddress}</a></b><br>
				</g:if>

				<g:if test="${itemInstance.account.email}">
					E-mail: <b><a href="mailto:${itemInstance.account.email}">${itemInstance.account.email}</a></b> <br>
				</g:if>

			</div>

		</div>


		<div class="row">
			<h3>
				<g:if test="${itemInstance.categoryType.code == 'GOOD'}">
					<g:message code="othergoods.list.label" default="All goods of company"/>
				</g:if>
				<g:else>
					<g:message code="otherservices.list.label" default="All services of company"/>
				</g:else>
			</h3>
		</div>
	</g:if>

	<g:if test="${!itemList.isEmpty()}">
		<div class="row">

			<ul class="bxsliderGoods">
				<g:each in="${itemList}" var="item">
					<li>

						<div style="height: 150px; padding: 3px; border: 1px solid silver; border-radius: 2px">
						<div class="flex-image">


							<g:if test="${item?.photo}">
								<g:link id="it${item.id}" url="[controller: 'files', action: 'item', id: item.photo?.image.id, params: [name: item.photo?.image.file]]"	data-gallery="">
								<img src="${createLink(controller: 'files', action: 'item', id: item.photo?.imageThumb.id, params: [name: item.photo?.imageThumb.file])}"/>
								</g:link>
							</g:if>

							<g:else>
								<img src="${resource(dir: 'images', file: 'front/logo_uch.png')}"/>
							</g:else>


						</div>
						</div>

						<g:link url="[controller: 'goods', action: 'item', id: item.id]">
							<div align="center">${item.name ?: ''}</div>
						</g:link>

					</li>
				</g:each>
			</ul>
			<script>
				$('.bxsliderGoods').bxSlider({
					minSlides: 4,
					maxSlides: 4,
					slideWidth: 200,
					slideMargin: 10,
					auto: true,
					autoControls: true
				});
			</script>

		</div>
	</g:if>


</div>
</div>
</body>
</html>