



<div id="dealSides" name="dealSides">
	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-6 col-xs-12 text-center">
				<div class="col-xs-12">Покупатель</div>
				<div class="col-xs-12">
					<g:link controller="company" id="${dealInstance.account.id}">
						<g:if test="${dealInstance.account.logo}">
							<img src="${createLink(controller: 'account', action: 'logo', id: dealInstance.account.id, params: [name: dealInstance.account?.logo?.file, type: 'main'])}"
								 title="Логотип предприятия покупателя" style="width: 80px; height: 80px; border-radius: 5px"/>
						</g:if>
						<g:else>
							<img src="${resource(dir: 'image', file: 'admin/avatar.jpg')}" style="width: 80px; height: 80px; border-radius: 5px"/>
						</g:else>
						<p style="margin-top: 2%">${dealInstance.account.orgForm.code + " \"" + dealInstance.account.name + "\""}</p>
					</g:link>
				</div>
			</div>

			<div class="col-sm-6 col-xs-12 text-center">
				<div class="col-xs-12">Продавец</div>
				<div class="col-xs-12">
					<g:link controller="company" id="${dealInstance.partner.id}">

						<g:if test="${dealInstance.partner.logo}">
							<img src="${createLink(controller: 'account', action: 'logo', id: dealInstance.partner.id, params: [name: dealInstance.partner?.logo?.file, type: 'main'])}"
								 title="Логотип предприятия продавца" style="width: 80px; height: 80px; border-radius: 5px"/>
						</g:if>
						<g:else>
							<img src="${resource(dir: 'image', file: 'admin/avatar.jpg')}" style="width: 80px; height: 80px; border-radius: 5px"/>
						</g:else>
						<p style="margin-top: 2%">${dealInstance.partner.orgForm.code + " \"" + dealInstance.partner.name + "\""}</p>
					</g:link>
				</div>
			</div>
		</div>
	</div>

</div>