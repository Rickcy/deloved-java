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
  <title>Поиск Услуг</title>
  <asset:javascript src="searchservice.js"/>
  <style>

  .upp{
    z-index: 200;position:fixed;bottom: 20px;left: 65%;opacity: 0.4!important;

  }
  .upp:hover{
    opacity: 0.8!important;
  }
  </style>
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

  $(document).ready(function(){
    // инициализировать элементы a, находящиеся внутри элемента, имеющего класс .tooltip-options a, как компонент tooltip с параметрами title, placement и delay
    $('.geo').remove();
    $(".s").tooltip({
      title : 'Выбирите ' +
      'единицы измерения.',

      delay: { show: 200, hide: 500 }
    });


  });

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
</head>

<body>



<div class="reg_form2" style="position: fixed;left:0px;top: 210px;">



  <g:form mapping="${mapping}" action="search">

    <div class="lead">Введите ключевое слово, название услуги или часть описания</div>


    <g:textField required="" name="search" value="${params.search}" class="search_big"/>




    <div class="row">
      <div class="col-md-6" style="width: 70%">


        Выбор региона и города
        <g:hiddenField name="regl" class="form-control" value="${filter?.regl}"/>
      </div>

      <div class="col-md-6" style="width: 70%">

        Выбор категорий

        <g:hiddenField name="catl" class="form-control" value="${filter?.catl}"/>
      </div>

    </div>
    <g:if test="${rowsCount > 5}">
      <div class="row">
        <div class="col-md-6 fr" style="width:80%">
          Выбор единиц измерения

          <select class="form-control s" id="measure" style="width: 85%;float: left;" data-original-title="" title="">
            <option value="1">Все</option>
            <option value="12">%</option>
            <option value="14">Год</option>
            <option value="8">Декада</option>
            <option value="15">Единица</option>
            <option value="13">Квартал</option>
            <option value="4">Лист авторский</option>
            <option value="3">Лист печатный</option>
            <option value="16">Лист учетно-издательский</option>
            <option value="9">Месяц</option>
            <option value="6">Место</option>
            <option value="17">Минута</option>
            <option value="18">Неделя</option>
            <option value="7">Норма/час</option>
            <option value="10">Полугодие</option>
            <option value="11">Сутки</option>
            <option value="19">Секунда</option>
            <option value="20">Символ</option>
            <option value="21">Слово</option>
            <option value="22">Смена</option>
            <option value="23">Тариф</option>
            <option value="2">Человек</option>
            <option value="5">Час</option>

          </select>
          <div class="b" style="float: left;width: 13%;height: 34px;margin-left: 2%"><span class="
glyphicon glyphicon-ok"></span> </div>
        </div>

      </div>

    </g:if>

    <div class="row">
      <div class="col-md-3" style="width: 30%;margin-top: 10px">



        <g:textField placeholder="Цена от" class="form-control" name="priceMin" value="${formatNumber(number: filter?.priceMin, format: '###,##0.00')}" />



      </div>

      <div class="col-md-3" style="width: 30%;margin-top: 10px">

        <g:textField placeholder="Цена до" class="form-control" name="priceMax" value="${formatNumber(number: filter?.priceMax, format: '###,##0.00')}"/>

      </div>


      <div class="col-md-3">

      </div>


      <div class="col-md-3">

      </div>



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
  <div class="col-md-7 r1 " style="float: right;margin-right: 2%;min-height: 550px">

    <g:if test="${rowsCount > 0}">

      <g:each in="${itemInstanceList}" status="i" var="obj">
        <g:render template="item" model="[itemInstance: obj]"/>
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