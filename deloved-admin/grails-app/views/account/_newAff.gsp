<%@ page import="ru.deloved.Affiliate" %>
<g:set var="liID" value="${new java.util.Date().time}"/>
<li id="li${liID}" class="list-group-item">
	<g:hiddenField name="aff.${i}.id" value="${affInstance?.id}"/>
	<g:hiddenField name="aff.${i}.city.id" value="${affInstance?.city?.id}"/>
	<g:hiddenField name="aff.${i}.cityname2" value="${affInstance?.city?.name}"/>
	<div class="col-sm-3 fieldcontain form-group ${hasErrors(bean: affInstance, field: 'address', 'error')} required">
		<g:textField class="form-control" name="aff.${i}.address" value="${affInstance?.address}" placeholder="${message(code: "affiliate.address.label")}"/>
	</div>

	<div class="col-sm-3 fieldcontain form-group ${hasErrors(bean: affInstance, field: 'city', 'error')} ">
		<g:textField class="form-control" name="aff.${i}.cityname" value="${affInstance?.city?.name}" placeholder="${message(code: "affiliate.city.label")}"/>
	</div>

	<div class="col-sm-3 fieldcontain form-group ${hasErrors(bean: affInstance, field: 'phone', 'error')} ">
		<g:textField class="form-control" name="aff.${i}.phone" value="${affInstance?.phone}" placeholder="${message(code: "affiliate.phone.label")}"/>
	</div>

	<div class="col-sm-3 fieldcontain form-group ${hasErrors(bean: affInstance, field: 'email', 'error')} ">
		<g:textField class="form-control" name="aff.${i}.email" value="${affInstance?.email}" placeholder="${message(code: "affiliate.email.label")}"/>
	</div>
	<button type="button" class="btn btn-default" onclick="$('#li${liID}').remove();">&times;</button>

	<script type="application/javascript">
		$(function () {
			$('#aff\\.${i}\\.cityname').autocomplete({
				source: "${createLink(controller: 'account', action: 'cities')}",
				minLength: 2,
				change: function (event, ui) {
					if ($('#aff\\.${i}\\.cityname2').val() != this.value) {
						$('#aff\\.${i}\\.city\\.id').val("");
					}
				},
				select: function (event, ui) {
					if (ui.item) {
						$('#aff\\.${i}\\.city\\.id').val(ui.item.id);
						$('#aff\\.${i}\\.cityname').val(ui.item.value);
						$('#aff\\.${i}\\.cityname2').val(ui.item.value);
					}
				}
			});
			$('#aff\\.${i}\\.cityname').autocomplete("option", "appendTo", "#editForm");
		});

	</script>
</li>
