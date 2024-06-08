package com.webspringmvc.service;

import java.util.List;

import com.webspringmvc.entity.LoaiPhong;
import com.webspringmvc.entity.Phong;
import com.webspringmvc.entity.TrangThaiPhong;

public interface IPhongService {
	int insert(Phong phong);
	int update(Phong phong);
	int delete(String id);
	List<Phong> getList();
	List<Phong> getRemainingRoom(String id);
	List<LoaiPhong> getLP();
	List<TrangThaiPhong> getTTP();
	boolean isIdValid(String id);
	boolean isNameValid(String name);
	String getFileExtension(String filePath);
}
