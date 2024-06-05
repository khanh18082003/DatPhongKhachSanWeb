package com.webspringmvc.entity;

import java.sql.Timestamp;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "PHIEUDAT")
public class PhieuDat {
	@Id
	@Column(name = "MAPD")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maPD;

	@Column(name = "NGAYDAT", nullable = false)
	private Timestamp ngayDat;

	@Column(name = "NGAYBD", nullable = false)
	private Timestamp ngayBD;

	@Column(name = "NGAYKT", nullable = false)
	private Timestamp ngayKT;
	// 0 là đã đặt , 1 là thành công, -1 là hủy
	@Column(name = "TRANGTHAI", nullable = false)
	private int trangThai;
	@ManyToOne
	@JoinColumn(name = "username")
	private TaiKhoan taiKhoan;
	
	@OneToMany(mappedBy = "phieuDat", fetch = FetchType.EAGER)
	private Collection<CT_PhieuDat> ctPD;
	
	public PhieuDat() {
	}
	public PhieuDat(Timestamp ngayDat, Timestamp ngayBD, Timestamp ngayKT, TaiKhoan taiKhoan, int trangThai) {
		this.ngayDat = ngayDat;
		this.ngayBD = ngayBD;
		this.ngayKT = ngayKT;
		this.taiKhoan = taiKhoan;
		this.trangThai = trangThai;
	}
	
	public int getMaPD() {
		return maPD;
	}

	public void setMaPD(int maPD) {
		this.maPD = maPD;
	}

	public Timestamp getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(Timestamp ngayDat) {
		this.ngayDat = ngayDat;
	}

	public Timestamp getNgayBD() {
		return ngayBD;
	}

	public void setNgayBD(Timestamp ngayBD) {
		this.ngayBD = ngayBD;
	}

	public Timestamp getNgayKT() {
		return ngayKT;
	}

	public void setNgayKT(Timestamp ngayKT) {
		this.ngayKT = ngayKT;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public Collection<CT_PhieuDat> getCtPD() {
		return ctPD;
	}

	public void setCtPD(Collection<CT_PhieuDat> ctPD) {
		this.ctPD = ctPD;
	}
	
	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
}
