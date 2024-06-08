package com.webspringmvc.controller.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webspringmvc.entity.Setting;
import com.webspringmvc.service.impl.MailerService;

@Controller
public class ContactController {
	
	@Autowired
	private MailerService mailer;
	
	@Autowired
	private ApplicationContext context;
	
	@RequestMapping("/contact-us")
	public String index(ModelMap model, HttpSession session) {
		Object obj = session.getAttribute("author");
		String email = null;
		if (obj != null) {
			email = (String) obj;
			model.addAttribute("emailUser", email);
		}
		return "user/contact";
	}

	@RequestMapping(value = "/contact-us", method = RequestMethod.POST)
	public String contactUs(ModelMap model, @RequestParam(value = "nameUser", required = true) String name,
			@RequestParam(value = "emailUser", required = true) String email,
			@RequestParam(value = "messageUser", required = true) String message) {
		if (name.isBlank()) {
			model.addAttribute("errorName", "Your name is not been empty");
		}
		if (email.isBlank()) {
			model.addAttribute("errorEmail", "Your email is not been empty");
		}
		if (message.isBlank()) {
			model.addAttribute("errorMess", "Your message is not been empty");
		}
		if (name != null && email != null && message != null) {
			Setting setting = (Setting) context.getBean("setting");
			String to = setting.getEmail();
			String subject = name + " contact with you";
			mailer.send(email, to, subject, message);
			model.addAttribute("success", "You submited successfully!");
		}
		return "user/contact";
	}
}
