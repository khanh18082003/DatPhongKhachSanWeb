package com.webspringmvc.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.webspringmvc.entity.Setting;

@ControllerAdvice
public class GlobalControllerAdvice {
	@Autowired
	private ApplicationContext context;
	
	@ModelAttribute
	public void addSettingAttributes(ModelMap model) {
		Setting setting = (Setting) context.getBean("setting");
		model.addAttribute("pathLogo", setting.getPathLogo());
		model.addAttribute("pathLogoHotel", setting.getPathLogoHotel());
		model.addAttribute("nameHotel", setting.getNameHotel());
		model.addAttribute("nameWeb", setting.getNameWeb());
		model.addAttribute("email", setting.getEmail());
		model.addAttribute("sdt", setting.getSdt());
		model.addAttribute("address", setting.getDiaChi());
	}
}
