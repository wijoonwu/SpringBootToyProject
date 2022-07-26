// postObject 객체 선언 
let replyObject = {

	// init() 함수 선언 
	init: function() {
		let _this = this;

		// "#btn-reply" 버튼에 "click" 이벤트가 발생하면 insertReply() 함수를 호출한다. 
		$("#btn-reply").on("click", () => {
			_this.insertReply();
		});
	},

	insertReply: function() {
		alert("댓글 등록 요청됨");

		let reply = {
			user_id: $("#user_id").val(),
			post_id: $("#post_id").val(),
			content: $("#reply_content").val()

		}
		console.log(reply);

		// Ajax를 이용한 비동기 호출
		$.ajax({
			type: "POST", // 요청 방식
			url: "/post/insertReply", // 요청 path
			data: JSON.stringify(reply), // user Object를 JSON으로 변환
			// HTTP 바디에 설정되는 데이터의 마임타입설정 
			contentType: "application/json; charset=utf-8"
			// done() : 요청 처리에 성공했을 때 실행될 코드를 작성한다.
			// 응답으로 들어온 JSON 데이터를 response로 받는다. 
		}).done(function(response) {
			// 메인 페이지로 이동한다.
			alert("댓글이 등록되었습니다.");
			location = "/post/"+ $("#post_id").val();
		});

	},
}

// postObject 객체의 init() 함수 호출. 
replyObject.init();

