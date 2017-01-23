<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <meta name='layout' content='admin'/>
  <title>История платежей</title>
</head>
<body>
<h1><g:message code="billing.list.history"/></h1>
<g:link class="btn btn-default" action="account"><span class="glyphicon glyphicon-chevron-left"></span>Назад</g:link>
<g:render template="/_common/flash-message"/>



<div class="panel panel-primary" style="width:80% "align="center">
    <div class="panel-heading">
        <b>История платежей</b>
    </div>

    <div class="panel-body">

    <div id="historyGrid">
        <g:render template="historyGrid" model="[requestInstanceList: requestInstanceList,
                                                 requestInstanceTotal: requestInstanceTotal,
                                                 params: params]"/>
    </div>

</div>
</div>

<script>
  function showModal() {
    $('#myModalOption').modal();
    return false
  }


  function checkRequestStatus(requestId) {
      jQuery.ajax({
          type:'GET',
          url:'${createLink([controller: 'paymaster', action: 'checkRequestStatus'])}/'+requestId,
          dataType: 'json',
          success:function(data,textStatus){



            if (data.ErrorCode == 0) {

               // alert(data)

                jQuery('#siteInvoiceId').html(data.Payment.SiteInvoiceID);
                jQuery('#paymentId').html(data.Payment.PaymentID);
                jQuery('#purpose').html(data.Payment.Purpose);
                jQuery('#amount').html(data.Payment.Amount + ' ' + data.Payment.CurrencyCode);
                jQuery('#paymentSystemId').html(data.Payment.PaymentSystemID);
                jQuery('#lastUpdateTime').html(data.Payment.LastUpdateTime);
                jQuery('#paymentMethod').html(data.Payment.PaymentMethod);
                $('#myModalOption').modal();
               // return false
                //showModal()
            }

            if (data.ErrorCode == -13) {
                alert("Платеж отсутствует в системе. Скорее всего вы прервали процесс оплаты.");
            }




          },
          error:function(XMLHttpRequest,textStatus,errorThrown){}});
console.log(data);
      return false
  }
</script>

<button onclick="showModal()">Модальное окно</button>
<!-- HTML код модального окна-->
<div id="myModalOption" class="modal fade" align="center">
  <div class="modal-dialog">
    <div class="modal-content" style="width: 350px; text-align: left">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">Детализация платежа</h4>
      </div>
     <div id="changeStatusMessage" class="modal-body">

       <b>Номер счета в системе deloved:</b> <br>
       <span id="siteInvoiceId"></span> <br>

       <b>Номер счета в платежной системе: </b> <br>
       <span id="paymentId"></span> <br>

       <b>Примечение к платежу:</b> <br>
       <span id="purpose"></span> <br>

       <b>Сумма:</b> <br>
       <span id="amount"></span> <br>

       <b>Способ оплаты: </b><br>
       <span id="paymentMethod"></span> <br>

       <b>Платежная система:</b> <br>
       <span id="paymentSystemId"></span> <br>

       <b>Дата платежа:</b> <br>
       <span id="lastUpdateTime"></span>

      </div>


      <div class="modal-footer">

           <button id="dismissButton" type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>

      </div>
    </div>
  </div>
</div>
</body>
</html>