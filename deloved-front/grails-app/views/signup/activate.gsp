<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="admin">
	<title>Активация учетной записи</title>
</head>

<body>

<g:render template="/_common/flash-message"/>


<div align="center">
	<div style="width: 80%; border-radius: 4px; border: 1px solid silver; padding: 15px" align="left">
<g:form url="[action: 'activate']" class="form-horizontal">
		<fieldset class="form">

			<div class="required form-group ">
				<label for="code" class="col-sm-3 control-label">
					Проверочный код
				</label>

				<div class="col-sm-9">
					<g:textField class="form-control" name="code" required="" value="${params.code}" placeholder="Введите проверочный код"/>
					<div class="pods">Введите в этом поле проверочный код, который указан в присланом вам письме.</div>
				</div>
			</div>

		</fieldset>
		<fieldset>
			<div align="right">
				<g:submitButton name="activate" class="btn btn-success" value="Активировать"/>
			</div>
		</fieldset>
</g:form>
</div>
</div>
</body>
</html>