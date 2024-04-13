package com.webspringmvc.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "KHACHHANG")
public class KhachHang {
	@javax.persistence.Id
	private String maKH;
	private String cMND;
	private String ho;
	private String ten;
	private String sDT;
	private String email;
	@OneToMany(mappedBy = "KhachHang", fetch = javax.persistence.FetchType.EAGER)
	private Collection<PhieuDat> phieuDat;

	public KhachHang() {
	}

	public KhachHang(String maKH, String cMND, String ho, String ten, String sDT, String email) {
		this.maKH = maKH;
		this.cMND = cMND;
		this.ho = ho;
		this.ten = ten;
		this.sDT = sDT;
		this.email = email;
	}

	public String getmaKH() {
		return maKH;
	}

	public void setmaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getcMND() {
		return cMND;
	}

	public void setcMND(String cMND) {
		this.cMND = cMND;
	}

	public String getho() {
		return ho;
	}

	public void setho(String ho) {
		this.ho = ho;
	}

	public String getten() {
		return ten;
	}

	public void setten(String ten) {
		this.ten = ten;
	}

	public String getsDT() {
		return sDT;
	}

	public void setsDT(String sDT) {
		this.sDT = sDT;
	}

	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}
	
}
