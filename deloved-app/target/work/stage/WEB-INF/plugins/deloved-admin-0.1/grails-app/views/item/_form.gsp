<%@ page import="ru.deloved.CategoryType; ru.deloved.Category; ru.deloved.Item" %>


			<sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_MANAGER">
				<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'showMain', 'error')}">
					<label class="control-label col-sm-3" for="showMain">
						<g:message code="item.showMain.label" default="Show on main"/>
					</label>

					<div class="col-sm-7">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-default <g:if test="${objInstance?.showMain == false}">active</g:if>">
								<g:radio name="showMain" value="false" checked="${objInstance?.showMain == false}"/>Нет
							</label>
							<label class="btn btn-default <g:if test="${objInstance?.showMain == true}">active</g:if>">
								<g:radio name="showMain" value="true" checked="${objInstance?.showMain == true}"/>ДА
							</label>
						</div>
					</div>
				</div>
				<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'account', 'error')} required form-group">
					<label class="col-sm-3 control-label" for="account">
						<g:message code="item.account.label" default="Account"/>
						<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
					</label>

					<div class="col-sm-7">
						<g:if test="${objInstance.id == null}">
							<g:if test="${objInstance.accountId}">
								${objInstance.account?.name}
								<g:hiddenField name="account.id" value="${objInstance.accountId}"/>
							</g:if>
							<g:elseif test="${accountList?.size() > 0}">
								<g:select id="account" name="account.id" class="form-control" from="${accountList}" optionKey="id" optionValue="name" required="" value="${objInstance?.account?.id}"
										  onchange="initTree(jQuery('#account').val());"/>
							</g:elseif>
							<g:else>
								<g:render template="/_common/auto-complete" model="[
										acAction   : createLink(controller: 'item', action: 'accounts'),
										acMinLength: 4,
										acKeyValue : '',
										acKeyName  : 'account.id',
										acViewValue: '',
										acViewName : 'accountname',
										acOnSelect : 'initTree(ui.item.id);'
								]"/>

							</g:else>
						</g:if>
						<g:else>
							<g:if test="${accountList?.size() > 1}">
								<g:select id="account" name="account.id" class="form-control" from="${accountList}" optionKey="id" optionValue="name" required="" value="${objInstance?.account?.id}"
										  onchange="initTree(jQuery('#account').val());"/>
							</g:if>
							<g:elseif test="${accountList?.size() == 1}">
								${objInstance.account?.name}
							</g:elseif>
							<g:else>
								<g:render template="/_common/auto-complete" model="[
										acAction   : createLink(controller: 'item', action: 'accounts'),
										acMinLength: 4,
										acKeyValue : objInstance.accountId,
										acKeyName  : 'account.id',
										acViewValue: objInstance.account.name,
										acViewName : 'accountname',
										acOnSelect : 'initTree(ui.item.id);'
								]"/>
							</g:else>
						</g:else>
					</div>
				</div>
			</sec:ifAnyGranted>

			<sec:ifAnyGranted roles="ROLE_ACCOUNT">
				<input type="hidden" name="account.id" value="${objInstance.account.id}"/>
			</sec:ifAnyGranted>

			<input type="hidden" name="categoryType.id" value="${objInstance.categoryType.id}"/>

			<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'name', 'error')} required form-group">
				<label class="col-sm-3 control-label" for="name">
					<g:message code="item.name.label" default="Name"/>
					<sup><span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span></sup>
				</label>
				<div class="col-sm-7">
					<g:textField name="name" class="form-control" value="${objInstance?.name}"/>
					<g:render template="/_common/error" model="[objInstance: objInstance, field: 'name']"/>
					<div class="pods"></div>
				</div>
			</div>
<g:if test="${objInstance.categoryType.code == 'GOOD'}">
	<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'kind', 'error')} required form-group">
		<label class="col-sm-3 control-label" for="kind">
			<g:message code="item.kind.label" default="Kind"/>
			<sup><span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span></sup>
		</label>
		<div class="col-sm-7">
			<g:textField name="kind" class="form-control" value="${objInstance?.kind}"/>
			<g:render template="/_common/error" model="[objInstance: objInstance, field: 'kind']"/>
			<div class="pods">Укажите марку, модель, вид, разновидность, иные уточняющие характеристики</div>
		</div>
	</div>
</g:if>
<g:if test="${objInstance.categoryType.code == 'SERVICE'}">
	<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'kind', 'error')} required form-group">
		<label class="col-sm-3 control-label" for="kind">
			<g:message code="item.kind.label" default="Kind"/>
			<sup><span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span></sup>
		</label>
		<div class="col-sm-7">
			<g:textField name="kind" class="form-control" value="${objInstance?.kind}"/>
			<g:render template="/_common/error" model="[objInstance: objInstance, field: 'kind']"/>
			<div class="pods"></div>
		</div>
	</div>
</g:if>


			<div class="fieldcontain required form-group">
				<label class="col-sm-3 control-label" for="price">
					<g:message code="item.price.label" default="Price"/>
					<sup><span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span></sup>
				</label>
				<div class="col-sm-4  ${hasErrors(bean: objInstance, field: 'price', 'error')}">
					<g:textField name="price" class="form-control" value="${formatNumber(number: objInstance?.price, format: '###,##0.00')}"/>
					<g:render template="/_common/error" model="[objInstance: objInstance, field: 'price']"/>
					<div class="pods"></div>
				</div>
				<div class="col-sm-3  ${hasErrors(bean: objInstance, field: 'currency', 'error')}">
					<g:select id="currency" class="form-control" name="currency.id" from="${ru.deloved.SystemCurrency.findAll()}" optionKey="id" optionValue="${{it.getSymbol(requestLocale) }}"
							   value="${objInstance?.currency?.id}" noSelection="${[null:  'Выберите валюту']}"/>
					<g:render template="/_common/error" model="[objInstance: objInstance, field: 'currency']"/>
					<div class="pods"></div>
				</div>
			</div>

			<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'measure', 'error')} required form-group">
				<label class="col-sm-3 control-label" for="measure">
					<g:message code="item.measure.label" default="Measure"/>
					<sup><span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span></sup>
				</label>
				<div class="col-sm-7">
					<g:select id="measure" class="form-control" name="measure.id" from="${ru.deloved.Measure.findAllByType(objInstance.categoryType)}" optionKey="id"
							  noSelection="${[null:'Выберите единицу измерения']}"
							  value="${objInstance?.measure?.id}"/>
					<g:render template="/_common/error" model="[objInstance: objInstance, field: 'measure']"/>
					<div class="pods"></div>
				</div>
			</div>

			<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'description', 'error')} required form-group">
				<label class="col-sm-3 control-label" for="description">
					<g:message code="item.description.label" default="Description"/>
					<sup><span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span></sup>
				</label>
				<div class="col-sm-7">
					<g:textArea style="min-height: 150px" class="form-control" name="description" value="${objInstance?.description}"/>
					<g:render template="/_common/error" model="[objInstance: objInstance, field: 'description']"/>
					<div class="pods"></div>
				</div>
			</div>

			<g:if test="${objInstance.categoryType.code == 'GOOD'}">
				<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'availability', 'error')}">
					<label class="control-label col-sm-3" for="availability">
						<g:message code="item.availability.label" default="Availability"/>
					</label>

					<div class="col-sm-7">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-default <g:if test="${objInstance?.availability == 1}">active</g:if>">
								<g:radio name="availability" value="1" checked="${objInstance?.availability == 1}"/>Есть
							</label>
							<label class="btn btn-default <g:if test="${objInstance?.availability == 0}">active</g:if>">
								<g:radio name="availability" value="0" checked="${objInstance?.availability == 0}"/>Под заказ
							</label>
						</div>
						<g:render template="/_common/error" model="[objInstance: objInstance, field: 'availability']"/>
						<div class="pods"></div>
					</div>
				</div>
			</g:if>

			<div class="fieldcontain form-group">
				<label class="control-label col-sm-3">
					<g:render template="/_common/hint" model="[hintText: 'Загружать фото в формате .pnsg']"/><g:message code="item.photo.label" default="photo"/>
				</label>

				<div class="col-sm-7">
					<g:render template="/_common/upload-multi-image" model="[
							objInstance: beanResource,
							attachList : attachList,
							imgWidth   : 100,
							imgHeight  : 100
					]"/>
				</div>
			</div>

		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'category', 'error')}">

			<label class="control-label col-sm-4">

				<g:message code="item.category.label" default="Category"/>
			</label>

<hr>

			<div class="col-sm-1 ">
				<g:hiddenField name="category.id" value="${objInstance?.categoryId}"/>
				<div id="tree">
				</div>
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'category']"/>
				<div class="pods"></div>
			</div>
		</div>
		</div>

		<script type="application/javascript">
			$(function () {
				function initTree(accountId) {
					var jst = $('#tree').jstree(true);
					if (jst) {
						jst.destroy();
					}
					$('#tree')
							.on('changed.jstree', function (e, data) {
								var val = '';
								if (data.selected.length > 0) {
									val = data.selected[0];
								}
								jQuery('#category\\.id').val(val);
							})
							.jstree({
								"core": {
									"multiple": false,
									"data": {
										"url": "${createLink(controller: 'item', action: 'cat')}",
										"data": function (n) {
											return {
												"categoryId": '${objInstance?.category?.id}',
												"pid": n.id && n.id != '#' ? n.id : ${ Category.findByTypeAndParent(objInstance.categoryType, null).id },
												"accountId": accountId
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
				}

				<g:if test="${objInstance.account}">
				initTree(${objInstance.account.id});
				</g:if>
				<g:elseif test="${accountList?.size() > 0}">
				initTree(${accountList[0].id});
				</g:elseif>
				window.initTree = initTree;
			});
		</script>
