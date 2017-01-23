
<div class="row header" style="display: none">
  <div class="col-md-2" style="border-right:1px solid silver"><g:link controller="front"><img src="${resource(dir: 'images', file: 'admin/logo.png')}" alt="Deloved"/></g:link>
  </div>

  <div class="col-md-4"><div class=podp>Личный кабинет</div>

    <a href="#" style="color:red"><i class="glyphicon glyphicon-remove-circle" style="color:red"></i> Удалить учетную запись</a>

  </div>

  <div class="col-md-1 col-lg-2">
    <script>
      /*
       <div style="position: absolute">

      <g:if test="${userInfo(what: 'avatarThumb')}">
       <div id="avatar" class="no-avatar">
       <span class="glyphicon glyphicon-user" />
       </div>
      </g:if>
		<g:else>

       <div id="avatar" class="no-avatar" onclick="function() {return false};">
       <span class="glyphicon glyphicon-user" />
       </div>

      </g:else>

       <div id="info" style="border-radius: 3px; padding: 15px;
       background-color: #ffffff; z-index: 2; border: 1px solid gainsboro; width: 350px; height: 185px; position: relative; left: -300px; bottom: -10px; display: block" align="center">

       <div class="row" style="padding: 0; width: 100%; vertical-align: top; horiz-align: left;">

       <div class="col-md-4" style="padding: 0" align="left">
       <img src="" width="100" height="100" alt=""/>
       </div>
       <div class="col-md-7" style="padding: 0" align="left">
       Иванов Иван Иванович<br>
       ООО "Иван и сыновья"
       </div>
       </div>

       <div class="row" style="padding: 0; width: 100%; margin-top: 15px">
       <div class="col-md-6" style="padding: 0" align="left">
      <g:link class="btn btn-default" controller="profile" action="edit"  style="height: 40px; vertical-align: middle">Редактировать</g:link>
       </div>
       <div class="col-md-6" style="padding: 0" align="right">
       <button class="btn btn-default" style="height: 40px">Выход</button>
       </div>
       </div>

       </div>
       </div>
       */
    </script>
  </div>

  <sec:ifLoggedIn>





    <div class="col-md-3 col-lg-2" style="font-size:13pt">Приветствуем&nbspВас,

      <br>
      <g:link controller="profile" style="text-decoration:underline" action="edit"><span class="glyphicon glyphicon-pencil" style="padding-right: 0.3em;"></span></g:link>
      <g:link controller="profile" style="text-decoration:underline" action="me">${userInfo(what: 'fio') ?: userInfo(what: 'username')}</g:link><br/>

      <g:form id="logoutForm" url="[controller: 'logout']" method="POST"></g:form>
    </div>

    <div class="col-md-2"><a class="btn btn-default" href="#" onclick="$('#logoutForm').submit()">Выйти</a></div>
  </sec:ifLoggedIn>

  <sec:ifNotLoggedIn>
    <g:link controller='login' action='auth'>Войти</g:link>
  </sec:ifNotLoggedIn>

</div>