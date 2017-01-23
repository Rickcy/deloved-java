

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="front">
	<meta name="keywords" content="${categoryFilterData?.category?.name}">
	<meta name="description" content="${categoryFilterData?.category?.name}">
	<title>
		Предприятия
		<g:if test="${categoryFilterData.category}">
			${categoryFilterData.category.name}
		</g:if>

	</title>
	<asset:stylesheet src="front_not_main.css"/>
	<asset:javascript src="front_not_main.js"></asset:javascript>



</head>

<body>

<div class="row">
	<div class="col-md-3 col-lg-3 thisblock" style="position: fixed;left: -3px;z-index: 100">

		<div class=left_block>
			<div class="head" style="text-align: center">Категории партнеров</div>



			<div class=category>



				<g:render template="/_common/category-filter" model="[
						categoryFilterData: categoryFilterData
				]"/>

			</div>

		</div>

	</div>

	<div class="col-md-6 col-lg-6 ndblock" style="position: relative;left: 25%;min-height: 700px">

		<g:if test="${categoryFilterData.category}">
			<h1 class="text-center">${categoryFilterData.category.name}</h1>
			<g:render template="/_common/category-breadcrumb" model="[
					categoryFilterData: categoryFilterData
			]"/>

			<g:each in="${accountInstanceList}" status="i" var="obj">
				<g:render template="item" model="[accountInstance: obj]"/>
			</g:each>

			<g:if test="${params.max < rowsCount}">
				<g:paginate total="${rowsCount ?: 0}"/>
			</g:if>

		</g:if>
		<g:else>
			<h1 style="padding-bottom: 15px" class="text-center">Предприятия</h1>

				<g:each in="${lastCreated}" var="obj">

						<g:render template="item" model="[accountInstance: obj]"/>




				</g:each>

			<g:if test="${params.max < rowsCount}">
				<g:paginate total="${rowsCount ?: 0}"/>
			</g:if>
		</g:else>

	</div>

	<div class="col-md-3 col-lg-3 thisblock" style="position: fixed;right: -5px;z-index: 100">


		<div class=left_block style="padding-bottom: 40px">
			<div class="head"  style="text-align: center;">Надежные партнеры</div>

<g:if test="${mainAccounts.size()>3}">
	<g:if test="${!mainAccounts.isEmpty()}">


		<ul class="Accounts" style="padding: 0;">


			<g:each in="${mainAccounts}" var="accountInstance">
				<li class="tablettt" style="width:50%;font-size: 90%;text-align: center;box-shadow: 0 0 10px #c8c8c8; border-radius: 10px;">

					<g:link url="[controller: 'company', id: accountInstance.id]">
						<g:if test="${accountInstance.logoThumb}">

							<img class="img-thumbnail" style="border: none;max-width: 161px;max-height: 161px"
								 src="${createLink(controller: 'files', action: 'logo', id: accountInstance.logoThumb?.id, params: [name: accountInstance.logoThumb?.file])}"/>




						</g:if>

						<g:else>
							<img class="img-thumbnail" src="${resource(dir: 'images', file: 'front/logo_uch.png')}" style="border: none;max-width: 178px;max-height: 161px;margin: 3% 0 3% 0"/>
						</g:else>


						<div align="center">${accountInstance.name}</div>

					</g:link>

				</li>
			</g:each>
		</ul>

	</g:if>
	<script>
		$(document).ready(function(){
			var windows =$(window).height();
			if($(window).height()>540){
				$('.Accounts').bxSlider({
					mode:'vertical',
					minSlides: 3,
					moveSlides:1,
					maxSlides: 20,
					slideWidth: 210,
					slideMargin: 10,
					auto: true,
					pause: 0,
					speed: 4500,

					autoHover:true
				});
			}else {
				$('.Accounts').bxSlider({
					mode:'vertical',
					minSlides: 2,
					moveSlides:1,
					maxSlides: 20,
					slideWidth: 210,
					slideMargin: 10,
					auto: true,
					pause: 0,
					speed: 4500,

					autoHover:true
				});
			}
		})

	</script>
</g:if>
			<g:else>
				<g:if test="${!mainAccounts.isEmpty()}">


					<ul class="GoodsMain" style="padding: 0;">


						<g:each in="${mainAccounts}" var="accountInstance">
							<li class="tablet" style="width:60%;font-size: 90%;min-width: 182px">

								<g:link url="[controller: 'company', id: accountInstance.id]">
									<g:if test="${accountInstance.logoThumb}">

										<img class="img-thumbnail" style="border: none;max-width: 161px;max-height: 161px"
											 src="${createLink(controller: 'files', action: 'logo', id: accountInstance.logoThumb?.id, params: [name: accountInstance.logoThumb?.file])}"/>




									</g:if>

									<g:else>
										<img class="img-thumbnail" src="${resource(dir: 'images', file: 'front/logo_uch.png')}" style="border: none;max-width: 178px;max-height: 161px;margin: 3% 0 3% 0"/>
									</g:else>


									<div align="center">${accountInstance.name}</div>

								</g:link>

							</li>
						</g:each>
					</ul>

				</g:if>
			</g:else>

			</div>
	</div>

</div>

<g:render template="/_common/modal" model="[
		container: 'reviewsContainer',
		modalId  : 'reviewsModal'
]"/>

<a href="#" style="opacity: 0" class="u"><img src="${resource(dir: 'images', file: 'front/Arrow.png')}" style="width: 20%;min-width: 45px"/></a>

</body>
</html>