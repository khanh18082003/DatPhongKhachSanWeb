package com.webspringmvc.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webspringmvc.entity.HangPhong;
import com.webspringmvc.entity.KieuPhong;
import com.webspringmvc.entity.LoaiPhong;

@Transactional
@Controller
@RequestMapping("/admin")
public class RoomController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("/hang-phong")
	public String hangPhongPage(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM HangPhong";
		Query query =  session.createQuery(hql);
		List<HangPhong> listHP = query.list();
		model.addAttribute("listHP", listHP);
		
		HangPhong hangPhong = new HangPhong();
		model.addAttribute("HangPhong", hangPhong);
		
		return "admin/hang-phong";
	}
	
	@ModelAttribute("lp") // listLP
	public Map<String, String> getLP() {
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiPhong";
		Query query =  session.createQuery(hql);
		List<LoaiPhong> listLP = query.list();
		
		Map<String, String> lp = new HashMap<String, String>();
		for (LoaiPhong loaiPhong : listLP) {
			lp.put(loaiPhong.getmaLP(), loaiPhong.gettenLP());
		}
		
		return lp;
	}
	
	@ModelAttribute("kp") // listKP
	public Map<String, String> getKP() {
		Session session = factory.getCurrentSession();
		String hql = "FROM KieuPhong";
		Query query =  session.createQuery(hql);
		List<KieuPhong> listKP = query.list();
		
		Map<String, String> kp = new HashMap<String, String>();
		for (KieuPhong kieuPhong : listKP) {
			kp.put(kieuPhong.getmaKP(), kieuPhong.gettenKP());
		}
		
		return kp;
	}
	
	@RequestMapping("/phong")
	public String phongPage() {
		return "admin/phong";
	}
}
