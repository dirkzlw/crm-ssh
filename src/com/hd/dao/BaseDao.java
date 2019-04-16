package com.hd.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * ͨ�õ�Dao�Ľӿ�
 */
public interface BaseDao<T> {
	
	//��ȡ��ɾ��
	public void save(T t);
	
	public void update(T t);
	
	public void delete(T t);
	
	//��ȡ��ѯ
	public T findById(Serializable id);
	
	public List<T> findAll();
	
	//ͳ�Ƹ���
	public Integer findCount(DetachedCriteria criteria);
	
	//��ҳ��ѯ
	public List<T> findByPage(DetachedCriteria criteria,Integer begin,Integer pageSize);
	
}
