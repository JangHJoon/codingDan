<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
	<script src="/static/bootstrap/vendor/jquery/jquery.min.js"></script>
</head>
<body>

	<h3>Ajax File Upload</h3>
	
	<div class="fileDrop" style="width:100%;height:200px;border:1px dotted blue"></div>
	
	<div class="uploadedList"></div>
	
	<script>

	var t;

	$(".uploadedList").on("click","small",function(event){

		var small = this;
		var fileName = $(small).data("src");
		
		$.ajax({
			url : "/sample/deleteFile",
			data : {fileName : fileName},
			dataType : "text",
			type : "POST",
			success : function(data){
				console.log(data);
				small.parentElement.remove();
			}
		});

		});

	$(".fileDrop").on("dragenter dragover", function(event){
		event.preventDefault();
	});

	$(".fileDrop").on("drop", function(event){
		event.preventDefault();

		var files = event.originalEvent.dataTransfer.files;
		var file = files[0];

		//console.log(file);

		var formData = new FormData();
		formData.append("file", file);

		$.ajax({
				url : "/sample/uploadAjax",
				data : formData,
				dataType : "json",
				processData : false,
				contentType : false,
				type : "POST",
				success : function(data){
					console.log(data);

					var html = "";
					if(data.isImage)
					{
						html=[
							"<div>"
							,"<a href=\"/sample/downFile?fileName=" + data.fileName + "\">"
							,"<img src=\"/sample/displayFile?fileName=" + data.fileName + "\"/>"
							,"</a>"
							,"<small data-src=\"" + data.fileName + "\">X</small>"
							,"</div>"
							].join("");
					}
					else
					{
						html = [
								"<div>"
								,"<a href=\"/sample/downFile?fileName=" + data.fileName + "\">" 
								,	data.fileName
								,"</a>"
								,"<small data-src=\"" + data.fileName + "\">X</small>"
								,"</div>"
								].join("");
					}

					$(".uploadedList").append(html);
				}
			});
		
	});

	
	</script>
	
	
	
</body>
</html>