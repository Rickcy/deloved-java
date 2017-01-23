<%@ page import="org.codehaus.groovy.grails.plugins.jquery.JQueryConfig; ru.deloved.Account" %>
<%@ page import="ru.deloved.CategoryType" %>
<%@ page import="ru.deloved.Category" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title><g:message code="account.show.label"/></title>
	<script src="//api-maps.yandex.ru/2.0-stable/?load=package.standard&lang=ru-RU" type="text/javascript"></script>
</head>

<body>

<style>
	.form-group {
		margin-bottom: 5px;
	}
</style>


<div id="show-account" class="content scaffold-show" role="main">

	<h1><g:message code="account.show.label"/></h1>

	<g:render template="/_common/flash-message"/>



	<div class="profile-info">

	<!-- Начало общей информации -->

		<h2>Общая информация</h2>

		<div class="row">
			<div class="label-col">
				<label for="id">ID в системе</label>
			</div>
			<div class="value-col">
				<span id="id" name="id">${accountInstance.id}</span>
			</div>
			<div class="action-col">

			</div>
		</div>

		<div class="row">
			<div class="label-col">
				<label for="orgForm">Организационно-правовая форма</label>
			</div>
			<div class="value-col">
				<span id="orgForm" name="orgForm">${accountInstance.orgForm.name}</span>
			</div>
			<div class="action-col">

			</div>
		</div>

		<div class="row">
			<div class="label-col">
				<label>Полное наименование</label>
			</div>
			<div class="value-col">
				<span>${accountInstance.fullName}</span>
			</div>
			<div class="action-col">

			</div>
		</div>

		<div class="row">
			<div class="label-col">
				<label>Краткое наименование</label>
			</div>
			<div class="value-col">
				<span>${accountInstance.name}</span>
			</div>
			<div class="action-col">

			</div>
		</div>

		<div class="row">
			<div class="label-col">
				<label>Фирменное наименование</label>
			</div>
			<div class="value-col">
				<span>${accountInstance.brandName}</span>
			</div>
			<div class="action-col">

			</div>
		</div>

		<div class="row">
			<div class="label-col">
				<label>Логотип</label>
			</div>
			<div class="value-col">
				<g:render template="/_common/upload-single-image" model="[
						'isUpload'    : accountInstance?.logo != null,
						'imageUrl'    : createLink(controller: 'account', action: 'logo', id: accountInstance?.id, params: [name: accountInstance?.logo?.file, type: 'main']),
						'thumbUrl'    : createLink(controller: 'account', action: 'logo', id: accountInstance?.id, params: [name: accountInstance?.logoThumb?.file]),
						'uploadAction': createLink(controller: 'account', action: 'upload', id: accountInstance?.id),
						'cropAction'  : createLink(controller: 'account', action: 'crop', id: accountInstance?.id),
						'deleteAction': 'deletelogo',
						'imgId'       : 'logo',
						'imgTitle'    : 'Логотип',
						'imgWidth'    : 100,
						'imgHeight'   : 100
				]"/>
			</div>
			<div class="action-col">

			</div>
		</div>

		<div class="row">
			<div class="label-col">
				<label>Деловая репутация</label>
			</div>
			<div class="value-col">
				<span>${accountInstance.rating}%</span>
			</div>
			<div class="action-col">
				<a href="javascript:void(0)">Отзывы</a>
			</div>
		</div>
	<!-- Конец общей информации -->


	<!-- Начало юридической информации -->

		<h2>Юридическая информация</h2>

		<div class="row">
			<div class="label-col">
				<label>ИНН</label>
			</div>
			<div class="value-col">
				<span>${accountInstance.inn}</span>
			</div>
			<div class="action-col">

			</div>
		</div>

		<div class="row">
			<div class="label-col">
				<label>ОГРН</label>
			</div>
			<div class="value-col">
				<span>${accountInstance.regNumber}</span>
			</div>
			<div class="action-col">

			</div>
		</div>

		<g:if test="${accountInstance.kpp}">
			<div class="row">
				<div class="label-col">
					<label>КПП</label>
				</div>
				<div class="value-col">
					<span>${accountInstance.kpp}</span>
				</div>
				<div class="action-col">
				</div>
			</div>
		</g:if>


		<div class="row">
			<div class="label-col">
				<label>Юридический адрес</label>
			</div>
			<div class="value-col">
				<span>${accountInstance.legalAddress}</span>
			</div>
			<div class="action-col">

			</div>
		</div>

		<div class="row">
			<div class="label-col">
				<label>Дата регистрации</label>
			</div>
			<div class="value-col">
				<span><g:formatDate date="${accountInstance.regDate}" format="dd.MM.yyyy"/></span>
			</div>
			<div class="action-col">

			</div>
		</div>

		<div class="row">
			<div class="label-col">
				<label>Управление</label>
			</div>
			<div class="value-col">
				<span>${accountInstance.manager}</span>
			</div>
			<div class="action-col">

			</div>
		</div>

	<!-- Конец юридической информации -->

	<!-- Начало контактной информации -->

		<h2>Контактная информация</h2>

		<div class="row">
			<div class="label-col">
				<label for="city">Фактический город</label>
			</div>
			<div class="value-col">
				<input id="city" name="city" class="form-control" type="text" readonly value="${accountInstance?.city.name ?: 'отсутсвует'}" data-old="${accountInstance?.city.name ?: 'отсутсвует'}"/>
			</div>
			<div class="action-col">
				<a href="javascript:void(0)" name="change" data-for="city">Изменить</a>
			</div>
		</div>

		<div class="row">
			<div class="label-col">
				<label for="address">Фактический адрес</label>
			</div>
			<div class="value-col">
				<input id="address" name="address" type="text" readonly value="${accountInstance.address ?: 'отсутсвует'}" data-old="${accountInstance.address ?: 'отсутсвует'}"/>
			</div>
			<div class="action-col">
				<a href="javascript:void(0)" name="change" data-for="address">Изменить</a>
				<a href="javascript:void(0)">Показать на карте</a>
			</div>
		</div>

		<script type="application/javascript">
			$(function () {
				$("#city").autocomplete({
					source: "${createLink(controller: 'profile', action: 'cities')}",
					minLength: 3
				});
			});

			var myMap = null;
			var myPlacemark = null;

			function showAddres(city, addr) {
				if (myMap) {
					if (addr != "") {
						ymaps.geocode(city + ", " + addr, {results: 1}).then(function (resAddr) {
							var addrGeoObject = resAddr.geoObjects.get(0);
							if (myPlacemark) {
								myMap.geoObjects.remove(myPlacemark);
							}
							myPlacemark = new ymaps.Placemark(addrGeoObject.geometry.getCoordinates(), {
								hintContent: addr
							});
							myMap.geoObjects.add(myPlacemark);
							myMap.setCenter(addrGeoObject.geometry.getCoordinates(), 15);

						}, function (err) {
							console.error(err.message);
						});
					}
				} else {
					if (city != "") {
						ymaps.geocode(city + (addr != "" ? (", " + addr) : ""), {results: 1}).then(function (res) {
							// Выбираем первый результат геокодирования
							var firstGeoObject = res.geoObjects.get(0);
							myMap = new ymaps.Map("map", {
								center: firstGeoObject.geometry.getCoordinates(),
								zoom: addr != "" ? 15 : 13
							});
							if (addr != "") {
								if (myPlacemark) {
									myMap.geoObjects.remove(myPlacemark);
								}
								myPlacemark = new ymaps.Placemark(firstGeoObject.geometry.getCoordinates(), {
									hintContent: addr
								});
								myMap.geoObjects.add(myPlacemark);
							}
							myMap.controls.add(new ymaps.control.ZoomControl());

							$("#mapToolBar").show();
						}, function (err) {
							console.error(err.message);
						});
					}
				}
			}


			ymaps.ready(function () {
				showAddres('${accountInstance?.city?.name}', '${accountInstance?.address}');
			});

			function mapToolBarShow() {
				$("#ancorShow").hide();
				$("#ancorHide").show();
				$("#map").show();
			}
			function mapToolBarHide() {
				$("#ancorShow").show();
				$("#ancorHide").hide();
				$("#map").hide();
			}
		</script>

		<div id="mapToolBar" style="display:block;">
			<a id="ancorShow" href="#" onclick="mapToolBarShow();return false;">показать карту</a>
			<a id="ancorHide" href="#" onclick="mapToolBarHide();return false;" style="display:none;">скрыть карту</a>
			<div id="map" style="width:500px; height:500px; display: none;"></div>
		</div>

		<div class="row">
			<div class="label-col">
				<label for="email">Email</label>
			</div>
			<div class="value-col">
				<input id="email" name="email" type="text" readonly value="${accountInstance.email ?: 'отсутсвует'}" data-old="${accountInstance.email ?: 'отсутсвует'}"/>
			</div>
			<div class="action-col">
				<a href="javascript:void(0)" name="change" data-for="email">Изменить</a>
			</div>
		</div>

		<div class="row">
			<div class="label-col">
				<label for="webAddress">Сайт</label>
			</div>
			<div class="value-col">
				<input id="webAddress" name="webAddress" type="text" readonly value="${accountInstance.webAddress ?: 'отсутсвует'}" data-old="${accountInstance.webAddress ?: 'отсутсвует'}"/>
			</div>
			<div class="action-col">
				<a href="javascript:void(0)" name="change" data-for="webAddress">Изменить</a>
			</div>
		</div>



		<div class="row">
			<div class="label-col">
				<label for="phone1">Номер телефона №1</label>
			</div>
			<div class="value-col">
				<input id="phone1" name="phone1" type="text" readonly value="${accountInstance.phone1 ?: 'отсутсвует'}" data-old="${accountInstance.phone1 ?: 'отсутсвует'}"/>
			</div>
			<div class="action-col">
				<a href="javascript:void(0)" name="change" data-for="phone1">Изменить</a>
			</div>
		</div>

		<div class="row">
			<div class="label-col">
				<label for="phone2">Номер телефона №2</label>
			</div>
			<div class="value-col">
				<input id="phone2" name="phone2" type="text" readonly value="${accountInstance.phone2 ?: 'отсутсвует'}" data-old="${accountInstance.phone2 ?: 'отсутсвует'}"/>
			</div>
			<div class="action-col">
				<a href="javascript:void(0)" name="change" data-for="phone2">Изменить</a>
			</div>
		</div>

		<div class="row">
			<div class="label-col">
				<label for="phone3">Номер телефона №3</label>
			</div>
			<div class="value-col">
				<input id="phone3" name="phone3" type="text" readonly value="${accountInstance.phone3 ?: 'отсутсвует'}" data-old="${accountInstance.phone3 ?: 'отсутсвует'}"/>
			</div>
			<div class="action-col">
				<a href="javascript:void(0)" name="change" data-for="phone3">Изменить</a>
			</div>
		</div>

		<div class="row">
			<div class="label-col">
				<label for="fax1">Факс №1</label>
			</div>
			<div class="value-col">
				<input id="fax1" name="fax1" type="text" readonly value="${accountInstance.fax1 ?: 'отсутсвует'}" data-old="${accountInstance.fax1 ?: 'отсутсвует'}"/>
			</div>
			<div class="action-col">
				<a href="javascript:void(0)" name="change" data-for="fax1">Изменить</a>
			</div>
		</div>

		<div class="row">
			<div class="label-col">
				<label for="fax2">Факс №2</label>
			</div>
			<div class="value-col">
				<input id="fax2" name="fax2" type="text" readonly value="${accountInstance.fax2 ?: 'отсутсвует'}" data-old="${accountInstance.fax2 ?: 'отсутсвует'}"/>
			</div>
			<div class="action-col">
				<a href="javascript:void(0)" name="change" data-for="fax2">Изменить</a>
			</div>
		</div>

	<!-- Конец контактной информации -->

	<!-- Начало допольнительной информации -->

   		<h2>Дополнительная информация</h2>

		<div class="row">
			<div class="label-col">
				<label for="workTime">Время работы</label>
			</div>
			<div class="value-col">
				<input id="workTime" name="workTime" type="text" readonly value="${accountInstance.workTime ?: 'отсутсвует'}" data-old="${accountInstance.workTime ?: 'отсутсвует'}"/>
			</div>
			<div class="action-col">
				<a href="javascript:void(0)" name="change" data-for="workTime">Изменить</a>
			</div>
		</div>

		<div class="row">
			<div class="label-col">
				<label for="dateCreated">Дата добавления</label>
			</div>
			<div class="value-col">
				<span id="dateCreated" name="dateCreated"><g:formatDate date="${accountInstance.dateCreated ?: 'отсутсвует'}" format="dd.MM.yyyy"/></span>
			</div>
			<div class="action-col">

			</div>
		</div>

		<div class="row">
			<div class="label-col">
				<label for="lastUpdated">Дата последнего обновления</label>
			</div>
			<div class="value-col">
				<span id="lastUpdated" name="lastUpdated"><g:formatDate date="${accountInstance.lastUpdated ?: 'отсутсвует'}" format="dd.MM.yyyy"/></div>
			<div class="action-col">

			</div>
		</div>

		<div class="row">
			<div class="label-col">
				<label for="description">Описание</label>
			</div>
			<div class="value-col">
				<textarea id="description" name="description" type="text" readonly data-old="${accountInstance.description ?: 'отсутсвует'}
				" style="width: 100%; height: 170px; border-radius: 4px">${accountInstance.description ?: 'отсутсвует'}
				</textarea>
			</div>
			<div class="action-col">
				<a href="javascript:void(0)" name="change" data-for="description">Изменить</a>
			</div>
		</div>

		<div class="row">
			<div class="label-col">
				<label for="keywords">Ключевые слова</label>
			</div>
			<div class="value-col">
				<textarea id="keywords" name="keywords" type="text" readonly data-old="${accountInstance.keywords ?: 'отсутсвует'}
				" style="width: 100%; height: 170px; border-radius: 4px">${accountInstance.keywords ?: 'отсутсвует'}
				</textarea>
			</div>
			<div class="action-col">
				<a href="javascript:void(0)" name="change" data-for="keywords">Изменить</a>
			</div>
		</div>


		<div class="row">
			<div class="label-col">
				<label>Филиалы</label>
			</div>
			<div class="value-col">
				<g:formRemote id="affiliateList" name="affiliateList" url="[action: 'updateAffiliates', id: accountInstance.id]"
					onSuccess="alert(data)" onFailure="alert('Невернные данные!')">

					<g:each in="${accountInstance.affiliates}" var="affiliateInstance" status="i">
						<g:render template="affiliate" model="[affiliateInstance: affiliateInstance, i: i]"/>
 					</g:each>

				</g:formRemote>
			</div>
			<div class="action-col">

				<g:remoteLink action="addAffiliate" onSuccess="pushAffiliate(data)" params="affiliateBlockCount()"><span class="glyphicon glyphicon-plus"></span>Добавить</g:remoteLink>
				<br>
				<a href="javascript:void(0)" onclick="$('#affiliateList').submit()"><span class="glyphicon glyphicon-save"></span> Сохранить изменения</a>
			</div>
		</div>

				<script>
					function affiliateBlockCount(){
						return {index: $('[name=affiliateBlock]').length}
					}

					function pushAffiliate(data) {
						$(data).appendTo('#affiliateList');
					}
				</script>


		<g:set var="typeList" value="${ru.deloved.CategoryType.listOrderById()}"/>



		<div class="row">
			<div class="label-col">
				<label>Категории деятельности</label>
			</div>
			<div class="value-col">
				<div class="tab-pane" id="cat">

					<ul class="nav nav-tabs">
						<g:each in="${typeList}" status="i" var="typeInstance">
							<li class="${i == 0 ? ' active' : ''}"><a href="#${typeInstance.code}" data-toggle="tab"><g:message code="categorytype.${typeInstance.code}"/></a></li>
						</g:each>
					</ul>

					<div class="tab-content" style="margin-top: 0">
						<g:each in="${typeList}" status="i" var="typeInstance">
							<div class="tab-pane ${i == 0 ? 'active' : ''}" id="${typeInstance.code}">
								<div id="${typeInstance.code}tree">
								</div>
							</div>
						</g:each>
					</div>
				</div>



				<script type="application/javascript">
					jQuery(function () {


						$('#updateCat').click(function(){

							var form = $('#catForm')
							var categories = ""
							<g:each in="${typeList}" status="i" var="typeInstance">
							jQuery.each(jQuery('#${typeInstance.code}tree').jstree(true).get_selected(), function (i, v) {

									categories = categories + v + '&'

							});
							</g:each>



							$.ajax({
								type: 'POST',
								url: '${createLink(controller: 'account', action: 'editNew', id: accountInstance.id)}',
								data: {prop: 'categories', value: categories},
								beforeSend: function() {},
								complete: function (textStatus) {
									form.html('')
								},
								success: function (data, textStatus) {

								},
								error: function (XMLHttpRequest, textStatus, errorThrown) {
								}
							})
						});

						<g:each in="${typeList}" status="i" var="typeInstance">
						jQuery('#${typeInstance.code}tree')
								.on('changed.jstree', function (e, data) {
									if (data.node) {
										if (!data.instance.is_leaf(data.node)) {
											data.instance.deselect_node(data.node.children_d, true);
											data.instance.deselect_node(data.node.parents, true);
											if (data.instance.is_selected(data.node)) {
												data.instance.close_node(data.node);
											} else {
												data.instance.open_node(data.node);
											}
										} else {
											data.instance.deselect_node(data.node.parents, true);
										}
									}
								})
								.jstree({
									"core": {
										"multiple": true,
										"data": {
											"url": "${createLink(resource:accountInstance, action: 'cat')}",
											"data": function (n) {
												return {
													"pid": n.id && n.id != '#' ? n.id : ${ Category.findByTypeAndParent(typeInstance, null).id }
												}
											}
										}
									},
									"checkbox": {
										"three_state": false,
										"cascade": "undetermined"
									},
									"plugins": ["json_data", "checkbox", "wholerow"]
								});
						</g:each>
					});
				</script>
			</div>

			<div class="action-col">
				<a href="javascript:void(0)" id="updateCat" name="updateCat">Сохранить изменения</a>
			</div>
		</div>


	<!-- Конец допольнительной информации -->

</div>
	<script>
		$(document).ready(function() {
			$('[name=change]').click(function(e) {

				//Получаем элемент который вызвал событие
				var el = e.target || e.srcElement;

				//Получаем значение поля data-for элемента который вызвал событие
				var prop = $(el).data('for');

				//Нажимаем изменить
				if ($('#'+prop).is('[readonly]')) {
					$('#'+prop).attr('readonly', false);
					$(el).html('Сохранить');

					//Нажимает сохранить
				} else {

					var oldValue = $('#'+prop).data('old');

					var newValue = $('#'+prop).val();

					if (oldValue != newValue) {
						$.ajax({
							type: 'POST',
							url: '${createLink(controller: 'account', action: 'editNew', id: accountInstance.id)}',
							data: {prop: prop, value: newValue},
							beforeSend: function() {
								$('#'+prop+'spinner').show();
							},
							complete: function (textStatus) {
								$('#'+prop+'spinner').hide();
								if (textStatus.status) {

								} else {

								}
							},
							success: function (data, textStatus) {
								if (data.status == 'success') {
									$('#'+prop).data('old', newValue);

								} else {
									$('#'+prop).val(oldValue);
								}
								showMessage(data.status, data.text)

							},
							error: function (XMLHttpRequest, textStatus, errorThrown) {
								showMessage('danger', 'Ошибка соединения')
							}

						})
					}
					$('#'+prop).attr('readonly', true);
					$(el).html('Изменить');
				}
			});
		});

	</script>

</div>

<g:render template="/_common/crop"/>
<g:render template="/_common/gallery-single"/>
</body>
</html>
