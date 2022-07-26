package com.fastcampus.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fastcampus.domain.Reply;
import com.fastcampus.domain.User;
import com.fastcampus.persistence.ReplyRepository;
import com.fastcampus.security.jpa.UserDetailsImpl;
import com.fastcampus.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ReplyController {

	@Autowired
	private ReplyService replyService;

	@Autowired
	private ReplyRepository replyRepository;

	@PostMapping("/post/insertReply")
	public String insertReply(@RequestBody Reply reply,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {
	    // Post 객체를 등록하기 위해서는 반드시 Users 객체를 Post 에 설정해야 한다.
	    // 그래야 Post 가 POST 테이블에 등록될 때 FK(USER_ID) 컬럼에 회원의 PK(ID)를 등록해 준다.
	    // @AuthenticationPrincipal 을 통해서 ID 값이 안들어가던 것을 고침
	    User user = userDetails.getUser();
	    reply.setUser(user);
	    replyService.insertReply(reply);
	    int postId = reply.getPost_id();

		return "redirect:/post/"+postId ;
	}

	@GetMapping("/post/deleteReply/{id}")
	public String deleteReply(@PathVariable int id) {
		Reply reply = replyRepository.getById(id);
		int postId = reply.getPost_id();
		replyService.deleteReply(id);
		return "redirect:/post/" + postId ;
	}
}
