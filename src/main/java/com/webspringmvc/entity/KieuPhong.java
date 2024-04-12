package com.webspringmvc.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "KIEUPHONG")
public class KieuPhong {
	@javax.persistence.Id
	private String MaKP;
	private String TenKP;
	@javax.persistence.OneToMany(mappedBy = "KieuPhong", fetch = javax.persistence.FetchType.EAGER)
	private java.util.Collection<HangPhong> HangPhong;
	
	public KieuPhong() {
	}

	public KieuPhong(String maKP, String tenKP) {
		MaKP = maKP;
		TenKP = tenKP;
	}

	public String getMaKP() {
		return MaKP;
	}

	public void setMaKP(String maKP) {
		MaKP = maKP;
	}

	public String getTenKP() {
		return TenKP;
	}

	public void setTenKP(String tenKP) {
		TenKP = tenKP;
	}
}
