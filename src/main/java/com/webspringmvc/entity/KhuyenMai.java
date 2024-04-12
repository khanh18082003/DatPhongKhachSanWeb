package com.webspringmvc.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KHUYENMAI")
public class KhuyenMai {
	@Id
	private String MaKM;
	private String TenKM;
	private Date NgayBD;
	private Date NgayKT;
	
	public KhuyenMai() {
	}

	public KhuyenMai(String maKM, String tenKM, Date ngayBD, Date ngayKT) {
		MaKM = maKM;
		TenKM = tenKM;
		NgayBD = ngayBD;
		NgayKT = ngayKT;
	}

	public String getMaKM() {
		return MaKM;
	}

	public void setMaKM(String maKM) {
		MaKM = maKM;
	}

	public String getTenKM() {
		return TenKM;
	}

	public void setTenKM(String tenKM) {
		TenKM = tenKM;
	}

	public Date getNgayBD() {
		return NgayBD;
	}

	public void setNgayBD(Date ngayBD) {
		NgayBD = ngayBD;
	}

	public Date getNgayKT() {
		return NgayKT;
	}

	public void setNgayKT(Date ngayKT) {
		NgayKT = ngayKT;
	}
	
}
