package com.webspringmvc.dao;

import java.util.List;

import com.webspringmvc.entity.BinhLuan;

public interface IBinhLuanDao extends GenericDao<BinhLuan, Integer> {
	List<BinhLuan> getList(String idHP);
	List<BinhLuan> getBlListOfUser(String idHP, int id);
	BinhLuan getLatestReview(String idHP, int id);
}
