package com.webspringmvc.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webspringmvc.dao.INhanVienDao;
import com.webspringmvc.entity.NhanVien;

@Transactional
@Repository
public class NhanVienDaoImpl implements INhanVienDao {
	
	@Autowired
	SessionFactory factory;
	
	@Override
	public int insert(NhanVien t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(NhanVien t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public NhanVien getNhanVienByEmail(String email) {
		Session session = factory.getCurrentSession();
		Query q = session.createQuery("FROM NhanVien WHERE email = :email");
		q.setParameter("email", email);
		Object obj = q.uniqueResult();
		NhanVien nv = null;
		if (obj != null) {
			nv = (NhanVien) obj;
		}
		return nv;
	}

}
