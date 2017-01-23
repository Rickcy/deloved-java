<%@ page import="ru.deloved.Adcontent" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="adcontent.show.label"/></title>
</head>

<body>
<div id="show-adcontent" class="content scaffold-show" role="main">

	<h1><g:message code="adcontent.show.label"/></h1>

	<g:render template="/_common/flash-message"/>

	<ol class="property-list adcontent">

		<g:if test="${adcontentInstance?.name}">
			<li class="fieldcontain">
				<span id="name-label" class="property-label"><g:message code="adcontent.name.label"
																		default="Name"/></span>

				<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${adcontentInstance}"
																						field="name"/></span>

			</li>
		</g:if>

		<g:if test="${adcontentInstance?.approved}">
			<li class="fieldcontain">
				<span id="enabled-label" class="property-label"><g:message code="adcontent.approved.label"
																		   default="Enabled"/></span>

				<span class="property-value" aria-labelledby="enabled-label"><g:formatBoolean
						boolean="${adcontentInstance?.approved}"/></span>

			</li>
		</g:if>

		<g:if test="${adcontentInstance?.dateCreated}">
			<li class="fieldcontain">
				<span id="dateCreated-label" class="property-label"><g:message code="adcontent.dateCreated.label"
																			   default="Date Created"/></span>

				<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate
						date="${adcontentInstance?.dateCreated}"/></span>

			</li>
		</g:if>

		<g:if test="${adcontentInstance?.description}">
			<li class="fieldcontain">
				<span id="description-label" class="property-label"><g:message code="adcontent.description.label"
																			   default="Description"/></span>

				<span class="property-value" aria-labelledby="description-label"><g:fieldValue
						bean="${adcontentInstance}"
						field="description"/></span>

			</li>
		</g:if>

	</ol>
	<g:form url="[resource: adcontentInstance, action: 'delete']" method="DELETE">
		<fieldset class="buttons">
			<g:link class="btn btn-primary btn-lg" action="edit" resource="${adcontentInstance}"><g:message
					code="default.button.edit.label" default="Edit"/></g:link>
			<g:actionSubmit class="btn btn-warning btn-lg" action="delete"
							value="${message(code: 'default.button.delete.label', default: 'Delete')}"
							onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>

		</fieldset>
	</g:form>
</div>
</body>
</html>
