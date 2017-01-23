<g:form id="fileupload" enctype="multipart/form-data" method="POST" url="[resource: objInstance, action: 'upload']">
	<!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
	<div class="row fileupload-buttonbar">
		<div class="col-sm-8">
		<!-- The fileinput-button span is used to style the file input field as button -->
			<g:if test="${!deleteOnly}">
				<button class="btn btn-success  fileinput-button" style="margin-bottom: 10px;border-radius: 15px; float:right;width: 220px;">
					<i class="glyphicon glyphicon-paperclip"></i>
					<span>Прекрепить документы</span>
					<input type="file" multiple="" name="files[]">
				</button>
			</g:if>
			<span class="fileupload-process"></span>
		</div>
		<!-- The global progress state -->
		<div class="col-lg-5 fileupload-progress fade">
			<!-- The global progress bar -->
			<div aria-valuemax="100" aria-valuemin="0" role="progressbar" class="progress progress-striped active">
				<div style="width:0%;" class="progress-bar progress-bar-success"></div>
			</div>
			<!-- The extended global progress state -->
			<div class="progress-extended">&nbsp;</div>
		</div>
	</div>
	<!-- The table listing the files available for upload/download -->
	<table class="table table-striped" role="presentation">
		<tbody class="files">
		<g:each in="${attachList}" var="att">
			<tr class="template-download">
				<td>
					<span class="preview">
						<a href="${createLink([action: 'download', id: att.attachment.id, params: [name: att.attachment.name, preview: '']])}"
						   title="${att.attachment.name}"
							<g:if test="${!att.attachment.isImage()}">
								download="${att.attachment.name}"
							</g:if>
							<g:else>
								data-gallery=""
							</g:else>>
							<g:if test="${att.attachment.isImage()}">
								<img src="${createLink([action: 'thumb', id: att.attachment.id, params: [name: att.attachment.name]])}">
							</g:if>
							<g:else>
								<asset:image src="fileupload/${att.attachment.getIcon()}"/>
							</g:else>
						</a>
					</span>
				</td>
				<td>
					<p class="name">
						<a href="${createLink([action: 'download', id: att.attachment.id, params: [name: att.attachment.name]])}"
						   title="${att.attachment.name}"
						   download="${att.attachment.name}">
							${att.attachment.name}
						</a>
					</p>
				</td>
				<td>
					<span class="size">${att.attachment.size}</span>
				</td>
				<td>
					<button class="btn btn-danger delete" data-type="DELETE"
							data-url="${createLink([action: 'deleteatt', id: att.attachment.id, params: [name: att.attachment.name]])}">
						<i class="glyphicon glyphicon-trash"></i>
						<span>Удалить</span>
					</button>
				</td>
			</tr>
		</g:each>
		</tbody>
	</table>


	<!-- The template to display files available for download -->
	<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download">
        <td>
            <span class="preview">
                {% if (file.thumbnailUrl) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
                {% } %}
            </span>
        </td>
        <td>
            <p class="name">
                {% if (file.url) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}">{%=file.name%}</a>
                {% } else { %}
                    <span>{%=file.name%}</span>
                {% } %}
            </p>
            {% if (file.error) { %}
                <div><span class="label label-danger">Error</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
            <span class="size">{%=o.formatFileSize(file.size)%}</span>
        </td>
        <td>
            {% if (file.deleteUrl) { %}
                <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>Удалить</span>
                </button>
            {% } else { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>

</g:form>

<script type="application/javascript">
	$(function () {
		// Initialize the jQuery File Upload widget:
//		$('#fileupload').fileupload({uploadTemplateId: null});
//		$('#fileupload').fileupload('option', {
		$('#fileupload').fileupload({
			uploadTemplateId: null,
			maxFileSize: 5000000,
			previewMaxWidth: 64,
			previewMaxHeight: 64,
			acceptFileTypes: /(\.|\/)(gif|jpe?g|png|doc|xls|pdf|ppt)$/i,
			add: function (e, data) {
				data.submit();
			}
		});
	});
</script>
