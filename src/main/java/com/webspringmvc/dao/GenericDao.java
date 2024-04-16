package com.webspringmvc.dao;

public interface GenericDao<T, E> {
	int insert(T t);
	int update(T t);
	int delete(E id);
}
