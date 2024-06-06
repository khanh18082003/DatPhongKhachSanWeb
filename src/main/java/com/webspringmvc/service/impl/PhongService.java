package com.webspringmvc.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webspringmvc.dao.IPhongDao;
import com.webspringmvc.entity.HangPhong;
import com.webspringmvc.entity.KieuPhong;
import com.webspringmvc.entity.LoaiPhong;
import com.webspringmvc.entity.Phong;
import com.webspringmvc.entity.TrangThaiPhong;
import com.webspringmvc.service.IPhongService;

@Service
public class PhongService implements IPhongService {
	@Autowired
	private IPhongDao phongDao;
	
	@Override
	public int insert(Phong phong) {
		return phongDao.insert(phong);
	}

	@Override
	public int update(Phong phong) {
		return phongDao.update(phong);
	}

	@Override
	public int delete(String id) {
		return phongDao.delete(id);
	}

	@Override
	public List<Phong> getList() {
		return phongDao.getList();
	}

	@Override
	public List<Phong> getRemainingRoom(String id) {
		return phongDao.getRemainingRoom(id);
	}

	@Override
	public List<LoaiPhong> getLP() {
		return phongDao.getLP();
	}
	
	@Override
	public List<TrangThaiPhong> getTTP() {
		return phongDao.getTTP();
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

	@Override
	public String getFileExtension(String filePath) {
		int lastDotIndex = filePath.lastIndexOf('.');
		if (lastDotIndex > 0) {
			return filePath.substring(lastDotIndex + 1);
		}
		return "";
	}
	
}
