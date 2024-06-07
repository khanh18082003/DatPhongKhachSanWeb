package com.webspringmvc.controller.admin;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webspringmvc.entity.TaiKhoan;
import com.webspringmvc.reCapchaValidation.ReCapchaValidation;
import com.webspringmvc.service.ITaiKhoanService;
import com.webspringmvc.service.impl.MailerService;

@Controller(value = "loginAdmin")
@RequestMapping("/admin")
public class LoginController {
	String quyen = "NV";

	@Autowired
	ITaiKhoanService taiKhoanService;

	@Autowired
	MailerService mailer;

	@RequestMapping(value = "/sign-in", method = RequestMethod.GET)
	public String signInAdmin(ModelMap model) {
		
		model.addAttribute("taiKhoan", new TaiKhoan());
		return "/admin/login/sign-in";
	}

	@RequestMapping(value = "/sign-in", method = RequestMethod.POST)
	public String signInAdmin(ModelMap model, @Validated @ModelAttribute("taiKhoan") TaiKhoan taiKhoan,
			BindingResult br, HttpServletRequest request, HttpSession session) {
		ReCapchaValidation verify = new ReCapchaValidation();
		String gReCaptchaResponse = request.getParameter("g-recaptcha-response");
		boolean verifyRecaptcha = verify.verification(gReCaptchaResponse);
		int checkAccount = taiKhoanService.checkAccount(taiKhoan.getUsername(), taiKhoan.getPassword(), quyen);

		// show error in view
		if (!verifyRecaptcha) {
			model.addAttribute("reCapchaError", "Please click on ReCapcha to validate human");
		}
		if (checkAccount == 0) {
			br.rejectValue("username", "taiKhoan", "Email is not exist.");
		}
		if (checkAccount == 2) {
			br.rejectValue("password", "taiKhoan", "Password is incorrect.");
		}

		if (!br.hasErrors() && verifyRecaptcha) {
			session.setAttribute("admin", taiKhoan.getUsername());
			return "redirect:/admin/home";
		}
		return "admin/login/sign-in";
	}

	@RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
	public String forgotPassword(ModelMap model) {
		model.addAttribute("message", "Enter your email address and we will send you a link to reset your password.");
		return "admin/login/forgot-password";
	}

	@RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
	public String forgotPassword(ModelMap model, HttpServletRequest request, @RequestParam("email") String email) {
		String resetPasswordToken = UUID.randomUUID().toString();
		boolean success = taiKhoanService.updateResetPasswordToken(resetPasswordToken, email, quyen, model);
		if (success) {
			String resetPasswordUrl = request.getRequestURL().toString().replace(request.getServletPath(), "")
					+ "/reset-password?token=" + resetPasswordToken + "&userType=admin";
			String subject = "Here's the link to reset your password";
			String body = "<p>Hello</p>" + "<p>You have requested to reset your password</p>"
					+ "<p>Click the link below to change your password:</p>" + "<p><b><a href=\"" + resetPasswordUrl
					+ "\">Change my password</a></b></p>"
					+ "<p>Ignore this email if you remember your password, or you have not made this password</p>";
			mailer.send("Sona support", email, subject, body);
			model.addAttribute("message", "We have already send a link to your email for reseting your password");
		}

		return "admin/login/forgot-password";
	}
	
	

	@RequestMapping("/logout")
	public String logoutAdmin(HttpSession session) {
		session.removeAttribute("admin");
		return "redirect:/admin/sign-in";
	}
}
