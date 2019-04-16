package com.hd.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 通用的Dao的接口
 */
public interface BaseDao<T> {
	
	//抽取增删改
	public void save(T t);
	
	public void update(T t);
	
	public void delete(T t);
	
	//抽取查询
	public T findById(Serializable id);
	
	public List<T> findAll();
	
	//统计个数
	public Integer findCount(DetachedCriteria criteria);
	
	//分页查询
	public List<T> findByPage(DetachedCriteria criteria,Integer begin,Integer pageSize);
	
}
