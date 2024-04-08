package com.webspringmvc.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutController {
	@RequestMapping("/about-us")
	public String index() {
		return "user/about";
	}
}
