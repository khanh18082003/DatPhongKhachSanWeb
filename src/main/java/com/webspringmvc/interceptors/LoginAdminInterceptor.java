package com.webspringmvc.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginAdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String admin = (String)request.getSession().getAttribute("admin");
		if (admin == null) {
			response.sendRedirect(request.getContextPath() + "/admin/sign-in");
			return false;
		}
		return true;
	}
	
}
