
<div id="aff${i}" class="tab-pane fade ${i == 0 || active == true ? 'in active' : ''}">

<div name="affiliateBlock" style="width: 100%">

  <g:hiddenField name="aff.${i}.id" value="${affiliateInstance?.id}"/>

  <div class="row">
    <div class="col-md-3" align="left">
      <label>Адресс</label>
    </div>
    <div class="col-md-9">
      <g:textField name="aff.${i}.address" class="form-control" value="${affiliateInstance?.address}" style="width: 100%"/>
    </div>
  </div>

  <div class="row">
    <div class="col-md-3" align="left">
      <label>Город</label>
    </div>
    <div class="col-md-9">
      <g:textField name="aff.${i}.city" class="form-control" value="${affiliateInstance?.city?.name}" style="width: 100%"/>
    </div>
  </div>

  <div class="row">
    <div class="col-md-3" align="left">
      <label>Email</label>
    </div>
    <div class="col-md-9">
      <g:textField name="aff.${i}.email" class="form-control" value="${affiliateInstance?.email}" style="width: 100%"/>
    </div>
  </div>

  <div class="row">
    <div class="col-md-3" align="left">
      <label>Телефон</label>
    </div>
    <div class="col-md-9">
      <g:textField name="aff.${i}.phone" class="form-control" value="${affiliateInstance?.phone}" style="width: 100%"/>
    </div>
  </div>


  <button class="btn btn-default" onclick="if (confirm('Вы действительно хотите удалить филиал?')) {$(this).parent().remove()}{$('#affiliateList').submit()} reloadPage()">Удалить</button>

</div>
</div>
<script>
  function reloadPage(){
    setTimeout('location.reload()',1)

  }
</script>