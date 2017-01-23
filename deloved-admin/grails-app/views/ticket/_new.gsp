<g:if test="${ticketCreateCommandInstance.account}">
	<g:hiddenField name="account" value="${ticketCreateCommandInstance.account}"/>
</g:if>


<div class=info>Детально опишите проблему в поле ниже. Укажите место, время и характер ошибки. Постарайтесь восстановить последовательность действий, которые привели к неисправности.</div>


<g:if test="${myAccounts}">
	<div class="fieldcontain required form-group">
		<label for="account" class="col-sm-3 control-label">
			<g:message code="ticket.account.label"/>
			<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
		</label>

		<div class="col-sm-9">
			<g:select class="form-control" name="account" from="${myAccounts}" optionKey="id" optionValue="name"/>
		</div>
	</div>
</g:if>

<div class="fieldcontain ${hasErrors(bean: ticketInstance, field: 'subject', 'error')} required form-group">
	<label for="subject" class="col-sm-3 control-label">
		<g:message code="ticket.subject.label"/>
		<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
	</label>

	<div class="col-sm-9">

		<g:textArea style="min-height: 200px" class="form-control" name="subject" required="" value="${ticketCreateCommandInstance?.subject}"/>
	</div>

</div>
