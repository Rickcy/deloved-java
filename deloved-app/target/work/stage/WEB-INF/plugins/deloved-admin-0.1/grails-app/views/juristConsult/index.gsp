<%@ page import="ru.deloved.recall.JuristConsultStatus" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="admin">
    <title><g:message code="consult.list.label"/></title>
</head>

<body>

<div id="list-tickets" class="content scaffold-list" role="main">

    <h1><g:message code="consult.list.label"/></h1>

    <g:render template="/_common/flash-message"/>

    <sec:ifAnyGranted roles="ROLE_ACCOUNT">
        <div class="buttons">
            <g:link class="btn btn-success ft" action="create"><g:message code="consult.button.new.label"/></g:link>
        </div>
    </sec:ifAnyGranted>



    <g:form url="[controller: 'juristConsult', action: 'index']">
        <div class="row">
            <div class="col-md-3">
                <g:select class="form-control"
                          name="status"
                          from="${JuristConsultStatus.values()}"
                          optionKey="value"
                          optionValue="${{ message(code: 'consult.status.' + it, default: it) }}"
                          value="${filter.status}"
                          noSelection="${['': message(code: 'consult.status.allstatus.label')]}"/>
            </div>

            <div class="col-md-3"><g:textField placeholder="Поиск" class="form-control" name="search" value="${filter.search}"/></div>

            <div class="col-md-5">
                <g:submitButton name="filterAction" class="btn btn-primary ft" value="${message(code: 'default.button.filter.label')}"/>
                <g:submitButton name="resetAction" class="btn btn-default ft" formaction="reset" value="${message(code: 'default.button.filterReset.label')}"/>
            </div>

        </div>

    </g:form>

    <div class="table-responsive">
        <table border="0" class="table table-striped table-responsive table-hover">
            <thead style="border-bottom: 3px solid rgba(176, 208, 83, 0.24)">
            <tr>
                <th class="ft">№</th>
                <th class="ft"><g:message code="consult.accounts.label" default="Ticket"/></th>
                <th class="ft"><g:message code="consult.status.label" default="Статус"/></th>
                <g:sortableColumn class="ft" property="dateCreated" title="${message(code: 'consult.dateCreated.label', default: 'Дата создания')}"/>

                <sec:ifAnyGranted roles="ROLE_ADMIN">
                    <th class="ft">Действия</th>
                </sec:ifAnyGranted>
            </tr>
            </thead>
            <tbody>
            <g:each in="${juristConsultInstanceList}" status="i" var="obj">
                <tr id="jurist${obj.id}" class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td>${i+1}</td>
                    <td><g:link id="${obj.id}"  class="${userNewObjects.contains(obj) ? "newItem" : ""}" controller="juristConsult" action="thread">${obj.account.name}</g:link>
                        <g:if test="${userNewObjectPosts[obj]}">
                            <span class="badge">+${userNewObjectPosts[obj]}</span>
                        </g:if>
                    </td>
                    <td>${message(code: 'consult.status.' + obj.status(), default: obj.status())}
                        <g:if test="${userNewObjectStatuses[obj]}">
                            <span class="badge">+${userNewObjectStatuses[obj]}</span>
                        </g:if>
                    </td>
                    <td><g:formatDate date="${obj.dateCreated}" format="dd.MM.yyyy HH:mm:ss"/></td>

                    <sec:ifAnyGranted roles="ROLE_ADMIN">
                        <td><a name="deleteJurist" for="${obj.id}" href="javascript:void(0)"><span class="glyphicon glyphicon-trash"></span></a></td>
                    </sec:ifAnyGranted>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <g:if test="${params.max < rowsCount}">
        <g:paginate total="${rowsCount ?: 0}"/>
    </g:if>

</div>
<script>
    $(document).ready(function() {
        $('[name=deleteJurist]').click(function(event){
            if (confirm('${message(code: 'item.delete.confirm.'+params.categoryType?.code, default: 'Delete this item?')}')) {
                var juristId = $(this).attr('for');
                jQuery.ajax({
                    type:'DELETE',
                    url:'${createLink([controller: 'juristConsult', action: 'deleteJurist'])}/'+juristId,
                    success:function(data,textStatus){
                        $('#jurist'+juristId).remove();
                        showMessage('success', 'Успешно удалено')
                    },
                    error:function(XMLHttpRequest,textStatus,errorThrown){

                        showMessage('danger', 'Удаление не удалось')
                    }
                });
            }
        })
    })
</script>
</body>
</html>