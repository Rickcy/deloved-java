<%@ page import="ru.deloved.CategoryType; ru.deloved.Item" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="${'item.list.' + params.categoryType?.code + '.label'}"/></title>
</head>

<body>
<div id="list-item" class="content scaffold-list" role="main">

	<h1><g:message code="${'item.list.' + params.categoryType?.code + '.label'}"/></h1>

	<g:render template="/_common/flash-message"/>
	<sec:ifAnyGranted roles="ROLE_ACCOUNT">
	<div class="buttons">

		<g:if test="${params.categoryType?.code == 'GOOD'}">
			<g:link style="width: 20%;min-width: 110px;margin-bottom: 5px" class="btn btn-success ft" controller="item" action="create" mapping="${params.categoryType?.code}"><g:message code="item.button.new.label.good"/></g:link>
		</g:if>
		<g:else>
			<g:link style="width: 20%;min-width: 110px;margin-bottom: 5px" class="btn btn-success ft" controller="item" action="create" mapping="${params.categoryType?.code}"><g:message code="item.button.new.label.service"/></g:link>
		</g:else>

		<g:link style="width: 20%;min-width: 110px;margin-bottom: 5px" class="btn btn-success ft" action="export" mapping="${params.categoryType?.code}"><g:message code="item.button.export.label"/></g:link>

		<span style="width: 20%;min-width: 110px;margin-bottom: 5px" class="btn btn-success fileinput-button ft">
			<span><g:message code="item.button.import.label"/></span>
			<input type="file" name="file" id="fileupload2" data-url="${createLink(action: 'import', mapping: params.categoryType?.code)}">
		</span>


		<script type="application/javascript">
			$(function () {
				$('#fileupload2').fileupload({
					uploadTemplateId: null,
					downloadTemplateId: null,
					dataType: 'json',
					formData: {},
					add: function (e, data) {
						data.submit();
					},
					done: function (e, data) {
						//data.result
						//data.textStatus;
						//data.jqXHR;
						//console.log(data.result);
						var f = data.result.files;
						if (f != null && f[0] != null) {
							location.reload();
						}
					},
					maxFileSize: 5000000,
					acceptFileTypes: /(\.|\/)(xlsx)$/i
				});

			});
		</script>

	</div>
	</sec:ifAnyGranted>
	<g:form url="[mapping: params.categoryType?.code, controller:'item' , action: 'index']">
		<div class="row" style="margin-bottom: 2%">
			<g:if test="${params.categoryType?.code == 'GOOD'}">
				<div class="col-md-2">
					<g:select class="form-control" style="margin-bottom: 3%"
							  name="avail"
							  from="${[1, 0]}"
							  valueMessagePrefix="item.filter.avail"
							  value="${filter?.avail}"
							  noSelection="${['': message(code: 'item.filter.avail.all')]}"/>
				</div>
			</g:if>

			<div class="col-md-3"><g:textField placeholder="Поиск" class="form-control" name="search" value="${filter?.search}" style="margin-bottom: 3%"/></div>

			<div class="col-md-2"><g:textField placeholder="Цена от" class="form-control" name="priceMin" value="${filter?.priceMin}" style="margin-bottom: 3%"/></div>

			<div class="col-md-2"><g:textField placeholder="Цена до" class="form-control" name="priceMax" value="${filter?.priceMax}" style="margin-bottom: 3%"/></div>

			<div class="col-md-3">
				<g:submitButton name="filterAction" class="btn btn-primary ft" value="${message(code: 'default.button.filter.label')}" style="margin-bottom: 4%"/>

				<g:submitButton name="resetAction" class="btn btn-default ft" style="margin-bottom: 4%"
								formaction="reset"
								value="${message(code: 'default.button.filterReset.label')}"/>

			</div>
		</div>

	</g:form>

	<div class="table-responsive">
		<table border="0" class="table table-striped table-responsive table-hover">
		<thead style="border-bottom: 3px solid rgba(176, 208, 83, 0.24)">
		<tr>
			<th></th>

			<th class="ft"><g:message code="item.account.label" default="Account"/></th>

			<g:sortableColumn property="name" class="ft" mapping="${params.categoryType?.code}" title="${message(code: 'item.name.label', default: 'Name')}"/>

			<g:sortableColumn property="price" class="ft" mapping="${params.categoryType?.code}" title="${message(code: 'item.price.label', default: 'Price')}"/>




			<g:sortableColumn class="ft" property="dateCreated" mapping="${params.categoryType?.code}" title="${message(code: 'item.dateCreated.label', default: 'Date Created')}"/>

			<th class="ft">Действия</th>

		</tr>
		</thead>
		<tbody>
		<g:each in="${itemInstanceList}" status="i" var="obj">
			<tr id="item${obj.id}" class="${(i % 2) == 0 ? 'even' : 'odd'}">
				<td><input type="checkbox" name="id" value="${obj.id}"/></td>

				<td id="gridRow${obj.id}account">${fieldValue(bean: obj, field: "account.name")}</td>

				<td name="itemName">
					<g:link  class="${userNewObjects.contains(obj) ? "newItem" : ""}" controller="item" action="edit" id="${obj.id}">${fieldValue(bean: obj, field: "name")}</g:link>
					<g:if test="${userNewObjects.contains(obj)}">
						<span class="badge">+1</span>
					</g:if>
				</td>

				<td id="gridRow${obj.id}price"><g:formatNumber number="${obj.price}" type="currency" currencyCode="${obj.currency.code}"/></td>




				<td><g:formatDate date="${obj.dateCreated}" format="dd.MM.yyyy HH:mm:ss"/></td>

				<td><a name="deleteItem" for="${obj.id}" href="javascript:void(0)"><span class="glyphicon glyphicon-trash"></span></a></td>



			</tr>
		</g:each>
		</tbody>
	</table>

	<g:if test="${params.max < rowsCount}">
		<g:paginate total="${rowsCount ?: 0}" mapping="${params.categoryType?.code}"/> <%-- params="[categoryTypeCode: params.categoryType?.code]"--%>
	</g:if>
	</div>
	</div>
<g:render template="/_common/edit-container"/>
<g:render template="/_common/gallery-multi"/>
<g:render template="/_common/crop"/>

<script>
	$(document).ready(function() {
		$('[name=deleteItem]').click(function(event){
			if (confirm('${message(code: 'item.delete.confirm.'+params.categoryType?.code, default: 'Delete this item?')}')) {
				var itemId = $(this).attr('for');
				jQuery.ajax({
					type:'DELETE',
					url:'${createLink([controller: 'item', action: 'delete'])}/'+itemId,
					success:function(data,textStatus){
						$('#item'+itemId).remove();
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
