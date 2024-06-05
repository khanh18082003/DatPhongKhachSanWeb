package com.webspringmvc.dao;

import java.util.List;

import com.webspringmvc.entity.KhachHang;

public interface IUserDao extends GenericDao<KhachHang, String> {
	KhachHang getUserById(String id);
	KhachHang getUserByEmail(String email);
	List<KhachHang> getList();
}
