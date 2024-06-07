package com.webspringmvc.controller.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webspringmvc.entity.HangPhong;
import com.webspringmvc.entity.LoaiPhong;
@Transactional
@Controller(value = "homeControllerOfUser")
public class HomeController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("/home")	
	public String homePage(HttpServletRequest request, ModelMap model) {
		Session session_query = factory.getCurrentSession();
		String hql = "From HangPhong";
		Query query = session_query.createQuery(hql);
		List<HangPhong> listHPTemp = query.list();
		hql = "select distinct soLuongNguoi from HangPhong";
		query = session_query.createQuery(hql);
		List<Integer> listSLN = query.list();
		hql = "From LoaiPhong";
		query = session_query.createQuery(hql);
		List<LoaiPhong> listLP = query.list();
		
		model.addAttribute("listLP", listLP);
		model.addAttribute("listSLN", listSLN);
		return "user/home";
	}
}
