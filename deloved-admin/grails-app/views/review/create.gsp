<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="review.create.label"/></title>
</head>

<body>

<div id="create-review" class="content scaffold-create" role="main">

	<h1><g:message code="review.create.label"/></h1>

	<g:if test="${freeAccount}">
		<g:render template="/_common/promo"/>
	</g:if>
	<g:else>
		<div class="row">
			<div class="col-md-9">
				<g:form id="createReview" url="[resource:reviewInstance,action: 'save']" method="POST" class="form-horizontal">

					<input type="hidden" name="from" value="${reviewInstance.from?.id}"/>
					<input type="hidden" name="to" value="${reviewInstance.to?.id}"/>
					<input type="hidden" name="deal" value="${reviewInstance.deal?.id}"/>
					<input type="hidden" name="author" value="${reviewInstance.author?.id}"/>

					<g:render template="form" model="[
							objInstance: reviewInstance
					]"/>

					<div class="fieldcontain form-group">
						<div class="col-md-3"></div>
						<div class="col-md-9">
							<button class="btn btn-success" type="submit">Оставить отзыв</button>
						</div>
					</div>

				</g:form>
			</div>
			<div class="com-md-3" style="color: gray; text-align: center; padding: 10px">
				<g:staticContent code="createReview"/>
			</div>
		</div>
	</g:else>

</div>
</body>
</html>