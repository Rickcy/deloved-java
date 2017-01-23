<%@ page import="ru.deloved.Adcontent" %>
<%@ page import="ru.deloved.AdcontentType" %>


<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'account', 'error')} required form-group">
	<label class="col-sm-3 control-label" for="account">
		<g:message code="adcontent.account.label" default="Account"/>
		<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
	</label>

	<div class="col-sm-9">
		<g:if test="${objInstance.id == null}">
			<g:if test="${objInstance.accountId}">
				${objInstance.account?.name}
				<g:hiddenField name="account.id" value="${objInstance.accountId}"/>
			</g:if>
			<g:if test="${accountList?.size() > 0}">
				<g:select id="account" name="account.id" class="form-control" from="${accountList}" optionKey="id" optionValue="name" required=""
						  value="${objInstance?.account?.id}"/>
			</g:if>
			<g:else>
				<g:render template="/_common/auto-complete" model="[
						acAction   : createLink(controller: 'adcontent', action: 'accounts'),
						acMinLength: 4,
						acKeyValue : '',
						acKeyName  : 'account.id',
						acViewValue: '',
						acViewName : 'accountname'
				]"/>

			</g:else>
		</g:if>
		<g:else>
			<g:if test="${accountList?.size() > 1}">
				<g:select id="account" name="account.id" class="form-control" from="${accountList}" optionKey="id" optionValue="name" required=""
						  value="${objInstance?.account?.id}"/>
			</g:if>
			<g:if test="${accountList?.size() == 1}">
				${objInstance.account?.name}
			</g:if>
			<g:else>
				<g:render template="/_common/auto-complete" model="[
						acAction   : createLink(controller: 'adcontent', action: 'accounts'),
						acMinLength: 4,
						acKeyValue : objInstance.accountId,
						acKeyName  : 'account.id',
						acViewValue: objInstance.account.name,
						acViewName : 'accountname'
				]"/>
			</g:else>
		</g:else>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'name', 'error')} required form-group">
	<label for="name" class="col-sm-3 control-label">
		<g:message code="adcontent.name.label" default="Name"/>
		<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
	</label>


	<div class="col-sm-9">
		<g:textField class="form-control" name="name" required="" value="${objInstance?.name}"/>

	</div>

</div>

<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'description', 'error')} form-group">
	<label for="description" class="col-sm-3 control-label">
		<g:message code="adcontent.description.label" default="Description"/>
	</label>


	<div class="col-sm-9">
		<g:textArea style="min-height: 100px" class="form-control" name="description" value="${objInstance?.description}"/>
	</div>

</div>

<g:if test="${objInstance.type == AdcontentType.Video.value()}">
	<div class="fieldcontain ${hasErrors(bean: objInstance, field: 'url', 'error')} required form-group">
		<label for="url" class="col-sm-3 control-label">
			<g:message code="adcontent.url.label" default="URL"/>
			<span class="required-indicator"><span class="glyphicon glyphicon-asterisk"/></span>
		</label>


		<div class="col-sm-9">
			<g:textArea style="min-height: 100px" class="form-control" name="url" required="" value="${objInstance?.url}"/>
		</div>
	</div>
</g:if>
<g:elseif test="${objInstance.type == AdcontentType.Audio.value()}">
	Audio
</g:elseif>
<g:elseif test="${objInstance.type == AdcontentType.Image.value()}">
	Image
</g:elseif>
<g:elseif test="${objInstance.type == AdcontentType.Pdf.value()}">
	Pdf
</g:elseif>

<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
	<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'approved', 'error')}">
		<label class="control-label col-sm-3" for="approved">
			<g:message code="account.chargeStatus.label" default="Approved"/>
		</label>

		<div class="col-sm-9">
			<div class="btn-group" data-toggle="buttons">
				<label class="btn btn-default <g:if test="${!objInstance?.approved}">active</g:if>">
					<g:radio name="approved" value="false" checked="${!objInstance?.approved}"/>На модерации
				</label>
				<label class="btn btn-default <g:if test="${objInstance?.approved}">active</g:if>">
					<g:radio name="approved" value="true" checked="${objInstance?.approved}"/>Одобрен
				</label>
			</div>
		</div>
	</div>
</sec:ifAnyGranted>
