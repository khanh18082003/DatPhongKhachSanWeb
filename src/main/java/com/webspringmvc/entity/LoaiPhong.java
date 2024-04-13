package com.webspringmvc.entity;

import java.util.Collection;

@javax.persistence.Entity
@javax.persistence.Table(name = "LOAIPHONG")
public class LoaiPhong {
	@javax.persistence.Id
	private String maLP;
	private String tenLP;
	@javax.persistence.OneToMany(mappedBy = "LOAIPHONG", fetch = javax.persistence.FetchType.EAGER)
	private Collection<HangPhong> hangPhong;
	public LoaiPhong() {
	}

	public LoaiPhong(String maLP, String tenLP) {
		this.maLP = maLP;
		this.tenLP = tenLP;
	}

	public String getmaLP() {
		return maLP;
	}

	public void setmaLP(String maLP) {
		this.maLP = maLP;
	}

	public String gettenLP() {
		return tenLP;
	}

	public void settenLP(String tenLP) {
		this.tenLP = tenLP;
	}
}
