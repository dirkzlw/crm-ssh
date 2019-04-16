package com.hd.dao;

import java.util.List;

import com.hd.entity.BaseDict;

/**
 * �ֵ�Dao�ӿ�
 * @author Dirk  Zhou
 *
 */
public interface BaseDictDao extends BaseDao<BaseDict>{

	List<BaseDict> findByTypeCode(String dict_type_code);

}
