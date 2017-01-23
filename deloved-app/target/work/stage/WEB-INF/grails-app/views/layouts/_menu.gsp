<sec:ifAnyGranted roles="ROLE_ACCOUNT,ROLE_ADMIN,ROLE_MEDIATOR,ROLE_JURIST,ROLE_JUDGE">
    <g:if test="${lenta.size()}">

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Деловые события</h3>
            </div>

            <div class="panel-body">

                <div class="lead">Внимание! В этой ленте появляются только новые события, прочитанные убираются!</div>

                <ul class="list-group">
                    <g:each in="${lenta}" status="i" var="obj">
                        <g:set var="eventDate" value="${obj.dateCreated}"/>
                        <g:set var="event" value="${obj}"/>
                        <g:set var="eventType" value="${obj.class.simpleName}"/>
                        <li class="list-group-item">
                            <div class="time"><g:formatDate date="${eventDate}" format="dd MMMM yyyy"/></div>
                            <sec:ifAnyGranted roles="ROLE_ACCOUNT">
                                <g:if test="${eventType == 'Deal'}">
                                    Вам предложена <g:link
                                        url="[resource: event, action: 'thread']">сделка</g:link> с ${event.account == (accounts ?: event.account) ? event.partner.name : event.account.name}
                                </g:if>
                                <g:if test="${eventType == 'DealNewPost'}">
                                    Новые ответы в <g:link
                                        url="[resource: event.item, action: 'thread']">сделке</g:link> с ${event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name}
                                    <span class="badge">+${event.count}</span>
                                </g:if>
                                <g:if test="${eventType == 'DealNewStatus'}">
                                    Новые статусы в <g:link
                                        url="[resource: event.item, action: 'thread']">сделке</g:link> с ${event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name}
                                    <span class="badge">+${event.count}</span>
                                </g:if>
                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_MEDIATOR,ROLE_ADMIN">
                                <g:if test="${eventType == 'Dispute'}">
                                    Открыт <g:link url="[resource: event, action: 'thread']">спор</g:link> от ${event.account.name}
                                </g:if>
                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_MEDIATOR,ROLE_ADMIN,ROLE_ACCOUNT">
                                <g:if test="${eventType == 'DisputeNewPost'}">
                                    Новые ответы в <g:link
                                        url="[resource: event.item, action: 'thread']">споре</g:link> с ${event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name}
                                    <span class="badge">+${event.count}</span>
                                </g:if>
                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_MEDIATOR,ROLE_ADMIN,ROLE_ACCOUNT">
                                <g:if test="${eventType == 'DisputeNewStatus'}">
                                    Новые статусы в <g:link
                                        url="[resource: event.item, action: 'thread']">споре</g:link> с ${event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name}
                                    <span class="badge">+${event.count}</span>
                                </g:if>
                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_JUDGE,ROLE_ADMIN">
                                <g:if test="${eventType == 'Claim'}">
                                    Новый <g:link url="[resource: event, action: 'thread']">иск</g:link> от ${event.account.name}
                                </g:if>
                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_MANAGER,ROLE_ADMIN">
                                <g:if test="${eventType == 'NewItem'}">
                                    Новый <g:link url="[resource: event, action: 'edit']">товар или услуга</g:link> от ${event.account.name}
                                </g:if>
                                <g:if test="${eventType == 'Account'}">
                                    Новое <g:link url="[resource: event, action: 'edit']">предприятие</g:link>
                                    <span class="badge">+1</span>
                                </g:if>
                            %{--<g:if test="${eventType == 'NewSuggestion'}">--}%
                            %{--Новое <g:link url="[resource: event, action: 'index']">Сообщение</g:link>--}%
                            %{--</g:if>--}%
                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_JUDGE,ROLE_ADMIN,ROLE_ACCOUNT">
                                <g:if test="${eventType == 'ClaimNewPost'}">
                                    Новые ответы в <g:link
                                        url="[resource: event.item, action: 'thread']">иске</g:link> с ${event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name}
                                    <span class="badge">+${event.count}</span>
                                </g:if>
                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_JUDGE,ROLE_ADMIN,ROLE_ACCOUNT">
                                <g:if test="${eventType == 'ClaimNewStatus'}">
                                    Новые статусы в <g:link
                                        url="[resource: event.item, action: 'thread']">иске</g:link> с ${event.item.account == (accounts ?: event.item.account) ? event.item.partner.name : event.item.account.name}
                                    <span class="badge">+${event.count}</span>
                                </g:if>
                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_MANAGER,ROLE_ADMIN,ROLE_ACCOUNT">
                                <g:if test="${eventType == 'Review'}">
                                    Новый <g:message code="${'review.value.' + event.value}"/> <g:link url="[resource: event, action: 'show']">отзыв</g:link> от ${event.from.name}
                                </g:if>
                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_JURIST,ROLE_ADMIN">
                                <g:if test="${eventType == 'Consult'}">
                                    Новая <g:link url="[resource: event, action: 'thread']">консультация</g:link> от ${event.account.name}
                                </g:if>
                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_JURIST,ROLE_ADMIN,ROLE_ACCOUNT">
                                <g:if test="${eventType == 'ConsultNewPost'}">
                                    Новые ответы в <g:link
                                        url="[resource: event.item, action: 'thread']">консультации</g:link> с ${event.item.jurist == profile ? event.item.account.name : event.item.account.name}
                                    <span class="badge">+${event.count}</span>
                                </g:if>
                            </sec:ifAnyGranted>

                            <sec:ifAnyGranted roles="ROLE_JURIST,ROLE_ADMIN,ROLE_ACCOUNT">
                                <g:if test="${eventType == 'ConsultNewStatus'}">
                                    Новые статусы в <g:link
                                        url="[resource: event.item, action: 'thread']">консультации</g:link> с ${event.item.jurist == profile ? event.item.account.name :event.item.account.name}
                                    <span class="badge">+${event.count}</span>
                                </g:if>
                            </sec:ifAnyGranted>

                            <sec:ifAnyGranted roles="ROLE_MANAGER,ROLE_ADMIN,ROLE_ACCOUNT">
                                <g:if test="${eventType == 'TicketNewPost'}">
                                    Новые Ответы в обращении в <g:link
                                        url="[resource: event.item, action: 'thread']">Службу поддержки</g:link>
                                    <span class="badge">+${event.count}</span>
                                </g:if>

                            </sec:ifAnyGranted>
                            <sec:ifAnyGranted roles="ROLE_MANAGER,ROLE_ADMIN,ROLE_ACCOUNT">
                                <g:if test="${eventType == 'TicketNewStatus'}">
                                    Новые Статусы в обращении в <g:link
                                        url="[resource: event.item, action: 'thread']">Службу поддержки</g:link>
                                    <span class="badge">+${event.count}</span>
                                </g:if>

                            </sec:ifAnyGranted>

                        </li>
                    </g:each>
                </ul>

            </div>
        </div>
    </g:if>
</sec:ifAnyGranted>


<div class="button_block fm" >
    <div class="col-md-6" style="float: right;margin-right: 8%">
        <sec:ifLoggedIn>
            <g:form id="logoutForm" url="[controller: 'logout']" method="POST"></g:form>
            <g:link style="margin-right: 5%;color: black;font-size: 12pt" controller="panel">
                <span class="glyphicon glyphicon-user"  style="margin-right: 7px;color: black"></span>Вернуться в кабинет

            </g:link>

            <a style="margin-right: 5%;color: black;font-size: 11pt" href="#" onclick="$('#logoutForm').submit()"><span class="glyphicon glyphicon-off"  style="margin-right: 7px;color: black"></span>Выйти</a>
        </sec:ifLoggedIn>
        <sec:ifNotLoggedIn>
            <g:link class="reg" style="margin-right: 5%;color: black;font-size: 12pt" controller="signup" action="create"><span class="glyphicon glyphicon-pencil" style="margin-right: 7px;color: black"></span>Регистрация</g:link>
            <g:link controller='panel' style="margin-right: 5%;color: black;font-size: 12pt"><span class="glyphicon glyphicon-user"  style="margin-right: 7px;color: black"></span>Личный кабинет</g:link>
        </sec:ifNotLoggedIn>
    </div>

    <div style="width: 16%;min-width: 130px;position: relative;top: -2px;left: 5%;">
        <div class="geo" >



            <g:render template="/_common/region-filter" model="[
                    regionFilterData: regionFilterData
            ]"/>

        </div>
    </div>

</div>

<div class="row" style="margin-bottom:0px;">
    <div class="col-md-3 col-sm-3 col-xs-9" style="padding-top: 15px;padding-left: 70px"><g:link controller="front"><img class="imga" src="${resource(dir: 'images', file: 'front/logo.gif')}" style="width: 78%;max-width:250px;min-width: 150px;float: right "/></g:link></div>

    <div class="col-md-9 col-sm-9 col-xs-9 " style="float: right">
        <div class="fm">
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

            <div class=layer>
                <g:link class="${(section == 'companies') ? 'active' : ''}" url="[controller: 'companies']"><span class="glyphicon glyphicon-briefcase" style="margin-right: 3%;font-size: 14pt" > </span>ПАРТНЕРЫ </g:link>
                <g:link	class="${(section == 'goods') ? 'active' : ''}" url="[controller: 'goods']"><span class="glyphicon glyphicon-shopping-cart" style="margin-right: 3%;font-size: 14pt" ></span> ТОВАРЫ</g:link>
                <g:link	class="${(section == 'services') ? 'active' : ''}" url="[controller: 'services']"><span class="glyphicon glyphicon-wrench" style="margin-right: 3%;font-size: 14pt" ></span> УСЛУГИ</g:link>
                <g:link	class="about" url="[controller: 'front', action:'about']"><span class="glyphicon glyphicon-comment" style="margin-right: 3%;font-size: 14pt" ></span> О ПРОЕКТЕ</g:link>

            </div>

        </div>
    <!-- поисковая форма -->

        <g:form controller="${section}" action="search" method="POST">

            <div class="input-group col-md-11  col-sm-11 col-xs-11">
                <g:textField required="" name="search" class="form-control" style="height: 45px;font-size: 15pt;box-shadow: inset 0 0 15px #e2e2e2" value="" placeholder="Поиск"/>
                <span class="input-group-btn">
                    <button class="btn btn-default" type="submit" style="height: 45px;margin-right: 5px;background-color:#94c43d"><span class="glyphicon glyphicon-search" style="padding: 10px;font-size: 15pt;color: white"></span> </button>
                </span>
            </div>
        </g:form>

    <!--конец формы-->
        <div class="search_block">

            <g:link controller="${section}" action="search" class="search_link">Расширенный поиск</g:link>

        </div>

    </div>

</div>

