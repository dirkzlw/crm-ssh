package com.hd.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.hd.entity.Customer;
import com.hd.entity.PageBean;

public interface CustomerService {

	public void save(Customer customer);

	public Customer findByID(long id);

	public PageBean findByPage(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize);

	public void delete(Customer customer);

	public void update(Customer customer);

	public List<Customer> findAll();
}
