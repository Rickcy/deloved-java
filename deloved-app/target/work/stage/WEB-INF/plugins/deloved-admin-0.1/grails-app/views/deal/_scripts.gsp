<script type="application/javascript">

	<%--
	Я знаю что порнография
	Я знаю что все не так
	Я знаю кто я
	Не поминайте злом
	Зато все работает
	--%>

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



	var myDropzone = new Dropzone("button#sendFiles", {
		method: 'POST',
		url: '${createLink(controller: 'deal', action: 'upload', id: dealInstance.id)}',
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

	function loadCheckPoints() {
		jQuery.ajax({
					type:'GET',
					url: '${createLink(action: 'getCheckPoints', id: dealInstance.id)}',
					success: function(data,textStatus){
						var cp = $('.deloved-progress-check');
						if (cp.length) {
							for (var i = 0; i < cp.length; i++) {
								$(cp[i]).remove();
							}
						}
						var pb = $(".deloved-progress-bar");
						$(data).appendTo(pb);
						$('[data-toggle="tooltip"]').tooltip();
					},
					error:function(XMLHttpRequest,textStatus,errorThrown){
					}}
		);
	}





	var postTimer, currentStatus = ${dealInstance.status};

	function getNewPosts() {

		var lastPost; postList = document.getElementsByName('postId');

		if (postList.length) {
			lastPost = postList[postList.length-1];

			jQuery.ajax({
				type:'GET',
				url:'${createLink([controller: 'deal', action: 'getLatestPosts'])}/'+lastPost.getAttribute('value'),
				success:function(data,textStatus){

					if (data != 'NO_CONTENT') {
						jQuery('#postsArea').append(data);

					}
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){

				}}
			);
		} else {
			return false
		}
	}

	function getNewStatus(progressElem, url) {

		jQuery.ajax({
					type:'GET',
					url: url,
					data: {currentStatus: currentStatus},
					dataType: 'json',
					success:function(data,textStatus) {
						<%--response format [currentStatus: [name, value, position], statusList: [[value, name],[value, name]...]--%>
						var result = [];
						var _node, _ref, _length, _i;



						if(currentStatus!="${dealInstance.status}"){
							window.location.reload()
						}

						if (data.currentStatus) {

							result.push(currentStatus = data.currentStatus.value)
							result.push($("input#dealProgressVal").val(data.currentStatus.position));
							result.push($("input#dealProgressVal").trigger("change"));


							var bar = $("div.deloved-progress");


							if (data.currentStatus.value == '${ru.deloved.DealStatus.Rejected.value()}' || progress == '${ru.deloved.DealStatus.Failed.value()}') {

								result.push(bar.removeClass('success-progress'));
								result.push(bar.addClass('failure-progress'));
							}

							if (data.currentStatus.value == '${ru.deloved.DealStatus.Suspended.value()}') {
								result.push(bar.removeClass('success-progress'));
								result.push(bar.addClass('suspended-progress'));
							}

							if (data.currentStatus.value == '${ru.deloved.DealStatus.SignUP.value()}') {
								_node = document.getElementById('toDispute');
								result.push(_node.disabled = false);
								_node = document.getElementById('toClaim');
								result.push(_node.disabled = false);
							}
							if (data.currentStatus.value == '${ru.deloved.DealStatus.Confirmed.value()}') {
								_node = document.getElementById('toReview');
								result.push(_node.disabled = false);
								_node = document.getElementById('reviewAlert');
								result.push(show(_node));
							}
							_node = document.getElementById('statusListMenu');
							_ref = _node.querySelectorAll('li');
							if (_ref.length) {
								_length = _ref.length
								for (_i = 0; _i < _length; _i++) {
									result.push(_node.removeChild(_ref[_i]));
								}
							}
							if(data.statusList) {
								var statusList = data.statusList;
								_node = document.getElementById('statusListMenu');
								var _button = document.getElementById('statusListButton');
								_length = statusList.length
								if (statusList.length) {
									for (_i = 0; _i < _length; _i++) {
										var li = document.createElement('LI');
										var a = document.createElement('A');
										a.setAttribute('href', 'javascript:void(0)')
										a.setAttribute('onclick', 'return changeStatus('+ statusList[_i].value+');')
										var text = document.createTextNode(statusList[_i].name)
										a.appendChild(text)
										li.appendChild(a)
										result.push(_node.appendChild(li))
									}
									if (_button.disabled) {
										result.push(_button.disabled = false);
									}
								} else {
									if (!_button.disabled) {
										result.push(_button.disabled = true);
									}
								}
							}
						}

						return result

					},
					error:function(XMLHttpRequest,textStatus,errorThrown){
						console.log("не прошло")
					}}
		);
	}


	function pullMessages() {
		clearTimeout(postTimer);
		getNewPosts();
		getNewStatus('input#dealProgressVal', '${createLink(action: 'getStatus', id: dealInstance.id)}');
		postTimer = setTimeout('pullMessages()', 4000); <%--каждые 6 секунд--%>
	}

	pullMessages();

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

	$(document).ready(function(){
		var _node = document.getElementById("statusForm");
		_node.onsubmit = function(event) {
			event.preventDefault();
			$.ajax({
				type: 'POST',
				data: jQuery(this).serialize(),
				url: '${createLink(resource: dealInstance, action: 'post')}',
				success:function(data,textStatus){
					pullMessages();
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
				}
			});
		}

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
				url: '${createLink(controller: 'deal', action: 'post', resource: dealInstance)}',
				success:function(data,textStatus){
					$('#messageText').val('');
					pullMessages();
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
				}
			});
		}

		_node = document.getElementById('dealProgressVal')

		_node.onchange = function (event) {

			var elem = event.target || event.srcElement,
				progress =  elem.getAttribute('value'),
				bar = $("div.deloved-progress"),
				result = [];

			result.push(loadCheckPoints(progress));

			result.push(
					bar.animate({
						width: progress + '%'
					}, 600)
			)

			return result
		}
	});

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
	}

	$(function () {
		$('[data-toggle="tooltip"]').tooltip()
	})

	function changeStatus(status) {

		$('#newstatus').val(status);

		if (status == 501) {
			document.getElementById('changeStatusMessage').innerHTML="Вы действительно хотите отвергнуть сделку?";
			document.getElementById('submitButton').innerHTML="ОК";
			$('#consultDiv').hide();
			$('#submitButton').click(function(){
				setTimeout(function(){location.reload()},4000)
			})

		}
		else if (status == 102 || status == 103 || status == 104) {
			document.getElementById('changeStatusMessage').innerHTML = '<g:staticContent code="arbitrationСlause"/>'
			document.getElementById('submitButton').innerHTML = "Изменить статус";
			$('#consultDiv').show();
			$('#submitButton').click(function(){
				setTimeout(function(){location.reload()},4000)
			})

		}
		else {
			document.getElementById('changeStatusMessage').innerHTML="Вы действительно хотите изменить статус сделки?";
			document.getElementById('submitButton').innerHTML="ОК";
			$('#consultDiv').hide();
			$('#submitButton').click(function(){
				setTimeout(function(){location.reload()},4000)
			})

		}

		$('#changeStatusModal').modal({
			backdrop: 'static',
			keyboard: true
		});
		return false;
	}

	function setNewStatus() {
		$('#changeStatusModal').modal('hide')

		$('#statusForm').trigger('submit');
	}
</script>