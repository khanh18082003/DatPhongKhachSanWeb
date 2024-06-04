package com.webspringmvc.dao;

import java.util.List;

import com.webspringmvc.entity.HangPhong;
import com.webspringmvc.entity.KieuPhong;
import com.webspringmvc.entity.LoaiPhong;

public interface IRoomDao extends GenericDao<HangPhong, String> {
	List<HangPhong> getList(); 
	List<HangPhong> getRemainingRoom(String id);
	List<LoaiPhong> getLP();
	List<KieuPhong> getKP();
}
