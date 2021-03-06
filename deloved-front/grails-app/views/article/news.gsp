<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="front">

    <title>Новости</title>
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
    <script>
        $(document).ready(function(){
            var div=$('.new').text();
            var div2=$('.new');
            var x =localStorage.search;
            if(x===undefined){}
            else {div2.each(function(i,elem){
                var res =$(elem).html();
                var regV = x;
                var result = res.match(x);
                if (result) {
                    $(elem).addClass('searchtext');
                    var scrollTop = $('.searchtext').offset().top;
                    $(document).scrollTop(scrollTop-100);
                }
            });

            }})
    </script>
</head>
<body>
<div class="shadow_block">
<div class="row" style="margin-top:3%;padding: 2% 8%;width: 110%;margin-left: -5%;background-color: white">
<h2 style="font-weight: bold;text-align:center!important;color:rgb(148, 196, 61)"><span style="margin-left: 1%">Новости</span>
    <div style="background-image: linear-gradient(270deg, rgb(248, 215, 53), rgb(148, 196, 61) 110%);
    width: 98%;
    height: 4px;"></div></h2>



    <g:newsContent code="News1"/>
    <g:newsContent code="News2"/>
    <g:newsContent code="News3"/>
    <g:newsContent code="News4"/>
    <g:newsContent code="News5"/>
    <g:newsContent code="News6"/>
    <g:newsContent code="News7"/>
    <g:newsContent code="News8"/>
    <g:newsContent code="News9"/>
    <g:newsContent code="News10"/>
    <g:newsContent code="News11"/>
    <g:newsContent code="News12"/>
    <g:newsContent code="News13"/>
    <g:newsContent code="News14"/>
    <g:newsContent code="News15"/>
    <g:newsContent code="News16"/>
    <g:newsContent code="News17"/>
    <g:newsContent code="News18"/>
    <g:newsContent code="News19"/>
    <g:newsContent code="News20"/>
    <g:newsContent code="News21"/>
    <g:newsContent code="News22"/>
    <g:newsContent code="News23"/>
    <g:newsContent code="News24"/>
    <g:newsContent code="News25"/>
    <g:newsContent code="News26"/>
    <g:newsContent code="News27"/>
    <g:newsContent code="News28"/>
    <g:newsContent code="News29"/>
    <g:newsContent code="News30"/>
    <g:newsContent code="News31"/>
    <g:newsContent code="News32"/>
    <g:newsContent code="News33"/>
    <g:newsContent code="News34"/>
    <g:newsContent code="News35"/>
    <g:newsContent code="News36"/>
    <g:newsContent code="News37"/>
    <g:newsContent code="News38"/>
    <g:newsContent code="News39"/>
    <g:newsContent code="News40"/>
    <g:newsContent code="News41"/>
    <g:newsContent code="News42"/>
    <g:newsContent code="News43"/>
    <g:newsContent code="News44"/>
    <g:newsContent code="News45"/>
    <g:newsContent code="News46"/>
    <g:newsContent code="News47"/>
    <g:newsContent code="News48"/>
    <g:newsContent code="News49"/>
    <g:newsContent code="News50"/>
    <g:newsContent code="News51"/>
    <g:newsContent code="News52"/>
    <g:newsContent code="News53"/>
    <g:newsContent code="News54"/>
    <g:newsContent code="News55"/>
    <g:newsContent code="News56"/>
    <g:newsContent code="News57"/>
    <g:newsContent code="News58"/>
    <g:newsContent code="News59"/>
    <g:newsContent code="News60"/>
    <g:newsContent code="News61"/>
    <g:newsContent code="News62"/>
    <g:newsContent code="News63"/>
    <g:newsContent code="News64"/>
    <g:newsContent code="News65"/>
    <g:newsContent code="News66"/>
    <g:newsContent code="News67"/>
    <g:newsContent code="News68"/>
    <g:newsContent code="News69"/>
    <g:newsContent code="News70"/>
    <g:newsContent code="News71"/>
    <g:newsContent code="News72"/>
    <g:newsContent code="News73"/>
    <g:newsContent code="News74"/>
    <g:newsContent code="News75"/>
    <g:newsContent code="News76"/>
    <g:newsContent code="News77"/>
    <g:newsContent code="News78"/>
    <g:newsContent code="News79"/>
    <g:newsContent code="News80"/>
    <g:newsContent code="News81"/>
    <g:newsContent code="News82"/>
    <g:newsContent code="News83"/>
    <g:newsContent code="News84"/>
    <g:newsContent code="News85"/>
    <g:newsContent code="News86"/>
    <g:newsContent code="News87"/>
    <g:newsContent code="News88"/>
    <g:newsContent code="News89"/>
    <g:newsContent code="News90"/>
    <g:newsContent code="News91"/>
    <g:newsContent code="News92"/>
    <g:newsContent code="News93"/>
    <g:newsContent code="News94"/>
    <g:newsContent code="News95"/>
    <g:newsContent code="News96"/>
    <g:newsContent code="News97"/>
    <g:newsContent code="News98"/>
    <g:newsContent code="News99"/>
    <g:newsContent code="News100"/>
    <g:newsContent code="News101"/>
    <g:newsContent code="News102"/>
    <g:newsContent code="News103"/>
    <g:newsContent code="News104"/>
    <g:newsContent code="News105"/>
    <g:newsContent code="News106"/>
    <g:newsContent code="News107"/>
    <g:newsContent code="News108"/>
    <g:newsContent code="News109"/>
    <g:newsContent code="News110"/>
    <g:newsContent code="News111"/>
    <g:newsContent code="News112"/>
    <g:newsContent code="News113"/>
    <g:newsContent code="News114"/>
    <g:newsContent code="News115"/>
    <g:newsContent code="News116"/>
    <g:newsContent code="News117"/>
    <g:newsContent code="News118"/>
    <g:newsContent code="News119"/>
    <g:newsContent code="News120"/>
    <g:newsContent code="News121"/>
    <g:newsContent code="News122"/>
    <g:newsContent code="News123"/>
    <g:newsContent code="News124"/>
    <g:newsContent code="News125"/>
    <g:newsContent code="News126"/>
    <g:newsContent code="News127"/>
    <g:newsContent code="News128"/>
    <g:newsContent code="News129"/>
    <g:newsContent code="News130"/>
    <g:newsContent code="News131"/>
    <g:newsContent code="News132"/>
    <g:newsContent code="News133"/>
    <g:newsContent code="News134"/>
    <g:newsContent code="News135"/>
    <g:newsContent code="News136"/>
    <g:newsContent code="News137"/>
    <g:newsContent code="News138"/>
    <g:newsContent code="News139"/>
    <g:newsContent code="News140"/>
    <g:newsContent code="News141"/>
    <g:newsContent code="News142"/>
    <g:newsContent code="News143"/>
    <g:newsContent code="News144"/>
    <g:newsContent code="News145"/>
    <g:newsContent code="News146"/>
    <g:newsContent code="News147"/>
    <g:newsContent code="News148"/>
    <g:newsContent code="News149"/>
    <g:newsContent code="News150"/>
    <g:newsContent code="News151"/>
    <g:newsContent code="News152"/>
    <g:newsContent code="News153"/>
    <g:newsContent code="News154"/>
    <g:newsContent code="News155"/>
    <g:newsContent code="News156"/>
    <g:newsContent code="News157"/>
    <g:newsContent code="News158"/>
    <g:newsContent code="News159"/>
    <g:newsContent code="News160"/>
    <g:newsContent code="News161"/>
    <g:newsContent code="News162"/>
    <g:newsContent code="News163"/>
    <g:newsContent code="News164"/>
    <g:newsContent code="News165"/>
    <g:newsContent code="News166"/>
    <g:newsContent code="News167"/>
    <g:newsContent code="News168"/>
    <g:newsContent code="News169"/>
    <g:newsContent code="News170"/>
    <g:newsContent code="News171"/>
    <g:newsContent code="News172"/>
    <g:newsContent code="News173"/>
    <g:newsContent code="News174"/>
    <g:newsContent code="News175"/>
    <g:newsContent code="News176"/>
    <g:newsContent code="News177"/>
    <g:newsContent code="News178"/>
    <g:newsContent code="News179"/>
    <g:newsContent code="News180"/>
    <g:newsContent code="News181"/>
    <g:newsContent code="News182"/>
    <g:newsContent code="News183"/>
    <g:newsContent code="News184"/>
    <g:newsContent code="News185"/>
    <g:newsContent code="News186"/>
    <g:newsContent code="News187"/>
    <g:newsContent code="News188"/>
    <g:newsContent code="News189"/>
    <g:newsContent code="News190"/>
    <g:newsContent code="News191"/>
    <g:newsContent code="News192"/>
    <g:newsContent code="News193"/>
    <g:newsContent code="News194"/>
    <g:newsContent code="News195"/>
    <g:newsContent code="News196"/>
    <g:newsContent code="News197"/>
    <g:newsContent code="News198"/>
    <g:newsContent code="News199"/>
    <g:newsContent code="News200"/>






</div>
    </div>
</body>