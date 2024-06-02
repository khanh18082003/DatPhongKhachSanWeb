package com.webspringmvc.service;

import java.util.List;

import org.springframework.ui.ModelMap;

import com.webspringmvc.entity.TaiKhoan;

public interface ITaiKhoanService {
	int insert(TaiKhoan t);
	int update(TaiKhoan t);
	TaiKhoan getTaiKhoan(String id, String quyen);
	List<TaiKhoan> getList();
	int checkAccount(String id, String password, String quyen);
	void updateResetPasswordToken(String token, String email, String quyen, ModelMap model);
	TaiKhoan get(String token, int index);
	void updateNewPassword(TaiKhoan taiKhoan, String newPassword);
}
