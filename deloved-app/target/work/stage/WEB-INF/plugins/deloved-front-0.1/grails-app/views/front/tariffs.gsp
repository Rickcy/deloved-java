<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="front">

    <title>Цены</title>
    <style>
    .shadow_block{
        position: relative;
        background-color: white;
    }
    .shadow_block:after {
        content: "";
        display: block;
        position: absolute;
        width: 124%;
        margin-left: -12%;
        z-index: -1;
        height: 20px;
        bottom: 0;
        right: 1em;
        left: 1em;
        border-radius: 50%;
        box-shadow: 0 0 10px 12px rgba(0,0,0,.2);
    }
    .shadow_block:before {
        content: "";
        display: block;
        position: absolute;
        width:124%;
        margin-left:-12%;
        z-index: -1;
        height: 20px;
        top: 0;
        right: 1em;
        left: 1em;
        border-radius: 50%;
        box-shadow: 0 0 10px 12px rgba(0,0,0,.2);
    }
    @media screen and (max-width: 638px){
        .text-left{
            text-align: center;
        }
        .block_text_info{
            width: 115% !important;
            text-align: left;
        }
        .tarifss_list{
            margin: 0!important;
        }
        .tarifss_list li{
            padding: 0 0 0 15px!important;
        }

        .tarifss_list1{
            margin: 0!important;
        }
        .tarifss_list1 li{
            padding: 0 0 0 15px!important;
            text-align: left!important;
        }
        .block_text_info > div >h2.text-right{
            text-align: center;
        }
    }
    </style>
</head>
<body>

    <div class="shadow_block">
        <div class="row" style="margin-top:3%;padding: 2% 8%;width: 110%;margin-left: -5%;background-color: white">

            <h2 style="font-weight: bold;text-align:center!important;color:rgb(148, 196, 61)"><span style="margin-left: 1%">Тарифные планы и цены на услуги</span>
        <div style="background-image: linear-gradient(270deg, rgb(248, 215, 53), rgb(148, 196, 61) 110%);
        width: 98%;
        height: 4px;"></div></h2>


    <div class="row">
        <div class="col-sm-6">
            <div class=podp_blue>Стартовая подписка</div>
            <ul class=tarifs_list>
                <li>Создание собственной деловой карточки.</li>
                <li>Создание списка товаров и услуг.</li>
                <li>Доступ к банку готовых форм и шаблонов документов.</li>
            </ul>
        </div>
        <div class="col-sm-6">
            <div class=podp_green>Расширенная подписка</div>
            <ul class=tarifs_list>
                <li>Включены все возможности стартовой подписки.</li>
                <li>Ведение сделок онлайн при юридическом сопровождении.</li>
                <li>Формирование собственного делового рейтинга.</li>
                <li>Просмотр деловой репутации партнеров, оценка рисков.</li>
                <li>Разрешение конфликтных ситуаций с участием медиатора.</li>
                <li>Рассмотрение споров в Третейском суде.</li>
            </ul>
        </div>
    </div>

    <hr>

    <div class="row">
        <div class="col-sm-3">
            <div class=price_block>
                <span class=head>1 месяц</span>
                <span class=price>Оплата:<strong> 1000&nbsp;р.</strong><br>В месяц: <strong>1000&nbsp;р.</strong></span>
                <span class=comment style="background-color:#d00000;" >Скидки нет<br>Экономии нет</span>
            </div>
        </div>

        <div class="col-sm-3">
            <div class=price_block>
                <span class=head>3 месяца</span>
                <span class=price>Оплата:<strong> 2850&nbsp;р.</strong><br>В месяц: <strong>950&nbsp;р.</strong></span>
                <span class=comment >Скидка: 5%<br>Экономия: 150 р.</span>
            </div>
        </div>

        <div class="col-sm-3">
            <div class=price_block>
                <span class=head>6 месяцев</span>
                <span class=price>Оплата:<strong> 5580&nbsp;р.</strong><br>В месяц: <strong>930&nbsp;р.</strong></span>
                <span class=comment >Скидка: 7%<br>Экономия: 420 р.</span>
            </div>
        </div>


        <div class="col-sm-3">
            <div class=price_block>
                <span class=head>12 месяцев</span>
                <span class=price>Оплата:<strong> 10800&nbsp;р.</strong><br>В месяц: <strong>900&nbsp;р.</strong></span>
                <span class=comment >Скидка: 10%<br>Экономия: 1200 р.</span>
            </div>
        </div>
    </div>

    <div style="text-align:center; margin-top:30px;margin-bottom: 30px">
        <sec:ifLoggedIn>
            <a href="${createLink(controller: 'billing')}" class=oplata>Оплатить подписку</a>
        </sec:ifLoggedIn>
        <sec:ifNotLoggedIn>
            <a href="${createLink(controller: 'signup', action: 'index')}" class="oplata">Зарегистрироваться</a>
        </sec:ifNotLoggedIn>
    </div>

    </div>
        </div>
    <div class="shadow_block">
        <div class="row" style="margin-top:7%;padding: 2% 8%;width: 110%;margin-left: -5%;background-color: white">
    <h2 style="font-weight: bold;text-align:center!important;color:rgb(148, 196, 61)"><span style="margin-left: 1%">Способы оплаты</span>
        <div style="background-image: linear-gradient(270deg, rgb(248, 215, 53), rgb(148, 196, 61) 110%);
        width: 98%;
        height: 4px;"></div></h2>

    <div class="row info_block">



    <div class="row info_block" style="margin-right: 0!important;">
        <div class="col-xs-12" style="margin-left:5%">
        <div class="col-xs-12 col-sm-6 block_text_info">
            <div class="col-xs-12 col-sm-3" style="text-align:center; ">
                <span class="glyphicon glyphicon-list-alt" style="color: #4B7BBE;font-size: 42pt"></span>
            </div>

            <div class="col-xs-12 col-sm-9">
                <h2 class="text-left">Расчетный счет</h2>
                <p>
                <ul class="tarifss_list">
                    <li>Счет на оплату формируется автоматически.
                 После оплаты передается Акт выполненных работ.
                    Без дополнительных комиссий.</li>

                </ul>
            </p>
            </div>
        </div>

        <div class="col-xs-12 col-sm-6 block_text_info">
            <div class="col-xs-12 col-sm-9">
                <h2 class="text-right">Оплата через Pay Master</h2>
                <p>
                <ul class="tarifss_list1">
                    <li>С лицевого счета пользователя или наличными через терминалы по квитанции.</li>


                </ul>
            </p>
            </div>
            <div class="col-xs-12 col-sm-3" style="text-align: center">
                <span class="glyphicon glyphicon-phone" style="color: #4B7BBE;font-size: 42pt"></span>
            </div>
        </div>


        </div>



    </div>
</div>
</div>
        </div>

</body>