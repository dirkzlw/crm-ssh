package com.hd.service;

import java.util.List;

import com.hd.entity.BaseDict;

/**
 * �ֵ��ҵ���Ľӿ�
 */
public interface BaseDictService {

	List<BaseDict> findByTypeCode(String dict_type_code);

}
