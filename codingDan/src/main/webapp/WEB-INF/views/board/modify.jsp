<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조회</title>
</head>
<body>

	<form name="modifyForm" role="form">
		<input type="hidden" name="bno" value="${vo.bno}">
		<div class="form-group">
			<label for="title">제목</label> 
			<input type="text" name="title" class="form-control" id="title" value="${vo.title}">
		</div>
		<div class="form-group">
			<label for="content">내용</label>
			<textarea class="form-control" name="content" id="content" rows="3">${vo.content}</textarea>
		</div>
		<div class="form-group">
			<label for="writer">글쓴이</label> 
			<input type="text" class="form-control" name="writer" id="writer" value="${vo.writer}">
		</div>
	</form>

	<button id="btn_save" type="submit" class="btn btn-primary">저장</button>
	<button id="btn_cancel" type="submit" class="btn btn-warning">취소</button>
	
	<script>

	$(document).ready(function()
	{
		var form = document.modifyForm;

		document.getElementById("btn_save").addEventListener("click", function() {
		      form.action = "/board/modify";
		      form.method = "post";
		      form.submit();
		});

		document.getElementById("btn_cancel").addEventListener("click", function() {
			 self.location = "/board/listAll";
		});

	});

	</script>

</body>
</html>