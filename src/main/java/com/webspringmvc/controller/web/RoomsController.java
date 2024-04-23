package com.webspringmvc.controller.web;

import java.sql.Date;
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
		List<HangPhong> listHPTemp1 = new ArrayList<HangPhong>();
		List<LoaiPhong> listLP = query.list();
		String sLN = request.getParameter("sLN");
		String lP = request.getParameter("lP");
		String dateOut = request.getParameter("dateOut");
		String dateIn = request.getParameter("dateIn");
		model.addAttribute("listLP", listLP);
		model.addAttribute("listSLN", listSLN);
		Map<String, Integer> roomAvai = new HashMap<String, Integer>();

		if (sLN != null && lP != null && dateOut != null && dateIn != null) {
			request.setAttribute("sLN", sLN);
			request.setAttribute("lP", lP);
			request.setAttribute("dateOut", dateOut);
			request.setAttribute("dateIn", dateIn);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH);

			for (HangPhong hp : listHPTemp) {
				if (hp.getSoLuongNguoi() == Integer.parseInt(sLN) && hp.getLoaiPhong().gettenLP().equals(lP)) {
					listHPTemp1.add(hp);
					java.util.Date dateOutTemp = null;
					java.util.Date dateInTemp = null;
					try {
						dateOutTemp = dateFormat.parse(dateOut);
						dateInTemp = dateFormat.parse(dateIn);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					hql = "SELECT CTPD.hangPhong.idHP, SUM(CTPD.sLPhong) as sl " + "FROM CT_PhieuDat CTPD "
							+ "WHERE (( :dateOut >= CTPD.phieuDat.ngayBD AND :dateOut < CTPD.phieuDat.ngayKT) "
							+ "   OR ( :dateIn > CTPD.phieuDat.ngayBD AND :dateIn <= CTPD.phieuDat.ngayKT) "
							+ "   OR :dateOut = CTPD.phieuDat.ngayBD " + "   OR :dateIn = CTPD.phieuDat.ngayKT "
							+ "   OR ( :dateOut < CTPD.phieuDat.ngayBD AND :dateIn > CTPD.phieuDat.ngayKT)) "
							+ " and CTPD.hangPhong.idHP = :idHP " + "GROUP BY CTPD.hangPhong.idHP";
					query = session.createQuery(hql);
					query.setParameter("idHP", hp.getIdHP());
					query.setParameter("dateOut", dateOutTemp);
					query.setParameter("dateIn", dateInTemp);
					List<Object[]> list = query.list();
					int sl = 0;
					for (Object[] objects : list) {
						sl += Integer.parseInt(objects[1].toString());
					}
					roomAvai.put(hp.getIdHP(), hp.getPhong().size() - sl);
				}
			}
			listHPTemp = listHPTemp1;
		}
		request.setAttribute("roomAvai", roomAvai);
		ChangePage.changePage(listHPTemp, page, model, 9);
		return "user/rooms";
	}
	@RequestMapping("/room-detail")
	public String roomDetail() {
		return "user/room-detail";
	}
}
