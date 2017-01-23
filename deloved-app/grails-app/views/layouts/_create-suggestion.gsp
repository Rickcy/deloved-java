

<sec:ifLoggedIn>
<sec:ifNotGranted roles="ROLE_NONE">
	<span>
		<a id="createSuggestion" href="javascript:void(0)">Отзывы или предложения</a>
	</span>
	<script>
	$(document).ready(function(){
		$("#createSuggestion").click(function(){
			$("#suggestionModal").modal("show");
		});
		$("#suggestionModal").on('show.bs.modal', function () {
			fillSugCatList();
		});
	});

	function fillSugCatList(){
		jQuery.ajax({
			 type:'GET',
			 url:'${createLink([controller: 'suggestion', action: 'getSuggestionCategories'])}',
			 dataType: 'json',
			 success:function(data,textStatus) {
			 if (data != 'NO_CONTENT') {
			 $.each(data, function() {
			 $('#sugCategory').append('<option value="' + this.id + '">' + this.name + '</option>')
			 });
			 }
			 },
			 error:function(XMLHttpRequest,textStatus,errorThrown){}
		});
	}

	function beforeSubmitSug() {
		if (document.getElementById('sugTitle').value == "" || document.getElementById('sugContent').value) {
			$('#suggestionForm').validate()
			return false
		}
	}

	function afterSubmitSug () {
		$('#suggestionModal').modal('hide')
		$('#sugCategory :first').attr("selected", "selected");
		document.getElementById('sugTitle').value = ""
		document.getElementById('sugContent').value = ""
	}
</script>

<div id="suggestionModal" class="modal fade">

	<div class="modal-dialog" align="center">

		<div class="modal-content" style="width: 600px">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" style="text-align: left">Мы рады вашим обращениям!</h4>
			</div>


			<div class="modal-body">

				<g:formRemote id="suggestionForm" name="createSuggestion" url="[controller: 'suggestion', action: 'create']" after="afterSubmitSug()">

					<div class="alert alert-info" style="text-align: justify; font-size: 14px">
						Укажите Ваши предложения, замечания или отзывы о работе портала, заполнив поля ниже.
					</div>

					<div class="form-group" align="left">
						<label for="sugCategory" class="label-control"45>Выбирете раздел</label>
						<select id="sugCategory" name="sugCategory" class="form-control" required>
                         <option></option>
						</select>
					</div>


					<div class="form-group" align="left">
						<label for="sugTitle" class="label-control">Тема</label>
						<input required id="sugTitle" class="form-control" type="text" name="sugTitle" required>
					</div>

					<div class="form-group" align="left">
						<label for="sugContent" class="label-control">Сообщение</label>
						<textarea required id="sugContent" required class="form-control" style="resize:vertical; height: 70px; text-align: justify" name="sugContent"></textarea>

					</div>


					<div align="right"><button type="submit" id="submitSuggestion" class="btn btn-default">Отправить</button></div>

				</g:formRemote>



			</div>



		</div>
	</div>
</div>
</sec:ifNotGranted>
</sec:ifLoggedIn>

<sec:ifNotLoggedIn>
	<g:link   controller="login" action="auth"

			data-toggle="modal"
			data-remote="${createLink(controller:'login' , action: 'auth')}"
			data-target="#myModal">Отзывы и предложения</g:link>
</sec:ifNotLoggedIn>