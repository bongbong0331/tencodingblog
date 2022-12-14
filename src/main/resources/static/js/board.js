
let index = {
	init: function() {
		$("#btn--save").bind("click", () => {
			this.save();
		});

		$("#btn--delete").bind("click", () => {
			this.deleteById();
		});
		
		$("#btn--update").bind("click", () => {
			this.update();
		});
		
		
	},



	save: function() {

		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};


		// ajax 통신 요청
		$.ajax({
			type: "POST",
			url: "/api/board",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
			dataType: "Json"
		}).done(function(data, textStatus, xhr) {
			console.log(data);
			if (data.status == "OK") {
				alert("글쓰기 성공쇼");
				location.href = "/";
			}
		}).fail(function(error) {
			alert("등록실패ㅜㅜ" + error.responseJSON.message);
		});

		/**
		람다식 -
		fail((error) => {
			console.log(error);
			alert(error.responseJSON.error)
		})
		 */

	},
	
	
	deleteById: function() {

		let id = $("#board-id").text();      // text val 차이 

		// 통신 ---> ajax
		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id

		}).done(function(data, textStatus, xhr) {
			if (data.status == "OK") {
				alert("글 삭제가 완료 되었습니다");
				location.href = "/";
			}
		}).fail(function() {
			alert("글 삭제 실패하였습니다");
		});

	},
	
	update: function(){
		
		// html 태그에 직접 속성을 정의할 수 있다. 규칙은 data-*
		// data-* 값을 가지고 오기 위해서 Jquery --> (선택자).arrt("data-[id]")
		let boardId = $("#board-id").attr("data-id");
		
		let data = {
			title : $("#title").val(),
			content : $("#content").val()
		} 
		
		$.ajax({
			type: "PUT",
			url: "/api/board/" + boardId,
			data : JSON.stringify(data),
			contentType : "application/json; charset=utf-8",
			dataType : "json"
		}).done(function(data, textStatus, xhr){
			if(data.status){
				alert("글 수정이 완료 되었습니다");
				location.href = "/";
			}
		}).fail(function(error){
			console.log(error);
			alert("글 수정이 실패 하였습니다");
		});
	}


}

index.init();