<%@ page import="ru.deloved.Profile" contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name='layout' content='admin'/>
	<title>Кабинет</title>
</head>

<body>

<g:if test="${lenta.size()}">

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Деловые события</h3>
		</div>

		<div class="panel-body">


				<g:each in="${lenta}" status="i" var="obj">
					<g:set var="eventDate" value="${obj.dateCreated}"/>
					<g:set var="event" value="${obj}"/>
					<g:set var="eventType" value="${obj.class.simpleName}"/>
					<ul class="list-group">
                            <li class="list-group-item">






						<sec:ifAnyGranted roles="ROLE_MEDIATOR,ROLE_ADMIN">
							<g:if test="${eventType == 'Dispute'}">
								<g:formatDate date="${eventDate}" format="dd MMMM yyyy"/>
								Новый <g:link url="[resource: event, action:'thread']">спор</g:link> от ${event.account.name}
							</g:if>
						</sec:ifAnyGranted>

						<sec:ifAnyGranted roles="ROLE_JUDGE,ROLE_ADMIN">
							<g:if test="${eventType == 'Claim'}">
								<g:formatDate date="${eventDate}" format="dd MMMM yyyy"/>
								Новый <g:link url="[resource: event, action:'thread']">иск</g:link> от ${event.account.name}
							</g:if>
						</sec:ifAnyGranted>

						<sec:ifAnyGranted roles="ROLE_MANAGER,ROLE_ADMIN">
							<g:if test="${eventType == 'Review'}">
								<g:formatDate date="${eventDate}" format="dd MMMM yyyy"/>
								Новый<g:message code="${'review.value.' + event.value}"/> <g:link url="[resource: event, action:'show']">отзыв</g:link> от ${event.from.name}
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


					</li>
					</ul>
				</g:each>


		</div>
	</div>
</g:if>

</body>
</html>