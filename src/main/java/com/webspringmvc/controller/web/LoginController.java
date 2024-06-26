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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webspringmvc.entity.Quyen;
import com.webspringmvc.entity.TaiKhoan;
import com.webspringmvc.form.ResetPasswordForm;
import com.webspringmvc.form.SignInForm;
import com.webspringmvc.form.SignUpForm;
import com.webspringmvc.helps.Bcrypt;
import com.webspringmvc.reCapchaValidation.ReCapchaValidation;
import com.webspringmvc.service.ITaiKhoanService;
import com.webspringmvc.service.impl.MailerService;

@Controller(value = "loginUser")
public class LoginController {
	@Autowired
	ITaiKhoanService taiKhoanService;

	@Autowired
	MailerService mailer;

	@RequestMapping(value = "/sign-up", method = RequestMethod.GET)
	public String signUp(ModelMap model) {
		model.addAttribute("signUpForm", new SignUpForm());
		return "user/login/sign-up";
	}

	String quyen = "KH";

	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public String signUp(ModelMap model, HttpServletRequest request,
			@Validated @ModelAttribute("signUpForm") SignUpForm signUpForm, BindingResult br, HttpSession session,
			HttpServletResponse response) {
		// verify recaptcha
		ReCapchaValidation verify = new ReCapchaValidation();
		String gReCaptchaResponse = request.getParameter("g-recaptcha-response");
		boolean verifyReCapcha = verify.verification(gReCaptchaResponse);
		// check account duplicate
		int checkAccount = taiKhoanService.checkAccount(signUpForm.getUsername(), signUpForm.getPassword(), quyen);

		// show error in view
		if (!verifyReCapcha) {
			model.addAttribute("reCapchaError", "Please click on ReCapcha to validate human");
		}
		if (!signUpForm.checkConfirmPassword()) {
			br.rejectValue("confirmPassword", "signUpForm", "Passwords do not match.");
		}
		if (checkAccount != 0) {
			br.rejectValue("username", "signUpForm", "Email has signed up. Please enter other email.");
		}
		// if no error and verify recaptcha
		if (!br.hasErrors() && verifyReCapcha) {
			// random secret key and set taikhoan
			String salt = UUID.randomUUID().toString();
			TaiKhoan newAccount = new TaiKhoan();
			newAccount.setUsername(signUpForm.getUsername());
			newAccount.setPassword(Bcrypt.toSHA1(signUpForm.getPassword(), salt));
			newAccount.setAuth(salt);
			newAccount.setQuyen(new Quyen("KH", "USER"));
			String token = Bcrypt.toSHA1(signUpForm.getUsername() + signUpForm.getPassword(), salt);
			newAccount.setToken(token);
			// insert to db
			taiKhoanService.insert(newAccount);
			// add session author to authenticate user
			session.setAttribute("author", newAccount.getUsername());
			// add cookie to when visit this web then user don't need to sign in
			Cookie cookie = new Cookie("token", token);
			cookie.setMaxAge(60 * 60 * 24 * 2);
			response.addCookie(cookie);
			return "redirect:/home";

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
		int checkAccount = taiKhoanService.checkAccount(signInForm.getUsername(), signInForm.getPassword(), quyen);

		if (!verifyReCapcha) {
			model.addAttribute("reCapchaError", "Please click on ReCapcha to validate human");
		}
		if (checkAccount == 0) {
			br.rejectValue("username", "signInForm", "Email is not exist.");
		}
		if (checkAccount == 2) {
			br.rejectValue("password", "signInForm", "Password is incorrect.");
		}
		if (!br.hasErrors() && verifyReCapcha) {
			// set key password
			String salt = UUID.randomUUID().toString();
			TaiKhoan t = taiKhoanService.getTaiKhoan(signInForm.getUsername(), quyen);
			t.setPassword(Bcrypt.toSHA1(signInForm.getPassword(), salt));
			t.setAuth(salt);
			// create token
			String token = Bcrypt.toSHA1(signInForm.getUsername() + signInForm.getPassword(), salt);
			t.setToken(token);
			taiKhoanService.update(t);
			Cookie cookie = new Cookie("token", token);
			cookie.setMaxAge(60 * 60 * 24 * 2);
			response.addCookie(cookie);
			// set session for tab
			session.setAttribute("author", signInForm.getUsername());
			return "redirect:/home";
		}
		return "user/login/sign-in";
	}

	@RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
	public String forgotPassword(ModelMap model) {
		String message = "Provide the email address associated with your account to recover your password";
		String title = "Forgot-Password";
		model.addAttribute("message", message);
		model.addAttribute("title", title);
		return "user/login/forgot-password";
	}

	@RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
	public String forgotPassword(ModelMap model, @RequestParam("email") String email, HttpServletRequest request) {
		String resetPasswordToken = UUID.randomUUID().toString();
		boolean success = taiKhoanService.updateResetPasswordToken(resetPasswordToken, email, quyen, model);
		if (success) {
			String resetPasswordUrl = request.getRequestURL().toString().replace(request.getServletPath(), "")
					+ "/reset-password?token=" + resetPasswordToken;
			String subject = "Here's the link to reset your password";
			String body = "<p>Hello</p>" + "<p>You have requested to reset your password</p>"
					+ "<p>Click the link below to change your password:</p>" + "<p><b><a href=\"" + resetPasswordUrl
					+ "\">Change my password</a></b></p>"
					+ "<p>Ignore this email if you remember your password, or you have not made this password</p>";
			mailer.send("Sona support", email, subject, body);
		}

		model.addAttribute("message", "Check your mail");
		model.addAttribute("title", "Forgot-Password");
		return "user/login/forgot-password";
	}

	@RequestMapping(value = "/reset-password", method = RequestMethod.GET)
	public String resetPassword(ModelMap model, @RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "userType", required = false) String userType, RedirectAttributes rd) {
		String title = "Reset-Password";
		TaiKhoan t = taiKhoanService.get(token, 1);
		if (t != null) {
			model.addAttribute("resetPasswordForm", new ResetPasswordForm());
			model.addAttribute("token", token);
			model.addAttribute("title", title);
			return "user/login/reset-password";
		}
		String error = "Invalid Token";
		rd.addAttribute("error", error);
		rd.addFlashAttribute("userType", userType);
		return "redirect:/notification/500";
	}

	@RequestMapping(value = "/reset-password", method = RequestMethod.POST)
	public String resetPassword(HttpServletRequest request, ModelMap model,
			@Validated @ModelAttribute("resetPasswordForm") ResetPasswordForm resetPasswordForm, BindingResult br,
			RedirectAttributes rd) {
		String token = request.getParameter("token");
		TaiKhoan t = taiKhoanService.get(token, 1);

		if (t != null) {
			boolean checkPassword = t.getPassword().equals(Bcrypt.toSHA1(resetPasswordForm.getPassword(), t.getAuth()));
			if (checkPassword) {
				br.rejectValue("password", "resetPasswordForm", "This password has recently reset.");
			}

			if (!resetPasswordForm.checkConfirmPassword()) {
				br.rejectValue("confirmPassword", "resetPasswordForm", "Passwords do not match.");
			}

			if (!br.hasErrors()) {
				taiKhoanService.updateNewPassword(t, resetPasswordForm.getPassword());
				rd.addFlashAttribute("message", "Reset Password");
				return "redirect:/notification/200";
			}
			model.addAttribute("token", token);
			return "user/login/reset-password";
		}
		String error = "invalidToken";
		rd.addAttribute("error", error);
		return "redirect:/notification/500";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session, HttpServletResponse response) {
		session.removeAttribute("author");
		session.removeAttribute("name");
		Cookie cookie = new Cookie("token", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "redirect:/home";
	}
}
