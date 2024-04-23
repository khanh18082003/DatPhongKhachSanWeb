package com.webspringmvc.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webspringmvc.dao.ITaiKhoanDao;
import com.webspringmvc.entity.TaiKhoan;

@Transactional
@Repository
public class TaiKhoanDaoImpl implements ITaiKhoanDao {
	@Autowired
	SessionFactory factory;
	
	@Override
	public int insert(TaiKhoan t) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(t);
			transaction.commit();
			System.out.println(t.getUsername());
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(TaiKhoan t) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(t);
			transaction.commit();
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TaiKhoan getTaiKhoan(String id) {
		Session session = factory.getCurrentSession();
		TaiKhoan taiKhoanById = (TaiKhoan) session.get(TaiKhoan.class, id);
		return taiKhoanById;
	}

	@Override
	public List<TaiKhoan> getList() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
