package com.webspringmvc.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
		Query query = session.createQuery(hql);
		List<HangPhong> listHP = query.list();
		model.addAttribute("listHP", listHP);

		HangPhong hangPhong = new HangPhong();
		model.addAttribute("HangPhong", hangPhong);

		return "admin/hang-phong";
	}

	@ModelAttribute("listLP") // listLP
	public List<LoaiPhong> getLP() {
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiPhong";
		Query query = session.createQuery(hql);
		List<LoaiPhong> listLP = query.list();

//		Map<String, String> lp = new HashMap<String, String>();
//		for (LoaiPhong loaiPhong : listLP) {
//			lp.put(loaiPhong.getmaLP(), loaiPhong.gettenLP());
//		}

		return listLP;
	}

	@ModelAttribute("listKP") // listKP
	public List<KieuPhong> getKP() {
		Session session = factory.getCurrentSession();
		String hql = "FROM KieuPhong";
		Query query = session.createQuery(hql);
		List<KieuPhong> listKP = query.list();

//		Map<String, String> kp = new HashMap<String, String>();
//		for (KieuPhong kieuPhong : listKP) {
//			kp.put(kieuPhong.getmaKP(), kieuPhong.gettenKP());
//		}

		return listKP;
	}

	/*-------------------------- EDIT/UPDATE HANGPHONG --------------------------*/
	@RequestMapping(value = "/editHangPhong", method = RequestMethod.GET)
	public String editHangPhong(@RequestParam("id") String id, ModelMap model, HttpServletRequest request) {
		Session session = factory.getCurrentSession();
		HangPhong hangPhong = (HangPhong) session.get(HangPhong.class, id);;
		if (hangPhong == null) {
			// Xử lý trường hợp không tìm thấy hàng phòng với ID đã cho
			// Ví dụ: thông báo lỗi, redirect, hoặc hiển thị trang 404
		} else {
			
			model.addAttribute("hangPhong", hangPhong);
		}
		return "admin/editHangPhong";
	}

	@Autowired
	ServletContext context;

	@RequestMapping(value = "/editHangPhong", method = RequestMethod.POST)
	public String editHangPhong(@ModelAttribute("hangPhong") HangPhong hangPhong, ModelMap model,
			HttpServletRequest request) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
//			
//			hangPhong.setAnh(hangPhong.getAnh());
//			session.update(hangPhong);
			t.commit();
			model.addAttribute("message", "Update successful");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Update failed");
		} finally {
			session.close();
		}

		return "redirect:/admin/hang-phong";

	}
	/*-------------------------- EDIT/UPDATE HANGPHONG PHOTO --------------------------*/
	@RequestMapping(value = "/editRoomPhoto", method = RequestMethod.GET)
	public String editRoomPhoto(@RequestParam("id") String id, ModelMap model, HttpServletRequest request) {
		Session session = factory.getCurrentSession();
		HangPhong hangPhong = (HangPhong) session.get(HangPhong.class, id);
		if (hangPhong == null) {
			// Xử lý trường hợp không tìm thấy hàng phòng với ID đã cho
			// Ví dụ: thông báo lỗi, redirect, hoặc hiển thị trang 404
		} else {
			
			model.addAttribute("hangPhong", hangPhong);
		}
		return "admin/editRoomPhoto";
	}
	


	/*-------------------------- INSERT HANGPHONG --------------------------*/

	@RequestMapping(value = "/insertHangPhong", method = RequestMethod.GET)
	public String insertHangPhong(ModelMap model) {
		model.addAttribute("hangPhong", new HangPhong());
		return "admin/insertHangPhong";
	}

	@RequestMapping(value = "/insertHangPhong", method = RequestMethod.POST)
	public String insertHangPhong(@ModelAttribute("hangPhong") HangPhong hangPhong,
			@RequestParam("photo") MultipartFile photo, ModelMap model) {

		String photoPath = "";
		if (photo.isEmpty()) {
			model.addAttribute("message", "Please Choose Your Photo");
		} else {
			try {
				photoPath = context.getRealPath("/template/admin/assets/img/" + photo.getOriginalFilename());
				photo.transferTo(new File(photoPath));
				
			} catch (IOException e) {
				model.addAttribute("message", "Error To Save File");
				
			}
		}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			hangPhong.setAnh(photoPath);
			session.save(hangPhong);
			t.commit();
			model.addAttribute("message", "Insert Successful");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Insert Failed" + e.getMessage());
		} finally {
			session.close();
		}

//		return "admin/insertHangPhong";
		return "redirect:/admin/hang-phong";

	}

	/*-------------------------- DELETE HANGPHONG --------------------------*/

	@RequestMapping(value = "/deleteHangPhong", method = RequestMethod.GET)
	public String deleteHangPhong(@RequestParam("id") String id, ModelMap model) {

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			HangPhong hangPhong = (HangPhong) session.get(HangPhong.class, id);
			session.delete(hangPhong);
			t.commit();
			model.addAttribute("message", "Delete Successful");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Delete Failed" + e.getMessage());
		} finally {
			session.close();
		}

//		return "admin/insertHangPhong";
		return "redirect:/admin/hang-phong";

	}

	@RequestMapping("/phong")
	public String phongPage() {
		return "admin/phong";
	}
}
