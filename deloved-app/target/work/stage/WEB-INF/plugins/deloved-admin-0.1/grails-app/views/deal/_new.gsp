<g:if test="${dealCreateCommandInstance.account}">
	<g:hiddenField name="account" value="${dealCreateCommandInstance.account}"/>
</g:if>

<g:hiddenField name="partner" value="${dealCreateCommandInstance.partner}"/>



<div class=info style="padding:15px">Укажите в текстовом поле ниже суть вашей сделки. Опишите, какие товары и услуги интересуют вас, либо, если вы предлагаете свои, расскажите о них максимально подробно</div>


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

<div class="fieldcontain ${hasErrors(bean: dealInstance, field: 'subject', 'error')} required form-group">
	<label for="subject" class="col-sm-3 control-label">
		<g:message code="deal.subject.label"/>
		<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
	</label>




	<div class="col-sm-9">

		<g:textArea style="min-height: 200px" class="form-control" name="subject" required="" value="${dealCreateCommandInstance?.subject}"/>

	</div>

</div>
