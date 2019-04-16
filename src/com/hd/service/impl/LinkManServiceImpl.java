package com.hd.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.hd.dao.LinkManDao;
import com.hd.entity.LinkMan;
import com.hd.entity.PageBean;
import com.hd.service.LinkManService;

/**
 * ��ϵ��ҵ���ʵ����
 */
@Transactional
public class LinkManServiceImpl implements LinkManService {
	//ע��Dao
	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	@Override
	public PageBean findAll(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize) {
		// ��װpageBean
		PageBean<LinkMan> pageBean = new PageBean<>();
		//��ǰҳ��
		pageBean.setCurrentPage(currentPage);
		//ÿҳ��¼��
		pageBean.setPageSize(pageSize);
		//�ܼ�¼��
		Integer totalCount = linkManDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		
		//��ҳ��
		Double num = Math.ceil(totalCount * 1.0 / pageSize);
		pageBean.setTotalPage(num.intValue());
		//ÿҳ��ʾ������
		Integer begin = (currentPage -1) * pageSize;
		List<LinkMan> list = linkManDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	//ҵ��㱣����ϵ��
	@Override
	public void save(LinkMan linkMan) {
		linkManDao.save(linkMan);
	}

	//����Id��ѯ��ϵ��
	@Override
	public LinkMan findById(Long lkm_id) {
		return linkManDao.findById(lkm_id);
	}

	//ҵ����޸���ϵ��
	@Override
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}

	/**
	 * ҵ���ɾ����ϵ��
	 */
	@Override
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
	}
	
}
