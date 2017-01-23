<%--
  Created by IntelliJ IDEA.
  User: User11
  Date: 15.09.2016
  Time: 17:22
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="front">
    <title>Статьи</title>
    <meta content="Статьи" name="description">
    <meta content="Статьи" name="keywords">
    <style>
    .list_article{
        list-style: none;
        margin: 0;
        padding-left: 0;
    }
        .list_article>li{
            margin: 30px;
            font-size: 13pt;
        }
    </style>
    <style>
    .shadow_block{
        position: relative;
        background-color: white;
    }
    .shadow_block:after {
        content: "";
        display: block;
        position: absolute;
        width: 104%;
        margin-left: -2%;
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
        width:104%;
        margin-left:-2%;
        z-index: -1;
        height: 20px;
        top: 0;
        right: 1em;
        left: 1em;
        border-radius: 50%;
        box-shadow: 0 0 10px 12px rgba(0,0,0,.2);
    }
    .searchtext>h4{
        color: #559ce7;
    }
    .new>b{
        font-weight: 100;
    }
    </style>
</head>

<body>
<div class="shadow_block">
    <div class="row" style="margin-top:5%;padding: 2% 8%;width: 110%;margin-left: -5%;background-color: white">
        <h2 style="font-weight: bold;text-align:center!important;color:rgb(148, 196, 61)"><span style="margin-left: 1%">Статьи</span>
            <div style="background-image: linear-gradient(270deg, rgb(248, 215, 53), rgb(148, 196, 61) 110%);
            width: 98%;
            height: 4px;"></div></h2>

                            <div class="col-xs-12">
                                <div class="col-sm-12 col-xs-12">
                        <div class="col-sm-4 col-sm-offset-0  col-xs-12   col-xs-offset-0 text-center ico" style="margin-top: 2%"><g:link style="color: white;text-shadow: 0 0 1px whitesmoke;" controller="article" action="rating_system" ><img src="${resource(dir: 'images', file: 'front/icon_rating.png')}" style="max-width:100px;"/><br><span class="text_btn_main" style="font-size: 15pt;color:#94C43D;font-weight: 600">Рейтинг</span></g:link></div>
                        <div class="col-sm-8 col-sm-offset-0 col-xs-12   col-xs-offset-0 text-left" style="margin-top: 4%;font-size: 17pt;">                  Избегайте рисков – работайте с проверенными контрагентами! <hr></div>

                                </div>
                                <div class="col-sm-12 col-xs-12">
                                    <div class="col-sm-4 col-sm-offset-0  col-xs-12   col-xs-offset-0 text-center ico" style="margin-top: 2%"><g:link style="color: white;text-shadow: 0 0 1px whitesmoke" controller="article" action="jurist_service" ><img src="${resource(dir: 'images', file: 'front/icon_jurist.png')}" style="max-width:100px;"/> <br><span class="text_btn_main" style="font-size: 15pt;color:#94C43D;font-weight: 600">Помощь юриста</span></g:link></div>
                                    <div class="col-sm-8 col-sm-offset-0 col-xs-12   col-xs-offset-0 text-left" style="margin-top:4%;font-size: 17pt;">Онлайн консультация юриста.<hr></div>
                            </div>
                                <div class="col-sm-12 col-xs-12">

                       <div class="col-sm-4 col-sm-offset-0  col-xs-12   col-xs-offset-0 text-center ico" style="margin-top: 2%"><g:link style="color: white;text-shadow: 0 0 1px whitesmoke" controller="article" action="judge_service" ><img src="${resource(dir: 'images', file: 'front/icon_sud.png')}" style="max-width:100px;"/> <br><span class="text_btn_main" style="font-size: 15pt;color:#94C43D;font-weight: 600">Третейский суд</span></g:link></div>
                       <div class="col-sm-8 col-sm-offset-0 col-xs-12   col-xs-offset-0 text-left" style="margin-top: 4%;font-size: 17pt;"> Подача иска в один клик. Судебное разрешение споров в Третейском суде «ДЕЛОВЕД» квалифицированными арбитрами.<hr></div>
                        </div>
                                <div class="col-sm-12 col-xs-12">
                            <div class="col-sm-4 col-sm-offset-0  col-xs-12  col-xs-offset-0 text-center ico" style="margin-top: 2%"><g:link style="color: white;text-shadow: 0 0 1px whitesmoke" controller="article" action="mediation_service" ><img src="${resource(dir: 'images', file: 'front/icon_mediation.png')}" style="max-width:100px"/> <br><span class="text_btn_main" style="font-size: 15pt;color:#94C43D;font-weight: 600">Медиация</span></g:link></div>
                            <div class="col-sm-8 col-sm-offset-0 col-xs-12   col-xs-offset-0 text-left" style="margin-top: 4%;font-size: 17pt;">Урегулирование спора с использованием процедуры медиации - эффективный способ, основанный на переговорах.<hr></div>

                                </div>
                                <div class="clearfix"></div>
                                <div class="col-sm-12 col-xs-12">
                                    <div class="col-sm-4 col-sm-offset-0  col-xs-12  col-xs-offset-0 text-center ico" ><g:link style="color: white;" controller="article" action="deal_online" > <img src="${resource(dir: 'images', file: 'front/icon_deal.png')}" style="max-width:100px;"/><br><span class="text_btn_main" style="font-size: 15pt;color:#94C43D;font-weight: 600 ">Сделки онлайн</span></g:link></div>
                                    <div class="col-sm-8 col-sm-offset-0 col-xs-12   col-xs-offset-0 text-left" style="margin-top: 4%;font-size: 17pt;">Выбор надежного контрагента и предложение сделки в один клик – просто и удобно!<hr></div>

                                </div>
        </div>
        </div>
</div>
</body>
</html>