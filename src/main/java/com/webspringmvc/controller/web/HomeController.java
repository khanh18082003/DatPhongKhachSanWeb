package com.webspringmvc.controller.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "homeControllerOfUser")
public class HomeController {
	@RequestMapping("/trang-chu")
	public String homePage(HttpServletRequest request, HttpSession session) {
		Cookie[] cookies = request.getCookies();
		String infos = "";
		for (Cookie c : cookies) {
			if (c.getName().equals("auth")) {
				infos = c.getValue();
				session.setAttribute("author", infos);
				break;
			}
		}
		
		return "user/home";
	}
}
