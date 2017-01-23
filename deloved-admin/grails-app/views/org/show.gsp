<%@ page import="ru.deloved.OrgForm" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin">
    <g:set var="entityName" value="${message(code: 'org.label', default: 'Org form')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<div id="show-currency" class="content scaffold-show" role="main">

    <h1><g:message code="default.show.label" args="[entityName]"/></h1>

    <g:render template="/_common/flash-message"/>

    <ol class="property-list currency">

        <g:if test="${orgInstance?.name}">
            <li class="fieldcontain">
                <span id="name-label" class="property-label"><g:message code="currency.name.label" default="Name"/></span>

                <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${orgInstance}" field="name"/></span>

            </li>
        </g:if>
        <g:if test="${orgInstance?.code}">
            <li class="fieldcontain">
                <span id="code-label" class="property-label"><g:message code="currency.code.label" default="Code"/></span>

                <span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${orgInstance}" field="code"/></span>

            </li>
        </g:if>



    </ol>
    <g:form url="[resource: orgInstance, action: 'delete']" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${orgInstance}"><g:message code="default.button.edit.label" default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
