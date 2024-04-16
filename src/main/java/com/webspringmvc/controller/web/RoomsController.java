package com.webspringmvc.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webspringmvc.entity.HangPhong;

@Transactional
@Controller
@RequestMapping("/rooms")
public class RoomsController {
	@Autowired
	SessionFactory factory;
	@RequestMapping(params = "page")
	public String index(@RequestParam("page") int page,ModelMap model) {
		System.out.println(page);
		Session session = factory.getCurrentSession();
		String hql = "From HangPhong";
		Query query = session.createQuery(hql);
		List<HangPhong> listHP = query.list();
		List<HangPhong> listHPTemp = new ArrayList<HangPhong>();
		for (int i = 1; i < listHP.size()+1; i++) {
			if (i == page) {
				listHPTemp.add(listHP.get(i-1));
			}
		}
		model.addAttribute("roomList",listHPTemp);
		return "user/rooms";
	}
	
	@RequestMapping("/room-detail")
	public String roomDetail() {
		return "user/room-detail";
	}
}
