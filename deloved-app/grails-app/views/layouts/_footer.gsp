<div>
	<div class="row bottom_menu" style="text-align: center;border-bottom-left-radius:0;border-bottom-right-radius:0;width: 107%;margin-left: -3.5%  ">

		<div class="col-xs-12">
			<ul style="padding: 0">
				<li><g:link controller="article" action="ticket">Статьи</g:link></li>
				<li><g:link controller="front" action="about">О портале</g:link></li>
				<li><g:link controller="front" action="tariffs">Цены</g:link></li>
				<li><g:link controller="front" action="sogl">Соглашение</g:link></li>
				<sec:ifNotLoggedIn>
					<li><g:render template="/layouts/create-answer" model="[]"/></li>
				</sec:ifNotLoggedIn>
				<sec:ifLoggedIn>
					<li><g:link controller="ticket" action="create">Связаться с нами</g:link></li>
				</sec:ifLoggedIn>
				<li><g:render template="/layouts/create-suggestion" model="[]"/></li>

			</ul>
		</div>
	</div>


	<div class="row bottom_block" style="border-top-left-radius: 0;border-top-right-radius:0;background-color: #efefef!important;color: #428BCA;font-size: 105%;width: 107%;margin-left: -3.5%">
		<div class="col-xs-4 col-sm-4 col-sm-offset-1" style="margin-top:10px;"><img style="margin-left: 20px;float:left; margin-right:10px;" src="${resource(dir: 'images', file: 'front/logo2.gif')}"/>
			<span class="hidden-sm hidden-xs">
			Бизнес портал товаров и услуг<br/>
			Все права защищены &copy; 2015
		</span></div>
		<div class="col-xs-6" style="font-size: 130%;text-align: right;color: #428BCA">
			<a href="https://vk.com/publicdelovedru" target="_blank"><img style="float:right;width: 80px" src="${resource(dir: 'images', file: 'front/vkIcon.png')}" /></a>
			<a href="http://www.facebook.com" target="_blank"><img style="margin-left: 4%;float:right;width: 80px;" src="${resource(dir: 'images', file: 'front/facebook.png')}"/></a>

		</div>

	</div>

</div>
