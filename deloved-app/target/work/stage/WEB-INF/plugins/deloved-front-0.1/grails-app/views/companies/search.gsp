<%--
  Created by IntelliJ IDEA.
  User: Андрейка
  Date: 19.12.2014
  Time: 0:00
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="front">
	<title>Поиск Предприятия</title>
	<style>

	.upp{
		z-index: 200;position:fixed;bottom: 20px;left: 65%;opacity: 0.4!important;

	}
	.upp:hover{
		opacity: 0.8!important;
	}
	</style>
	<script type="application/javascript">
		(function($) {
			$(function() {

				$('.u').click(function() {
					$('html, body').animate({scrollTop: 0},1500);
					return false;
				})

			})
		})(jQuery)
	</script>
	<script type="application/javascript">

		var h_hght = 220; // высота шапки
		var h_mrg = 0;    // отступ когда шапка уже не видна
		$(function(){
			$(window).scroll(function(){
				var top = $(this).scrollTop();
				var elem = $('.reg_form2');
				if (top+h_mrg < h_hght) {
					elem.css('top', (h_hght-top));
				} else {
					elem.css('top', h_mrg);
				}
			});
		});

	</script>
	<script>
		$(window).scroll(function(){
			var elem = $('.u');

			var top = $(this).scrollTop();

			if (top>520){
				elem.fadeIn('slow',function(){elem.addClass('upp')})
			}else if(top<520){
				elem.fadeIn('slow',function(){elem.removeClass('upp')})
			}

		})
	</script>
	<script>
		$(document).ready(function(){
			$('.geo').remove();
		})
	</script>
</head>

<body>


<div class="reg_form2" style="position: fixed;left:0px;top: 210px;">
<g:form action="search">

	<div class="lead">Введите ключевое слово, ИНН или другую известную информацию</div>

	<g:textField required="" name="search" value="${params.search}" class="search_big"  />


	<div class="row">
	<div class="col-md-6" style="width: 70%">

		Выбор региона и города

		<g:hiddenField name="regl" class="form-control" value="${filter?.regl}"/></div>
	<div class="col-md-6" style="width: 70%;margin-top: 10px">

		Выбор категорий

		<g:hiddenField name="catl" class="form-control" value="${filter?.catl}"/></div>
</div>


	<br>
<div style="text-align: center">

	<button class="btn btn-success btn-lg" style="float: right;width: 50%" type="submit">Поиск</button>


</div>

</g:form>


</div>



<script type="text/javascript">
	$('#regl').select2({

		allowClear: true,
		multiple: true,
		data: ${raw(regionFilterData.regionsTreeJson)}
	});
	$('#catl').select2({

		allowClear: true,
		multiple: true,
		data: ${raw(categoryFilterData.categoriesTreeJson)}
	});

</script>

<h3 style="text-align: right;margin-right: 120px;">Результаты поиска <span style="margin-left: 10px" class="glyphicon glyphicon-search"></span></h3>
<div class="row">
	<div class="col-md-7 r1 " style="float: right;margin-right: 2%;min-height: 550px;margin-left: 3%">

		<g:if test="${rowsCount > 0}">

			<g:each in="${accountInstanceList}" status="i" var="obj">
				<g:render template="item" model="[accountInstance: obj]"/>
			</g:each>

			<g:if test="${rowsCount > 0 && params.max < rowsCount}">
				<g:paginate total="${rowsCount ?: 0}"/>
			</g:if>
		</g:if>
		<g:else>
			<h4 style="text-align: center;margin-top: 150px;">Ничего не найдено</h4>
		</g:else>
		<a href="#" style="opacity: 0" class="u"><img src="${resource(dir: 'images', file: 'front/Arrow.png')}" style="width: 20%;min-width: 45px"/></a>
	</div>

</div>

</body>
</html>