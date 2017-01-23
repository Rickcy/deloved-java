<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h4 class="modal-title" id="reviewsModalLabel"><g:message code="review.list.label"/></h4>
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

		<g:if test="${reviewsCount > 0}">

			<ol>
				<g:each in="${reviews}" var="review">
					<li>
						<g:formatDate date="${review.dateCreated}" format="dd.MM.yyyy HH:mm:ss"/> <br/>
						<g:if test="${review.value > 0}">
							Отзыв положительный
						</g:if>
						<g:elseif test="${review.value < 0}">
							Отзыв отрицательный
						</g:elseif>
						<g:else>
							Отзыв нейтральный
						</g:else>

						<br/>
						Автор отзыва <g:link url="[controller: 'company', id: review.from.id]">
							<g:fieldValue bean="${review.from}" field="name"/>
						</g:link>

						<hr/>

						<div>${review.content}</div>
						<hr/>
					</li>
				</g:each>
			</ol>
		</g:if>
		<g:else>
			пока нет отзывов
		</g:else>
	</g:else>

</div>

<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">${message(code: 'default.button.close.label')}</button>
</div>
