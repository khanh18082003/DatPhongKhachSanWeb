package com.webspringmvc.controller.admin;

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

import com.webspringmvc.entity.TaiKhoan;
import com.webspringmvc.reCapchaValidation.ReCapchaValidation;
import com.webspringmvc.service.ITaiKhoanService;

@Controller(value = "loginAdmin")
@RequestMapping("/admin")
public class LoginController {
	String quyen = "NV";

	@Autowired
	ITaiKhoanService taiKhoanService;

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
		return "/admin/login/sign-in";
	}
	
	@RequestMapping("/logout")
	public String logoutAdmin(HttpSession session) {
		session.removeAttribute("admin");
		return "redirect:/admin/sign-in";
	}
}
