package com.webspringmvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CT_KHUYENMAI")
public class CT_KhuyenMai {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "maKM")
	private KhuyenMai khuyenMai;

	@ManyToOne
	@JoinColumn(name = "idHP")
	private HangPhong hangPhong;

	@Column(name = "PHANTRAMGIAM")
	private int phanTramGiam;

	public CT_KhuyenMai() {
	}

	public CT_KhuyenMai(KhuyenMai khuyenMai, HangPhong hangPhong, int phanTramGiam) {
		this.khuyenMai = khuyenMai;
		this.hangPhong = hangPhong;
		this.phanTramGiam = phanTramGiam;
	}

	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	public HangPhong getHangPhong() {
		return hangPhong;
	}

	public void setHangPhong(HangPhong hangPhong) {
		this.hangPhong = hangPhong;
	}

	public int getPhanTramGiam() {
		return phanTramGiam;
	}

	public void setPhanTramGiam(int phanTramGiam) {
		this.phanTramGiam = phanTramGiam;
	}

}
