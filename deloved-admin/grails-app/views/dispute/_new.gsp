<g:if test="${disputeCreateCommandInstance.account}">
	<g:hiddenField name="account" value="${disputeCreateCommandInstance.account}"/>
</g:if>

<g:hiddenField name="partner" value="${disputeCreateCommandInstance.partner}"/>
<g:hiddenField name="deal" value="${disputeCreateCommandInstance.deal}"/>



<div class=info>Укажите в текстовом поле ниже суть спора.</div>


<g:if test="${myAccounts}">
	<div class="fieldcontain required form-group">
		<label for="account" class="col-sm-3 control-label">
			<g:message code="dispute.account.label"/>
			<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
		</label>

		<div class="col-sm-9">
			<g:select class="form-control" name="account" from="${myAccounts}" optionKey="id" optionValue="name"/>
		</div>
	</div>
</g:if>

<div class="fieldcontain ${hasErrors(bean: disputeInstance, field: 'subject')} required form-group">
	<label for="subject" class="col-sm-3 control-label">
		<g:message code="dispute.subject.label"/>
		<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
	</label>

	<div class="col-sm-9">

		<g:textArea style="min-height: 200px" class="form-control" name="subject" required="" value="${disputeCreateCommandInstance?.subject}"/>
	</div>

</div>
