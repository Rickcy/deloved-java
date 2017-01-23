<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="item.create.label"/></title>
</head>

<body>
<div id="create-item" class="content scaffold-create" role="main">

	<h1><g:message code="${'item.create.' + itemInstance.categoryType.code + '.label'}" default="${itemInstance.categoryType.code}"/></h1>

<g:if test="${count==0}">

</g:if>


	<script>

	</script>

	<div class="row" style="margin-top: 10px">

		<div class="col-md-12">
			<g:render template="/_common/create" model="[
					'objInstance'  : itemInstance,
					'formMappings' : params.categoryType?.code,
					'cancelMapping': params.categoryType?.code
			]"/>

		</div>

	</div>



	<g:render template="/_common/gallery-multi"/>
	<g:render template="/_common/crop"/>
</div>
</body>
</html>


