package com.webspringmvc.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "KIEUPHONG")
public class KieuPhong {
	@Id
	@Column(name = "MAKP")
	private String maKP;
	
	@Column(name = "TENKP")
	private String tenKP;
	
	@OneToMany(mappedBy = "kieuPhong", fetch = javax.persistence.FetchType.EAGER)
	private Collection<HangPhong> hangPhong;
	
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
