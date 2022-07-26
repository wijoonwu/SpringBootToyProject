package com.fastcampus.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fastcampus.domain.Post;
import com.fastcampus.domain.Reply;
import com.fastcampus.domain.User;
import com.fastcampus.persistence.ReplyRepository;
import com.fastcampus.security.jpa.UserDetailsImpl;
import com.fastcampus.service.PostService;
import com.fastcampus.service.ReplyService;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private ReplyRepository replyRepsitory;

	@GetMapping({ "/", "" })
	public String getPostList(Model model,
			@PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {
		model.addAttribute("postList", postService.getPostList(pageable));
		return "welcome";
	}

	@GetMapping("/post/insertPost")
	public String insertPost() {
		return "post/insertPost";
	}

	@PostMapping("/post/insertPost")
	public @ResponseBody String insertPost(@RequestBody Post post,
	                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
	    // Post 객체를 등록하기 위해서는 반드시 Users 객체를 Post 에 설정해야 한다.
	    // 그래야 Post 가 POST 테이블에 등록될 때 FK(USER_ID) 컬럼에 회원의 PK(ID)를 등록해 준다.

	    // @AuthenticationPrincipal 을 통해서 ID 값이 안들어가던 것을 고침
	    User user = userDetails.getUser();
	    post.setUser(user);
	    postService.insertPost(post);
	    log.info("받은 post 값 = {}", post.getCreateDate());
	    return "새로운 1:1 문의를 등록했습니다.";
	}

	@PutMapping("/post/update")
	public String updatePost(@RequestBody Post post) {
		postService.updatePost(post);
		return "redirect:/";
	}
	
	@GetMapping("/post/update/{id}")
	public String updatePost(@PathVariable int id, Model model) {
		model.addAttribute("post", postService.getPost(id));
		return "post/edit";
	}

	@GetMapping("/post/{id}")
	public String getPost(@PathVariable int id, Model model,@AuthenticationPrincipal UserDetailsImpl principal) {
		Post post = postService.getPost(id);
		model.addAttribute("post", post);
		model.addAttribute("replyList", replyService.getReplyList(id));
		model.addAttribute("user",principal);
		return "post/show";
	}


	@GetMapping("/post/delete/{id}")
	public String deletePost(@PathVariable int id) {
		ArrayList<Reply> deleteReplyList = replyRepsitory.findByPostId(id);
		replyService.deleteByPost(deleteReplyList);
		postService.deletePost(id);
		return "redirect:/";
	}
}
