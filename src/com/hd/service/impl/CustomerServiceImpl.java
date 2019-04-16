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

	//注入Dao层
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	//保存客户
	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	//根据id查询客户
	@Override
	public Customer findByID(long id) {
		return customerDao.findById(id);
	}

	//分页查询客户
	@Override
	public PageBean findByPage(DetachedCriteria detachedCriteria, Integer currentPage,Integer pageSize) {
		PageBean<Customer> pageBean = new PageBean();
		//封装当前页数
		pageBean.setCurrentPage(currentPage);
		//封装每页显示的记录数目
		pageBean.setPageSize(pageSize);
		//封装总记录数
		Integer totalCount = customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		//封装总页数,ceil向上取整
		Double num = Math.ceil(totalCount*1.0/pageSize);
		pageBean.setTotalPage(num.intValue());
		//封装每页显示的数据集合
		Integer begin = (currentPage - 1) * pageSize;
		List<Customer> list = customerDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	//业务层删除客户
	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	//业务层修改客户
	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	//业务层查询所哟客户
	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

}
