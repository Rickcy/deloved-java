<%@ page import="grails.plugin.springsecurity.SpringSecurityUtils; org.codehaus.groovy.grails.commons.DefaultGrailsApplication" %>
<!DOCTYPE html>
<html lang="ru" style="overflow-x: hidden;
width: 106%;
margin-left: -3%;">
<head>
	<title><g:layoutTitle default="Бизнес-портал Деловед"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<asset:stylesheet src="front.css"/>
	<asset:stylesheet src="deloved.css"/>
	<asset:javascript src="front.js"/>


	<link rel="shortcut icon" type="image/vnd.microsoft.icon" href="${createLinkTo(dir: 'images', file: 'favicon/favicon.ico')}"/>
	<g:layoutHead/>
<sec:ifLoggedIn>
	<script>
		$(document).ready(function() {

			var newIt = $(".newIt");

			function getNew(){
				jQuery.ajax({
					type:'GET',
					url:'${createLink([controller: 'panel', action: 'getAccount'])}',
					success:function(data,textStatus){

						var dat =data;

						var newAccounts = dat.newAcc.length;
						var newClaims = dat.newClaim.length;
						var newDeals = dat.newDeal.length;
						var newDisputes = dat.newDisp.length;
						var newReviews = dat.newReview.length;
						var newJurists = dat.newJurist.length;
						var newTickets = dat.newTicket.length;
						var newItems = dat.newItem.length;
						var newDealStatusPost = dat.newDealStatusPost.length;
						var newDealPost = dat.newDealPost.length;
						var newClaimStatusPost = dat.newClaimStatusPost.length;
						var newClaimPost = dat.newClaimPost.length;
						var newJuristStatusPost = dat.newJuristStatusPost.length;
						var newJuristPost = dat.newJuristPost.length;
						var newTicketStatusPost = dat.newTicketStatusPost.length;
						var newTicketPost = dat.newTicketPost.length;
						var newDisputeStatusPost = dat.newDisputeStatusPost.length;
						var newDisputePost = dat.newDisputePost.length;

						if(newAccounts!=0||newClaims!=0||newDeals!=0||newDisputes!=0||newReviews!=0||newJurists!=0||newTickets!=0||newItems!=0||newDealStatusPost!=0||
						newDealPost!=0||newClaimStatusPost!=0||newClaimPost!=0||newJuristStatusPost!=0||newJuristPost!=0||newTicketStatusPost!=0||newTicketPost!=0||newDisputeStatusPost!=0||newDisputePost!=0){
							newIt.text("+1");
						}




					},
					error:function(XMLHttpRequest,textStatus,errorThrown){
						console.log(XMLHttpRequest,textStatus)
					}
				})
			}
			getNew();
			setInterval(getNew,4000)


		})
	</script>
</sec:ifLoggedIn>
	<script>


		function authAjax() {

			var form = $("#loginForm");
			$.ajax({
				url:       form.attr("action"),
				method:   "POST",
				data:      form.serialize(),
				dataType: "JSON",
				success: function(json,status, textStatus, jqXHR) {

					if(json.error){
						var ib = $('#flash-message');
						showMessage(json,status);
						function showMessage(json,status){

							if ($.inArray(status, ['info', 'danger', 'warning', 'success']) != -1) {
								ib.attr('class', ib.attr('class').replace(/\balert-\w+\b/g, 'alert-'+status));
							} else {
								ib.attr('class', ib.attr('class').replace(/\balert-\w+\b/g, 'alert-info'));
							}

							ib.clearQueue();
							ib.stop();
							ib.hide();
							ib.html(json.error);

							setTimeout(function(){
								ib.fadeIn(450);
								setTimeout(function(){
									ib.fadeOut(4000, function() {
									});
									ib.mouseenter(function() {
										ib.clearQueue();
										ib.stop();
										ib.animate({opacity: 1});
									});
									ib.mouseleave(function() {
										ib.fadeOut(8000, function() {
										});
									});
								}, 4000)

							}, 100)
						}

					}else{
						if(window.location.pathname=='${createLink(controller: 'front',action: 'index')}'){
							document.location.href='${createLink(controller: 'panel',action: 'index')}'
						}
						else {
							window.location.reload()
						}


					}},
				error: function(jqXHR, textStatus, errorThrown,responseText) {

					console.log(responseText);


				}
			});
		}



	</script>
	<script>
		$(document).ready(function(){

			var path_name_1 = window.location.pathname;
			var path_name_2 = "${createLink(controller: 'front', action: 'index')}";

			if(path_name_1!=path_name_2){
				$(".EadnD").remove()
			}



		});

	</script>
	<style>
		.navbar-top{
			border-bottom: 1px solid #a8dc43;
			-webkit-box-shadow: 0 0 10px #94C43D;
			-moz-box-shadow: 0 0 10px #94C43D;
			box-shadow:  0 0 10px #94C43D;
		}
body{
	overflow-x: hidden;
}
#auth{
	color:black;
}
.navbar-brand {
	float: none;
	padding: 10px 5px;
}
.navbar-nav .nav-item {
	float: none;
	margin: 0 20px;

}
.navbar-divider,
.navbar-nav .nav-item+.nav-item,
.navbar-nav .nav-link + .nav-link {
	margin-left: 0;
}
@media (min-width: 34em) {
	.navbar-brand {
		float: left;
		min-height: 30px!important;
	}
	.navbar-nav .nav-item {

		display: block;
		margin-left: 0;
		padding: 10px 0 10px 30px;


	}
	.navbar-divider,
	.navbar-nav .nav-item + .nav-item,
	.navbar-nav .nav-link + .nav-link {
		display: block;
		margin-left: 0;
		padding: 10px 0 10px 30px;

	}
}
@media (max-width: 34em) {
	.navbar-brand {
		display: inline-block;
		min-height: 30px!important;
		padding: 10px 5px;
	}
	.navbar-nav .nav-item {

		display: block;
		margin-left: 0;
		padding: 10px 0 10px 30px;


	}

}

.navbar-toggler {
	vertical-align:middle;
	padding: .7rem .7rem;
	margin: .5rem .25rem;
	background-color: transparent;
	background-image: none;
	border: 1px solid transparent;
	border-radius: 4px;
}

.navbar-toggler {
	border-color: #ddd;
}
.navbar-toggler:focus, .navbar-toggler:hover {
	background-color: #ddd;
}
.navbar-toggler .icon-bar {
	display: block;
	width: 22px;
	height: 2px;
	border-radius: 1px;
}
.navbar-toggler .icon-bar {
	background-color: #888;
}
.navbar-toggler .icon-bar+.icon-bar {
	margin-top: 4px;
}
@media (max-width: 767px) {

	.navbar-brand{
		float: left;
	}
	.menu_search{
		display: none;
	}
	.text_main_front{
		font-size: 15pt!important;
	}
	.movie_delo{
		width: 75%!important;
		margin-top: 8%!important;
	}
	#auth{
		margin-right:4%;
		margin-left: 4%;
	}
}
@media (min-width: 768px) {
	.ico:hover{
		-webkit-transform: scale(1.2);
		-moz-transform: scale(1.2);
		-o-transform: scale(1.2);
		-moz-transition: all 400ms ease-out;
		-o-transition: all 400ms ease-out;
		-webkit-transition: all 400ms ease-out;
	}
	.icovideo:hover{
		-webkit-transform: scale(1.1);
		-moz-transform: scale(1.1);
		-o-transform: scale(1.1);
		-moz-transition: all 400ms ease-out;
		-o-transition: all 400ms ease-out;
		-webkit-transition: all 400ms ease-out;
	}
	.icovideo1:hover{
		-webkit-transform: scale(1.2);
		-moz-transform: scale(1.2);
		-o-transform: scale(1.2);
		-moz-transition: all 300ms ease-out;
		-o-transition: all 300ms ease-out;
		-webkit-transition: all 300ms ease-out;
	}
	.navbar-toggler{
		display: none;
	}
	.navbar-brand{
		float: right;
	}
	#auth{
		margin-right: 7%;
		margin-left: 4%;
	}
	.navbar{
		min-height: 40px;
		height: 40px;
		margin-bottom: 0;
	}
	.menu_mobile_search{
		display: none;
	}

}

</style>
</head>

<body style="width: 95%;margin: 0 auto">


<g:render template="/_common/activation-warning"/>
<g:render template="/_common/flash-message"/>

<nav class="navbar navbar-dark navbar-top bg-inverse" style="background-color: rgb(239, 239, 239);width: 110%;margin-left: -5%;padding: 0 30px;;">
	<button style="float: right;margin-right: 2%;" type="button" class="navbar-toggler hidden-sm-up pull-xs-right" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
		<span class="sr-only">Toggle navigation</span>
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
	</button>
	<sec:ifLoggedIn>
		<g:link class="navbar-brand" style="font-size: 11pt;margin-right: 7%;margin-left:2%;color: black" controller="panel" action="index"><span style="margin-right: 3px" class="glyphicon glyphicon-user"  ></span>В Кабинет<span class="badge badge_red newIt" s></span></g:link>

	</sec:ifLoggedIn>
	<sec:ifNotLoggedIn>
		<g:link class="navbar-brand loginBtn" style="font-size: 11pt;margin-right: 7%;margin-left:2%;color: black" controller="login" action="auth"


				data-toggle="modal"
				data-remote="${createLink(controller:'login' , action: 'auth')}"
				data-target="#myModal"><span style="margin-right: 3px" class="glyphicon glyphicon-user"  ></span>Кабинет</a></g:link>

		%{--<g:render  template="/layouts/auth" model="[]"/>--}%
	</sec:ifNotLoggedIn>
	<sec:ifLoggedIn>
		<g:form id="logoutForm" url="[controller: 'logout', action: 'index']" method="POST"></g:form>
		<a class="navbar-brand" style="font-size: 11pt;color: black"  href="#" onclick="$('#logoutForm').submit()"><span style="margin-right: 3px" class="glyphicon glyphicon-off"  ></span>Выйти</a>
	</sec:ifLoggedIn>
	<sec:ifNotLoggedIn>
		<g:link class="navbar-brand registert" style="font-size: 11pt;color: black" controller="signup" action="index"><span style="margin-right: 3px" class="glyphicon glyphicon-pencil" ></span>Регистрация</g:link>
	</sec:ifNotLoggedIn>
	<div class="navbar-brand visible-xs EadnD" style="padding: 0;margin: 0 0 0 2.4%;">
		<div class="navbar-brand E" ><img src="${resource(dir: 'images', file: 'front/Dol.png')}" style="width: 25px"/><span style="font-size: 10pt;font-weight: 600" class="D_money"></span></div>
		<div class="navbar-brand D" ><img src="${resource(dir: 'images', file: 'front/Ev.png')}" style="width: 25px"/><span style="font-size: 10pt;font-weight: 600" class="E_money"></span>
		</div>
		</div>
	<div class="collapse navbar-toggleable-xs" id="navbar" style="clear: both">

		<nav class="nav navbar-nav pull-xs-left">
			<g:set var="section">companies</g:set>
			<g:if test="${params.controller in ['companies', 'company']}">
				<g:set var="section">companies</g:set>
			</g:if>
			<g:elseif test="${params.controller in ['goods']}">
				<g:set var="section">goods</g:set>
			</g:elseif>
			<g:elseif test="${params.controller in ['services']}">
				<g:set var="section">services</g:set>
			</g:elseif>


	<g:link class="${(section == 'companies') ? 'active' : ''} nav-item nav-link" url="[controller: 'companies']"><span class="glyphicon glyphicon-briefcase" style="margin-right: 3%;font-size: 14pt" > </span>Партнеры</g:link>
	<g:link	class="${(section == 'goods') ? 'active' : ''} nav-item nav-link" url="[controller: 'goods']"><span class="glyphicon glyphicon-shopping-cart" style="margin-right: 3%;font-size: 14pt" ></span>Товары</g:link>
	<g:link	class="${(section == 'services') ? 'active' : ''} nav-item nav-link" url="[controller: 'services']"><span class="glyphicon glyphicon-wrench" style="margin-right: 3%;font-size: 14pt" ></span>Услуги</g:link>
	<g:link	class=" nav-item nav-link" url="[controller: 'front', action:'about']"><span class="glyphicon glyphicon-comment" style="margin-right: 3%;font-size: 14pt" ></span>О проекте</g:link>
	<g:link	class="nav-item nav-link" url="[controller: 'front', action:'tariffs']"><span class="glyphicon" style="margin-right: 5%;font-size: 18pt;padding-top: 0;margin-top: -6px;" >₽</span>Тарифы</g:link>
		</nav>


	</div>
</nav>

<div class="row menu_mobile_search">

	<div class="col-xs-12">
		<div class="col-xs-3" style="padding: 0;margin: 0">
			<g:link controller="front"><img class="imga" src="${resource(dir: 'images', file: 'front/logo.gif')}" style="float: right;width: 100%;min-width: 81px;max-width: 97px"/></g:link>
		</div>
		<div class="col-xs-9">

			<g:form class="pull-sm-right" controller="${section}" action="search" method="POST">

				<div class="input-group col-md-12 col-sm-12 col-xs-12">
					<g:textField required="" name="search" class="form-control" style="height: 45px;font-size: 15pt;box-shadow: inset 0 0 15px #e2e2e2" value="" placeholder="Поиск"/>
					<span class="input-group-btn">
						<button class="btn btn-default" type="submit" style="height: 45px;margin-right: 5px;background-color:#94c43d"><span class="glyphicon glyphicon-search" style="padding: 10px;font-size: 15pt;color: white"></span> </button>
					</span>
				</div>
			</g:form>
			<div class="search_block">

				<g:link controller="${section}" action="search" class="search_link">Расширенный поиск</g:link>

			</div>
		</div>

	</div>

</div>




<div class="row menu_search" style="margin-bottom:0px;">
	<div class="col-md-3 col-sm-3 col-xs-9" style="padding-top: 15px;padding-left: 70px;padding-right: 20px"><g:link controller="front"><img class="imga" src="${resource(dir: 'images', file: 'front/logo.gif')}" style="width: 78%;max-width:250px;min-width: 150px;float: right;margin-right: 15px "/></g:link></div>

	<div class="col-md-9 col-sm-9 col-xs-9 " style="float: right;padding: 0">
<div class="fm">
		<g:set var="section">companies</g:set>
		<g:if test="${params.controller in ['companies', 'company']}">
			<g:set var="section">companies</g:set>
		</g:if>
		<g:elseif test="${params.controller in ['goods']}">
			<g:set var="section">goods</g:set>
		</g:elseif>
		<g:elseif test="${params.controller in ['services']}">
			<g:set var="section">services</g:set>
		</g:elseif>

		<div class="layer" style="margin-top: 0.6%">
	         <g:link class="${(section == 'companies') ? 'active' : ''}" url="[controller: 'companies']"><span class="glyphicon glyphicon-briefcase" style="margin-right: 3%;font-size: 14pt" > </span>ПАРТНЕРЫ </g:link>
	         <g:link	class="${(section == 'goods') ? 'active' : ''}" url="[controller: 'goods']"><span class="glyphicon glyphicon-shopping-cart" style="margin-right: 3%;font-size: 14pt" ></span> ТОВАРЫ</g:link>
	         <g:link	class="${(section == 'services') ? 'active' : ''}" url="[controller: 'services']"><span class="glyphicon glyphicon-wrench" style="margin-right: 3%;font-size: 14pt" ></span> УСЛУГИ</g:link>
	         <g:link	class="about" url="[controller: 'front', action:'about']"><span class="glyphicon glyphicon-comment" style="margin-right: 3%;font-size: 14pt" ></span> О ПРОЕКТЕ</g:link>
	         <g:link	class="about" url="[controller: 'front', action:'tariffs']"><span class="glyphicon" style="margin-right: 3%;font-size: 18pt;padding-top: 0;margin-top: -6px;" >₽</span> ТАРИФЫ</g:link>

</div>

</div>
		<!-- поисковая форма -->

			<g:form controller="${section}" action="search" method="POST">

				<div class="input-group col-md-11  col-sm-11 col-xs-11">
					<g:textField required="" name="search" class="form-control" style="height: 45px;font-size: 15pt;box-shadow: inset 0 0 15px #e2e2e2" value="" placeholder="Поиск"/>
					<span class="input-group-btn">
						<button class="btn btn-default" type="submit" style="height: 45px;margin-right: 5px;background-color:#94c43d"><span class="glyphicon glyphicon-search" style="padding: 10px;font-size: 15pt;color: white"></span> </button>
					</span>
				</div>
			</g:form>

		<!--конец формы-->
		<div class="search_block">

			<g:link controller="${section}" action="search" class="search_link">Расширенный поиск</g:link>

		</div>

	</div>

</div>



<div class="content" style="z-index: 100"><g:layoutBody/></div>



<g:render template="/layouts/footer"/>

<asset:deferredScripts/>

<g:render template="/_common/edit-container"/>
</body>
</html>