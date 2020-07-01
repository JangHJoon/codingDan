<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
						<form name="searchForm">
							<div class="form-row align-items-center">
								<div class="col-auto my-1">
									<label class="mr-sm-2 sr-only" for="searchType">Preference</label> <select name="searchType" class="custom-select mr-sm-2" id="searchType">
										<option value="">---</option>
										<option value="T" <c:if test="${vo.searchType eq 'T'}">selected="selected"</c:if>>제목</option>
										<option value="C" <c:if test="${vo.searchType eq 'C'}">selected="selected"</c:if>>내용</option>
										<option value="W" <c:if test="${vo.searchType eq 'W'}">selected="selected"</c:if>>작성자</option>
										<option value="TC" <c:if test="${vo.searchType eq 'TC'}">selected="selected"</c:if>>제목+내용</option>
										<option value="CW" <c:if test="${vo.searchType eq 'CW'}">selected="selected"</c:if>>내용+작성자</option>
										<option value="TCW" <c:if test="${vo.searchType eq 'TCW'}">selected="selected"</c:if>>제목+내용+작성자</option>
									</select>
								</div>
								<div class="col-auto my-1">
									<label class="sr-only" for="searchKeyword">keyword</label> 
									<input type="text" name="searchKeyword" class="form-control" id="searchKeyword" placeholder="검색"
										value="${vo.searchKeyword}"
									>
								</div>
								<div class="col-auto my-1">
									<button type="button" onclick="searchList();" class="btn btn-primary">조회</button>
								</div>
							</div>
						</form>
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
											<td><a href="/board/read?${vo.queryString}&bno=${item.bno}">${item.title}</a></td>
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
						<c:param name="link" value="${vo.link}"/>
						<c:param name="queryString" value="${vo.queryString}"/>
						<c:param name="rowPerPage" value="${vo.rowPerPage}"/>
						<c:param name="pagePerView" value="${vo.pagePerView}"/>
						<c:param name="totalCount" value="${vo.totalCount}"/>
						<c:param name="page" value="${vo.page}"/>
					</c:import>

				</div>
			</div>
		</div>
	</div>
	
</body>
</html>