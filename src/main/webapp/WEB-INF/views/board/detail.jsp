<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>







<div class="container">

		<button class="btn bg-secondary" onclick="history.back();">돌아가기</button>
		<button class="btn btn-warning">수정</button>
		<button class="btn btn-danger">삭제</button>
		<br><br><br>

	<div class="">
		<h3>${board.title }</h3>

	</div>

	<div>${board.content }</div>

		<br/><br/><br/>

</div>


















<%@ include file="../layout/footer.jsp"%>