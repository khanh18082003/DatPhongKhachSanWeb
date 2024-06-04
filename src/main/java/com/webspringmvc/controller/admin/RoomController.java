package com.webspringmvc.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

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
import org.springframework.web.multipart.MultipartFile;

import com.webspringmvc.entity.HangPhong;
import com.webspringmvc.entity.KieuPhong;
import com.webspringmvc.entity.LoaiPhong;
import com.webspringmvc.service.IRoomService;
@Transactional
@Controller
@RequestMapping("/admin")
public class RoomController {
	@Autowired
	SessionFactory factory;

	@Autowired
	ServletContext context;

	@Autowired
	IRoomService roomService;

	@RequestMapping("/hang-phong")
	public String hangPhongPage(ModelMap model) {
		model.addAttribute("listHP", roomService.getList());
		return "admin/hang-phong";
	}

	@ModelAttribute("listLP") // listLP
	public List<LoaiPhong> getLP() {
		return roomService.getLP();
	}

	@ModelAttribute("listKP") // listKP
	public List<KieuPhong> getKP() {
		return roomService.getKP();
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
//		
		List<HangPhong> listHP = roomService.getList();

//		List<HangPhong> otherHPs = listHP.stream().filter(hp -> hp.getIdHP() != hangPhong.getIdHP())
//				.collect(Collectors.toList());

		List<HangPhong> otherHPs = roomService.getRemainingRoom(hangPhong.getIdHP());

		hangPhong.setTenHP(hangPhong.getTenHP().trim());
		hangPhong.setMoTa(hangPhong.getMoTa().trim());

		/*---------------- check name ----------------*/
		if (otherHPs.stream().anyMatch(existingHP -> existingHP.getTenHP().equals(hangPhong.getTenHP()))) {
			errors.rejectValue("tenHP", "HangPhong", "Name \"" + hangPhong.getTenHP() + "\" Already Exists.");
		}
		if (!roomService.isNameValid(hangPhong.getTenHP())) {
			errors.rejectValue("tenHP", "HangPhong", "Name \"" + hangPhong.getTenHP()
					+ "\" Must Not Include Special Characters. Only Letters, Numbers, Spaces And Underscores Are Allowed In Names.\"");
		}

		if (errors.hasErrors()) {
			model.addAttribute("message", "*Please Check These Errors.");
			return "/admin/editHangPhong";
		}

		if (roomService.update(hangPhong) == 1) {
			model.addAttribute("message", "*Update successful");
		} else {
			model.addAttribute("message", "*Update failed");
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
		String extension = roomService.getFileExtension(photo.getOriginalFilename());
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

		try {
			photo.transferTo(new File(fileName));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hangPhong.setAnh(photoPath);
		if (roomService.update(hangPhong) == 1) {
			model.addAttribute("message", "*Update successful");
		} else {
			model.addAttribute("message", "*Update failed.");
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
		List<HangPhong> listHP = roomService.getList();

		hangPhong.setIdHP(hangPhong.getIdHP().trim());
		hangPhong.setTenHP(hangPhong.getTenHP().trim());
		hangPhong.setMoTa(hangPhong.getMoTa().trim());

		/*---------------- check id ----------------*/
		if (listHP.stream().anyMatch(existingHP -> existingHP.getIdHP().equals(hangPhong.getIdHP()))) {
			errors.rejectValue("idHP", "HangPhong", "ID \"" + hangPhong.getIdHP() + "\" Already Exists.");
		}
		if (!roomService.isIdValid(hangPhong.getIdHP())) {
			errors.rejectValue("idHP", "HangPhong", "ID \"" + hangPhong.getIdHP()
					+ "\" Must Not Include Special Characters. Only Letters, Numbers, And Underscores Are Allowed In IDs.\"");
		}
		/*---------------- check name ----------------*/
		if (listHP.stream().anyMatch(existingHP -> existingHP.getTenHP().equals(hangPhong.getTenHP()))) {
			errors.rejectValue("tenHP", "HangPhong", "Name \"" + hangPhong.getTenHP() + "\" Already Exists.");
		}
		if (!roomService.isNameValid(hangPhong.getTenHP())) {
			errors.rejectValue("tenHP", "HangPhong", "Name \"" + hangPhong.getTenHP()
					+ "\" Must Not Include Special Characters. Only Letters, Numbers, Spaces And Underscores Are Allowed In Names.\"");
		}
		/*---------------- check photo ----------------*/
		String extension = roomService.getFileExtension(photo.getOriginalFilename());
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
			photoPath = "/template/admin/assets/img/" + photo.getOriginalFilename();
			photo.transferTo(new File(fileName));

		} catch (IOException e) {
			model.addAttribute("message", "*Error To Save File");

		}

		hangPhong.setAnh(photoPath);
		if (roomService.insert(hangPhong) == 1) {
			model.addAttribute("message", "*Insert Successful");
		} else {
			model.addAttribute("message", "*Insert Failed");
			errors.rejectValue("idHP", "HangPhong", "Please Check This ID, Maybe ID \"" + hangPhong.getIdHP() + "\" Already Exists.");
		}
		return "/admin/insertHangPhong";

	}

	/*-------------------------- DELETE HANGPHONG --------------------------*/

	@RequestMapping(value = "/deleteHangPhong", method = RequestMethod.GET)
	public String deleteHangPhong(@RequestParam("id") String id, ModelMap model) {
		if (roomService.delete(id) == 1) {
			model.addAttribute("message", "*Delete Successful");
		} else {
			model.addAttribute("message", "*Delete Failed");
		}
		return "redirect:/admin/hang-phong";
	}

	@RequestMapping("/phong")
	public String phongPage() {
		return "admin/phong";
	}
}
