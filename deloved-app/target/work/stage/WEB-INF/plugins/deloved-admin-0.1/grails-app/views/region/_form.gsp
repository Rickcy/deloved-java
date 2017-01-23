<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'name', 'error')} required form-group">
	<label for="name" class="col-sm-3 control-label">
		<g:message code="region.name.label"/>
		<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
	</label>

	<div class="col-sm-9">
		<g:textField class="form-control"
					 name="name"
					 required=""
					 value="${fieldValue(bean: objInstance, field: 'name')}"/>
	</div>
</div>

<g:if test="${countryDefaults}">

	<div class="fieldcontain required form-group">
		<label for="currency" class="col-sm-3 control-label">
			<g:message code="region.currency.label" default="Валюта"/>
			<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
		</label>

		<div class="col-sm-9">
			<g:select id="currency" class="form-control" name="currency.id" from="${ru.deloved.SystemCurrency.findAll()}" optionKey="id" optionValue="code"
					  required=""
					  value="${countryDefaults?.currency?.id}"/>
		</div>
	</div>

</g:if>

