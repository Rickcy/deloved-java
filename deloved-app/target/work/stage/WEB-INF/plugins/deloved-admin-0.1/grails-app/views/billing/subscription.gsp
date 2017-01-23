<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <meta name='layout' content='admin'/>
  <title>История платежей</title>
</head>


<body>

<g:if test="${freeAccount}">
  <h1><g:message code="billing.list.subscription.activate"/></h1>
</g:if>

<g:else>
  <h1><g:message code="billing.list.subscription.renew"/></h1>
</g:else>
<g:link class="btn btn-default" action="account"><span class="glyphicon glyphicon-chevron-left"></span>Назад</g:link>
<g:render template="/_common/flash-message"/>


<div class="lead">При покупке более длительного периода расширенной подписки стоимость месяца получается дешевле</div>

<div align="center">

  <g:each in="${tariffs}" var="tariff">


      <div class="tariff-block">

      <p><span style="font-weight: bold; color: #404040">${tariff.name}</span><br>
      <span style="color: grey">${30*tariff.months} дней</span></p>

      <hr>

      <p>
        <span style="font-weight: bold;"><g:formatNumber number="${tariff.price/tariff.months}" format="####" /> руб в месяц </span><br>
        <g:formatNumber number="${tariff.price}" format="####" /> руб всего</p>
      <p> <span style="color: green; font-size: smaller">Вы сэкономите: <g:formatNumber number="${basePrice*tariff.months - tariff.price}" format="####" /> руб</span></p>

    <g:form name="${'subscriptionForm'+tariff.id}" method="POST" action="invoice">
        <input type="hidden" name="tariffId" value="${tariff.id}">
      <g:submitButton class="tariff-btn" name="${'send'+tariff.id}" value="Купить"/>
    </g:form>

      </div>




  </g:each>

  <div class="subcription_block">
    </div>
  <div class="subcription_block">
  </div>

</div>

</body>
</html>