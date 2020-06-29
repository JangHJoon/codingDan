<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조회</title>
</head>
<body>

	<form name="readForm" role="form">
		<input type="hidden" name="bno" value="${vo.bno}">
	</form>

	<div class="form-group">
		<label for="title">제목</label> 
		<input type="text" class="form-control" id="title" readonly="readonly" value="${vo.title}">
	</div>
	<div class="form-group">
		<label for="content">내용</label>
		<textarea class="form-control" id="content" rows="3" readonly="readonly">${vo.content}</textarea>
	</div>
	<div class="form-group">
		<label for="writer">글쓴이</label> 
		<input type="text" class="form-control" id="writer" readonly="readonly" value="${vo.writer}">
	</div>

	<button id="btn_modify" type="submit" class="btn btn-warning">수정</button>
	<button id="btn_delete" type="submit" class="btn btn-danger">삭제</button>
	<button id="btn_list" type="submit" class="btn btn-primary">목록</button>
	
	<script>

	$(document).ready(function()
	{
		var form = document.readForm;

		document.getElementById("btn_modify").addEventListener("click", function() {
		      form.action = "/board/modify";
		      form.method = "get";
		      form.submit();
		});

		document.getElementById("btn_delete").addEventListener("click", function() {
		      form.action = "/board/remove";
		      form.method = "post";
		      form.submit();
		});

		document.getElementById("btn_list").addEventListener("click", function() {
		      self.location = "/board/listAll";
		});
	});

	</script>

</body>
</html>