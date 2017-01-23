<div id="modalContainer"></div>
<script type="application/javascript">
	$(function () {

		function constructModalDOM() {
			return $("<div></div>").
					attr('id', 'myModal').
					addClass("modal").
					addClass("fade").
					attr('tabindex', '-1').
					attr('role', 'dialog').
					attr('aria-labelledby', 'myModalLabel').
					attr('aria-hidden', 'true').
					on('hidden.bs.modal', onHideModal).
					append(
					$("<div></div>").
							addClass("modal-dialog").
							append(
							$("<div></div>").
									attr('id', 'myModalContent').
									addClass("modal-content")
					)
			);
		}

		function onHideModal() {
			$('#myModal').replaceWith(constructModalDOM());
		}

		constructModalDOM().appendTo($('#modalContainer'));
	});
</script>
