package com.webspringmvc.controller.admin;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webspringmvc.entity.CT_PhieuDat;
import com.webspringmvc.entity.PhieuDat;

@Controller(value = "BookingDetailHistory")
@Transactional
@RequestMapping("/admin")
public class BookingHistory {
	@Autowired
	SessionFactory factory;
	@RequestMapping("/booking-history")
	public String bookingHistory(Model model) {
		String hql = "From PhieuDat";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<PhieuDat> listPD = query.list();
		model.addAttribute("listPD", listPD);
		
		return "admin/booking-history";
	}

	@RequestMapping("/viewBookingDetail")
	public String viewBookingDetail(Model model,
			@RequestParam("id") int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CT_PhieuDat ctPD WHERE ctPD.phieuDat.maPD = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<CT_PhieuDat> listCTPD = query.list();
		System.out.println("listCTPD: " + listCTPD);
		model.addAttribute("listCTPD", listCTPD);
		return "admin/view-booking-detail";
	}
}
