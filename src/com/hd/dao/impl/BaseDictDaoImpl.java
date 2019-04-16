package com.hd.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hd.dao.BaseDictDao;
import com.hd.entity.BaseDict;
/**
 * 字典Dao的实现类
 * @author Dirk  Zhou
 *
 */
public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao {

	/**
	 * 根据类型编码查询字典数据
	 */
	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return (List<BaseDict>) this.getHibernateTemplate().find("from BaseDict where dict_type_code = ?", dict_type_code);
	}

}
