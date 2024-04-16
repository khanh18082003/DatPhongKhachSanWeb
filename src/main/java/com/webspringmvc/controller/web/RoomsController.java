package com.webspringmvc.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webspringmvc.service.IPhongService;

@Controller
@RequestMapping("/rooms")
public class RoomsController {
	@Autowired
	private IPhongService phongService;
	
	@RequestMapping("/index")
	public String index() {
		return "user/rooms";
	}
	
	@RequestMapping("/room-detail")
	public String roomDetail() {
		return "user/room-detail";
	}
}
