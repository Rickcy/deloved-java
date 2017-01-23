<%@ page import="ru.deloved.billing.PaymentRequest" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title>Счета на оплату</title>
</head>

<body>

<div id="list-paymentRequest" class="content scaffold-list" role="main">

	<h1>Счета на оплату</h1>

	<g:render template="/_common/flash-message"/>


	<g:form url="[controller: 'paymentRequest', action: 'index']">
		<div class="row">

			<div class="col-md-2"><g:textField placeholder="Номер счета" class="form-control" name="id" value="${filter.id}" autocomplete="off"/></div>

			<div class="col-md-2">
				<g:select class="form-control"
						  name="status"
						  from="${ru.deloved.billing.RequestStatus.values()}"
						  optionKey="${{ it.value }}"
						  valueMessagePrefix="paymentRequest.filter.status"
						  value="${filter.status}"
						  noSelection="${['': message(code: 'paymentRequest.filter.status.all')]}"/>
			</div>

			<div class="col-md-3">
				<g:submitButton name="filterAction" class="btn btn-primary" value="${message(code: 'default.button.filter.label')}"/>
				<g:submitButton name="resetAction" class="btn btn-default" formaction="reset" value="${message(code: 'default.button.filterReset.label')}"/>
			</div>
		</div>
	</g:form>
	<div class="table-responsive">
	<table border="0" class="table table-striped">
		<thead>
		<tr>
			<th>#</th>
			<g:sortableColumn property="dateCreated" title="${message(code: 'paymentRequest.dateCreated.label', default: 'Дата создания')}"/>
			<g:sortableColumn property="keeper" title="${message(code: 'paymentRequest.keeper.label', default: 'Keeper')}"/>
			<g:sortableColumn property="profile" title="${message(code: 'paymentRequest.profile.label', default: 'Профиль')}"/>
			<g:sortableColumn property="status" title="${message(code: 'paymentRequest.status.label', default: 'Статус')}"/>
			<g:sortableColumn property="method" title="${message(code: 'paymentRequest.method.label', default: 'Метод')}"/>
			<g:sortableColumn property="amount" title="${message(code: 'paymentRequest.amount.label', default: 'Сумма')}"/>
		</tr>
		</thead>
		<tbody>
		<g:each in="${paymentRequestInstanceList}" status="i" var="obj">
			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
				<td><g:link url="[resource: obj, action: 'show']">${obj.id}</g:link></td>
				<td><g:formatDate date="${obj.dateCreated}" format="dd.MM.yyyy HH:mm:ss"/></td>
				<td>${fieldValue(bean: obj, field: "keeper.number")}</td>
				<td>${fieldValue(bean: obj, field: "profile.fio")}</td>
				<td>${message(code: 'paymentRequest.status.' + obj.status, default: obj.status)}</td>
				<td>${fieldValue(bean: obj, field: "method.name")}</td>
				<td>${formatNumber(number: obj.value, type: 'currency', currencyCode: obj.keeper.currency.code)}</td>
			</tr>
		</g:each>
		</tbody>
	</table>
		</div>
	<g:if test="${params.max < rowsCount}">
		<g:paginate total="${rowsCount ?: 0}"/>
	</g:if>

</div>

</body>
</html>
