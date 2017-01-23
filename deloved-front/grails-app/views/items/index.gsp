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
	<title>Goods</title>
	<style>
	.hiddencat {
		display: none;
	}
	</style>
</head>

<body>

<div class="row">
	<div class="col-md-3">



		<div class=left_block>
			<div class=head>Каталог товаров</div>
			<div class=geo>
				<h3>Выберите регион</h3>


				<g:render template="/_common/region-filter" model="[
						regionFilterData: regionFilterData
				]"/>

			</div>

			<div class=category>
				<h3>Категория</h3>


				<g:render template="/_common/category-filter" model="[
						categoryFilterData: categoryFilterData
				]"/>


			</div>



		</div>
	</div>

	<div class="col-md-6">

		<g:if test="${categoryFilterData.company}">
			Товары предприятия <g:link url="[controller: 'company', id: categoryFilterData.company.id]">${categoryFilterData.company.name}</g:link>
		</g:if>

		<g:if test="${categoryFilterData.category}">

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

			<g:render template="/_common/category-catalog" model="[
					categoryFilterData: categoryFilterData
			]"/>

		</g:else>

	</div>

	<div class="col-md-3">
		Последние добавленные
		<ul>
			<g:each in="${lastCreated}" var="obj">
				<li>
					<div class="itemlogo">
						<g:if test="${obj?.photo}">
							<img class="img-thumbnail"
								 src="${createLink(controller: 'files', action: 'item', id: obj.photo?.imageThumb.id, params: [name: obj.photo?.imageThumb.file])}"/>
						</g:if>
					</div>

					<g:link controller="items" action="item" id="${obj.id}">
						<g:fieldValue bean="${obj}" field="name"/>
					</g:link>

					<div class=price><g:formatNumber number="${obj?.price}" type="currency" currencyCode="${obj?.currency.code}"/> / ${obj?.measure?.name}</div>

				</li>
			</g:each>
		</ul>
	</div>

</div>
<g:render template="/_common/gallery-multi"/>








</body>
</html>