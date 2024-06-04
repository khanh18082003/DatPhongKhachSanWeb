package com.webspringmvc.dao;

import java.util.List;

import com.webspringmvc.entity.CT_KhuyenMai;

public interface IPromotionDetailDao extends GenericDao<CT_KhuyenMai, Integer> {
	List<CT_KhuyenMai> getList(String id); 
}
