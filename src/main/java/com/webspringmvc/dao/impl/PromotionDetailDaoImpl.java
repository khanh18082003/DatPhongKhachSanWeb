package com.webspringmvc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webspringmvc.dao.IPromotionDetailDao;
import com.webspringmvc.entity.CT_KhuyenMai;

@Transactional
@Repository
public class PromotionDetailDaoImpl extends AbstractDaoImpl<CT_KhuyenMai, Integer> implements IPromotionDetailDao {
	@Autowired
	SessionFactory factory;

	@Override
	public int insert(CT_KhuyenMai ctkm) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(ctkm);
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
	public int update(CT_KhuyenMai ctkm) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.update(ctkm);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}
		return 0;
	}

	@Override
	public int delete(Integer id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			CT_KhuyenMai CTKM = (CT_KhuyenMai) session.get(CT_KhuyenMai.class, id);
			session.delete(CTKM);
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
	public List<CT_KhuyenMai> getList(String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CT_KhuyenMai WHERE maKM = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return query.list();
	}

}
