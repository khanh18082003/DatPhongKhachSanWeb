package com.webspringmvc.entity;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "NhanVien")
public class NhanVien {
	@Id
	@Column(name = "MANV")
	private String maNV;

	@Column(name = "HO", nullable = false, length = 50)
	private String ho;

	@Column(name = "TEN", nullable = false, length = 10)
	private String ten;

	@Column(name = "PHAI", nullable = false)
	private String phai;

	@Column(name = "NGAYSINH")
	private Date ngaySinh;

	@Column(name = "DIACHI")
	private String diaChi;

	@Column(name = "SDT")
	private String sdt;

	@Column(name = "EMAIL")
	private String email;

	@OneToMany(mappedBy = "nhanVien", fetch = FetchType.EAGER)
	private Collection<TaiKhoan> taiKhoan;

	public NhanVien() {
	}

	public NhanVien(String maNV, String ho, String ten, String phai, Date ngaySinh, String diaChi, String sdt,
			String email) {
		this.maNV = maNV;
		this.ho = ho;
		this.ten = ten;
		this.phai = phai;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.sdt = sdt;
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

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date  ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public Collection<TaiKhoan> getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(Collection<TaiKhoan> taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
