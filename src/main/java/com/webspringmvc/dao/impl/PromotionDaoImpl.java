package com.webspringmvc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webspringmvc.dao.IPromotionDao;
import com.webspringmvc.entity.KhuyenMai;

@Transactional
@Repository
public class PromotionDaoImpl extends AbstractDaoImpl<KhuyenMai, String> implements IPromotionDao {
	@Autowired
	SessionFactory factory;
	@Override
	public int insert(KhuyenMai km) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(km);
			t.commit();
			return 1;
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}
		return 0;
	}

	@Override
	public int update(KhuyenMai km) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(km);
			t.commit();
			return 1;
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}
		return 0;
	}

	@Override
	public int delete(String id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			KhuyenMai khuyenMai = (KhuyenMai) session.get(KhuyenMai.class, id);
			session.delete(khuyenMai);
			t.commit();
			return 1;
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}
		return 0;
	}

	@Override
	public List<KhuyenMai> getList() {
		Session session = factory.getCurrentSession();
		String hql = "FROM KhuyenMai";
		Query query = session.createQuery(hql);		
		return query.list();
	}

	@Override
	public List<KhuyenMai> getRemainingPromo(String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM KhuyenMai km WHERE km.maKM != :editedID";
		Query query = session.createQuery(hql);
		query.setParameter("editedID", id);
		return query.list();
	}

}
