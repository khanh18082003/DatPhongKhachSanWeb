package com.webspringmvc.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.webspringmvc.dao.IRoomDao;
import com.webspringmvc.entity.HangPhong;
import com.webspringmvc.entity.KieuPhong;
import com.webspringmvc.entity.LoaiPhong;
import com.webspringmvc.service.IRoomService;

@Service
public class RoomService implements IRoomService {
	@Autowired
	private IRoomDao roomDao;

	@Override
	public int insert(HangPhong hp) {
		return roomDao.insert(hp);
	}

	@Override
	public int update(HangPhong hp) {
		return roomDao.update(hp);
	}

	@Override
	public int delete(String id) {
		return roomDao.delete(id);
	}

	@Override
	public List<HangPhong> getList() {
		return roomDao.getList();
	}

	@Override
	public List<HangPhong> getRemainingRoom(String id) {
		return roomDao.getRemainingRoom(id);
	}

	@Override
	public List<LoaiPhong> getLP() {
		return roomDao.getLP();
	}

	@Override
	public List<KieuPhong> getKP() {
		return roomDao.getKP();
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
