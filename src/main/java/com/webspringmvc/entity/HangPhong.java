package com.webspringmvc.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HANGPHONG")
public class HangPhong {
	@javax.persistence.Id
	private String idHP; 
	private String tenHP;
	@javax.persistence.ManyToOne
	@javax.persistence.JoinColumn(name = "maLP")
	private LoaiPhong maLP;
	@javax.persistence.ManyToOne
	@javax.persistence.JoinColumn(name = "maKP")
	private KieuPhong maKP;
	@javax.persistence.OneToMany(mappedBy = "HangPhong", fetch = javax.persistence.FetchType.EAGER)
	private java.util.Collection<Phong> phong;
	
	public HangPhong() {
	}

	public HangPhong(String idHP, String tenHP, LoaiPhong maLP, KieuPhong maKP) {
		this.idHP = idHP;
		this.tenHP = tenHP;
		this.maLP = maLP;
		this.maKP = maKP;
	}

	public String getidHP() {
		return idHP;
	}

	public void setidHP(String idHP) {
		this.idHP = idHP;
	}

	public String gettenHP() {
		return tenHP;
	}

	public void settenHP(String tenHP) {
		this.tenHP = tenHP;
	}

	public LoaiPhong getmaLP() {
		return maLP;
	}

	public void setmaLP(LoaiPhong maLP) {
		this.maLP = maLP;
	}

	public KieuPhong getmaKP() {
		return maKP;
	}

	public void setmaKP(KieuPhong maKP) {
		this.maKP = maKP;
	}
	
}
