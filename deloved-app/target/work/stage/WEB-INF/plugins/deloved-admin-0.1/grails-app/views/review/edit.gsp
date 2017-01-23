<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="review.edit.label"/></title>
</head>

<body>
	<div id="edit-review" class="content scaffold-edit" role="main">

		<h1><g:message code="review.edit.label"/></h1>
		<g:if test="${freeAccount}">
			<g:render template="/_common/promo"/>
		</g:if>
		<g:else>
			<div class="row">
				<div class="col-md-9">
					<g:form id="editReview" url="[resource: reviewInstance, action: 'update']" method="POST" class="form-horizontal">
						<g:render template="form" model="[
						        objInstance: reviewInstance
						]"/>

						<div class="fieldcontain form-group">
							<div class="col-md-3"></div>
							<div class="col-md-9">

								<button class="btn btn-success" type="submit">Сохранить</button>

								<a href="${createLink(action: 'delete', id: reviewInstance.id)}" id="deleteReview" class="btn btn-danger">Удалить</a>
								<script	type="application/javascript">
									document.getElementById('deleteReview').onclick = function(){
										confirm("Вы уверены, что хотите удалить данный отзыв?")
									}
								</script>

							</div>
						</div>

				</g:form>
				</div>
				<div class="com-md-3" style="color: gray; text-align: center; padding: 10px">

					<sec:ifAnyGranted roles="ROLE_ACCOUNT">
						<g:staticContent code="editReviewByUser"/>
					</sec:ifAnyGranted>
					<sec:ifAnyGranted roles="ROLE_ADMIN">
						<g:staticContent code="editReviewByAdmin"/>
					</sec:ifAnyGranted>
					<sec:ifAnyGranted roles="ROLE_MANAGER">
						<g:staticContent code="editReviewByManager"/>
					</sec:ifAnyGranted>

				</div>
			</div>
		</g:else>
	</div>
</body>
</html>
