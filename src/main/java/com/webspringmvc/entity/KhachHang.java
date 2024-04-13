package com.webspringmvc.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KHACHHANG")
public class KhachHang {
	@Id
	@Column(name = "MAKH")
	private String maKH;
	
	@Column(name = "CMND", nullable = false, unique = true)
	private String cmnd;
	
	@Column(name = "HO", nullable = false, length = 50)
	private String ho;
	
	@Column(name = "TEN", nullable = false, length = 10)
	private String ten;
	
	@Column(name = "SDT", nullable = false, length = 11)
	private String sdt;
	
	@Column(name = "EMAIL", nullable = false)
	private String email;

	public KhachHang() {
	}

	public KhachHang(String maKH, String cmnd, String ho, String ten, String sdt, String email) {
		this.maKH = maKH;
		this.cmnd = cmnd;
		this.ho = ho;
		this.ten = ten;
		this.sdt = sdt;
		this.email = email;
	}

	public String getmaKH() {
		return maKH;
	}

	public void setmaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
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

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}
	
}
