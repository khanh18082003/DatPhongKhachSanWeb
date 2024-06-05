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

@Transactional
@Controller
public class BookingHistory {
	@Autowired
	SessionFactory factory;

	@RequestMapping("/booking-history")
	public String index(HttpSession session) {
		// in ra danh sách các đơn đặt phòng đã thực hiện
		String auth = (String) session.getAttribute("author");
		String hql = "select username from TaiKhoan where auth = :auth";
		Session session_query = factory.getCurrentSession();
		Query query = session_query.createQuery(hql);
		query.setParameter("auth", auth);
		String username = (String) query.uniqueResult();

		hql = "select HD.tongTien, PD.ngayBD, PD.ngayKT, CTPD.hangPhong.tenHP,  CTPD.hangPhong.gia " + "from HoaDon HD "
				+ "join HD.maPD PD " + "join PD.ctPD CTPD "
				+ "where PD.taiKhoan.username = :username order by PD.ngayBD desc";

		// in ra thông tin các đơn đặt phòng
		session_query = factory.getCurrentSession();
		query = session_query.createQuery(hql);
		query.setParameter("username", username);
		List<Object[]> list = query.list();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		for (Object[] objects : list) {
			objects[1] = dateFormat.format((Date) objects[1]);
			objects[2] = dateFormat.format((Date) objects[2]);
		}
		// gửi thông tin đến trang booking-history.jsp
		session.setAttribute("bookingHistoryList", list);

		return "user/booking-history";
	}
}
