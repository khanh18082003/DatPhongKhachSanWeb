package com.webspringmvc.dao;

import com.webspringmvc.entity.NhanVien;

public interface INhanVienDao extends GenericDao<NhanVien, String> {
	NhanVien getNhanVienByEmail(String email);
}
