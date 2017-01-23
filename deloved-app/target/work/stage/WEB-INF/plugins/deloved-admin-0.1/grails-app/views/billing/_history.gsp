<div class="panel panel-primary" style="width:80% "align="center">

    <div class="panel-heading">
        <b class="ft">История платежей</b>
    </div>

    <div id="historyGrid" class="panel-body table-responsive">
        <g:render template="historyGrid" model="[requestInstanceList: requestInstanceList,
                                                 requestInstanceTotal: requestInstanceTotal,
                                                 params: params]"/>
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
          success:function(data,textStatus) {
              if (data.ErrorCode == 0) {
                  jQuery('#siteInvoiceId').html(data.Payment.SiteInvoiceID);
                  jQuery('#paymentId').html(data.Payment.PaymentID);
                  jQuery('#purpose').html(data.Payment.Purpose);
                  jQuery('#amount').html(data.Payment.Amount + ' ' + data.Payment.CurrencyCode);
                  jQuery('#paymentSystemId').html(data.Payment.PaymentSystemID);
                  jQuery('#lastUpdateTime').html(data.Payment.LastUpdateTime);
                  jQuery('#paymentMethod').html(data.Payment.PaymentMethod);
                  $('#myModalOption').modal();
              }
              if (data.ErrorCode == -13) {
                  alert("Платеж отсутствует в системе. Скорее всего вы прервали процесс оплаты.");
              }
          },
          error:function(XMLHttpRequest,textStatus,errorThrown){}});
        return false
  }
</script>



<!-- HTML код модального окна-->
<div id="myModalOption" class="modal fade" align="center">
  <div class="modal-dialog">
    <div class="modal-content" style="width: 450px; text-align: left">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title ft">Детализация платежа</h4>
      </div>
     <div id="changeStatusMessage" class="modal-body" >

       <b class="ft" >Номер счета в системе Deloved:</b>
       <span id="siteInvoiceId" class="fr"></span> <br>
         <hr>
       <b class="ft">Номер счета в платежной системе: </b>
       <span class="fr" id="paymentId"></span> <br>
         <hr>
       <b class="ft">Примечение к платежу:</b><br>
       <span class="fr" id="purpose"></span> <br>
         <hr>
       <b class="ft"> Сумма:</b>
       <span class="fr" id="amount"></span> <br>
         <hr>
       <b class="ft">Способ оплаты: </b>
       <span class="fr" id="paymentMethod"></span> <br>
         <hr>
       <b class="ft">Платежная система:</b>
       <span class="fr" id="paymentSystemId"></span> <br>
         <hr>
       <b class="ft">Дата платежа:</b><br>
       <span class="fr" id="lastUpdateTime"></span>

      </div>


      <div class="modal-footer">

           <button id="dismissButton" type="button" class="btn btn-default ft" data-dismiss="modal">Отмена</button>

      </div>
    </div>
  </div>
</div>
