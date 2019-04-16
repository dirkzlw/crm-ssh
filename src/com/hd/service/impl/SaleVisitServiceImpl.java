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
 * 拜访记录业务层实现类
 *
 */
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {

	//Service注入Dao
	@Resource(name="saleVisitDao")
	private SaleVisitDao saleVisitDao;

	/**
	 * 业务层查询拜访记录的列表
	 */
	@Override
	public PageBean<SaleVisit> findByPage(DetachedCriteria criteria, Integer currentPage, Integer pageSize) {
		System.out.println("service层");
		
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
		//设置当前页
		pageBean.setCurrentPage(currentPage);
		//设置每页显示多少条数据
		pageBean.setPageSize(pageSize);
		//设置共多少条记录
		Integer count = saleVisitDao.findCount(criteria);
		pageBean.setTotalCount(count);
		System.out.println(count);
		//设置一共多少页
		Double totalPage = Math.ceil(count * 1.0 / pageSize);
		System.out.println(totalPage);
		pageBean.setTotalPage(totalPage.intValue());
		//每一页显示的数据
		Integer begin = (currentPage - 1) * pageSize;
		List<SaleVisit> list = saleVisitDao.findByPage(criteria, begin, pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	/**
	 * 保存
	 */
	@Override
	public void save(SaleVisit saleVisit) {
		//调用dao保存
		saleVisitDao.save(saleVisit);
	}

	/**
	 * 根据id查找
	 */
	@Override
	public SaleVisit findById(String visit_id) {
		return saleVisitDao.findById(visit_id);
	}

	/**
	 * 删除
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
