package com.webspringmvc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webspringmvc.dao.IRoomDao;
import com.webspringmvc.entity.HangPhong;
import com.webspringmvc.entity.KieuPhong;
import com.webspringmvc.entity.LoaiPhong;

@Repository
@Transactional
public class RoomDaoImpl extends AbstractDaoImpl<HangPhong, String> implements IRoomDao {
	@Autowired
	SessionFactory factory;

	@Override
	public int insert(HangPhong hangPhong) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(hangPhong);
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
	public int update(HangPhong hangPhong) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(hangPhong);
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
			HangPhong hangPhong = (HangPhong) session.get(HangPhong.class, id);
			session.delete(hangPhong);
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
	public List<HangPhong> getList() {
		Session session = factory.getCurrentSession();
		String hql = "FROM HangPhong";
		Query query = session.createQuery(hql);
		List<HangPhong> listHP = query.list();
		return listHP;
	}

	@Override
	public List<HangPhong> getRemainingRoom(String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM HangPhong hp WHERE hp.idHP != :editedID";
		Query query = session.createQuery(hql);
		query.setParameter("editedID", id);
		List<HangPhong> otherHPs = query.list();
		return otherHPs;
	}

	@Override
	public List<LoaiPhong> getLP() {
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiPhong";
		Query query = session.createQuery(hql);
		List<LoaiPhong> listLP = query.list();
		return listLP;
	}

	@Override
	public List<KieuPhong> getKP() {
		Session session = factory.getCurrentSession();
		String hql = "FROM KieuPhong";
		Query query = session.createQuery(hql);
		List<KieuPhong> listKP = query.list();
		return listKP;
	}

}
