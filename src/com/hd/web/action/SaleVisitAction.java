package com.hd.web.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.hd.entity.Customer;
import com.hd.entity.PageBean;
import com.hd.entity.SaleVisit;
import com.hd.entity.User;
import com.hd.service.CustomerService;
import com.hd.service.SaleVisitService;
import com.hd.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
	private static final long serialVersionUID = 1L;
	
	//模型驱动使用的对象,madan，再忘记new对象不是人
	private SaleVisit saleVisit = new SaleVisit();
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}

	//在Action中注入Service,不必提供set
	@Resource(name="saleVisitService")
	private SaleVisitService saleVisitService;
	@Resource(name="customerService")
	private CustomerService customerService;
	@Resource(name="userService")
	private UserService userService;
	
	//接收分页数据
	private Integer currentPage =1;
	public void setCurrentPage(Integer currentPage) {
		if(currentPage == null){
			currentPage = 1;
		}
		this.currentPage = currentPage;
	}
	private Integer pageSize =3;
	public void setPageSize(Integer pageSize) {
		if(pageSize == null){
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}

	//接收visit_end_time数据
	private Date visit_end_time;
	//回显数据需要get方法，编程action属性，action存在于栈
	public Date getVisit_end_time() {
		return visit_end_time;
	}
	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}

	/**
	 * 查询客户拜访记录列表：findAll
	 */
	public String findAll(){
		
		//创建离线条件查询对象
		DetachedCriteria criteria = DetachedCriteria.forClass(SaleVisit.class);
		//设置条件
		if(saleVisit.getVisit_time() != null){
			criteria.add(Restrictions.ge("visit_time", saleVisit.getVisit_time()));  //ge是>=
		}
		if(visit_end_time != null){
			criteria.add(Restrictions.le("visit_time", visit_end_time));
		}
		
		//调用业务层
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(criteria,currentPage,pageSize);
		//存入值栈
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return "findAll";
	}
	
	/**
	 * 拜访记录跳转到添加页面
	 */
	public String saveUI(){
		return "saveUI";
	}
	
	/**
	 * 保存客户拜访记录
	 */
	public String save(){
		//调用业务层保存拜访记录
		saleVisitService.save(saleVisit);
		return "saveSuccess";
	}
	
	/**
	 * 删除客户拜访记录
	 * @return
	 */
	public String delete(){

		//根据id得到拜访记录
		SaleVisit saleVisit2 = saleVisitService.findById(saleVisit.getVisit_id());
		saleVisitService.delete(saleVisit2);
		
		return "deleteSuccess";
	}
	
	/**
	 * 显示修改页面
	 */
	public String edit(){

		//获取需要回显的数据
		List<Customer> customerList = customerService.findAll();
		System.out.println(customerList.size());
		List<User> userList = userService.findAll();
		saleVisit = saleVisitService.findById(saleVisit.getVisit_id());
		ActionContext.getContext().getValueStack().set("customerList", customerList);
		//保存数据到值栈
		ActionContext.getContext().getValueStack().set("userList", userList);
		ActionContext.getContext().getValueStack().push(saleVisit);
		
		return "editSuccess";
	}
	
	/**
	 * 修改拜访信息并保存
	 */
	public String update(){
		//调用业务层
		saleVisitService.update(saleVisit);
		return "updateSuccess";
	}
}

