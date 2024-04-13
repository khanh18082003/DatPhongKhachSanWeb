package com.webspringmvc.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class TaiKhoan {
	@Id
	private String username;

	private String password;

	@ManyToOne
	@JoinColumn(name = "MaQuyen", nullable = false)
	private Quyen quyen;

	@ManyToOne
	@JoinColumn(name = "MaNV")
	private NhanVien nhanVien;

	@ManyToOne
	@JoinColumn(name = "MaKH")
	private KhachHang khachHang;

	@OneToMany(mappedBy = "taiKhoan", fetch = FetchType.EAGER)
	private Collection<PhieuDat> phieuDat;

	public TaiKhoan() {
	}

	public TaiKhoan(String username, String password, Quyen quyen, NhanVien nhanVien, KhachHang khachHang) {
		this.username = username;
		this.password = password;
		this.quyen = quyen;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Quyen getQuyen() {
		return quyen;
	}

	public void setQuyen(Quyen quyen) {
		this.quyen = quyen;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public Collection<PhieuDat> getPhieuDat() {
		return phieuDat;
	}

	public void setPhieuDat(Collection<PhieuDat> phieuDat) {
		this.phieuDat = phieuDat;
	}

}
