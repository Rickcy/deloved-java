
<ol class="breadcrumb" style="margin-top: 20px">

	<g:each in="${categoryFilterData.breadcrumbList}" var="obj">

		<li><g:link
				params="${categoryFilterData.company ? [acategory: obj.id, offset: 0, company: categoryFilterData.company.id] : [category: obj.id, offset: 0]}">${obj.name}</g:link></li>
	</g:each>
</ol>
