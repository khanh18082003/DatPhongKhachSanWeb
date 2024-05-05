package com.webspringmvc.page;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
	
	public static java.util.Date formatDate(String Date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH);
		java.util.Date dateTemp = null;
		try {
			dateTemp = dateFormat.parse(Date);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return dateTemp;
	}
	
}
