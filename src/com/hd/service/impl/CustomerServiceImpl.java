package com.hd.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.hd.dao.CustomerDao;
import com.hd.entity.Customer;
import com.hd.entity.PageBean;
import com.hd.service.CustomerService;

@Transactional
public class CustomerServiceImpl implements CustomerService {

	//ע��Dao��
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	//����ͻ�
	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	//����id��ѯ�ͻ�
	@Override
	public Customer findByID(long id) {
		return customerDao.findById(id);
	}

	//��ҳ��ѯ�ͻ�
	@Override
	public PageBean findByPage(DetachedCriteria detachedCriteria, Integer currentPage,Integer pageSize) {
		PageBean<Customer> pageBean = new PageBean();
		//��װ��ǰҳ��
		pageBean.setCurrentPage(currentPage);
		//��װÿҳ��ʾ�ļ�¼��Ŀ
		pageBean.setPageSize(pageSize);
		//��װ�ܼ�¼��
		Integer totalCount = customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		//��װ��ҳ��,ceil����ȡ��
		Double num = Math.ceil(totalCount*1.0/pageSize);
		pageBean.setTotalPage(num.intValue());
		//��װÿҳ��ʾ�����ݼ���
		Integer begin = (currentPage - 1) * pageSize;
		List<Customer> list = customerDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	//ҵ���ɾ���ͻ�
	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	//ҵ����޸Ŀͻ�
	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	//ҵ����ѯ��Ӵ�ͻ�
	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

}
