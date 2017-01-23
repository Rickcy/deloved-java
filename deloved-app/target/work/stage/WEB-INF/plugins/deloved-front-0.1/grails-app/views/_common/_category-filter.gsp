<ul class="directory" style="overflow-y: scroll;height: 630px">

	<g:if test="${categoryFilterData != null && categoryFilterData.level != null}">
		<li><g:link style="color: white;background-color: #4B7BBE;padding: 10px;" params="${categoryFilterData.company ? [acategory: 0, company: categoryFilterData.company.id] : [category: 0]}">Главная</g:link></li>

			<li class="up"><g:link class="${(categoryFilterData.level == categoryFilterData.category) ? 'active' : 'under_active'}"
							   params="${categoryFilterData.company ?
									   [
											   acategory: categoryFilterData.level.id,
											   offset   : 0,
											   company  : categoryFilterData.company.id
									   ] :
									   [
											   category: categoryFilterData.level.id,
											   offset  : 0
									   ]}">${categoryFilterData.level.name}</g:link>
		</li>
	</g:if>




	<g:each in="${categoryFilterData.categories}" var="cat">

		<li><g:link class="${(cat == categoryFilterData.category) ? 'active' : ''}" params="${categoryFilterData.company ?
				[
						acategory: cat.id,
						offset   : 0,
						company  : categoryFilterData.company.id
				] :
				[
						category: cat.id,
						offset  : 0
				]}">${cat.name}</g:link>
		</li>
	</g:each>
</ul>
