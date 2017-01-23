<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h4 class="modal-title" id="adcontentsModalLabel">Реклама</h4>
</div>

<div class="modal-body">

	<g:if test="${freeAccount}">
		<sec:ifNotLoggedIn>
			<g:render template="/_common/promo-modal-unauth"/>
		</sec:ifNotLoggedIn>
		<sec:ifAllGranted roles="ROLE_ACCOUNT">
			<g:render template="/_common/promo-modal-unauth"/>
		</sec:ifAllGranted>
	</g:if>
	<g:else>

		<g:if test="${adcontentsCount > 0}">

			<ol>
				<g:each in="${adcontents}" var="adcontents">
					<li>
						<g:formatDate date="${adcontents.dateCreated}" format="dd.MM.yyyy HH:mm:ss"/> <br/>
						<div>${adcontents.name}</div>
						<hr>
						<div>${adcontents.description}</div>
						<hr/>

						<div>${adcontents.file}</div>
						<hr/>
					</li>
				</g:each>
			</ol>
		</g:if>
		<g:else>
			Нет рекламы
		</g:else>
	</g:else>

</div>

<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">${message(code: 'default.button.close.label')}</button>
</div>
