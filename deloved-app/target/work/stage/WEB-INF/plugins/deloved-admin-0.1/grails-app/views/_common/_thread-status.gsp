<%@ page import="ru.deloved.DealStatus" %>


<!--div class="deal_status"-->
<div name="post" class="${obj.person == myProfile ? 'bubble bubble-in bubble-status' : 'bubble bubble-out bubble-status'}">
	Изменен статус:    ${message(code: params.controller + '.status.' + obj.status(), default: obj.status())}


<g:if test="${obj instanceof ru.deloved.DealPost}">
	<g:if test="${obj?.dispute}">
		(<g:link url="[resource: obj.dispute, action: 'thread']">
		${message(code: 'deal.open.dispute')}
	</g:link>)
	</g:if>
	<g:if test="${obj?.claim}">
		(<g:link url="[resource: obj.claim, action: 'thread']">
		${message(code: 'deal.open.claim')}
	</g:link>)
	</g:if>
	<g:if test="${obj.status() == DealStatus.Failed && obj.deal.failedBy}">
		(<g:link url="[resource: obj.deal.failedBy, action: 'show']">
		${obj.deal.failedBy.name}
	</g:link>)
	</g:if>
</g:if>


</div>
