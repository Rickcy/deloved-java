<%@ page import="ru.deloved.Account" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="account.list.label"/></title>
	<script src="//api-maps.yandex.ru/2.0-stable/?load=package.standard&lang=ru-RU" type="text/javascript"></script>
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

<div id="list-account" class="content scaffold-list" role="main">

	<h1><g:message code="account.list.label"/></h1>
	<div style="z-index: 100;"> <g:render template="/_common/hint" model="[hintText: 'Для удаления предприятия в Краткое описание введите Данные удалены и все переключатели выключить!']"/></div>
	<g:render template="/_common/flash-message"/>

	<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
		<div class="buttons">
			<g:link class="btn btn-success" action="create"><g:message code="account.button.new.label"/></g:link>
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

				function batchSetVerified() {
					if (confirm('${message(code: 'user.button.setVerified.confirm.message')}')) {
						$('#batch_action').val('batchSetVerified');
						$('#batch_form').submit();
					}
					return false;
				}

				function batchSetUnverified() {
					if (confirm('${message(code: 'user.button.setUnverified.confirm.message')}')) {
						$('#batch_action').val('batchSetUnverified');
						$('#batch_form').submit();
					}
					return false;
				}
			</script>

			<div class="btn-group">
				<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><g:message code="default.button.actionSelect.label"/> <span
						class="caret"></span></button>
				<ul class="dropdown-menu" role="menu">
					<sec:ifAllGranted roles="ROLE_ADMIN">
						<li><a href="#" onclick="return batchDelete();"><span class="glyphicon glyphicon-remove" style="color: red;"></span> <g:message
								code="user.button.delete.label"/></a></li>
					</sec:ifAllGranted>
					<li><a href="#" onclick="return batchEnable();"><span class="glyphicon glyphicon-ok" style="color: lightgreen;"></span> <g:message
							code="user.button.enable.label"/></a></li>
					<li><a href="#" onclick="return batchDisable();"><span class="glyphicon glyphicon-lock" style="color: red;"></span> <g:message
							code="user.button.disable.label"/></a></li>
					<li><a href="#" onclick="return batchSetVerified();"><span class="glyphicon glyphicon-ok" style="color: lightgreen;"></span> <g:message
							code="user.button.setVerified.label"/></a></li>
					<li><a href="#" onclick="return batchSetUnverified();"><span class="glyphicon glyphicon-ban-circle" style="color: red;"></span> <g:message
							code="user.button.setUnverified.label"/></a></li>
				</ul>
			</div>
		</div>

		<g:form url="[controller: 'account', action: 'index']">
			<div class="row">

				<div class="col-md-3"><g:textField placeholder="Name" class="form-control" name="name" value="${filter.name}"/></div>

				<div class="col-md-3">
					<g:submitButton name="filterAction" class="btn btn-primary" value="${message(code: 'default.button.filter.label')}"/>
					<g:submitButton name="resetAction" class="btn btn-default" formaction="reset" value="${message(code: 'default.button.filterReset.label')}"/>
				</div>
			</div>
		</g:form>
	</sec:ifAnyGranted>

	<g:form id="batch_form" url="[controller: 'account', action: 'batch']" method="POST">
		<g:hiddenField name="batch_action" value=""/>
		<div class="table-responsive">
			<table border="0" class="table table-striped">
				<thead>
				<tr>
					<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
						<td>#</td>
					</sec:ifAnyGranted>
					<g:sortableColumn property="name" title="${message(code: 'account.name.label')}"/>

					<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
						<g:sortableColumn property="publicStatus" title="${message(code: 'account.publicStatus.label')}"/>
						<g:sortableColumn property="verifyStatus" title="${message(code: 'account.verifyStatus.label')}"/>
					</sec:ifAnyGranted>
					<g:sortableColumn property="city.name" title="${message(code: 'account.city.label')}"/>
					<g:sortableColumn property="dateCreated" title="${message(code: 'account.dateCreated.label')}"/>
				</tr>
				</thead>
				<tbody>
				<g:each in="${accountInstanceList}" status="i" var="obj">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
							<td>
								<input type="checkbox" name="id" value="${obj.id}"/>
							</td>
						</sec:ifAnyGranted>
						<td>
							<g:link action="edit"
									id="${obj.id}"
									class="${userNewObjects.contains(obj)?"newItem":""}"
						>${fieldValue(bean: obj, field: "name")}</g:link>
							<g:if test="${userNewObjects.contains(obj)}">
								<span class="badge">+1</span>
							</g:if>
						</td>

					

						<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
							<td id="gridRow${obj.id}ps">
								<g:render template="status" model="[status: obj.publicStatus, statusClass: 'publicStatus', iconFalse: 'glyphicon-lock']"/>
							</td>
							<td id="gridRow${obj.id}vs">
								<g:render template="status" model="[status: obj.verifyStatus, statusClass: 'verifyStatus']"/>
							</td>
						</sec:ifAnyGranted>

						<td id="gridRow${obj.id}city">${fieldValue(bean: obj, field: "city.name")}</td>

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

	<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
		<script type="application/javascript">
	$(function () {

		function onClickPublicStatus(event) {
			var parent = $(event.currentTarget).parent();
			var pid = parent.attr('id');
			pid = pid.replace('gridRow', '');
			pid = pid.replace('ps', '');
			jQuery.ajax({
				type: 'POST',
				data: {id: pid, type: 'ps'},
				url: '${createLink(controller: 'account', action: 'status')}',
				success: function (data, textStatus) {
					console.log(textStatus);
					jQuery(parent).html("").append(jQuery(data).bind("click", onClickPublicStatus));
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
				}
			});
		}

		$('span.publicStatus').bind("click", onClickPublicStatus);

		function onClickVerifyStatus(event) {
			var parent = $(event.currentTarget).parent();
			var pid = parent.attr('id');
			pid = pid.replace('gridRow', '');
			pid = pid.replace('vs', '');
			jQuery.ajax({
				type: 'POST',
				data: {id: pid, type: 'vs'},
				url: '${createLink(controller: 'account', action: 'status')}',
				success: function (data, textStatus) {
					jQuery(parent).html("").append(jQuery(data).bind("click", onClickVerifyStatus));
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
				}
			});
		}

		$('span.verifyStatus').bind("click", onClickVerifyStatus);
	});
</script>
	</sec:ifAnyGranted>
</div>

<g:render template="/_common/edit-container"/>
<g:render template="/_common/gallery-single"/>
<g:render template="/_common/crop"/>

</body>
</html>
