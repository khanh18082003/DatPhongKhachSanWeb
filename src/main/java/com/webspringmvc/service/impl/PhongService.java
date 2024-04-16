package com.webspringmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webspringmvc.dao.IPhongDao;
import com.webspringmvc.entity.Phong;
import com.webspringmvc.service.IPhongService;

@Service
public class PhongService implements IPhongService {
	@Autowired
	private IPhongDao phongDao;
	
	@Override
	public List<Phong> getList() {
		return phongDao.getList();
	}
	
}
