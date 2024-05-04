package com.webspringmvc.entity;

import java.util.Collection;

import javax.persistence.Column;
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
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "AUTH", nullable = false, unique = true)
	private String auth;

	@Column(name = "RESET_PASSWORD_TOKEN")
	private String resetPasswordToken;

	@ManyToOne
	@JoinColumn(name = "MAQUYEN", nullable = false)
	private Quyen quyen;

	@ManyToOne
	@JoinColumn(name = "MANV")
	private NhanVien nhanVien;

	@OneToMany(mappedBy = "taiKhoan", fetch = FetchType.EAGER)
	private Collection<PhieuDat> phieuDat;

	public TaiKhoan() {
	}

	public TaiKhoan(String username, String password, Quyen quyen, NhanVien nhanVien, String auth,
			String resetPasswordToken) {
		this.username = username;
		this.password = password;
		this.quyen = quyen;
		this.nhanVien = nhanVien;
		this.auth = auth;
		this.resetPasswordToken = resetPasswordToken;
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

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
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

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

}
