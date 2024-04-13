package com.webspringmvc.entity;


import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CT_KHUYENMAI")
public class CT_KhuyenMai {
	@Id
	private String maKH;
	@Id
	private String idHP;
	private int phanTramGiam;
	@OneToMany(mappedBy = "CT_KhuyenMai", fetch = javax.persistence.FetchType.EAGER)
	private Collection<KhuyenMai> khuyenMai;
	@OneToMany(mappedBy = "CT_KhuyenMai", fetch = javax.persistence.FetchType.EAGER)
	private Collection<HangPhong> hangPhong;
	
	public CT_KhuyenMai() {
	}

	public CT_KhuyenMai(String maKH, String idHP, int phanTramGiam) {
		this.maKH = maKH;
		this.idHP = idHP;
		this.phanTramGiam = phanTramGiam;
	}
	public String getmaKH() {
		return maKH;
	}

	public void setmaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getidHP() {
		return idHP;
	}

	public void setidHP(String idHP) {
		this.idHP = idHP;
	}

	public int getphanTramGiam() {
		return phanTramGiam;
	}

	public void setphanTramGiam(int phanTramGiam) {
		this.phanTramGiam = phanTramGiam;
	}
}
