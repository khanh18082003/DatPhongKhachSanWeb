package com.webspringmvc.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "homeControllerOfUser")
public class HomeController {
	@RequestMapping("/trang-chu")
	public String homePage() {
		return "user/home";
	}
}
