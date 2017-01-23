<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'name', 'error')} required form-group">
	<label for="name" class="col-sm-3 control-label">
		<g:message code="category.name.label"/>
		<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
	</label>

	<div class="col-sm-9">
		<g:textField class="form-control"
					 name="name"
					 required=""
					 value="${fieldValue(bean: objInstance, field: 'name')}"/>
	</div>
</div>

