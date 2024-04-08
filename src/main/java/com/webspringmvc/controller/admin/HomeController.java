package com.webspringmvc.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "homeControllerOfAdmin")
@RequestMapping("/admin")
public class HomeController {
	@RequestMapping("/trang-chu")
	public String homePage() {
		return "admin/home";
	}
}