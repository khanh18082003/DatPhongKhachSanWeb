package com.webspringmvc.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.webspringmvc.dao.ITaiKhoanDao;
import com.webspringmvc.entity.TaiKhoan;
import com.webspringmvc.helps.Bcrypt;
import com.webspringmvc.service.ITaiKhoanService;

@Service
public class TaiKhoanService implements ITaiKhoanService {
	@Autowired
	ITaiKhoanDao taiKhoanDao;

	@Override
	public TaiKhoan getTaiKhoan(String id) {
		id = id.trim();
		return taiKhoanDao.getTaiKhoan(id);
	}

	@Override
	public List<TaiKhoan> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(TaiKhoan t) {
		t.setUsername(t.getUsername().trim());
		return taiKhoanDao.insert(t);
	}

	@Override
	public int update(TaiKhoan t) {
		return taiKhoanDao.update(t);
	}

	@Override
	public boolean checkAccount(String id) {
		return getTaiKhoan(id) == null;
	}

	@Override
	public boolean checkPassword(String id, String password) {
		TaiKhoan t = getTaiKhoan(id);
		if (t != null) {
			password = Bcrypt.toSHA1(password, t.getAuth());
			return password.equals(t.getPassword());
		}
		return false;
	}
	
	@Override
	public void updateResetPasswordToken(String token, String email, ModelMap model) {
		TaiKhoan t = taiKhoanDao.getTaiKhoan(email);
		if (t != null) {
			System.out.println(t.getUsername());
			t.setResetPasswordToken(token);
			taiKhoanDao.update(t);
		}else {
			model.addAttribute("error", "Could not find user with this email");
		}
	}
	
	@Override
	public TaiKhoan get(String token) {
		return taiKhoanDao.getTaiKhoanByToken(token);
	}
	
	@Override
	public void updateNewPassword(TaiKhoan taiKhoan, String newPassword) {
		String salt = UUID.randomUUID().toString();
		taiKhoan.setPassword(Bcrypt.toSHA1(newPassword, salt));
		taiKhoan.setAuth(salt);
		taiKhoan.setResetPasswordToken(null);
		taiKhoanDao.update(taiKhoan);
	}
}
