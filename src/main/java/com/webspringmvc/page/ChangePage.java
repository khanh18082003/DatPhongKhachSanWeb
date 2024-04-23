package com.webspringmvc.page;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.ModelMap;

import com.webspringmvc.entity.HangPhong;

public class ChangePage {

	public static <T> void changePage(List<T> listTemp, int page,ModelMap model, int numberItems) {
		List<T> list = new ArrayList<T>();
		for (int i = 1; i < listTemp.size()+1; i++) {
			if (i <= page*numberItems && i > (page-1)*numberItems) {
				list.add(listTemp.get(i-1));
			}
		}
		int pageMax = (int) Math.ceil((double) listTemp.size() / numberItems);
		model.addAttribute("roomList",list);
		model.addAttribute("pagecur",page);
		model.addAttribute("pageMax",pageMax);
	}
	
}
