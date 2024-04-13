package com.webspringmvc.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TrangThaiPhong {
	@Id
	private String maTTP;
	private String tenTTP;
	@OneToMany(mappedBy = "TrangThaiPhong",fetch = FetchType.EAGER)
	private Collection<Phong> phong;

	public TrangThaiPhong() {
	}

	public TrangThaiPhong(String maTTP, String tenTTP) {
		this.maTTP = maTTP;
		this.tenTTP = tenTTP;
	}

	public String getMaTTP() {
		return maTTP;
	}

	public void setMaTTP(String maTTP) {
		this.maTTP = maTTP;
	}

	public String getTenTTP() {
		return tenTTP;
	}

	public void setTenTTP(String tenTTP) {
		this.tenTTP = tenTTP;
	}
	
}
