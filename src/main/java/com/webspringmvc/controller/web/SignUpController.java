package com.webspringmvc.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class SignUpController {
	@RequestMapping(value="/sign-up", method = RequestMethod.GET)
	public String signUp() {
		
		return "login/sign-up";
	}
	
	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public String signUp(ModelMap model) {
		return null;
		
	}
}
