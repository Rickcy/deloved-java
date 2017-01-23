<%@ page import="ru.deloved.User" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
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

<g:if test="${freeAccount}">
	<g:render template="/_common/promo"/>
</g:if>
<g:else>
	<div id="list-adcontent" class="content scaffold-list" role="main">
		<h1><g:message code="adcontent.list.label"/></h1>

		<g:render template="/_common/flash-message"/>

		<div class="buttons raw">

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


			<div class="row" style="border:0px solid black">

				<div class="col-md-6">

					<g:set var="showFiles" value="${true}"/>
					<g:if test="${showAccount}">
						<g:if test="${myAccounts?.size() > 0}">
							<g:select id="account" name="account" class="form-control" from="${myAccounts}" optionKey="id" optionValue="name" required=""/>
						</g:if>
						<g:else>
							<g:set var="showFiles" value="${false}"/>
							<g:render template="/_common/auto-complete" model="[
									acAction   : createLink(controller: 'adcontent', action: 'accounts'),
									acMinLength: 4,
									acKeyValue : '',
									acKeyName  : 'naccount',
									acViewValue: '',
									acViewName : 'naccountname',
									acOnSelect : 'setupForm(1);',
									acOnChange : 'setupForm(0);'
							]"/>

						</g:else>
					</g:if>

				</div>
			</div>

			<div class="row" style="border:0px solid black">

				<div class="col-md-3">

					<g:form id="fileupload" enctype="multipart/form-data" method="POST" url="[action: 'upload']">
						<div class="btn fileupload-buttonbar">
							<div class="col-lg-2">
								<span id="uploadButton" class="btn btn-success fileinput-button">
									<i class="glyphicon glyphicon-plus"></i>
									<span>Выбрать файлы</span>
									<input type="file" multiple="" name="files[]">
								</span>
							</div>
						</div>

						<!-- The template to display files available for download -->
						<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
			<tr class="template-download">
				<td>
					<input type="checkbox" name="id" value="{%=file.id%}"/>
				</td>

				<td>
				    {% if (file.error) { %}
					{%=file.name%}
					<div><span class="label label-danger">Error</span> {%=file.error%}</div>
				    {% } else { %}
					<a id="gridRow{%=file.id%}name" data-target="#myModal" data-remote="{%=file.editUrl%}" data-toggle="modal" href="{%=file.editUrl%}">{%=file.name%}</a>

				    {% } %}
				</td>

				<td>
					<a id="gridRow{%=file.id%}download" href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}">
						link
					</a>
					<a target="_blank" id="gridRow{%=file.id%}preview" href="{%=file.previewUrl%}" title="{%=file.name%}">
						open
					</a>
				</td>

				<td>{%=file.type%}</td>

				<td id="gridRow{%=file.id%}approved">
					<g:render template="status" model="[status: false]"/>
				</td>

						<td>{%=file.account%}</td>

				<td>{%=file.dateCreated%}</td>
			</tr>
{% } %}
</script>

					</g:form>

				</div>

				<div class="col-md-9" style="padding-top: 7px;">

					<sec:ifAnyGranted roles="ROLE_ACCOUNT">
					<g:link class="btn btn-success" action="create"><g:message code="adcontent.button.new.label"/></g:link>
					</sec:ifAnyGranted>
						<div class="btn-group">

						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><g:message code="default.button.actionSelect.label"/> <span
								class="caret"></span></button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="return batchDelete();"><g:message code="adcontent.button.delete.label"/></a>
							</li>
							<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
								<li><a href="#" onclick="return batchEnable();"><g:message code="adcontent.button.enable.label"/></a></li>
								<li><a href="#" onclick="return batchDisable();"><g:message code="adcontent.button.disable.label"/></a></li>
							</sec:ifAnyGranted>
						</ul>
					</div>
				</div>
			</div>

		</div>


		<g:form url="[controller: 'adcontent', action: 'index']">
			<div class="row">
				<div class="col-md-3">
					<g:select class="form-control"
							  name="type"
							  from="${ru.deloved.AdcontentType.values()}"
							  optionKey="value"
							  valueMessagePrefix="adcontent.type"
							  value="${filter.type}"
							  noSelection="${['': message(code: 'adcontent.alltypes.label')]}"/>
				</div>

				<div class="col-md-2">
					<g:select class="form-control"
							  name="approved"
							  from="${[true, false]}"
							  valueMessagePrefix="adcontent.approved"
							  value="${filter.approved}"
							  noSelection="${['': message(code: 'adcontent.approved.all')]}"/>
				</div>

				<div class="col-md-3"><g:textField placeholder="Search" class="form-control" name="search"
												   value="${filter.search}"/></div>

				<div class="col-md-3">
					<g:submitButton name="filterAction" class="btn btn-primary"
									value="${message(code: 'default.button.filter.label')}"/>
					<g:submitButton name="resetAction" class="btn btn-default" formaction="reset"
									value="${message(code: 'default.button.filterReset.label')}"/>
				</div>

			</div>
		</g:form>



		<g:form id="batch_form" url="[controller: 'adcontent', action: 'batch']" method="POST">
			<g:hiddenField name="batch_action" value=""/>
			<div class="table-responsive">
                    <table border="0" class="table table-striped table-responsive table-hover">
                            <thead>
                            <tr>
                                <th>#</th>

					<g:sortableColumn property="name" title="${message(code: 'adcontent.name.label', default: 'Name')}"/>
					<th>Действия</th>

					<g:sortableColumn property="approved"
									  title="${message(code: 'adcontent.approved.label', default: 'Approved')}"/>

					<th><g:message code="adcontent.account.label" default="Account"/></th>


					<g:sortableColumn property="dateCreated"
									  title="${message(code: 'adcontent.dateCreated.label', default: 'Date Created')}"/>

				</tr>
				</thead>
				<tbody class="files">
				<g:each in="${adcontentInstanceList}" status="i" var="obj">
					<sec:ifAnyGranted roles="ROLE_ACCOUNT">
						<g:if test="${myAccounts.name==[obj.account.name]}">
							<g:if test="${obj.file.mimeType!='application/octet-stream'&&'text/plain'}">


							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
								<td>
									<input type="checkbox" name="id" value="${obj.id}"/>
								</td>

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
					</sec:ifAnyGranted>
					<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">




						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td>
								<input type="checkbox" name="id" value="${obj.id}"/>
							</td>

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
									   title="Просмотр"><span class="glyphicon glyphicon-eye-open">1</span>
									</a>
								</g:if>
								<g:else>
									<a title="Просмотр" href="${obj.url}" ><span class="glyphicon glyphicon-eye-open" ></span></a>
								</g:else>

							</td>

							<td>${message(code: 'adcontent.type.' + obj.type, default: obj.type)}</td>

							<td id="gridRow${obj.id}approved">
								<g:render template="status" model="[status: obj.approved]"/>
							</td>

							<td>${obj.account.name}</td>


							<td><g:formatDate date="${obj.dateCreated}" format="dd.MM.yyyy HH:mm:ss"/></td>

						</tr>


					</sec:ifAnyGranted>
				</g:each>
				</tbody>
			</table>
			<g:if test="${params.max < rowsCount}">
				<g:paginate total="${rowsCount ?: 0}"/>
			</g:if>
			</div>

		</g:form>


</g:else>




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
			acceptFileTypes: /(\.|\/)(gif|jpe?g|png|pdf|mp3)$/i,
			add: function (e, data) {
//				console.log("submit data!");
				data.submit();
			}
		});
		<g:if test="${showAccount}">
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
</div>
</body>
</html>
