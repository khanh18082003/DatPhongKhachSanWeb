package com.webspringmvc.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.webspringmvc.entity.KhachHang;
import com.webspringmvc.service.IUserService;

public class BookRoomInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	IUserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String email = (String) request.getSession().getAttribute("author");
		KhachHang user = userService.getUserByEmail(email);
		if (user == null || (user.getHo() == null || user.getTen() == null || user.getSdt() == null)) {
			response.sendRedirect(request.getContextPath() + "/update-user");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

}
