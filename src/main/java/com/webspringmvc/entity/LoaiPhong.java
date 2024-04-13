package com.webspringmvc.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LOAIPHONG")
public class LoaiPhong {
	@Id
	@Column(name = "MALP")
	private String maLP;
	
	@Column(name = "TENLP")
	private String tenLP;
	
	@OneToMany(mappedBy = "loaiPhong", fetch = javax.persistence.FetchType.EAGER)
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
