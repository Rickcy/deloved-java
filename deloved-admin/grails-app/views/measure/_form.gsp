<%@ page import="ru.deloved.Measure" %>

<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'name', 'error')}required form-group">
	<label for="name" class="col-sm-3 control-label">
		<g:message code="measure.name.label" default="Name"/>
		<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
	</label>

	<div class="col-sm-9">
		<g:textField name="name" class="form-control" value="${objInstance?.name}"/>
	</div>
</div>
<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'fullname', 'error')}form-group">
	<label for="fullname" class="col-sm-3 control-label">
		<g:message code="measure.fullname.label" default="Fullname"/>
	</label>

	<div class="col-sm-9">
		<g:textField name="fullname" class="form-control" value="${objInstance?.fullname}"/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'type', 'error')} required form-group">
	<label for="type" class="col-sm-3 control-label">
		<g:message code="measure.type.label" default="Type"/>

	</label>

	<div class="col-sm-9">
		<g:select id="type" class="form-control"
				  name="type.id"
				  from="${ru.deloved.CategoryType.list()}"
				  optionKey="id"
				  optionValue="${{ message(code: 'categorytype.' + it.code, default: it.code) }}"
				  value="${objInstance?.type?.id}"/>
	</div>
</div>

