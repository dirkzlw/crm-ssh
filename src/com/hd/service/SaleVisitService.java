package com.hd.service;

import org.hibernate.criterion.DetachedCriteria;

import com.hd.entity.PageBean;
import com.hd.entity.SaleVisit;

/**
 * �ͻ��ݷü�¼��ҵ���ӿ�
 */
public interface SaleVisitService {

	PageBean<SaleVisit> findByPage(DetachedCriteria criteria, Integer currentPage, Integer pageSize);

	void save(SaleVisit saleVisit);

	SaleVisit findById(String visit_id);

	void delete(SaleVisit saleVisit2);

	void update(SaleVisit saleVisit);

}
