package com.webspringmvc.service;

import com.webspringmvc.entity.KhachHang;

public interface IUserService {
	int insert(KhachHang kh);
	int update(KhachHang kh);
	KhachHang getUserById(String id);
	KhachHang getUserByEmail(String email);
}
