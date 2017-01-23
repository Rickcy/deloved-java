<%@ page import="ru.deloved.Account" %>
<%@ page import="ru.deloved.Profile" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="front">
	<title><g:fieldValue field="name" bean="${accountInstance}"/></title>
	<meta name="keywords" content="${accountInstance?.keywords}">
	<meta name="description" content="${accountInstance?.description}">
	<script src="//api-maps.yandex.ru/2.0-stable/?load=package.standard&lang=ru-RU" type="text/javascript"></script>
<script>
	$(document).ready(function(){

		var text = $('.status').text();
		regV= 'пользователь';
		var search = text.match(regV);
		if(search){
			$('.claim_block').remove();
			$('.searchandgoods').remove();
			$('.email').remove();
			$('.phone').remove();
			$('.btn-primary').remove();
			$('.gray_block').remove()

		}
	})
</script>
	<style>
		.bx-wrapper{
			width: 100%!important;
		}

		.status{
			margin-left: 25%;
		}

	</style>
</head>

<body>

<g:render template="/_common/flash-message"/>

<div id="info-account" class="content" role="main">

	<div class="row big_cart" style="padding-top:20px;box-shadow: 0px 0px 20px #d8d8d8;border-radius: 20px">

		<div class="col-md-3">



			<g:if test="${accountInstance.logoThumb}">
			<div class="text-center">
				<img class="img-thumbnail img_left" style="border: none"
					 src="${createLink(controller: 'files', action: 'logo', id: accountInstance.logoThumb?.id, params: [name: accountInstance.logoThumb?.file])}"/>
			</div>
			</g:if>




			<h2 style="text-align: left">

				<g:fieldValue field="name" bean="${accountInstance}"/>

			</h2>



			<div class="forma" style="text-align: left">

				<g:if test="${accountInstance?.orgForm}">

					<g:fieldValue bean="${accountInstance.orgForm}" field="name"/>

				</g:if>

			</div>
			<hr>
			<p style="font-size: 12pt;width: 90%;text-align: left;font-family: Verdana, Geneva, sans-serif;">
				<b>ИНН:</b>	<g:fieldValue bean="${accountInstance}" field="inn"/>
			</p>
			<br>
			<p style="font-size: 12pt;width: 90%;text-align: left;font-family: Verdana, Geneva, sans-serif;">
				<b>ОГРН:</b> 	<g:fieldValue bean="${accountInstance}" field="regNumber"/>
			</p>

			<hr>

<sec:ifLoggedIn>

	<div class="gray_block" style="margin-top: 10px;">

		<h4>Рейтинг (${accountInstance.rating ? accountInstance.rating + '%' : '-'})</h4>
		<span class=rating><g:rating value="${accountInstance.rating}"/></span>

		<g:if test="${reviewsCount > 0}">
			<g:link class="otz" action="reviews"
					data-toggle="modal"
					data-remote="${createLink(id: accountInstance.id, action: 'reviews')}"
					data-target="#reviewsModal">Отзывы (${reviewsCount})</g:link>

		</g:if>
		<g:else>
			<a href=# class=otz>Отзывов нет</a>
		</g:else>


		<a href=# class=stat>Статистика</a>

	</div>
	<g:if test="${accountInstance?.verifyStatus==false}">
		<div class="gray_block3" style="margin-top: 10px;border-radius: 20px">

			<h4>Рейтинг (${accountInstance.rating ? accountInstance.rating + '%' : '-'})</h4>
			<span class=rating><g:rating value="${accountInstance.rating}"/></span>


			<a href=# class=otz>Отзывы</a>



			<a href=# class=stat>Статистика</a>

		</div>

	</g:if>
</sec:ifLoggedIn>



			<sec:ifNotLoggedIn>
				<div class="gray_block" style="margin-top: 10px;">

					<h4>Рейтинг</h4>



						<a href=# class=otz>Отзывы</a>



					<a href=# class=stat>Статистика</a>

				</div>
			</sec:ifNotLoggedIn>



			<div class="claim_block">

			<g:if test="${itsMyAccount}">
				<button class="deal_button" style="border: none; background-color: grey; text-align: left" data-toggle="popover">Предложить сделку</button>
				<button class="review_button" style="border: none; background-color: grey; text-align: left" data-toggle="popover">Оставить отзыв</button>
				<hr>
				<button class="claim_button" style="border: none; background-color: grey; text-align: left" data-toggle="popover">Разрешить спор</button>
				<button class="jud_button" style="border: none; background-color: grey; text-align: left" data-toggle="popover">Подать иск</button>


			<script>
					var template = ['<div class="timePickerWrapper popover">',
						'<div class="arrow"></div>',
						'<div class="popover-content">',
						'</div>',
						'</div>'].join('');

				<g:if test="${freeAccount}">
					var content = ['<div>Данная возможность доступна только <a href="javascript:void(0)">владельцам расширенной подписки</a></div>'
					].join('');
				</g:if>


				<g:else>
					var content = ['<div>Целью этого действия не может является ваше же предприятие.</div>'
					].join('');
				</g:else>


					$(document).ready(function(){

						$('[data-toggle="popover"]').popover({
							template: template,
							content: content,
							html: true
						});

						$('[data-toggle="popover"]').on('click', function (e) {
							$('[data-toggle="popover"]').not(this).popover('hide');
						});

					});

			</script>


			</g:if>

			<g:else>
				<sec:ifNotLoggedIn>
					<g:link class="deal_button"  controller="login" action="auth"

							data-toggle="modal"
							data-remote="${createLink(controller:'login' , action: 'auth')}"
							data-target="#myModal">Предложить сделку</g:link>


					<g:link class="review_button"  controller="login" action="auth"

							data-toggle="modal"
							data-remote="${createLink(controller:'login' , action: 'auth')}"
							data-target="#myModal">Оставить отзыв</g:link>


					<hr>


					<g:link class="claim_button"  controller="login" action="auth"

							data-toggle="modal"
							data-remote="${createLink(controller:'login' , action: 'auth')}"
							data-target="#myModal"><img src="${resource(dir: 'images', file: 'front/mediation_ultra.png')}"  style="width:22px;float: left;margin-right: 5px;margin-bottom: 5px"/>Разрешить спор</g:link>

					<g:link class="jud_button"  controller="login" action="auth"

							data-toggle="modal"
							data-remote="${createLink(controller:'login' , action: 'auth')}"
							data-target="#myModal">Подать иск</g:link>


				</sec:ifNotLoggedIn>

				<sec:ifLoggedIn>
					<g:link class="deal_button" controller="deal" action="create" params="[partner: accountInstance.id]">Предложить сделку</g:link>
					<g:link class="review_button" controller="deal" action="index" params="[to: accountInstance.id,var: val]">Оставить отзыв</g:link>
					<hr>
					<g:link class="claim_button" controller="deal" action="index" params="[partner: accountInstance.id,rar:rar]"><img src="${resource(dir: 'images', file: 'front/mediation_ultra.png')}"  style="width:22px;float: left;margin-right: 5px;margin-bottom: 5px"/>Разрешить спор</g:link>
					<g:link class="jud_button" controller="deal" action="index" params="[partner: accountInstance.id,sal:sal]">Подать иск</g:link>
				</sec:ifLoggedIn>

			</g:else>





			</div>
			<g:if test="${accountInstance?.verifyStatus==false}">

				<button class="deal_button" style="margin-top: 20px;border: none; background-color: grey; text-align: left" data-toggle="popover">Предложить сделку</button>
				<button class="review_button" style="border: none; background-color: grey; text-align: left" data-toggle="popover">Оставить отзыв</button>
				<hr>
				<button class="claim_button" style="border: none; background-color: grey; text-align: left" data-toggle="popover"><img src="${resource(dir: 'images', file: 'front/mediation_ultra.png')}" style="width:22px;float: left;margin-right: 5px;margin-bottom: 5px"/>Разрешить спор</button>
				<button class="jud_button" style="border: none; background-color: grey; text-align: left" data-toggle="popover">Подать иск</button>


			</g:if>

		</div>

		<div class="col-md-7">

<g:if test="${accountInstance?.verifyStatus==false}">
	<br>
	<div class=" glyphicon glyphicon-ban-circle status" style="color: red;font-size: 12pt;"> Данный пользователь не авторизован!</div>
	<hr>
</g:if>
			<h2 style="border-bottom: 2px solid #6fb35b;border-left:2px solid #6fb35b;border-radius: 20px;border-right:2px solid #6fb35b;">О компании</h2>

			<g:if test="${accountInstance?.description}">
				<div class=description style="text-align: center">
					<g:if test="${accountInstance.description.length() >220}">
						<g:truncate max="220" words="true" tail="" value="${accountInstance.description}" varTail="tail"><span id="tail">…</span> <span id="next"
																																						style="display: none;">${tail}</span>
							<a class="podr" id="open" href="javascript:void(0)" onclick="$('#tail').hide();
							$('#next').show();
							$('#close').show();
							$(this).hide();">Подробнее</a>
							<a class="podr" id="close" style="display: none" href="javascript:void(0)" onclick="$('#tail').show();
							$('#next').hide();
							$('#open').show();
							$(this).hide();">Свернуть</a>
						</g:truncate>

					</g:if>
					<g:else>
						<g:fieldValue bean="${accountInstance}" field="description"/>
					</g:else>
				</div>
				<hr>
			</g:if>



			<div class="clearfix"></div>





<div class="searchandgoods">
	<g:if test="${goods.size()>20}">
		<g:if test="${!goods.isEmpty()}">
			<h2 style="border-bottom: 2px solid #6fb35b;border-left:2px solid #6fb35b;border-right:2px solid #6fb35b;border-radius: 20px">
				<g:link url="[controller: 'goods', params: [company: accountInstance.id]]">Наши предложения</g:link></h2>

			<ul class="bxsliderGoods" >
				<g:each in="${goods}" var="item">
					<li class="tablet">


							<g:link url="[controller: 'goods', action: 'item', id: item.id]">
								<g:if test="${item?.photo}">
									<img class="img-thumbnail" style="border: none"
										 src="${createLink(controller: 'files', action: 'item', id: item.photo?.imageThumb.id, params: [name: item.photo?.imageThumb.file])}"/>
								</g:if>

								<g:else>
									<img class="img-thumbnail" src="${resource(dir: 'images', file: 'front/goods.png')}" style="border: none"/>
								</g:else>

								<span>${item.name ?: ''}</span><br>
								<span>${item.price}</span> <span>${item.currency.name}</span>
							</g:link>


					</li>
				</g:each>
			</ul>
			<script>
				$('.bxsliderGoods').bxSlider({
					minSlides: 6,
					moveSlides:1,
					maxSlides: 20,
					slideWidth: 160,
					slideMargin: 10,
					auto: true,
					pause: 0,
					speed: 3500,
					autoHover:true
				});
			</script>
		</g:if>
	</g:if>
	<g:else>
		<g:if test="${!goods.isEmpty()}">
			<h2 style="border-bottom: 2px solid #6fb35b;border-left:2px solid #6fb35b;border-right:2px solid #6fb35b;border-radius: 20px">
				<g:link url="[controller: 'goods', params: [company: accountInstance.id]]">Наши предложения</g:link></h2>
			<ul class="bxsliderGoods" style="padding: 0;text-align: center">
				<g:each in="${goods}" var="item">
					<li class="tablet">

							<g:link url="[controller: 'goods', action: 'item', id: item.id]">
								<g:if test="${item?.photo}">
									<img class="img-thumbnail" style="border: none"
										 src="${createLink(controller: 'files', action: 'item', id: item.photo?.imageThumb.id, params: [name: item.photo?.imageThumb.file])}"/>
								</g:if>

								<g:else>
									<img class="img-thumbnail" src="${resource(dir: 'images', file: 'front/goods.png')}" style="border: none"/>
								</g:else>

								<span>${item.name ?: ''}</span><br>
								<span>${item.price}</span> <span>${item.currency.name}</span>
							</g:link>



					</li>
				</g:each>
			</ul>

		</g:if>
	</g:else>

	<g:if test="${services.size()>20}">
		<g:if test="${!services.isEmpty()}">
			<h2 style="border-bottom: 2px solid #6fb35b;border-right:2px solid #6fb35b;border-left:2px solid #6fb35b;border-radius: 20px">
				<g:link url="[controller: 'services', params: [company: accountInstance.id]]">Все услуги</g:link></h2>
			<ul class="bxsliderServices">
				<g:each in="${services}" var="good">

						<li class="tablet">
							<g:link url="[controller: 'services', action: 'item', id: good.id]">
								<g:if test="${good?.photo}">


									<img class="img-thumbnail"
										 src="${createLink(controller: 'files', action: 'item', id: good.photo?.imageThumb.id, params: [name: good.photo?.imageThumb.file])}" style="border: none"/>

								</g:if>
								<g:else>
									<img class="img-thumbnail" src="${resource(dir: 'images', file: 'front/services.png')}" style="border: none"/>
								</g:else>
								<span>${good.name ?: ''}</span><br>
								<span>${good.price}</span> <span>${good.currency.name}</span>
							</g:link>
						</li>



				</g:each>
			</ul>
			<script>
				$('.bxsliderServices').bxSlider({
					minSlides: 6,
					moveSlides:1,
					maxSlides: 20,
					slideWidth: 160,
					slideMargin: 10,
					auto: true,
					pause: 0,
					speed: 3500,
					autoHover:true
				});
			</script>

		</g:if>
	</g:if>
	<g:else>
		<g:if test="${!services.isEmpty()}">
			<h2 style="border-bottom: 2px solid #6fb35b;border-right:2px solid #6fb35b;border-left:2px solid #6fb35b;border-radius: 20px">
				<g:link url="[controller: 'services', params: [company: accountInstance.id]]">Все услуги</g:link></h2>
			<ul class="bxsliderServices" style="padding: 0;text-align: center">
				<g:each in="${services}" var="good">

						<li class="tablet">
							<g:link url="[controller: 'services', action: 'item', id: good.id]">
								<g:if test="${good?.photo}">


									<img class="img-thumbnail"
										 src="${createLink(controller: 'files', action: 'item', id: good.photo?.imageThumb.id, params: [name: good.photo?.imageThumb.file])}" style="border: none"/>

								</g:if>
								<g:else>
									<img class="img-thumbnail" src="${resource(dir: 'images', file: 'front/services.png')}" style="border: none"/>
								</g:else>
								<span>${good.name ?: ''}</span><br>
								<span>${good.price}</span> <span>${good.currency.name}</span>
							</g:link>
						</li>


				</g:each>
			</ul>

		</g:if>

	</g:else>

	<g:if test="${!goods.isEmpty()||!services.isEmpty()}">
		<sec:ifLoggedIn>
			<h2 style="border-bottom: 2px solid #6fb35b;border-right:2px solid #6fb35b;border-left:2px solid #6fb35b;border-radius: 20px">
				<g:link url="[controller: 'adcontent',action: 'index2', params: [company: accountInstance.name]]">Все рекламные материалы</g:link></h2>
		</sec:ifLoggedIn>
		<sec:ifNotLoggedIn>
			<h2 style="border-bottom: 2px solid #6fb35b;border-right:2px solid #6fb35b;border-left:2px solid #6fb35b;border-radius: 20px">
			<g:link   controller="login" action="auth"

					data-toggle="modal"
					data-remote="${createLink(controller:'login' , action: 'auth')}"
					data-target="#myModal">Все рекламные материалы</g:link>
		</sec:ifNotLoggedIn>

	</g:if>

</div>


			<div class="clearfix"></div>

		</div>

		<div class="col-md-2">

			<img src="${resource(dir: 'images', file: 'front/local.png')}"/> <g:if test="${accountInstance?.city}">
			<span  class="property-value" aria-labelledby="city-label">${accountInstance?.city?.name}</span></g:if>
<sec:ifLoggedIn>
			<div class="address" >
			<g:if test="${accountInstance?.address}">
				<g:fieldValue bean="${accountInstance}" field="address"/>
				<hr>
			</g:if>
		</div>
</sec:ifLoggedIn>
			<g:if test="${freeAccount}">
			</g:if>
			<g:else>



				<script type="application/javascript">
					var myMap = null;
					var myPlacemark = null;
					function showAddres(city, addr) {
						//console.log(city + "," + addr);
						if (city != "") {
							ymaps.geocode(city + (addr != "" ? (", " + addr) : ""), {results: 1}).then(function (res) {
								// Выбираем первый результат геокодирования
								var firstGeoObject = res.geoObjects.get(0);
								var center = firstGeoObject.geometry.getCoordinates();
								myMap = new ymaps.Map("map", {
									center: center,
									zoom: addr != "" ? 15 : 13
								});
								if (addr != "") {
									if (myPlacemark) {
										myMap.geoObjects.remove(myPlacemark);
									}
									myPlacemark = new ymaps.Placemark(firstGeoObject.geometry.getCoordinates(), {
										hintContent: addr
									});
									myMap.geoObjects.add(myPlacemark);
								}
								myMap.controls.add(new ymaps.control.ZoomControl());
								var src = "http://static-maps.yandex.ru/1.x/?l=map&pt=" + center[1] + "," + center[0] + "&z=" + (addr != "" ? "16" : "10") + "&size=230,200";
								$("#smallmap").append($("<img/>").attr("src", src))
								$("#map").show();
							}, function (err) {
								console.error(err.message);
							});
						}
					}
					$(function () {
						ymaps.ready(function () {
							showAddres('${accountInstance?.city?.name}', '${accountInstance?.address}');
						});
					});
				</script>

				<div style="position: relative; width: 100%;max-width: 400px;margin: 0 auto">
					<a id="smallmap" href="#" onclick="return false;" data-toggle="modal" data-target="#myModalMap" style="display: block;position: relative;">
						<span class="map-link">
							<span class="glyphicon glyphicon-fullscreen btn-lg" style="vertical-align: middle;"></span>
							<span style="vertical-align: middle;text-align: left;display: inline-block;">Посмотреть адрес <br> на карте</span>
						</span>
					</a>
				</div>

				<!-- Modal -->
				<div class="modal fade" id="myModalMap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content" style="width: 80%;margin: 0 auto">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Закрыть</span></button>
								<h4 class="modal-title" id="myModalLabel">${accountInstance?.city?.name}<g:if test="${accountInstance?.address}">, <g:fieldValue
										bean="${accountInstance}"
										field="address"/></g:if></h4>
							</div>

							<div class="modal-body">
								<div id="map" class="map img-thumbnail" style="width:100%; height:600px; display: none;"></div>

							</div>
						</div>
					</div>
				</div>
			</g:else>



			<br>



		<g:if test="${accountInstance.affiliates}">
			<sec:ifLoggedIn>
				<button type="button" class="btn-primary btn" style="width: 100%" data-toggle="modal"  data-target="#myModalAff">
					Список филиалов
				</button>
			</sec:ifLoggedIn>
		<sec:ifNotLoggedIn>
			<g:link class="btn-primary btn" style="width: 100%" controller="login" action="auth"

					  data-toggle="modal"
					  data-remote="${createLink(controller:'login' , action: 'auth')}"
					  data-target="#myModal">Список филиалов</g:link>

		</sec:ifNotLoggedIn>


			<!--g:else-->
				<!-- Button trigger modal -->



				<!-- Modal -->
				<div class="modal fade" id="myModalAff" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
								<h4 class="modal-title" style="text-align: center" id="myModalLabel">Список филиалов</h4>
							</div>

							<div class="modal-body">
								<ul style="list-style: none">

									<g:each in="${accountInstance.affiliates}" var="aff" status="i">
										<hr>
										<li>
											<ul style="list-style: none;margin-bottom: 30px;text-align: left">
												<g:if test="${aff.address}">
													<li>Адресс : ${aff.address}</li>
												</g:if>
												<g:if test="${aff.city}">
													<li>Город : ${aff.city.name}</li>
												</g:if>
												<g:if test="${aff.phone}">
													<li>Телефон : ${aff.phone}</li>
												</g:if>
												<g:if test="${aff.email}">
													<li>E-mail : ${aff.email}</li>
												</g:if>
											</ul>

										</li>
									</g:each>

								</ul>

							</div>

							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
							</div>
						</div>
					</div>
				</div>
			<!--/g:else-->
			<hr>
		</g:if>







<sec:ifLoggedIn>
	<g:if test="${accountInstance?.phone1}">
		<div class="phone"><img src="${resource(dir: 'images', file: 'front/tel.png')}"/>
		<g:fieldValue bean="${accountInstance}" field="phone1"/>
		<hr>
	</g:if>



	</div>


	<div class="email">

		<g:if test="${accountInstance?.email}">
			Email : <g:fieldValue bean="${accountInstance}" field="email"/>

			<hr>
		</g:if>

	</div>
</sec:ifLoggedIn>


	<div class="manager">
		<b>Руководитель : </b><br>


		<span class="property-value" aria-labelledby="manager-label"><g:fieldValue bean="${accountInstance}" field="manager"/></span>


		<hr>

	</div>

	<div class="worktime">
		<b>Режим работы : </b><br>
		<g:if test="${accountInstance?.workTime}">
			<g:fieldValue bean="${accountInstance}" field="workTime"/>
			<hr>
		</g:if>
	</div>








			<g:if test="${freeAccount}">
				<g:render template="/_common/promo-modal" />
			</g:if>
		</div>

	</div>

</div>

<g:render template="/_common/modal" model="[
		container: 'reviewsContainer',
		modalId  : 'reviewsModal'
]"/>



</body>
</html>