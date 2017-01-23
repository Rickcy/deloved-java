<g:each in="${gridCols}" var="col">
jQuery('#gridRow${gridRowId}${col.key}').html("${rawCols && rawCols[col.key] ? raw(col.value) : col.value}");
</g:each>
${handler?raw(handler):''}
jQuery("#myModal").modal("hide");
