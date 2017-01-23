<!--Форма с автосабмитом для POST запроса с перенаправлением. На момент написания(11/06/2015) для Grails ничего лучше нет -->
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title></title>
</head>

<body>
<p>Перенаправление...</p>
<form id=pay name=pay method="POST" action="${url}"> <!-- https://paymaster.ru/Payment/Init-->

   <input type="hidden" name="LMI_MERCHANT_ID" value="${merchant_id}"> <!-- Идентификатор учетной записи -->
   <input type="hidden" name="LMI_PAYMENT_AMOUNT" value="${paymentRequest.value}"> <!-- Сумма платежа -->
   <input type="hidden" name="LMI_CURRENCY" value="${paymentRequest.keeper.currency.code}"> <!-- Валюта -->
   <input type="hidden" name="LMI_PAYMENT_NO" value="${paymentRequest.id}"> <!-- ИД покупки (необязательно) -->
   <input type="hidden" name="LMI_PAYMENT_DESC" value="Расширенная подписка на услуги бизнес-портала 'Деловед' сроком на: ${tariff.name}"><!-- Описание товара или услуги (необязательно) -->
   <input type="hidden" name="LMI_PAYMENT_METHOD" value="${paymentRequest.method.code}"><!-- Метод оплаты (необязательно) -->

<g:if test="${isDevelopment}">
   <input type="hidden" name="LMI_PAYMENT_NOTIFICATION_URL" value="http://212.74.218.142:42001/deloved-app/paymaster/paymentNotification"> <!--  -->
   <input type="hidden" name="LMI_SUCCESS_URL" value="http://212.74.218.142:42001/deloved-app/paymaster/admin/billing/index"> <!--  -->
   <input type="hidden" name="LMI_FAILURE_URL" value="http://212.74.218.142:42001/deloved-app/paymaster/admin/billing/account"> <!--  -->
   <input type="hidden" name="LMI_INVOICE_CONFIRMATION_URL" value="http://212.74.218.142:42001/deloved-app/paymaster/invoiceConfirmation"> <!--  -->
</g:if>


   <input type="submit" value="submit" hidden="hidden"> <!-- style="display: none;"> -->


</form>

<script type="text/javascript">
  window.onload = function() {
    document.forms["pay"].submit();
  }
</script>

</body>
</html>