<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form action="">
		<div class="form-group">
			<label for="username">username:</label> <input type="text" class="form-control" placeholder="username" id="username">
		</div>
		<div class="form-group">
			<label for="password">password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<div class="form-group">
			<label for="email">email:</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
		<button type="submit" class="btn btn-primary">signUp</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>