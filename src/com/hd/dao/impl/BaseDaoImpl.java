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
 * 通用的Dao的实现类
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class clazz;

	public BaseDaoImpl() {
		//反射：第一部获取Class
		Class clazz = this.getClass();
		//获得参数化类型
		Type type = clazz.getGenericSuperclass();
		//强转
		ParameterizedType pType = (ParameterizedType) type;
		//获得市级类型参数的数组
		Type[] types = pType.getActualTypeArguments();
		//只获得一个实际类型参数
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

	//查询所有
	@Override
	public List<T> findAll() {
		//clazz.getSimpleName()   得到全名称
		return (List<T>) this.getHibernateTemplate().find("from " + clazz.getSimpleName());
	}

	//统计个数
	@Override
	public Integer findCount(DetachedCriteria criteria) {
		//设置条件
		criteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list.size()>0){
			return list.get(0).intValue();
		}
		return null;
	}

	//分页查询
	@Override
	public List<T> findByPage(DetachedCriteria criteria, Integer begin, Integer pageSize) {
		//清空条件
		criteria.setProjection(null);
		return (List<T>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}

}
