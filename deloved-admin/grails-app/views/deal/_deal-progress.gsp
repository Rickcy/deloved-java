<%@ page import="ru.deloved.DealStatus" %>

<style>

</style>
<div id="dealProgress">




		<div class="deloved-progress-bar">

			<input id="dealProgressVal" type="hidden" value="${dealInstance.position()}">

			<g:if test="${[DealStatus.Rejected.value(), DealStatus.Failed.value()].contains(dealInstance.status)}">
				<div class="deloved-progress failure-progress" style="width: ${dealInstance.position()}%"></div>
			</g:if>
			<g:elseif test="${ru.deloved.DealStatus.Suspended.value() == dealInstance.status}">
				<div class="deloved-progress suspended-progress" style="width: ${dealInstance.position()}%"></div>
			</g:elseif>
			<g:else>
				<div class="deloved-progress success-progress" style="width: ${dealInstance.position()}%"></div>
			</g:else>



			<g:render template="check-points" model="[
			        dealInstance: dealInstance
			]"/>
		</div>

</div>
