<%@ page import="ru.deloved.Review" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="review.show.label"/></title>
</head>

<body>
<div id="show-review" class="content scaffold-show" role="main">

	<h1><g:message code="review.show.label"/></h1>

	<g:render template="/_common/flash-message"/>


	<g:if test="${freeAccount}">
		<g:render template="/_common/promo"/>
	</g:if>
	<g:else>

		<div class="form-horizontal">



			<div class="fieldcontain form-group">
				<label class="col-sm-3 control-label">
					Отзыв о
				</label>
				<div class="col-sm-9">
					<a href="${createLink(controller: 'company', action: 'index', id: reviewInstance.to.id)}">
						<p class="form-control-static">
							${reviewInstance.to.orgForm.code + '\"'+ reviewInstance.to.name + '\"'}
						</p>
					</a>
				</div>
			</div>

			<g:if test="${reviewInstance.deal}">
				<div class="fieldcontain form-group">
					<label class="col-sm-3 control-label">
						По сделке
					</label>
					<div class="col-sm-9">
						<a href="${createLink(controller: 'deal', action: 'thread', id: reviewInstance.deal.id)}">
							<p class="form-control-static">
								${reviewInstance.deal.id}
							</p>
						</a>
					</div>
				</div>


				<div class="fieldcontain form-group">
					<label class="col-sm-3 control-label">
						Оценка
					</label>
					<div class="col-sm-9">

							<p class="form-control-static">


								<g:if test="${reviewInstance.value == 1}">
									<label class="good-review" title="Положительная оценка"><span class="glyphicon glyphicon-plus-sign"></span></label>
								</g:if>
								<g:if test="${reviewInstance.value == 0}">
									<label class="neutral-review" title="Нейтральная оценка"><span class="glyphicon glyphicon-record"></span></label>
								</g:if>
								<g:if test="${reviewInstance.value == -1}">
									<label class="bad-review" title="Отрицательная оценка"><span class="glyphicon glyphicon-minus-sign"></span></label>
								</g:if>

							</p>

					</div>
				</div>
			</g:if>

			<div class="fieldcontain form-group">
				<label class="col-sm-3 control-label">
					Содеражние
				</label>
				<div class="col-sm-9">

						<p class="form-control-static">
							${reviewInstance.content}
						</p>

				</div>
			</div>

			<div class="fieldcontain form-group">
				<label class="col-sm-3 control-label">
					Автор
				</label>
				<div class="col-sm-9">
					<a href="${createLink(controller: 'company', action: 'index', id: reviewInstance.from.id)}">
						<p class="form-control-static">
							${reviewInstance.from.orgForm.code + '\"'+ reviewInstance.from.name + '\"'}
						</p>
					</a>
				</div>
			</div>


			<div class="fieldcontain form-group">
				<div class="col-sm-3">

				</div>
				<div class="col-sm-9">
					<g:link class="btn btn-default" action="index"><span class="glyphicon glyphicon-chevron-left"></span> <g:message
							code="review.backallreviews"/></g:link>
				</div>
			</div>





		</div>


	</g:else>

</div>

<g:render template="/_common/gallery-multi"/>

</body>
</html>
