<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container mt-3">
	<form>
		<div class="mb-3">
			<label for="title">Title:</label> <input type="text" class="form-control" id="title" value="${post.title}">
		</div>
		<div class="mb-3">
			<label for="content">Content:</label>
			<textarea class="form-control" rows="5" id="content">${post.content}</textarea>
		</div>
		<input type="hidden" value="${post.id}" id="id"> <input type="hidden" value="${users.usersname}" id="username">
	</form>

	<button id="btn-insert" class="btn btn-secondary">포스트 수정</button>
</div>

<script>
	$(document).ready(function() {
		$("#content").summernote({
			height : 300
		});
	});
</script>

<script src="/js/edit.js"></script>

<%@ include file="../layout/footer.jsp"%>