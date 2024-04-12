package com.webspringmvc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CT_PHIEUDAT")
public class CT_PhieuDat {
	@Id
	private String MaPD;
	@Id
	private String IdHP;
	
	private int SLPhong;

	public CT_PhieuDat() {
	}

	public CT_PhieuDat(String maPD, String idHP, int slPhong) {
		MaPD = maPD;
		IdHP = idHP;
		SLPhong = slPhong;
	}

	public String getMaPD() {
		return MaPD;
	}

	public void setMaPD(String maPD) {
		MaPD = maPD;
	}

	public String getIdHP() {
		return IdHP;
	}

	public void setIdHP(String idHP) {
		IdHP = idHP;
	}

	public int getSLPhong() {
		return SLPhong;
	}

	public void setSLPhong(int slPhong) {
		SLPhong = slPhong;
	}
}
