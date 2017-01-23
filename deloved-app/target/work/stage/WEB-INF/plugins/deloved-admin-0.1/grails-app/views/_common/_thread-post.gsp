
<g:each status="i"  in="${threadPosts}" var="obj" >

  <%-- Начало блока сообщения --%>

  <%-- Строка с аватаром, телом сообщения и датой --%>
  <div class="thread-post" name="post" id="post${obj.id}">

    <g:hiddenField name="postId" value="${obj.id}"/>

    <%-- Начало аватара. Показывать в случае сообщения собеседника --%>
    <div class="thread-avatar">
      <g:if test="${obj.person != myProfile}">

        <g:if test="${notMyAccount?.logo}">
          <img src="${createLink(controller: 'account', action: 'logo', id: notMyAccount.id, params: [name: notMyAccount?.logoThumb?.file])}"/>
        </g:if>
        <g:elseif test="${obj.person.avatarThumb}">
          <img src="${createLink(controller: 'profile', action: 'avatar', id: obj.person.id, params: [name: obj.person.avatarThumb?.file])}"/>
        </g:elseif>
        <g:else>
          <img src="${resource(dir: 'image', file: 'admin/avatar.jpg')}"/>
        </g:else>
      </g:if>
    </div>
    <%-- Конец аватара --%>

    <div class="thread-subject">



      <g:if test="${obj.person != myProfile}">

        <g:if test="${notMyAccount?.name}">
          <span class="nickname">${notMyAccount.orgForm.code + ' \"' + notMyAccount.name + '\"'}</span>
        </g:if>
        <g:else>
          <span class="nickname">${obj.person.fio}</span>
        </g:else>


      </g:if>
    <g:if test="${obj.status != null}">

    <%-- Тело сообщения в случае если это статус --%>

        <g:if test="${threadStatusTemplate}">
          <g:render template="/_common/thread-status" model="[obj: obj, myProfile: myProfile]"/>
        </g:if>
        <g:else>
          <div name="post" class="${obj.person == myProfile ? 'bubble bubble-in bubble-status' : 'bubble bubble-out bubble-status'}">
            Изменен статус: ${message(code: threadStatusPrefix + '.' + obj.status(), default: obj.status())}
          <script>

          </script>
          </div>
        </g:else>
    </g:if>
    <g:else>
      <%-- Тело соодбщения. Меняет фон и выранивание в зависимости от сторон --%>
        <div class="${obj.person == myProfile ? 'bubble bubble-in' : 'bubble bubble-out'}">
          ${obj.post}
        <%-- Обработка случая, если к сообщению приложены файлы --%>
          <g:if test="${obj.attachments}">
             <div align="left">
              <g:each in="${obj.attachments}" var="att">
                <table style="display: inline-block; margin: 5px" id="att${att.attachment.id}">
                  <tr>
                    <td style="padding-right: 10px" rowspan="2">

                        <g:if test="${att.attachment.isImage()}">
                          <a style="text-decoration: none" href="${createLink([action: 'download', id: att.attachment.id, params: [name: att.attachment.name]])}"
                             title="${att.attachment.name}" download="${att.attachment.name}" data-gallery>
                          <img width="32" height="32" src="${createLink([action: 'thumb', id: att.attachment.id, params: [name: att.attachment.name]])}">
                          </a>
                        </g:if>
                        <g:else>
                          <img width="32" height="32" src="${resource(dir: 'images', file: "fileupload/${att.attachment.getIcon()}")}"/>
                        </g:else>
                    </td>
                    <td>
                        ${att.attachment.name}
                    </td>
                  </tr>
                  <tr>
                    <td>${att.attachment.readableByte()} <a style="text-decoration: none" href="${createLink([action: 'download', id: att.attachment.id, params: [name: att.attachment.name]])}"
                                                            title="${att.attachment.name}"
                                                            download="${att.attachment.name}">Скачать</a>
                      <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
                        <g:remoteLink action="deleteatt" id="${att.attachment.id}" params="[name: att.attachment.name]" onSuccess="jQuery('#att${att.attachment.id}').remove()"
                                      onFailure="onDeleteError(XMLHttpRequest)" before="if(!confirm('Are you sure?')) return false"><span class="glyphicon glyphicon-trash"></span></g:remoteLink>
                      </sec:ifAnyGranted>
                    </td>
                  </tr>
                </table>
              </g:each>
            </div>
          </g:if>
           <sec:ifAnyGranted roles="ROLE_ADMIN">
            <g:set var="canEdit" value="true"/>
           </sec:ifAnyGranted>
          <g:if test="${obj.person == myProfile || canEdit}">
            <div name="editPost" style="font-size: small">
               <button name="deletepost" class="link" id="deletelink" value="${obj.id}" onclick="deletepost(event, ${obj.id})" title="Удалить сообщение, доступно в течении 5 минут после публикации">
                удалить сообщение
              </button>
            </div>
          </g:if>
        </div>
      <%-- Конец сообщения --%>

    </g:else>
  </div>
  <%-- Столбец с датой --%>
    <div class="thread-time">
        <g:formatDate id="timeouts1" date="${obj.dateCreated}" format="dd.MM.yyyy"/><br>
        <g:formatDate id="timeouts2" date="${obj.dateCreated}" format="HH:mm"/>
    </div>
    <%-- Конец даты --%>
  </div>
  <%-- Конец блока сообщения --%>
</g:each>

%{--<script>--}%
    %{--$(document).ready(function(){--}%
        %{--var elem =$('.thread-subject:last').html();--}%
        %{--var text = 'Изменен статус';--}%
        %{--var text2= 'bubble-out';--}%
        %{--var result = elem.match(text);--}%
        %{--var result2=elem.match(text2);--}%
      %{--var inter = setInterval (function(){--}%
        %{--if(result&&result2){--}%
            %{--location.reload();--}%


        %{--}--}%
        %{--}, 25000 )--}%

    %{--})--}%
%{--</script>--}%