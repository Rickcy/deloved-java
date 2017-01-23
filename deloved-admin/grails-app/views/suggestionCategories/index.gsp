<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name='layout' content='admin'/>
	<title></title>
</head>

<body>
<sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_MANAGER,ROLE_SUPPORT">
<div id="suggestionCategories">
	<g:each in="${sugCategories}" var="cat">
		<g:render template="suggestionCategory" model="[cat: cat]"/>
	</g:each>
</div>
<input  id="newSugCatValue" class="form-control" style="width: 30%; display: inline-block" value="" placeholder="Введите имя новой категории"/>
<button id="newSugCatButton" class="btn btn-default" onclick="addSugCat()">Создать</button>


<script>
	function delSugCat(e) {
		var el = e.target || e.srcElement;
		var id = $(el).val()
		$.ajax({
			type: 'POST',
			url: '${createLink([controller: 'suggestionCategories', action: 'deleteSuggestionCategory'])}',
			data: {id: id},
			dataType: 'json',
			success: function (data) {
				if (data.status == 'success') {
					$(el).parent().remove()
					return true;
				}
				return false;
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	};

	function updateSugCat(e) {
		var el = e.target || e.srcElement;
		var id = $(el).val()
		var sugCatName = $(el).parent().children([name = sugCatName]).val()
		$.ajax({
			type: 'POST',
			url: '${createLink([controller: 'suggestionCategories', action: 'updateSuggestionCategory'])}',
			data: {id: id, sugCatName: sugCatName},
			dataType: 'json',
			success: function (data) {
				if (data.sugCategory != null) {
					$('[name=divSugCat]').val(data.sugCategory)
					return true;
				}
				return false;
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	}

	function addSugCat() {
		var n = $('#newSugCatValue').val()
		if (n == null) {
			return false
		}
		$.ajax({
			type: 'POST',
			url: '${createLink([controller: 'suggestionCategories', action: 'saveSuggestionCategory'])}',
			data: {sugCatName: n},
			dataType: 'html',
			success: function (data) {
				if (data != null) {
					$('#suggestionCategories').append(data);
					$('#newSugCatValue').val("");
					return true;
				}
				return false;
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	}
</script>
</sec:ifAnyGranted>
</body>
</html>