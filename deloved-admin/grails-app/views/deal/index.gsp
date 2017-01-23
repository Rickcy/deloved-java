<%@ page import="ru.deloved.DealStatus" contentType="text/html;charset=UTF-8" %>

<html>
<head>
	<meta name="layout" content="admin">
	<meta name="referrer" content="origin-when-crossorigin">
	<title><g:message code="deal.list.label"/></title>


<%--<script>
		$(document).ready(function(){
		var url = document.referrer;
		var regV = 'company';
		var result = url.match(regV);
		if (result) {
			$(function (){$('.buttton').click()
				})
		} else {

		}}
		)
	</script>--%>
	<%--<script>
		$(document).ready(function(){
			var url = document.referrer;
			var regV = 'company';
			var result = url.match(regV);
			if (result) {
				$(function (){
					$("tbody.tbody1").remove()
				})
			} else {

			}}
		)
	</script>
	<script>
		$(document).ready(function(){
			var url = document.referrer;
			var regV = 'company';
			var result = url.match(regV);
			if (result) {

			} else {$(function (){
				$("tbody.tbody2").remove()
			})

			}}
		)
	</script>--%>
	<script>
	$(document).ready(function(){
		var url = window.location.href;
		var regV = 'rar';

		var result = url.match(regV);
		if (result) {
			$('.buttton1').click()
		} else {

		}}
	)
</script>
	<script>
		$(document).ready(function(){
			var url = window.location.href;
			var regV = 'sal';

			var result = url.match(regV);
			if (result) {
				$('.buttton2').click()


			} else {

			}}
		)
	</script>
	<script>
		$(document).ready(function(){
			var url = window.location.href;
			var regV = 'var';

			var result = url.match(regV);
			if (result) {
				$('.buttton3').click()


			} else {

			}}
		)
	</script>
	<script>
		$(document).ready(function(){
			var R ='Покупатель';

				var table = $('.side1');
			table.each(function(i,elem) {
				var res= $(elem).html()
			var result = res.match(R)
				if(result){this.remove()}
			}

			)
				},function(){
			var R ='Продавец';

					var table = $('.side2');
					table.each(function(i,elem) {
								var res= $(elem).html()
								var result = res.match(R)
								if(result){this.remove()}
							}

					)}

			)
	</script>
	<script>
		$(document).ready(function(){
			var R ='Продавец';

					var table = $('.side2');
					table.each(function(i,elem) {
								var res= $(elem).html()
								var result = res.match(R)
								if(result){this.remove()}
							}

					)}

			)
	</script>
	<script>
		$(document).ready(function(){
			$('#myTab a').click(function(e){
				e.preventDefault();
				$(this).tab('show');
			});
		});
	</script>


</head>

<body>
<g:if test="${myAccounts}">
	<button type="button" class="buttton1"   data-toggle="modal" data-target="#OpenDispute">
	</button>
	<button type="button" class="buttton2"   data-toggle="modal" data-target="#OpenClaim">
	</button>
	<button type="button" class="buttton3"   data-toggle="modal" data-target="#OpenReview">
	</button>

	<div class="modal fade" id="OpenDispute" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					<h4 class="modal-title ft" id="myModalLabel" style="text-align: center">Хотите Разрешить Спор? </h4>
				</div>

				<div class="modal-body" style="text-align: center">
					<p>Выбирите Сделку по которой хотите разрешить Спор!</p>
					<p>Если у вас возникли вопросы воспользуйтесь Службой поддержки!</p>

				</div>

				<div class="modal-footer">
					<%--<div id="jud_button_contunue" class="col-md-6" align="left">
						<g:link id="consultButton" name="consultButton" controller="consult" action="create" target="_blank" class="jud_button_contunue_main">Помощь юриста</g:link>
					</div>--%>
					<div class="jud_button_contunue">
						<g:link class="jud_button_contunue_main ft" controller="ticket" action="create" target="_blank">Служба поддержки</g:link>
					<button type="button" class="btn btn-default ft" data-dismiss="modal">Продолжить</button>
				</div>


				</div>
			</div>
		</div>
	</div>
	<%--Open claim--%>
	<div class="modal fade" id="OpenClaim" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					<h4 class="modal-title ft" id="myModalLabel" style="text-align: center">Хотите подать Иск?</h4>
				</div>

				<div class="modal-body" style="text-align: center">
					<p>Выбирите Сделку по которой хотите подать Иск!</p>
					<p>Если у вас возникли вопросы воспользуйтесь Службой поддержки!</p>

				</div>

				<div class="modal-footer">
					<%--<div id="jud_button_contunue" class="col-md-6" align="left">
						<g:link id="consultButton" name="consultButton" controller="consult" action="create" target="_blank" class="jud_button_contunue_main">Помощь юриста</g:link>
					</div>--%>
					<div class="jud_button_contunue">
						<g:link class="jud_button_contunue_main ft" controller="ticket" action="create" target="_blank">Служба поддержки</g:link>
					<button type="button" class="btn btn-default ft" data-dismiss="modal">Продолжить</button>
				</div>


				</div>
			</div>
		</div>
	</div>
	<%--OpenReview--%>
	<div class="modal fade" id="OpenReview" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					<h4 class="modal-title ft" id="myModalLabel" style="text-align: center">Хотите оставить озыв о партнере?</h4>
				</div>

				<div class="modal-body" style="text-align: center">
					<p>Выбирите Сделку по которой хотите оставить Отзыв!</p>
					<p>Если у вас возникли вопросы воспользуйтесь Службой поддержки!</p>

				</div>

				<div class="modal-footer">
					<%--<div id="jud_button_contunue" class="col-md-6" align="left">
						<g:link id="consultButton" name="consultButton" controller="consult" action="create" target="_blank" class="jud_button_contunue_main">Помощь юриста</g:link>
					</div>--%>
					<div class="jud_button_contunue">
						<g:link class="jud_button_contunue_main ft" controller="ticket" action="create" target="_blank">Служба поддержки</g:link>
					<button type="button" class="btn btn-default ft" data-dismiss="modal">Продолжить</button>
				</div>


				</div>
			</div>
		</div>
	</div>
</g:if>

<div id="list-deals" class="content scaffold-list" role="main">

	<h1><g:message code="deal.list.label"/></h1>

	<g:render template="/_common/flash-message"/>


	<g:form url="[controller: 'deal', action: 'index']" style="float: right;width: 70%;margin-right: 5%">
		<div class="row">

			</div>
			<div class="col-md-4" style="margin-bottom: 2%;padding-left: 0%">
				<g:select class="form-control"
						  name="status"
						  from="${DealStatus.DEAL()}"
						  optionKey="value"
						  optionValue="${{ message(code: 'deal.status.' + it, default: it) }}"

						  value="${filter.status}"
						  noSelection="${['': message(code: 'deal.status.allstatus.labell')]}" />
				</div>



			<div class="col-md-4" style="margin-bottom: 2%;padding-left: 0%"><g:textField placeholder="Поиск" class="form-control" name="Поиск" value="${filter.search}"/></div>

			<div class="col-md-4" style="margin: 0%;padding: 0%">
				<button type="submit" name="filterAction" class="btn btn-primary ft" style="margin-bottom: 3%" id="filterAction">${message(code: 'default.button.filter.label')}</button>

				<button type="submit" name="resetAction" class="btn btn-default ft" formaction="reset" value="Сбросить " style="margin-bottom: 3%" id="resetAction">${message(code: 'default.button.filterReset.label')}</button>

			</div>

		</div>

	</g:form>
<div class="row">

<sec:ifAnyGranted roles="ROLE_ACCOUNT">
	<ul id="myTab" class="nav nav-tabs" style="border-bottom: none !important;">
	<li class="dropdown">
		<a data-toggle="dropdown" class="dropdown-toggle" href="#" style="border:1px solid #dddddd !important;border-radius: 5px !important;padding: 6px 15px !important;
		color: #000000 !important;">
			Стороны сделки
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li><a href="#panel1">Все</a></li>
			<li><a href="#panel2">Продавец</a></li>
			<li><a href="#panel3">Покупатель</a></li>
		</ul>
	</li>

</ul>
</sec:ifAnyGranted>


	<div class="tab-content table-responsive">
		<div id="panel1" class="tab-pane fade in active">
			<table border="0" class="table table-striped table-hover table-responsive" id="mytable">
				<thead style="border-bottom: 3px solid rgba(176, 208, 83, 0.24)">
				<tr>
					<th>№</th>
					<th class="ft"><g:message code="deal.accounts.label" default="Deal"/></th>
					<th class="ft">Покупатель</th>
					<th class="ft">Продавец</th>
					<th class="ft"><g:message code="deal.status.label" default="Status"/></th>
					<g:sortableColumn property="dateCreated" title="${message(code: 'deal.dateCreated.label', default: 'Date Created')}" class="ft"/>
<sec:ifAnyGranted roles="ROLE_ADMIN">
	<th class="ft">Действия</th>
</sec:ifAnyGranted>


				</tr>
				</thead>

				<tbody class="tbody1">
				<g:each in="${dealInstanceList}" status="i" var="obj">
					<tr id="deal${obj.id}" class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${i+1}</td>
						<td><g:link  class="${userNewObjects.contains(obj) ? "newItem" : ""}" id="${obj.id}" controller="deal" action="thread">${obj.account.name} &amp; ${obj.partner.name}</g:link>
							<g:if test="${userNewObjectPosts[obj]}">
								<span class="badge">+${userNewObjectPosts[obj]}</span>
							</g:if>
						</td>
						<g:if test="${myAccounts}">
							<g:if test="${myAccounts.contains(obj.partner)}">
								<td id="side"><g:link controller="company" id="${obj.account.id}">${obj.account.name}</g:link></td>
								<td id="you">Вы</td>
							</g:if>
							<g:else>
								<td id="you">Вы</td>
								<td id="side"><g:link controller="company" id="${obj.partner.id}">${obj.partner.name}</g:link></td>
							</g:else>
						</g:if>
						<g:else>
							<td>${obj.partner.name}</td>
							<td>${obj.account.name}</td>
						</g:else>


						<td>${message(code: 'deal.status.' + obj.status(), default: obj.status())}
							<g:if test="${userNewObjectStatuses[obj]}">
								<span class="badge">+${userNewObjectStatuses[obj]}</span>
							</g:if>
						</td>
						<td><g:formatDate date="${obj.dateCreated}" format="dd.MM.yyyy HH:mm:ss"/></td>
						<sec:ifAnyGranted roles="ROLE_ADMIN">
							<td><a name="deleteDeal" for="${obj.id}" href="javascript:void(0)"><span class="glyphicon glyphicon-trash"></span></a></td>
						</sec:ifAnyGranted>

					</tr>
				</g:each>
				</tbody>

			</table>
		</div>
		<div id="panel2" class="tab-pane fade">
			<table border="0" class="table table-striped table-hover" id="mytable">
				<thead style="border-bottom: 3px solid rgba(176, 208, 83, 0.24)">
				<tr>
					<th>№</th>
					<th class="ft"><g:message code="deal.accounts.label" default="Deal"/></th>

					<th class="ft">Вы</th>
					<th class="ft"><g:message code="deal.status.label" default="Status"/></th>
					<g:sortableColumn property="dateCreated" title="${message(code: 'deal.dateCreated.label', default: 'Date Created')}" class="ft"/>
				</tr>
				</thead>

				<tbody class="tbody1">
				<g:each in="${dealInstanceList}" status="i" var="obj">

					<tr id="${obj.id}" class="${(i % 2) == 0 ? 'even' : 'odd'} side1">
						<td>${i+1}</td>
						<td><g:link  class="${userNewObjects.contains(obj) ? "newItem" : ""}" id="${obj.id}" controller="deal" action="thread">${obj.account.name} &amp; ${obj.partner.name}</g:link>
							<g:if test="${userNewObjectPosts[obj]}">
								<span class="badge">+${userNewObjectPosts[obj]}</span>
							</g:if>
						</td>
						<g:if test="${myAccounts}">
							<g:if test="${myAccounts.contains(obj.partner)}">

								<td id="you">Продавец</td>
							</g:if>
							<g:else>

								<td >Покупатель</td>
							</g:else>
						</g:if>
						<g:else>
							<td>${obj.partner.name}</td>
							<td>${obj.account.name}</td>
						</g:else>


						<td>${message(code: 'deal.status.' + obj.status(), default: obj.status())}
							<g:if test="${userNewObjectStatuses[obj]}">
								<span class="badge">+${userNewObjectStatuses[obj]}</span>
							</g:if>
						</td>
						<td><g:formatDate date="${obj.dateCreated}" format="dd.MM.yyyy HH:mm:ss"/></td>
					</tr>
				</g:each>
				</tbody>

			</table>
		</div>
		<div id="panel3" class="tab-pane fade">
			<table border="0" class="table table-striped table-hover" id="mytable">
				<thead style="border-bottom: 3px solid rgba(176, 208, 83, 0.24)">
				<tr>
					<th>№</th>
					<th class="ft"><g:message code="deal.accounts.label" default="Deal"/></th>
					<th class="ft">Вы</th>

					<th class="ft"><g:message code="deal.status.label" default="Status"/></th>
					<g:sortableColumn property="dateCreated" title="${message(code: 'deal.dateCreated.label', default: 'Date Created')}" class="ft"/>

				</tr>
				</thead>

				<tbody class="tbody1">
				<g:each in="${dealInstanceList}" status="i" var="obj">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'} side2">
						<td>${i+1}</td>
						<td><g:link  class="${userNewObjects.contains(obj) ? "newItem" : ""}" id="${obj.id}" controller="deal" action="thread">${obj.account.name} &amp; ${obj.partner.name}</g:link>
							<g:if test="${userNewObjectPosts[obj]}">
								<span class="badge">+${userNewObjectPosts[obj]}</span>
							</g:if>
						</td>
						<g:if test="${myAccounts}">
							<g:if test="${myAccounts.contains(obj.partner)}">
								<td>Продавец</td>

							</g:if>
							<g:else>
								<td id="you">Покупатель</td>

							</g:else>
						</g:if>
						<g:else>
							<td>${obj.partner.name}</td>
							<td>${obj.account.name}</td>
						</g:else>


						<td>${message(code: 'deal.status.' + obj.status(), default: obj.status())}
							<g:if test="${userNewObjectStatuses[obj]}">
								<span class="badge">+${userNewObjectStatuses[obj]}</span>
							</g:if>
						</td>
						<td><g:formatDate date="${obj.dateCreated}" format="dd.MM.yyyy HH:mm:ss"/></td>

					</tr>
				</g:each>
				</tbody>

			</table>
		</div>
</div>
	<g:if test="${params.max < rowsCount}">
		<g:paginate total="${rowsCount ?: 0}"/>
	</g:if>

</div>
<script>
	$(document).ready(function() {
		$('[name=deleteDeal]').click(function(event){
			if (confirm('${message(code: 'item.delete.confirm.'+params.categoryType?.code, default: 'Delete this item?')}')) {
				var dealId = $(this).attr('for');
				jQuery.ajax({
					type:'DELETE',
					url:'${createLink([controller: 'deal', action: 'deletedeal'])}/'+dealId,
					success:function(data,textStatus){
						$('#deal'+dealId).remove();
						showMessage('success', 'Успешно удалено')
					},
					error:function(XMLHttpRequest,textStatus,errorThrown){

						showMessage('danger', 'Удаление не удалось')
					}
				});
			}
		})
	})
</script>

</body>
</html>