package com.webspringmvc.dao.impl;


import org.hibernate.Query;
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
			return 1;
		} catch (Exception e) {
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
		} catch (Exception e) {
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
	public TaiKhoan getTaiKhoan(String id, String quyen) {
		Session session = factory.getCurrentSession();
		Query q = session.createQuery("FROM TaiKhoan tk WHERE tk.quyen.maQuyen = :quyen AND username = :username");
		q.setParameter("quyen", quyen);
		q.setParameter("username", id);
		Object obj = q.uniqueResult();
		TaiKhoan taiKhoanById = null;
		if (obj != null) {
			taiKhoanById = (TaiKhoan) obj;
		}
		return taiKhoanById;
	}

	@Override
	public TaiKhoan getTaiKhoanByToken(String token, int index) {
		Session session = factory.getCurrentSession();
		Query q = null;
		TaiKhoan t = null;
		if (index == 1) {
			q = session.createQuery("FROM TaiKhoan WHERE resetPasswordToken = :token");
			q.setParameter("token", token);
			t = (TaiKhoan) q.uniqueResult();
		}else if (index == 2) {
			q = session.createQuery("FROM TaiKhoan WHERE token = :token");
			q.setParameter("token", token);
			t = (TaiKhoan) q.uniqueResult();
		}
		
		return t;
	}

	@Override
	public TaiKhoan getTaiKhoanByMaNV(String id) {
		Session session = factory.getCurrentSession();
		Query q = session.createQuery("FROM TaiKhoan tk WHERE tk.nhanVien.maNV = :maNV");
		q.setParameter("maNV", id);
		Object obj = q.uniqueResult();
		TaiKhoan taiKhoanById = null;
		if(obj != null) {
			taiKhoanById = (TaiKhoan) obj;
		}
		
		return taiKhoanById;
	}

}
