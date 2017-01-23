<%@ page import="ru.deloved.Item" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="item.show.label"/></title>
</head>

<body>
<div id="show-item" class="content scaffold-show" role="main">

	<h1><g:message code="item.show.label"/></h1>

	<g:render template="/_common/flash-message"/>

	<ol class="property-list item">

		<g:if test="${itemInstance?.account}">
			<li class="fieldcontain">
				<span id="account-label" class="property-label"><g:message code="item.account.label" default="Account"/></span>

				<span class="property-value" aria-labelledby="account-label"><g:link controller="account" action="show" id="${itemInstance?.account?.id}">${itemInstance?.account?.encodeAsHTML()}</g:link></span>

			</li>
		</g:if>

		<g:if test="${itemInstance?.name}">
			<li class="fieldcontain">
				<span id="name-label" class="property-label"><g:message code="item.name.label" default="Name"/></span>

				<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${itemInstance}" field="name"/></span>

			</li>
		</g:if>

		<g:if test="${itemInstance?.kind}">
			<li class="fieldcontain">
				<span id="kind-label" class="property-label"><g:message code="item.kind.label" default="Kind"/></span>

				<span class="property-value" aria-labelledby="kind-label"><g:fieldValue bean="${itemInstance}" field="kind"/></span>

			</li>
		</g:if>

		<g:if test="${itemInstance?.price}">
			<li class="fieldcontain">
				<span id="price-label" class="property-label"><g:message code="item.price.label" default="Price"/></span>

				<span class="property-value" aria-labelledby="price-label"><g:fieldValue bean="${itemInstance}" field="price"/></span>

			</li>
		</g:if>

		<g:if test="${itemInstance?.measure}">
			<li class="fieldcontain">
				<span id="measure-label" class="property-label"><g:message code="item.measure.label" default="Measure"/></span>

				<span class="property-value" aria-labelledby="measure-label"><g:link controller="measure" action="show" id="${itemInstance?.measure?.id}">${itemInstance?.measure?.encodeAsHTML()}</g:link></span>

			</li>
		</g:if>

		<g:if test="${itemInstance?.measureValue}">
			<li class="fieldcontain">
				<span id="measureValue-label" class="property-label"><g:message code="item.measureValue.label" default="Measure Value"/></span>

				<span class="property-value" aria-labelledby="measureValue-label"><g:fieldValue bean="${itemInstance}" field="measureValue"/></span>

			</li>
		</g:if>

		<g:if test="${itemInstance?.photo}">
			<li class="fieldcontain">
				<span id="photo-label" class="property-label"><g:message code="item.photo.label" default="Photo"/></span>

				<span class="property-value" aria-labelledby="photo-label"><g:link controller="attachment" action="show" id="${itemInstance?.photo?.id}">${itemInstance?.photo?.encodeAsHTML()}</g:link></span>

			</li>
		</g:if>

		<g:if test="${itemInstance?.photoThumb}">
			<li class="fieldcontain">
				<span id="photoThumb-label" class="property-label"><g:message code="item.photoThumb.label" default="Photo Thumb"/></span>

				<span class="property-value" aria-labelledby="photoThumb-label"><g:link controller="attachment" action="show"
																						id="${itemInstance?.photoThumb?.id}">${itemInstance?.photoThumb?.encodeAsHTML()}</g:link></span>

			</li>
		</g:if>

		<g:if test="${itemInstance?.description}">
			<li class="fieldcontain">
				<span id="description-label" class="property-label"><g:message code="item.description.label" default="Description"/></span>

				<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${itemInstance}" field="description"/></span>

			</li>
		</g:if>

		<g:if test="${itemInstance?.availability}">
			<li class="fieldcontain">
				<span id="availability-label" class="property-label"><g:message code="item.availability.label" default="Availability"/></span>

				<span class="property-value" aria-labelledby="availability-label"><g:fieldValue bean="${itemInstance}" field="availability"/></span>

			</li>
		</g:if>

		<g:if test="${itemInstance?.dateCreated}">
			<li class="fieldcontain">
				<span id="dateCreated-label" class="property-label"><g:message code="item.dateCreated.label" default="Date Created"/></span>

				<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${itemInstance?.dateCreated}"/></span>

			</li>
		</g:if>

	</ol>
	<g:form url="[resource: itemInstance, action: 'delete']" method="DELETE">
		<fieldset class="buttons">
			<g:link class="edit" action="edit" resource="${itemInstance}"><g:message code="default.button.edit.label" default="Edit"/></g:link>
			<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}"
							onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
		</fieldset>
	</g:form>
</div>
</body>
</html>
