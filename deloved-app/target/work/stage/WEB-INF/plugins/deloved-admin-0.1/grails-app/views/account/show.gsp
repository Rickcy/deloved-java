<%@ page import="org.codehaus.groovy.grails.plugins.jquery.JQueryConfig; ru.deloved.Account" %>
<%@ page import="ru.deloved.CategoryType" %>
<%@ page import="ru.deloved.Category" %>
<%@ page import="ru.deloved.Account" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin">
    <title><g:message code="account.show.label"/></title>
    <script src="//api-maps.yandex.ru/2.0-stable/?load=package.standard&lang=ru-RU" type="text/javascript"></script>


</head>

<body>
<script type="application/javascript">
    $(document).ready(function(){
        var url = document.referrer;
        var regV = 'index2';

        var result = url.match(regV);

        if (result){$('.remove').remove()}
    })
</script>

<g:render template="/_common/flash-message"/>

<div id="show-account" class="content scaffold-show" role="main">



<sec:ifAnyGranted roles="ROLE_ACCOUNT">
    <g:render template="/_common/hint" model="[hintText: 'Поля, закрытые для редактирования, можно изменить через обращение в службу поддержки.']"/>
</sec:ifAnyGranted>

    <div class="remove">
    <div class="profile-info">

        <%-- Начало общей информации --%>

        <h2 class="ft">Общая информация</h2>

        <div class="row">
            <div class="label-col ft">
                <label for="id">ID в системе</label>
            </div>
            <div class="value-col ft">
                <p id="id" name="id">${accountInstance.id}</p>
            </div>
            <div class="action-col">

            </div>
        </div>

        <div class="row">
            <div class="label-col ft">
                <label for="orgForm">Организационно-правовая форма</label>
            </div>
            <div class="value-col ft">
                <p id="orgForm" name="orgForm">${accountInstance.orgForm.name}</p>
            </div>
            <div class="action-col">

            </div>
        </div>

        <div class="row">
            <div class="label-col ft">
                <label for="fullName">Полное наименование</label>
            </div>
            <div class="value-col ft">
                <p id="fullName" name="fullName">${accountInstance.fullName}</p>
            </div>
            <div class="action-col">

            </div>
        </div>

        <div class="row">
            <div class="label-col ft">
                <label for="name">Краткое наименование</label>
            </div>
            <div class="value-col ft">
                <p id="name" name="name">${accountInstance.name}</p>
            </div>
            <div class="action-col">

            </div>
        </div>

        <div class="row">
            <div class="label-col ft">
                <label for="brandName">Фирменное наименование</label>
            </div>
            <div class="value-col ft">
                <p id="brandName" name="brandName">${accountInstance.brandName}</p>
            </div>
            <div class="action-col">

            </div>
        </div>

        <div class="row">
            <div class="label-col ft">

                <label>Логотип</label>
            </div>
            <div class="value-col ft">
                <div name="logo">
                    <g:render template="/_common/upload-single-image" model="[
                            'isUpload'    : accountInstance?.logo != null,
                            'imageUrl'    : createLink(controller: 'account', action: 'logo', id: accountInstance?.id, params: [name: accountInstance?.logo?.file, type: 'main']),
                            'thumbUrl'    : createLink(controller: 'account', action: 'logo', id: accountInstance?.id, params: [name: accountInstance?.logoThumb?.file]),
                            'uploadAction': createLink(controller: 'account', action: 'upload', id: accountInstance?.id),
                            'cropAction'  : createLink(controller: 'account', action: 'crop', id: accountInstance?.id),
                            'deleteAction': 'deletelogo',
                            'imgId'       : 'logo',
                            'imgTitle'    : 'Логотип',
                            'imgWidth'    : 100,
                            'imgHeight'   : 100
                    ]"/>
                </div>
            </div>
            <div class="action-col ft">

            </div>
        </div>

        <div class="row">
            <div class="label-col ft">
                <label for="rating">Деловая репутация</label>
            </div>
            <div class="value-col">
                <p id="rating" name="rating">${accountInstance.rating}%</p>
            </div>
            <div class="action-col">

                    <g:link class="otz"
                            data-toggle="modal"
                            data-remote="${createLink(id: accountInstance.id, action: 'revi                                                                                                                                                                                          ews')}"
                            data-target="#OpenOTZ">Отзывы</g:link>

            </div>
        </div>
        <div class="modal fade" id="OpenOTZ" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title ft" id="reviewsModalLabel"><g:message code="review.list.label"/></h4>
                    </div>

                    <div class="modal-body">



                            <g:if test="${reviewsCount > 0}">

                                <ol>
                                    <g:each in="${reviews}" var="review">
                                        <li>
                                            <g:formatDate date="${review.dateCreated}" format="dd.MM.yyyy HH:mm:ss"/> <br/>
                                            <g:if test="${review.value > 0}">
                                                Отзыв положительный
                                            </g:if>
                                            <g:elseif test="${review.value < 0}">
                                                Отзыв отрицательный
                                            </g:elseif>
                                            <g:else>
                                                Отзыв нейтральный
                                            </g:else>

                                            <br/>
                                            Автор отзыва <g:link url="[controller: 'company', id: review.from.id]">
                                                <g:fieldValue bean="${review.from}" field="name"/>
                                            </g:link>

                                            <hr/>

                                            <div>${review.content}</div>
                                            <hr/>
                                        </li>
                                    </g:each>
                                </ol>
                            </g:if>
                            <g:else>
                                Отзывы отсутствуют
                            </g:else>


                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default ft" data-dismiss="modal">${message(code: 'default.button.close.label')}</button>
                    </div>

                </div>
            </div>
        </div>
        <%-- Конец общей информации --%>
        <hr>

        <%-- Начало юридической информации --%>

        <h2 class="ft">Юридическая информация</h2>

        <div class="row">
            <div class="label-col ft">
                <label for="inn">ИНН</label>
            </div>
            <div class="value-col ft">
                <p id="inn" name="inn">${accountInstance.inn}</p>
            </div>
            <div class="action-col">

            </div>
        </div>

        <div class="row">
            <div class="label-col ft">
                <label for="regNumber">ОГРН</label>
            </div>
            <div class="value-col ft">
                <p id="regNumber" name="regNumber">${accountInstance.regNumber}</p>
            </div>
            <div class="action-col">

            </div>
        </div>

        <%--
        <g:if test="${accountInstance.kpp}">
            <div class="row">
                <div class="label-col">
                    <label for="kpp">КПП</label>
                </div>
                <div class="value-col">
                    <p id="kpp" name="kpp">${accountInstance.kpp}<p/>
                </div>
                <div class="action-col">
                </div>
            </div>
        </g:if>
        --%>

        <div class="row">
            <div class="label-col ft">
                <label for="legalAddress">Юридический адрес</label>
            </div>
            <div class="value-col ft">
                <p id="legalAddress" name="legalAddress">${accountInstance.legalAddress}</p>
            </div>
            <div class="action-col">

            </div>
        </div>

        <div class="row">
            <div class="label-col ft">
                <label for="regDate">Дата регистрации</label>
            </div>
            <div class="value-col ft">
                <p id="regDate" name="regDate"><g:formatDate date="${accountInstance.regDate}" format="dd.MM.yyyy"/></p>
            </div>
            <div class="action-col">

            </div>
        </div>

        <div class="row">
            <div class="label-col ft">
                <label for="manager">Руководство</label>
            </div>
            <div class="value-col ft">
                <p id="manager" name="manager">${accountInstance.manager}</p>
            </div>
            <div class="action-col">
            </div>
        </div>

        <%-- Конец юридической информации --%>
        <hr>
        <%-- Начало контактной информации --%>

        <h2 class="ft">Контактная информация</h2>

        <div class="row">
            <div class="label-col ft">
                <label for="city">Фактический город</label>
            </div>
            <div class="value-col ft">
                <input id="city" name="city" type="text" readonly value="${accountInstance?.city.name}" data-old="${accountInstance?.city.name}"
                       placeholder="Отсутствует"/>
                <div class="pods fr">Город вашего центрального офиса</div>
            </div>

            <div class="action-col">
                <a href="javascript:void(0)" name="change" data-for="city">Изменить</a>
            </div>
        </div>

        <div class="row">
            <div class="label-col ft">
                <label for="address">Фактический адрес</label>
            </div>
            <div class="value-col ft">
                <input id="address" name="address" type="text" readonly value="${accountInstance.address}" data-old="${accountInstance.address}"
                       placeholder="Отсутствует"/>
                <div class="pods fr">Адрес вашего центрального офиса</div>
            </div>
            <div class="action-col">
                <a href="javascript:void(0)" name="change" data-for="address">Изменить</a>
                <!--a href="javascript:void(0)">Показать на карте</a-->
                <a id="ancorShow" href="#" onclick="mapToolBarShow();return false;">Показать карту</a>
                <a id="ancorHide" href="#" onclick="mapToolBarHide();return false;" style="display:none;">Скрыть карту</a>
            </div>
        </div>

        <script type="application/javascript">
            $(function () {
                $("#city").autocomplete({
                    source: "${createLink(controller: 'profile', action: 'cities')}",
                    minLength: 3
                });
            });

            var myMap = null;
            var myPlacemark = null;

            function showAddres(city, addr) {
                if (myMap) {
                    if (addr != "") {
                        ymaps.geocode(city + ", " + addr, {results: 1}).then(function (resAddr) {
                            var addrGeoObject = resAddr.geoObjects.get(0);
                            if (myPlacemark) {
                                myMap.geoObjects.remove(myPlacemark);
                            }
                            myPlacemark = new ymaps.Placemark(addrGeoObject.geometry.getCoordinates(), {
                                hintContent: addr
                            });
                            myMap.geoObjects.add(myPlacemark);
                            myMap.setCenter(addrGeoObject.geometry.getCoordinates(), 15);

                        }, function (err) {
                            console.error(err.message);
                        });
                    }
                } else {
                    if (city != "") {
                        ymaps.geocode(city + (addr != "" ? (", " + addr) : ""), {results: 1}).then(function (res) {
                            <%--Выбираем первый результат геокодирования--%>
                            var firstGeoObject = res.geoObjects.get(0);
                            myMap = new ymaps.Map("map", {
                                center: firstGeoObject.geometry.getCoordinates(),
                                zoom: addr != "" ? 15 : 13
                            });
                            if (addr != "") {
                                if (myPlacemark) {
                                    myMap.geoObjects.remove(myPlacemark);
                                }
                                myPlacemark = new ymaps.Placemark(firstGeoObject.geometry.getCoordinates(), {
                                    hintContent: addr
                                });
                                myMap.geoObjects.add(myPlacemark);
                            }
                            myMap.controls.add(new ymaps.control.ZoomControl());

                            $("#mapToolBar").show();
                        }, function (err) {
                            console.error(err.message);
                        });
                    }
                }
            }

            ymaps.ready(function () {
                showAddres('${accountInstance?.city?.name}', '${accountInstance?.address}');
            });

            function mapToolBarShow() {
                $("#ancorShow").hide();
                $("#ancorHide").show();
                $("#map").show();
            }
            function mapToolBarHide() {
                $("#ancorShow").show();
                $("#ancorHide").hide();
                $("#map").hide();
            }
        </script>

        <div id="mapToolBar" style="display:block;" align="center">
            <div id="map" style="width:500px; height:500px; display: none; margin: 15px"></div>
        </div>

        <div class="row">
            <div class="label-col ft">
                <label for="email">Email</label>
            </div>
            <div class="value-col ft">
                <input id="email" name="email" type="text" readonly value="${accountInstance.email}" data-old="${accountInstance.email}"
                       placeholder="Отсутствует"/>
                <div class="pods fr">Адрес электронной почты</div>
            </div>
            <div class="action-col">
                <a href="javascript:void(0)" name="change" data-for="email">Изменить</a>
            </div>
        </div>

        <div class="row">
            <div class="label-col ft">
                <label for="webAddress">Сайт</label>
            </div>
            <div class="value-col ft">
                <input id="webAddress" name="webAddress" type="text" readonly value="${accountInstance.webAddress}" data-old="${accountInstance.webAddress}"
                       placeholder="Отсутствует"/>
                <div class="pods fr">Адрес веб-сайта</div>
            </div>
            <div class="action-col">
                <a href="javascript:void(0)" name="change" data-for="webAddress">Изменить</a>
            </div>
        </div>

        <div class="row">
            <div class="label-col ft">
                <label for="phone1">Номер телефона №1</label>
            </div>
            <div class="value-col ft">
                <input id="phone1" name="phone1" type="text" readonly value="${accountInstance.phone1}" data-old="${accountInstance.phone1}"
                       placeholder="Отсутствует"/>
                <div class="pods fr">Основной номер телефона</div>
            </div>
            <div class="action-col">
                <a href="javascript:void(0)" name="change" data-for="phone1">Изменить</a>
            </div>
        </div>

        <div class="row">
            <div class="label-col ft">
                <label for="phone2">Номер телефона №2</label>
            </div>
            <div class="value-col ft">
                <input id="phone2" name="phone2" type="text" readonly value="${accountInstance.phone2}" data-old="${accountInstance.phone2}"
                       placeholder="Отсутствует"/>
                <div class="pods fr">Дополнительный номер телефона</div>
            </div>
            <div class="action-col">
                <a href="javascript:void(0)" name="change" data-for="phone2">Изменить</a>
            </div>
        </div>

        <div class="row">
            <div class="label-col ft">
                <label for="phone3">Номер телефона №3</label>
            </div>
            <div class="value-col ft">
                <input id="phone3" name="phone3" type="text" readonly value="${accountInstance.phone3}" data-old="${accountInstance.phone3}"
                       placeholder="Отсутствует"/>
                <div class="pods fr">Дополнительный номер телефона</div>
            </div>
            <div class="action-col ">
                <a href="javascript:void(0)" name="change" data-for="phone3">Изменить</a>
            </div>
        </div>

        <div class="row">
            <div class="label-col ft">
                <label for="fax1">Факс №1</label>
            </div>
            <div class="value-col ft">
                <input id="fax1" name="fax1" type="text" readonly value="${accountInstance.fax1}" data-old="${accountInstance.fax1}"
                       placeholder="Отсутствует"/>
                <div class="pods fr">Основной номер факса</div>
            </div>
            <div class="action-col">
                <a href="javascript:void(0)" name="change" data-for="fax1">Изменить</a>
            </div>
        </div>

        <div class="row">
            <div class="label-col ft">
                <label for="fax2">Факс №2</label>
            </div>
            <div class="value-col ft">
                <input id="fax2" name="fax2" type="text" readonly value="${accountInstance.fax2}" data-old="${accountInstance.fax2}"
                       placeholder="Отсутствует"/>
                <div class="pods fr">Дополнительный номер факса</div>
            </div>
            <div class="action-col">
                <a href="javascript:void(0)" name="change" data-for="fax2">Изменить</a>
            </div>
        </div>
        <hr>
        <%-- Конец контактной информации --%>
        <%-- Начало допольнительной информации --%>

        <h2 class="ft">Дополнительная информация</h2>

        <div class="row">
            <div class="label-col ft">
                <label for="workTime">Время работы</label>
            </div>
            <div class="value-col ft">
                <input id="workTime" name="workTime" type="text" readonly value="${accountInstance.workTime}" data-old="${accountInstance.workTime}"
                       placeholder="Отсутствует"/>
                <div class="pods fr">В свободной форме укажите график работы</div>
            </div>
            <div class="action-col">
                <a href="javascript:void(0)" name="change" data-for="workTime">Изменить</a>
            </div>
        </div>

        <div class="row">
            <div class="label-col ft">
                <label for="description">Описание</label>
            </div>
            <div class="value-col ft">
                <textarea id="description" name="description" type="text" readonly data-old="${accountInstance.description}"
                          placeholder="Отсутствует">${accountInstance.description}
                </textarea>
                <div class="pods fr">Краткое описанеи вашей деятельности, основые направления деятельности, предлагаемые товары и услуги.</div>
            </div>
            <div class="action-col">
                <a href="javascript:void(0)" name="change" data-for="description">Изменить</a>
            </div>
        </div>

        <div class="row">
            <div class="label-col ft">
                <label for="keywords">Ключевые слова</label>
            </div>
            <div class="value-col ft">
                <textarea id="keywords" name="keywords" type="text" readonly data-old="${accountInstance.keywords}"
                          placeholder="Отсутствуют">${accountInstance.keywords}
                </textarea>
                <div class="pods fr">Укажите через запятые набор слов наиболее полно отражающих вашу деятельности.
                Ключевые слова используются для более эффективного поиска вашего предприятия</div>
            </div>
            <div class="action-col">
                <a href="javascript:void(0)" name="change" data-for="keywords">Изменить</a>
            </div>
        </div>
        <hr>
        <div class="row">
            <h2 class="ft">Филиалы</h2>
            <div class="col-md-3 col-lg-3 col-sm-3 col-xs-2"></div>
            <div class="value-col ft">


                <ul id="affTabNav" class="nav nav-pills">
                    <g:each in="${accountInstance.affiliates}" var="affiliateInstance" status="i">
                        <li class="${(i == 0) ? 'active' : ''}"><a data-toggle="tab" href="#aff${i}">${i+1}</a></li>
                    </g:each>
                    <li id="affPlus"><g:remoteLink action="addAffiliate" onSuccess="pushAffiliate(data)" params="affiliateBlockCount()"><span class="glyphicon glyphicon-plus"></span></g:remoteLink></li>
                </ul>

                <g:formRemote id="affiliateList" name="affiliateList" url="[action: 'updateAffiliates', id: accountInstance.id]"
                              onSuccess="showMessage('success','Изменения успешно сохранены')" onFailure="showMessage('success','Не верные данные')">
                    <div id="affTabContent" class="tab-content">

                        <g:each in="${accountInstance.affiliates}" var="affiliateInstance" status="i">
                            <g:render template="affiliate" model="[affiliateInstance: affiliateInstance, i: i, active: false]"/>
                        </g:each>

                    </div>
                </g:formRemote>

            </div>
            <div class="action-col">
                <a href="javascript:void(0)" onclick="$('#affiliateList').submit()">Сохранить изменения</a>
            </div>
        </div>

        <script>

            function createTab(index){
                var tab = document.createElement('LI');
                var a = document.createElement('A');
                a.setAttribute('href', '#aff'+index);
                a.dataset.toggle = 'tab';
                a.innerText = index+1;
                tab.appendChild(a);
                return tab
            }

            function affiliateBlockCount(){
                return {index: $('[name=affiliateBlock]').length}
            }
            function pushAffiliate(data) {
                var result = []

                var tab = createTab(document.getElementsByName('affiliateBlock').length)

                result.push($("li.active").removeClass('active').removeClass('in'));
                result.push($(tab).appendTo('#affTabNav'));
                result.push($(tab).addClass('active'));

                result.push($(".tab-pane.active.in").removeClass('active').removeClass('in'));
                result.push($(data).appendTo('#affTabContent'));

                result.push($('#affPlus').insertAfter(tab));

                return result
            }
        </script>

        <g:set var="typeList" value="${ru.deloved.CategoryType.listOrderById()}"/>
</div>
    </div>
    <hr>
        <div class="row">
            <h2 class="ft">Категория деятельности</h2>
            <div class="col-md-3 col-lg-3 col-sm-3 col-xs-2"></div>
            <div class="value-col">
                <div class="tab-pane" id="cat" >
                    <ul class="nav nav-pills" style="margin-bottom: 20px;padding: 5px 5px !important;">
                        <g:each in="${typeList}" status="i" var="typeInstance">
                            <li style="font-size: 16pt;" class="${i == 0 ? ' active' : ''}"><a href="#${typeInstance.code}" data-toggle="tab"><g:message code="categorytype.${typeInstance.code}"/></a></li>
                        </g:each>
                    </ul>

                    <div class="tab-content">
                        <g:each in="${typeList}" status="i" var="typeInstance">
                            <div class="tab-pane ${i == 0 ? 'active' : ''}" id="${typeInstance.code}">
                                <div id="${typeInstance.code}tree">
                                </div>
                            </div>
                        </g:each>
                    </div>

                </div>



                <script type="application/javascript">
                    jQuery(function () {


                        $('#updateCat').click(function(){

                            var form = $('#catForm')
                            var categories = "?"
                            <g:each in="${typeList}" status="i" var="typeInstance">
                            jQuery.each(jQuery('#${typeInstance.code}tree').jstree(true).get_selected(), function (i, v) {

                                categories = categories + 'value=' + v + '&'

                            });

                            </g:each>

                            categories = categories + 'prop=categories'
                            console.log(categories)

                            $.ajax({
                                type: 'POST',
                                url: '${createLink(controller: 'account', action: 'editNew', id: accountInstance.id)}'+categories,
                                //data: {prop: 'categories', value: categories, value: categories},
                                beforeSend: function() {},
                                complete: function (textStatus) {
                                    form.html('')
                                },
                                success: function (data, textStatus) {
                                    showMessage(data.status, data.messages)
                                },
                                error: function (XMLHttpRequest, textStatus, errorThrown) {
                                    showMessage('danger', 'Ошибка соединения')
                                }
                            })
                        });

                        <g:each in="${typeList}" status="i" var="typeInstance">
                        jQuery('#${typeInstance.code}tree')
                                .on('changed.jstree', function (e, data) {
                                    if (data.node) {
                                        if (!data.instance.is_leaf(data.node)) {
                                            data.instance.deselect_node(data.node.children_d, true);
                                            data.instance.deselect_node(data.node.parents, true);
                                            if (data.instance.is_selected(data.node)) {
                                                data.instance.close_node(data.node);
                                            } else {
                                                data.instance.open_node(data.node);
                                            }
                                        } else {
                                            data.instance.deselect_node(data.node.parents, true);
                                        }
                                    }
                                })
                                .jstree({
                                    "core": {
                                        "multiple": true,
                                        "data": {
                                            "url": "${createLink(resource:accountInstance, action: 'cat')}",
                                            "data": function (n) {
                                                return {
                                                    "pid": n.id && n.id != '#' ? n.id : ${ Category.findByTypeAndParent(typeInstance, null).id }
                                                }
                                            }
                                        }
                                    },
                                    "checkbox": {
                                        "three_state": false,
                                        "cascade": "undetermined"
                                    },
                                    "plugins": ["json_data", "checkbox", "wholerow"]
                                });
                        </g:each>
                    });
                </script>
            </div>

            <div class="action-col">
                <a href="javascript:void(0)" id="updateCat" name="updateCat">Сохранить изменения</a>
            </div>
        </div>


        <%-- Конец допольнительной информации --%>

    <script>
        $(document).ready(function() {
            $('[name=change]').click(function(e) {
                <%--Получаем элемент который вызвал событие--%>
                var el = e.target || e.srcElement;
                <%--Получаем значение поля data-for элемента который вызвал событие--%>
                var prop = $(el).data('for');
                <%--Нажимаем изменить--%>
                if ($('#'+prop).is('[readonly]')) {
                    $('#'+prop).attr('readonly', false);
                    $(el).html('Сохранить');
                    <%--Нажимает сохранить--%>
                } else {
                    var oldValue = $('#'+prop).data('old');
                    var newValue = $('#'+prop).val();
                    if (oldValue != newValue) {
                        $.ajax({
                            type: 'POST',
                            url: '${createLink(controller: 'account', action: 'editNew', id: accountInstance.id)}',
                            data: {prop: prop, value: newValue},
                            beforeSend: function() {
                                $('#'+prop+'spinner').show();
                            },
                            complete: function (textStatus) {
                                $('#'+prop+'spinner').hide();
                                if (textStatus.status) {

                                } else {

                                }
                            },
                            success: function (data, textStatus) {
                                if (data.status == 'success') {
                                    $('#'+prop).data('old', newValue);

                                } else {
                                    $('#'+prop).val(oldValue);
                                }
                                showMessage(data.status, data.messages)
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                showMessage('danger', 'Ошибка соединения')
                            }
                        })
                    }
                    $('#'+prop).attr('readonly', true);
                    $(el).html('Изменить');
                }
            });
        });
    </script>
</div><g:render template="/_common/modal" model="[
        container: 'reviewsContainer',
        modalId  : 'reviewsModal'
]"/>


<g:render template="/_common/crop"/>
<g:render template="/_common/gallery-single"/>
</body>
</html>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         