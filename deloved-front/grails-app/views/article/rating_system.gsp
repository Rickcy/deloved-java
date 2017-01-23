
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="front">
    <title><g:titleContent code="TDKContentRatingSystem"/></title>
    <g:descKeyContent code="TDKContentRatingSystem"/>
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
    @media screen and (max-width: 420px){
        .h2_main_name{
            font-size: 14pt!important;
        }
    }
    </style>
</head>

<body>
<div class="shadow_block">
    <div class="row" style="margin-top:2%;padding: 2% 8%;width: 110%;margin-left: -5%;background-color: white">
    <h2 class="h2_main_name" style="font-weight: bold;text-align:left!important;color:rgb(75, 100, 190)"><img style="min-width:40px;width: 5%;margin-left: 20px;margin-right: 20px" src="${resource(dir: 'images', file: 'front/icon_rating.png')}"/>Рейтинговая система
        <div style="background-image: linear-gradient(270deg, rgb(149, 184, 234), rgb(75, 100, 190) 110%);
        width: 98%;
        height: 4px;"></div></h2>
    <div class="img-responsive">
        <img src="${resource(dir: 'images', file: 'front/rating.jpg')}" style="width: 90%;margin: 0 5%;border-radius: 30px"/>
    </div>
<div class="row">
    <div style="margin-top: 3%">
        <div class="col-sm-6" style="font-size: 15pt;padding-top: 5%">

            <g:contContentColumn1 code="TDKContentRatingSystem"/>


        </div>

        <div class="col-sm-6"><img style="width: 100%" src="${resource(dir: 'images', file: 'admin/rating1.jpg')}"/></div>

    </div>
</div>
<div class="row">
    <div style="margin-top: 3%">
        <div class="col-sm-6 col-sm-push-6 "  style="font-size: 15pt;padding-top: 5%">
            <g:contContentColumn2 code="TDKContentRatingSystem"/>

        </div>


        <div class="col-sm-6 col-sm-pull-6"><img style="width: 100%" src="${resource(dir: 'images', file: 'admin/rating2.jpg')}"/></div>




    </div>
</div>
</div>

</div>
</body>
</html>