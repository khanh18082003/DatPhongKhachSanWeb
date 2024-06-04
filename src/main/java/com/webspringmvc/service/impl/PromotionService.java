package com.webspringmvc.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webspringmvc.dao.IPromotionDao;
import com.webspringmvc.entity.KhuyenMai;
import com.webspringmvc.service.IPromotionService;

@Service
public class PromotionService implements IPromotionService {
	@Autowired
	IPromotionDao promotionDao;

	@Override
	public int insert(KhuyenMai km) {
		return promotionDao.insert(km);
	}

	@Override
	public int update(KhuyenMai km) {
		return promotionDao.update(km);
	}

	@Override
	public int delete(String id) {
		return promotionDao.delete(id);
	}

	@Override
	public List<KhuyenMai> getList() {
		return promotionDao.getList();
	}

	@Override
	public List<KhuyenMai> getRemainingRoom(String id) {
		return promotionDao.getRemainingPromo(id);
	}

	@Override
	public boolean isIdValid(String id) {
		String regex = "[a-zA-Z0-9_]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(id);
		return matcher.matches();
	}

	@Override
	public boolean isNameValid(String name) {
		String regex = "[a-zA-Z0-9_ ]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(name);
		return matcher.matches();
	}

}
