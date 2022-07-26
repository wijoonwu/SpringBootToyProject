<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container mt-3">
	<div class="card-header">
		<h5 class="card-title">${post.title}</h5>
	</div>
	<div class="card-body">
		<h6 class="card-subtitle mb-2 text-muted">포스트 번호 : ${post.id} | 작성자 : ${post.user.username}</h6>
		<p class="card-text">${post.content}</p>
		<a class="btn btn-secondary" href="/">돌아가기</a>
		<c:if test="${post.user.username == user.username}">
			<a class="btn btn-primary" href="/post/update/${post.id}">수정하기</a>
			<a class="btn btn-danger" href="/post/delete/${post.id}">삭제하기</a>
		</c:if>

		<div class="container-fluid mt-3">
			<c:if test="${!empty replyList }">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">내용</th>
							<th scope="col">작성자</th>
							<th scope="col">삭제</th>
						</tr>
					</thead>
					<tbody class="table-group-divider">
						<c:forEach var="reply" items="${replyList }">
							<tr>
								<c:if test="${reply.user.username != user.username}">
									<td>${reply.content }</td>
									<td>${reply.user.username }</td>
									<td></td>
								</c:if>
								<c:if test="${reply.user.username == user.username}">
									<td>${reply.content }</td>
									<td>${reply.user.username }</td>
									<td><a href="/post/deleteReply/${reply.id}" class="btn btn-secondary">삭제</a></td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</c:if>
			<div class="card">
				<div class="card-body">
					<input type="text" id="reply_content"> <input type="text" id="post_id" hidden value="${post.id}">
					<button id="btn-reply" class="btn btn-secondary">댓글 등록</button>
				</div>

			</div>
		</div>
	</div>
</div>





<script src="/js/reply.js"></script>

<%@ include file="../layout/footer.jsp"%>
