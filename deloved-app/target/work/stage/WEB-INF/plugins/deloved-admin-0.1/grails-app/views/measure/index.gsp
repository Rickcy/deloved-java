<%@ page import="ru.deloved.Measure" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="measure.list.label"/></title>
</head>

<body>
<div id="list-measure" class="content scaffold-list" role="main">

	<h1><g:message code="measure.list.label"/></h1>

	<g:render template="/_common/flash-message"/>

	<div class="buttons">
		<g:link class="btn btn-success" action="create"><g:message code="measure.button.new.label"/></g:link>
		<script type="application/javascript">

			function batchDelete() {
				if (confirm('${message(code: 'default.button.delete.confirm.message')}')) {
					$('#batch_action').val('batchDelete');
					$('#batch_form').submit();
				}
				return false;
			}
		</script>

		<div class="btn-group">
			<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><g:message code="default.button.actionSelect.label"/> <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a href="#" onclick="return batchDelete();"><g:message code="measure.button.delete.label"/></a></li>
			</ul>
		</div>
	</div>

	<g:form url="[controller: 'measure', action: 'index']">
		<div class="row">
			<div class="col-md-3">
				<g:select class="form-control"
						  name="ctype"
						  from="${ru.deloved.CategoryType.list()}"
						  optionKey="id"
						  optionValue="${{ message(code: 'categorytype.' + it.code, default: it.code) }}"
						  value="${filter.ctype}"
						  noSelection="${['': message(code: 'categorytype.alltypes.label')]}"/>
			</div>

			<div class="col-md-3"><g:textField placeholder="Name" class="form-control" name="mname" value="${filter.mname}"/></div>

			<div class="col-md-3">
				<g:submitButton name="filterAction" class="btn btn-primary" value="${message(code: 'default.button.filter.label')}"/>
				<g:submitButton name="resetAction" class="btn btn-default" formaction="reset" value="${message(code: 'default.button.filterReset.label')}"/>
			</div>

		</div>

	</g:form>

	<g:form id="batch_form" url="[controller: 'measure', action: 'batch']" method="POST">
		<g:hiddenField name="batch_action" value=""/>
		<div class="table-responsive">
                <table border="0" class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>#</th>
				<g:sortableColumn property="name" title="${message(code: 'measure.name.label', default: 'Name')}"/>
				<g:sortableColumn property="fullname" title="${message(code: 'measure.fullname.label', default: 'Fullname')}"/>
				<th><g:message code="measure.type.label" default="Type"/></th>
			</tr>
			</thead>
			<tbody>
			<g:each in="${measureInstanceList}" status="i" var="obj">
				<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td>
						<input type="checkbox" name="id" value="${obj.id}"/>
					</td>

					<td><g:link action="edit"
								id="${obj.id}"
								elementId="gridRow${obj.id}name"
								data-toggle="modal"
								data-remote="${createLink(id: obj.id, action: 'edit')}"
								data-target="#myModal">${fieldValue(bean: obj, field: "name")}</g:link></td>

					<td id="gridRow${obj.id}fullname">${fieldValue(bean: obj, field: "fullname")}</td>
					<td id="gridRow${obj.id}type">${message(code: 'categorytype.' + obj.type.code, default: obj.type.code)}</td>

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
