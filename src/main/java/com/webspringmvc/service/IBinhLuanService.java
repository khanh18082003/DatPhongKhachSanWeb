package com.webspringmvc.service;

import java.util.List;

import com.webspringmvc.entity.BinhLuan;

public interface IBinhLuanService {
	int insert(BinhLuan bl);
	int update(BinhLuan bl);
	int delete(BinhLuan bl);
	List<BinhLuan> getList(String idHP);
}
