package com.webspringmvc.controller.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/notification")
public class NotificationController {
	@RequestMapping("/404")
	public String notFound(ModelMap model, @RequestParam(value = "userType", required = false) String userType) {
		if (userType != null) {
			model.addAttribute("role", "/" + userType);
		}
		model.addAttribute("title", "404 - Not found");
		return "404";
	}

	@RequestMapping("/500")
	public String serverError(ModelMap model, @RequestParam(value = "error", required = true) String error) {
		Object obj = model.get("userType");
		if (obj != null) {
			model.addAttribute("role", "/" + obj);
		}
		model.addAttribute("title", "500 - Server error");
		model.addAttribute("error", error);
		return "500";
	}

	@RequestMapping("/200")
	public String success(ModelMap model, HttpSession session) {
		model.addAttribute("message", (String) model.get("message"));
		model.addAttribute("title", "200 - Success");
		return "success";
	}
}
