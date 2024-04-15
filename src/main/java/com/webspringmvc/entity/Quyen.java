package com.webspringmvc.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Quyen")
public class Quyen {
	@Id
	@Column(name = "MAQUYEN")
	private String maQuyen;

	@Column(name = "TENQUYEN", nullable = false)
	private String tenQuyen;

	@OneToMany(mappedBy = "quyen", fetch = FetchType.EAGER)
	private Collection<TaiKhoan> taiKhoan;

	public Quyen() {
	}

	public Quyen(String maQuyen, String tenQuyen) {
		this.maQuyen = maQuyen;
		this.tenQuyen = tenQuyen;
	}

	public String getMaQuyen() {
		return maQuyen;
	}

	public void setMaQuyen(String maQuyen) {
		this.maQuyen = maQuyen;
	}

	public String getTenQuyen() {
		return tenQuyen;
	}

	public void setTenQuyen(String tenQuyen) {
		this.tenQuyen = tenQuyen;
	}

	public Collection<TaiKhoan> getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(Collection<TaiKhoan> taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

}
