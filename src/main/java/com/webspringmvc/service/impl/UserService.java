package com.webspringmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webspringmvc.dao.IUserDao;
import com.webspringmvc.entity.KhachHang;
import com.webspringmvc.service.IUserService;

@Service
public class UserService implements IUserService {
	
	@Autowired
	IUserDao userDao;
	
	@Override
	public int insert(KhachHang kh) {
		return userDao.insert(kh);
	}

	@Override
	public int update(KhachHang kh) {
		return userDao.update(kh);
	}

	@Override
	public KhachHang getUserById(String id) {
		return userDao.getUserById(id);
	}

	@Override
	public KhachHang getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

}
