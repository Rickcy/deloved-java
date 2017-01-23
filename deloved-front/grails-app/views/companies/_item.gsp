<%@ page import="ru.deloved.Account" %>



<!-- миникарточка компании -->
<div class="row minicart">
	<div class="col-md-12 ">
		<div class="row">
			<div class="col-xs-4 col-sm-3  " style=" text-align: center;">

				<g:if test="${accountInstance.logoThumb}">

					<img class="img-thumbnail img_left" style="border: none"
						 src="${createLink(controller: 'files', action: 'logo', id: accountInstance.logoThumb?.id, params: [name: accountInstance.logoThumb?.file])}"/>
				</g:if>
				<g:else>
					<img class="img-thumbnail img_left" src="${resource(dir: 'images', file: 'front/logo_uch.png')}" style="border: none"/>
				</g:else>

			</div>

			<div class="col-sm-4 col-xs-8 " >

				<g:if test="${accountInstance?.name}">
					<g:link class="name" url="[controller: 'company', id: accountInstance.id]">
						<g:fieldValue bean="${accountInstance}" field="name"/>
					</g:link>
				</g:if>

				<g:if test="${accountInstance?.description}">
					<div class=description>
						${truncate(max: 200, words: true, value: accountInstance.description)}
					</div>
				</g:if>

			</div>
			<div class="col-xs-12 col-sm-5 " >
				<sec:ifLoggedIn>
					<div class=gray_block>Рейтинг (${accountInstance.rating ? accountInstance.rating + '%' : '-'})
						<br><h5 style="font-size: 12px;color: #428bca"><g:rating value="${accountInstance.rating}"/></h5>



						<g:if test="${reviewsCount > 0}">
							<g:link class="otz" action="reviews"
									data-toggle="modal"
									data-remote="${createLink(id: accountInstance.id, action: 'reviews')}"
									data-target="#reviewsModal">Отзывы (${reviewsCount})</g:link>

						</g:if>
						<g:else>
							<a href=# class=otz>Отзывов нет</a>
						</g:else>
						<g:render template="/_common/modal" model="[
								container: 'reviewsContainer',
								modalId  : 'reviewsModal'
						]"/>
				</div>
				</sec:ifLoggedIn>
				<sec:ifNotLoggedIn>
					<div class=gray_block>


						<g:link class="otz"  controller="login" action="auth"

								  data-toggle="modal"
								  data-remote="${createLink(controller:'login' , action: 'auth')}"
								  data-target="#myModal">Рейтинг</g:link>

							<a href=# class=otz>Отзывы</a>



					</div>
				</sec:ifNotLoggedIn>





				<g:link class="podr" url="[controller: 'company', id: accountInstance.id]">
					Подробнее
				</g:link>

			</div>
	</div>



		<ul class=cont>
			<li><img src="${resource(dir: 'images', file: 'front/local.png')}"/>	<span class="property-value" aria-labelledby="city-label">${accountInstance?.city?.name}</span>
			</li>





		</ul>

	</div>


</div>


