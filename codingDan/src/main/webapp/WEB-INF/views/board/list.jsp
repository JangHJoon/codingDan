<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록</title>
</head>
<body>

	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
		</div>
		<div class="card-body">
			<div class="table-responsive">
				<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
					<div class="row">
						<div class="col-sm-12 col-md-6">
							<div class="dataTables_length" id="dataTable_length">
								<label>
									Show 
									<select name="dataTable_length" aria-controls="dataTable" class="custom-select custom-select-sm form-control form-control-sm">
										<option value="10">10</option>
										<option value="25">25</option>
										<option value="50">50</option>
										<option value="100">100</option>
									</select> 
									entries
								</label>
							</div>
						</div>
						<div class="col-sm-12 col-md-6">
							<div id="dataTable_filter" class="dataTables_filter">
								<label>Search:
									<input type="search" class="form-control form-control-sm" placeholder="" aria-controls="dataTable">
								</label>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<table class="table table-bordered dataTable" id="dataTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
								<thead>
									<tr role="row">
										<th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Order: activate to sort column ascending" style="width: 90px;">순번</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Title: activate to sort column descending" style="width: 500px;">제목</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Writer: activate to sort column ascending" style="width: 200px;">등록자</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Regist Date: activate to sort column ascending" style="width: 200px;">등록일시</th>
										<th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="View Count: activate to sort column ascending" style="width: 90px;">조회수</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${list}" var="item">
									<tr role="row">
										<td>${item.bno}</td>
										<td><a href="/board/read?bno=${item.bno}">${item.title}</a></td>
										<td>${item.writer}</td>
										<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${item.regdate}" /></td>
										<td><span class="badge badge-info">${item.viewcnt}</span></td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>

					<c:import url="/common/pagination/includePagination">
						<c:param name="link" value="${requestScope['javax.servlet.forward.request_uri']}"/>
						<c:param name="rowPerPage" value="10"/>
						<c:param name="pagePerView" value="5"/>
						<c:param name="totalCount" value="${vo.totalCount}"/>
						<c:param name="page" value="${vo.page}"/>
					</c:import>

				</div>
			</div>
		</div>
	</div>
	
	<script>

	 $(window).load(function
	 {
		 var msg = '${msg}';
	
		 if(msg)
		 {
			 alert(mng);
		 }

	 });

	</script>

</body>
</html>