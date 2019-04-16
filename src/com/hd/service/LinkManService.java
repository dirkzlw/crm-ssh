package com.hd.service;

import org.hibernate.criterion.DetachedCriteria;

import com.hd.entity.LinkMan;
import com.hd.entity.PageBean;

/**
 * ��ϵ��Service�ӿ�
 */
public interface LinkManService {

	PageBean findAll(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize);

	void save(LinkMan linkMan);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	void delete(LinkMan linkMan);

}
