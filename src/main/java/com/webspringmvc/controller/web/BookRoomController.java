package com.webspringmvc.controller.web;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webspringmvc.dao.ITaiKhoanDao;
import com.webspringmvc.entity.CT_PhieuDat;
import com.webspringmvc.entity.HangPhong;
import com.webspringmvc.entity.PhieuDat;
import com.webspringmvc.entity.TaiKhoan;
import com.webspringmvc.service.ITaiKhoanService;
import com.webspringmvc.service.impl.MailerService;

@Transactional
@Controller
public class BookRoomController {
	@Autowired
	SessionFactory factory;
	@Autowired
	MailerService mailer;
	@RequestMapping("/book-room")
	public String formBookRoom(@RequestParam("id") String idHP,
			@RequestParam("checkin") String dateIn,
			@RequestParam("checkout") String dateOut,
			@RequestParam("sl") String sLP,
			HttpServletRequest request) {
		SimpleDateFormat originalFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        SimpleDateFormat targetFormat = new SimpleDateFormat("EEE dd MMM yyyy", Locale.ENGLISH);
		try {
			Date dateInTemp = originalFormat.parse(dateIn);
			Date dateOutTemp = originalFormat.parse(dateOut);
			dateIn = targetFormat.format(dateInTemp);
			dateOut = targetFormat.format(dateOutTemp);
		} catch (ParseException  e) {
		    e.printStackTrace();
		}
		Session session = factory.getCurrentSession();
		String hql = "select hp.gia from HangPhong as hp where hp.idHP = :idHP";
		Query query = session.createQuery(hql);
		query.setParameter("idHP", idHP);
		float price = (float)query.uniqueResult();
		request.setAttribute("numberOfRoom", sLP);
		request.setAttribute("price", price);
		request.setAttribute("dateIn", dateIn);
		request.setAttribute("dateOut", dateOut);
		request.setAttribute("idHP", idHP);
		return "user/book-room";
	}
	
	@RequestMapping(value = "/booking-room", method = RequestMethod.POST)
	public String bookRoom(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("idHP") String idHP ) {	
		try {
            String maPD = "PD02";
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd MMM yyyy", Locale.ENGLISH);
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            Date now = Calendar.getInstance().getTime();
            Timestamp ngayDat = new Timestamp(dateFormat.parse(dateFormat.format(now)).getTime());
            
            String ngayBDStr = request.getParameter("checkin");
            Date date = dateFormat.parse(ngayBDStr);
            ngayBDStr = outputFormat.format(date);
            Timestamp ngayBD = new Timestamp(outputFormat.parse(ngayBDStr).getTime());
            
            String ngayKTStr = request.getParameter("checkout");
            date = dateFormat.parse(ngayKTStr);
            ngayKTStr = outputFormat.format(date);
            Timestamp ngayKT = new Timestamp(outputFormat.parse(ngayKTStr).getTime());
            
            PhieuDat PD = new PhieuDat(maPD, ngayDat, ngayBD, ngayKT);
            
            int numberOf = Integer.parseInt(request.getParameter("numberOfRoom"));
            String hql = "From HangPhong where idHP = :idHP";
            Session session = factory.getCurrentSession();
    		Query query = session.createQuery(hql);
    		query.setParameter("idHP", idHP);
    		HangPhong hp = (HangPhong) query.uniqueResult();
            CT_PhieuDat  CTPD = new CT_PhieuDat(PD, hp, numberOf);
            
            Session session_insert = factory.openSession();
            Transaction t = session_insert.beginTransaction();
            try {
            	session_insert.save(PD);
            	t.commit();
            	session_insert.save(CTPD);
            	t.commit();
            }
            catch (Exception e) {
				t.rollback();
			}
            finally {
            	session_insert.close();
            }
            // Continue with using PD object
        } catch (ParseException e) {
            System.out.println("Error parsing the date: " + e.getMessage());
        }
//		String tittle = "Thank! Your booking at Sona has been confirmed.";
//		String body = "Cam on ban";
//		System.out.println(email);
//		mailer.send("Sona Support", email, tittle, body);
		return "user/home";
	}
}
