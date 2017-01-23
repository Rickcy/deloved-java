
			<span id="dropzoneFiles">
				<g:each in="${attachList}" var="att">
					<div class="uploadImage">
						<a id="im${att.id}" href="${createLink([action: 'image', id: att.id, params: [name: att.image.file, type: 'main']])}" data-gallery="">
							<img name="uploadPreview" id="th${att.id}" src="${createLink([action: 'image', id: att.id, params: [name: att.imageThumb.file]])}">
						</a>
						<button type="button" class="deleteimage" data-item="${att.id}">
							<span class="glyphicon glyphicon-trash"></span>
						</button>
						<button type="button" class="cropimage" data-toggle="modal" data-target="#cropModal" crop-target="#im${att.id}" crop-result="#th${att.id}"
								crop-action="${createLink([action: 'crop', id: att.id])}">
							<span class="glyphicon glyphicon-edit"></span>
						</button>
						<input type="hidden" name="itemAttach" value="${att.id}"/>
					</div>
				</g:each>
			</span>

			<g:if test="${!deleteOnly}">
				<div id="dropzoneUpload" class="uploadButton">
					<span class="glyphicon glyphicon-picture"></span>
				</div>
			</g:if>

<div id="preview-template" style="display: none">
	<div class="uploadImage">
		<a id="" href="" data-gallery="">
			<img id="" name="uploadPreview"/>
		</a>
		<div class="uploadProgress">
			<div data-dz-uploadprogress>
			</div>
		</div>
		<button type="button" data-item="" class="deleteimage" disabled>
			<span class="glyphicon glyphicon-trash"></span>
		</button>
		<button type="button" class="cropimage" data-toggle="modal" data-target="#cropModal" crop-target="" crop-result="" crop-action="" disabled>
			<span class="glyphicon glyphicon-picture"></span>
		</button>
	</div>
</div>

<script type="application/javascript">

	var previewTemplate = document.querySelector("#preview-template").innerHTML;

	$(document).ready(function() {
		$('#dropzoneFiles').on('click','.deleteimage', function(event){
			var id, elem
			elem = this
			id = jQuery(elem).data('item');
			jQuery.ajax({
				url: '${createLink(controller: 'item', action: 'deleteimage')}/'+id,//elem.dataset.item,
				type: 'DELETE',
				dataType: 'json',
				success: function(data, textStatus){
					var preview = jQuery(elem).parent()
					preview.animate({opacity: 0}, 300, function() {
						preview.animate({width: 0}, 300, function(){
							preview.remove();
						})
					})
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					showMessage('danger', 'Сбой при попытке удалить изображние')
				}
			});
		});

		var myDropzone = new Dropzone('#dropzoneUpload', {
			paramName: "files[]",
			maxFileSize: 2,
			method: 'POST',
			url: '${createLink(controller: 'item', action: 'upload')}',
			clickable: true,
			parallelUploads: 1,
			uploadMultiple: false,
			<%--maxFiles: 5,--%>
			params: {${objInstance?.id ? 'id: ' + objInstance.id  : null}},
			acceptedFiles: "image/jpeg,image/png,image/gif,image/jpg",
			previewsContainer: '#dropzoneFiles',
			previewTemplate: previewTemplate,
			addRemoveLinks: false,
			success: function(file, response) {
				var node, _i, _len, _ref, _results, input;
				if (file.previewElement) {
					_ref = file.previewElement.getElementsByTagName('img');
					_results = [];
					for (_i = 0, _len = _ref.length; _i < _len; _i++) {
						node = _ref[_i];
						_results.push(node.src = response.files[0].thumbnailUrl);
						_results.push(node.setAttribute('id', 'th'+response.files[0].id));
					}
					_ref = file.previewElement.getElementsByClassName('deleteimage');
					for (_i = 0, _len = _ref.length; _i < _len; _i++){
						node = _ref[_i];
						_results.push(node.dataset.item = response.files[0].id)
						_results.push(node.removeAttribute('disabled'))
					}
					_ref = file.previewElement.getElementsByClassName('cropimage');
					for (_i = 0, _len = _ref.length; _i < _len; _i++){
						node = _ref[_i];
						_results.push(node.setAttribute('crop-target', '#im' + response.files[0].id));
						_results.push(node.setAttribute('crop-result', '#th' + response.files[0].id));
						_results.push(node.setAttribute('crop-action', '${createLink(controller: 'item', action: 'crop')}/' + response.files[0].id));
						_results.push(node.removeAttribute('disabled'))
					}
					_ref = file.previewElement;

					input = document.createElement('input');
					input.setAttribute('type', 'hidden');
					input.setAttribute('name', 'itemAttach');
					input.setAttribute('value', response.files[0].id);
					_results.push(_ref.appendChild(input));

					_ref = file.previewElement.getElementsByTagName('a')
					for (_i = 0, _len = _ref.length; _i < _len; _i++){
						node = _ref[_i];
						_results.push(node.setAttribute('id', 'im'+response.files[0].id));
						_results.push(node.setAttribute('href', response.files[0].url));
					}
					return _results
				}
			},
			complete: function(file){
				var node, _i, _len, _ref, _results;
				_ref = file.previewElement.querySelectorAll("[data-dz-uploadprogress]");
				_results = [];
				for (_i = 0, _len = _ref.length; _i < _len; _i++) {
					node = _ref[_i];
					_results.push(file.previewElement.removeChild(node.parentNode))
				}
				return _results
			},
			uploadprogress: function(file, progress, bytesSent){
				var node, _i, _len, _ref, _results;
				if (file.previewElement) {
					_ref = file.previewElement.querySelectorAll("[data-dz-uploadprogress]");
					_results = [];
					for (_i = 0, _len = _ref.length; _i < _len; _i++) {
						node = _ref[_i];
						if (node.nodeName === 'PROGRESS') {
							_results.push(node.value = progress);
						} else {
							_results.push(node.style.width = "" + progress + "%");
						}
					}
					return _results;
				}
			},
			maxfilesexceeded: function(file) {
				this.removeFile(file);
			}
		});

	<%-- var mockFiles = []
		<g:each in="${attachList}" var="att">
			mockFiles.push({
				file: {
					name:'${att.image.name}',
					size: ${att.image.size}
				},
				response: {
					files: [
						{
							url         : '${createLink([controller: 'item', action: 'image', id: att.id, params: [name: att.image.file, type: 'main']])}',
							thumbnailUrl: '${createLink([controller: 'item', action: 'image', id: att.id, params: [name: att.imageThumb.file]])}',
							id: ${att.id}
						}
					]
				}
			});
		</g:each>


		if (mockFiles.length > 0){
			mockFiles.forEach(function(item, i, array) {
				// Call the default addedfile event handler
				myDropzone.files.push(item.file);
				myDropzone.emit("addedfile", item.file);
				myDropzone.emit("success", item.file, item.response)
				// And optionally show the thumbnail of the file:
				//myDropzone.emit("thumbnail", item.file, item.response.files[0].url);
				// Make sure that there is no progress bar, etc...
				myDropzone.emit("complete", item.file);
			})
		} --%>

	});

</script>