package com.webspringmvc.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "NhanVien")
public class NhanVien {
	@Id
	private String maNV;
	private String ho;
	private String ten;
	private String phai;
	private String ngaySinh;
	private String diaChi;
	private String sDT;
	private String email;
	@OneToMany(mappedBy = "NhanVien",fetch = FetchType.EAGER)
	private Collection<TaiKhoan> taiKhoan;
	public NhanVien() {
	}

	public NhanVien(String maNV, String ho, String ten, String phai, String ngaySinh, String diaChi, String sDT,
			String email) {
		this.maNV = maNV;
		this.ho = ho;
		this.ten = ten;
		this.phai = phai;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.sDT = sDT;
		this.email = email;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
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

	public String getPhai() {
		return phai;
	}

	public void setPhai(String phai) {
		this.phai = phai;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getsDT() {
		return sDT;
	}

	public void setsDT(String sDT) {
		this.sDT = sDT;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
