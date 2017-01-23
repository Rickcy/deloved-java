<g:if test="${dealCreateCommandInstance.account}">
	<g:hiddenField name="account" value="${dealCreateCommandInstance.account}"/>
</g:if>

<g:hiddenField name="partner" value="${dealCreateCommandInstance.partner}"/>




<script>
	console.log(${freeAccount})
</script>

<g:if test="${myAccounts}">
	<div class="fieldcontain required form-group">
		<label for="account" class="col-sm-3 control-label">
			<g:message code="deal.account.label"/>
			<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
		</label>

		<div class="col-sm-9">
			<g:select class="form-control" name="account" from="${myAccounts}" optionKey="id" optionValue="name"/>
		</div>
	</div>
</g:if>


<!-- Выбор роли при инициации сделки. Добавлено 02.06.2015 -->
<div class="fieldcontain form-group ${hasErrors(bean: beanResource, field: 'ifBuyer', 'error')}">

	<label class="control-label col-sm-3" for="chargeStatus">
		<g:message code="deal.role.label" default="DealRole"/>
	</label>

	<div class="col-sm-5">
		<div class="btn-group" data-toggle="buttons">

			<label class="btn btn-default active">
				<g:radio name="isBuyer" value="true" checked="true"/>Я хочу купить
			</label>

			<label class="btn btn-default">
				<g:radio name="isBuyer" value="false"/>Я хочу продать
			</label>
		</div>
	</div>


</div>
</div>
<!-- Выбор роли при инициации сделки -->

<div style="opacity: 0" class="fieldcontain ${hasErrors(bean: dealInstance, field: 'subject', 'error')} required form-group">
	<label for="subject" class="col-sm-3 control-label">
		<g:message code="deal.subject.label"/>
		<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
	</label>




	<div class="col-sm-9">

		<g:textArea  style="height: 0px;cursor: default" class="form-control text" name="subject" required="" value="${dealCreateCommandInstance?.subject}">
		</g:textArea>
	</div>

</div>
<script>
	$(document).ready(function(){
		var x =localStorage.search;
		var zxc =localStorage.find;


		if(x==undefined){}else {
			$('.buttons').css('width', '100%')
			var w = $('.goodsandservice').text("Наименование :" + x)
			var e = $('.text').text(x)
			var q = 'Я хочу купить';
			var p = 'Я хочу продать';
			if (zxc == undefined) {
				$('.asd').click(function () {
					var t = $('.btn-group>.active').text();
					var res = t.match(q);
					if (res) {
						$('.text').text('Вам предлогается сделка по покупке' + ' ' + x)
					}
					else {
						$('.text').text('Вам предлогается сделка по продаже' + ' ' + x)
					}

				})
			} else {
				$('.btn-group>.active:first-child').text()
				$('.asd').click(function () {
					var t = $('.btn-group>.active').text();
					var res = t.match(q);
					if (res) {
						$('.text').text('Вам предлогается сделка по ' + ' ' + zxc)
					}
					else {
						$('.text').text('Вам предлогается сделка по ' + ' ' + zxc)
					}
				})


			}

		}
	})
</script>