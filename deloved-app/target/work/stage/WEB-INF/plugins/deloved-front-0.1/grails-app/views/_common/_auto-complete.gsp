
<g:set var="_acKeyName" value="${acKeyName.replaceAll('[.]','\\\\\\\\.')}"/>
<g:set var="_acViewName" value="${acViewName.replaceAll('[.]','\\\\\\\\.')}"/>

<g:hiddenField name="${acKeyName}" value="${acKeyValue}"/>
<g:hiddenField name="${acViewName}old" value="${acViewValue}"/>
<g:textField class="form-control" name="${acViewName}" value="${acViewValue}"/>


<script type="application/javascript">
	$(function () {
		$("#${_acViewName}").autocomplete({
			source: "${acAction}",
			minLength: ${acMinLength},
			change: function (event, ui) {
				if ($("#${_acViewName}old").val() != this.value) {
					$("#${_acKeyName}").val("");
					$("#${_acViewName}").parent().addClass("error");
					${acOnChange?:''}
				}
			},
			select: function (event, ui) {
				if (ui.item) {
					$("#${_acKeyName}").val(ui.item.id);
					$("#${_acViewName}").val(ui.item.value).parent().removeClass("error");
					$("#${_acViewName}old").val(ui.item.value);
					${acOnSelect?:''}
				}
//				console.log(ui.item);
//				console.log(ui.item ?
//						"Selected: " + ui.item.id + " aka " + ui.item.label :
//						"Nothing selected, input was " + this.value);
			}
		});
		$("#${_acViewName}").autocomplete("option", "appendTo", "#editForm");
	});
</script>
