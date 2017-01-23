<%@ page import="ru.deloved.DocumentCategory; ru.deloved.Document" %>

<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'name', 'error')}required form-group">
	<label for="name" class="col-sm-3 control-label">
		<g:message code="document.name.label" default="Name"/>
		<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
	</label>

	<div class="col-sm-9">
		<g:textField name="name" class="form-control" value="${objInstance?.name}"/>
	</div>
</div>

<div class="fieldcontain  form-group ${hasErrors(bean: objInstance, field: 'description', 'error')} ">
	<label class="control-label col-sm-3" for="description">
		<g:message code="document.description.label" default="Description"/>

	</label>

	<div class="col-sm-9"><g:textArea class="form-control" name="description" value="${objInstance?.description}" rows="10" maxlength="1500"/></div>
</div>


<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'category', 'error')} required form-group">
	<label for="category" class="col-sm-3 control-label">
		<g:message code="document.type.label" default="Type"/>

	</label>

	<div class="col-sm-9">
		<g:select id="category" class="form-control"
				  name="category.id"
				  from="${DocumentCategory.list()}"
				  optionKey="id"
				  optionValue="name"
				  value="${objInstance?.category?.id}"
				  noSelection="${['': '-']}"/>
	</div>
</div>

