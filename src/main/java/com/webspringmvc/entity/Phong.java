package com.webspringmvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Phong")
public class Phong {
	@Id
	@Column(name = "MAPHONG")
	private String maPhong;

	@Column(name = "TENPHONG", nullable = false)
	private String tenPhong;

	@Column(name = "TANG")
	private int tang;

	@ManyToOne
	@JoinColumn(name = "MATTP")
	private TrangThaiPhong trangThaiPhong;

	@ManyToOne
	@JoinColumn(name = "IDHP")
	private HangPhong hangPhong;

	public Phong() {
	}

	public Phong(String maPhong, String tenPhong, int tang, TrangThaiPhong trangThaiPhong, HangPhong hangPhong) {
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.tang = tang;
		this.trangThaiPhong = trangThaiPhong;
		this.hangPhong = hangPhong;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public int getTang() {
		return tang;
	}

	public void setTang(int tang) {
		this.tang = tang;
	}

	public TrangThaiPhong getTrangThaiPhong() {
		return trangThaiPhong;
	}

	public void setTrangThaiPhong(TrangThaiPhong trangThaiPhong) {
		this.trangThaiPhong = trangThaiPhong;
	}

	public HangPhong getHangPhong() {
		return hangPhong;
	}

	public void setHangPhong(HangPhong hangPhong) {
		this.hangPhong = hangPhong;
	}

}
