package com.webspringmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webspringmvc.dao.IRevenueDao;
import com.webspringmvc.entity.HoaDon;
import com.webspringmvc.service.IRevenueService;

@Service
public class RevenueService implements IRevenueService {
	@Autowired
	IRevenueDao revenueDao;
	@Override
	public int insert(HoaDon hd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(HoaDon hd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<HoaDon> getList() {
		return revenueDao.getList();
	}

	@Override
	public List<HoaDon> getMonthlyRevenue(String year) {
		return revenueDao.getMonthlyRevenue(year);
	}
	
	@Override
	public List<HoaDon> getCurrentMonthRevenue() {
		return revenueDao.getCurrentMonthRevenue();
	}

	@Override
	public List<String> getYearList() {
		return revenueDao.getYearList();
	}

	

}
