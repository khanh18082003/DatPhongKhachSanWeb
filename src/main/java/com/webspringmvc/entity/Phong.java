package com.webspringmvc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Phong")
public class Phong {
	@Id
	private String maPhong;
	private String tenPhong;
	private String tang;
	@ManyToOne
	@JoinColumn(name = "maTTP")
	private String maTTP;
	@ManyToOne
	@JoinColumn(name = "idHP")
	private String idHP;
	
	public Phong() {
	}

	public Phong(String maPhong, String tenPhong, String tang, String maTTP, String idHP) {
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.tang = tang;
		this.maTTP = maTTP;
		this.idHP = idHP;
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

	public String getTang() {
		return tang;
	}

	public void setTang(String tang) {
		this.tang = tang;
	}

	public String getMaTTP() {
		return maTTP;
	}

	public void setMaTTP(String maTTP) {
		this.maTTP = maTTP;
	}

	public String getIdHP() {
		return idHP;
	}

	public void setIdHP(String idHP) {
		this.idHP = idHP;
	}
	
}
