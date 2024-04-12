package com.webspringmvc.entity;

import java.util.Collection;

@javax.persistence.Entity
@javax.persistence.Table(name = "LOAIPHONG")
public class LoaiPhong {
	@javax.persistence.Id
	private String MaLP;
	private String TenLP;
	@javax.persistence.OneToMany(mappedBy = "LOAIPHONG", fetch = javax.persistence.FetchType.EAGER)
	private Collection<HangPhong> HangPhong;
	public LoaiPhong() {
	}

	public LoaiPhong(String maLP, String tenLP) {
		MaLP = maLP;
		TenLP = tenLP;
	}

	public String getMaLP() {
		return MaLP;
	}

	public void setMaLP(String maLP) {
		MaLP = maLP;
	}

	public String getTenLP() {
		return TenLP;
	}

	public void setTenLP(String tenLP) {
		TenLP = tenLP;
	}
}
