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
  <title>Поиск Товаров</title>
</head>

<body>


<h1>Расширенный поиск товаров</h1>
<div class="reg_form">



  <g:form mapping="${mapping}" action="search">

    <div class="lead">Введите ключевое слово, название товара, часть описания или модель</div>


    <g:textField required="" name="search" class="form-control" value="${params.search}" class="search_big"/>




    <div class="row">
      <div class="col-md-6">


        Выбор региона и города
        <g:hiddenField name="regl" class="form-control" value="${filter?.regl}"/>
      </div>

      <div class="col-md-6">

        Выбор категорий

        <g:hiddenField name="catl" class="form-control" value="${filter?.catl}"/>
      </div>

    </div>
    <br>

    Укажите диапазон цен

    <div class="row">
      <div class="col-md-3">



        <g:textField placeholder="Цена от" class="form-control" name="priceMin" value="${formatNumber(number: filter?.priceMin, format: '###,##0.00')}" />



      </div>

      <div class="col-md-3">

        <g:textField placeholder="Цена до" class="form-control" name="priceMax" value="${formatNumber(number: filter?.priceMax, format: '###,##0.00')}"/>

      </div>


      <div class="col-md-3">

      </div>


      <div class="col-md-3">

      </div>



    </div>



    <br>


    <div style="text-align: center">

      <button class="btn btn-success btn-lg" type="submit">Поиск</button>


    </div>
  </g:form>
</div>



<script type="text/javascript">
  $('#regl').select2({
    placeholder: "Выберите регион или город",
    allowClear: true,
    multiple: true,
    data: ${raw(regionFilterData.regionsTreeJson)}
  });
  $('#catl').select2({
    placeholder: "Выберите одну или несколько категории",
    allowClear: true,
    multiple: true,
    data: ${raw(categoryFilterData.categoriesTreeJson)}
  });

</script>

<h2>Результаты поиска</h2>
<div class="row">
  <div class="col-md-12">

    <g:if test="${rowsCount > 0}">

      <g:each in="${itemInstanceList}" status="i" var="obj">
        <g:render template="item" model="[itemInstance: obj]"/>
      </g:each>

      <g:if test="${rowsCount > 0 && params.max < rowsCount}">
        <g:paginate total="${rowsCount ?: 0}"/>
      </g:if>
    </g:if>
    <g:else>
      не найдено
    </g:else>

  </div>

</div>

</body>
</html>