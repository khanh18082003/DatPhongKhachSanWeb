package com.webspringmvc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webspringmvc.dao.IUserDao;
import com.webspringmvc.entity.KhachHang;

@Transactional
@Repository
public class UserDaoImpl implements IUserDao {
	
	@Autowired
	SessionFactory factory;
	
	@Override
	public int insert(KhachHang t) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(t);
			transaction.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(KhachHang t) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(t);
			transaction.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(String id) {
		return 0;
	}

	@Override
	public KhachHang getUserById(String id) {
		Session session = factory.getCurrentSession();
		Query q = session.createQuery("FROM KhachHang kh WHERE maKH = :id");
		q.setParameter("id", id);
		KhachHang kh = null;
		Object obj = q.uniqueResult();
		if (obj != null) {
			kh = (KhachHang) obj;
		}
		 
		return kh;
	}

	@Override
	public KhachHang getUserByEmail(String email) {
		Session session = factory.getCurrentSession();
		Query q = session.createQuery("FROM KhachHang WHERE email.username = :email");
		q.setParameter("email", email);
		KhachHang kh = null;
		Object obj = q.uniqueResult();
		if (obj != null) {
			kh = (KhachHang) obj;
		}
		return kh;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KhachHang> getList() {
		Session session = factory.getCurrentSession();
		Query q = session.createQuery("FROM KhachHang");
		List<KhachHang> khList = q.list();

		return khList;
	}
	
}
