package com.webspringmvc.controller.web;

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

import com.webspringmvc.entity.KhachHang;
import com.webspringmvc.entity.TaiKhoan;
import com.webspringmvc.service.ITaiKhoanService;
import com.webspringmvc.service.IUserService;

@Controller
public class UpdateUserController {
	private String role = "KH";

	@Autowired
	IUserService userService;

	@Autowired
	ITaiKhoanService taiKhoanService;

	@RequestMapping(value = "/update-user", method = RequestMethod.GET)
	public String updateUser(ModelMap model, HttpSession session, HttpServletRequest request) {
		String email = (String) session.getAttribute("author");
		KhachHang kh = userService.getUserByEmail(email);
		TaiKhoan tk = taiKhoanService.getTaiKhoan(email, role);

		if (kh == null) {
			kh = new KhachHang();
			kh.setemail(tk);
			model.addAttribute("user", kh);
			session.setAttribute("inserted", false);
		} else {
			kh.setemail(tk);
			model.addAttribute("user", kh);
			session.setAttribute("inserted", true);
		}

		return "user/update-info-user";
	}

	@RequestMapping(value = "/update-user", method = RequestMethod.POST)
	public String updateUser(ModelMap model, @Validated @ModelAttribute("user") KhachHang user, BindingResult br, HttpServletRequest request, HttpSession session) {
		if (!br.hasErrors()) {
			boolean inserted = (boolean) session.getAttribute("inserted");
			session.removeAttribute("inserted");
			if (!inserted) {
				System.out.println(inserted);
				//user.setemail(email);
				userService.insert(user);
			}else {
				userService.update(user);
			}
			model.addAttribute("success", "Update infomation of user successfully!");
		}
		return "user/update-info-user";
	}
}
