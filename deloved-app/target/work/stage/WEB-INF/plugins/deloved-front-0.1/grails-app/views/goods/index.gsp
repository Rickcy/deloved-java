<%--
  Created by IntelliJ IDEA.
  User: Андрейка
  Date: 23.10.2014
  Time: 0:27
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>

	<meta name="layout" content="front">
	<meta name="keywords" content="${categoryFilterData?.category?.name}">
	<meta name="description" content="${categoryFilterData?.category?.name}">
	<title>
	Товары
<g:if test="${categoryFilterData.category}">
	${categoryFilterData?.category.name}
</g:if>




		</title>
	<asset:stylesheet src="front_not_main.css"/>
	<asset:javascript src="front_not_main.js"></asset:javascript>
	<asset:javascript src="goodInd.js"></asset:javascript>

</head>

<body>

<div class="row">
	<div class="col-md-3 col-lg-3 thisblock" style="left: -3px;;position: fixed;z-index: 100">



		<div class=left_block>
			<div class=head style="text-align: center">Категории товаров</div>


			<div class="category">



				<g:render template="/_common/category-filter" model="[
						categoryFilterData: categoryFilterData
				]"/>


			</div>



		</div>
	</div>

	<div class="col-md-6 col-lg-6 ndblock" style="position: relative;left: 25%;min-height: 700px">

		<g:if test="${categoryFilterData.company}">
			Товары предприятия <g:link url="[controller: 'company', id: categoryFilterData.company.id]">${categoryFilterData.company.name}</g:link>

		</g:if>

		<g:if test="${categoryFilterData.category}">
			<h1 class="text-center">${categoryFilterData.category.name}</h1>
			<g:render template="/_common/category-breadcrumb" model="[
					categoryFilterData: categoryFilterData
			]"/>

			<g:render template="/_common/flash-message"/>


			<g:each in="${itemInstanceList}" status="i" var="obj">
				<g:render template="item" model="[itemInstance: obj]"/>
			</g:each>

			<g:if test="${params.max < rowsCount}">
				<g:paginate total="${rowsCount ?: 0}"/>
			</g:if>

		</g:if>
		<g:else>
				<h1 style="padding-bottom: 15px" class="text-center">Товары</h1>
			<g:each in="${lastCreated}" var="obj">
				<g:render template="item" model="[itemInstance: obj]"/>

			</g:each>
			<g:if test="${params.max < rowsCount}">
				<g:paginate total="${rowsCount ?: 0}"/>
			</g:if>
		</g:else>

	</div>

	<div class="col-md-3 col-lg-3 thisblock" style="position: fixed;right:-5px;z-index: 100">



		<div class=left_block style="padding-bottom: 40px">
			<div class="head"  style="text-align: center;">Выгодные предложения</div>
<g:if test="${mainGoods.size()>2}">
	<g:if test="${!mainGoods.isEmpty()}">


		<ul class="GoodsMain" style="padding: 0">

			<g:each in="${mainGoods}" var="item">
				<li class="tablettt" style="width:50%;font-size: 90%;text-align: center;box-shadow: 0 0 10px #c8c8c8; border-radius: 10px;">

						<g:link url="[controller: 'goods', action: 'item', id: item.id]">
							<g:if test="${item?.photo}">
								<img class="img-thumbnail" style="border: none;width: 188px;max-height: 171px"
									 src="${createLink(controller: 'files', action: 'item', id: item.photo?.imageThumb.id, params: [name: item.photo?.imageThumb.file])}"/>
							</g:if>

							<g:else>
								<img class="img-thumbnail" src="${resource(dir: 'images', file: 'front/goods.png')}" style="border: none;width: 188px;max-height: 171px"/>
							</g:else>
							<br>
							<span>${item.name ?: ''}<br>
								${item.price}
								${item.currency.name}/${item.measure.fullname}

							</span>
						</g:link>



				</li>
			</g:each>

		</ul>
		<script>


			$('.GoodsMain').bxSlider({
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




		</script>
	</g:if>


</g:if>
			<g:else>
				<g:if test="${!mainGoods.isEmpty()}">


					<ul class="GoodsMain" style="padding: 0">

						<g:each in="${mainGoods}" var="item">
								<li class="tablet" style="width:60%;font-size: 90%;">

									<g:link url="[controller: 'goods', action: 'item', id: item.id]">
										<g:if test="${item?.photo}">
											<img class="img-thumbnail" style="border: none;width: 171px;max-height: 171px"
												 src="${createLink(controller: 'files', action: 'item', id: item.photo?.imageThumb.id, params: [name: item.photo?.imageThumb.file])}"/>
										</g:if>

										<g:else>
											<img class="img-thumbnail" src="${resource(dir: 'images', file: 'front/goods.png')}" style="border: none;width: 188px;max-height: 171px"/>
										</g:else>
										<br>
										<span>${item.name ?: ''}<br>
											${item.price}
											${item.currency.name}/${item.measure.fullname}

										</span>
									</g:link>


							</li>
						</g:each>

					</ul>

				</g:if>
			</g:else>

		</div>
	</div>

</div>
<g:render template="/_common/gallery-multi"/>






<a href="#" style="opacity: 0" class="u"><img src="${resource(dir: 'images', file: 'front/Arrow.png')}" style="width: 20%;min-width: 45px"/></a>

</body>
</html>