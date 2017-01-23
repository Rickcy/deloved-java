<%--
  User: artwolf
  Date: 28.09.2015
  Time: 11:40
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="admin">
	<title>Повторый запрос инструкций</title>
</head>

<body>

<g:hasErrors bean="${objInstance}">
    <ul class="alert alert-danger errors" role="alert">
        <g:eachError bean="${objInstance}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
        </g:eachError>
    </ul>
</g:hasErrors>

<div align="center">
<div style="width: 80%; border-radius: 4px; border: 1px solid silver; padding: 15px" align="left">



  <span style="text-align: left; font-weight: bold;">Повторный запрос письма с инструкциями по активации учетной записи</span>

   <g:form id="formResend" name="formResend" role="form" style="margin-top: 15px"
               url="[controller: 'signup', action: 'resendActivateMail']" method="POST">

    <input id="email" name="email" type="email" class="form-control" placeholder="Введите адрес электронной почты" value="${beanResource?.email}"/>
    <div class="pods" align="center">Е-mail, для повторной отправки, оставте текущий или укажите новый.</div>

    <div align="center" style="margin: 15px">
      <recaptcha:ifEnabled>
        <recaptcha:recaptcha theme=""/>
      </recaptcha:ifEnabled>
    </div>

    <div align="right">
      <button type="submit" class="btn btn-primary">Выслать</button>
      <g:link controller="panel" action="index" class="btn btn-default">Отмена</g:link>
    </div>
  </g:form>

</div>
</div>

</body>
</html>