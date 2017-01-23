
		<div class="left-block">

			<ul class="admin_menu">

				<li><a class="home" href="${createLink(controller: "panel")}"><g:message code="default.home.label"/></a></li>

			</ul>


			<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">

				<h1>Справочники</h1>

				<div class="ug"></div>

				<ul class="admin_menu">

					<li <g:if test="${params.controller == 'category'}">class="active"</g:if>><g:link controller="category"><g:message code="category.list.label"/></g:link></li>
					<li <g:if test="${params.controller == 'measure'}">class="active"</g:if>><g:link controller="measure"><g:message code="measure.list.label"/></g:link></li>
					<li <g:if test="${params.controller == 'region'}">class="active"</g:if>><g:link controller="region"><g:message code="region.list.label"/></g:link></li>
					<li <g:if test="${params.controller == 'content'}">class="active"</g:if>><g:link controller="content"><g:message code="content.list.label"/></g:link></li>
					<li <g:if test="${params.controller == 'systemCurrency'}">class="active"</g:if>><g:link controller="systemCurrency"><g:message code="currency.list.label"/></g:link></li>
					<sec:ifAnyGranted roles="ROLE_ADMIN">
						<li <g:if test="${params.controller == 'tariffPrice'}">class="active"</g:if>><g:link controller="tariffPrice"><g:message code="tariff.list.label"/></g:link></li>
					</sec:ifAnyGranted>
				</ul>

				<h1>Обратная связь</h1>
				<div class="ug"></div>

				<ul class="admin_menu">
					<li <g:if test="${params.controller == 'suggestion'}">class="active"</g:if>><g:link controller="suggestion">Пожелания и предложения</g:link></li>
					<li <g:if test="${params.controller == 'suggestionCategories'}">class="active"</g:if>><g:link controller="suggestionCategories">Категории предложений</g:link></li>
					<li <g:if test="${params.controller == 'ticket'}">class="active"</g:if>><g:link controller="ticket">Обращения в службу поддержки</g:link></li>
				</ul>


			</sec:ifAnyGranted>

			<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">

				<h1>Пользователи</h1>

				<div class="ug"></div>

				<ul class="admin_menu">

					<li <g:if test="${params.controller == 'user'}">class="active"</g:if>><g:link controller="user"><g:message code="user.list.label"/></g:link></li>
					<li <g:if test="${params.controller == 'profile'}">class="active"</g:if>><g:link controller="profile"><g:message code="profile.list.label"/></g:link></li>

				</ul>
			</sec:ifAnyGranted>

			<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
				<h1>Биллинг</h1>

				<div class="ug"></div>

				<ul class="admin_menu">
					<li <g:if test="${params.controller == 'paymentRequest'}">class="active"</g:if>><g:link controller="paymentRequest">Счета на оплату</g:link></li>
				</ul>
			</sec:ifAnyGranted>

			<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER,ROLE_ACCOUNT">
				<h1>Бизнес</h1>

				<div class="ug"></div>

				<ul class="admin_menu">

					<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
						<li <g:if test="${params.controller == 'account'}">class="active"</g:if>><g:link controller="account">Данные предприятий</g:link></li>
					</sec:ifAnyGranted>

					<sec:ifAnyGranted roles="ROLE_ACCOUNT">
						<li <g:if test="${params.controller == 'account'}">class="active"</g:if>><g:link controller="account">Мои данные</g:link></li>
					</sec:ifAnyGranted>

				<!-- Сделки -->
					<li <g:if test="${params.controller == 'deal'}">class="active"</g:if>><g:link controller="deal"><g:message code="deal.list.label"/>
						<g:if test="${userNewObjectsCount?.deals}"><span class="badge badge_red">+${userNewObjectsCount?.deals}</span></g:if>
						<g:if test="${freeAccount}"><span class="badge badge_green badge_pro">pro</span></g:if>
					</g:link></li>

					<!-- Отзывы -->
					<li <g:if test="${params.controller == 'review'}">class="active"</g:if>><g:link controller="review">Отзывы
						<g:if test="${userNewObjectsCount?.reviews}"><span class="badge badge_red">+${userNewObjectsCount?.reviews}</span></g:if>
						<g:if test="${freeAccount}"><span class="badge badge_green badge_pro">pro</span></g:if>
					</g:link></li>

					<!-- Товары -->
					<li <g:if test="${params.controller == 'item' && params.categoryTypeCode == 'GOOD'}">class="active"</g:if>><g:link mapping="GOOD"><g:message
							code="goods.list.label"/></g:link></li>

					<!-- Услуги -->
					<li <g:if test="${params.controller == 'item' && params.categoryTypeCode == 'SERVICE'}">class="active"</g:if>><g:link mapping="SERVICE"><g:message
							code="services.list.label"/></g:link></li>

					<!-- Рекламные материалы -->
					<li <g:if test="${params.controller == 'adcontent'}">class="active"</g:if>><g:link controller="adcontent"><g:message code="adcontent.list.label"/>
						<g:if test="${freeAccount}"><span class="badge badge_green badge_pro">pro</span></g:if>
					</g:link></li>

				</ul>

			</sec:ifAnyGranted>

			<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER,ROLE_ACCOUNT,ROLE_MEDIATOR,ROLE_JURIST,ROLE_JUDGE">
				<h1>Юриспрудеция</h1>
				<div class="ug"></div>
				<ul class="admin_menu">
					<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER,ROLE_ACCOUNT,ROLE_MEDIATOR">
						<li <g:if test="${params.controller == 'dispute'}">class="active"</g:if>><g:link controller="dispute"><g:message code="dispute.list.label"/>
							<g:if test="${userNewObjectsCount?.disputes}"><span class="badge badge_red">+${userNewObjectsCount?.disputes}</span></g:if>
							<g:if test="${freeAccount}"><span class="badge badge_green badge_pro">pro</span></g:if>
						</g:link></li>
					</sec:ifAnyGranted>
					<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER,ROLE_ACCOUNT,ROLE_JUDGE">
						<li <g:if test="${params.controller == 'claim'}">class="active"</g:if>><g:link controller="claim"><g:message code="claim.list.label"/>
							<g:if test="${userNewObjectsCount?.claims}"><span class="badge badge_red">+${userNewObjectsCount?.claims}</span></g:if>
							<g:if test="${freeAccount}"><span class="badge badge_green badge_pro">pro</span></g:if>
						</g:link></li>
					</sec:ifAnyGranted>
					<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER,ROLE_ACCOUNT,ROLE_JURIST">
						<li <g:if test="${params.controller == 'consult'}">class="active"</g:if>><g:link controller="consult"><g:message code="consult.list.label"/>
							<g:if test="${userNewObjectsCount?.consults}"><span class="badge badge_red">+${userNewObjectsCount?.consults}</span></g:if>
							<g:if test="${freeAccount}"><span class="badge badge_green badge_pro">pro</span></g:if>
						</g:link></li>

					</sec:ifAnyGranted>
					<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER,ROLE_ACCOUNT,ROLE_MEDIATOR,ROLE_JURIST,ROLE_JUDGE">
						<li <g:if test="${params.controller == 'document'}">class="active"</g:if>>
							<g:link controller="document"><g:message code="document.list.label"/></g:link>
						</li>
					</sec:ifAnyGranted>
				</ul>
			</sec:ifAnyGranted>


			<sec:ifAnyGranted roles="ROLE_ACCOUNT">
				<h1>Служба поддержки</h1>
				<div class="ug"></div>
				<ul class="admin_menu">
					<li <g:if test="${params.controller == 'ticket'}">class="active"</g:if>><g:link controller="ticket"><g:message code="ticket.list.label"/></g:link></li>
				</ul>
			</sec:ifAnyGranted>
			<sec:ifAnyGranted roles="ROLE_ADMIN">
				<h1>Администратор</h1>

				<div class="ug"></div>

				<ul class="admin_menu">
					<li <g:if test="${params.controller == 'adminTool'}">class="active"</g:if>><g:link controller="adminTool"><g:message code="admintool.label"/></g:link></li>

				</ul>
			</sec:ifAnyGranted>
		</ul>
		</div>





