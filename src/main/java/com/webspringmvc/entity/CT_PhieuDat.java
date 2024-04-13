package com.webspringmvc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CT_PHIEUDAT")
public class CT_PhieuDat {
	@Id
	@ManyToOne
	@javax.persistence.JoinColumn(name = "MaPD")
	private String maPD;
	@Id
	@ManyToOne
	@javax.persistence.JoinColumn(name = "IdHP")
	private String idHP;
	
	private int sLPhong;

	public CT_PhieuDat() {
	}

	public CT_PhieuDat(String maPD, String idHP, int sLPhong) {
		this.maPD = maPD;
		this.idHP = idHP;
		this.sLPhong = sLPhong;
	}

	public String getmaPD() {
		return maPD;
	}

	public void setmaPD(String maPD) {
		this.maPD = maPD;
	}

	public String getidHP() {
		return idHP;
	}

	public void setidHP(String idHP) {
		this.idHP = idHP;
	}

	public int getsLPhong() {
		return sLPhong;
	}

	public void setsLPhong(int sLPhong) {
		this.sLPhong = sLPhong;
	}
}
