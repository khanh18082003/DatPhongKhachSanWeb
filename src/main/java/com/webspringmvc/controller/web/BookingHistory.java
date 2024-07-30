package com.webspringmvc.controller.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Transactional
@Controller
public class BookingHistory {
	@Autowired
	SessionFactory factory;

	@RequestMapping("/booking-history")
	public String index(HttpSession session) {
		// in ra danh sách các đơn đặt phòng đã thực hiện
		String username = (String) session.getAttribute("author");
		String hql = "select HD.tongTien, PD.ngayBD, PD.ngayKT, CTPD.hangPhong.tenHP,  CTPD.hangPhong.gia, PD.trangThai,PD.maPD " + "from HoaDon HD "
				+ "join HD.maPD PD " + "join PD.ctPD CTPD "
				+ "where PD.taiKhoan.username = :username order by PD.ngayBD desc";

		// in ra thông tin các đơn đặt phòng
		Session session_query = factory.getCurrentSession();
		Query query = session_query.createQuery(hql);
		query.setParameter("username", username);
		List<Object[]> list = query.list();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		for (Object[] objects : list) {
			objects[1] = dateFormat.format((Date) objects[1]);
			objects[2] = dateFormat.format((Date) objects[2]);
			if (objects[5].equals(0)) {
				objects[5] = "Booked";
			} else if(objects[5].equals(1)) {
				objects[5] = "Success";
			}
			else {
				objects[5] = "Cancelled";
			}
		}
		session.setAttribute("bookingHistoryList", list);

		return "user/booking-history";
	}
	@RequestMapping(value = "/booking-history",method = RequestMethod.POST)
	public String cancelBooking(HttpSession session, @RequestParam("maPD") int maPD) {
		// hủy đơn đặt phòng
		Session session_query = factory.getCurrentSession();
		String hql = "update PhieuDat set trangThai = -1 where maPD = :maPD";
		Query query = session_query.createQuery(hql);
		query.setParameter("maPD", maPD);
		query.executeUpdate();
		return "redirect:/booking-history";
	}
}
