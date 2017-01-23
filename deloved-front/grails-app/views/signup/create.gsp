	<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.codehaus.groovy.grails.commons.DefaultGrailsApplication; ru.deloved.CategoryType" %>
<%@ page import="ru.deloved.Category" %>
<html>
<head>
	<meta name="layout" content="front">
	<title>Регистрация нового пользователя</title>
	<style>
		.text_reg_1{
			color: #4b7bbe;
		}
	.shadow_block{
		position: relative;
		background-color: white;
	}
	.shadow_block:after {
		content: "";
		display: block;
		position: absolute;
		width: 104%;
		margin-left: -2%;
		z-index: -1;
		height: 20px;
		bottom: 0;
		right: 1em;
		left: 1em;
		border-radius: 50%;
		box-shadow: 0 0 10px 12px rgba(0,0,0,.2);
	}
	.shadow_block:before {
		content: "";
		display: block;
		position: absolute;
		width:104%;
		margin-left:-2%;
		z-index: -1;
		height: 20px;
		top: 0;
		right: 1em;
		left: 1em;
		border-radius: 50%;
		box-shadow: 0 0 10px 12px rgba(0,0,0,.2);
	}
	</style>
	<script>
		$(document).ready(function(){
			var inn=$('.inn').text()

			var text ='Деловед'
			var search = inn.match(text)
			if(search){$('.inn>span').css('opacity','0')
			$('.redinn').css('display','block')

			}
		})
	</script>
</head>
<body>

<g:render template="/_common/flash-message"/>


<g:hasErrors bean="${objInstance}">
	<%--
	<ul class="alert alert-danger errors" role="alert">
		<g:eachError bean="${objInstance}" var="error">
			<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
		</g:eachError>
	</ul>
	--%>
	<ul class="alert alert-danger errors" role="alert">
		<li>Заполните обязательные поля без ошибок</li>
	</ul>
</g:hasErrors>


<div class="shadow_block">

	<div class="row" style="margin-top:2%;padding: 2% 8%;width: 110%;margin-left: -5%;background-color: white">
		<h1 style="font-weight: bold;text-align:left!important;color:rgb(148, 196, 61)">Регистрация пользователя
			<div style="background-image: linear-gradient(270deg, rgb(248, 215, 53), rgb(148, 196, 61) 110%);
			width: 98%;
			height: 4px;"></div></h1>
<g:form name="editForm" url="[controller: 'signup', action: 'save']" class="form-horizontal ">
	<fieldset class="form">

		<h3 class="text_reg_1">Данные пользователя для авторизации</h3>

	<div class="signup_desc">После регистрации на вашу почту придет письмо об активации.</div>

		<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'userEmail', 'error')} required form-group">
			<label for="userEmail" class="col-sm-3 control-label">
				<g:message code="signupForm.userEmail.label" default="Email" />
				<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
			</label>
			<div class="col-sm-9">
				<g:field class="form-control" type="email" name="userEmail" required="" value="${beanResource?.userEmail}" placeholder="name@domain.ru" />
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'userEmail']"/>
			</div>
		</div>

		<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'password', 'error')} required form-group">
			<label for="password" class="col-sm-3 control-label">
				<g:message code="signupForm.password.label" default="Password"/>
				<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
			</label>
			<div class="col-sm-9">
				<g:passwordField class="form-control" name="password" required="" value="${beanResource?.password}"/>
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'password']"/>
			</div>
		</div>

		<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'passwordRepeat', 'error')} required form-group">
			<label for="passwordRepeat" class="col-sm-3 control-label">
				<g:message code="signupForm.passwordRepeat.label" default="Repeat Password"/>
				<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
			</label>
			<div class="col-sm-9">
				<g:passwordField class="form-control" name="passwordRepeat" required="" value="${beanResource?.passwordRepeat}"/>
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'passwordRepeat']"/>
			</div>
		</div>

		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'userCity', 'error')} required">
			<label class="col-sm-3 control-label" for="userCityname">
				<g:message code="signupForm.userCity.label" default="City"/>
				<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
			</label>
			<div class="col-sm-9">
				<g:render template="/_common/auto-complete" model="[
						acAction   : createLink(action: 'cities'),
						acMinLength: 2,
						acKeyValue : beanResource?.userCity?.id,
						acKeyName  : 'userCity.id',
						acViewValue: beanResource?.userCity?.name,
						acViewName : 'userCityname'
				]" />
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'userCity']"/>
			</div>
		</div>

<hr>

	<h3 class="text_reg_1">Данные предприятия/предпринимателя</h3>

	<div class="signup_desc">Заполните поля в соответствии с данными ЕГРЮЛ/ЕГРИП, уставом. Обратите внимание на примеры заполнения</div>

		<%-- Конец блока "Организационно-правовая форма" --%>
		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'orgForm', 'error')} required">
			<label class="control-label col-sm-3" for="orgForm">
				<g:message code="signupForm.orgForm.label" default="Org Form"/>
				<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
			</label>
			<div class="col-sm-9">
				<g:select class="form-control" name="orgForm" from="${ru.deloved.OrgForm.listOrderByCode()}"
						  optionKey="id" optionValue="code" value="${beanResource?.orgForm?.id}"
						  			noSelection="${['null': message(code: 'default.selection.notSelected')]}"/>
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'orgForm']"/>
				<div class="pods">Выберите организационно-правовую форму из списка. Далее ОПФ указывать не надо</div>
			</div>
		</div>
		<%-- Конец блока "Организационно-правовая форма" --%>

		<%-- Начало блока "Полное наименование" --%>
		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'fullName', 'error')} required">
			<label class="control-label col-sm-3" for="name">
				<g:message code="signupForm.fullName.label" default="Full name"/>
				<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
			</label>
			<div class="col-sm-9">
				<g:textField class="form-control" name="fullName" value="${beanResource?.fullName}" placeholder="Торговый Дом АгроТрейд" />
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'fullName']"/>
				<div class="pods">Например, Управляющая компания Город, Торговый дом УралТранс, Иванов Дмитрий Иванович (для ИП) и т.п.</div>
			</div>
		</div>
		<%-- Конец блока "Полное наименование" --%>

		<%-- Конец блока "Фирменное наименование" --%>
		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'brandName', 'error')} required">
			<label class="control-label col-sm-3" for="name">
				<g:message code="signupForm.brandName.label" default="Full name"/>
				<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
			</label>
			<div class="col-sm-9">
				<g:textField class="form-control" name="brandName" value="${beanResource?.brandName}" placeholder="ТД АгроТрейд" />
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'brandName']"/>
				<div class="pods">Торговая марка, фирменное название, под которым ведется деятельность, используется на вывесках, либо указать полное наименование</div>
			</div>
		</div>
		<%-- Конец блока "Фирменное наименование" --%>

		<%-- Начало блока "Сокращенное наименование" --%>
		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'name', 'error')} required">
				<label class="control-label col-sm-3" for="name">
					<g:message code="signupForm.name.label" default="Name"/>
					<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
				</label>

				<div class="col-sm-9">
					<g:textField class="form-control" name="name" value="${beanResource?.name}" placeholder="АгроТрейд" />
					<g:render template="/_common/error" model="[objInstance: objInstance, field: 'name']"/>
					<div class="pods">Например, УК Город, ТД УралТранс, Иванов Д.И.(для ИП) и т.п.</div>
				</div>
		</div>
	<%-- Конец блока "Сокращенное наименование" --%>

		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'regNumber', 'error')} required">
			<label class="control-label col-sm-3" for="regNumber">
				<g:message code="signupForm.regNumber.label" default="Reg Number"/>
				<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
			</label>
			<div class="col-sm-9"><g:textField class="form-control" name="regNumber" value="${beanResource?.regNumber}" placeholder="1234567890123" />
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'regNumber']"/>
				<div class="pods">Укажите государственный регистрационный номер</div>
			</div>
		</div>

		<div class="fieldcontain  form-group ${hasErrors(bean: objInstance, field: 'inn', 'error')} required">
			<label class="control-label col-sm-3" for="inn">
				<g:message code="signupForm.inn.label" default="Inn"/>
				<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
			</label>
			<div class="col-sm-9 inn"><g:textField class="form-control" name="inn" value="${beanResource?.inn}" placeholder="000505174307"  />
				<i class="redinn" style="color: red;display: none">Проверьте правильность написания</i>
			<g:render template="/_common/error" model="[objInstance: objInstance, field: 'inn']"/>

				<div class="pods">Введите индивидуальный налоговый номер</div>
			</div>
		</div>

		<%--
		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'kpp', 'error')} ">
			<label class="control-label col-sm-3" for="kpp">
				<g:message code="signupForm.kpp.label" default="Kpp" />
			</label>
			<div class="col-sm-9"><g:textField class="form-control" name="kpp" value="${beanResource?.kpp}" placeholder="010203011" />
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'kpp']"/>
				<div class="pods">Введите код причины постановки на учет, если он у вас есть (у индивидуальных предпринимателей его нет)</div>
			</div>
		</div>
		--%>

		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'legalAddress', 'error')} required">
			<label class="control-label col-sm-3" for="legalAddress">
				<g:message code="signupForm.legalAddress.label" default="Legal Address"/>
				<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
			</label>
			<div class="col-sm-9">
				<g:textField class="form-control" name="legalAddress" value="${beanResource?.legalAddress}" placeholder="г. Москва, Тверской бульвар, 12, офис 14" />
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'legalAddress']"/>
				<div class="pods">Укажите юридический адрес или адрес регистрации(для ИП)</div>
			</div>
		</div>

		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'regDate', 'error')} required">
			<label class="control-label col-sm-3" for="regDate">
				<g:message code="signupForm.regDate.label" default="Reg Date"/>
				<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
			</label>
			<div class="col-sm-9">
				<g:datePicker name="regDate" precision="day" value="${beanResource?.regDate}"/>
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'regDate']"/>
				<div class="pods">Укажите дату регистрации в соответсвиии с данными ЕГРЮЛ или ЕГРИП</div>
			</div>
		</div>

	<%-- Начало блока "Адрес центрального офиса" --%>
		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'city', 'error')} required">
			<label class="control-label col-sm-3" for="cityname">
				<g:message code="signupForm.centralOffice.label" default="City"/>
				<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
			</label>
			<div class="col-sm-3">
				<g:render template="/_common/auto-complete" model="[
						acAction   : createLink(action: 'cities'),
						acMinLength: 2,
						acKeyValue : beanResource?.city?.id,
						acKeyName  : 'city.id',
						acViewValue: beanResource?.city?.name,
						acViewName : 'cityname'
				]"/>
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'city']"/>

				<div class="pods">Укажите город центрального офиса</div>
			</div>
			<div class="col-sm-6">
				<g:textField class="form-control" name="address" value="${beanResource?.address}" placeholder="ул. Тверская, 12, офис 1" />
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'address']"/>
				<div class="pods">Укажите адрес центрального офиса</div>
			</div>
		</div>

		<%--
		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'address', 'error')} ">
			<label class="control-label col-sm-3" for="address">
				<g:message code="signupForm.address.label" default="Address"/>
			</label>
			<div class="col-sm-9">
				<g:textField class="form-control" name="address" value="${beanResource?.address}" placeholder="ул. Тверская, 12, офис 1" />
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'address']"/>
				<div class="pods">Укажите адрес ведения деятельности в формате: улица, номер дома, номер офиса</div>
			</div>
		</div>
		--%>

		<%-- Конец блока "Адрес центрального офиса --%>

		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'manager', 'error')} required">
			<label class="control-label col-sm-3" for="manager">
				<g:message code="signupForm.manager.label" default="Manager"/>
				<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
			</label>
			<div class="col-sm-9">
				<g:textField class="form-control" name="manager" value="${beanResource?.manager}" placeholder="Иванов Иван Иванович" />
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'manager']"/>
				<div class="pods">Укажите, в соответствии с ЕГРЮЛ, Ф.И.О. руководителя или название управляющей компании, либо ФИО(для ИП)</div>
			</div>
		</div>

		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'phone1', 'error')} required">
			<label class="control-label col-sm-3" for="phone1">
				<g:message code="signupForm.phone1.label" default="Phone1"/>
				<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
			</label>
			<div class="col-sm-9">
				<g:textField class="form-control" name="phone1" value="${beanResource?.phone1}" placeholder="+7 (945) 222-33-44" />
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'phone1']"/>
				<div class="pods">Укажите основной номер телефон в формате +7 (945) 222-33-44 </div>
			</div>
		</div>

		<%--
		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'phone2', 'error')} ">
			<label class="control-label col-sm-3" for="phone2">
				<g:message code="signupForm.phone2.label" default="Phone2"/>
			</label>
			<div class="col-sm-9">
				<g:textField class="form-control" name="phone2" value="${beanResource?.phone2}" placeholder="+7 (945) 222-33-44"/>
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'phone2']"/>
				<div class="pods">Укажите дополнительный номер телефон в формате +7 (945) 222-33-44 </div>
			</div>
		</div>

		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'phone3', 'error')}">
			<label class="control-label col-sm-3" for="phone3">
				<g:message code="signupForm.phone3.label" default="Phone3"/>
			</label>
			<div class="col-sm-9">
				<g:textField class="form-control" name="phone3" value="${beanResource?.phone3}" placeholder="+7 (945) 222-33-44"/>
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'phone3']"/>
				<div class="pods">Укажите дополнительный номер телефон в формате +7 (945) 222-33-44</div>
			</div>
		</div>
		--%>

		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'fax1', 'error')} ">
			<label class="control-label col-sm-3" for="fax1">
				<g:message code="signupForm.fax1.label" default="Fax1"/>
			</label>
			<div class="col-sm-9">
				<g:textField class="form-control" name="fax1" value="${beanResource?.fax1}" placeholder="+7 (945) 222-33-44"/>
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'fax1']"/>
				<div class="pods">Укажите основной факс в формате +7 (945) 222-33-44 </div>
			</div>
		</div>

		<%--
		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'fax2', 'error')} ">
			<label class="control-label col-sm-3" for="fax2">
				<g:message code="signupForm.fax2.label" default="Fax2"/>
			</label>
			<div class="col-sm-9"><g:textField class="form-control" name="fax2" value="${beanResource?.fax2}" placeholder="+7 (945) 222-33-44"  />
				<div class="pods">Укажите дополнительный факс в формате +7 (945) 222-33-44 </div>
			</div>
		</div>
		--%>

		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'workTime', 'error')} required">
			<label class="control-label col-sm-3" for="workTime">
				<g:message code="signupForm.workTime.label" default="Work Time"/>
				<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
			</label>
			<div class="col-sm-9">
				<g:textField class="form-control" name="workTime" value="${beanResource?.workTime}" placeholder="ПН-ПТ: 9:30 - 18.00, СБ, ВС: Выходной"  />
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'workTime']"/>
				<div class="pods">Укажите время работы в свободной форме</div>
			</div>
		</div>

		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'webAddress', 'error')} ">
			<label class="control-label col-sm-3" for="webAddress">
				<g:message code="signupForm.webAddress.label" default="Web Address"/>
			</label>
			<div class="col-sm-9">
				<g:textField class="form-control" name="webAddress" value="${beanResource?.webAddress}" placeholder="http://www.domain.ru"  />
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'webAddress']"/>
				<div class="pods">Укажите адрес сайта в формате http://www.domain.ru</div>
			</div>
		</div>

		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'email', 'error')} ">
			<label class="control-label col-sm-3" for="email">
				<g:message code="signupForm.email.label" default="Email Address"/>
			</label>
			<div class="col-sm-9">
				<g:field class="form-control" type="email" name="email" value="${beanResource?.email}" placeholder="info@domain.ru"  />
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'email']"/>
				<div class="pods">Укажите фирменную электронную почту в формате info@domain.ru</div>
			</div>
		</div>

		<div class="fieldcontain  form-group ${hasErrors(bean: objInstance, field: 'description', 'error')} ">
			<label class="control-label col-sm-3" for="description">
				<g:message code="signupForm.description.label" default="Description"/>
			</label>
			<div class="col-sm-9">
				<g:textArea class="form-control" style="resize: none" name="description" value="${beanResource?.description}" rows="7" maxlength="1500"  />
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'description']"/>
				<div class="pods">Опишите в свободной форме вашу деятельность</div>
			</div>
		</div>

		<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'keywords', 'error')} ">
			<label class="control-label col-sm-3" for="keywords">
				<g:message code="signupForm.keywords.label" default="Keywords"/>
			</label>
			<div class="col-sm-9">
				<g:textArea class="form-control" style="resize: none" name="keywords" value="${beanResource?.keywords}"/>
				<g:render template="/_common/error" model="[objInstance: objInstance, field: 'keywords']"/>
				<div class="pods">Напишите через запятую ключевые слова, связанные с вашей деятельностью</div>
			</div>
		</div>
		<hr>
	<h3 class="text_reg_1">Категории деятельности</h3>

	<div class="signup_desc">Отметьте галочками ветки категорий товаров и услуг, которые относятся к виду вашей деятельности.</div>

<div class="fieldcontain form-group">

	<label class="control-label col-sm-3">


	</label>

	<div class="col-sm-9">
		<g:set var="typeList" value="${ru.deloved.CategoryType.listOrderById()}"/>

		<div class="tab-pane" id="cat" >
			<ul class="nav nav-pills" style="margin-bottom: 20px">
				<g:each in="${typeList}" status="i" var="typeInstance">
					<li style="font-size: 16pt;" class="${i == 0 ? ' active' : ''}"><a href="#${typeInstance.code}" data-toggle="tab"><g:message code="categorytype.${typeInstance.code}"/></a></li>
				</g:each>
			</ul>

			<div class="tab-content">
				<g:each in="${typeList}" status="i" var="typeInstance">
					<div class="tab-pane ${i == 0 ? 'active' : ''}" id="${typeInstance.code}">
						<div id="${typeInstance.code}tree">
						</div>
					</div>
				</g:each>
			</div>

		</div>

		<script type="application/javascript">
			jQuery(function () {

				$('#editForm').submit(function () {
					var form = $(this);
					<g:each in="${typeList}" status="i" var="typeInstance">
					jQuery.each(jQuery('#${typeInstance.code}tree').jstree(true).get_selected(), function (i, v) {
						form.append('<input type="hidden" name="cat" value="' + v + '" />');
					});
					</g:each>
				});

				<g:each in="${typeList}" status="i" var="typeInstance">
				jQuery('#${typeInstance.code}tree')
						.on('changed.jstree', function (e, data) {
							if (data.node) {
								if (!data.instance.is_leaf(data.node)) {
									data.instance.deselect_node(data.node.children_d, true);
									data.instance.deselect_node(data.node.parents, true);
									if (data.instance.is_selected(data.node)) {
										data.instance.close_node(data.node);
									} else {
										data.instance.open_node(data.node);
									}
								} else {
									data.instance.deselect_node(data.node.parents, true);
								}
							}
						})
						.jstree({
							"core": {
								"multiple": true,
								"data": {
									"url": "${createLink(action: 'cat')}",
									"data": function (n) {
										return {
											"pid": n.id && n.id != '#' ? n.id : ${ Category.findByTypeAndParent(typeInstance, null).id }
										}
									}
								}
							},
							"checkbox": {
								"three_state": false,
								"cascade": "undetermined"
							},
							"plugins": [ "json_data", "checkbox", "wholerow"]
						});
				</g:each>
			});
		</script>

	</div>

</div>

<hr>
	</fieldset>

	<div style="margin-bottom: 15px" align="center">

	<div class="${hasErrors(bean: objInstance, field: 'agreement', 'error-div')}">
		<input type="checkbox" name="agreement" value="true" ${beanResource?.agreement == true ? 'checked' : ''}>
		Я согласен с условиями <a href="javascript:void(0)">Пользовательского соглашения</a>
		<g:render template="/_common/error" model="[objInstance: objInstance, field: 'agreement']"/>
	</div>

	<div class="${hasErrors(bean: objInstance, field: 'recaptcha', 'error-div')}">
	<recaptcha:ifEnabled><recaptcha:recaptcha theme=""/></recaptcha:ifEnabled>
	<g:render template="/_common/error" model="[objInstance: objInstance, field: 'recaptcha']"/>
	</div>

	</div>

	<fieldset class="buttons" style="text-align: right">
		<g:submitButton name="create" class="btn btn-md btn-success" value="Завершить регистрацию" style=" font-size: 25px;"/>
	</fieldset>
</g:form>
</div>
	</div>



<button type="button" class="but" style="opacity: 0"   data-toggle="modal" data-target="#inn">
</button>
<div class="modal fade" id="inn" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>

			</div>

			<div class="modal-body">
				<p>Проверьте правильность написания ИНН</p>


			</div>

			<div class="modal-footer">
				<%--<div id="jud_button_contunue" class="col-md-6" align="left">
                    <g:link id="consultButton" name="consultButton" controller="consult" action="create" target="_blank" class="jud_button_contunue_main">Помощь юриста</g:link>
                </div>--%>
				<div class="jud_button_contunue">

					<button type="button" class="btn btn-default ft" data-dismiss="modal">Продолжить</button>
				</div>


			</div>
		</div>
	</div>
</div>

</body>
</html>