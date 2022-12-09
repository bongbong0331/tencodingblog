<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- jQuery library -->
<!-- <script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script> -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<!-- Popper JS -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<h1>form 테스트</h1>

	<div class="container">
		<form action="/blog/dummy/signup2" method="post">
			<div class="form-group">
				<label for="username">username:</label> 
				<input type="text"
					class="form-control" placeholder="Enter username" id="username" name="username" value="고길동1">
			</div>
			<div class="form-group">
				<label for="password">password:</label> 
				<input type="password"
					class="form-control" placeholder="Enter password" id="password" name="password" value="asd1234">
			</div>
			<div class="form-group">
				<label for="email">email:</label> 
				<input type="email"
					class="form-control" placeholder="Enter email" id="email" name="email" value="aa@naver.com">
			</div>
			
			<!-- 	<button id="" type="submit" class="btn btn-primary">회원가입</button> -->

		</form>
		<button id="join--submit" class="btn btn-primary">회원가입</button>
	</div>


	<script src="/blog/js/join.js"></script>
</body>
</html>