package com.webspringmvc.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rooms")
public class RoomsController {
	@RequestMapping("/index")
	public String index() {
		return "user/rooms";
	}
	
	@RequestMapping("/room-detail")
	public String roomDetail() {
		return "user/room-detail";
	}
}
