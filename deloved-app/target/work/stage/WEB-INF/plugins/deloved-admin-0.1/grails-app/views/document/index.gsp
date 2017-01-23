<%@ page import="ru.deloved.DocumentCategory" %>
<%@ page import="ru.deloved.Document" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="document.list.label"/></title>
</head>

<body>
<g:if test="${freeAccount}">
	<g:render template="/_common/promo"/>
</g:if>
<g:else>
	<div id="list-document" class="content scaffold-list" role="main">

		<h1><g:message code="document.list.label"/></h1>

		<g:render template="/_common/flash-message"/>

		<g:set var="editAllowed" value="${false}"/>
		<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
			<g:set var="editAllowed" value="${true}"/>
		</sec:ifAnyGranted>

		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			<g:each in="${documentCategoryInstanceList}" status="i" var="obj">
				<h2>Категории</h2>
				<div class="panel panel-default" id="cat${obj.id}">
					<div class="panel-heading" role="tab" id="heading${obj.id}">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapse${obj.id}" aria-expanded="true" aria-controls="collapse${obj.id}">
								${fieldValue(bean: obj, field: "name")}
							</a>
							(${obj.documents.size()})

							<g:if test="${editAllowed}">
								<g:remoteLink method="DELETE" action="delcat" id="${obj.id}" onSuccess="jQuery('#cat${obj.id}').remove()"
											  onFailure="onDeleteError(XMLHttpRequest)" before="if(!confirm('Вы уверены?')) return false"><span
										class="glyphicon glyphicon-trash"></span></g:remoteLink>
							</g:if>
						</h4>
					</div>

					<div id="collapse${obj.id}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${obj.id}">
						<div class="panel-body">
							<table class="table table-striped">
								<tr>
									<th>Название</th>
									<th></th>
									<th></th>

								</tr>
								<g:each in="${obj.documents}" status="di" var="att">
									<tr id="att${att.attachment.id}">
										<td>
											<g:if test="${editAllowed}">
												<g:link action="edit"
														id="${att.id}"
														elementId="gridRow${att.id}name"
														data-toggle="modal"
														data-remote="${createLink(id: att.id, action: 'edit')}"
														data-target="#myModal">${fieldValue(bean: att, field: "name")}s</g:link>
											</g:if>
											<g:else>
												${att.name}
											</g:else>
										</td>

										<td>
											<a href="${createLink([action: 'download', id: att.id, params: [name: att.name]])}" title="${att.name}"
											   download="${att.name}">Скачать
											</a>
											</td>
											<td>
										<a target="_blank" id="gridRow${att.attachment.id}preview" href="${createLink([action: 'download', id: att.attachment.id, params: [name: att.attachment.name, preview: true]])}"
										   title="Просмотр"><span class="glyphicon glyphicon-eye-open"></span>
										</a>
											</td>

											<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
											<td>
												<g:remoteLink action="deleteatt" id="${att.attachment.id}" params="[name: att.attachment.name]"
															  onSuccess="jQuery('#att${att.attachment.id}').remove()"
															  onFailure="onDeleteError(XMLHttpRequest)" before="if(!confirm('Вы уверены?')) return false"><span
														class="glyphicon glyphicon-trash"></span></g:remoteLink>
											</td>


											</sec:ifAnyGranted>


									</tr>
								</g:each>
							</table>
						</div>
					</div>
				</div>
			</g:each>
		</div>

		<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">

			<div class=lead>Загрузка файлов</div>
			<g:render template="/_common/upload-multi" model="[
					objInstance: new Document(),
					attachList : attach
			]"/>


			<g:if test="${!attach.isEmpty()}">
				<div>Переместить загруженные в новую категорию</div>
			</g:if>
			<g:form class="form-inline" name="addForm" url="[controller: 'document', action: 'savecat']">
				<div class="form-group">
					<g:textField name="name" required="" class="form-control"/>
				</div>
				<g:submitButton name="${message(code: 'category.button.new.label')}" class="btn btn-success">Создать категорию</g:submitButton>
			</g:form>
			<g:if test="${DocumentCategory.count > 0}">
				или существующую:
				<g:form class="form-inline" name="addForm" url="[controller: 'document', action: 'move']">
					<div class="form-group">
						<g:select class="form-control"
								  name="id"
								  from="${DocumentCategory.list()}"
								  optionKey="id"
								  optionValue="name"/>
					</div>
					<g:submitButton name="Переместить" class="btn btn-success"/>
				</g:form>
			</g:if>

		</sec:ifAnyGranted>
	</div>

	<g:render template="/_common/edit-container"/>
</g:else>

</body>
</html>