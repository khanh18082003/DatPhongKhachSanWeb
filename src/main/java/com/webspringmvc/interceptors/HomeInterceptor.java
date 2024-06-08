package com.webspringmvc.interceptors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.webspringmvc.entity.KhachHang;
import com.webspringmvc.entity.TaiKhoan;
import com.webspringmvc.service.ITaiKhoanService;
import com.webspringmvc.service.IUserService;

public class HomeInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	ITaiKhoanService taiKhoanService;
	
	@Autowired
	IUserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return true;
		}
		HttpSession session = request.getSession();
		for (Cookie c : cookies) {
			if (c.getName().equals("token")) {
				String token = c.getValue();
				TaiKhoan t = taiKhoanService.get(token, 2);
				
				if (t == null) {
					response.sendError(500, "Error Token is not valid!");
					return false; 
				}
				KhachHang kh = userService.getUserByEmail(t.getUsername());
				if (kh != null) {
					session.setAttribute("name", kh.getHo().trim() + " "+ kh.getTen().trim());
				}
				session.setAttribute("author", t.getUsername());
				break;
			}
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
