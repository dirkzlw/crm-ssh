package com.hd.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hd.dao.BaseDictDao;
import com.hd.entity.BaseDict;
import com.hd.service.BaseDictService;

/**
 * 字典业务层的实现类
 * @author Dirk  Zhou
 *
 */
@Transactional
public class BaseDictServiceImpl implements BaseDictService {
	
	//注入Dao
	private BaseDictDao baseDictDao;
	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}
	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return baseDictDao.findByTypeCode(dict_type_code);
	}
	
}
