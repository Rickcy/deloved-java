<script>
	$(document).ready(function(){
		var action = 'dispute'
		var action2 = 'claim'
		var w = window.location.href
		var reg2= w.match(action)
		var reg3= w.match(action2)
		if(reg2||reg3){
			$('.class3').remove();
			$('button >.link').remove()
		}
		if(reg2){
			$('.class').text('Сторона');
			$('.class2').text('Сторона');
		}
	})
</script>
<div class="table-responsive">
<table class="table table-striped">
	<tr>
		<td><span id="partner">
			<div class="class">Истец:</div> <g:link class="name" url="[controller: 'company', id: account.id]"><g:fieldValue bean="${account}" field="name"/></g:link>
		</span></td>
		<td align="center">Контрагент</td>
		<td align="right"><span id="partner">
			<div class="class2">Ответчик:</div> <g:link class="name" url="[controller: 'company', id: partner.id]"><g:fieldValue bean="${partner}" field="name"/></g:link>
		</span></td>
	</tr>
	<tr>
		<td><span id="partner">${account.manager}</span></td>
		<td align="center">ФИО руководителя</td>
		<td align="right"><span id="partner">${partner.manager}</span></td>
	</tr>
	<tr class="class3">
		<td style="vertical-align: middle">
			<g:if test="${!accountUsers.isEmpty()}">
				<g:each in="${accountUsers}" var="user">
					<span>
						<g:if test="${user.avatarThumb}">
							<a href="${createLink(controller: 'profile', action: 'avatar', id: user.id, params: [name: user.avatar?.file, type: 'main'])}" class="avatar"
							   data-gallery="avatar${user.id}">
								<img class="avatar"
									 src="${createLink(controller: 'profile', action: 'avatar', id: user.id, params: [name: user.avatarThumb?.file])}"/></a>
						</g:if>
						<g:else>
							<img class="avatar without-avatar" src="${resource(dir: 'image', file: 'admin/avatar.jpg')}"/>
						</g:else>
						${user.fio}
					</span>
				</g:each>
				</ul>
			</g:if>
		</td>

		<td align="center" style="vertical-align: middle">ФИО контактного лица</td>
		<td align="right" style="vertical-align: middle">
			<g:if test="${!partnerUsers.isEmpty()}">
				<g:each in="${partnerUsers}" var="user">
					<span>
						${user.fio}
						<g:if test="${user.avatarThumb}">
							<a href="${createLink(controller: 'profile', action: 'avatar', id: user.id, params: [name: user.avatar?.file, type: 'main'])}"
							   data-gallery="avatar${user.id}">
								<img class="avatar"
									 src="${createLink(controller: 'profile', action: 'avatar', id: user.id, params: [name: user.avatarThumb?.file])}"/></a>
						</g:if>
						<g:else>
							<img class="avatar without-avatar" src="${resource(dir: 'image', file: 'admin/avatar.jpg')}"/>

						</g:else>

					</span>
				</g:each>
				</ul>
			</g:if>
		</td>
	</tr>
</table>
</div>