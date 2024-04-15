package com.webspringmvc.entity;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "KHUYENMAI")
public class KhuyenMai {
	@Id
	@Column(name = "MAKM")
	private String maKM;
	
	@Column(name = "TENKM", nullable = false)
	private String tenKM;
	
	@Column(name = "NGAYBD")
	private Timestamp ngayBD;
	
	@Column(name = "NGAYKT")
	private Timestamp ngayKT;
	
	@OneToMany(mappedBy = "khuyenMai", fetch = FetchType.EAGER)
	private Collection<CT_KhuyenMai> ctKM;
	
	public KhuyenMai() {
	}

	public KhuyenMai(String maKM, String tenKM, Timestamp ngayBD, Timestamp ngayKT) {
		this.maKM = maKM;
		this.tenKM = tenKM;
		this.ngayBD = ngayBD;
		this.ngayKT = ngayKT;
	}

	public String getmaKM() {
		return maKM;
	}

	public void setmaKM(String maKM) {
		this.maKM = maKM;
	}

	public String gettenKM() {
		return tenKM;
	}

	public void settenKM(String tenKM) {
		this.tenKM = tenKM;
	}

	public Timestamp getngayBD() {
		return ngayBD;
	}

	public void setngayBD(Timestamp ngayBD) {
		this.ngayBD = ngayBD;
	}

	public Timestamp getngayKT() {
		return ngayKT;
	}

	public void setngayKT(Timestamp ngayKT) {
		this.ngayKT = ngayKT;
	}
	
}
