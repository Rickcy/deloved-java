<%@ page import="org.joda.time.LocalDate; ru.deloved.Profile" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="profile.list.label"/></title>
</head>

<body>
<div id="list-profile" class="content scaffold-list" role="main">

	<h1><g:message code="profile.list.label"/></h1>

	<g:render template="/_common/flash-message"/>



	<g:form url="[controller: 'profile', action: 'index']">
		<div class="row">
			<div class="col-md-2">
				<g:select class="form-control"
						  name="role"
						  from="${ru.deloved.Role.list()}"
						  optionKey="id"
						  optionValue="${{ message(code: 'role.' + it.authority, default: it.authority) }}"
						  value="${filter.role}"
						  noSelection="${['': message(code: 'role.allroles.label')]}"/>
			</div>

			<div class="col-md-1">
				<g:select class="form-control"
						  name="enabled"
						  from="${[true, false]}"
						  valueMessagePrefix="user.activity"
						  value="${filter.enabled}"
						  noSelection="${['': message(code: 'user.activity.all')]}"/>
			</div>
			<div class="col-md-2">
				<g:select class="form-control"
						  name="charge"
						  from="${[0, 1, 2]}"
						  valueMessagePrefix="profile.chargeFilter"
						  value="${filter.charge}"
						  noSelection="${['': message(code: 'profile.chargeFilter.all')]}"/>
			</div>

			<div class="col-md-2"><g:textField placeholder="Login" class="form-control" name="username" value="${filter.username}"/></div>

			<div class="col-md-2"><input type="hidden" class="form-control" id="region" name="region" value="${regionFilterData.selectedRegion?.id}"/></div>

			<script type="text/javascript">
				$('#region').select2({
					placeholder: "Выберите регион или город",
					allowClear: true,
					data: ${raw(regionFilterData.regionsTreeJson)}
				});
			</script>


			<div class="col-md-3">
				<g:submitButton name="filterAction" class="btn btn-primary" value="${message(code: 'default.button.filter.label')}"/>
				<g:submitButton name="resetAction" class="btn btn-default" formaction="reset" value="${message(code: 'default.button.filterReset.label')}"/>
			</div>

		</div>

	</g:form>

	<div class="table-responsive">
	<table border="0" class="table table-striped">
		<thead>
		<tr>
			<th><g:message code="profile.username.label"/></th>


			<g:sortableColumn property="fio" title="${message(code: 'profile.fio.label')}"/>

			<g:sortableColumn property="city" title="${message(code: 'profile.city.label')}"/>

			<g:if test="${!filter.role}">
				<g:sortableColumn property="user.role" title="${message(code: 'user.role.label')}"/>
			</g:if>



		</tr>
		</thead>
		<tbody>
		<g:each in="${profileInstanceList}" status="i" var="profileInstance">
			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

				<td><g:link action="edit"
							id="${profileInstance.id}"
							data-toggle="modal"
							data-remote="${createLink(id: profileInstance.id, action: 'edit')}"
							data-target="#myModal">${fieldValue(bean: profileInstance, field: "user.username")}</g:link>
				</td>


				<td id="gridRow${profileInstance.id}fio">${fieldValue(bean: profileInstance, field: "fio")}</td>

				<td id="gridRow${profileInstance.id}city">${profileInstance.city?.name}</td>
				<g:if test="${!filter.role}">
					<td>${message(code: 'role.' + profileInstance.user.role.authority)}</td>
				</g:if>



			</tr>
		</g:each>
		</tbody>
	</table>
		</div>
	<g:if test="${params.max < rowsCount}">
		<g:paginate total="${rowsCount ?: 0}"/>
	</g:if>

</div>

<g:render template="/_common/edit-container"/>
<g:render template="/_common/gallery-single"/>
<g:render template="/_common/crop"/>

</body>
</html>
