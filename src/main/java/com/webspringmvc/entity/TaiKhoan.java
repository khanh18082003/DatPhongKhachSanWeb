package com.webspringmvc.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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

	@OneToMany(mappedBy = "taiKhoan", fetch = FetchType.EAGER)
	private Collection<PhieuDat> phieuDat;

	public TaiKhoan() {
	}

	public TaiKhoan(String username, String password, Quyen quyen, NhanVien nhanVien) {
		this.username = username;
		this.password = password;
		this.quyen = quyen;
		this.nhanVien = nhanVien;
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

	public Collection<PhieuDat> getPhieuDat() {
		return phieuDat;
	}

	public void setPhieuDat(Collection<PhieuDat> phieuDat) {
		this.phieuDat = phieuDat;
	}

}
