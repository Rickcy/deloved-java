<div id="${container}"></div>
<script type="application/javascript">
	$(function () {

		function constructModalDOM() {
			return $("<div></div>").
					attr('id', '${modalId}').
					addClass("modal").
					addClass("fade").
					attr('tabindex', '-1').
					attr('role', 'dialog').
					attr('aria-labelledby', '${modalId}Label').
					attr('aria-hidden', 'true').
					on('hidden.bs.modal', onHideModal).
					append(
					$("<div></div>").
							addClass("modal-dialog").
							append(
							$("<div></div>").
									attr('id', '${modalId}Content').
									addClass("modal-content")
					)
			);
		}

		function onHideModal() {
			$('#${modalId}').replaceWith(constructModalDOM());
		}

		constructModalDOM().appendTo($('#${container}'));
	});
</script>
