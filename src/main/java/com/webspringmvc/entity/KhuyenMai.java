package com.webspringmvc.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KHUYENMAI")
public class KhuyenMai {
	@Id
	private String maKM;
	private String tenKM;
	private Date ngayBD;
	private Date ngayKT;
	
	public KhuyenMai() {
	}

	public KhuyenMai(String maKM, String tenKM, Date ngayBD, Date ngayKT) {
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

	public Date getngayBD() {
		return ngayBD;
	}

	public void setngayBD(Date ngayBD) {
		this.ngayBD = ngayBD;
	}

	public Date getngayKT() {
		return ngayKT;
	}

	public void setngayKT(Date ngayKT) {
		this.ngayKT = ngayKT;
	}
	
}
