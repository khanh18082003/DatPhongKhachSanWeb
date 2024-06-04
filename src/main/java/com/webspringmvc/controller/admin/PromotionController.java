package com.webspringmvc.controller.admin;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webspringmvc.entity.CT_KhuyenMai;
import com.webspringmvc.entity.HangPhong;
import com.webspringmvc.entity.KhuyenMai;

@Transactional
@Controller
@RequestMapping("/admin")
public class PromotionController {
	@Autowired
	SessionFactory factory;

	@Autowired
	ServletContext context;

	@RequestMapping("/promotion")
	public String promotionPage(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM KhuyenMai";
		Query query = session.createQuery(hql);
		List<KhuyenMai> listKM = query.list();
		model.addAttribute("listKM", listKM);

		return "admin/promotion";
	}

	public static boolean isIdValid(String id) {
		// Biểu thức chính quy để khớp ID hợp lệ (chỉ chứa chữ cái, số và gạch dưới)
		String regex = "[a-zA-Z0-9_ ]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(id);
		return matcher.matches();
	}

	/*-------------------------- INSERT KHUYENMAI --------------------------*/

	@RequestMapping(value = "/insertPromo", method = RequestMethod.GET)
	public String insertPromo(ModelMap model) {
		KhuyenMai khuyenMai = new KhuyenMai();
		String str = "2018-09-01 09:01:15";
		Timestamp timestamp = Timestamp.valueOf(str);
		khuyenMai.setngayBD(timestamp);
		khuyenMai.setngayKT(timestamp);
		model.addAttribute("khuyenMai", khuyenMai);
		return "admin/insertPromo";
	}

	@RequestMapping(value = "/insertPromo", method = RequestMethod.POST)
	public String insertPromo(@ModelAttribute("khuyenMai") KhuyenMai khuyenMai, ModelMap model,
			HttpServletRequest request, BindingResult errors) {

		/* Validate input */
		Session session = factory.getCurrentSession();
		String hql = "FROM KhuyenMai";
		Query query = session.createQuery(hql);
		List<KhuyenMai> listKM = query.list();

		khuyenMai.setmaKM(khuyenMai.getmaKM().trim());
		khuyenMai.settenKM(khuyenMai.gettenKM().trim());

		String ngayBD = request.getParameter("ngayBDStr").replace('T', ' ') + ":00";
		String ngayKT = request.getParameter("ngayKTStr").replace('T', ' ') + ":00";

		// Set Date fields
		khuyenMai.setngayBD(Timestamp.valueOf(ngayBD));
		khuyenMai.setngayKT(Timestamp.valueOf(ngayKT));

		/*---------------- check id ----------------
		if (listKM.stream().anyMatch(existingHP -> existingHP.getIdHP().equals(khuyenMai.getIdHP()))) {
			errors.rejectValue("idHP", "khuyenMai", "ID \"" + khuyenMai.getIdHP() + "\" Already Exists.");
		}
		if (!isIdValid(khuyenMai.getIdHP())) {
			errors.rejectValue("idHP", "khuyenMai", "ID \"" + khuyenMai.getIdHP()
					+ "\" Must Not Include Special Characters. Only Letters, Numbers, and underscores are allowed in IDs.\"");
		}*/
		/*---------------- check name ----------------*/
		if (listKM.stream().anyMatch(existingHP -> existingHP.gettenKM().equals(khuyenMai.gettenKM()))) {
			errors.rejectValue("tenKM", "khuyenMai", "Name \"" + khuyenMai.gettenKM() + "\" Already Exists.");
		}
		if (!isIdValid(khuyenMai.gettenKM())) {
			errors.rejectValue("tenKM", "khuyenMai", "Name \"" + khuyenMai.gettenKM()
					+ "\" Must Not Include Special Characters. Only Letters, Numbers, And Underscores Are Allowed In Names.\"");
		}
		/*---------------- check time ----------------*/
		if (khuyenMai.getngayKT().compareTo(khuyenMai.getngayBD()) < 0
				|| khuyenMai.getngayKT().compareTo(khuyenMai.getngayBD()) == 0) {
			errors.rejectValue("ngayKT", "khuyenMai", "End Must Be After Begin.");
		}

		for (KhuyenMai km : listKM) {
			if (km.getngayBD().equals(khuyenMai.getngayBD()) && km.getngayKT().equals(khuyenMai.getngayKT())) {
				errors.rejectValue("ngayKT", "khuyenMai", "This Period Coincides With The Promotion \"" + km.gettenKM()
						+ "\"" + ", ID is \"" + km.getmaKM() + "\".");
			}
		}

		if (errors.hasErrors()) {
			model.addAttribute("message", "*Please Check These Errors.");
			return "/admin/insertPromo";
		}

		session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(khuyenMai);
			t.commit();
			model.addAttribute("message", "*Insert Successful");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "*Insert Failed");
			errors.rejectValue("maKM", "khuyenMai",
					"ID \"" + khuyenMai.getmaKM() + "\" Already Exists." + e.getLocalizedMessage());
		} finally {
			session.close();
		}

		return "/admin/insertPromo";

	}

	/*-------------------------- EDIT/UPDATE KHUYENMAI --------------------------*/
	@RequestMapping(value = "/editPromo", method = RequestMethod.GET)
	public String editPromo(@RequestParam("id") String id, ModelMap model) {
		Session session = factory.getCurrentSession();
		KhuyenMai khuyenMai = (KhuyenMai) session.get(KhuyenMai.class, id);

		if (khuyenMai == null) {
			// Xử lý trường hợp không tìm thấy hàng phòng với ID đã cho
			// Ví dụ: thông báo lỗi, redirect, hoặc hiển thị trang 404
		} else {

			model.addAttribute("khuyenMai", khuyenMai);
		}
		return "admin/editPromo";
	}

	@RequestMapping(value = "/editPromo", method = RequestMethod.POST)
	public String editPromo(@ModelAttribute("khuyenMai") KhuyenMai khuyenMai, ModelMap model,
			HttpServletRequest request, BindingResult errors) {
		/* Validate input */
		Session session = factory.getCurrentSession();
		String hql = "FROM KhuyenMai";
		Query query = session.createQuery(hql);
		List<KhuyenMai> listKM = query.list();

		hql = "FROM KhuyenMai km WHERE km.maKM != :editedID";
		query = session.createQuery(hql);
		query.setParameter("editedID", khuyenMai.getmaKM());
		List<KhuyenMai> otherKMs = query.list();

		khuyenMai.setmaKM(khuyenMai.getmaKM().trim());
		khuyenMai.settenKM(khuyenMai.gettenKM().trim());

		String ngayBD = request.getParameter("ngayBDStr").replace('T', ' ') + ":00";
		String ngayKT = request.getParameter("ngayKTStr").replace('T', ' ') + ":00";

		// Set Date fields
		khuyenMai.setngayBD(Timestamp.valueOf(ngayBD));
		khuyenMai.setngayKT(Timestamp.valueOf(ngayKT));

		/*---------------- check name ----------------*/
		if (otherKMs.stream().anyMatch(existingKM -> existingKM.gettenKM().equals(khuyenMai.gettenKM()))) {
			errors.rejectValue("tenKM", "khuyenMai", "Name \"" + khuyenMai.gettenKM() + "\" Already Exists.");
		}
		if (!isIdValid(khuyenMai.gettenKM())) {
			errors.rejectValue("tenKM", "khuyenMai", "Name \"" + khuyenMai.gettenKM()
					+ "\" Must Not Include Special Characters. Only Letters, Numbers, And Underscores Are Allowed In Names.\"");
		}
		/*---------------- check time ----------------*/
		if (khuyenMai.getngayKT().compareTo(khuyenMai.getngayBD()) < 0
				|| khuyenMai.getngayKT().compareTo(khuyenMai.getngayBD()) == 0) {
			errors.rejectValue("ngayKT", "khuyenMai", "End Must Be After Begin.");
		}

		for (KhuyenMai km : otherKMs) {
			if (km.getngayBD().equals(khuyenMai.getngayBD()) && km.getngayKT().equals(khuyenMai.getngayKT())) {
				errors.rejectValue("ngayKT", "khuyenMai", "This Period Coincides With The Promotion \"" + km.gettenKM()
						+ "\"" + ", ID is \"" + km.getmaKM() + "\".");
			}
		}

		if (errors.hasErrors()) {
			model.addAttribute("message", "*Please Check These Errors.");
			return "/admin/editPromo";
		}

		session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.update(khuyenMai);
			t.commit();
			model.addAttribute("message", "*Update Successful");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "*Update Failed");
		} finally {
			session.close();
		}

		return "/admin/editPromo";

	}
	/*-------------------------- DELETE KHUYENMAI --------------------------*/

	@RequestMapping(value = "/deletePromo", method = RequestMethod.GET)
	public String deletePromo(@RequestParam("id") String id, ModelMap model) {

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			KhuyenMai khuyenMai = (KhuyenMai) session.get(KhuyenMai.class, id);
			session.delete(khuyenMai);
			t.commit();
			model.addAttribute("message", "*Delete Successful");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "*Delete Failed. There Is Promotion Detail In Promotion");
		} finally {
			session.close();
		}

		return "redirect:/admin/promotion";

	}

	/*-------------------------------- CT_KHUYENMAI --------------------------------*/
	@RequestMapping(value = "/promotion-detail", method = RequestMethod.GET)
	public String pdPage(@RequestParam("id") String maKM, ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CT_KhuyenMai WHERE maKM = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", maKM);
		List<CT_KhuyenMai> listCTKM = query.list();

		model.addAttribute("listCTKM", listCTKM);
		model.addAttribute("maKM", maKM);

		return "admin/promotion-detail";
	}

	/*-------------------------- INSERT CT_KHUYENMAI --------------------------*/

	@RequestMapping(value = "/insertPD", method = RequestMethod.GET)
	public String insertPD(@RequestParam("idKM") String idKM, ModelMap model) {
		CT_KhuyenMai CTKM = new CT_KhuyenMai();
		Session session = factory.getCurrentSession();
		KhuyenMai khuyenMai = (KhuyenMai) session.get(KhuyenMai.class, idKM);
		CTKM.setKhuyenMai(khuyenMai);

		String hql = "FROM HangPhong";
		Query query = session.createQuery(hql);
		List<HangPhong> listHP = query.list();

		System.out.println(khuyenMai.getmaKM() + " " + khuyenMai.gettenKM());

		model.addAttribute("listHP", listHP);
		model.addAttribute("idKM", idKM);
		model.addAttribute("CTKM", CTKM);
		return "admin/insertPD";
	}

	@RequestMapping(value = "/insertPD", method = RequestMethod.POST)
	public String insertPD(@ModelAttribute("CTKM") CT_KhuyenMai CTKM, ModelMap model, BindingResult errors) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CT_KhuyenMai WHERE khuyenMai.maKM = :makm";
		Query query = session.createQuery(hql);
		query.setParameter("makm", CTKM.getKhuyenMai().getmaKM());
		List<CT_KhuyenMai> listCTKM = query.list();

		hql = "FROM HangPhong";
		query = session.createQuery(hql);
		List<HangPhong> listHP = query.list();

		if (listCTKM.stream().anyMatch(ctkm -> ctkm.getHangPhong().equals(CTKM.getHangPhong()))) {
			errors.rejectValue("hangPhong", "CTKM", "This Room Already Exists In This Promotion.");
		}

		if (errors.hasErrors()) {
			model.addAttribute("message", "*Please Check These Errors.");
			model.addAttribute("listHP", listHP);
			return "/admin/insertPD";
		}

		session = factory.openSession();
		Transaction t = session.beginTransaction();
		System.out.println(CTKM.getKhuyenMai().getmaKM() + " " + CTKM.getKhuyenMai().gettenKM());

		try {
			session.save(CTKM);
			t.commit();
			model.addAttribute("message", "*Insert Successful");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "*Insert Failed. Maybe This Room Detail Already Exists In This Promotion");
		} finally {
			session.close();
		}
		session = factory.openSession();
		KhuyenMai khuyenMai = (KhuyenMai) session.get(KhuyenMai.class, CTKM.getKhuyenMai().getmaKM());
		CT_KhuyenMai ct_KhuyenMai = new CT_KhuyenMai();
		ct_KhuyenMai.setKhuyenMai(khuyenMai);
		model.addAttribute("CTKM", ct_KhuyenMai);
		model.addAttribute("idKM", CTKM.getKhuyenMai().getmaKM());
		model.addAttribute("listHP", listHP);

		return "/admin/insertPD";

	}

	/*-------------------------- EDIT/UPDATE CT_KHUYENMAI --------------------------*/
	@RequestMapping(value = "/editPD", method = RequestMethod.GET)
	public String editPD(@RequestParam("idCTKM") int id, @RequestParam("idKM") String idKM, ModelMap model) {
		Session session = factory.getCurrentSession();
		CT_KhuyenMai CTKM = (CT_KhuyenMai) session.get(CT_KhuyenMai.class, id);
		if (CTKM == null) {
			// Xử lý trường hợp không tìm thấy hàng phòng với ID đã cho
			// Ví dụ: thông báo lỗi, redirect, hoặc hiển thị trang 404
		} else {

			model.addAttribute("CTKM", CTKM);
		}
		model.addAttribute("idKM", idKM);
		return "admin/editPD";
	}

	@RequestMapping(value = "/editPD", method = RequestMethod.POST)
	public String editPD(@ModelAttribute("CTKM") CT_KhuyenMai CTKM, ModelMap model,
			RedirectAttributes redirectAttributes, BindingResult errors) {

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.update(CTKM);
			t.commit();
//			model.addAttribute("message", "*Update Successful");
			redirectAttributes.addFlashAttribute("message", "*Update Successful");
		} catch (Exception e) {
			t.rollback();
//			model.addAttribute("message", "*Update Failed");
			redirectAttributes.addFlashAttribute("message", "*Update Failed");
		} finally {
			session.close();
		}

//		model.addAttribute("idKM", CTKM.getKhuyenMai().getmaKM());
		redirectAttributes.addAttribute("idKM", CTKM.getKhuyenMai().getmaKM());

		return "redirect:/admin/editPD?idCTKM=" + CTKM.getId();
//		 + "&idKM=" + CTKM.getKhuyenMai().getmaKM()

	}
	/*-------------------------- DELETE CT_KHUYENMAI --------------------------*/

	@RequestMapping(value = "/deletePD", method = RequestMethod.GET)
	public String deletePD(@RequestParam("idCTKM") int idCTKM, @RequestParam("idKM") String idKM, ModelMap model) {

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			CT_KhuyenMai CTKM = (CT_KhuyenMai) session.get(CT_KhuyenMai.class, idCTKM);
			session.delete(CTKM);
			t.commit();
			model.addAttribute("message", "*Delete Successful");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "*Delete Failed. " + e.getCause());
		} finally {
			session.close();
		}

		return "redirect:/admin/promotion-detail?id=" + idKM;

	}
}
