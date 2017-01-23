<div class="panel panel-primary" style="width:80% "align="center">
  <div class="panel-heading">
    <b class="ft">Продление расширешенной подписки</b>
  </div>

  <div class="panel-body table-responsive">
    <g:form name="payForm" url="[controller: 'billing', action: 'income']" target="_blank" method="POST">

    <table id="tariff_table" class="table table-hover">

      <thead>
      <tr style="border-bottom: 3px solid rgba(176, 208, 83, 0.24)">
        <td><!--Наименование тарифа--></td>
        <td align="center" class="ft"><b>Цена за месяц, руб.</b></td>
        <td align="center" class="ft"><b>Цена тарифа, руб.</b></td>
        <td align="center" class="ft"><b>Экономия, руб.</b></td>
        <td><!--Чекбокс--></td></tr>
      </thead>

      <tbody>
      <g:each in="${tariffs}" var="tariff">
        <tr>
          <td align="right">
            <b class="fr">${tariff.name}</b>
          </td>

          <td align="center">
            <g:formatNumber number="${tariff.price/tariff.months}" format="####" />

          </td>

          <td align="center">
            <g:formatNumber number="${tariff.price}" format="####" />

          </td>

          <td align="center">
            <g:formatNumber number="${basePrice.price*tariff.months - tariff.price}" format="####" />
          </td>
          <td align="center">
            <div class="check-box"><input required type="radio" name="tariff" data-amount="${tariff.price}" value="${tariff.id}"/></div>
          </td>
        </tr>
      </g:each>
      </tbody>
    </table>



      <label for="amount" class="control-label">Cумма:</label>

      <input class="form-control" id="amount" readonly type="text" form="payForm" pattern="[1-9](\d+)?((\.|\,)\d\d?)?" name="amount" value="" autocomplete="off" style="display: inline-block; width: 90px; text-align: center"/>
      <b>, руб</b>

      <label for="selectMethod" class="control-label" style="margin-left: 20px">Способ оплаты:</label>

      <select required id="selectMethod" name="method.id" class="form-control" style="display: inline-block; width: 220px">

        <option value=""></option>

        <g:each in="${methods}" var="method">
          <option value="${method.id}">${method.name}</option>
        </g:each>

      </select>



      <g:submitButton name="payment" class="btn btn-primary"  onclick="" style="margin-left: 20px" value="Оплатить" />

    </g:form>

    <script>



      $("#tariff_table input[name='tariff']").click(function(){
        var amount = $('input:radio[name=tariff]:checked').data("amount")
        $('#amount').val(amount);
      });

      function submitPayment(){
        $('#amount').val('');
        $('#selectMethod :first').attr("selected", "selected");
        return false
      }



    </script>

  </div>
</div>