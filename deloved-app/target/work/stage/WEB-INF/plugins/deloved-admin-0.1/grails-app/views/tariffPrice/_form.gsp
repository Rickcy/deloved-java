<%@ page import="ru.deloved.billing.TariffPrice" %>

<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'name', 'error')}required form-group">
	<label for="name" class="col-sm-3 control-label">
		<g:message code="tariff.name.label" default="Name"/>
		<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
	</label>

	<div class="col-sm-9">
		<g:textField name="name" class="form-control" value="${objInstance?.name}"/>
	</div>
</div>
<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'price', 'error')} required form-group">
	<label class="col-sm-3 control-label" for="price">
		<g:message code="item.price.label" default="Price"/>
		<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
	</label>

	<div class="col-sm-6">
		<g:textField name="price" class="form-control" value="${formatNumber(number: (objInstance?.price) ?: 0, format: '###,##0.00')}" required=""/>
	</div>
	<div class="col-sm-3">
		<g:select id="currency" class="form-control" name="currency.id" from="${ru.deloved.SystemCurrency.findAll()}" optionKey="id" optionValue="${{it.getSymbol(requestLocale)}}"
				  required=""
				  value="${objInstance?.currency?.id}"/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'days', 'error')}form-group">
	<label for="days" class="col-sm-3 control-label">
		<g:message code="tariff.days.label" default="Days"/>
	</label>

	<div class="col-sm-9">
		<g:textField name="days" class="form-control" value="${objInstance?.days}"/>
	</div>
</div>
<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'weeks', 'error')}form-group">
	<label for="weeks" class="col-sm-3 control-label">
		<g:message code="tariff.weeks.label" default="Weeks"/>
	</label>

	<div class="col-sm-9">
		<g:textField name="weeks" class="form-control" value="${objInstance?.weeks}"/>
	</div>
</div>
<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'months', 'error')}form-group">
	<label for="months" class="col-sm-3 control-label">
		<g:message code="tariff.months.label" default="Months"/>
	</label>

	<div class="col-sm-9">
		<g:textField name="months" class="form-control" value="${objInstance?.months}"/>
	</div>
</div>
<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'years', 'error')}form-group">
	<label for="years" class="col-sm-3 control-label">
		<g:message code="tariff.years.label" default="Years"/>
	</label>

	<div class="col-sm-9">
		<g:textField name="years" class="form-control" value="${objInstance?.years}"/>
	</div>
</div>

