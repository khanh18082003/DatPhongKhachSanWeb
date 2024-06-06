package com.webspringmvc.controller.admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.webspringmvc.entity.HoaDon;
import com.webspringmvc.service.IRevenueService;

@Controller(value = "homeControllerOfAdmin")
@RequestMapping("/admin")

/* This home page is for revenue */
public class HomeController {
	@Autowired
	SessionFactory factory;

	@Autowired
	IRevenueService revenueService;

	@RequestMapping(value = {"/home", "/"})
	public String homePage(HttpServletRequest request, ModelMap model) {
		Calendar getCurrentDate = Calendar.getInstance();
		int getCurrentYear = getCurrentDate.get(Calendar.YEAR);
		int getCurrentMonth = getCurrentDate.get(Calendar.MONTH) + 1;

		List<HoaDon> revenueData = new ArrayList<>();
		String year = request.getParameter("year");
		if (year != null && !year.isEmpty()) {
			model.addAttribute("year", year);
			revenueData = revenueService.getMonthlyRevenue(year);
		} else {
			year = String.valueOf(getCurrentYear); // Cập nhật giá trị year để sử dụng lại
			revenueData = revenueService.getMonthlyRevenue(year);
		}

		String revenueDataJson = new Gson().toJson(revenueData);
		String currentMonthRevenueJson = new Gson().toJson(revenueService.getCurrentMonthRevenue());

		model.addAttribute("revenueList", revenueService.getList());
		model.addAttribute("yearList", revenueService.getYearList());
		model.addAttribute("revenueDataList", revenueDataJson);
		model.addAttribute("currentMonthRevenue", currentMonthRevenueJson);
		model.addAttribute("year", year); // Đảm bảo rằng "year" luôn được thêm vào model, kể cả khi nó là năm hiện tại
		
		model.addAttribute("thisMonth", getCurrentMonth);
		model.addAttribute("thisYear", getCurrentYear);


	@RequestMapping("/charts")
	public String charts() {
		return "admin/charts";
	}
}