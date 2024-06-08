package com.webspringmvc.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BINHLUAN")
public class BinhLuan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "MAKH", nullable = false)
	private KhachHang kh;
	
	@ManyToOne
	@JoinColumn(name = "IDHP", nullable = false)
	private HangPhong hangPhong;
	
	@Column(name = "RATING")
	private int rating;
	
	@Column(name = "COMMENT")
	private String comment;
	
	@Column(name = "CREATE_DATE")
	private Timestamp createDate;
	
	public BinhLuan() {
		
	}

	public BinhLuan(int id, KhachHang kh, HangPhong hangPhong, int rating, String comment, Timestamp createDate) {
		this.id = id;
		this.kh = kh;
		this.hangPhong = hangPhong;
		this.rating = rating;
		this.comment = comment;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public KhachHang getKh() {
		return kh;
	}

	public void setKh(KhachHang kh) {
		this.kh = kh;
	}

	public HangPhong getHangPhong() {
		return hangPhong;
	}

	public void setHangPhong(HangPhong hangPhong) {
		this.hangPhong = hangPhong;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	
	
}
