package com.webspringmvc.service;

import java.util.List;

import com.webspringmvc.entity.BinhLuan;

public interface IBinhLuanService {
	int insert(BinhLuan bl);
	int update(BinhLuan bl);
	int delete(int id);
	List<BinhLuan> getList(String idHP);
	List<BinhLuan> getBlListOfUser(String idHP, int id);
	BinhLuan getLatestReview(String idHP, int id);
}
