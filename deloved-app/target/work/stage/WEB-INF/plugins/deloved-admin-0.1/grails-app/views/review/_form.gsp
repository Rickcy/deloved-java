<%@ page import="ru.deloved.ReviewValue; ru.deloved.Review" %>
<%@ page import="ru.deloved.Deal" %>


<sec:ifAnyGranted roles="ROLE_ACCOUNT">
	<div class="fieldcontain form-group">
		<label class="col-sm-3 control-label">
			Публикация
		</label>
		<div class="col-sm-9">
			<p class="form-control-static">
				${objInstance.published ? 'Да' : 'Нет'}
			</p>
		</div>
	</div>
</sec:ifAnyGranted>

<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
	<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'published', 'error')}">
		<label class="col-sm-3 control-label">
			Публикация
		</label>
		<div class="col-sm-9">
			<div class="btn-group" data-toggle="buttons">
				<label class="btn btn-default <g:if test="${objInstance?.published == true}">active</g:if>">
					<g:radio name="published" value="true" checked="${objInstance?.published == true}"/>Да
				</label>
				<label class="btn btn-default <g:if test="${objInstance?.published == false}">active</g:if>">
					<g:radio name="published" value="false" checked="${objInstance?.published == false}"/>Нет
				</label>
			</div>
			<g:render template="/_common/error" model="[objInstance: objInstance, field: 'published']"/>
		</div>
	</div>
</sec:ifAnyGranted>

<div class="fieldcontain form-group">
	<label class="col-sm-3 control-label">
		Отзыв о
	</label>
	<div class="col-sm-9">
		<a href="${createLink(controller: 'company', action: 'index', id: objInstance?.to.id)}">
			<p class="form-control-static">
				${objInstance?.to.orgForm.code + ' \"'+objInstance?.to.name + '\"'}
			</p>
		</a>
	</div>
</div>

<div class="fieldcontain form-group">
	<label class="col-sm-3 control-label">
		По сделке
	</label>
	<div class="col-sm-9">
		<a href="${createLink(controller: 'deal', action: 'thread', id: objInstance?.deal.id)}">
			<p class="form-control-static">
				${objInstance?.deal.id}
			</p>
		</a>
	</div>
</div>

<sec:ifAnyGranted roles="ROLE_ACCOUNT">
	<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'value', 'error')} required ">
		<label class="col-sm-3 control-label" for="value">
			Оценка
		</label>
		<div class="col-sm-9">
			<div id="value" class="dv-switch">
				<div class="dv-case">
					<input id="good-review" type="radio" name="value" value="${ReviewValue.Good.value()}" ${objInstance?.value == ReviewValue.Good.value() ? 'checked' : ''}>
					<label for="good-review" class="good-review" title="Положительная оценка">
						<span class="glyphicon glyphicon-plus-sign"></span>
					</label>
				</div>

				<div class="dv-case">
					<input id="neutral-review" type="radio" name="value" value="${ReviewValue.Neutral.value()}" ${objInstance?.value == ReviewValue.Neutral.value() ? 'checked' : ''}>
					<label for="neutral-review" class="neutral-review" title="Нейтральная оценка">
						<span class="glyphicon glyphicon-record"></span>
					</label>
				</div>

				<div class="dv-case">
					<input id="bad-review" type="radio" name="value" value="${ReviewValue.Bad.value()}" ${objInstance?.value == ReviewValue.Bad.value() ? 'checked' : ''}>
					<label for="bad-review" class="bad-review" title="Отрицательная оценка">
						<span class="glyphicon glyphicon-minus-sign"></span>
					</label>
				</div>

			</div>
			<g:render template="/_common/error" model="[objInstance: objInstance, field: 'value']"/>
			<div class="pods">Общее впечатление по сделке</div>
		</div>
	</div>
</sec:ifAnyGranted>

<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
	<div class="fieldcontain form-group">
		<label class="col-sm-3 control-label">
			Оценка
		</label>
		<div class="col-sm-9">
			<g:if test="${objInstance.value == 1}">
				<label class="good-review" title="Положительная оценка"><span class="glyphicon glyphicon-plus-sign"></span></label>
			</g:if>
			<g:if test="${objInstance.value == 0}">
				<label class="neutral-review" title="Нейтральная оценка"><span class="glyphicon glyphicon-record"></span></label>
			</g:if>
			<g:if test="${objInstance.value == -1}">
				<label class="bad-review" title="Отрицательная оценка"><span class="glyphicon glyphicon-minus-sign"></span></label>
			</g:if>
			<div class="pods">Общее впечатление по сделке</div>
		</div>
	</div>
</sec:ifAnyGranted>


<sec:ifAnyGranted roles="ROLE_ACCOUNT">
	<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'content', 'error')} required">
		<label class="col-sm-3 control-label" for="content">
			Содержание
		</label>
		<div class="col-sm-9">
			<g:textArea id="content" style="min-height: 200px; resize: none" class="form-control" name="content" value="${objInstance?.content}"/>
			<g:render template="/_common/error" model="[objInstance: objInstance, field: 'content']"/>
			<div class="pods">Содержание отзыва</div>
		</div>
	</div>
</sec:ifAnyGranted>

<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MANAGER">
	<div class="fieldcontain form-group">
		<label class="col-sm-3 control-label">
			Содержание
		</label>
		<div class="col-sm-9">
			<p class="form-control-static">
				${objInstance.content}
			</p>
			<div class="pods">Содержание отзыва</div>
		</div>
	</div>
</sec:ifAnyGranted>


<sec:ifAnyGranted roles="ROLE_ACCOUNT">
	<g:if test="${objInstance.remark}">
		<div class="fieldcontain form-group">
			<label class="col-sm-3 control-label" style="color: red">
				Нарушения
			</label>
			<div class="col-sm-9">
				<p class="form-control-static">
					${objInstance.remark}
				</p>
				<div class="pods">Отзыв не будет опубликован до тех пор, пока не будут устранены все нарушения.</div>
			</div>
		</div>
	</g:if>
</sec:ifAnyGranted>

<sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_MANAGER">
	<div class="fieldcontain form-group ${hasErrors(bean: objInstance, field: 'content', 'remark')} required">
		<label class="col-sm-3 control-label" for="remark" style="color: red">
			Нарушения
		</label>
		<div class="col-sm-9">
			<g:textArea id="remark" style="min-height: 200px; resize: none" class="form-control" name="remark" value="${objInstance?.remark}"/>
			<g:render template="/_common/error" model="[objInstance: objInstance, field: 'remark']"/>
			<div class="pods">Ваши замечания к содержанию отзыва</div>
		</div>
	</div>
</sec:ifAnyGranted>
