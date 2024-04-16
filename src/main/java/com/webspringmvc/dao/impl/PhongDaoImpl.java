package com.webspringmvc.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webspringmvc.dao.IPhongDao;
import com.webspringmvc.entity.Phong;

@Repository
public class PhongDaoImpl extends AbstractDaoImpl<Phong, String> implements IPhongDao {
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public int insert(Phong phong) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(phong);
			t.commit();
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int update(Phong phong) {
		return 0;
	}
	
	@Override
	public int delete(String maPhong) {
		return 0;
	}
	
	@Override
	public List<Phong> getList() {
		
		return null;
	}

}
