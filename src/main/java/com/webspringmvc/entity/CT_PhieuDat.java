package com.webspringmvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "CT_PHIEUDAT")
public class CT_PhieuDat {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "MAPD")
	private PhieuDat phieuDat;

	@ManyToOne
	@JoinColumn(name = "IDHP")
	private HangPhong hangPhong;

	private int sLPhong;

	public CT_PhieuDat() {
	}

	public CT_PhieuDat(PhieuDat phieuDat, HangPhong hangPhong, int sLPhong) {
		this.phieuDat = phieuDat;
		this.hangPhong = hangPhong;
		this.sLPhong = sLPhong;
	}

	public PhieuDat getPhieuDat() {
		return phieuDat;
	}

	public void setPhieuDat(PhieuDat phieuDat) {
		this.phieuDat = phieuDat;
	}

	public HangPhong getHangPhong() {
		return hangPhong;
	}

	public void setHangPhong(HangPhong hangPhong) {
		this.hangPhong = hangPhong;
	}

	public int getsLPhong() {
		return sLPhong;
	}

	public void setsLPhong(int sLPhong) {
		this.sLPhong = sLPhong;
	}
}
