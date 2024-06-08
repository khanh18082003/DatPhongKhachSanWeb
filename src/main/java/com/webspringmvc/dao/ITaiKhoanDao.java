package com.webspringmvc.dao;


import com.webspringmvc.entity.TaiKhoan;

public interface ITaiKhoanDao extends GenericDao<TaiKhoan, String> {
	TaiKhoan getTaiKhoan(String id, String quyen);
	TaiKhoan getTaiKhoanByToken(String token, int index);
	TaiKhoan getTaiKhoanByMaNV(String id);
}
