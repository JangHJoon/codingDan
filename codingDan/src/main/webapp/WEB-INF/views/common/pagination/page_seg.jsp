<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<div class="row">
	<div class="col-sm-12 col-md-5">
		<div class="dataTables_info" id="dataTable_info" role="status" aria-live="polite">
			<c:if test="${page.totalCount > 0}">
			Showing ${page.rowStart+1} to ${page.totalCount < page.rowEnd ? page.totalCount : page.rowEnd} of ${page.totalCount} entries
			</c:if>
			<c:if test="${page.totalCount eq 0}">
			Showing 0 entry
			</c:if>
		</div>
	</div>
	<div class="col-sm-12 col-md-7">
		<div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
			<ul class="pagination">
				<li class="paginate_button page-item previous <c:if test="${not page.enablePrev}">disabled</c:if>" id="dataTable_previous">
					<a 
						<c:if test="${not page.enablePrev}">
							href="#"
						</c:if>
						<c:if test="${page.enablePrev}">
							href="${page.link}?${page.queryStringExPage}${page.startPage - 1}"
						</c:if>
						aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link"
					>Previous</a>
				</li>
				<c:forEach begin="${page.startPage}" end="${page.endPage}" var="num">
				<li class="paginate_button page-item <c:if test="${num eq page.page}">active</c:if>">
					<a href="${page.link}?${page.queryStringExPage}${num}" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">${num}</a>
				</li>
				</c:forEach>
				<li class="paginate_button page-item next <c:if test="${not page.enableNext}">disabled</c:if>" id="dataTable_next">
					<a 
						<c:if test="${not page.enableNext}">
							href="#"
						</c:if>
						<c:if test="${page.enableNext}">
							href="${page.link}?${page.queryStringExPage}${page.endPage + 1}"
						</c:if>
						
						aria-controls="dataTable" data-dt-idx="7" tabindex="0" class="page-link"
					>Next</a>
				</li>
			</ul>
		</div>
	</div>
</div>