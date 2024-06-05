package com.webspringmvc.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "KHACHHANG")
public class KhachHang {
	@Id
	@Column(name = "MAKH")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maKH;
	
	@NotBlank(message = "First Name can not be empty.")
	@Column(name = "HO", nullable = false, length = 50)
	private String ho;
	
	@NotBlank(message = "Last Name can not be empty.")
	@Column(name = "TEN", nullable = false, length = 10)
	private String ten;
	
	@NotBlank(message = "Phone Number can not be empty.")
	@Column(name = "SDT", nullable = false, length = 11)
	private String sdt;
	
	@OneToOne
	@JoinColumn(name = "EMAIL")
	private TaiKhoan email;

	public KhachHang() {
	}

	public KhachHang( String ho, String ten, String sdt, TaiKhoan email) {
		this.ho = ho;
		this.ten = ten;
		this.sdt = sdt;
		this.email = email;
	}

	public KhachHang(TaiKhoan email) {
		this.email = email;
	}

	public int getMaKH() {
		return maKH;
	}

	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public TaiKhoan getEmail() {
		return email;
	}

	public void setEmail(TaiKhoan email) {
		this.email = email;
	}

	
}
