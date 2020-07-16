<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 등록</title>
<style type="text/css">
.fileDrop{
	width:80%;
	height:100px;
	border:1px dotted gray;
	background-color:lightslategrey;
	margin:auto;
}
</style>
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
		<div class="form-group">
			<label for="">파일을 여기로 드래그하세요</label>
			<div class="fileDrop"></div>
		</div>
		<hr>
		<div class="row uploadedList"></div>
		
		<button type="submit" class="btn btn-primary">등록</button>
	</form>
	
	<script id="uploadTemplate" type="text/x-handlebats-template">
	<div class="col-xl-3 col-md-6 mb-5">
		<div class="card" style="width:10rem;">
			<img class="card-img-top" 
				{{#if isImage}}
				src="/board/displayFile?fileName={{fileName}}"
				{{else}}
				src="/static/image/fileicon.png"
				{{/if}}
			alt="Card image cap">
			<div class="card-body">
				<a title="{{fileName}}" href="/board/downFile?fileName={{fileName}}" class="mailbox-attachment-name">
					{{cutFilename fileName}}
				</a>
				<a href="#" data-src="{{fileName}}" class="btn btn-primary">x</a>
			</div>
		</div>
	</div>	
	</script>
	
	<script>

		$(function() {

			document.registerForm.addEventListener("submit",function(event){ 

				var frm = this;
				
				event.preventDefault(); 
				
				$(".uploadedList a[data-src]").each(function(){ 
					var input = document.createElement("INPUT");
					input.type = "hidden";
					input.name = "files";
					input.value = $(this).data("src");
					frm.append(input);
				});

				frm.submit();
				
			});
			
			
			var uploadTemplate = Handlebars.compile($("#uploadTemplate").html());

			Handlebars.registerHelper("cutFilename", function(fileName) {
				return "..." + fileName.substring(fileName.length-15);
			});

			$(".fileDrop").on("dragenter dragover", function(event){
				event.preventDefault();
			});

			$(".fileDrop").on("drop", function(event){
				event.preventDefault();

				var files = event.originalEvent.dataTransfer.files;
				var file = files[0];

				console.log(file);

				var formData = new FormData();
				formData.append("file", file);

				$.ajax({
						url : "/board/uploadAjax",
						data : formData,
						dataType : "json",
						processData : false,
						contentType : false,
						type : "POST",
						success : function(data){

							console.log(data);

							var html = uploadTemplate(data);

							$(".uploadedList").append(html);
						}
					});
				
			});

			/*
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
					
			*/

		}); // ready end

	</script>
	
</body>
</html>