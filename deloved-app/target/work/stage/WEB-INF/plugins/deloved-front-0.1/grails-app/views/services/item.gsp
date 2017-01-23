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
		${itemInstance.name}


	</title>
	<meta name="keywords" content="${itemInstance?.kind}">
	<meta name="description" content="${itemInstance?.description}">
	<script>
		$(document).ready(function(){
			localStorage.clear();
			var goods_name= $('#name').text()
			var goods_price = $('.price').text()
			var price = goods_price.split('?',1)


			localStorage.search =goods_name+' '+'за'+' '+price;
			localStorage.find ="услуге "+goods_name+' '+'за'+' '+price;
			var t =localStorage.search;
			var c=localStorage.find;
		})
	</script>
	<style>
	.bx-wrapper .bx-viewport{
		height: 100px!important;
	}
	</style>

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





		<!-- Начало строки товара -->
		<div class="col-lg-7 col-md-7 col-sm-12 col-xs-12" style="margin: 0;padding: 0">
			<h3 class="name" id="name" style="margin-left: -15px;text-align: center;border-bottom: 2px solid rgba(140, 192, 98, 0.3);"><g:if test="${itemInstance?.name}">
				${itemInstance.name}
			</g:if></h3>

			<!-- Начало столбца превью -->
			<div style="padding: 0; margin: 0" class="col-md-5 col-lg-5 col-sm-5" align="center">

				<div style="padding: 10px; border-radius: 3px; height: 250px">
					<div class="flex-image">

						<g:if test="${itemInstance?.photo}">

							<a id="main" href="${createLink(controller: 'files', action: 'item', id: itemInstance.photo?.image.id, params: [name: itemInstance.photo?.image.file])}" data-gallery="">

								<img src="${createLink(controller: 'files', action: 'item', id: itemInstance.photo?.image.id, params: [name: itemInstance.photo?.image.file])}"/>

							</a>

						</g:if>

						<g:else>

							<img src="${resource(dir: 'images', file: 'front/goods.png')}"/>

						</g:else>

					</div>
				</div>

			<div class="row">
				<g:if test="${!attachList.isEmpty()}">

					<div id="slider-prev" class="col-md-2 col-lg-2 col-sm-2 col-xs-2" style="float: left;height: 100px"><img src="${resource(dir: 'images', file: 'front/arrowleft.png')}" style="width: 27px;margin-top: 35px"/></div>

					<div  class="col-md-8 col-lg-8 col-sm-8 col-xs-8" style="display: inline-block;float: left">
						<ul id="thumbnail" class="slider">
							<g:each in="${attachList}" var="attach">
								<li>

									<g:link id="im${attach.id}" url="[controller: 'files', action: 'item', id: attach.image.id, params: [name: attach.image.file]]"	data-gallery="">
										<div style=" height: 100px; padding: 2px; border-radius: 2px">
											<div class="flex-image">
												<img id="th${attach.id}" src="${createLink(controller: 'files', action: 'item', id: attach.imageThumb.id, params: [name: attach.imageThumb.file])}"/>
											</div>
										</div>
									</g:link>


								</li>
							</g:each>
						</ul>
					</div>

					<div id="slider-next" class="col-md-2 col-lg-2 col-sm-2 col-xs-2" style="float: left;height: 100px;padding: 0"><img src="${resource(dir: 'images', file: 'front/arrowright.png')}" style="width: 27px;margin-top: 35px"/></div>


					<script>

						$(document).ready(function(){
							$('#thumbnail').bxSlider({
								slideWidth: 1000,
								minSlides: 2,
								maxSlides: 2,
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




			</div>
			<!-- Конец столбца с описанием товара -->

		</div>
	<!-- Конец строки товара -->


		<g:if test="${itemInstance.account}">



			<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12" style="margin: 0;padding: 0">
				<h3 class="name" style="text-align: center;border-bottom: 2px solid rgba(140, 192, 98, 0.3);">Продавец</h3>

				<div style="padding: 0; margin: 0" class="col-md-4 col-lg-4 col-sm-1 col-xs-5">

				</div>

				<div class="col-md-7 col-lg-7 col-sm-10 col-xs-12">


					<g:if test="${itemInstance.account.name}">
						<a class="postavshik" href="${createLink(controller: 'company', action: 'index', id: itemInstance.account.id)}">${itemInstance.account.name}</a><br>
						<hr>
					</g:if>

					<g:if test="${itemInstance.account.city.name}">
						Город: <b>${itemInstance.account.city.name } </b><br>
						<hr>
						</g:if>

					<g:if test="${itemInstance.account.address}">
						Адрес:  <b>${itemInstance.account.address} </b><br>
						<hr>
					</g:if>

					<sec:ifLoggedIn>
					<g:if test="${freeAccount && isMyAccount}">
						<button class="deal_button" style="border: none; background-color: grey; text-align: left" disabled="true">Предложить сделку</button>
					</g:if>

					<g:else>
						<g:link class="deal_button" controller="deal" action="create2" params="[partner: itemInstance.account.id]">Предложить сделку</g:link>
					</g:else>
					</sec:ifLoggedIn>
					<sec:ifNotLoggedIn>
						<g:link class="deal_button"  controller="login" action="auth"

								data-toggle="modal"
								data-remote="${createLink(controller:'login' , action: 'auth')}"
								data-target="#myModal">Предложить сделку</g:link>
					</sec:ifNotLoggedIn>
				</div>

			</div>


		</g:if>
		<hr>


</div>
</div>
</body>
</html>