package com.webspringmvc.controller.web;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webspringmvc.entity.Quyen;
import com.webspringmvc.entity.TaiKhoan;
import com.webspringmvc.form.SignInForm;
import com.webspringmvc.form.SignUpForm;
import com.webspringmvc.helps.Bcrypt;
import com.webspringmvc.reCapchaValidation.ReCapchaValidation;
import com.webspringmvc.service.ITaiKhoanService;

@Controller
public class LoginController {
	@Autowired
	ITaiKhoanService taiKhoanService;

	@RequestMapping(value = "/sign-up", method = RequestMethod.GET)
	public String signUp(ModelMap model) {
		model.addAttribute("signUpForm", new SignUpForm());
		return "user/login/sign-up";
	}

	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public String signUp(ModelMap model, HttpServletRequest request,
			@Validated @ModelAttribute("signUpForm") SignUpForm signUpForm, BindingResult br, HttpSession session, HttpServletResponse response) {
		ReCapchaValidation verify = new ReCapchaValidation();
		String gReCaptchaResponse = request.getParameter("g-recaptcha-response");
		boolean verifyReCapcha = verify.verification(gReCaptchaResponse);
		boolean checkAccount = taiKhoanService.checkAccount(signUpForm.getUsername());
		
		if (!verifyReCapcha) {
			model.addAttribute("reCapchaError", "Please click on ReCapcha to validate human");
		}
		if (!signUpForm.checkConfirmPassword()) {
			br.rejectValue("confirmPassword", "signUpForm", "Passwords do not match.");
		}
		if (!checkAccount) {
			br.rejectValue("username", "signUpForm", "Email has signed up. Please enter other email.");
		}
		if (!br.hasErrors() && verifyReCapcha) {
			if (checkAccount) {
				String salt = UUID.randomUUID().toString();
				TaiKhoan newAccount = new TaiKhoan();
				newAccount.setUsername(signUpForm.getUsername());
				newAccount.setPassword(Bcrypt.toSHA1(signUpForm.getPassword(), salt));
				newAccount.setAuth(salt);
				newAccount.setQuyen(new Quyen("KH", "USER"));
				taiKhoanService.insert(newAccount);
				session.setAttribute("author", newAccount.getUsername());
				Cookie cookie = new Cookie("auth", salt);
				cookie.setMaxAge(60 * 60 * 24 * 2);
				response.addCookie(cookie);
				return "redirect:/trang-chu";
			}
		}
		return "user/login/sign-up";
	}

	@RequestMapping(value = "/sign-in", method = RequestMethod.GET)
	public String signIn(ModelMap model, HttpServletRequest request) {
		model.addAttribute("signInForm", new SignInForm());
		return "/user/login/sign-in";
	}

	@RequestMapping(value = "/sign-in", method = RequestMethod.POST)
	public String signIn(ModelMap model, HttpServletRequest request,
			@Validated @ModelAttribute("signInForm") SignInForm signInForm, BindingResult br, HttpSession session,
			HttpServletResponse response) {
		ReCapchaValidation verify = new ReCapchaValidation();
		String gReCaptchaResponse = request.getParameter("g-recaptcha-response");
		boolean verifyReCapcha = verify.verification(gReCaptchaResponse);
		boolean checkAcount = taiKhoanService.checkAccount(signInForm.getUsername());
		boolean checkPassword = taiKhoanService.checkPassword(signInForm.getUsername(), signInForm.getPassword());
	
		if (!verifyReCapcha) {
			model.addAttribute("reCapchaError", "Please click on ReCapcha to validate human");
		}
		if (checkAcount) {
			br.rejectValue("username", "signInForm", "Email is not exist.");
		}
		if (!checkPassword) {
			br.rejectValue("password", "signInForm", "Password or Email is incorrect.");
		}
		if (!br.hasErrors() && verifyReCapcha) {
			String salt = UUID.randomUUID().toString();
			TaiKhoan t = taiKhoanService.getTaiKhoan(signInForm.getUsername());
			t.setPassword(Bcrypt.toSHA1(signInForm.getPassword(), salt));
			t.setAuth(salt);
			taiKhoanService.update(t);
			session.setAttribute("author", signInForm.getUsername());
			Cookie cookie = new Cookie("auth", salt);
			cookie.setMaxAge(60 * 60 * 24 * 2); 
			response.addCookie(cookie);
			return "redirect:/trang-chu";
		}
		return "user/login/sign-in";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session, HttpServletResponse response) {
		session.removeAttribute("author");
		session.removeAttribute("name");
		Cookie cookie = new Cookie("auth", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "redirect:/trang-chu";
	}
}
