package com.webspringmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webspringmvc.dao.IPromotionDetailDao;
import com.webspringmvc.entity.CT_KhuyenMai;
import com.webspringmvc.service.IPromotionDetailService;

@Service
public class PromotionDetailService implements IPromotionDetailService {
	@Autowired
	IPromotionDetailDao promotionDetailDao;
	
	@Override
	public int insert(CT_KhuyenMai ctkm) {
		return promotionDetailDao.insert(ctkm);
	}

	@Override
	public int update(CT_KhuyenMai ctkm) {
		return promotionDetailDao.update(ctkm);
	}

	@Override
	public int delete(int id) {
		return promotionDetailDao.delete(id);
	}

	@Override
	public List<CT_KhuyenMai> getList(String id) {
		return promotionDetailDao.getList(id);
	}

}
