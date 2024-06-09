package com.webspringmvc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webspringmvc.dao.IBinhLuanDao;
import com.webspringmvc.entity.BinhLuan;

@Transactional
@Repository
public class BinhLuanDaoImpl implements IBinhLuanDao {

	@Autowired
	SessionFactory factory;

	@Override
	public int insert(BinhLuan t) {
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
	public int update(BinhLuan t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query q = session.createQuery("DELETE BinhLuan WHERE id = :id");
			q.setParameter("id", id);
			int res = q.executeUpdate();
			transaction.commit();
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BinhLuan> getList(String idHP) {
		Session session = factory.getCurrentSession();
		Query q = session.createQuery("From BinhLuan bl WHERE bl.hangPhong.idHP = :idHP");
		q.setParameter("idHP", idHP);
		List<BinhLuan> blList = q.list();
		return blList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BinhLuan> getBlListOfUser(String idHP, int id) {
		Session session = factory.getCurrentSession();
		Query q = session.createQuery("From BinhLuan bl WHERE hangPhong.idHP = :idHP AND kh.maKH = :id");
		q.setParameter("idHP", idHP);
		q.setParameter("id", id);
		List<BinhLuan> blList = q.list();
		return blList;
	}

	@Override
	public BinhLuan getLatestReview(String idHP, int id) {
		Session session = factory.getCurrentSession();
		Query q = session.createQuery(
				"From BinhLuan bl WHERE hangPhong.idHP = :idHP AND kh.maKH = :id ORDER BY createDate DESC");
		q.setParameter("idHP", idHP);
		q.setParameter("id", id);
		q.setMaxResults(1);
		Object obj = q.uniqueResult();
		BinhLuan bl = null;
		if (obj != null) {
			bl = (BinhLuan) q.uniqueResult();
		}

		return bl;
	}

}
