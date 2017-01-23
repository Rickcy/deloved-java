<%@ page import="ru.deloved.User" %>



<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'username', 'error')} required form-group">

	<label class="col-sm-3 control-label" for="username">
		<g:message code="user.username.label" default="Username"/>
		<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
	</label>

	<div class="col-sm-9">
		<g:textField class="form-control" name="username" required="" value="${objInstance?.username}"/>
	</div>
</div>


<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'enabled', 'error')} form-group">
	<label class="col-sm-3 control-label" for="enabled">
		<g:message code="user.enabled.label" default="Enabled"/>
	</label>

	<div class="col-sm-9">
		<g:checkBox name="enabled" value="${objInstance?.enabled}"/>
	</div>
</div>
