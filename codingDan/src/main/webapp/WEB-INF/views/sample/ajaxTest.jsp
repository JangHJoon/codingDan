<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
	<script src="/static/bootstrap/vendor/jquery/jquery.min.js"></script>
	<style type="text/css">
	
	#modDiv{
	width:300xp;
	height:100px;
	z-index: 10000;
	padding: 10px;
	margin-top: -50px;
	margin-left: -150px;
	top: 50%;
	left: 50%;
	background-color:gray;
	position:absolute;
	}
	
	
	</style>
</head>
<body>

	<h2>Ajax Test Page</h2>

	<form name="replyForm">	
		<div>
			작성자 : <input type="text" name="replyer">
		</div>
		<div>
			댓글 : <input type="text" name="replytext">
		</div>
		
		<button id="replyAddBtn" type="button">추가</button>
	</form>
	
	<ul id="replies">
	</ul>
	<ul id="reliesPaging">
	</ul>

	<div id="modDiv" style="display:none;">
		<form name="modifyForm">
		<div class="modal-title"></div>
		<div>
			<input type="hidden" name="rno">
			<input type="text" name="replytext">
		</div>
		</form>
		<div>
			<button type="button" id="replyModBtn">수정</button>
			<button type="button" id="replyDelBtn">삭제</button>
			<button type="button" id="closeBtn">닫기</button>
		</div>
	</div>
	
	<script>

	var t;
	
	var bno = 100;

	function getReply(page)
	{
		if(page == undefined)
		{
			page = 1;
		}
		
		var ul = document.getElementById("replies");
		ul.textContent = "";
		
		$.getJSON("/board/"+bno+"/replies/" + page, function(d){

			console.log(d);
			
			d.list.forEach(a=>{
				var btn = document.createElement("BUTTON");
				btn.innerText = "수정";
				var li = document.createElement("LI");
				li.setAttribute("data-rno",a.rno);
				li.innerText = a.replytext;
				li.appendChild(btn);
				ul.appendChild(li);
			});	

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
			html += "<li><a href='' onclick='getReply("+ (data.startPage - 1) +"); return false;'> << </a></li>";
		}

		for(var i=data.startPage ; i<=data.endPage ; i++)
		{
			html += "<li class='"+(page==i?"active":"")+"'><a href='' onclick='getReply("+ i +"); return false;'> "+ i +" </a></li>";
		}

		if(data.enableNext)
		{
			html += "<li><a href='' onclick='getReply("+ (data.endPage + 1) +"); return false;'> >> </a></li>";
		}

		console.log(html);

		document.getElementById("reliesPaging").innerHTML = html;
	}

	
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
		var form = document.modifyForm;
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
					$("#modDiv").hide();
					getReply();
				}
			});
	});

	document.getElementById("replyDelBtn").addEventListener("click",function(a){   
		var form = document.modifyForm;
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
					$("#modDiv").hide();
					getReply();
				}
			});
	});

	document.getElementById("closeBtn").addEventListener("click",function(a){   
		$("#modDiv").hide();
	});

	$("#replies").on("click","button",function(a){
			var p = this.parentNode;
			var rno = $(p).data("rno");
			var replytext = p.firstChild.textContent;
		
			$("#modDiv .modal-title").text(rno);
			document.modifyForm.rno.value = rno;
			document.modifyForm.replytext.value = replytext;

			$("#modDiv").show("show");
		});
		

	getReply();

	</script>

</body>
</html>