
let index = {
	init: function() {
		$("#btn--save").bind("click", () => {
			this.save();
			console.log("asdsadadsadsadsadd");
		});

		$("#btn--delete").bind("click", () => {
			this.deleteById();
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

	}

}

index.init();