<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="front">
	<title><g:titleContent code="TDKContent"/></title>
	<g:descKeyContent code="TDKContent"/>


	<asset:javascript src="/mCustomScrollbar/jquery.mCustomScrollbar.concat.min.js"/>
	<asset:javascript src="/frontend.js"/>

	<asset:link rel="stylesheet" href="/mCustomScrollbar/jquery.mCustomScrollbar.min.css"/>
	<asset:link rel="stylesheet" href="/main.css"/>
	<asset:link rel="buttons" href="/mCustomScrollbar/mCSB_buttons.png"/>

	<script>

		$(document).ready(function(){
			if($(window).width()>487){
				$("#hiddenShowen").click(function() {
					// Отображаем скрытый блок
					$("#hide_block_lg").toggle('slow'); // fadeIn - плавное появление

				});
			}else{
				$("#hiddenShowen").click(function() {
					// Отображаем скрытый блок
					$("#hide_block_xs").toggle('slow'); // fadeIn - плавное появление

				});
			}


					 // end of toggle()
			var str = $('.new>b');
			var str2=$('.new>h4');
			if($(window).width()<768){

				var str_3 =$(".ContContentP");
				str_3.each(function(i,e){
					var text_1 = $(e).text();
					var val_1 = text_1.substr(0,412);
					var inertext_1=this.innerText=val_1;
				});
			}

			$('.new').addClass('col-md-12');
			str.each(function(i,elem){
				var text = $(elem).text();
				var val = text.substr(0,130)+'...';
				var inertext=this.innerText=val;
			});
			str2.each(function(i,elem){
				var text = $(elem).text();
				var val='<g:link style="text-decoration: underline" controller='article' action='news' params='[var:val]'>'+text+'</g:link>';
				var inertext = this.innerHTML=val
			});
			str2.each(function(i,elem){
				$(elem).click(function(){
					localStorage.clear();
					localStorage.search =$(elem).text();
					var t =localStorage.search;

				})
			})





		});

	</script>

	<script src="http://api-maps.yandex.ru/2.0-stable/?load=package.standard&lang=ru-RU" type="text/javascript"></script>
	<script type="text/javascript">
		window.onload = function () {
			$("#user-city").text(ymaps.geolocation.city);

			var text = $('#user-city').text();

		}
	</script>
	<script>

		window.onload=function(){
			$("#status").fadeOut(0),$("#preloader").delay(300).fadeOut("slow")
			var usd_money = $(".usd_money").text()
			var euro_money =$(".euro_money").text()

			$(".D_money").text(" "+usd_money)
			$(".E_money").text(" "+euro_money)
			var el_1 = $(".text_main");
			var el_2 = $(".resixe")
			var el_3 = $(".playvideo")

			if($(window).width()>767){

				var windowsHeight_1 = $(window).height();
			var elementOffset_2 = $(".second_block").offset().top

			if(windowsHeight_1>elementOffset_2){

				var result_123 = windowsHeight_1-elementOffset_2;
				if(result_123>0){
					var resssult = result_123/4+"px"
				el_1.css('marginTop',resssult)
				el_2.css('marginTop',resssult)
				el_3.css('marginTop',resssult)
				el_3.css('marginBottom',resssult)
			}}
		}}
	</script>
	<style>
		#hide_block{
			-moz-transition: all 300ms ease-out;
			-o-transition: all 300ms ease-out;
			-webkit-transition: all 300ms ease-out;
		}
	.ContContent{
		border-bottom: 2px solid silver;
		padding-bottom: 15px;
		font-size: 22pt;
		color:#428BCA;
		font-weight: bold;
		padding-top: 30px;
	}


	.shadow_block{
		position: relative;
		background-color: white;
	}
	.shadow_block:after {
		content: "";
		display: block;
		position: absolute;
		width: 125%;
		margin-left: -13%;
		z-index: -1;
		height: 20px;
		bottom: 0;
		right: 1em;
		left: 1em;
		border-radius: 50%;
		box-shadow: 0 0 10px 12px rgba(0,0,0,.2);
	}
	.shadow_block:before {
		content: "";
		display: block;
		position: absolute;
		width: 125%;
		margin-left: -13%;
		z-index: -1;
		height: 20px;
		top: 0;
		right: 1em;
		left: 1em;
		border-radius: 50%;
		box-shadow: 0 0 10px 12px rgba(0,0,0,.2);
	}


	</style>

</head>

<body>
<div id="preloader">
	<div id="status">
		<img src="${resource(dir: 'images', file: 'preloader.gif')}"/>
	</div>
</div>




<g:render template="/_common/flash-message"/>

<div class="row first_block block_main">


	<div class="row text_main">
		<div class="col-xs-10 col-xs-offset-1 text_main_front" style="margin-top:1%;font-size: 31px;color: #5697E4;border-radius: 20px">
			<p class="text-center">Заключайте выгодные контракты в режиме онлайн, получайте юридическую защиту сделок, выбирайте контрагентов с высоким деловым рейтингом!</p>
		</div>
	</div>

	<div class="row resixe" style="width: 110%;margin-left: -5%;padding: 2%;">




		<div class="row" >
			<div class="col-xs-12">
			<div class="col-xs-10  col-xs-offset-1 col-sm-2 col-sm-offset-1  text-center ico" ><g:link style="color: white;" controller="article" action="deal_online" > <img src="${resource(dir: 'images', file: 'front/icon_deal.png')}" style="max-width:100px;"/><br><span class="text_btn_main" style="font-size: 15pt;color:#94C43D;font-weight: 600 ">Сделки онлайн</span></g:link></div>
			<div class="col-xs-5   col-xs-offset-1 col-sm-2 col-sm-offset-0 text-center ico" ><g:link style="color: white;text-shadow: 0 0 1px whitesmoke;" controller="article" action="rating_system" ><img src="${resource(dir: 'images', file: 'front/icon_rating.png')}" style="max-width:100px;"/><br><span class="text_btn_main" style="font-size: 15pt;color:#94C43D;font-weight: 600">Рейтинг</span></g:link></div>
			<div class="col-xs-5   col-xs-offset-0 col-sm-2 col-sm-offset-0 text-center ico" ><g:link style="color: white;text-shadow: 0 0 1px whitesmoke" controller="article" action="jurist_service" ><img src="${resource(dir: 'images', file: 'front/icon_jurist.png')}" style="max-width:100px;"/> <br><span class="text_btn_main" style="font-size: 15pt;color:#94C43D;font-weight: 600">Помощь юриста</span></g:link></div>
			<div class="col-xs-5   col-xs-offset-1 col-sm-2 col-sm-offset-0 text-center ico" ><g:link style="color: white;text-shadow: 0 0 1px whitesmoke" controller="article" action="judge_service" ><img src="${resource(dir: 'images', file: 'front/icon_sud.png')}" style="max-width:100px;"/> <br><span class="text_btn_main" style="font-size: 15pt;color:#94C43D;font-weight: 600">Третейский суд</span></g:link></div>
			<div class="col-xs-5   col-xs-offset-0 col-sm-2 col-sm-offset-0 text-center ico" ><g:link style="color: white;text-shadow: 0 0 1px whitesmoke" controller="article" action="mediation_service" ><img src="${resource(dir: 'images', file: 'front/icon_mediation.png')}" style="max-width:100px"/> <br><span class="text_btn_main" style="font-size: 15pt;color:#94C43D;font-weight: 600">Медиация</span></g:link></div>
		</div>
		</div>

	</div>

	<div class="row playvideo" >
		<div class="col-xs-10 col-xs-offset-1 text-center playvideo">
			<div class="col-xs-0 col-sm-1"></div>
			<div class="col-xs-10 col-xs-offset-1 col-sm-5 col-sm-offset-0" style="position: relative;margin-top: 15px">

				<img  src="${resource(dir: 'images', file: 'front/videow.png')}" style="width: 100%;">
				<g:render  template="video" model="[]"/>


			</div>
			<div class="col-xs-10 col-xs-offset-1 col-sm-5 col-sm-offset-0" style="position: relative;margin-top: 15px">
				<g:link controller="signup" action="index">
					<img  src="${resource(dir: 'images', file: 'front/videoq.png')}" style="width: 100%;">
					<img  class="icovideo1" src="${resource(dir: 'images', file: 'front/on_btn.png')}" style="width: 20%;position: absolute;top: 30%;right: 40%">

				</g:link>
			</div>
		</div>
	</div>
</div>
<div class="shadow_block hidden-xs">


	<div class="row second_block block_main " style="background-color:white;margin-top: 7%;margin-bottom: 6%;width: 110%;margin-left: -5%;padding:5%">
		<!-- Навигационная панель с 3 вкладками -->
		<ul class="nav nav-pills nav-justified" style="margin-left: 5%;width: 89%;box-shadow:0 0 15px #747474;background-color:#4b70b2;border-top-left-radius:10px;border-top-right-radius:15px" role="tablist" id="myTabExample">
			<li class="actives a"><a href="#deal_online" style="min-height: 55px;font-size: 11pt;color: white" role="tab" data-toggle="tab"><img src="${resource(dir: 'images', file: 'front/dea.png')}" style="max-width:45px;float: left;border-bottom-right-radius: 10px;border-bottom-left-radius:10px "/>СДЕЛКИ <br> ОНЛАЙН</a></li>
			<li class="a"><a href="#rating_system" role="tab" style="min-height:55px;font-size: 11pt;color: white" data-toggle="tab"><img src="${resource(dir: 'images', file: 'front/rating_ultra.png')}" style="max-width:45px;float: left;border-bottom-right-radius: 10px;border-bottom-left-radius:10px"/>РЕЙТИНГОВАЯ <br> СИСТЕМА</a></li>
			<li class="a"><a href="#jurist_system" role="tab" style="min-height: 55px;font-size: 11pt;color: white" data-toggle="tab"><img src="${resource(dir: 'images', file: 'front/sud_ultra.png')}" style="max-width:45px;float: left;border-bottom-right-radius: 10px;border-bottom-left-radius:10px"/>ЮРИДИЧЕСКАЯ <br> СЛУЖБА</a></li>
			<li class="a"><a href="#sud_system" role="tab" style="min-height: 55px;font-size: 11pt;color: white" data-toggle="tab"><img src="${resource(dir: 'images', file: 'front/hammer.png')}" style="max-width:45px;float: left;border-bottom-right-radius: 10px;border-bottom-left-radius:10px"/>ТРЕТЕЙCКИЙ <br> СУД</a></li>
			<li class="a"><a href="#mediation" role="tab" style="min-height: 55px;font-size: 11pt;color: white" data-toggle="tab"><img src="${resource(dir: 'images', file: 'front/mediation_ultra.png')}" style="max-width:45px;float: left;border-bottom-right-radius: 10px;border-bottom-left-radius:10px"/>МЕДИАЦИЯ <br><b style="opacity: 0">служба</b></a></li>
		</ul>

		<!-- Содержимое вкладок -->
		<div class="tab-content" style="width: 89%;box-shadow: 0 0 20px #9c9c9c;margin-left: 5%  ">
			<!-- Содержимое 1 вкладки -->
			<div class="tab-pane active" id="deal_online">
				<img src="${resource(dir: 'images', file: 'front/deal_online.jpg')}" style="width: 100%"/>
			</div>

			<!-- Содержимое 2 вкладки -->
			<div class="tab-pane active" id="rating_system">
				<img src="${resource(dir: 'images', file: 'front/rating.jpg')}" style="width: 100%"/>

			</div>
			<!-- Содержимое 3 вкладки -->
			<div class="tab-pane active" id="jurist_system">
				<img src="${resource(dir: 'images', file: 'front/jurist.jpg')}" style="width: 100%"/>
			</div>
			<div class="tab-pane active" id="sud_system">
				<img src="${resource(dir: 'images', file: 'front/sud.jpg')}" style="width: 100%"/>
			</div>
			<div class="tab-pane active" id="mediation">
				<img src="${resource(dir: 'images', file: 'front/mediation.jpg')}" style="width: 100%"/>
			</div>
		</div>
		<img src="${resource(dir: 'images', file: 'front/playlist.png')}" style="width:10%;position: absolute;bottom: 2%;right: 45%"/>
		<!-- Скрипт для активирования работы вкладок -->
		<script>
			$(function() {
				$('#myTabExample a:first').tab('show')
			});
		</script>
	</div>
</div>

<div class="partnersAndgoods third_block block_main" style="width: 87%;margin-left: 6%">
	<h2 class="AccountsMainh1 text_main_front" >Надежные партнеры</h2>
	<div class="shadow_block">


		<div style="background-color:white;width: 124%;margin-left: -12%;padding: 13px 0">

			<div class="partners" style="width: 96%;margin-left: 2%">

				<g:if test="${mainAccounts!=null}">
					<g:if test="${mainAccounts.size()>5}">




						<ul class="AccountsMain" style="padding: 0">


							<g:each in="${mainAccounts}" var="accountInstance">
								<li class="tablet" style="width: 17%;font-size: 90%;margin-top: 13px">

									<g:link url="[controller: 'company', id: accountInstance.id]">
										<g:if test="${accountInstance.logoThumb}">

											<img class="img-thumbnail" style="border: none;width: 171px;max-height: 171px"
												 src="${createLink(controller: 'files', action: 'logo', id: accountInstance.logoThumb?.id, params: [name: accountInstance.logoThumb?.file])}"/>




										</g:if>

										<g:else>
											<img class="img-thumbnail" src="${resource(dir: 'images', file: 'front/logo_uch.png')}" style="border: none;width: 188px;max-height: 171px;margin-bottom: 7%"/>
										</g:else>


										<div align="center">${accountInstance.name}</div>

									</g:link>

								</li>
							</g:each>
						</ul>


						<script>
							$('.AccountsMain').bxSlider({
								minSlides: 5,
								moveSlides:1,
								maxSlides: 20,
								slideWidth: 220,
								slideMargin: 10,
								auto: true,
								pause: 0,
								speed: 3500,

								autoHover:true
							});
						</script>
					</g:if>
					<g:else>



						<ul class="AccountsMain" style="padding: 0">


							<g:each in="${mainAccounts}" var="accountInstance">
								<li class="tablet" style="width: 17%;font-size: 90%">

									<g:link url="[controller: 'company', id: accountInstance.id]">
										<g:if test="${accountInstance.logoThumb}">

											<img class="img-thumbnail" style="border: none;width: 171px;max-height: 171px"
												 src="${createLink(controller: 'files', action: 'logo', id: accountInstance.logoThumb?.id, params: [name: accountInstance.logoThumb?.file])}"/>




										</g:if>

										<g:else>
											<img class="img-thumbnail" src="${resource(dir: 'images', file: 'front/logo_uch.png')}" style="border: none;width: 188px;max-height: 171px;margin: 3.5% 0 3.5% 0"/>
										</g:else>


										<div align="center">${accountInstance.name}</div>

									</g:link>

								</li>
							</g:each>
						</ul>


					</g:else>
				</g:if>
			</div>
		</div>
	</div>
	<h2 class="GoodsMainh1 text_main_front">Выгодные предложения</h2>
	<div class="shadow_block">
		<div style="background-color:white;width: 124%;margin-left: -12%;padding: 13px 0">
			<div class="goods" style="width: 84%;margin-left: 8%">




				<ul class="GoodsMain" style="padding: 0">

					<g:if test="${mainGoods!=null&&mainGoods.size()>5}">

						<g:each in="${mainGoods}" var="item">
							<li class="tablet" style="width:17%;font-size: 90%">

									<g:link url="[controller: 'goods', action: 'item', id: item.id]">
										<g:if test="${item?.photo}">
											<img class="img-thumbnail" style="border: none;width: 188px;max-height: 171px"
												 src="${createLink(controller: 'files', action: 'item', id: item.photo?.imageThumb.id, params: [name: item.photo?.imageThumb.file])}"/>
										</g:if>

										<g:else>
											<img class="img-thumbnail" src="${resource(dir: 'images', file: 'front/goods.png')}" style="border: none;width: 188px;max-height: 171px"/>
										</g:else>
										<br>
										<span>${item.name ?: ''}<br>
											${item.price}
											${item.currency.name}

										</span>
									</g:link>



							</li>
						</g:each>

					</g:if>



					<g:if test="${mainServices!=null&&mainServices.size()>3}">
						<g:each in="${mainServices}" var="item">
							<li class="tablet" style="width: 17%;font-size: 90%">

									<g:link url="[controller: 'services', action: 'item', id: item.id]">
										<g:if test="${item?.photo}">
											<img class="img-thumbnail" style="border: none;width: 188px;max-height: 171px"
												 src="${createLink(controller: 'files', action: 'item', id: item.photo?.imageThumb.id, params: [name: item.photo?.imageThumb.file])}"/>
										</g:if>

										<g:else>
											<img class="img-thumbnail" src="${resource(dir: 'images', file: 'front/services.png')}"  style="border: none;width: 188px;max-height: 171px"/>
										</g:else>
										<br>
										<span>${item.name ?: ''}<br>
											${item.price}
											${item.currency.name}

										</span>
									</g:link>

							</li>
						</g:each>
					</g:if>
				</ul>

				<g:if test="${mainServices!=null&&mainServices.size()>3||mainGoods!=null&&mainGoods.size()>5}">
					<script>
						$('.GoodsMain').bxSlider({
							minSlides: 5,
							moveSlides:1,
							maxSlides: 20,
							slideWidth: 200,
							slideMargin: 10,
							auto: true,
							pause: 0,
							speed: 3500,

							autoHover:true
						});
					</script>
				</g:if>




				<ul class="GoodsMain" style="padding: 0;width: 116%;margin-left: -8%">
					<g:if test="${mainGoods!=null&&mainGoods.size()<=5}">
						<g:each in="${mainGoods}" var="item">
							<li class="tablet" style="width: 17%;font-size: 90%">

									<g:link url="[controller: 'goods', action: 'item', id: item.id]">
										<g:if test="${item?.photo}">
											<img class="img-thumbnail"style="border: none;width: 188px;max-height: 171px"
												 src="${createLink(controller: 'files', action: 'item', id: item.photo?.imageThumb.id, params: [name: item.photo?.imageThumb.file])}"/>
										</g:if>

										<g:else>
											<img class="img-thumbnail" src="${resource(dir: 'images', file: 'front/goods.png')}" style="border: none;width: 188px;max-height: 171px"/>
										</g:else>
										<br>
										<span>${item.name ?: ''}<br>
											${item.price}
											${item.currency.name}

										</span>
									</g:link>


							</li>
						</g:each>
					</g:if>
					<g:if test="${mainServices!=null&&mainServices.size()<=3}">
						<g:each in="${mainServices}" var="item">
							<li class="tablet" style="width:17%;font-size: 90%">

									<g:link url="[controller: 'services', action: 'item', id: item.id]">
										<g:if test="${item?.photo}">
											<img class="img-thumbnail" style="border: none;width: 188px;max-height: 171px"
												 src="${createLink(controller: 'files', action: 'item', id: item.photo?.imageThumb.id, params: [name: item.photo?.imageThumb.file])}"/>
										</g:if>

										<g:else>
											<img class="img-thumbnail" src="${resource(dir: 'images', file: 'front/services.png')}"  style="border: none;width: 188px;max-height: 171px"/>
										</g:else>
										<br>
										<span>${item.name ?: ''}<br>
											${item.price}
											${item.currency.name}

										</span>
									</g:link>


							</li>
						</g:each>
					</g:if>
				</ul>




			</div>
		</div>
	</div>
</div>
<div class="shadow_block">
	<div class="row fourth_block block_main" style="background-color:white;margin-top:10%;padding: 2%;width: 124%;margin-left:-12%;">
		<div class="newsandvalutes col-lg-12 col-md-12 col-md-offset-0 col-lg-offset-0 col-xs-10 col-xs-offset-1 "  >


			<div class="col-md-offset-1 col-lg-offset-1 col-lg-7 col-md-7 col-sm-12 col-xs-12" >
				<div class="col-md-8 col-lg-8 col-sm-8 col-xs-7 text_main_front" style="color: #428BCA;font-size: 22pt;font-weight: bold;padding-bottom: 10px;padding-left: 0">Новости</div>



				<div class="news col-lg-12 col-md-12 col-sm-12 col-xs-12" style="background-color: white;border-top: 2px solid silver;border-bottom: 2px solid silver;padding-left: 0">
					<div id="scrollContent" style="height: 325px" class="scrollContent">

						<div id="newss">
							<g:render template="newsTemlate"/>



						</div>



					</div>


				</div>
				<div class="row">

					<div class="col-xs-12">
						<g:link controller="article" action="news"><span style="font-size: 14pt;float: left;text-decoration: underline;font-weight: 600;margin-top: 2%;margin-bottom: 2%"><i>Все новости</i></span></g:link>
					</div>


				</div>
			</div>

			<div class="valutes col-lg-3 col-md-3 col-sm-8 col-xs-12 col-sm-offset-2 col-lg-offset-0 col-md-offset-0 hidden-xs"  >
				<div class="col-md-12 col-lg-12 col-sm-12 col-xs-12 text_main_front" style="padding-bottom: 10px;text-align: center;"><a title="Курс валют ЦБ РФ на сегодня" href="http://xn--2-stbsei.xn--p1ai/" onclick="return false" style="font-size: 22pt;font-weight: bold;">Курс валют</a></div>
				<div style="background-color: #e8e8e8;float:left;width: 95%;min-height: 325px;border-radius: 30px;">
					<div class="row" style="margin-top: 30px;padding: 15px 0;background-color: white">
						<div class="col-md-4 col-sm-2 col-xs-4 col-md-offset-1 col-sm-offset-1" style="text-align: center;margin-top: 7px">
							<a href="http://www.cbr.ru/" style="font-size: 12pt;" title="Курс доллара" target="_blank" ><img src="${resource(dir: 'images', file: 'front/Dol.png')}" style="width: 55px"/></a>
						</div>
						<div class="col-md-6 col-xs-6">
							<div class="row" style="margin-top: 10%">
								<div class="col-md-12 col-sm-12 col-xs-12 usd_money" style="padding: 0;text-align: center;font-weight:bold;font-size: 13pt;" id="USD_td">00.000</div>


							</div>
							<div class="row" style="margin-top: 10%">
								<div class="col-md-6 col-sm-6 col-xs-6" style="padding:0;text-align: center;font-weight:bold;font-size: 11pt;"><img src="http://xn--2-stbsei.xn--p1ai/images/arr_red.png" id="USD_src"></div>
								<div class="col-md-4 col-sm-4 col-xs-4" style="padding: 0;text-align: left;font-weight:bold;font-size: 11pt;" id="USD_tm">0.000</div>
							</div>

						</div>
					</div>
					<div class="row" style="padding: 15px 0;background-color: white;margin-top:50px">
						<div class="col-md-4 col-sm-2 col-xs-4 col-md-offset-1 col-sm-offset-1" style="text-align: center;margin-top: 7px">
							<a href="http://www.cbr.ru/" style="font-size: 12pt;" title="Курс Евро" target="_blank" ><img src="${resource(dir: 'images', file: 'front/Ev.png')}" style="width: 55px"/></a>
						</div>
						<div class="col-md-6 col-xs-6">
							<div class="row" style="margin-top: 10%">
								<div class="col-md-12 col-sm-12 col-xs-12 euro_money" style="padding: 0;text-align: center;font-weight:bold;font-size: 13pt;" id="EUR_td">00.0000</div>

							</div>
							<div class="row" style="margin-top: 10%">
								<div class="col-md-6 col-sm-6 col-xs-6" style="padding:0;text-align: center;font-weight:bold;font-size: 11pt;" ><img src="http://xn--2-stbsei.xn--p1ai/images/arr_red.png" id="EUR_src"></div>
								<div class="col-md-4 col-sm-4 col-xs-4" style="padding: 0;text-align: left;font-weight:bold;font-size: 11pt;" id="EUR_tm">0.000</div>
							</div>


						</div>

					</div>
				</div>

				<script src="http://xn--2-stbsei.xn--p1ai/b11/generateCode"></script>



			</div>

		</div>
	</div>

</div>
%{--<script>--}%
	%{--function checkFlash() {--}%
		%{--var flashinstalled = false;--}%
		%{--if (navigator.plugins) {--}%
			%{--if (navigator.plugins["Shockwave Flash"]) {--}%
				%{--flashinstalled = true;--}%
			%{--}--}%
			%{--else if (navigator.plugins["Shockwave Flash 2.0"]) {--}%
				%{--flashinstalled = true;--}%
			%{--}--}%
		%{--}--}%
		%{--else if (navigator.mimeTypes) {--}%
			%{--var x = navigator.mimeTypes['application/x-shockwave-flash'];--}%
			%{--if (x && x.enabledPlugin) {--}%
				%{--flashinstalled = true;--}%
			%{--}--}%
		%{--}--}%
		%{--else {--}%
			%{--// на всякий случай возвращаем true в случае некоторых экзотических браузеров--}%
			%{--flashinstalled = true;--}%
		%{--}--}%
		%{--return flashinstalled;--}%
	%{--}--}%
	%{--if (checkFlash()) {--}%

	%{--} else {--}%
		%{--$('#FlashPlay').remove();--}%
	%{--}--}%
%{--</script>--}%



<div class="shadow_block">
	<div class="row fifth_block block_main" style="background-color: white;margin-bottom: 3%;margin-top:10%;padding-bottom: 10px;padding-left: 5%;width: 124%;margin-left:-12%;">
		<div class="col-xs-10 col-xs-offset-1 TDKContent" style="padding-left:0;padding-right:5%;padding-bottom: 40px">
			<g:contContent code="TDKContent"/>


			<a href="javascript:void(0)" id="hiddenShowen" ><span style="font-size: 14pt;float: left;text-decoration: underline;font-weight: 600;margin-top: 3%"><i>Подробнее</i></span></a>
			<div style="margin-top: 130px;background-color: silver;height: 2px;width: 100%"></div>
		</div>

	</div>
</div>
%{--<div id="FlashPlay" style="text-align: center;margin-top: 60px">--}%
	%{--<object width="70%" classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"--}%
			%{--codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,18,0"--}%
			%{--type="application/x-shockwave-flash" src="${resource(dir: 'images', file: 'front/bannerHH.swf')}">--}%
		%{--<param name="quality" value="high" />--}%
		%{--<param name="scale" value="noborder" />--}%
		%{--<param name="salign" value="t" />--}%
		%{--<param name="wmode" value="opaque" />--}%
		%{--<param name="src" value="${resource(dir: 'images', file: 'front/bannerHH.swf')}" />--}%
		%{--<embed width="70%" id="linebanner" src="${resource(dir: 'images', file: 'front/bannerHH.swf')}"> </embed>--}%
	%{--</object>--}%
%{--</div>--}%
<script>



</script>
</body>
</html>
