package com.webspringmvc.service;

import java.util.List;

import com.webspringmvc.entity.HoaDon;

public interface IRevenueService {
	int insert(HoaDon hd);
	int update(HoaDon hd);
	int delete(String id);
	List<HoaDon> getList();
	List<HoaDon> getMonthlyRevenue(String year);
	List<HoaDon> getCurrentMonthRevenue();
	List<String> getYearList();
}
