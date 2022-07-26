package com.fastcampus.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.fastcampus.domain.User;

public class AuthenticateInterceptor implements HandlerInterceptor {

	@Override // 사전 처리(Controller가 실행되기 전에 동작)
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 세션에 회원정보가 있는지 확인한다. 
		HttpSession session = request.getSession();
		
		User principal = (User) session.getAttribute("principal");
		if(principal == null) {
			response.sendRedirect("/auth/login");
		}
		return true;
	}
	
}
