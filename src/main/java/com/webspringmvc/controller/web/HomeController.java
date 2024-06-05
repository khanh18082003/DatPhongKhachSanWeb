package com.webspringmvc.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "homeControllerOfUser")
public class HomeController {
	@RequestMapping("/home")
	public String homePage(HttpServletRequest request, HttpSession session) {
		return "user/home";
	}
}
