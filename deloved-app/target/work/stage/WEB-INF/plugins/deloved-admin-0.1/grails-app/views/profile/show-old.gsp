<%@ page import="ru.deloved.Profile" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="profile.show.label"/></title>
</head>

<body>
<div id="show-profile" class="content scaffold-show" role="main">

	<h1><g:message code="profile.show.label"/></h1>

	<g:render template="/_common/flash-message"/>





	<ul class="property-list profile">
		<li class="fieldcontain">
			<span id="fio-label" class="property-label"><g:message code="profile.fio.label"/></span>
			<span class="property-value" aria-labelledby="fio-label"><g:fieldValue bean="${profileInstance}" field="fio"/></span>
		</li>
		<li class="fieldcontain">
			<span id="email-label" class="property-label"><g:message code="profile.email.label"/></span>
			<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${profileInstance}" field="email"/></span>
			<g:if test="${confirmEmailList?.size()>0}">
				<ul>
					<g:each in="${confirmEmailList}" var="confirm">
						<li>
							${confirm.contact} - <g:message code="contactConfirm.${confirm.status()}.status"/>
						</li>
					</g:each>
				</ul>
			</g:if>
		</li>
		<li class="fieldcontain">
			<span id="cellPhone-label" class="property-label"><g:message code="profile.cellPhone.label"/></span>
			<span class="property-value" aria-labelledby="cellPhone-label"><g:fieldValue bean="${profileInstance}" field="cellPhone"/></span>
			<g:if test="${confirmCellphoneList?.size()>0}">
				<ul>
					<g:each in="${confirmCellphoneList}" var="confirm">
						<li>
							${confirm.contact} - <g:message code="contactConfirm.${confirm.status()}.status"/>
						</li>
					</g:each>
				</ul>
			</g:if>
		</li>
	</ul>

	<div id="avatarPanel">
		<g:if test="${profileInstance?.avatar}">
			<a id="mainlink" href="${createLink([controller: 'profile', action: 'avatar', id: profileInstance.id, params: [name: profileInstance.avatar.file, type: 'main']])}" data-gallery>
				<img id="avatar" title="Аватар" width="100" height="100" src="${createLink([controller: 'profile', action: 'avatar', id: profileInstance.id, params: [name: profileInstance.avatarThumb.file]])}">
			</a>
		</g:if>
	</div>

	<g:if test="${profileInstance.user.role.authority = 'ROLE_ACCOUNT'}">
		<li class="fieldcontain">
			<span id="chargeStatus-label" class="property-label"><g:message code="profile.chargeStatus.label" default="Charge Status"/></span>
			<span class="property-value" aria-labelledby="chargeStatus-label"><g:fieldValue bean="${profileInstance}" field="chargeStatus"/></span>
		</li>
		<li class="fieldcontain">
			<span id="chargeTill-label" class="property-label"><g:message code="profile.chargeTill.label" default="Charge till"/></span>
			<span class="property-value" aria-labelledby="chargeTill-label"><g:fieldValue bean="${profileInstance}" field="chargeTill"/></span>
		</li>
	</g:if>



	<fieldset class="buttons">
		<g:link class="btn btn-primary btn-lg" controller="profile" action="edit"><g:message code="default.button.edit.label"/></g:link>
		<g:link class="btn btn-primary btn-lg" controller="profile" action="passwd"><g:message code="profile.button.passwd.label"/></g:link>
	</fieldset>

</div>

<g:render template="/_common/gallery-single"/>

</body>
</html>
