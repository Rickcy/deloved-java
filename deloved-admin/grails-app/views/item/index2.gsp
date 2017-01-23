<%@ page import="ru.deloved.CategoryType; ru.deloved.Item" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin">
    <title><g:message code="${'item.list.' + params.categoryType?.code + '.label'}"/></title>
    <script type="application/javascript">
        $(document).ready(function(){
            var url = window.location.href;
            var regV = 'services';

            var result = url.match(regV);
            if (result) {
                $(function (){$('.buttton1').click()
                })
            } else {

            }}
        )
    </script>
    <script type="application/javascript">
        $(document).ready(function(){
            var url = window.location.href;
            var regV = 'goods';

            var result = url.match(regV);
            if (result) {
                $(function (){$('.buttton2').click()
                })
            } else {

            }}
        )
    </script>
</head>

<body>
<g:if test="${myAccounts}">
    <button type="button" class="buttton1"   data-toggle="modal" data-target="#OpenDispute">
    </button>
    <button type="button" class="buttton2"   data-toggle="modal" data-target="#OpenClaim">
    </button>

    <div class="modal fade" id="OpenDispute" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabel">У вас не выбраны Категория услуг!</h4>
                </div>



                <div class="modal-footer">
                    <%--<div id="jud_button_contunue" class="col-md-6" align="left">
                        <g:link id="consultButton" name="consultButton" controller="consult" action="create" target="_blank" class="jud_button_contunue_main">Помощь юриста</g:link>
                    </div>--%>
                    <div class="jud_button_contunue">
                        <g:link class="jud_button_contunue_main" controller="account" action="index">Добавить категории</g:link>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                </div>


                </div>
            </div>
        </div>
    </div>
<%--Open claim--%>
    <div class="modal fade" id="OpenClaim" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabel">У вас не выбранна Категория товаров!</h4>
                </div>



                <div class="modal-footer">
                    <%--<div id="jud_button_contunue" class="col-md-6" align="left">
                        <g:link id="consultButton" name="consultButton" controller="consult" action="create" target="_blank" class="jud_button_contunue_main">Помощь юриста</g:link>
                    </div>--%>
                    <div class="jud_button_contunue">
                        <g:link class="jud_button_contunue_main" style="margin-top: 20px !important;" controller="account" action="index">Добавить категории</g:link>
                    <button type="button" class="btn btn-default"  data-dismiss="modal">Отмена</button>
                </div>


                </div>
            </div>
        </div>
    </div>
    </g:if>

<div id="list-item" class="content scaffold-list" role="main">

    <h1><g:message code="${'item.list.' + params.categoryType?.code + '.label'}"/></h1>

    <g:render template="/_common/flash-message"/>
<sec:ifAnyGranted roles="ROLE_ACCOUNT">
    <div class="buttons">
        <g:link class="btn btn-success" action="create" mapping="${params.categoryType?.code}"><g:message code="item.button.new.label"/></g:link>
        <g:link class="btn btn-success" action="export" mapping="${params.categoryType?.code}"><g:message code="item.button.export.label"/></g:link>

        <span class="btn btn-success fileinput-button">
            <span><g:message code="item.button.import.label"/></span>
            <input type="file" name="file" id="fileupload2" data-url="${createLink(action: 'import', mapping: params.categoryType?.code)}">
        </span>
        <script type="application/javascript">
            $(function () {
                $('#fileupload2').fileupload({
                    uploadTemplateId: null,
                    downloadTemplateId: null,
                    dataType: 'json',
                    formData: {},
                    add: function (e, data) {
                        data.submit();
                    },
                    done: function (e, data) {
                        //data.result
                        //data.textStatus;
                        //data.jqXHR;
                        //console.log(data.result);
                        var f = data.result.files;
                        if (f != null && f[0] != null) {
                            location.reload();
                        }
                    },
                    maxFileSize: 5000000,
                    acceptFileTypes: /(\.|\/)(xlsx)$/i
                });

            });
        </script>

    </div>
</sec:ifAnyGranted>


    <g:form url="[mapping: params.categoryType?.code, action: 'index']">
        <div class="row">
            <g:if test="${params.categoryType?.code == 'GOOD'}">
                <div class="col-md-2">
                    <g:select class="form-control"
                              name="avail"
                              from="${[1, 0]}"
                              valueMessagePrefix="item.filter.avail"
                              value="${filter?.avail}"
                              noSelection="${['': message(code: 'item.filter.avail.all')]}"/>
                </div>
            </g:if>

            <div class="col-md-3"><g:textField placeholder="Поиск" class="form-control" name="search" value="${filter?.search}"/></div>

            <div class="col-md-2"><g:textField placeholder="Цена от" class="form-control" name="priceMin" value="${filter?.priceMin}"/></div>

            <div class="col-md-2"><g:textField placeholder="Цена до" class="form-control" name="priceMax" value="${filter?.priceMax}"/></div>

            <div class="col-md-2">
                <g:submitButton name="filterAction" class="btn btn-primary" value="${message(code: 'default.button.filter.label')}"/>
            </div>
            <div class="col-md-2">
                <g:submitButton name="resetAction" class="btn btn-default"
                                formaction="reset"
                                value="${message(code: 'default.button.filterReset.label')}"/>

            </div>
        </div>

    </g:form>

    <div class="table-responsive">
    <table border="0" class="table table-striped">
        <thead>
        <tr>
            <th>#</th>

            <th><g:message code="item.account.label" default="Account"/></th>

            <g:sortableColumn property="name" mapping="${params.categoryType?.code}" title="${message(code: 'item.name.label', default: 'Name')}"/>

            <g:sortableColumn property="price" mapping="${params.categoryType?.code}" title="${message(code: 'item.price.label', default: 'Price')}"/>

            <g:if test="${params.categoryType?.code == 'GOOD'}">

                <th><g:message code="item.availability.label" default="availability"/></th>

            </g:if>


            <g:sortableColumn property="dateCreated" mapping="${params.categoryType?.code}" title="${message(code: 'item.dateCreated.label', default: 'Date Created')}"/>

            <th>Действия</th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${itemInstanceList}" status="i" var="obj">
            <tr id="item${obj.id}" class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td><input type="checkbox" name="id" value="${obj.id}"/></td>

                <td id="gridRow${obj.id}account">${fieldValue(bean: obj, field: "account.name")}</td>

                <td name="itemName"><g:link action="edit" controller="item"
                                            id="${obj.id}"
                                            elementId="gridRow${obj.id}name"
                                            data-toggle="modal"
                                            data-remote="${createLink(id: obj.id, action: 'edit')}"
                                            data-target="#myModal">${fieldValue(bean: obj, field: "name")}</g:link></td>

                <td id="gridRow${obj.id}price"><g:formatNumber number="${obj.price}" type="currency" currencyCode="${obj.currency.code}"/></td>


                <g:if test="${params.categoryType?.code == 'GOOD'}">

                    <td id="gridRow${obj.id}availability">${message(code: 'item.filter.avail.' + obj.availability, default: obj.availability)}</td>

                </g:if>

                <td><g:formatDate date="${obj.dateCreated}" format="dd.MM.yyyy HH:mm:ss"/></td>

                <td><a name="deleteItem" for="${obj.id}" href="javascript:void(0)"><span class="glyphicon glyphicon-trash"></span></a></td>



            </tr>
        </g:each>
        </tbody>
    </table>
        </div>
    <g:if test="${params.max < rowsCount}">
        <g:paginate total="${rowsCount ?: 0}" mapping="${params.categoryType?.code}"/> <%-- params="[categoryTypeCode: params.categoryType?.code]"--%>
    </g:if>
</div>

<g:render template="/_common/edit-container"/>
<g:render template="/_common/gallery-multi"/>
<g:render template="/_common/crop"/>

<script>
    $(document).ready(function() {
        $('[name=deleteItem]').click(function(event){
            if (confirm('${message(code: 'item.delete.confirm.'+params.categoryType?.code, default: 'Delete this item?')}')) {
                var itemId = $(this).attr('for');
                jQuery.ajax({
                    type:'DELETE',
                    url:'${createLink([controller: 'item', action: 'delete'])}/'+itemId,
                    success:function(data,textStatus){
                        $('#item'+itemId).remove();
                        showMessage('success', 'Успешно удалено')
                    },
                    error:function(XMLHttpRequest,textStatus,errorThrown){
                        alert(XMLHttpRequest + textStatus + errorThrown)
                        showMessage('danger', 'Удаление не удалось')
                    }
                });
            }
        })
    })
</script>

</body>
</html>
