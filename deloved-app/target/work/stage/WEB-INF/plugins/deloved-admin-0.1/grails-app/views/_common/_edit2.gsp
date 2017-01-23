<g:render template="/_common/flash-message"/>

<g:hasErrors bean="${objInstance}">
    <ul class="alert alert-danger errors" role="alert">
        <g:eachError bean="${objInstance}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
        </g:eachError>
    </ul>
</g:hasErrors>

<g:form name="editForm" url="[resource: beanResource, action: 'update']" method="PUT" class="form-horizontal">
    <g:hiddenField name="version" value="${beanResource?.version}"/>
    <fieldset class="form">
        <g:render template="${formTemplate ?: 'form2'}"/>
    </fieldset>
    <fieldset class="buttons">
        <g:actionSubmit class="btn  btn-success" action="update" value="${message(code: 'default.button.update.label')}"/>
        <g:link class="btn btn-default" mapping="${cancelMapping}" action="index"><g:message code="default.cancel"/></g:link>
    </fieldset>
</g:form>
