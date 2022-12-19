<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>




<c:forEach var="board" items="${boards.content}">

	<div class="card m-2">
		<div class="card-body">
			<h4>${board.title}</h4>
			<a href="/board/${board.id }" class="btn btn-primary">상세보기</a>
		</div>
	</div>
</c:forEach>

<!--
page.first == true, false  < -- 첫번째 페이지 true
page.last == true, false  < -- 마지막 페이지 last
-->

<ul class="pagination justify-content-center">
	<c:set var="isDisabled" value="disabled"></c:set>
	<c:set var="isNotDisabled" value=""></c:set>
	<li class="page-item ${boards.first ? isDisabled : isNotDisabled } "><a class="page-link" href="?page=${boards.number - 1 }">Previous</a></li>
		<!-- 반복문 처리 하기-->
		<!-- 1 2 3, 1 2 3 4 , 1 2 3 4 5  -->
	<c:forEach var="num" items="${pageNumbers }">
		<c:choose>
			<c:when test="${nowPage eq num }">
			<!-- 0 부터 시작 컨트롤러에서 +1 -->
				<li class="page-item active"><a class="page-link" href="?page=${num - 1 }">${num }</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${num - 1 }">${num }</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>	

	<li class="page-item ${boards.last ? isDisabled : isNotDisabled }"><a class="page-link" href="?page=${boards.number + 1 }">Next</a></li>

</ul>

<%@ include file="layout/footer.jsp"%>









