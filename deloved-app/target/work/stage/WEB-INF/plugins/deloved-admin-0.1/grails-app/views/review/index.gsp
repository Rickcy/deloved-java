<%@ page import="ru.deloved.Review" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title>Отзывы</title>
</head>

<body>
<div id="list-review" class="content scaffold-list" role="main">

	<h1>Отзывы</h1>

	<g:render template="/_common/flash-message"/>

	<g:form url="[controller: 'review', action: 'index']">
		<sec:ifAllGranted roles="ROLE_ACCOUNT">
			<ul class="nav nav-tabs">
				<li class="<g:if test="${filter.type == null || filter.type == "from"}">active</g:if>">
					<a class="ft" href="${createLink(action: 'index', params: [type: 'from'])}">Мои</a>
				</li>
				<li class="<g:if test="${filter.type == "to"}">active</g:if>">
					<a class="ft" href="${createLink(action: 'index', params: [type: 'to'])}">Обо мне</a>
				</li>
			</ul>
		</sec:ifAllGranted>

		<br>

		<div class="row">

			<div class="col-md-3"><g:textField placeholder="Поиск" class="form-control" name="search" value="${filter.search}"/></div>
			<g:if test="${!toMe}">
				<div class="col-md-2">
					<g:select class="form-control"
							  name="status"
							  from="${statusFilterValues}"
							  valueMessagePrefix="review.filter.status"
							  value="${filter.status}"
							  noSelection="${['': message(code: 'review.filter.status.all')]}"/>
				</div>
			</g:if>

			<div class="col-md-2">
				<g:select class="form-control"
						  name="value"
						  from="${[1, -1]}"
						  valueMessagePrefix="review.filter.value"
						  value="${filter.value}"
						  noSelection="${['': message(code: 'review.filter.value.all')]}"/>
			</div>


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


			<sec:ifNotGranted roles="ROLE_ACCOUNT">
				<th class="ft"><g:message code="review.accounts.label" default="Предприятия"/></th>
			</sec:ifNotGranted>
			<sec:ifAllGranted roles="ROLE_ACCOUNT">
				<th class="ft"><g:message code="review.account.label" default="Предприятие"/></th>
			</sec:ifAllGranted>



			<th class="ft"><g:message code="review.author.label" default="Author"/></th>

			<th class="ft"><g:message code="review.value.label" default="Value"/></th>

			<th class="ft"><g:message code="review.published.label" default="Published"/></th>

			<g:sortableColumn property="dateCreated" title="${message(code: 'review.dateCreated.label', default: 'Date Created')}" class="ft"/>
			<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
				<th class="ft">Действия</th>
			</sec:ifAnyGranted>
		</tr>
		</thead>
		<tbody>
		<g:each in="${reviewInstanceList}" status="i" var="obj">
			<tr id="rev${obj.id}" class="${(i % 2) == 0 ? 'even' : 'odd'}">



				<td>
					<g:link class="${userNewObjects.contains(obj) ? "newItem" : ""}" action="edit" id="${obj.id}">
						<sec:ifNotGranted roles="ROLE_ACCOUNT">
							${fieldValue(bean: obj, field: "from.name")} <span class="glyphicon glyphicon-arrow-right"></span> ${fieldValue(bean: obj, field: "to.name")}
						</sec:ifNotGranted>
						<sec:ifAllGranted roles="ROLE_ACCOUNT">
							${fieldValue(bean: obj, field: filter.type == 'to' ? "from.name" : "to.name")}
						</sec:ifAllGranted>
					</g:link>

				</td>




				<td>${fieldValue(bean: obj, field: "author.fio")}</td>

				<td>
					<g:if test="${obj.value == 1}">
						<label><span class="glyphicon glyphicon-plus-sign"></span></label>
					</g:if>
					<g:if test="${obj.value == 0}">
						<label><span class="glyphicon glyphicon-record"></span></label>
					</g:if>
					<g:if test="${obj.value == -1}">
						<label><span class="glyphicon glyphicon-minus-sign"></span></label>
					</g:if>
				</td>

				<td>
					<g:if test="${obj.finished}">
						<span class='glyphicon ${obj.published ? 'glyphicon-ok' : 'glyphicon-lock'}'></span>
					</g:if>
					<g:else>
						<g:if test="${obj.value != 0}">
							<span class="glyphicon glyphicon-question-sign"></span>
						</g:if>
					</g:else>
				</td>

				<td><g:formatDate date="${obj.dateCreated}" format="dd.MM.yyyy HH:mm:ss"/></td>
				<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
					<td><a name="deleteRev" for="${obj.id}" href="javascript:void(0)"><span class="glyphicon glyphicon-trash"></span></a></td>
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

<g:render template="/_common/edit-container"/>
<g:render template="/_common/gallery-multi"/>
<script>
	$(document).ready(function() {
		$('[name=deleteRev]').click(function(event){
			if (confirm('${message(code: 'item.delete.confirm.'+params.categoryType?.code, default: 'Delete this item?')}')) {
				var RevId = $(this).attr('for');
				jQuery.ajax({
					type:'DELETE',
					url:'${createLink([controller: 'deal', action: 'deletedeal'])}/'+RevId,
					success:function(data,textStatus){
						$('#rev'+RevId).remove();
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
