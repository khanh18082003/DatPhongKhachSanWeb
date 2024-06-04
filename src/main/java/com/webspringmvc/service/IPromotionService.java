package com.webspringmvc.service;

import java.util.List;

import com.webspringmvc.entity.KhuyenMai;

public interface IPromotionService {
	int insert(KhuyenMai km);
	int update(KhuyenMai km);
	int delete(String id);
	List<KhuyenMai> getList();
	List<KhuyenMai> getRemainingRoom(String id);
	boolean isIdValid(String id);
	boolean isNameValid(String name);
}
