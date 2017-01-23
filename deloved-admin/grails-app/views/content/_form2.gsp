<%@ page import="ru.deloved.Measure" %>


<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'name', 'error')}required form-group">
    <label for="name" class="col-sm-3 control-label">
        <g:message code="content.name.label" default="Название/h1"/>
        <span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
    </label>

    <div class="col-sm-9">
        <g:textField name="name" class="form-control" value="${objInstance?.name}"/>
    </div>
</div>



<div class="fieldcontain title form-group">
    <label for="title" class="col-sm-3 control-label">
        <g:message code="content.name.label" default="Title"/>

    </label>

    <div class="col-sm-9">
        <g:textField name="title" class="form-control" value="${objInstance?.title}"/>
    </div>
</div>
<div class="fieldcontain description form-group">
    <label for="description" class="col-sm-3 control-label">
        <g:message code="content.name.label" default="Description"/>

    </label>

    <div class="col-sm-9">
        <g:textField name="description" class="form-control" value="${objInstance?.description}"/>
    </div>
</div>

<div class="fieldcontain keywords form-group">
    <label for="keywords" class="col-sm-3 control-label">
        <g:message code="content.name.label" default="Keywords"/>

    </label>

    <div class="col-sm-9">
        <g:textField name="keywords" class="form-control" value="${objInstance?.keywords}"/>
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
        <g:message code="content.content.label" default="MainContent/column1"/>

    </label>

    <div class="col-sm-9"><g:textArea class="form-control" name="content" value="${objInstance?.content}" rows="15" maxlength="500000"/></div>
</div>
<div class="fieldcontain content  form-group ${hasErrors(bean: objInstance, field: 'content2', 'error')} ">
    <label class="control-label col-sm-3" for="content2">
        <g:message code="content.content.label" default="Content/column2"/>

    </label>

    <div class="col-sm-9"><g:textArea class="form-control" name="content2" value="${objInstance?.content2}" rows="15" maxlength="500000"/></div>
</div>






