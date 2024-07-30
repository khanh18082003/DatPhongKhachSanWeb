package com.webspringmvc.controller.web;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webspringmvc.entity.CT_PhieuDat;
import com.webspringmvc.entity.HangPhong;
import com.webspringmvc.entity.HoaDon;
import com.webspringmvc.entity.KhachHang;
import com.webspringmvc.entity.PhieuDat;
import com.webspringmvc.entity.Setting;
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
	@Autowired
	Setting setting;
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
	
	private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int STRING_LENGTH = 10;
    private static final SecureRandom RANDOM = new SecureRandom();
    public static String generateRandomString() {
        StringBuilder sb = new StringBuilder(STRING_LENGTH);
        for (int i = 0; i < STRING_LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }
    
	private boolean checkContent(String uuid) {
		 Session session = factory.getCurrentSession();
	     String sql = "select 1 from HoaDon where maGD = :uuid and trangThai = 1";
	     Query query = session.createQuery(sql);
	     query.setParameter("uuid", uuid);
	     return query.uniqueResult()==null?false:true;
	}
	public static float amount;
	public static String content;
	@RequestMapping(value = "/booking-room")
	public String generateQRCode(HttpServletRequest request,
			@Validated @ModelAttribute("khachHang") KhachHang kh, HttpSession sessionUser,
			BindingResult err, ModelMap model, RedirectAttributes rd) {
        Map<String, Integer> discount = RoomsController.getDiscount();

		CT_PhieuDat ctpd = (CT_PhieuDat) request.getSession().getAttribute("ctPD");
		PhieuDat pd = (PhieuDat)request.getSession().getAttribute("pd");
		
		String username = sessionUser.getAttribute("author").toString();
		Session session = factory.getCurrentSession();
		String hql ="from TaiKhoan where username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		TaiKhoan tk = (TaiKhoan) query.uniqueResult();
		
		if(err.hasErrors()) {
			request.setAttribute("ctPhieuDat", ctpd);
			request.setAttribute("pd", pd);
			request.setAttribute("tk", tk);
			int soNgay = (int)TimeUnit.DAYS.convert(pd.getNgayKT().getTime() - pd.getNgayBD().getTime(), TimeUnit.MILLISECONDS);
			request.setAttribute("soNgay", soNgay);
			request.getSession().setAttribute("ctPD", ctpd);
    		request.getSession().setAttribute("pd", pd);
	        return "user/book-room";
		}
		
		Session session_insert = factory.openSession();
		Transaction t = session_insert.beginTransaction();
		// Kiểm tra xem có tài khoản hay chưa
		hql = "select kh from KhachHang kh join kh.email tk where tk.username = :username";
		query = session_insert.createQuery(hql);
		query.setParameter("username", tk.getUsername());
		List<KhachHang> khList = query.list();
		int discountValue = discount.getOrDefault(ctpd.getHangPhong().getIdHP(), 0);
		float tongTien = ctpd.getHangPhong().getGia() * ctpd.getsLPhong() * (100 - discountValue) / 100;
        Timestamp myDateObj = new Timestamp(System.currentTimeMillis());
        
        amount = tongTien;
		content = generateRandomString();
		
        while (checkContent(content)) {
        	content = generateRandomString();
        }
        System.out.println(content);
        
        String qrImgSrc = "https://img.vietqr.io/image/970422-"+setting.getAccountNumber()+"-compact2.png?amount=" + amount + "&addInfo=" + content + "&accountName=" + setting.getAccountName().replace(" ", "%20");
        model.addAttribute("qrImgSrc", qrImgSrc);
        
        HoaDon hd = new HoaDon(myDateObj, tongTien, pd, content);
		try {
			if (khList.isEmpty()) {
				kh.setEmail(tk);
				session_insert.save(kh);
			}
			session_insert.save(pd);
			session_insert.save(ctpd);
			session_insert.save(hd);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			session_insert.close();
		}
		// Gửi mail
		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        String checkInDate = "";
        String checkOutDate = "";
        try {
            checkInDate = targetFormat.format(originalFormat.parse(pd.getNgayBD().toString()));
            checkOutDate = targetFormat.format(originalFormat.parse(pd.getNgayKT().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String subject = "Thank! Your booking at Sona has been confirmed.";
        String body = "</p>Dear " + kh.getHo() +" "+kh.getTen() + ",</p>"
                + "<p>We sincerely thank you for choosing [Hotel/Resort Name]. We are pleased to inform you that your booking has been successfully confirmed with the following details:</p>"
                + "<p>Customer Name: " + kh.getHo() +" "+kh.getTen() + "</p>"
                + "<p>Booking Number: " + kh.getSdt()+ "</p>"
                + "<p>Room :" + ctpd.getHangPhong().getTenHP() + "</p>"
                + "<p>Check-in Date: "+ checkInDate + "</p>"
                + "<p>Check-out Date: "+ checkOutDate + "</p>"
                + "<p>Total Amount: " + tongTien + "</p>"
                + "<p>We are committed to providing you with an exceptional and memorable stay. If you have any special requests or questions, please feel free to contact us via this email or call our hotline at 0123456789.</p>"
                + "<p>Once again, thank you, and we look forward to welcoming you to Sona.</p>"
                + "<p>Best regards,</p>"
                + "<p>Sona Hotel</p>";

        mailer.send("Sona Support", tk.getUsername(), subject, body);
        rd.addFlashAttribute("message", "Booking Room");
        
        return "user/QRcode";
    }
	
	@RequestMapping(value = "/booking-room-success", method = RequestMethod.POST)
	public String bookRoom(@RequestParam("maGD") String maGD, @RequestParam("amount") String tongTien) {
	    try {
	        // Xử lý logic khi booking room thành công
	        Session session = factory.getCurrentSession();
	        String sql = "update HoaDon set trangThai = 1 where maGD = :maGD and tongTien = :tongTien and trangThai = 0";
	        Query query = session.createQuery(sql);
	        query.setParameter("maGD", maGD);
	        query.setParameter("tongTien",Float.parseFloat(tongTien));
	        System.out.println(maGD + " " + tongTien);
	        query.executeUpdate();
	        System.out.println("Booking room success");
	        return "redirect:/notification/200";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "redirect:/notification/500";
	    }
	}

}
