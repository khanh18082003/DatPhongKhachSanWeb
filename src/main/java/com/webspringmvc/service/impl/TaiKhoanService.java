package com.webspringmvc.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.webspringmvc.dao.INhanVienDao;
import com.webspringmvc.dao.ITaiKhoanDao;
import com.webspringmvc.entity.NhanVien;
import com.webspringmvc.entity.TaiKhoan;
import com.webspringmvc.helps.Bcrypt;
import com.webspringmvc.service.ITaiKhoanService;

@Service
public class TaiKhoanService implements ITaiKhoanService {
	@Autowired
	ITaiKhoanDao taiKhoanDao;
	
	@Autowired
	INhanVienDao nhanVienDao;
	
	@Override
	public TaiKhoan getTaiKhoan(String id, String quyen) {
		id = id.trim();
		return taiKhoanDao.getTaiKhoan(id, quyen);
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
	public int checkAccount(String id, String password, String quyen) {
		TaiKhoan t = getTaiKhoan(id, quyen);
		if (t != null) {
			password = Bcrypt.toSHA1(password, t.getAuth());
			if (password.equals(t.getPassword())) {
				return 1;
			}
			return 2; // password is wrong
		}
		return 0; // username not exist
	}

	@Override
	public boolean updateResetPasswordToken(String token, String email, String quyen, ModelMap model) {
		TaiKhoan t  = null;
		NhanVien nv = null;
		
		if (quyen == "KH") {
			t = taiKhoanDao.getTaiKhoan(email, quyen);
		}else if (quyen == "NV") {
			nv = nhanVienDao.getNhanVienByEmail(email);
			if (nv != null) {
				t = taiKhoanDao.getTaiKhoanByMaNV(nv.getMaNV());
			}
		}
		
		if (t != null) {
			t.setResetPasswordToken(token);
			taiKhoanDao.update(t);
			return true;
		} else {
			model.addAttribute("error", "Could not find user with this email");
			return false;
		}
	}

	@Override
	public TaiKhoan get(String token, int index) {
		return taiKhoanDao.getTaiKhoanByToken(token, index);
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
