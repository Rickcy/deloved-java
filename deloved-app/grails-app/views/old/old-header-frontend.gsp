<div class=button_block>
  <sec:ifLoggedIn>

    <g:form id="logoutForm" url="[controller: 'logout']" method="POST"></g:form>
    <g:link target="_blank" class="btn btn-default btn-sm" controller="panel">Кабинет</g:link>
    <a class="btn btn-default btn-sm" href="#" onclick="$('#logoutForm').submit()">Выйти</a>
  </sec:ifLoggedIn>
  <sec:ifNotLoggedIn>
    <g:link controller='panel' class="btn btn-default btn-sm"><span class="glyphicon glyphicon-user"></span> Личный кабинет</g:link>
  </sec:ifNotLoggedIn>
</div>

<div class="row" style="margin-bottom:0px;">
  <div class="col-md-3"><g:link controller="front"><img src="${resource(dir: 'images', file: 'front/logo.gif')}"/></g:link></div>

  <div class="col-md-9">

    <g:set var="section">companies</g:set>
    <g:if test="${params.controller in ['companies', 'company']}">
      <g:set var="section">companies</g:set>
    </g:if>
    <g:elseif test="${params.controller in ['goods']}">
      <g:set var="section">goods</g:set>
    </g:elseif>
    <g:elseif test="${params.controller in ['services']}">
      <g:set var="section">services</g:set>
    </g:elseif>

    <div class=layer><g:link class="${(section == 'companies') ? 'active' : ''}" url="[controller: 'companies']">партнеры</g:link><g:link
            class="${(section == 'goods') ? 'active' : ''}" url="[controller: 'goods']"><g:message code="goods.list.label"/></g:link><g:link
            class="${(section == 'services') ? 'active' : ''}" url="[controller: 'services']"><g:message
              code="services.list.label"/></g:link></div>

    <div class="search_block">

    <!-- поисковая форма -->

      <g:form controller="${section}" action="search" method="POST">

        <div class="input-group">
          <g:textField required="" name="search" class="form-control" value="${params.search}"/>
          <span class="input-group-btn">
            <button class="btn btn-default" type="submit">Поиск</button>
          </span>
        </div>
      </g:form>

    <!--конец формы-->

      <g:link controller="${section}" action="search" class="search_link">Расширенный поиск</g:link>

    </div>

  </div>

</div>