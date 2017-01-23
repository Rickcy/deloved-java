<g:hasErrors bean="${objInstance}" field="${field}">
	<g:each in="${objInstance.errors.getFieldErrors(field)}" var="error">
		<span style="display: block; color: red"><g:message error="${error}"/></span>
	</g:each>
</g:hasErrors>
