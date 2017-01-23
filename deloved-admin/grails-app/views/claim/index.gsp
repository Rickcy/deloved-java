<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="ru.deloved.Claim" %>
<%@ page import="ru.deloved.ClaimStatus" %>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="claim.list.label"/></title>
</head>

<body>

<div id="list-claims" class="content scaffold-list" role="main">
	<sec:ifAnyGranted roles="ROLE_ADMIN">
		<div style="z-index: 101"> <g:render template="/_common/hint" model="[hintText: 'Для подачи нового иска нужно выбрать спорную сделку.']"/></div>
	</sec:ifAnyGranted>

	<h1><g:message code="claim.list.label"/></h1>

	<g:render template="/_common/flash-message"/>



	<g:form url="[controller: 'claim', action: 'index']">
		<div class="row">
			<div class="col-md-3">
				<g:select class="form-control"
						  name="status"
						  from="${ClaimStatus.values()}"
						  optionKey="value"
						  optionValue="${{ message(code: 'claim.status.' + it, default: it) }}"
						  value="${filter.status}"
						  noSelection="${['': message(code: 'claim.status.allstatus.label')]}"/>
			</div>

			<div class="col-md-3"><g:textField placeholder="Поиск" class="form-control" name="search" value="${filter.search}"/></div>

			<div class="col-md-6">
				<g:submitButton name="filterAction" class="btn btn-primary ft" value="${message(code: 'default.button.filter.label')}"/>
				<g:submitButton name="resetAction" class="btn btn-default ft" formaction="reset" value="${message(code: 'default.button.filterReset.label')}"/>
			</div>

		</div>

	</g:form>

	<div class="table-responsive">
	<table border="0" class="table table-striped table-hover">
		<thead style="border-bottom: 3px solid rgba(176, 208, 83, 0.24)">
		<tr>
			<th class="ft">№</th>
			<th class="ft"><g:message code="claim.accounts.label" default="Claim"/></th>
			<th class="ft"><g:message code="claim.status.label" default="Status"/></th>
			<g:sortableColumn class="ft" property="dateCreated" title="${message(code: 'claim.dateCreated.label', default: 'Date Created')}"/>
			<sec:ifAnyGranted roles="ROLE_ADMIN">
				<th class="ft">Действия</th>
			</sec:ifAnyGranted>

				</tr>
		</thead>
		<tbody>
		<g:each in="${claimInstanceList}" status="i" var="obj">
			<tr id="claim${obj.id}" class="${(i % 2) == 0 ? 'even' : 'odd'}">
				<td>${i+1}</td>
				<td><g:link id="${obj.id}"  class="${userNewObjects.contains(obj)?"newItem":""}" controller="claim" action="thread">${obj.account.name} <span class="glyphicon glyphicon-arrow-right"></span> ${obj.partner.name}</g:link>
					<g:if test="${userNewObjectPosts[obj]}">
						<span class="badge">+${userNewObjectPosts[obj]}</span>
					</g:if>
				</td>
				<td>${message(code: 'claim.status.' + obj.status(), default: obj.status())}
					<g:if test="${userNewObjectStatuses[obj]}">
						<span class="badge">+${userNewObjectStatuses[obj]}</span>
					</g:if>
				</td>
				<td><g:formatDate date="${obj.dateCreated}" format="dd.MM.yyyy HH:mm:ss"/></td>

				<sec:ifAnyGranted roles="ROLE_ADMIN">
					<td><a name="deleteClaim" for="${obj.id}" href="javascript:void(0)"><span class="glyphicon glyphicon-trash"></span></a></td>
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
		$('[name=deleteClaim]').click(function(event){
			if (confirm('${message(code: 'item.delete.confirm.'+params.categoryType?.code, default: 'Delete this item?')}')) {
				var claimId = $(this).attr('for');
				jQuery.ajax({
					type:'DELETE',
					url:'${createLink([controller: 'claim', action: 'deleteclaim'])}/'+claimId,
					success:function(data,textStatus){
						$('#claim'+claimId).remove();
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