<%@ page import="ru.deloved.DealStatus" %>

<g:if test="${![DealStatus.Suspended, DealStatus.Failed, DealStatus.Confirmed, DealStatus.Rejected].contains(dealInstance.status())}">
	<div class="deloved-progress-check" style="left: 28%" data-toggle="tooltip" data-placement="top" title="Договор подписан"></div>

</g:if>



<g:if test="${DealStatus.nonPaidStatuses().contains(dealInstance.status())}">

	<g:each in="${DealStatus.nonPaidStatuses()}" var="status">
		<div class="deloved-progress-check"
			 style="left: ${status.position()}%;background: -moz-linear-gradient(right, #ffa200, #e9ec10);
			 background: -ms-linear-gradient(right,#ffa200, #e9ec10);
			 background: -o-linear-gradient(right,#ffa200,#e9ec10);
			 background: -webkit-linear-gradient(right,#ffa200, #e9ec10);"
			 data-toggle="tooltip"
			 data-placement="top"
			 title="${message(code: 'deal.status.' + status, default: status)}"
		></div>
	</g:each>
	<div class="deloved-progress-check" style="left: 46%" data-toggle="tooltip" data-placement="top" title="Исполнения обязательств"></div>
	<div class="deloved-progress-check" style="left: 64%" data-toggle="tooltip" data-placement="top" title="Обязательства исполнены"></div>
	<div class="deloved-progress-check" style="left: 82%" data-toggle="tooltip" data-placement="top" title="Оплата внесена"></div>
</g:if>

<g:if test="${DealStatus.fullPaidStatuses().contains(dealInstance.status())}">

	<g:each in="${DealStatus.fullPaidStatuses()}" var="status">
		<div class="deloved-progress-check"
			 style="left: ${status.position()}%;background: -moz-linear-gradient(right, #ffa200, #e9ec10);
			 background: -ms-linear-gradient(right,#ffa200, #e9ec10);
			 background: -o-linear-gradient(right,#ffa200,#e9ec10);
			 background: -webkit-linear-gradient(right, #ffa200, #e9ec10);"
			 data-toggle="tooltip"
			 data-placement="top"
			 title="${message(code: 'deal.status.' + status, default: status)}"
		></div>
	</g:each>
	<div class="deloved-progress-check" style="left: 46%" data-toggle="tooltip" data-placement="top" title="Предоплата внесена"></div>
	<div class="deloved-progress-check" style="left: 64%" data-toggle="tooltip" data-placement="top" title="Полная предоплата подтвержденна"></div>
	<div class="deloved-progress-check" style="left: 82%" data-toggle="tooltip" data-placement="top" title="Исполнения обязательств"></div>
	<div class="deloved-progress-check" style="left: 95%" data-toggle="tooltip" data-placement="top" title="Обязательства исполнены"></div>
</g:if>

<g:if test="${DealStatus.halfPaidStatuses().contains(dealInstance.status())}">

	<g:each in="${DealStatus.halfPaidStatuses()}" var="status">
		<div class="deloved-progress-check"
			 style="left: ${status.position()}%; background: -moz-linear-gradient(right, #ffa200, #e9ec10);
			 background: -ms-linear-gradient(right,#ffa200, #e9ec10);
			 background: -o-linear-gradient(right,#ffa200,#e9ec10);
			 background: -webkit-linear-gradient(right, #ffa200, #e9ec10);"
			 data-toggle="tooltip"
			 data-placement="top"
			 title="${message(code: 'deal.status.' + status, default: status)}"
		></div>
	</g:each>
	<div class="deloved-progress-check" style="left: 40%" data-toggle="tooltip" data-placement="top" title="Частичная предоплата"></div>
	<div class="deloved-progress-check" style="left: 52%" data-toggle="tooltip" data-placement="top" title="Частичная предоплата подтвержденна"></div>
	<div class="deloved-progress-check" style="left: 64%" data-toggle="tooltip" data-placement="top" title="Исполнения обязательств"></div>
	<div class="deloved-progress-check" style="left: 76%" data-toggle="tooltip" data-placement="top" title="Обязательства исполнены"></div>
	<div class="deloved-progress-check" style="left: 88%" data-toggle="tooltip" data-placement="top" title="Полная предоплата внесена"></div>
</g:if>