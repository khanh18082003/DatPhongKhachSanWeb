package com.webspringmvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CT_KHUYENMAI")
public class CT_KhuyenMai {
	@Id
	private String MaKM;
	@Id
	private String IdHP;
	private int PhanTramGiam;
	
	public CT_KhuyenMai() {
	}

	public CT_KhuyenMai(String maKM, String idHP, int phanTramGiam) {
		MaKM = maKM;
		IdHP = idHP;
		PhanTramGiam = phanTramGiam;
	}
	public String getMaKM() {
		return MaKM;
	}

	public void setMaKM(String maKM) {
		MaKM = maKM;
	}

	public String getIdHP() {
		return IdHP;
	}

	public void setIdHP(String idHP) {
		IdHP = idHP;
	}

	public int getPhanTramGiam() {
		return PhanTramGiam;
	}

	public void setPhanTramGiam(int phanTramGiam) {
		PhanTramGiam = phanTramGiam;
	}
}
