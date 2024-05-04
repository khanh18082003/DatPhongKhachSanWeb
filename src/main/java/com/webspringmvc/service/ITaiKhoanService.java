package com.webspringmvc.service;

import java.util.List;

import org.springframework.ui.ModelMap;

import com.webspringmvc.entity.TaiKhoan;

public interface ITaiKhoanService {
	int insert(TaiKhoan t);
	int update(TaiKhoan t);
	TaiKhoan getTaiKhoan(String id);
	List<TaiKhoan> getList();
	boolean checkAccount(String id);
	boolean checkPassword(String id, String password);
	void updateResetPasswordToken(String token, String email, ModelMap model);
	TaiKhoan get(String token);
	void updateNewPassword(TaiKhoan taiKhoan, String newPassword);
}
