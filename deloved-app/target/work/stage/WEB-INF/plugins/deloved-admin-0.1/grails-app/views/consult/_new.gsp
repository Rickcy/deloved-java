<%@ page import="ru.deloved.ConsultCategory" %>
<g:if test="${consultCreateCommandInstance.account}">
	<g:hiddenField name="account" value="${consultCreateCommandInstance.account}"/>
</g:if>


	<div class=info>Укажите в текстовом поле ниже суть вопроса</div>






<g:if test="${myAccounts}">
	<div class="fieldcontain required form-group">
		<label for="account" class="col-sm-3 control-label">
			<g:message code="consult.account.label"/>
			<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
		</label>

		<div class="col-sm-9">
			<g:select class="form-control" name="account" from="${myAccounts}" optionKey="id" optionValue="name"/>
		</div>
	</div>
</g:if>

<div class="fieldcontain ${hasErrors(bean: consultInstance, field: 'subject', 'error')} required form-group">
	<label for="subject" class="col-sm-3 control-label">
		<g:message code="consult.subject.label"/>
		<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
	</label>

	<div class="col-sm-9">

		<g:textArea style="min-height: 200px" class="form-control" name="subject" required="" value="${consultCreateCommandInstance?.subject}"/>
	</div>

</div>
