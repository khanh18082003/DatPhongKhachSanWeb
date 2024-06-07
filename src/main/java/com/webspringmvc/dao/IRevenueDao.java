package com.webspringmvc.dao;

import java.util.List;

import com.webspringmvc.entity.HoaDon;

public interface IRevenueDao extends GenericDao<HoaDon, String> {
	List<HoaDon> getList();
	List<HoaDon> getMonthlyRevenue(String year);
	List<HoaDon> getCurrentMonthRevenue();
	List<String> getYearList();
}
