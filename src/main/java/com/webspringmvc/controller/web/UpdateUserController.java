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
			kh.setEmail(tk);
			model.addAttribute("user", kh);
			session.setAttribute("inserted", false);
		} else {
			kh.setEmail(tk);
			model.addAttribute("user", kh);
			session.setAttribute("inserted", true);
		}

		return "user/update-info-user";
	}

	@RequestMapping(value = "/update-user", method = RequestMethod.POST)
	public String updateUser(ModelMap model, @Validated @ModelAttribute("user") KhachHang user, BindingResult br, HttpServletRequest request, HttpSession session) {
		if (!br.hasErrors()) {
			boolean inserted = (boolean) session.getAttribute("inserted");
			String email = (String) session.getAttribute("author");
			TaiKhoan tk = taiKhoanService.getTaiKhoan(email, role);
			user.setEmail(tk);
			user.setHo(user.getHo().trim());
			user.setTen(user.getTen().trim());
			user.setSdt(user.getSdt().trim());
			
			if (!inserted) {
				userService.insert(user);
				session.setAttribute("inserted", true);
			}else {
				userService.update(user);
			}
			
			if (user.getHo() != null && user.getTen() != null) {
				session.setAttribute("name", user.getHo().trim() + " " + user.getTen().trim());
			}
			model.addAttribute("success", "Update information of user successfully!");
		}
		return "user/update-info-user";
	}
}
