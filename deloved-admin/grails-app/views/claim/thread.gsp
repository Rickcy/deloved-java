<%@ page import="ru.deloved.Claim" %>
<%@ page import="ru.deloved.ClaimStatus" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="admin">
	<title>Чат Иска</title>
	<asset:javascript src="/dropzonejs/dropzone.min.js"/>
	<asset:javascript src="/mCustomScrollbar/jquery.mCustomScrollbar.concat.min.js"/>
	<asset:link rel="stylesheet" href="/mCustomScrollbar/jquery.mCustomScrollbar.min.css"/>
	<asset:link rel="buttons" href="/mCustomScrollbar/mCSB_buttons.png"/>
	<script>

		$(window).load(function(){
			$("#scrollContent").mCustomScrollbar({
				alwaysShowScrollbar: 1,
				advanced:{
					updateOnContentResize: true,
					updateOnImageLoad: true
				},
				callbacks:{
					whileScrolling: function () {

					},
					onInit: function() {
						$('#scrollContent').mCustomScrollbar('scrollTo','bottom');
					},
					onUpdate: function(){
						if (checkBottomEdge() == true) {
							$('#scrollContent').mCustomScrollbar('scrollTo','bottom');
						}
					},
				}
			});
		});

		function checkBottomEdge() {
			var scrollbar = $('#mCSB_1_scrollbar_vertical').height()
			var dragger = $('#mCSB_1_dragger_vertical').height()
			var draggerPosition = $('#mCSB_1_dragger_vertical').position().top
			var pctButton =  (1 - (dragger + draggerPosition)/scrollbar)
			var psA =  49 / $('#postsArea3').height()
			if (pctButton < psA) {
				return true
			} else {
				return false
			}
		}

	</script>
</head>

<body>

<g:if test="${freeAccount}">
	<g:render template="/_common/promo"/>
</g:if>
<g:else>

	<div id="claim-thread" class="content scaffold-list" role="main">
		<div class="result"></div>
		<g:render template="/_common/flash-message"/>

		<g:link class="btn btn-default" action="index"><span class="glyphicon glyphicon-chevron-left"></span> <g:message code="claim.backallclaims"/></g:link>

		<br>    <br>

		<div>
			<label for="disputeId">Номер иска:</label>
			<span id="disputeId">${claimInstance.id}</span>
		</div>

			<div>
				<label for="deal">Сделка:</label>
				<span id="deal"><g:link url="[resource: claimInstance.deal, action: 'thread']">Иск открыт по данной сделке</g:link></span>
			</div>

		<div>
			<label for="status">Статус иска:</label>
			<span id="status">${message(code: 'claim.status.' + claimInstance.status(), default: claimInstance.status())}</span>
		</div>
		<g:if test="${claimInstance.judge}">
			<div>
				<label for="judge">Судья:</label>
				<a id="judge" href="javascript:void(0)">${claimInstance.judge.fio}</a>
			</div>

			<script>
				$(document).ready(function(){
					$("#judge").click(function(){
						$("#judgeModal").modal("show");
					});

				});

			</script>

			<div id="judgeModal" class="modal fade" style="z-index: 1000000">

				<div class="modal-dialog" align="center">

					<div class="modal-content" style="width: 120%">

						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title" style="text-align: center">${claimInstance.judge.fio}</h4>
						</div>


						<div class="modal-body">






							<div class="row">
								<div class="label-col ft">
									<label>Фото:</label>
								</div>
								<div class="value-col">
									<div name="avatar">
			<g:if test="${claimInstance.judge.avatarThumb}">
										<img
											 src="${createLink(controller: 'profile', action: 'avatar', id: claimInstance.judge.id, params: [name: claimInstance.judge.avatarThumb?.file])}"/>
								</g:if>
										<g:else>
										<img class="avatar without-avatar" src="${resource(dir: 'image', file: 'admin/avatar.jpg')}"/>
									</g:else>
										</div>
								</div>

							</div>
							<br>
							<div class="row">
								<div class="label-col ft">
									<label for="city">Город</label>
								</div>
								<div class="value-col ft">
									<input id="city" name="city" readonly value="${claimInstance.judge?.city?.name}" data-old="${claimInstance.judge?.city?.name}"
										   placeholder="Отсутствует"/>


								</div>

							</div>

							<br>


								<div class="row">
									<div class="label-col ft">
										<label for="cellPhone">Стаж работы</label>
									</div>
									<div class="value-col ft">
										<input id="cellPhone" name="cellPhone" readonly value="${claimInstance.judge.dayOfWork}" data-old="${claimInstance.judge.dayOfWork}"
											   placeholder="Отсутствует"/>


										<img id="cellPhonespinner" style="display: none" src="${resource(dir: 'image', file: 'spinner.gif')}"/>

									</div>


								</div>


							<br>

							<div class="row">
								<div class="label-col ft">
									<label for="dateCreated">Дата регистрации</label>
								</div>
								<div class="value-col ft">
									<p id="dateCreated" name="dateCreated"><g:formatDate date="${claimInstance.judge.dateCreated}" format="dd.MM.yyyy"/></p>
								</div>

							</div>

						</div>



					</div>
				</div>
			</div>
		</g:if>

		<g:render template="/_common/thread-accounts" model="[
				account     : claimInstance.account,
				partner     : claimInstance.partner,
				accountUsers: dealUsers1,
				partnerUsers: dealUsers2
		]"/>
		<hr>
		<sec:ifAnyGranted roles="ROLE_ACCOUNT">
			<g:if test="${myAccount==claimInstance.account}">
				<div class="col-sm-12 w" style="padding: 20px;font-size: 11pt">
					<p>Для рассматрения иска прикрепите документы</p>
					<ul style="list-style:circle;">
						<li style="padding: 5px">Исковое заявление</li>
						<li style="padding: 5px">Документ об оплате третейского сбора(<a target="_blank" href="http://delovedsud.ru/treteiski-sbor/calculator.php">Расчет третейского сбора</a>)</li>
						<li style="padding: 5px">Договор или соглашение, содержащее третейскую оговорку</li>

					</ul></div>
		</g:if>

		</sec:ifAnyGranted>

		<sec:ifAnyGranted roles="ROLE_ACCOUNT,ROLE_JUDGE">
			<g:render template="/_common/thread" model="[
					threadPosts       : posts,
					threadAccount     : myAccount ?: claimInstance.account,
					threadStatusPrefix: 'claim.status'
			]"/>

		</sec:ifAnyGranted>
		<sec:ifAnyGranted roles="ROLE_ADMIN">
			<div id="scrollContent" class="scrollContent">

				<div id="postsArea3">
					<g:render template="/_common/thread-post" model="[
							threadPosts       : posts,
							threadAccount     : myAccount ?: claimInstance.account,
							threadStatusPrefix: 'claim.status'
					]"/>


				</div>

				<span class="glyphicon glyphicon-upload arrows" id="up"></span>

			</div>

		</sec:ifAnyGranted>
		<g:if test="${statusList}">
			<div class="row" style="margin-top: 10px">
				<div class="col-md-1"></div>

				<div class="col-md-10" style="border-radius: 5px;padding: 10px;
				background-color: #f4f4f4;box-shadow: inset 0px 0px 10px #d7d7d7">



					<script type="application/javascript">
						function setStatus(cond, status) {
							if (confirm('${message(code: 'claim.button.status.confirm.message')}')) {
								switch (cond) {
									case 1:
										if (status == ${ClaimStatus.Processing.value()}){
											$('#newstatus').val(status);
											$('#statusForm').submit();
										}
										if (status == ${ClaimStatus.Resolve.value()}) {
											$('#dealstatus').show();
											$('#dealstatus2').hide();
										}if (status == ${ClaimStatus.ResolveWS.value()}) {
										$('#dealstatus3').show();
										$('#dealstatus2').hide();

									}
										if (status == ${ClaimStatus.Return.value()}) {
											$('#dealstatus5').show();
											$('#dealstatus2').hide();

										}else if (status == ${ClaimStatus.Failed.value()}) {
										$('#dealstatus').hide();
										$('#dealstatus2').show();

									}
										break;
									case 2:
										$('#newstatus').val(${ClaimStatus.Resolve.value()});
										$('#new2status').val(status);
										$('#statusForm').submit();
										break;
									case 5:
										$('#newstatus').val(${ClaimStatus.Return.value()});
										$('#new2status').val(status);
										$('#statusForm').submit();
										break;
									case 3:
										$('#newstatus').val(${ClaimStatus.Failed.value()});
										$('#failedSide').val(status);
										$('#statusForm').submit();
										break;
									case 4:
										$('#newstatus').val(${ClaimStatus.ResolveWS.value()});
										$('#new2status').val(status);
										$('#statusForm').submit();
										break;
								}
							}
							return false;
						}
					</script>

					<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle"
								data-toggle="dropdown">Изменение статуса иска <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<g:each in="${statusList}" var="st">
								<li><a href="#"
									   onclick="return setStatus(1,${st.value()});">${message(code: 'claim.status.' + st, default: st)}</a>
								</li>
							</g:each>
						</ul>
					</div>
					<g:if test="${claimInstance.deal}">
						<div id="dealstatus" class="btn-group" style="display: none;">
							<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown">Изменение статуса сделки <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<g:each in="${status2List}" var="st">
									<li <g:if test="${st == ru.deloved.DealStatus.Suspended}">class="disabled" disabled </g:if>>
										<a href="#" onclick="return setStatus(2,${st.value()});">${message(code: 'deal.status.' + st, default: st)}</a>
									</li>
								</g:each>
							</ul>
						</div>
						<div id="dealstatus5" class="btn-group" style="display: none;">
							<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown">Изменение статуса сделки <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<g:each in="${status2List}" var="st">
									<li <g:if test="${st == ru.deloved.DealStatus.Suspended}">class="disabled" disabled </g:if>>
										<a href="#" onclick="return setStatus(5,${st.value()});">${message(code: 'deal.status.' + st, default: st)}</a>
									</li>
								</g:each>
							</ul>
						</div>

						<div id="dealstatus2" class="btn-group" style="display: none;">
							<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown">Изменение рейтинга <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#" onclick="return setStatus(3, 1);">${message(code: 'deal.failed.b', args: [claimInstance.account.name])}</a></li>
								<li><a href="#" onclick="return setStatus(3, 2);">${message(code: 'deal.failed.b', args: [claimInstance.partner.name])}</a></li>
							</ul>
						</div>
						<div id="dealstatus3" class="btn-group" style="display: none;">
							<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown">Изменение рейтинга <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#" onclick="return setStatus(4, 2);">${message(code: 'deal.failed.bw', args: [claimInstance.account.name])}</a></li>
								<li><a href="#" onclick="return setStatus(4, 1);">${message(code: 'deal.failed.bw', args: [claimInstance.partner.name])}</a></li>
							</ul>
						</div>



					</g:if>


					<div class=red style="float: right">Внимание! Все изменения статуса фиксируются в ленте иска!</div>

					<g:form id="statusForm" url="[resource: claimInstance, action: 'post']">
						<g:hiddenField name="version" value="${claimInstance?.version}"/>
						<g:hiddenField name="newstatus" value=""/>
						<g:hiddenField name="new2status" value=""/>
						<g:hiddenField name="failedSide" value=""/>
					</g:form>
				</div>
			</div>
		</g:if>

		<g:if test="${canPost}">
<sec:ifAnyGranted roles="ROLE_ACCOUNT">
	<form id="messageForm" name="messageForm" class="form-horizontal add" action="" method="post" style="margin-top: 10px" >
		<g:render template="/_common/hint" model="[hintText: 'Удалить сообщение можно путем наведения на сообщение! Доступно в течении 5 минут после публикации.']"/>
		<g:hiddenField name="version" value="${claimInstance?.version}"/>
		<g:textArea required="" id="messageText" class="form-control" name="post" rows="4" placeholder="Ваше сообщение"/>
		<div class="pods">Подсказка: Enter - отправить сообщение, Ctrl+Enter - перенос строки</div>
	</form>
	<div class="row">
		<div class="col-sm-12" style="margin-top: 10px">


			<button  id="messageSubmit" class="btn btn-success dsa e" name="messageSubmit" type="submit" form="messageForm" style="margin-bottom: 10px;border-radius: 15px; float: left;width: 220px;">
				<i class="glyphicon glyphicon-comment"></i>
				${message(code: 'claim.send.label')}
			</button>
			<button onclick="setTimeout(function(){location.reload()},2000)"  id="messageSubmit" class="btn btn-success dsa q" name="messageSubmit" type="submit" form="messageForm" style="margin-bottom: 10px;border-radius: 15px; float: left;width: 220px;">
				<i class="glyphicon glyphicon-comment"></i>
				${message(code: 'claim.send.label')}
			</button>


			<g:render template="/_common/upload-multi" model="[
					objInstance: claimInstance,
					attachList : attach
			]"/>

		</div>

	</div>
	<script>
		$(document).ready(function(){
			var text = 'Документы'
			$('.fileinput-button').click(function(){
				$('#messageText').text(text)})
			var mes = $('.thread-post').text();



			var result = mes.match(text)

			if(result){
				$('.add').css('display','block')
				$('.e').css('display','block')
				$('.q').css('display','none')
				$('.w').css('display','none')
			}else {
				$('.add').css('display','none')
				$('.e').css('display','none')
				$('.q').css('display','block')
				$('.w').css('display','block')
			}


		})
	</script>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles="ROLE_JUDGE">
	<form id="messageForm" name="messageForm" class="form-horizontal add" action="" method="post" style="margin-top: 10px" >
		<g:render template="/_common/hint" model="[hintText: 'Удалить сообщение можно путем наведения на сообщение! Доступно в течении 5 минут после публикации.']"/>
		<g:hiddenField name="version" value="${claimInstance?.version}"/>
		<g:textArea required="" id="messageText" class="form-control" name="post" rows="4" placeholder="Ваше сообщение"/>
		<div class="pods">Подсказка: Enter - отправить сообщение, Ctrl+Enter - перенос строки</div>
	</form>
	<div class="row">
		<div class="col-sm-12" style="margin-top: 10px">


			<button  id="messageSubmit" class="btn btn-success dsa e" name="messageSubmit" type="submit" form="messageForm" style="margin-bottom: 10px;border-radius: 15px; float: left;width: 220px;">
				<i class="glyphicon glyphicon-comment"></i>
				${message(code: 'claim.send.label')}
			</button>


			<g:render template="/_common/upload-multi" model="[
					objInstance: claimInstance,
					attachList : attach
			]"/>

		</div>

	</div>
</sec:ifAnyGranted>






		</g:if>






	</div>

	<g:render template="/_common/gallery-multi"/>

	<script type="application/javascript">
		function deletepost(event, id) {
			var el = event.target || event.srcElement;
			$.ajax({
				type: 'DELETE',
				url: '${createLink(action: 'deletepost')}/'+id,
				success: function (data, textStatus) {
					$('#post'+id).remove()
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {}
			});
		}
	</script>

	<script type="application/javascript">

		document.body.onkeydown = function(event) {
			var e = event || window.event;
			var code = e.keyCode || e.which;
			var activeEl = document.activeElement.id;
			if(code == 13 && e.ctrlKey && activeEl == "messageText") {
				document.getElementById('messageArea').value += "\n";
			} else if(code == 13 && activeEl == "messageText") {

				//document.getElementById('messageSubmit').click();
				$('#messageForm').trigger('submit');
				$('#messageForm').innerText=""
				return false
			}
		};
		$(document).ready(function(){


			_node = document.getElementById("messageForm");
			_node.onsubmit = function(event){
				event.preventDefault();
				var text_redirect = $('#messageText').val();
				text_redirect = text_redirect.replace(/^\s+/, "");
				if(text_redirect == "") {
					return false
				}
				$.ajax({
					type: 'POST',
					data: jQuery(this).serialize(),
					url: '${createLink(controller: 'claim', action: 'post', resource: claimInstance)}',
					success:function(data,textStatus){
						$('#messageText').val('');
						pullMessages();
					},
					error:function(XMLHttpRequest,textStatus,errorThrown){
					}
				});
			}


		});
		function getNewPosts() {
			var lastPost, postList = document.getElementsByName('postId');
			if (postList.length) {
				lastPost = postList[postList.length-1];
				jQuery.ajax({
					type:'GET',
					url:'${createLink([controller: 'claim', action: 'getLatestPosts'])}/'+lastPost.getAttribute('value'),
					success:function(data,textStatus){
						if (data != 'NO_CONTENT') {
							jQuery('#postsArea').append(data);
						}
						if (data != 'NO_CONTENT') {
							jQuery('#postsArea3').append(data);
						}
						if (data != 'NO_CONTENT') {
							jQuery('#postsArea5').append(data);
						}
						if (data != 'NO_CONTENT') {
							jQuery('#postsArea1').append(data);
						}
						if (data != 'NO_CONTENT') {
							jQuery('#postsArea2').append(data);
						}
						if (data != 'NO_CONTENT') {
							jQuery('#postsArea6').append(data);
						}
					},
					error:function(XMLHttpRequest,textStatus,errorThrown){

					}}
				);
			} else {
				return false
			}
		}
		function getRealDisplay(elem) {
			if (elem.currentStyle) {
				return elem.currentStyle.display
			} else if (window.getComputedStyle) {
				var computedStyle = window.getComputedStyle(elem, null )
				return computedStyle.getPropertyValue('display')
			}
		}
		function hide(el) {
			if (!el.getAttribute('displayOld')) {
				el.setAttribute("displayOld", el.style.display)
			}
			el.style.display = "none"
		}
		function pullMessages() {
			clearTimeout(postTimer);
			getNewPosts();

			postTimer = setTimeout('pullMessages()', 4000); <%--каждые 6 секунд--%>
		}
		var postTimer = ${claimInstance.status};
		pullMessages();
		displayCache = {}
		function isHidden(el) {
			var width = el.offsetWidth, height = el.offsetHeight,
					tr = el.nodeName.toLowerCase() === "tr"
			return width === 0 && height === 0 && !tr ?
					true : width > 0 && height > 0 && !tr ? false :	getRealDisplay(el)
		}
		function toggle(el) {
			isHidden(el) ? show(el) : hide(el)
		}
		function show(el) {
			if (getRealDisplay(el) != 'none') return
			var old = el.getAttribute("displayOld");
			el.style.display = old || "";
			if ( getRealDisplay(el) === "none" ) {
				var nodeName = el.nodeName, body = document.body, display
				if ( displayCache[nodeName] ) {
					display = displayCache[nodeName]
				} else {
					var testElem = document.createElement(nodeName)
					body.appendChild(testElem)
					display = getRealDisplay(testElem)
					if (display === "none" ) {
						display = "block"
					}
					body.removeChild(testElem)
					displayCache[nodeName] = display
				}
				el.setAttribute('displayOld', display)
				el.style.display = display
			}
		}
		function getRealDisplay(elem) {
			if (elem.currentStyle) {
				return elem.currentStyle.display
			} else if (window.getComputedStyle) {
				var computedStyle = window.getComputedStyle(elem, null )
				return computedStyle.getPropertyValue('display')
			}
		}
		function hide(el) {
			if (!el.getAttribute('displayOld')) {
				el.setAttribute("displayOld", el.style.display)
			}
			el.style.display = "none"
		}

		displayCache = {}

		function isHidden(el) {
			var width = el.offsetWidth, height = el.offsetHeight,
					tr = el.nodeName.toLowerCase() === "tr"
			return width === 0 && height === 0 && !tr ?
					true : width > 0 && height > 0 && !tr ? false :	getRealDisplay(el)
		}

		function toggle(el) {
			isHidden(el) ? show(el) : hide(el)
		}

		function show(el) {
			if (getRealDisplay(el) != 'none') return
			var old = el.getAttribute("displayOld");
			el.style.display = old || "";
			if ( getRealDisplay(el) === "none" ) {
				var nodeName = el.nodeName, body = document.body, display
				if ( displayCache[nodeName] ) {
					display = displayCache[nodeName]
				} else {
					var testElem = document.createElement(nodeName)
					body.appendChild(testElem)
					display = getRealDisplay(testElem)
					if (display === "none" ) {
						display = "block"
					}
					body.removeChild(testElem)
					displayCache[nodeName] = display
				}
				el.setAttribute('displayOld', display)
				el.style.display = display
			}
		}

	</script>
</g:else>

</body>
</html>