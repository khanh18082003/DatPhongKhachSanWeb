package com.webspringmvc.controller.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webspringmvc.entity.CT_PhieuDat;
import com.webspringmvc.entity.HangPhong;
import com.webspringmvc.entity.HoaDon;
import com.webspringmvc.entity.KhachHang;
import com.webspringmvc.entity.PhieuDat;
import com.webspringmvc.entity.TaiKhoan;
import com.webspringmvc.page.ChangePage;
import com.webspringmvc.service.impl.MailerService;

@Transactional
@Controller
public class BookRoomController{
	@Autowired
	SessionFactory factory;
	@Autowired
	MailerService mailer;
	@RequestMapping("/book-room")
	public String formBookRoom(HttpServletRequest request, HttpSession sessionUser,
			ModelMap model) {
		Map<String, Integer> discount = RoomsController.getDiscount();
		model.addAttribute("discount", discount);
		
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH);
		try {
			String ngayBDStr = request.getParameter("checkin");
            Timestamp ngayBD = new Timestamp(targetFormat.parse(ngayBDStr).getTime());
            
            String ngayKTStr = request.getParameter("checkout");
            Timestamp ngayKT = new Timestamp(targetFormat.parse(ngayKTStr).getTime());
            
            int sLP = Integer.parseInt(request.getParameter("sl"));
            System.out.println(sLP);
            String idHP = request.getParameter("id");
            String hql = "From HangPhong where idHP = :idHP";
            Session session = factory.getCurrentSession();
    		Query query = session.createQuery(hql);
    		query.setParameter("idHP", idHP);
    		HangPhong hp = (HangPhong) query.uniqueResult();
    		
    		String username = sessionUser.getAttribute("author").toString();
    		hql ="from TaiKhoan where username = :username";
    		query = session.createQuery(hql);
    		query.setParameter("username", username);
    		TaiKhoan tk = (TaiKhoan) query.uniqueResult();
    		// Xử lý thêm Khach Hang
    		hql = "select kh from KhachHang kh join kh.email tk where tk.username = :username";
    		query = session.createQuery(hql);
    		query.setParameter("username", username);
    		List<KhachHang> khList = query.list();
    		KhachHang kh = khList.isEmpty() ? new KhachHang() : khList.get(0);
    		model.addAttribute("khachHang", kh);
    		
    		Date dateNow = Calendar.getInstance().getTime();
            Timestamp ngayDat = new Timestamp(targetFormat.parse(targetFormat.format(dateNow)).getTime());
    		PhieuDat pd = new PhieuDat(ngayDat, ngayBD, ngayKT, tk, 0);
    		
    		int soNgay = (int)TimeUnit.DAYS.convert(ngayKT.getTime() - ngayBD.getTime(), TimeUnit.MILLISECONDS);
    		
    		CT_PhieuDat ctpd =  new CT_PhieuDat(pd,hp,sLP);
    		request.getSession().setAttribute("ctPD", ctpd);
    		request.getSession().setAttribute("pd", pd);
    		request.getSession().setAttribute("tk", tk);
    		request.setAttribute("ctPhieuDat", ctpd);
    		request.setAttribute("soNgay", soNgay);

//    		Map<String, Integer> discount = (Map<String, Integer>) request.getSession().getAttribute("discount");
		} catch (ParseException  e) {
		    e.printStackTrace();
		}
		return "user/book-room";
	}
	
	@RequestMapping(value = "/booking-room")
	public String generateQRCode(Model model) {
		String amount = "10000";
        String checkContent = "testqr13072024";
        String accountName = "NGUYEN THANH TAM";
        String qrImgSrc = "https://img.vietqr.io/image/970422-0396441431-compact2.png?amount=" + amount + "&addInfo=" + checkContent + "&accountName=" + accountName.replace(" ", "%20");
        
        model.addAttribute("qrImgSrc", qrImgSrc);
        return "user/QRcode";
    }
	@RequestMapping(value = "/booking-room-success", method = RequestMethod.GET)
    public String bookRoom() {
        // Xử lý logic khi booking room thành công
        return "redirect:/notification/200";
    }
//	public String bookRoom(HttpServletRequest request,
//			@Validated @ModelAttribute("khachHang") KhachHang kh, HttpSession sessionUser,
//			BindingResult err, ModelMap model, RedirectAttributes rd) {
//		Map<String, Integer> discount = RoomsController.getDiscount();
//
//		CT_PhieuDat ctpd = (CT_PhieuDat) request.getSession().getAttribute("ctPD");
//		PhieuDat pd = (PhieuDat)request.getSession().getAttribute("pd");
//		
//		String username = sessionUser.getAttribute("author").toString();
//		Session session = factory.getCurrentSession();
//		String hql ="from TaiKhoan where username = :username";
//		Query query = session.createQuery(hql);
//		query.setParameter("username", username);
//		TaiKhoan tk = (TaiKhoan) query.uniqueResult();
//		
//		if(err.hasErrors()) {
//			request.setAttribute("ctPhieuDat", ctpd);
//			request.setAttribute("pd", pd);
//			request.setAttribute("tk", tk);
//			int soNgay = (int)TimeUnit.DAYS.convert(pd.getNgayKT().getTime() - pd.getNgayBD().getTime(), TimeUnit.MILLISECONDS);
//			request.setAttribute("soNgay", soNgay);
//			request.getSession().setAttribute("ctPD", ctpd);
//    		request.getSession().setAttribute("pd", pd);
//	        return "user/book-room";
//		}
//		Session session_insert = factory.openSession();
//		Transaction t = session_insert.beginTransaction();
//		// Kiểm tra xem có tài khoản hay chưa
//		hql = "select kh from KhachHang kh join kh.email tk where tk.username = :username";
//		query = session_insert.createQuery(hql);
//		query.setParameter("username", tk.getUsername());
//		List<KhachHang> khList = query.list();
//		int discountValue = discount.getOrDefault(ctpd.getHangPhong().getIdHP(), 0);
//		float tongTien = ctpd.getHangPhong().getGia() * ctpd.getsLPhong() * (100 - discountValue) / 100;
//        Timestamp myDateObj = new Timestamp(System.currentTimeMillis());
//        HoaDon hd = new HoaDon(myDateObj, tongTien, pd);
//		try {
//			if (khList.isEmpty()) {
//				kh.setEmail(tk);
//				session_insert.save(kh);
//			}
//			session_insert.save(pd);
//			session_insert.save(ctpd);
//			session_insert.save(hd);
//			t.commit();
//		} catch (Exception e) {
//			t.rollback();
//			e.printStackTrace();
//		} finally {
//			session_insert.close();
//		}
//		// Gửi mail
//		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
//        SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
//        
//        String checkInDate = "";
//        String checkOutDate = "";
//        try {
//            checkInDate = targetFormat.format(originalFormat.parse(pd.getNgayBD().toString()));
//            checkOutDate = targetFormat.format(originalFormat.parse(pd.getNgayKT().toString()));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        String subject = "Thank! Your booking at Sona has been confirmed.";
//        String body = "</p>Dear " + kh.getHo() +" "+kh.getTen() + ",</p>"
//                + "<p>We sincerely thank you for choosing [Hotel/Resort Name]. We are pleased to inform you that your booking has been successfully confirmed with the following details:</p>"
//                + "<p>Customer Name: " + kh.getHo() +" "+kh.getTen() + "</p>"
//                + "<p>Booking Number: " + kh.getSdt()+ "</p>"
//                + "<p>Room :" + ctpd.getHangPhong().getTenHP() + "</p>"
//                + "<p>Check-in Date: "+ checkInDate + "</p>"
//                + "<p>Check-out Date: "+ checkOutDate + "</p>"
//                + "<p>Total Amount: " + tongTien + "</p>"
//                + "<p>We are committed to providing you with an exceptional and memorable stay. If you have any special requests or questions, please feel free to contact us via this email or call our hotline at 0123456789.</p>"
//                + "<p>Once again, thank you, and we look forward to welcoming you to Sona.</p>"
//                + "<p>Best regards,</p>"
//                + "<p>Sona Hotel</p>";
//
//        mailer.send("Sona Support", tk.getUsername(), subject, body);
//        rd.addFlashAttribute("message", "Booking Room");
//        return "redirect:/notification/200";
//	}
}
