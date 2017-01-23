<sec:ifAnyGranted roles="ROLE_ACCOUNT">
<div id="scrollContent" class="scrollContent">

	<div id="postsArea">

	<g:render template="/_common/thread-post" model="[
			threadPosts         : posts,
			threadAccount       : myAccount ?: dealInstance.account,
			threadStatusPrefix  : 'deal.status',
			threadStatusTemplate: 'thread-status'
	]"/>

</div>

	<span class="glyphicon glyphicon-upload arrows" id="up"></span>

</div>

</sec:ifAnyGranted>

<sec:ifAnyGranted roles="ROLE_MANAGER">
	<div id="scrollContent" class="scrollContent">

		<div id="postsArea2">
			<g:render template="/_common/thread-post" model="[
					threadPosts       : posts,
					threadAccount     : myAccount ?: ticketInstance.account,
					threadStatusPrefix: 'ticket.status'
			]"/>


		</div>


		<span class="glyphicon glyphicon-upload arrows" id="up"></span>

	</div>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles="ROLE_ADMIN">
	<div id="scrollContent" class="scrollContent">

		<div id="postsArea3">
			<g:render template="/_common/thread-post" model="[
					threadPosts       : posts,
					threadAccount     : myAccount ?: disputeInstance.account,
					threadStatusPrefix: 'dispute.status'
			]"/>


		</div>

		<span class="glyphicon glyphicon-upload arrows" id="up"></span>

	</div>

</sec:ifAnyGranted>
<sec:ifAnyGranted roles="ROLE_JURIST">
	<div id="scrollContent" class="scrollContent">

		<div id="postsArea3">
			<g:render template="/_common/thread-post" model="[
					threadPosts       : posts,
					threadAccount     : myAccount ?: consultInstance.account,
					threadStatusPrefix: 'consult.status'
			]"/>


		</div>
		<span class="glyphicon glyphicon-upload arrows" id="up"></span>



	</div>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles="ROLE_MEDIATOR">
	<div id="scrollContent" class="scrollContent">

		<div id="postsArea4">
			<g:render template="/_common/thread-post" model="[
					threadPosts       : posts,
					threadAccount     : myAccount ?: disputeInstance.account,
					threadStatusPrefix: 'dispute.status'
			]"/>


		</div>


		<span class="glyphicon glyphicon-upload arrows" id="up"></span>
	</div>


</sec:ifAnyGranted>
<sec:ifAnyGranted roles="ROLE_JUDGE">
	<div id="scrollContent" class="scrollContent">

		<div id="postsArea5">
			<g:render template="/_common/thread-post" model="[
					threadPosts       : posts,
					threadAccount     : myAccount ?: claimInstance.account,
					threadStatusPrefix: 'claim.status'
			]"/>


		</div>


		<span class="glyphicon glyphicon-upload arrows" id="up"></span>
	</div>

</sec:ifAnyGranted>
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
	}
			,function(){
				$('#up').click(function() {
							$('.mCSB_container').animate({top: '0'},2000);
							$('.mCSB_dragger').animate({top: '0'},2000)
						}
				)
			});

	function checkBottomEdge() {
		var scrollbar = $('#mCSB_1_scrollbar_vertical').height()
		var dragger = $('#mCSB_1_dragger_vertical').height()
		var draggerPosition = $('#mCSB_1_dragger_vertical').position().top
		var pctButton =  (1 - (dragger + draggerPosition)/scrollbar)
		var psA =  49 / $('#postsArea').height()
		if (pctButton < psA) {
			return true
		} else {
			return false
		}
	}

</script>
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
				}
			}
		});
	}
			,function(){
				$('#up').click(function() {
							$('.mCSB_container').animate({top: '0'},2000);
							$('.mCSB_dragger').animate({top: '0'},2000)
						}
				)
			});

	function checkBottomEdge() {
		var scrollbar = $('#mCSB_1_scrollbar_vertical').height()
		var dragger = $('#mCSB_1_dragger_vertical').height()
		var draggerPosition = $('#mCSB_1_dragger_vertical').position().top
		var pctButton =  (1 - (dragger + draggerPosition)/scrollbar)
		var psA =  49 / $('#postsArea2').height()
		if (pctButton < psA) {
			return true
		} else {
			return false
		}
	};


</script>
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
	}
			,function(){
				$('#up').click(function() {
							$('.mCSB_container').animate({top: '0'},2000);
							$('.mCSB_dragger').animate({top: '0'},2000)
						}
				)
			});

	function checkBottomEdge() {
		var scrollbar = $('#mCSB_1_scrollbar_vertical').height()
		var dragger = $('#mCSB_1_dragger_vertical').height()
		var draggerPosition = $('#mCSB_1_dragger_vertical').position().top
		var pctButton =  (1 - (dragger + draggerPosition)/scrollbar)
		var psA =  49 / $('#postsArea5').height()
		if (pctButton < psA) {
			return true
		} else {
			return false
		}
	}

</script>
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
