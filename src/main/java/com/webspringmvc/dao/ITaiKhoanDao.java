package com.webspringmvc.dao;

import java.util.List;

import com.webspringmvc.entity.TaiKhoan;

public interface ITaiKhoanDao extends GenericDao<TaiKhoan, String> {
	TaiKhoan getTaiKhoan(String id, String quyen);
	TaiKhoan getTaiKhoanByToken(String token, int index);
	List<TaiKhoan> getList();
}
