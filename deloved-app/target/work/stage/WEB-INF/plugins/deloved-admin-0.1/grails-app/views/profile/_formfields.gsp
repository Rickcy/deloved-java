<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'fio', 'error')} ">
	<label class="col-sm-3 control-label" for="fio">
		<g:message code="profile.fio.label" default="Fio"/>
	</label>

	<div class="col-sm-9">
		<g:textField class="form-control" name="fio" maxlength="50" value="${objInstance?.fio}"/>
	</div>
</div>

<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'email', 'error')} ">
	<label class="col-sm-3 control-label" for="email">
		<g:message code="profile.email.label" default="Email"/>
	</label>

	<div class="col-sm-9">
		<g:field class="form-control" type="email" name="email" maxlength="50" value="${objInstance?.email}"/>

		<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
			<div class="checkbox">
				<label for="withoutEmailConfirm">
					<g:checkBox name="withoutEmailConfirm" value="1" checked="false"/>
					<g:message code="profile.email.without.confirm.label" default="Подтвердить"/>
				</label>
			</div>
		</sec:ifAnyGranted>
		<g:if test="${confirmEmailList?.size() > 0}">
			<ul>
				<g:each in="${confirmEmailList}" var="confirm">
					<li>
						${confirm.contact} - <g:message code="contactConfirm.${confirm.status()}.status"/>
						<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
							<g:if test="${confirm.status() == ru.deloved.ConfirmStatus.Process}">
								<g:link url="[plugin: 'deloved-front', controller: 'signup', action: 'activate', params: [code: confirm.secret]]">Confirm</g:link>
							</g:if>
						</sec:ifAnyGranted>
					</li>
				</g:each>
			</ul>
		</g:if>

	</div>
</div>
<g:if test="${beanResource.user.role.authority in ['ROLE_MANAGER','ROLE_JUDGE','ROLE_JURIST','ROLE_MEDIATOR']}">
	<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'dayOfWork', 'error')} ">
		<label class="col-sm-3 control-label" for="dayOfWork">
			<g:message code="profile.cell.label" default="Стаж работы"/>
		</label>

		<div class="col-sm-9">
			<g:textField class="form-control" name="dayOfWork" maxlength="20" value="${objInstance?.dayOfWork}"/>
		</div>



	</div>
</g:if>
<g:else>
	<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'cellPhone', 'error')} ">
		<label class="col-sm-3 control-label" for="cellPhone">
			<g:message code="profile.cellPhone" default="Сотовый"/>
		</label>



		<div class="col-sm-9">
			<g:if test="${confirmCellphoneList?.size() == 0}">
				<g:textField class="form-control" name="cellPhone" maxlength="20" value="${objInstance?.cellPhone}"/>

				<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
					<div class="checkbox">
						<label for="withoutSmsConfirm">
							<g:checkBox name="withoutSmsConfirm" value="1" checked="false"/>
							<g:message code="profile.cellPhone.without.confirm.label" default="Подтвердить"/>
						</label>
					</div>
				</sec:ifAnyGranted>
			</g:if>
			<g:else>
				<p class="form-control-static">${objInstance?.cellPhone}</p>
				<ul>
					<g:each in="${confirmCellphoneList}" var="confirm">
						<li>
							${confirm.contact} - <g:message code="contactConfirm.${confirm.status()}.status"/>
							<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
								Код:${confirm.secret}
							</sec:ifAnyGranted>
							<g:if test="${confirm.status() == ru.deloved.ConfirmStatus.Process}">
								<div class="form-inline">
									<label class="control-label" for="smsCode">
										<g:message code="profile.smsCode.label" default="SMS Code"/>
									</label>
									<g:textField class="form-control" name="smsCode" maxlength="6"/>
								</div>
							</g:if>
						</li>
					</g:each>
				</ul>
			</g:else>
		</div>

	</div>

</g:else>

<g:if test="${inputCity}">
	<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'city', 'error')} required">
		<label class="col-sm-3 control-label" for="cityname">
			<g:message code="profile.city.label" default="City"/>
			<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
		</label>

		<div class="col-sm-9">
			<g:hiddenField name="city.id" value="${objInstance?.city?.id}"/>
			<g:hiddenField name="cityname2" value="${objInstance?.city?.name}"/>
			<g:textField class="form-control" name="cityname" value="${objInstance?.city?.name}"/>
		</div>
	</div>

	<script type="application/javascript">
		$(function () {
			$("#cityname").autocomplete({
				source: "${createLink(controller: 'profile', action: 'cities')}",
				minLength: 2,
				change: function (event, ui) {
					if ($("#cityname2").val() != this.value) {
						$("#city\\.id").val("");
						console.log($("#cityname :parent"));
						$("#cityname").parent().addClass("error");
					}
				},
				select: function (event, ui) {
					if (ui.item) {
						$("#city\\.id").val(ui.item.id);
						$("#cityname").val(ui.item.value);
						$("#cityname").parent().removeClass("error");
						$("#cityname2").val(ui.item.value);
					} else {

					}
					console.log(ui.item);
					console.log(ui.item ?
					"Selected: " + ui.item.id + " aka " + ui.item.label :
					"Nothing selected, input was " + this.value);
				}
			});
			$("#cityname").autocomplete("option", "appendTo", "#editForm");
		});
	</script>
</g:if>


<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'avatar', 'error')} ">
	<label class="col-sm-3 control-label">
		<g:message code="profile.avatar.label" default="Avatar"/>
	</label>

	<div class="col-sm-9">

		<g:render template="/_common/upload-single-image" model="[
				'isUpload'    : beanResource?.avatar != null,
				'imageUrl'    : createLink(controller: 'profile', action: 'avatar', id: beanResource.id, params: [name: beanResource.avatar?.file, type: 'main']),
				'thumbUrl'    : createLink(controller: 'profile', action: 'avatar', id: beanResource.id, params: [name: beanResource.avatarThumb?.file]),
				'uploadAction': createLink(controller: 'profile', action: 'upload', id: beanResource.id),
				'cropAction'  : createLink(controller: 'profile', action: 'crop', id: beanResource.id),
				'deleteAction': 'deleteavatar',
				'imgId'       : 'avatar',
				'imgTitle'    : 'Аватар',
				'imgWidth'    : 100,
				'imgHeight'   : 100

		]"/>
	</div>
</div>


<g:if test="${beanResource.user.role.authority = 'ROLE_ACCOUNT'}">
	<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
		<div class="fieldcontain form-group ${hasErrors(bean: beanResource, field: 'chargeStatus', 'error')}">
			<label class="control-label col-sm-3" for="chargeStatus">
				<g:message code="profile.chargeStatus.label" default="Подписка"/>
			</label>

			<div class="col-sm-9">
				<div class="btn-group" data-toggle="buttons">
					<label class="btn btn-default <g:if test="${beanResource?.chargeStatus == 0}">active</g:if>">
						<g:radio name="chargeStatus" value="0" checked="${beanResource?.chargeStatus == 0}"/>Стартовая
					</label>
					<label class="btn btn-default <g:if test="${beanResource?.chargeStatus == 1}">active</g:if>">
						<g:radio name="chargeStatus" value="1" checked="${beanResource?.chargeStatus == 1}"/>Расширенная
					</label>
				</div>
			</div>
		</div>

		<div class="fieldcontain form-group ${hasErrors(bean: beanResource, field: 'chargeTill', 'error')}">
			<label class="control-label col-sm-3" for="chargeTill">
				<g:message code="profile.chargeTill.label" default="Срок подписки"/>
			</label>

			<div class="col-sm-9">
				<g:textField class="form-control" name="chargeTill" value="${formatDate(date: beanResource.chargeTill, format: 'dd.MM.yyyy HH:mm:ss')}"/>
			</div>
		</div>

	</sec:ifAnyGranted>
</g:if>