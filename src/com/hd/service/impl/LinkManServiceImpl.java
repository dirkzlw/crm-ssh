package com.hd.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.hd.dao.LinkManDao;
import com.hd.entity.LinkMan;
import com.hd.entity.PageBean;
import com.hd.service.LinkManService;

/**
 * 联系人业务层实现类
 */
@Transactional
public class LinkManServiceImpl implements LinkManService {
	//注入Dao
	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	@Override
	public PageBean findAll(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize) {
		// 封装pageBean
		PageBean<LinkMan> pageBean = new PageBean<>();
		//当前页数
		pageBean.setCurrentPage(currentPage);
		//每页记录数
		pageBean.setPageSize(pageSize);
		//总记录数
		Integer totalCount = linkManDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		
		//总页数
		Double num = Math.ceil(totalCount * 1.0 / pageSize);
		pageBean.setTotalPage(num.intValue());
		//每页显示的数据
		Integer begin = (currentPage -1) * pageSize;
		List<LinkMan> list = linkManDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	//业务层保存联系人
	@Override
	public void save(LinkMan linkMan) {
		linkManDao.save(linkMan);
	}

	//根据Id查询联系人
	@Override
	public LinkMan findById(Long lkm_id) {
		return linkManDao.findById(lkm_id);
	}

	//业务层修改联系人
	@Override
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}

	/**
	 * 业务层删除联系人
	 */
	@Override
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
	}
	
}
