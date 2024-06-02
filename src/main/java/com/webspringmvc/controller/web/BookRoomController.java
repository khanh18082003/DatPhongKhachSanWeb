package com.webspringmvc.controller.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Transactional
@Controller
public class BookRoomController {
	@RequestMapping("/book-room")
	public String bookRoom(@RequestParam("id") String idHP,
			@RequestParam("checkin") String dateIn,
			@RequestParam("checkout") String dateOut,
			@RequestParam("sl") String sLP,
			HttpServletRequest request) {
		SimpleDateFormat originalFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        SimpleDateFormat targetFormat = new SimpleDateFormat("EEE dd MMM yyyy", Locale.ENGLISH);
		try {
			Date dateInTemp = originalFormat.parse(dateIn);
			Date dateOutTemp = originalFormat.parse(dateOut);
			dateIn = targetFormat.format(dateInTemp);
			dateOut = targetFormat.format(dateOutTemp);
		} catch (ParseException  e) {
		    e.printStackTrace();
		}
		request.setAttribute("dateIn", dateIn);
		request.setAttribute("dateOut", dateOut);
		System.out.println(sLP);
		System.out.println(request.getAttribute("price"));;
		
		return "user/book-room";
	}
}
