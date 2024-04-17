package com.webspringmvc.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class RoomController {
	@RequestMapping("/hang-phong")
	public String hangPhongPage() {
		return "admin/hang-phong";
	}
	
	@RequestMapping("/phong")
	public String phongPage() {
		return "admin/phong";
	}
}
