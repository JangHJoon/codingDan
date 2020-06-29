<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러</title>
</head>
<body>

	<div class="text-center">
		<div class="error mx-auto" data-text="ERROR">ERROR</div>
		<p class="lead text-gray-800 mb-5">에러가 났습니다.</p>
		<p class="text-gray-500 mb-0">${exception.message}</p>
		<a href="/board/listAll">← 메인으로 돌아가기</a>
	</div>

</body>
</html>