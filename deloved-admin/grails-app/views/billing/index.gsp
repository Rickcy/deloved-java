<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="ru.deloved.Account" %>
<%@ page import="ru.deloved.Profile" %>
<head>
	<meta name='layout' content='admin'/>
	<title>Лицевой счет</title>
</head>
<body>
<h1><g:message code="billing.list.label"/></h1>
<g:render template="/_common/flash-message"/>
<script>
	console.log(${freeAccount})
</script>
<g:if test="${freeAccount}">
	<script>
		$(document).ready(function(){
			var SelectOption = $('#selectMethod>option');
			var word = 'Оплата по счету';
			SelectOption.each(function(i,elem){
				var element = $(elem);
				var elementWord = $(elem).text();
				if(!elementWord.match(word)){
					element.remove()
				}
			})
		})
	</script>

</g:if>
<g:if test="${profile.dateCreated.dateString==profile.getChargeTill().minus(31).dateString}">
	<script>
		$(document).ready(function(){
			var SelectOption = $('#selectMethod>option');
			var word = 'Оплата по счету';
			SelectOption.each(function(i,elem){
				var element = $(elem);
				var elementWord = $(elem).text();
				if(!elementWord.match(word)){
					element.remove()
				}
			})
		})
	</script>
</g:if>

<g:if test="${profile.chargeTill.date - new Date().date<=4&&profile.chargeTill.month-new Date().month==0&&profile.chargeTill.year-new Date().year==0}">
	<p style="text-align: center;font-size: 12pt;color: #0bc617">Расширенная подписка, истекает <g:formatDate date="${profile.chargeTill}" format="dd.MM.yyyy"/> </p>
</g:if>
<g:if test="${keeper}">

	<div align="center">

		<g:render template="addfunds" model="[
				tariffs: tariffs,
				methods: methods
		]"/>

		<br>

		<g:render template="history" model="[
				requestInstanceList: requestInstanceList,
				requestInstanceTotal: requestInstanceTotal
		]"/>

	</div>

</g:if>

<g:else>
	<g:form url="[action: 'account']" method="POST">
		<g:submitButton name="create" class="btn btn-primary ft" value="Создать счет"/>
	</g:form>
</g:else>

</body>
</html>