
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name='layout' content='admin'/>
	<title>Пожелания и предложения</title>

</head>

<body>
<sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_MANAGER">

<g:form style="text-align: justify" url="[controller: 'suggestion', action: 'index']">

<label class="control-label" for="selSugCat">Категория:</label>

<select id="selSugCat" class="form-control" style="width: auto; display: inline-block" name="category">
    <option value="">Все</option>

    <g:each in="${sugCategories}" var="cat">
    <option value="${cat.id}">${cat.name}</option>
  </g:each>
</select>

<script>
 /*
<label class="control-label" for="selDateFrom">Период от:</label> <g:datePicker id="selDateFrom" name="from" precision="day" />
<label class="control-label" for="selDateTill">до: </label> <g:datePicker id="selDateTill" name="till" precision="day" />
*/
</script>


<label class="control-label" for="selDateOrder">  Дата по:</label>

<select id="selDateOrder" class="form-control" style="width: auto; display: inline-block" name="order">
<option value="desc">По возрастанию</option>
<option value="asc">По убыванию</option>
</select>

<label class="control-label" for="selMaxCount">Кол-во:</label>

<select id="selMaxCount" class="form-control" style="width: auto; display: inline-block" name="max">
  <option value="10">10</option>
  <option value="50">50</option>
  <option value="100">100</option>
</select>

<input class="btn btn-success" type="submit" value="Показать">



<g:link class="btn btn-default" controller="suggestion" action="index">Сбросить</g:link>

<input class="btn btn-danger" type="submit" form="suggestionAction" value="Удалить">
</g:form>

<g:form id="suggestionAction" name="suggestionAction" url="[controller: 'suggestion', action: 'delete']">
<g:each in="${suggestions}" var="suggestion">
  <div class="table-responsive">
    <div style="margin: 10px 10px 10px 0; border: 1px solid silver; background-color: #f2f2f2; padding: 10px; border-radius: 4px">

      <div class="row">
        <div class="col-md-6" align="left">
        ${suggestion?.category?.name ?: 'Без категории'}
      </div>
      <div class="col-md-6" align="right">

        <g:formatDate date="${suggestion?.datePublished}" format="dd-MM-yyyy HH:mm"/>

        <g:checkBox name="checkSuggestion"  value="${suggestion.id}" checked="false"/>

      </div>
    </div>

    <div class="row">
      <div class="col-md-12">
        <b>${suggestion.title}</b><br>
        ${suggestion.content}
      </div>
    </div>

    <div class="row" align="right">
      <div class="col-md-12">
        ${suggestion.author?.fio} <b>(id:${suggestion.author.id})</b>
      </div>
    </div>

  </div>
</div>
</g:each>
</g:form>

<div align="center"><g:paginate total="${sugTotal}"/></div>
</sec:ifAnyGranted>
</body>
</html>