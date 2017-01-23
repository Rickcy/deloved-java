<%@ page import="ru.deloved.Deal" %>
<%@ page import="ru.deloved.DealStatus" %>
<%@ page import="ru.deloved.ConsultCategory" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title>Сделка</title>
	<asset:javascript src="/dropzonejs/dropzone.min.js"/>
	<asset:javascript src="/mCustomScrollbar/jquery.mCustomScrollbar.concat.min.js"/>
	<asset:link rel="stylesheet" href="/mCustomScrollbar/jquery.mCustomScrollbar.min.css"/>
	<asset:link rel="buttons" href="/mCustomScrollbar/mCSB_buttons.png"/>
	<script>

		$(window).load(function(){
			$("#scrollContent").mCustomScrollbar({
				alwaysShowScrollbar: 1,
				advanced:{
					updateOnContentResize: true,
					updateOnImageLoad: true
				},
				callbacks:{
					whileScrolling: function () {

					},
					onInit: function() {
						$('#scrollContent').mCustomScrollbar('scrollTo','bottom');
					},
					onUpdate: function(){
						if (checkBottomEdge() == true) {
							$('#scrollContent').mCustomScrollbar('scrollTo','bottom');
						}
					},
				}
			});
		});

		function checkBottomEdge() {
			var scrollbar = $('#mCSB_1_scrollbar_vertical').height()
			var dragger = $('#mCSB_1_dragger_vertical').height()
			var draggerPosition = $('#mCSB_1_dragger_vertical').position().top
			var pctButton =  (1 - (dragger + draggerPosition)/scrollbar)
			var psA =  49 / $('#postsArea3').height()
			if (pctButton < psA) {
				return true
			} else {
				return false
			}
		}

	</script>

</head>
<body>
	<g:if test="${freeAccount}">
		<g:render template="/_common/promo"/>
	</g:if>
	<g:else>

	<div id="deal-thread" class="content scaffold-list" role="main">
		<g:render template="/_common/flash-message"/>

		<g:render template="deal-partners" model="[
		        dealInstance: dealInstance,
		]"/>
<sec:ifAnyGranted roles="ROLE_ACCOUNT">
	<g:render template="/_common/thread" model="[
		threadPosts         : posts,
		threadAccount       : myAccount ?: dealInstance.account,
		threadStatusPrefix  : 'deal.status',
		threadStatusTemplate: 'thread-status'

]"/>


</sec:ifAnyGranted>
<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MEDIATOR,ROLE_JUDGE,ROLE_JURIST">
	<div id="scrollContent" class="scrollContent">

		<div id="postsArea3">

			<g:render template="/_common/thread-post" model="[
					threadPosts         : posts,
					threadAccount       : myAccount ?: dealInstance.account,
					threadStatusPrefix  : 'deal.status',
					threadStatusTemplate: 'thread-status'
			]"/>

		</div>


		<span class="glyphicon glyphicon-upload arrows" id="up"></span>
	</div>
	<script>

		$(window).load(function(){
					$("#scrollContent").mCustomScrollbar({
						alwaysShowScrollbar: 1,
						advanced:{
							updateOnContentResize: true,
							updateOnImageLoad: true
						},
						callbacks:{
							whileScrolling: function () {

							},
							onInit: function() {
								$('#scrollContent').mCustomScrollbar('scrollTo','bottom');
							},
							onUpdate: function(){
								if (checkBottomEdge() == true) {
									$('#scrollContent').mCustomScrollbar('scrollTo','bottom');
								}
							},
						}
					});
				},function(){
					$('#up').click(function() {
								$('.mCSB_container').animate({top: '0'},2000);
								$('.mCSB_dragger').animate({top: '0'},2000)
							}
					)
				}
		);

		function checkBottomEdge() {
			var scrollbar = $('#mCSB_1_scrollbar_vertical').height()
			var dragger = $('#mCSB_1_dragger_vertical').height()
			var draggerPosition = $('#mCSB_1_dragger_vertical').position().top
			var pctButton =  (1 - (dragger + draggerPosition)/scrollbar)
			var psA =  49 / $('#posts').height()
			if (pctButton < psA) {
				return true
			} else {
				return false
			}
		};



	</script>
</sec:ifAnyGranted>

		<div class="row" style="margin-top: 20px;margin-bottom: 10px; margin-left: 8%; width: 84%">

<sec:ifAnyGranted roles="ROLE_ACCOUNT"><div id="reviewAlert" class="alert alert-info" style="margin-bottom: 5px; display: ${showReviewAlert ? 'block' : 'none'}">
	Сделка успешно завершена, а вы до сих пор не оставили к ней отзыв! Отзыв об успешной сделке является одной из составляющих в формировании вашей деловой репутации.
	<br><a  onclick="window.location.href='${createLink(controller: 'review', action: 'create', id: dealInstance.id)}'" style="cursor: pointer"
		${[DealStatus.Confirmed.value(), DealStatus.Failed.value()].contains(dealInstance.status) ? '' : 'disabled'}>Поддержите своих партнеров и они поддержат вас!</a>
</div>
</sec:ifAnyGranted>

			<g:render template="deal-progress" model="[
					dealInstance: dealInstance
			]"/>

			<sec:ifAnyGranted roles="ROLE_ACCOUNT">

				<g:render template="change-status" model="[
							statusList: statusList,
							objInstance: dealInstance
				]"/>

				<form    id="messageForm" name="messageForm" class="form-horizontal" action="" method="post" style="margin-top: 10px" >
					<g:render template="/_common/hint" model="[hintText: 'Удалить сообщение можно путем наведения на сообщение! Доступно в течении 5 минут после публикации.']"/>
					<g:hiddenField name="version" value="${dealInstance?.version}"/>
					<textarea
					${DealStatus.Proposed.value()==dealInstance.status ? 'disabled' :'' }
						id="messageText" class="form-control" name="post" rows="4" placeholder="${DealStatus.Proposed.value()==dealInstance.status?'Сделка не подтверждена':'Ваше сообщение' }"></textarea>
					<div class="pods">Подсказка: Enter - отправить сообщение, Ctrl+Enter - перенос строки</div>
				</form>

				<div style="padding: 0;margin-top:2%;margin-bottom: 5%;" align="right">
					<div class="co-xs-12 col-sm-6">
					<button ${DealStatus.Proposed.value()==dealInstance.status ? 'disabled' :'' } id="messageSubmit" class="btn btn-success dsa" name="messageSubmit" type="submit" form="messageForm" style="margin-bottom: 10px;border-radius: 15px; float: left;width:100%;">
						<i class="glyphicon glyphicon-comment"></i>
						${message(code: 'deal.send.label')}
					</button>

					</div>
					<div class="col-sm-1 hidden-xs"></div>
					<div class="co-xs-12 col-sm-5">
					<div id="hiddenInputContainer"></div>
					<button ${DealStatus.Proposed.value()==dealInstance.status ? 'disabled' :'' } id="sendFiles" class="btn btn-success" name="sendFiles" type="button" style="margin-bottom: 10px; width:100%;border-radius: 15px">
						<i class="glyphicon glyphicon-paperclip"></i>
						Отправить файлы
					</button>
					</div>
				</div>









				<div class="row">
					<div ><g:render template="/_common/hint" model="[hintText: 'Отзыв, спор и иск доступны на определенных этапах сделки».   ']"/></div>
					<div class="col-xs-12 col-xs-offset-0 col-lg-2 col-lg-offset-0 col-md-5 col-md-offset-1 col-sm-5 col-sm-offset-1">
					<div id="toReview" class="btn interaction-btn review-btn1" style="float: left;padding-right: 70px;" onclick="window.location.href='${createLink(controller: 'review', action: 'create', id: dealInstance.id)}'"
						${[DealStatus.Confirmed.value(), DealStatus.Failed.value()].contains(dealInstance.status) ? '' : 'disabled'}>

						Оставить отзыв

						<div class="rew"></div>
					</div>
					</div>
				<div class="col-xs-12 col-xs-offset-0 col-lg-2 col-lg-offset-2 col-md-5 col-md-offset-1 col-sm-5 col-sm-offset-1">
						<g:if test="${myAccount==dealInstance.account}">
						<div id="toDispute" class="btn interaction-btn dispute-btn1 "  ${(dealInstance.status < DealStatus.SignUP.value()) ? 'disabled' : ''} onclick="
							window.location.href='${createLink(controller: 'dispute', action: 'create', params: [partner:dealInstance.partner.id,deal:dealInstance.id])}'">
							<img src="${resource(dir: 'images', file: 'front/mediation_ultra.png')}" style="width:17%;float: left;margin-right: 5px;margin-bottom: 5px"/>
							Разрешить спор

							<div class="disp"></div>

						</div>
					</g:if>
					<g:if test="${myAccount==dealInstance.partner}">
						<div id="toDispute" class="btn interaction-btn dispute-btn1 "  ${(dealInstance.status < DealStatus.SignUP.value()) ? 'disabled' : ''} onclick="
							window.location.href='${createLink(controller: 'dispute', action: 'create', params: [partner:dealInstance.account.id,deal: dealInstance.id])}'">
							<img src="${resource(dir: 'images', file: 'front/mediation_ultra.png')}" style="width:17%;float: left;margin-right: 5px;margin-bottom: 5px"/>
							Разрешить спор

							<div class="disp"></div>

						</div>
					</g:if>
					</div>
				<div class="col-xs-12 col-xs-offset-0 col-lg-2 col-lg-offset-2 col-md-5 col-md-offset-4 col-sm-5 col-sm-offset-4">
					<g:if test="${myAccount==dealInstance.account}">
						<div id="toClaim" class="btn interaction-btn claim-btn1"  ${(dealInstance.status <  DealStatus.SignUP.value()) ? 'disabled' : ''} onclick="
							window.location.href='${createLink(controller: 'claim', action: 'create', params: [partner: dealInstance.partner.id, deal: dealInstance.id])}'">

							Подать иск

							<div class="claim"></div>
						</div>
					</g:if>
					<g:if test="${myAccount==dealInstance.partner}">
						<div  id="toClaim" class="btn interaction-btn claim-btn1" ${(dealInstance.status < DealStatus.SignUP.value()) ? 'disabled' : ''}
							 onclick="
							window.location.href='${createLink(controller: 'claim', action: 'create', params: [partner: dealInstance.account.id,deal: dealInstance.id])}'">

							Подать иск

							<div class="claim"></div>
						</div>
					</g:if>
				</div>
				</div>


			</sec:ifAnyGranted>

		</div>
		<g:render template="/_common/gallery-multi"/>
		<g:render template="scripts" model="[dealInstance: dealInstance]"/>
	</div>
	</g:else>

</body>
</html>