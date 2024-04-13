package com.webspringmvc.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "HOADON")
public class HoaDon {
	private String maHD;
	private Date ngayLap;
	private float tongTien;
	@OneToOne
	@JoinColumn(name = "maPD")
	private PhieuDat maPD;
	
	public HoaDon() {
	}

	public HoaDon(String maHD, Date ngayLap, float tongTien, PhieuDat maPD) {
		this.maHD = maHD;
		this.ngayLap = ngayLap;
		this.tongTien = tongTien;
		this.maPD = maPD;
	}

	public String getmaHD() {
		return maHD;
	}

	public void setmaHD(String maHD) {
		this.maHD = maHD;
	}

	public Date getngayLap() {
		return ngayLap;
	}

	public void setngayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	public float gettongTien() {
		return tongTien;
	}

	public void settongTien(float tongTien) {
		this.tongTien = tongTien;
	}

	public PhieuDat getmaPD() {
		return maPD;
	}

	public void setmaPD(PhieuDat maPD) {
		this.maPD = maPD;
	}
	
}
