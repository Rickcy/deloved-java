	<%@ page import="ru.deloved.DealStatus" %>
<%-- Универсальный шаблон для смены статусов в сделках, судах, спорах, консультациях и прочих производных от треда --%>
	<script type="text/javascript" language="javascript">
		$(document).ready(function() {
			$('.dropdown-menu>li>a').css('padding','10px')

			$("#messageForm").submit(function (event) {
				$('#counter').html(+$('#counter').html()+1);
				var q =$('#counter').html()

				if(q%5==0) {

					$('.btn-group').addClass('open');
					$('#statusListButton').text('Доступные статусы').css('color', 'red')
					$('.dropdown-menu>li>a').css('color','red')

					setTimeout(function () {
						$('.btn-group').removeClass('open')

						$('.dropdown-menu>li>a').css('color','black')
						$('#statusListButton').text('Статусы сделки').css('color', 'black')

					}, 10000)

				}});
		;
		});
	</script>


	<div style="display:none;" id="counter">0</div>

	<div id="statusListBlock" class="well">
		<div class="row">
			<div class="col-md-5">
				<div class="btn-group" onmouseenter="$('.btn-group').addClass('open')" onmouseleave="$('.btn-group').removeClass('open')">
					<button style="width: 100%;border-radius: 5px;border: 1px solid rgba(255, 0, 0, 0.36)" id="statusListButton" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" ${statusList ? '' : 'disabled'}>
						Статусы сделки <span class="caret"></span>
					</button>
					<ul id="statusListMenu" class="dropdown-menu" style="top: 90%;min-width:220px;" >
						<g:if test="${statusList}">
							<g:each in="${statusList}" var="st">
								<li>
									<a href="javascript:void(0)" onclick="return changeStatus(${st.value});">
										${message(code: 'deal.status.' + st + '.i', default: st)}
									</a>
								</li>
							</g:each>
						</g:if>
					</ul>
				</div>

				<form id="statusForm" name="statusForm" action="" method="post">
					<g:hiddenField name="newstatus" value=""/>
				</form>

				</div>
			<div class="col-md-7">
				<div class=red style="float:right; font-size: 12pt; padding: 10px;">
				Выбирайте статус в зависимости от этапа сделки!
				</div>
			</div>
		</div>
	<!-- HTML код модального окна-->
	<div id="changeStatusModal" class="modal fade">
		<div class="modal-dialog" style="width: 70%!important;margin: 5% auto">
			<div class="modal-content"  >
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title text-center">Подтверждение изменения статуса сделки</h4>
				</div>
				<div id="changeStatusMessage" class="modal-body">
					<p>Вы действительно хотите сменить статус сделки?</p>

				</div>
				<div class="modal-footer">

					<div id="consultDiv" class="col-md-8" align="left">
						<g:link id="consultButton" name="consultButton" controller="juristConsult" action="create" target="_blank" class="btn btn-primary">Консультация юриста</g:link>
					</div>

					<div align="right">
						<button id="submitButton"  name="submitButton" style="margin-bottom: 10px;float: left" class="btn btn-success"onclick="setNewStatus();">ОК</button>
						<button id="dismissButton" type="button" style="margin-bottom: 10px;float: left" class="btn btn-danger" data-dismiss="modal">Отмена</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



