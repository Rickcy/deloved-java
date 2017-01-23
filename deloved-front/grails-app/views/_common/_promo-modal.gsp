<!-- Modal -->
<div class="modal fade" id="myModalPromo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">


			<div class="modal-body">
				<sec:ifNotLoggedIn>
					<g:render template="/_common/promo-modal-unauth"/>
				</sec:ifNotLoggedIn>
				<sec:ifAllGranted roles="ROLE_ACCOUNT">
					<g:render template="/_common/promo-modal-unauth"/>
				</sec:ifAllGranted>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
			</div>
		</div>
	</div>
</div>
