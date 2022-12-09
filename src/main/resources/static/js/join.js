
$('#join--submit').on('click', function(){
	let data = {
		username : $('#username').val(),
		password : $('#password').val(),
		email : $('#email').val(),
	};
	
	console.log("data : " + data.username);
	console.log("data : " + data.password);
	console.log("data : " + data.email);
	
	// js 도 http 통신 가능, 동기 방식, 비동기 방식 둘 다 지원
	// 대부분 비동기 통신을 많이 사용한다!!!
	$.ajax({
		type: 'POST',
		url: '/blog/dummy/signup',
		data: JSON.stringify(data),
		contentType: 'application/json; charset=utf-8',
		dataType: 'json'
	}).done(function(response){
		console.log(response);
//		alert("회원가입 성공");
	}).fail(function(error){
		console.log(error);
	});
	
	
	
});

