
<table class="table table-hover">
	<thead style="border-bottom: 3px solid rgba(176, 208, 83, 0.24)">
	<tr>
	<th class="ft">Номер счета</th>              <!-- Номер счета -->

	<th class="ft"><g:remoteLink controller="billing" action="index" update="historyGrid" params="${params + [sort: 'method', order: (params.order =='desc') ? 'asc' : 'desc']}">
	Способ оплаты</g:remoteLink></th>
	<!--th>Способ оплаты</th-->            <!-- Способ оплаты -->

	<th class="ft"><g:remoteLink controller="billing" action="index" update="historyGrid" params="${params + [sort: 'dateCreated', order: (params.order =='desc') ? 'asc' : 'desc']}">
	Дата оплаты</g:remoteLink></th>
	<!--<th>Дата создания</th-->            <!-- Дата создания -->

	<th class="ft"><g:remoteLink controller="billing" action="index" update="historyGrid" params="${params + [sort: 'value', order: (params.order =='desc') ? 'asc' : 'desc']}">
	Сумма</g:remoteLink></th>
	<!--th>Сумма</th-->                    <!-- Сумма -->

	<th class="ft"><g:remoteLink controller="billing" action="index" update="historyGrid" params="${params + [sort: 'keeper', order: (params.order =='desc') ? 'asc' : 'desc']}">
	Валюта</g:remoteLink></th>
	<!--th>Валюта</th-->

	<th class="ft"><g:remoteLink controller="billing" action="index" update="historyGrid" params="${params + [sort: 'status', order: (params.order =='desc') ? 'asc' : 'desc']}">
	Статус</g:remoteLink></th>
	<!--th>Статус</th-->                   <!-- Статус -->
	<th class="ft">Детализация</th>

	</tr>
	</thead>
	<tbody>
	<g:each in="${requestInstanceList}" var="request">
		<tr>
			<td>${request.id}</td>
			<td>${request.method.name}</td>
			<td><g:formatDate date="${request.dateCreated}" format="dd.MM.yyyy hh.mm"/></td>
			<td>${request.value}</td>
			<td>${request.keeper.currency.code}</td>
			<td>${message(code: 'paymentRequest.status.' + request.status, default: request.status)}
			<td>
				<g:if test="${request.method.code != 'INCOME_MANUAL'}">
					<a href="javascript:void(0)" onclick="checkRequestStatus('${request.id}')">Подробнее</a>
				</g:if>
				<g:else>
					<a target="_blank" href="${createLink(controller: 'billing',  action: 'bill', id: request.id)}">Подробнее</a>
				</g:else>
			</td>
		</tr>
	</g:each>
	</tbody>
</table>

<g:set var="offsetValue" value="${params.offset ? (params.offset).toInteger() : 0}"/>

	<ul class="pagination">
			<li class="prev ${offsetValue == 0 ? 'disabled' : ''}">
				<g:remoteLink controller="billing" action="index" update="historyGrid"
				params="${params + [offset: offsetValue != 0 ? offsetValue - 10 : offsetValue]}">
				&laquo;</g:remoteLink>
			</li>

			<g:each var="i" in="${0..<((requestInstanceTotal % 10 > 0) ? requestInstanceTotal/10 + 1 : requestInstanceTotal/10)}">
				<li class="${offsetValue == i*10 ? 'active' : ''}">
					<g:remoteLink controller="billing" action="index" update="historyGrid" params="${params + [offset: (i*10)]}">${i+1}</g:remoteLink>
				</li>
			</g:each>

			<li class="prev ${offsetValue + 10 >= requestInstanceTotal ? 'disabled' : ''}">
				<g:remoteLink controller="billing" action="index" update="historyGrid"
				params="${params + [offset: offsetValue + 10 <= requestInstanceTotal ? offsetValue + 10 : offsetValue]}">
				&raquo;</g:remoteLink>
			</li>
	</ul>

