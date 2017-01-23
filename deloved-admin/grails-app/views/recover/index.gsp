<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="front">


	<title>Бизнес-портал Деловед</title>
	<asset:javascript src="application.js"/>

	<asset:javascript src="/mCustomScrollbar/jquery.mCustomScrollbar.concat.min.js"/>
	<asset:link rel="stylesheet" href="/mCustomScrollbar/jquery.mCustomScrollbar.min.css"/>
	<asset:link rel="buttons" href="/mCustomScrollbar/mCSB_buttons.png"/>
	<asset:link rel="stylesheet" href="/main.css"/>
	<asset:javascript src="front.js"/>
	<script>

		$(document).ready(function(){
			localStorage.clear();
			var str = $('.new>i');
			var str2=$('.new>h4');
			$('.new').addClass('col-md-6');
			str.each(function(i,elem){
				var text = $(elem).text();
				var val = text.substr(0,180)+'...';
				var inertext=this.innerText=val;
			});
			str2.each(function(i,elem){
				var text = $(elem).text();
				var val='<g:link  controller='front' action='news' params='[var:val]'>'+text+'</g:link>';
				var inertext = this.innerHTML=val
			});
			str2.each(function(i,elem){
				$(elem).click(function(){
					localStorage.search =$(elem).text();
					var t =localStorage.search;

				})
			})





		});

	</script>
	<style>
		body{
			overflow-y: hidden;
		}
	</style>
</head>

<body>



<g:render template="/_common/flash-message"/>
<ul class="nav nav-pills nav-justified" style="box-shadow: 0 0 20px #9c9c9c;width: 87%;margin: 0 auto;background-color:#4b70b2;border-top-left-radius:10px;border-top-right-radius:15px" role="tablist" id="myTabExample">
	<li class="actives a"><a href="#deal_online" style="min-height: 55px;font-size: 12pt;color: white" role="tab" data-toggle="tab"><img src="${resource(dir: 'images', file: 'front/dea.png')}" style="max-width:50px;float: left"/>СДЕЛКИ <br> ОНЛАЙН</a></li>
	<li class="a"><a href="#rating_system" role="tab" style="min-height: 55px;font-size: 12pt;color: white" data-toggle="tab"><img src="${resource(dir: 'images', file: 'front/rating_ultra.png')}" style="max-width:50px;float: left"/>РЕЙТИНГОВАЯ <br> СИСТЕМА</a></li>
	<li class="a"><a href="#jurist_system" role="tab" style="min-height: 55px;font-size: 12pt;color: white" data-toggle="tab"><img src="${resource(dir: 'images', file: 'front/sud_ultra.png')}" style="max-width:50px;float: left"/>ЮРИДИЧЕСКАЯ <br> СЛУЖБА</a></li>
	<li class="a"><a href="#sud_system" role="tab" style="min-height: 55px;font-size: 12pt;color: white" data-toggle="tab"><img src="${resource(dir: 'images', file: 'front/hammer.png')}" style="max-width:50px;float: left"/>ТРЕТЕЙКИЙ <br> СУД</a></li>
	<li class="a"><a href="#mediation" role="tab" style="min-height: 55px;font-size: 12pt;color: white" data-toggle="tab"><img src="${resource(dir: 'images', file: 'front/mediation_ultra.png')}" style="max-width:50px;float: left"/>МЕДИАЦИЯ <br><b style="opacity: 0">служба</b></a></li>
</ul>

<!-- Содержимое вкладок -->
<div class="tab-content" style="width: 87%;margin: 0 auto;box-shadow: 0 0 20px #9c9c9c">
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

<!-- Скрипт для активирования работы вкладок -->
<script>
	$(function() {
		$('#myTabExample a:first').tab('show')
	});
</script>

<script>
	$(document).ready(function(){
		var url = window.location.href;
		var regV = 'recover';

		var result = url.match(regV);
		if (result) {
			$(function (){$('.buttton1').click()
			})
		} else {

		}}
	)
</script>


<button type="button" class="buttton1" style="opacity: 0;margin-bottom: 100px"   data-toggle="modal" data-target="#Login1">
</button>
<g:render template="/_common/flash-message"/>
<div class="modal fade" id="Login1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">



			<div id="recoverContent" style="background-color: white; border-radius: 5px; border: none; width: 100%; padding: 20px">
				<g:render template="formRecover"/>
			</div>


	</div>

</div>




</body>
</html>
