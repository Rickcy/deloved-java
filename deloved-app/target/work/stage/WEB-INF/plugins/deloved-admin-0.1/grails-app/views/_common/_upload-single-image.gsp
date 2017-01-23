
	<div class="row" style="height: ${imgHeight}">
	<div class="col-md-6" id="uploadImagePanel" style="width: ${imgWidth}px; margin-right: 10px">
		<g:if test="${isUpload}">
			<a id="mainlink" href="${imageUrl}" data-gallery>
				<img id="${imgId}" title="${imgTitle}" width="${imgWidth}" height="${imgHeight}" src="${thumbUrl}"/>
			</a>
		</g:if>
		<g:else>
			<img width="${imgWidth}" height="${imgHeight}" src="${resource(dir: 'image', file: '/admin/avatar.jpg')}"/>
		</g:else>
	</div>

	<div class="col-md-6 single-image-edit">

		<a href="javascript:void(0)"  style="display: block;" type="button" id="fileuploadTrigger" onclick="$('#fileupload').click()">
			<span class="glyphicon glyphicon-download-alt"></span>
		</a>

		<input style="display: none" type="file" name="files[]" id="fileupload" data-url="${uploadAction}">

		<a href="javascript:void(0)" style="display: ${isUpload ? 'block' : 'none'};" type="button" id="deleteUploadButton"
			onclick="if (confirm('Подтвердить удаление изображения?')) ${remoteFunction(action: deleteAction, id: params.id, onSuccess: 'onImageDelete()')}">
				<span class="glyphicon glyphicon-trash"></span>
		</a>
		<a href="javascript:void(0)" id="cropButton" style="display: ${isUpload ? 'block' : 'none'};" type="button"
			data-toggle="modal" data-target="#cropModal" crop-target="#mainlink" crop-result="#${imgId}" crop-action="${cropAction}">
				<span class="glyphicon glyphicon-resize-full"></span>
		</a>
	</div>

	</div>
<script type="application/javascript">
	function onImageDelete() {
		$('#uploadImagePanel').html('');
		$('#deleteUploadButton').hide();
		$('#cropButton').hide();
	}
	$(function () {
		$('#fileupload').fileupload({
			uploadTemplateId: null,
			downloadTemplateId: null,
			dataType: 'json',
			formData: {},
			add: function (e, data) {
				data.submit();
			},
			done: function (e, data) {
				// data.result
				// data.textStatus;
				// data.jqXHR;
				//console.log(data.result);
				var f = data.result.files;
				if (f != null && f[0] != null) {
					$('#uploadImagePanel').html('');
					$('<a>').attr('id', 'mainlink').attr('href', f[0].url).attr('data-gallery', '').appendTo('#uploadImagePanel');
					$('<img>').attr('id', '${imgId}').attr('title', '${imgTitle}').attr('width', ${imgWidth}).attr('height', ${imgHeight}).attr('src', f[0].thumbnailUrl).appendTo('#mainlink');
					$('#deleteUploadButton').show();
					$('#cropButton').show();
					$('#jcrop_target').attr('src', f[0].url);
				}
			},
			maxFileSize: 5000000,
			acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i
		});

	});
</script>
