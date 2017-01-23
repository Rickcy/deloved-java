<script>
	$(document).ready(function(){
		$('.asd').attr('disabled','true')
	})
</script>
<g:if test="${claimCreateCommandInstance.account}">
	<g:hiddenField name="account" value="${claimCreateCommandInstance.account}"/>
</g:if>

<g:hiddenField name="partner" value="${claimCreateCommandInstance.partner}"/>
<g:hiddenField name="deal" value="${claimCreateCommandInstance.deal}"/>


<div class="col-sm-12" style="padding:10px">
	<ul  style="list-style:none;padding-left: 0">
		<li style="padding: 5px;font-size: 12pt"><img style="width: 6%;margin-left: 16px;margin-right: 14px" src="${resource(dir: 'images', file: 'admin/sud_ultra.png')}"/>При подаче иска вы всегда можете воспользоваться помощью юриста</li>
		<li style="padding: 5px;font-size: 12pt"><img style="width: 5%;margin-left: 20px;margin-right: 18px" src="${resource(dir: 'images', file: 'admin/hammer.png')}"/>Иск будет рассмотрен в <a target="_blank" href="http://delovedsud.ru">Третейском суде "Деловед"</a></li>
	</ul>
</div>
<div class="clearfix"></div>
<div class=info  style="padding:10px;">Опишете в текстовом поле кратко суть иска.</div>


<g:if test="${myAccounts}">
	<div class="fieldcontain required form-group">
		<label for="account" class="col-sm-3 control-label">
			<g:message code="claim.account.label"/>
			<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
		</label>

		<div class="col-sm-9">
			<g:select class="form-control" name="account" from="${myAccounts}" optionKey="id" optionValue="name"/>
		</div>
	</div>
</g:if>

<div class="fieldcontain ${hasErrors(bean: claimInstance, field: 'subject', 'error')} required form-group">


	<div class="col-sm-12">

		<g:textArea style="min-height: 100px" class="form-control" name="subject" required="" value="${claimCreateCommandInstance?.subject}"/>
	</div>


	<div class="col-sm-12" style="padding: 15px;font-size: 12pt">
		<p>Проверьте наличие следующих документов</p>
		<ul style="list-style:circle;">
		<li style="padding: 3px">Исковое заявление</li>
		<li style="padding: 3px">Документ об оплате третейского сбора(<a target="_blank" href="http://delovedsud.ru/treteiski-sbor/calculator.php">Расчет третейского сбора</a>)</li>
		<li style="padding: 3px">Договор или соглашение, содержащие <a target="_blank" href="http://delovedsud.ru/appeal/">третейскую оговорку</a></li>
		<input style="margin-top: 15px;margin-right: 10px" type="checkbox" id="check" > Я распологаю всеми документами
	</ul></div>
	<script>
		$(function() {
			$( "#check" ).on( "click", function() {
				if($(this).is(":checked")) {$('.asd').removeAttr('disabled') }
				else {$('.asd').attr('disabled','true')}
			})
		});
	</script>
	<script>
		$(document).ready(function(){
			localStorage.clear();
			var http = window.location.href;
			localStorage.search =http;
			var location = localStorage.search;
		})
	</script>

</div>
