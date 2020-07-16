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

/* Style the Image Used to Trigger the Modal */
#myImg {
  border-radius: 5px;
  cursor: pointer;
  transition: 0.3s;
}

#myImg:hover {opacity: 0.7;}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 100px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.9); /* Black w/ opacity */
}

/* Modal Content (Image) */
.modal-content {
  margin: auto;
  display: block;
  width: 80%;
  max-width: 700px;
}

/* Caption of Modal Image (Image Text) - Same Width as the Image */
#caption {
  margin: auto;
  display: block;
  width: 80%;
  max-width: 700px;
  text-align: center;
  color: #ccc;
  padding: 10px 0;
  height: 150px;
}

/* Add Animation - Zoom in the Modal */
.modal-content, #caption {
  animation-name: zoom;
  animation-duration: 0.6s;
}

@keyframes zoom {
  from {transform:scale(0)}
  to {transform:scale(1)}
}

/* The Close Button */
.close {
  position: absolute;
  top: 15px;
  right: 35px;
  color: #f1f1f1;
  font-size: 40px;
  font-weight: bold;
  transition: 0.3s;
}

.close:hover,
.close:focus {
  color: #bbb;
  text-decoration: none;
  cursor: pointer;
}

/* 100% Image Width on Smaller Screens */
@media only screen and (max-width: 700px){
  .modal-content {
    width: 100%;
  }
}
</style>
</head>
<body>

	<!-- The Modal -->
	<div id="myModal" class="modal">
	  <!-- The Close Button -->
	  <span id="closeModel" class="close">&times;</span>
	  <!-- Modal Content (The Image) -->
	  <img class="modal-content" id="img01">
	  <!-- Modal Caption (Image Text) -->
	  <div id="caption"></div>
	</div>
	

	<form name="readForm" role="form">
		<input type="hidden" name="queryString" value="${vo.queryString}">
		<input type="hidden" name="bno" value="${detail.bno}">
	</form>

	<div class="form-group">
		<label for="title">제목</label> 
		<input type="text" class="form-control" id="title" readonly="readonly" value="${detail.title}">
	</div>
	<div class="form-group">
		<label for="content">내용</label>
		<textarea class="form-control" id="content" rows="3" readonly="readonly">${detail.content}</textarea>
	</div>
	<div class="form-group">
		<label for="writer">글쓴이</label> 
		<input type="text" class="form-control" id="writer" readonly="readonly" value="${detail.writer}">
	</div>
	<div class="form-group">
		<label for="">파일을 여기로 드래그하세요</label> 
		<div class="fileDrop"></div>
	</div>

	<hr>

	<div class="row uploadedList">
	</div>

	<button id="btn_modify" type="submit" class="btn btn-warning">수정</button>
	<button id="btn_delete" type="submit" class="btn btn-danger">삭제</button>
	<button id="btn_list" type="submit" class="btn btn-primary">목록</button>

	<div class="coment-bottom bg-white p-2 px-4">
		<form name="replyForm">
			<div class="form-row">
				<div class="form-group col-md-1">
				<input name="replyer" type="text" class="form-control mr-3" placeholder="작성자">
				</div>
				<div class="form-group col-md-8">
					<input name="replytext" type="text" class="form-control mr-3" placeholder="댓글">
				</div>
				<button id="replyAddBtn" class="btn btn-primary" type="button">추가</button>
			</div>
		</form>
		<div id="replies" class="commented-section mt-2">
			<div class="d-flex flex-row align-items-center commented-user">
				<h5 class="mr-2">작성자</h5>
				<span class="dot mb-1"></span><span class="mb-1 ml-2">시간</span>
			</div>
			<div class="comment-text-sm">
				<span>댓글</span>
			</div>
			<div class="action">			
				<button type="button" class="btn btn-primary">수정</button>
			</div>
		</div>
	</div>
	<div>
		<nav aria-label="Page navigation example">
			<ul class="pagination" id="repliesPaging">
			</ul>
		</nav>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">	
				<div class="modal-body">
					<form name="replyModForm">
						<div class="form-row">
							<div class="form-group col-md-8">
								<input name="rno" type="hidden">
								<input name="replytext" type="text" class="form-control mr-3" placeholder="댓글">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button id="replyModBtn" type="button" class="btn btn-primary">수정</button>
					<button id="replyDelBtn" type="button" class="btn btn-danger">삭제</button>
					<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>

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
			</div>
		</div>
	</div>	
	</script>
	
	<script id="replyTemplate" type="text/x-handlebats-template">
		{{#each .}}
			<div class="d-flex flex-row align-items-center commented-user">
				<h5 class="mr-2">{{replyer}}</h5>
				<span class="dot mb-1"></span><span class="mb-1 ml-2">{{prettifyDate regdate}}</span>
			</div>
			<div data-rno="{{rno}}" class="comment-text-sm">
				<span>{{replytext}}</span>
			</div>
			<div class="action">			
				<button type="button" class="btn btn-primary">수정</button>
			</div>
		{{/each}}
	</script>
	<script>

		var t;

		var bno = '${vo.bno}';

		var uploadTemplate = Handlebars.compile($("#uploadTemplate").html());
		var template = Handlebars.compile($("#replyTemplate").html());
		
		Handlebars.registerHelper("prettifyDate", function(timeValue) {
			var dateObj = new Date(timeValue);
			var year = dateObj.getFullYear();
			var month = dateObj.getMonth() + 1;
			var date = dateObj.getDate();
			return year + "/" + month + "/" + date;
		});

		Handlebars.registerHelper("cutFilename", function(fileName) {
			return "..." + fileName.substring(fileName.length-15);
		});

		$(function() {

			var form = document.readForm;

			document.getElementById("btn_modify").addEventListener("click",
					function() {
						form.action = "/board/modify";
						form.method = "get";
						form.submit();
					});

			document.getElementById("btn_delete").addEventListener("click",
					function() {
						form.action = "/board/remove";
						form.method = "post";
						form.submit();
					});
			document.getElementById("btn_list").addEventListener("click",
					function() {
						self.location = "/board/listPage?${vo.queryString}";
					});

			
			// 댓글 관련
			document.getElementById("replyAddBtn").addEventListener("click",
					function(a) {

						var form = document.replyForm;

						var replyer = form.replyer.value;
						var replytext = form.replytext.value;

						var param = {};
						param.bno = bno;
						param.replyer = replyer;
						param.replytext = replytext;

						$.ajax({
							type : 'post',
							url : '/board/' + bno + '/replies',
							headers : {
								"Content-Type" : "application/json",
								"X-HTTP-Method-Override" : "POST"
							},
							dataType : 'text',
							data : JSON.stringify(param),
							success : function(result) {
								alert(result);
								getReply();
							}
						});
					});

			document.getElementById("replyModBtn").addEventListener("click",
					function(a) {
						var form = document.replyModForm;
						var replytext = form.replytext.value;
						var rno = form.rno.value;

						var param = {};
						param.replytext = replytext;

						$.ajax({
							type : 'put',
							url : '/board/' + bno + '/replies/' + rno,
							headers : {
								"Content-Type" : "application/json",
								"X-HTTP-Method-Override" : "PUT"
							},
							dataType : 'text',
							data : JSON.stringify(param),
							success : function(result) {
								alert(result);
								$("#exampleModal").modal("hide");
								getReply();
							}
						});
					});

			document.getElementById("replyDelBtn").addEventListener("click",
					function(a) {
						var form = document.replyModForm;
						var rno = form.rno.value;

						$.ajax({
							type : 'delete',
							url : '/board/' + bno + '/replies/' + rno,
							headers : {
								"Content-Type" : "application/json",
								"X-HTTP-Method-Override" : "DELETE"
							},
							dataType : 'text',
							success : function(result) {
								alert(result);
								$("#exampleModal").modal("hide");
								getReply();
							}
						});
					});

			$("#replies").on("click", "button", function(a) {
				var p = this.parentNode.previousElementSibling;
				var rno = $(p).data("rno");
				var replytext = p.innerText;

				document.replyModForm.rno.value = rno;
				document.replyModForm.replytext.value = replytext;

				$("#exampleModal").modal("show");
			});

			getReply();

			// 파일 업로드 관련

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

			getAttach();

			// 이미지 팝업 관련

			$(".uploadedList").on("click", "a", function(event){
				if($(this).data("isImage"))
				{
					event.preventDefault();
					var src = this.attributes.href.value;

					var modal = document.getElementById("myModal");
					var modalImg = document.getElementById("img01");
					var captionText = document.getElementById("caption");

				 	modal.style.display = "block";
				  	modalImg.src = src;
				  	//captionText.innerHTML = "";
				}
			
				
			});
			
			$("#closeModel").on("click",function(){
				var modal = document.getElementById("myModal");
				modal.style.display = "none";
			});

			
		}); // ready end

		var printReply = function(replyArr) {
			t = $("#replyTemplate");

			var html = template(replyArr);

			$("#replies").html(html);
		}

		var getReply = function(page) {
			(new Event(window.event)).preventDefault();

			if (page == undefined) {
				page = 1;
			}

			var lDiv = document.getElementById("replies");
			lDiv.textContent = "";

			$.getJSON("/board/" + bno + "/replies/" + page, function(d) {

				console.log(d);

				printReply(d.list);

				printPaging(d.vo);
			});
		}

		var getAttach = function() {

			$.getJSON("/board/"+bno+"/attach", function(list) {

				console.log(list);

				list.forEach(function(data){
					
					var html = uploadTemplate(data);

					$(".uploadedList").append(html);
				})


			});
		};

		function printPaging(data) {
			var page = data.page;
			var pagePerView = data.pagePerView;
			var rowPerPage = data.rowPerPage;
			var link = data.link;

			var html = "";

			if (data.enablePrev) {
				html += '<li class="page-item">';
				html += '<a class="page-link" href="#." aria-label="Previous">';
				html += '<span aria-hidden="true">&laquo;</span>';
				html += '<span class="sr-only">Previous</span>';
				html += '</a>';
				html += '</li>';
				//html += "<li><a href='' onclick='getReply("+ (data.startPage - 1) +"); return false;'> << </a></li>";
			}

			for (var i = data.startPage; i <= data.endPage; i++) {
				html += "<li class='page-item" + (page == i ? " disabled" : "")
						+ "'><a class='page-link' href='#.' onclick='"
						+ (page == i ? "" : "getReply(" + i + ");") + "'> " + i
						+ " </a></li>";
			}

			if (data.enableNext) {
				html += '<li class="page-item">';
				html += '<a class="page-link" href="#." aria-label="Next">';
				html += '<span aria-hidden="true">&raquo;</span>';
				html += '<span class="sr-only">Next</span>';
				html += '</a>';
				html += '</li>';
				//html += "<li><a href='' onclick='getReply("+ (data.endPage + 1) +"); return false;'> >> </a></li>";
			}

			console.log(html);

			document.getElementById("repliesPaging").innerHTML = html;
		}
	</script>

</body>
</html>