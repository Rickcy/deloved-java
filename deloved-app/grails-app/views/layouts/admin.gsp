<%@ page import="ru.deloved.Account" contentType="text/html;charset=UTF-8" %>
<%@ page import="org.codehaus.groovy.grails.plugins.jquery.JQueryConfig; ru.deloved.Account" %>
<!DOCTYPE html >
<html lang="ru" >
<head>
	<title><g:layoutTitle/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<style>
	.fr{
		font-family: Verdana, Geneva, sans-serif;
	}
		.ft{
			font-family: 'Palatino Linotype', 'Book Antiqua', Palatino, serif;
		}
	</style>
	<asset:stylesheet src="admin.css"/>
	<asset:stylesheet src="deloved.css"/>
	<asset:javascript src="front.js"/>
	<asset:javascript src="application.js"/>
	<asset:javascript src="/mCustomScrollbar/jquery.mCustomScrollbar.concat.min.js"/>
	<link rel="shortcut icon" type="image/vnd.microsoft.icon" href="${createLinkTo(dir: 'images', file: 'favicon/favicon.ico')}"/>
	<g:layoutHead/>


</head>

<body   style="background-color: #ececec; padding: 15px;width: 92%;margin: 0 auto" >

<g:render template="/_common/flash-message"/>

<g:render template="/_common/activation-warning"/>

<g:render template="/layouts/navigation-bar"/>

<div class="row" style="margin: 0; padding: 0" >
	<div id="saved-title" style="    width: 0px;
	height: 0px;
	overflow: hidden;
	display: none;"></div>
	<div class=" col-lg-3 col-md-4" style="padding: 0 10px 0 0" id="asd">
		<div class="left-block"  >

			<ul class="admin_menu">

				<li><a class="home" href="${createLink(controller: "panel")}"><g:message code="default.home.label"/></a></li>

			</ul>


			<sec:ifAnyGranted roles="ROLE_ADMIN">

				<h1 class="ft">Справочники</h1>

				<div class="ug"></div>

				<ul class="admin_menu">

					<li <g:if test="${params.controller == 'category'}">class="active"</g:if>><g:link controller="category"><g:message code="category.list.label"/></g:link></li>
					<li <g:if test="${params.controller == 'measure'}">class="active"</g:if>><g:link controller="measure"><g:message code="measure.list.label"/></g:link></li>
					<li <g:if test="${params.controller == 'region'}">class="active"</g:if>><g:link controller="region"><g:message code="region.list.label"/></g:link></li>
					<li <g:if test="${params.controller == 'content'}">class="active"</g:if>><g:link controller="content"><g:message code="content.list.label"/></g:link></li>
					<li <g:if test="${params.controller == 'systemCurrency'}">class="active"</g:if>><g:link controller="systemCurrency"><g:message code="currency.list.label"/></g:link></li>
					<li <g:if test="${params.controller == 'org'}">class="active"</g:if>><g:link controller="org"><g:message code="org.list.label2"/></g:link></li>
					<sec:ifAnyGranted roles="ROLE_ADMIN">
						<li <g:if test="${params.controller == 'tariffPrice'}">class="active"</g:if>><g:link controller="tariffPrice"><g:message code="tariff.list.label"/></g:link></li>
					</sec:ifAnyGranted>
				</ul>
			</sec:ifAnyGranted>
			<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
				<h1 class="ft">Обратная связь</h1>
				<div class="ug"></div>

				<ul class="admin_menu">
					<li <g:if test="${params.controller == 'suggestion'}">class="active"</g:if>><g:link controller="suggestion">Связаться с нами


						</g:link></li>
					<sec:ifAnyGranted roles="ROLE_ADMIN">
						<li <g:if test="${params.controller == 'suggestionCategories'}">class="active"</g:if>><g:link controller="suggestionCategories">Категории связи</g:link></li>
					</sec:ifAnyGranted>


					<li <g:if test="${params.controller == 'ticket'}">class="active"</g:if>><g:link controller="ticket">Служба поддержки
						<span class="badge badge_red ticketNew"></span>
					</g:link></li>
				</ul>
			</sec:ifAnyGranted>





			<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">

				<h1 class="ft">Пользователи</h1>

				<div class="ug"></div>

				<ul class="admin_menu">

					<li <g:if test="${params.controller == 'user'}">class="active"</g:if>><g:link controller="user"><g:message code="user.list.label"/></g:link></li>
					<li <g:if test="${params.controller == 'profile'}">class="active"</g:if>><g:link controller="profile"><g:message code="profile.list.label"/></g:link></li>

				</ul>
			</sec:ifAnyGranted>

			<sec:ifAnyGranted roles="ROLE_ADMIN">
				<h1 class="ft">Биллинг</h1>

				<div class="ug"></div>

				<ul class="admin_menu">
					<li <g:if test="${params.controller == 'paymentRequest'}">class="active"</g:if>><g:link controller="paymentRequest">Счета на оплату</g:link></li>
				</ul>
			</sec:ifAnyGranted>

			<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER,ROLE_ACCOUNT">

				<h1 class="ft">Бизнес</h1>

					<div class="ug"></div>

					<ul class="admin_menu">

						<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
					<li <g:if test="${params.controller == 'account'}">class="active"</g:if>><g:link controller="account">Предприятия
						<span class="badge badge_red accountNew"></span>
					</g:link></li>
					</sec:ifAnyGranted>

					<sec:ifAnyGranted roles="ROLE_ACCOUNT">
						<li <g:if test="${params.controller == 'account'}">class="active"</g:if>><g:link controller="account">Мои данные</g:link></li>
					</sec:ifAnyGranted>
					<!-- Сделки -->
<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_ACCOUNT">
	<li <g:if test="${params.controller == 'deal'}">class="active"</g:if>><g:link controller="deal"><g:message code="deal.list.label"/>
	<span class="badge badge_red dealNew"></span>
	<g:if test="${freeAccount}"><span class="badge badge_green badge_pro">pro</span></g:if>
</g:link></li>
</sec:ifAnyGranted>


				<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER,ROLE_ACCOUNT">
					<!-- Отзывы -->
					<li <g:if test="${params.controller == 'review'}">class="active"</g:if>><g:link controller="review">Отзывы
						<span class="badge badge_red reviewNew"></span>
						<g:if test="${freeAccount}"><span class="badge badge_green badge_pro">pro</span></g:if>
					</g:link></li>

					<!-- Товары -->
					<li <g:if test="${params.controller == 'item' && params.categoryTypeCode == 'GOOD'}">class="active"</g:if>><g:link mapping="GOOD"><g:message
							code="goods.list.label"/>
						<span class="badge badge_red itemNew"></span>
					</g:link></li>

					<!-- Услуги -->
					<li <g:if test="${params.controller == 'item' && params.categoryTypeCode == 'SERVICE'}">class="active"</g:if>><g:link mapping="SERVICE"><g:message
							code="services.list.label"/>
						<span class="badge badge_red itemNew"></span>
					</g:link>
					</li>

					<!-- Рекламные материалы -->
					<li <g:if test="${params.controller == 'adcontent'}">class="active"</g:if>><g:link controller="adcontent"><g:message code="adcontent.list.label"/>
						<g:if test="${freeAccount}"><span class="badge badge_green badge_pro">pro</span></g:if>
					</g:link></li>
				</sec:ifAnyGranted>
				</ul>

			</sec:ifAnyGranted>

			<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_ACCOUNT,ROLE_MEDIATOR,ROLE_JURIST,ROLE_JUDGE">
				<h1 class="ft">Юриспрудеция</h1>
				<div class="ug"></div>
				<ul class="admin_menu">
					<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_ACCOUNT,ROLE_MEDIATOR">
						<li <g:if test="${params.controller == 'dispute'}">class="active"</g:if>><g:link controller="dispute"><g:message code="dispute.list.label"/>
							<span class="badge badge_red disputeNew"></span>
							<g:if test="${freeAccount}"><span class="badge badge_green badge_pro">pro</span></g:if>
						</g:link></li>
					</sec:ifAnyGranted>
					<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_JUDGE,ROLE_ACCOUNT">
						<li <g:if test="${params.controller == 'claim'}">class="active"</g:if>><g:link controller="claim"><g:message code="claim.list.label"/>
							<span class="badge badge_red claimNew"></span>
							<g:if test="${freeAccount}"><span class="badge badge_green badge_pro">pro</span></g:if>
						</g:link></li>
					</sec:ifAnyGranted>
					<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_JURIST,ROLE_ACCOUNT">
						<li <g:if test="${params.controller == 'juristConsult'}">class="active"</g:if>><g:link controller="juristConsult"><g:message code="consult.list.label"/>
							<span class="badge badge_red juristNew"></span>
							<g:if test="${freeAccount}"><span class="badge badge_green badge_pro">pro</span></g:if>
						</g:link></li>

					</sec:ifAnyGranted>
					<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_ACCOUNT,ROLE_MANAGER">
					<li <g:if test="${params.controller == 'document'}">class="active"</g:if>>
						<g:link controller="document"><g:message code="document.list.label"/></g:link>
					</li>
					</sec:ifAnyGranted>
				</ul>
			</sec:ifAnyGranted>

			<sec:ifAnyGranted roles="ROLE_ACCOUNT,ROLE_JUDGE,ROLE_MEDIATOR,ROLE_JURIST">
				<h1 class="ft">Служба поддержки</h1>
				<div class="ug"></div>
				<ul class="admin_menu">
					<li <g:if test="${params.controller == 'ticket'}">class="active"</g:if>><g:link controller="ticket"><g:message code="ticket.list.label"/>
						<span class="badge badge_red ticketNew"></span>
					</g:link></li>
				</ul>
			</sec:ifAnyGranted>
			<sec:ifAnyGranted roles="ROLE_ADMIN">
				<h1 class="ft">Администратор</h1>

				<div class="ug"></div>

				<ul class="admin_menu">
					<li <g:if test="${params.controller == 'adminTool'}">class="active"</g:if>><g:link controller="adminTool"><g:message code="admintool.label"/></g:link></li>

				</ul>
			</sec:ifAnyGranted>

		</div>

		<div class="shadow"></div>
	</div>

	<div class=" col-lg-9 col-md-8" style="padding: 0">
		<div class="block">
			<g:layoutBody/>
		</div>

		<div class="shadow"></div>
	</div>
</div>

<g:render template="/layouts/footer"/>

<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
<asset:deferredScripts/>
<script>
	$(document).ready(function() {
		var claimBadge=$(".claimNew");
		var DealBadge=$(".dealNew");
		var AccountBadge=$(".accountNew");
		var DisputeBadge=$(".disputeNew");
		var ReviewBadge=$(".reviewNew");
		var JuristBadge=$(".juristNew");
		var ItemBadge=$(".itemNew");
		var TicketBadge=$(".ticketNew");

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


					//Claim
					if(newClaimStatusPost>0){

						claimBadge.text("+"+newClaimStatusPost);
					}
					if(newClaims>0){

						claimBadge.text("+"+newClaims);
					}
					if(newClaimPost>0){

						claimBadge.text("+"+newClaimPost);
					}
					//---------------------------
					//Deal
					if(newDealStatusPost>0){

						DealBadge.text("+"+newDealStatusPost);
					}
					if(newDeals>0){

						DealBadge.text("+"+newDeals);
					}
					if(newDealPost>0){

						DealBadge.text("+"+newDealPost);
					}
					//Accounts
					if(newAccounts>0){

						AccountBadge.text("+"+newAccounts);
					}


					//Reviews
					if(newReviews>0){

						ReviewBadge.text("+"+newReviews);
					}

					//Jurist
					if(newJurists>0){

						JuristBadge.text("+"+newJurists);
					}
					if(newJuristStatusPost>0){

						JuristBadge.text("+"+newJuristStatusPost);
					}

					if(newJuristPost>0){

						JuristBadge.text("+"+newJuristPost);
					}
					//Ticket
					if(newTickets>0){

						TicketBadge.text("+"+newTickets);
					}
					if(newTicketStatusPost>0){

						TicketBadge.text("+"+newTicketStatusPost);
					}

					if(newTicketPost>0){

						TicketBadge.text("+"+newTicketPost);
					}

					//Dispute
					if(newDisputes>0){

						DisputeBadge.text("+"+newDisputes);
					}
					if(newDisputeStatusPost>0){

						DisputeBadge.text("+"+newDisputeStatusPost);
					}

					if(newDisputePost>0){

						DisputeBadge.text("+"+newDisputePost);
					}
					//Item
					if(newItems>0){

						ItemBadge.text("+"+newItems);
					}





				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					console.log(XMLHttpRequest,textStatus)
				}
			})
		}
		getNew();
		setInterval(getNew,5000)


	})
</script>
</body>
</html>