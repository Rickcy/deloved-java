<%@ page import="ru.deloved.User" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="front">
	<title><g:message code="adcontent.list.label"/></title>
	<style type="text/css">
	span.enabled {
		color: lightgreen;
	}

	span.disabled {
		color: orangered;
	}

	.bar {
		height: 18px;
		background: green;
	}
	</style>

</head>

<body>
<div id="list-adcontent" class="content scaffold-list" style="min-height:300px " role="main">
	<h1><g:message code="adcontent.list.label"/></h1>

	<g:render template="/_common/flash-message"/>







	<g:form id="batch_form" url="[controller: 'adcontent', action: 'batch']" method="POST">
		<g:hiddenField name="batch_action" value=""/>
		<table border="0" class="table table-striped">
			<thead>
			<tr>


				<g:sortableColumn property="name" title="${message(code: 'adcontent.name.label', default: 'Name')}"/>
				<th>Возможные действия</th>


				<g:sortableColumn property="approved"
								  title="${message(code: 'adcontent.approved.label', default: 'Approved')}"/>

					<th><g:message code="adcontent.account.label" default="Account"/></th>


				<g:sortableColumn property="dateCreated"
								  title="${message(code: 'adcontent.dateCreated.label', default: 'Date Created')}"/>

			</tr>
			</thead>
			<tbody class="files">
			<g:each in="${adcontentInstanceList}" status="i" var="obj">

				<g:if test="${obj.approved==true}">

					<g:if test="${params.company==obj.account.name}">



				<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">


					<td><g:link action="edit" controller="adcontent"
								id="${obj.id}"
								elementId="gridRow${obj.id}name"
								data-toggle="modal"
								data-remote="${createLink(id: obj.id, action: 'edit')}"
								data-target="#myModal">${fieldValue(bean: obj, field: "name")}</g:link>

					<td>
						<g:if test="${obj.file}">
							<a style="margin-right: 20px" id="gridRow${obj.id}download" href="${createLink([action: 'download', id: obj.file.id, params: [name: obj.file.name]])}"
							   title="Скачать"
							   download="${obj.file.name}">
								<span class="glyphicon glyphicon-cloud-download" ></span>
							</a>
							<a target="_blank" id="gridRow${obj.id}preview" href="${createLink([action: 'download', id: obj.file.id, params: [name: obj.file.name, preview: true]])}"
							   title="Просмотр"><span class="glyphicon glyphicon-eye-open"></span>
							</a></g:if>
						<g:else>
							<a title="Просмотр" href="${obj.url}" ><span class="glyphicon glyphicon-eye-open" ></span></a>
						</g:else>

					</td>



					<td id="gridRow${obj.id}approved">
						<g:render template="status" model="[status: obj.approved]"/>
					</td>

						<td>${obj.account.name}</td>


					<td><g:formatDate date="${obj.dateCreated}" format="dd.MM.yyyy HH:mm:ss"/></td>

				</tr>
					</g:if>
			</g:if>
			</g:each>
			</tbody>
		</table>
		<g:if test="${params.max < rowsCount}">
			<g:paginate total="${rowsCount ?: 0}"/>
		</g:if>

	</g:form>

</div>

<g:render template="/_common/edit-container"/>
<g:render template="/_common/gallery-multi"/>

<script type="application/javascript">
	function setupForm(flag) {
//		console.log(flag);
		if (flag) {
			$('#fileupload').fileupload('enable');
		} else {
			$('#fileupload').fileupload('disable');
		}
	}
	$(function () {

		// Initialize the jQuery File Upload widget:
		$('#fileupload').fileupload({
			uploadTemplateId: null,
			maxFileSize: 5000000,
			previewMaxWidth: 64,
			previewMaxHeight: 64,
			filesContainer: $('tbody.files'),
			acceptFileTypes: /(\.|\/)(gif|jpeg|png|pdf|mp3)$/i,
			add: function (e, data) {
//				console.log("submit data!");
				data.submit();
			}
		});
		<g:if test="${!showAccount}">
		$('#fileupload').bind('fileuploadsubmit', function (e, data) {
			var input = $('#naccount');
			data.formData = {account: input.val()};
//			console.log(data.formData);
			if (!data.formData.account) {
				data.context.find('button').prop('disabled', false);
				input.focus();
				return false;
			}
		});
		</g:if>
		<g:if test="${!showFiles}">
		$('#fileupload').fileupload('disable');
		</g:if>
	});
</script>

</body>
</html>
