package com.webspringmvc.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "HOADON")
public class HoaDon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MAHD")
	private int maHD;
	
	@Column(name = "NGAYLAP")
	private Timestamp ngayLap;
	
	@Column(name = "TONGTIEN")
	private float tongTien;
	
	@OneToOne
	@JoinColumn(name = "MAPD")
	private PhieuDat maPD;
	
	public HoaDon() {
	}

	public HoaDon(Timestamp ngayLap, float tongTien, PhieuDat maPD) {
		this.ngayLap = ngayLap;
		this.tongTien = tongTien;
		this.maPD = maPD;
	}

	public int getmaHD() {
		return maHD;
	}

	public void setmaHD(int maHD) {
		this.maHD = maHD;
	}

	public Timestamp getngayLap() {
		return ngayLap;
	}

	public void setngayLap(Timestamp ngayLap) {
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
