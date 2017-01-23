<%@ page import="ru.deloved.billing.TariffPrice" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title>Тарифы</title>
</head>ы

<body>
<div id="list-tariff" class="content scaffold-list" role="main">

	<h1><g:message code="tariff.list.label"/></h1>

	<g:render template="/_common/flash-message"/>

	<div class="buttons">
		<g:link class="btn btn-success" action="create"><g:message code="tariff.button.new.label"/></g:link>
		<script type="application/javascript">

			function batchDelete() {
				if (confirm('${message(code: 'default.button.delete.confirm.message')}')) {
					$('#batch_action').val('batchDelete');
					$('#batch_form').submit();
				}
				return false;
			}
		</script>

		<div class="btn-group">
			<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><g:message code="default.button.actionSelect.label"/> <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a href="#" onclick="return batchDelete();"><g:message code="tariff.button.delete.label"/></a></li>
			</ul>
		</div>
	</div>

	<g:form url="[controller: 'tariffPrice', action: 'index']">
		<div class="row">
			<div class="col-md-3">
				<g:select class="form-control"
						  name="currencyId"
						  from="${ru.deloved.SystemCurrency.list()}"
						  optionKey="id"
						  optionValue="code"
						  value="${filter.currencyId}"
						  noSelection="${['': message(code: 'tariff.allcurrencies.label', default: '')]}"/>
			</div>

			<div class="col-md-3">
				<g:submitButton name="filterAction" class="btn btn-primary" value="${message(code: 'default.button.filter.label')}"/>
				<g:submitButton name="resetAction" class="btn btn-default" formaction="reset" value="${message(code: 'default.button.filterReset.label')}"/>
			</div>

		</div>

	</g:form>

	<g:form id="batch_form" url="[controller: 'tariffPrice', action: 'batch']" method="POST">
		<g:hiddenField name="batch_action" value=""/>
		<div class="table-responsive">
                <table border="0" class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>#</th>
				<g:sortableColumn property="name" title="${message(code: 'tariff.name.label', default: 'Name')}"/>
				<g:sortableColumn property="price" title="${message(code: 'tariff.price.label', default: 'Price')}"/>
			</tr>
			</thead>
			<tbody>
			<g:each in="${tariffPriceInstanceList}" status="i" var="obj">
				<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td>
						<input type="checkbox" name="id" value="${obj.id}"/>
					</td>

					<td><g:link action="edit"
								id="${obj.id}"
								elementId="gridRow${obj.id}name"
								data-toggle="modal"
								data-remote="${createLink(id: obj.id, action: 'edit')}"
								data-target="#myModal">${fieldValue(bean: obj, field: "name")}</g:link></td>

					<td id="gridRow${obj.id}price"><g:formatNumber number="${obj.price}" type="currency" currencyCode="${obj.currency.code}" /></td>

				</tr>
			</g:each>
			</tbody>
		</table>
</div>
		<g:if test="${params.max < rowsCount}">
			<g:paginate total="${rowsCount ?: 0}"/>
		</g:if>
	</g:form>

</div>

<g:render template="/_common/edit-container"/>

</body>
</html>
