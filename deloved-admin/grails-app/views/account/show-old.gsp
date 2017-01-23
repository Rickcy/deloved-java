<%@ page import="ru.deloved.Account" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="account.show.label"/></title>
</head>

<body>

<div id="show-account" class="content scaffold-show" role="main">

	<h1><g:message code="account.show.label"/></h1>

	<g:render template="/_common/flash-message"/>

	<ol class="property-list account">

		<g:if test="${accountInstance?.address}">
			<li class="fieldcontain">
				<span id="address-label" class="property-label"><g:message code="account.address.label" default="Address"/></span>

				<span class="property-value" aria-labelledby="address-label"><g:fieldValue bean="${accountInstance}" field="address"/></span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.city}">
			<li class="fieldcontain">
				<span id="city-label" class="property-label"><g:message code="account.city.label" default="City"/></span>

				<span class="property-value" aria-labelledby="city-label">${accountInstance?.city?.name}</span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.dateCreated}">
			<li class="fieldcontain">
				<span id="dateCreated-label" class="property-label"><g:message code="account.dateCreated.label" default="Date Created"/></span>

				<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${accountInstance?.dateCreated}"/></span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.description}">
			<li class="fieldcontain">
				<span id="description-label" class="property-label"><g:message code="account.description.label" default="Description"/></span>

				<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${accountInstance}" field="description"/></span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.fax1}">
			<li class="fieldcontain">
				<span id="fax1-label" class="property-label"><g:message code="account.fax1.label" default="Fax1"/></span>

				<span class="property-value" aria-labelledby="fax1-label"><g:fieldValue bean="${accountInstance}" field="fax1"/></span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.fax2}">
			<li class="fieldcontain">
				<span id="fax2-label" class="property-label"><g:message code="account.fax2.label" default="Fax2"/></span>

				<span class="property-value" aria-labelledby="fax2-label"><g:fieldValue bean="${accountInstance}" field="fax2"/></span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.inn}">
			<li class="fieldcontain">
				<span id="inn-label" class="property-label"><g:message code="account.inn.label" default="Inn"/></span>

				<span class="property-value" aria-labelledby="inn-label"><g:fieldValue bean="${accountInstance}" field="inn"/></span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.keywords}">
			<li class="fieldcontain">
				<span id="keywords-label" class="property-label"><g:message code="account.keywords.label" default="Keywords"/></span>

				<span class="property-value" aria-labelledby="keywords-label"><g:fieldValue bean="${accountInstance}" field="keywords"/></span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.kpp}">
			<li class="fieldcontain">
				<span id="kpp-label" class="property-label"><g:message code="account.kpp.label" default="Kpp"/></span>

				<span class="property-value" aria-labelledby="kpp-label"><g:fieldValue bean="${accountInstance}" field="kpp"/></span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.legalAddress}">
			<li class="fieldcontain">
				<span id="legalAddress-label" class="property-label"><g:message code="account.legalAddress.label" default="Legal Address"/></span>

				<span class="property-value" aria-labelledby="legalAddress-label"><g:fieldValue bean="${accountInstance}" field="legalAddress"/></span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.logo}">
			<li class="fieldcontain">
				<span id="logo-label" class="property-label"><g:message code="account.logo.label" default="Logo"/></span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.manager}">
			<li class="fieldcontain">
				<span id="manager-label" class="property-label"><g:message code="account.manager.label" default="Manager"/></span>

				<span class="property-value" aria-labelledby="manager-label"><g:fieldValue bean="${accountInstance}" field="manager"/></span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.name}">
			<li class="fieldcontain">
				<span id="name-label" class="property-label"><g:message code="account.name.label" default="Name"/></span>

				<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${accountInstance}" field="name"/></span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.orgForm}">
			<li class="fieldcontain">
				<span id="orgForm-label" class="property-label"><g:message code="account.orgForm.label" default="Org Form"/></span>

				<span class="property-value" aria-labelledby="orgForm-label"><g:fieldValue bean="${accountInstance}" field="orgForm.name"/></span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.phone1}">
			<li class="fieldcontain">
				<span id="phone1-label" class="property-label"><g:message code="account.phone1.label" default="Phone1"/></span>

				<span class="property-value" aria-labelledby="phone1-label"><g:fieldValue bean="${accountInstance}" field="phone1"/></span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.phone2}">
			<li class="fieldcontain">
				<span id="phone2-label" class="property-label"><g:message code="account.phone2.label" default="Phone2"/></span>

				<span class="property-value" aria-labelledby="phone2-label"><g:fieldValue bean="${accountInstance}" field="phone2"/></span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.phone3}">
			<li class="fieldcontain">
				<span id="phone3-label" class="property-label"><g:message code="account.phone3.label" default="Phone3"/></span>

				<span class="property-value" aria-labelledby="phone3-label"><g:fieldValue bean="${accountInstance}" field="phone3"/></span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.publicStatus}">
			<li class="fieldcontain">
				<span id="publicStatus-label" class="property-label"><g:message code="account.publicStatus.label" default="Public Status"/></span>

				<span class="property-value" aria-labelledby="publicStatus-label"><g:fieldValue bean="${accountInstance}" field="publicStatus"/></span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.regDate}">
			<li class="fieldcontain">
				<span id="regDate-label" class="property-label"><g:message code="account.regDate.label" default="Reg Date"/></span>

				<span class="property-value" aria-labelledby="regDate-label"><g:formatDate date="${accountInstance?.regDate}"/></span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.regNumber}">
			<li class="fieldcontain">
				<span id="regNumber-label" class="property-label"><g:message code="account.regNumber.label" default="Reg Number"/></span>

				<span class="property-value" aria-labelledby="regNumber-label"><g:fieldValue bean="${accountInstance}" field="regNumber"/></span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.webAddress}">
			<li class="fieldcontain">
				<span id="webAddress-label" class="property-label"><g:message code="account.webAddress.label" default="Web Address"/></span>

				<span class="property-value" aria-labelledby="webAddress-label"><g:fieldValue bean="${accountInstance}" field="webAddress"/></span>

			</li>
		</g:if>

		<g:if test="${accountInstance?.workTime}">
			<li class="fieldcontain">
				<span id="workTime-label" class="property-label"><g:message code="account.workTime.label" default="Work Time"/></span>

				<span class="property-value" aria-labelledby="workTime-label"><g:fieldValue bean="${accountInstance}" field="workTime"/></span>

			</li>
		</g:if>

	</ol>
	<g:form url="[resource: accountInstance, action: 'delete']" method="DELETE">
		<fieldset class="buttons">
			<g:link class="btn btn-primary btn-lg" action="edit" resource="${accountInstance}"><g:message code="default.button.edit.label" default="Edit"/></g:link>
			<g:actionSubmit class="btn btn-warning btn-lg" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}"
							onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>

		</fieldset>
	</g:form>
</div>
</body>
</html>
