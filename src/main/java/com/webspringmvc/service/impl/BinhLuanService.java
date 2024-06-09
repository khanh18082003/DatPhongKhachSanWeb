package com.webspringmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webspringmvc.dao.IBinhLuanDao;
import com.webspringmvc.entity.BinhLuan;
import com.webspringmvc.service.IBinhLuanService;

@Service
public class BinhLuanService implements IBinhLuanService {
	@Autowired
	IBinhLuanDao binhLuanDao;
	
	@Override
	public List<BinhLuan> getList(String idHP) {
		
		return binhLuanDao.getList(idHP);
	}

	@Override
	public int insert(BinhLuan bl) {
		return binhLuanDao.insert(bl);
	}

	@Override
	public int update(BinhLuan bl) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		return binhLuanDao.delete(id);
	}

	@Override
	public List<BinhLuan> getBlListOfUser(String idHP, int id) {
		return binhLuanDao.getBlListOfUser(idHP, id);
	}

	@Override
	public BinhLuan getLatestReview(String idHP, int id) {
		return binhLuanDao.getLatestReview(idHP, id);
	}
	
}
