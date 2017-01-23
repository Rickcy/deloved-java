<h1>Список категорий</h1>



<ul class="level_one">
	<g:each in="${categoryFilterData.categories}" var="cat">
		<g:link params="${categoryFilterData.company ? [acategory: cat.id, offset: 0, company: categoryFilterData.company.id] : [category: cat.id, offset: 0]}"><a href="javascript:void(0)" onclick="$('.hiddencat${cat.id}').toggle('fast')">${cat.name}</a></g:link>
		<ul class="level_two">
			<g:set var="cnt" value="${categoryFilterData.subcategories[cat].size()}"/>
			<g:each in="${categoryFilterData.subcategories[cat]}" status="i" var="cat2">
				<g:link class="${(cnt > 0) ? 'hiddencat hiddencat' + cat.id : ''}"
						params="${categoryFilterData.company ? [acategory: cat2.id, offset: 0, company: categoryFilterData.company.id] : [category: cat2.id, offset: 0]}">${cat2.name}</g:link>
			</g:each>

		</ul>
	</g:each>
</ul>
