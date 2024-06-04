package com.webspringmvc.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.webspringmvc.entity.HangPhong;
import com.webspringmvc.entity.KieuPhong;
import com.webspringmvc.entity.LoaiPhong;

@Transactional
@Controller
@RequestMapping("/admin")
public class RoomController {
	@Autowired
	SessionFactory factory;

	@Autowired
	ServletContext context;

	@RequestMapping("/hang-phong")
	public String hangPhongPage(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM HangPhong";
		Query query = session.createQuery(hql);
		List<HangPhong> listHP = query.list();
		model.addAttribute("listHP", listHP);

		return "admin/hang-phong";
	}

	@ModelAttribute("listLP") // listLP
	public List<LoaiPhong> getLP() {
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiPhong";
		Query query = session.createQuery(hql);
		List<LoaiPhong> listLP = query.list();

		return listLP;
	}

	@ModelAttribute("listKP") // listKP
	public List<KieuPhong> getKP() {
		Session session = factory.getCurrentSession();
		String hql = "FROM KieuPhong";
		Query query = session.createQuery(hql);
		List<KieuPhong> listKP = query.list();

		return listKP;
	}

	public static boolean isIdValid(String id) {
		// Biểu thức chính quy để khớp ID hợp lệ (chỉ chứa chữ cái, số và gạch dưới)
		String regex = "[a-zA-Z0-9_ ]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(id);
		return matcher.matches();
	}

	public static String getFileExtension(String filePath) {
		int lastDotIndex = filePath.lastIndexOf('.');
		if (lastDotIndex > 0) {
			return filePath.substring(lastDotIndex + 1);
		}
		return "";
	}

	/*-------------------------- EDIT/UPDATE HANGPHONG --------------------------*/
	@RequestMapping(value = "/editHangPhong", method = RequestMethod.GET)
	public String editHangPhong(@RequestParam("id") String id, ModelMap model) {
		Session session = factory.getCurrentSession();
		HangPhong hangPhong = (HangPhong) session.get(HangPhong.class, id);
		;
		if (hangPhong == null) {
			// Xử lý trường hợp không tìm thấy hàng phòng với ID đã cho
			// Ví dụ: thông báo lỗi, redirect, hoặc hiển thị trang 404
		} else {

			model.addAttribute("hangPhong", hangPhong);
		}
		return "admin/editHangPhong";
	}

	@RequestMapping(value = "/editHangPhong", method = RequestMethod.POST)
	public String editHangPhong(@ModelAttribute("hangPhong") HangPhong hangPhong, ModelMap model,
			BindingResult errors) {
		/* Validate input */
		Session session = factory.getCurrentSession();
		String hql = "FROM HangPhong";
		Query query = session.createQuery(hql);
		List<HangPhong> listHP = query.list();

//		List<HangPhong> otherHPs = listHP.stream().filter(hp -> hp.getIdHP() != hangPhong.getIdHP())
//				.collect(Collectors.toList());
		hql = "FROM HangPhong hp WHERE hp.idHP != :editedID";
		query = session.createQuery(hql);
		query.setParameter("editedID", hangPhong.getIdHP());
		List<HangPhong> otherHPs = query.list();
	
		hangPhong.setTenHP(hangPhong.getTenHP().trim());
		hangPhong.setMoTa(hangPhong.getMoTa().trim());

		/*---------------- check name ----------------*/
		if (otherHPs.stream().anyMatch(existingHP -> existingHP.getTenHP().equals(hangPhong.getTenHP()))) {
			errors.rejectValue("tenHP", "HangPhong", "Name \"" + hangPhong.getTenHP() + "\" Already Exists.");
		}
		if (!isIdValid(hangPhong.getTenHP())) {
			errors.rejectValue("tenHP", "HangPhong", "Name \"" + hangPhong.getTenHP()
					+ "\" Must Not Include Special Characters. Only Letters, Numbers, and underscores are allowed in Names.\"");
		}

		if (errors.hasErrors()) {
			model.addAttribute("message", "*Please Check These Errors.");
			return "/admin/editHangPhong";
		}
		session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(hangPhong);
			t.commit();
			model.addAttribute("message", "*Update successful");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "*Update failed");
		} finally {
			session.close();
		}

		return "admin/editHangPhong";

	}

	/*-------------------------- EDIT/UPDATE HANGPHONG PHOTO --------------------------*/
	@RequestMapping(value = "/editRoomPhoto", method = RequestMethod.GET)
	public String editRoomPhoto(@RequestParam("id") String id, ModelMap model) {
		Session session = factory.getCurrentSession();
		HangPhong hangPhong = (HangPhong) session.get(HangPhong.class, id);
		if (hangPhong == null) {
			// Xử lý trường hợp không tìm thấy hàng phòng với ID đã cho
			// Ví dụ: thông báo lỗi, redirect, hoặc hiển thị trang 404
		} else {

			model.addAttribute("hangPhong", hangPhong);
		}
		return "admin/editRoomPhoto";
	}

	@RequestMapping(value = "/editRoomPhoto", method = RequestMethod.POST)
	public String editRoomPhoto(@ModelAttribute("hangPhong") HangPhong hangPhong, ModelMap model,
			@RequestParam("photo") MultipartFile photo, BindingResult errors) {
		/*---------------- check photo ----------------*/
		String extension = getFileExtension(photo.getOriginalFilename());
		if (!extension.equalsIgnoreCase("jpg") && !extension.equalsIgnoreCase("jpeg")
				&& !extension.equalsIgnoreCase("png") && !extension.equalsIgnoreCase("gif")
				&& !extension.equalsIgnoreCase("bmp")) {
			errors.rejectValue("anh", "HangPhong", "Choose a file with the extension is jpg, jpeg, png, gif or bmp.");
		}

		if (errors.hasErrors()) {
			model.addAttribute("message", "*Please Check These Errors.");
			return "/admin/editRoomPhoto";
		}

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		/* get the path of new photo */
		String fileName = context.getRealPath("/template/admin/assets/img/" + photo.getOriginalFilename());
		String photoPath = "/template/admin/assets/img/" + photo.getOriginalFilename();

		/* get the path of old photo to delete */
		String existingPhotoPath = hangPhong.getAnh();

		try {
			if (existingPhotoPath != null && !existingPhotoPath.isEmpty()) {
				String existingFullPath = context.getRealPath(existingPhotoPath);
				File existingFile = new File(existingFullPath);
				/*
				 * File.getAbsoluteFile() returns a File object representing the specified path,
				 * but it doesn't guarantee that the file actually exists. It simply creates a
				 * File object based on the provided path.
				 */
				if (existingFile.exists()) {
					existingFile.delete();
				}
			}

			photo.transferTo(new File(fileName));
			hangPhong.setAnh(photoPath);
			session.update(hangPhong);
			t.commit();
			model.addAttribute("message", "*Update successful");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "*Update failed.");
		} finally {
			session.close();
		}

		return "admin/editRoomPhoto";

	}

	/*-------------------------- INSERT HANGPHONG --------------------------*/

	@RequestMapping(value = "/insertHangPhong", method = RequestMethod.GET)
	public String insertHangPhong(ModelMap model) {
		model.addAttribute("hangPhong", new HangPhong());
		return "admin/insertHangPhong";
	}

	@RequestMapping(value = "/insertHangPhong", method = RequestMethod.POST)
	public String insertHangPhong(@ModelAttribute("hangPhong") HangPhong hangPhong,
			@RequestParam("photo") MultipartFile photo, ModelMap model, BindingResult errors) {

		/* Validate input */
		Session session = factory.getCurrentSession();
		String hql = "FROM HangPhong";
		Query query = session.createQuery(hql);
		List<HangPhong> listHP = query.list();

		hangPhong.setIdHP(hangPhong.getIdHP().trim());
		hangPhong.setTenHP(hangPhong.getTenHP().trim());
		hangPhong.setMoTa(hangPhong.getMoTa().trim());

		/*---------------- check id ----------------*/
		if (listHP.stream().anyMatch(existingHP -> existingHP.getIdHP().equals(hangPhong.getIdHP()))) {
			errors.rejectValue("idHP", "HangPhong", "ID \"" + hangPhong.getIdHP() + "\" Already Exists.");
		}
		if (!isIdValid(hangPhong.getIdHP())) {
			errors.rejectValue("idHP", "HangPhong", "ID \"" + hangPhong.getIdHP()
					+ "\" Must Not Include Special Characters. Only Letters, Numbers, And Underscores Are Allowed In IDs.\"");
		}
		/*---------------- check name ----------------*/
		if (listHP.stream().anyMatch(existingHP -> existingHP.getTenHP().equals(hangPhong.getTenHP()))) {
			errors.rejectValue("tenHP", "HangPhong", "Name \"" + hangPhong.getTenHP() + "\" Already Exists.");
		}
		if (!isIdValid(hangPhong.getTenHP())) {
			errors.rejectValue("tenHP", "HangPhong", "Name \"" + hangPhong.getTenHP()
					+ "\" Must Not Include Special Characters. Only Letters, Numbers, and underscores are allowed in Names.\"");
		}
		/*---------------- check photo ----------------*/
		String extension = getFileExtension(photo.getOriginalFilename());
		if (!extension.equalsIgnoreCase("jpg") && !extension.equalsIgnoreCase("jpeg")
				&& !extension.equalsIgnoreCase("png") && !extension.equalsIgnoreCase("gif")
				&& !extension.equalsIgnoreCase("bmp")) {
			errors.rejectValue("anh", "HangPhong", "Choose a file with the extension is jpg, jpeg, png, gif or bmp.");
		}

		if (errors.hasErrors()) {
			model.addAttribute("message", "*Please Check These Errors.");
			return "/admin/insertHangPhong";
		}

		String photoPath = "";

		try {

//				String fileName = context.getRealPath("/") + "\\template\\admin\\assets\\img\\"
//						+ photo.getOriginalFilename();
			String fileName = context.getRealPath("/template/admin/assets/img/" + photo.getOriginalFilename());
//				String fileName = context.getContextPath() + "/template/admin/assets/img/"
//						+ photo.getOriginalFilename();
			System.out.println(fileName);
			photoPath = "/template/admin/assets/img/" + photo.getOriginalFilename();
			photo.transferTo(new File(fileName));

		} catch (IOException e) {
			model.addAttribute("message", "*Error To Save File");

		}

		session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			hangPhong.setAnh(photoPath);
			session.save(hangPhong);
			t.commit();
			model.addAttribute("message", "*Insert Successful");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "*Insert Failed");
			errors.rejectValue("idHP", "HangPhong", "ID \"" + hangPhong.getIdHP() + "\" Already Exists.");
		} finally {
			session.close();
		}

		return "/admin/insertHangPhong";

	}

	/*-------------------------- DELETE HANGPHONG --------------------------*/

	@RequestMapping(value = "/deleteHangPhong", method = RequestMethod.GET)
	public String deleteHangPhong(@RequestParam("id") String id, ModelMap model) {

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			HangPhong hangPhong = (HangPhong) session.get(HangPhong.class, id);
			session.delete(hangPhong);
			t.commit();
			model.addAttribute("message", "*Delete Successful");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "*Delete Failed. There Is Room Detail In Room");
		} finally {
			session.close();
		}

		return "redirect:/admin/hang-phong";

	}

	@RequestMapping("/phong")
	public String phongPage() {
		return "admin/phong";
	}
}
