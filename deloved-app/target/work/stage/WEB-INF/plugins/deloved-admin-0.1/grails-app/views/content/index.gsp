<%@ page import="ru.deloved.Content" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="content.list.label"/></title>
	<style>
		.modal-dialog{
			width: 80% !important;
		}
	</style>
</head>

<body>
<div id="list-content" class="content scaffold-list" role="main">

	<h1><g:message code="content.list.label"/></h1>

	<g:render template="/_common/flash-message"/>

	<div class="buttons">
		<g:link class="btn btn-success" action="create"><g:message code="content.button.new.label"/></g:link>
		<script type="application/javascript">

			function batchDelete() {
				if (confirm('${message(code: 'default.button.delete.confirm.message')}')) {
					$('#batch_action').val('batchDelete');
					$('#batch_form').submit();
				}
				return false;
			}

			function batchEnable() {
				if (confirm('${message(code: 'content.button.enable.confirm.message')}')) {
					$('#batch_action').val('batchEnable');
					$('#batch_form').submit();
				}
				return false;
			}

			function batchDisable() {
				if (confirm('${message(code: 'content.button.disable.confirm.message')}')) {
					$('#batch_action').val('batchDisable');
					$('#batch_form').submit();
				}
				return false;
			}
		</script>

		<div class="btn-group">
			<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><g:message code="default.button.actionSelect.label"/> <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a href="#" onclick="return batchDelete();"><g:message code="content.button.delete.label"/></a></li>
				<li><a href="#" onclick="return batchEnable();"><span class="glyphicon glyphicon-ok" style="color: lightgreen;"></span> <g:message code="content.button.enable.label"/></a></li>
				<li><a href="#" onclick="return batchDisable();"><span class="glyphicon glyphicon-lock" style="color: red;"></span> <g:message code="content.button.disable.label"/></a></li>
			</ul>
		</div>
	</div>

	<g:form url="[controller: 'content', action: 'index']">
		<div class="row">
			<div class="col-md-3"><g:textField placeholder="Name" class="form-control" name="name" value="${filter.name}"/></div>
			<div class="col-md-3"><g:textField placeholder="Code" class="form-control" name="code" value="${filter.code}"/></div>

			<div class="col-md-3">
				<g:submitButton name="filterAction" class="btn btn-primary" value="${message(code: 'default.button.filter.label')}"/>
				<g:submitButton name="resetAction" class="btn btn-default" formaction="reset" value="${message(code: 'default.button.filterReset.label')}"/>
			</div>

		</div>

	</g:form>

	<g:form id="batch_form" url="[controller: 'content', action: 'batch']" method="POST">
		<g:hiddenField name="batch_action" value=""/>
		<div class="table-responsive">
                <table border="0" class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>#</th>
				<g:sortableColumn property="name" title="${message(code: 'content.name.label', default: 'Name')}"/>
				<g:sortableColumn property="code" title="${message(code: 'content.code.label', default: 'Code')}"/>
				<g:sortableColumn property="enabled" title="${message(code: 'content.enabled.label', default: 'Enabled')}"/>
			</tr>
			</thead>
			<tbody>
			<g:each in="${contentInstanceList}" status="i" var="obj">
				<script>
					console.log("${obj.news!='Новость'}")
				</script>

					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>
							<input type="checkbox" name="id" value="${obj.id}"/>
						</td>
						<g:if test="${obj.news=='Новость'}">

						<td><g:link action="edit"
									id="${obj.id}"
									elementId="gridRow${obj.id}name"
									data-toggle="modal"

									data-remote="${createLink(id: obj.id, action: 'edit')}"

									data-target="#myModal">${fieldValue(bean: obj, field: "name")}</g:link></td>
						</g:if>
						<g:else>
							<td><g:link action="edit2"
										id="${obj.id}"
										elementId="gridRow${obj.id}name"
										data-toggle="modal"

										data-remote="${createLink(id: obj.id, action: 'edit2')}"

										data-target="#myModal">${fieldValue(bean: obj, field: "name")}</g:link></td>
					</g:else>
						<td id="gridRow${obj.id}code">${obj.code}</td>
						<td id="gridRow${obj.id}enabled">${obj.enabled}</td>

					</tr>





			</g:each>
			</tbody>
		</table>
</div>
		<g:if test="${params.max < rowsCount}">
			<g:paginate total="${rowsCount ?: 0}"/>
		</g:if>
	</g:form>

</div>

<g:render template="/_common/edit-container"/>

</body>
</html>
