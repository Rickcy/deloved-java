<%@ page import="ru.deloved.Profile" %>

<g:if test="${regEditAllowed}">

	<div>
		<ul class="nav nav-tabs">
			<li class="active"><a href="#main" data-toggle="tab"><g:message code="profile.tab.main"/></a></li>

			<g:if test="${regEditAllowed}">
				<g:set var="countryList" value="${ru.deloved.Region.findAllByParent(null)}"/>
				<li><a href="#reg" data-toggle="tab"><g:message code="profile.tab.regions"/></a></li>
			</g:if>
		</ul>

		<div class="tab-content">
			<div class="tab-pane active" id="main">
				<g:render template="formfields"/>
			</div>

			<g:if test="${regEditAllowed}">
				<div class="tab-pane" id="reg">
					<ul class="nav nav-pills">
						<g:each in="${countryList}" status="i" var="countryInstance">
							<li class="${i == 0 ? ' active' : ''}"><a href="#cntr${countryInstance.id}" data-toggle="tab">${countryInstance.name}</a></li>
						</g:each>
					</ul>

					<div class="tab-content">
						<g:each in="${countryList}" status="i" var="countryInstance">
							<div class="tab-pane ${i == 0 ? 'active' : ''}" id="cntr${countryInstance.id}">
								<div id="cntr${countryInstance.id}tree">
								</div>
							</div>
						</g:each>
					</div>

				</div>
			</g:if>
		</div>
	</div>
	<script type="application/javascript">
		jQuery(function () {

			<g:if test="${regEditAllowed}">

			$('#editForm').submit(function () {
				var form = $(this);
				<g:each in="${countryList}" status="i" var="countryInstance">
				jQuery.each(jQuery('#cntr${countryInstance.id}tree').jstree(true).get_selected(), function (i, v) {
					form.append('<input type="hidden" name="reg" value="' + v + '" />');
				});
				</g:each>
			});


			<g:each in="${countryList}" status="i" var="countryInstance">
			jQuery('#cntr${countryInstance.id}tree')
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
								"url": "${createLink(id: params.id, controller: 'profile', action: 'reg')}",
								"data": function (n) {
									return {
										"pid": n.id && n.id != '#' ? n.id : ${countryInstance.id}
									}
								}
							}
						},
						"checkbox": {
							"three_state": false,
							"cascade": "undetermined"
						},
						"plugins": [ "json_data", "checkbox", "wholerow" ]
					});
			</g:each>
			</g:if>
		});
	</script>

</g:if>
<g:else>
	<g:render template="formfields"/>
</g:else>

