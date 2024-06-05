package com.webspringmvc.controller.admin;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webspringmvc.entity.CT_KhuyenMai;
import com.webspringmvc.entity.HangPhong;
import com.webspringmvc.entity.KhuyenMai;
import com.webspringmvc.service.IPromotionDetailService;
import com.webspringmvc.service.IPromotionService;
import com.webspringmvc.service.IRoomService;

@Transactional
@Controller
@RequestMapping("/admin")
public class PromotionController {
	@Autowired
	SessionFactory factory;

	@Autowired
	ServletContext context;
	
	@Autowired
	IPromotionService promotionService;

	@RequestMapping("/promotion")
	public String promotionPage(ModelMap model) {
		model.addAttribute("listKM", promotionService.getList());
		return "admin/promotion";
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
		List<KhuyenMai> listKM = promotionService.getList();

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
		if (!promotionService.isNameValid(khuyenMai.gettenKM())) {
			errors.rejectValue("tenKM", "khuyenMai", "Name \"" + khuyenMai.gettenKM()
					+ "\" Must Not Include Special Characters. Only Letters, Numbers, Spaces And Underscores Are Allowed In Names.\"");
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
		
		if (promotionService.insert(khuyenMai) == 1) {
			model.addAttribute("message", "*Insert Successful");
		}
		else {
			model.addAttribute("message", "*Insert Failed");
			errors.rejectValue("maKM", "khuyenMai",
					"Please Check This ID, Maybe ID \"" + khuyenMai.getmaKM() + "\" Already Exists.");
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
		List<KhuyenMai> listKM = promotionService.getList();

//		hql = "FROM KhuyenMai km WHERE km.maKM != :editedID";
//		query = session.createQuery(hql);
//		query.setParameter("editedID", khuyenMai.getmaKM());
		List<KhuyenMai> otherKMs = promotionService.getRemainingRoom(khuyenMai.getmaKM());

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
		if (!promotionService.isNameValid(khuyenMai.gettenKM())) {
			errors.rejectValue("tenKM", "khuyenMai", "Name \"" + khuyenMai.gettenKM()
					+ "\" Must Not Include Special Characters. Only Letters, Numbers, Spaces And Underscores Are Allowed In Names.\"");
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
		
		if (promotionService.update(khuyenMai) == 1) {
			model.addAttribute("message", "*Update Successful");
		} else {
			model.addAttribute("message", "*Update Failed");
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
			
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "*Delete Failed. There Is Promotion Detail In Promotion");
		} finally {
			session.close();
		}
		
		if (promotionService.delete(id) == 1) {
			model.addAttribute("message", "*Delete Successful");
		} else {
			model.addAttribute("message", "*Delete Failed. Maybe There Is Promotion Detail In Promotion");
		}

		return "redirect:/admin/promotion";

	}

	@Autowired
	IRoomService roomService;
	
	@Autowired
	IPromotionDetailService promotionDetailService;
	
	/*-------------------------------- CT_KHUYENMAI --------------------------------*/
	@RequestMapping(value = "/promotion-detail", method = RequestMethod.GET)
	public String pdPage(@RequestParam("id") String maKM, ModelMap model) {
		model.addAttribute("listCTKM", promotionDetailService.getList(maKM));
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

		model.addAttribute("listHP", roomService.getList());
		model.addAttribute("idKM", idKM);
		model.addAttribute("CTKM", CTKM);
		return "admin/insertPD";
	}

	@RequestMapping(value = "/insertPD", method = RequestMethod.POST)
	public String insertPD(@ModelAttribute("CTKM") CT_KhuyenMai CTKM, ModelMap model, BindingResult errors) {
		List<CT_KhuyenMai> listCTKM = promotionDetailService.getList(CTKM.getKhuyenMai().getmaKM());

		List<HangPhong> listHP = roomService.getList();

		if (listCTKM.stream().anyMatch(ctkm -> ctkm.getHangPhong().equals(CTKM.getHangPhong()))) {
			errors.rejectValue("hangPhong", "CTKM", "This Room Already Exists In This Promotion.");
		}

		if (errors.hasErrors()) {
			model.addAttribute("message", "*Please Check These Errors.");
			model.addAttribute("listHP", listHP);
			return "/admin/insertPD";
		}

		
		if (promotionDetailService.insert(CTKM) == 1) {
			model.addAttribute("message", "*Insert Successful");
		}
		else {
			model.addAttribute("message", "*Insert Failed. Maybe This Room Detail Already Exists In This Promotion");
		}
		
		Session session = factory.openSession();
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

		if (promotionDetailService.update(CTKM) == 1) {
			redirectAttributes.addFlashAttribute("message", "*Update Successful");
		}
		else {
			redirectAttributes.addFlashAttribute("message", "*Update Failed");
		}

//		model.addAttribute("idKM", CTKM.getKhuyenMai().getmaKM());
		redirectAttributes.addAttribute("idKM", CTKM.getKhuyenMai().getmaKM());

		return "redirect:/admin/editPD?idCTKM=" + CTKM.getId();
//		 + "&idKM=" + CTKM.getKhuyenMai().getmaKM()

	}
	/*-------------------------- DELETE CT_KHUYENMAI --------------------------*/

	@RequestMapping(value = "/deletePD", method = RequestMethod.GET)
	public String deletePD(@RequestParam("idCTKM") int idCTKM, @RequestParam("idKM") String idKM, ModelMap model) {
		
		if (promotionDetailService.delete(idCTKM) == 1) {
			model.addAttribute("message", "*Delete Successful");
		}
		else {
			model.addAttribute("message", "*Delete Failed");
		}

		return "redirect:/admin/promotion-detail?id=" + idKM;

	}
}
