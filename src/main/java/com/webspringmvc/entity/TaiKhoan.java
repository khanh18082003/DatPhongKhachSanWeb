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
	@JoinColumn(name = "maQuyen")
	private String maQuyen;
	@ManyToOne
	@JoinColumn(name = "maNV")
	private String maNV;
	@ManyToOne
	@JoinColumn(name = "maKH")
	private String maKH;
	@OneToMany(mappedBy = "TaiKhoan",fetch = FetchType.EAGER)
	private Collection<PhieuDat> phieuDat;

	public TaiKhoan() {
	}

	public TaiKhoan(String username, String password, String maQuyen, String maNV, String maKH) {
		this.username = username;
		this.password = password;
		this.maQuyen = maQuyen;
		this.maNV = maNV;
		this.maKH = maKH;
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

	public String getMaQuyen() {
		return maQuyen;
	}

	public void setMaQuyen(String maQuyen) {
		this.maQuyen = maQuyen;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	
}
