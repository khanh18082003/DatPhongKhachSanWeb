package com.webspringmvc.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HANGPHONG")
public class HangPhong {
	@javax.persistence.Id
	private String IdHP; 
	private String TenHP;
	@javax.persistence.ManyToOne
	@javax.persistence.JoinColumn(name = "MaLP")
	private LoaiPhong MaLP;
	@javax.persistence.ManyToOne
	@javax.persistence.JoinColumn(name = "MaKP")
	private KieuPhong MaKP;
	@javax.persistence.OneToMany(mappedBy = "HangPhong", fetch = javax.persistence.FetchType.EAGER)
	private java.util.Collection<Phong> Phong;
	
	public HangPhong() {
	}

	public HangPhong(String idHP, String tenHP, LoaiPhong maLP, KieuPhong maKP) {
		IdHP = idHP;
		TenHP = tenHP;
		MaLP = maLP;
		MaKP = maKP;
	}

	public String getIdHP() {
		return IdHP;
	}

	public void setIdHP(String idHP) {
		IdHP = idHP;
	}

	public String getTenHP() {
		return TenHP;
	}

	public void setTenHP(String tenHP) {
		TenHP = tenHP;
	}

	public LoaiPhong getMaLP() {
		return MaLP;
	}

	public void setMaLP(LoaiPhong maLP) {
		MaLP = maLP;
	}

	public KieuPhong getMaKP() {
		return MaKP;
	}

	public void setMaKP(KieuPhong maKP) {
		MaKP = maKP;
	}
	
}
