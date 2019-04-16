package com.hd.dao;

import java.util.List;

import com.hd.entity.BaseDict;

/**
 * ×ÖµäDao½Ó¿Ú
 * @author Dirk  Zhou
 *
 */
public interface BaseDictDao extends BaseDao<BaseDict>{

	List<BaseDict> findByTypeCode(String dict_type_code);

}
