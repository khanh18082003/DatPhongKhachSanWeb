package com.webspringmvc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webspringmvc.dao.IRevenueDao;
import com.webspringmvc.entity.HoaDon;

@Repository
@Transactional
public class RevenueDaoImpl extends AbstractDaoImpl<HoaDon, String> implements IRevenueDao {
	@Autowired
	SessionFactory factory;

	@Override
	public int insert(HoaDon t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(HoaDon t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<HoaDon> getList() {
		Session session = factory.getCurrentSession();
		String hql = "FROM HoaDon hd " + "WHERE hd.maPD.trangThai = 1";
		Query query = session.createQuery(hql);
		return query.list();
	}

	@Override
	public List<HoaDon> getMonthlyRevenue(String year) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT YEAR(h.ngayLap) as year, MONTH(h.ngayLap) as month, SUM(h.tongTien) as total"
				+ " FROM HoaDon h WHERE YEAR(h.ngayLap) = :year AND h.maPD.trangThai = 1"
				+ " GROUP BY YEAR(h.ngayLap), MONTH(h.ngayLap)" + " ORDER BY YEAR(h.ngayLap) DESC, MONTH(h.ngayLap)";
		Query query = session.createQuery(hql);
		query.setParameter("year", Integer.parseInt(year)); // Chuyển đổi thành số nếu cần
		return query.list();
	}

	@Override
	public List<String> getYearList() {
		Session session = factory.getCurrentSession();
		String hql = "SELECT DISTINCT YEAR(h.ngayLap)" + " FROM HoaDon h WHERE h.maPD.trangThai = 1";
		Query query = session.createQuery(hql);
		return query.list();

	}

	@Override
	public List<HoaDon> getCurrentMonthRevenue() {
		Session session = factory.getCurrentSession();
		String hql = "SELECT day(h.ngayLap), SUM(h.tongTien) " + "FROM HoaDon h "
				+ "WHERE month(h.ngayLap) = MONTH(current_date()) "
				+ "AND YEAR(h.ngayLap) = YEAR(current_date()) AND h.maPD.trangThai = 1 " + "GROUP BY day(h.ngayLap)";
		Query query = session.createQuery(hql);
		return query.list();
	}

}
