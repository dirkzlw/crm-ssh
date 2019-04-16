package com.hd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.hd.dao.SaleVisitDao;
import com.hd.entity.PageBean;
import com.hd.entity.SaleVisit;
import com.hd.service.SaleVisitService;
/**
 * �ݷü�¼ҵ���ʵ����
 *
 */
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {

	//Serviceע��Dao
	@Resource(name="saleVisitDao")
	private SaleVisitDao saleVisitDao;

	/**
	 * ҵ����ѯ�ݷü�¼���б�
	 */
	@Override
	public PageBean<SaleVisit> findByPage(DetachedCriteria criteria, Integer currentPage, Integer pageSize) {
		System.out.println("service��");
		
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
		//���õ�ǰҳ
		pageBean.setCurrentPage(currentPage);
		//����ÿҳ��ʾ����������
		pageBean.setPageSize(pageSize);
		//���ù���������¼
		Integer count = saleVisitDao.findCount(criteria);
		pageBean.setTotalCount(count);
		System.out.println(count);
		//����һ������ҳ
		Double totalPage = Math.ceil(count * 1.0 / pageSize);
		System.out.println(totalPage);
		pageBean.setTotalPage(totalPage.intValue());
		//ÿһҳ��ʾ������
		Integer begin = (currentPage - 1) * pageSize;
		List<SaleVisit> list = saleVisitDao.findByPage(criteria, begin, pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	/**
	 * ����
	 */
	@Override
	public void save(SaleVisit saleVisit) {
		//����dao����
		saleVisitDao.save(saleVisit);
	}

	/**
	 * ����id����
	 */
	@Override
	public SaleVisit findById(String visit_id) {
		return saleVisitDao.findById(visit_id);
	}

	/**
	 * ɾ��
	 */
	@Override
	public void delete(SaleVisit saleVisit2) {
		saleVisitDao.delete(saleVisit2);
		
	}

	@Override
	public void update(SaleVisit saleVisit) {
		saleVisitDao.update(saleVisit);
		
	}
	
}
