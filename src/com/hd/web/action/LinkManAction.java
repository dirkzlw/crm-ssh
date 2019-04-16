package com.hd.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.hd.entity.Customer;
import com.hd.entity.LinkMan;
import com.hd.entity.PageBean;
import com.hd.service.CustomerService;
import com.hd.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

	//模型驱动使用的对象
	private LinkMan linkMan = new LinkMan();
	@Override
	public LinkMan getModel() {
		return linkMan;
	}
	
	//注入LinkmanService
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	//注入CustomerService
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	//分页参数：
	private Integer currentPage = 1;
	public void setCurrentPage(Integer currentPage) {
		if(currentPage == null){
			currentPage = 1;
		}
		this.currentPage = currentPage;
	}
	private Integer pageSize = 3;
	public void setPageSize(Integer pageSize) {
		if(pageSize == null){
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}

	/**
	 * 查询联系人列表
	 */
	public String findAll(){
		//创建离线条件查询
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		//设置条件
		if(linkMan.getLkm_name() != null){
			//设置按名称查询的条件
			detachedCriteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		if(linkMan.getLkm_gender() != null && !"".equals(linkMan.getLkm_gender())){
			//设置按性别查询的条件
			detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		//调用业务层
		PageBean pageBean = linkManService.findAll(detachedCriteria,currentPage,pageSize);
		//将pageBean放入值栈
		ActionContext.getContext().getValueStack().push(pageBean);
		System.out.println(pageBean);
		
		return "findAll";
	}

	/**
	 * 跳转页面函数
	 */
	public String saveUI(){
		//查询所有客户
		List<Customer> list = customerService.findAll();
		//将list集合保存到值栈
		ActionContext.getContext().getValueStack().set("list", list);
		
		return "saveUI";
	}
	
	/**
	 * 保存客户的方法
	 */
	public String save(){
		//调用业务层保存联系人
		linkManService.save(linkMan);
		System.out.println(linkMan);
		
		return "saveSuccess";
	}
	
	/**
	 * 跳转到编辑页面的方法：edit
	 */
	public String edit(){
		//查询所有客户，进行回显
		List<Customer> list = customerService.findAll();
		//根据id查询联系人进行回显
		linkMan = linkManService.findById(linkMan.getLkm_id()); 
		//将集合和对象进行回显;注意：这种写法标签中存在name可以自动回显
		ActionContext.getContext().getValueStack().set("list", list);
		ActionContext.getContext().getValueStack().push(linkMan);
		
		return "editSuccess";
	}
	
	/**
	 * 修改联系人信息并保存
	 */
	public String update(){
		//调用业务层
		linkManService.update(linkMan);
		return "updateSuccess";
	}
	
	/**
	 * 删除联系人
	 */
	public String delete(){
		//根据id先查出此联系人
		linkMan = linkManService.findById(linkMan.getLkm_id());
		//删除此联系人
		linkManService.delete(linkMan);
		
		return "deleteSuccess";
	}
	
}
