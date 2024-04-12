package com.webspringmvc.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "HOADON")
public class HoaDon {
	private String MaHD;
	private Date NgayLap;
	private float TongTien;
	@OneToOne
	@JoinColumn(name = "MaPD")
	private PhieuDat MaPD;
	
	public HoaDon() {
	}

	public HoaDon(String maHD, Date ngayLap, float tongTien, PhieuDat maPD) {
		MaHD = maHD;
		NgayLap = ngayLap;
		TongTien = tongTien;
		MaPD = maPD;
	}

	public String getMaHD() {
		return MaHD;
	}

	public void setMaHD(String maHD) {
		MaHD = maHD;
	}

	public Date getNgayLap() {
		return NgayLap;
	}

	public void setNgayLap(Date ngayLap) {
		NgayLap = ngayLap;
	}

	public float getTongTien() {
		return TongTien;
	}

	public void setTongTien(float tongTien) {
		TongTien = tongTien;
	}

	public PhieuDat getMaPD() {
		return MaPD;
	}

	public void setMaPD(PhieuDat maPD) {
		MaPD = maPD;
	}
	
}
