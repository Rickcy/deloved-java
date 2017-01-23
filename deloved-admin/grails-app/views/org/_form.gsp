<%@ page import="ru.deloved.OrgForm" %>

<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'name', 'error')}required form-group">
    <label for="name" class="col-sm-3 control-label">
        <g:message code="currency.name.label" default="Name"/>
        <span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
    </label>

    <div class="col-sm-9">
        <g:textField name="name" class="form-control" value="${objInstance?.name}"/>
    </div>
</div>
<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'code', 'error')}form-group">
    <label for="code" class="col-sm-3 control-label">
        <g:message code="currency.code.label" default="Code"/>
        <span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
    </label>

    <div class="col-sm-9">
        <g:textField name="code" class="form-control" value="${objInstance?.code}"/>
    </div>
</div>


