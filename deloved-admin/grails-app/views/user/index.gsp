<%@ page import="ru.deloved.User" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="user.list.label"/></title>
	<style type="text/css">
	span.status {
		cursor: pointer;
	}

	span.enabled {
		color: lightgreen;
	}

	span.disabled {
		color: orangered;
	}
	</style>

</head>

<body>
<div id="list-user" class="content scaffold-list" role="main">
	<h1><g:message code="user.list.label"/></h1>

	<g:render template="/_common/flash-message"/>

	<div class="buttons">
		<g:link class="btn btn-success" action="create"><g:message code="user.button.new.label"/></g:link>

		<script type="application/javascript">

			function batchDelete() {
				if (confirm('${message(code: 'default.button.delete.confirm.message')}')) {
					$('#batch_action').val('batchDelete');
					$('#batch_form').submit();
				}
				return false;
			}
			function batchEnable() {
				if (confirm('${message(code: 'user.button.enable.confirm.message')}')) {
					$('#batch_action').val('batchEnable');
					$('#batch_form').submit();
				}
				return false;
			}
			function batchDisable() {
				if (confirm('${message(code: 'user.button.disable.confirm.message')}')) {
					$('#batch_action').val('batchDisable');
					$('#batch_form').submit();
				}
				return false;
			}
		</script>

		<div class="btn-group">
			<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><g:message code="default.button.actionSelect.label"/> <span class="caret"></span></button>
			<ul class="dropdown-menu" role="menu">
				<sec:ifAllGranted roles="ROLE_ADMIN">
					<li><a href="#" onclick="return batchDelete();"><g:message code="user.button.delete.label"/></a></li>
				</sec:ifAllGranted>
				<li><a href="#" onclick="return batchEnable();"><g:message code="user.button.enable.label"/></a></li>
				<li><a href="#" onclick="return batchDisable();"><g:message code="user.button.disable.label"/></a></li>
			</ul>
		</div>
	</div>



	<g:form url="[controller: 'user', action: 'index']">
		<div class="row">
			<div class="col-md-3">
				<g:select class="form-control"
						  name="role"
						  from="${ru.deloved.Role.list()}"
						  optionKey="id"
						  optionValue="${{ message(code: 'role.' + it.authority, default: it.authority) }}"
						  value="${filter.role}"
						  noSelection="${['': message(code: 'role.allroles.label')]}"/>
			</div>

			<div class="col-md-2">
				<g:select class="form-control"
						  name="enabled"
						  from="${[true, false]}"
						  valueMessagePrefix="user.activity"
						  value="${filter.enabled}"
						  noSelection="${['': message(code: 'user.activity.all')]}"/>
			</div>

			<div class="col-md-3"><g:textField placeholder="Login" class="form-control" name="username" value="${filter.username}"/></div>

			<div class="col-md-3">
				<g:submitButton name="filterAction" class="btn btn-primary" value="${message(code: 'default.button.filter.label')}"/>
				<g:submitButton name="resetAction" class="btn btn-default" formaction="reset" value="${message(code: 'default.button.filterReset.label')}"/>
			</div>

		</div>

	</g:form>


	<g:form id="batch_form" url="[controller: 'user', action: 'batch']" method="POST">
		<g:hiddenField name="batch_action" value=""/>
		<div class="table-responsive">
			<table border="0" class="table table-striped">
				<thead>
				<tr>
					<th>#</th>

					<g:sortableColumn property="username" title="${message(code: 'user.username.label', default: 'Username')}"/>

					<g:sortableColumn property="enabled" title="${message(code: 'user.enabled.label', default: 'Enabled')}"/>
					<g:if test="${!filter.role}">
						<g:sortableColumn property="role" title="${message(code: 'user.role.label', default: 'Role')}"/>
					</g:if>

					<g:sortableColumn property="dateCreated" title="${message(code: 'user.dateCreated.label', default: 'Date Created')}"/>

				</tr>
				</thead>
				<tbody>
				<g:each in="${userInstanceList}" status="i" var="obj">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>
							<input type="checkbox" name="id" value="${obj.id}"/>
						</td>

						<td><g:link action="edit"
									id="${obj.id}"
									elementId="gridRow${obj.id}username"
									data-toggle="modal"
									data-remote="${createLink(id: obj.id, action: 'edit')}"
									data-target="#myModal">${fieldValue(bean: obj, field: "username")}</g:link>
						</td>

						<td id="gridRow${obj.id}enabled">
							<g:render template="status" model="[status: obj.enabled]"/>
						</td>

						<g:if test="${!filter.role}">
							<td>${message(code: 'role.' + obj.role.authority, default: obj.role.authority)}</td>
						</g:if>

						<td><g:formatDate date="${obj.dateCreated}" format="dd.MM.yyyy HH:mm:ss"/></td>

					</tr>
				</g:each>
				</tbody>
			</table>
		</div>

		<g:if test="${params.max < rowsCount}">
			<g:paginate total="${rowsCount ?: 0}"/>
		</g:if>

	</g:form>
	<script type="application/javascript">

		function onClickStatus(event) {
			//console.log(event);
			//console.log(event.currentTarget);
			var parent = $(event.currentTarget).parent();
			var pid = parent.attr('id');
			//console.log(pid);
			pid = pid.replace('gridRow', '');
			pid = pid.replace('enabled', '');
			//console.log(pid);

			jQuery.ajax({
						type: 'POST',
						data: {id: pid},
						url: '${createLink(controller: 'user', action: 'status')}',
						success: function (data, textStatus) {
							jQuery(parent).html("").append(jQuery(data).bind("click", onClickStatus));
						},
						error: function (XMLHttpRequest, textStatus, errorThrown) {
						}
					}
			);
		}

		$(function () {
			$('span.status').bind("click", onClickStatus);
		});
	</script>
</div>

<g:render template="/_common/edit-container"/>

</body>
</html>
