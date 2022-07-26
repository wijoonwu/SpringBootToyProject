package com.fastcampus.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fastcampus.domain.User;
import com.fastcampus.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	
	// 로그인 인증 처리
	@PostMapping("/auth/login")
	public @ResponseBody String login(@RequestBody User user, HttpSession session) {
		User findUser = userService.getUser(user.getUsername());
		
		// 검색 결과 유무와 사용자가 입력한 비밀번호가 유효한지 확인한다. 
		if(findUser.getUsername() == null) {
			return "아이디에 해당하는 회원이 존재하지 않아요.";
		} else {
			if(user.getPassword().equals(findUser.getPassword())) {
				// 로그인 성공 시 세션에 사용자 정보를 저장한다.
				session.setAttribute("principal", findUser);
				return findUser.getUsername() + "님 로그인 성공하셨습니다.";
			} else {
				return "비밀번호 오류!";
			}
		}
	}
	
	// 로그아웃 처리
	@GetMapping("/auth/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";		
	}


}
