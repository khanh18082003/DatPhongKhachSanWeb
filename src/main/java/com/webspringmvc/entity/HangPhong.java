package com.webspringmvc.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "HANGPHONG")
public class HangPhong {
	@Id
	@Column(name = "IDHP")
	private String idHP;
	
	@Column(name ="TENHP")
	private String tenHP;
	
	@Column(name = "ANH")
	private String anh;
	
	@Column(name = "GIA") 
	private float gia;
	
	@Column(name = "SOLUONGNGUOI")
	private int soLuongNguoi;
	
	@Column(name = "MOTA")
	private String moTa;
	
	@ManyToOne
	@JoinColumn(name = "MALP")
	private LoaiPhong loaiPhong;
	
	@ManyToOne
	@JoinColumn(name = "MAKP")
	private KieuPhong kieuPhong;
	
	@OneToMany(mappedBy = "hangPhong", fetch = FetchType.EAGER)
	private Collection<Phong> phong;
	
	@OneToMany(mappedBy = "hangPhong", fetch = FetchType.EAGER)
	private Collection<CT_KhuyenMai> ctKM;
	
	@OneToMany(mappedBy = "hangPhong", fetch = FetchType.EAGER)
	private Collection<CT_PhieuDat> ctPD;
	
	public HangPhong() {
	}

	public HangPhong(String idHP, String tenHP, String anh) {
		this.idHP = idHP;
		this.tenHP = tenHP;
		this.anh = anh;
	}

	public String getIdHP() {
		return idHP;
	}

	public void setIdHP(String idHP) {
		this.idHP = idHP;
	}

	public String getTenHP() {
		return tenHP;
	}

	public void setTenHP(String tenHP) {
		this.tenHP = tenHP;
	}

	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	public KieuPhong getKieuPhong() {
		return kieuPhong;
	}

	public void setKieuPhong(KieuPhong kieuPhong) {
		this.kieuPhong = kieuPhong;
	}

	public java.util.Collection<Phong> getPhong() {
		return phong;
	}

	public void setPhong(java.util.Collection<Phong> phong) {
		this.phong = phong;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public float getGia() {
		return gia;
	}

	public void setGia(float gia) {
		this.gia = gia;
	}

	public int getSoLuongNguoi() {
		return soLuongNguoi;
	}

	public void setSoLuongNguoi(int soLuongNguoi) {
		this.soLuongNguoi = soLuongNguoi;
	}
	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public Collection<CT_KhuyenMai> getCtKM() {
		return ctKM;
	}

	public void setCtKM(Collection<CT_KhuyenMai> ctKM) {
		this.ctKM = ctKM;
	}

	public Collection<CT_PhieuDat> getCtPD() {
		return ctPD;
	}

	public void setCtPD(Collection<CT_PhieuDat> ctPD) {
		this.ctPD = ctPD;
	}
	
}
