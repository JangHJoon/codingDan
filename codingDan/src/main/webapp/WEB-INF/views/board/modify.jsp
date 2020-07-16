<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조회</title>
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

	<form name="modifyForm" role="form">
		<input type="hidden" name="queryString" value="${vo.queryString}" />
		<input type="hidden" name="bno" value="${detail.bno}">
		<div class="form-group">
			<label for="title">제목</label> 
			<input type="text" name="title" class="form-control" id="title" value="${detail.title}">
		</div>
		<div class="form-group">
			<label for="content">내용</label>
			<textarea class="form-control" name="content" id="content" rows="3">${detail.content}</textarea>
		</div>
		<div class="form-group">
			<label for="writer">글쓴이</label> 
			<input type="text" class="form-control" name="writer" id="writer" value="${detail.writer}">
		</div>
		
		<div class="form-group">
			<label for="">파일을 여기로 드래그하세요</label> 
			<div class="fileDrop"></div>
		</div>
		<hr>
		<div class="row uploadedList">
		</div>
	</form>

	<button id="btn_save" type="submit" class="btn btn-primary">저장</button>
	<button id="btn_cancel" type="submit" class="btn btn-warning">취소</button>
	
	<script id="uploadTemplate" type="text/x-handlebats-template">
	<div class="col-xl-3 col-md-6 mb-5">
		<div class="card" style="width:10rem;">
			<img class="card-img-top" 
				{{#if isImage}}
				src="/board/displayFile?fileName={{fileName}}"
				{{else}}
				src="/static/image/fileicon.png"
				{{/if}}
			alt="Attachment">
			<div class="card-body">
				<a title="{{fileName}}" data-is-image="{{isImage}}" href="/board/downFile?fileName={{fileName}}" class="mailbox-attachment-name">
					{{cutFilename fileName}}
				</a>
				<a href="#" class="btn btn-danger">x</a>
			</div>
		</div>
	</div>	
	</script>
	<script>

	var t;
	
	var bno = '${vo.bno}';

	var uploadTemplate = Handlebars.compile($("#uploadTemplate").html());

	Handlebars.registerHelper("cutFilename", function(fileName) {
		return "..." + fileName.substring(fileName.length-15);
	});
	
	$(document).ready(function()
	{
		var form = document.modifyForm;

		document.getElementById("btn_save").addEventListener("click", function() {

			$(".uploadedList a[title]").each(function(){ 
				var input = document.createElement("INPUT");
				input.type = "hidden";
				input.name = "files";
				input.value = $(this).attr("title");
				form.append(input);
			});

		      form.action = "/board/modify";
		      form.method = "post";
		      form.submit();
		});

		document.getElementById("btn_cancel").addEventListener("click", function() {
			 self.location = "/board/listPage?${vo.queryString}";
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

		$(".uploadedList").on("click",".btn-danger", function(event) {
			event.preventDefault();
			this.parentNode.parentNode.parentNode.remove();
		});

		getAttach();

	});

	var getAttach = function() {

		$.getJSON("/board/"+bno+"/attach", function(list) {

			console.log(list);

			list.forEach(function(data){
				
				var html = uploadTemplate(data);

				$(".uploadedList").append(html);
			})


		});
	};


	</script>

</body>
</html>