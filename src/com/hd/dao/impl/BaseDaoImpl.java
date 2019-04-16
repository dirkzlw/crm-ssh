package com.hd.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hd.dao.BaseDao;

/**
 * ͨ�õ�Dao��ʵ����
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class clazz;

	public BaseDaoImpl() {
		//���䣺��һ����ȡClass
		Class clazz = this.getClass();
		//��ò���������
		Type type = clazz.getGenericSuperclass();
		//ǿת
		ParameterizedType pType = (ParameterizedType) type;
		//����м����Ͳ���������
		Type[] types = pType.getActualTypeArguments();
		//ֻ���һ��ʵ�����Ͳ���
		this.clazz = (Class) types[0];
	}
	
	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	//��ѯ����
	@Override
	public List<T> findAll() {
		//clazz.getSimpleName()   �õ�ȫ����
		return (List<T>) this.getHibernateTemplate().find("from " + clazz.getSimpleName());
	}

	//ͳ�Ƹ���
	@Override
	public Integer findCount(DetachedCriteria criteria) {
		//��������
		criteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list.size()>0){
			return list.get(0).intValue();
		}
		return null;
	}

	//��ҳ��ѯ
	@Override
	public List<T> findByPage(DetachedCriteria criteria, Integer begin, Integer pageSize) {
		//�������
		criteria.setProjection(null);
		return (List<T>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}

}
