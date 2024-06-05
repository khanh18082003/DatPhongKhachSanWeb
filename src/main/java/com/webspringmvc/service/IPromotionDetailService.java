package com.webspringmvc.service;

import java.util.List;

import com.webspringmvc.entity.CT_KhuyenMai;

public interface IPromotionDetailService {
	int insert(CT_KhuyenMai ctkm);
	int update(CT_KhuyenMai ctkm);
	int delete(int id);
	List<CT_KhuyenMai> getList(String id);
}
