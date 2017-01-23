
<!-- Modal -->
<div class="modal fade" id="cropModal" tabindex="-1" role="dialog" aria-labelledby="myCropLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<table>
					<tr>
						<td>
							<img id="jcrop_target"/>
						</td>
						<td valign="top" style="padding-left: 20px;">
							<div style="width:100px;height:100px;overflow:hidden;">
								<img id="preview"/>
							</div>
						</td>
					</tr>
				</table>
			</div>

			<div class="modal-footer">
				<button disabled="" id="saveSelection" type="button" class="btn btn-primary" onclick="selection()">Save changes</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<script type="application/javascript">
	function showPreview(coords) {
		//console.log(coords.w)
		var rx = 100 / coords.w;
		var ry = 100 / coords.h;
		var tar = $('#jcrop_target');

		if (coords.w > 0 && coords.h > 0) {
			$('#saveSelection').attr('disabled', null);
			$('#preview').show();
		} else {
			$('#saveSelection').attr('disabled', '');
			$('#preview').hide();
		}
		$('#preview').css({
			width: Math.round(rx * tar.width()) + 'px',
			height: Math.round(ry * tar.height()) + 'px',
			marginLeft: '-' + Math.round(rx * coords.x) + 'px',
			marginTop: '-' + Math.round(ry * coords.y) + 'px'
		});
	}
	var jcrop_api;
	var jcrop_result;
	var jcrop_action;
	function selection() {
		jQuery.ajax({
			type: 'POST',
			data: jcrop_api.tellSelect(),
			url: jcrop_action,
			success: function (data, textStatus) {
				if (data.thumb) {
					jcrop_result.attr('src', data.thumb);
				}
				$("#cropModal").modal("hide");
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				alert("Error:" + errorThrown);
			}});
	}
	$(function () {
		$('#cropModal').on('show.bs.modal', function (e) {
			var related = $(e.relatedTarget);
			jcrop_result = $(related.attr('crop-result'));
			jcrop_action = related.attr('crop-action');
			$('#jcrop_target').attr('src', $(related.attr('crop-target')).attr('href'));
			$('#jcrop_target').Jcrop({
				onChange: showPreview,
				onSelect: showPreview,
				aspectRatio: 1,
				minSize: [200, 200],
				setSelect: [ 100, 100, 200, 200 ],
				boxWidth: 700,
				boxHeight: 600
			}, function () {
				jcrop_api = this;
			});
			$('#saveSelection').attr('disabled', '');
			$('#preview').attr('src', $('#jcrop_target').attr('src'));
			$('#preview').hide();
		})
		$('#cropModal').on('hidden.bs.modal', function (e) {
			// do something...
			jcrop_api.destroy();
			jcrop_result = null;
			jcrop_action = null;
			$('#preview').hide();

		})

	});
</script>
