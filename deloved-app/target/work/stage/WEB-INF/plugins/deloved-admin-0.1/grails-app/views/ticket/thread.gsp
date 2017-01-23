<%@ page import="ru.deloved.recall.Ticket" %>
<%@ page import="ru.deloved.recall.TicketStatus" %>
<
<html>
<head>
	<meta name="layout" content="admin">
	<title>Консультация</title>
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

	<div id="ticket-thread" class="content scaffold-list" role="main">

		<g:render template="/_common/flash-message"/>


		<g:link class="btn btn-default" action="index"><span class="glyphicon glyphicon-chevron-left"></span> <g:message
				code="ticket.backalltickets"/></g:link>

		<br>    <br>

		<div class="lead">
			<sec:ifAnyGranted roles="ROLE_ACCOUNT">
				<g:if test="${ticketInstance.support!=null}">
					<label for="account">Консультация с :<a id="judge" href="javascript:void(0)">${ticketInstance.support.fio}</a></label>




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

								<div class="modal-header" >
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title" style="text-align: center">${ticketInstance.support.fio}</h4>
								</div>


								<div class="modal-body" style="font-size: 14px!important;">






									<div class="row">
										<div class="label-col ft">
											<label>Фото:</label>
										</div>
										<div class="value-col">
											<div name="avatar">
												<g:if test="${ticketInstance.support.avatarThumb}">
													<img
															src="${createLink(controller: 'profile', action: 'avatar', id: ticketInstance.support.id, params: [name: ticketInstance.support.avatarThumb?.file])}"/>
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
											<input id="city" name="city" readonly value="${ticketInstance.support?.city?.name}" data-old="${ticketInstance.support?.city?.name}"
												   placeholder="Отсутствует"/>


										</div>

									</div>

									<br>


									<div class="row">
										<div class="label-col ft">
											<label for="cellPhone">Стаж работы</label>
										</div>
										<div class="value-col ft">
											<input id="cellPhone" name="cellPhone" readonly value="${ticketInstance.support.dayOfWork}" data-old="${ticketInstance.support.dayOfWork}"
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
											<p id="dateCreated" name="dateCreated"><g:formatDate date="${ticketInstance.support.dateCreated}" format="dd.MM.yyyy"/></p>
										</div>

									</div>

								</div>



							</div>
						</div>
					</div>
				</g:if>

			</sec:ifAnyGranted>

			<sec:ifAnyGranted roles="ROLE_MANAGER,ROLE_ADMIN">

					<label for="account">Консультация с :</label>
					<span id="account">${ticketInstance.account.name}</span>
			</sec:ifAnyGranted>
		</div>

		<div>
			<label for="status">Статус:</label>
			<span id="status">${message(code: 'ticket.status.' + ticketInstance.status(), default: ticketInstance.status())}</span>
		</div>


		<hr>
		<sec:ifAnyGranted roles="ROLE_ACCOUNT,ROLE_MANAGER,ROLE_JUDGE,ROLE_MEDIATOR,ROLE_JURIST">
			<g:render template="/_common/thread" model="[
				threadPosts       : posts,
				threadAccount     : myAccount ?: ticketInstance.account,
				threadStatusPrefix: 'ticket.status'
		]"/>
		</sec:ifAnyGranted>
		<sec:ifAnyGranted roles="ROLE_ADMIN">
			<div id="scrollContent" class="scrollContent">

				<div id="postsArea3">
					<g:render template="/_common/thread-post" model="[
							threadPosts       : posts,
							threadAccount     : myAccount ?: ticketInstance.account,
							threadStatusPrefix: 'ticket.status'
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
						function setStatus(status) {
							if (confirm('${message(code: 'ticket.button.status.confirm.message')}')) {
								$('#newstatus').val(status);
								$('#statusForm').submit();
								location.reload()
							}
							return false;
						}
					</script>

					<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle"
								data-toggle="dropdown" style="border-radius: 15px;border: 1px solid rgba(255, 0, 0, 0.36)" >Изменение статуса<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<g:each in="${statusList}" var="st">
								<li><a href="#"
									   onclick="return setStatus(${st.value()});">${message(code: 'ticket.status.' + st, default: st)}</a>
								</li>
							</g:each>
						</ul>
					</div>




					<g:form id="statusForm" url="[resource: ticketInstance, action: 'post']">
						<g:hiddenField name="version" value="${ticketInstance?.version}"/>
						<g:hiddenField name="newstatus" value=""/>
					</g:form>
				</div>
			</div>
		</g:if>

		<g:if test="${canPost}">



					<form id="messageForm" name="messageForm" class="form-horizontal" action="" method="post" style="margin-top: 10px" >
						<g:render template="/_common/hint" model="[hintText: 'Удалить сообщение можно путем наведения на сообщение! Доступно в течении 5 минут после публикации.']"/>
						<g:hiddenField name="version" value="${ticketInstance?.version}"/>
						<g:textArea id="messageText" class="form-control" name="post" rows="4" placeholder="Ваше сообщение"/>
						<div class="pods">Подсказка: Enter - отправить сообщение, Ctrl+Enter - перенос строки</div>
					</form>

					<div style="padding: 0;margin-bottom: 5%;" align="right">
						<button  id="messageSubmit" class="btn btn-success" name="messageSubmit" type="submit" form="messageForm" style="margin-bottom: 10px;border-radius: 15px; float: left;width: 220px;">
							<i class="glyphicon glyphicon-comment"></i>
							${message(code: 'ticket.send.label')}
						</button>
<sec:ifAnyGranted roles="ROLE_ACCOUNT">
	<div id="hiddenInputContainer"></div>
	<button id="sendFiles" class="btn btn-success" name="sendFiles" type="button" style="margin-bottom: 10px; width: 220px;border-radius: 15px">
		<i class="glyphicon glyphicon-paperclip"></i>
		Отправить файлы
	</button>
	<script type="application/javascript">
		var myDropzone = new Dropzone("button#sendFiles", {
			method: 'POST',
			url: '${createLink(controller: 'ticket', action: 'upload', id: ticketInstance.id)}',
			clickable: true,
			parallelUploads: 8,
			uploadMultiple: true,
			previewsContainer: null,
			<%--Ввел параметр maxTotalSize, условие при котором сумма всех файлов не должна превышать заданную--%>
			maxTotalSize: 120, //mb
			hiddenInputContainer: 'div#hiddenInputContainer',
			init: function(){
				<%--Переопределил один функцию, чтобы отсекать неудовлятворяющие maxTotalSize случае еще на старте--%>
				this.setupHiddenFileInput = (function(_this) {
					return function() {
						if (_this.hiddenFileInput) {
							_this.hiddenFileInput.parentNode.removeChild(_this.hiddenFileInput);
						}
						_this.hiddenFileInput = document.createElement("input");
						_this.hiddenFileInput.setAttribute("type", "file");
						if ((_this.options.maxFiles == null) || _this.options.maxFiles > 1) {
							_this.hiddenFileInput.setAttribute("multiple", "multiple");
						}
						_this.hiddenFileInput.className = "dz-hidden-input";
						if (_this.options.acceptedFiles != null) {
							_this.hiddenFileInput.setAttribute("accept", _this.options.acceptedFiles);
						}
						if (_this.options.capture != null) {
							_this.hiddenFileInput.setAttribute("capture", _this.options.capture);
						}
						_this.hiddenFileInput.style.visibility = "hidden";
						_this.hiddenFileInput.style.position = "absolute";
						_this.hiddenFileInput.style.top = "0";
						_this.hiddenFileInput.style.left = "0";
						_this.hiddenFileInput.style.height = "0";
						_this.hiddenFileInput.style.width = "0";
						document.querySelector(_this.options.hiddenInputContainer).appendChild(_this.hiddenFileInput);
						return _this.hiddenFileInput.addEventListener("change", function() {
							var totalSize = 0, file, files, _i, _len;
							files = _this.hiddenFileInput.files;
							if (files.length) {
								for (_i = 0, _len = files.length; _i < _len; _i++) {
									totalSize += files[_i]['size']
								}
								if (totalSize > _this.options.maxTotalSize * 1024 * 1024) {
									return false
								}
								for (_i = 0, _len = files.length; _i < _len; _i++) {
									file = files[_i];
									_this.addFile(file);
								}
							}
							_this.emit("addedfiles", files);
							return _this.setupHiddenFileInput();
						});
					};
				})(this);
				this.setupHiddenFileInput();
			},
			addedfile: function() {
				<%-- Отключить рисование превьюшек --%>
				return true
			},
			totaluploadprogress: function(totalUploadProgress, totalBytes, totalBytesSent) {
				<%-- Подсчет суммарного прогресса загрузки нескольких файлов --%>

			}
		});
	</script>
</sec:ifAnyGranted>


					</div>










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
			return false
		}
	};
	$(document).ready(function(){
		var _node = document.getElementById("statusForm");
		_node.onsubmit = function(event) {
			event.preventDefault();
			$.ajax({
				type: 'POST',
				data: jQuery(this).serialize(),
				url: '${createLink(resource: ticketInstance, action: 'post')}',
				success:function(data,textStatus){
					pullMessages();
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
				}
			});
		};

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
				url: '${createLink(controller: 'ticket', action: 'post', resource: ticketInstance)}',
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
				url:'${createLink([controller: 'ticket', action: 'getLatestPosts'])}/'+lastPost.getAttribute('value'),
				success:function(data,textStatus){
					if (data != 'NO_CONTENT') {
						jQuery('#postsArea').append(data);
					}
					if (data != 'NO_CONTENT') {
						jQuery('#postsArea1').append(data);
					}
					if (data != 'NO_CONTENT') {
						jQuery('#postsArea2').append(data);
					}
					if (data != 'NO_CONTENT') {
						jQuery('#postsArea5').append(data);
					}
					if (data != 'NO_CONTENT') {
						jQuery('#postsArea4').append(data);
					}
					if (data != 'NO_CONTENT') {
						jQuery('#postsArea3').append(data);
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
	var postTimer = ${ticketInstance.status};
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