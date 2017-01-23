<%@ page import="ru.deloved.billing.RequestStatus; ru.deloved.billing.PaymentRequest" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title>Подтверждение</title>
</head>

<body>

<div id="show-paymentRequest" class="content scaffold-show" role="main">

	<h1>Подтверждение</h1>

	<g:render template="/_common/flash-message"/>

	<ol class="property-list paymentRequest">

		<li class="fieldcontain">
			<span id="id-label" class="property-label"><g:message code="paymentRequest.id.label" default="ID"/></span>
			<span class="property-value" aria-labelledby="id-label"><g:fieldValue bean="${paymentRequestInstance}" field="id"/></span>
		</li>
		<li class="fieldcontain">
			<span id="sum-label" class="property-label"><g:message code="paymentRequest.sum.label" default="Сумма"/></span>
			<span class="property-value" aria-labelledby="sum-label">${formatNumber(number: paymentRequestInstance.value, type: 'currency', currencyCode: paymentRequestInstance.keeper.currency.code)}</span>
		</li>

	</ol>
	<g:form url="[resource: paymentRequestInstance, action: 'show']" method="POST">
		<fieldset class="buttons">
			<g:link class="btn btn-default" action="index"><g:message code="default.cancel"/></g:link>
			<g:if test="${paymentRequestInstance.status == ru.deloved.billing.RequestStatus.New.value() && paymentRequestInstance.method.code == 'INCOME_MANUAL'}">

				<g:actionSubmit class="btn btn-warning" action="approve" value="${message(code: 'paymentRequest.button.approve.label', default: 'Подтвердить')}"
								onclick="return confirm('${message(code: 'paymentRequest.button.approve.confirm.message', default: 'Вы точно хотите подтвердить?')}');"/>
				<g:actionSubmit class="btn btn-warning" action="decline" value="${message(code: 'paymentRequest.button.approve.label', default: 'Отказ')}"
								onclick="return confirm('${message(code: 'paymentRequest.button.decline.confirm.message', default: 'Вы точно хотите отказать')}');"/>
			</g:if>

		</fieldset>
	</g:form>
</div>
</body>
</html>
