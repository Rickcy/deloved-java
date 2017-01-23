<g:form mapping="${mapping}" name="regionForm" method="POST">
	<input type="hidden" class="form-control" id="region" name="region" value="${regionFilterData?.selectedRegion?.id}"/>
</g:form>


<script type="text/javascript">
	$('#region').select2({
		placeholder: "Выберите регион или город",
		allowClear: true,
		data: ${raw(regionFilterData?.regionsTreeJson)}
	});
	$("#region").on("change", function (e) {
		$("#regionForm").submit();
	});

</script>
