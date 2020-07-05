<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조회</title>
</head>
<body>

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
	
		var bno = ${vo.bno};
	
		$(document).ready(
						function() {
							var form = document.readForm;

							document.getElementById("btn_modify")
									.addEventListener("click", function() {
										form.action = "/board/modify";
										form.method = "get";
										form.submit();
									});

							document.getElementById("btn_delete")
									.addEventListener("click", function() {
										form.action = "/board/remove";
										form.method = "post";
										form.submit();
									});
							document
									.getElementById("btn_list")
									.addEventListener(
											"click",
											function() {
												self.location = "/board/listPage?${vo.queryString}";
											});
							
							document.getElementById("replyAddBtn").addEventListener("click",function(a){   

								var form = document.replyForm;

								var replyer = form.replyer.value;
								var replytext = form.replytext.value;

								var param = {};
								param.bno = bno;
								param.replyer = replyer;
								param.replytext = replytext;
								
								$.ajax(
									{
										type : 'post',
										url : '/board/' + bno + '/replies',
										headers : {
											"Content-Type" : "application/json", "X-HTTP-Method-Override" : "POST"
										},
										dataType : 'text',
										data : JSON.stringify(param),
										success : function(result){
											alert(result);
											getReply();
										}
									});
							});

							document.getElementById("replyModBtn").addEventListener("click",function(a){   
								var form = document.replyModForm;
								var replytext = form.replytext.value;
								var rno = form.rno.value;

								var param = {};
								param.replytext = replytext;
								
								$.ajax(
									{
										type : 'put',
										url : '/board/' + bno + '/replies/' + rno,
										headers : {
											"Content-Type" : "application/json", "X-HTTP-Method-Override" : "PUT"
										},
										dataType : 'text',
										data : JSON.stringify(param),
										success : function(result){
											alert(result);
											$("#exampleModal").modal("hide");
											getReply();
										}
									});
							});

							document.getElementById("replyDelBtn").addEventListener("click",function(a){   
								var form = document.replyModForm;
								var rno = form.rno.value;

								$.ajax(
									{
										type : 'delete',
										url : '/board/' + bno + '/replies/' + rno,
										headers : {
											"Content-Type" : "application/json", "X-HTTP-Method-Override" : "DELETE"
										},
										dataType : 'text',
										success : function(result){
											alert(result);
											$("#exampleModal").modal("hide");
											getReply();
										}
									});
							});


							Handlebars.registerHelper("prettifyDate", function(timeValue)
									{
										var dateObj = new Date(timeValue);
										var year = dateObj.getFullYear();
										var month = dateObj.getMonth() + 1;
										var date = dateObj.getDate();
										return year + "/" + month + "/" + date;
									});

							$("#replies").on("click","button",function(a){
								var p = this.parentNode.previousElementSibling;
								var rno = $(p).data("rno");
								var replytext = p.innerText;
							
								document.replyModForm.rno.value = rno;
								document.replyModForm.replytext.value = replytext;

								$("#exampleModal").modal("show");
							});


							getReply();
							
						});

		var printReply = function(replyArr)
		{
			t = $("#replyTemplate");
			
			var template = Handlebars.compile($("#replyTemplate").html());

			var html = template(replyArr);

			$("#replies").html(html);
		}

		var getReply = function(page)
		{
			(new Event(window.event)).preventDefault();
			
			if(page == undefined)
			{
				page = 1;
			}
			
			var lDiv = document.getElementById("replies");
			lDiv.textContent = "";
			
			$.getJSON("/board/"+bno+"/replies/" + page, function(d){

				console.log(d);
				
				printReply(d.list);

				printPaging(d.vo);
			});
		}

		function printPaging(data)
		{
			var page = data.page;
			var pagePerView = data.pagePerView;
			var rowPerPage = data.rowPerPage;
			var link = data.link;

			var html = "";
			
			if(data.enablePrev)
			{
				html += '<li class="page-item">';
				html += '<a class="page-link" href="#." aria-label="Previous">';
				html += '<span aria-hidden="true">&laquo;</span>';
				html += '<span class="sr-only">Previous</span>';
				html += '</a>';
				html += '</li>';
				//html += "<li><a href='' onclick='getReply("+ (data.startPage - 1) +"); return false;'> << </a></li>";
			}

			for(var i=data.startPage ; i<=data.endPage ; i++)
			{
				html += "<li class='page-item"+(page==i?" disabled":"")+"'><a class='page-link' href='#.' onclick='" + (page==i ? "" : "getReply("+ i +");") + "'> "+ i +" </a></li>";
			}

			if(data.enableNext)
			{
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