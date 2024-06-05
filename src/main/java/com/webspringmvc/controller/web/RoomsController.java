package com.webspringmvc.controller.web;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.webspringmvc.entity.HangPhong;
import com.webspringmvc.entity.LoaiPhong;
import com.webspringmvc.entity.Phong;
import com.webspringmvc.entity.TaiKhoan;
import com.webspringmvc.page.ChangePage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.webspringmvc.service.IPhongService;

@Transactional
@Controller
@RequestMapping("/rooms")
public class RoomsController {
	@Autowired
	private IPhongService phongService;
	@Autowired
	SessionFactory factory;

	@RequestMapping("/index")
	public String index(@RequestParam(value = "page", defaultValue = "1") int page, ModelMap model,
			HttpServletRequest request) {
		Session session = factory.getCurrentSession();
		String hql = "From HangPhong";
		Query query = session.createQuery(hql);
		List<HangPhong> listHPTemp = query.list();
		hql = "select distinct soLuongNguoi from HangPhong";
		query = session.createQuery(hql);
		List<Integer> listSLN = query.list();
		hql = "From LoaiPhong";
		query = session.createQuery(hql);
		List<HangPhong> listHPTempLookFor = new ArrayList<HangPhong>();
		List<LoaiPhong> listLP = query.list();
		String sLN = request.getParameter("sLN");
		String lP = request.getParameter("lP");
		String dateOut = request.getParameter("dateOut");
		String dateIn = request.getParameter("dateIn");
		model.addAttribute("listLP", listLP);
		model.addAttribute("listSLN", listSLN);
		Map<String, Integer> roomAvai = new HashMap<String, Integer>();
		java.util.Date dateOutTemp = null;
		java.util.Date dateInTemp = null;
		if (sLN != null && lP != null && dateOut != null && dateIn != null) {
			request.setAttribute("sLN", sLN);
			request.setAttribute("lP", lP);
			request.setAttribute("dateOut", dateOut);
			request.setAttribute("dateIn", dateIn);

			for (HangPhong hp : listHPTemp) {
				if (hp.getSoLuongNguoi() == Integer.parseInt(sLN) && hp.getLoaiPhong().gettenLP().equals(lP)) {
					listHPTempLookFor.add(hp);
					dateOutTemp = ChangePage.formatDate(dateOut);
					dateInTemp = ChangePage.formatDate(dateIn);
					// số lượng phòng đã đặt
					hql = "SELECT CTPD.hangPhong.idHP, SUM(CTPD.sLPhong) as sl " + "FROM CT_PhieuDat CTPD "
							+ "WHERE (( :dateOut > CTPD.phieuDat.ngayBD AND :dateOut <= CTPD.phieuDat.ngayKT) "
							+ "   OR ( :dateIn >= CTPD.phieuDat.ngayBD AND :dateIn < CTPD.phieuDat.ngayKT) "
							+ "   OR :dateIn = CTPD.phieuDat.ngayBD " + "   OR :dateOut = CTPD.phieuDat.ngayKT "
							+ "   OR ( :dateIn < CTPD.phieuDat.ngayBD AND :dateOut > CTPD.phieuDat.ngayKT)) "
							+ " and CTPD.hangPhong.idHP = :idHP " + "GROUP BY CTPD.hangPhong.idHP";
					query = session.createQuery(hql);
					query.setParameter("idHP", hp.getIdHP());
					query.setParameter("dateOut", dateOutTemp);
					query.setParameter("dateIn", dateInTemp);
					List<Object[]> list = query.list();
					// số lượng phòng đã đặt
					int sl_book = 0;
					for (Object[] objects : list) {
						sl_book += Integer.parseInt(objects[1].toString());
					}
					hql = "SELECT p.hangPhong.idHP, COUNT(p.maPhong)"
							+ "FROM Phong p "
							+ "WHERE p.hangPhong.idHP = :idHP AND p.trangThaiPhong.maTTP = 'TT1' "
							+ "GROUP BY p.hangPhong.idHP ";
					query = session.createQuery(hql);
					query.setParameter("idHP", hp.getIdHP());
					list = query.list();
					// số lượng phòng
					int numberOfRoom = 0;
					for (Object[] objects : list) {
						numberOfRoom += Integer.parseInt(objects[1].toString());
					}
					roomAvai.put(hp.getIdHP(), numberOfRoom - sl_book);
					
				}
			}
			listHPTemp = listHPTempLookFor;
		}
		hql = "select c.hangPhong.idHP, c.phanTramGiam "
				+ "from KhuyenMai km "
				+ "join km.ctKM c "
				+ "where km.ngayBD <= :dateIn and km.ngayKT >= :dateOut";
		query = session.createQuery(hql);
		query.setParameter("dateOut", dateOutTemp);
		query.setParameter("dateIn", dateInTemp);
		List<Object[]> list = query.list();
		Map<String, Integer> discount = new HashMap<String, Integer>();
		for (Object[] objects : list) {
			discount.put(objects[0].toString(), Integer.parseInt(objects[1].toString()));
			System.out.println(objects[0].toString() + " " + objects[1].toString());
		}
		request.getSession().setAttribute("discount", discount);
		request.getSession().setAttribute("roomAvai", roomAvai);
		ChangePage.changePage(listHPTemp, page, model, 9);
		return "user/rooms";
	}
	@RequestMapping("/room-detail")
	public String roomDetail(@RequestParam("id") String idHP,
			@RequestParam("dateOut") String dateOut,
			@RequestParam("dateIn") String dateIn,
			HttpServletRequest request) {
		Map<String, Integer> roomAvai = new HashMap<String, Integer>();
		roomAvai = (Map<String, Integer>) request.getSession().getAttribute("roomAvai");
		Map<String, Integer> discount = new HashMap<String, Integer>();
		discount = (Map<String, Integer>) request.getSession().getAttribute("discount");
		request.getSession().setAttribute("discount", discount.get(idHP)==null? 0: discount.get(idHP));
		String hql = "From HangPhong where idHP = :idHP";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("idHP", idHP);
		HangPhong hp = (HangPhong) query.uniqueResult();
		request.setAttribute("hp", hp);
		hql = "SELECT SUM(CTPD.sLPhong) as sl " + "FROM CT_PhieuDat CTPD "
				+ "WHERE (( :dateOut >= CTPD.phieuDat.ngayBD AND :dateOut < CTPD.phieuDat.ngayKT) "
				+ "   OR ( :dateIn > CTPD.phieuDat.ngayBD AND :dateIn <= CTPD.phieuDat.ngayKT) "
				+ "   OR :dateOut = CTPD.phieuDat.ngayBD " + "   OR :dateIn = CTPD.phieuDat.ngayKT "
				+ "   OR ( :dateOut < CTPD.phieuDat.ngayBD AND :dateIn > CTPD.phieuDat.ngayKT)) "
				+ " and CTPD.hangPhong.idHP = :idHP ";
		java.util.Date dateOutTemp = ChangePage.formatDate(dateOut);
		java.util.Date dateInTemp = ChangePage.formatDate(dateIn);
		query = session.createQuery(hql);
		query.setParameter("idHP", idHP);
		query.setParameter("dateOut", dateOutTemp);
		query.setParameter("dateIn", dateInTemp);
		
		request.setAttribute("slPhong", roomAvai.get(idHP));
		SimpleDateFormat originalFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH);
        try {
        	String ngayBDStr = dateInTemp.toString();
            java.util.Date date = originalFormat.parse(ngayBDStr);
            ngayBDStr = targetFormat.format(date);
            Timestamp ngayBD = new Timestamp(targetFormat.parse(ngayBDStr).getTime());
            
            String ngayKTStr = dateOutTemp.toString();
            date = originalFormat.parse(ngayKTStr);
            ngayKTStr = targetFormat.format(date);
            Timestamp ngayKT = new Timestamp(targetFormat.parse(ngayKTStr).getTime());
            
            request.setAttribute("dateIn", ngayBD);
    		request.setAttribute("dateOut", ngayKT);
        }
        catch (ParseException  e) {
        	   e.printStackTrace();
        }
		
		return "user/room-detail";
	}
}
