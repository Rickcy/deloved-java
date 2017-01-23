<g:render template="/_common/flash-message"/>

<%--Пока такой вот костыль, с постепенным переходом на новые "ошибки" удалить --%>

<g:hasErrors bean="${objInstance}">
	<%--
<ul class="alert alert-danger errors" role="alert">
	<g:eachError bean="${objInstance}" var="error">
		<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
	</g:eachError>
</ul>
     --%>
	<ul class="alert alert-danger errors" role="alert">
		<li>Заполните обязательные поля без ошибок</li>
	</ul>
</g:hasErrors>

<%--Конец --%>

<g:form name="editForm" url="[action: 'save', params: formParams, mapping: formMappings]" class="form-horizontal">
	<g:each in="${hiddenExtra}">
		<g:hiddenField name="${it.key}" value="${it.value}"/>
	</g:each>
	<fieldset class="form">
		<g:render template="${formTemplate ?: 'form'}"/>
	</fieldset>
	<fieldset class="buttons">
		<g:submitButton name="create" class="btn btn-success asd" value="${message(code: 'default.button.create.label')}"/>
		<g:link class="btn btn-default" mapping="${cancelMapping}" controller="item" action="index"><g:message code="default.cancel"/></g:link>
	</fieldset>
</g:form>
