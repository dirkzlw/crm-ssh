package com.hd.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hd.dao.BaseDictDao;
import com.hd.entity.BaseDict;
/**
 * �ֵ�Dao��ʵ����
 * @author Dirk  Zhou
 *
 */
public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao {

	/**
	 * �������ͱ����ѯ�ֵ�����
	 */
	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return (List<BaseDict>) this.getHibernateTemplate().find("from BaseDict where dict_type_code = ?", dict_type_code);
	}

}
