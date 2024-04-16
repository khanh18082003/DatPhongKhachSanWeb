package com.webspringmvc.dao;

import java.util.List;

import com.webspringmvc.entity.Phong;

public interface IPhongDao extends GenericDao<Phong, String> {
	List<Phong> getList();
}
