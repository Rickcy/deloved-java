<%@ page import="ru.deloved.Measure" %>

<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'news', 'error')}required form-group">
	<label for="news" class="col-sm-3 control-label">
		<g:message code="content.name.label" default="News"/>
		<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
	</label>

	<div class="col-sm-9">
		<g:textField name="news" class="form-control" value="${objInstance?.news}"/>
	</div>
</div>
		<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'name', 'error')}required form-group">
			<label for="name" class="col-sm-3 control-label">
				<g:message code="content.name.label" default="Название"/>
				<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
			</label>

			<div class="col-sm-9">
				<g:textField name="name" class="form-control" value="${objInstance?.name}"/>
			</div>
		</div>







		<div class="fieldcontain time form-group ">
			<label for="time" class="col-sm-3 control-label">
				<g:message code="content.code.label" default="Time"/>

			</label>

			<div class="col-sm-9">
				<g:textField name="time" class="form-control" value="${objInstance?.time}"/>
			</div>
		</div>

		<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'code', 'error')}required form-group">
			<label for="code" class="col-sm-3 control-label">
				<g:message code="content.code.label" default="Code"/>
				<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
			</label>

			<div class="col-sm-9">
				<g:textField name="code" class="form-control" value="${objInstance?.code}"/>
			</div>
		</div>

		<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'enabled', 'error')} form-group">
			<label class="col-sm-3 control-label" for="enabled">
				<g:message code="content.enabled.label" default="Enabled"/>
			</label>

			<div class="col-sm-9">
				<g:checkBox name="enabled" value="${objInstance?.enabled}"/>
			</div>
		</div>

		<div class="fieldcontain content  form-group ${hasErrors(bean: objInstance, field: 'content', 'error')} ">
			<label class="control-label col-sm-3" for="content">
				<g:message code="content.content.label" default="Content"/>

			</label>

			<div class="col-sm-9"><g:textArea class="form-control" name="content" value="${objInstance?.content}" rows="15" maxlength="500000"/></div>
		</div>






