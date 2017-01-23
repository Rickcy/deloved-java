<%@ page import="org.codehaus.groovy.grails.commons.DefaultGrailsApplication; ru.deloved.Profile" contentType="text/html;charset=UTF-8" %>
<%@ page import="ru.deloved.DealPost" contentType="text/html;charset=UTF-8" %>
<%@ page import="ru.deloved.Account" contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name='layout' content='admin'/>
	<title>Кабинет</title>

</head>

<body>


<g:if test="${defaultAccount}">
<sec:ifAnyGranted roles="ROLE_ACCOUNT">
<g:if test="${profile.chargeTill}">

	<g:if test="${profile.dateCreated.dateString==profile.getChargeTill().minus(31).dateString}">
		<div id="chargeTill" style="margin-top: 10px;font-size:12pt;padding: 10px 20px;text-align: center;background-color: rgba(255, 0, 0, 0.26);border-radius: 10px;border: 1px solid rgba(255, 0, 0, 0.55);margin-bottom: 10px">
		У вас Активирован Пробный период на 1 месяц до ${profile.getChargeTill().dateString}
		</div>
	</g:if>

	<script>
		console.log("${profile.dateCreated.dateString}")
		console.log("${profile.getChargeTill().minus(31).dateString}")
	</script>

</g:if>
	<g:if test="${profile.chargeTill.date - new Date().date<=4&&profile.chargeTill.month-new Date().month==0&&profile.chargeTill.year-new Date().year==0}">
		<script>
			console.log("${profile.chargeTill.date - new Date().date<=4&&profile.chargeTill.month-new Date().month==0&&profile.chargeTill.year-new Date().year==0}")
		</script>
		<div id="chargeTill" style=";margin-top:10px;font-size:12pt;padding: 10px 20px;text-align: center;background-color: rgba(255, 0, 0, 0.26);border-radius: 10px;border: 1px solid rgba(255, 0, 0, 0.55);margin-bottom: 10px">
			Осталось  ${profile.chargeTill.date - new Date().date} дня расширенной подписки

				<g:link url="[controller: 'billing', action: 'index']">Продлить</g:link>


		</div>





	</g:if>
</sec:ifAnyGranted>



	<div class="row">


	</div>
	<sec:ifAnyGranted roles="ROLE_ACCOUNT">
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title ft">Рейтинг и мои данные</h3>
					</div>

					<div class="panel-body">
						<g:if test="${freeAccount}">
							Доступно для платной подписки
						</g:if>
						<g:else>
							<h4 class="ft">Рейтинг (${defaultAccount.rating ? defaultAccount.rating + '%' : '-'})</h4>
							<span class=rating><g:rating value="${defaultAccount.rating}"/></span>

							<ul>
								<li>Положительных отзовов ${defaultAccountStat.reviewsPositiveCount}</li>
								<li>Отрицательных отзовов ${defaultAccountStat.reviewsNegativeCount}</li>
								<li>Товаров в базе ${defaultAccountStat.goodsCount}</li>
								<li>Услуг в базе ${defaultAccountStat.servicesCount}</li>
								<li>Совершенных сделок ${defaultAccountStat.dealsFinishedCount}</li>
								<li>Активных сделок ${defaultAccountStat.dealsInProcessCount}</li>
								<li>Открытых споров ${defaultAccountStat.disputesInProcessCount}</li>
								<li>Проигранных споров ${defaultAccountStat.disputesFailed}</li>
								<li>Открытых исков ${defaultAccountStat.claimsInProcessCount}</li>
								<li>Проигранных исков ${defaultAccountStat.claimsFailed}</li>
							</ul>
						</g:else>
					</div>
				</div>
			</div>

			<div class="col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title ft">Посещаемость</h3>
					</div>

					<div class="panel-body">
						<g:if test="${freeAccount}">
							Доступно для платной подписки
						</g:if>
						<g:else>

							<h3 class="ft">В текущем месяце</h3>
							<ul>
								<li>Просмотры карточки ${defaultAccountStat.viewStat.monthViewAccount}</li>
								<li>Просмотры товаров ${defaultAccountStat.viewStat.monthViewGoods}</li>
								<li>Просмотры услуг ${defaultAccountStat.viewStat.monthViewServices}</li>
							</ul>

							<h3 class="ft">За все время</h3>
							<ul>
								<li>Просмотры карточки ${defaultAccountStat.viewStat.totalViewAccount}</li>
								<li>Просмотры товаров ${defaultAccountStat.viewStat.totalViewGoods}</li>
								<li>Просмотры услуг ${defaultAccountStat.viewStat.totalViewServices}</li>
							</ul>
						</g:else>
					</div>
				</div>
			</div>
		</div>
	</sec:ifAnyGranted>

</g:if>


<g:if test="${lenta.size()}">

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Деловые события</h3>
		</div>

		<div class="panel-body">

			<div class="lead">Внимание! В ленте появляются только новые события!</div>

			<ul class="list-group">
				<g:each in="${lenta}" status="i" var="obj">
					<g:set var="eventDate" value="${obj.dateCreated}"/>
					<g:set var="event" value="${obj}"/>
					<g:set var="eventType" value="${obj.class.simpleName}"/>
					<li class="list-group-item">




						<sec:ifAnyGranted roles="ROLE_ACCOUNT">
						<g:if test="${eventType == 'Deal'}">
							<div class="time"><g:formatDate date="${eventDate}" format="dd MMMM yyyy"/></div>
							Вам предложена <g:link
								url="[resource: event, action: 'thread']">сделка</g:link> с ${event.account == (accounts ?: event.account) ? event.partner.name : event.account.name}
						</g:if>
						<g:if test="${eventType == 'DealNewPost'}">
							<div class="time"><g:formatDate date="${eventDate}" format="dd MMMM yyyy"/></div>
							Новые ответы в <g:link
								url="[resource: event.item, action: 'thread']">сделке</g:link> с ${event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name}
							<span class="badge">+${event.count}</span>
						</g:if>
						<g:if test="${eventType == 'DealNewStatus'}">
							<div class="time"><g:formatDate date="${eventDate}" format="dd MMMM yyyy"/></div>
							Новые статусы в <g:link
								url="[resource: event.item, action: 'thread']">сделке</g:link> с ${event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name}
							<span class="badge">+${event.count}</span>
						</g:if>
						</sec:ifAnyGranted>

						<sec:ifAnyGranted roles="ROLE_MEDIATOR,ROLE_ADMIN">
						<g:if test="${eventType == 'Dispute'}">
							<div class="time"><g:formatDate date="${eventDate}" format="dd MMMM yyyy"/></div>
							Открыт <g:link url="[resource: event, action: 'thread']">спор</g:link> от ${event.account.name}
						</g:if>
					</sec:ifAnyGranted>

						<sec:ifAnyGranted roles="ROLE_MEDIATOR,ROLE_ADMIN,ROLE_ACCOUNT">
						<g:if test="${eventType == 'DisputeNewPost'}">
							<div class="time"><g:formatDate date="${eventDate}" format="dd MMMM yyyy"/></div>
							Новые ответы в <g:link
								url="[resource: event.item, action: 'thread']">споре</g:link> с ${event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name}
							<span class="badge">+${event.count}</span>
						</g:if>
					</sec:ifAnyGranted>

						<sec:ifAnyGranted roles="ROLE_MEDIATOR,ROLE_ADMIN,ROLE_ACCOUNT">
						<g:if test="${eventType == 'DisputeNewStatus'}">
							<div class="time"><g:formatDate date="${eventDate}" format="dd MMMM yyyy"/></div>
							Новые статусы в <g:link
								url="[resource: event.item, action: 'thread']">споре</g:link> с ${event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name}
							<span class="badge">+${event.count}</span>
						</g:if>
						</sec:ifAnyGranted>

						<sec:ifAnyGranted roles="ROLE_JUDGE,ROLE_ADMIN">
						<g:if test="${eventType == 'Claim'}">
							<div class="time"><g:formatDate date="${eventDate}" format="dd MMMM yyyy"/></div>
							Новый <g:link url="[resource: event, action: 'thread']">иск</g:link> от ${event.account.name}
						</g:if>
					</sec:ifAnyGranted>





						<sec:ifAnyGranted roles="ROLE_MANAGER,ROLE_ADMIN">

							<g:if test="${eventType == 'Item'}">
							<div class="time"><g:formatDate date="${eventDate}" format="dd MMMM yyyy"/></div>
							 <g:link url="[resource: event, action: 'edit']">
								<g:if test="${event.categoryType.code=='GOOD'}">
									Новый товар
								</g:if>
								<g:else>
									Новая услуга
								</g:else>
								</g:link> от ${event.account.name}

							</g:if>
						<g:if test="${eventType == 'Account'}">
							<div class="time"><g:formatDate date="${eventDate}" format="dd MMMM yyyy"/></div>
							Новое <g:link url="[resource: event, action: 'edit']">предприятие</g:link>

						</g:if>
						%{--<g:if test="${eventType == 'NewSuggestion'}">--}%
							%{--Новое <g:link url="[resource: event, action: 'index']">Сообщение</g:link>--}%
						%{--</g:if>--}%
					</sec:ifAnyGranted>






						<sec:ifAnyGranted roles="ROLE_JUDGE,ROLE_ADMIN,ROLE_ACCOUNT">
						<g:if test="${eventType == 'ClaimNewPost'}">
							<div class="time"><g:formatDate date="${eventDate}" format="dd MMMM yyyy"/></div>
							Новые ответы в <g:link
								url="[resource: event.item, action: 'thread']">иске</g:link> с ${event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name}
							<span class="badge">+${event.count}</span>
						</g:if>
					</sec:ifAnyGranted>

					<sec:ifAnyGranted roles="ROLE_JUDGE,ROLE_ADMIN,ROLE_ACCOUNT">
						<g:if test="${eventType == 'ClaimNewStatus'}">
							<div class="time"><g:formatDate date="${eventDate}" format="dd MMMM yyyy"/></div>
							Новые статусы в <g:link
								url="[resource: event.item, action: 'thread']">иске</g:link> с ${event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name}
							<span class="badge">+${event.count}</span>
						</g:if>
					</sec:ifAnyGranted>

					<sec:ifAnyGranted roles="ROLE_MANAGER,ROLE_ADMIN,ROLE_ACCOUNT">
						<g:if test="${eventType == 'Review'}">
							<div class="time"><g:formatDate date="${eventDate}" format="dd MMMM yyyy"/></div>
							Новый <g:message code="${'review.value.' + event.value}"/> <g:link url="[resource: event, action: 'show']">отзыв</g:link> от ${event.from.name}
						</g:if>
					</sec:ifAnyGranted>

					<sec:ifAnyGranted roles="ROLE_JURIST,ROLE_ADMIN">
						<g:if test="${eventType == 'JuristConsult'}">
							<div class="time"><g:formatDate date="${eventDate}" format="dd MMMM yyyy"/></div>
							Новая <g:link url="[resource: event, action: 'thread']">Консультация</g:link> от ${event.account.name}
						</g:if>
					</sec:ifAnyGranted>

						<sec:ifAnyGranted roles="ROLE_JURIST,ROLE_ADMIN,ROLE_ACCOUNT">
						<g:if test="${eventType == 'ConsultNewPost'}">
							<div class="time"><g:formatDate date="${eventDate}" format="dd MMMM yyyy"/></div>
							Новые ответы в <g:link
								url="[resource: event.item, action: 'thread']">консультации</g:link> с ${event.item.jurist == profile ? event.item.account.name : event.item.account.name}
							<span class="badge">+${event.count}</span>
						</g:if>
						</sec:ifAnyGranted>

						<sec:ifAnyGranted roles="ROLE_JURIST,ROLE_ADMIN,ROLE_ACCOUNT">
						<g:if test="${eventType == 'ConsultNewStatus'}">
							<div class="time"><g:formatDate date="${eventDate}" format="dd MMMM yyyy"/></div>
							Новые статусы в <g:link
								url="[resource: event.item, action: 'thread']">консультации</g:link> с ${event.item.jurist == profile ? event.item.account.name :event.item.account.name}
							<span class="badge">+${event.count}</span>
						</g:if>
						</sec:ifAnyGranted>

						<sec:ifAnyGranted roles="ROLE_MANAGER,ROLE_ADMIN,ROLE_ACCOUNT">
						<g:if test="${eventType == 'TicketNewPost'}">
							<div class="time"><g:formatDate date="${eventDate}" format="dd MMMM yyyy"/></div>
							Новые Ответы в обращении в <g:link
								url="[resource: event.item, action: 'thread']">Службу поддержки</g:link>
							<span class="badge">+${event.count}</span>
						</g:if>

						</sec:ifAnyGranted>
						<sec:ifAnyGranted roles="ROLE_MANAGER,ROLE_ADMIN,ROLE_ACCOUNT">
							<g:if test="${eventType == 'TicketNewStatus'}">
								<div class="time"><g:formatDate date="${eventDate}" format="dd MMMM yyyy"/></div>
								<g:link
										url="[resource: event.item, action: 'thread']">Новые Статусы в обращении </g:link> в Службу поддержки
								<span class="badge">+${event.count}</span>
							</g:if>

						</sec:ifAnyGranted>
						<sec:ifAnyGranted roles="ROLE_MANAGER,ROLE_ADMIN">
							<g:if test="${eventType == 'Ticket'}">
								<div class="time"><g:formatDate date="${eventDate}" format="dd MMMM yyyy"/></div>
								<g:link url="[resource: event, action: 'thread']">Новое Обращение</g:link> от ${event.account.name}
							</g:if>

						</sec:ifAnyGranted>
					</li>
				</g:each>
			</ul>

		</div>
	</div>
</g:if>

</body>
</html>