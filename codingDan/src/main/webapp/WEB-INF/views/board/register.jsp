<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 등록</title>
</head>
<body>

	<form name="registerForm" action="/board/register" method="post">
	  <div class="form-group">
	    <label for="title">제목</label>
	    <input type="text" class="form-control" name="title">
	  </div>
	  <div class="form-group">
	    <label for="content">내용</label>
	    <textarea class="form-control" name="content" rows="3"></textarea>
	  </div>
	  <div class="form-group">
	    <label for="writer">글쓴이</label>
	    <input type="text" class="form-control" name="writer">
	  </div>
	  <button type="submit" class="btn btn-primary">Submit</button>
	</form>

</body>
</html>