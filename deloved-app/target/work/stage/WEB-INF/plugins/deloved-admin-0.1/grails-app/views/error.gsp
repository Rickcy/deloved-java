<!DOCTYPE html>
<html>
	<head>
		<title><g:if env="development">Ошибка</g:if><g:else>Error</g:else></title>
		<meta name="layout" content="main">
		<g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
	</head>
	<body>
		<g:if env="development">
			<g:renderException exception="${exception}" />
		</g:if>
		<g:else>
			<ul class="errors">
				<li>Технические неполадки</li>
			</ul>
		</g:else>
	<script>
		$(document).ready(function(){
			setTimeout(function(){location.reload()},7000)
		})
	</script>
	</body>
</html>
