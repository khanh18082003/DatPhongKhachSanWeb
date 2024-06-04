package com.webspringmvc.service;

import java.util.List;

import com.webspringmvc.entity.HangPhong;
import com.webspringmvc.entity.KieuPhong;
import com.webspringmvc.entity.LoaiPhong;

public interface IRoomService {
	int insert(HangPhong hp);
	int update(HangPhong hp);
	int delete(String id);
	List<HangPhong> getList();
	List<HangPhong> getRemainingRoom(String id);
	List<LoaiPhong> getLP();
	List<KieuPhong> getKP();
	boolean isIdValid(String id);
	boolean isNameValid(String name);
	String getFileExtension(String filePath);
}
