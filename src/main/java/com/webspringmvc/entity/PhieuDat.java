package com.webspringmvc.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PhieuDat")
public class PhieuDat {
	@Id
	private String maPD;
	private Date ngayDat;
	private Date ngayBD;
	private Date ngayKT;
	@ManyToOne
	@JoinColumn(name = "username")
	private String username;
	
	public PhieuDat() {
	}

	public PhieuDat(String maPD, Date ngayDat, Date ngayBD, Date ngayKT, String username) {
		this.maPD = maPD;
		this.ngayDat = ngayDat;
		this.ngayBD = ngayBD;
		this.ngayKT = ngayKT;
		this.username = username;
	}

	public String getMaPD() {
		return maPD;
	}

	public void setMaPD(String maPD) {
		this.maPD = maPD;
	}

	public Date getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}

	public Date getNgayBD() {
		return ngayBD;
	}

	public void setNgayBD(Date ngayBD) {
		this.ngayBD = ngayBD;
	}

	public Date getNgayKT() {
		return ngayKT;
	}

	public void setNgayKT(Date ngayKT) {
		this.ngayKT = ngayKT;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
