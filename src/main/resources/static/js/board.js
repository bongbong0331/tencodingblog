
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

		$("#btn-reply-save").bind("click", () => {
			this.replySave();
		});

	},



	save: function() {
		let xCheckTitle = XSSCheck($("#title").val());
		let token = $("meta[name='_csrf']").attr("content");
		let csrfHeader = $("meta[name='_csrf_header']").attr("content");

		let data = {
			title: xCheckTitle,
			content: $("#content").val()
		};


		// ajax 통신 요청

		$.ajax({
			beforeSend: function(xhr){
				xhr.setRequestHeader(csrfHeader, token);
			},
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
		
		let token = $("meta[name='_csrf']").attr("content");
		let csrfHeader = $("meta[name='_csrf_header']").attr("content");

		let id = $("#board-id").val();      // text val 차이 

		// 통신 ---> ajax
		$.ajax({
			beforeSend:function(xhr){
				xhr.setRequestHeader(csrfHeader, token);
			},
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

	update: function() {
		
		let token= $("meta[name='_csrf']").attr("content");
		let csrfHeader= $("meta[name='_csrf_header']").attr("content");

		// html 태그에 직접 속성을 정의할 수 있다. 규칙은 data-*
		// data-* 값을 가지고 오기 위해서 Jquery --> (선택자).arrt("data-[id]")
		let boardId = $("#board-id").attr("data-id");
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}

		$.ajax({
			beforeSend: function(xhr){
				xhr.setRequestHeader(csrfHeader, token);
			},
			type: "PUT",
			url: "/api/board/" + boardId,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(data, textStatus, xhr) {
			if (data.status) {
				alert("글 수정이 완료 되었습니다");
				location.href = "/";
			}
		}).fail(function(error) {
			console.log(error);
			alert("글 수정이 실패 하였습니다");
		});
	},


	replySave: function() {
		let token= $("meta[name='_csrf']").attr("content");
		let csrfHeader= $("meta[name='_csrf_header']").attr("content");

		let replyData = {
			boardId: $("#board-id").val(),    // fk (board pk)
			content: $("#content").val()
		};


		// ajax 통신 요청
		$.ajax({
			beforeSend: function(xhr){
				xhr.setRequestHeader(csrfHeader, token);
			},
			type: "POST",
			// 쌤 꿀팁 *****************
			url: `/api/board/${replyData.boardId}/reply`,
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(replyData),
			dataType: "Json"
		}).done(function(data, textStatus, xhr) {
			console.log(data);
			if (data.status == "OK") {
				alert("댓글 작성이 완료 되었습니다");
				location.href = `/board/${replyData.boardId}`;
			}
		}).fail(function(error) {
			console.log(error);
			alert("댓글작성에 실패하여어어었습니다");
		});

	},
	
	
	
	
	replyDelete: function(boardId, replyId) {
		let token= $("meta[name='_csrf']").attr("content");
		let csrfHeader= $("meta[name='_csrf_header']").attr("content");
			
			
		$.ajax({
			beforeSend: function(xhr){
				xhr.setRequestHeader(csrfHeader, token);
			},
			type: 'DELETE',
			url: `/api/board/${boardId}/reply/${replyId}`,
			dataType: 'json'
		}).done(function(resData) {
			if (resData.status == "OK") {
				alert(" 댓글 삭제 성공 하였습니다.");
				location.href = `/board/${boardId}`
			}

		}).fail(function(error) {
			alert("댓글 삭제 실퍃햏ㅎ")
		});
	}
}



function XSSCheck(str, level) {
	if (level == undefined || level == 0) {
		str = str.replace(/\<|\>|\"|\'|\%|\;|\(|\)|\&|\+|\-/g, "홍길동맨");
	} else if (level != undefined && level == 1) {
		str = str.replace(/\</g, "&lt;");
		str = str.replace(/\>/g, "&gt;");
	}
	return str;
}






index.init();

