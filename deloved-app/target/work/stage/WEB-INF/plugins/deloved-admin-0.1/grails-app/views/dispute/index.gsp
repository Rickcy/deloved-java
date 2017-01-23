<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="ru.deloved.Dispute" %>
<%@ page import="ru.deloved.DisputeStatus" %>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="dispute.list.label"/></title>
</head>

<body>

<div id="list-disputes" class="content scaffold-list" role="main">
<sec:ifAnyGranted roles="ROLE_ACCOUNT">
<div style="z-index: 100;"> <g:render template="/_common/hint" model="[hintText: 'Для разрешения нового спора нужно выбрать спорную сделку.']"/></div>
</sec:ifAnyGranted>
	<h1><g:message code="dispute.list.label"/></h1>

	<g:render template="/_common/flash-message"/>



	<g:form url="[controller: 'dispute', action: 'index']">
		<div class="row">
			<div class="col-md-3">
				<g:select class="form-control"
						  name="status"
						  from="${DisputeStatus.values()}"
						  optionKey="value"
						  optionValue="${{ message(code: 'dispute.status.' + it, default: it) }}"
						  value="${filter.status}"
						  noSelection="${['': message(code: 'dispute.status.allstatus.label')]}"/>
			</div>

			<div class="col-md-3"><g:textField placeholder="Поиск" class="form-control" name="search" value="${filter.search}"/></div>

			<div class="col-md-5">
				<g:submitButton name="filterAction" class="btn btn-primary ft" value="${message(code: 'default.button.filter.label')}"/>
				<g:submitButton name="resetAction" class="btn btn-default ft" formaction="reset" value="${message(code: 'default.button.filterReset.label')}"/>
			</div>

		</div>

	</g:form>

	<div class="table-responsive">
	<table border="0" class="table table-striped  table-hover">
		<thead style="border-bottom: 3px solid rgba(176, 208, 83, 0.24)">
		<tr>
			<th class="ft">№</th>
			<th class="ft"><g:message code="dispute.accounts.label" default="Dispute"/></th>
			<th class="ft"><g:message code="dispute.status.label" default="Status"/></th>
			<g:sortableColumn class="ft" property="dateCreated" title="${message(code: 'dispute.dateCreated.label', default: 'Date Created')}"/>
			<sec:ifAnyGranted roles="ROLE_ADMIN">
				<th class="ft">Действия</th>
			</sec:ifAnyGranted>
		</tr>
		</thead>
		<tbody>
		<g:each in="${disputeInstanceList}" status="i" var="obj">
			<tr id="disp${obj.id}" class="${(i % 2) == 0 ? 'even' : 'odd'}">
				<td>${i+1}</td>
				<td><g:link id="${obj.id}"  class="${userNewObjects.contains(obj)?"newItem":""}" controller="dispute" action="thread">${obj.account.name} &amp; ${obj.partner.name}</g:link>
					<g:if test="${userNewObjectPosts[obj]}">
						<span class="badge">+${userNewObjectPosts[obj]}</span>
					</g:if>
				</td>
				<td>${message(code: 'dispute.status.' + obj.status(), default: obj.status())}
					<g:if test="${userNewObjectStatuses[obj]}">
						<span class="badge">+${userNewObjectStatuses[obj]}</span>
					</g:if>
				</td>
				<td><g:formatDate date="${obj.dateCreated}" format="dd.MM.yyyy HH:mm:ss"/></td>

				<sec:ifAnyGranted roles="ROLE_ADMIN">
					<td><a name="deleteDisp" for="${obj.id}" href="javascript:void(0)"><span class="glyphicon glyphicon-trash"></span></a></td>
				</sec:ifAnyGranted>
			</tr>
		</g:each>
		</tbody>
	</table>
		</div>
	<g:if test="${params.max < rowsCount}">
		<g:paginate total="${rowsCount ?: 0}"/>
	</g:if>

</div>
<script>
	$(document).ready(function() {
		$('[name=deleteDisp]').click(function(event){
			if (confirm('${message(code: 'item.delete.confirm.'+params.categoryType?.code, default: 'Delete this item?')}')) {
				var dispId = $(this).attr('for');
				jQuery.ajax({
					type:'DELETE',
					url:'${createLink([controller: 'dispute', action: 'deletedisp'])}/'+dispId,
					success:function(data,textStatus){
						$('#disp'+dispId).remove();
						showMessage('success', 'Успешно удалено')
					},
					error:function(XMLHttpRequest,textStatus,errorThrown){

						showMessage('danger', 'Удаление не удалось')
					}
				});
			}
		})
	})
</script>
</body>
</html>