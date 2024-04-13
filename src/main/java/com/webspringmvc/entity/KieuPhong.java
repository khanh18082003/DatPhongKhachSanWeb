package com.webspringmvc.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "KIEUPHONG")
public class KieuPhong {
	@javax.persistence.Id
	private String maKP;
	private String tenKP;
	@javax.persistence.OneToMany(mappedBy = "KieuPhong", fetch = javax.persistence.FetchType.EAGER)
	private java.util.Collection<HangPhong> hangPhong;
	
	public KieuPhong() {
	}

	public KieuPhong(String maKP, String tenKP) {
		this.maKP = maKP;
		this.tenKP = tenKP;
	}

	public String getmaKP() {
		return maKP;
	}

	public void setmaKP(String maKP) {
		this.maKP = maKP;
	}

	public String gettenKP() {
		return tenKP;
	}

	public void settenKP(String tenKP) {
		this.tenKP = tenKP;
	}
}
