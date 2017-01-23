<%@ page import="ru.deloved.Category" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="category.list.label"/></title>

</head>

<body>

<div id="list-category" class="content scaffold-list" role="main">

	<h1><g:message code="category.list.label"/></h1>

	<g:render template="/_common/flash-message"/>

	<sec:ifAllGranted roles="ROLE_ADMIN">
		<g:set var="ADMIN_USER" value="${true}"/>
	</sec:ifAllGranted>
	<g:if test="${parent || ADMIN_USER}">
		<div class="buttons">
			<div class="row">
				<div class="col-md-8 cat">
					<g:if test="${parent}">
						<g:form name="addForm" url="[controller: 'category', action: 'save']">
							<g:hiddenField name="parentId" value="${parent.id}"/>
							<g:textField name="name" required=""/>
							<g:submitButton name="${message(code: 'category.button.new.label')}" class="btn btn-success">Add</g:submitButton>
						</g:form>
					</g:if>
				</div>
				<sec:ifAllGranted roles="ROLE_ADMIN">
					<div class="col-md-4">
						<button class="btn btn-danger"
								onclick="if (confirm('${message(code: 'default.button.delete.confirm.message')}')) $('#deleteForm').submit();">
							${message(code: 'default.button.deleteSelected.label')}
						</button>
					</div>
				</sec:ifAllGranted>
			</div>
		</div>
	</g:if>


	<ol class="breadcrumb">
		<li><g:link id="0">${message(code: 'category.breadcrumb.root', default: 'Root')}</g:link></li>
		<g:each in="${breadcrumbList}" var="obj">
			<li><g:link id="${obj.id}">${obj.name}</g:link></li>
		</g:each>
	</ol>


	<g:form id="deleteForm" url="[controller: 'category', action: 'delete']" method="DELETE">
		<table border="0" class="table table-striped">
			<thead>
			<tr>
				<th>#</th>
				<g:sortableColumn property="name" title="${message(code: 'category.name.label', default: 'Name')}"/>
				<th>Вложенность</th>
			</tr>
			</thead>
			<tbody>
			<g:each in="${categoryInstanceList}" status="i" var="obj">
				<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td><input type="checkbox" name="id" value="${obj.id}"/></td>

					<td><g:link action="edit"
								id="${obj.id}"
								elementId="gridRow${obj.id}name"
								data-toggle="modal"
								data-remote="${createLink(id: obj.id, action: 'edit')}"
								data-target="#myModal">${fieldValue(bean: obj, field: "name")}</g:link>
					</td>
					<td><g:link id="${obj.id}">внутрь</g:link></td>
				</tr>
			</g:each>
			</tbody>
		</table>
	</g:form>
	<g:if test="${params.max < rowsCount}">
		<g:paginate total="${rowsCount ?: 0}"/>
	</g:if>

</div>

<g:render template="/_common/edit-container"/>

</body>
</html>
