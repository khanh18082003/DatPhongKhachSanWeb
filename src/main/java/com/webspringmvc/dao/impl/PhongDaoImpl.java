package com.webspringmvc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webspringmvc.dao.IPhongDao;
import com.webspringmvc.entity.LoaiPhong;
import com.webspringmvc.entity.Phong;
import com.webspringmvc.entity.TrangThaiPhong;

@Transactional
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
		} finally {
			session.close();
		}
		return 0;
	}
	
	@Override
	public int update(Phong phong) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(phong);
			t.commit();
			return 1;
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close()
		}
		return 0;
	}
	
	@Override
	public int delete(String id) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			Phong phong = (Phong) session.get(Phong.class, id);
			session.delete(phong);
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
	public List<Phong> getList() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Phong";
		Query query = session.createQuery(hql);
		List<Phong> listPhong = query.list();
		return listPhong;
	}
	
	@Override
	public List<LoaiPhong> getLP() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM LoaiPhong";
		Query query = session.createQuery(hql);
		List<LoaiPhong> listLP = query.list();
		return listLP;
	}
	
	@Override
	public List<TrangThaiPhong> getTTP() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM TrangThaiPhong";
		Query query = session.createQuery(hql);
		List<TrangThaiPhong> listTTP = query.list();
		return listTTP;
	}

	@Override
	public List<Phong> getRemainingRoom(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
