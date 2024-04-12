package com.webspringmvc.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "KHACHHANG")
public class KhachHang {
	@javax.persistence.Id
	private String MaKH;
	private String CMND;
	private String Ho;
	private String Ten;
	private String SDT;
	private String Email;
	@OneToMany(mappedBy = "KhachHang", fetch = javax.persistence.FetchType.EAGER)
	private Collection<PhieuDat> PhieuDat;

	public KhachHang() {
	}

	public KhachHang(String maKH, String cMND, String ho, String ten, String sDT, String email) {
		MaKH = maKH;
		CMND = cMND;
		Ho = ho;
		Ten = ten;
		SDT = sDT;
		Email = email;
	}

	public String getMaKH() {
		return MaKH;
	}

	public void setMaKH(String maKH) {
		MaKH = maKH;
	}

	public String getCMND() {
		return CMND;
	}

	public void setCMND(String cMND) {
		CMND = cMND;
	}

	public String getHo() {
		return Ho;
	}

	public void setHo(String ho) {
		Ho = ho;
	}

	public String getTen() {
		return Ten;
	}

	public void setTen(String ten) {
		Ten = ten;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	
}
