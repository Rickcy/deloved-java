<div name="divSugCat" style="margin-bottom: 5px">
	<input  class="form-control" name="sugCatName" style="width: 30%; display: inline-block" type="text"  value="${cat.name}"/>
	<button class="btn btn-primary" name="updateSugCat" value="${cat.id}" onclick="updateSugCat(event)">Изменить</button>
	<button class="btn btn-danger" name="delSugCat" value="${cat.id}" onclick="delSugCat(event)">X</button> <br>
</div>