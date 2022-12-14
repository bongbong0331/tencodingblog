
let index = {
	init: function() {
		$("#btn--save").bind("click", () => {
			this.save();
			console.log("asdsadadsadsadsadd");
		});
	},
	save: function() {

		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		
		
		// ajax 통신 요청
		$.ajax({
			type:"POST",
			url:"/api/board",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
			dataType: "Json"
		}).done(function(data, textStatus, xhr){
			console.log(data);
			if(data.status == "OK"){
				alert("글쓰기 성공쇼");
				location.href = "/";
			}
		}).fail(function(error){
			alert("등록실패ㅜㅜ" + error.responseJSON.message);
		});
		
		/**
		람다식 -
		fail((error) => {
			console.log(error);
			alert(error.responseJSON.error)
		})
		 */
		
	}

}

index.init();