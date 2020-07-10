<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>

	<form name="uploadForm" target="zeroFrame" action="/sample/uploadForm" method="post" enctype="multipart/form-data">
		<input type="file" name="file"> <input type="submit">
	</form>
	
	<iframe name="zeroFrame" style="display:none;"></iframe>

	<script>
		function addFilePath(msg)
		{
			alert(msg);
			document.uploadForm.reset();
		}
	</script>
</body>
</html>