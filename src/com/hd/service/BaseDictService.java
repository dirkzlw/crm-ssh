package com.hd.service;

import java.util.List;

import com.hd.entity.BaseDict;

/**
 * 字典的业务层的接口
 */
public interface BaseDictService {

	List<BaseDict> findByTypeCode(String dict_type_code);

}
