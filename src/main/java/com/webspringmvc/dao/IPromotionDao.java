package com.webspringmvc.dao;

import java.util.List;

import com.webspringmvc.entity.KhuyenMai;

public interface IPromotionDao extends GenericDao<KhuyenMai, String> {
	List<KhuyenMai> getList(); 
	List<KhuyenMai> getRemainingPromo(String id);
}
