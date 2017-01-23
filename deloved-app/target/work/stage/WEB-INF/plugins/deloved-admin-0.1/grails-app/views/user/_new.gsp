<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'username', 'error')} required form-group">
	<label for="username" class="col-sm-3 control-label">
		<g:message code="user.username.label" default="Username"/>
		<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
	</label>
	<div class="col-sm-9">
		<g:textField class="form-control" name="username" required="" value="${objInstance?.username}"/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'password', 'error')} required form-group ">
	<label for="password" class="col-sm-3 control-label">
		<g:message code="user.password.label" default="Password"/>
		<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
	</label>
	<div class="col-sm-9">
		<g:passwordField class="form-control" name="password" required="" value="${objInstance?.password}"/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'passwordRepeat', 'error')} required form-group">
	<label for="passwordRepeat" class="col-sm-3 control-label">
		<g:message code="user.passwordRepeat.label" default="Repeat Password"/>
		<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
	</label>
	<div class="col-sm-9">
		<g:passwordField class="form-control" name="passwordRepeat" required="" value="${objInstance?.passwordRepeat}"/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'role', 'error')} required form-group">
	<label for="role" class="col-sm-3 control-label">
		<g:message code="user.role.label" default="Role"/>
		<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
	</label>

	<div class="col-sm-9">

		<g:select class="form-control"
				  name="role"
				  from="${ru.deloved.Role.list()}"
				  optionKey="id"
				  optionValue="${{ message(code: 'role.' + it.authority, default: it.authority) }}"

				  value="${objInstance?.role ?: ru.deloved.Role.findByAuthority('ROLE_NONE').id}"/>

	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'roleRequest', 'error')} form-group">
	<label for="roleRequest" class="col-sm-3 control-label">
		<g:message code="user.roleRequest.label" default="Role after activate"/>
	</label>

	<div class="col-sm-9">

		<g:select class="form-control"
				  name="roleRequest"
				  from="${ru.deloved.Role.list()}"
				  optionKey="id"
				  optionValue="${{ message(code: 'role.' + it.authority, default: it.authority) }}"

				  noSelection="['': 'Не задано']"/>

	</div>
</div>

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
