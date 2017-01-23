<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h4 class="modal-title" id="myModalLabel"><g:message code="${params.controller}.edit.label"/></h4>
</div>

<div class="modal-body">

	<g:render template="/_common/flash-message"/>

	<g:hasErrors bean="${objInstance}">
		<ul class="alert alert-danger errors" role="alert">
			<g:eachError bean="${objInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
			</g:eachError>
		</ul>
	</g:hasErrors>
	<g:formRemote name="editForm" update="myModalContent" url="[resource: beanResource, action: 'update']" class="form-horizontal" style="overflow: hidden"
				  onFailure="alert(textStatus)">
		<g:hiddenField name="_method" value="PUT"/>
		<g:hiddenField name="version" value="${beanResource?.version}"/>
		<fieldset class="form">
			<g:render template="form"/>
		</fieldset>
	</g:formRemote>



</div>

<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">${message(code: 'default.button.close.label')}</button>
	<button type="button" class="btn btn-primary" onclick="$('#editForm').submit()">${message(code: 'default.button.update.label')}</button>
</div>
