package com.webspringmvc.dao;

import java.util.List;

import com.webspringmvc.entity.LoaiPhong;
import com.webspringmvc.entity.Phong;
import com.webspringmvc.entity.TrangThaiPhong;

public interface IPhongDao extends GenericDao<Phong, String> {
	List<Phong> getList();
	List<LoaiPhong> getLP();
	List<TrangThaiPhong> getTTP();
	List<Phong> getRemainingRoom(String id);
}
