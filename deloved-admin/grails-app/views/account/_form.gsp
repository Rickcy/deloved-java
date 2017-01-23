<%@ page import="ru.deloved.CategoryType" %>
<%@ page import="ru.deloved.Category" %>
<%@ page import="ru.deloved.Account" %>

<div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#main" data-toggle="tab"><g:message code="account.tab.main"/></a></li>
		<li><a href="#contacts" data-toggle="tab"><g:message code="account.tab.contacts"/></a></li>
		<li><a href="#affiliates" data-toggle="tab"><g:message code="account.tab.affiliate"/></a></li>
		<li><a href="#seo" data-toggle="tab"><g:message code="account.tab.seo"/></a></li>
		<li><a href="#cat" data-toggle="tab"><g:message code="account.tab.category"/></a></li>

	</ul>

	<div class="tab-content">
		<div class="tab-pane active" id="main">

			<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
				<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'publicStatus', 'error')}">
					<label class="control-label col-sm-3" for="publicStatus">
						<g:message code="account.publicStatus.label" default="Public status"/>
					</label>

					<div class="col-sm-9">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-default <g:if test="${objInstance?.publicStatus == false}">active</g:if>">
								<g:radio name="publicStatus" value="false" checked="${objInstance?.publicStatus == false}"/>OFF
							</label>
							<label class="btn btn-default <g:if test="${objInstance?.publicStatus == true}">active</g:if>">
								<g:radio name="publicStatus" value="true" checked="${objInstance?.publicStatus == true}"/>ON
							</label>
						</div>
					</div>
				</div>

				<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'verifyStatus', 'error')}">
					<label class="control-label col-sm-3" for="verifyStatus">
						<g:message code="account.verifyStatus.label" default="Verify status"/>
					</label>

					<div class="col-sm-9">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-default <g:if test="${objInstance?.verifyStatus == false}">active</g:if>">
								<g:radio name="verifyStatus" value="false" checked="${objInstance?.verifyStatus == false}"/>NO
							</label>
							<label class="btn btn-default <g:if test="${objInstance?.verifyStatus == true}">active</g:if>">
								<g:radio name="verifyStatus" value="true" checked="${objInstance?.verifyStatus == true}"/>YES
							</label>
						</div>
					</div>
				</div>

				<div class="fieldcontain form-group">
					<label class="control-label col-sm-3" for="profilefio">
						<g:message code="profile.label"/>
					</label>

					<div class="col-sm-9">

						<g:render template="/_common/auto-complete" model="[
								acAction   : createLink(resource: beanResource, action: 'profiles'),
								acMinLength: 4,
								acKeyValue : profile?.id,
								acKeyName  : 'profileid',
								acViewValue: profile?.fio,
								acViewName : 'profilefio'
						]"/>

					</div>
				</div>
				<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'showMain', 'error')}">
					<label class="control-label col-sm-3" for="showMain">
						<g:message code="account.showMain.label" default="Show on main"/>
					</label>

					<div class="col-sm-9">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-default <g:if test="${objInstance?.showMain == false}">active</g:if>">
								<g:radio name="showMain" value="false" checked="${objInstance?.showMain == false}"/>OFF
							</label>
							<label class="btn btn-default <g:if test="${objInstance?.showMain == true}">active</g:if>">
								<g:radio name="showMain" value="true" checked="${objInstance?.showMain == true}"/>ON
							</label>
						</div>
					</div>
				</div>

			</sec:ifAnyGranted>


			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'name', 'error')} required">
				<label class="control-label col-sm-3" for="name">
					<g:message code="account.name.label" default="Name"/>
					<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
				</label>

				<div class="col-sm-9">
					<g:textField class="form-control" name="name" value="${objInstance?.name}"/>
					<g:render template="/_common/error" model="[objInstance: objInstance, field: 'name']"/>
					<div class="pods"></div>
				</div>
			</div>

			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'fullName', 'error')} required">
				<label class="control-label col-sm-3" for="fullName">
					<g:message code="account.fullName.label" default="Full name"/>
					<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
				</label>

				<div class="col-sm-9"><g:textField class="form-control" name="fullName" value="${objInstance?.fullName}"/></div>
			</div>

			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'brandName', 'error')} required">
				<label class="control-label col-sm-3" for="brandName">
					<g:message code="account.brandName.label" default="Brand name"/>
					<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
				</label>

				<div class="col-sm-9"><g:textField class="form-control" name="brandName" value="${objInstance?.brandName}"/></div>
			</div>

			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'orgForm', 'error')} required">
				<label class="control-label col-sm-3" for="orgForm">
					<g:message code="account.orgForm.label" default="Org Form"/>
					<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
				</label>

				<div class="col-sm-9">
					<g:select class="form-control" name="orgForm" from="${ru.deloved.OrgForm.listOrderByCode()}" optionKey="id" optionValue="code"
							  value="${objInstance?.orgForm?.id}"
							  noSelection="${['null': message(code: 'default.selection.notSelected')]}"/>
				</div>
			</div>

			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'regNumber', 'error')} required">
				<label class="control-label col-sm-3" for="regNumber">
					<g:message code="account.regNumber.label" default="Reg Number"/>
					<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
				</label>

				<div class="col-sm-9"><g:textField class="form-control" name="regNumber" value="${objInstance?.regNumber}"/></div>
			</div>

			<div class="fieldcontain  form-group ${hasErrors(bean: objInstance, field: 'inn', 'error')} required">
				<label class="control-label col-sm-3" for="inn">
					<g:message code="account.inn.label" default="Inn"/>
					<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
				</label>

				<div class="col-sm-9"><g:textField class="form-control" name="inn" value="${objInstance?.inn}"/></div>
			</div>

			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'kpp', 'error')} ">
				<label class="control-label col-sm-3" for="kpp">
					<g:message code="account.kpp.label" default="Kpp"/>
				</label>

				<div class="col-sm-9"><g:textField class="form-control" name="kpp" value="${objInstance?.kpp}"/></div>
			</div>

			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'legalAddress', 'error')} required">
				<label class="control-label col-sm-3" for="legalAddress">
					<g:message code="account.legalAddress.label" default="Legal Address"/>
					<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
				</label>

				<div class="col-sm-9"><g:textField class="form-control" name="legalAddress" value="${objInstance?.legalAddress}"/></div>
			</div>

			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'regDate', 'error')} required">
				<label class="control-label col-sm-3" for="regDate">
					<g:message code="account.regDate.label" default="Reg Date"/>
					<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
				</label>

				<div class="col-sm-9"><g:datePicker name="regDate" precision="day" value="${objInstance?.regDate}"/></div>
			</div>

			<g:if test="${beanResource?.id}">
				<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'logo', 'error')} ">
					<label class="col-sm-3 control-label">
						<g:message code="profile.avatar.label" default="Avatar"/>
					</label>

					<div class="col-sm-9">

						<g:render template="/_common/upload-single-image" model="[
								'isUpload'    : beanResource?.logo != null,
								'imageUrl'    : createLink(controller: 'account', action: 'logo', id: beanResource?.id, params: [name: beanResource?.logo?.file, type: 'main']),
								'thumbUrl'    : createLink(controller: 'account', action: 'logo', id: beanResource?.id, params: [name: beanResource?.logoThumb?.file]),
								'uploadAction': createLink(controller: 'account', action: 'upload', id: beanResource?.id),
								'cropAction'  : createLink(controller: 'account', action: 'crop', id: beanResource?.id),
								'deleteAction': 'deletelogo',
								'imgId'       : 'logo',
								'imgTitle'    : 'Логотип',
								'imgWidth'    : 100,
								'imgHeight'   : 100
						]"/>

					</div>
				</div>
			</g:if>
		</div>

		<div class="tab-pane" id="contacts">

			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'city', 'error')} required">
				<label class="control-label col-sm-3" for="cityname">
					<g:message code="account.city.label" default="City"/>
					<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
				</label>

				<div class="col-sm-9">

					<g:hiddenField name="city.id" value="${objInstance?.city?.id}"/>
					<g:hiddenField name="cityname2" value="${objInstance?.city?.name}"/>
					<g:textField class="form-control" name="cityname" value="${objInstance?.city?.name}"/>

				</div>
			</div>

			<script type="application/javascript">
				var myMap = null;
				var myPlacemark = null;
				function showAddres(city, addr) {
					//console.log(city + "," + addr);
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
				var addrTO = null;
				var lastAddr = "";
				function addressUpdate(addr) {
					clearTimeout(addrTO);
					addrTO = null;
					var addr = $('#address').val();
					var city = $('#cityname').val();
					console.log(city);
					console.log(addr);
					if (lastAddr != addr) {
						lastAddr = addr;
						showAddres(city, addr);
					}
				}
				$(function () {
					$("#cityname").autocomplete({
						source: "${createLink(resource:objInstance, action: 'cities')}",
						minLength: 2,
						change: function (event, ui) {
							if ($("#cityname2").val() != this.value) {
								$("#city\\.id").val("");
//					console.log($("#cityname :parent"));
								$("#cityname").parent().addClass("error");
							}
						},
						select: function (event, ui) {
							if (ui.item) {
								$("#city\\.id").val(ui.item.id);
								$("#cityname").val(ui.item.value);
								$("#cityname").parent().removeClass("error");
								$("#cityname2").val(ui.item.value);
							}
//				console.log(ui.item);
//				console.log(ui.item ?
//						"Selected: " + ui.item.id + " aka " + ui.item.label :
//						"Nothing selected, input was " + this.value);
							showAddres(this.value, $("#address").val());
						}
					});
					$("#cityname").autocomplete("option", "appendTo", "#editForm");
					$("#address").keyup(function () {
						console.log('keyup');
						if (addrTO) {
							clearTimeout(addrTO);
						}
						addrTO = setTimeout(addressUpdate, 2000);
					});

					ymaps.ready(function () {
						showAddres('${objInstance?.city?.name}', '${objInstance?.address}');
					});
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


			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'address', 'error')} ">
				<label class="control-label col-sm-3" for="address">
					<g:message code="account.address.label" default="Address"/>

				</label>

				<div class="col-sm-9"><g:textField class="form-control" name="address" value="${objInstance?.address}"/>
					<div id="mapToolBar" style="display:none;">
						<a id="ancorShow" href="#" onclick="mapToolBarShow();return false;">показать карту</a>
						<a id="ancorHide" href="#" onclick="mapToolBarHide();return false;" style="display:none;">скрыть карту</a>
						<div id="map" style="width:500px; height:500px; display: none;"></div>
					</div>
				</div>
			</div>


			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'manager', 'error')} required">
				<label class="control-label col-sm-3" for="manager">
					<g:message code="account.manager.label" default="Manager"/>
					<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
				</label>

				<div class="col-sm-9"><g:textField class="form-control" name="manager" value="${objInstance?.manager}"/></div>
			</div>

			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'phone1', 'error')} required">
				<label class="control-label col-sm-3" for="phone1">
					<g:message code="account.phone1.label" default="Phone1"/>
					<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
				</label>

				<div class="col-sm-9"><g:textField class="form-control" name="phone1" value="${objInstance?.phone1}"/></div>
			</div>

			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'phone2', 'error')} ">
				<label class="control-label col-sm-3" for="phone2">
					<g:message code="account.phone2.label" default="Phone2"/>

				</label>

				<div class="col-sm-9"><g:textField class="form-control" name="phone2" value="${objInstance?.phone2}"/></div>
			</div>

			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'phone3', 'error')} ">
				<label class="control-label col-sm-3" for="phone3">
					<g:message code="account.phone3.label" default="Phone3"/>

				</label>

				<div class="col-sm-9"><g:textField class="form-control" name="phone3" value="${objInstance?.phone3}"/></div>
			</div>

			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'fax1', 'error')} ">
				<label class="control-label col-sm-3" for="fax1">
					<g:message code="account.fax1.label" default="Fax1"/>

				</label>

				<div class="col-sm-9"><g:textField class="form-control" name="fax1" value="${objInstance?.fax1}"/></div>
			</div>

			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'fax2', 'error')} ">
				<label class="control-label col-sm-3" for="fax2">
					<g:message code="account.fax2.label" default="Fax2"/>

				</label>

				<div class="col-sm-9"><g:textField class="form-control" name="fax2" value="${objInstance?.fax2}"/></div>
			</div>

			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'workTime', 'error')} required">
				<label class="control-label col-sm-3" for="workTime">
					<g:message code="account.workTime.label" default="Work Time"/>
					<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
				</label>

				<div class="col-sm-9"><g:textField class="form-control" name="workTime" value="${objInstance?.workTime}"/></div>
			</div>

			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'webAddress', 'error')} ">
				<label class="control-label col-sm-3" for="webAddress">
					<g:message code="account.webAddress.label" default="Web Address"/>

				</label>

				<div class="col-sm-9"><g:textField class="form-control" name="webAddress" value="${objInstance?.webAddress}"/></div>
			</div>

			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'email', 'error')} ">
				<label class="control-label col-sm-3" for="email">
					<g:message code="account.email.label" default="Email Address"/>

				</label>

				<div class="col-sm-9"><g:textField class="form-control" name="email" value="${objInstance?.email}"/></div>
			</div>

		</div>

		<div class="tab-pane" id="affiliates">

			<ul id="affiliatesList" class="list-group">
				<g:each in="${beanResource?.affiliates}" var="affInstance" status="i">
					<g:render template="newAff" model="[affInstance: affInstance, i: i]"/>
				</g:each>
			</ul>

			<g:remoteLink class="btn btn-default" action="affform" onSuccess="addAffForm(data)" params="getFormData()"><g:message code="account.addaffilate"/></g:remoteLink>

			<script type="application/javascript">
				var affiliateCount =${beanResource?.affiliates == null ? 0 : beanResource?.affiliates.size()};
				function getFormData() {
					return {index: affiliateCount};
				}
				function addAffForm(data) {
					affiliateCount++;
					$(data).appendTo('#affiliatesList');
				}
			</script>
		</div>

		<div class="tab-pane" id="seo">

			<div class="fieldcontain  form-group ${hasErrors(bean: objInstance, field: 'description', 'error')} ">
				<label class="control-label col-sm-3" for="description">
					<g:message code="account.description.label" default="Description"/>

				</label>

				<div class="col-sm-9"><g:textArea class="form-control" name="description" value="${objInstance?.description}" rows="15" maxlength="1500"/></div>
			</div>


			<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'keywords', 'error')} ">
				<label class="control-label col-sm-3" for="keywords">
					<g:message code="account.keywords.label" default="Keywords"/>

				</label>

				<div class="col-sm-9"><g:textArea class="form-control" name="keywords" value="${objInstance?.keywords}"/></div>
			</div>

		</div>

		<g:set var="typeList" value="${ru.deloved.CategoryType.listOrderById()}"/>

		<div class="tab-pane" id="cat">
			<ul class="nav nav-pills">
				<g:each in="${typeList}" status="i" var="typeInstance">
					<li class="${i == 0 ? ' active' : ''}"><a href="#${typeInstance.code}" data-toggle="tab"><g:message code="categorytype.${typeInstance.code}"/></a></li>
				</g:each>
			</ul>

			<div class="tab-content">
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

				$('#editForm').submit(function () {
					var form = $(this);
					<g:each in="${typeList}" status="i" var="typeInstance">
					jQuery.each(jQuery('#${typeInstance.code}tree').jstree(true).get_selected(), function (i, v) {
						form.append('<input type="hidden" name="cat" value="' + v + '" />');
					});
					</g:each>
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
									"url": "${createLink(resource:beanResource, action: 'cat')}",
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
</div>

