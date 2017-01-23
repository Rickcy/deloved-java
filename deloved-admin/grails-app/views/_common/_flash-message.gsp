<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>


<div id="flash-message" class="flash-alert alert alert-info" hidden>

	<!--g:message message="${flash.message}" encodeAs="None"/-->

</div>

<script type="application/javascript">


	<g:if test="${flash.message}">
	showMessage('${flash.message}')
	</g:if>

	var ib = $('#flash-message');

	function showMessage(){
		setTimeout(function(){

			ib.fadeIn(450);
			setTimeout(function(){
				ib.fadeOut(4000, function() {
					//ib.remove();
				});
				ib.mouseenter(function() {
					ib.clearQueue();
					ib.stop();
					ib.animate({opacity: 1});
				});
				ib.mouseleave(function() {
					ib.fadeOut(8000, function() {
						//ib.remove();
					});
				});
			}, 4000)

		}, 100)
	}



	function showInfoMessage(message){
		ib.clearQueue();
		ib.stop();
		ib.hide();
		ib.attr('class', ib.attr('class').replace(/\balert-\w+\b/g, 'alert-info'));
		$('#flash-message').html(message);
		showMessage();
	}

	function showWarningMessage(message){
		ib.clearQueue();
		ib.stop();
		ib.hide();
		ib.attr('class', ib.attr('class').replace(/\balert-\w+\b/g, 'alert-warning'));
		$('#flash-message').html(message);
		showMessage();
	}

	function showDangerMessage(message){
		ib.clearQueue();
		ib.stop();
		ib.hide();
		ib.attr('class', ib.attr('class').replace(/\balert-\w+\b/g, 'alert-danger'));
		$('#flash-message').html(message);
		showMessage();
	}

	function showSuccessMessage(message){
		ib.clearQueue();
		ib.stop();
		ib.hide();
		ib.attr('class', ib.attr('class').replace(/\balert-\w+\b/g, 'alert-success'));
		$('#flash-message').html(message);
		showMessage();
	}


</script>